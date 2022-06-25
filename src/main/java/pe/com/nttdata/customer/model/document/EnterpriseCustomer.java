package pe.com.nttdata.customer.model.document;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "enterprise_customers")
public class EnterpriseCustomer extends Customer {

    private String businessName;
    private Long RUC;

}
