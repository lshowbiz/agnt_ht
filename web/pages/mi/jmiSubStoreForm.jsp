<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%-- 
<style type="text/css">

table.detail {
	
	font-weight: normal;
	border-collapse:collapse;
	position: relative;
	border: 1px solid #dadada;
}

table.detail th {
	width:150px;
	border: 1px solid #dadada;
	color: #333333;
	text-align: right;
	background: #f0f0f0;
	height: 20px;
	padding: 4px;
	white-space: nowrap;
}

table.detail th label {
	width:100%;
	text-align: right;
}

table.detail th.tallCell {
    vertical-align: top;
}

table.detail th.command{
	border: 1px solid #dadada;
	color: #165AB3;
	text-align: right;
	background: #f0f0f0;
	height: 20px;
	padding: 4px;
}

table.detail td {
	border: 1px solid #dadada;
	color: black;
	padding: 4px;
}

table.detail td.moveOptions {
    text-align: center;
    width: 50px;
    padding: 4px;
}

table.detail td.moveOptions button {
    margin-bottom: 3px;
    width: 45px;
    white-space: nowrap;
}

table.detail td.command{
	border: 1px solid #dadada;
	color: black;
	padding: 4px;
	background: #f0f0f0;
}

table.detail td.title{
	border: 1px solid #dadada;
	color: #6B91C9;
	background: #E1E9F4;
	height: 20px;
	padding: 4px;
	white-space: nowrap;
	font-weight: bold;
}

table.detail td.buttonBar {
    padding-top: 10px;
}

table.detail td.updateStatus {
    font-size: 11px;
    color: #c0c0c0;
}

table.detailSub td{
	border: none;
	padding: 1px;
}

table.inSideTable {
	font-weight: normal;
	border-collapse:collapse;
	position: relative;
}

table.inSideTable td {
	border: 1px none #dadada;
	color: black;
	padding: 4px;
}
</style>
--%>
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script> 
<script type="text/javascript" src="./scripts/validate.js"> </script> 
<script type="text/javascript" src="./scripts/jquery/jquery-142min.js"> </script> 

<script type="text/javascript" src="<c:url value='/dwr/util.js'/>" ></script> 
<script type="text/javascript" src="<c:url value='/dwr/engine.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/dwr/interface/alCityManager.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/dwr/interface/alDistrictManager.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/dwr/interface/jmiMemberManager.js'/>" ></script>

<script type="text/javascript" >
		   function searchMiMember(){
		   var loadinfo = "loading....." ; 
		   var userCode=document.getElementById('miMember.userCode').value;
		   jmiMemberManager.getJmiMember(userCode,callBack);
		       DWRUtil.useLoadingMessage(loadinfo);  
		   }
		     function callBack(valid){
			   if(valid==null){
			   		alert('<jecs:locale key="operation.notice.js.orderNo.miMember.memberNoError"/>');
			   }else{
				   document.getElementById('miUserName').innerText=valid.miUserName;
				   if(valid.sex=='M'){
				   	   document.getElementById('sex').innerText="<jecs:locale key='sex.male'/>";
				   }else if(valid.sex=='F'){
				   	   document.getElementById('sex').innerText="<jecs:locale key='sex.female'/>";
				   }
				   document.getElementById('mobiletele').innerText=valid.mobiletele;
				   if(valid.papertype=='1'){
				   	   document.getElementById('papertype').innerText="<jecs:locale key='miMember.idNo'/>";
				   }else if(valid.papertype=='2'){
				   	   document.getElementById('papertype').innerText="<jecs:locale key='papertype.2'/>";
				   }else if(valid.papertype=='3'){
				   	   document.getElementById('papertype').innerText="<jecs:locale key='papertype.3'/>";
				   }
				   document.getElementById('papernumber').innerText=valid.papernumber;
				   document.getElementById('jmiMember.subRecommendStore1').innerText=
					   '<span class="textbox">'+valid.recommendNo+'</span>';
				   document.getElementById('jmiMember.subRecommendStore').value=valid.recommendNo;
				   searchSubRecommendStore();
			   }
			   }
			   
			 function searchSubRecommendStore(){
			   var loadinfo = "loading....." ; 
			   var userCode=document.getElementById('jmiMember.subRecommendStore1').innerText;
			   jmiMemberManager.getJmiMember(userCode,callBackSubRecommendStore);
			       DWRUtil.useLoadingMessage(loadinfo);  
		   	} 
		   function callBackSubRecommendStore(valid){
			   if(valid==null){
			   		alert('<jecs:locale key="operation.notice.js.orderNo.miMember.memberNoError"/>');
			   }else{
			   	 document.getElementById('jmiMember.subRecommendStore.name').innerText=
			   		 '<span class="textbox">'+valid.miUserName+'</span>';
			   }
			}
			   
			  function checkStore(strAction){
					$('#strAction').val(strAction);
					$('#jmiSubStoreForm').submit();
				}
			   
