<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=utf-8" language="java"%>
<title><jecs:locale key="fiPayAccountList.title"/></title>
<content tag="heading"><jecs:locale key="fiPayAccountList.heading"/></content>
<meta name="menu" content="FiPayAccountMenu"/>


<form action="fiPayAccounts.html" method="get" name="fiPayAccountSearchForm" id="fiPayAccountSearchForm">
<div class="searchBar">

	商户号：<input name="accountCode" type="text" value="${param['accountCode'] }" size="14"/>
	支付平台：<jecs:list listCode="providertypes" name="providerType" value="${param.providerType}" defaultValue="" showBlankLine="true"/>
	适用类型：<select id="accountType" name="accountType">
				<option value=""></option>
				<option value="1">网页</option>
        		<option value="2">移动终端</option>
			</select>
	状态：<select id="status" name="status">
				<option value=""></option>
				<option value="1">启用</option>
        		<option value="0">禁用</option>
			</select>
			
	<input name="search" class="button" type="submit" value="<jecs:locale key="operation.button.search"/>"/>
	<jecs:power powerCode="addFiPayAccount">
		    <input type="button" class="button" style="margin-right: 5px"
		        onclick="location.href='<c:url value="/editFiPayAccount.html"/>?strAction=addFiPayAccount'"
		        value="<jecs:locale key="button.add"/>"/>
	</jecs:power>
</div>
</form>
<ec:table 
	tableId="fiPayAccountListTable"
	items="fiPayAccountList"
	var="fiPayAccount"
	action="${pageContext.request.contextPath}/fiPayAccounts.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="/jecs/images/extremetable/*.gif">
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editFiPayAccount('${fiPayAccount.accountId}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
				
				<ec:column property="providerType" title="支付平台" >
					<jecs:code listCode="providertypes" value="${fiPayAccount.providerType}"/>
				</ec:column>
				<ec:column property="accountCode" title="商户号" />				
    			<ec:column property="passKey" title="密钥" />    			
    			<ec:column property="accountName" title="注册名称" />
    			<ec:column property="registerEmail" title="注册邮箱" />
    			<ec:column property="remark" title="备注" />
    			
    			
    			<ec:column property="isDefault" title="是否默认" >
    				<c:if test="${fiPayAccount.isDefault==1}">是</c:if>
    				<c:if test="${fiPayAccount.isDefault==0}">&nbsp;</c:if>
    			</ec:column>
    			<ec:column property="status" title="状态" >
    				<c:if test="${fiPayAccount.status==1}">启用</c:if>
    				<c:if test="${fiPayAccount.status==0}">禁用</c:if>
    			</ec:column>
    			
    			<ec:column property="createUserName" title="创建人" />
    			<ec:column property="createTime" title="创建时间" />
    			<ec:column property="accountType" title="适用类型" >
    				<c:if test="${fiPayAccount.accountType==1}">启用</c:if>
    				<c:if test="${fiPayAccount.accountType==0}">禁用</c:if>
    			</ec:column>
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editFiPayAccount(accountId){
   		<jecs:power powerCode="editFiPayAccount">
					window.location="editFiPayAccount.html?strAction=editFiPayAccount&accountId="+accountId;
			</jecs:power>
		}

</script>
