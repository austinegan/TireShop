package seniorproject.dao;

import java.util.List;
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
	
	public String generateQuery(String name, String address, String phone, String email) {
		String queryString = "SELECT * FROM CUSTOMER";
		/*
		// List<String> myList;
		//if (!empty of name)
		//  myList.append( "Name CONTAINS " + name);
		//repeat
		//repeat
		//repeat
		//if myList.notEmpty
		queryString +=" WHERE ";
		queryString += myList[0]
		//for (int i = 1; i < myList.length; i++)
			queryString += " AND " + myList[i];
	
		 */
	}

	public List<Customer> getCustomer(String query) {
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
	
	public List<Customer> getAllCustomer(){
		return getCustomer("SELECT * FROM customer");
	}
	
}
