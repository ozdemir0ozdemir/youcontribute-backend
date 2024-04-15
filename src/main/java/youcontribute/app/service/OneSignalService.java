package youcontribute.app.service;


import com.currencyfair.onesignal.OneSignal;
import com.currencyfair.onesignal.model.notification.Button;
import com.currencyfair.onesignal.model.notification.NotificationRequest;
import org.springframework.stereotype.Service;
import youcontribute.app.config.ApplicationProperties;
import youcontribute.app.model.IssueChallenge;

import java.util.List;
import java.util.Map;

@Service

public record OneSignalService( ApplicationProperties applicationProperties ) {


    public void sendNotification(IssueChallenge challenge) {


        Button acceptButton = new Button(
                "accept-button",
                "Accept",
                "",
                "http://localhost:4200/challenge/" + challenge.getId() + "/accept"
        );

        Button rejectButton = new Button(
                "reject-button",
                "Reject",
                "",
                "http://localhost:4200/challenge/" + challenge.getId() + "/reject"
        );

        String message = String.format("Would you like to solve the issue? \nRepository : %s \nIssue : %s",
                challenge.getIssue().getRepository().getRepository(),
                challenge.getIssue().getTitle()
        );


        NotificationRequest notificationRequest = new NotificationRequest();

        notificationRequest.setWebButtons(List.of(acceptButton, rejectButton));
        notificationRequest.setTemplateId(applicationProperties.getOneSignalProperties().getTemplateId());
        notificationRequest.setHeadings(Map.of("en", "New Challenge Available"));
        notificationRequest.setContents(Map.of("en", message));

        notificationRequest.setAppId(applicationProperties.getOneSignalProperties().getAppId());
        notificationRequest.setIncludedSegments(List.of("Total Subscriptions"));

        OneSignal.createNotification(
                applicationProperties.getOneSignalProperties().getApiAuthKey(),
                notificationRequest
        );

    }
}
