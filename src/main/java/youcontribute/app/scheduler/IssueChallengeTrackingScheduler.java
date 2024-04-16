package youcontribute.app.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import youcontribute.app.model.Issue;
import youcontribute.app.model.IssueChallenge;
import youcontribute.app.model.IssueChallengeStatus;
import youcontribute.app.model.Repository;
import youcontribute.app.service.GithubService;
import youcontribute.app.service.IssueChallengeService;
import youcontribute.app.service.IssueService;
import youcontribute.app.service.OneSignalService;

import java.util.Arrays;

@Component
@Slf4j
@RequiredArgsConstructor
public class IssueChallengeTrackingScheduler {

    private final IssueChallengeService issueChallengeService;
    private final GithubService githubService;

    @Scheduled(fixedRateString = "${app.challenge-tracking-frequency}")
    public void challengeIssuesScheduler() {
        log.info("IssueChallengeTrackingScheduler started.");

        this.issueChallengeService.findAll()
                .stream()
                .filter( issueChallenge -> IssueChallengeStatus.ACCEPTED.equals(issueChallenge.getStatus()) )
                .forEach( issueChallenge -> {
                    Repository repository = issueChallenge.getIssue().getRepository();
                    Arrays.stream(this.githubService.listPulls(repository.getOrganization(), repository.getRepository()))
                            .filter(pull -> "ozdemir0ozdemir".equals(pull.getUser().getLogin()) && "closed".equals(pull.getState()))
                            .findFirst()
                            .ifPresent( pullResponse -> {
                                log.info("Issue Resolved!");
                                // TODO: complete challenge
                            });
                });
        log.info("IssueChallengeTrackingScheduler finished.");
    }
}
