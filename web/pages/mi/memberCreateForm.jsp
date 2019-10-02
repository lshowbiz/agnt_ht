<%@ page language="java" errorPage="/error.jsp"
	contentType="text/html; charset=utf-8"%>
	<%@ include file="/common/taglibs.jsp"%>
<c:if test="${fn:substring(sessionScope.sessionLogin.jmiMember.jmiRecommendRef.treeIndex,0,36)!='00100000000000000b00n00000u003000013'}">
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
<title><jecs:locale key="jmiMemberDetail.title"/></title>
<content tag="heading"><jecs:locale key="jmiMemberDetail.heading"/></content>
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script> 

<script type="text/javascript" src="<c:url value='/scripts/global_js.js'/>"></script>
<script type="text/javascript" src="<c:url value='/dwr/util.js'/>" ></script> 
<script type="text/javascript" src="<c:url value='/dwr/engine.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/dwr/interface/alCityManager.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/dwr/interface/alDistrictManager.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/dwr/interface/jalTownManager.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/dwr/interface/jalPostalcodeManager.js'/>" ></script>

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

<form:form commandName="jmiMember" method="post" action="memberCreate.html"  id="jmiMemberForm" onsubmit="if(isFormPosted()){return true;}{return false;}">
	
<input type="hidden" name="strAction" value="${param.strAction }"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JmiMember')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
<form:hidden path="userCode"/>
<%--
<div class="searchBar">
	<div class="new_searchBar" align="center">
		<button type="submit" class="btn btn_ins" name="save" onclick="bCancel=false">
			<jecs:locale key="operation.button.save"/>	
		</button>
	</div>
	 
	<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="operation.button.save"/>" />
	
		<span style="font-weight: bold;color: red;">
			<jecs:locale key="memberCreate.tips.1" />
		</span>
	
