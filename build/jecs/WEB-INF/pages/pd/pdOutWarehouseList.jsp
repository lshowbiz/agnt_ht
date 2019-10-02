<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="pdOutWarehouseList.title"/></title>
<content tag="heading"><jecs:locale key="pdOutWarehouseList.heading"/></content>
<meta name="menu" content="PdOutWarehouseMenu"/>
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script> 

<c:set var="buttons">
<div class="new_searchBar">

     <button type="submit" class="btn btn_sele"  style="width:auto" >
		        <jecs:locale  key='operation.button.search'/>
     </button>
     
	 <c:if test="${requestParaMap.strAction == 'editPdOutWarehouse'}">
	
			<jecs:power powerCode="addPdOutWarehouse">  
				    <button type="button" class="btn btn_ins"  style="width:auto" onclick="editPdOutWarehouseAdd();">
				        <jecs:locale key="button.add"/>
		           </button>
			</jecs:power>
	 </c:if> 
			
			
</div>

<%--  <c:if test="${requestParaMap.strAction == 'editPdOutWarehouse'}">

		<jecs:power powerCode="addPdOutWarehouse">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editPdOutWarehouse.html"/>?strAction=addPdOutWarehouse'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power>
 </c:if> 
		<input type="submit" class="button" value="<jecs:locale  key='operation.button.search'/>"/> --%>
</c:set>

<form name="pdOutWarehouseList" action="<c:url value='/pdOutWarehouses.html'/>" >
	<input type="hidden" id="strAction" name="strAction" value="${requestParaMap.strAction}"/>
	<div class="searchBar">
	
	  <div class="new_searchBar">	
		    <jecs:title  key="pdOutWarehouseDetail.owNo"/>
			<input name="owNo" id="owNo" value="<c:out value='${requestParaMap.owNo}'/>"  cssClass="text medium"/>
	  </div>
	  
	  <div class="new_searchBar">	  	
		    <jecs:title key="pdOutWarehouse.owtNo"/>
		    <jecs:list listCode="owtnolist" name="owtNo" id="owtNo" showBlankLine="true" value="${requestParaMap.owtNo}" defaultValue=""/>
	  </div>
	  <div class="new_searchBar" style="white-space:nowrap;">
		<jecs:title key="busi.warehouse.outwatehouseno"/>
			<input name="warehouseNo" id="warehouseNo" value="<c:out value='${requestParaMap.warehouseNo}'/>"   cssClass="text medium"/>
			
			<button name="select" class="btn btn_sele" onclick="selectWarehouse('warehouseNo');" >
				   <jecs:locale key="button.select"/>
			</button>
			
			<!-- <input type="button" class="button" name="select" onclick="selectWarehouse('warehouseNo');" value="<jecs:locale key="button.select"/>" />-->
	  </div>
	  <div class="new_searchBar">	
			<jecs:title key="pd.createUserNo"/>
			<input name="createUsrCode" id="createUsrCode" value="<c:out value='${requestParaMap.createUsrCode}'/>"  cssClass="text medium"/>
      </div>
      <div class="new_searchBar">
             <jecs:title key="pd.createTime"/>
            <input name="createTimeStart" id="createTimeStart" type="text" value="${requestParaMap.createTimeStart }" style="cursor:pointer;width:76px;"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
	        - 
	        <input name="createTimeEnd" id="createTimeEnd" type="text" value="${requestParaMap.createTimeEnd }" style="cursor:pointer;width:76px;"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
      </div>
		<%-- <jecs:locale key="pd.createTime"/>
			<input name="createTimeStart" size="11" id="createTimeStart"  value="${requestParaMap.createTimeStart}">
      	<img src="./images/calendar/calendar7.gif" id="img_startTime" style="cursor: pointer;" title="<jecs:locale key="Calendar.TT.SEL_DATE"/>"/> 
				<script type="text/javascript"> 
					Calendar.setup({
					inputField     :    "createTimeStart", 
					ifFormat       :    "%Y-%m-%d",  
					button         :    "img_startTime", 
					singleClick    :    true
					}); 
				</script>
				-<input name="createTimeEnd" size="11" id="createTimeEnd"  value="${requestParaMap.createTimeEnd}">
      	<img src="./images/calendar/calendar7.gif" id="img_endTime" style="cursor: pointer;" title="<jecs:locale key="Calendar.TT.SEL_DATE"/>"/> 
				<script type="text/javascript"> 
					Calendar.setup({
					inputField     :    "createTimeEnd", 
					ifFormat       :    "%Y-%m-%d",  
					button         :    "img_endTime", 
					singleClick    :    true
					}); 
				</script> --%>
		<div class="new_searchBar">
		       <jecs:title key="pd.okTime"/>
		       <input name="okTimeStart" id="okTimeStart" type="text" value="${requestParaMap.okTimeStart }" style="cursor:pointer;width:76px;"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
		       -
		       <input name="okTimeEnd" id="okTimeEnd" type="text" value="${requestParaMap.okTimeEnd }" style="cursor:pointer;width:76px;"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
		</div>
		
		<%-- <jecs:locale key="pd.okTime"/>
			<input name="okTimeStart" size='11' id="okTimeStart"  value="${requestParaMap.okTimeStart}">
      	<img src="./images/calendar/calendar7.gif" id="img_okTimeStart" style="cursor: pointer;" title="<jecs:locale key="Calendar.TT.SEL_DATE"/>"/> 
				<script type="text/javascript"> 
					Calendar.setup({
					inputField     :    "okTimeStart", 
					ifFormat       :    "%Y-%m-%d",  
					button         :    "img_okTimeStart", 
					singleClick    :    true
					}); 
				</script>
				- <input name="okTimeEnd"  size='11' id="okTimeEnd"  value="${requestParaMap.okTimeEnd}">
      	<img src="./images/calendar/calendar7.gif" id="img_okTimeEnd" style="cursor: pointer;" title="<jecs:locale key="Calendar.TT.SEL_DATE"/>"/> 
				<script type="text/javascript"> 
					Calendar.setup({
					inputField     :    "okTimeEnd", 
					ifFormat       :    "%Y-%m-%d",  
					button         :    "img_okTimeEnd", 
					singleClick    :    true
					}); 
				</script> --%>
		<div class="new_searchBar">		
			<jecs:title  key="pdSendInfo.orderFlag"/>
			<jecs:list listCode="pd.orderflag.exchangeorder" name="orderFlag" id="orderFlag" showBlankLine="true" value="${requestParaMap.orderFlag}" defaultValue=""/>
		</div>
		<div class="new_searchBar">		
			<jecs:title key="busi.product.productno" />
			<input name="productNo" id="productNo"
				value="<c:out value='${requestParaMap.productNo }'/>"
				cssClass="text medium" />
		</div>	
			<c:out value="${buttons}" escapeXml="false"/>
	</div>
