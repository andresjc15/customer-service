package pe.com.nttdata.customer.model.document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

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

    //@JsonIgnoreProperties(value={"enterprise_customers", "personal_customers", "hibernateLazyInitializer", "handler"}, allowSetters=true)
    //private List<String> accounts;

    private boolean isActive;
    private Date createdAt;
    private Date updatedAt;

}
