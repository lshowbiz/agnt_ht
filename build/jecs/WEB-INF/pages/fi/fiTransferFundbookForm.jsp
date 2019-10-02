<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@ include file="/common/taglibs.jsp"%>

<head>
    <meta name="heading" content="<fmt:message key='fiTransferFundbookDetail.heading'/>"/>
    
</head>
<style>
.div-t{
	width:100%;background-color: #f4f6fc;padding-left: 40px;padding-top: 15px;padding-bottom: 20px;
}
.ft-green {
	COLOR: #41ab01
}
</style>
<form:errors path="*" cssClass="alert alert-error fade in" element="div"/>

<form:form commandName="fiTransferFundbook" method="post" action="fiTransferFundbookform" cssClass="well form-horizontal"
           id="fiTransferFundbookForm" name="fiTransferFundbookForm" onsubmit="return validateOthers()">
<form:hidden path="transferUserCode" id="transferUserCode"/>
<form:hidden path="bankbookType" id="bankbookType"/>
<form:hidden path="transferType" id="transferType"/>
<c:if test="${not empty fiTransferFundbook.destinationUserCode}">
	<form:hidden path="destinationUserCode" id="destinationUserCode"/>
</c:if>
						
						
           <h2 class="title mgb20">
				<c:if test="${fiFundbookBalance.bankbookType eq '1'}">分红基金转账申请</c:if>
				<c:if test="${fiFundbookBalance.bankbookType eq '2'}">定向基金转账申请</c:if>
			</h2>
            <div class="content fr"> 
                <table width="100%" border="0" class="personalInfoTab">
                    <colgroup style="width:90px;"></colgroup>
                    <colgroup></colgroup>
                    <colgroup style="width:90px;"></colgroup>
                    <colgroup></colgroup>
                    <colgroup style="width:90px;"></colgroup>
                    <tbody>
                       
                       	<tr>
			                <td colspan="6">
			                	<div class="div-t">
			                	<c:if test="${fiFundbookBalance.bankbookType eq '1'}">分红基金帐户</c:if><c:if test="${fiFundbookBalance.bankbookType eq '2'}">定向基金帐户</c:if>：${fiFundbookBalance.userCode}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			                	帐户余额：<b class="ft-green">${fiFundbookBalance.balance}</b>元
			                	</div>
			                </td>
			            </tr>
			            <tr>
			            	<td colspan="6" height="5">&nbsp;</td>
			            </tr>
			            
			            <tr>
                            <td width="100"><label for="" class="star">目标帐户类型:</label></td>
                            <td>
                            	<c:if test="${not empty fiTransferFundbook.destinationUserCode}">自己的</c:if>
                            	<c:if test="${empty fiTransferFundbook.destinationUserCode}">他人的</c:if>
                            	<c:if test="${fiTransferFundbook.transferType eq '1'}">分红基金帐户</c:if>
                            	<c:if test="${fiTransferFundbook.transferType eq '2'}">定向基金帐户</c:if>
                            	<c:if test="${fiTransferFundbook.transferType eq '3'}">发展基金帐户(可用于订单支付)</c:if>
							</td>
                            <td></td>
                            
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                        
						<c:if test="${empty fiTransferFundbook.destinationUserCode}">
                        <tr>
                            <td width="100"><label for="" class="star">收款会员编号:</label></td>
                            <td><form:input path="destinationUserCode" id="destinationUserCode" cssClass="formerror"/>
                            </td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                        </c:if>
                        <tr>
                            <td><label for="" class="star">金额:</label></td>
                            <td><form:input path="money" id="money" cssClass="formerror"/></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td><label for="">&nbsp;&nbsp;摘要:</label></td>
                            <td>
                             <form:textarea path="notes" id="notes" rows="6" cols="50" htmlEscape="true"/>
                            </td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td><label for="" class="star">支付密码:</label></td>
                            <td><input type="password" name="paypassword" id="paypassword"  class="text medium"/></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td>
                            <table>
                            	<tr>
                            		<td align="left">
	                            <button type="submit" class="btn-primary btn_common ft14" name="save" onclick="bCancel=false">
					            	<i class="icon-ok icon-white"></i>&nbsp;确认提交&nbsp;
						        </button></td>
					        	<td>
						        
						    </td>
                            	</tr>
                            </table>
                             
					        </td>
							<td></td>
                            <td colspan="3"></td>
                        </tr>
                   </tbody>
                 </table>
              </div>
          <div class="clear"></div>
      </form:form>
