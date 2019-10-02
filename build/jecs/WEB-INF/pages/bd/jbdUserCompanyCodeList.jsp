<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jbdUserCompanyCodeList.title"/></title>
<content tag="heading"><jecs:locale key="jbdUserCompanyCodeList.heading"/></content>
<meta name="menu" content="JbdUserCompanyCodeMenu"/>

<form action="" method="get" name="miMemberForm" id="miMemberForm">
<div id="titleBar" class="searchBar">
	<div class="new_searchBar">
		<jecs:title key="miMember.memberNo"/>
		<input name="userCode" type="text" value="${param.userCode}" size="10"/>
	</div>	
	<div class="new_searchBar">
		<button type="submit" class="btn btn_sele" name="cancel">
			<jecs:locale key="operation.button.search"/>
		</button>
		<jecs:power powerCode="addJbdUserCompanyCode">
			<button type="button" class="btn btn_ins"
				onclick="location.href='<c:url value="/editJbdUserCompanyCode.html"/>?strAction=addJbdUserCompanyCode'">
				<jecs:locale key="button.add"/>
			</button>
		</jecs:power>
	</div>
</div>
</form>

<ec:table 
	tableId="jbdUserCompanyCodeListTable"
	items="jbdUserCompanyCodeList"
	var="jbdUserCompanyCode"
	action="${pageContext.request.contextPath}/jbdUserCompanyCodes.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
				<ec:row >
    			<ec:column property="userCode" title="miMember.memberNo" />
    			<ec:column property="wweek" title="bdBounsDeduct.wweek" >
    				<jecs:weekFormat week="${jbdUserCompanyCode.wweek }" weekType="w" />
    			</ec:column>
    			<ec:column property="oldCompany" title="jbdUserCompanyCode.oldCompany" >
		<jecs:company name="oldCompany" selected="${jbdUserCompanyCode.oldCompany }"  prompt="" withAA="false" label="true" />	
    			
    			</ec:column>
    			<ec:column property="newCompany" title="jbdUserCompanyCode.newCompany" >
		<jecs:company name="newCompany" selected="${jbdUserCompanyCode.newCompany }"  prompt="" withAA="false" label="true" />	
    			
    			</ec:column>
    			
				<%--<ec:column property="1" title="title.operation" sortable="false" width="100" >
<img src="images/newIcons/pencil_16.gif" onclick="window.location.href='editJbdUserCompanyCode.html?id=${jbdUserCompanyCode.id}&strAction=editJbdUserCompanyCode';" alt="<jecs:locale key="button.edit"/>" style="cursor:pointer"/>
						
				</ec:column>--%>
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editJbdUserCompanyCode(id){
   		<jecs:power powerCode="editJbdUserCompanyCode">
					window.location="editJbdUserCompanyCode.html?strAction=editJbdUserCompanyCode&id="+id;
			</jecs:power>
		}

</script>
