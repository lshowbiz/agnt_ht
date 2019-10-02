<%@ page language="java" errorPage="/error.jsp"
	contentType="text/html; charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>




<script type="text/javascript" src="<c:url value='/dwr/util.js'/>"></script>
<script type="text/javascript" src="<c:url value='/dwr/engine.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/dwr/interface/jmiMemberManager.js'/>"></script>

<script type="text/javascript">
	function selectMiMember() {
		var userCode = document.getElementById("userCode").value;

		if (userCode != '') {

			jmiMemberManager.getJmiMember(userCode, callBack);
		}

		//open("<c:url value='/bdBounsDeductFindMembers.html?userCode="+userCode+"'/>","","height=650, width=800, top=20, left=20, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no");
	}

	function callBack(valid) {
		if (valid == null) {
			alert('<jecs:locale key="miMember.notFound"/>');
		} else {
			document.getElementById('userNameSpan').innerHTML = valid.miUserName;
			if (valid.isstore == '0') {
				document.getElementById('isstoreSpan').innerHTML = '店铺类型：无';
			} else if (valid.isstore == '1') {
				document.getElementById('isstoreSpan').innerHTML = '店铺类型：一级店铺';
			} else if (valid.isstore == '2') {
				document.getElementById('isstoreSpan').innerHTML = '店铺类型：二级店铺';
			}
			if (valid.active == '0') {
				document.getElementById('activeSpan').innerHTML = '死点：否';
			} else if (valid.active == '1') {
				document.getElementById('activeSpan').innerHTML = '死点：是';
			}
			if (valid.exitDate == '' || valid.exitDate == null) {
				document.getElementById('exitDateSpan').innerHTML = '退出：否';
			} else {
				document.getElementById('exitDateSpan').innerHTML = '退出：是';
			}

			if (valid.freezeStatus == '0') {
				document.getElementById('freezeSpan').innerHTML = '冻结：否';
			} else if (valid.freezeStatus == '1') {
				document.getElementById('freezeSpan').innerHTML = '冻结：是';
			}
		}

	}

	function checkValue() {
		var money = document.getElementById("money").value;

		var type = document.getElementsByName("type")[0].value;
		if (money == '' || isNaN(money)) {
			alert('原扣补金额不能为空且必须为数字');
			return false;
		}
		<c:if test="${param.strAction=='addDeduct' }">
		var userCode = document.getElementById("userCode").value;
		if (userCode == '') {
			alert('<jecs:locale key="operation.notice.js.orderNo.miMember.memberNoError"/>');
			return false;
		}
		</c:if>

		if (type == '') {

			alert('<jecs:locale key="operation.notice.js.typeNotNull"/>');
			return false;
		}
		return true;
	}
</script>
<title><jecs:locale key="bdBounsDeductDetail.title" />
</title>
<content tag="heading">
<jecs:locale key="bdBounsDeductDetail.heading" /></content>


<spring:bind path="bdBounsDeduct.*">
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