</script>
	
		

<title><jecs:locale key="jmiSubStoreDetail.title"/></title>
<content tag="heading"><jecs:locale key="jmiSubStoreDetail.heading"/></content>

<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<c:if test="${param.strAction!='viewJmiSubStore' }">
					
					<c:if test="${isFirstBuySeat=='isFirstBuySeat' }">
						<button type="submit" class="btn btn_sele" name="save"  onclick="bCancel=false">
							<jecs:locale key="register.agree"/>
						</button>
						<%-- 
						<input type="submit" class="button" name="save"  onclick="bCancel=false" 
							value="<jecs:locale key="register.agree"/>" />
						--%>
					</c:if>
					<c:if test="${isFirstBuySeat!='isFirstBuySeat' }">
						<button type="submit" class="btn btn_sele" name="save"  onclick="bCancel=false">
							<jecs:locale key="operation.button.save"/>
						</button>
						<%-- 
						<input type="submit" class="button" name="save"  onclick="bCancel=false" 
							value="<jecs:locale key="operation.button.save"/>" />
						--%>
					</c:if>
				
				
				
				</c:if>
				
				
		</jecs:power>
		

		<%--<jecs:power powerCode="jmiSubStorePrint">
		<input type="button" name="btnPrint" value="<jecs:locale key="button.print"/>" class="button" onclick="go_print()"/>
		</jecs:power>
		
		
		<jecs:power powerCode="checkJmiSubStore">
			<input type="button" class="button" name="save"  onclick="checkStore('checkJmiSubStore')" value="<jecs:locale key="button.audit"/>" />
		</jecs:power>
		
		<jecs:power powerCode="unCheckJmiSubStore">
			<input type="button" class="button" name="save"  onclick="checkStore('unCheckJmiSubStore')" value="<jecs:locale key="button.notCheck"/>" />
		</jecs:power>
		
		<jecs:power powerCode="confirmJmiSubStore">
			<input type="button" class="button" name="save"  onclick="checkStore('confirmJmiSubStore')" value="<jecs:locale key="operation.button.confirm"/>" />
		</jecs:power>
		
		<jecs:power powerCode="unConfirmJmiSubStore">
			<input type="button" class="button" name="save"  onclick="checkStore('unConfirmJmiSubStore')" value="<jecs:locale key="button.bdUnCheck"/>" />
		</jecs:power>--%>
	
	
		<c:if test="${isFirstBuySeat=='isFirstBuySeat' }">
			<button type="submit" class="btn btn_sele" name="save"   onclick="document.getElementById('isFirstBuySeat').value='isFirstBuySeat_No';"  >
				<jecs:locale key="register.unagree"/>
			</button>
			<input type="hidden" value="" id="isFirstBuySeat" name="isFirstBuySeat" >
		</c:if>
		
		<button type="button" class="btn btn_sele" name="cancel" onclick="window.history.back()" >
			<jecs:locale key="operation.button.return"/>
		</button>
</c:set>

<spring:bind path="jmiSubStore.*">
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


<form:form commandName="jmiSubStore" method="post" action="editJmiSubStore.html"   id="jmiSubStoreForm" name="jmiSubStoreForm"  onsubmit="if(isFormPosted()){return true;}{return false;}">
 <%-- 
