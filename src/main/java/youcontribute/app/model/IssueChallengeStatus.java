package youcontribute.app.model;

import java.util.Arrays;
import java.util.List;

public enum IssueChallengeStatus {

    PENDING("PENDING"),
    ACCEPTED("ACCEPTED"),
    REJECTED("REJECTED"),
    PROGRESSING("PROGRESSING"),
    COMPLETED("COMPLETED");

    private String value;

    IssueChallengeStatus(String value) {
        this.value = value;
    }

    public static List<IssueChallengeStatus> ongoingStatuses() {
        return Arrays.stream(IssueChallengeStatus.values())
                .filter(status -> PENDING.equals(status) ||  ACCEPTED.equals(status) ||  PROGRESSING.equals(status) )
                .toList();
    }
}
