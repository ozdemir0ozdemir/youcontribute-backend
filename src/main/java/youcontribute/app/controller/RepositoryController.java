package youcontribute.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import youcontribute.app.controller.request.CreateRepositoryRequest;
import youcontribute.app.controller.response.RepositoryResponse;
import youcontribute.app.model.Issue;
import youcontribute.app.service.IssueService;
import youcontribute.app.service.RepositoryService;

import java.util.List;

@RestController
@RequestMapping("/repository")
public record RepositoryController( RepositoryService service,
                                    IssueService issueService ) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody CreateRepositoryRequest request) {
        this.service.create(request. getOrganization(), request.getRepository());
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<RepositoryResponse> findAll() {
        return RepositoryResponse.createFor(this.service.findAll());
    }

    @GetMapping("{repositoryId}/issues")
    @ResponseStatus(HttpStatus.OK)
    public List<Issue> findIssuesByRepositoryId(@PathVariable Long repositoryId) {
        return issueService.findByRepositoryId(repositoryId);
    }

}