<ec:table 
	tableId="pdOutWarehouseListTable"
	items="pdOutWarehouseList"
	var="pdOutWarehouse"
	action="${pageContext.request.contextPath}/pdOutWarehouses.html"
	width="100%"
	form="pdOutWarehouseList"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
				<ec:row onclick="showTR('tr${ROWCOUNT}');">
				
    			<c:if test="${sessionScope.sessionLogin.isManager}">
    					<ec:column property="companyCode" title="sys.company.code" />
    			</c:if>
    			<ec:column property="owNo" title="pdOutWarehouseDetail.owNo" />
    			<ec:column property="owtNo" title="pdOutWarehouse.owtNo" >
    				<jecs:code listCode="owtnolist" value="${pdOutWarehouse.owtNo}"/>
    			</ec:column>
    			
    			<ec:column property="warehouseNo" title="busi.warehouse.outwatehouseno" />
    			<ec:column property="createUsrCode" title="pd.createUserNo" />
    			 
    			<ec:column property="createTime" title="pd.createTime" >
    				<fmt:formatDate value="${pdOutWarehouse.createTime}" pattern="yyyy-MM-dd"/>
    			</ec:column>
    			<ec:column property="checkUsrCode" title="busi.user.check" />
    			<ec:column property="checkTime" title="logType.BC" >
    				<fmt:formatDate value="${pdOutWarehouse.checkTime}" pattern="yyyy-MM-dd"/>
    			</ec:column>
    			
    			<ec:column property="okUsrCode" title="pd.confirmUserNo" />
    			
    			<ec:column property="okTime" title="pd.okTime" >
    				<fmt:formatDate value="${pdOutWarehouse.okTime}" pattern="yyyy-MM-dd"/>
    			</ec:column>
    			
    				
    			<ec:column property="orderFlag" title="pd.orderflag" >
    				<jecs:code listCode="pd.orderflag.exchangeorder" value="${pdOutWarehouse.orderFlag}"/>
    			</ec:column>	
    			<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/newIcons/pencil_16.gif'/>" 
								onclick="javascript:editPdOutWarehouse('${pdOutWarehouse.owNo}','${requestParaMap.strAction}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
									<c:if test="${requestParaMap.strAction=='confirmPdOutWarehouse'}">		 	
								 	<img src="<c:url value='/images/icons/printer.gif'/>" 
								onclick="javascript:printOutWarehouse('${pdOutWarehouse.owNo}')"
								 style="cursor: pointer;" title="<jecs:locale key="button.print"/>"/> 
						</c:if>
					</ec:column>
					
    				<c:if test="${ROWCOUNT>0}">
						<c:if test="${ROWCOUNT%2!=0}"><tr class="odddetail" style="display:none" id="tr${ROWCOUNT}"></c:if>
						<c:if test="${ROWCOUNT%2==0}"><tr class="odddetail" style="display:none" id="tr${ROWCOUNT}"></c:if>
							<td align="right" valign="top">&nbsp;</td>
							<c:if test="${sessionScope.sessionLogin.isManager}">
								<td colspan="8">
							</c:if>
							<c:if test="${!sessionScope.sessionLogin.isManager}">
								<td colspan="7">
							</c:if>
							
								<c:forEach items="${pdOutWarehouse.pdOutWarehouseDetails}" var="pdOutWarehouseDetail" varStatus="status">
									<c:if test="${!status.first}"><br/></c:if>
										
									<font color=#888888>[<font color="green">${pdOutWarehouseDetail.qty}</font>]${pdOutWarehouseDetail.productNo}/
										${compamyProductMap[pdOutWarehouseDetail.productNo]}
										</font>
									
								</c:forEach>
							</td>
							<td colspan="3">
								<font color="green">${pdOutWarehouse.remark}</font>
							</td>
						</tr>
				</c:if>
				</ec:row>

