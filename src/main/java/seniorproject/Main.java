package seniorproject;

import seniorproject.dao.InventoryDao;
import seniorproject.model.Inventory;

public class Main {

	public static void main(String[] args) {

//		Txt.setup();
//		Txt.sendMessage();
//		Txt.sendMessage("+17279027098", "now with custom messages! :)");

		InventoryDao inventoryDao = new InventoryDao();
		//add inventory
		//inventoryDao.addInventory(new Inventory("modrhdbtdzdel","g",1.0,2.0,3,"t",7,8,9));
		
		//remove inventory
		//inventoryDao.deleteInventory(inventoryDao.getAllInventory().get(0));
		
		//update inventory
		Inventory change = inventoryDao.getInventory(3);
		change.setCount(9001);
		inventoryDao.updateInventory(change);
		
		GenerateUI.main(args);
	}
}