</div>--%>
<fieldset style="border-top: 2px solid #cccccc;border-right: 0px ;border-bottom: 0px ;border-left: 0px ;font-family: Arial, Helvetica, sans-serif;font-weight:bold;">
<legend ><jecs:locale key="member.base.info"/>:</legend>
<table class='detail' width="70%">
	<tbody class="window">
	<tr class="edit_tr" style="display: none;">
		<th>
      		<span class="text-font-style-tc">会员类型:</span>
    	</th>
    	<td>
  			<span class="textbox">
  				<jecs:list name="memberUserType" id="memberUserType" onclick="" onchange="" listCode="member_user_type" 
  					value="${jmiMember.memberUserType}" defaultValue="1" styleClass="textbox-text"/>
  			</span>
   	 	</td>
    </tr>
	<tr class="edit_tr">
		<th>
		    <span class="text-font-style-tc"><jecs:label key="miMember.recommendNo" required="true" /></span>
    	</th>
    	<td>
			<span class="textbox">
				<form:input path="jmiRecommendRef.recommendJmiMember.userCode" id="recommendNo" cssClass="textbox-text" cssStyle="width:124px;"/>
	       		<button type="button" class="btn btn_sele" onclick="miSelectMember(this.form)" >
	       			<jecs:locale key="operation.button.search"/>
	       		</button>
       		</span>
        		<%-- 
        		<input type="button" class="button" onclick="miSelectMember(this.form)" 
        			value="<jecs:locale key="operation.button.search"/>"/>--%>
	        <script type="text/javascript">
				function miSelectMember(theForm){
					if(theForm.recommendNo.value==""){
						alert('<jecs:locale key="operation.notice.js.orderNo.miMember.memberNoError"/>');
						theForm.recommendNo.focus();
						return;
					}
					var pars=new Array();
					pars[0]="<jecs:locale key='function.menu.findMembers'/>";
					pars[1]="miSelectRecommendRef.html?strAction=miSelectRecommendRef&memberNo=" + theForm.recommendNo.value;
					pars[2]=window;
					var ret=showDialog("<%=request.getContextPath()%>",pars,810,550,1);
					if(ret!=undefined){
						theForm.linkNo.value=ret;
					}
				}
			</script>
    	</td>
	    <th>
			<span class="text-font-style-tc"><jecs:label key="miMember.linkNo" required="true" /></span>
	    </th>
	    <td align="left">
			<span class="textbox">
				<form:input path="jmiLinkRef.linkJmiMember.userCode" id="linkNo" cssClass="textbox-text"/>
			</span>
	    </td>
	</tr>
	<tr class="edit_tr">
	    <th>
	    	<span class="text-font-style-tc"><jecs:label key="miMember.firstName" required="true" /></span>
	    </th>
	    <td>
	    	<span class="textbox"><form:input path="firstName" id="firstName" cssClass="textbox-text"/></span>
	    </td>
	    <th>
	    	<span class="text-font-style-tc"><jecs:label key="miMember.lastName" required="true" /></span>
	    </th>
	    <td>
	    	<span class="textbox"><form:input path="lastName" id="lastName" cssClass="textbox-text"/></span>
	    </td>
	</tr>
	<tr class="edit_tr">
		<th>
		   <span class="text-font-style-tc"><jecs:label key="miMember.firstNamePy" /></span>
	    </th>
	    <td>
			<span class="textbox"><form:input path="firstNamePy" id="firstNamePy" cssClass="textbox-text"/></span>
	    </td>
	    <th>
			<span class="text-font-style-tc"><jecs:label key="miMember.lastNamePy"/></span>
	    </th>
	    <td>
			<span class="textbox"><form:input path="lastNamePy" id="lastNamePy" cssClass="textbox-text"/></span>
	    </td>
	</tr>
	<tr class="edit_tr">
	    <th>
	    	<span class="text-font-style-tc"><jecs:label key="miMember.petName" required="true" /></span>
	    </th>
	    <td>
	    	<span class="textbox"><form:input path="petName" id="petName" cssClass="textbox-text"/></span>
	    </td>
	    <th>
	    	<span class="text-font-style-tc"><jecs:label  key="miMember.sex"/></span>
	    </th>
	    <td>
	    	<span class="textbox"><jecs:list name="sex" listCode="sex" value="${jmiMember.sex}" defaultValue="" styleClass="textbox-text"/></span>
	    </td>
	</tr>
	<%-- 
    <c:if test="${sessionScope.sessionLogin.companyCode=='JP'}">
    
	     <tr><th>
		   <jecs:label key="miMember.firstNameKana" required="true" />
	    </th>
	    <td align="left">
		 <form:input path="firstNameKana" id="firstNameKana" cssClass="text medium"/>
	    </td><th>
		 <jecs:label key="miMember.lastNameKana" required="true" />
	    </th>
	    <td align="left">
		<form:input path="lastNameKana" id="lastNameKana" cssClass="text medium"/>
	    </td></tr>
    </c:if>
    <c:if test="${sessionScope.sessionLogin.companyCode=='TW' || sessionScope.sessionLogin.companyCode=='JP'}">
    
	     <tr><th>
		   <jecs:label key="bdCalculatingSubDetail.name" required="true" />
	    </th>
	    <td align="left">
		 <form:input path="miUserName" id="miUserName" cssClass="text medium"/>
	    </td><th>
		 <jecs:label key="miMember.identityType" required="true" />
	    </th>
	    <td align="left">
	 		<jecs:list name="identityType" listCode="identitytype.tw" value="${jmiMember.identityType}" defaultValue="0" onchange="javascript:changeIdInfoCheck(this.value)"/>	
	    </td></tr>
    
    </c:if>
    --%>
	<tr class="edit_tr">
		<th>
      		<span class="text-font-style-tc"><jecs:label  key="miMember.papertype"/></span>
    	</th>
    	<td>
    		<c:if test="${sessionScope.sessionLogin.companyCode=='TW'}">
	  			<span class="textbox">
	  				<jecs:list name="papertype" id="papertype" listCode="papertype.tw" 
	  					value="${jmiMember.papertype}" defaultValue="1" styleClass="textbox-text"/>
	  			</span>
    		</c:if>
    		<c:if test="${sessionScope.sessionLogin.companyCode!='TW'}">
	   			<c:choose>
	    			<c:when test ="${sessionScope.sessionLogin.companyCode=='CN' && sessionScope.sessionLogin.userType=='M'}">
			     		<span class="textbox">
			     			<jecs:list name="papertype" id="papertype" listCode="paper.member" 
			     				value="${jmiMember.papertype}" defaultValue="1" styleClass="textbox-text"/>
			     		</span>
			     	</c:when>
			     	<c:otherwise>
			     		<span class="textbox">
			     			<jecs:list name="papertype" id="papertype" listCode="papertype" 
			     				value="${jmiMember.papertype}" defaultValue="1" styleClass="textbox-text"/>
			     		</span>
			     	</c:otherwise>
			    </c:choose>
    		</c:if>
   	 	</td>
   	 	<th>
	 		<span class="text-font-style-tc"><jecs:label key="miMember.papernumber" required="true" /></span>
    	</th>
    	<td>
	 		<span class="textbox"><form:input path="papernumber" id="papernumber" cssClass="textbox-text" /></span>
    	</td>
    </tr>
    <tr class="edit_tr">
    	<th>
	 		<c:if test="${sessionScope.sessionLogin.companyCode!='PH'}">
	     		<span class="text-font-style-tc"><jecs:label key="miMember.province" required="true" /></span>
	   		</c:if>
      		<c:if test="${sessionScope.sessionLogin.companyCode=='PH'}">
	     		<span class="text-font-style-tc"><jecs:label key="miMember.island" required="true" /></span>
	   		</c:if>
    	</th>
    	<td>
    		<span class="textbox">
		 		<form:select path="province"  cssClass="textbox-text" onchange="getIdCity();">
					<form:option label="" value=""/>
			        <form:options items="${alStateProvinces}" itemValue="stateProvinceId" itemLabel="stateProvinceName"/>
				</form:select>
			</span>
    	</td>
    	<th>
	 		<c:if test="${sessionScope.sessionLogin.companyCode!='PH'}">
				<span class="text-font-style-tc"><jecs:label key="miMember.idAddr2" required="true" /></span>
			</c:if>
		    <c:if test="${sessionScope.sessionLogin.companyCode=='PH'}">
				<span class="text-font-style-tc"><jecs:label key="miMember.region" required="true" /></span>
			</c:if>
    	</th>
    	<td>
    		<span class="textbox">
		 		<select name="city" id="city" onchange="getIdDistrict();" class="textbox-text">
					<option value=""><jecs:locale key="list.please.select"/></option>
				</select>
			</span>
    	</td>
    </tr>
    <%-- 
     <c:if test="${sessionScope.sessionLogin.companyCode=='US'}">
     
	     <tr id="companyNameDiv"><th>
		   <jecs:label key="miMember.companyName" required="true" />
	    </th>
	    <td align="left">
	    	<form:input path="companyName" id="companyName" cssClass="text medium"/>
		 	
	    </td><th>
		  	
	    </th>
	    <td align="left">
	    	
	    </td></tr>
     
     </c:if>