<div  class="noPrint" id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
--%>
<input type="hidden" name="strAction" value="${param.strAction }" id="strAction"/>

<fieldset style="border-top: 2px solid #cccccc;border-right: 0px ;border-bottom: 0px ;border-left: 0px ;font-family: Arial, Helvetica, sans-serif;font-weight:bold;">
<legend ><jecs:locale key="member.base.info"/>:</legend>

<form:hidden path="id"/>

<table class='detail' width="70%">
<tbody class="window">
    <tr class="edit_tr">
	    <th>
		    <span class="text-font-style-tc"><jecs:label  key="miMember.memberNo"/></span>
	    </th>
	    <td>
	    	<c:if test="${param.strAction=='addJmiSubStore' &&  sessionScope.sessionLogin.userType=='C'}">
	   			<span class="textbox">
	   			<form:input path="jmiMember.userCode" id="miMember.userCode" cssClass="textbox-text"/>
	   			<img src="<c:url value="/images/l_1.gif"/>" id="person"  onclick="searchMiMember();" 
	   				style="cursor: pointer;" title="<jecs:locale key="operation.button.search"/>"/>
	   			</span> 
	   		</c:if>
	    	<c:if test="${param.strAction!='addJmiSubStore' ||  sessionScope.sessionLogin.userType!='C'}">
		 	 	<span class="textbox">${jmiSubStore.jmiMember.userCode }</span>
	    	</c:if>
	    </td>
	    <th>
		 	<span class="text-font-style-tc"><jecs:label key="bdCalculatingSubDetail.name" /></span>
	    </th>
	    <td>
	    	<span class="textbox">${jmiSubStore.jmiMember.miUserName }</span>
	    	<span id="miUserName"></span>
	    </td>
	</tr>
    
    <tr class="edit_tr">
	    <th>
		    <span class="text-font-style-tc"><jecs:label  key="miMember.sex"/></span>
	    </th>
	    <td>
	        <span class="textbox"><jecs:code listCode="sex" value="${jmiSubStore.jmiMember.sex}"/></span>
	        <span id="sex"></span>
	    </td>
	    <th>
	        <span class="text-font-style-tc"><jecs:label  key="miMember.mobiletele"/></span>
	    </th>
	    <td>
	    	<span class="textbox">${jmiSubStore.jmiMember.mobiletele }</span>
	    	<span id="mobiletele"></span>
	    </td>
	</tr>
    
    <tr class="edit_tr">
	    <th>
	        <span class="text-font-style-tc"><jecs:label  key="miMember.papertype"/></span>
	    </th>
	    <td>
	        <span class="textbox"><jecs:code listCode="papertype" value="${jmiSubStore.jmiMember.papertype}"/></span>
	        <span id="papertype" class="textbox-text"></span>
	    </td>
	    <th>
	        <span class="text-font-style-tc"><jecs:label  key="miMember.papernumber"/></span>
	    </th>
	    <td>
	    	<span class="textbox">${jmiSubStore.jmiMember.papernumber }</span>
	    	<span id="papernumber" class="textbox-text"></span>
	    </td>
	</tr>

    <tr class="edit_tr">
	    <th>
			<span class="text-font-style-tc"><jecs:label  key="miMember.subRecommendStore" required="true"/></span>
	    </th>
	    <td>
	    	<c:if test="${ sessionScope.sessionLogin.userType=='C' && param.strAction=='editJmiSubStore'}">
	   			<c:if test="${empty recommendStoreModify}">
		    		<form:input path="jmiMember.subRecommendStore" id="jmiMember.subRecommendStore" cssClass="textbox-text"/>
			    	<span id="jmiMember.subRecommendStore1" style="display: none" class="textbox-text"></span>
		    	</c:if>
		    
		    	<c:if test="${recommendStoreModify=='yes'}">
		    		<span id="jmiMember.subRecommendStore1" class="textbox-text">${jmiSubStore.jmiMember.subRecommendStore }</span>
		    		<input type="hidden" id="jmiMember.subRecommendStore" name="jmiMember.subRecommendStore" 
		    			value="${jmiSubStore.jmiMember.subRecommendStore }" >
		    	</c:if>
	    	</c:if>
	   		<c:if test="${ sessionScope.sessionLogin.userType!='C'|| param.strAction!='editJmiSubStore'}">
	    		<span id="jmiMember.subRecommendStore1" class="textbox">
	    			${jmiSubStore.jmiMember.subRecommendStore }
	    		</span>
	    		<input type="hidden" id="jmiMember.subRecommendStore" name="jmiMember.subRecommendStore"
	    			 value="${jmiSubStore.jmiMember.subRecommendStore }" >
	   		</c:if>
	    </td>
	    <th>
		    <span class="text-font-style-tc"><jecs:label  key="miMember.subRecommendStore.name"/></span>
	    </th>
	    <td>
	    	<span id="jmiMember.subRecommendStore.name">
		    	<c:if test="${not empty recommendStoreName }">
		    		<span class="textbox">${recommendStoreName }</span>
		    	</c:if>
	    	</span>
	    </td>
	</tr>
