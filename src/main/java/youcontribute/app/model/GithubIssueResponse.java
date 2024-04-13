package youcontribute.app.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@JsonIgnoreProperties(ignoreUnknown = true)
//public class GithubIssueResponse {
//
//    private String url;
//
//    @JsonProperty("id")
//    private Long githubId;
//
//    private String state;
//    private String title;
//    private String body;
//
//    @JsonProperty("number")
//    private Integer issueNumber;
//}


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
