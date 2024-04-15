package youcontribute.app.controller.response;

import lombok.Builder;
import lombok.Data;
import youcontribute.app.model.Repository;

import java.util.List;

@Data
@Builder
public class RepositoryResponse {

    private Long id;
    private String repository;
    private String organization;


    public static RepositoryResponse createFor(Repository repository) {
        return RepositoryResponse.builder()
                .id(repository.getId())
                .repository(repository.getRepository())
                .organization(repository.getOrganization())
                .build();
    }

    public static List<RepositoryResponse> createFor(List<Repository> repositories) {
        return repositories.stream()
                .map(RepositoryResponse::createFor)
                .toList();
    }
}
