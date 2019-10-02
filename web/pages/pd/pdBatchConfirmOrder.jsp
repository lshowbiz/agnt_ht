<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="pdSendInfoList.title"/></title>
<content tag="heading"><jecs:locale key="pdSendInfoList.heading"/></content>
<meta name="menu" content="PdSendInfoMenu"/>
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 

<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script> 
 



<c:set var="buttons">
	<!-- <input type="button" onclick="searchMe()" class="button"  value="<jecs:locale  key='operation.button.search'/>"/>
	 <input type="button" onclick="excuteMe('batchConfirm')" class="button"  value="<jecs:locale  key='operation.button.execute'/>"/>
	 <input type="button" onclick="excuteMe('batchHurry')" class="button"  value="<jecs:locale  key='operation.hurry.execute'/>"/>
	-->
	<jecs:power powerCode="updateTrackingNo">
		<div class="new_searchBar">
			<button type="button" class="btn btn_long"  style="width:auto"  onclick="importFile();">
					    <jecs:locale  key='operation.button.uploadtrackingno'/>
			</button>
		</div>
		</jecs:power>
		<!--  
	    <jecs:power powerCode="updateTrackingNo">	
  		     <input type="button" class="button" onclick="importFile();" value="<jecs:locale  key='operation.button.uploadtrackingno'/>"/>
		</jecs:power>
		-->
</c:set>


	
<form name="pdSendInfoList" id="pdSendInfoList" method="post" action="<c:url value='/pdSendInfos.html'/>" >
	<input type="hidden" id="strAction" name="strAction" value="${requestParaMap.strAction}"/>
<input type="hidden" id="confirmFlag" name="confirmFlag" value=""/>
<input type="hidden" id="siNoList" name="siNoList" value="${siNoList}"/>
<div class="searchBar">
	
		<div class="new_searchBar">
			<jecs:label  key="pdSendInfo.siNo"/>
			<input name="siNo" id="siNo" value="<c:out value='${requestParaMap.siNo}'/>"  cssClass="text medium"/>
		</div>		
		<div class="new_searchBar">	
			<jecs:label  key="pdSendInfo.orderFlag"/>
				<jecs:list listCode="pdsendinfo.orderflag" name="orderFlag" id="orderFlag" showBlankLine="true" value="${requestParaMap.orderFlag}" defaultValue=""/>
		</div>			
		<div class="new_searchBar">				
				<jecs:label  key="customerRecord.customerNo"/>
				
						<input type="text" name="customCode" value='${requestParaMap.customCode}' size='11' id="customCode"/>
						<img src="<c:url value="/images/l_1.gif"/>" id="person"  onclick="searchUser();" style="cursor: pointer;" title="<jecs:locale key="operation.button.search"/>"/> 
						<!--  <input type="text" name="customName"  size='11' id="customName" readonly="true"/>-->
		</div>				
		<div class="new_searchBar" style="white-space:nowrap;">				
		                <jecs:label key="pdOutWarehouse.warehouseNo"/>
						<input name="warehouseNo" id="warehouseNo" value="<c:out value='${requestParaMap.warehouseNo}'/>"  cssClass="text medium"/>
		                <button name="select" class="btn btn_sele" onclick="selectWarehouse('warehouseNo');" >
				                   <jecs:locale key="button.select"/>
			            </button>
		</div>		
		<!--  		
				<jecs:label key="pdOutWarehouse.warehouseNo"/>
					
						<input name="warehouseNo" id="warehouseNo" value="<c:out value='${requestParaMap.warehouseNo}'/>"  cssClass="text medium"/>
						<input type="button" class="button" name="select" onclick="selectWarehouse('warehouseNo');" value="<jecs:locale key="button.select"/>" />
		-->			
					
							
			
				
				
				
				
				
		
				
					
			<div class="new_searchBar">			
				<jecs:label  key="busi.order.orderno"/>
				<input name="orderNo" id="orderNo" value="<c:out value='${requestParaMap.orderNo}'/>"  cssClass="text medium"/>
			</div>	
			<div class="new_searchBar">		
				
				<jecs:label key="alStateProvince.stateProvinceName"/>
				
				<select name="recipientCaNo">
					<option value=""></option>
					<c:forEach items="${alStateProvinces}" var="alStateProvince">
						<option value="${alStateProvince.stateProvinceCode}" <c:if test="${alStateProvince.stateProvinceCode eq requestParaMap.recipientCaNo}">selected</c:if> >
								${alStateProvince.stateProvinceName}
						</option>
					</c:forEach>
				</select>
			</div>		
			<div class="new_searchBar">	
			       <jecs:label key="pd.createTime"/>
			        <input name="createTimeStart" id="createTimeStart" type="text" value="${param.createTimeStart }" style="cursor: pointer;width:76px;"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
	            - 
	            <input name="createTimeEnd" id="createTimeEnd" type="text" value="${param.createTimeEnd }" style="cursor:pointer;width:76px;"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
			</div>
			<!-- 	
				<jecs:label key="pd.createTime"/>
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
		 -->	
		 <div class="new_searchBar">	
				<jecs:label key="pd.shno"/>
				<jecs:list listCode="pd.shno" name="shNo" showBlankLine="true" id="shNo" value="${requestParaMap.shNo}" defaultValue=""/>	
		</div>			
					
		
				
		
	
	
	<c:out value="${buttons}" escapeXml="false"/>
