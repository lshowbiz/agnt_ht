<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html; charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<title>
<jecs:locale key="alCompanyAreaDetail.title"/>
</title>
<content tag="heading">
	<jecs:locale key="alCompanyAreaDetail.heading"/>
</content>
<spring:bind path="alCompanyArea.*">
<c:if test="${not empty status.errorMessages}">
<div class="error">
	<c:forEach var="error" items="${status.errorMessages}"> <img src="<c:url value="images/newIcons/warning_16.gif"/>"
		alt="
		<jecs:locale key="icon.warning"/>
		" class="icon" />
		<c:out value="${error}" escapeXml="false"/>
		<br />
	</c:forEach>
</div>
</c:if>
</spring:bind>
<form method="post" action="editAlCompanyArea.html" onsubmit="return validateAlCompanyArea(this)" id="alCompanyAreaForm" name="alCompanyAreaForm">
	<input type="hidden" name="strAction" value="editAlCompanyArea"/>
	<table class='detail' width="100%">
		<tr>
			<td align="left">所有国家/地区</td>
			<td align="left">&nbsp;</td>
			<td>${alCompany.companyName} 分公司所管辖的国家/地区</td>
		</tr>
		<tr>
			<td width="50%" valign="top">
				<select name="noCompanyAlCountry" size="13" multiple="multiple" id="noCompanyAlCountry" style="width:100%;height:180px;">
					<c:forEach items="${noCompanyAlCountrys}" var="noCompanyAlCountryVar">
					<option value="${noCompanyAlCountryVar.countryCode }">${noCompanyAlCountryVar.countryName }</option>
					</c:forEach>
				</select>
			</td>
			<td><input type="button" name="btnSelect" id="btnSelect" value="&gt;" title="选择" onclick="selectCountryToCompany();"/>
			<br />
			<br />
			<input type="button" name="btnNoSelect" id="btnNoSelect" value="&lt;" title="不选择" onclick="unSelectCountryFromCompany();"/></td>
			<td width="50%" valign="top">
				<select name="companyAlCountry" size="13" multiple="multiple" id="companyAlCountry" style="width:100%;height:180px;">
					<c:forEach items="${companyAlCountrys}" var="companyAlCountryVar">
					<option value="${companyAlCountryVar.countryCode }">${companyAlCountryVar.countryName }</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td class="command" colspan="4" align="center">
				<input type="hidden" name="companyId" value="${param.companyId }"/>
				<jecs:power powerCode="editAlCompanyArea">
					<button type="submit" class="btn btn_sele" name="save"  onclick="bCancel=false"><jecs:locale key="operation.button.save"/></button>
				</jecs:power>
				<button type="button" class="btn btn_sele" name="btnReturn"  onclick="window.location='alCompanys.html'"><jecs:locale key="operation.button.cancel"/></button> 
			</td>
		</tr>
	</table>
</form>

<script type="text/javascript">
function selectCountryToCompany(){
	var theForm=document.alCompanyAreaForm;
	var noCompanyAlCountry=theForm.noCompanyAlCountry;
	var companyAlCountry=theForm.companyAlCountry;
	
	for(var i=0;i<noCompanyAlCountry.options.length;i++){
		if(noCompanyAlCountry.options[i].selected){
			var newItem = new Option(noCompanyAlCountry.options[i].text,noCompanyAlCountry.options[i].value);
			companyAlCountry.options.add(newItem);
        	noCompanyAlCountry.options[i]=null;
        	i--;
		}
	}
}

function unSelectCountryFromCompany(){
	var theForm=document.alCompanyAreaForm;
	var noCompanyAlCountry=theForm.noCompanyAlCountry;
	var companyAlCountry=theForm.companyAlCountry;
	
	for(var i=0;i<companyAlCountry.options.length;i++){
		if(companyAlCountry.options[i].selected){
			var newItem = new Option(companyAlCountry.options[i].text,companyAlCountry.options[i].value);
			noCompanyAlCountry.options.add(newItem);
        	companyAlCountry.options[i]=null;
        	i--;
		}
	}
}

function validateAlCompanyArea(theForm){
	for(var i=0;i<theForm.companyAlCountry.options.length;i++){
		theForm.companyAlCountry.options[i].selected=true;
	}
	return isFormPosted();
}
</script>