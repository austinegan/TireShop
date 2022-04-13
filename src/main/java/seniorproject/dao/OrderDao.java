package seniorproject.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import seniorproject.model.Order;
import seniorproject.util.HibernateUtil;

public class OrderDao {
	public Order getOrder(int id) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			Order order = session.get(Order.class, id);
			transaction.commit();
			return order;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Order> getAllOrders() {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			List<Order> order = session.createNativeQuery("SELECT * FROM workorder", Order.class).getResultList();
			transaction.commit();
			return order;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
			return null;
		}
	}
}