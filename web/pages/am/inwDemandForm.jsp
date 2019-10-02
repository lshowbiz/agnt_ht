<%@ include file="/common/taglibs.jsp"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
<title><jecs:locale key="inwDemandDetail.title"/></title>
<content tag="heading"><jecs:locale key="inwDemandDetail.heading"/></content>

<c:set var="buttons">

<c:if test="${param.strAction != 'browserInwDemand'}">
		<jecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="operation.button.save"/>" />
		</jecs:power>
		<c:if test="${param.strAction eq 'editInwDemand'}">
			<jecs:power powerCode="deleteInwDemand">
				<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'inwDemand')" value="<jecs:locale key="operation.button.delete"/>" />
		</jecs:power>
		</c:if>
</c:if>		
		<input type="button" class="button" onclick="history.back();" value='<jecs:locale key="operation.button.return"></jecs:locale>' />
		
</c:set>


<spring:bind path="inwDemand.*">
    <c:if test="${not empty status.errorMessages}">
    <div class="error">    
        <c:forEach var="error" items="${status.errorMessages}">
            <img src="<c:url value="/images/iconWarning.gif"/>"
                alt="<jecs:locale key="icon.warning"/>" class="icon" />
            <c:out value="${error}" escapeXml="false"/><br />
        </c:forEach>
    </div>
    </c:if>
</spring:bind>

<form:form  action="editInwDemand.html"  method="post"
	enctype="multipart/form-data" 
  	commandName="inwDemand" 
  	onsubmit="return validateInwDemand(this)"
  	id="inwDemandForm"  >

<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<table class='detail' width="100%">

<form:hidden path="id"/>
    

    <c:if test="${param.strAction != 'browserInwDemand' }">
			<tr><th>
			        <jecs:label  key="inwDemand.name"/>
			    </th>
			    <td align="left">
			        <!--form:errors path="name" cssClass="fieldError"/-->
			        <form:input path="name" id="name" cssClass="text medium"/>
			    </td></tr>
			
			    <tr><th>
			        <jecs:label  key="inwDemand.summary"/>
			    </th>
			    <td align="left">
			        <!--form:errors path="summary" cssClass="fieldError"/-->
			        <form:input path="summary" id="summary" cssClass="text medium"/>
			    </td></tr>
			
			    
			     <tr><th width="20%">
			        <jecs:label  key="amAnnounce.content"/>
			    </th>
			    <td align="left">
			       	
							 			<FCK:editor instanceName="detailExplanation" toolbarSet="selfBar" height="250px">
										<jsp:attribute name="value">${inwDemand.detailExplanation}</jsp:attribute>
										
									</FCK:editor>
			    </td></tr>
			
			    <tr><th>
			        <jecs:label  key="inwDemand.demandImage"/>
			    </th>
			    <td align="left">
			        <%-- <form:input path="demandImage" id="demandImage" cssClass="text medium"/> --%>
			        
			        <c:choose>
					<c:when test="${inwDemand.demandImage!=null}"> 
						<form:hidden path="demandImage" />
						<img src="${FILE_URL }/${inwDemand.demandImage }" onclick="suonvtuShow('${FILE_URL }/${inwDemand.demandImage }');" width="150" height="150" style="border-color: green;border-width: 1px;cursor: pointer;" alt="<jecs:locale key='productimage.onclickshow'/>"/>
						<br/><input type="file" name="file2" id="file2"/><jecs:label key="jpmProductSaleImage.imageLink.Prompt" />
					 </c:when>
					<c:otherwise>
						<input type="file" name="file" id="file"/>
					</c:otherwise>
				</c:choose> 
			    </td></tr>		   
		</c:if>
		<c:if test="${param.strAction == 'browserInwDemand' }">
  		 <tr><th>
				        <jecs:label  key="inwDemand.name"/>
				    </th>
				    <td align="left">
				        ${inwDemand.name}
				    </td></tr>
				
				    <tr><th>
				        <jecs:label  key="inwDemand.summary"/>
				    </th>
				    <td align="left">
				        ${inwDemand.summary}
				    </td></tr>
				
				    
				     <tr><th width="20%">
				        <jecs:label  key="amAnnounce.content"/>
				    </th>
				    <td align="left">
							<c:out value="${inwDemand.detailExplanation}" escapeXml="false"></c:out>
							
				    </td></tr>
				
				    <tr><th>
				        <jecs:label  key="inwDemand.demandImage"/>
				    </th>
				    <td align="left">
				        
				        <img src="${FILE_URL }/${inwDemand.demandImage }" onclick="suonvtuShow('${FILE_URL }/${inwDemand.demandImage }');" width="150" height="150" style="border-color: green;border-width: 1px;cursor: pointer;" alt="<jecs:locale key='productimage.onclickshow'/>"/>
				    </td></tr>
				     <tr><th>
				        <jecs:label  key="inwDemand.postUserCode"/>
				    </th>
				     <td align="left">
				        ${inwDemand.postUserCode}
				    </td></tr>
				     <tr><th>
				        <jecs:label  key="inwDemand.postTime"/>
				    </th>
				     <td align="left">
				        ${inwDemand.postTime}
				    </td></tr>
				     <tr><th>
				        <jecs:label  key="inwDemand.reviewedUserCode"/>
				    </th>
				     <td align="left">
				        ${inwDemand.reviewedUserCode}
				    </td></tr>
				    <tr><th>
				        <jecs:label  key="inwDemand.reviewedTime"/>
				    </th>
				     <td align="left">
				        ${inwDemand.reviewedTime}
				    </td></tr>
    </c:if>

</table>
<!--/fieldset-->

<%-- <table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('InwDemand')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table> --%>
</form:form>

<script type="text/javascript">
    function suonvtuShow(suonvtu) {	
		document.getElementById("suonvtuFrame").innerHTML="<iframe src='"+suonvtu+"' width='600' align='middle' frameborder='0' height='400' scrolling='auto'></iframe>";
   		var width = (document.body.clientWidth - 728) / 2;
   		var suonvtuId = document.getElementById("suonvtuId");
   		suonvtuId.style.width=728;
		suonvtuId.style.left = width;    		
   		suonvtuId.style.display="";			    	
   	}
    
    function removeSuonvtu() {
		document.getElementById("suonvtuId").style.display = "none";
	}
   
</script>
