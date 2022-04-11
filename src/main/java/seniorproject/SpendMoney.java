package seniorproject;
import com.squareup.square.Environment;
import java.io.*;
import com.squareup.square.SquareClient;
import com.squareup.square.models.*;
import com.squareup.square.api.*;
import com.squareup.square.*;
import java.util.*;
import java.util.UUID;

public class SpendMoney {
	private static SquareClient client;
	private static CustomersApi customersApi;
	private static TerminalApi terminalApi;
	
	public static void initialize() {
        InputStream inputStream = SpendMoney.class.getResourceAsStream("/config.properties");
        Properties prop = new Properties();
        try {
            prop.load(inputStream);
        } catch (IOException e) {
            System.out.println("Error reading properties file");
            e.printStackTrace();
        }
        
		client = new SquareClient.Builder()
				  .environment(Environment.SANDBOX)
				  .accessToken(prop.getProperty("SQUARE_ACCESS_TOKEN"))
				  .build();
		customersApi = client.getCustomersApi();
		terminalApi = client.getTerminalApi();
	}

	public static void main(String[] args) {
		
		Money amountMoney = new Money.Builder()
				  .amount(2500L)
				  .currency("USD")
				  .build();

				TipSettings tipSettings = new TipSettings.Builder()
				  .build();

				DeviceCheckoutOptions deviceOptions = new DeviceCheckoutOptions.Builder("devide_id")
				  .tipSettings(tipSettings)
				  .build();

				TerminalCheckout checkout = new TerminalCheckout.Builder(amountMoney, deviceOptions)
				  .referenceId("database reference")
				  .note("note about the transaction")
				  .paymentType("CARD_PRESENT")
				  .customerId("")
				  .build();

				CreateTerminalCheckoutRequest body = new CreateTerminalCheckoutRequest.Builder(newUUID(), checkout)
				  .build();

				terminalApi.createTerminalCheckoutAsync(body)
				  .thenAccept(result -> {
				    System.out.println("Success!");
				  })
				  .exceptionally(exception -> {
				    System.out.println("Failed to make the request");
				    System.out.println(String.format("Exception: %s", exception.getMessage()));
				    return null;
				  });
		
		
		
	}
	
	public static String newUUID() {
		return UUID.randomUUID().toString();
	}


	
	public static Address addressMethod(String line1, String line2CityStateZip) {
		return new Address.Builder()
				   .addressLine1(line1)
				   .addressLine2(line2CityStateZip)
				   .build();
	}
	
	public static CreateCustomerRequest customerCreator(String givenName, String familyName, Address address) {
		 return new CreateCustomerRequest.Builder()
			    .idempotencyKey(newUUID())
			    .givenName(givenName)
			    .familyName(familyName)
			    .address(address)
			    .build();
	}
	
	public static void customerPush(CreateCustomerRequest request) {
		customersApi.createCustomerAsync(request)
	    .thenAccept(result -> {
	        System.out.printf("customer created:\t Given name = %s \t Family name = %s",
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
	
	
	public static void customerCreateAndPush(String givenName, String familyName, String address1, String address2) {
		customerPush(customerCreator(givenName, familyName, addressMethod(address1, address2)));
	}
	
}





//UUID.randomUUID().toString(); Create a new UUID for idempotency

//Address address = new Address.Builder()
//.addressLine1("1234 Main St")
//.addressLine2("Tampa, FL 33333")
//.build();
//
//
//CreateCustomerRequest request = new CreateCustomerRequest.Builder()
// .idempotencyKey(newUUID())
// .givenName("AustinTest1")
// .familyName("EganTest1")
// .address(address)
// .build();
//
//SquareClient client = new SquareClient.Builder()
//		  .environment(Environment.SANDBOX)
//		  .accessToken(prop.getProperty("SQUARE_ACCESS_TOKEN"))
//		  .build();
//
//
//customersApi.createCustomerAsync(request)
// .thenAccept(result -> {
//     System.out.printf("customer created:\n Given name =  %s Family name = %s",
//         result.getCustomer().getGivenName(),
//         result.getCustomer().getFamilyName());
// })
// .exceptionally(exception -> {
//     System.out.println("Failed to make the request");
//     System.out.println(String.format("Exception: %s", 
//         exception.getMessage()));
//     return null;
// })
// .join();