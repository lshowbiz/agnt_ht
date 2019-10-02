<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jbdManuallyAdjustAlgebraList.title"/></title>
<content tag="heading"><jecs:locale key="jbdManuallyAdjustAlgebraList.heading"/></content>
<meta name="menu" content="JbdManuallyAdjustAlgebraMenu"/>

<form action="" method="get" name="miMemberManageForm1" id="miMemberManageForm1">

<div class="searchBar">

<div class="new_searchBar">	
	<jecs:title key="miMember.memberNo"/>
		<input name="userCode" type="text" value="${param.userCode }" size="8"/>	
</div>

<div class="new_searchBar">	
		<button name="search" type="submit" class="btn btn_sele" >
			<jecs:locale key="operation.button.search"/>
		</button>
		
		<jecs:power powerCode="addJbdManuallyAdjustAlgebra">
			    <button type="button" class="btn btn_ins" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editJbdManuallyAdjustAlgebra.html"/>?strAction=addJbdManuallyAdjustAlgebra'"
			        ><jecs:locale key="button.add"/></button>
		</jecs:power>
</div>

</div>

</form>
<ec:table 
	tableId="jbdManuallyAdjustAlgebraListTable"
	items="jbdManuallyAdjustAlgebraList"
	var="jbdManuallyAdjustAlgebra"
	action="${pageContext.request.contextPath}/jbdManuallyAdjustAlgebras.html"
	width="100%"
	autoIncludeParameters="true"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
				<ec:row >
    			<ec:column property="wweek" title="bdBounsDeduct.wweek" >
    				<jecs:weekFormat week="${jbdManuallyAdjustAlgebra.wweek}" weekType="w" />
    			</ec:column>
    			<ec:column property="userCode" title="miMember.memberNo" />
    			<ec:column property="algebra" title="bdMemberBounsCalcSell.level" >
    				
        <jecs:code listCode="algebra" value="${jbdManuallyAdjustAlgebra.algebra}"/>	
    			</ec:column>
    			<%--<ec:column property="createNo" title="jbdManuallyAdjustAlgebra.createNo" />
    			<ec:column property="createTime" title="jbdManuallyAdjustAlgebra.createTime" />--%>
				<ec:column property="edit" title="title.operation" sortable="false" >
					<jecs:power powerCode="editJbdManuallyAdjustAlgebra">
					<img src="images/newIcons/pencil_16.gif" onclick="window.location.href='editJbdManuallyAdjustAlgebra.html?strAction=editJbdManuallyAdjustAlgebra&id=${jbdManuallyAdjustAlgebra.id}'" alt="<jecs:locale key="button.edit"/>" style="cursor:pointer"/>
					</jecs:power>
				</ec:column>
				</ec:row>

</ec:table>	
