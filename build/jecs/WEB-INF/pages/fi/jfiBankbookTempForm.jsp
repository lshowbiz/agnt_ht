<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="fiBankbookTempDetail.title" />
</title>
<content tag="heading">
<jecs:locale key="fiBankbookTempDetail.heading" />
</content>

<script language="javascript" src="scripts/validate.js"></script>
<script type="text/javascript" src="<c:url value='/dwr/util.js'/>"></script>
<script type="text/javascript" src="<c:url value='/dwr/engine.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/dwr/interface/sysUserManager.js'/>"></script>
<script>
		   function searchMiMember(){
		   sysUserManager.getSysUser($("sysUser.userCode").value,callBack);
		   }
		   function callBack(valid){
			   if(valid==null){
			   	alert('<jecs:locale key="operation.notice.js.orderNo.miMember.memberNoError"/>');
			   }else{
				   document.getElementById('userName').innerText=valid.userName;
			   }
		   }
</script>
<form:form commandName="jfiBankbookTemp" method="post"
	action="editJfiBankbookTemp.html"
	onsubmit="return validateJfiBankbookTemp(this)&&validateOthers(this)"
	id="jfiBankbookTempForm">
	<input type="hidden" name="strAction" value="${param.strAction }" />

	<spring:bind path="jfiBankbookTemp.*">
		<c:if test="${not empty status.errorMessages}">
			<div class="error">
				<c:forEach var="error" items="${status.errorMessages}">
					<img src="<c:url value="images/newIcons/warning_16.gif"/>"
						alt="<jecs:locale key="icon.warning"/>" class="icon" />
					<c:out value="${error}" escapeXml="false" />
					<br />
				</c:forEach>
			</div>
		</c:if>
	</spring:bind>
		
	<table class='detail' width="70%">
		<form:hidden path="tempId" />
		<tbody class="window">
			<tr class="edit_tr">
				<th>
					<span class="text-font-style-tc">
						<span class="req">*</span>
						<jecs:label key="label.member.or.agent.code" />
					</span>
				</th>
				<td>
					 <span class="textbox">
						<c:if test="${not empty jfiBankbookTemp.tempId}">
							<form:input path="sysUser.userCode" id="sysUser.userCode"
								cssClass="readonly" readonly="true" />
						</c:if>
						<c:if test="${empty jfiBankbookTemp.tempId}">
							<form:input path="sysUser.userCode" id="sysUser.userCode" cssClass="textbox-text"/>
						</c:if>
					  </span>
					  <img src="<c:url value="/images/l_1.gif"/>" id="person"
								onclick="searchMiMember()" style="cursor: pointer;"
								title="<jecs:locale key="operation.button.search"/>" />
						<span id="userName">${jfiBankbookTemp.sysUser.userName }</span>
					 
				</td>
				<th>
					<span class="text-font-style-tc">
						<span class="req">*</span>
						<jecs:label key="fiBankbookTemp.dealType" />
					</span>
				</th>
				<td>
					<span class="textbox">
						<jecs:list listCode="fibankbooktemp.dealtype" name="dealType" styleClass="textbox-text"
						id="dealType" value="${jfiBankbookTemp.dealType}" defaultValue="A" />
					</span>
				</td>
			</tr>
			<tr class="edit_tr">
				<th>
					<span class="text-font-style-tc">
						<span class="req">*</span>
						<jecs:label key="fiBankbookTemp.moneyType" />
					</span>
				</th>
				<td>
					<span class="textbox">
						<select name="moneyType" onchange="toConDiv(this)" class="textbox-text">
							<c:forEach items="${moneyTypes}" var="moneyTypeVar">
								<c:if
									test="${moneyTypeVar.key!=13 && moneyTypeVar.key!=15 && moneyTypeVar.key!=16 && moneyTypeVar.key!=21 && moneyTypeVar.key!=22 && moneyTypeVar.key!=29 && moneyTypeVar.key!=30 && moneyTypeVar.key!=31 && moneyTypeVar.key!=32 && moneyTypeVar.key!=33}">
									<option value="${moneyTypeVar.key }"
										<c:if test="${moneyTypeVar.key==jfiBankbookTemp.moneyType || (empty jfiBankbookTemp.moneyType && moneyTypeVar.key==1)}"> selected</c:if>>
										<jecs:locale key="${moneyTypeVar.value }" />
									</option>
								</c:if>
							</c:forEach>
						</select>
					</span>
				</td>
				<th>
					<span class="text-font-style-tc">
						<span class="req">*</span>
						<jecs:label key="busi.finance.amount" />
					</span>
				</th>
				<td>
					<span class="textbox">
						<form:input path="money" id="money" maxlength="11" cssClass="textbox-text"/>
					</span>
				</td>
			</tr>
			<tr class="edit_tr">
				<th>
					<span class="text-font-style-tc-tare">
						<jecs:label key="bdBounsDeduct.summary" />
					</span>
				</th>
				<td colspan="3">
					 <span class="text-font-style-tc-right">
						<form:textarea path="notes" id="notes" rows="5" cols="100"
						htmlEscape="true" cssClass="textarea_border"/>
					 </span>
				</td>
			</tr>
			<tr class="edit_tr">
				<th>
					<span class="text-font-style-tc-tare">
						<jecs:label key="bdBounsDeduct.description" />
					</span>
				</th>
				<td colspan="3">
					 <span class="text-font-style-tc-right">
						<form:textarea path="description" id="description" rows="5"
						cols="100" htmlEscape="true" cssClass="textarea_border"/>
					 </span>
				</td>
			</tr>
			<tr>
				<td class="command" colspan="4" align="center">		
					<jecs:power powerCode="${param.strAction}">
						<input type="submit" name="save" class="btn btn_sele"
							onclick="bCancel=false"
							value="<jecs:locale key="operation.button.save"/>" />
					</jecs:power>
					<c:if test="${not empty jfiBankbookTemp.tempId}">
						<jecs:power powerCode="deleteFiBankbookTemp">
							<input type="submit" name="delete" class="btn btn_sele"
								onclick="bCancel=true;return confirmDelete(this.form,'FiBankbookTemp')"
								value="<jecs:locale key="operation.button.delete"/>" />
						</jecs:power>
					</c:if>
	
					<input type="button" name="cancel" class="btn btn_sele"
						onclick="history.back();"
						value="<jecs:locale key="operation.button.cancel"/>" />				
				</td>
			</tr>
		</tbody>
	</table>
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('jfiBankbookTempForm'));
    function validateOthers(theForm){
    
		if(theForm.elements['sysUser.userCode'].value==""){
			alert("<jecs:locale key="bdBounsDeduct.error.memberNo.empty"/>");
			theForm.elements['sysUser.userCode'].focus();
			return false;
		}
		if(theForm.money.value=="" || !isUnsignedNumeric(theForm.money.value)){
			alert("<jecs:locale key="fiPayAdvice.money.required"/>");
			theForm.money.focus();
			return false;
		}
		if(theForm.money.value==0){
			alert("<jecs:locale key="fiPayAdvice.money.required"/>");
			theForm.money.focus();
			return false;
		}
		if(!isUnsignedMoney(theForm.money.value)){
			alert("金额不符合要求");
			theForm.money.focus();
			return false;
		}
		
		if(theForm.notes.value.length > 500){
			alert("<jecs:locale key="fi.notestolength.required"/>");
			theForm.notes.focus();
			return false;
		}
		
		var pospay_num=document.getElementById("billpospayNum").value;
		
		theForm.billpospayNum.value=pospay_num;
		return isFormPosted();
	}
	function toConDiv(selectId){
		
		if(selectId.value==28){

			document.getElementById("pospaynum_div1").style.display="block";
			document.getElementById("pospaynum_div2").style.display="block";
		}else{
			document.getElementById("pospaynum_div1").style.display="none";
			document.getElementById("pospaynum_div2").style.display="none";
		}
	}
</script>

<v:javascript formName="jfiBankbookTemp" cdata="false"
	dynamicJavascript="true" staticJavascript="false" />
<script type="text/javascript"
	src="<c:url value="/scripts/validator.jsp"/>"></script>
