<%@ page language="java" errorPage="/error.jsp"
	contentType="text/html; charset=utf-8"%><%@ include
	file="/common/taglibs.jsp"%>

<title><jecs:locale key="bdPeriodList.title" />
</title>
<content tag="heading">
<jecs:locale key="bdPeriodList.heading" />
</content>
<meta name="menu" content="BdSendRecordMenu" />

<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" />
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script>
<script type="text/javascript"
	src="./scripts/calendar/calendar-setup.js"> </script>
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script>

<script type="text/javascript" src="./scripts/validate.js"> </script>
<link rel="stylesheet" type="text/css" media="all"
	href="<c:url value='/styles/print.css'/>" />
<style media="print">
.noPrint {
	display: none;
}
</style>
<c:if test="${not empty errorMessages }">

	${ errorMessages }
	
		<c:remove var="errorMessages" scope="session" />
</c:if>
<form:form commandName="bdPeriod" method="post"
	action="jmiMemberDataSelectReport.html" id="bdPeriodForm"
	enctype="multipart/form-data">

	<spring:bind path="bdPeriod.*">
		<c:if test="${not empty status.errorMessages}">
			<div class="error">
				<c:forEach var="error" items="${status.errorMessages}">
					<img src="<c:url value="/images/iconWarning.gif"/>"
						alt="<jecs:locale key="icon.warning"/>" class="icon" />
					<c:out value="${error}" escapeXml="false" />
					<br />
				</c:forEach>
			</div>
		</c:if>
	</spring:bind>

	<table class='detail' width="70%">
		<tbody class="window">				
			<tr class="edit_tr">
				<th width="30%">
					<jecs:label key="fiPayData.importFile" />
				</th>
				<td colspan="3" >
				 	<input name="importExcelFile" type="file" id="importExcelFile"
					size="50" />
				</td>			
			</tr>
			<tr class="edit_tr">
				<th>
					<jecs:label key="busi.common.remark" />
				</th>
				<td colspan="3" >
				 	所导入的文件格式说明:
					<br />
					1.第一行为抬头，不导入
					<br />
					2.第1列为： 会员编号
					<br />
					注：找出所导入的会员编号中，是否存在其推荐网络下，如果有，就展现在导出文件的第二列中
				</td>			
			</tr>
			<tr>
				<td class="command" colspan="4" align="center">
					<input type="submit" name="submit"
						value="<jecs:locale key="button.report"/>" class="btn btn_sele" />
					<input type="hidden" name="strAction"
						value="jmiMemberDataSelectReport" />
				</td>
			</tr>
		</tbody>
	</table>

</form:form>

