package youcontribute.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import youcontribute.app.model.Repository;


public interface RepositoryRepository extends JpaRepository<Repository, Long> {

    Repository findByOrganizationOrderByIdDesc(String organization);
}
