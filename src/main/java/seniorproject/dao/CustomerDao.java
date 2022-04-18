package seniorproject.dao;

import java.util.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import seniorproject.model.Customer;
import seniorproject.util.HibernateUtil;

public class CustomerDao {
	public Customer getCustomer(int id) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			Customer customer = session.get(Customer.class, id);
			transaction.commit();
			return customer;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
			return null;
		}
	}

	public void addCustomer(Customer cus) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.save(cus);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
	
	public void deleteCustomer(Customer cus) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.delete(cus);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
	
	public void updateCustomer(Customer cus) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.update(cus);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
	
	public static String generateQueryCustomerTest(String name, String address, String phone, String email) {
		String queryString = "SELECT * FROM customer";
		
		 
		 List<String> myList = new ArrayList<String>();
		 if(!name.isBlank()) {myList.add(" name LIKE '" + name + "'");}
		 if(!address.isBlank()) {myList.add(" address LIKE '" + address + "'");}
		 if(!phone.isBlank()) {myList.add(" phone LIKE '" + phone + "'");}
		 if(!email.isBlank()) {myList.add(" email LIKE '" + email + "'");}

		if(!myList.isEmpty()) {queryString += " WHERE" + myList.get(0);}
		for (int i = 1; i < myList.size(); i++) {
			queryString += " AND" + myList.get(i);
		}
		return queryString;
	}
	
	public static List<Customer> generateQueryCustomer(String name, String address, String phone, String email) {
		 
		String queryString = "SELECT * FROM customer";
		List<Customer> myCustomerList;
		List<String> myList = new ArrayList<String>();
		 if(!name.isBlank()) {myList.add(" name LIKE '" + name + "'");}
		 if(!address.isBlank()) {myList.add(" address LIKE '" + address + "'");}
		 if(!phone.isBlank()) {myList.add(" phone LIKE '" + phone + "'");}
		 if(!email.isBlank()) {myList.add(" email LIKE '" + email + "'");}

		if(!myList.isEmpty()) {queryString += " WHERE" + myList.get(0);
			for (int i = 1; i < myList.size(); i++) {
				queryString += " AND" + myList.get(i);
			}
			System.out.println(queryString);
			myCustomerList = getCustomer(queryString);
			if(myCustomerList.size() == 0) {
				System.out.println("No results found. Expanding search results.");
				queryString = "SELECT * FROM customer";
				for (int i = 1; i < myList.size(); i++) {
					queryString += " OR" + myList.get(i);
				}
				System.out.println(queryString);
				myCustomerList = getCustomer(queryString);
			}
			return myCustomerList;
		}
		myCustomerList = getCustomer(queryString);
		return myCustomerList;
		//return queryString;
	}

	public static List<Customer> getCustomer(String query) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			List<Customer> Customer = session.createNativeQuery(query, Customer.class).getResultList();
			transaction.commit();
			return Customer;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
			return null;
		}
	}
	
	public static List<Customer> getAllCustomer(){
		return getCustomer("SELECT * FROM customer");
	}
	
}





/* if (!empty of name)
//  myList.append( "Name CONTAINS " + name);
//repeat
//repeat
//repeat
//if myList.notEmpty
queryString +=" WHERE ";
queryString += myList[0]
*/
