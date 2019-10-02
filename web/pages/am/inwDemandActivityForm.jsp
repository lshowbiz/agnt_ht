<%@ include file="/common/taglibs.jsp"%>

<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
<title><jecs:locale key="inwDemandDetail.title"/></title>
<content tag="heading"><jecs:locale key="inwDemandDetail.heading"/></content>

<c:set var="buttons">

<c:if test="${param.strAction != 'browserInwDemandActivity'}">
		<jecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save"  onclick="saveOrUpdate(document.inwDemandFormActivity)" value="<jecs:locale key="operation.button.save"/>" />
		</jecs:power>
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

<form:form  commandName="inwDemand" method="post" action="editInwDemandActivity.html?strAction=addInwDemandActivity"  enctype="multipart/form-data"  id="inwDemandFormActivity"  name="inwDemandFormActivity">
	
<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
<input type="hidden" name="strAction" id="strAction" value="${param.strAction }"/>

<table class='detail' width="100%">

<form:hidden path="id"/>
    

    <c:if test="${param.strAction != 'browserInwDemandActivity' }">
            <tr>
                 <th><jecs:label key="inwDemand.demandsortId"/></th>
                  <td>
                     <form:select path="demandsortId" id="demandsortId">
                        <form:option label="" value=""></form:option>
                        <form:options items="${demandsortList}"  itemValue="id" itemLabel="name"/>
                    </form:select> 
                 </td>        
            </tr>
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
			    <td align="left" >
			        <form:textarea path="summary" id="summary" cssClass="text medium"  cssErrorClass="text medium error" cssStyle="width:80%;height:45px;margin-top:10px;resize:none;"/>
			    </td></tr>
			     <tr><th width="20%">
			        <jecs:label  key="inwDemand.detailExplanation"/>
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
						<img src="${FILE_URL}/${inwDemand.demandImage }" onclick="suonvtuShow('${FILE_URL }/${inwDemand.demandImage }');" width="150" height="150" style="border-color: green;border-width: 1px;cursor: pointer;" alt="<jecs:locale key='productimage.onclickshow'/>"/>
						<br/><input type="file" name="file2" id="file2"/><jecs:label key="jpmProductSaleImage.imageLink.Prompt" />
					 </c:when>
					<c:otherwise>
						<input type="file" name="file" id="file"/>
					</c:otherwise>
				</c:choose> 
			    </td></tr>		   
		</c:if>
		<c:if test="${param.strAction == 'browserInwDemandActivity' }">
		<tr>
                 <th><jecs:label key="inwDemand.demandsortId"/></th>
                  <td>
                     <form:select path="demandsortId" id="demandsortId">
                        <form:option label="" value=""></form:option>
                        <form:options items="${demandsortList}"  itemValue="id" itemLabel="name"/>
                    </form:select> 
                 </td>        
            </tr>
		
		
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
				        <jecs:label  key="inwDemand.detailExplanation"/>
				    </th>
				    <td align="left">
				        <c:choose>
				           <c:when test="true">
							<c:out value="${inwDemand.detailExplanation}" escapeXml="false"></c:out>
				           </c:when>
				        </c:choose>
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
   	
   	function saveOrUpdate(theForm){
   	      theForm.submit();
   	}
   	
   	function returnOld(){
   	     window.location.href = "inwDemandsActivity.html?strAction=editDemandsActivity";
   	}
</script>