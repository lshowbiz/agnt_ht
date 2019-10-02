<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="pdFlitWarehouseList.title"/></title>
<content tag="heading"><jecs:locale key="pdFlitWarehouseList.heading"/></content>
<meta name="menu" content="PdFlitWarehouseMenu"/>
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script> 

<c:set var="buttons">
<div class="new_searchBar">
	<button type="submit" class="btn btn_sele"  style="width:auto" >
		        <jecs:locale  key='operation.button.search'/>
    </button>
	<c:if test="${listFlag =='1'}">
			 <jecs:power powerCode="addPdFlitWarehouse">
				 <button type="button" class="btn btn_ins"  style="width:auto" onclick="pdFlitWarehouseAdd();">
					        <jecs:locale key="operation.button.add"/>
			    </button>
		    </jecs:power>
    </c:if>
</div>
    <%-- <input type="submit" class="button" value="<jecs:locale  key='operation.button.search'/>"/>
	<c:if test="${listFlag =='1'}">
			 <jecs:power powerCode="addPdFlitWarehouse">
			<input type="button" class="button" style="margin-right: 5px"
		        onclick="location.href='<c:url value="/editPdFlitWarehouse.html"/>?strAction=addPdFlitWarehouse'"
		        value="<jecs:locale key="operation.button.add"/>"/>
		    </jecs:power>
    </c:if> --%>

</c:set>








