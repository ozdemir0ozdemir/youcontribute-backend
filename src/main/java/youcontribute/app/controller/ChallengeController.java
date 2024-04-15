package youcontribute.app.controller;

import org.springframework.web.bind.annotation.*;
import youcontribute.app.controller.request.UpdateChallengeStatusRequest;
import youcontribute.app.model.IssueChallenge;
import youcontribute.app.service.IssueChallengeService;

import java.util.List;

@RestController
@RequestMapping("/challenge")
public record ChallengeController( IssueChallengeService issueChallengeService ) {

    @PatchMapping("/{challengeId}")
    public void updateStatus(@PathVariable Long challengeId,
                             @RequestBody UpdateChallengeStatusRequest request) {

        System.out.println("\n\nCHALLENGE ID: " + challengeId + ", Status : " + request.getStatus()+"\n\n");

    }

    @GetMapping
    public List<IssueChallenge> findAll() {
        return issueChallengeService.findAll();
    }
}
