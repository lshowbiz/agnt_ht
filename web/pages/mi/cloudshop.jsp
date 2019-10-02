<%@ page language="java" errorPage="/error.jsp"
	contentType="text/html; charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jmiMemberDetail.title" /></title>
<content tag="heading">
<jecs:locale key="jmiMemberDetail.heading" /></content>
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" />
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script>
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script>
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script>
<script type="text/javascript" src="<c:url value='/dwr/util.js'/>"></script>
<script type="text/javascript" src="<c:url value='/dwr/engine.js'/>"></script>
<script type="text/javascript" src="<c:url value='/dwr/interface/jmiMemberManager'/>"></script>

<spring:bind path="jmiMember.*">
    <c:if test="${not empty status.errorMessages}">
    <div class="error">    
        <c:forEach var="error" items="${status.errorMessages}">
            <img src="<c:url value="images/newIcons/warning_16.gif"/>"
                alt="<jecs:locale key="icon.warning"/>" class="icon" />
            <c:out value="${error}" escapeXml="false"/><br />
        </c:forEach>
    </div>
    </c:if>
</spring:bind>

<form:form commandName="jmiMember" 
	method="post" 
	action="cloudshop.html"
	id="cloudshopFrom"
	onsubmit="if(isFormPosted()){return true;}{return false;}">

	<fieldset
		style="border-top: 2px solid #cccccc; border-right: 0px; border-bottom: 0px; border-left: 0px; font-family: Arial, Helvetica, sans-serif; font-weight: bold;">
		<legend> 云店会员手机号码修改： </legend>
		<table class='detail' width="70%">
			<tbody class="window">
				<tr class="edit_tr">
					<th><span class="text-font-style-tc"><jecs:label key="pd.agentormember" required="true" /></span></th>
					<td><span class="textbox" id="userCodeSpan">
					<form:input path="userCode" id="userCode" cssClass="textbox-text" cssStyle="width:124px;" />
					</span> 
					</td>
					<th><span class="text-font-style-tc"><jecs:label key="linkman.mobilephone" required="true" /></span></th>
					<td><span class="textbox"> 
					<form:input path="cloudshopPhone" id="cloudshopPhone" cssClass="textbox-text" cssStyle="width:124px;"/>
					</span>
					</td>
				</tr>
				<tr class="edit_tr">
					<td colspan="4" align="center" class="command">
						<button type="submit" class="btn btn_sele" name="save" onclick="bCancel=false">
							<jecs:locale key="operation.button.save" />
						</button>
						<%-- <button type="button" class="btn btn_sele" name="back">
							<jecs:locale key="operation.button.return" />
						</button> --%>
					</td>
				</tr>
			</tbody>
		</table>
	</fieldset>
</form:form>


