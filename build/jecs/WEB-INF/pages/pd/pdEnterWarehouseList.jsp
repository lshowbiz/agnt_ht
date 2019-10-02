<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="pdEnterWarehouseList.title"/></title>
<content tag="heading"><jecs:locale key="pdEnterWarehouseList.heading"/></content>
<meta name="menu" content="PdEnterWarehouseMenu"/>
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script> 

<c:set var="buttons">	
     
          <c:if test="${requestParaMap.strAction!='statisticPdEnterWarehouse'}">
           <div class="new_searchBar"> 
		            &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp;
		           <button type="submit" class="btn btn_sele"  style="width:auto" >
						        <jecs:locale  key='operation.button.search'/>
				    </button>
		  
				 	<c:if test="${listFlag == '1'}">
				 		<jecs:power powerCode="addPdEnterWarehouse">
				 			<button type="button" class="btn btn_ins"  style="width:auto" onclick="pdEnterWarehouseAdd();">
								        <jecs:locale key="operation.button.add"/>
						    </button>
				 			
							<%-- <input type="button" class="button" style="margin-right: 5px"
				        onclick="location.href='<c:url value="/editPdEnterWarehouse.html"/>?strAction=addPdEnterWarehouse'"
				        value="<jecs:locale key="operation.button.add"/>"/> --%>
				        
				      </jecs:power>
			  
			      </c:if>
		    </div> 
           </c:if>  
		  
</c:set>




<form name="frm" action="<c:url value='/pdEnterWarehouses.html'/>" >
	<input type="hidden" id="strAction" name="strAction" value="${requestParaMap.strAction}"/>
<div class="searchBar">
    <div class="new_searchBar">
		<jecs:title  key="pdEnterWarehouse.ewNo"/>
		<input name="ewNo" id="ewNo" value="<c:out value='${requestParaMap.ewNo}'/>" cssClass="text medium"/>	
	</div>
	<div class="new_searchBar" style="white-space:nowrap;">	
		<jecs:title key="busi.warehouse.warehouseno"/>
			<input name="warehouseNo" id="warehouseNo" value="<c:out value='${requestParaMap.warehouseNo}'/>"   cssClass="text medium"/>
			<button name="select" class="btn btn_sele" onclick="selectWarehouse('warehouseNo');" >
				   <jecs:locale key="button.select"/>
			</button>
<%-- 			<input type="button" class="button" name="select" onclick="selectWarehouse('warehouseNo');" value="<jecs:locale key="button.select"/>" />
 --%>	</div>
	<div class="new_searchBar">
	        	<jecs:title key="pd.createTime"/>
	        	<input name="createTimeStart" id="createTimeStart" type="text" value="${requestParaMap.createTimeStart }" style="cursor:pointer;width:76px;"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
	        - 
	        <input name="createTimeEnd" id="createTimeEnd" type="text" value="${requestParaMap.createTimeEnd }" style="cursor:pointer;width:76px;"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
	</div>	
		<%-- <jecs:title key="pd.createTime"/>
			<input name="createTimeStart" size='11' id="createTimeStart"  value="${requestParaMap.createTimeStart}">
      	<img src="./images/calendar/calendar7.gif" id="img_startTime" style="cursor: pointer;" title="<jecs:locale key="Calendar.TT.SEL_DATE"/>"/> 
				<script type="text/javascript"> 
					Calendar.setup({
					inputField     :    "createTimeStart", 
					ifFormat       :    "%Y-%m-%d",  
					button         :    "img_startTime", 
					singleClick    :    true
					}); 
				</script>
				-<input name="createTimeEnd" size='11' id="createTimeEnd"  value="${requestParaMap.createTimeEnd}">
      	<img src="./images/calendar/calendar7.gif" id="img_endTime" style="cursor: pointer;" title="<jecs:locale key="Calendar.TT.SEL_DATE"/>"/> 
				<script type="text/javascript"> 
					Calendar.setup({
					inputField     :    "createTimeEnd", 
					ifFormat       :    "%Y-%m-%d",  
					button         :    "img_endTime", 
					singleClick    :    true
					}); 
				</script> --%>
		<%-- <jecs:title key="pd.okTime"/>
		<input name="okTimeStart" size='11' id="okTimeStart"   value="${requestParaMap.okTimeStart}">
      	<img src="./images/calendar/calendar7.gif" id="img_okTimeStart" style="cursor: pointer;" title="<jecs:locale key="Calendar.TT.SEL_DATE"/>"/> 
				<script type="text/javascript"> 
					Calendar.setup({
					inputField     :    "okTimeStart", 
					ifFormat       :    "%Y-%m-%d",  
					button         :    "img_okTimeStart", 
					singleClick    :    true
					}); 
				</script>
				- <input name="okTimeEnd"  size='11' id="okTimeEnd"   value="${requestParaMap.okTimeEnd}">
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
	        	<jecs:title key="pd.okTime"/>
	        	<input name="okTimeStart" id="okTimeStart" type="text" value="${requestParaMap.okTimeStart }" style="cursor:pointer;width:76px;"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
	        - 
	        <input name="okTimeEnd" id="okTimeEnd" type="text" value="${requestParaMap.okTimeEnd }" style="cursor:pointer;width:76px;"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
	</div>
	<div class="new_searchBar">
		<jecs:title  key="pd.orderflag"/>
		<jecs:list listCode="pd.orderflag.exchangeorder" name="orderFlag" id="orderFlag" showBlankLine="true" value="${requestParaMap.orderFlag}" defaultValue=""/>	
	</div>	
		<c:if test="${requestParaMap.strAction=='statisticPdEnterWarehouse'}">
		  <div class="new_searchBar" style="white-space:nowrap;">	
			<jecs:title  key="busi.product.productno"/>
				<input name="productNo" id="productNo" value="<c:out value='${requestParaMap.productNo}'/>" cssClass="text medium"/>
				  <button type="submit" class="btn btn_sele"  style="width:auto" >
						        <jecs:locale  key='operation.button.search'/>
				    </button>
		  </div>
		</c:if>
		
			<%-- <input type="submit" class="button" value="<jecs:locale  key='operation.button.search'/>"/> --%>
			<c:out value="${buttons}" escapeXml="false"/>
