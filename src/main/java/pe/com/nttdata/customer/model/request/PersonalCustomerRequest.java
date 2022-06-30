package pe.com.nttdata.customer.model.request;

import lombok.Data;
import pe.com.nttdata.customer.model.document.Customer;

@Data
public class PersonalCustomerRequest extends Customer  {

    private String name;
    private String lastName;

}
