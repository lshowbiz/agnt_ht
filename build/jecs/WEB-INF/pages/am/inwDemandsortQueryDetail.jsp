<%@ include file="/common/taglibs.jsp"%>
<title><jecs:locale key="inwDemandsortDetail.title"/></title>
<content tag="heading"><jecs:locale key="inwDemandsortDetail.heading"/></content>
<c:set var="buttons">
	<input type="button" class="button" name="cancel" onclick="location.href='<c:url value="/inwDemandsorts.html"/>?strAction=queryInwDemandsort'" value="<jecs:locale key="operation.button.return"/>" />
</c:set>
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
<form:form commandName="inwDemandsort" method="post" action="editInwDemandsort.html" enctype="multipart/form-data" onsubmit="return validateInwDemandsort(this)" id="inwDemandsortForm">
<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
<input type="hidden" name="strAction" value="${param.strAction }"/>
<table class='detail' width="100%">
<form:hidden path="id"/>
    <tr><th>
        <jecs:label  key="inwDemandsort.name"/>
    </th>
    <td align="left">
        ${inwDemandsort.name }
    </td></tr>
    <tr><th>
        <jecs:label  key="inwDemandsort.requireIntroduction"/>
    </th>
    <td align="left">
        <form:textarea path="requireIntroduction" id="requireIntroduction" cssClass="text medium" cssErrorClass="text medium error" rows="10" cols="60" readonly="true"/>
    </td></tr>
    <tr><th>
        <jecs:label  key="inwDemandsort.image"/>
    </th>
         <td align="left">
			        <c:choose>
					<c:when test="${inwDemandsort.image!=null}"> 
						<form:hidden path="image" />
						<img src="${FILE_URL }/${inwDemandsort.image }" onclick="suonvtuShow('${FILE_URL }/${inwDemandsort.image }');" width="150" height="150" style="border-color: green;border-width: 1px;cursor: pointer;" alt="<jecs:locale key='productimage.onclickshow'/>"/>
						<br/>
					 </c:when>
					<c:otherwise>
						<input type="file" name="file" id="file"/>
					</c:otherwise>
				</c:choose> 
			    </td>
    </tr>
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
   
</script>

<v:javascript formName="inwDemandsort" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