</div>






<ec:table 
	tableId="pdEnterWarehouseListTable"
	items="pdEnterWarehouseList"
	var="pdEnterWarehouse"
	action="${pageContext.request.contextPath}/pdEnterWarehouses.html"
	width="100%"
	form="frm"
	method="post"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
				
				<!--ec:exportCsv fileName="warehousing.csv" /-->
				<!--ec:exportXls fileName="warehousing.xls"/-->
			<ec:row onclick="showTR('tr${ROWCOUNT}');">
					
					<c:if test="${sessionScope.sessionLogin.isManager}">
    					<ec:column property="companyCode" title="sys.company.code" />
    			</c:if>
					<ec:column property="ewNo" title="pdEnterWarehouse.ewNo" />
    			<ec:column property="warehouseNo" title="busi.warehouse.warehouseno" />
    			<ec:column property="createUsrCode" title="pd.createUserNo" />
    			
    				
    			<ec:column property="createTime" title="pd.createTime" >
    				<fmt:formatDate value="${pdEnterWarehouse.createTime}" pattern="yyyy-MM-dd"/>
    			</ec:column >
    			
    			
    			<ec:column property="orderFlag" title="pd.orderflag" >
    				<jecs:code listCode="pd.orderflag.exchangeorder" value="${pdEnterWarehouse.orderFlag}"/>
    			</ec:column>
    				<ec:column property="edit" title="title.operation" sortable="false" styleClass="centerColumn" viewsAllowed="html"	>
							<img src="<c:url value='/images/icons/editIcon.gif'/>" onclick="javascript:editPdEnterWarehouse('${pdEnterWarehouse.ewNo}','${requestParaMap.strAction}')" style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
								<c:if test="${requestParaMap.strAction=='confirmPdEnterWarehouse'}">		 	
								 <img src="<c:url value='/images/icons/printer.gif'/>" 
								onclick="javascript:printOrder('${pdEnterWarehouse.ewNo}')"
								 style="cursor: pointer;" title="<jecs:locale key="button.print"/>"/> 
								</c:if>
					</ec:column>
    			<c:if test="${ROWCOUNT>0}">
						<c:if test="${ROWCOUNT%2!=0}"><tr class="odddetail" style="display:none" id="tr${ROWCOUNT}"></c:if>
						<c:if test="${ROWCOUNT%2==0}"><tr class="evendetail" style="display:none" id="tr${ROWCOUNT}"></c:if>
							<td align="right" valign="top">&nbsp;</td>
							<c:if test="${sessionScope.sessionLogin.isManager}">
								<td colspan="4">
							</c:if>
							<c:if test="${!sessionScope.sessionLogin.isManager}">
								<td colspan="3">
							</c:if>
							
								<c:forEach items="${pdEnterWarehouse.pdEnterWarehouseDetails}" var="pdEnterWarehouseDetail" varStatus="status">
									<c:if test="${!status.first}"><br/></c:if>
										<img src="<c:url value='/images/menu_tree/file12.gif'/>"/>
									<font color=#888888>[<font color="green">${pdEnterWarehouseDetail.qty}</font>]${pdEnterWarehouseDetail.productNo}/
										${compamyProductMap[pdEnterWarehouseDetail.productNo]}
										</font>
									
								</c:forEach>
							</td>
							<td colspan="2">
								<font color="green">${pdEnterWarehouse.remark}</font>
							</td>
						
				</c:if>
    		
    	
				</ec:row>
				
				
				
				

</ec:table>	
</form>
<c:if test="${requestParaMap.strAction=='statisticPdEnterWarehouse'}">
<ec:table 
	tableId="pdEnterWarehouseTotalTable"
	items="pdEnterWarehouseTotal"
	var="pd"
	action="${pageContext.request.contextPath}/pdEnterWarehouses.html"
	width="100%"
	method="post"
	showPagination="false"
	sortable="false" imagePath="./images/extremetable/*.gif">
				<ec:parameter name="strAction" value="${requestParaMap.strAction}"/>
				<ec:exportCsv fileName="warehousingTotal.csv" />
				<ec:exportXls fileName="warehousingTotal.xls"/>
				<ec:row >
					
					<ec:column property="PRODUCT_NO" title="busi.product.productno" />
    			<ec:column property="PRODUCT_NAME" title="pmProduct.productName" />
    			<ec:column property="QTY" title="pd.qty" calc="total" calcTitle="busi.logistics.total" styleClass="numberColumn"/>
    			
    		
				</ec:row>

</ec:table>	
</c:if>
<script type="text/javascript">

   function editPdEnterWarehouse(ewNo,strAction){
   				<jecs:power powerCode="${requestParaMap.strAction}">
   						window.location="editPdEnterWarehouse.html?ewNo="+ewNo+"&strAction="+strAction;
   				</jecs:power>
   				//form1.ewNo.value=ewNo;
   				//form1.submit();
					//window.location="editPdEnterWarehouse.html?ewNo="+ewNo+"&strAction="+strAction;
		}

	function printOrder(siNo){
	 		window.open("<c:url value='/pdOrderPrints.html'/>?strAction=printEnterWarehouse&orderNo="+siNo);
	 	}
	
	function pdEnterWarehouseAdd(){
			window.location="editPdEnterWarehouse.html?strAction=addPdEnterWarehouse";
	}
</script>
