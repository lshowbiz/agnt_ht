<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="foundationOrderDetail.title" />
</title>
<content tag="heading">
<jecs:locale key="foundationOrderDetail.heading" />
</content>
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script>
<!-- 
<c:choose>
	<c:when test="${uniNoStr!=null && uniNoStr!='' && uniNoStr!='null'}">
		<script type="text/javascript">
			location.href='jpmProductSaleNews.html?strAction=confirmJpmProductSaleNew&uniNoStr=${uniNoStr }&status2=${status2 }';
		</script>
	</c:when>
	<c:otherwise>
          </c:otherwise>
</c:choose>
 -->
<%-- 	
	<c:set var="buttons">
	
		
			<input type="submit" class="button" name="save"
				onclick="bCancel=false" value="<jecs:locale key="button.foundation"/>" />
		
		<input type="button" class="button" name="cancel" onclick="window.history.back()" value="<jecs:locale key="operation.button.return"/>" />
	</c:set>
--%>	
<c:set var="buttons">
	
		
			<button type="submit" class="btn btn_sele" name="save"
				onclick="bCancel=false" ><jecs:locale key="button.foundation"/></button>
		
		<button type="button" class="btn btn_sele" name="cancel" onclick="window.history.back()"><jecs:locale key="operation.button.return"/></button>
	</c:set>
	<spring:bind path="foundationOrder.*">
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
	
	<form:form commandName="foundationOrder" method="post"
		action="editFoundationOrder.html"
		onsubmit="return validateFoundationOrder(this)"
		id="foundationOrderForm">
	
		 
		<input type="hidden" name="strAction" value="${param.strAction }" />
	
		<!--fieldset style="padding: 2">
	<legend>
					<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
	        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('FoundationOrder')" value="<jecs:locale key="button.delete"/>" />
	        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
	
	</legend-->
	<table class='detail' width="70%">
		<tbody class="window">
			<form:hidden path="orderId" />

			
			
			<tr class="edit_tr">
				<th>
					<span class="text-font-style-tc"><jecs:locale key="foundationItem.name" /></span>
				</th>
				<td>
					 <span class="textbox"><span class="textbox-text">${foundationOrder.foundationItem.name }</span></span>
				</td>
			
				<th>
					<span class="text-font-style-tc"><jecs:locale key="foundationItem.amount" /></span>
				</th>
				<td>
					<span class="textbox"><span class="textbox-text"> ${foundationOrder.foundationItem.amount }${foundationOrder.foundationItem.unit }</span></span>
					 <input type="hidden" name="amountV" id="amountV" value="${foundationOrder.foundationItem.amount }"/>
				</td>
			</tr>
	
			
	
			<tr class="edit_tr">
				<th>
					<span class="text-font-style-tc"><jecs:label key="foundationOrder.fiNum" /></span>
				</th>
				<td>
					<!--form:errors path="fiNum" cssClass="fieldError"/-->
					<c:if test="${foundationOrder.foundationItem.type==1}">
						<span class="textbox"><form:input path="fiNum" id="fiNum" cssClass="textbox-text" readonly="true"/></span>
					</c:if>
					<c:if test="${foundationOrder.foundationItem.type!=1}">
						<span class="textbox"><form:input path="fiNum" id="fiNum" cssClass="textbox-text" onkeyup="setFinum();" onchange="setFinum();"/></span>
					</c:if>
					
					<input type="hidden" name="fiId" value="${foundationOrder.foundationItem.fiId }"/>
					<input type="hidden" name="dan" value="${foundationOrder.foundationItem.fiId }"/>
				</td>
			
				<th>
					<span class="text-font-style-tc"><jecs:locale key="foundationOrder.amount" /></span>
				</th>
				<td>
					<!--form:errors path="amount" cssClass="fieldError"/-->
					<span class="textbox"><span class="textbox-text"><font id="amount" color="red">${foundationOrder.foundationItem.amount }</font></span></span>
				</td>
			</tr>
			
			<tr class="edit_tr">
				<th>
					<span class="text-font-style-tc-tare"><jecs:locale key="foundationItem.remark" /></span>
				</th>
				<td colspan="3">
					<span class="text-font-style-tc-right">
						<textarea class="textarea_border" readonly="readonly">
							${foundationOrder.foundationItem.remark }
						</textarea>
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
<%-- 
		<table class='detail' width="100%">
	
			<form:hidden path="orderId" />
	
			
			
			<tr>
				<th>
					<jecs:locale key="foundationItem.name" />
				</th>
				<td align="left">
					 ${foundationOrder.foundationItem.name }
				</td>
			</tr>
			<tr>
				<th>
					<jecs:locale key="foundationItem.remark" />
				</th>
				<td align="left">
					 ${foundationOrder.foundationItem.remark }
				</td>
			</tr>
			<tr>
				<th>
					<jecs:locale key="foundationItem.amount" />
				</th>
				<td align="left">
					 ${foundationOrder.foundationItem.amount }${foundationOrder.foundationItem.unit }
					 <input type="hidden" name="amountV" id="amountV" value="${foundationOrder.foundationItem.amount }"/>
				</td>
			</tr>
	
			
	
			<tr>
				<th>
					<jecs:label key="foundationOrder.fiNum" />
				</th>
				<td align="left">
					<!--form:errors path="fiNum" cssClass="fieldError"/-->
					<c:if test="${foundationOrder.foundationItem.type==1}">
						<form:input path="fiNum" id="fiNum" cssClass="text medium" readonly="true"/>
					</c:if>
					<c:if test="${foundationOrder.foundationItem.type!=1}">
						<form:input path="fiNum" id="fiNum" cssClass="text medium" onkeyup="setFinum();" onchange="setFinum();"/>
					</c:if>
					
					<input type="hidden" name="fiId" value="${foundationOrder.foundationItem.fiId }"/>
					<input type="hidden" name="dan" value="${foundationOrder.foundationItem.fiId }"/>
				</td>
			</tr>
	
			<tr>
				<th>
					<jecs:locale key="foundationOrder.amount" />
				</th>
				<td align="left">
					<!--form:errors path="amount" cssClass="fieldError"/-->
					<font id="amount" color="red">${foundationOrder.foundationItem.amount }</font>
				</td>
			</tr>
			
			<tr>
				<th>
					&nbsp;
				</th>
				<td colspan="2"><c:out value="${buttons}" escapeXml="false" /></td>
			</tr>
	
		</table>
--%>
		<!--/fieldset-->
	
		<!--table class='detail' width="100%">
	    <tr>
	    <td>
	        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
	        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('FoundationOrder')" value="<jecs:locale key="button.delete"/>" />
	        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
	    </td></tr>
	</table-->
	</form:form>
	
	<script type="text/javascript">
	    Form.focusFirstElement($('foundationOrderForm'));
	    
	    function setFinum(){
	    	var fiNum = document.getElementById("fiNum");
	    	var amountV = document.getElementById("amountV");
		    var amount = document.getElementById("amount");
	    	if(fiNum.value==null || isNaN(fiNum.value) || fiNum.value<1){
	    	
	    		fiNum.value = '${foundationOrder.fiNum }';	    		 		
	    		return;
	    	}else{
		    	amount.innerHTML = amountV.value*fiNum.value;
		    }
	    }
	</script>
	
	<v:javascript formName="foundationOrder" cdata="false"
		dynamicJavascript="true" staticJavascript="false" />
	<script type="text/javascript"
		src="<c:url value="/scripts/validator.jsp"/>"></script>

