<%@ include file="/common/taglibs.jsp"%>
<title><jecs:locale key="顾客推荐奖 扣补"/></title>
<content tag="heading"><jecs:locale key="jbdDayCustRecommendKb.heading"/></content>


<spring:bind path="jbdDayCustRecommendKb.*">
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

<form:form commandName="jbdDayCustRecommendKb" method="post"
	action="editJbdDayCustRecommendKb.html" id="jbdDayCustRecommendKbForm">

	<div id="titleBar">
		<c:out value="${buttons}" escapeXml="false" />
	</div>
	<input type="hidden" name="strAction" value="${param.strAction }" />

	<table class='detail' width="100%">

		<tbody class="window">
 			<form:hidden path="id" />



			<tr class="edit_tr">
				<th><span class="text-font-style-tc"><font color="red">*</font>
						<jecs:label key="miMember.memberNo" /> </span></th>
				<td><span class="textbox"> <form:input path="userCode"
							id="userCode" cssClass="textbox-text" />
				</span></td>
				<th><span class="text-font-style-tc"><font color="red">*</font>
						<jecs:label key="bdBounsDeduct.wweek" /> </span></th>
				<td><span class="textbox"><form:input path="wweek"
							id="wweek" readonly="true"
							onclick="WdatePicker({dateFmt:'yyyyMMdd',readOnly:true})"
							cssClass="textbox-text" /></span></td>
			</tr>


			<tr class="edit_tr">
				<th><span class="text-font-style-tc"> <jecs:label
							key="jbdMemberLinkCalcHist.deductMoney" />
				</span></th>
				<td><span class="textbox"> <form:input path="kb_money"
							id="kb_money"
							onkeypress="if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value"
							onkeyup="if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value"
							maxlength="12" cssClass="textbox-text" />
				</span></td>
				<th></th>
				<td></td>
			</tr>


			<tr class="edit_tr">
				<th><span class="text-font-style-tc-tare"> <jecs:label
							key="bdBounsDeduct.summary" />
				</span></th>
				<td colspan="3"><span class="text-font-style-tc-right">
						<form:textarea path="kb_reason" id="kb_reason"
							cssClass="textarea_border" rows="6" cols="50" />
				</span></td>
			</tr>




			<tr class="edit_tr">

				<td class="command" colspan="4" align="center"><c:if
						test="${jbdDayCustRecommendKb.status==0  || empty jbdDayCustRecommendKb.id}">

						<button type="submit" class="btn btn_sele" name="back">
							<jecs:locale key="operation.button.save" />
						</button>

						<button type="submit" class="btn btn_sele" name="back"
							onclick="bCancel=true;return confirmDelete(jbdDayCustRecommendKbForm,'JbdDayCustRecommendKb')">
							<jecs:locale key="operation.button.delete" />
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


