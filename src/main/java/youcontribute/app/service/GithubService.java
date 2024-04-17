package youcontribute.app.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import youcontribute.app.config.GithubProperties;
import youcontribute.app.model.GithubAccessTokenReponse;
import youcontribute.app.model.GithubIssueResponse;
import youcontribute.app.model.GithubPullResponse;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Service
public record GithubService( RestTemplate restTemplate,
                             GithubProperties githubProperties ) {


    public GithubIssueResponse[] listIssues(String organization, String repository, ZonedDateTime date) {

        // since YYYY-MM-DDTHH:MM:SSZ
        String issuesUrl = String.format("%s/repos/%s/%s/issues?since=%s",
                this.githubProperties.getApiUrl(),
                organization,
                repository,
                date.format(DateTimeFormatter.ISO_INSTANT)
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

    public GithubPullResponse[] listPulls(String organization, String repository) {

        String pullsUrl = String.format("%s/repos/%s/%s/pulls",
                this.githubProperties.getApiUrl(),
                organization,
                repository
        );

        HttpHeaders headers = new HttpHeaders();
        headers.add( HttpHeaders.AUTHORIZATION , "token " + this.githubProperties.getToken());

        HttpEntity<?> httpEntity = new HttpEntity<>(headers);

        ResponseEntity<GithubPullResponse[]> response =
                restTemplate.exchange( pullsUrl,
                        HttpMethod.GET,
                        httpEntity,
                        GithubPullResponse[].class
                );

        return response.getBody();
    }

    public String getAuthorizeUrl() {
        return String.format( "%s?client_id=%s",
                githubProperties.getOAuthUrl(),
                githubProperties.getOAuthId()
        );
    }

    public GithubAccessTokenReponse getAccessToken(String code) {

        Map<String, String> params = new HashMap<>();
        params.put("client_id", githubProperties.getOAuthId());
        params.put("client_secret", githubProperties.getOAuthSecret());
        params.put("code", code);

        // TODO: nullpointerexception may thrown
        GithubAccessTokenReponse response = this.restTemplate.postForObject(
                "https://github.com/login/oauth/access_token?client_id={client_id}&client_secret={client_secret}&code={code}",
                null,
                GithubAccessTokenReponse.class,
                params
        );

        return response;
    }

}
