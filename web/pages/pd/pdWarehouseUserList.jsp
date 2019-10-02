<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="pdWarehouseUserList.title"/></title>
<content tag="heading"><jecs:locale key="pdWarehouseUserList.heading"/></content>
<meta name="menu" content="PdWarehouseUserMenu"/>

<c:set var="buttons">
		<jecs:power powerCode="addPdWarehouseUser">
			   
			        	
			   <span onclick="location.href='<c:url value="/editPdWarehouseUser.html"/>?strAction=addPdWarehouseUser&warehouseNo=${requestParaMap.warehouseNo}'" style="cursor:pointer">
				<img alt="<jecs:locale  key='operation.button.add'/>" src="images/newIcons/plus_16.gif" border="0" align="absmiddle">
				<font color=black><jecs:locale  key='operation.button.add'/></font>
				</span>
				
		</jecs:power>
</c:set>
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>
<ec:table 
	tableId="pdWarehouseUserListTable"
	items="pdWarehouseUsers"
	var="pdWarehouseUser"
	action="${pageContext.request.contextPath}/pdWarehouseUsers.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
	      <ec:parameter name="strAction" value="${param.strAction}"/>
				<ec:row>
					
					<ec:column property="edit" title="title.operation" sortable="false">
						<img src="<c:url value='/images/newIcons/pencil_16.gif'/>" onclick="javascript:editPdWarehouseUser('${pdWarehouseUser.wuId}')" style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
    			</ec:column>
    			<ec:column property="warehouseNo" title="busi.warehouse.warehouseno" />
    			<ec:column property="userCode" title="login.userType.console" />
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editPdWarehouseUser(wuId){
   		
   		window.location="editPdWarehouseUser.html?strAction=editPdWarehouseUser&wuId="+wuId;
   		
   		<jecs:power powerCode="deletePdWarehouseUser">
					//window.location="editPdWarehouseUser.html?strAction=deletePdWarehouseUser&wuId="+wuId;
			</jecs:power>
		}

</script>
