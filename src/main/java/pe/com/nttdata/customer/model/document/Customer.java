package pe.com.nttdata.customer.model.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import javax.validation.constraints.*;
import java.util.Date;

@Data
public class Customer {

    @Transient
    public static final String SEQUENCE_NAME = "customers_sequence";

    @Id
    private Long id;

    @NotEmpty(message = "Address can't be empty")
    private String address;

    @Max(value = 999999999, message = "Invalid DNI")
    @Min(value = 999999, message = "Invalid DNI")
    @NotNull(message = "DNI can't be null")
    private Integer dni;

    @Max(value = 999999999, message = "Invalid phone")
    @Min(value = 99999999, message = "Invalid phone")
    @NotNull(message = "DNI can't be null")
    private Integer phone;

    @NotBlank(message = "Email can't be null")
    @Email(message = "Invalid email")
    private String email;

    private boolean isActive;
    private Date createdAt;
    private Date updatedAt;

}
