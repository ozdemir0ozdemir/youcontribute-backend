package youcontribute.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import youcontribute.app.model.Issue;

import java.util.List;
import java.util.Optional;

public interface IssueRepository extends JpaRepository<Issue, Long> {

    List<Issue> findIssueByRepositoryId(Long repositoryId);

    @Query(value = "select * from issue where id not in (select issue_id from issue_challenge) order by random() limit 1;", nativeQuery = true)
    Optional<Issue> findRandomIssue();
}