--%>
    <tr class="edit_tr">
    	<th id="districtTh">
	      <c:if test="${sessionScope.sessionLogin.companyCode!='PH'}">
		     <span class="text-font-style-tc"><jecs:label key="miMember.district" required="true" /></span>
		  </c:if>
	      <c:if test="${sessionScope.sessionLogin.companyCode=='PH'}">
		  	<span class="text-font-style-tc"><jecs:label key="miMember.province" required="true" /></span>
		  </c:if>
    	</th>
    	<td>
			<c:if test="${sessionScope.sessionLogin.companyCode=='PH'}">
				<span class="textbox">
					<select name="district" id="district"onchange="getIdTown();" class="textbox-text">
						<option value=""><jecs:locale key="list.please.select"/></option>
					</select>	
				</span>
			</c:if>
			<c:if test="${sessionScope.sessionLogin.companyCode!='PH'}">
				<span class="textbox">
					<select name="district" id="district" onchange="getPostalcodeTW(this)" class="textbox-text" 
						<c:if test="${sessionScope.sessionLogin.companyCode=='JP'}"> style="display: none" </c:if>>
						<option value=""><jecs:locale key="list.please.select"/></option>
					</select>	
				</span>
		 	</c:if>
		</td>
		<th>
	   		<span class="text-font-style-tc"><jecs:label key="miMember.idAddr" required="true" /></span>
    	</th>
	    <td>
		  <span class="textbox"><form:input path="address" id="address" cssClass="textbox-text"/></span>
	    </td>
	</tr>
    
    <%-- 
      <c:if test="${sessionScope.sessionLogin.companyCode=='JP'}">
    <tr>
       <th>
	 <jecs:label key="miMember.building"  />
	    </th>
	    <td align="left">
	    	  <form:input path="building" id="building" cssClass="text medium"/>
	    </td><th>
		
	    </th>
    <td align="left">
	  
    </td></tr>
	 </c:if>
	 
     <c:if test="${sessionScope.sessionLogin.companyCode=='PH'}">
    <tr>
       <th>
	 <jecs:label key="miMember.idAddr2" required="true" />
	    </th>
	    <td align="left">
			
		<select name="town" id="town" >
				<option value=""><jecs:locale key="list.please.select"/></option>
			</select>	
	    </td><th>
		 <jecs:label key="miMember.nationality"  />
	    </th>
    <td align="left">
	  <jecs:list name="nationality" id="nationality" listCode="nationality" value="${jmiMember.nationality}" defaultValue="PH"/>
    </td></tr>
	 </c:if>
    
    <c:if test="${sessionScope.sessionLogin.companyCode=='TW' || 
    				sessionScope.sessionLogin.companyCode=='PH' || 
    				sessionScope.sessionLogin.companyCode=='US' || 
    				sessionScope.sessionLogin.companyCode=='JP'|| 
    				sessionScope.sessionLogin.companyCode=='ID' }">
    
	     <tr><th>
		  <jecs:label key="miMember.birthday" required="true" />
	    </th>
	    <td align="left">
		 
		<form:input path="birthday" id="birthday"  cssClass="text medium"/>
					<img src="./images/calendar/calendar7.gif" id="img_birthday" style="cursor: pointer;" title="<jecs:locale key="Calendar.TT.SEL_DATE"/>" /> 
					<script type="text/javascript"> 
						Calendar.setup({
						inputField     :    "birthday", 
						ifFormat       :    "%Y-%m-%d",  
						button         :    "img_birthday", 
						singleClick    :    true
						});
					</script>
	    </td><th>
		 	 
	    </th>
	    <td align="left">
	    </td></tr>
    </c:if>
    --%>
    <%-- 
    <c:if test="${sessionScope.sessionLogin.companyCode=='TW' && sessionScope.sessionLogin.userType=='C' }">
    	
	    
	    
	     <tr><th>
		   <jecs:label key="miMember.villageAddr" required="true" />
	    </th>
	    <td align="left">
		 	 <form:input path="villageAddr" id="villageAddr" cssClass="text medium"/>
	    </td><th>
		  <jecs:label key="miMember.townAddr" required="true" />
	    </th>
	    <td align="left">
		 	<form:input path="townAddr" id="townAddr" cssClass="text medium"/>
	    </td></tr>
	    
	    
	     <tr><th>
		  
	    </th>
	    <td align="left">
		 	 <input type="checkbox" id="sameIdInfo" name="sameIdInfo" value="true" onclick="sameAsIdInfo()"><jecs:locale key="same.as.idinfo"  />
	    </td><th>
		  
	    </th>
	    <td align="left">
		 	
	    </td></tr>
	    
	     <tr><th>
		   <jecs:label key="miMember.commProvince" required="true" />
	    </th>
	    <td align="left">
			 <form:select path="commProvince"  cssClass="text medium" onchange="getCommCity();">
							<form:option label="" value=""/>
				            <form:options items="${alStateProvinces}" itemValue="stateProvinceId" itemLabel="stateProvinceName"/>
			</form:select>
	    </td><th>
		  <jecs:label key="miMember.commCity" required="true" />
	    </th>
	    <td align="left">
			<select name="commCity" id="commCity" onchange="getCommDistrict();">
					<option value=""><jecs:locale key="list.please.select"/></option>
				</select>
	    </td></tr>
    
    
	     <tr><th>
		   <jecs:label key="miMember.commDistrict" required="true" />
	    </th>
	    <td align="left">
			<select name="commDistrict" id="commDistrict" onchange="getCommPostalcode(this);">
					<option value=""><jecs:locale key="list.please.select"/></option>
				</select>
	    </td><th>
		 
	    </th>
	    <td align="left">
	    </td></tr>
    
	     <tr><th>
		   <jecs:label key="miMember.commAddr" required="true" />
	    </th>
	    <td align="left">
	    	<form:input path="commAddr" id="commAddr" cssClass="text medium"/>
		 	
	    </td><th>
		  	<jecs:label key="miMember.commPostalcode" required="true" />
	    </th>
	    <td align="left">
	    	<form:input path="commPostalcode" id="commPostalcode" cssClass="text medium"/>
	    </td></tr> 
    </c:if>
    --%>
    <%-- 
     <c:if test="${sessionScope.sessionLogin.companyCode=='TW' || sessionScope.sessionLogin.companyCode=='JP' }">
    	
		 <tr id="companyNameDiv" style="display: none"><th>
			   <jecs:label key="miMember.companyName" required="true" />
		    </th>
		    <td align="left">
		    	<form:input path="companyName" id="companyName" cssClass="text medium"/>
			 	
		    </td><th> 
		    <c:if test="${sessionScope.sessionLogin.companyCode=='TW' }">
			  	<jecs:label key="miMember.personCharge" required="true" />
		    </c:if>
		    <c:if test="${sessionScope.sessionLogin.companyCode=='JP' }">
			  	<jecs:label key="miMember.personCharge.comName" required="true" />
		    </c:if>
		    </th>
		    <td align="left">
		    	<form:input path="personCharge" id="personCharge" cssClass="text medium"/>
		    </td></tr>
		    
		     <tr id="companyAddrDiv" style="display: none"><th>
		    <c:if test="${sessionScope.sessionLogin.companyCode=='TW' }">
			   <jecs:label key="miMember.companyAddr" required="true" />
		    </c:if>
		    <c:if test="${sessionScope.sessionLogin.companyCode=='JP' }">
			   <jecs:label key="miMember.companyAddr.depart" required="true" />
		    </c:if>
		    </th>
		    <td align="left">
		    	<form:input path="companyAddr" id="companyAddr" cssClass="text medium"/>
			 	
		    </td><th>
		    <c:if test="${sessionScope.sessionLogin.companyCode=='TW' }">
			  	<jecs:label key="miMember.uniteNumber" required="true" />
		    </c:if>
		    <c:if test="${sessionScope.sessionLogin.companyCode=='JP' }">
			  	<jecs:label key="miMember.uniteNumber.position" required="true" />
		    </c:if>
		    </th>
		    <td align="left">
		    	<form:input path="uniteNumber" id="uniteNumber" cssClass="text medium"/>
		    </td></tr>
	</c:if>
    --%>
    <tr class="edit_tr">
    	<th>
	    	<span class="text-font-style-tc"><jecs:label key="miMember.postalcode" /></span>
    	</th>
    	<td align="left">
	 		<span class="textbox">
	 			<form:input path="postalcode" id="postalcode" cssClass="textbox-text"/>
	 		</span>
	 		<%-- 
	 		<c:if test="${sessionScope.sessionLogin.companyCode=='JP' }">
	 	 		<input type="button" class="button" onclick="getPostalcode();" 
	 	 			value="<jecs:locale key="operation.button.search"/>" />
	 		</c:if>
	 		--%>
    	</td>
    	<th>
	 		<span class="text-font-style-tc"><jecs:label key="miMember.phone" /></span>
    	</th>
	    <td>
			<span class="textbox"><form:input path="phone" id="phone" cssClass="textbox-text"/></span>
	    </td>
	</tr>
	<tr class="edit_tr">
		<th>
	    	<span class="text-font-style-tc"><jecs:label  key="miMember.faxtele"/></span>
    	</th>
	    <td>
			<span class="textbox"><form:input path="faxtele" id="faxtele" cssClass="textbox-text"/></span>
	    </td>
	    <th>
			<span class="text-font-style-tc"><jecs:label key="miMember.mobiletele" required="true" /></span>
    	</th>
	    <td>
		  	<span class="textbox"><form:input path="mobiletele" id="mobiletele" cssClass="textbox-text"/></span>
	    </td>
	</tr>
	<tr class="edit_tr">
		<th>
	    	<span class="text-font-style-tc"><jecs:label  key="miMember.email"/></span>
    	</th>
	    <td align="left">
			<span class="textbox"><form:input path="email" id="email" cssClass="textbox-text"/></span>
	    </td>
	    <th>
	    	<span class="text-font-style-tc">
			<c:if test="${ sessionScope.sessionLogin.companyCode=='JP' }">
    			<jecs:label  key="miMember.mobile.mail"/>
			</c:if>
	  		<c:if test="${ sessionScope.sessionLogin.companyCode=='CN' }">
	    		<jecs:label key="miMember.addrcl" />
	  		</c:if>
	  		</span>
    	</th>
    	<td>
    		<span class="textbox">
			  	<c:if test="${ sessionScope.sessionLogin.companyCode=='JP' }">
			  		<form:input path="villageAddr" id="villageAddr" cssClass="textbox-text"/>
			  	</c:if>
			  	<c:if test="${ sessionScope.sessionLogin.companyCode=='CN' }">
			 		<form:input path="clAddress" id="clAddress" cssClass="textbox-text"/>
			  	</c:if>
		  	</span>
		</td>
	</tr>
	</tbody>
