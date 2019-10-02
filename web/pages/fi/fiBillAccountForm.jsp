<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=utf-8" language="java"%>
<title><jecs:locale key="fiBillAccountDetail.title" />
</title>

<content tag="heading">
<jecs:locale key="fiBillAccountDetail.heading" />
</content>



<spring:bind path="fiBillAccount.*">
	<c:if test="${not empty status.errorMessages}">
		<div class="error">
			<c:forEach var="error" items="${status.errorMessages}">
				<img src="<c:url value="/images/iconWarning.gif"/>"
					alt="<jecs:locale key="icon.warning"/>" class="icon" />
				<c:out value="${error}" escapeXml="false" />
				<br />
			</c:forEach>
		</div>
	</c:if>
</spring:bind>

<form:form commandName="fiBillAccount" method="post"
	action="editFiBillAccount.html" onsubmit="return validateOthers(this)"
	id="fiBillAccountForm">

	<input type="hidden" name="strAction" value="${param.strAction }" />

	<table class='detail' width="70%">
		<form:hidden path="accountId" />
		<tbody class="window">
			<tr class="edit_tr">
				<th>
					<span class="text-font-style-tc">
						<span class="req">*</span>适用类型:
					</span>
				</th>
				<td>
					<span class="textbox">
						<select id="accountType" name="accountType" class="textbox-text">
							<option value="1" selected="selected">
								PC
							</option>
							<!--			<option value="2">移动终端</option>-->
						</select>
					</span>
				</td>
				<th>
					<span class="text-font-style-tc">
						<span class="req">*</span>平台类型:
					</span>
				</th>
				<td>
					<span class="textbox">
						<jecs:list name="providerType" listCode="paycompany"
						value="${fiBillAccount.providerType}" defaultValue=""
						showBlankLine="false"  styleClass="textbox-text"/>
					</span>
				</td>
			</tr>
			<tr class="edit_tr">
				<th>
					<span class="text-font-style-tc">
						<span class="req">*</span>商户号:
					</span>
				</th>
				<td>
					<span class="textbox">
						<form:input path="billAccountCode" id="billAccountCode"
						cssClass="textbox-text"
						disabled="${strAction=='editFiBillAccount' ? 'true' : 'false'}" />
					</span>
				</td>
				<th>
					<span class="text-font-style-tc">
						<span class="req">*</span>最大限额:
					</span>
				</th>
				<td>
					<span class="textbox">
						<form:input path="maxMoney" id="maxMoney" cssClass="textbox-text"
							onkeypress="if(!this.value.match(/^[\+\-]?\d*?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\d+)?)?$/))this.o_value=this.value"
							onkeyup="if(!this.value.match(/^[\+\-]?\d*?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\d+)?)?$/))this.o_value=this.value"
							maxlength="12" />
						<input type="hidden" name="oldMaxMoney"
							value="${fiBillAccount.maxMoney }" />
					</span>
				</td>
			</tr>
			<tr class="edit_tr">
				<th>
					<span class="text-font-style-tc">
						<span class="req">*</span>经销商编号:
					</span>
				</th>
				<td>
					<span class="textbox">
						<form:input path="userCode" id="userCode" cssClass="textbox-text" />
					</span>
				</td>
				<th>
					<span class="text-font-style-tc">
						<span class="req">*</span>注册名称:
					</span>
				</th>
				<td>
					<span class="textbox">
						<form:input path="accountName" id="accountName"
						cssClass="textbox-text" />
					</span>
				</td>
			</tr>
			<tr class="edit_tr">
				<th>
					<span class="text-font-style-tc">
						<jecs:label key="fiBillAccount.registerEmail" />
					</span>
				</th>
				<td>
					<span class="textbox">
						<form:input path="registerEmail" id="registerEmail"
						cssClass="textbox-text" />
					</span>
				</td>
				<th>
					<span class="text-font-style-tc">
						联系方式:
					</span>
				</th>
				<td>
					<span class="textbox">
						<form:input path="linkNum" id="linkNum" cssClass="textbox-text" />
					</span>
				</td>
			</tr>
			<tr class="edit_tr">
				<th>
					<span class="text-font-style-tc">
						<jecs:label key="fiBillAccount.status" />
					</span>
				</th>
				<td>
					<span class="textbox">
					<c:if test="${empty fiBillAccount.accountId}">
							<label>
								<input name="status" id="status" type="radio" value="0"
									checked="checked" />
								启用
							</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<label>
								<input name="status" id="status" type="radio" value="1" />
								停用
							</label>
						</c:if>
						<c:if test="${not empty fiBillAccount.accountId}">
		
							<label>
								<input name="status" id="status" type="radio" value="0"
									<c:if test='${fiBillAccount.status==0}'>checked='checked'</c:if> />
								启用
							</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<label>
								<input name="status" id="status" type="radio" value="1"
									<c:if test='${fiBillAccount.status==1}'>checked='checked'</c:if> />
								停用
							</label>
						</c:if>
						</span>
				</td>
				<th>
					<span class="text-font-style-tc">
						<jecs:label key="fiBillAccount.remark" />
					</span>
				</th>
				<td>
					<span class="textbox">
						<form:input path="remark" id="remark" cssClass="textbox-text" />
					</span>
				</td>
			</tr>
			<c:if test="${not empty fiBillAccount.accountId}">
				<tr class="edit_tr">
					<th>
						<span class="text-font-style-tc">
							<jecs:label key="fiBillAccount.createTime" />
						</span>
					</th>
					<td>
						<span class="textbox">
						 	<form:input path="createTime" id="createTime"
								cssClass="textbox-text" disabled="true" />
						</span>
					</td>
					<th>
						<span class="text-font-style-tc">
							<jecs:label key="fiBillAccount.createUserName" />
						</span>
					</th>
					<td>
						<span class="textbox">
						 	<form:input path="createUserName" id="createUserName"
								cssClass="textbox-text" disabled="true" />
						</span>
					</td>		
				</tr>
			</c:if>
			<tr>
				<td class="command" colspan="4" align="center">
					<input type="submit" class="btn btn_sele" name="save"
						onclick="bCancel=false"
						value="<jecs:locale key="operation.button.save"/>" />
	
					<c:if test="${not empty fiBillAccount.accountId}">
						<!-- 
	        <jecs:power powerCode="deleteFiBillAccount">
	        	<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'FiBillAccount')" value="<jecs:locale key="operation.button.delete"/>" />
			</jecs:power>
			 -->
						<input type="button" class="btn btn_sele" name="cancel"
							onclick="toQueryList();"
							value="<fmt:message key="operation.button.cancel"/>" />
					</c:if>
					<c:if test="${empty fiBillAccount.accountId}">
						<input type="button" class="btn btn_sele" name="cancel"
							onclick="history.back();"
							value="<fmt:message key="operation.button.cancel"/>" />
					</c:if>
				</td>
			</tr>
		</tbody>
	</table>

