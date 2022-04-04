package seniorproject.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import seniorproject.model.Employee;
import seniorproject.util.HibernateUtil;

public class EmployeeDao {
	public Employee getEmployee(int id) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			Employee employee = session.get(Employee.class, id);
			transaction.commit();
			return employee;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
			return null;
		}
	}

	public void addEmployee(Employee emp) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.save(emp);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
	
	public void deleteEmployee(Employee emp) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.delete(emp);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
	
	public void updateEmployee(Employee emp) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.update(emp);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public List<Employee> getAllEmployee() {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			List<Employee> employee = session.createNativeQuery("SELECT * FROM employee", Employee.class).getResultList();
			transaction.commit();
			return employee;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
			return null;
		}
	}
}
