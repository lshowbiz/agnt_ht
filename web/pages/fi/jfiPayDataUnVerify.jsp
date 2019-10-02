<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="fiPayDataDetail.title"/></title>
<content tag="heading"><jecs:locale key="fiPayDataDetail.heading"/></content>

<form:form commandName="jfiPayData" method="post" action="unVerifyJfiPayData.html" onsubmit="return validateOthers(this)" id="jfiPayDataForm">
<input type="hidden" name="strAction" value="unVerifyFiPayData"/>

<div id="titleBar">

<jecs:power powerCode="unVerifyJfiPayData">
	<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.not.verified"/>" />
</jecs:power>

<input type="button" class="button" name="cancel" onclick="window.location='jfiPayDatas.html?strAction=listFiPayDatas&needReload=1';" value="<jecs:locale key="operation.button.cancel"/>" />
</div>

<table class='detail' width="100%">
<form:hidden path="dataId"/>
    <tr><th>
        <jecs:label  key="fiPayData.accountCode"/>
    </th>
    <td align="left">
        ${jfiPayData.accountCode }
    </td></tr>

    <tr><th>
        <jecs:label  key="comm.busi.deal.transaction.date"/>
    </th>
    <td align="left">
        ${jfiPayData.dealDate }
    </td></tr>

    <tr><th>
    	<jecs:label key="fiPayData.incomeMoney"/>
    </th>
    <td align="left">
        ${jfiPayData.incomeMoney }
    </td></tr>

    <tr><th>
        <jecs:label  key="fiPayData.excerpt"/>
    </th>
    <td align="left">
        ${jfiPayData.excerpt }
    </td></tr>
    
    <tr><th>
        <jecs:label  key="fiPayData.adviceCode"/>
    </th>
    <td align="left">
        ${jfiPayData.adviceCode }
    </td></tr>
    
    <tr><th>
        <jecs:label  key="label.member.or.agent.code"/>
    </th>
    <td align="left">
        ${jfiPayData.sysUser.userCode }
    </td></tr>
    
    <tr><th>
        <jecs:label  key="fiBankbookTemp.status"/>
    </th>
    <td align="left">
        <jecs:code listCode="fiagentpayadvice.status" value="${jfiPayData.status}"/> - ${jfiPayData.checkTime } - ${jfiPayData.checkerName }
    </td></tr>
    
    <tr><th>
        <jecs:label  key="busi.common.remark"/>
    </th>
    <td align="left">
        ${jfiPayData.remark }
    </td></tr>
    
    <tr><th>
        <jecs:label key="fiPayData.cancelRemark.red"/>
    </th>
    <td align="left">
        <textarea name="cancelRemark" rows="6" cols="50"></textarea>
    </td></tr>
</table>
</form:form>

<script type="text/javascript">
Form.focusFirstElement($('jfiPayDataForm'));

function validateOthers(theForm){
	if(theForm.cancelRemark.value==""){
		alert("<jecs:locale key="fiPayData.cancelRemark.required"/>");
		theForm.cancelRemark.focus();
		return false;
	}
	if( confirm("<jecs:locale key="fiPayData.confirm.unVerfied"/>")){
		return isFormPosted();
	}
}
</script>