
<%@ include file="/common/taglibs.jsp"%>

<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" />
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script>
<script type="text/javascript"
	src="./scripts/calendar/calendar-setup.js"> </script>
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script>

<title><jecs:locale key="jpoMemberNycDetail.title" />
</title>
<content tag="heading">
<jecs:locale key="jpoMemberNycDetail.heading" />
</content>

<c:set var="buttons">

	<jecs:power powerCode="${param.strAction}">
		<button type="submit" class="btn btn_sele" name="save"
			onclick="bCancel=false">
			<jecs:locale key="button.save" />
		</button>
	</jecs:power>
	<c:if test="${jpoMemberNyc.id !=null}">
		<jecs:power powerCode="deleteJpoMemberNyc">

			<button type="submit" class="btn btn_sele" name="delete"
				onclick="bCancel=true;return confirmDelete(this.form,'JpoMemberNyc')">
				<jecs:locale key="button.delete" />
			</button>
		</jecs:power>
	</c:if>

	<button type="button" class="btn btn_sele" name="back"
		onclick="location.href='${ctx}/jpoMemberNycs.html'">
		<jecs:locale key="button.back" />
	</button>
</c:set>

<spring:bind path="jpoMemberNyc.*">
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

<form:form commandName="jpoMemberNyc" method="post"
	action="editJpoMemberNyc.html"
	onsubmit="return validateJpoMemberNyc(this)" id="jpoMemberNycForm">

	<input type="hidden" name="strAction" value="${param.strAction }" />

	<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  

onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return 

confirmDelete('JpoMemberNyc')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" 

value="<jecs:locale key="button.cancel"/>" />

</legend-->
	<table class='detail' width="70%">
		<tbody class="window">
			<tr class="edit_tr">

				<form:hidden path="id" />

				<th>
					<span class="text-font-style-tc"><span class="req">*</span><jecs:label
							key="poMemberNyc.yearOfMonth" />
					</span>
				</th>
				<td align="left">
					<!--form:errors path="yearOfMonth" cssClass="fieldError"/-->
					<span class="textbox"><form:input path="yearOfMonth"
							id="yearOfMonth" cssClass="textbox-text" />
					</span>
				</td>

				<th>
					<span class="text-font-style-tc"><span class="req">*</span><jecs:label
							key="poMemberNyc.memberNo" />
					</span>
				</th>
				<td align="left">
					<!--form:errors path="memberNo" cssClass="fieldError"/-->
					<span class="textbox"><form:input path="memberNo"
							id="memberNo" cssClass="textbox-text" />
					</span>
				</td>
			</tr>

			<tr class="edit_tr">
				<th>
					<span class="text-font-style-tc"><span class="req">*</span><jecs:label
							key="poMemberNyc.pushAt" />
					</span>
				</th>
				<td align="left">
					<span class="textbox"> <form:input path="pushAt" id="pushAt"
							readonly="true" cssClass="textbox-text"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})" />
					</span>


				</td>

				<th>
					<span class="text-font-style-tc"><span class="req">*</span><jecs:label
							key="poMemberNyc.status" />
					</span>
				</th>
				<td align="left">
					<!--form:errors path="status" cssClass="fieldError"/-->

					<span class="textbox"> <form:select path="status"
							cssClass="textbox-text">

							<c:choose>
								<c:when test="${jpoMemberNyc.status eq 0}">
									<option value="0" selected>
										<jecs:locale key="poMemberNyc.status.unlock" />
									</option>
									<option value="1">
										<jecs:locale key="poMemberNyc.status.lock" />
									</option>
								</c:when>
								<c:otherwise>
									<option value="0">
										<jecs:locale key="poMemberNyc.status.unlock" />
									</option>
									<option value="1" selected>
										<jecs:locale key="poMemberNyc.status.lock" />
									</option>
								</c:otherwise>
							</c:choose>

						</form:select>
					</span>
				</td>
			</tr>

			<tr class="edit_tr">
				<th>

				</th>
				<td align="left" colspan="3">
					<p style="word-wrap: break-word; word-break: break-all;">
						${jpoMemberNyc.oldRemarks}
					</p>



				</td>

			</tr>

			<tr class="edit_tr">
				<th>
					<span class="text-font-style-tc-tare">
					<span class="req">*</span><jecs:label key="poMemberNyc.remarks" />
					</span>
				</th>
				<td align="left" colspan="3">

					<span class="text-font-style-tc-right"><form:textarea
							path="remarks" id="remarks" cssClass="textarea_border"></form:textarea>
					</span>

				</td>
			</tr>
			<tr>
				<td class="command" colspan="4" align="center">
					<c:out value="${buttons}" escapeXml="false" />
				</td>
			</tr>

		</tbody>
	</table>
	<!--/fieldset-->

	<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" 

value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return 

confirmDelete('JpoMemberNyc')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" 

value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('jpoMemberNycForm'));






    var bCancel = false;

    function validateJpoMemberNyc(form) {
        if (bCancel)
            return true;
        else
            return validateRequired(form);
    }

    function required () {
        this.aa = new Array("yearOfMonth", "<jecs:locale  key="poMemberNyc.yearOfMonth"/> 

<jecs:locale  key="field.required"/>", new Function ("varName", " return this[varName];"));
        this.ab = new Array("memberNo", "<jecs:locale  key="poMemberNyc.memberNo"/>  <jecs:locale  

key="field.required"/>", new Function ("varName", " return this[varName];"));
        this.ac = new Array("pushAt", "<jecs:locale  key="poMemberNyc.pushAt"/>  <jecs:locale  

key="field.required"/>", new Function ("varName", " return this[varName];"));
        this.ad = new Array("remarks", "<jecs:locale  key="poMemberNyc.remarks"/>  <jecs:locale  

key="field.required"/>", new Function ("varName", " return this[varName];"));
        this.ae = new Array("status", "<jecs:locale  key="poMemberNyc.status"/>  <jecs:locale  

key="field.required"/>", new Function ("varName", " return this[varName];"));
    }

 
</script>

<script type="text/javascript"
	src="<c:url value="/scripts/validator.jsp"/>"></script>
