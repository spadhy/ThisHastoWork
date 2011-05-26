package com.sellall.businessinfo.daos;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class DataAccessEM {

	public static EntityManager getEntMgr() {
		EntityManagerFactory emf;
		EntityManager em;
		emf = Persistence.createEntityManagerFactory("people");
		em = emf.createEntityManager();
		return em;
	}

}
