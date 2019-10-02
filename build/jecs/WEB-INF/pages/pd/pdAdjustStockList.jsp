<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="pdAdjustStockList.title"/></title>
<content tag="heading"><jecs:locale key="pdAdjustStockList.heading"/></content>
<meta name="menu" content="PdAdjustStockMenu"/>
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script> 

<c:set var="buttons">
<div class="new_searchBar"> 
    <button type="submit" class="btn btn_sele"  style="width:auto" >
		        <jecs:locale  key='operation.button.search'/>
    </button>
    <c:if test="${listFlag == '1'}">
		<jecs:power powerCode="addPdAdjustStock">
	        	<button type="button" class="btn btn_ins"  style="width:auto" onclick="pdAdjustStockAdd();">
					        <jecs:locale key="operation.button.add"/>
			    </button>
       </jecs:power>
	</c:if>
</div>
	<!-- <c:if test="${listFlag == '1'}">
		<jecs:power powerCode="addPdAdjustStock">
			<input type="button" class="button" style="margin-right: 5px"
        onclick="location.href='<c:url value="/editPdAdjustStock.html"/>?strAction=addPdAdjustStock'"
        value="<jecs:locale key="operation.button.add"/>"/>
        	
    </jecs:power>
	</c:if>
	<input type="submit" class="button" value="<jecs:locale  key='operation.button.search'/>"/> -->
    
</c:set>




	





	<form name="frm" action="<c:url value='/pdAdjustStocks.html'/>" >
		<input type="hidden" id="strAction" name="strAction" value="${requestParaMap.strAction}"/>
	<div class="searchBar">
	
	
		
		
	<div class="new_searchBar">
		<jecs:title  key="pdAdjustStock.asNo"/>
			<input name="asNo" id="asNo" value="<c:out value='${requestParaMap.asNo}'/>"  cssClass="text medium"/>
	</div>
	<div class="new_searchBar" style="white-space:nowrap;">	
		<jecs:title key="busi.warehouse.warehouseno"/>
			<input name="warehouseNo" id="warehouseNo" value="<c:out value='${requestParaMap.warehouseNo}'/>"   cssClass="text medium"/>
			<button name="select" class="btn btn_sele" onclick="selectWarehouse('warehouseNo');" >
				   <jecs:locale key="button.select"/>
			</button>
