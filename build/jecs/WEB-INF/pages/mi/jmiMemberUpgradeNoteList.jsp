<%@ include file="/common/taglibs.jsp"%>


<title><jecs:locale key="miMemberUpgradeNoteList.title"/></title>
<content tag="heading"><jecs:locale key="miMemberUpgradeNoteList.heading"/></content>
<meta name="menu" content="MiMemberUpgradeNoteMenu"/>

<form action="" method="post" name="miMemberUpgradeNoteForm" id="miMemberUpgradeNoteForm">
	<div class="searchBar">
		<div class="new_searchBar"> 
			<jecs:label key="miMember.memberNo" />
			<input name="userCode" type="text" size="10" value="${param.userCode }"/>	
		</div>
		<div class="new_searchBar">
			<button name="search" type="submit" class="btn btn_sele">
				<jecs:locale key="operation.button.search"/>
			</button>
		</div>
	</div>
</form>

<ec:table 
	tableId="jmiMemberUpgradeNoteListTable"
	items="jmiMemberUpgradeNoteList"
	var="miMemberUpgradeNote"
	action="${pageContext.request.contextPath}/jmiMemberUpgradeNotes.html"
	width="100%"
	autoIncludeParameters="true"	
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
				<ec:row>
				
				  <%--<ec:column property="status" title="title.operation" sortable="false" styleClass="centerColumn">
					<img style="cursor:pointer" src="images/newIcons/search_16.gif" onclick="window.location.href='editMiMemberUpgradeNote.html?miId=${miMemberUpgradeNote.miId }&strAction=memberUpgradeView';" alt="<jecs:locale key="function.menu.view"/>"/>
				</ec:column>--%>
				<ec:column property="jmiMember.userCode" title="miMember.memberNo" />
				<%--<ec:column property="miMember.miUserName" title="bdCalculatingSubDetail.name" />--%>
    			<ec:column property="newCard" title="miMemberUpgradeNote.newCard" styleClass="centerColumn">
    				<jecs:code listCode="bd.cardtype" value="${miMemberUpgradeNote.newCard}"/>
    			</ec:column>
    			<ec:column property="oldCard" title="miMemberUpgradeNote.oldCard" styleClass="centerColumn">
    				<jecs:code listCode="bd.cardtype" value="${miMemberUpgradeNote.oldCard}"/>
    			</ec:column>
   
    			<ec:column property="memberOrderNo" title="miMemberUpgradeNote.memberOrderNo" />
    			<ec:column property="updateDate" title="miMemberUpgradeNote.updateDate" >
    				<fmt:formatDate value="${ miMemberUpgradeNote.updateDate}" pattern="yyyy-MM-dd"/>
    			</ec:column>
    			<ec:column property="updateType" title="bdReconsumMoneyReport.typeCH" styleClass="centerColumn">
    				<jecs:code listCode="bd.member.upgradetype" value="${miMemberUpgradeNote.updateType}"/>
    			</ec:column>

				</ec:row>

</ec:table>	

