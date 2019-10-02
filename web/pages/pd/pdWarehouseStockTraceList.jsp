<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="pdWarehouseStockTraceList.title"/></title>
<content tag="heading"><jecs:locale key="pdWarehouseStockTraceList.heading"/></content>
<meta name="menu" content="PdWarehouseStockTraceMenu"/>

<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script type='text/javascript' src='<c:url value="/dwr/engine.js"/>'></script>
<script type='text/javascript' src='<c:url value="/dwr/util.js"/>'></script>
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script> 


<form name="frm" id ="frm" action="<c:url value='/pdWarehouseStockTraces.html'/>" >
	<input type="hidden" id="searchFlag" name="searchFlag" value="searchTrace"/>
	<input type="hidden" id="strAction" name="strAction" value="${requestParaMap.strAction}"/>
	<div class="searchBar">
	  <div class="new_searchBar">
		<jecs:title  key="busi.warehouse.warehouseno"/>
		<input type="text" name="warehouseNo" id="warehouseNo" onclick="selectWarehouse('warehouseNo');"  value="${requestParaMap.warehouseNo}" cssClass="text medium"/>	
	  </div>
	  <div class="new_searchBar">	
		<jecs:title  key="busi.product.productno"/>
			<input type="text" name="productNo" id="productNo" value="${requestParaMap.productNo}" cssClass="text medium"/>
	  </div>
	  <div class="new_searchBar">	
		<jecs:title  key="customerRecord.customerNo"/>
		<input type="text" name="customCode" id="customCode" value="${requestParaMap.customCode}" cssClass="text medium"/>
	  </div>
	  <div class="new_searchBar">
	         <jecs:title  key="common.startTime"/>
	         <input name="startTime" id="startTime" type="text" value="${requestParaMap.startTime }" style="cursor:pointer;width:70px;"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
	        - 
	        <input name="endTime" id="endTime" type="text" value="${requestParaMap.endTime }" style="cursor:pointer;width:70px;"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
	  </div>
		<%-- <jecs:title  key="common.startTime"/>
			<input type="text" name="startTime" size='11' id="startTime"  value="${requestParaMap.startTime}" cssClass="text medium"/>
	   <img src="./images/calendar/calendar7.gif" id="img_startOperatorTime" style="cursor: pointer;" title="<jecs:locale key="Calendar.TT.SEL_DATE"/>"/> 
        	<script type="text/javascript"> 
			Calendar.setup({
			inputField     :    "startTime", 
			ifFormat       :    "%Y-%m-%d",  
			button         :    "img_startOperatorTime", 
			singleClick    :    true
			}); 
		</script>
		 
	  -
		<input type="text" name="endTime" size='11'  id="endTime"  value="${requestParaMap.endTime}" cssClass="text medium"/>
	   <img src="./images/calendar/calendar7.gif" id="img_endOperatorTime" style="cursor: pointer;" title="<jecs:locale key="Calendar.TT.SEL_DATE"/>"/> 
        	<script type="text/javascript"> 
			Calendar.setup({
			inputField     :    "endTime", 
			ifFormat       :    "%Y-%m-%d",  
			button         :    "img_endOperatorTime", 
			singleClick    :    true
			}); 
		</script> --%>	
		 <div class="new_searchBar">	
				<jecs:title  key="pd.orderNo"/>
				<input type="text" name="orderNo" id="orderNo" value="${requestParaMap.orderNo}" cssClass="text medium"/>
         </div>
          <div class="new_searchBar" style="white-space:nowrap;">
		<jecs:title key="pdWarehouseStockTrace.orderType"/>
			<jecs:list listCode="pd.stock.actiontype" name="actionType" id="actionType" onchange="changeType();" showBlankLine="true" value="${requestParaMap.actionType}" defaultValue=""/>
		    <button type="button" class="btn btn_sele"  style="width:auto" onclick="submitMe();">
				        <jecs:locale  key='operation.button.search'/>
		     </button>
		</div>	
		 
		 <!--input type="hidden" name="subType" id="subType" value=""/-->
			<jecs:list listCode="owtnolist" name="subType" id="subType"  disabled="true" styleClass="subTypeClass" style="display:none"  showBlankLine="true" value="${requestParaMap.subType}" defaultValue=""/>
	 

			<jecs:list listCode="astnolist" name="subType" id="subType" disabled="true" styleClass="subTypeClass" style="display:none"  showBlankLine="true" value="${requestParaMap.subType}" defaultValue=""/>
		
		