<!-- 			<input type="button" class="button" name="select" onclick="selectWarehouse('warehouseNo');" value="<jecs:locale key="button.select"/>" />
 -->	</div>	
 <div class="new_searchBar">	
		<jecs:title key="pdAdjustStock.astNo"/>
			<jecs:list listCode="astnolist" name="astNo" id="astNo" showBlankLine="true" value="${requestParaMap.astNo}" defaultValue=""/>
	</div>
	<div class="new_searchBar">	
	    <jecs:title key="pd.createTime"/>
	    <input name="createTimeStart" id="createTimeStart" type="text" value="${requestParaMap.createTimeStart }" style="cursor:pointer;width:76px;"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
	        - 
	        <input name="createTimeEnd" id="createTimeEnd" type="text" value="${requestParaMap.createTimeEnd }" style="cursor:pointer;width:76px;"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
	</div>		
		<!-- <jecs:locale key="pd.createTime"/>
			<input name="createTimeStart" size='11' id="createTimeStart" size="13" value="${requestParaMap.createTimeStart}"/>
      	<img src="./images/calendar/calendar7.gif" id="img_startTime" style="cursor: pointer;" title="<jecs:locale key="Calendar.TT.SEL_DATE"/>"/> 
				<script type="text/javascript"> 
					Calendar.setup({
					inputField     :    "createTimeStart", 
					ifFormat       :    "%Y-%m-%d",  
					button         :    "img_startTime", 
					singleClick    :    true
					}); 
				</script>
				-<input name="createTimeEnd" size='11' id="createTimeEnd"  size="13" value="${requestParaMap.createTimeEnd}"/>
      	<img src="./images/calendar/calendar7.gif" id="img_endTime" style="cursor: pointer;" title="<jecs:locale key="Calendar.TT.SEL_DATE"/>"/> 
				<script type="text/javascript"> 
					Calendar.setup({
					inputField     :    "createTimeEnd", 
					ifFormat       :    "%Y-%m-%d",  
					button         :    "img_endTime", 
					singleClick    :    true
					}); 
				</script> -->
		<div class="new_searchBar">	
	   <jecs:title key="pd.okTime"/>
	    <input name="okTimeStart" id="okTimeStart" type="text" value="${requestParaMap.okTimeStart }" style="cursor:pointer;width:76px;"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
	        - 
	        <input name="okTimeEnd" id="okTimeEnd" type="text" value="${requestParaMap.okTimeEnd }" style="cursor:pointer;width:76px;"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
	</div>		
			
			<!-- <jecs:locale key="pd.okTime"/>
				<input name="okTimeStart" size='11' id="okTimeStart"  size="13" value="${requestParaMap.okTimeStart}"/>
      	<img src="./images/calendar/calendar7.gif" id="img_okTimeStart" style="cursor: pointer;" title="<jecs:locale key="Calendar.TT.SEL_DATE"/>"/> 
				<script type="text/javascript"> 
					Calendar.setup({
					inputField     :    "okTimeStart", 
					ifFormat       :    "%Y-%m-%d",  
					button         :    "img_okTimeStart", 
					singleClick    :    true
					}); 
				</script>
				-<input name="okTimeEnd" size='11' id="okTimeEnd"  size="13" value="${requestParaMap.okTimeEnd}"/>
      	<img src="./images/calendar/calendar7.gif" id="img_okTimeEnd" style="cursor: pointer;" title="<jecs:locale key="Calendar.TT.SEL_DATE"/>"/> 
				<script type="text/javascript"> 
					Calendar.setup({
					inputField     :    "okTimeEnd", 
					ifFormat       :    "%Y-%m-%d",  
					button         :    "img_okTimeEnd", 
					singleClick    :    true
					}); 
				</script> -->
				
		<div class="new_searchBar">			
		<jecs:title  key="pd.createUserNo"/>
		<input name="createUsrCode" id="createUsrCode" value="<c:out value='${requestParaMap.createUsrCode}'/>"  cssClass="text medium"/>
		</div>
		<div class="new_searchBar">			
				<jecs:title  key="busi.user.check"/>
				<input name="checkUsrCode" id="checkUsrCode" value="<c:out value='${requestParaMap.checkUsrCode}'/>"  cssClass="text medium"/>	
		</div>			
		<div class="new_searchBar">				
				<jecs:title  key="pd.confirmUserNo"/>
				<input name="okUsrCode" id="okUsrCode" value="<c:out value='${requestParaMap.okUsrCode}'/>"  cssClass="text medium"/>
	    </div>
		
	
		
	
		<c:out value="${buttons}" escapeXml="false"/>
		
		
		
		

</div>
		
		