</table>
</fieldset>

    <%--<c:if test="${sessionScope.sessionLogin.companyCode=='RU'}">

<fieldset style="border-top: 2px solid #cccccc;border-right: 0px ;border-bottom: 0px ;border-left: 0px ;font-family: Arial, Helvetica, sans-serif;font-weight:bold;">
<legend ><jecs:locale key="member.bank.info"/>:</legend>
<table class='detail' width="100%">


     <tr><th >
	    <jecs:label key="miMember.bankProvince" required="true" />
    </th>
    <td align="left" nowrap="nowrap" width="250">
        <form:select path="bankProvince"  cssClass="text medium" onchange="getBankCity()">
					<form:option label="" value=""/>
		            <form:options items="${alStateProvinces}" itemValue="stateProvinceId" itemLabel="stateProvinceName"/>
		</form:select>
	
    </td><th>
	 <jecs:label  key="miMember.bankCity"/>
    </th>
    <td align="left">
	
        <select name="bankCity" id="bankCity" >
			<option value=""><jecs:locale key="list.please.select"/></option>
		</select>
		
    </td></tr>

     <tr><th>
	    <jecs:label key="miMember.openBank" required="true" />
    </th>
    <td align="left">
					<form:select path="bank"  cssClass="text medium">
						<form:option value=""></form:option>
			            <form:options items="${sysBankList}" itemValue="bankKey" itemLabel="bankValue"/>
			        </form:select>
    </td><th>
	<jecs:label key="miMember.bcity" required="true" />
    </th>
    <td align="left">
	 <form:input path="bankaddress" id="bankaddress" cssClass="text medium"/>
    </td></tr>

     <tr><th>
	    <jecs:label key="miMember.bname" required="true" />
    </th>
    <td align="left">
	 <jecs:locale key="miMember.openName.def" />
    </td><th>
	 <jecs:label key="miMember.bnum" required="true" />
    </th>
    <td align="left">
	 <form:input path="bankcard" id="bankcard" cssClass="text medium"/>
    </td></tr>
