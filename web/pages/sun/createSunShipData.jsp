<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="sunDistShipList.title"/></title>
<content tag="heading"><jecs:locale key="sunDistShipList.heading"/></content>
<meta name="menu" content="SunDistShipMenu"/>
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 

<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script> 


<form name="sunDistShip" action="<c:url value='/sunDistShips.html'/>" >
	<input type="hidden" id="strAction" name="strAction" value="${requestParaMap.strAction}"/>
	<input type="hidden" id="exeFlag" name="exeFlag" value=""/>
	<div class="searchBar">
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
				
				
			  <input type="button" class="button" onclick="createDate()" value="<jecs:locale  key='operation.button.execute'/>"/>
	</div>
	</form>
<ec:table 
	tableId="sunDistShipListTable"
	items="sunDistShipList"
	var="sunDistShip"
	action="${pageContext.request.contextPath}/sunDistShips.html"
	width="100%"
	retrieveRowsCallback="limit"./images/extremetabletable="true" imagePath="/jecs/images/extremetable/*.gif">
				<ec:row onclick="showTR('tr${ROWCOUNT}');">
					<ec:column property="distCode" title="jfiSunDistribution.agentCode" />
    			<ec:column property="pdSendInfo.siNo" title="pdSendInfo.siNo" />
					<ec:column property="pdSendInfo.orderNo" title="busi.order.orderno" />
						<ec:column property="pdSendInfo.recipientCaNo" title="alStateProvince.stateProvinceName" >
							${alStateProvinceMap[sunDistShip.pdSendInfo.recipientCaNo]}
						</ec:column>
						<ec:column property="pdSendInfo.city" title="busi.city" >
							${alCityMap[sunDistShip.pdSendInfo.city]}
						</ec:column>
						<ec:column property="pdSendInfo.recipientAddr" title="pdSendInfo.recipientAddr" />
						<ec:column property="pdSendInfo.recipientName" title="pdSendInfo.recipientName" />
							<ec:column property="pdSendInfo.orderFlag" title="pdSendInfo.orderFlag" >
    				<jecs:code listCode="pdsendinfo.orderflag" value="${sunDistShip.pdSendInfo.orderFlag}"/>
    			</ec:column>
					<ec:column property="pdSendInfo.createTime"   title="pd.createTime" >
    				<fmt:formatDate value="${sunDistShip.pdSendInfo.createTime}" pattern="yyyy-MM-dd"/>
    			</ec:column>
    			<ec:column property="pdSendInfo.okTime"   title="pd.okTime" >
    				<fmt:formatDate value="${sunDistShip.pdSendInfo.okTime}" pattern="yyyy-MM-dd"/>
    			</ec:column>	
    				<c:if test="${ROWCOUNT>0}">
						<c:if test="${ROWCOUNT%2!=0}"><tr class="odddetail" style="display:none" id="tr${ROWCOUNT}"></c:if>
						<c:if test="${ROWCOUNT%2==0}"><tr class="evendetail" style="display:none" id="tr${ROWCOUNT}"></c:if>
							<td align="right" valign="top">&nbsp;</td>
							<td colspan="6">
								<c:forEach items="${sunDistShip.pdSendInfo.pdSendInfoDetails}" var="pdSendInfoDetail" varStatus="status">
								  <c:if test="${pdSendInfoDetail.qty != 0}">
								  
									<c:if test="${!status.first}"><br/></c:if>
									<img src="<c:url value='/images/menu_tree/file12.gif'/>"/>	
									<font color=#888888>[<font color="green">${pdSendInfoDetail.qty}</font>]${pdSendInfoDetail.productNo}/
										${compamyProductMap[pdSendInfoDetail.productNo]}
									

									
										</font>
										
											
									
									</c:if>

									
								</c:forEach>
							</td>
							
						</tr>
						</c:if>
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editSunDistShip(sdsId){
   		<jecs:power powerCode="editSunDistShip">
					window.location="editSunDistShip.html?strAction=editSunDistShip&sdsId="+sdsId;
			</jecs:power>
		}
		function createDate(){
				
				waiting();
				$('exeFlag').value='1';
				$('sunDistShip').submit();
				
			}

</script>
