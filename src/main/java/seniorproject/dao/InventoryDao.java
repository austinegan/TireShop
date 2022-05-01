package seniorproject.dao;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.Combo;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import seniorproject.model.Customer;
//import seniorproject.model.Customer;
import seniorproject.model.Inventory;
import seniorproject.util.HibernateUtil;

public class InventoryDao {
	public Inventory getInventory(int id) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			Inventory inventory = session.get(Inventory.class, id);
			transaction.commit();
			return inventory;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
			return null;
		}
	}

	public static void addInventory(Inventory inv) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.save(inv);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
	
	public static void deleteInventory(Inventory inv) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.delete(inv);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
	
	public static void updateInventory(Inventory inv) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.update(inv);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public static List<Inventory> generateQueryInventory(String brand, String model, String width, String aspRatio, String diameter, Boolean expandable) {	
		return generateQueryInventory(brand, model, width, aspRatio, diameter, expandable, " ORDER BY id");
	}
	
	public static List<Inventory> generateQueryInventory(String brand, String model, String width, String aspRatio, String diameter, Boolean expandable, String order) {
		 
		String queryString = "SELECT * FROM inventory";
		List<Inventory> myInventoryList;
		List<String> myList = new ArrayList<String>();
		 if(!brand.isBlank() && (brand != null)) {myList.add(" brand LIKE :bra");}
		 if(!model.isBlank()) {myList.add(" model_number LIKE :mod");}
		 if(!width.isBlank()) {myList.add(" width LIKE :wid");}
		 if(!aspRatio.isBlank()) {myList.add(" aspectratio LIKE :asp");}
		 if(!diameter.isBlank()) {myList.add(" diameter LIKE :dia");}

		if(!myList.isEmpty()) {queryString += " WHERE" + myList.get(0);
			for (int i = 1; i < myList.size(); i++) {
				queryString += " AND" + myList.get(i);
			}
			queryString += order;
			System.out.println(queryString);
			myInventoryList = getInventory(queryString, brand, model, width, aspRatio, diameter);
			if(myInventoryList.size() == 0) {
				System.out.println("No results found.");
				if (expandable) {
						System.out.println(" Expanding search results.");
				queryString = "SELECT * FROM inventory WHERE" + myList.get(0);
				for (int i = 1; i < myList.size(); i++) {
					queryString += " OR" + myList.get(i);
				}
				queryString += order;
				System.out.println(queryString);
				myInventoryList = getInventory(queryString, brand, model, width, aspRatio, diameter);
				}
				else {System.out.println("But expandable results are off. So that's all you get (0 results)");}
			}
			return myInventoryList;
		}
		queryString += order;
		myInventoryList = getInventory(queryString, brand, model, width, aspRatio, diameter);
		return myInventoryList;
	}
	
	
	public static List<Inventory> getInventory(String query, String brand, String model, String width, String aspRatio, String diameter) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			Query<Inventory> myQuery = session.createNativeQuery(query, Inventory.class);
			if(!brand.isBlank() && (brand != null)) {myQuery.setParameter("bra", "%" + brand + "%");}
			if(!model.isBlank()) {myQuery.setParameter("mod", "%" + model + "%");}
			if(!width.isBlank()) {myQuery.setParameter("wid", "%" + width + "%");}
			if(!aspRatio.isBlank()) {myQuery.setParameter("asp", "%" + aspRatio + "%");}
			if(!diameter.isBlank()) {myQuery.setParameter("dia", "%" + diameter + "%");}
			 
			List<Inventory> inventory = myQuery.getResultList();
			transaction.commit();
			return inventory;
		} catch (Exception e) {
			if (transaction != null) {
				//transaction.rollback();
			}
			e.printStackTrace();
			return null;
		}
	}
	
	public static List<Inventory> getInventory(String query) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			List<Inventory> inventory = session.createNativeQuery(query, Inventory.class).getResultList();
			transaction.commit();
			return inventory;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
			return null;
		}
	}
	
	public static List<Inventory> getAllInventory() {
		return getInventory("SELECT * FROM inventory ORDER BY id");
	}

	public static List<String> comboQuery(String column){
		Transaction transaction = null;
		List<String> inventory = new ArrayList<String>();
		List<Integer> invIntList;
		String queryString = "SELECT DISTINCT " + column + " FROM inventory ORDER BY " + column; 
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			if(column == "width" || column == "diameter" || column == "aspectratio") {
				invIntList = (List<Integer>) session.createNativeQuery(queryString).list();
				for (Integer inv : invIntList) {inventory.add(inv.toString());}
			}
			else {
			inventory = (List<String>) session.createNativeQuery(queryString).list();
			}
			transaction.commit();
			return inventory;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
			return null;
		}
	}
}
