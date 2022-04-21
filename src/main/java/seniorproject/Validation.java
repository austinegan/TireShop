package seniorproject;

import java.util.regex.Pattern;

import org.eclipse.swt.widgets.Text;

import seniorproject.model.Customer;
import seniorproject.model.Inventory;

public class Validation {
	
	
	
	// Limits the amount of characters to ensure the database accepts the values
	// Also prevents name and phone number from being null
	public static boolean customerIsValid(Customer cus, Text error) {
		boolean isValid;
		String msg = "";
		if (cus.getName().length() == 0)
			msg += "Name is a required field. "; 
		if (cus.getName().length() > 45)
			msg += "Name must be 45 characters or less. "; 
		if (cus.getPhone().length() == 0)
			msg += "Phone number is a required field. ";
		if (cus.getPhone().length() > 25 || cus.getPhone().length() < 10)
			msg += "Phone number must be 10-25 characters long. ";
		if (cus.getEmail().length() > 45)
			msg += "Email must be 45 characters or less. "; 
		if (cus.getAddress().length() > 225)
			msg += "Address must be 225 characters or less. ";
		
		error.setText(msg);
		
		if (msg != "") {
			isValid = false;
		}else {
			isValid = true;
		}	
		
		return isValid;
	}
	
	public static boolean inventoryIsValid(Inventory inv, Text error, String msg) {
		boolean isValid;
		
		if (inv.getBrand().length() == 0)
			msg += "Brand is a required field. ";  
		if (inv.getModel_number().length() == 0)
			msg += "Model is a required field. ";
		if (inv.getSize().length() == 0)
			msg += "Size is a required field. ";
		if (!Pattern.matches("[0-9]{3}[/][0-9]{2}[a-zA-Z][0-9]{2}", inv.getSize()))
			msg += "The format for size must be ###/##A##. ";
			
		try {
			String count = Integer.toString(inv.getCount());
			if (count.length() == 0)
				msg += "Quantity is a required field. ";
		} catch (NumberFormatException e){
			msg += "Quantity must be an integer. ";
		}
		try {
			String purPrice = Double.toString(inv.getPurchase_price());
			if (purPrice.length() == 0)
				msg += "Purchase price is a required field. ";
		} catch (NumberFormatException e){
			msg += "Purchase price must be a double. ";
		}
		try {
			String salePrice = Double.toString(inv.getSale_price());
			if (salePrice.length() == 0)
				msg += "Sale price is a required field. ";
		} catch (NumberFormatException e){
			msg += "Sale price must be a double. ";
		}
		System.out.println(msg);
		error.setText(msg);
		
		if (msg != "") {
			isValid = false;
		}else {
			isValid = true;
		}	
		
		return isValid;
	}
	
	//Thought these two functions may be useful, but didn't end up using them
	public static boolean isDouble(String str) {
		boolean isDouble = true;
		try {
			double i = Double.parseDouble(str);
		} catch (NumberFormatException e){
			isDouble = false;
			return isDouble;
		}
		return isDouble;
	}
	
	public static boolean isInt(String str) {
		boolean isInt = true;
		try {
			int i = Integer.parseInt(str);
		} catch (NumberFormatException e){
			isInt = false;
			return isInt;
		}
		return isInt;
	}
}
