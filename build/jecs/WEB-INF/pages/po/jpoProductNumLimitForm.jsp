<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jpoProductNumLimitDetail.title"/></title>
<content tag="heading"><jecs:locale key="jpoProductNumLimitDetail.heading"/></content>
<script type="text/javascript" src="<c:url value='/dwr/util.js'/>" ></script> 
<script type="text/javascript" src="<c:url value='/dwr/engine.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/dwr/interface/jpoProductNumLimitManager.js'/>" ></script>
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" />
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script>
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script>
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script>

<c:set var="buttons">
<c:if test='${empty jpoProductNumLimit.id}'>
		<jecs:power powerCode="${param.strAction}">
				<button type="button" class="btn btn_sele" name="save"  onclick="validForm();" ><jecs:locale key="button.save"/></button>
		</jecs:power>
		</c:if>
		
		<c:if test='${not empty jpoProductNumLimit.id}'>
		
		<jecs:power powerCode="${param.strAction}">
				<button type="button" class="btn btn_sele" name="save"  onclick="validForm();" ><jecs:locale key="button.save"/></button>
		</jecs:power>
		
		<jecs:power powerCode="deleteJpoProductNumLimit">
				<button type="submit" class="btn btn_sele" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'JpoProductNumLimit')"><jecs:locale key="operation.button.delete"/></button>
		</jecs:power>
		</c:if>
		
		<button type="button" class="btn btn_sele" name="back"  onclick="window.location.href='jpoProductNumLimits.html?needReload=1'"><jecs:locale key="operation.button.return"/></button>
</c:set> 


<spring:bind path="jpoProductNumLimit.*">
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

<form:form commandName="jpoProductNumLimit" method="post" action="editJpoProductNumLimit.html" onsubmit="return onSubmitCheck(this)" id="jpoProductNumLimitForm">

<input type="hidden" name="strAction" value="${param.strAction }"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JpoProductNumLimit')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
<table class='detail' width="70%">
<tbody class="window" >
<form:hidden path="id"/>

    <tr class="edit_tr">
		<th><span class="text-font-style-tc"><jecs:label  key="jpmSalePromoter.presentno"  required="true"/></span></th>
		<td>
			<!--form:errors path="productNo" cssClass="fieldError"/-->
			<span class="textbox">
			<form:input path="productNo" id="productNo" cssClass="textbox-text" onchange="searchPros();"/>
			</span>
			<div id="divNo" style="display:inline;"></div>
		</td>
		<th><span class="text-font-style-tc"><jecs:label  key="jpmProduct.productName"/></span></th>
		<td>
			<!--form:errors path="productName" cssClass="fieldError"/-->
			<span class="textbox"><form:input path="productName" id="productName" cssClass="textbox-text"/></span>
		</td>
	</tr>

   
    <tr class="edit_tr">
	<th><span class="text-font-style-tc"><jecs:label  key="jpmWineSettingInf.productQty"  required="true"/></span></th>
    <td>
        <!--form:errors path="num" cssClass="fieldError"/-->
        <span class="textbox"><form:input path="num" id="num" cssClass="textbox-text" onchange="regNum();"/></span>
        <div id="divNum" style="display:inline;"></div>
    </td>
	<th><span class="text-font-style-tc"><jecs:label  key="jpmSalePromoter.startime"/></span></th>
	    <td align="left">
			<span class="textbox">
	    	<input type="text" name="startime" id="startime" class="textbox-text"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})" 
    	  		value="<fmt:formatDate value="${jpoProductNumLimit.startime }" pattern="yyyy-MM-dd HH:mm:ss"/>"/></span>
	        
	    </td>
	</tr>

    <tr class="edit_tr">
	<th><span class="text-font-style-tc"><jecs:label  key="jpmSalePromoter.endtime"/></span></th>
	    <td align="left">
	        <!--form:errors path="endtime" cssClass="fieldError"/-->
			<span class="textbox">
	        <input type="text" name="endtime" id="endtime" class="textbox-text"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})" 
    		value="<fmt:formatDate value="${jpoProductNumLimit.endtime }" pattern="yyyy-MM-dd HH:mm:ss"/>"/></span>
	       
	    </td>
    </tr>
    <tr>		
	    <td class="command" colspan="4" align="center">
	    	<c:out value="${buttons}" escapeXml="false"/>
	    </td>
    </tr>
</tbody>
</table>
<!--/fieldset-->

<%-- <table class='detail' width='100%'>
    <tr>
    <th></th><Td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JpoProductNumLimit')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table> --%>
</form:form>
<script type="text/javascript">

function strDateTime(str)
{
	var reg = /^(\d{4})-(\d{2})-(\d{2}) (\d{2}):(\d{2}):(\d{2})$/
	var r = str.match(reg);
	if(r==null)return false; 
	var d = new Date(r[1],r[2]-1,r[3],r[4],r[5],r[6]);  
	return (d.getFullYear()==r[1]&&(d.getMonth()+1)==r[2]&&d.getDate()==r[3]&&d.getHours()==r[4]&&d.getMinutes()==r[5]&&d.getSeconds()==r[6]);
}

function validForm(){
	var proNo = document.getElementById('productNo').value;
	var num = document.getElementById('num').value;
	var stime = document.getElementById('startime').value;
	if(proNo =="" || num=="" || stime==""){
		alert("<jecs:locale key='productNo.num.isrequid'/>");
		return;
	}
	
	var str = document.getElementById("divNo").innerHTML;
	if(str != ""){
		alert(str);
		return;
	}
	
	var num = document.getElementById("divNum").innerHTML;
	if(num != ""){
		alert(num);
		return;
	}
	var stime = document.getElementById('startime').value;
	
	if(stime==null || stime==''){
		alert("开始时间不能为空！");
		return;
	}
	document.forms["jpoProductNumLimitForm"].submit();
}

function del() {   
	var id = document.getElementById("id").value;
	if(confirm ('<jecs:locale key="amMessage.confirmdelete"/>')){ 
		window.location.href="${ctx}/editJpoProductNumLimit.html?strAction=deleteJpoProductNumLimit&id="+id;
	}
}

function searchPros(){       
	var proNo = document.getElementById('productNo').value;
	jpoProductNumLimitManager.getByProductNo(proNo,rowback);
}
function rowback(valid){
	if(valid !=null){
		document.getElementById("divNo").innerHTML="<jecs:locale key='productNo.isExtist'/>";
	}else{
		document.getElementById("divNo").innerHTML="";
		var proNo = document.getElementById('productNo').value; 
		if(proNo.length >= 10){
		}else{
			document.getElementById("divNo").innerHTML="<jecs:locale key='productNo.error'/>";
		}
	}
}


function onSubmitCheck(){
	if(validateJpoMemberOrder(formId)){
		if(isFormPosted()){
			return true;
		}else{
		return false;
		}
	}else{
		return false;
	}
}

function regNum(){
	document.getElementById("divNum").innerHTML="";
	
	 var reg = new RegExp("^[0-9]*[1-9][0-9]*$");  
	   var num = document.getElementById('num').value;
	   if(''==num || num==null || !reg.test(num)){  
		   document.getElementById("divNum").innerHTML="<jecs:locale key='nummt0'/>";
	   	}
}
</script>
<script type="text/javascript">
    Form.focusFirstElement($('jpoProductNumLimitForm'));
</script>

<v:javascript formName="jpoProductNumLimit" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
