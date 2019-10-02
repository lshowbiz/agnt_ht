<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jalTownList.title"/></title>
<content tag="heading"><jecs:locale key="jalTownList.heading"/></content>
<meta name="menu" content="JalTownMenu"/>

<c:set var="buttons">
		<jecs:power powerCode="addJalTown">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editJalTown.html"/>?strAction=addJalTown'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power>
</c:set>
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>
<ec:table 
	tableId="jalTownListTable"
	items="jalTownList"
	var="jalTown"
	action="${pageContext.request.contextPath}/jalTowns.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sort./images/extremetableecs/images/extremetable/*.gif">
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editJalTown('${jalTown.townId}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="townCode" title="jalTown.townCode" />
    			<ec:column property="townName" title="jalTown.townName" />
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editJalTown(townId){
   		<jecs:power powerCode="editJalTown">
					window.location="editJalTown.html?strAction=editJalTown&townId="+townId;
			</jecs:power>
		}

</script>