</table>
</fieldset>

</c:if>--%>
<%--<fieldset style="border-top: 2px solid #cccccc;border-right: 0px ;border-bottom: 0px ;border-left: 0px ;font-family: Arial, Helvetica, sans-serif;font-weight:bold;">
<legend ><jecs:locale key="member.shipping.info"/>:</legend>
<table class='detail' width="100%">


     <tr><th >
	    <jecs:label  key="shipping.firstName" required="true" />
    </th>
    <td align="left"  width="250px">
	 <input type="text" id="shippingFirstName" name="shippingFirstName" value="${shippingFirstName }" class="text medium"><br/>
	 <input type="checkbox" onclick="sameAsName(this);"><jecs:locale  key="member.register.same.name"/>
    </td><th>
	<jecs:label  key="shipping.lastName" required="true" />
    </th>
    <td align="left">
	  <input type="text" id="shippingLastName" name="shippingLastName" value="${shippingLastName }" class="text medium">
    </td></tr>

     <tr><th>
	    <jecs:label  key="shipping.province" required="true" />
    </th>
    <td align="left" >
	<select name="shippingProvince" id="shippingProvince" onchange="getCity()">
			<option value=""><jecs:locale key="list.please.select"/></option>
			<c:forEach items="${alStateProvinces}" var="var">
				<c:if test="${shippingProvince==var.stateProvinceId }">
					<option value="${var.stateProvinceId}" selected="selected">${var.stateProvinceName }</option>
				</c:if>
				<c:if test="${shippingProvince!=var.stateProvinceId }">
					<option value="${var.stateProvinceId }">${var.stateProvinceName }</option>
				</c:if>
			</c:forEach>
		</select><br/>
		 <input type="checkbox" onclick="sameAsIdAddr(this);"><jecs:locale key="member.register.same.addr"/>
    </td><th>
	<jecs:label  key="shipping.city" required="true" />
    </th>
    <td align="left">
	<select name="shippingCity" id="shippingCity" onchange="getDistrict()">
			<option value=""><jecs:locale key="list.please.select"/></option>
		</select>	
    </td></tr>

     <tr><th>
	    <jecs:label  key="shipping.district" />
    </th>
    <td align="left">
	 <select name="shippingDistrict" id="shippingDistrict" >
			<option value=""><jecs:locale key="list.please.select"/></option>
		</select>
    </td><th>
	 <jecs:label  key="shipping.address" required="true" />
    </th>
    <td align="left">
	<input type="text" id="shippingAddress" name="shippingAddress" value="${shippingAddress }" class="text medium">
    </td></tr>

     <tr><th>
	    <jecs:label  key="shipping.postalcode" required="true" />
    </th>
    <td align="left">
	 <input type="text" id="shippingPostalcode" name="shippingPostalcode" value="${shippingPostalcode }" class="text medium">
    </td><th>
	 <jecs:label  key="shipping.phone" required="true" />
    </th>
    <td align="left">
	<input type="text" id="shippingPhone" name="shippingPhone" value="${shippingPhone }" class="text medium">
    </td></tr>

     <tr><th>
	   <jecs:label  key="shipping.mobiletele" required="true" />
    </th>
    <td align="left">
	 <input type="text" id="shippingMobiletele" name="shippingMobiletele" value="${shippingMobiletele }" class="text medium">
    </td><th>
	
    </th>
    <td align="left">
	
    </td></tr>
