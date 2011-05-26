package com.sellall.businessinfo.daos;

import java.util.HashMap;
import java.util.List;
import javax.persistence.EntityManager;
import com.sellall.businessinfo.entities.ConfigValues;

public class ConfigValueDataAccess {
	 
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public HashMap listConfigValues() throws Exception {
        EntityManager em = DataAccessEM.getEntMgr();
        HashMap configValueMap=new HashMap();        
        
        final List<ConfigValues> list = em.createQuery("select bi from ConfigValues bi").getResultList();
        for (ConfigValues current : list) {
        	String configParam = current.getConfigParam();
        	String configValue = current.getConfigValue();
            configValueMap.put(configParam, configValue);
       }
        return configValueMap;
        
    }
}

