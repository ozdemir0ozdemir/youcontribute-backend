package youcontribute.app.scheduler;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import youcontribute.app.model.Issue;
import youcontribute.app.model.IssueChallenge;
import youcontribute.app.service.IssueChallengeService;
import youcontribute.app.service.IssueService;
import youcontribute.app.service.OneSignalService;

@Component
@Slf4j
@RequiredArgsConstructor
public class ChallengeIssuesScheduler {

    private final IssueService issueService;
    private final IssueChallengeService issueChallengeService;
    private final OneSignalService oneSignalService;

    @Scheduled(fixedRateString = "${app.challenge-frequency}")
    public void challengeIssuesScheduler(){
        log.info("Challenge issue scheduler started.");
        if(this.issueChallengeService.hasOngoingChallenge()){
            log.warn("There is already an ongoing challenge, skipping...");
            return;
        }
        Issue randomIssue = this.issueService.findRandomIssue();
        log.info("Found a random issue. Issue Id: {}", randomIssue.getId());

        IssueChallenge challenge = issueChallengeService.create(randomIssue);
        oneSignalService.sendNotification(challenge);

        log.info("Challenge issue scheduler finished.");
    }
}
