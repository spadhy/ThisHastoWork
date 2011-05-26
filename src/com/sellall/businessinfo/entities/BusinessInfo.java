package com.sellall.businessinfo.entities;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
@Entity
public class BusinessInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int id;

	private int businessTypeId;

	private String businessName;

	private Addressinfo businessAddress= new Addressinfo();
	
	private String businessDesc;

		public BusinessInfo(){
			
		}
		/**
		 * @return the id
		 */
		public Integer getId() {
			return id;
		}

		/**
		 * @param id the id to set
		 */
		public void setId(Integer id) {
			this.id = id;
		}

		/**
		 * @return the businessTypeId
		 */
		public int getBusinessTypeId() {
			return businessTypeId;
		}

		/**
		 * @param businessTypeId the businessTypeId to set
		 */
		public void setBusinessTypeId(int businessTypeId) {
			this.businessTypeId = businessTypeId;
		}

		/**
		 * @return the busienss_name
		 */
		public String getBusinessName() {
			return businessName;
		}

		/**
		 * @param busienss_name the busienss_name to set
		 */
		public void setBusinessName(String businessName) {
			this.businessName = businessName;
		}

		/**
		 * @return the businessAddress
		 */
		@OneToOne		
		public Addressinfo getBusinessAddress() {
			return businessAddress;
		}

		/**
		 * @param businessAddress the businessAddress to set
		 */
		public void setBusinessAddress(Addressinfo businessAddress) {
			this.businessAddress = businessAddress;
		}

		/**
		 * @return the businessDesc
		 */
		public String getBusinessDesc() {
			return businessDesc;
		}

		/**
		 * @param businessDesc the businessDesc to set
		 */
		public void setBusinessDesc(String businessDesc) {
			this.businessDesc = businessDesc;
		}

}
