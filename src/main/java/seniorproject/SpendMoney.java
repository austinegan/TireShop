package seniorproject;
import com.squareup.square.Environment;
import com.squareup.square.SquareClient;

public class SpendMoney {
	
	
	public static void main(String[] args) {
		SquareClient client = new SquareClient.Builder()
		  .environment(Environment.SANDBOX)
		  .accessToken("EAAAEA0HoegiPjZ_NwZVzYspp_tmBy_hFlmZDMqHcP2tBpjDpqkbjyon5xR_NrKD")
		  .build();


	}


}
