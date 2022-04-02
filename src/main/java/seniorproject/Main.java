package seniorproject;

import java.sql.Connection;

public class Main {

	public static void main(String[] args) {
		
		Connection connection = null;
		connection = database.connect(connection);
		database.disconnect(connection);

//		Txt.setup();
//		Txt.sendMessage();
//		Txt.sendMessage("+17279027098", "now with custom messages! :)");


			GenerateUI.main(args);


	}

}
