package youcontribute.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import youcontribute.app.model.IssueChallenge;
import youcontribute.app.model.IssueChallengeStatus;

import java.util.List;
import java.util.Optional;

public interface IssueChallengeRepository extends JpaRepository<IssueChallenge, Long> {

    Optional<IssueChallenge> findByStatusIn(List<IssueChallengeStatus> status);
}
