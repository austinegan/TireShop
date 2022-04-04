package seniorproject;
import com.squareup.square.Environment;
import com.squareup.square.SquareClient;
import com.squareup.square.models.*;
import com.squareup.square.api.*;
import com.squareup.square.*;
public class SpendMoney {
	
	
	public static void main(String[] args) {
		SquareClient client = new SquareClient.Builder()
		  .environment(Environment.SANDBOX)
		  .accessToken("EAAAEA0HoegiPjZ_NwZVzYspp_tmBy_hFlmZDMqHcP2tBpjDpqkbjyon5xR_NrKD")
		  .build();

		Address address = new Address.Builder()
				   .addressLine1("1455 Market St")
				   .addressLine2("San Francisco, CA 94103")
				   .build();

				CreateCustomerRequest request = new CreateCustomerRequest.Builder()
				    .idempotencyKey("12345")
				    .givenName("JohnJava2")
				    .familyName("DoeJava2")
				    .address(address)
				    .build();
				
				CustomersApi customersApi = client.getCustomersApi();
				customersApi.createCustomerAsync(request)
				    .thenAccept(result -> {
				        System.out.printf("customer created:\n Given name =  %s Family name = %s",
				            result.getCustomer().getGivenName(),
				            result.getCustomer().getFamilyName());
				    })
				    .exceptionally(exception -> {
				        System.out.println("Failed to make the request");
				        System.out.println(String.format("Exception: %s", 
				            exception.getMessage()));
				        return null;
				    })
				    .join();
	}


}
