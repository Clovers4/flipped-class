package online.templab.flippedclass.common.websocket;

import online.templab.flippedclass.entity.Question;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class QuestionPool {

    /**
     * Key 为 AttendanceId
     * Value 为 这个 Attendance 对应的提问列表
     */
    private Map<Long, List<Question>> map = new HashMap<>();

    private Random random = new Random();

    public void put(Long attendanceId, Question question) {
        List<Question> questionList = map.getOrDefault(attendanceId, new LinkedList<>());
        questionList.add(question);
        map.put(attendanceId, questionList);
    }

    public Question pick(Long attendanceId) {
        List<Question> questionList = map.get(attendanceId);
        if (questionList == null) {
            return null;
        }
        int randomIndex = random.nextInt(questionList.size());
        Question question = questionList.get(randomIndex);

        // 从map中移除
        questionList.remove(question);
        map.put(attendanceId, questionList);
        return question;
    }
}
