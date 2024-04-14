package youcontribute.app.exception;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class RepositoryAlreadyExistsException extends RuntimeException {

    public RepositoryAlreadyExistsException(String organization, String repository) {
        super( String.format("Repository (%s) with the organization (%s) is already exists", repository, organization) );

    }
}
