<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=utf-8" language="java" %>

<title><jecs:locale key="jmiGradeLockDetail.title"/></title>
<content tag="heading"><jecs:locale key="jmiGradeLockDetail.heading"/></content>


<script type="text/javascript" src="<c:url value='/dwr/util.js'/>" ></script> 
<script type="text/javascript" src="<c:url value='/dwr/engine.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/dwr/interface/jmiMemberManager.js'/>" ></script>

<spring:bind path="jmiGradeLock.*">
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

<form:form commandName="jmiGradeLock" method="post" action="editJmiGradeLock.html" onsubmit="return validateOthers(this)"  id="jmiGradeLockForm">
<%-- 
	<div class="searchBar">	
		<jecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="operation.button.save"/>" />
		</jecs:power>

		
		<input type="button" class="button" name="cancel" onclick="window.history.back()" value="<jecs:locale key="operation.button.return"/>" />
	</div>
--%>	
	
<input type="hidden" name="strAction" value="${param.strAction }"/>


<table class='detail' width="70%">
<tbody class="window">
<form:hidden path="id"/>

    <tr class="edit_tr"><th><span class="text-font-style-tc">
        <jecs:label  key="miMember.memberNo"/>
    </span></th>
    <td>
    	<span class="textbox">
        <form:input path="userCode" id="userCode" cssClass="textbox-text"/>
        </span>
    	<img src="<c:url value="/images/l_1.gif"/>" id="person"  onclick="searchMiMember();"
    	style="cursor: pointer;" title="<jecs:locale key="operation.button.search"/>"/> 
    	<span id="memberName"></span>	
    </td><th><span class="text-font-style-tc">
        <jecs:label  key="bdBounsDeduct.wweek"/>
    </span></th>
    <td>
        <span class="textbox"><form:input path="validWeek" id="validWeek" cssClass="textbox-text"/></span>
   
    </td></tr>


    <tr class="edit_tr"><th><span class="text-font-style-tc">
        <jecs:label  key="miMember.gradeType"/>
    </span></th>
    <td>
        <!--form:errors path="newMemberLevel" cssClass="fieldError"/-->
       <span class="textbox">
         <jecs:list styleClass="textbox-text" name="gradeType" listCode="grade.type" 
         value="${jmiGradeLock.gradeType}" defaultValue="" showBlankLine="true" /> 
    	</span>
    </td></tr>
	<tr class="edit_tr">
		
		<td class="command" colspan="4" align="center">
			<jecs:power powerCode="${param.strAction}">
				<button type="submit" class="btn btn_sele" name="save"  onclick="bCancel=false">
					<jecs:locale key="operation.button.save"/>
				</button>
			</jecs:power>
			<button type="button" class="btn btn_sele" name="cancel" onclick="window.history.back()">
				<jecs:locale key="operation.button.return"/>
			</button>
		</td>
	</tr>
	</tbody>
    
</table>

</form:form>



<script>
 function searchMiMember(){
		   var loadinfo = "loading....." ; 
		   var userCode=document.getElementById('userCode').value;
		   jmiMemberManager.getJmiMember(userCode,callBack);
		       DWRUtil.useLoadingMessage(loadinfo);  
		   }
		   function callBack(valid){
			   if(valid==null){
			   	alert('<jecs:locale key="operation.notice.js.orderNo.miMember.memberNoError"/>');
			   }else if('${sessionScope.sessionLogin.companyCode}'!='AA'&&'${sessionScope.sessionLogin.companyCode}'!=valid.companyCode){
			   	alert('error');
			   }else{
				   document.getElementById('memberName').innerText=valid.petName;
				  
			   }
		   }
		   
		   function validateOthers(theForm){
				if(theForm.wweek.value=="" || theForm.wweek.value.length!=6 || isNaN(theForm.wweek.value)){
					alert("<jecs:locale key="week.isError"/>");
					theForm.wweek.focus();
					return false;
				}
				//setTimeout('showProgressBar()', 4000);
				return true;
			}
		   </script>