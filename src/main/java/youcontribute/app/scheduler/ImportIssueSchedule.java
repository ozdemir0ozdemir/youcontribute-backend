package youcontribute.app.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import youcontribute.app.manager.RepositoryManager;
import youcontribute.app.model.Repository;
import youcontribute.app.service.RepositoryService;

import java.util.List;

@Component
@Slf4j
public record ImportIssueSchedule( RepositoryManager repositoryManager,
                                   RepositoryService repositoryService ) {

    @Scheduled(fixedRateString = "${app.import-frequency}")
    public void importIssues() {
        log.info("Import scheduler started!");

        List<Repository> repositoryList = this.repositoryService.findAll();
        repositoryList.forEach(this.repositoryManager::importIssues);

        log.info("Import scheduler finished!");
    }
}
