<%@ include file="/common/taglibs.jsp"%>

<script type="text/javascript" src="<c:url value='/dwr/util.js'/>" ></script> 
<script type="text/javascript" src="<c:url value='/dwr/engine.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/dwr/interface/alCityManager.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/dwr/interface/sysBankManager.js'/>" ></script>
<script type="text/javascript" src="./scripts/jquery/jquery-142min.js"> </script> 
<spring:bind path="jmiMember.*">
    <c:if test="${not empty status.errorMessages}">
    <div class="error">    
        <c:forEach var="error" items="${status.errorMessages}">
            <img src="<c:url value="images/newIcons/warning_16.gif"/>"
                alt="<jecs:locale key="icon.warning"/>" class="icon" />
            <c:out value="${error}" escapeXml="false"/><br />
        </c:forEach>
    </div>
    </c:if>
</spring:bind>

<script type="text/javascript" >
var jq = jQuery.noConflict();
function changeBankInfo(valid){
	if(valid=='0'){
		document.getElementById('bankAddressId').innerHTML='<jecs:label key="miMember.bcity"  />';
		document.getElementById('bankbookId').innerHTML='<jecs:label key="miMember.bname"  />';
		
		viewBank(true,'iText one','');
	}else if(valid=='1'){
		document.getElementById('bankAddressId').innerHTML='<jecs:label key="miMember.post.num"  />';
		document.getElementById('bankbookId').innerHTML='<jecs:label key="miMember.bname.1"  />';
		viewBank(false,'iText','none');
	}

}
function viewBank(flag,className,dis){
		
		bankFlag=flag;
		jq('#bankNo')[0].className = className;
		jq('#bankNameKana')[0].className = className;
		jq('#bank')[0].className = className;
		jq('#townAddr')[0].className = className;
		
		//jq('#bankbookDiv')[0].style.display = dis;
		jq('#bankNameKanaDiv')[0].style.display = dis;
		jq('#bankDiv')[0].style.display = dis;
		jq('#bankNoDiv')[0].style.display = dis;
		jq('#townAddrDiv')[0].style.display = dis;
		
		
		jq('#bankCodeDiv')[0].style.display = dis;
		jq('#bankKanaDiv')[0].style.display = dis;
		
		if(flag==false){
			jq('#bankNameKana')[0].value = '';
			jq('#bank')[0].value = '';
			jq('#bankNo')[0].value = '';
			jq('#townAddr')[0].value = '';
			jq('#bankCode')[0].value = '';
			jq('#bankKana')[0].value = '';
		}
		
}

</script>

<form:form commandName="jmiMember" action="miMemberBankManageForm.html" name="miMemberBankManageForm" id="miMemberBankManageForm" method="post" >
<%--
	<div class="searchBar">
	<c:if test="${param.strAction=='editMiMemberBank' }">
	<jecs:power powerCode="editMiMemberBank">
	<input type="submit" class="button" name="save"  value="<jecs:locale key="operation.button.save"/>" />
	</jecs:power>
	</c:if>	
	<input type="button" class="button" name="back"  onclick="window.location.href='miMemberBankManageList.html'" value="<jecs:locale key="operation.button.return"/>" />
	
	</div>
--%>
<input type="hidden" name="strAction" value="${param.strAction }"/>
		<form:hidden path="userCode"/>
		
