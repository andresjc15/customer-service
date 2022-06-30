package pe.com.nttdata.customer.model.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
public class Customer {

    @Transient
    public static final String SEQUENCE_NAME = "customers_sequence";

    @Id
    private Long id;

    private String address;

    //@NotBlank(message = "DNI no puede ser nulo o vacio")
    private Integer dni;
    private Integer phone;

    @NotBlank(message = "Email no puede ser nulo o vacio")
    private String email;

    private boolean isActive;
    private Date createdAt;
    private Date updatedAt;

}