</div>

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
	tableId="pdSendInfoListTable"
	items="pdSendInfoList"
	var="pdSendInfo"
	action="${pageContext.request.contextPath}/pdSendInfos.html" 
	width="100%" 
	method="post"
	
	form="pdSendInfoList"
	showPagination="false"
	 sortable="false" imagePath="/jecs/images/extremetable/*.gif">
				
				
					<!--ec:exportCsv fileName="pdSendInfos.csv" /-->
					<!--ec:exportXls fileName="pdSendInfos.xls"/-->
				<ec:row onclick="showTR('tr${ROWCOUNT}');">
					
					<ec:column property="edit" title="<input type='checkbox' checked='true' name='selectedAll' id='selectedAll' class='checkbox' onclick='switchAll();'/>" sortable="false" styleClass="centerColumn" viewsAllowed="html"	>
							<input type="checkbox" name="selectedId" id="selectedId" value="${pdSendInfo.siNo}" checked="true" class="checkbox"/>
					</ec:column>
					
					
					 
					
					<ec:column property="siNo" title="pdSendInfo.siNo" />
					<ec:column property="orderNo" title="busi.order.orderno" />	
    			<ec:column property="customer.userCode" title="customerRecord.customerNo" >
    				${pdSendInfo.customer.userCode}/${pdSendInfo.customer.userName}
    			</ec:column>
    			
    			<ec:column property="createUsrCode" title="pd.createUserNo" />	
    			<ec:column property="createTime"   title="pd.createTime" >
    				<fmt:formatDate value="${pdSendInfo.createTime}" pattern="yyyy-MM-dd"/>
    			</ec:column>
    			
    			
    				
    		 
    			
    			<ec:column property="warehouseNo" title="pdSendInfo.sendWarehouseNo" />
    			
    			<ec:column property="shNo" title="pd.shno" >
    				<jecs:code listCode="pd.shno" value="${pdSendInfo.shNo}"/>
    			</ec:column>
    			
    			 
    		 
    		
    			<ec:column property="orderFlag" title="pdSendInfo.orderFlag" >
    				<jecs:code listCode="pdsendinfo.orderflag" value="${pdSendInfo.orderFlag}"/>
    			</ec:column>
    			
				</ec:row>
				
				
				<c:if test="${ROWCOUNT>0}">
						<c:if test="${ROWCOUNT%2!=0}"><tr class="odddetail"  style="display:none" id="tr${ROWCOUNT}"></c:if>
						<c:if test="${ROWCOUNT%2==0}"><tr class="evendetail" style="display:none"  id="tr${ROWCOUNT}"></c:if>
							<c:if test="${sessionScope.sessionLogin.isManager}">
								<td  colspan="2" align="right" valign="top">&nbsp;
							</c:if>
							<c:if test="${!sessionScope.sessionLogin.isManager}">
								<td   align="right" valign="top">&nbsp;
							</c:if>
							
							<c:if test="${requestParaMap.strAction !='acceptPdSendInfo'}">
								<td colspan="8">
							</c:if>
							<c:if test="${requestParaMap.strAction =='acceptPdSendInfo'}">
								<td colspan="5">
							</c:if>
							
								${pdSendInfo.recipientAddr}/${pdSendInfo.recipientName}<br/>
								<c:forEach items="${pdSendInfo.pdSendInfoDetails}" var="pdSendInfoDetail" varStatus="status">
								  <c:if test="${pdSendInfoDetail.qty != 0}">
								  
									<c:if test="${!status.first}"><br/></c:if>
										
									<font color=#888888>[<font color="green">${pdSendInfoDetail.qty}</font>]${pdSendInfoDetail.productNo}/
										<c:forEach items="${compamyProductMap}" var="productEn">
    									<c:if test="${productEn.key eq pdSendInfoDetail.productNo}">${productEn.value}</c:if>

										
    								</c:forEach>

									
										</font>
										
										
									
									</c:if>

									
								</c:forEach>
							
							
						
				</c:if>
				
				
				
</ec:table>	





</form>




<script type="text/javascript">

function excuteMe(strAction){
		checkTable();
		$('confirmFlag').value=strAction;
		if($('siNoList').value.length <1){
				alert("Please select the orders to be confirmed!");
				return;
			}
		if(confirm('Do you want to confirm these orders now?')){
				waiting();
				$('pdSendInfoList').submit();
				
			}
		
	
	}
	function searchMe(){
		waiting();
		$('confirmFlag').value='search';
		$('pdSendInfoList').submit();
		
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
	 		window.location="<c:url value='pdSendInfos.html'/>?strAction=addPdSendInfo";
	 	}
	 	function printPdSendInfo(siNo){
	 		window.open("<c:url value='/pdOrderPrints.html'/>?strAction=printSendInfo&orderNo="+siNo);
	 	}
   function editPdSendInfo(siNo){
   				
   			//	form1.siNo.value=siNo;
   			//	form1.submit();
   			<jecs:power powerCode="${requestParaMap.strAction}">
   			
				window.location="editPdSendInfo.html?siNo="+siNo+"&strAction=<c:out value='${requestParaMap.strAction}'/>";
				
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
    			var userCodeExample = $('customCode').value;
    			//open("<c:url value='/sysUserSelect.html'/>?selectControl=agentAndMember&userCode="+userCodeExample);
    			var ret = window.showModalDialog("<c:url value='/sysUserSelect.html'/>?strAction=companyUserSelect&selectControl=agentAndMember&userCode="+userCodeExample,null,"dialogHeight:400px;dialogWidth:1000px;");
    			
    			$('customCode').value=ret['userCode'];
    			//$('agentName').value=ret['userName'];
    			
    		}
    		
			function viewUPS(trackingNo){
			var url="http://wwwapps.ups.com/WebTracking/track?HTMLVersion=5.0&loc=en_US&Requester=UPSHome&track.x=Track&trackNums="+trackingNo;
			window.open(url);
		}

    		function importFile(){
					window.open("<c:url value='/pdFileUpload.html?strAction=updateTrackingNo'/>");
			}
</script>
