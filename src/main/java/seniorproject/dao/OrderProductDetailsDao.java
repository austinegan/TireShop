package seniorproject.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import seniorproject.model.OrderProductDetails;
import seniorproject.util.HibernateUtil;

public class OrderProductDetailsDao {
	public static List<OrderProductDetails> getOrderProductDetails(String query) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			List<OrderProductDetails> details = session.createNativeQuery(query, OrderProductDetails.class).getResultList();
			transaction.commit();
			return details;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
			return null;
		}
	}
	public static List<OrderProductDetails> getAllOrderDetails(){
		return getOrderProductDetails("SELECT * FROM orderProductDetails ORDER BY order_number");
	}
	
	public static List<OrderProductDetails> getDetailsFromOrderNum(int orderNum) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			List<OrderProductDetails> details = session.createNativeQuery("SELECT * FROM orderProductDetails WHERE order_number = :num", OrderProductDetails.class).setParameter("num",  orderNum).getResultList();
			System.out.println(details);
			transaction.commit();
			return details;
		} catch (Exception e) {
			if (transaction != null) {
				//transaction.rollback();
			}
			e.printStackTrace();
			return null;
		}
	}
}