</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('fiBillAccountForm'));
    var $merchantType = document.getElementById("merchantType");
	$merchantType.onchange=function(){
		var _val = this.value;
		$zsmm = document.getElementById("zsmm");
		$trqmy = document.getElementById("trqmy");
		$trhmy = document.getElementById("trhmy");
		if(_val==1){
			$zsmm.style.display="none";
			$trqmy.style.display="table-row";
			$trhmy.style.display="table-row";
		}else{
			$zsmm.style.display="table-row"; 
			$trqmy.style.display="none";
			$trhmy.style.display="none";
		}

	}
	$merchantType.onchange();
   
    function toQueryList(){
    	
    	window.location="fiBillAccounts.html";
    }
    if(${strAction=='editFiBillAccount'}){
    	document.getElementById('maxMoney').disabled=true;
	}
    
    function validateOthers(theForm){
    	
		if(theForm.elements['billAccountCode'].value==""){
			alert("商户号不能为空!");
			theForm.elements['billAccountCode'].focus();
			return false;
		}
		
		if(theForm.elements['providerType'].value==""){
			alert("平台类型不能为空!");
			theForm.elements['billAccountCode'].focus();
			return false;
		}
		
		if(theForm.elements['maxMoney'].value==""){
			alert("最大限额值不能为空!");
			theForm.elements['maxMoney'].focus();
			return false;
		}
		
		if(theForm.elements['userCode'].value==""){
			alert("经销商编号不能为空!");
			theForm.elements['userCode'].focus();
			return false;
		}

		if(theForm.elements['accountName'].value==""){
			alert("注册名称不能为空!");
			theForm.elements['accountName'].focus();
			return false;
		}
	}
</script>

<v:javascript formName="fiBillAccount" cdata="false"
	dynamicJavascript="true" staticJavascript="false" />
<script type="text/javascript"
	src="<c:url value="/scripts/validator.jsp"/>"></script>
