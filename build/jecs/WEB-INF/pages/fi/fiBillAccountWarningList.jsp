<%@ include file="/common/taglibs.jsp"%>
<%@page import="java.math.BigDecimal"%>
<%@ page contentType="text/html; charset=utf-8" language="java"%>
<title><jecs:locale key="fiBillAccountWarningList.title" />
</title>
<content tag="heading">
<jecs:locale key="fiBillAccountWarningList.heading" />
</content>
<meta name="menu" content="FiBillAccountWarningMenu" />

<form action="fiBillAccountWarnings.html" method="get"
	name="fiBillAccountWarningSearchForm"
	id="fiBillAccountWarningSearchForm">
	
	
		<div class="new_searchBar">
			<button name="search" class="btn btn_sele" type="submit">
				<jecs:locale key="operation.button.refresh"/>
			</button>
			<button name="search" class="btn btn_ins" onclick="location.href='<c:url value="/fiBillAccounts.html"/>'">
				<jecs:locale key="button.back"/>
			</button>
		</div>

</form>
<table class='detail' width="100%">
	<tr>
		<th>
			所属大区
		</th>
		<th>
			所属省份
		</th>
		<th>
			商户号
		</th>
		<th>
			经销商名称
		</th>

		<th>
			警戒线
		</th>
		<th>
			当前总额
		</th>
		<th>
			离上限差额
		</th>
		<th>
			手工调整
		</th>
	</tr>

	<c:forEach items="${fiBillAccountWarningList}" var="mfiBillAccounts">
		<tr align="right"
			<c:if test="${(mfiBillAccounts.income_limit - mfiBillAccounts.now_total_amount)<=0}">style='background-color:#FF9933'</c:if>>
			<td>
				<c:if test="${mfiBillAccounts.field=='1'}">东北区</c:if>
				<c:if test="${mfiBillAccounts.field=='2'}">华北区</c:if>
				<c:if test="${mfiBillAccounts.field=='3'}">华东区</c:if>
				<c:if test="${mfiBillAccounts.field=='4'}">华南区</c:if>
			</td>
			<td>
				<jecs:region regionType="p" regionId="${mfiBillAccounts.province}"></jecs:region>
			</td>
			<td>
				${mfiBillAccounts.bill_account_code}
			</td>
			<td>
				${mfiBillAccounts.account_name}
			</td>

			<td>
				${mfiBillAccounts.income_limit}
			</td>
			<td>
				${mfiBillAccounts.now_total_amount}
			</td>
			<td>
				<c:set var="a" value="${mfiBillAccounts.income_limit}" />
				<c:set var="b" value="${mfiBillAccounts.now_total_amount}" />
				<c:if
					test="${(mfiBillAccounts.income_limit - mfiBillAccounts.now_total_amount)<10000}">

					<span style='color: red'> <%
						BigDecimal c = new BigDecimal(pageContext.getAttribute("a").toString());
						BigDecimal d = new BigDecimal(pageContext.getAttribute("b").toString());
						BigDecimal e=c.subtract(d);
						out.print(e);
					 %> </span>
				</c:if>
				<c:if
					test="${(mfiBillAccounts.income_limit - mfiBillAccounts.now_total_amount)>=10000}">
					<%
						BigDecimal c = new BigDecimal(pageContext.getAttribute("a").toString());
						BigDecimal d = new BigDecimal(pageContext.getAttribute("b").toString());
						BigDecimal e=c.subtract(d);
						out.print(e);
					 %>
				</c:if>
			</td>
			<td>
				<img src="<c:url value='/images/icons/editIcon.gif'/>"
					onclick="javascript:editFiBillAccount('${mfiBillAccounts.account_id}')"
					style="cursor: pointer;" title="<jecs:locale key="button.edit"/>" />
			</td>
		</tr>
	</c:forEach>

</table>
<script type="text/javascript">

   function editFiBillAccount(accountId){
   		<jecs:power powerCode="editFiBillAccount">
					window.location="editFiBillAccount.html?strAction=editFiBillAccount&accountId="+accountId;
			</jecs:power>
		}

</script>
<%-- 
<ec:table 
	tableId="fiBillAccountWarningListTable"
	items="fiBillAccountWarningList"
	var="fiBillAccountWarning"
	action="${pageContext.request.contextPath}/fiBillAccountWarnings.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="100" sortable="true" imagePath="/jecs/images/extremetable/*.gif">
				<ec:row>
				<ec:column property="field" title="fiBillAccount.field" >
					<jecs:code listCode="fibillaccount.area" value="${fiBillAccountWarning.field}"/>
				</ec:column>
				<ec:column property="province" title="fiBillAccount.province">
    				<jecs:region regionType="p" regionId="${fiBillAccountWarning.province}"></jecs:region>
    			</ec:column>
    			<ec:column property="bill_account_code" title="fiBillAccount.billAccountCode" />
    			<ec:column property="account_name" title="fiBillAccount.accountName" />
    			<ec:column property="income_limit" title="fiBillAccount.incomeLimit" />
    			<ec:column property="now_total_amount" title="fiBillAccount.nowTotalAmount" />
    			<ec:column property="difference_amount" title="fiBillAccount.differenceAmount">
    				<c:if test="${(fiBillAccountWarning.income_limit - fiBillAccountWarning.now_total_amount)<10000}">
							<span style='color: red'>${fiBillAccountWarning.income_limit - fiBillAccountWarning.now_total_amount}</span>
					</c:if>
					<c:if test="${(fiBillAccountWarning.income_limit - fiBillAccountWarning.now_total_amount)>=10000}">
							${fiBillAccountWarning.income_limit - fiBillAccountWarning.now_total_amount}
					</c:if>
    			</ec:column>
    			
    			<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editFiBillAccount('${fiBillAccountWarning.billAccountCode}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
				</ec:row>

</ec:table>	
--%>

