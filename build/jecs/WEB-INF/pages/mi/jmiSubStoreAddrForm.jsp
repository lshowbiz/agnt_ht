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
			   }
			   }
			   
			 function searchSubRecommendStore(){
			   var loadinfo = "loading....." ; 
			   var userCode=document.getElementById('jmiMember.subRecommendStore').value;
			   jmiMemberManager.getJmiMember(userCode,callBackSubRecommendStore);
			       DWRUtil.useLoadingMessage(loadinfo);  
		   	} 
		   function callBackSubRecommendStore(valid){
			   if(valid==null){
			   		alert('<jecs:locale key="operation.notice.js.orderNo.miMember.memberNoError"/>');
			   }else{
			   	 document.getElementById('jmiMember.subRecommendStore.name').innerText=valid.miUserName;
			   }
			}
			   
			  function checkStore(strAction){
					$('#strAction').val(strAction);
					$('#jmiSubStoreForm').submit();
				}
			   
</script>
	
		

<title><jecs:locale key="jmiSubStoreDetail.title"/></title>
<content tag="heading"><jecs:locale key="jmiSubStoreDetail.heading"/></content>
<%--
<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<c:if test="${param.strAction!='viewJmiSubStoreAddr' && (jmiSubStore.addrCheck!='1'  || sessionScope.sessionLogin.userType=='C') }">
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="operation.button.save"/>" />
				</c:if>
		</jecs:powe
		


		
		<c:if test="${param.strAction=='viewJmiSubStoreAddr'}">
		<jecs:power powerCode="addrCheckJmiSubStore">
			<input type="button" class="button" name="save"  onclick="checkStore('addrCheckJmiSubStore')" value="<jecs:locale key="button.audit"/>" />
		</jecs:power>
		
		<jecs:power powerCode="addrUnCheckJmiSubStore">
			<input type="button" class="button" name="save"  onclick="checkStore('addrUnCheckJmiSubStore')" value="<jecs:locale key="button.notCheck"/>" />
		</jecs:power>
		
		<jecs:power powerCode="addrConfirmJmiSubStore">
			<input type="button" class="button" name="save"  onclick="checkStore('addrConfirmJmiSubStore')" value="<jecs:locale key="operation.button.confirm"/>" />
		</jecs:power>
		
		<jecs:power powerCode="addrUnConfirmJmiSubStore">
			<input type="button" class="button" name="save"  onclick="checkStore('addrUnConfirmJmiSubStore')" value="<jecs:locale key="button.bdUnCheck"/>" />
		</jecs:power>
		</c:if>
		
		<input type="button" class="button" name="cancel" onclick="window.history.back()" value="<jecs:locale key="operation.button.return"/>" />
</c:set>
--%>
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



<form:form commandName="jmiSubStore" method="post" action="editJmiSubStoreAddr.html"  onsubmit="return validateOthers();"  id="jmiSubStoreForm" name="jmiSubStoreForm">