<form name="frm" action="<c:url value='/pdFlitWarehouses.html'/>" >
<input type="hidden" id="strAction" name="strAction" value="${requestParaMap.strAction}"/>
<div class="searchBar">
     <div class="new_searchBar"> 
		<jecs:title  key="pdFlitWarehouseDetail.fwNo"/>
		<input name="fwNo" id="fwNo" value="<c:out value='${requestParaMap.fwNo}'/>"  cssClass="text medium"/>	
	</div>		
	<div class="new_searchBar"> 
	    <jecs:title key="pd.createTime"/>
	    <input name="createTimeStart" id="createTimeStart" type="text" value="${requestParaMap.createTimeStart }" style="cursor:pointer;width:76px;"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
	    - 
	    <input name="createTimeEnd" id="createTimeEnd" type="text" value="${requestParaMap.createTimeEnd }" style="cursor:pointer;width:76px;"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
	</div>		
		<%-- <jecs:title key="pd.createTime"/>
		<input name="createTimeStart" id="createTimeStart" size='11'  value="${requestParaMap.createTimeStart}">
      	<img src="./images/calendar/calendar7.gif" id="img_startTime" style="cursor: pointer;" title="<jecs:locale key="Calendar.TT.SEL_DATE"/>"/> 
				<script type="text/javascript"> 
					Calendar.setup({
					inputField     :    "createTimeStart", 
					ifFormat       :    "%Y-%m-%d",  
					button         :    "img_startTime", 
					singleClick    :    true
					}); 
				</script>
				-<input name="createTimeEnd" id="createTimeEnd" size='11'  value="${requestParaMap.createTimeEnd}">
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
		     <jecs:title key="busi.warehouse.outwatehouseno"/>
		     <input name="outWarehouseNo" size="10" id="outWarehouseNo" value="<c:out value='${requestParaMap.outWarehouseNo}'/>" onclick="selectWarehouse('outWarehouseNo');" cssClass="text medium"/>	
		</div>	
		<div class="new_searchBar"> 	
		<jecs:title key="busi.pd.enterWarehouseNo"/>
		<input name="inWarehouseNo" size="10" id="inWarehouseNo" value="<c:out value='${requestParaMap.inWarehouseNo}'/>"  onclick="selectAllWarehouse('inWarehouseNo');" cssClass="text medium"/>
	</div>
	<div class="new_searchBar"> 
	  <jecs:title  key="pd.orderflag"/>
		<jecs:list listCode="pdflitwarehouse.orderflag" name="orderFlag" id="orderFlag" showBlankLine="true" value="${requestParaMap.orderFlag}" defaultValue=""/>		
		</div>	
	<div class="new_searchBar"> 
			<jecs:title key="pd.createUserNo"/>
			<input name="createUsrCode" id="createUsrCode" value="<c:out value='${requestParaMap.createUsrCode}'/>"  cssClass="text medium"/>
		</div>		
		<div class="new_searchBar">
		     <jecs:title key="pd.okTime"/>
		     <input name="okTimeStart" id="okTimeStart" type="text" value="${requestParaMap.okTimeStart }" style="cursor:pointer;width:76px;"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
	         - 
	         <input name="okTimeEnd" id="okTimeEnd" type="text" value="${requestParaMap.okTimeEnd }" style="cursor:pointer;width:76px;"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
		</div>			
			<%-- <jecs:title key="pd.okTime"/>
				<input name="okTimeStart" id="okTimeStart" size='11'  value="${requestParaMap.okTimeStart}">
      	<img src="./images/calendar/calendar7.gif" id="img_okTimeStart" style="cursor: pointer;" title="<jecs:locale key="Calendar.TT.SEL_DATE"/>"/> 
				<script type="text/javascript"> 
					Calendar.setup({
					inputField     :    "okTimeStart", 
					ifFormat       :    "%Y-%m-%d",  
					button         :    "img_okTimeStart", 
					singleClick    :    true
					}); 
				</script>
				- <input name="okTimeEnd" id="okTimeEnd" size='11'  value="${requestParaMap.okTimeEnd}">
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
		     <jecs:title key="pdFlitWarehouse.arrivedPdFlitWarehouse"/>
		     <input name="toTimeStart" id="toTimeStart" type="text" value="${requestParaMap.toTimeStart }" style="cursor:pointer;width:76px;"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
	         - 
	         <input name="toTimeEnd" id="toTimeEnd" type="text" value="${requestParaMap.toTimeEnd }" style="cursor:pointer;width:76px;"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
		</div>	
			<%-- <jecs:title key="pdFlitWarehouse.arrivedPdFlitWarehouse"/>
				<input name="toTimeStart" id="toTimeStart" size='11'  value="${requestParaMap.toTimeStart}">
      	<img src="./images/calendar/calendar7.gif" id="img_toTimeStart" style="cursor: pointer;" title="<jecs:locale key="Calendar.TT.SEL_DATE"/>"/> 
				<script type="text/javascript"> 
					Calendar.setup({
					inputField     :    "toTimeStart", 
					ifFormat       :    "%Y-%m-%d",  
					button         :    "img_toTimeStart", 
					singleClick    :    true
					}); 
				</script>
				- <input name="toTimeEnd" id="toTimeEnd" size='11'  value="${requestParaMap.toTimeEnd}">
      	<img src="./images/calendar/calendar7.gif" id="img_toTimeEnd" style="cursor: pointer;" title="<jecs:locale key="Calendar.TT.SEL_DATE"/>"/> 
				<script type="text/javascript"> 
					Calendar.setup({
					inputField     :    "toTimeEnd", 
					ifFormat       :    "%Y-%m-%d",  
					button         :    "img_toTimeEnd", 
					singleClick    :    true
					}); 
				</script> --%>
			<div class="new_searchBar">	
			<jecs:title key="busi.user.check"/>
				<input name="checkUsrCode" size="10" id="checkUsrCode" value="<c:out value='${requestParaMap.checkUsrCode}'/>"  cssClass="text medium"/>
			</div>
			<div class="new_searchBar">		
			<jecs:title key="pd.confirmUserNo"/>
			<input name="okUsrCode" size="10" id="okUsrCode" value="<c:out value='${requestParaMap.okUsrCode}'/>"  cssClass="text medium"/>
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
	tableId="pdFlitWarehouseListTable"
	items="pdFlitWarehouseList"
	var="pdFlitWarehouse"
	action="${pageContext.request.contextPath}/pdFlitWarehouses.html"
	width="100%"
	retrieveRowsCallback="limit"
	method="post"
	form="frm"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
				<!--ec:exportCsv fileName="flitting.csv" /-->
				<!--ec:exportXls fileName="flitting.xls"/-->
			<ec:row onclick="showTR('tr${ROWCOUNT}');">
					
					
					
					<ec:column property="fwNo" title="pdFlitWarehouseDetail.fwNo" />
    			
    			<ec:column property="createUsrCode" title="pd.createUserNo" />
    			<ec:column property="createTime" title="pd.createTime" >
    					<fmt:formatDate value="${pdFlitWarehouse.createTime}" pattern="yyyy-MM-dd"/>
    			</ec:column>
    			
    			<ec:column property="outWarehouse.warehouseNo" title="busi.warehouse.outwatehouseno" />
    			<ec:column property="inWarehouse.warehouseNo" title="busi.pd.enterWarehouseNo" />
    		
    			
    			<ec:column property="orderFlag" title="pd.orderflag" >
    				<jecs:code listCode="pdflitwarehouse.orderflag" value="${pdFlitWarehouse.orderFlag}"/>
    			</ec:column>
    				<ec:column property="edit" title="title.operation" sortable="false" styleClass="centerColumn" viewsAllowed="html"	>
							<img src="<c:url value='/images/icons/editIcon.gif'/>" onclick="javascript:editPdFlitWarehouse('${pdFlitWarehouse.fwNo}','${requestParaMap.strAction}')" style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					
							 <c:if test="${requestParaMap.strAction=='confirmPdFlitWarehouse'}">		 	
								<img src="<c:url value='/images/icons/printer.gif'/>" 
								onclick="javascript:printOrder('${pdFlitWarehouse.fwNo}')"
								 style="cursor: pointer;" title="<jecs:locale key="button.print"/>"/> 
							</c:if>
					</ec:column>
    			
    			<!--ec:column property="checkUsrCode" title="busi.user.check" /-->
    			<!--ec:column property="okUsrCode" title="pd.confirmUserNo" /-->
    			<!--ec:column property="toUsrCode" title="busi.pd.tousrno" /-->
    		 
    			<c:if test="${ROWCOUNT>0}">
						<c:if test="${ROWCOUNT%2!=0}"><tr class="odddetail"  style="display:none" id="tr${ROWCOUNT}"></c:if>
						<c:if test="${ROWCOUNT%2==0}"><tr class="evendetail" style="display:none"  id="tr${ROWCOUNT}"></c:if>
							<td align="right" valign="top">&nbsp;</td>
							
							<td colspan="5">
		
							
								<c:forEach items="${pdFlitWarehouse.pdFlitWarehouseDetails}" var="pdFlitWarehouseDetail" varStatus="status">
									<c:if test="${!status.first}"><br/></c:if>
										<img src="<c:url value='/images/menu_tree/file12.gif'/>"/>
									<font color=#888888>[<font color="green">${pdFlitWarehouseDetail.qty}</font>]${pdFlitWarehouseDetail.productNo}/
										${compamyProductMap[pdFlitWarehouseDetail.productNo]}
										
										</font>
										
								</c:forEach>
							</td>
							<td colspan="3">
								<font color="green">${pdFlitWarehouse.remark}</font>
							</td>	
							
						</tr>
				</c:if>
    		
    		
    			
				</ec:row>
				
				
				
				