</tbody>
</table>

</fieldset>

<fieldset style="border-top: 2px solid #cccccc;border-right: 0px ;border-bottom: 0px ;border-left: 0px ;font-family: Arial, Helvetica, sans-serif;font-weight:bold;">
<legend ><jecs:locale key="subStore.address.info"/>:</legend>


<table class='detail' width="70%">
<tbody class="window">
    <tr class="edit_tr">
	    <th>
		    <span class="text-font-style-tc"><jecs:label  key="subStore.province"  required="true"/></span>
	    </th>
	    <td>
	    	<span class="textbox">
	    	<c:if test="${modifyStatus=='yes' }">
	        	<form:select path="province"  cssClass="textbox-text" onchange="getCity()">
					<form:option label="" value=""><jecs:locale key='list.please.select'/></form:option>
		            <form:options items="${alStateProvinces}" itemValue="stateProvinceId" itemLabel="stateProvinceName" />
				</form:select>
	    	</c:if>
	    	<c:if test="${modifyStatus=='no' }">
	        	<form:select path="province"  cssClass="textbox-text" onchange="getCity()" disabled="true">
					<form:option label="" value=""><jecs:locale key='list.please.select'/></form:option>
		            <form:options items="${alStateProvinces}" itemValue="stateProvinceId" itemLabel="stateProvinceName" />
				</form:select>
	    	</c:if>
	    	</span>
	    </td>
	    <th>
		    <span class="text-font-style-tc"><jecs:label  key="subStore.city"  required="true"/></span>
	    </th>
	    <td>
	    	<span class="textbox">
	    	<c:if test="${modifyStatus=='yes' }">
		        <select name="city" id="city" class="textbox-text">
					<option value=""><jecs:locale key="list.please.select"/></option>
				</select>
	    	</c:if>
	    	<c:if test="${modifyStatus=='no' }">
		        <select name="city" id="city"  disabled="disabled" class="textbox-text">
					<option value=""><jecs:locale key="list.please.select"/></option>
				</select>
	    	</c:if>
	    	</span>
	    </td>
	</tr>
	
