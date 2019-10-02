<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="pdCombinationOrderList.title"/></title>
<content tag="heading"><jecs:locale key="pdCombinationOrderList.heading"/></content>
<meta name="menu" content="PdCombinationOrderMenu"/>
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script> 

<c:set var="buttons">

<div class="new_searchBar">
		<button type="submit" class="btn btn_sele"  style="width:auto" >
				        <jecs:locale  key='operation.button.search'/>
		</button>
		 <jecs:power powerCode="addPdCombinationOrder">
			<c:if test="${requestParaMap.strAction=='editPdCombinationOrder'}">
			    <button type="button" class="btn btn_ins"  style="width:auto" onclick="editPdCombinationOrderAdd();">
				        <jecs:locale key="button.add"/>
		        </button>
			</c:if>
		</jecs:power>
</div>		
		
		<%-- <jecs:power powerCode="addPdCombinationOrder">
			<c:if test="${requestParaMap.strAction=='editPdCombinationOrder'}">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editPdCombinationOrder.html"/>?strAction=addPdCombinationOrder'"
			        value="<jecs:locale key="button.add"/>"/>
			</c:if>
		</jecs:power>
		<input type="submit" class="button" value="<jecs:locale  key='operation.button.search'/>"/> --%>
</c:set>
<%-- <form name="frm" action="<c:url value='/pdAdjustStocks.html'/>" > --%>
<form name="frm" action="<c:url value='/pdCombinationOrders.html'/>" >
		<input type="hidden" id="strAction" name="strAction" value="${requestParaMap.strAction}"/>
	<div class="searchBar">
		<div class="new_searchBar">
		<jecs:title  key="pd.orderNo"/>
			<input name="pcNo" id="owNo" value="<c:out value='${owNo}'/>"  cssClass="text medium"/>
		</div>
		<div class="new_searchBar" style="white-space:nowrap;">
		<jecs:title key="busi.warehouse.warehouseno"/>
			<input name="warehouseNo" id="warehouseNo" value="<c:out value='${warehouseNo}'/>"   cssClass="text medium"/>
			<button name="select" class="btn btn_sele" onclick="selectWarehouse('warehouseNo');" >
				     <jecs:locale key="button.select"/>
			</button>
<%-- 			<input type="button" class="button" name="select" onclick="selectWarehouse('warehouseNo');" value="<jecs:locale key="button.select"/>" />
 --%>		
		</div>
		<div class="new_searchBar">
		     <jecs:title key="pd.createTime"/>
		      <input name="createTimeStart" id="createTimeStart" type="text" value="${createTimeStart }" style="cursor: pointer;width:76px;"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
	            - 
	            <input name="createTimeEnd" id="createTimeEnd" type="text" value="${createTimeEnd }" style="cursor:pointer;width:76px;"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
		</div>
		<%-- <jecs:locale key="pd.createTime"/>
			<input name="createTimeStart" size="11" id="createTimeStart"  value="${createTimeStart}">
      	<img src="./images/calendar/calendar7.gif" id="img_startTime" style="cursor: pointer;" title="<jecs:locale key="Calendar.TT.SEL_DATE"/>"/> 
				<script type="text/javascript"> 
					Calendar.setup({
					inputField     :    "createTimeStart", 
					ifFormat       :    "%Y-%m-%d",  
					button         :    "img_startTime", 
					singleClick    :    true
					}); 
				</script>
				-<input name="createTimeEnd" size="11" id="createTimeEnd"  value="${createTimeEnd}">
      	<img src="./images/calendar/calendar7.gif" id="img_endTime" style="cursor: pointer;" title="<jecs:locale key="Calendar.TT.SEL_DATE"/>"/> 
				<script type="text/javascript"> 
					Calendar.setup({
					inputField     :    "createTimeEnd", 
					ifFormat       :    "%Y-%m-%d",  
					button         :    "img_endTime", 
					singleClick    :    true
					}); 
				</script> --%>
		<div class="new_searchBar" >
		     <jecs:title key="pd.okTime"/>
		     <input name="okTimeStart" id="okTimeStart" type="text" value="${okTimeStart }" style="cursor: pointer;width:76px;"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
	            - 
	         <input name="okTimeEnd" id="okTimeEnd" type="text" value="${okTimeEnd }" style="cursor:pointer;width:76px;"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
		</div>
		
			<%-- <input name="okTimeStart" size='11' id="okTimeStart"  value="${okTimeStart}">
      	<img src="./images/calendar/calendar7.gif" id="img_okTimeStart" style="cursor: pointer;" title="<jecs:locale key="Calendar.TT.SEL_DATE"/>"/> 
				<script type="text/javascript"> 
					Calendar.setup({
					inputField     :    "okTimeStart", 
					ifFormat       :    "%Y-%m-%d",  
					button         :    "img_okTimeStart", 
					singleClick    :    true
					}); 
				</script>
				- <input name="okTimeEnd"  size='11' id="okTimeEnd"  value="${okTimeEnd}">
      	<img src="./images/calendar/calendar7.gif" id="img_okTimeEnd" style="cursor: pointer;" title="<jecs:locale key="Calendar.TT.SEL_DATE"/>"/> 
				<script type="text/javascript"> 
					Calendar.setup({
					inputField     :    "okTimeEnd", 
					ifFormat       :    "%Y-%m-%d",  
					button         :    "img_okTimeEnd", 
					singleClick    :    true
					}); 
				</script> --%>
				
			
		<c:out value="${buttons}" escapeXml="false"/>
		</div>
