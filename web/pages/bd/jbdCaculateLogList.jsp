<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jbdCaculateLogList.title"/></title>
<content tag="heading"><jecs:locale key="jbdCaculateLogList.heading"/></content>
<meta name="menu" content="JbdCaculateLogMenu"/>

<c:set var="buttons">
		<jecs:power powerCode="addJbdCaculateLog">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editJbdCaculateLog.html"/>?strAction=addJbdCaculateLog'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power>
</c:set>

<form action="" method="get" name="miMemberForm" id="miMemberForm">
<div id="titleBar" class="searchBar">
		<div class="new_searchBar">
			<jecs:title key="bdBounsDeduct.wweek"/>
			<input name="wweek" type="text" value="${param.wweek}" size="10"/>
		</div>
		<div class="new_searchBar">
			<jecs:title key="customerRecord.type"/>
			<jecs:list name="currentStep" listCode="bd.caculate.log" value="${param.currentStep}" defaultValue="" />
        </div>
		
	<div class="new_searchBar" style="margin-left:50px;">	
		<button name="search" class="btn btn_sele" type="submit" >
			<jecs:locale key="operation.button.search"/>
		</button>
	</div>
</div>

</form>

<ec:table 
	tableId="jbdCaculateLogListTable"
	items="jbdCaculateLogList"
	var="jbdCaculateLog"
	action="${pageContext.request.contextPath}/jbdCaculateLogs.html"
	width="100%"
	autoIncludeParameters="true"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
				<ec:row >
    			<ec:column property="wweek" title="bdBounsDeduct.wweek" >
    				<jecs:weekFormat week="${jbdCaculateLog.wweek}" weekType="w" />
    			</ec:column>
    			<ec:column property="currentStep" title="customerRecord.type" >
    				
    				<jecs:code listCode="bd.caculate.log" value="${jbdCaculateLog.currentStep }"/>
    			</ec:column>
    			<ec:column property="errmsg" title="icon.information" />
    			<ec:column property="userCode" title="pdProductsMain.editUreNo" />
    			<ec:column property="createTime" title="pd.createTime" />
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editJbdCaculateLog(id){
   		<jecs:power powerCode="editJbdCaculateLog">
					window.location="editJbdCaculateLog.html?strAction=editJbdCaculateLog&id="+id;
			</jecs:power>
		}

</script>