</tbody>
</table>
</fieldset> 
 <%--   <tr><th>
	    <jecs:label  key="subStore.district" />
    </th>
    <td align="left">
    	<c:if test="${modifyStatus=='yes' }">
	        <select name="district" id="district" >
				<option value=""><jecs:locale key="list.please.select"/></option>
			</select>
    	</c:if>
    	<c:if test="${modifyStatus=='no' }">
	        <select name="district" id="district"  disabled="disabled">
				<option value=""><jecs:locale key="list.please.select"/></option>
			</select>
    	</c:if>	
    </td><th>
	    <jecs:label  key="subStore.address"  required="true"/>
    </th>
    <td align="left">
    	<c:if test="${modifyStatus=='yes' }">
	  	<form:input path="address" id="address" cssClass="text medium"/>
    	</c:if>
    	<c:if test="${modifyStatus=='no' }">
	 	 	${jmiSubStore.address }
    	</c:if>
    </td></tr>
    
    <tr><th>
	    <jecs:label  key="subStore.postalcode"  required="true"/>
    </th>
    <td align="left">
    	<c:if test="${modifyStatus=='yes' }">
	    	<c:if test="${empty modifyCC  }">
	  	<form:input path="postalcode" id="postalcode" cssClass="text medium"/>
	    	</c:if>
	    	<c:if test="${not empty modifyCC  }">
	 	 		${jmiSubStore.postalcode }
	    	</c:if>
    	</c:if>
    	<c:if test="${modifyStatus=='no' }">
	 	 	${jmiSubStore.postalcode }
    	</c:if>
    </td><th>
	    <jecs:label  key="subStore.email"  />
    </th>
    <td align="left">
    	<c:if test="${modifyStatus=='yes' }">
	    	<c:if test="${empty modifyCC  }">
	  	<form:input path="email" id="email" cssClass="text medium" />
	    	</c:if>
	    	<c:if test="${not empty modifyCC  }">
	 	 		${jmiSubStore.email }
	    	</c:if>
    	</c:if>
    	<c:if test="${modifyStatus=='no' }">
	 	 	${jmiSubStore.email }
    	</c:if>
    </td></tr>
    
    <tr><th>
	    <jecs:label  key="subStore.mobiletele"  required="true"/>
    </th>
    <td align="left">
    	<c:if test="${modifyStatus=='yes' }">
	    	<c:if test="${empty modifyCC  }">
	  	<form:input path="mobiletele" id="mobiletele" cssClass="text medium"/>
	    	</c:if>
	    	<c:if test="${not empty modifyCC  }">
	 	 		${jmiSubStore.mobiletele }
	    	</c:if>
    	</c:if>
    	<c:if test="${modifyStatus=='no' }">
	 	 	${jmiSubStore.mobiletele }
    	</c:if>
    </td><th>
	    <jecs:label  key="subStore.phone"  />
    </th>
    <td align="left">
    	<c:if test="${modifyStatus=='yes' }">
	    	<c:if test="${empty modifyCC  }">
	  	<form:input path="phone" id="phone" cssClass="text medium"/>
	    	</c:if>
	    	<c:if test="${not empty modifyCC  }">
	 	 		${jmiSubStore.phone }
	    	</c:if>
    	</c:if>
    	<c:if test="${modifyStatus=='no' }">
	 	 	${jmiSubStore.phone }
    	</c:if>
    </td></tr>

</table>

</fieldset>

<fieldset style="border-top: 2px solid #cccccc;border-right: 0px ;border-bottom: 0px ;border-left: 0px ;font-family: Arial, Helvetica, sans-serif;font-weight:bold;">
<legend ><jecs:locale key="subStore.other.info"/>:</legend>


