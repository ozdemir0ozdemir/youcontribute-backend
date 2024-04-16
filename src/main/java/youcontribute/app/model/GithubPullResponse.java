package youcontribute.app.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GithubPullResponse {

    @JsonProperty("id")
    private Long githubId;

    private String state;

    @JsonProperty("number")
    private Integer issueNumber;

    private User user;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class User {
        private String login;
    }

}
