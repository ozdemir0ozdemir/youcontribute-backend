package youcontribute.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import youcontribute.app.model.Repository;

import java.util.Optional;


public interface RepositoryRepository extends JpaRepository<Repository, Long> {

    Repository findByOrganizationOrderByIdDesc(String organization);

    Optional<Repository> findByOrganizationAndRepository(String organization, String repository);
}