</ec:table>	

</form>

<c:if test="${requestParaMap.strAction=='statisticPdFlitWarehouse'}">
<ec:table 
	tableId="pdFlitWarehouseTotalTable"
	items="pdFlitWarehouseTotal"
	var="pd"
	action="${pageContext.request.contextPath}/pdFlitWarehouses.html"
	width="100%"
	method="post"
	autoIncludeParameters="true"
	showPagination="false"
	sortable="false" imagePath="./images/extremetable/*.gif">
				<ec:parameter name="strAction" value="${requestParaMap.strAction}"/>
				<ec:exportCsv fileName="flittingTotal.csv" encoding="UTF-8"/>
				<ec:exportXls fileName="flittingTotal.xls" encoding="UTF-8"/>
				<ec:row >
					
					<ec:column property="PRODUCT_NO" title="busi.product.productno" />
    			<ec:column property="PRODUCT_NAME" title="pmProduct.productName" />
    			<ec:column property="QTY" title="pd.qty" calc="total" calcTitle="busi.logistics.total" styleClass="numberColumn"/>
    			
    		
				</ec:row>

</ec:table>	
</c:if>




<script type="text/javascript">

   function editPdFlitWarehouse(fwNo,strAction){
   				//form1.strAction.value=strAction;
   				//form1.fwNo.value=fwNo;
   				//form1.submit();
   				<jecs:power powerCode="${requestParaMap.strAction}">
   				
   				
						window.location="editPdFlitWarehouse.html?fwNo="+fwNo+"&strAction="+strAction;
					
					</jecs:power>
		}
		function printOrder(siNo){
	 		window.open("<c:url value='/pdOrderPrints.html'/>?strAction=printFlitWarehouse&orderNo="+siNo);
	 	}
	function selectWarehouse(elementId){ 
		  <c:if test="${sessionScope.sessionLogin.isManager}">
		 		 selectAllWarehouse(elementId)
		  </c:if>
		  <c:if test="${!sessionScope.sessionLogin.isManager}">
		  	open("<c:url value='/pdWarehouses.html'/>?strAction=selectWarehouse&elementId="+elementId,"","height=400, width=1000, top=20, left=20, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no");
		  </c:if>
     			
     		
     	}
     	
     function pdFlitWarehouseAdd(){
          	window.location="editPdFlitWarehouse.html?strAction=addPdFlitWarehouse";
     }
	
</script>
