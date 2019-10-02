<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="pdExchangeOrderList.title"/></title>
<content tag="heading"><jecs:locale key="pdExchangeOrderList.heading"/></content>
<meta name="menu" content="PdExchangeOrderMenu"/>
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script>
<script type='text/javascript' src='<c:url value="/dwr/engine.js"/>'></script>
<script type='text/javascript' src='<c:url value="/dwr/util.js"/>'></script>
<script type='text/javascript' src='<c:url value="/dwr/interface/pdExchangeOrderManager.js"/>'></script>

<c:set var="buttons">
<div class="new_searchBar">
</div>
<div class="new_searchBar">
</div>
    <div class="new_searchBar">
        &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp; 
        <button type="submit" class="btn btn_sele"  style="width:auto" >
			        <jecs:locale  key='operation.button.search'/>
	    </button>
	    
       <c:if test="${param.strAction=='editPdExchangeOrder'}">
		<jecs:power powerCode="addPdExchangeOrder">
				<button type="button" class="btn btn_ins"  style="width:auto" onclick="newExchangeOrder();">
						        <jecs:locale key="button.add"/>
			    </button>
		</jecs:power>
		</c:if>
		<button type="button" class="btn btn_ins"  style="width:auto" 
					onclick="exportXls('${param.orderNo }',
										'${requestParaMap.eoNo}',
										'${requestParaMap.customCode}',
										'${requestParaMap.warehouseNo}',
										'${requestParaMap.createUsrCode}',
										'${requestParaMap.createTimeStart}',
										'${requestParaMap.createTimeEnd}',
										'${requestParaMap.okTimeStart}',
										'${requestParaMap.okTimeEnd}',
										'${requestParaMap.orderFlag}',
										'${requestParaMap.selfReplacement}');" class="button" style="margin-right: 5px"/> 
                             <jecs:locale key="toolbar.text.xls"/>
			    </button>
     </div>
	  <%-- <c:if test="${param.strAction=='editPdExchangeOrder'}">
		<jecs:power powerCode="addPdExchangeOrder">
			
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="newExchangeOrder();"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power>
		</c:if>
		<input type="submit" class="button" value="<jecs:locale  key='operation.button.search'/>"/>
		<input type="button" class="button" name="button" value="<jecs:locale key="toolbar.text.xls"/>"  
					onclick="exportXls('${param.orderNo }',
										'${requestParaMap.eoNo}',
										'${requestParaMap.customCode}',
										'${requestParaMap.warehouseNo}',
										'${requestParaMap.createUsrCode}',
										'${requestParaMap.createTimeStart}',
										'${requestParaMap.createTimeEnd}',
										'${requestParaMap.okTimeStart}',
										'${requestParaMap.okTimeEnd}',
										'${requestParaMap.orderFlag}',
										'${requestParaMap.selfReplacement}');" class="button" style="margin-right: 5px"/> --%>
</c:set>

<form name="frm" action="<c:url value='/pdExchangeOrders.html'/>"  id="frm">
	<input type="hidden" id="strAction" name="strAction" value="${requestParaMap.strAction}"/>
	<div class="searchBar">
	<div class="new_searchBar">
		<jecs:title  key="busi.order.orderno"/>
		<input name="orderNo" id="orderNo" value="${param.orderNo }"  cssClass="text medium"/>
	</div>
	<div class="new_searchBar">	
		<jecs:title  key="pd.orderNo"/>
		<input name="eoNo" id="eoNo" value="<c:out value='${requestParaMap.eoNo}'/>"  cssClass="text medium"/>
	</div>
	<div class="new_searchBar">				
		<jecs:title  key="customerRecord.customerNo"/>
		<input name="customCode" id="customCode" value="<c:out value='${requestParaMap.customCode}'/>"  cssClass="text medium"/>
	</div>				
	<div class="new_searchBar">				
		<jecs:title  key="pdReturnPurchase.returnWarehouseNo"/>
		<input name="warehouseNo" id="warehouseNo" value="<c:out value='${requestParaMap.warehouseNo}'/>" onclick="selectWarehouse('warehouseNo');" cssClass="text medium"/>
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
	        	<input name="okTimeStart" id="okTimeStart" type="text" value="${requestParaMap.okTimeStart }" style="cursor:pointer;width:76px;"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
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
		<jecs:list listCode="pd.orderflag.exchangeorder" name="orderFlag" showBlankLine="true" id="orderFlag" value="${requestParaMap.orderFlag}" defaultValue=""/>
		</div>
		
		<c:out value="${buttons}" escapeXml="false"/>
		
		
	</table>
