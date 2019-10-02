<%@ page language="java" errorPage="/error.jsp" contentType="text/html; charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="pdWarehouseUserList.title"/></title>
<content tag="heading"><jecs:locale key="pdWarehouseUserList.heading"/></content>
<meta name="menu" content="PdWarehouseUserMenu"/>
<link rel="stylesheet" type="text/css" href="styles/dhtmlXTree.css" />
<script src="./scripts/dhtmlxTree/dhtmlXCommon.js" type="text/javascript"></script>
<script src="./scripts/dhtmlxTree/dhtmlXTree.js" type="text/javascript"></script>

<form action="sys_power_m_frm.html" method="post" name="myPowerForm">
	<%-- <input type="button" class="button" onClick="saveMyPower(this.form)" value="<jecs:locale key="operation.button.save"/>"/> --%>
	<button type="button" class="btn btn_sele" onclick="saveMyPower(this.form)" ><jecs:locale key="operation.button.save"/></button>
	
	<input type="hidden" name="strAction" value="addPdWarehouseUser2"/>
	<input type="hidden" name="selectedWarehouseCode" value=""/>
	<input type="hidden" name="userCode" id="userCode" value="${userCode }"/>
	<input type="hidden" name="companyCode" value="${companyCode }"/>
</form>

<div id="treebox" style="width:100%; height:100%;overflow:auto;"></div>
<script type="text/javascript">
	var myManM=new dhtmlXTreeObject("treebox","100%","100%",0);
	myManM.setImagePath("./images/dhtmlxTree/csh_vista/");
	myManM.enableCheckBoxes(true);
	myManM.enableThreeStateCheckboxes(true);
	
	myManM.insertNewChild("0","root","<jecs:locale key='pdAdjustStock.warehouseNo'/>",0,0,0,0,"");
	//myManM.showItemCheckbox("root",false);
	
	<c:forEach items="${warehouseList}" var="pdWarehouse">
		myManM.insertNewChild("root","${pdWarehouse.warehouse_no}","${pdWarehouse.warehouse_no}-${pdWarehouse.warehouse_name}",0,0,0,0,"<c:if test="${not empty pdWarehouse.wu_id}">CHECKED</c:if>"); 
	</c:forEach>
	
	function saveMyPower(theForm){
		var selectedWarehouseCode = window.myManM.getAllChecked();
		theForm.selectedWarehouseCode.value=selectedWarehouseCode;
		theForm.submit();
	}
</script>