</table>
</fieldset>--%>


<fieldset style="border-top: 2px solid #cccccc;border-right: 0px ;border-bottom: 0px ;border-left: 0px ;font-family: Arial, Helvetica, sans-serif;font-weight:bold;">
<legend ><jecs:locale key="member.security.info"/>:</legend>
<table class='detail' width="70%">
	<tbody class="window" >
	<tr class="edit_tr">
		<th>
	    	<span class="text-font-style-tc"><jecs:label key="miMember.pwd1" required="true" /></span>
    	</th>
	    <td>
		 	<span class="textbox">
		 		<form:password path="sysUser.password" cssClass="textbox-text" id="password1"/>
		 	</span>
	    </td>
	    <th>
		 	<span class="text-font-style-tc"><jecs:label key="label.affirmNewPassword" required="true" /></span>
	    </th>
	    <td>
		 	<span class="textbox">
		 		<input id="password1Confirm" name="password1Confirm" type="password" class="textbox-text"/>
		 	</span>
	    </td>
	</tr>

    <c:if test="${sessionScope.sessionLogin.companyCode!='TW'&& sessionScope.sessionLogin.companyCode!='JP'}">
    <tr class="edit_tr">
     	<th>
	    	<span class="text-font-style-tc"><jecs:label key="miMember.pwd2" required="true" /></span>
    	</th>
    	<td>
	 		<span class="textbox">
	 			<form:password path="sysUser.password2" cssClass="textbox-text" id="password2"/>
	 		</span>
    	</td>
    	<th>
		 	<span class="text-font-style-tc"><jecs:label key="label.affirmAdvancedPassword" required="true" /></span>
	    </th>
	    <td>
		 	<span class="textbox">
		 		<input id="password2Confirm" name="password2Confirm" type="password" class="textbox-text">
		 	</span>
	    </td>
	</tr>
    </c:if>
    <tr class="edit_tr">
    	<td colspan="4" align="center" class="command">
    		<button type="submit" class="btn btn_sele" name="save" onclick="bCancel=false">
				<jecs:locale key="operation.button.save"/>	
			</button>
			<button type="button" class="btn btn_sele" name="back"  
				onclick="window.location.href='jmiMembers.html?strAction=memberSearch'">
				<jecs:locale key="operation.button.return"/>
			</button>
		</td>
    </tr>
    </tbody>
</table>
</fieldset>