</ec:table>	
</form>

<c:if test="${requestParaMap.strAction=='statisticPdOutWarehouse'}">
<ec:table 
	tableId="pdOutWarehouseTotalTable"
	items="pdOutWarehouseTotal"
	var="pd"
	action="${pageContext.request.contextPath}/pdOutWarehouses.html"
	method="post"
	width="100%"
	showPagination="false"
	 sortable="false" imagePath="./images/extremetable/*.gif">
				<ec:parameter name="strAction" value="${requestParaMap.strAction}"/>
				<ec:exportCsv fileName="outstorageTotal.csv" encoding="UTF-8"/>
				<ec:exportXls fileName="outstorageTotal.xls" encoding="UTF-8"/>
				<ec:row >
					
					<ec:column property="PRODUCT_NO" title="busi.product.productno" />
    			<ec:column property="PRODUCT_NAME" title="pmProduct.productName" />
    			<ec:column property="QTY" title="pd.qty" calc="total" calcTitle="busi.logistics.total" styleClass="numberColumn"/>

				</ec:row>

</ec:table>	
</c:if>
<script type="text/javascript">

   function editPdOutWarehouse(owNo,strAction){
   				<jecs:power powerCode="${requestParaMap.strAction}">
   				
		   				
								window.location="editPdOutWarehouse.html?owNo="+owNo+"&strAction="+strAction;
							
					</jecs:power>
		}
		function printOutWarehouse(siNo){
	 		window.open("<c:url value='/pdOrderPrints.html'/>?strAction=printOutWarehouse&orderNo="+siNo);
	 	}
	 	
	 	function editPdOutWarehouseAdd(){
	 	       	window.location="editPdOutWarehouse.html?strAction=addPdOutWarehouse";
	 	}

</script>
