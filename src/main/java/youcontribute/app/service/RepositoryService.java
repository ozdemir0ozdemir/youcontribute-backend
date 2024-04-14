package youcontribute.app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import youcontribute.app.controller.request.CreateRepositoryRequest;
import youcontribute.app.model.Repository;
import youcontribute.app.repository.RepositoryRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RepositoryService {

    private final RepositoryRepository repository;


    public List<Repository> findAll() {
        return repository.findAll();
    }

    @Transactional
    public void create(String organization, String repository) {
        Repository repo = Repository.builder()
                .organization(organization)
                .repository(repository)
                .build();

        this.repository.save(repo);
    }
}
