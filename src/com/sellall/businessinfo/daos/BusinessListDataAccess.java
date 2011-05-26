package com.sellall.businessinfo.daos;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.sellall.businessinfo.entities.BusinessInfo;

public class BusinessListDataAccess {

	@SuppressWarnings("unchecked")
	public List<BusinessInfo> listBusiness() {
		EntityManager em = DataAccessEM.getEntMgr();
		em.getTransaction().begin();
		final List<BusinessInfo> bList = em.createQuery(
				"select bi from BusinessInfo bi").getResultList();
		return bList;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<BusinessInfo> listBusinessByAddressIds(List<String> latLngs) {
		EntityManager em = DataAccessEM.getEntMgr();
		em.getTransaction().begin();
		Query query = em.createQuery("select bi from BusinessInfo bi, Addressinfo add WHERE " +
				"add=bi.businessAddress and add.latlong in :latLongs");
		query.setParameter("latLongs", latLngs);
		List<BusinessInfo> bList = query.getResultList();
		return bList;
	}

}
