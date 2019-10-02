<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="alStateProvinceDetail.title"/></title>
<content tag="heading"><jecs:locale key="alStateProvinceDetail.heading"/></content>

<form:form commandName="alStateProvince" method="post" action="editAlStateProvince.html" onsubmit="return validateAlStateProvince(this)" id="alStateProvinceForm">

<spring:bind path="alStateProvince.*">
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

<div id="titleBar" class="searchBar">
			
</div>
<form:hidden path="stateProvinceId"/>

<input type="hidden" name="countryId" value="${alStateProvince.alCountry.countryId }"/>

<table class="detail" width="70%">
<tbody class="window" >
	<tr class="edit_tr">
		<th><span class="text-font-style-tc"><jecs:label key="alStateProvince.alCountry.countryCode"/></span></th>
        <td><span class="textbox">&nbsp;&nbsp;${alStateProvince.alCountry.countryCode}</span></td>
    </tr>
    <tr class="edit_tr">
		<th><span class="text-font-style-tc"><jecs:label key="alStateProvince.stateProvinceCode"/></span></th>
        <td>
			<span class="textbox">
        <c:if test="${not empty alStateProvince.stateProvinceId }">
        	<form:input path="stateProvinceCode" id="stateProvinceCode" cssClass="textbox-text" readonly="true" size="4"/>
        </c:if>
        <c:if test="${empty alStateProvince.stateProvinceId }">
        	<form:input path="stateProvinceCode" id="stateProvinceCode" size="4" cssClass="textbox-text"/>
        </c:if>
			</span>
        </td>
    </tr>

    <tr class="edit_tr">
		<th><span class="text-font-style-tc"><jecs:label key="alStateProvince.stateProvinceName"/></span></th>
        <td>
			<span class="textbox">
        <form:input path="stateProvinceName" id="stateProvinceName" size="40" htmlEscape="true" cssClass="textbox-text"/>
        <jecs:locale key="alStateProvince.please.input.character.key"/>
			</span>
        </td>
    </tr>
	<tr>		
		<td class="command" colspan="4" align="center">
				
			<jecs:power powerCode="${param.strAction}">
			<button type="submit" class="btn btn_sele" name="save"  onclick="bCancel=false" ><jecs:locale key="operation.button.save"/></button>
			</jecs:power>
			<c:if test="${not empty alStateProvince.stateProvinceId and sessionScope.sessionLogin.companyCode=='AA'}">
			<jecs:power powerCode="deleteAlStateProvince">
			<button type="submit" class="btn btn_sele" name="delete" onclick="bCancel=true;return confirmDelete(this.form, 'AlStateProvince')" ><jecs:locale key="operation.button.delete"/></button>
			</jecs:power>
			</c:if>
			<button type="button" class="btn btn_sele" name="cancel" onclick="window.location='alStateProvinces.html?countryId=${alStateProvince.alCountry.countryId }&needReload=1'"><jecs:locale key="operation.button.cancel"/>
			</button>
			<input type="hidden" name="strAction" value="${param.strAction }"/>
		</td>
	</tr>
</tbody>   

</table>

</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('alStateProvinceForm'));
</script>

<v:javascript formName="alStateProvince" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
