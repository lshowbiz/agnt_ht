package com.joymain.jecs.pd.model;

import java.util.ArrayList;
import java.util.List;

public class PdLogistics extends com.joymain.jecs.model.BaseObject implements java.io.Serializable{

	private List<PdLogisticsBase>   pdLogisticsBase_list = new ArrayList();
	private String bar_code;//modify by fu 2016-02-25 条形码
	
	public PdLogistics(){
		
	}
	
	public PdLogistics(String bar_code){
		this.bar_code = bar_code;
	}
	
	public List<PdLogisticsBase> getPdLogisticsBase_list() {
		return pdLogisticsBase_list;
	}

	public void setPdLogisticsBase_list(List<PdLogisticsBase> pdLogisticsBaselist) {
		pdLogisticsBase_list = pdLogisticsBaselist;
	}
	
	public String getBar_code() {
		return bar_code;
	}

	public void setBar_code(String barCode) {
		bar_code = barCode;
	}

	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

}
