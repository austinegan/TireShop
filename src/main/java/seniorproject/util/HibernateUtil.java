package seniorproject.util;

import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import seniorproject.model.Customer;
import seniorproject.model.Employee;
import seniorproject.model.Inventory;
import seniorproject.model.OrdCust;
import seniorproject.model.OrderProductDetails;
import seniorproject.model.OrderProductRelation;
import seniorproject.model.WorkOrder;

public class HibernateUtil {
	private static SessionFactory sessionFactory;
	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			try {
				Configuration configuration = new Configuration();

				Properties settings = new Properties();
				settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
				settings.put(Environment.URL, "jdbc:mysql://database-2.cgeho1qoscbs.us-east-1.rds.amazonaws.com:3306/myDB2");
				settings.put(Environment.USER, "admin");
				settings.put(Environment.PASS, "49Ug2pJ1OcxGrbvDdDxE");
				settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
				settings.put(Environment.SHOW_SQL, "true");
				settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
				settings.put(Environment.HBM2DDL_AUTO, "update");

				configuration.setProperties(settings);
				configuration.addAnnotatedClass(Inventory.class);
				configuration.addAnnotatedClass(Customer.class);
				configuration.addAnnotatedClass(WorkOrder.class);
				configuration.addAnnotatedClass(Employee.class);
				configuration.addAnnotatedClass(OrderProductDetails.class);
				configuration.addAnnotatedClass(OrdCust.class);
				configuration.addAnnotatedClass(OrderProductRelation.class);
				
				ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
						.applySettings(configuration.getProperties()).build();
				
				sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return sessionFactory;
	}
	
	public static void closeFactory() {
		if (sessionFactory != null) {
			try {
				sessionFactory.close();
			} catch (HibernateException ignored) {
			}
		}
	}
}