<div  class="noPrint" id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
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
    <td >
    		<span class="textbox">${jmiSubStore.jmiMember.miUserName }</span>
    		<span id="miUserName"></span>
    </td>
    </tr>
    
    <tr class="edit_tr">
    <th>
	    <span class="text-font-style-tc"><jecs:label  key="miMember.sex"/></span>
    </th>
    <td >
        <span class="textbox"><jecs:code listCode="sex" value="${jmiSubStore.jmiMember.sex}"/></span>
        <span id="sex"></span>
    </td><th>
        <span class="text-font-style-tc"><jecs:label  key="miMember.mobiletele"  /></span>
    </th>
    <td >
    	<span class="textbox">${jmiSubStore.jmiMember.mobiletele }</span>
    	<span id="mobiletele"></span>
    </td></tr>
    
    <tr class="edit_tr"><th>
        <span class="text-font-style-tc"><jecs:label  key="miMember.papertype"/></span>
    </th>
    <td >
    	<span class="textbox">
        <jecs:code listCode="papertype" value="${jmiSubStore.jmiMember.papertype}"/>
        </span>
        <span id="papertype"></span>
    </td><th>
        <span class="text-font-style-tc"><jecs:label  key="miMember.papernumber"  /></span>
    </th>
    <td >
    	<span class="textbox">${jmiSubStore.jmiMember.papernumber }</span>
    	<span id="papernumber"></span>
    </td></tr>

    <tr class="edit_tr"><th>
		<span class="text-font-style-tc">
		<jecs:label  key="miMember.subRecommendStore" required="true"/></span>
    </th>
    <td >
	 	 	<span class="textbox">${jmiSubStore.jmiMember.subRecommendStore }</span>
    </td><th>
	    <span class="text-font-style-tc"><jecs:label  key="miMember.subRecommendStore.name"/></span>
    </th>
    <td >
    	<c:if test="${not empty recommendStoreName }">
    		<span class="textbox">${recommendStoreName }</span>
    	</c:if>
    	<span id="jmiMember.subRecommendStore.name"></span>
    </td></tr>
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
	        <form:select path="province"  cssClass="textbox-text" onchange="getCity()" disabled="${provinceDisabled }">
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
	    </td><th>
		    <span class="text-font-style-tc"><jecs:label  key="subStore.city"  required="true"/></span>
	    </th>
	    <td >
	    	<span class="textbox">
	    	<c:if test="${modifyStatus=='yes' }">
		        <select name="city" id="city" onchange="getDistrict()" disabled="${provinceDisabled }" class="textbox-text">
					<option value=""><jecs:locale key="list.please.select"/></option>
				</select>
	    	</c:if>
	    	<c:if test="${modifyStatus=='no' }">
		        <select name="city" id="city" onchange="getDistrict()" disabled="disabled" class="textbox-text">
					<option value=""><jecs:locale key="list.please.select"/></option>
				</select>
	    	</c:if>
	    	</span>
	    </td>
	</tr>
    
    <tr class="edit_tr"><th>
	    <span class="text-font-style-tc"><jecs:label  key="subStore.district" /></span>
    </th>
    <td >
    	<span class="textbox">
    	<c:if test="${modifyStatus=='yes' }">
	        <select name="district" id="district" class="textbox-text">
				<option value=""><jecs:locale key="list.please.select"/></option>
			</select>
    	</c:if>
    	<c:if test="${modifyStatus=='no' }">
	        <select name="district" id="district"  disabled="disabled" class="textbox-text">
				<option value=""><jecs:locale key="list.please.select"/></option>
			</select>
    	</c:if>	
    	</span>
    </td><th>
	    <span class="text-font-style-tc"><jecs:label  key="subStore.address"  required="true"/></span>
    </th>
    <td >
    	
    	<c:if test="${modifyStatus=='yes' }">
	  	<span class="textbox"><form:input path="address" id="address" cssClass="textbox-text"/></span>
    	</c:if>
    	<c:if test="${modifyStatus=='no' }">
	 	 	<span class="textbox">${jmiSubStore.address }</span>
    	</c:if>
    	
    </td></tr>
    
    <tr class="edit_tr"><th>
	    <span class="text-font-style-tc"><jecs:label  key="subStore.postalcode" /></span>
    </th>
    <td >
    	
    	<c:if test="${modifyStatus=='yes' }">
	  		<span class="textbox"><form:input path="postalcode" id="postalcode" cssClass="textbox-text"/></span>
    	</c:if>
    	<c:if test="${modifyStatus=='no' }">
	 	 	<span class="textbox">${jmiSubStore.postalcode }</span>
    	</c:if>
    	
    </td><th>
	    <span class="text-font-style-tc"><jecs:label  key="subStore.email"  /></span>
    </th>
    <td >
    	
    	<c:if test="${modifyStatus=='yes' }">
	  		<span class="textbox"><form:input path="email" id="email" cssClass="textbox-text" /></span>
    	</c:if>
    	<c:if test="${modifyStatus=='no' }">
	 	 	<span class="textbox">${jmiSubStore.email }</span>
    	</c:if>
    	</span>
    </td></tr>
    
    <tr class="edit_tr"><th>
	    <span class="text-font-style-tc"><jecs:label  key="subStore.mobiletele"  required="true"/></span>
    </th>
    <td >
    	
    	<c:if test="${modifyStatus=='yes' }">
	  		<span class="textbox"><form:input path="mobiletele" id="mobiletele" cssClass="textbox-text"/></span>
    	</c:if>
    	<c:if test="${modifyStatus=='no' }">
	 	 	<span class="textbox">${jmiSubStore.mobiletele }</span>
    	</c:if>
    	
    </td><th>
	    <span class="text-font-style-tc"><jecs:label  key="subStore.phone"  /></span>
    </th>
    <td >
    	<c:if test="${modifyStatus=='yes' }">
	  		<span class="textbox"><form:input path="phone" id="phone" cssClass="textbox-text"/></span>
    	</c:if>
    	<c:if test="${modifyStatus=='no' }">
	 	 	<span class="textbox">${jmiSubStore.phone }</span>
    	</c:if>
    </td></tr>