</div>
<ec:table 
	tableId="pdExchangeOrderListTable"
	items="pdExchangeOrderList"
	var="pdExchangeOrder"
	action="${pageContext.request.contextPath}/pdExchangeOrders.html"
	width="100%"
	form="frm"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
				<ec:row >
				
					
					<c:if test="${sessionScope.sessionLogin.isManager}">
    					<ec:column property="companyCode" title="sys.company.code" style="white-space:nowrap;"/>
    			</c:if>
    			<ec:column property="eoNo" title="pdExchangeOrder.ewNo" style="white-space:nowrap;"/>
    			<ec:column property="customer.userCode" title="customerRecord.customerNo" style="white-space:nowrap;">
						${pdExchangeOrder.customer.userCode}/${pdExchangeOrder.customer.userName}
				</ec:column>

    		  <ec:column property="orderNo" title="prReFund.originateOrderNo" style="white-space:nowrap;"/>
    			<ec:column property="warehouseNo" title="pdReturnPurchase.returnWarehouseNo" style="white-space:nowrap;"/>
    				
    			
    				
    				<ec:column property="createUsrCode" title="pd.createUserNo" style="white-space:nowrap;"/>
    				<ec:column property="createTime" title="pd.createTime" style="white-space:nowrap;">
    				<fmt:formatDate value="${pdEnterWarehouse.createTime}" pattern="yyyy-MM-dd"/>
    			</ec:column > 
    						
	            <ec:column property="orderFlag" title="pd.orderflag" style="white-space:nowrap;">
    				<jecs:code listCode="pd.orderflag.exchangeorder" value="${pdExchangeOrder.orderFlag}"/>
    			</ec:column>
    			
					
    			    
    			   
    			    
    			

    				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html" style="white-space:nowrap;">
    					<jecs:power powerCode="${param.strAction}">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editPdExchangeOrder('${pdExchangeOrder.eoNo}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
										</jecs:power>
						
					</ec:column>
				</ec:row>

</ec:table>	

</form>
	<c:if test="${requestParaMap.strAction=='statisticPdExchangeOrder'}">
		<ec:table 
			tableId="pdExchangeOrderTotalTable"
			items="pdExchangeOrderTotal"
			var="pd"
			action="${pageContext.request.contextPath}/pdExchangeOrders.html"
			width="100%"
			method="post"
			showPagination="false"
			sortable="false" imagePath="./images/extremetable/*.gif">
			<ec:parameter name="strAction" value="${requestParaMap.strAction}"/>
			<ec:exportXls fileName="pdExchangeOrderProducts.xls"></ec:exportXls>
			<ec:row >
			
	   			<ec:column property="FProductNo" title="商品编号"/>
	   			<ec:column property="BQTY" title="退货数量" />
	   			<ec:column property="DQTY" title="换货数量" />
			</ec:row>
		</ec:table>	
	</c:if>


<script type="text/javascript">

function exportXls(orderNo,eoNo,customCode,warehouseNo,createUsrCode,createTimeStart,createTimeEnd,okTimeStart,okTimeEnd,
		orderFlag,selfReplacement){
	
	/*
	var warehouseNo = document.getElementById('warehouseNo');
	
	if(warehouseNo.value==null || warehouseNo.value==''){
		alert('<jecs:locale key="pdReturnPurchase.returnWarehouseNo"/><jecs:locale key="operation.notice.js.bankKey.notNull"/>');
		return false;
	}
	
	
			//$('strAction').value=strAction;
			waiting();
		  var createTimeStart= $('createTimeStart').value;
			var createTimeEnd =$('createTimeEnd').value;
			if(((createTimeStart == null)||(createTimeStart == ''))&&((createTimeEnd == null)||(createTimeEnd ==''))){
				alert('<jecs:locale key="saFiIncomeReport.dataSection"/><jecs:locale key="isNotNull"/>');
				waitingEnd();
				return;
			}
			var okTimeStart= $('okTimeStart').value;
			var okTimeEnd =$('okTimeEnd').value;
			if(((okTimeStart == null)||(okTimeStart == ''))&&((okTimeEnd == null)||(okTimeEnd ==''))){
				alert('<jecs:locale key="saFiIncomeReport.dataSection"/><jecs:locale key="isNotNull"/>');
				waitingEnd();
				return;
			}
			
			waitingEnd();
		*/
			//$('frm').submit();
			window.location.href="<c:url value="/pdExchangeOrders.html"/>?strAction=pdExchangeOrderReport&orderNo="+orderNo
					+"&eoNo="+eoNo+"&customCode="+customCode+"&warehouseNo="+warehouseNo+"&createUsrCode="+createUsrCode
					+"&createTimeStart="+createTimeStart+"&createTimeEnd="+createTimeEnd+"&okTimeStart="+okTimeStart
					+"&okTimeEnd="+okTimeEnd+"&orderFlag="+orderFlag+"&selfReplacement="+selfReplacement;
	}

   function editPdExchangeOrder(eoNo){
   		
					window.location="editPdExchangeOrder.html?strAction=${param.strAction}&eoNo="+eoNo;
		
		}

function newExchangeOrder(){
	waiting();
	location.href='<c:url value="/pdExchangeOrders.html"/>?strAction=newExchangeOrder'
	}

   function cancelExchangeY(eoNo){
	   if(confirm('<jecs:locale key="pd.cancelExchange.yes"/>')){
			pdExchangeOrderManager.cancelExchangeY(eoNo,reResult);
		}
   }

   function cancelExchangeN(eoNo){
	   if(confirm('<jecs:locale key="pd.cancelExchange.no"/>')){
			pdExchangeOrderManager.cancelExchangeN(eoNo,reResult);
		}
   }

   function reResult(rs){
	     if("yzf"==rs){
		     alert('<jecs:locale key="pd.cancelExchange.yzf"/>');
	     }else if("yscfhd"==rs){
		     alert('<jecs:locale key="pd.cancelExchange.yscfhd"/>');
	     }else if("failShiBai"==rs){
		     alert('<jecs:locale key="pd.cancelExchange.fail"/>');
	     }else if("ysh"==rs){
	    	 alert('<jecs:locale key="pd.cancelExchange.ysh"/>');
	     }else{
	    	 window.location.reload();  
	     }
	}
   
</script>
