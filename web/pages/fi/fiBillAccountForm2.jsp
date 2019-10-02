<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=utf-8" language="java"%>
<title><jecs:locale key="fiBillAccountDetail.title" />
</title>
<content tag="heading">
<jecs:locale key="fiBillAccountDetail.heading" /></content>



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
	action="editBusiness.html?strReq=save" 
	enctype="multipart/form-data" 
	onsubmit="return validateOthers(this)"
	id="fiBillAccountForm" name="fiBillAccountForm">

	<input type="hidden" name="strReq" value="${param.strReq }" />

	<table class='detail' width="100%">

		<form:hidden path="accountId" />

		<tr>
			<th><span class="req">*</span>适用类型：</th>
			<td align="left"><select id="accountType" name="accountType">
					<option value="1" selected="selected">PC</option>
					<!--			<option value="2">移动终端</option>-->
			</select></td>

			<th><span class="req">*</span>平台类型：</th>
			<td align="left"><jecs:list name="providerType"
					listCode="paycompany" value="${fiBillAccount.providerType}"
					defaultValue="" showBlankLine="false" /></td>
		</tr>

		<tr>
			<th><span class="req">*</span>商户号：</th>
			<td align="left">
				<!--form:errors path="billAccountCode" cssClass="fieldError"/--> <form:input
					path="billAccountCode" id="billAccountCode" cssClass="text medium"
					disabled="${strAction=='editFiBillAccount' ? 'true' : 'false'}" />

			</td>
		
			<th><span class="req">*</span>最大限额：</th>
			<td align="left">
				<!--form:errors path="createUserName" cssClass="fieldError"/--> <form:input
					path="maxMoney" id="maxMoney" cssClass="text medium"
					onkeypress="if(!this.value.match(/^[\+\-]?\d*?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\d+)?)?$/))this.o_value=this.value"
					onkeyup="if(!this.value.match(/^[\+\-]?\d*?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\d+)?)?$/))this.o_value=this.value"
					maxlength="12" /> <input type="hidden" name="oldMaxMoney"
				value="${fiBillAccount.maxMoney }" /></td>
		</tr>
		<tr>
			
		
			<th><span class="req">*</span>注册名称</th>
			<td align="left">
				<!--form:errors path="accountName" cssClass="fieldError"/--> <form:input
					path="accountName" id="accountName" cssClass="text medium" /></td>
			<th><jecs:label key="fiBillAccount.registerEmail" /></th>
			<td align="left">
				<!--form:errors path="registerEmail" cssClass="fieldError"/--> <form:input
					path="registerEmail" id="registerEmail" cssClass="text medium" /></td>
		</tr>

		<tr>
			<th><jecs:label key="fiBillAccount.status" /></th>
			<td align="left">
				<!--form:errors path="status" cssClass="fieldError"/--> <c:if
					test="${empty fiBillAccount.accountId}">
					<label><input name="status" id="status" type="radio"
						value="0" checked="checked" /> 启用</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<label><input name="status" id="status" type="radio"
						value="1" /> 停用</label>
				</c:if> <c:if test="${not empty fiBillAccount.accountId}">

					<label><input name="status" id="status" type="radio"
						value="0"
						<c:if test='${fiBillAccount.status==0}'>checked='checked'</c:if> />
						启用</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<label><input name="status" id="status" type="radio"
						value="1"
						<c:if test='${fiBillAccount.status==1}'>checked='checked'</c:if> />
						停用</label>
				</c:if>
			</td>
			<th>联系方式：</th>
			<td align="left">
				<!--form:errors path="registerEmail" cssClass="fieldError"/--> <form:input
					path="linkNum" id="linkNum" cssClass="text medium" /></td>
		</tr>
		
		<tr>
			<th>商户类型：</th>
			<td align="left">
				<select id="businessType" name="businessType">
					<option value="1" <c:if test='${fiBillAccount["businessType"] == 1}'>selected="selected"</c:if> >非全额</option>
					<option value="2" <c:if test='${fiBillAccount["businessType"] == 2}'>selected="selected"</c:if> >全额</option>
				</select>
			</td>
			<th id="jxsshbh"><span class="req">*</span>经销商编号</th>
			<td align="left"> 
				<form:input path="userCode" id="userCode" cssClass="text medium" />
				<form:select path="province"  cssClass="text medium" id="province">
					<form:options items="${alStateProvinces}" itemValue="stateProvinceId" itemLabel="stateProvinceName"/>
				</form:select>
			</td>
		</tr>

		<tr>
			<th>
				加密方式：
			</th>
			<td align="left">
				
				<select name="merchantType" id="merchantType">
					<option value="1">密钥</option>
					<option value="2" ${fiBillAccount.merchantType == 2 ? 'selected':''}>证书</option>
				</select>
			</td>
			<th>
				证书密码：
			</th>
			<td align="left">
				<form:input path="billAccountPassword" id="billAccountPassword" cssClass="text medium"/>
			</td>
		</tr>
		
		<tr>
			<th>私钥：</th>
			<td>
				<input id="filePassword" name="filePassword"  class="text medium" value='${fiBillAccount.password}'/>
				<c:set var="file" value="/${fiBillAccount.providerType }/"></c:set>
				<a name='downloadA' href="#" style="color:#00F">${fn:substringAfter(fiBillAccount.password, file)}</a>
			</td>
			<th>公钥：</th>
			<td>
				<input id="filePassword2" name="filePassword2" class="text medium" value='${fiBillAccount.password2}'/>
				<a name='downloadA' href="#" style="color:#00F">${fn:substringAfter(fiBillAccount.password2, file)}</a>
			</td>
		</tr>
		<tr>
			<th>业务代码：</th>
			<td  colspan="3"><form:input path="busicode" id="busicode" cssClass="text medium" /></td>
		</tr>
		<tr>
			<th><jecs:label key="fiBillAccount.remark" /></th>
			<td align="left" colspan="3">
				<form:textarea path="remark" id="remark" cssClass="text medium" cssStyle="width: 80%;height:50px" /></td>
		</tr>
		
		<tr>
			<th><jecs:label key="sysOperationLog.moduleName" /></th>
			<td align="left"><input type="submit" class="button" name="save"
				onclick="bCancel=false"
				value="<jecs:locale key="operation.button.save"/>" /> <c:if
					test="${not empty fiBillAccount.accountId}">
					<input type="button" class="button" name="cancel"
						onclick="toQueryList();"
						value="<fmt:message key="operation.button.cancel"/>" />
				</c:if> <c:if test="${empty fiBillAccount.accountId}">
					<input type="button" class="button" name="cancel"
						onclick="history.back();"
						value="<fmt:message key="operation.button.cancel"/>" />
				</c:if></td>
		</tr>

	</table>

