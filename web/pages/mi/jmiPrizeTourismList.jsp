<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jmiPrizeTourismList.title"/></title>
<content tag="heading"><jecs:locale key="jmiPrizeTourismList.heading"/></content>
<meta name="menu" content="JmiPrizeTourismMenu"/>
<%--
<c:set var="buttons">
 
		<jecs:power powerCode="addJmiPrizeTourism">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editJmiPrizeTourism.html"/>?strAction=addJmiPrizeTourism'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power>
		
</c:set>
 --%>
<form action="" method="get" name="miMemberForm" id="miMemberForm">
	<input type="hidden" id="strAction" name="strAction" value=""/>
	<div class="searchBar">
		<div class="new_searchBar">
			<jecs:title key="miMember.memberNo"/>
			<input name="userCode" type="text" value="${param.userCode}" size="10"/>
		</div>
		<div class="new_searchBar">
			<jecs:title key="jmiPrizeTourism.cardname"/>
			<input name="cardname" type="text" value="${param.cardname}" size="10"/>	
		</div>
		<div class="new_searchBar">
			<button type="submit" class="btn btn_sele" name="cancel">
				<jecs:locale key="operation.button.search"/>
			</button>
			<%--<input type="submit" class="button" name="cancel" value="<jecs:locale key="operation.button.search"/>" />--%>
		</div>
		<%-- 
		<div id="titleBar">
		<c:out value="${buttons}" escapeXml="false"/>
		</div>
		--%>
		</div>
</form>

<ec:table 
	tableId="jmiPrizeTourismListTable"
	items="jmiPrizeTourismList"
	var="jmiPrizeTourism"
	action="${pageContext.request.contextPath}/jmiPrizeTourisms.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="images/extremetable/*.gif">
				<ec:row >
    			<ec:column property="userCode" title="miMember.memberNo" />
    			<ec:column property="cardname" title="jmiPrizeTourism.cardname" />
    			<ec:column property="cardid" title="jmiPrizeTourism.cardid" />
    			<ec:column property="phone" title="jmiPrizeTourism.phone" />
    			<ec:column property="acceptanceaddress" title="jmiPrizeTourism.acceptanceaddress" />
    			<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editJmiPrizeTourism('${jmiPrizeTourism.prizeTouismId}','${jmiPrizeTourism.userCode}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editJmiPrizeTourism(prizeTouismId,userCode){
   		<jecs:power powerCode="editJmiPrizeTourism">
					window.location="editJmiPrizeTourism.html?strAction=editJmiPrizeTourism&prizeTouismId="+prizeTouismId+"&userCode="+userCode;
			</jecs:power>
		}

</script>