<ec:table 
	tableId="pdAdjustStockListTable"
	items="pdAdjustStockList"
	var="pdAdjustStock"
	action="${pageContext.request.contextPath}/pdAdjustStocks.html"
	form="frm"
	method="post"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
	      
					<ec:row onclick="showTR('tr${ROWCOUNT}');">
					
					
					
					<c:if test="${sessionScope.sessionLogin.isManager}">
    					<ec:column property="companyCode" title="sys.company.code" />
    			</c:if>
    			
    			<ec:column property="asNo" title="pdAdjustStock.asNo" />
    			<ec:column property="createUsrCode" title="pd.createUserNo" />
    			<ec:column property="createTime" title="pd.createTime" >
    				<fmt:formatDate value="${pdAdjustStock.createTime}" pattern="yyyy-MM-dd"/>
    			</ec:column>
    			<ec:column property="warehouseNo" title="busi.warehouse.warehouseno" />
    			
    			<ec:column property="checkUsrCode" title="busi.user.check" />
    					
    				<ec:column property="orderFlag" title="pd.orderflag" >
    				<jecs:code listCode="pd.orderflag.exchangeorder" value="${pdAdjustStock.orderFlag}"/>
    			</ec:column>
    				
    			<!--ec:column property="checkUsrNo" title="busi.user.check" /-->
    			
    			
    			<!--ec:column property="okUsrNo" title="pd.confirmUserNo" /-->
    			
    			
    			
    			<ec:column property="astNo" title="pdAdjustStock.astNo" >
    				<jecs:code listCode="astnolist" value="${pdAdjustStock.astNo}"/>
    			</ec:column>
				</ec:row>
				<ec:column property="edit" title="title.operation" sortable="false" styleClass="centerColumn" viewsAllowed="html"	>
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editPdAdjustStock('${pdAdjustStock.asNo}','${requestParaMap.strAction}')" 
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
									
						 <c:if test="${requestParaMap.strAction=='confirmPdAdjustStock'}">		 	
								<img src="<c:url value='/images/icons/printer.gif'/>" 
								onclick="javascript:printOrder('${pdAdjustStock.asNo}')"
								 style="cursor: pointer;" title="<jecs:locale key="button.print"/>"/> 
							</c:if>
					</ec:column>
				
				<c:if test="${ROWCOUNT>0}">
						<c:if test="${ROWCOUNT%2!=0}"><tr class="odddetail" style="display:none" id="tr${ROWCOUNT}"></c:if>  
						<c:if test="${ROWCOUNT%2==0}"><tr class="evendetail" style="display:none" id="tr${ROWCOUNT}"></c:if> 
							<td align="right" valign="top">&nbsp;</td>
							<c:if test="${sessionScope.sessionLogin.isManager}">
								<td colspan="9">
							</c:if>
							<c:if test="${!sessionScope.sessionLogin.isManager}">
								<td colspan="8">
							</c:if>
							
								<c:forEach items="${pdAdjustStock.pdAdjustStockDetails}" var="pdAdjustStockDetail" varStatus="status">
									<c:if test="${!status.first}"><br/></c:if>
										
									<font color=#888888>[<font color="green">${pdAdjustStockDetail.qty}</font>]${pdAdjustStockDetail.productNo}/
										${compamyProductMap[pdAdjustStockDetail.productNo]}
										</font>
									
								</c:forEach>
							</td>
							
						</tr>
				</c:if>

</ec:table>	
</form>
<c:if test="${requestParaMap.strAction=='statisticPdAdjustStock'}">
<ec:table 
	tableId="pdAdjustStockTotalTable"
	items="pdAdjustStockTotal"
	var="pd"
	action="${pageContext.request.contextPath}/pdAdjustStocks.html"
	width="100%"
	method="post"
	showPagination="false"
	 sortable="false" imagePath="./images/extremetable/*.gif">
				<ec:parameter name="strAction" value="${requestParaMap.strAction}"/>
				<ec:exportCsv fileName="adjustStocksTotal.csv" encoding="UTF-8"/>
				<ec:exportXls fileName="adjustStocksTotal.xls" encoding="UTF-8"/>
				<ec:row >
					
					<ec:column property="PRODUCT_NO" title="busi.product.productno" />
    			<ec:column property="PRODUCT_NAME" title="pmProduct.productName" />
    			<ec:column property="QTY_POSITIVE" calc="total" calcTitle="busi.logistics.total" styleClass="numberColumn" title="pd.qty" />
    			<ec:column property="QTY_NEGATIVE" calc="total" calcTitle="busi.logistics.total" styleClass="numberColumn" title="pd.qty" />
    			<ec:column property="QTY_TOTAL" calc="total" calcTitle="busi.logistics.total"  styleClass="numberColumn" title="busi.logistics.total" />
    				
				</ec:row>

</ec:table>	
</c:if>




<script type="text/javascript">

   function editPdAdjustStock(asNo,strAction){
   	<jecs:power powerCode="${requestParaMap.strAction}">
   			
   				window.location="editPdAdjustStock.html?asNo="+asNo+"&strAction="+strAction;
   			
   	</jecs:power>
   				
				//	window.location="editPdAdjustStock.html?asNo="+asNo;
		}
		
		function printOrder(siNo){
	 		window.open("<c:url value='/pdOrderPrints.html'/>?strAction=printAdjustStock&orderNo="+siNo);
	 	}
		function selectWarehouse(str){
     			
     			window.open("<c:url value='/pdWarehouses.html'/>?strAction=selectWarehouse&elementId="+str,"","height=300, width=1000, top=20, left=20, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no");
     	}
     	
     	function pdAdjustStockAdd(){
     	        window.location="editPdAdjustStock.html?strAction=addPdAdjustStock";
     	}
     	
</script>