<table class="detail" width="70%">
<tbody class="window">		
	<tr class="edit_tr">
		<th>
			<span class="text-font-style-tc"><jecs:label key="miMember.memberNo"/></span>
		</th>
		<td>
			<span class="textbox">${jmiMember.userCode}</span>
		</td>	
		<th>
			<span class="text-font-style-tc"><jecs:label key="miMember.petName"/></span>
		</th>
		<td>
			<span class="textbox">${jmiMember.petName}</span>
		</td>
	</tr>
		
		<%--
      <c:if test="${sessionScope.sessionLogin.companyCode=='JP'}">
      
      
      
		<tr>
			<th>
					<jecs:label key="miMember.firstNameKana"/>
			</th>
			<td>
				${jmiMember.firstNameKana}
			</td>
		</tr>
		<tr>
			<th>
					<jecs:label key="miMember.lastNameKana"/>
			</th>
			<td>
				${jmiMember.lastNameKana}
			</td>
		</tr>
      
      
		<tr>
			<th>
				<jecs:label key="jp.bank.type"/>
			</th>
			<td>
				<c:if test="${param.strAction=='viewMiMemberBank' }">
					<jecs:code listCode="jp.bank.type" value="${jmiMember.bankType}"/>
				</c:if>
				<c:if test="${param.strAction=='editMiMemberBank' }">
					<jecs:list name="bankType" id="bankType" listCode="jp.bank.type" value="${jmiMember.bankType}" defaultValue="0" onchange="javascript:changeBankInfo(this.value)"/>		
				</c:if>
				
			</td>
		</tr>
		
		<c:if test="${param.strAction=='editMiMemberBank' }">
			<tr id="bankNoDiv">
				<th>
					<jecs:label key="miMember.bankNo"/>
				</th>
				<td>
					<input id="bankNo" value="" name="bankNo" type="text" class="textbox-text"/>
					 <input type="button" class="button" onclick="getBankByNo();" value="<jecs:locale key="operation.button.search"/>" />
				</td>
			</tr>
			<tr id="bankNameKanaDiv">
				<th>
					<jecs:label key="jp.bankName.kana"/>
				</th>
				<td>
					<input id="bankNameKana" name="bankNameKana" value="${param.bankNameKana }" type="text" class="textbox-text" disabled="disabled"/>
				</td>
			</tr>
		</c:if>
		
		</c:if>
		--%>
	<tr id="bankDiv" class="edit_tr">
		<th>
			<span class="text-font-style-tc"><jecs:label key="miMember.openBank"/></span>
		</th>
		<td>
			<span class="textbox">
			<c:if test="${param.strAction=='viewMiMemberBank' }">
				${jmiMember.bank }
			</c:if>
			<c:if test="${param.strAction=='editMiMemberBank' }">
				<c:if test="${sessionScope.sessionLogin.companyCode=='JP' }">
		      		<form:input path="bank" cssClass="textbox-text" readonly="true"/>
		      	</c:if>
		      	<c:if test="${sessionScope.sessionLogin.companyCode=='US' }">
		     	 	<form:input path="bank" cssClass="textbox-text" />
		      	</c:if>
		      	<c:if test="${sessionScope.sessionLogin.companyCode!='JP' && sessionScope.sessionLogin.companyCode!='US'}">
					<form:select path="bank"  cssClass="textbox-text">
		            	<form:options items="${sysBankList}" itemValue="bankKey" itemLabel="bankValue"/>
		        	</form:select>
		      	</c:if>
	        </c:if>
			</span>
		</td>
	
		<th id="bankAddressId">
			<span class="text-font-style-tc"><jecs:label key="miMember.bcity" required="true"/></span>
		</th>
		<td>
			<span class="textbox">
			<c:if test="${param.strAction=='viewMiMemberBank' }">
				${jmiMember.bankaddress }
			</c:if>
			<c:if test="${param.strAction=='editMiMemberBank' }">
				<form:input path="bankaddress" id="bankaddress" cssClass="textbox-text"/>	
			</c:if>
			</span>
		</td>
	</tr>
		
		<%-- 
      <c:if test="${sessionScope.sessionLogin.companyCode=='JP' || sessionScope.sessionLogin.companyCode=='US'}">
		<tr id="bankCodeDiv">
			<th>
				<jecs:label key="miMember.bankCode"/>
			</th>
			<td>
				<c:if test="${param.strAction=='viewMiMemberBank' }">
					${jmiMember.bankCode }
				</c:if>
				<c:if test="${param.strAction=='editMiMemberBank' }">
					<form:input path="bankCode" id="bankCode" cssClass="textbox-text"/>	
				</c:if>
				
			</td>
		</tr>
		</c:if>
		<c:if test="${sessionScope.sessionLogin.companyCode=='JP'}">
		
		<tr id="bankKanaDiv">
			<th>
				<jecs:label key="miMember.bankKana"/>
			</th>
			<td>
				<c:if test="${param.strAction=='viewMiMemberBank' }">
					${jmiMember.bankKana }
				</c:if>
				<c:if test="${param.strAction=='editMiMemberBank' }">
					<form:input path="bankKana" id="bankKana" cssClass="textbox-text"/>	
				</c:if>
				
			</td>
		</tr>
		</c:if>
		--%>
		
	<tr class="edit_tr">
		<th id="bankbookId" >
			<span class="text-font-style-tc"><jecs:label key="miMember.bname" required="true"/></span>
		</th>
		<td>
			<span class="textbox">
			<c:if test="${param.strAction=='viewMiMemberBank' }">
				${jmiMember.bankbook }
			</c:if>
			<c:if test="${param.strAction=='editMiMemberBank' }">
				<form:input path="bankbook" id="bankbook" cssClass="textbox-text" />
			</c:if>
			</span>
		</td>
		<th>
			<span class="text-font-style-tc"><jecs:label key="miMember.bnum" required="true"/></span>
		</th>
		<td>
			<span class="textbox">
			<c:if test="${param.strAction=='viewMiMemberBank' }">
				${jmiMember.bankcard }
			</c:if>
			<c:if test="${param.strAction=='editMiMemberBank' }">
				<form:input path="bankcard" id="bankcard" cssClass="textbox-text" size="50"/>
			</c:if>
			</span>
		</td>
	</tr>
	<%-- 
      <c:if test="${sessionScope.sessionLogin.companyCode=='JP'}">
		<tr id="townAddrDiv">
			<th>
				
				<jecs:label key="jp.bank.port"/>
			</th>
			<td>
				<c:if test="${param.strAction=='viewMiMemberBank' }">
					<jecs:code listCode="jp.bank.port" value="${jmiMember.townAddr}"/>
				</c:if>
				<c:if test="${param.strAction=='editMiMemberBank' }">
					<jecs:list name="townAddr" id="townAddr"  listCode="jp.bank.port" value="${jmiMember.townAddr}" defaultValue="" showBlankLine="true"/>	
				</c:if>
				
			</td>
		</tr>
		</c:if>
		--%>
	<tr class="edit_tr">
		<th>
			<span class="text-font-style-tc">
			<c:if test="${sessionScope.sessionLogin.companyCode!='PH'}">
				<jecs:label key="miMember.bankProvince" required="true"/>
		   	</c:if>
	      	<c:if test="${sessionScope.sessionLogin.companyCode=='PH'}">
		   		<jecs:label key="miMember.island" required="true" />
		   	</c:if>
		   	</span>
		</th>
		<td>
			<span class="textbox">
			<c:if test="${param.strAction=='viewMiMemberBank' }">		
				<form:select path="bankProvince"  cssClass="textbox-text" onchange="getBankCity()" disabled="true">
					<form:option label="" value=""/>
		            <form:options items="${alStateProvinces}" itemValue="stateProvinceId" itemLabel="stateProvinceName"/>
				</form:select>
			</c:if>
			<c:if test="${param.strAction=='editMiMemberBank' }">
				<form:select path="bankProvince"  cssClass="textbox-text" onchange="getBankCity()">
					<form:option label="" value=""/>
		            <form:options items="${alStateProvinces}" itemValue="stateProvinceId" itemLabel="stateProvinceName"/>
				</form:select>
			</c:if>
			</span>
		</td>
		<th>
			<span class="text-font-style-tc">
	    	<c:if test="${sessionScope.sessionLogin.companyCode!='PH'}">
				<jecs:label key="miMember.bankCity" required="true"/>
		   	</c:if>
	      	<c:if test="${sessionScope.sessionLogin.companyCode=='PH'}">
		   		<jecs:label key="miMember.region" required="true" />
		   	</c:if>
		   	</span>
		</th>
		<td>
			<span class="textbox">
			<c:if test="${param.strAction=='viewMiMemberBank' }">
				<select name="bankCity" id="bankCity" disabled="disabled" class="textbox-text">
					<option value=""><jecs:locale key="list.please.select"/></option>
				</select>
			</c:if>
			<c:if test="${param.strAction=='editMiMemberBank' }">
		        <select name="bankCity" id="bankCity" class="textbox-text">
					<option value=""><jecs:locale key="list.please.select"/></option>
				</select>
			</c:if>
			</span>	
		</td>
	</tr>
	<tr class="edit_tr">
		<th>
			<span class="text-font-style-tc-tare"><jecs:label key="miMember.remark"/></span>
		</th>
		<td colspan="3">
			<span class="text-font-style-tc-right">
				<c:if test="${param.strAction=='viewMiMemberBank' }">
					<%-- <span class="textbox">${jmiMember.remark }</span> --%>
					<form:textarea path="remark" id="remark" cssClass="textarea_border"/>
				</c:if>
				<c:if test="${param.strAction=='editMiMemberBank' }">
						<form:textarea path="remark" id="remark" cssClass="textarea_border"/>
				</c:if>
				</span>
		</td>
	</tr>
	<tr>
		<td class="command" colspan="4" align="center">
			<c:if test="${param.strAction=='editMiMemberBank' }">
				<jecs:power powerCode="editMiMemberBank">
					<button type="submit" class="btn btn_sele" name="save">
						<jecs:locale key="operation.button.save"/>
					</button>
				</jecs:power>
			</c:if>	
			<button type="button" class="btn btn_sele" name="back"  
				onclick="window.location.href='miMemberBankManageList.html'">
				<jecs:locale key="operation.button.return"/>
			</button>
		</td>
	</tr>