<table class='detail' width="100%">
    <tr><th>
	    <jecs:label  key="subStore.personQty"/>
    </th>
    <td align="left">
    	<c:if test="${modifyStatus=='yes' }">
	    	<c:if test="${empty modifyCC  }">
	  			<form:input path="personQty" id="personQty" cssClass="text medium"/>
	    	</c:if>
	    	<c:if test="${not empty modifyCC  }">
	 	 		${jmiSubStore.personQty }
	    	</c:if>
    	</c:if>
    	<c:if test="${modifyStatus=='no' }">
	 	 	${jmiSubStore.personQty }
    	</c:if>
    	<jecs:locale  key="busi.unit.wan"/>
    </td><th>
	 <jecs:label key="subStore.storePhone"/>
    </th>
    <td align="left">
    	<c:if test="${modifyStatus=='yes' }">
	    	<c:if test="${empty modifyCC  }">
	  <form:input path="storePhone" id="storePhone" cssClass="text medium"/>
	    	</c:if>
	    	<c:if test="${not empty modifyCC  }">
	 	 		${jmiSubStore.storePhone }
	    	</c:if>
    	</c:if>
    	<c:if test="${modifyStatus=='no' }">
	 	 	${jmiSubStore.storePhone }
    	</c:if>
    </td></tr>
   
    <tr><th>
	    <jecs:label  key="subStore.businessArea"/>
    </th>
    <td align="left">
    	<c:if test="${modifyStatus=='yes' }">
	    	<c:if test="${empty modifyCC  }">
	  <form:input path="businessArea" id="businessArea" cssClass="text medium"/>
	    	</c:if>
	    	<c:if test="${not empty modifyCC  }">
	 	 		${jmiSubStore.businessArea }
	    	</c:if>
    	</c:if>
    	<c:if test="${modifyStatus=='no' }">
	 	 	${jmiSubStore.businessArea }
    	</c:if>
    	<jecs:locale  key="busi.unit.m"/>
    </td><th>
	 <jecs:label key="subStore.averageIncome"/>
    </th>
    <td align="left">
    	<c:if test="${modifyStatus=='yes' }">
	    	<c:if test="${empty modifyCC  }">
	  <form:input path="averageIncome" id="averageIncome" cssClass="text medium"/>
	    	</c:if>
	    	<c:if test="${not empty modifyCC  }">
	 	 		${jmiSubStore.averageIncome }
	    	</c:if>
    	</c:if>
    	<c:if test="${modifyStatus=='no' }">
	 	 	${jmiSubStore.averageIncome }
    	</c:if>
    	<jecs:locale  key="busi.unit.yuan"/>
    </td></tr>
   
    <tr><th>
	    <jecs:label  key="subStore.investAmount"/>
    </th>
    <td align="left">
    	<c:if test="${modifyStatus=='yes' }">
	    	<c:if test="${empty modifyCC  }">
	  <form:input path="investAmount" id="investAmount" cssClass="text medium"/>
	    	</c:if>
	    	<c:if test="${not empty modifyCC  }">
	 	 		${jmiSubStore.investAmount }
	    	</c:if>
    	</c:if>
    	<c:if test="${modifyStatus=='no' }">
	 	 	${jmiSubStore.investAmount }
    	</c:if>
    	<jecs:locale  key="busi.unit.million"/>
    </td><th>
	 <jecs:label key="subStore.startDate"/>
    </th>
    <td align="left">
    	<c:if test="${modifyStatus=='yes' }">
	    	<c:if test="${empty modifyCC  }">
    <form:input path="startDate" id="startDate" readonly="true" size="8" cssClass="readonly"/>
				<img src="./images/calendar/calendar7.gif" id="img_startDate" style="cursor: pointer;" title="<jecs:locale key="Calendar.TT.SEL_DATE"/>"/> 
				<script type="text/javascript"> 
					Calendar.setup({
					inputField     :    "startDate", 
					ifFormat       :    "%Y-%m-%d",  
					button         :    "img_startDate", 
					singleClick    :    true
					}); 
				</script>
	    	</c:if>
	    	<c:if test="${not empty modifyCC  }">
	 	 		${jmiSubStore.startDate }
	    	</c:if>
    	</c:if>
    	<c:if test="${modifyStatus=='no' }">
	 	 	${jmiSubStore.startDate }
    	</c:if>
    </td></tr>
    
    <tr><th>
	    <jecs:label  key="subStore.isdeal"/>
    </th>
    <td align="left">
    	<c:if test="${modifyStatus=='yes' }">
	    	<c:if test="${empty modifyCC  }">
         <jecs:list name="isdeal" listCode="yesno" value="${jmiSubStore.isdeal}" defaultValue="" showBlankLine="true"/>	
	    	</c:if>
	    	<c:if test="${not empty modifyCC  }">
	 	 	 <jecs:code listCode="yesno" value="${jmiSubStore.isdeal}"/>
	    	</c:if>
    	</c:if>
    	<c:if test="${modifyStatus=='no' }">
	 	 	 <jecs:code listCode="yesno" value="${jmiSubStore.isdeal}"/>
    	</c:if>
    </td><th>
	 <jecs:label key="subStore.specificBusiness"/>
    </th>
    <td align="left">
    	<c:if test="${modifyStatus=='yes' }">
	    	<c:if test="${empty modifyCC  }">
	  <form:input path="specificBusiness" id="specificBusiness" cssClass="text medium"/>
	    	</c:if>
	    	<c:if test="${not empty modifyCC  }">
	 	 	${jmiSubStore.specificBusiness }
	    	</c:if>
    	</c:if>
    	<c:if test="${modifyStatus=='no' }">
	 	 	${jmiSubStore.specificBusiness }
    	</c:if>
    </td></tr>
   

