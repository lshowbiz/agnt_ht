package com.joymain.jecs.pm.model;
// Generated 2013-6-3 18:00:54 by Hibernate Tools 3.1.0.beta4

/**
 * 接口参数传递类封装
 */

public class JpmProductSaleErp extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String PRODUCT_NO;//EC商品编号
	private String ERP_PRODUCT_NO;//ERP商品编码
	private String PRODUCT_CATEGORY;//商品类别 选自list
	private String UNIT_NO;//商品单位,选自list
	private String PRODUCT_STYLE;//商品规格
	private String PRODUCT_SIZE;//商品尺寸
	private String COMPANY_CODE;//区域
	private String PRODUCT_NAME;//商品名称
	private String STATUS;//状态
	private String REMARK;//备注
	private String WHO_PRICE;//价格
	private String PRODUCT_DESC;//描述
	private String START_ON_SALE;//起始销售日期
	private String END_ON_SALE;//截止销售日期
	private String HOT_FLAG;//是否热卖
	private String SORT_FLAG;//排序号
	private String CREATE_TIME;//创建时间
	private String BRIEF_INTRODUCTION;//简介
	private String HEALTH_KNOWLEDGE;//健康知识
	private String PRODUCT_SPECIFICATION;//规格
	private String SIMG_LINK;//小图地址
	private String CIMG_LINK;//中图地址
	private String BIMG_LINK;//大图地址 

	
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

	public String getPRODUCT_NO() {
		return PRODUCT_NO;
	}

	public void setPRODUCT_NO(String pRODUCTNO) {
		PRODUCT_NO = pRODUCTNO;
	}

	public String getERP_PRODUCT_NO() {
		return ERP_PRODUCT_NO;
	}

	public void setERP_PRODUCT_NO(String eRPPRODUCTNO) {
		ERP_PRODUCT_NO = eRPPRODUCTNO;
	}

	public String getPRODUCT_CATEGORY() {
		return PRODUCT_CATEGORY;
	}

	public void setPRODUCT_CATEGORY(String pRODUCTCATEGORY) {
		PRODUCT_CATEGORY = pRODUCTCATEGORY;
	}

	public String getUNIT_NO() {
		return UNIT_NO;
	}

	public void setUNIT_NO(String uNITNO) {
		UNIT_NO = uNITNO;
	}

	public String getPRODUCT_STYLE() {
		return PRODUCT_STYLE;
	}

	public void setPRODUCT_STYLE(String pRODUCTSTYLE) {
		PRODUCT_STYLE = pRODUCTSTYLE;
	}

	public String getPRODUCT_SIZE() {
		return PRODUCT_SIZE;
	}

	public void setPRODUCT_SIZE(String pRODUCTSIZE) {
		PRODUCT_SIZE = pRODUCTSIZE;
	}

	public String getCOMPANY_CODE() {
		return COMPANY_CODE;
	}

	public void setCOMPANY_CODE(String cOMPANYCODE) {
		COMPANY_CODE = cOMPANYCODE;
	}

	public String getPRODUCT_NAME() {
		return PRODUCT_NAME;
	}

	public void setPRODUCT_NAME(String pRODUCTNAME) {
		PRODUCT_NAME = pRODUCTNAME;
	}

	public String getSTATUS() {
		return STATUS;
	}

	public void setSTATUS(String sTATUS) {
		STATUS = sTATUS;
	}

	public String getREMARK() {
		return REMARK;
	}

	public void setREMARK(String rEMARK) {
		REMARK = rEMARK;
	}

	public String getWHO_PRICE() {
		return WHO_PRICE;
	}

	public void setWHO_PRICE(String wHOPRICE) {
		WHO_PRICE = wHOPRICE;
	}

	public String getPRODUCT_DESC() {
		return PRODUCT_DESC;
	}

	public void setPRODUCT_DESC(String pRODUCTDESC) {
		PRODUCT_DESC = pRODUCTDESC;
	}

	public String getSTART_ON_SALE() {
		return START_ON_SALE;
	}

	public void setSTART_ON_SALE(String sTARTONSALE) {
		START_ON_SALE = sTARTONSALE;
	}

	public String getEND_ON_SALE() {
		return END_ON_SALE;
	}

	public void setEND_ON_SALE(String eNDONSALE) {
		END_ON_SALE = eNDONSALE;
	}

	public String getHOT_FLAG() {
		return HOT_FLAG;
	}

	public void setHOT_FLAG(String hOTFLAG) {
		HOT_FLAG = hOTFLAG;
	}

	public String getSORT_FLAG() {
		return SORT_FLAG;
	}

	public void setSORT_FLAG(String sORTFLAG) {
		SORT_FLAG = sORTFLAG;
	}

	public String getCREATE_TIME() {
		return CREATE_TIME;
	}

	public void setCREATE_TIME(String cREATETIME) {
		CREATE_TIME = cREATETIME;
	}

	public String getBRIEF_INTRODUCTION() {
		return BRIEF_INTRODUCTION;
	}

	public void setBRIEF_INTRODUCTION(String bRIEFINTRODUCTION) {
		BRIEF_INTRODUCTION = bRIEFINTRODUCTION;
	}

	public String getHEALTH_KNOWLEDGE() {
		return HEALTH_KNOWLEDGE;
	}

	public void setHEALTH_KNOWLEDGE(String hEALTHKNOWLEDGE) {
		HEALTH_KNOWLEDGE = hEALTHKNOWLEDGE;
	}

	public String getPRODUCT_SPECIFICATION() {
		return PRODUCT_SPECIFICATION;
	}

	public void setPRODUCT_SPECIFICATION(String pRODUCTSPECIFICATION) {
		PRODUCT_SPECIFICATION = pRODUCTSPECIFICATION;
	}

	public String getSIMG_LINK() {
		return SIMG_LINK;
	}

	public void setSIMG_LINK(String sIMGLINK) {
		SIMG_LINK = sIMGLINK;
	}

	public String getCIMG_LINK() {
		return CIMG_LINK;
	}

	public void setCIMG_LINK(String cIMGLINK) {
		CIMG_LINK = cIMGLINK;
	}

	public String getBIMG_LINK() {
		return BIMG_LINK;
	}

	public void setBIMG_LINK(String bIMGLINK) {
		BIMG_LINK = bIMGLINK;
	}

}
