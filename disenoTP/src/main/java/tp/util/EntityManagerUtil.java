package tp.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerUtil {
	public static EntityManager getEntityManager() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("pruebaTP");
		EntityManager manager= factory.createEntityManager();
		return manager;
	}
	/*
	public static void main(String[] args) {
		EntityManager manager=EntityManagerUtil.getEntityManager();
		System.out.println("EntityManager class: "+ manager.getClass().getCanonicalName());
	}
	*/
	
}
