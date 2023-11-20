package tp.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerUtil {
	private static EntityManagerFactory emf;
	public static EntityManager getEntityManager() {
		//EntityManagerFactory factory = Persistence.createEntityManagerFactory("pruebaTP");
		EntityManager manager= emf.createEntityManager();
		return manager;
	}
	public static void createEntityManagerFactory() {
		emf=Persistence.createEntityManagerFactory("pruebaTP");
	}
	/*
	public static void main(String[] args) {
		EntityManager manager=EntityManagerUtil.getEntityManager();
		System.out.println("EntityManager class: "+ manager.getClass().getCanonicalName());
	}
	*/
	
	
}
