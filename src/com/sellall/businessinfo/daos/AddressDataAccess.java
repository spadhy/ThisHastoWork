package com.sellall.businessinfo.daos;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.apache.log4j.BasicConfigurator;
import com.sellall.businessinfo.entities.Addressinfo;

public class AddressDataAccess {

	private EntityManagerFactory emf;

	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<Addressinfo> listAddresses() {	
		EntityManager em = DataAccessEM.getEntMgr();
		final List<Addressinfo> addList = em.createQuery(
				"select add from Address add").getResultList();
		return addList;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Integer> listAddressesByLatLong(Collection latLongs) {
		EntityManager em = DataAccessEM.getEntMgr();
/*		
		HashSet<String> testColl= new HashSet<String>(2);
 		testColl.add("39.9658560--75.5271650");
		testColl.add("39.9667840--75.5407950");
		testColl.add("39.9681580--75.5565810");
		*/
		Query query = em.createQuery("select add.id from Addressinfo add WHERE add.latlong in :addresses");
		query.setParameter("addresses", latLongs);
		List<Integer> addList = query.getResultList();
		return addList;
	}
	
}
