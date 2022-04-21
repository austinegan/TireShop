package seniorproject.dao;

import java.util.*;

import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.query.Query;
//import org.hibernate.Criteria;
//import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import seniorproject.model.Customer;
import seniorproject.util.HibernateUtil;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;

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
	
	public static List<Customer> generateQueryCustomer(String name, String phone, String address, String email, Boolean expandable) {	
			return generateQueryCustomer(name, phone, address, email, expandable, " ORDER BY id");
	}
	public static List<Customer> generateQueryCustomer(String name, String phone, String address, String email, Boolean expandable, String order) {
		 
		String queryString = "SELECT * FROM customer";
		List<Customer> myCustomerList;
		List<String> myList = new ArrayList<String>();
		 if(!name.isBlank()) {myList.add(" name LIKE :nam");}
		 if(!phone.isBlank()) {myList.add(" phone LIKE :pho");}
		 if(!address.isBlank()) {myList.add(" address LIKE :add");}
		 if(!email.isBlank()) {myList.add(" email LIKE :ema");}

		if(!myList.isEmpty()) {queryString += " WHERE" + myList.get(0);
			for (int i = 1; i < myList.size(); i++) {
				queryString += " AND" + myList.get(i);
			}
			queryString += order;
			System.out.println(queryString);
			myCustomerList = getCustomer(queryString, name, phone, address, email);
			if(myCustomerList.size() == 0) {
				System.out.println("No results found.");
				if (expandable) {
						System.out.println(" Expanding search results.");
				queryString = "SELECT * FROM customer WHERE" + myList.get(0);
				for (int i = 1; i < myList.size(); i++) {
					queryString += " OR" + myList.get(i);
				}
				queryString += order;
				System.out.println(queryString);
				myCustomerList = getCustomer(queryString, name, phone, address, email);
				}
				else {System.out.println("But expandable results are off. So that's all you get (0 results)");}
			}
			return myCustomerList;
		}
		myCustomerList = getCustomer(queryString);
		return myCustomerList;
	}
	

	
	public static List<Customer> getCustomer(String query, String name, String phone, String address, String email) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			Query<Customer> myQuery = session.createNativeQuery(query, Customer.class);
			if(!name.isBlank()) {myQuery.setParameter("nam", "%" + name + "%");}
			if(!phone.isBlank()) {myQuery.setParameter("pho", "%" + phone + "%");}
			if(!address.isBlank()) {myQuery.setParameter("add", "%" + address + "%");}
			if(!email.isBlank()) {myQuery.setParameter("ema", "%" + email + "%");}
			 
			List<Customer> customers = myQuery.getResultList();
			transaction.commit();
			return customers;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
			return null;
		}
	}

	public static List<Customer> getCustomer(String query) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			List<Customer> customers = session.createNativeQuery(query, Customer.class).getResultList();
			transaction.commit();
			return customers;
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

	public static List<Customer> generateQueryCustomerWithNamedParameters(String name, String address, String phone, String email) {
		 
		String queryString = "SELECT * FROM customer";
		List<Customer> myCustomerList;
		List<String> myList = new ArrayList<String>();
		 if(!name.isBlank()) {myList.add(" name LIKE '%:name%'");}
		 if(!address.isBlank()) {myList.add(" address LIKE '%:address%'");}
		 if(!phone.isBlank()) {myList.add(" phone LIKE '%:phone%'");}
		 if(!email.isBlank()) {myList.add(" email LIKE '%:email%'");}

		if(!myList.isEmpty()) {queryString += " WHERE" + myList.get(0);
			for (int i = 1; i < myList.size(); i++) {
				queryString += " AND" + myList.get(i);
			}
			System.out.println(queryString);
			myCustomerList = getCustomer(queryString);
			if(myCustomerList.size() == 0) {
				System.out.println("No results found. Expanding search results.");
				queryString = "SELECT * FROM customer WHERE" + myList.get(0);
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
	}

	public static List<Customer> generateQueryCustomerWithNamedParametersTest(String name, String address, String phone, String email) {
		 
		String queryString = "SELECT * FROM customer";
		List<Customer> myCustomerList;
		List<String> myList = new ArrayList<String>();
		 myList.add(" name LIKE '%:nam%'");
		 myList.add(" address LIKE '%:add%'");
		 myList.add(" phone LIKE '%:pho%'");
		 myList.add(" email LIKE '%:ema%'");

		if(!myList.isEmpty()) {queryString += " WHERE" + myList.get(0);
			for (int i = 1; i < myList.size(); i++) {
				queryString += " AND" + myList.get(i);
			}
			System.out.println(queryString);
			myCustomerList = getCustomer(queryString);
			if(myCustomerList.size() == 0) {
				System.out.println("No results found. Expanding search results.");
				queryString = "SELECT * FROM customer WHERE" + myList.get(0);
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
	}
	
	
	
	//	public static String getCustomerTestCriteria(String name, String phone, String address, String email) {
//		Transaction transaction = null;
//		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//			transaction = session.beginTransaction();
//			Criteria cr = session.createCriteria(Customer.class);
//			//List<Customer> customers = session.createNativeQuery(query, Customer.class).getResultList();
//			transaction.commit();
//			//return customers;
//			return ":)";
//		} catch (Exception e) {
//			if (transaction != null) {
//				transaction.rollback();
//			}
//			e.printStackTrace();
//			return null;
//		}
//	}
 * 
 * 
 * //			 Query query = session.createQuery(
//					"SELECT * FROM customer WHERE" + 
//					" name LIKE '%:nam%' " + andOR + 
//					" address LIKE '%:add%' " + andOR + 
//					" phone LIKE '%:pho%' " + andOR + 
//					" email LIKE '%:ema%'",
//					Customer.class);
//			 query.setParameter("nam",name);
//			 query.setParameter("add",phone);
//			 query.setParameter("pho",address);
//			 query.setParameter("ema",email);
			//List<Customer> customers = query.list();
			
			
			
				public static List<Customer> getCustomerTestNamedParametersNative(String name, String phone, String address, String email, String andOR) {
		Transaction transaction = null;
		List<Customer> customers;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			System.out.println("test");
			customers = session.createNativeQuery("SELECT * FROM customer WHERE name LIKE :nam AND  address LIKE :add AND phone LIKE :pho AND email LIKE :ema", Customer.class)
					.setParameter("nam", "%" + name + "%")
					.setParameter("pho", "%" + phone + "%")
					.setParameter("add", "%" + address + "%")
					.setParameter("ema", "%" + email + "%")
					.getResultList();
			System.out.println("test 2");

			transaction.commit();
			return customers;
		} catch (Exception e) {
			if (transaction != null) {
				System.out.println("transaction was null");
				transaction.rollback();
			}
			e.printStackTrace();
			return null;
		}
		
	}
	

	
	public static List<Customer> getCustomerTestNamedParameters(String name){// String phone, String address, String email, String andOR) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			System.out.println("test");
			List<Customer> customers = session.createNamedQuery("GET_CUSTOMERS_BY_NAME", Customer.class)
					//.setParameter("nam", name)
					//.setParameter("pho", phone)
					//.setParameter("add", address)
					//.setParameter("ema", email)
					.getResultList();
			System.out.println("test 2");

			transaction.commit();
			return customers;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
			return null;
		}
	}
//	
	*/

