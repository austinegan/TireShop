package seniorproject;

import java.sql.Connection;

public class Main {

	public static void main(String[] args) {
		
//		Connection connection = null;
//		connection = database.connect(connection);
//		database.disconnect(connection);

//		Txt.setup();
//		Txt.sendMessage();
//		Txt.sendMessage("+17279027098", "now with custom messages! :)");

Thing first  = new Thing(1, "brand1", 1234, 49.99, 35.0, 2, 24, 4, 32, 20);
Thing second = new Thing(2, "brand2", 1235, 59.99, 40.0, 3, 26, 2, 34, 20);
Thing third  = new Thing(3, "brand3", 1236, 35.99, 25.0, 1, 22, 3, 30, 20);

//first.print();
//second.print();
//third.print();
//System.out.println(first);


ProductList myList = new ProductList();
myList.addItem(first);
myList.addItem(second);
myList.addItem(third);
System.out.println(myList.list);
myList.clearList();
System.out.println(myList.list);
			GenerateUI.main(args);
			//GenerateUI myGen = new GenerateUI();
			
//myGen.fillProductsTable(first);

	}

}
