<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="pdBatchSendInfoList.title"/></title>
<content tag="heading"><jecs:locale key="pdBatchSendInfoList.heading"/></content>
<meta name="menu" content="pdBatchSendInfoMenu"/>
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 

<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script> 
 



<c:set var="buttons">
<div class="new_searchBar">
    <button type="button" class="btn btn_sele"  style="width:auto" onclick="searchMe()">
				       <jecs:locale  key='operation.button.search'/>
    </button>
    <button type="button" class="btn btn_long"  style="width:auto" onclick="excuteMe()">
				       生成发货单
    </button>
</div>

	<%-- <input type="button" onclick="searchMe()" class="button"  value="<jecs:locale  key='operation.button.search'/>"/>
	<input type="button" onclick="excuteMe()" class="button"  value="生成发货单"/> --%>
</c:set>


	
<form name="pdBatchSendInfoList" id="pdBatchSendInfoList" method="post" action="<c:url value='/pdBatchSendInfos.html'/>" >
	<input type="hidden" id="strAction" name="strAction" value="${requestParaMap.strAction}"/>
	<input type="hidden" id="confirmFlag" name="confirmFlag" value=""/>
	<input type="hidden" id="siNoList" name="siNoList" value="${siNoList}"/>

	<div class="searchBar" style="display: none;">
		<jecs:title  key="pdSendInfo.siNo"/>
		<input name="siNo" id="siNo" value="<c:out value='${requestParaMap.siNo}'/>"  cssClass="text medium"/>
		
		<jecs:title  key="pdSendInfo.orderFlag"/>
		<jecs:list listCode="pdsendinfo.orderflag" name="orderFlag" id="orderFlag" showBlankLine="true" value="${requestParaMap.orderFlag}" defaultValue=""/>
							
		<jecs:title  key="customerRecord.customerNo"/>
		<input type="text" name="customCode" value='${requestParaMap.customCode}' size='11' id="customCode"/>
		<img src="<c:url value="/images/l_1.gif"/>" id="person"  onclick="searchUser()" style="cursor: pointer;" title="<jecs:locale key="operation.button.search"/>"/> 
		<input type="text" name="customName"  size='11' id="customName" readonly="true"/>
		
		<jecs:title key="pdOutWarehouse.warehouseNo"/>
		<input name="warehouseNo" id="warehouseNo" value="<c:out value='${requestParaMap.warehouseNo}'/>"  cssClass="text medium"/>
		<input type="button" class="button" name="select" onclick="selectWarehouse('warehouseNo');" value="<jecs:locale key="button.select"/>" />
	
		<jecs:title  key="busi.order.orderno"/>
		<input name="orderNo" id="orderNo" value="<c:out value='${requestParaMap.orderNo}'/>"  cssClass="text medium"/>
					
		<jecs:title key="alStateProvince.stateProvinceName"/>
		<select name="recipientCaNo">
			<option value=""></option>
			<c:forEach items="${alStateProvinces}" var="alStateProvince">
				<option value="${alStateProvince.stateProvinceCode}" <c:if test="${alStateProvince.stateProvinceCode eq requestParaMap.recipientCaNo}">selected</c:if> >
						${alStateProvince.stateProvinceName}
				</option>
			</c:forEach>
		</select>
						
		<jecs:title key="pd.createTime"/>
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
		- <input name="createTimeEnd" size='11'  id="createTimeEnd"  value="${requestParaMap.createTimeEnd}">
	    <img src="./images/calendar/calendar7.gif" id="img_endTime" style="cursor: pointer;" title="<jecs:locale key="Calendar.TT.SEL_DATE"/>"/> 
		<script type="text/javascript"> 
			Calendar.setup({
			inputField     :    "createTimeEnd", 
			ifFormat       :    "%Y-%m-%d",  
			button         :    "img_endTime", 
			singleClick    :    true
			}); 
		</script>
					
		<jecs:title key="pd.shno"/>
		<jecs:list listCode="pd.shno" name="shNo" showBlankLine="true" id="shNo" value="${requestParaMap.shNo}" defaultValue=""/>	
	</div>
		<c:out value="${buttons}" escapeXml="false"/>

	<c:if test="${not empty error}">
		<div class="error" id="errorMessages">
			<c:forEach var="e" items="${error}">
				<img src="<c:url value="images/newIcons/warning_16.gif"/>"
					alt="<jecs:locale key="icon.warning"/>" class="icon" />
				<c:out value="${e}" escapeXml="false" />
				<br />
			</c:forEach>
		</div>
		<c:remove var="errors" />
	</c:if>

	<ec:table 
	tableId="pdBatchSendInfoListTable"
	items="pdBatchSendInfoList"
	var="jpoMemberOrder"
	action="${pageContext.request.contextPath}/pdBatchSendInfos.html"
	width="100%" 
	method="pos./images/extremetableo"
	showPagination="false"
	rowsDisplayed="20" 
	sortable="false" imagePath="./images/extremetable/*.gif">
	
		<ec:row onclick="showTR($('tr${ROWCOUNT}'));">
			<ec:column property="edit" title="<input type='checkbox' checked='true' name='selectedAll' id='selectedAll' class='checkbox' onclick='switchAll();'/>" sortable="false" styleClass="centerColumn" viewsAllowed="html"	>
					<input type="checkbox" name="selectedId" id="selectedId" value="${jpoMemberOrder['mo_id']}" checked="true" class="checkbox"/>
			</ec:column>
	    	<ec:column property="member_order_no" title="poMemberOrder.memberOrderNo" />
	    	<ec:column property="order_type" title="poMemberOrder.orderType" styleClass="centerColumn">
	    		<jecs:code listCode="po.all.order_type" value="${jpoMemberOrder['order_type']}"/>
	    	</ec:column>
	    	<ec:column property="user_code" title="miMember.memberNo" />
	    	<ec:column property="amount" title="busi.order.amount" styleClass="numberColumn" />
	    	<ec:column property="pv_amt" title="poMemberOrder.pvAmt" styleClass="numberColumn" />
	    	<ec:column property="pay_mode" title="poMemberOrder.payMode" styleClass="centerColumn">
	    		<jecs:code listCode="po.paymode" value="${jpoMemberOrder['pay_mode']}"/>
	    	</ec:column>
	    	<ec:column property="status" title="pd.checkstatus" styleClass="centerColumn">
	    		<jecs:code listCode="po.status" value="${jpoMemberOrder['status']}"/>
	    	</ec:column>
			<ec:column property="editA" title="title.operation" sortable="false" style="width:100px;" viewsAllowed="html">
	    		<jecs:power powerCode="viewPoMemberOrder">
					<a href="jpoMemberOrderView.html?strAction=viewPoMemberOrder&moId=${jpoMemberOrder['mo_id']}"><img border="0" src="images/newIcons/search_16.gif" alt="<jecs:locale key="function.menu.view"/>" /></a>
	    		</jecs:power>
			</ec:column>
		</ec:row>
