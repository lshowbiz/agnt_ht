package com.joymain.jecs.mi.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "MiStatusResponse")
@XmlAccessorType(XmlAccessType.FIELD)
public class MiStatusResponse
{
	 	private String statusResponse;
	 	private String remark;
	 	
		public String getRemark() {
			return remark;
		}

		public void setRemark(String remark) {
			this.remark = remark;
		}

		public String getStatusResponse() {
			return statusResponse;
		}

		public void setStatusResponse(String statusResponse) {
			this.statusResponse = statusResponse;
		}




	  

  
}