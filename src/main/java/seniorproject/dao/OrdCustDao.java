package seniorproject.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import seniorproject.model.OrdCust;
import seniorproject.util.HibernateUtil;

public class OrdCustDao {
	
	public static List<OrdCust> getPendingOrdCust(){
		return getOrdCust("SELECT * FROM ordCustView WHERE status = 'pending' ORDER BY time_create asc");
	}
	
	public static List<OrdCust> getCompletedOrdCust(){
		return getOrdCust("SELECT * FROM ordCustView WHERE status = 'completed' ORDER BY time_update_status desc");
	}
	
	public static List<OrdCust> getOrdCust(String query) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			List<OrdCust> details = session.createNativeQuery(query, OrdCust.class).getResultList();
			transaction.commit();
			return details;
		} catch (Exception e) {
			e.printStackTrace();
			if (transaction != null) {
				//transaction.rollback();
			}
			
			return null;
		}
	}
	public static List<OrdCust> getAllOrdCust(){
		return getOrdCust("SELECT * FROM ordCust ORDER BY order_number");
	}
	
	public static List<OrdCust> getDetailsFromOrderNum(int orderNum) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			List<OrdCust> details = session.createNativeQuery("SELECT * FROM ordCustView WHERE order_number = :num", OrdCust.class).setParameter("num",  orderNum).getResultList();
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