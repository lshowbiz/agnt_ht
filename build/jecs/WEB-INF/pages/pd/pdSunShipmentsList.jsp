<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="pdSendInfoList.title"/></title>
<content tag="heading"><jecs:locale key="pdSendInfoList.heading"/></content>
<meta name="menu" content="PdSendInfoMenu"/>
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 

<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script> 







	
<form name="pdSendInfoList" action="<c:url value='/pdSunShipments.html'/>" >
	<input type="hidden" id="strAction" name="strAction" value="${requestParaMap.strAction}"/>
	
	<div class="searchBar">
			
			<jecs:title  key="busi.order.orderno"/>
			<input name="orderNo" id="orderNo" value="<c:out value='${requestParaMap.orderNo}'/>"  cssClass="text medium"/>
						
			<jecs:title  key="pdSendInfo.siNo"/>
			<input name="siNo" id="siNo" value="<c:out value='${requestParaMap.siNo}'/>"  cssClass="text medium"/>
				
			
				
					
						
				<jecs:title key="jfiSunDistribution.agentCode"/>
		
						<input type="text" name="agentNo" value='${requestParaMap.agentNo}' size='11' id="agentNo"/>
				
				
				<jecs:title key="pdOutWarehouse.warehouseNo"/>
					
						<input name="warehouseNo" id="warehouseNo" value="<c:out value='${requestParaMap.warehouseNo}'/>"  cssClass="text medium"/>
						<input type="button" class="button" name="select" onclick="selectWarehouse('warehouseNo');" value="<jecs:locale key="button.select"/>" />
					
					
				
				
				
				<jecs:title key="alStateProvince.stateProvinceName"/>
			
				<select name="recipientCaNo">
					<option value=""></option>
					<c:forEach items="${alStateProvinces}" var="alStateProvince">
						<option value="${alStateProvince.stateProvinceId}" <c:if test="${alStateProvince.stateProvinceId eq requestParaMap.recipientCaNo}">selected</c:if> >
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
				
				<jecs:title key="pd.okTime"/>
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
				</script>
				
					
					<jecs:title key="busi.common.trackingno"/>
						<input name="trackingNo" id="trackingNo" value="<c:out value='${requestParaMap.trackingNo}'/>"  cssClass="text medium"/>
							
				<input type="submit" class="button" value="<jecs:locale  key='operation.button.search'/>"/>
				
		
</div>
<ec:table 
	tableId="pdSendInfoListTable"
	items="pdSendInfoList"
	var="pdSendInfo"
	action="${pageContext.request.contextPath}/pdSunShipments.html"
	width="100%" 
./images/extremetable"pdSendInfoList"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="/jecs/images/extremetable/*.gif">
				
				
					<!--ec:exportCsv fileName="pdSendInfos.csv" /-->
					<!--ec:exportXls fileName="pdSendInfos.xls"/-->
				<ec:row>
					
				
					
					
					
					
					
    			<ec:column property="agent_no" title="customerRecord.customerNo" />
    			<ec:column property="agent_name" title="jfiSunMemberOrder.agentName" />
    			<ec:column property="si_no" title="pdSendInfo.siNo" />
					<ec:column property="order_no" title="busi.order.orderno" />
    			
    			<ec:column property="create_time"   title="pd.createTime" />
    				
    			
    			
    			<ec:column property="ok_time"   title="pd.okTime" />
    			
    				
    		
    			
    			<ec:column property="warehouse_no" title="pdSendInfo.sendWarehouseNo" />
    			
    			<ec:column property="sh_no" title="pd.shno" >
    				<jecs:code listCode="pd.shno" value="${pdSendInfo.sh_no}"/>
    			</ec:column>
    			
    		
    			<ec:column property="order_flag" title="pdSendInfo.orderFlag" >
    				<jecs:code listCode="pdsendinfo.orderflag" value="${pdSendInfo.order_flag}"/>
    			</ec:column>
    			
				
				<ec:column property="tracking_no" title="busi.common.trackingno" />
					
		   <ec:column property="recipient_ca_no" title="pdSendInfo.recipientAddr" >
				${alStateProvinceMap[pdSendInfo.recipient_ca_no]}/${alCityMap[pdSendInfo.city]}/${pdSendInfo.recipient_addr}/${pdSendInfo.recipient_name}
			</ec:column>	
				
					
			</ec:row >
				
</ec:table>	
</form>





<!--form name="form1" action="<c:url value='editPdSendInfo.html'/>">
			<input type="hidden" name="strAction" id="strAction" value='${requestParaMap.strAction}'/>
			<input type="hidden" name='siNo' id='siNo'/>
</form-->

<script type="text/javascript">

	 

function searchUser(){
    			var userCodeExample = $('customCode').value;
    			//open("<c:url value='/sysUserSelect.html'/>?selectControl=agentAndMember&userCode="+userCodeExample);
    			var ret = window.showModalDialog("<c:url value='/sysUserSelect.html'/>?strAction=companyUserSelect&selectControl=agentAndMember&userCode="+userCodeExample);
    			
    			$('agentNo').value=ret['agentNo'];
    			$('customCodeName').value=ret['userName'];
    			
    		}
    		
    		
   function viewTracking(trackingNo,shNo){
   	        var b2='';
   				if(trackingNo.length >1){
   					b2=trackingNo.substring(0,1);
   				}
   	       
		    
   	        if(b2=='E'){
   	        	  viewEMS(trackingNo);
   	        	}else if(shNo=='ZJS'){
    							viewZJS(trackingNo);
    					}else if(shNo=='DTW'){
    						  viewDT(trackingNo);
    						}
   }
  function viewEMS(trackingNo){
   		var url="http://www.ems.com.cn/";
			window.open(url);
   	}
   function viewZJS(trackingNo){
   		
		var url="http://www.zjs.com.cn";
			window.open(url);
   	}
   	
   	function viewDT(trackingNo){
   		var url="http://www.dtw.com.cn/";
			window.open(url);
   		}
			function viewUPS(trackingNo){
		
			var url="http://wwwapps.ups.com/WebTracking/track?HTMLVersion=5.0&loc=en_US&Requester=UPSHome&track.x=Track&trackNums="+trackingNo;
			window.open(url);
		}
	
	function newFuction2(){
			disableButton();
			window.open("<c:url value='/pdSendInfos.html?strAction=newFuction2'/>")
			
		}
    		function importFile(){
					window.open("<c:url value='/pdFileUpload.html?strAction=updateTrackingNo'/>");
			}
</script>
