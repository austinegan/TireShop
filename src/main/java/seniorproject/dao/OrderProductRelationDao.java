package seniorproject.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import seniorproject.model.OrderProductRelation;
import seniorproject.util.HibernateUtil;

public class OrderProductRelationDao {
	public static void addOrderProductRelation(OrderProductRelation cus) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.save(cus);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
			}
			
		}
	}
}
