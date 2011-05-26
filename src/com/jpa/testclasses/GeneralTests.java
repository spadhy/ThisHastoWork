package com.jpa.testclasses;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sellall.businessinfo.daos.AddressDataAccess;
import com.sellall.businessinfo.daos.BusinessListDataAccess;
import com.sellall.businessinfo.entities.Addressinfo;
import com.sellall.businessinfo.entities.BusinessInfo;
import com.sellall.businessinfo.services.BusinessListService;
public class GeneralTests {
	
	@Before
	public void setUp(){
		BasicConfigurator.configure();
		//Logger.getLogger("org").setLevel(Level.ERROR);
	}
	
	@After
	public void tearDown(){
		System.out.println("After step");
	}

	@SuppressWarnings({ "rawtypes" })
    @Test
	public void testing() throws Exception{
		BusinessListService bsvc = new BusinessListService();		
		List<BusinessInfo> bis=  bsvc.getBusinessListFromDatabase();
	}	
}
