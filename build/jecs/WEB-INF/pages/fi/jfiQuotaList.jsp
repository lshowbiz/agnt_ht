<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jfiQuotaList.title" />
</title>
<content tag="heading">
<jecs:locale key="jfiQuotaList.heading" />
</content>
<meta name="menu" content="JfiQuotaMenu" />


<form name="frm" id="frm" action="<c:url value='/jfiQuotas.html'/>">
	<c:set var="buttons">
		<!--<jecs:power powerCode="addJfiQuota">
				    <input type="button" class="button" style="margin-right: 5px"
				        onclick="location.href='<c:url value="/editJfiQuota.html"/>?strAction=addJfiQuota'"
				        value="<jecs:locale key="button.add"/>"/>
			</jecs:power>-->
	</c:set>
	<div id="titleBar">
		<div class="new_searchBar">
			<jecs:title key="bdBounsDeduct.wweek" />
			<input name="validityPeriod" id="validityPeriod"
				value="<c:out value='${param.validityPeriod}'/>"
				cssClass="text medium" />
		</div>
		<div class="new_searchBar">
			<jecs:title key="fiBillAccount.AccountCode" />
			<input name="billAccountCode" id="billAccountCode"
				value="<c:out value='${param.billAccountCode}'/>"
				cssClass="text medium" />
		</div>
		<div class="new_searchBar">
			<jecs:locale key="jpmProductSaleTeamType.state" />
			<jecs:list listCode="jmimemberteam.status" name="status" id="status"
				showBlankLine="false" value="${param.status}" defaultValue="" />
		</div>
		<div class="new_searchBar">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<button name="search" class="btn btn_sele" type="submit">
				<jecs:locale key="operation.button.search"/>
			</button>
		</div>
			<c:out value="${buttons}" escapeXml="false" />
	</div>
</form>
<ec:table tableId="jfiQuotaListTable" items="jfiQuotaList"
	var="jfiQuota"
	action="${pageContext.request.contextPath}/jfiQuotas.html" width="100%"
	retrieveRowsCallback="limit" autoIncludeParameters="true"
	rowsDisplayed="20" sortable="true"
	imagePath="./images/extremetable/*.gif">
	<ec:exportXls fileName="jfiQuotas.xls" />
	<ec:row>
		<ec:column property="validityPeriod" title="bdBounsDeduct.wweek" />
		<ec:column property="fiBillAccount.billAccountCode"
			title="fiBillAccount.AccountCode" />
		<ec:column property="status" title="jpmProductSaleTeamType.state">
			<c:choose>
				<c:when test="${jfiQuota.status=='0'}">
					<font color='green'><jecs:code
							listCode="jmimemberteam.status" value="${jfiQuota.status}" />
					</font>
				</c:when>
				<c:when test="${jfiQuota.status=='1'}">
					<font color='red'><jecs:code listCode="jmimemberteam.status"
							value="${jfiQuota.status}" />
					</font>
				</c:when>
			</c:choose>
		</ec:column>
		<ec:column property="maxMoney" title="jfiquota.maxmoney" />
		<ec:column property="operateName" title="pdProductsMain.editUreNo" />
		<ec:column property="operateTime" title="sysOperationLog.operateTime"
			format="yyyy-MM-dd HH:mm:ss" cell="date" />
		<ec:column property="remark" title="busi.common.remark" />
		<ec:column property="edit" title="title.operation" sortable="false"
			viewsAllowed="html">
			<a
				href="<c:url value='/jfiAmountDetails.html'/>?quotaId=${jfiQuota.quotaId }"><font
				color="red" style="cursor: pointer;">detail</font>
			</a>
		</ec:column>
	</ec:row>

</ec:table>

<script type="text/javascript">

   function editJfiQuota(quotaId){
   		<jecs:power powerCode="editJfiQuota">
					window.location="editJfiQuota.html?strAction=editJfiQuota&quotaId="+quotaId;
			</jecs:power>
		}

</script>
