<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=utf-8" language="java" %>

<title><jecs:locale key="jmiValidLevelListDetail.title"/></title>
<content tag="heading"><jecs:locale key="jmiValidLevelListDetail.heading"/></content>


<script type="text/javascript" src="<c:url value='/dwr/util.js'/>" ></script> 
<script type="text/javascript" src="<c:url value='/dwr/engine.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/dwr/interface/jmiMemberManager.js'/>" ></script>

<spring:bind path="jmiValidLevelList.*">
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

<form:form commandName="jmiValidLevelList" method="post" action="editJmiValidLevelList.html" onsubmit="return validateOthers(this)"  id="jmiValidLevelListForm">
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
     	 <c:if test="${not empty jmiValidLevelList.id}">
	        	<span class="textbox">${jmiValidLevelList.userCode }</span>
	        	<input type="hidden" id="userCode" name="userCode" value="${ jmiValidLevelList.userCode}">
	        </c:if>
	        <c:if test="${empty jmiValidLevelList.id}">
	        	<span class="textbox">
	        	<form:input path="userCode" id="userCode" cssClass="textbox-text"/>
	        	</span>
	        </c:if>
    		<img src="<c:url value="/images/l_1.gif"/>" id="person"  onclick="searchMiMember();" 
    		style="cursor: pointer;" title="<jecs:locale key="operation.button.search"/>"/> 
    	<span id="memberName"></span>	
    </td>

	<th><span class="text-font-style-tc">
        	<jecs:label key="cur.cardType"/>
    </span></th>
    <td >
    	<span id="memberCardType" class="textbox"></span>	
    </td>
    </tr>
    
    <tr class="edit_tr"><th><span class="text-font-style-tc">
        <jecs:label  key="bdBounsDeduct.wweek"/>
    </span></th>
    <td>
    	<c:if test="${param.strAction=='addJmiValidLevelList' }">
    	<span class="textbox">
        <form:input path="wweek" id="wweek" cssClass="textbox-text"/>
        </span>
    	</c:if>
    	<c:if test="${param.strAction=='editJmiValidLevelList' }">
    		
    		<span class="textbox"><jecs:weekFormat week="${jmiValidLevelList.wweek }" weekType="w"></jecs:weekFormat></span>
    	</c:if>
    </td>

   <%--  <tr class="edit_tr"><th><span class="text-font-style-tc">
        <jecs:label  key="member.oldCard"/>
    </span></th>
    <td>
    	<jecs:code listCode="member.level" value="${jmiValidLevelList.oldMemberLevel}"/>
    </td></tr> --%>

    <th><span class="text-font-style-tc">
        <jecs:label  key="member.newCard"/>
    </span></th>
    <td>
        <!--form:errors path="newMemberLevel" cssClass="fieldError"/-->
       <span class="textbox">
         <jecs:list styleClass="textbox-text" name="newMemberLevel" listCode="member.level.new" value="${jmiValidLevelList.newMemberLevel}" defaultValue="0" /> 
    	</span>
    </td></tr>

   <%--  <tr class="edit_tr"><th><span class="text-font-style-tc">
        <jecs:label  key="sysClickLog.isValid"/>
    </span></th>
    <td>
         <jecs:list styleClass="textbox-text" name="isLock" listCode="yesno" value="${jmiValidLevelList.isLock}" defaultValue="1" />
    </td></tr> --%>
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
				   var memberLevel='';
				   	if('0'==valid.memberLevel){
				   		memberLevel="待审会员";
				   	}else if('5'==valid.memberLevel){
				   		memberLevel="优惠顾客";
				   	}else if('10'==valid.memberLevel){
				   		memberLevel="一级";
				   	}else if('20'==valid.memberLevel){
				   		memberLevel="二级";
				   	}else if('30'==valid.memberLevel){
				   		memberLevel="三级";
				   	}else if('40'==valid.memberLevel){
				   		memberLevel="四级";
				   	}else if('50'==valid.memberLevel){
				   		memberLevel="五级";
				   	}else if('60'==valid.memberLevel){
				   		memberLevel="六级";
				   	}else if('70'==valid.memberLevel){
				   		memberLevel="七级";
				   	}else if('80'==valid.memberLevel){
				   		memberLevel="八级";
				   	}else if('90'==valid.memberLevel){
				   		memberLevel="九级";
				   	}else if('1000'==valid.memberLevel){
				   		memberLevel="优惠顾客";
				   	}else if('1500'==valid.memberLevel){
				   		memberLevel="推广员";
				   	}else if('2000'==valid.memberLevel){
				   		memberLevel="永久优惠顾客";
				   	}else if('2500'==valid.memberLevel){
				   		memberLevel="vip级";
				   	}else if('3000'==valid.memberLevel){
				   		memberLevel="三级代理";
				   	}else if('4000'==valid.memberLevel){
				   		memberLevel="二级代理";
				   	}else if('5000'==valid.memberLevel){
				   		memberLevel="一级代理";
				    }else if('500'==valid.memberLevel){
				   		memberLevel="云客";
				    }
				   
				   document.getElementById('memberCardType').innerText=memberLevel;
			   }
		   }
		   
		   function validateOthers(theForm){
				if(theForm.wweek.value=="" || theForm.wweek.value.length!=6 || isNaN(theForm.wweek.value)){
					alert('<jecs:locale key="week.isError"/>');
					theForm.wweek.focus();
					return false;
				}
				//setTimeout('showProgressBar()', 4000);
				return true;
			}
		   
		   $(function(){

			    $(":button").click(function() {

			        $("#test option").each(function() {

			            var val = $(this).val();

			            if ( $("#test option[value='" + val + "']").length > 1 )

			                $("#test option[value='" + val + "']:gt(0)").remove(); 

			        });

			    });

			});

		   </script>