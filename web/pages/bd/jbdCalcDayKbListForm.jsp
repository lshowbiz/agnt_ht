<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jbdCalcDayKbListDetail.title"/></title>
<content tag="heading"><jecs:locale key="jbdCalcDayKbListDetail.heading"/></content>

<%-- <c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
		</jecs:power>
		<jecs:power powerCode="deleteJbdCalcDayKbList">
				<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'JbdCalcDayKbList')" value="<jecs:locale key="button.delete"/>" />
		</jecs:power>
</c:set> --%>

<spring:bind path="jbdCalcDayKbList.*">
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

<form:form commandName="jbdCalcDayKbList" method="post" action="editJbdCalcDayKbList.html"  id="jbdCalcDayKbListForm">

<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JbdCalcDayKbList')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
<table class='detail' width="100%">

		<tbody class="window">
<form:hidden path="id"/>



    <tr class="edit_tr"><th><span class="text-font-style-tc"><font color="red">*</font>
        <jecs:label  key="miMember.memberNo"/>
    </span></th>
    <td>
    	<span class="textbox">
        <form:input path="userCode" id="userCode" cssClass="textbox-text"/>
        </span>
    	<%-- <img src="<c:url value="/images/l_1.gif"/>" id="person"  onclick="searchMiMember();"
    	style="cursor: pointer;" title="<jecs:locale key="operation.button.search"/>"/> 
    	<span id="memberName"></span> --%>	
    </td><th><span class="text-font-style-tc"><font color="red">*</font>
        <jecs:label  key="bdBounsDeduct.wweek"/>
    </span></th>
    <td>
        <span class="textbox"><form:input path="wweek" id="wweek" readonly="true" onclick="WdatePicker({dateFmt:'yyyyMMdd',readOnly:true})" cssClass="textbox-text"/></span>
   
    </td></tr>


<tr class="edit_tr"><th><span class="text-font-style-tc">
        <jecs:label  key="jbdMemberLinkCalcHist.deductMoney"/>
    </span></th>
    <td>
    	<span class="textbox">
        <form:input path="kbMoney" id="kbMoney" onkeypress="if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value"
							onkeyup="if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value"
							maxlength="12"  cssClass="textbox-text"/>
        </span>
    </td><th></th>
    <td>
       
   
    </td></tr>


		<tr class="edit_tr">
				<th>
					<span class="text-font-style-tc-tare"> 
						<jecs:label key="bdBounsDeduct.summary" />
					</span>
				</th>
				<td colspan="3">
					<span class="text-font-style-tc-right">
							<form:textarea path="kbReason" id="kbReason" cssClass="textarea_border"
								rows="6" cols="50" />
					</span>
				</td>
			</tr>

    

  
 	<tr class="edit_tr">
				
				<td class="command" colspan="4" align="center">
				
				<c:if test="${jbdCalcDayKbList.status==0  || empty jbdCalcDayKbList.id}">
						
						<button type="submit" class="btn btn_sele" name="back">
							<jecs:locale key="operation.button.save" />
						</button>
						
						<button type="submit" class="btn btn_sele" name="back" onclick="bCancel=true;return confirmDelete(jbdCalcDayKbListForm,'JbdCalcDayKbList')">
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
<!--/fieldset-->

<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JbdCalcDayKbList')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>