<script>
		   function getBankCity(){
		  	 var bankProvince=document.getElementById('bankProvince').value;
		   	 alCityManager.getAlCityByProvinceId(bankProvince,callBackBankCity);
		   
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
		   
		   
		   function getCity(){
		   var province=document.getElementById('shippingProvince').value;

		   	alCityManager.getAlCityByProvinceId(province,callBackCity);
		   
		   }
		   function callBackCity(valid){
			   var cityElemment=$('shippingCity');
        		cityElemment.options.length=0;
          		var o=new Option("<jecs:locale key="list.please.select"/>","");
           		cityElemment.options.add(o);  
           		
           		
			   var districtElemment=$('shippingDistrict');
        		districtElemment.options.length=0;
          		var o=new Option("<jecs:locale key="list.please.select"/>","");
           		districtElemment.options.add(o);  
           
		       for (var i=0;i<valid.length;i++) {
			        var cityId= valid[i].cityId;	   
			        var cityName=valid[i].cityName;   
			        var o=new Option(cityName,cityId);
			        cityElemment.options.add(o);  
			        if(cityId=='${shippingCity}'){
			        	o.selected=true;
			        }
			   }
			   		getDistrict();
			   
		   }

		   function getDistrict(){
		   	var city=document.getElementById('shippingCity').value;
		   		alDistrictManager.getAlDistrictByCityId(city,callBackDistrict);
		   }
		   function callBackDistrict(valid){
			   var districtElemment=document.getElementById('shippingDistrict');
    
        		districtElemment.options.length=0;
     
          		var o=new Option("<jecs:locale key="list.please.select"/>","");
           		districtElemment.options.add(o);  
           
		       for (var i=0;i<valid.length;i++) {
		        var districtId= valid[i].districtId;	   
		        var districtName=valid[i].districtName;   
		        var o=new Option(districtName,districtId);
		        districtElemment.options.add(o); 
		        
				   if('${shippingDistrict}'==districtId){
				   		o.selected=true;
				   } 
			   }

		   }
		</script>
		<script>
			
			
			
			function getIdCity(){
			
		   var province=document.getElementById('province').value;
		   	alCityManager.getAlCityByProvinceId(province,callIdCity);
		   
		   }
		   function callIdCity(valid){
			   var cityElemment=$('city');
        		cityElemment.options.length=0;
          		var o=new Option("<jecs:locale key="list.please.select"/>","");
           		cityElemment.options.add(o);  

           		
			   var districtElemment=$('district');
        		districtElemment.options.length=0;
          		var o=new Option("<jecs:locale key="list.please.select"/>","");
           		districtElemment.options.add(o);  

		       for (var i=0;i<valid.length;i++) {
			        var cityId= valid[i].cityId;	   
			        var cityName=valid[i].cityName;   
			        var o=new Option(cityName,cityId);
			        cityElemment.options.add(o);  
			        if(cityId=='${jmiMember.city}'){
			        	o.selected=true;
			        }
			   }
			   
			   		getIdDistrict();
			   
		   }

		   function getIdDistrict(){
		   	var city=document.getElementById('city').value;
		   		alDistrictManager.getAlDistrictByCityId(city,callBackIdDistrict);
		   }
		   function callBackIdDistrict(valid){
			   var districtElemment=document.getElementById('district');
    		
        		districtElemment.options.length=0;
     
          		var o=new Option("<jecs:locale key="list.please.select"/>","");
           		districtElemment.options.add(o);  
           
		       for (var i=0;i<valid.length;i++) {
		        var districtId= valid[i].districtId;	   
		        var districtName=valid[i].districtName;   
		        var o=new Option(districtName,districtId);
		        districtElemment.options.add(o); 
		        
				   if('${jmiMember.district}'==districtId){
				   		o.selected=true;
				   } 
			   }

    <c:if test="${sessionScope.sessionLogin.companyCode=='PH'}">
    	getIdTown();
    </c:if>
		   }
		   
		   
		   function getIdTown(){
		   	var district=document.getElementById('district').value;
		   		jalTownManager.getJalTownByDistrictId(district,callBackIdTown);
		   }
		   function callBackIdTown(valid){
			   var townElemment=document.getElementById('town');
    		
        		townElemment.options.length=0;
     
          		var o=new Option("<jecs:locale key="list.please.select"/>","");
           		townElemment.options.add(o);  
           
		       for (var i=0;i<valid.length;i++) {
		        var townId= valid[i].townId;	   
		        var townName=valid[i].townName;   
		        var o=new Option(townName,townId);
		        townElemment.options.add(o); 
		        
				   if('${jmiMember.town}'==townId){
				   		o.selected=true;
				   } 
			   }

		   }
		   
		   function getCommCity(){
			
		   var commProvince=document.getElementById('commProvince').value;
		   	alCityManager.getAlCityByProvinceId(commProvince,callCommCity);
		   
		   }
		   function callCommCity(valid){
			   var cityElemment=$('commCity');
        		cityElemment.options.length=0;
          		var o=new Option("<jecs:locale key="list.please.select"/>","");
           		cityElemment.options.add(o);  

		       for (var i=0;i<valid.length;i++) {
			        var cityId= valid[i].cityId;	   
			        var cityName=valid[i].cityName;   
			        var o=new Option(cityName,cityId);
			        cityElemment.options.add(o);  
			        if(cityId=='${jmiMember.commCity}'){
			        	o.selected=true;
			        }
			   }
			   getCommDistrict();
		   }


		   function getCommDistrict(){
		   	var city=document.getElementById('commCity').value;
		   		alDistrictManager.getAlDistrictByCityId(city,callBackCommDistrict);
		   }
		   function callBackCommDistrict(valid){
			   var districtElemment=document.getElementById('commDistrict');
    		
        		districtElemment.options.length=0;
     
          		var o=new Option("<jecs:locale key="list.please.select"/>","");
           		districtElemment.options.add(o);  
           
		       for (var i=0;i<valid.length;i++) {
		        var districtId= valid[i].districtId;	   
		        var districtName=valid[i].districtName;   
		        var o=new Option(districtName,districtId);
		        districtElemment.options.add(o); 
		        
				   if('${jmiMember.commDistrict}'==districtId){
				   		o.selected=true;
				   } 
			   }

		   }
		   
    <c:if test="${sessionScope.sessionLogin.companyCode=='TW'}">
    	getCommCity();
    </c:if>
    <c:if test="${sessionScope.sessionLogin.companyCode=='RU'}">
    	getBankCity();
    </c:if>
    
			getIdCity();
		</script>
</form:form>

<script type="text/javascript">


function changeIdInfoCheck(valid){
	if(valid=='2'){
		document.getElementById('companyNameDiv').style.display="";
		document.getElementById('companyAddrDiv').style.display="";
	}else{
		document.getElementById('companyNameDiv').style.display="none";
		document.getElementById('companyAddrDiv').style.display="none";
	}
}



    Form.focusFirstElement($('jmiMemberForm'));
    

	function isValidityBrithByIdCard(idCard){
		idCard = trim(idCard.replace(/ /g, ""));
		if (idCard.length == 15) {  
			isValidityBrithBy15IdCard(idCard);
		} else if (idCard.length == 18) {    
			isValidityBrithBy18IdCard(idCard);
		}
 
	}   
	function isValidityBrithBy18IdCard(idCard18){   
	    var year =  idCard18.substring(6,10);   
	    var month = idCard18.substring(10,12);   
	    var day = idCard18.substring(12,14);   
	    var temp_date = new Date(year,parseFloat(month)-1,parseFloat(day));     
	    if(temp_date.getFullYear()!=parseFloat(year)   
	          ||temp_date.getMonth()!=parseFloat(month)-1   
	          ||temp_date.getDate()!=parseFloat(day)){   
	             alert('false');
	    }else{      
	             setBirthday(year,month,day);
	    }  
	     
	}   

	  function isValidityBrithBy15IdCard(idCard15){   
	      var year =  idCard15.substring(6,8);   
	      var month = idCard15.substring(8,10);   
	      var day = idCard15.substring(10,12);   
	      var temp_date = new Date(year,parseFloat(month)-1,parseFloat(day));   

	      if(temp_date.getYear()!=parseFloat(year)   
	              ||temp_date.getMonth()!=parseFloat(month)-1   
	              ||temp_date.getDate()!=parseFloat(day)){   
	               alert('false');
	        }else{   
	             setBirthday(temp_date.getFullYear(),month,day); 
	        }   
	  }   
  	function setBirthday(year,month,day){
  		$('birthday').value=year+"-"+month+"-"+day;
  	} 
	function trim(str) {   
	    return str.replace(/(^\s*)|(\s*$)/g, "");   
	}  
	function sameAsName(checkBoxId){
		if(checkBoxId.checked){
			document.getElementById("shippingLastName").value=document.getElementById("lastName").value;
			document.getElementById("shippingFirstName").value=document.getElementById("firstName").value;
		}else{
			document.getElementById("shippingLastName").value='';
			document.getElementById("shippingFirstName").value='';
		}
		
	
	}
	function sameAsIdAddr(checkBoxId){
		if(checkBoxId.checked){
		
	DWREngine.setAsync(false);
		document.getElementById("shippingProvince").value=document.getElementById("province").value;
		getCity();
		document.getElementById("shippingCity").value=document.getElementById("city").value;
		getDistrict();
		document.getElementById("shippingDistrict").value=document.getElementById("district").value;
		document.getElementById("shippingAddress").value+=document.getElementById("address").value;
		document.getElementById("shippingPostalcode").value=document.getElementById("postalcode").value;
		document.getElementById("shippingMobiletele").value=document.getElementById("mobiletele").value;
		document.getElementById("shippingPhone").value=document.getElementById("phone").value;
		
		
		DWREngine.setAsync(true);
		}else{
			document.getElementById("shippingProvince").value='';
			document.getElementById("shippingCity").value='';
			document.getElementById("shippingDistrict").value='';
			document.getElementById("shippingAddress").value='';
			document.getElementById("shippingPostalcode").value='';
			document.getElementById("shippingPhone").value='';
			document.getElementById("shippingMobiletele").value='';
		
		}
	}
	   function sameAsIdInfo(){
	   
	DWREngine.setAsync(false);
				if (document.getElementById("sameIdInfo").checked == true) {
					for(var i = 0 ; i < document.getElementById("commCity").options.length ; i++){
						if(document.getElementById("commCity").options[i].value == document.getElementById("city").value){
							document.getElementById("commCity").selectedIndex = i;
						}
					}
					  getCommDistrict();
					for(var i = 0 ; i < document.getElementById("commDistrict").options.length ; i++){
						if(document.getElementById("commDistrict").options[i].value == document.getElementById("district").value){
							document.getElementById("commDistrict").selectedIndex = i;
						}
					}
					document.getElementById("commAddr").value=document.getElementById("address").value;
					document.getElementById("commPostalcode").value=document.getElementById("postalcode").value;
					
				}else{
					
				}
	
		DWREngine.setAsync(true);		
				
			}
	
		    function getPostalcodeTW(selObj){
		    	alDistrictManager.getAlDistrict(selObj.options[selObj.selectedIndex].value,callBackPostalcode);
		    }
		    function callBackPostalcode(valid){
		    	if(valid.postalcode!=null){
		    		document.getElementById('postalcode').value=valid.postalcode;
		    	}
		    }
		    function getCommPostalcode(selObj){
		    	alDistrictManager.getAlDistrict(selObj.options[selObj.selectedIndex].value,callBackCommPostalcode);
		    }
		    function callBackCommPostalcode(valid){
		    	if(valid.postalcode!=null){
		    		document.getElementById('commPostalcode').value=valid.postalcode;
		    	}
		    }
		    
		  
    <c:if test="${sessionScope.sessionLogin.companyCode=='JP'}"> 
		    document.getElementById('districtTh').innerHTML='';
	</c:if>
	
	
	  function getPostalcode(){
		   		var postalcode=document.getElementById('postalcode').value;
		   		jalPostalcodeManager.getJalPostalcodeByCode(postalcode,callGetPostalcode);
		   }
		    function callGetPostalcode(valid){
		    	if(valid!=null){
		    	
	DWREngine.setAsync(false);
		    		for(var i = 0 ; i < document.getElementById("province").options.length ; i++){
						if(document.getElementById("province").options[i].value ==valid.alCity.alStateProvince.stateProvinceId){
							document.getElementById("province").selectedIndex = i;
						}
					}
					 getIdCity();
					 for(var i = 0 ; i < document.getElementById("city").options.length ; i++){
						if(document.getElementById("city").options[i].value ==valid.alCity.cityId){
							document.getElementById("city").selectedIndex = i;
						}
					}
	DWREngine.setAsync(true);
		    	}
		    	
		    }
	
</script>

<%--<v:javascript formName="jmiMember" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>--%>

</c:if>