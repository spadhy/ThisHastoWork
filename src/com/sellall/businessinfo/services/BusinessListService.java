package com.sellall.businessinfo.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.sellall.businessinfo.daos.AddressDataAccess;
import com.sellall.businessinfo.daos.BusinessListDataAccess;
import com.sellall.businessinfo.entities.BusinessInfo;
import com.sellall.businessinfo.utilities.GooglePlacesInteraction;

public class BusinessListService {
	private List<String> getLatLongListFromGoogle(String currentLocation) {
//TODO use this to send location info to the recipient classes		
		return null;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<BusinessInfo> getBusinessListFromDatabase() throws Exception {
		Collection<String> lstLatLong = new GooglePlacesInteraction().getLatLongStrings();
		System.out.println("LatLongs -->" + lstLatLong.toString());
		List<BusinessInfo> businessList = new ArrayList<BusinessInfo>();
		businessList.addAll(new BusinessListDataAccess().listBusinessByAddressIds((ArrayList)lstLatLong));
		return businessList; 
	}
	
}
