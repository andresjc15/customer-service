package pe.com.nttdata.customer.model.request;

import lombok.Data;
import pe.com.nttdata.customer.model.document.Customer;

@Data
public class EnterpriseCustomerRequest extends Customer {

    private String businessName;
    private Long ruc;

}
