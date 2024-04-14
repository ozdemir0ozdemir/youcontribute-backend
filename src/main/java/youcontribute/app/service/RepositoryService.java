package youcontribute.app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import youcontribute.app.exception.RepositoryAlreadyExistsException;
import youcontribute.app.model.Repository;
import youcontribute.app.repository.RepositoryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RepositoryService {

    private final RepositoryRepository repositoryRepository;


    public List<Repository> findAll() {
        return repositoryRepository.findAll();
    }

    @Transactional
    public void create(String organization, String repository) {
        validate(organization, repository);
        Repository repo = Repository.builder()
                .organization(organization)
                .repository(repository)
                .build();

        this.repositoryRepository.save(repo);
    }

    private void validate(String organization, String repository){
        this.repositoryRepository.findByOrganizationAndRepository(organization, repository)
                .ifPresent( r -> {
                    throw new RepositoryAlreadyExistsException(r.getOrganization(), r.getRepository());
                });
    }
}
