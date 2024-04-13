package youcontribute.app.service;

import org.springframework.stereotype.Service;
import youcontribute.app.controller.request.CreateRepositoryRequest;
import youcontribute.app.model.Repository;
import youcontribute.app.repository.RepositoryRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public record RepositoryService(RepositoryRepository repository) {


    public List<Repository> findAll() {
        return repository.findAll();
    }

    public void create(String organization, String repository) {
        Repository repo = Repository.builder()
                .organization(organization)
                .repository(repository)
                .build();

        repository().save(repo);
    }
}
