<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jbdCalcDayXsHistDetail.title"/></title>
<content tag="heading"><jecs:locale key="jbdCalcDayXsHistDetail.heading"/></content>


<%-- 
<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
		</jecs:power>
		<jecs:power powerCode="deleteJbdCalcDayXsHist">
				<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'JbdCalcDayXsHist')" value="<jecs:locale key="button.delete"/>" />
		</jecs:power>
</c:set>
--%>


<c:set var="buttons">

	<jecs:power powerCode="${param.strAction}">
		<button type="submit" class="btn btn_sele" name="save">
			<jecs:locale key="operation.button.save" />
		</button>
	</jecs:power>
	<button type="button" class="btn btn_sele" name="back"
			onclick="javascript:history.back();">
		<jecs:locale key="operation.button.return" />
	</button>
</c:set>


<spring:bind path="jbdCalcDayXsHist.*">
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

<form:form commandName="jbdCalcDayXsHist" method="post" action="editJbdCalcDayXsHist.html" onsubmit="return validateJbdCalcDayXsHist(this)" id="jbdCalcDayXsHistForm">

<%-- 
<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
--%>
<input type="hidden" name="strAction" value="${param.strAction }"/>
<form:hidden path="id"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JbdCalcDayXsHist')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->

<table class='detail' width="70%">
	<tbody class="window">
	
		<tr class="edit_tr">
			<th>
					<span class="text-font-style-tc"><jecs:label key="jbd.clac.xs.hist.wweek" />	
					</span>
				</th>
				<td>
					<span class="textbox"><form:input path="wweek"
							id="wweek" cssClass="textbox-text" />
					</span>
				</td>
	
			<th>
					<span class="text-font-style-tc"><jecs:label key="jpoUserCoupon.userCode" />	
					</span>
				</th>
				<td>
					<span class="textbox"><form:input path="userCode"
							id="userCode" cssClass="textbox-text" />
					</span>
				</td>
		</tr>
		
		<tr class="edit_tr">
			<th>
					<span class="text-font-style-tc"><jecs:label key="miMember.cardType" />
					</span>
				</th>
				<td>
					<span class="textbox"><jecs:list styleClass="textbox-text"
							listCode="member.level" name="memberLevel" id="memberLevel"
							value="${jbdCalcDayXsHist.memberLevel}" defaultValue="" />
					</span>
					
				</td>
	
			<th>
					<span class="text-font-style-tc"><jecs:label key="bdBonusStatReport.company.keey.pv" />
					</span>
				</th>
				<td>
					<span class="textbox"><form:input path="keepPv"
							id="keepPv" cssClass="textbox-text" />
					</span>
				</td>
		</tr>
		
		<tr class="edit_tr">
			<th>
					<span class="text-font-style-tc"><jecs:label key="bdBonus.kpvMember" />
					</span>
				</th>
				<td>
					<span class="textbox"><form:input path="keepUserCode"
							id="keepUserCode" cssClass="textbox-text" />
					</span>
				</td>
	
		</tr>
		
		
		<tr>
				<td class="command" colspan="4" align="center">
					<c:out value="${buttons}" escapeXml="false" />
				</td>
			</tr>
		</tbody>
</table>

<!--/fieldset-->

<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JbdCalcDayXsHist')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('jbdCalcDayXsHistForm'));
</script>

<v:javascript formName="jbdCalcDayXsHist" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