<%-- 		<input type="button" class="button" onclick="submitMe();" value="<jecs:locale  key='operation.button.search'/>"/>
 --%>		
	
	</div>	
<ec:table 
	tableId="pdWarehouseStockTraceListTable"
	items="pdWarehouseStockTraceList"
	var="pdWarehouseStockTrace"
	action="${pageContext.request.contextPath}/pdWarehouseStockTraces.html"
	width="100%"
	form="frm"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
				<ec:row >
				<ec:exportCsv fileName="stocktrace.csv" />
				<ec:exportXls fileName="stocktrace.xls"/>
					
					<ec:column property="pdWarehouse.warehouseNo" title="busi.warehouse.warehouseno" style="white-space:nowrap;"/>
    			<ec:column property="createTime" title="pd.createTime" style="white-space:nowrap;"/>
    				
    			<ec:column property="productNo" title="busi.product.productno"/>
    				<ec:column property="productName" title="pmProduct.productName" sortable="false" >	
    				${compamyProductMap[pdWarehouseStockTrace.productNo]}
    		  </ec:column>
    			<ec:column property="beforeQty" calc="total" calcTitle="report.allTotal" styleClass="numberColumn" title="pdWarehouseStockTrace.beforQty" style="white-space:nowrap;"/>
    			<ec:column property="changeQty" calc="total" calcTitle="report.allTotal" styleClass="numberColumn" title="pdWarehouseStockTrace.changeQty" style="white-space:nowrap;"/>
    			<ec:column property="behindQty" calc="total" calcTitle="report.allTotal" styleClass="numberColumn" title="pdWarehouseStockTrace.behindQty" style="white-space:nowrap;"/>
    			<ec:column property="orderNo" title="pd.orderNo" style="white-space:nowrap;"/>
				</ec:row>

</ec:table>	
</form>

<script type="text/javascript">


function submitMe(){
			waiting();
			
			$('frm').submit();
		}

function changeType(){
	var orderType = $('actionType').value;
	
	var o = document.getElementsByClassName('subTypeClass');

	var S1='<jecs:list listCode="owtnolist" name="subType" id="subType"  disabled="true" styleClass="subTypeClass" style="display:none"  showBlankLine="true" value="${requestParaMap.subType}" defaultValue=""/>';
	if(o != null){
			for(var i=0;i<o.length;i++){
					var subE = o[i];
					Element.remove(subE);
				}
		}
	if(orderType=='2'){
			new Insertion.Before('person', '<jecs:list listCode="owtnolist" name="subType" id="subType"  disabled="true" styleClass="subTypeClass" style="display:none"  showBlankLine="true" value="${requestParaMap.subType}" defaultValue=""/>');
		}else if(orderType=='3'){
			new Insertion.Before('person', '<jecs:list listCode="astnolist" name="subType" id="subType" disabled="true" styleClass="subTypeClass" style="display:none"  showBlankLine="true" value="${requestParaMap.subType}" defaultValue=""/>');
		}
	
	}

   function editPdWarehouseStockTrace(uniNo){
   		<jecs:power powerCode="editPdWarehouseStockTrace">
					window.location="editPdWarehouseStockTrace.html?strAction=editPdWarehouseStockTrace&uniNo="+uniNo;
			</jecs:power>
		}
changeType();
</script>
