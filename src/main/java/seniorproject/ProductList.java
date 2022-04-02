package seniorproject;

import java.util.*;
import java.lang.*;
public class ProductList {

	List<Item> list = new ArrayList<Item>();
	/* A more efficient implementation than list, but not as familiar.
	 * Map will instantly go to the value needed instead of iterating through the list.
	 * May implement later. 
	Map<Integer, Item> map = new LinkedHashMap<>();
	*/
	
	
	public void addItem(Item addThis){
		for (Item check : list) {
			if (check.id == addThis.id) {
				check.count++;
				return;
			}
		}
		list.add(addThis);
		//need to set the count in the list of the new item to 1.
	}
	
	
	public void subtractItem(Item subtractThis){
		for (Item check : list) {
			if (check.id == subtractThis.id) {
				if (check.count > 0) {
					System.out.println("Old count " + check.count);
					check.count--;
					System.out.println("Subtracted to new count " + check.count);
				}
				if (check.count == 0) {
					//make a pop-up appear on screen. "Count reduced to 0. Would you like to remove this item from your cart?"
					//if yes, remove item from list. For now, the item is just removed automatically.
					list.remove(check);
				}
				
				return;
			}
		}
		System.out.println("Item with id " + subtractThis.id + " not found in this list");
	}
	
	public void clearList() {
		System.out.println("It's been an honor serving as your list. Goodbye o7\n List cleared");
		list.clear();
	}
	
	
	
}
