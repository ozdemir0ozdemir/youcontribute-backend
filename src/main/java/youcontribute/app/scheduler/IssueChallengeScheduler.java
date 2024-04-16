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
public class IssueChallengeScheduler {

    private final IssueService issueService;
    private final IssueChallengeService issueChallengeService;
    private final OneSignalService oneSignalService;
    private final GithubService githubService;

    @Scheduled(fixedRateString = "${app.challenge-frequency}")
    public void challengeIssuesScheduler() {
        log.info("IssueChallengeScheduler started.");

        if (this.issueChallengeService.hasOngoingChallenge()) {
            log.warn("IssueChallengeScheduler skipping.");
            return;
        }
        Issue randomIssue = this.issueService.findRandomIssue();
        log.info("Found a random issue. Issue Id: {}", randomIssue.getId());

        IssueChallenge challenge = issueChallengeService.create(randomIssue);
        oneSignalService.sendNotification(challenge);

        log.info("IssueChallengeScheduler finished.");
    }
}
