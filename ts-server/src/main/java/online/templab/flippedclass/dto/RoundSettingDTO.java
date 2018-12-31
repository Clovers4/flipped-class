package online.templab.flippedclass.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import online.templab.flippedclass.entity.KlassRound;
import online.templab.flippedclass.entity.Round;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author wk
 */
@Data
@Accessors(chain = true)
public class RoundSettingDTO {
    private Long roundId;
    private Integer preScoreType;
    private Integer quesScoreType;
    private Integer reportScoreType;
    private Long[] klassId;
    private Integer[] enrollLimit;

    public Round getRound() {
        return new Round()
                .setId(roundId)
                .setPreScoreType(preScoreType)
                .setQuesScoreType(quesScoreType)
                .setReportScoreType(reportScoreType);
    }

    public List<KlassRound> getKlassRounds() {
        if (klassId == null || enrollLimit == null) {
            return null;
        }
        if (klassId.length != enrollLimit.length) {
            return null;
        }
        List<KlassRound> klassRounds = new LinkedList<>();
        for (int i = 0; i < klassId.length; i++) {
            KlassRound klassRound = new KlassRound()
                    .setEnrollLimit(enrollLimit[i])
                    .setKlassId(klassId[i])
                    .setRoundId(roundId);

            klassRounds.add(klassRound);
        }
        return klassRounds;
    }

   /* @Override
    public String toString() {
        return DebugLogger.toJsonString(this);
    }*/
}
