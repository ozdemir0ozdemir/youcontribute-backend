package youcontribute.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import youcontribute.app.controller.request.CreateRepositoryRequest;
import youcontribute.app.controller.response.RepositoryResponse;
import youcontribute.app.service.RepositoryService;

import java.util.List;

@RestController
@RequestMapping("/repository")
public record RepositoryController( RepositoryService service ) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody CreateRepositoryRequest request) {
        this.service.create(request.getRepository(), request. getOrganization());
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<RepositoryResponse> findAll() {
        return RepositoryResponse.createFor(this.service.findAll());
    }

}
