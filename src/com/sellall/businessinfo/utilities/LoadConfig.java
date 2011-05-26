package com.sellall.businessinfo.utilities;

import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.BasicConfigurator;

import com.sellall.businessinfo.daos.ConfigValueDataAccess;
import com.sellall.businessinfo.entities.ConfigValues;

public class LoadConfig {
		
	private static EntityManagerFactory emf;	
	@SuppressWarnings("rawtypes")
	public static void  getConfigValuesFromDatabase() throws Exception {
			ConfigValueDataAccess cdo = new ConfigValueDataAccess();
			@SuppressWarnings("unused")
			HashMap hMap = cdo.listConfigValues();
		}
	
	public static EntityManager getEm() {
		BasicConfigurator.configure();
		//Logger.getLogger("org").setLevel(Level.ERROR);
		emf = Persistence.createEntityManagerFactory("people");
		return emf.createEntityManager();
	}

	public void cleanup() {
		
	}

   	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static HashMap retrieve() {
    	EntityManager em = getEm();
    	em.getTransaction().begin();
    	HashMap hMap = new HashMap();
        final List<ConfigValues> list = em.createQuery("select bi from ConfigValues bi")
                .getResultList();
        for (ConfigValues current : list) {
        	hMap.put(current.getConfigParam(), current.getConfigValue());
       }
        return hMap;
    }	

	
}
