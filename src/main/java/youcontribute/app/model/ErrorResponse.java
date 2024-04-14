package youcontribute.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {

    private String message;
    private String exception;
    private String status;
    private String occurredAt;
}
