<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jbdUserCardListList.title"/></title>
<content tag="heading"><jecs:locale key="jbdUserCardListList.heading"/></content>
<meta name="menu" content="JbdUserCardListMenu"/>

<c:set var="buttons">
		<jecs:power powerCode="addJbdUserCardList">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editJbdUserCardList.html"/>?strAction=addJbdUserCardList'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power>
</c:set>
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>
<ec:table 
	tableId="jbdUserCardListListTable"
	items="jbdUserCardListList"
	var="jbdUserCardList"
	action="${pageContext.request.contextPath}/jbdUserCardLists.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sort./images/extremetableecs/images/extremetable/*.gif">
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editJbdUserCardList('${jbdUserCardList.id}')"
								style="cursor: pointer;" title="<wecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="userCode" title="jbdUserCardList.userCode" />
    			<ec:column property="wweek" title="jbdUserCardList.wweek" />
    			<ec:column property="oldCard" title="jbdUserCardList.oldCard" />
    			<ec:column property="newCard" title="jbdUserCardList.newCard" />
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editJbdUserCardList(id){
   		<jecs:power powerCode="editJbdUserCardList">
					window.location="editJbdUserCardList.html?strAction=editJbdUserCardList&id="+id;
			</jecs:power>
		}

</script>