</table>

</fieldset>

<fieldset style="border-top: 2px solid #cccccc;border-right: 0px ;border-bottom: 0px ;border-left: 0px ;font-family: Arial, Helvetica, sans-serif;font-weight:bold;">
<legend ><jecs:locale key="subStore.other.info"/>:</legend>


<table class='detail' width="70%">
<tbody class="window">
    <tr class="edit_tr"><th>
	    <span class="text-font-style-tc"><jecs:label  key="subStore.personQty"/></span>
    </th>
    <td >
    	<span class="textbox">
	    	<c:if test="${modifyStatus=='yes' }">
		  		<form:input path="personQty" id="personQty" cssClass="textbox-text" cssStyle="width:140px"/>
	    	</c:if>
	    	<c:if test="${modifyStatus=='no' }">
		 	 	${jmiSubStore.personQty }
	    	</c:if>
	    	<jecs:locale  key="busi.unit.wan"/>
    	</span>
    </td><th>
	 <span class="text-font-style-tc"><jecs:label key="subStore.storePhone"/></span>
    </th>
    <td >
	    <span class="textbox">
	    	<c:if test="${modifyStatus=='yes' }">
		  		<form:input path="storePhone" id="storePhone" cssClass="textbox-text"/>
	    	</c:if>
	    	<c:if test="${modifyStatus=='no' }">
		 	 	${jmiSubStore.storePhone }
	    	</c:if>
	    </span>
    </td></tr>
   
    <tr class="edit_tr"><th>
	    <span class="text-font-style-tc"><jecs:label  key="subStore.businessArea"/></span>
    </th>
    <td >
	    <span class="textbox">
	    	<c:if test="${modifyStatus=='yes' }">
		  	<form:input path="businessArea" id="businessArea" cssClass="textbox-text" cssStyle="width:140px"/>
	    	</c:if>
	    	<c:if test="${modifyStatus=='no' }">
		 	 	${jmiSubStore.businessArea }
	    	</c:if>
	    	<jecs:locale  key="busi.unit.m"/>
	    	</span>
    </td><th>
	 <span class="text-font-style-tc"><jecs:label key="subStore.averageIncome"/></span>
    </th>
    <td >
	    <span class="textbox">
	    	<c:if test="${modifyStatus=='yes' }">
		  	<form:input path="averageIncome" id="averageIncome" cssClass="textbox-text" cssStyle="width:140px"/>
	    	</c:if>
	    	<c:if test="${modifyStatus=='no' }">
		 	 	${jmiSubStore.averageIncome }
	    	</c:if>
	    	<jecs:locale  key="busi.unit.yuan"/>
	    	</span>
    </td></tr>
   
    <tr class="edit_tr"><th>
	    <span class="text-font-style-tc"><jecs:label  key="subStore.investAmount"/></span>
    </th>
    <td >
	    <span class="textbox">
	    	<c:if test="${modifyStatus=='yes' }">
		  	<form:input path="investAmount" id="investAmount" cssClass="textbox-text" cssStyle="width:140px"/>
	    	</c:if>
	    	<c:if test="${modifyStatus=='no' }">
		 	 	${jmiSubStore.investAmount }
	    	</c:if>
	    	<jecs:locale  key="busi.unit.million"/>
	    	</span>
    </td><th>
	 <span class="text-font-style-tc"><jecs:label key="subStore.startDate"/></span>
    </th>
    <td >
    	<c:if test="${modifyStatus=='yes' }">
    		<span class="textbox">
    		<form:input path="startDate" id="startDate" readonly="true" 
    			cssClass="textbox-text"	onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
			</span>
    	</c:if>
    	<c:if test="${modifyStatus=='no' }">
	 	 	<span class="textbox">${jmiSubStore.startDate }</span>
    	</c:if>
    </td></tr>
    
    <tr class="edit_tr">
    <th>
	    <span class="text-font-style-tc"><jecs:label  key="subStore.isdeal"/></span>
    </th>
    <td >
    	<c:if test="${modifyStatus=='yes' }">
         <span class="textbox">
         	<jecs:list name="isdeal" listCode="yesno" value="${jmiSubStore.isdeal}" 
         	defaultValue="" showBlankLine="true" styleClass="textbox-text"/>
         </span>	
    	</c:if>
    	<c:if test="${modifyStatus=='no' }">
	 	 	 <span class="textbox"><jecs:code listCode="yesno" value="${jmiSubStore.isdeal}"/></span>
    	</c:if>
    	
    </td><th>
	 <span class="text-font-style-tc"><jecs:label key="subStore.specificBusiness"/></span>
    </th>
    <td >
    	
    	<c:if test="${modifyStatus=='yes' }">
	  <span class="textbox"><form:input path="specificBusiness" id="specificBusiness" cssClass="textbox-text"/></span>
    	</c:if>
    	<c:if test="${modifyStatus=='no' }">
	 	 	<span class="textbox">${jmiSubStore.specificBusiness }</span>
    	</c:if>
    </td></tr>
   
