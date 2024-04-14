package youcontribute.app.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GithubIssueResponse (

    String url,

    @JsonProperty("id")
    Long githubId,

    String state,
    String title,
    String body,

    @JsonProperty("number")
    Integer issueNumber ){

}
