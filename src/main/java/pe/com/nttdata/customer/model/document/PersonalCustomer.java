package pe.com.nttdata.customer.model.document;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "personal_customers")
public class PersonalCustomer {

    private String name;
    private String lastName;

}
