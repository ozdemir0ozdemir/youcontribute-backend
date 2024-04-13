package youcontribute.app.service;


import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.RestTemplate;
import youcontribute.app.config.GithubProperties;
import youcontribute.app.model.GithubIssueResponse;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.BDDAssertions.then;


@ContextConfiguration(initializers = GithubServiceTest.Initializer.class)
@SpringBootTest(classes = {GithubService.class, GithubProperties.class, RestTemplate.class})
class GithubServiceTest {


    @RegisterExtension
    static WireMockExtension extension = new WireMockExtension();

    @Autowired
    private GithubService githubService;


    @Test
    void it_should_list_all_repositories () throws Exception {
    	//given
        extension.stubFor(
                get( urlPathEqualTo( "/repos/libgdx/libgdx/issues") )
                        .willReturn( aResponse()
                                .withBodyFile("github/issues.json")
                                .withStatus(200)
                                .withHeader("Content-Type", "application/json")
                                .withHeader(HttpHeaders.AUTHORIZATION, "token ozdemirtokenozdemir")
                        )
        );



    	//when
        GithubIssueResponse[] responseList =
                this.githubService.listIssues("libgdx", "libgdx");

        //then
        GithubIssueResponse response = responseList[0];

        then( response ).isNotNull();
        then( response.url() ).isEqualTo("https://api.github.com/repos/ozdemir0ozdemir/youcontribute-backend/issues/1");
        then( response.githubId() ).isEqualTo(2241570366L);
        then( response.state() ).isEqualTo("open");
        then( response.body() ).isEqualTo("T01 - Test issue description. For testing rest api");
        then( response.title() ).isEqualTo("Test Issue - T01");
        then( response.issueNumber() ).isEqualTo(1);




    }

    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
            TestPropertyValues.of( "github.api-url=" + extension.baseUrl(),
                            "github.token=ozdemirtokenozdemir" )
                    .applyTo(applicationContext.getEnvironment());
        }
    }

}