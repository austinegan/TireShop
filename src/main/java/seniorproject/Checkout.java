package seniorproject;
import com.squareup.square.Environment;
import com.squareup.square.SquareClient;
import com.squareup.square.models.*;
import com.squareup.square.api.CustomersApi;
import com.squareup.square.api.DevicesApi;
import com.squareup.square.api.TerminalApi;

import com.squareup.square.*;
//import com.squareup.square.api.DevicesApi.*;
public class Checkout {
	public static void main(String[] args) {
		SquareClient client = new
				SquareClient.Builder()
				.environment(Environment.PRODUCTION)
				.accessToken("EAAAFDOFeV_mUj86upi57IglXm_fkSrm3Gc12qNxd_FbXhBqlsmKg5RLqE9rP_5f")
				.build();
		
		Money amountMoney = new Money.Builder()
				  .amount(200L)
				  .currency("USD")
				  .build();

				DeviceCheckoutOptions deviceOptions = new DeviceCheckoutOptions.Builder("204CS149A9004201")
				  .skipReceiptScreen(false)
				  .build();

				TerminalCheckout checkout = new TerminalCheckout.Builder(amountMoney, deviceOptions)
				  .build();

				CreateTerminalCheckoutRequest body = new CreateTerminalCheckoutRequest.Builder("de59f4e1-6dcc-45b5-b150-fcb32c108d30", checkout)
				  .build();
				TerminalApi terminalApi = client.getTerminalApi();
				terminalApi.createTerminalCheckoutAsync(body)
				  .thenAccept(result -> {
				    System.out.println("Success!");
				  })
				  .exceptionally(exception -> {
				    System.out.println("Failed to make the request");
				    System.out.println(String.format("Exception: %s", exception.getMessage()));
				    return null;
				  });
	}}
	
	