<script type="text/javascript">

    var checkSubmitFlg = false;

    function validateOthers(){
  	
  		if (!checkSubmitFlg) {
  		
  			var destin_user=document.fiTransferFundbookForm.destinationUserCode;
	  		if(destin_user.value==""){
				alert("收款会员编号不能为空");
				destin_user.focus();
				return false;
			}
			
			if(${destOther} == "1"){

				var transfer_user=document.fiTransferFundbookForm.transferUserCode.value;
				alert("==\=="+transfer_user);
				if(destin_user.value==transfer_user){
					
					alert("收款会员编号不能为自己，请输入正确的收款会员编号！");
					destin_user.focus();
					return false;
				}
			}
			
			var paymoney=document.fiTransferFundbookForm.money;
			if(paymoney.value==""){
				alert("金额不能为空");
				paymoney.focus();
				return false;
			}
			if(paymoney.value <= 0){
				alert("金额必须大于0");
				paymoney.focus();
				return false;
			}
			var regu = "^[0-9]+[\.][0-9]{0,3}$"; 
			var re = new RegExp(regu); 
			
			var regu2 = "^[0-9]+$"; 
			var re2 = new RegExp(regu2); 
			if(re.test(paymoney.value)!=true && paymoney.value.search(re2) == -1){
				
				alert("金额不符合要求");
				paymoney.focus();
				return false;
			}
			
			if(parseFloat(paymoney.value) > parseFloat(${fiFundbookBalance.balance})){
				
				alert("对不起！余额不足！");
				paymoney.focus();
				return false;
			}
	
			if(document.fiTransferFundbookForm.paypassword.value==""){
				alert("支付密码不能为空");
				document.fiTransferFundbookForm.paypassword.focus();
				return false;
			}
			
			var strTitle;
			var strBankbookName;
			var disBankbookName;
			
			if(${fiTransferFundbook.bankbookType}=='1'){
					strBankbookName = "分红基金帐户";
			}
			if(${fiTransferFundbook.bankbookType}=='2'){
					strBankbookName = "定向基金帐户";
			}
			
			if(${fiTransferFundbook.transferType}=='1'){
					disBankbookName = "分红基金帐户";
			}
			if(${fiTransferFundbook.transferType}=='2'){
					disBankbookName = "定向基金帐户";
			}
			if(${fiTransferFundbook.transferType}=='3'){
					disBankbookName = "发展基金帐户";
			}
			
			if(${destOther} == "0"){
				
				strTitle = "您确定从"+strBankbookName+"转入"+paymoney.value+"到自己的"+disBankbookName;
			}
			if(${destOther} == "1"){
				strTitle = "您确定从"+strBankbookName+"转入"+paymoney.value+"到他人"+disBankbookName;
			}
			
			if(window.confirm(strTitle+"?")){
				
				checkSubmitFlg = true;
				//document.getElementById("save").disabled=true;
				return isFormPosted();
			}else{
			
				return false;	
			}
		}else {

			//重复提交
			alert("请勿重复提交!");
			return false;
		}
	}
</script>    

<v:javascript formName="fiTransferFundbook" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/scripts/datepicker/css/datepicker.css'/>" />
<script type="text/javascript" src="<c:url value='/scripts/datepicker/js/bootstrap-datepicker.js'/>"></script>
<c:if test="${pageContext.request.locale.language != 'en'}">
<script type="text/javascript" src="<c:url value='/scripts/datepicker/js/locales/bootstrap-datepicker.${pageContext.request.locale.language}.js'/>"></script>
</c:if>
<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['fiTransferFundbookForm']).focus();
        $('.input-append.date').datepicker({format: "<fmt:message key='calendar.format'/>", weekStart: "<fmt:message key='calendar.weekstart'/>", language: '${pageContext.request.locale.language}'});
        
    });
</script>
