<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jbdTravelPointLogDetail.title" /></title>
<content tag="heading">
<jecs:locale key="jbdTravelPointLogDetail.heading" />
</content>

<c:set var="buttons">

	<jecs:power powerCode="${param.strAction}">
		<input type="submit" class="button" name="save"
			onclick="bCancel=false"
			value="<jecs:locale key="operation.button.save"/>" />
	</jecs:power>
	<input type="button" class="button" name="cancel"
		onclick="window.history.back()"
		value="<jecs:locale key="operation.button.return"/>" />
</c:set>

<spring:bind path="jbdTravelPointLog.*">
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

<form:form commandName="jbdTravelPointLog" method="post"
	action="editJbdTravelPointLog.html"
	onsubmit="if(isFormPosted()){return true;}{return false;}"
	id="jbdTravelPointLogForm">
	<input type="hidden" name="strAction" value="${param.strAction }" />

	<table class='detail' width="70%">
		<form:hidden path="logId" />
		<tbody class="window">				
			<tr class="edit_tr">
				<th>
					<span class="text-font-style-tc">
						<jecs:label key="miMember.memberNo" />
					</span>
				</th>
				<td>
				 	<span class="textbox">
						<form:input path="userCode" id="userCode" cssClass="textbox-text" />
					</span>
				</td>
				<th>
					<span class="text-font-style-tc">
						<jecs:label key="fiBankbookTemp.dealType" />
					</span>
				</th>
				<td>
				 	<span class="textbox">
				 		<jecs:list listCode="fibankbooktemp.dealtype" name="dealType"
						id="dealType" value="${jbdTravelPointLog.dealType}" styleClass="textbox-text"
						defaultValue="A" />
					</span>
				</td>			
			</tr>
			<tr class="edit_tr">
				<th>
					<span class="text-font-style-tc">
						<jecs:label key="fiCoinLog.coin" />
					</span>
				</th>
				<td colspan="3" >
				 	<span class="textbox">
				 		<form:input path="usePoint" id="usePoint" cssClass="textbox-text"/>
					</span>
				</td>			
			</tr>
			<tr class="edit_tr">
				<th>
					<span class="text-font-style-tc-tare">
						<jecs:label key="customerRecord.remark" />
					</span>
				</th>
				<td colspan="3" >
				 	<span class="text-font-style-tc-right">
				 		<form:textarea path="remark" id="remark" rows="5" cols="50"
							htmlEscape="true" />
					</span>
				</td>			
			</tr>
			<tr>
				<td class="command" colspan="4" align="center">
					<jecs:power powerCode="${param.strAction}">
						<input type="submit" class="btn btn_sele" name="save"
							onclick="bCancel=false"
							value="<jecs:locale key="operation.button.save"/>" />
					</jecs:power>
					<input type="button" class="btn btn_sele" name="cancel"
						onclick="window.history.back()"
						value="<jecs:locale key="operation.button.return"/>" />				
				</td>
			</tr>
		</tbody>
	</table>
</form:form>
