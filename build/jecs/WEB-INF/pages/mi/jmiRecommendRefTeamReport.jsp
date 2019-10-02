<%@ include file="/common/taglibs.jsp"%>

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

<c:if test="${empty bdPeriod.wid}">
	<form:form commandName="bdPeriod" method="post"
		action="jmiRecommendRefTeamReport.html" id="bdPeriodForm">
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
					<th>
						<span class="text-font-style-tc"><jecs:label key="miMember.memberNo" /></span>
					</th>
					<td>
						<span class="textbox">
					 	<input type="text" name="userCode" id="userCode"
						value="${param.userCode }" size="10" class="textbox-text" />
						</span>
					</td>			
				
					<th>
						<span class="text-font-style-tc"><jecs:locale key="bdCaculateLog.cretaeTime" /></span>
					</th>
					<td>
						<span class="textbox">
					 	<input id="startDate" name="startDate" type="text" class="textbox-text"
							value="${param.startDate }" style="cursor:pointer;width:auto;"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})" />
						</span>
						
						-
						<span class="textbox">
						<input id="endDate" name="endDate" type="text" class="textbox-text"
							value="${param.endDate }" style="cursor:pointer;width:auto;"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})" />
						</span>
					</td>			
				</tr>
				<tr class="edit_tr">
					<td class="command" colspan="4" align="center">
						<jecs:power powerCode="jmiRecommendRefTeamReport">
							<button type="submit" name="submit" class="btn btn_sele"">
								<jecs:locale key="button.report" />
							</button>
							<%-- 	
						<input type="submit" name="submit" value="<jecs:locale key="button.report"/>" class="button"/>
						--%>
						</jecs:power>
						<input type="hidden" name="strAction"
							value="jmiRecommendRefTeamReport" />
					</td>
				</tr>
			</tbody>
		</table>

	</form:form>

</c:if>
