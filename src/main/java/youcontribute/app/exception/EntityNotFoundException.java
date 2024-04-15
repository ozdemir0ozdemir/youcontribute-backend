package youcontribute.app.exception;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String message) {
        super( message );

    }
}
