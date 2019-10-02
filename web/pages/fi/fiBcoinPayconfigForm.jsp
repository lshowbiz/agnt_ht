<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="fiBcoinPayconfigDetail.title"/></title>
<content tag="heading"><jecs:locale key="fiBcoinPayconfigDetail.heading"/></content>
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script> 

<spring:bind path="fiBcoinPayconfig.*">
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

<form:form commandName="fiBcoinPayconfig" method="post" action="editFiBcoinPayconfig.html" onsubmit="return validateFiBcoinPayconfig(this)" id="fiBcoinPayconfigForm">

<input type="hidden" name="strAction" value="${param.strAction }"/>

<table class='detail' width="100%">

<form:hidden path="configId"/>
	
	<tr><th>
        <jecs:label  key="fiBcoinPayconfig.title"/>
    </th>
    <td align="left">
        <!--form:errors path="title" cssClass="fieldError"/-->
        <form:input path="title" id="title" cssClass="text medium"/>
    </td></tr>
    
    <tr><th>
        <jecs:label  key="fiBcoinPayconfig.startTime"/>
    </th>
    <td align="left">
        <!--form:errors path="startTime" cssClass="fieldError"/-->
        <form:input path="startTime" id="startTime" cssClass="text medium"/>
        <img src="./images/calendar/calendar7.gif" id="img_startDate" style="cursor: pointer;" title="<jecs:locale key="Calendar.TT.SEL_DATE"/>"/> 
		<script type="text/javascript"> 
			Calendar.setup({
			inputField     :    "startTime", 
			ifFormat       :    "%Y-%m-%d %H:%M:00",  
			button         :    "img_startDate", 
			showsTime	   :	true,
			singleClick    :    true
			}); 
		</script>
    </td></tr>

    <tr><th>
        <jecs:label  key="fiBcoinPayconfig.endTime"/>
    </th>
    <td align="left">
        <!--form:errors path="endTime" cssClass="fieldError"/-->
        <form:input path="endTime" id="endTime" cssClass="text medium"/>
        <img src="./images/calendar/calendar7.gif" id="img_endDate" style="cursor: pointer;" title="<jecs:locale key="Calendar.TT.SEL_DATE"/>"/> 
		<script type="text/javascript"> 
			Calendar.setup({
			inputField     :    "endTime", 
			ifFormat       :    "%Y-%m-%d %H:%M:00",  
			button         :    "img_endDate", 
			showsTime	   :	true,
			singleClick    :    true
			}); 
		</script>
    </td></tr>
    
    <tr><th>
        <jecs:label  key="fiBcoinPayconfig.limit"/>
    </th>
    <td align="left">
        <!--form:errors path="limit" cssClass="fieldError"/-->
        <jecs:list listCode="fibcoinpayconfig.limit" name="limit" id="limit" value="${fiBcoinPayconfig.limit}" defaultValue="0"/>
        
    </td></tr>

    <tr><th>
        <jecs:label  key="fiBcoinPayconfig.proportion"/>
    </th>
    <td align="left">
        <!--form:errors path="proportion" cssClass="fieldError"/-->
        <jecs:list listCode="fibcoinpayconfig.proportion" name="proportion" id="proportion" value="${fiBcoinPayconfig.proportion}" defaultValue="1"/>

    </td></tr>
    
    <tr><th>
        <jecs:label key="sysOperationLog.moduleName"/>
    </th>
    <td align="left">
        
        
        <input type="submit" class="button" name="save"	onclick="bCancel=false" value="<jecs:locale key="operation.button.save"/>" />
        
        <c:if test="${not empty fiBcoinPayconfig.configId}">
        	<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'FiBcoinPayconfig')" value="<jecs:locale key="operation.button.delete"/>" />
			<input type="button" class="button" name="cancel" onclick="toQueryList();" value="<fmt:message key="operation.button.cancel"/>" />
		</c:if>
		<c:if test="${empty fiBcoinPayconfig.configId}">
			<input type="button" class="button" name="cancel" onclick="history.back();" value="<fmt:message key="operation.button.cancel"/>" />
		</c:if>
		
    </td></tr>
		
</table>

</form:form>

<c:if test="${not empty fiBcoinPayconfig.configId}">
<input type="button" class="button" style="margin-right: 5px" onclick="location.href='<c:url value="/editFiBcoinPayconfigDetail.html"/>?strAction=addFiBcoinPayconfigDetail&flag=1&configId=${fiBcoinPayconfig.configId }'" value="<jecs:locale key="button.add"/>"/>
<input type="button" class="button" style="margin-right: 5px" onclick="location.href='<c:url value="/editFiBcoinPayconfigDetail.html"/>?strAction=addFiBcoinPayconfigDetail&flag=2&configId=${fiBcoinPayconfig.configId }'" value="<jecs:locale key="button.import"/>"/>
				        
<table class='detail' width="100%">
<tr  style="background-color:#E6E6E6;font-size: 9pt;vertical-align: top;">
	<td><jecs:locale  key="busi.product.productno"/></td>
	
	<td><jecs:locale  key="fiBcoinPayconfigDetail.nowQuantity"/></td>
	<td><jecs:locale  key="fiBcoinPayconfigDetail.sumQuantity"/></td>
	<td><jecs:locale  key="sysOperationLog.moduleName"/></td>
</tr>

<c:forEach  items="${fiBcoinPayconfigDetailList}"  var="fiBcoinPayconfigDetail"  varStatus='status'>
          
	<tr>
		<td>${fiBcoinPayconfigDetail.productNo}</td>
		<td>${fiBcoinPayconfigDetail.nowQuantity}</td>
		<td>${fiBcoinPayconfigDetail.sumQuantity}</td>
		<td>
			<img src="<c:url value='/images/icons/editIcon.gif'/>" onclick="javascript:editProduct('${fiBcoinPayconfigDetail.detailId}')" style="cursor: pointer;" title="<jecs:locale key="button.delete"/>"/> 
			&nbsp;
			<img src="<c:url value='/images/icons/w.gif'/>" onclick="javascript:delProduct('${fiBcoinPayconfigDetail.detailId}')" style="cursor: pointer;" title="<jecs:locale key="button.delete"/>"/> 
		</td>
	</tr>     
</c:forEach>
</table>
</c:if>

<script type="text/javascript">
    Form.focusFirstElement($('fiBcoinPayconfigForm'));
    
    function editProduct(detailId){
    	
    	window.location="editFiBcoinPayconfigDetail.html?strAction=addFiBcoinPayconfigDetail&flag=1&detailId="+detailId+"&configId="+${fiBcoinPayconfig.configId};
    }
    
    function delProduct(detailId){
    	
    	window.location="editFiBcoinPayconfig.html?strAction=deleteFiBcoinPayconfigDetail&detailId="+detailId+"&configId="+${fiBcoinPayconfig.configId};
    }
    
    function toQueryList(){
    	
    	window.location="fiBcoinPayconfigs.html";
    }
</script>

<v:javascript formName="fiBcoinPayconfig" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