</ec:table>	
</form>

<script type="text/javascript">

function excuteMe(){
		checkTable();
		$('confirmFlag').value='batchConfirm';
		if($('siNoList').value.length <1){
				//alert("Please select the orders to be confirmed!");
				alert("请选择订单！");
				return;
			}
		//if(confirm('Do you want to confirm these orders now?')){
		if(confirm('是否生成发货单？')){
				waiting();
				$('pdBatchSendInfoList').submit();
				
			}
		
	
	}
	function searchMe(){
		waiting();
		$('confirmFlag').value='search';
		$('pdBatchSendInfoList').submit();
		
	}
	 function checkTable()
 {  
    var elements = document.getElementsByName('selectedId');
    var s='';
	var money=0;
	for(var i=0;i<elements.length;i++){
		if(elements[i].checked){
			if(i==0){
				  s = elements[i].value;
				}else{
					s += ','+elements[i].value;
					}
			
		}
		
	}
	
	$('siNoList').value=s;
		//alert(s);
  }
  
	 function addPdSendInfo(){
	 		window.location="<c:url value='pdBatchSendInfos.html'/>?strAction=addpdBatchSendInfo";
	 	}
	 	function printPdSendInfo(siNo){
	 		window.open("<c:url value='/pdOrderPrints.html'/>?strAction=printSendInfo&orderNo="+siNo);
	 	}
   function editPdSendInfo(siNo){
   				
   			//	form1.siNo.value=siNo;
   			//	form1.submit();
   			<jecs:power powerCode="${requestParaMap.strAction}">
   			
				window.location="editpdBatchSendInfo.html?siNo="+siNo+"&strAction=<c:out value='${requestParaMap.strAction}'/>";
				
			</jecs:power>
		}


function switchAll() {
			var selectedAll=document.getElementsByName("selectedAll");
 			var selectedId=document.getElementsByName("selectedId");
			if(selectedId!=undefined){

				for(var i=0;i<selectedId.length;i++){
					selectedId[i].checked=selectedAll[0].checked;
				}
			}
			
		}
function searchUser(){
    			var userCodeExample = $('agentNo').value;
    			//open("<c:url value='/sysUserSelect.html'/>?selectControl=agentAndMember&userCode="+userCodeExample);
    			var ret = window.showModalDialog("<c:url value='/sysUserSelect.html'/>?strAction=companyUserSelect&selectControl=agentAndMember&userCode="+userCodeExample);
    			
    			$('agentNo').value=ret['userCode'];
    			$('agentName').value=ret['userName'];
    			
    		}
    		
			function viewUPS(trackingNo){
			var url="http://wwwapps.ups.com/WebTracking/track?HTMLVersion=5.0&loc=en_US&Requester=UPSHome&track.x=Track&trackNums="+trackingNo;
			window.open(url);
		}

    		function importFile(){
					window.open("<c:url value='/pdFileUpload.html?strAction=updateTrackingNo'/>");
			}
</script>
