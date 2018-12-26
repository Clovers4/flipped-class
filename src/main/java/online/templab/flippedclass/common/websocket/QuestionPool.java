package online.templab.flippedclass.common.websocket;

import online.templab.flippedclass.entity.Attendance;
import online.templab.flippedclass.entity.Question;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class QuestionPool {

    /**
     * Key 为 AttendanceId
     * Value 为 这个 Attendance 对应的提问列表
     */
    private Map<Long, List<Question>> map = new ConcurrentHashMap();

    private Random random = new Random();

    public void put(Long attendanceId, Question question) {
        List<Question> questionList = map.getOrDefault(attendanceId, new LinkedList<>());
        for (Question questionInList : questionList) {
            // 这个人已经提问了,因此现在不能再提问了
            if (questionInList.getStudentId().equals(question.getStudentId())) {
                return;
            }
        }
        questionList.add(question);
        map.put(attendanceId, questionList);
    }

    public Question pick(Long attendanceId) {
        List<Question> questionList = map.get(attendanceId);
        if (questionList.size() == 0 || questionList == null) {
            return null;
        }
        int randomIndex = random.nextInt(questionList.size());
        Question question = questionList.get(randomIndex);

        // 从map中移除
        questionList.remove(question);
        map.put(attendanceId, questionList);
        return question;
    }

    public Integer size(Long attendanceId) {
        List<Question> questions = map.get(attendanceId);
        if (questions == null || questions.size() == 0) {
            return 0;
        } else {
            return questions.size();
        }
    }
}
