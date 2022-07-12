package pe.com.nttdata.customer.model.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import pe.com.nttdata.customer.model.request.EnterpriseCustomerRequest;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "enterprise_customers")
public class EnterpriseCustomer extends Customer {

    private String businessName;
    private Long ruc;

    public EnterpriseCustomer(Long id, String address, String businessName, Long ruc) {
        this.setId(id);
        this.setAddress(address);
        this.businessName = businessName;
        this.ruc = ruc;
    }

    public EnterpriseCustomer(EnterpriseCustomerRequest enterpriseCustomerRequest) {
        this.setId(enterpriseCustomerRequest.getId());
        this.setAddress(enterpriseCustomerRequest.getAddress());
        this.setDni(enterpriseCustomerRequest.getDni());
        this.setPhone(enterpriseCustomerRequest.getPhone());
        this.setEmail(enterpriseCustomerRequest.getEmail());
        this.businessName = enterpriseCustomerRequest.getBusinessName();
        this.ruc = enterpriseCustomerRequest.getRuc();
        this.setUpdatedAt(enterpriseCustomerRequest.getUpdatedAt());
    }
}
