package youcontribute.app.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import youcontribute.app.config.GithubProperties;
import youcontribute.app.model.GithubIssueResponse;

@Service
public record GithubService( RestTemplate restTemplate,
                             GithubProperties githubProperties ) {



    public GithubIssueResponse[] listIssues(String organization, String repository) {

        // since YYYY-MM-DDTHH:MM:SSZ

        String issuesUrl = String.format("%s/repos/%s/%s/issues",
                this.githubProperties.getApiUrl(),
                organization,
                repository
        );

        HttpHeaders headers = new HttpHeaders();
        headers.add( HttpHeaders.AUTHORIZATION , "token " + this.githubProperties.getToken());

        HttpEntity<?> httpEntity = new HttpEntity<>(headers);

        ResponseEntity<GithubIssueResponse[]> response =
                restTemplate.exchange( issuesUrl,
                                       HttpMethod.GET,
                                       httpEntity,
                                       GithubIssueResponse[].class
                );

        return response.getBody();
    }

}
