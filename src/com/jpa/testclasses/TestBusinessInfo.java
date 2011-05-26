package com.jpa.testclasses;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.BasicConfigurator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.jpa.first.Family;
import com.jpa.first.Person;
import com.sellall.businessinfo.entities.BusinessInfo;
import com.sellall.businessinfo.entities.ConfigValues;




public class TestBusinessInfo {
	private EntityManagerFactory emf;
	private EntityManager em;
	
	@Before
	public void setUp(){
		BasicConfigurator.configure();
		//Logger.getLogger("org").setLevel(Level.ERROR);
		emf = Persistence.createEntityManagerFactory("people");
		em= emf.createEntityManager();
	}
	
	@After
	public void tearDown(){
		System.out.println("After step");
	}

	@SuppressWarnings("unchecked")
    @Test
	public void testing() throws Exception{
    	
        final List<BusinessInfo> list = em.createQuery("select bi from BusinessInfo bi")
                .getResultList();
        for (BusinessInfo current : list) {
            final String configParam = current.getBusinessName() + "," + current.getBusinessDesc() + "," + current.getBusinessAddress().getAddress1();
            System.out.println("Config info -->"+ configParam);
       }
	}	
}
