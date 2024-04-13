package youcontribute.app.controller.request;

import lombok.Data;

@Data
public class CreateRepositoryRequest {

    private String repository;
    private String organization;
}
