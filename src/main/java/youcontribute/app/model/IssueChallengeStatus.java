package youcontribute.app.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public enum IssueChallengeStatus implements Serializable {

    PENDING("PENDING"),
    ACCEPTED("ACCEPTED"),
    REJECTED("REJECTED"),
    COMPLETED("COMPLETED");

    private String value;

    IssueChallengeStatus(String value) {
        this.value = value;
    }

    public static List<IssueChallengeStatus> ongoingStatuses() {
        return Arrays.stream(IssueChallengeStatus.values())
                .filter(status -> PENDING.equals(status) ||  ACCEPTED.equals(status) )
                .toList();
    }

}
