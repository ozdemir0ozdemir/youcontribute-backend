package youcontribute.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import youcontribute.app.model.Issue;

import java.util.List;
import java.util.Optional;

public interface IssueRepository extends JpaRepository<Issue, Long> {

    List<Issue> findIssueByRepositoryId(Long repositoryId);
}
