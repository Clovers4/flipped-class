package online.templab.flippedclass.common.websocket;

import lombok.Data;
import lombok.Getter;
import lombok.experimental.Accessors;
import online.templab.flippedclass.entity.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wk
 */
@Getter
@Accessors(chain = true)
public class SeminarMonitor {

    /**
     * klassSeminarId
     */
    private Long klassSeminarId;

    /**
     * 展示组
     */
    private List<Attendance> enrollList;

    /**
     * K attendanceId
     * V preScore
     */
    private Map<String, Integer> preScoreMap;

    /**
     * 当前的展示到的Index
     */
    private Integer onPreAttendanceIndex;

    /**
     * 当前正在进行的展示
     */
    private Attendance onPreAttendance;

    /**
     * 当前提问数量
     */
    private Integer raisedQuestionsCount;

    /**
     * K attendanceId
     * V preScore
     */
    private Map<String, List<Question>> askedQuestion;

    private SeminarState state;

    /**
     * Key 为 AttendanceId
     * Value 为 这个 Attendance 对应的提问列表
     */
    private Map<Long, List<Question>> waitingQuestionsMap;

    private Random random = new Random();

    /**
     * 初始化，完成基础设置
     *
     * @param klassSeminarId
     * @param enrollList
     */
    public SeminarMonitor(Long klassSeminarId, List<Attendance> enrollList) {
        this.klassSeminarId = klassSeminarId;
        this.enrollList = enrollList;
        this.preScoreMap = new HashMap<>(6);
        for (Attendance enroll : enrollList) {
            preScoreMap.put(String.valueOf(enroll.getId()), -1);
        }
        this.onPreAttendanceIndex = 0;
        this.onPreAttendance = enrollList.get(0);
        this.raisedQuestionsCount = 0;
        this.askedQuestion = new HashMap<>();
        for (Attendance enroll : enrollList) {
            this.askedQuestion.put(String.valueOf(enroll.getId()), new LinkedList<>());
        }
        this.state = new SeminarState().setProgressState("PAUSE").setTimeStamp(0L);
        // 初始化 waitingQuestionsMap
        this.waitingQuestionsMap = new ConcurrentHashMap();
        for (Attendance enroll : enrollList) {
            this.waitingQuestionsMap.put(enroll.getId(), new LinkedList<>());
        }
    }


    /**
     * 更新 monitor中的展示分数
     *
     * @param attendanceId
     * @param score
     */
    public void markPreScore(Long attendanceId, Integer score) {
        preScoreMap.put(String.valueOf(attendanceId), score);
    }

    /**
     * 切换到下一个展示
     * 功能：
     * 重设 state
     * 清空当前 attendance的提问池
     * 若没有下一个，则不切换,并将state设置为 TERMINATE
     */
    public void switchToNextAttendance() {
        // 重设 state
        this.state.setProgressState("PAUSE")
                .setTimeStamp(0L);
        // 清空当前 attendance的提问池
        this.clearOnPreAttendanceWaitingQuestions();
        // Index 增加
        this.onPreAttendanceIndex++;
        // 还有下一个,则需要设置OnPreAttendance
        if (this.onPreAttendanceIndex < this.enrollList.size()) {
            this.onPreAttendance = enrollList.get(onPreAttendanceIndex);
        }
        // 该讨论课已经结束
        else {
            state.setProgressState("TERMINATE");
        }
    }

    /**
     * 将某个 question放入提问池
     *
     * @param attendanceId
     * @param question
     */
    public void putWaitingQuestion(Long attendanceId, Question question) {
        List<Question> questionList = waitingQuestionsMap.getOrDefault(attendanceId, new LinkedList<>());
        for (Question questionInList : questionList) {
            // 这个人已经提问了,因此现在不能再提问了
            if (questionInList.getStudentId().equals(question.getStudentId())) {
                return;
            }
        }
        questionList.add(question);
        this.waitingQuestionsMap.put(attendanceId, questionList);
        // 更新monitor的当前提问数量
        this.raisedQuestionsCount = this.waitingQuestionsMap.get(attendanceId).size();
    }

    /**
     * 根据某个算法抽取一个 question,并将这个question放入 askedWaiting
     *
     * @param attendanceId
     * @return
     */
    public Question pickWaitingQuestions(Long attendanceId) {
        // 获得 waitingQuestionList,若没有则返回 null
        List<Question> waitingQuestionList = waitingQuestionsMap.get(attendanceId);
        if (waitingQuestionList.size() == 0 || waitingQuestionList == null) {
            return null;
        }
        // 根据某个算法抽取一个 question
        int randomIndex = random.nextInt(waitingQuestionList.size());
        Question question = waitingQuestionList.get(randomIndex);

        // 从 waitingQuestionList中移除
        waitingQuestionList.remove(question);
        waitingQuestionsMap.put(attendanceId, waitingQuestionList);
        // 放入 askedQuestionList
        this.putAskedQuestion(attendanceId,question);
        // 更新monitor的当前提问数量
        this.raisedQuestionsCount = this.waitingQuestionsMap.get(attendanceId).size();
        return question;
    }

    /**
     * 获得当前 attendance的提问池中的数量
     *
     * @return
     */
    public Integer sizeOfOnPreAttendanceWaitingQuestions() {
        List<Question> questions = waitingQuestionsMap.get(this.onPreAttendance.getId());
        if (questions == null || questions.size() == 0) {
            return 0;
        } else {
            return questions.size();
        }
    }

    /**
     * 获得某个 attendance的已提问列表
     *
     * @param attendanceIdx
     * @param questionIdx
     * @return
     */
    public Question getAskedQuestion(Integer attendanceIdx, Integer questionIdx) {
        Attendance enroll=enrollList.get(attendanceIdx);
        return getAskedQuestion(enroll.getId()).get(questionIdx);
    }

    /**
     * 获得某个 attendance的已提问列表
     *
     * @param attendanceId
     * @return
     */
    private List<Question> getAskedQuestion(Long attendanceId) {
        return askedQuestion.getOrDefault(String.valueOf(attendanceId), new LinkedList<>());
    }

    /**
     * 将某个 question放入某个 attendance的已提问列表
     *
     * @param attendanceId
     * @param question
     */
    private void putAskedQuestion(Long attendanceId, Question question) {
        List<Question> questions = askedQuestion.getOrDefault(String.valueOf(attendanceId), new LinkedList<>());
        questions.add(question);
        askedQuestion.put(String.valueOf(attendanceId), questions);
    }

    /**
     * 清空当前 attendance的提问池
     */
    private void clearOnPreAttendanceWaitingQuestions() {
        this.waitingQuestionsMap.remove(this.onPreAttendance.getId());
    }

}
