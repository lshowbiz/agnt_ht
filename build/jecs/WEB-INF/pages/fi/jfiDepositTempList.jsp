<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jfiDepositTempList.title"/></title>
<content tag="heading"><jecs:locale key="jfiDepositTempList.heading"/></content>
<meta name="menu" content="JfiDepositTempMenu"/>

<c:set var="buttons">
		<jecs:power powerCode="addJfiDepositTemp">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editJfiDepositTemp.html"/>?strAction=addJfiDepositTemp'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power>
</c:set>
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>
<ec:table 
	tableId="jfiDepositTempListTable"
	items="jfiDepositTempList"
	var="jfiDepositTemp"
	action="${pageContext.request.contextPath}/jfiDepositTemps.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="/jecs/images/extremetable/*.gif">
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editJfiDepositTemp('${jfiDepositTemp.tempId}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="companyCode" title="jfiDepositTemp.companyCode" />
    			<ec:column property="userCode" title="jfiDepositTemp.userCode" />
    			<ec:column property="dealType" title="jfiDepositTemp.dealType" />
    			<ec:column property="moneyType" title="jfiDepositTemp.moneyType" />
    			<ec:column property="money" title="jfiDepositTemp.money" />
    			<ec:column property="notes" title="jfiDepositTemp.notes" />
    			<ec:column property="createrNo" title="jfiDepositTemp.createrNo" />
    			<ec:column property="createTime" title="jfiDepositTemp.createTime" />
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editJfiDepositTemp(tempId){
   		<jecs:power powerCode="editJfiDepositTemp">
					window.location="editJfiDepositTemp.html?strAction=editJfiDepositTemp&tempId="+tempId;
			</jecs:power>
		}

</script>
