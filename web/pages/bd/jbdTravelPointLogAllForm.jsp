<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jbdTravelPointLogAllDetail.title" /></title>
<content tag="heading">
<jecs:locale key="jbdTravelPointLogAllDetail.heading" />
</content>

<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" />
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script>
<script type="text/javascript"
	src="./scripts/calendar/calendar-setup.js"> </script>
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script>

<c:set var="buttons">

	<jecs:power powerCode="${param.strAction}">
		<input type="submit" class="btn btn_sele" name="save"
			onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
	</jecs:power>
	<!--<jecs:power powerCode="deleteJbdTravelPointLogAll">
				<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'JbdTravelPointLogAll')" value="<jecs:locale key="button.delete"/>" />
		</jecs:power>-->
</c:set>

<spring:bind path="jbdTravelPointLogAll.*">
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

<form:form commandName="jbdTravelPointLogAll" method="post"
	action="editJbdTravelPointLogAll.html"
	onsubmit="return validateJbdTravelPointLogAll(this)"
	id="jbdTravelPointLogAllForm">

	<div id="titleBar">
	</div>
	<input type="hidden" name="strAction" value="${param.strAction }" />

	<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JbdTravelPointLogAll')" value="<jecs:locale key="button.delete"/>" />
        

</legend-->
	<table class='detail' width="70%">
		<form:hidden path="logId" />
		<tbody class="window">
			<tr class="edit_tr">
				<th>
					<span class="text-font-style-tc">
						<span class="req">*</span><jecs:label key="jbdTravelPointAll.fyear" />
					</span>
				</th>
				<td>
					<span class="textbox">
						<jecs:list listCode="jbdtravelpointall.fyear" name="fyear"
						 id="fyear" value="${jbdTravelPointLogAll.fyear}" defaultValue="" styleClass="textbox-text"/>
					</span>
				</td>
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
			</tr>
			<tr class="edit_tr">
				<th>
					<span class="text-font-style-tc">
						<span class="req">*</span><jecs:label key="fiBankbookTemp.dealType" />
					</span>
				</th>
				<td>
					<span class="textbox">
						<jecs:list listCode="fibankbooktemp.dealtype" name="dealType"
						id="dealType" value="${jbdTravelPointLogAll.dealType}" styleClass="textbox-text"
						defaultValue="A" />
					</span>
				</td>
				<th>
					<span class="text-font-style-tc">
						<span class="req">*</span><jecs:label key="fiCoinLog.coin" />
					</span>
				</th>
				<td>
					<span class="textbox">
						<form:input path="usePoint" id="usePoint" cssClass="textbox-text" />
					</span>
				</td>
			</tr>
			<tr class="edit_tr">

				<th>
					<span class="text-font-style-tc-tare">
						<jecs:label key="customerRecord.remark" />
					</span>
				</th>
				<td colspan="3">
					<span class="text-font-style-tc-right">
						<form:textarea path="remark" id="remark" rows="5" cols="50"
						htmlEscape="true" cssClass="textarea_border"/>
					</span>

				</td>
			</tr>
			<tr>
				<td class="command" colspan="4" align="center">	
					<c:out value="${buttons}" escapeXml="false" />
					<input type="button" class="btn btn_sele" name="cancel"
						onclick="window.history.back()"
						value="<jecs:locale key="operation.button.return"/>" />					
				</td>
			</tr>
		</tbody>
	</table>
	<!--/fieldset-->

	<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JbdTravelPointLogAll')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('jbdTravelPointLogAllForm'));
</script>

<v:javascript formName="jbdTravelPointLogAll" cdata="false"
	dynamicJavascript="true" staticJavascript="false" />
<script type="text/javascript"
	src="<c:url value="/scripts/validator.jsp"/>"></script>