</form:form>
<c:if test="${param.strReq ne 'add'}">
<div style="margin: 8px">
<ec:table 
	tableId="fiBillAccountListTable"
	items="fiBillAccountList"
	var="entity"
	action="${pageContext.request.contextPath}/editBusiness.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="5" sortable="true" imagePath="./images/extremetable/*.gif">
			<ec:row >
    				<ec:column property="businessType" title="${param.businessType == 1 ? '省份' : '经销商编号'}" >
	    				<c:if test="${fiBillAccount.businessType==1}">
							<jecs:region regionType="p" regionId="${fiBillAccount.province}"></jecs:region>
						</c:if>
	    				<c:if test="${fiBillAccount.businessType==2}">${fiBillAccount.userCode }</c:if>
					</ec:column>
					
	    			<ec:column property="userCode" title="经销商编号" />
	    			<ec:column property="accountName" title="注册名称" />
	    		
	    			<ec:column property="providerType" title="平台类型" >
						<jecs:code listCode="paycompany" value="${fiBillAccount.providerType}"/>
					</ec:column>
	    			<ec:column property="status" title="状态" >
	    				<c:if test="${fiBillAccount.status==0}">启用</c:if>
	    				<c:if test="${fiBillAccount.status==1}">禁用</c:if>
	    			</ec:column>
	    			<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
						<c:if test="${fiBillAccount.status==0}">
							<img src="<c:url value='/images/icons/w.gif'/>" onclick="javascript:businessType('stop','${fiBillAccount.accountId}')"
									style="cursor: pointer;" title="禁用"/>
						</c:if>
						<c:if test="${fiBillAccount.status==1}">
							<img src="<c:url value='/images/icons/r.gif'/>" onclick="javascript:businessType('start','${fiBillAccount.accountId}')"
									style="cursor: pointer;" title="启用"/>
						</c:if>	
						&nbsp;&nbsp;&nbsp;
						<jecs:power powerCode="editFiBillAccount">
						<img src="<c:url value='/images/icons/editIcon.gif'/>" 
									onclick="javascript:editFiBillAccount('${fiBillAccount.accountId}')"
									style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
						</jecs:power>
					</ec:column>
			</ec:row>
</ec:table>	
</div>
</c:if>
<script type="text/javascript">
	Form.focusFirstElement($('fiBillAccountForm'));
	

	if(${param.strReq ne 'edit'}){
		document.getElementsByName('downloadA')[0].style.display="none";
		document.getElementsByName('downloadA')[1].style.display="none";
	}


	function toQueryList() {
		window.location = "businessList.html";
	}

	var $businessType = document.getElementById("businessType");
	var $merchantType = document.getElementById("merchantType");
	var $userCode = document.getElementById("userCode");
	var $province = document.getElementById("province");

	$businessType.onchange=function(){
		var _str =   "经销商编号";
		if($businessType.value == 1){
			_str = "省份";
			$userCode.disabled=true;
			$province.disabled=false;
			$userCode.style.display="none";
			$province.style.display="inline";
		}else{
			$userCode.disabled=false;
			$province.disabled=true;
			$userCode.style.display="inline";
			$province.style.display="none";
		}
		document.getElementById("jxsshbh").innerHTML='<span class="req">*</span>'+_str;
	};
	$businessType.onchange();
	
	$merchantType.onchange=function(){
		if($merchantType.value == 1){
			document.getElementById("filePassword").setAttribute("type","text");
			document.getElementById("filePassword2").setAttribute("type","text");
		}else{
			document.getElementById("filePassword").setAttribute("type","file");
			document.getElementById("filePassword2").setAttribute("type","file");
		}
	};
	$merchantType.onchange();

	

	if(${strReq eq 'edit' }){
		$businessType.disabled = true;
	}
	
	var $businessType =document.getElementById("businessType"); 

	function validateOthers(theForm) {

		if (theForm.elements['billAccountCode'].value == "") {
			alert("商户号不能为空!");
			theForm.elements['billAccountCode'].focus();
			return false;
		}

		if (theForm.elements['providerType'].value == "") {
			alert("商户号不能为空!");
			theForm.elements['billAccountCode'].focus();
			return false;
		}

		if (theForm.elements['maxMoney'].value == "") {
			alert("最大限额值不能为空!");
			theForm.elements['maxMoney'].focus();
			return false;
		}

		if (theForm.elements['userCode'].value == "") {
			alert("经销商编号不能为空!");
			theForm.elements['userCode'].focus();
			return false;
		}
		if (theForm.elements['province'].value == "") {
			alert("省份不能为空!");
			theForm.elements['province'].focus();
			return false;
		}
		
		if (theForm.elements['accountName'].value == "") {
			alert("注册名称不能为空!");
			theForm.elements['accountName'].focus();
			return false;
		}
	}
	function editFiBillAccount(accountId){
		<jecs:power powerCode="editFiBillAccount">
				window.location="editBusiness.html?strReq=edit&accountId="+accountId;
		</jecs:power>
	}
	function businessType(_type,accountId){
		var _href ="editBusiness.html?strReq="+_type+"&accountId="+accountId;
		document.fiBillAccountForm.elements['strReq'].value=_type;
		document.fiBillAccountForm.elements['accountId'].value=accountId;
		document.fiBillAccountForm.action=_href;
		document.fiBillAccountForm.submit();
		
	}
</script>

<v:javascript formName="fiBillAccount" cdata="false"
	dynamicJavascript="true" staticJavascript="false" />
<script type="text/javascript"
	src="<c:url value="/scripts/validator.jsp"/>"></script>
