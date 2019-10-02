<%@ page language="java" errorPage="/error.jsp" contentType="text/html; charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<form action="" method="get" name="stockAccountForm" id="stockAccountForm">
	<input id="strAction" name="strAction" value="" type="hidden">
	<div class="searchBar">&nbsp;&nbsp;
		<label>会员编号</label>
		<input name="userCode" type="text" value="${param.userCode}" size="10"/>	
		<label>会员姓名</label>	
		<input name="userName" type="text" value="${param.userName}" size="10"/>	
		<button name="search" type="submit" class="btn btn_sele" >	<jecs:locale key="operation.button.search"/></button>
	</div>
</form>

<ec:table 
	tableId="jsysStockAccountListTable"
	items="jsysStockAccountList"
	var="stockAccount"
	autoIncludeParameters="true"
	action="${pageContext.request.contextPath}/jsysStockAccountList.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
		<ec:exportXls fileName="stockAccount.xls"/>
			<ec:row>
   			<%-- <ec:column property="create_date" title="填写时间" >
   				<fmt:parseDate value="${stockAccount['CREATE_DATE']}" pattern="yyyy-MM-dd" var="varDate"/>
				<fmt:formatDate value="${varDate}" pattern="yyyy-MM-dd"/>
   			</ec:column> --%>
   			<ec:column property="id" title="统计序号" />
   			<ec:column property="user_code" title="会员编号" />
   			<ec:column property="user_name" title="姓名" />
   			<%-- <ec:column property="papernumber" title="身份证号" />
   			<ec:column property="mobiletele" title="手机号" /> --%>
   			<ec:column property="stock_account" title="港股账号" />
   			<ec:column property="fee_status" title="是否已汇入手续费" cell="com.joymain.jecs.util.ectable.EcTableCell"><jecs:code listCode="yesno" value="${stockAccount.fee_status}"/></ec:column>
		</ec:row>
</ec:table>