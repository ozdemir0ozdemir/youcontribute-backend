package youcontribute.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import youcontribute.app.model.Issue;

public interface IssueRepository extends JpaRepository<Issue, Long> {
}
