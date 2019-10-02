<%@ include file="/common/taglibs.jsp"%>
<title><jecs:locale key="inwDemandsortDetail.title"/></title>
<content tag="heading"><jecs:locale key="inwDemandsortDetail.heading"/></content>
<!--<c:set var="buttons">
	<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="operation.button.save"/>" />
	<input type="button" class="button" name="cancel" onclick="location.href='<c:url value="/inwDemandsorts.html"/>?strAction=queryInwDemandsort'" value="<jecs:locale key="operation.button.return"/>" />
</c:set>-->
<spring:bind path="inwDemandsort.*">
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

<c:if test ="${param.strAction=='addInwDemandsort'}">
<form:form action="editInwDemandsort.html?strAction=addInwDemandsort" method="post"  enctype="multipart/form-data" commandName="inwDemandsort"  onsubmit="return validateInwDemandsort(this)" id="inwDemandsortForm" name="inwDemandsortForm">
<input type="hidden" name="strAction" value="${param.strAction}"/>
<table class='detail' width="100%">
<form:hidden path="id"/>
    <tr><th>
        <jecs:label  key="inwDemandsort.name"/>
    </th>
    <td align="left">
        <form:input path="name" id="name" cssClass="text medium"/>
    </td></tr>
    <tr><th>
        <jecs:label  key="inwDemandsort.requireIntroduction"/>
    </th>
    <td align="left">
        <form:textarea path="requireIntroduction" id="requireIntroduction" cssClass="text medium" cssErrorClass="text medium error" cssStyle="width:80%;height:45px;margin-top:10px;resize:none;"/>
    </td></tr>
    <tr><th>
        <jecs:label  key="inwDemandsort.image"/>
    </th>
    
         <td align="left">
			        <c:choose>
					<c:when test="${inwDemandsort.image!=null}"> 
						<form:hidden path="image" />
						<img src="${FILE_URL }/${inwDemandsort.image }" onclick="suonvtuShow('${FILE_URL }/${inwDemandsort.image }');" width="150" height="150" style="border-color: green;border-width: 1px;cursor: pointer;" alt="<jecs:locale key='productimage.onclickshow'/>"/>
						<br/><input type="file" name="file2" id="file2"/><jecs:label key="jpmProductSaleImage.imageLink.Prompt" />
					 </c:when>
					<c:otherwise>
						<input type="file" name="file" id="file"/>
					</c:otherwise>
				</c:choose> 
			    </td>
    </tr>
</table>
</form:form>
</c:if>
<c:if test="${param.strAction=='updateInwDemandsort'}">
<form:form action="editInwDemandsort.html?strAction=updateInwDemandsort" method="post"  enctype="multipart/form-data" commandName="inwDemandsort"  onsubmit="return validateInwDemandsort(this)" id="inwDemandsortForm" name="inwDemandsortForm">
<input type="hidden" name="strAction" value="${param.strAction}"/>
<table class='detail' width="100%">
<form:hidden path="id"/>
    <tr><th>
        <jecs:label  key="inwDemandsort.name"/>
    </th>
    <td align="left">
        <form:input path="name" id="name" cssClass="text medium"/>
    </td></tr>
    <tr><th>
        <jecs:label  key="inwDemandsort.requireIntroduction"/>
    </th>
    <td align="left">
        <form:textarea path="requireIntroduction" id="requireIntroduction" cssClass="text medium" cssErrorClass="text medium error" cssStyle="width:80%;height:45px;margin-top:10px;resize:none;"/>
    </td></tr>
    <tr><th>
        <jecs:label  key="inwDemandsort.image"/>
    </th>
    
         <td align="left">
			        <c:choose>
					<c:when test="${inwDemandsort.image!=null}"> 
						<form:hidden path="image" />
						<img src="${FILE_URL }/${inwDemandsort.image }" onclick="suonvtuShow('${FILE_URL }/${inwDemandsort.image }');" width="150" height="150" style="border-color: green;border-width: 1px;cursor: pointer;" alt="<jecs:locale key='productimage.onclickshow'/>"/>
						<br/><input type="file" name="file2" id="file2"/><jecs:label key="jpmProductSaleImage.imageLink.Prompt" />
					 </c:when>
					<c:otherwise>
						<input type="file" name="file" id="file"/>
					</c:otherwise>
				</c:choose> 
			    </td>
    </tr>
</table>
</form:form>



</c:if>
<table width="100%">
       <td align="right"><input type="submit" class="button" name="save"  onclick="addOrUpdateInwDemandsort(document.inwDemandsortForm)" value="<jecs:locale key="operation.button.save"/>" />&nbsp;&nbsp;&nbsp;&nbsp;</td>
       <td align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" class="button" name="cancel" onclick="location.href='<c:url value="/inwDemandsorts.html"/>?strAction=queryInwDemandsort'" value="<jecs:locale key="operation.button.return"/>" /></td>


</table>

<script type="text/javascript">
    //window.onload = function inwDemandInit(){
    //      var strAction = "<%= request.getAttribute("strAction")%>";
    //     document.getElementById("strAction").value = strAction;
   // }
   
   
    function addOrUpdateInwDemandsort(theForm){
            theForm.submit();
    }
    
    
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