</table>

</fieldset>

<c:if test="${sessionScope.sessionLogin.userType=='C' }">
<div id="backInfo">
<fieldset  style="border-top: 2px solid #cccccc;border-right: 0px ;border-bottom: 0px ;border-left: 0px ;font-family: Arial, Helvetica, sans-serif;font-weight:bold;">



<table class='detail' width="100%">
    <tr><th>
	    <jecs:label  key="busi.user.check"/>
    </th>
    <td align="left">
		${jmiSubStore.checkUser } - ${jmiSubStore.checkTime }
    </td><th>
	 <jecs:label key="pd.confirmUserNo"/>
    </th>
    <td align="left">
		${jmiSubStore.confirmUser } - ${jmiSubStore.confirmTime }
    </td></tr>
   
    <tr><th>
	    <jecs:label  key="jmiSubStore.checkRemark"/>
    </th>
    <td align="left">
		<form:textarea path="checkRemark" rows="7" cols="30" />
    </td><th>
	 <jecs:label key="jmiSubStore.confirmRemark"/>
    </th>
    <td align="left">
		<form:textarea path="confirmRemark" rows="7" cols="30"/>
		
    </td></tr>
    

</table>

</fieldset>
</div>
</c:if>--%>

 <c:if test="${param.strAction!='viewJmiSubStore' }">
<fieldset style="border-top: 2px solid #cccccc;border-right: 0px ;border-bottom: 0px ;border-left: 0px ;font-family: Arial, Helvetica, sans-serif;font-weight:bold;">
<legend ><jecs:locale key="register.memberInfoIden.agree"/>:</legend>


<table class='detail' width="100%">
<tbody class="window">
    <tr class="edit_tr">
    	<th></th>
	    <td colspan="3">
	    	<div style="overflow-y:auto;  height:200px; " > 
			<p align="center"><strong>二级生活馆网站申请承诺书</strong><strong> </strong></p>
			<ol>
			  <li>本人将认真填写申请表格，资料不全，公司将不予审核。 </li>
			  <li>本人承诺申请资料所填写内容为全部真实并有效，否则将承担由此所产生的全部责任。 </li>
			  <li>本人承诺遵守并执行中脉公司对于二级生活馆申请政策（详见网站生活馆专栏）。 </li>
			  <li>公司审批通过该二级生活馆申请，本人承诺将在7天内登录系统付款，否则公司有权在7天后取消申请资格。 </li>
			  <li>本人承诺在付款后，将在四周内提交营业执照、二级生活馆经营合同、生活馆装修照片。否则接受停发、扣除奖金，如情节严重，解除生活馆经营合同的处罚。 </li>
			  <li>以上资料是申请人本人登录、合同签署法人、以及营业执照法人为本人姓名。 </li>
			  <li>本人承诺付款生成二级生活馆订单后，不能退款；不得退货；非质量问题不得换货。 </li>
			  <li>如发货地址填写有误，本人将自行承担运费。 </li>
			  <li>本人接受个人所开设二级生活馆周边，新增开设一级生活馆。 </li>
			  <li>本人承诺遵守并执行公司对于经销商、直销员、生活馆管理规定；</li>
			  <li>本人承诺遵守执行二级生活馆经营合同之规定；</li>
			  <li>本人承诺遵守执行生活馆营业守则及公司规章管理制度；  </li>
			</ol>
			<p>以上须知条款最终解释权在南京中脉科技发展有限公司。 </p>
			</div>
	    </td>
    </tr>
   
    <tr class="edit_tr">
     	<th></th>
	    <td colspan="3">
			<input type="checkbox" value="agree1" id="agree1" name="agree1"><jecs:locale key="register.agree"/>
	    </td>
	</tr>
   	<tr class="edit_tr">
   		<th></th>
    	<td>
    		<div style="overflow-y:auto;  height:200px; " > 
				<div class=Section1 style='layout-grid:15.6pt'>
					<jsp:include flush="true" page="subStore_attachment0.jsp"></jsp:include>
					<!-- 附件一 -->
					<jsp:include flush="true" page="subStore_attachment1.jsp"></jsp:include>
					<jsp:include flush="true" page="subStore_attachment2.jsp"></jsp:include>
					<jsp:include flush="true" page="subStore_attachment3.jsp"></jsp:include>
				</div>
			</div>
    	</td>
    </tr>
   
    <tr class="edit_tr">
	    <th></th>
	    <td colspan="3">
			<input type="checkbox" value="agree2" id="agree2" name="agree2"><jecs:locale key="register.agree"/>
	    </td>
	</tr>
	<tr class="edit_tr">
		<td class="command" align="center" colspan="4">
			<c:out value="${buttons}" escapeXml="false"/>
		</td>
	</tr>
