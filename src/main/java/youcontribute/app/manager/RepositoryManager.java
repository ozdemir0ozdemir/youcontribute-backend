package youcontribute.app.manager;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import youcontribute.app.config.ApplicationProperties;
import youcontribute.app.config.GithubProperties;
import youcontribute.app.model.GithubIssueResponse;
import youcontribute.app.model.Issue;
import youcontribute.app.model.Repository;
import youcontribute.app.service.GithubService;
import youcontribute.app.service.IssueService;
import youcontribute.app.service.RepositoryService;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RepositoryManager {

    private final RepositoryService repositoryService;
    private final GithubService githubService;
    private final IssueService issueService;
    private final ApplicationProperties applicationProperties;

    public void importRepository(String organization, String repository) {
        this.repositoryService.create(organization, repository);
    }

    @Async
    public void importIssues(Repository repository) {
        ZonedDateTime date = ZonedDateTime.now().minusSeconds(applicationProperties.getImportFrequency() / 1000);
//        ZonedDateTime date = ZonedDateTime.now().minusMonths(1L);
        GithubIssueResponse[] issues =
                this.githubService.listIssues(repository.getOrganization(), repository.getRepository(), date);

        if(issues.length == 0){
            return;
        }

        List<Issue> issueList = Arrays.stream(issues)
                .map(issue -> Issue.createFor(issue, repository) )
                .collect(Collectors.toList());

        issueService.saveAll(issueList);

    }




}