</tbody>
</table>
</form:form>



<script>
		   function getBankCity(){
		   
			<c:if test="${sessionScope.sessionLogin.companyCode!='US'}">
		  	 var bankProvince=document.getElementById('bankProvince').value;
		   	 alCityManager.getAlCityByProvinceId(bankProvince,callBackBankCity);
		   </c:if>  
		   }
		   function callBackBankCity(valid){
			   var cityElemment=$('bankCity');
        		cityElemment.options.length=0;
          		var o=new Option("<jecs:locale key="list.please.select"/>","");
           		cityElemment.options.add(o);  
           
		       for (var i=0;i<valid.length;i++) {
			        var cityId= valid[i].cityId;	   
			        var cityName=valid[i].cityName;   
			        var o=new Option(cityName,cityId);
			        cityElemment.options.add(o);  
			        if(cityId=='${jmiMember.bankCity}'){
			        	o.selected=true;
			        }
			   }
		   }
		   
      <c:if test="${sessionScope.sessionLogin.companyCode!='JP' && sessionScope.sessionLogin.companyCode!='US' }">
		   getBankCity();
		 
		 </c:if>  
		function getBankByNo(){
		var bankNo=document.getElementById('bankNo').value;
		if(bankNo!=''){
			sysBankManager.getJsysBankByBankNo(bankNo,callBackGetBankByNo);
		}
		
	}
	function callBackGetBankByNo(valid){
		if(valid!=null){
			document.getElementById('bank').value=valid.bankValue;
			document.getElementById('bankNameKana').value=valid.bankKana;
		}
	}
		   
		   
</script>


		 <c:if test="${jmiMember.bankType=='1'&& param.strAction=='editMiMemberBank' }">  
		 	
<script>
	changeBankInfo("1");
</script>


		 </c:if>
		   