</tbody>
</table>

</fieldset>   
  </c:if>  
    
</form:form>
<c:if test="${logoutSession=='logoutSession' }">

		<script>
			window.parent.location.href='logout.jsp';
		</script>

</c:if>


<script>
		   function getCity(){
		   var province=document.getElementById('province').value;
		   alCityManager.getAlCityByProvinceId(province,callBackCity);
		   }
		   function callBackCity(valid){
			   var cityElemment=document.getElementById('city');
    
        		cityElemment.options.length=0;
     
          		var o=new Option("<jecs:locale key="list.please.select"/>","");
           		cityElemment.options.add(o);  
           
		       for (var i=0;i<valid.length;i++) {
		        var cityId= valid[i].cityId;	   
		        var cityName=valid[i].cityName;   
		        var o=new Option(cityName,cityId);
		        cityElemment.options.add(o);
			        if(cityId=='${jmiSubStore.city}'){
			        	o.selected=true;
			        }  
			   }
			   
		   	
		   		//getDistrict();	   
		   		
		   }

		   function getDistrict(){
		   	var city=document.getElementById('city').value;
		   	alDistrictManager.getAlDistrictByCityId(city,callBackDistrict);
		   }
		   function callBackDistrict(valid){
			   var districtElemment=document.getElementById('district');
    
        		districtElemment.options.length=0;
     
          		var o=new Option("<jecs:locale key="list.please.select"/>","");
           		districtElemment.options.add(o);  
           
		       for (var i=0;i<valid.length;i++) {
		        var districtId= valid[i].districtId;	   
		        var districtName=valid[i].districtName;   
		        var o=new Option(districtName,districtId);
		        districtElemment.options.add(o);       
				   if('${jmiSubStore.district}'==districtId){
				   		o.selected=true;
				   } 
			   }
		   
		
		   }
		</script>



	
		<script>
			getCity();
			
function validateOthers(){
	if(document.getElementById('personQty').value!=''&& !isUnsignedInteger(document.getElementById('personQty').value)){
		alert("<jecs:locale key="register.us.select.number"/>");
		document.getElementById('personQty').focus();
		return false;
	}
	if( document.getElementById('averageIncome').value!=''&& !isUnsignedInteger(document.getElementById('averageIncome').value)){
		alert("<jecs:locale key="register.us.select.number"/>");
		document.getElementById('averageIncome').focus();
		return false;
	}
	if( document.getElementById('investAmount').value!=''&& !isUnsignedNumeric(document.getElementById('investAmount').value)){
		alert("<jecs:locale key="register.us.select.number"/>");
		document.getElementById('investAmount').focus();
		return false;
	}
	
	return true;
}

function go_print () {
	var titleBar=document.getElementById('titleBar');
	var backInfo=document.getElementById('backInfo');
    titleBar.style.visibility="hidden";
    backInfo.style.visibility="hidden";
    window.print();
    titleBar.style.visibility="visible";
    backInfo.style.visibility="visible";
  }
  $(document).ready(function(){
   $("#jmiMember\\.subRecommendStore").blur(function(){
   $("#jmiMember\\.subRecommendStore1")[0].value='';
   $("#jmiMember\\.subRecommendStore1")[0].innerHTML=$("#jmiMember\\.subRecommendStore")[0].value;
   	searchSubRecommendStore();
   });
});
		</script>
		