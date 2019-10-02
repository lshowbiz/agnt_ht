<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="sunProductInfoDetail.title"/></title>
<content tag="heading"><jecs:locale key="sunProductInfoDetail.heading"/></content>
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 

<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script> 
<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="operation.button.save"/>" />
		</jecs:power>
		
</c:set>

<spring:bind path="sunProductInfo.*">
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

<form:form commandName="sunProductInfo" method="post" action="editSunProductInfo.html" onsubmit="return validateSunProductInfo(this)" id="sunProductInfoForm">

<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('SunProductInfo')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
<table class='detail' width="100%">

<form:hidden path="jpiId"/>

    <tr><th>
        <jecs:label  key="pmProduct.productName"/>
    </th>
    <td align="left">
        <!--form:errors path="productId" cssClass="fieldError"/-->
        <form:hidden path="jpmProductSale.uniNo" id="productId"/>
        <input type="text" name="productName" id="productName" value="${sunProductInfo.jpmProductSale.productName}" size="40"/>
        <input type="button" class="button" name="select" onclick="selectProduct()" value="<jecs:locale key="button.select"/>" />
        
    </td></tr>

    <tr><th>
        <jecs:label  key="pmProduct.discountPrice"/>
    </th>
    <td align="left">
        <!--form:errors path="discount" cssClass="fieldError"/-->
        <form:input path="discount" id="discount" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="customerFollow.createTime"/>
    </th>
    <td align="left">
        <!--form:errors path="beginDate" cssClass="fieldError"/-->
        <form:input path="beginDate" id="beginDate" readonly="true" cssClass="readonly text medium"/>
        	<img src="./images/calendar/calendar7.gif" id="img_startTime" style="cursor: pointer;" title="<jecs:locale key="Calendar.TT.SEL_DATE"/>"/> 
				<script type="text/javascript"> 
					Calendar.setup({
					inputField     :    "beginDate", 
					ifFormat       :    "%Y-%m-%d",  
					button         :    "img_startTime", 
					singleClick    :    true
					}); 
				</script>
    </td></tr>

    <tr><th>
        <jecs:label  key="customerFollow.endTime"/>
    </th>
    <td align="left">
        <!--form:errors path="endDate" cssClass="fieldError"/-->
        <form:input path="endDate" id="endDate" readonly="readonly" cssClass="readonly text medium"/>
        	<img src="./images/calendar/calendar7.gif" id="img_endTime" style="cursor: pointer;" title="<jecs:locale key="Calendar.TT.SEL_DATE"/>"/> 
				<script type="text/javascript"> 
					Calendar.setup({
					inputField     :    "endDate", 
					ifFormat       :    "%Y-%m-%d",  
					button         :    "img_endTime", 
					singleClick    :    true
					}); 
				</script>
    </td></tr>

</table>
<!--/fieldset-->

<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('SunProductInfo')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('sunProductInfoForm'));
    
    function selectProduct(){
    		//open("<c:url value='/pmProducts.html?strAction=selectProduct&selectControl=1'/>","","height=500, width=600, top=50, left=50, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no");
    		open("<c:url value='/jpmProductSales.html?strAction=selectProduct&selectControl=sun'/>","","height=500, width=600, top=50, left=50, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no");
    		
    	}
</script>

<v:javascript formName="sunProductInfo" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