<ec:table 
	tableId="pdCombinationOrderListTable"
	items="pdCombinationOrderList"
	var="pdCombinationOrder"
	form="frm"
	action="${pageContext.request.contextPath}/pdCombinationOrders.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
					<ec:row onclick="showTR('tr${ROWCOUNT}');">
				
					<c:if test="${sessionScope.sessionLogin.isManager}">
    				<ec:column property="companyCode" title="sys.company.code" />
    			</c:if>
    			<ec:column property="pcNo" title="pd.orderNo" />
    			<ec:column property="warehouseNo" title="busi.warehouse.warehouseno" />
    			<ec:column property="productNo" title="busi.product.productno" />
    			<ec:column property="createUsrCode" title="pd.createUserNo" />
    		 
    			<ec:column property="createTime" title="pd.createTime" >
    				<fmt:formatDate value="${pdCombinationOrder.createTime}" pattern="yyyy-MM-dd"/> 
    			</ec:column>
    			
    			<ec:column property="checkUsrCode" title="busi.user.check" />
    		 
    			<ec:column property="okUsrCode" title="pd.confirmUserNo" />
    			<ec:column property="okTime"   title="pd.okTime" >
    				<fmt:formatDate value="${pdCombinationOrder.okTime}" pattern="yyyy-MM-dd"/>
    			</ec:column>	
    			
    			<ec:column property="orderFlag" title="pocounterorder.csstatus">
    				<jecs:code listCode="pd.orderflag.exchangeorder" value="${pdCombinationOrder.orderFlag}"/>
    			</ec:column>
    			
    			
    				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
    					<jecs:power powerCode="${param.strAction}">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editPdCombinationOrder('${pdCombinationOrder.pcNo}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/>
							</jecs:power> 
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
							
								<c:forEach items="${pdCombinationOrder.pdCombinationDetails}" var="pdCombinationDetail" varStatus="status">
									<c:if test="${!status.first}"><br/></c:if>
									<img src="<c:url value='/images/menu_tree/file12.gif'/>"/>	
									<font color=#888888>[<font color="green">${pdCombinationDetail.qty}</font>]${pdCombinationDetail.productNo}/${compamyProductMap[pdCombinationDetail.productNo]}
													</font>
									
								</c:forEach>
							</td>
							
						</tr>
				</c:if>
				
				</ec:row>

</ec:table>	
</form>


<c:if test="${requestParaMap.strAction=='statisticPdCombinationOrder'}">
<ec:table 
	tableId="pdCombinationDetailTotalTable"
	items="pdCombinationDetailTotal"
	var="pd"
	action="${pageContext.request.contextPath}/pdCombinationOrders.html"
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

   function editPdCombinationOrder(pcNo){
   		 
					window.location="editPdCombinationOrder.html?strAction=${param.strAction}&pcNo="+pcNo;
			 
		}
	
	function editPdCombinationOrderAdd(){
	      window.location="editPdCombinationOrder.html?strAction=addPdCombinationOrder";
	}

</script>
