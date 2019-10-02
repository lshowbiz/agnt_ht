<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jbdTravelPointDetailDetail.title" />
</title>
<content tag="heading">
<jecs:locale key="jbdTravelPointDetailDetail.heading" />
</content>


<spring:bind path="jbdTravelPointDetail.*">
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

<form:form commandName="jbdTravelPointDetail" method="post"
	action="editJbdTravelPointDetail.html"
	onsubmit="if(isFormPosted()){return true;}{return false;}"
	id="jbdTravelPointDetailForm">
	<input type="hidden" name="strAction" value="${param.strAction }" />
	
	<table class='detail' width="70%">
		<tbody class="window">
			<tr>
				<th>
					<span class="text-font-style-tc">
						<span class="req">*</span><jecs:label key="miMember.memberNo" />
					</span>
				</th>
				<td>
					<span class="textbox">
						<form:input path="userCode" id="userCode" cssClass="textbox-text" />
					</span>
				</td>
				<th>
					<span class="text-font-style-tc">
						<span class="req">*</span><jecs:label key="bdBonusStatReport.item" />
					</span>
				</th>
				<td>
					<span class="textbox">
						<jecs:list name="travelType" id="travelType" styleClass="textbox-text"
						listCode="bd.traveltype" value="${jbdTravelPointDetail.travelType}"
						defaultValue="" showBlankLine="true" />
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
