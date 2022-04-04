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

	public List<Customer> getAllCustomer() {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			List<Customer> Customer = session.createNativeQuery("SELECT * FROM customer", Customer.class).getResultList();
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
}
