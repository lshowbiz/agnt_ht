<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="alDistrictDetail.title"/></title>
<content tag="heading"><jecs:locale key="alDistrictDetail.heading"/></content>



<spring:bind path="alDistrict.*">
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

<form:form commandName="alDistrict" method="post" action="editAlDistrict.html" onsubmit="return validateAlDistrict(this)" id="alDistrictForm">


<input type="hidden" name="strAction" value="${param.strAction }"/>

<table class='detail' width="70%" >
		<tbody class="window" >

<form:hidden path="districtId"/>

<input type="hidden" name="cityId" value="${alDistrict.alCity.cityId}"/>
	<tr class="edit_tr">
		<th><span class="text-font-style-tc"><jecs:label key="alDistrict.in.city"/></span></th>
        <td><span class="textbox">${alDistrict.alCity.cityName}</span></td>
		
		<th><span class="text-font-style-tc"><jecs:label  key="alDistrict.districtCode"/></span></th>
		 <td>
			<span class="textbox">
			<c:if test="${not empty alCity.cityId}">
				<form:input path="districtCode" id="districtCode" cssClass="textbox-text" readonly="true" size="4"/>
			</c:if>
			<c:if test="${empty alCity.cityId }">
				<form:input path="districtCode" id="districtCode" size="4" cssClass="textbox-text"/>
			</c:if>
			</span>
		</td>
    </tr>
    
    <tr class="edit_tr">
		<th><span class="text-font-style-tc"><jecs:label  key="alDistrict.districtName"/></span></th>
		<td>
			<span class="textbox">
			<form:input path="districtName" id="districtName" cssClass="textbox-text"/>
			</span>
		</td>
	</tr>
	<tr>
		<td class="command" colspan="4" align="center">
			<jecs:power powerCode="${param.strAction}">
			<button type="submit" class="btn btn_sele" name="save"  onclick="bCancel=false" value="<jecs:locale key="operation.button.save"/>" ><jecs:locale key="operation.button.save"/></button>
			</jecs:power>
			<c:if test="${not empty alDistrict.districtId and sessionScope.sessionLogin.companyCode=='AA'}">
			<jecs:power powerCode="deleteAlDistrict">
			<button type="submit" class="btn btn_sele" name="delete" onclick="bCancel=true;return confirmDelete(this.form, 'AlDistrict')" value="<jecs:locale key="operation.button.delete"/>" ><jecs:locale key="operation.button.delete"/></button>
			</jecs:power>
			</c:if>
			<button type="button" class="btn btn_sele" name="cancel" onclick="window.location='alDistricts.html?cityId=${alDistrict.alCity.cityId }&needReload=1'" value="<jecs:locale key="operation.button.cancel"/>" ><jecs:locale key="operation.button.cancel"/></button>

		</td>
	</tr>
</tbody>
</table>
<!--/fieldset-->

<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('AlDistrict')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('alDistrictForm'));
</script>

<v:javascript formName="alDistrict" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
