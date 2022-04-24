package seniorproject.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import seniorproject.model.Inventory;
import seniorproject.model.WorkOrder;
import seniorproject.util.HibernateUtil;

public class WorkOrderDao {
	public static WorkOrder getOrder(int id) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			WorkOrder order = session.get(WorkOrder.class, id);
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
	
	public static List<WorkOrder> getAllOrders(String orderBy) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			List<WorkOrder> theseOrders = session.createNativeQuery("SELECT * FROM work_order" + orderBy, WorkOrder.class).getResultList();
			transaction.commit();
			return theseOrders;
		} catch (Exception e) {
			if (transaction != null) {
//				transaction.rollback();
			}
			e.printStackTrace();
			return null;
		}
	}
	public static List<WorkOrder> getAllOrders() {return getAllOrders("");}
}