package pe.com.nttdata.customer.model.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import pe.com.nttdata.customer.model.request.PersonalCustomerRequest;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "personal_customers")
public class PersonalCustomer extends Customer {

    private String name;
    private String lastName;

    public PersonalCustomer(PersonalCustomerRequest personalCustomerRequest) {
        this.setAddress(personalCustomerRequest.getAddress());
        this.setDni(personalCustomerRequest.getDni());
        this.setPhone(personalCustomerRequest.getPhone());
        this.setEmail(personalCustomerRequest.getEmail());
        this.name = personalCustomerRequest.getName();
        this.lastName = personalCustomerRequest.getLastName();
    }
}
