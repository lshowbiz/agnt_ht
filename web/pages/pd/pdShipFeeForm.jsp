<%@ page pageEncoding="UTF-8"%> 
<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="pdShipFeeDetail.title"/></title>
<content tag="heading"><jecs:locale key="pdShipFeeDetail.heading"/></content>

<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
<!-- 				<input type="button" class="button" name="save"  onclick="savePdShipFee(document.pdShipFeeForm)" value="保存"  />
 -->	
        <button type="button" class="btn btn_sele" name="save"  onclick="savePdShipFee(document.pdShipFeeForm)" >
		           保存
		</button>	
        </jecs:power>
		<c:if test="${param.strAction eq 'editPdShipFee'}">
			<jecs:power powerCode="deletePdShipFee">
<!-- 					<input type="submit" class="button" name="delete" onclick="deletePdShipFeeF(document.pdShipFeeForm)" value="删除" />
 -->			
			<button type="submit" class="btn btn_sele" name="delete"  onclick="deletePdShipFeeF(document.pdShipFeeForm)" >
			           删除
			</button>	
			</jecs:power>
		</c:if>
<!-- 		<input type="button" class="button" onclick="javascript:history.back();" value="返回" />
 -->	
            <button type="button" class="btn btn_sele" onclick="javascript:history.back();" >
			           返回
			</button>	
</c:set>

<spring:bind path="pdShipFee.*">
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

<form:form commandName="pdShipFee" method="post" action="editPdShipFee.html" onsubmit="return validatePdShipFee(this)" id="pdShipFeeForm" name="pdShipFeeForm">

<input type="hidden" name="strAction" value="${param.strAction }" id="strAction"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('PdShipFee')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
<table class='detail' width="70%">
<tbody class="window" >
<form:hidden path="psfId"/>
<tr height="50px"></tr>
    <tr class="edit_tr">
    <th>
        <span class="text-font-style-tc">省/市：</span>
    </th>
    <td>
        <!--form:errors path="provinceName" cssClass="fieldError"/
        <form:input path="provinceName" id="provinceName" cssClass="text medium"/>
        -->
         <span class="textbox"><select name="provinceName" class="textbox-text" <c:if test="${not empty pdShipFee.provinceName}">disabled</c:if> >
			<c:forEach items="${alStateProvinces}" var="alStateProvince">
				<option value="${alStateProvince.stateProvinceId}" 
				<c:if test="${alStateProvince.stateProvinceId eq pdShipFee.provinceName}">selected</c:if> >
						${alStateProvince.stateProvinceName}
				</option>
			</c:forEach>
		</select></span>
    </td>

    <th>
        	<span class="text-font-style-tc">物流费：</span>
    </th>
    <td>
        <!--form:errors path="fee" cssClass="fieldError"/-->
        <span class="textbox"><form:input path="fee" id="fee" cssClass="textbox-text" maxlength="6" onkeypress="if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onkeyup="if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" /></span>
    </td>
    </tr>
    <tr height="15px"></tr>
        <tr class="edit_tr">
		<td class="command"  colspan="4" align="center">
			<c:out value="${buttons}" escapeXml="false"/>
		</td>
	</tr>
    
<%-- <div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div> --%>
</tbody>
</table>
<!--/fieldset-->

<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('PdShipFee')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('pdShipFeeForm'));
    
    function deletePdShipFeeF(theForm){
        if(confirm("<jecs:locale key="amMessage.confirmdelete"/>")){
             document.getElementById("strAction").value="deletePdShipFee";
             theForm.submit();
        }  
    }
    
    function savePdShipFee(theForm){
          var fee = document.getElementById("fee").value;
          if(""==fee || null==fee){
              alert("请输入物流费");
          }else{
              theForm.submit();
          }
    
    }
</script>

<v:javascript formName="pdShipFee" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
