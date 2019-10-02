<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="alCityDetail.title"/></title>
<content tag="heading"><jecs:locale key="alCityDetail.heading"/></content>



<spring:bind path="alCity.*">
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

<form:form commandName="alCity" method="post" action="editAlCity.html" onsubmit="return validateAlCity(this)" id="alCityForm">

<div id="titleBar" class="searchBar">

			
	
</div>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<table class='detail' width="70%" >
		<tbody class="window" >

<form:hidden path="cityId"/>

<input type="hidden" name="stateProvinceId" value="${alCity.alStateProvince.stateProvinceId }"/>
	<tr class="edit_tr">
		<th><span class="text-font-style-tc"><jecs:label key="alCompany.stateProvinceCode"/></span></th>
        <td><span class="textbox">${alCity.alStateProvince.stateProvinceName}</span></td>
		
		<th><span class="text-font-style-tc"> <jecs:label  key="alCity.cityCode"/></span></th>
		<td>
			<span class="textbox">
			<c:if test="${not empty alCity.cityId}">
				<form:input path="cityCode" id="cityCode" cssClass="textbox-text" readonly="true" size="4"/>
			</c:if>
			<c:if test="${empty alCity.cityId }">
				<form:input path="cityCode" id="cityCode" size="4" cssClass="textbox-text"/>
			</c:if>
			</span>
		</td>
    </tr>
  

    <tr class="edit_tr">
		<th><span class="text-font-style-tc"><jecs:label  key="alCity.cityName"/></span></th>
		<td>
			<span class="textbox">
			<!--form:errors path="cityName" cssClass="fieldError"/-->
			<form:input path="cityName" id="cityName" cssClass="textbox-text"/>
			</span>
		</td>
	</tr>
	<tr>
		<td class="command" colspan="4" align="center">
			<jecs:power powerCode="${param.strAction}">
			<button type="submit" class="btn btn_sele" name="save"  onclick="bCancel=false" value="<jecs:locale key="operation.button.save"/>" ><jecs:locale key="operation.button.save"/></button>
			</jecs:power>
			<c:if test="${not empty alCity.cityId and sessionScope.sessionLogin.companyCode=='AA'}">
			<jecs:power powerCode="deleteAlCity">
			<button type="submit" class="btn btn_sele" name="delete" onclick="bCancel=true;return confirmDelete(this.form, 'AlCity')" value="<jecs:locale key="operation.button.delete"/>" ><jecs:locale key="operation.button.delete"/></button>
			</jecs:power>
			</c:if>
			<button type="button" class="btn btn_sele" name="cancel" onclick="window.location='alCitys.html?stateProvinceId=${alCity.alStateProvince.stateProvinceId }&needReload=1'" value="<jecs:locale key="operation.button.cancel"/>" ><jecs:locale key="operation.button.cancel"/></button>

		</td>
	</tr>
</tbody>
</table>

</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('alCityForm'));
</script>

<v:javascript formName="alCity" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
