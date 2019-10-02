<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="pdReturnPurchaseList.title"/></title>
<content tag="heading"><jecs:locale key="pdReturnPurchaseList.heading"/></content>
<meta name="menu" content="PdReturnPurchaseMenu"/>
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script> 
<c:set var="buttons">
		<%-- <jecs:power powerCode="addPdReturnPurchase">
			<c:if test="${listFlag==1}">
			<!--		<div id="titleBar">
				
				<span onclick="location.href='<c:url value="/editPdReturnPurchase.html"/>?strAction=addPdReturnPurchase'" style="cursor:pointer">
				<img alt="<jecs:locale  key='operation.button.add'/>" src="images/newIcons/plus_16.gif" border="0" align="absmiddle">
				<font color=black><jecs:locale  key='operation.button.add'/></font>
				</span>
			</div>-->
			
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editPdReturnPurchase.html"/>?strAction=addPdReturnPurchase'"
			        value="<jecs:locale key="operation.button.add"/>"/>
			</c:if>        	
		</jecs:power>
		<input type="submit" class="button" value="<jecs:locale  key='operation.button.search'/>"/> --%>
</c:set>





	
<form name="frm" action="<c:url value='/pdReturnPurchases.html'/>" >
	<input type="hidden" id="strAction" name="strAction" value="${requestParaMap.strAction}"/>
	<div class="searchBar">
	<div class="new_searchBar">
			<jecs:title  key="pd.orderNo"/>
				<input name="rpNo" id="rpNo" value="<c:out value='${requestParaMap.rpNo}'/>"  cssClass="text medium"/>
	</div>
	<div class="new_searchBar">		
			<jecs:title  key="customerRecord.customerNo"/>
				<input name="customCode" id="customCode" value="<c:out value='${requestParaMap.customCode}'/>"  cssClass="text medium"/>
	</div>
	<div class="new_searchBar">		
			<jecs:title  key="pdReturnPurchase.returnWarehouseNo"/>
				<input name="returnWarehouseNo" id="returnWarehouseNo" value="<c:out value='${requestParaMap.returnWarehouseNo}'/>" onclick="selectWarehouse('returnWarehouseNo');" cssClass="text medium"/>
	</div>
	<div class="new_searchBar">		
			<jecs:title key="pd.createUserNo"/>
			<input name="createUsrCode" id="createUsrCode" value="<c:out value='${requestParaMap.createUsrCode}'/>"  cssClass="text medium"/>
	</div>
	<div class="new_searchBar">
	        <jecs:title key="pd.createTime"/>
	         <input name="createTimeStart" id="createTimeStart" type="text" value="${requestParaMap.createTimeStart }" style="cursor: pointer;width:76px;"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
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
				- <input name="createTimeEnd" size='11' id="createTimeEnd"  value="${requestParaMap.createTimeEnd}">
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
	         <input name="okTimeStart" id="okTimeStart" type="text" value="${requestParaMap.okTimeStart }" style="cursor: pointer;width:76px;"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
	            - 
	            <input name="okTimeEnd" id="okTimeEnd" type="text" value="${requestParaMap.okTimeEnd }" style="cursor:pointer;width:76px;"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
	</div>
			<%-- <jecs:title key="pd.okTime"/>
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
			<jecs:title  key="pocounterorder.csstatus"/>
				<jecs:list listCode="pdreturnpurchase.orderflag" name="orderFlag" showBlankLine="true" id="orderFlag" value="${requestParaMap.orderFlag}" defaultValue=""/>
		</div>
		<div class="new_searchBar">			
					<jecs:title  key="customerRecord.type"/>
				<jecs:list listCode="pdreturn.returntype" name="returnType" showBlankLine="true" id="returnType" value="${requestParaMap.returnType}" defaultValue=""/>
		</div>
		<div class="new_searchBar" style="white-space:nowrap;width:400px;">	
			<jecs:title key="busi.user.check"/>
			<input name="checkUsrCode" id="checkUsrCode" value="<c:out value='${requestParaMap.checkUsrCode}'/>"  cssClass="text medium"/>
	        <button type="submit" class="btn btn_sele"  style="width:auto" >
					    <jecs:locale  key='operation.button.search'/>
			</button>
	         <jecs:power powerCode="addPdReturnPurchase">
					<c:if test="${listFlag==1}">
						    <button type="button" class="btn btn_ins"  style="width:auto" onclick="editPdReturnPurchaseAdd();">
							        <jecs:locale key="operation.button.add"/>
					        </button>
					</c:if>        	
		    </jecs:power>
	    </div>
	   <%--  <div class="new_searchBar" > 
	        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	        <button type="submit" class="btn btn_sele"  style="width:auto" >
					    <jecs:locale  key='operation.button.search'/>
			</button>
	         <jecs:power powerCode="addPdReturnPurchase">
					<c:if test="${listFlag==1}">
						    <button type="button" class="btn btn_ins"  style="width:auto" onclick="editPdReturnPurchaseAdd();">
							        <jecs:locale key="operation.button.add"/>
					        </button>
					</c:if>        	
		    </jecs:power>
			
	    </div> --%>
		
		
			
			
			
			
				
				
		
			
			
		
		<c:out value="${buttons}" escapeXml="false"/>
		
		
		
		
	</table>
</div>
	







