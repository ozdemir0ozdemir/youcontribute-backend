package youcontribute.app.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import youcontribute.app.model.Issue;
import youcontribute.app.repository.IssueRepository;

import java.util.List;

@Service
public class IssueService {

    private final IssueRepository issueRepository;

    public IssueService(IssueRepository issueRepository) {
        this.issueRepository = issueRepository;
    }

    public void create(Issue issue) {
        issueRepository.save(issue);
    }

    @Transactional
    public void saveAll(List<Issue> issues) {
        issueRepository.saveAll(issues);
    }
}