</tbody>
</table>

</fieldset>



<c:if test="${sessionScope.sessionLogin.userType=='C' }">
<div id="backInfo">
<fieldset  style="border-top: 2px solid #cccccc;border-right: 0px ;border-bottom: 0px ;border-left: 0px ;font-family: Arial, Helvetica, sans-serif;font-weight:bold;">

<table class='detail' width="70%">
<tbody class="window">
    <tr class="edit_tr">
	    <th>
		    <span class="text-font-style-tc"><jecs:label  key="busi.user.check"/></span>
	    </th>
	    <td >
			<span class="textbox">${jmiSubStore.addrCheckUser } - ${jmiSubStore.addrCheckTime }</span>
	    </td>
	    <th>
		 <span class="text-font-style-tc"><jecs:label key="pd.confirmUserNo"/></span>
	    </th>
	    <td >
			<span class="textbox">${jmiSubStore.addrConfirmUser } - ${jmiSubStore.addrConfirmTime }</span>
	    </td>
	</tr>
    <tr class="edit_tr">
	    <th>
		    <span class="text-font-style-tc-tare"><jecs:label  key="jmiSubStore.checkRemark"/></span>
	    </th>
	    <td colspan="3">
			<span class="text-font-style-tc-right">
				<form:textarea path="checkRemark" cssClass="textarea_border" />
			</span>
	    </td>
	</tr>
	<tr class="edit_tr">
	    <th>
		 	<span class="text-font-style-tc-tare"><jecs:label key="jmiSubStore.confirmRemark"/></span>
	    </th>
	    <td colspan="3">
			<span class="text-font-style-tc-right">
				<form:textarea path="confirmRemark" cssClass="textarea_border"/>
			</span>
	    </td>
	</tr>
    <tr class="edit_tr">
    	<td colspan="4" align="center">
    		
		<jecs:power powerCode="${param.strAction}">
			<c:if test="${param.strAction!='viewJmiSubStoreAddr' && 
						(jmiSubStore.addrCheck!='1'  || sessionScope.sessionLogin.userType=='C') }">
				<button type="submit" class="btn btn_sele" name="save"  onclick="bCancel=false">
					<jecs:locale key="operation.button.save"/>
				</button>
			</c:if>
		</jecs:power>
		<c:if test="${param.strAction=='viewJmiSubStoreAddr'}">
			<jecs:power powerCode="addrCheckJmiSubStore">
				<button type="button" class="btn btn_sele" name="save"  onclick="checkStore('addrCheckJmiSubStore')">
					<jecs:locale key="button.audit"/>
				</button>
			</jecs:power>
		
			<jecs:power powerCode="addrUnCheckJmiSubStore">
				<button type="button" class="btn btn_sele" name="save"  onclick="checkStore('addrUnCheckJmiSubStore')">
					<jecs:locale key="button.notCheck"/>
				</button>
			</jecs:power>
		
			<jecs:power powerCode="addrConfirmJmiSubStore">
				<button type="button" class="btn btn_sele" name="save"  onclick="checkStore('addrConfirmJmiSubStore')">
					<jecs:locale key="operation.button.confirm"/>
				</button>
			</jecs:power>
		
			<jecs:power powerCode="addrUnConfirmJmiSubStore">
				<button type="button" class="btn btn_sele" name="save"  onclick="checkStore('addrUnConfirmJmiSubStore')">
					<jecs:locale key="button.bdUnCheck"/>
				</button>
			</jecs:power>
		</c:if>
		<button type="button" class="btn btn_sele" name="cancel" onclick="window.history.back()">
			<jecs:locale key="operation.button.return"/>
		</button>
		</td>
    </tr>
</tbody>
</table>

</fieldset>
</div>
</c:if>



    
</form:form>



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
			   
		   	
		   		getDistrict();	   
		   		
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
		
<c:if test="${not empty jmiSubStore.province }">
			getCity();
</c:if>
			
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
   	searchSubRecommendStore();
   });
});
		</script>
		