<ec:table 
	tableId="pdReturnPurchaseListTable"
	items="pdReturnPurchaseList"
	var="pdReturnPurchase"
	retrieveRowsCallback="limit"
	form="frm"
	action="${pageContext.request.contextPath}/pdReturnPurchases.html"
	width="100%"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
	
				<!--ec:exportCsv fileName="returnPurchase.csv" /-->
				<!--ec:exportXls fileName="returnPurchase.xls"/-->
			<ec:row onclick="showTR('tr${ROWCOUNT}');">
					
					
					<c:if test="${sessionScope.sessionLogin.isManager}">
    					<ec:column property="companyCode" title="sys.company.code" />
    			</c:if>
					<ec:column property="rpNo" title="pd.orderNo" />
						
    			<ec:column property="createTime" title="pd.createTime" >
    				<fmt:formatDate value="${pdReturnPurchase.createTime}" pattern="yyyy-MM-dd"/>
    			</ec:column>
    				
    			<ec:column property="customer.userCode" title="customerRecord.customerNo" >
					${pdReturnPurchase.customer.userCode}/${pdReturnPurchase.customer.userName}
				</ec:column>
    		
    			<ec:column property="returnWarehouseNo" title="pdReturnPurchase.returnWarehouseNo" />
    			
    			
    			<ec:column property="createUsrCode" title="pd.createUserNo" />
    			<ec:column property="checkUsrCode" title="busi.user.check" />
    				
    			<ec:column property="orderFlag" title="pocounterorder.csstatus">
    				<jecs:code listCode="pdreturnpurchase.orderflag" value="${pdReturnPurchase.orderFlag}"/>
    			</ec:column>
				</ec:row>
				<ec:column property="edit" title="title.operation" sortable="false" styleClass="centerColumn" viewsAllowed="html"	>
							<img src="<c:url value='/images/newIcons/pencil_16.gif'/>" 
								onclick="javascript:editPdReturnPurchase('${pdReturnPurchase.rpNo}','${requestParaMap.strAction}')"
								 style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
								 	
								 	 <c:if test="${requestParaMap.strAction=='confirmPdReturnPurchase'}">		 	
								<img src="<c:url value='/images/icons/printer.gif'/>" 
								onclick="javascript:printOrder('${pdReturnPurchase.rpNo}')"
								 style="cursor: pointer;" title="<jecs:locale key="button.print"/>"/> 
							</c:if>
					</ec:column>

				<c:if test="${ROWCOUNT>0}">
						<c:if test="${ROWCOUNT%2!=0}"><tr class="odddetail" style="display:none" id="tr${ROWCOUNT}"></c:if>  
						<c:if test="${ROWCOUNT%2==0}"><tr class="evendetail" style="display:none" id="tr${ROWCOUNT}"></c:if> 
							<td align="right" valign="top">&nbsp;</td>
							<c:if test="${sessionScope.sessionLogin.isManager}">
								<td colspan="7">
							</c:if>
							<c:if test="${!sessionScope.sessionLogin.isManager}">
								<td colspan="6">
							</c:if>
							
								<c:forEach items="${pdReturnPurchase.pdReturnPurchaseDetails}" var="pdReturnPurchaseDetail" varStatus="status">
									<c:if test="${!status.first}"><br/></c:if>
									<img src="<c:url value='/images/menu_tree/file12.gif'/>"/>	
									<font color=#888888>[<font color="green">${pdReturnPurchaseDetail.qty}</font>]${pdReturnPurchaseDetail.productNo}/
										<c:forEach items="${compamyProductMap}" var="productEn">
    									<c:if test="${productEn.key eq pdReturnPurchaseDetail.productNo}">${productEn.value}</c:if>
    								</c:forEach>
										
										</font>
									
								</c:forEach>
							</td>
							
						</tr>
				</c:if>
				
</ec:table>	
</form>
<c:if test="${requestParaMap.strAction=='statisticPdReturnPurchase'}">
<ec:table 
	tableId="pdReturnPurchaseTotalTable"
	items="pdReturnPurchaseTotal"
	var="pd"
	action="${pageContext.request.contextPath}/pdReturnPurchases.html"
	width="100%"
	method="post"
	showPagination="false"
	 sortable="false" imagePath="./images/extremetable/*.gif">
				<ec:parameter name="strAction" value="${requestParaMap.strAction}"/>
				<ec:exportCsv fileName="pdReturnPurchaseTotal.csv" />
				<ec:exportXls fileName="pdReturnPurchaseTotal.xls"/>
				<ec:row >
					
					<ec:column property="PRODUCT_NO" title="busi.product.productno" />
    			<ec:column property="PRODUCT_NAME" title="pmProduct.productName" />
    			<ec:column property="QTY" title="pd.qty" calc="total" calcTitle="busi.logistics.total" styleClass="numberColumn"/>

				</ec:row>

</ec:table>	
</c:if>

<script type="text/javascript">

   function editPdReturnPurchase(rpNo,strAction){
   		<jecs:power powerCode="${requestParaMap.strAction}">
					window.location="editPdReturnPurchase.html?strAction="+strAction+"&rpNo="+rpNo;
			</jecs:power>
		}
		
		function printOrder(siNo){
	 		window.open("<c:url value='/pdOrderPrints.html'/>?strAction=printReturnPurchase&orderNo="+siNo);
	 	}
	 	
	 	function editPdReturnPurchaseAdd(){
	 	    window.location="editPdReturnPurchase.html?strAction=addPdReturnPurchase";
	 	}

</script>
