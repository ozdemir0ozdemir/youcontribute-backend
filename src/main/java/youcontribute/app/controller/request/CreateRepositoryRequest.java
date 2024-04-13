package youcontribute.app.controller.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateRepositoryRequest {

    private String repository;
    private String organization;
}
