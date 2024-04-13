package youcontribute.app.model;

import jakarta.persistence.*;

@Entity
public class Issue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Repository repository;

    private String url;
    private Long githubId;
    private String state;
    private String title;
    private String body;
    private Integer issueNumber;

    public Issue(Repository repository, String url, Long githubId, String state, String title, String body, Integer issueNumber) {
        this.repository = repository;
        this.url = url;
        this.githubId = githubId;
        this.state = state;
        this.title = title;
        this.body = body;
        this.issueNumber = issueNumber;
    }

    public Issue() {
    }

    public static Issue createFor(GithubIssueResponse response, Repository repository){
        return new Issue(
                    repository,
                    response.url(),
                    response.githubId(),
                    response.state(),
                    response.title(),
                    response.body(),
                    response.issueNumber()
                );
    }

    // GETTER & SETTER


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Repository getRepository() {
        return repository;
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getGithubId() {
        return githubId;
    }

    public void setGithubId(Long githubId) {
        this.githubId = githubId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Integer getIssueNumber() {
        return issueNumber;
    }

    public void setIssueNumber(Integer issueNumber) {
        this.issueNumber = issueNumber;
    }
}


