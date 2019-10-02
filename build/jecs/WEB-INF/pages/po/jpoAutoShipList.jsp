<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jpoAutoShipList.title"/></title>
<content tag="heading"><jecs:locale key="jpoAutoShipList.heading"/></content>
<meta name="menu" content="JpoAutoShipMenu"/>

<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script> 

<form action="jpoAutoShips.html" method="get" name="searchForm" id="searchForm">
<div class="searchBar">

<c:if test="${sessionScope.sessionLogin.userType=='C'}">
            <jecs:title key="miMember.memberNo"/>
			<input name="sysUser.userCode" type="text" size="10" value="${param['sysUser.userCode'] }" size="12"/>
</c:if>

            <jecs:locale key="poMemberOrder.Times"/><jecs:title key="label.dateselect.ex"/>
			<input id="startOrderTime" name="startOrderTime" type="text" size="10" maxlength="10" value="${param.startOrderTime }"/>
			<img src="./images/calendar/calendar7.gif" id="img_startOperatorTime" style="cursor: pointer;" title="<jecs:locale key="Calendar.TT.SEL_DATE"/>"/> 
			<script type="text/javascript"> 
				Calendar.setup({
				inputField     :    "startOrderTime", 
				ifFormat       :    "%Y-%m-%d",  
				button         :    "img_startOperatorTime", 
				singleClick    :    true
				}); 
			</script> - 
			<input id="endOrderTime" name="endOrderTime" type="text" size="10" maxlength="10" value="${param.endOrderTime }"/>
			<img src="./images/calendar/calendar7.gif" id="img_endOperatorTime" style="cursor: pointer;" title="<jecs:locale key="Calendar.TT.SEL_DATE"/>"/> 
			<script type="text/javascript"> 
				Calendar.setup({
				inputField     :    "endOrderTime", 
				ifFormat       :    "%Y-%m-%d",  
				button         :    "img_endOperatorTime", 
				singleClick    :    true
				}); 
			</script>
			
			<input name="search" class="button" type="submit" value="<jecs:locale key="operation.button.search"/>" />
		<jecs:power powerCode="addJpoAutoShip">
			<c:if test="${autoship=='0' }">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editJpoAutoShip.html"/>?strAction=addJpoAutoShip'"
			        value="<jecs:locale key="button.add"/>"/>
			</c:if>
		</jecs:power>
</div>
</form>
<ec:table 
	tableId="jpoAutoShipListTable"
	items="jpoAutoShipList"
	var="jpoAutoShip"
	action="${pageContext.request.contextPath}/jpoAutoShips.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
				<ec:row >
    			<ec:column property="companyCode" title="jpoAutoShip.companyCode" />
    			<ec:column property="userCode" title="jpoAutoShip.userCode" />
    			<ec:column property="jasActionTime" title="jpoAutoShip.jasActionTime" />
    			<ec:column property="status" title="jpoAutoShip.status" />
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
					<jecs:power powerCode="addJpoAutoShip">
						<c:if test="${autoship=='0' }">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editJpoAutoShip('${jpoAutoShip.jasId}')"
								style="cursor: pointer;" title="<wecs:locale key="button.edit"/>"/>
						</c:if>
					</jecs:power>
				</ec:column>
				</ec:row>
</ec:table>	

<script type="text/javascript">

   function editJpoAutoShip(jasId){
   		<jecs:power powerCode="editJpoAutoShip">
					window.location="editJpoAutoShip.html?strAction=editJpoAutoShip&jasId="+jasId;
			</jecs:power>
		}

</script>
