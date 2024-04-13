package youcontribute.app.manager;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import youcontribute.app.model.GithubIssueResponse;
import youcontribute.app.model.Issue;
import youcontribute.app.model.Repository;
import youcontribute.app.service.GithubService;
import youcontribute.app.service.IssueService;
import youcontribute.app.service.RepositoryService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RepositoryManager {

    private final RepositoryService repositoryService;
    private final GithubService githubService;
    private final IssueService issueService;

    public RepositoryManager(RepositoryService repositoryService, GithubService githubService, IssueService issueService) {
        this.repositoryService = repositoryService;
        this.githubService = githubService;
        this.issueService = issueService;
    }

    public void importRepository(String organization, String repository) {
        this.repositoryService.create(organization, repository);
    }

    @Async
    public void importIssues(Repository repository) {
        GithubIssueResponse[] issues =
                this.githubService.listIssues(repository.getOrganization(), repository.getRepository());

        if(issues.length == 0){
            return;
        }

        List<Issue> issueList = Arrays.stream(issues)
                .map(issue -> Issue.createFor(issue, repository) )
                .collect(Collectors.toList());

        issueService.saveAll(issueList);

    }




}
