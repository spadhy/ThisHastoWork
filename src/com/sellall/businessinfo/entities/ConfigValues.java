package com.sellall.businessinfo.entities;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
@Entity
public class ConfigValues {

		@Id
		public String configParam;
		
		public String configValue;

		public String getConfigParam() {
			return configParam;
		}

		public void setConfigParam(String configParam) {
			this.configParam = configParam;
		}

		public String getConfigValue() {
			return configValue;
		}

		public void setConfigValue(String configValue) {
			this.configValue = configValue;
		}

}