<form:form commandName="bdBounsDeduct" method="post"
	action="editBdBounsDeductCreate.html" onsubmit="return checkValue();"
	id="bdBounsDeductCreateForm">


	<input type="hidden" name="strAction" value="${param.strAction }" />

	<table class='detail' width="70%">
		<tbody class="window">
			<form:hidden path="id" />

			<tr class="edit_tr">
				<th><span class="text-font-style-tc"> <font color="red">*</font>
					<jecs:label key="miMember.memberNo" /> </span>
				</th>
				<td><span class="textbox"> <!--form:errors path="memberNo" cssClass="fieldError"/-->
						<c:if test="${param.strAction=='view'||param.strAction=='editDeduct' }">
       						 ${bdBounsDeduct.jmiMember.userCode}
       					</c:if> 
       				<c:if test="${param.strAction=='addDeduct' }">
							<form:input cssClass="textbox-text" path="jmiMember.userCode"
								id="userCode" />
							<img src="images/icons/more.gif" onclick="selectMiMember();"
								alt="<jecs:locale key="operation.notice.more"/>"
								style="cursor: pointer;" />
						</c:if> </span>
				</td>
		
				<th><span class="text-font-style-tc"> <jecs:label
							key="bdCalculatingSubDetail.name" /> </span>
				</th>
				<td><span class="textbox"> <!--form:errors path="memberNo" cssClass="fieldError"/-->
						<c:if
							test="${param.strAction=='view'||param.strAction=='editDeduct' }">
        ${bdBounsDeduct.jmiMember.miUserName }
        </c:if> <c:if test="${param.strAction=='addDeduct' }">
							<%--<form:input cssClass="textbox-text" path="jmiMember.miUserName" id="userName" cssClass="text medium" disabled="true"/>--%>
							<span id="userNameSpan"></span>&nbsp;&nbsp;<span id="isstoreSpan"></span>&nbsp;&nbsp;<span
								id="activeSpan"></span>&nbsp;&nbsp;<span id="exitDateSpan"></span>&nbsp;&nbsp;<span
								id="freezeSpan"></span>
						</c:if> </span>
				</td>
			</tr>
			
			<tr class="edit_tr">
			<c:if
				test="${param.strAction=='view'||param.strAction=='editDeduct' }">
				<tr class="edit_tr">
					<th><span class="text-font-style-tc"> <font color="red">*</font>
						<jecs:label key="miMember.cardType" /> </span>
					</th>
					<td><span class="textbox"> <!--form:errors path="memberNo" cssClass="fieldError"/-->
							<jecs:code listCode="bd.cardtype"
								value="${bdBounsDeduct.jmiMember.cardType}" /> </span>
					</td>
				
			</c:if>


			
				<th><span class="text-font-style-tc"> <font color="red">*</font>
					<jecs:label key="bdReconsumMoneyReport.typeCH" /> </span>
				</th>
				<td><span class="textbox"> <!--form:errors path="type" cssClass="fieldError"/-->
						<c:if test="${param.strAction=='view' }">
							<jecs:list styleClass="textbox-text" 
								name="type" listCode="bdbounsdeduct.item"
								value="${bdBounsDeduct.type }" defaultValue=""
								showBlankLine="true" disabled="disabled" />
						</c:if> <c:if
							test="${param.strAction=='editDeduct'||param.strAction=='addDeduct' }">
							<jecs:list styleClass="textbox-text" name="type"
								listCode="bdbounsdeduct.item" value="${bdBounsDeduct.type }"
								defaultValue="" showBlankLine="true" />
						</c:if> </span>
				</td>
			</tr>

			<c:if test="${param.strAction=='view' }">

				<tr class="edit_tr">
					<th><span class="text-font-style-tc"> <jecs:label
								key="bdBounsDeduct.wweek" /> </span>
					</th>
					<td><span class="textbox"> <!--form:errors path="deductMoney" cssClass="fieldError"/-->
							${bdBounsDeduct.wweek } </span>
					</td>
				

					<th><span class="text-font-style-tc"> <jecs:label
								key="bdBounsDeduct.deductTime" /> </span>
					</th>
					<td><span class="textbox"> <!--form:errors path="deductTime" cssClass="fieldError"/-->
							${bdBounsDeduct.deductTime } </span>
					</td>
				</tr>

				<tr class="edit_tr">
					<th><span class="text-font-style-tc"> <jecs:label
								key="bdBounsDeduct.deducterCode" /> </span>
					</th>
					<td><span class="textbox"> <!--form:errors path="deductTime" cssClass="fieldError"/-->
							${bdBounsDeduct.deducterCode } </span>
					</td>
				
					<th><span class="text-font-style-tc"> <jecs:label
								key="bdBounsDeduct.deducterName" /> </span>
					</th>
					<td><span class="textbox"> <!--form:errors path="deductTime" cssClass="fieldError"/-->
							${bdBounsDeduct.deducterName } </span>
					</td>
				</tr>
			</c:if>
			<c:if
				test="${param.strAction=='editDeduct'||param.strAction=='view' }">
				<tr class="edit_tr">
					<th><span class="text-font-style-tc"> <jecs:label
								key="bdBounsDeduct.status" /> </span>
					</th>
					<td><span class="textbox"> <!--form:errors path="deductTime" cssClass="fieldError"/-->
							<jecs:code listCode="bdbounsdeduct.status"
								value="${bdBounsDeduct.status}" /> </span>
					</td>
					<th>
						<span class="text-font-style-tc"> 
							<font color="red">*</font><jecs:label key="bdBounsDeduct.money" />
						</span>
					</th>
					<td>
						<span class="textbox">
							<c:if test="${param.strAction=='view' }">
					         ${bdBounsDeduct.money }
					        </c:if> 
					        <c:if test="${param.strAction=='editDeduct'||param.strAction=='addDeduct' }">
								<form:input cssClass="textbox-text" path="money" id="money"/>
								<jecs:locale key="operation.notice.bdDeductMoney" />
							</c:if> 
						</span>
					</td>
				</tr>

				<c:if test="${bdBounsDeduct.status==2}">
					<tr class="edit_tr">
						<th><span class="text-font-style-tc"> <jecs:label
									key="pd.okTime" /> </span>
						</th>
						<td><span class="textbox"> <!--form:errors path="deductTime" cssClass="fieldError"/-->
								${bdBounsDeduct.checkTime } </span>
						</td>
					

						<th><span class="text-font-style-tc"> <jecs:label
									key="bdBounsDeduct.checkerName" /> </span>
						</th>
						<td><span class="textbox"> <!--form:errors path="deductTime" cssClass="fieldError"/-->
								${bdBounsDeduct.checkerName } </span>
						</td>
					</tr>
				</c:if>




				<tr class="edit_tr">
					<th><span class="text-font-style-tc"> <jecs:label
								key="prRefund.createTime" /> </span>
					</th>
					<td><span class="textbox"> <!--form:errors path="createTime" cssClass="fieldError"/-->
							${bdBounsDeduct.createTime } </span>
					</td>
			
					<th><span class="text-font-style-tc"> <jecs:label
								key="bdBounsDeduct.createName" /> </span>
					</th>
					<td><span class="textbox"> <!--form:errors path="createTime" cssClass="fieldError"/-->
							${bdBounsDeduct.createName } </span>
					</td>
				</tr>
			</c:if>
			
			<tr class="edit_tr">
				<th>
					<span class="text-font-style-tc-tare"> 
						<jecs:label key="bdBounsDeduct.summary" />
					</span>
				</th>
				<td colspan="3">
					<span class="text-font-style-tc-right">
						<c:if test="${param.strAction=='view' }">
					    	<form:textarea path="summary" id="summary" cssClass="textarea_border"
									rows="6" cols="50" />
					    </c:if> 
				        <c:if test="${param.strAction=='editDeduct'||param.strAction=='addDeduct' }">
							<form:textarea path="summary" id="summary" cssClass="textarea_border"
								rows="6" cols="50" />
						</c:if> 
					</span>
				</td>
			</tr>
			<tr class="edit_tr">
				
				<td class="command" colspan="4" align="center"><c:if
						test="${param.strAction=='editDeduct'||param.strAction=='addDeduct' }">
						<button type="submit" class="btn btn_sele" name="back">
							<jecs:locale key="operation.button.save" />
						</button>
					</c:if>
					<button type="button" class="btn btn_sele" name="back"
						onclick="javascript:history.back();">
						<jecs:locale key="operation.button.return" />
					</button></td>
			</tr>
		</tbody>
	</table>

</form:form>

<script type="text/javascript">
	Form.focusFirstElement($('bdBounsDeductCreateForm'));
</script>

<v:javascript formName="bdBounsDeduct" cdata="false"
	dynamicJavascript="true" staticJavascript="false" />
<script type="text/javascript"
	src="<c:url value="/scripts/validator.jsp"/>"></script>
