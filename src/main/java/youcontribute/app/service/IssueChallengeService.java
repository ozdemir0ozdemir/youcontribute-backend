package youcontribute.app.service;

import org.springframework.stereotype.Service;
import youcontribute.app.model.Issue;
import youcontribute.app.model.IssueChallenge;
import youcontribute.app.model.IssueChallengeStatus;
import youcontribute.app.repository.IssueChallengeRepository;

import java.util.List;

@Service
public record IssueChallengeService(IssueChallengeRepository issueChallengeRepository) {

    public IssueChallenge create(Issue issue) {
        IssueChallenge challenge = issueChallengeRepository.save(
                IssueChallenge.builder()
                        .issue(issue)
                        .status(IssueChallengeStatus.PENDING)
                        .build()
        );

        return challenge;
    }

    public boolean hasOngoingChallenge() {
       return issueChallengeRepository
               .findByStatusIn( IssueChallengeStatus.ongoingStatuses() )
               .isPresent();
    }

    public List<IssueChallenge> findAll() {
        return issueChallengeRepository.findAll();
    }
}
