<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jfiMoneyDetail.title"/></title>
<content tag="heading"><jecs:locale key="jfiMoneyDetail.heading"/></content>
<c:set var="buttons">
	<input type="submit" class="button" name="save"  onclick="bCancel1=true" value="<jecs:locale key="button.save"/>" />
	
	<input type="button" class="button" name="cancel" onclick="history.back();" value="<jecs:locale key="operation.button.cancel"/>" />
</c:set>
<script language="javascript">
var bCancel1 = true;
function checkForm(){
if(bCancel1==true){
	var amount1 = document.getElementById("amount1").value;
	var amount2 = document.getElementById("amount2").value;
	var amount3 = document.getElementById("amount3").value;
	
	var extReg1 = /^(0|[1-9]\d*)?$/;
	if(amount1 ==""){
		alert('<jecs:locale key="errors.required" args="busi.order.amount" argTransFlag="true"/>');
		return false;
	}
	if(!amount1.match(extReg1)){
		alert('<jecs:locale key="errors.invalid" args="busi.order.amount" argTransFlag="true"/>');
		return false;
	}
	var extReg2 = /^(0|[1-9])?$/;
	if(amount2 =="" || amount3 ==""){
		alert('<jecs:locale key="errors.required" args="busi.order.amount" argTransFlag="true"/>');
		return false;
	}
	if(!amount2.match(extReg2) || !amount3.match(extReg2)){
		alert('<jecs:locale key="errors.invalid" args="busi.order.amount" argTransFlag="true"/>');
		return false;
	}
	if(Number(amount1) + Number(amount2 / 10) + Number(amount3 / 100)<=3){
		alert('<jecs:locale key="errors.invalid" args="busi.order.amount" argTransFlag="true"/>');
		return false;
	}
	if(document.getElementById("userCode").value ==""){
		alert('<jecs:locale key="errors.required" args="miMember.memberNo" argTransFlag="true"/>');
		return false;
	}
}
    <%--<c:if test="${sessionLogin.userType!='C' }">
	if(document.getElementById("password").value ==""){
		alert('<jecs:locale key="errors.required" args="register.passwordInfo2" argTransFlag="true"/>');
		return false;
	}
	</c:if>--%>
	return isFormPosted();
}
</script>
<form id="jfiMoney" name="jfiMoney" method="post" action="" onsubmit="return checkForm()">

<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>

<table class='detail' width="100%">
	<input type="hidden" name="time" id="time" value="${time }"/>
    <c:if test="${sessionLogin.userType=='C' }">
    <tr><th>
        <jecs:title  key="miMember.memberNo" required="true"/>
    </th>
    <td align="left">
        <input name="userCode" id="userCode" type="text" value=""/>
    </td></tr>
	</c:if>
    <c:if test="${sessionLogin.userType!='C' }">
	<input type="hidden" name="userCode" id="userCode" value="${sessionLogin.userCode }"/>
    <tr><th>
        <jecs:title  key="miMember.pwd2" required="true"/>
    </th>
    <td align="left">
        <input name="password" id="password" type="password" value=""/>
    </td></tr>
	</c:if>
    <input type="hidden" name="amountall" id="amountall" value=""/>
    <tr><th>
		<input type="submit" class="button" name="saveall"  onclick="document.getElementById('amountall').value='${amountall }';bCancel1=false;" value="<jecs:locale key="button.save.all"/>" />
        <jecs:title  key="busi.order.amount" required="true"/>
    </th>
    <td align="left">
        <input name="amount1" id="amount1" type="text" value="0"/>.
        <input name="amount2" id="amount2" type="text" value="0" size="2" maxlength="1"/>
        <input name="amount3" id="amount3" type="text" value="0" size="2" maxlength="1"/>
    </td></tr>
</table>

</form>