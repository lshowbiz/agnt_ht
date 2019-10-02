<%@ page language="java" errorPage="/error.jsp" contentType="text/html; charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jmiMemberDetail.title"/></title>
<content tag="heading"><jecs:locale key="jmiMemberDetail.heading"/></content>
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script> 


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

<form:form commandName="jmiMember" method="post" action="editJmiMember.html" id="jmiMemberForm">
<%-- 
	<div class="searchBar">	
			<input type="submit" class="button" name="save" value="<jecs:locale key="operation.button.save"/>" />

		<input type="button" class="button" name="cancel" onclick="window.location.href='jmiMembers.html?strAction=memberSearch'" value="<jecs:locale key="operation.button.return"/>" />
	</div>
--%>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<table class='detail' width="70%">
	<form:hidden path="userCode"/>
	<tbody class="window">
    <tr class="edit_tr">
	    <th>
	        <span class="text-font-style-tc"><jecs:label  key="miMember.memberNo"/></span>
	    </th>
	    <td>
	    	<span class="textbox">
	        <!--form:errors path="recommendNo" cssClass="fieldError"/-->
	        ${jmiMember.userCode }
	        <%--<form:input path="recommendNoJmiMember.userCode" id="recommendNo" cssClass="textbox-text"/>--%>
	        </span>
	    </td>
	    <th>
	    	<span class="text-font-style-tc"><jecs:label  key="miMember.cardType"/></span>
    	</th>
	    <td>
	    	 <!--form:errors path="cardType" cssClass="fieldError"/-->
        	<%--
        	<jecs:power powerCode="changeCardType">
				<jecs:list name="cardType" listCode="bd.cardtype" value="${jmiMember.cardType}" defaultValue="0"/>
			</jecs:power>
			--%>
			<span class="textbox">
				<jecs:code listCode="member.level" value="${jmiMember.memberLevel}"/>
				<input type="hidden" name="oldCardType" value="${oldCardType}">
	        </span>
	    </td>
	</tr>
    <tr class="edit_tr">
		<th>
			<span class="text-font-style-tc"><jecs:label  key="miMember.firstName"  required="true"/></span>
		</th>
		<td>
			<span class="textbox">
				<!--form:errors path="firstName" cssClass="fieldError"/-->
	        	<form:input path="firstName" id="firstName" cssClass="textbox-text"/>
        	</span>
		</td>
		<th>
			<span class="text-font-style-tc"><jecs:label  key="miMember.lastName"  required="true"/></span>
		</th>
		<td>
			<span class="textbox">
				<!--form:errors path="lastName" cssClass="fieldError"/-->
	        	<form:input path="lastName" id="lastName" cssClass="textbox-text"/>
	        </span>
		</td>
	</tr>
    <tr class="edit_tr">
	    <th>
	    	<span class="text-font-style-tc"><jecs:label  key="miMember.mobiletele"  required="true"/></span>
	    </th>
	    <td>
	    	<span class="textbox">
		        <!--form:errors path="mobiletele" cssClass="fieldError"/-->
		        <form:input path="mobiletele" id="mobiletele" cssClass="textbox-text"/>
	    	</span>
	    </td>
	    <th>
	    	<span class="text-font-style-tc"><jecs:label key="bdReconsumMoneyReport.companyCH"/></span>
	    	
	    </th>
	    <td>
	    	<span class="textbox">
	    		<jecs:company name="view" selected="${jmiMember.companyCode }" label="true"  withAA="false"  />
	    		<input type="hidden" id="oldCompanyCode" name="oldCompanyCode" value="${oldCompanyCode }" />
	    	</span>
	    </td>
	</tr>
	
    <tr class="edit_tr">
	    <th>
	   		<span class="text-font-style-tc"><jecs:label  key="miMember.recommendNo"/></span>
	    </th>
	    <td>
	    	<span class="textbox">
	        	${jmiMember.recommendNo }
	        </span>
    	</td>
    	<th>
    		<span class="text-font-style-tc">
		    	<!--form:errors path="linkNo" cssClass="fieldError"/-->
		    	<jecs:label  key="miMember.linkNo"/>
	    	</span>
	      	
    	</th>
    	<td>
    		<span class="textbox">${jmiMember.linkNo }</span>    
    	</td>
   	</tr>
    <tr class="edit_tr">
		<th>
			<span class="text-font-style-tc">
			<c:if test="${sessionScope.sessionLogin.companyCode!='PH'}">
	     		<jecs:label key="miMember.province" required="true" />
	   		</c:if>
      		<c:if test="${sessionScope.sessionLogin.companyCode=='PH'}">
	     		<jecs:label key="miMember.island" required="true" />
	   		</c:if>
	   		</span>
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
			<span class="text-font-style-tc">
	      	<c:if test="${sessionScope.sessionLogin.companyCode!='PH'}">
		 		<jecs:label key="busi.city" required="true" />
		   	</c:if>
	      	<c:if test="${sessionScope.sessionLogin.companyCode=='PH'}">
		   		<jecs:label key="miMember.region" required="true" />
		   	</c:if>
		   	</span>
		</th>
		<td>
			<span class="textbox">
		       	<select name="city" id="city" onchange="getIdDistrict();" class="textbox-text">
					<option value=""><jecs:locale key="list.please.select"/></option>
				</select>
			</span>
		</td>
	</tr>
	
    <tr class="edit_tr">
	    <th>
	    	<span class="text-font-style-tc">
	        <c:if test="${sessionScope.sessionLogin.companyCode!='PH'}">
		    	<jecs:label key="miMember.district" required="true" />
		   	</c:if>
	      	<c:if test="${sessionScope.sessionLogin.companyCode=='PH'}">
		   		<jecs:label key="miMember.province" required="true" />
		   	</c:if>
	        </span>
	    </th>
	    <td>
	       	<span class="textbox">
		    <c:if test="${sessionScope.sessionLogin.companyCode!='PH'}">
				<select name="district" id="district" onchange="getPostalcode(this)" class="textbox-text">
			</c:if>
	      	<c:if test="${sessionScope.sessionLogin.companyCode=='PH'}">
		 		<select name="district" id="district" onchange="getIdTown();" class="textbox-text">
		   	</c:if>
				<option value=""><jecs:locale key="list.please.select"/></option>
			</select>
	    </td>
	    <th>
	    	<span class="text-font-style-tc"><jecs:label  key="miMember.postalcode"/></span>
	    </th>
    	<td>
    		<span class="textbox">
	        	<!--form:errors path="postalcode" cssClass="fieldError"/-->
        		<form:input path="postalcode" id="postalcode" cssClass="textbox-text"/>
        	</span>
	    </td>
	</tr>
	
    <tr class="edit_tr">
	    <th>
	        <span class="text-font-style-tc"><jecs:label  key="busi.address"  required="true"/></span>
	    </th>
	    <td>
	    	<span class="textbox">
	        <!--form:errors path="address" cssClass="fieldError"/-->
	        <form:input path="address" id="address" cssClass="textbox-text"/>
	        </span>
	    </td>
	    <th>
        	<span class="text-font-style-tc"><jecs:label key="miMember.addrcl" /></span>
    	</th>
	    <td>
	    	<span class="textbox">
		 		<form:input path="clAddress" id="clAddress" cssClass="textbox-text"/>
		 	</span>
	    </td>
	</tr>

	<%-- 
    <c:if test="${sessionScope.sessionLogin.companyCode=='JP'}">
	    <tr><th>
	        	
	    </th>
	    <td align="left">
	     
	    </td><th>
	        <jecs:label key="miMember.building" required="true" />
	    </th>
	    <td align="left">
	        <!--form:errors path="address" cssClass="fieldError"/-->
	        <form:input path="building" id="building" cssClass="textbox-text"/>
	    </td></tr>
    </c:if>
	--%>
    
    <tr class="edit_tr">
    	<th>
		   	<span class="text-font-style-tc"><jecs:label key="miMember.firstNamePy"/></span>
	    </th>
	    <td>
			<span class="textbox"><form:input path="firstNamePy" id="firstNamePy" cssClass="textbox-text"/></span>
	    </td>
	    <th>
		   <span class="text-font-style-tc"><jecs:label key="miMember.lastNamePy"/></span>
	    </th>
	    <td>
	    	<span class="textbox">
				<form:input path="lastNamePy" id="lastNamePy" cssClass="textbox-text"/>
			</span>
	    </td>
	</tr>
	
	<%-- 
    <c:if test="${sessionScope.sessionLogin.companyCode=='JP'}">
    
	     <tr><th>
		   <jecs:label key="miMember.firstNameKana" required="true" />
	    </th>
	    <td align="left">
		 <form:input path="firstNameKana" id="firstNameKana" cssClass="textbox-text"/>
	    </td><th>
		 <jecs:label key="miMember.lastNameKana" required="true" />
	    </th>
	    <td align="left">
		<form:input path="lastNameKana" id="lastNameKana" cssClass="textbox-text"/>
	    </td></tr>
    </c:if>
    --%>

    <tr class="edit_tr">
    	<th>
    		<span class="text-font-style-tc">
		    	<c:if test="${sessionScope.sessionLogin.companyCode!='TW'}">
		        	<jecs:label  key="miMember.petName"  required="true"/>
		        </c:if>
		    	<c:if test="${sessionScope.sessionLogin.companyCode=='TW'}">
		        	<jecs:label  key="bdCalculatingSubDetail.name"  required="true"/>
		        </c:if>
	        </span>
   	 	</th>
	    <td>
	    	<span class="textbox">
	        <!--form:errors path="petName" cssClass="fieldError"/-->
	    	<c:if test="${sessionScope.sessionLogin.companyCode!='TW'}">
	        	<form:input path="petName" id="petName" cssClass="textbox-text"/>
	        </c:if>
	    	<c:if test="${sessionScope.sessionLogin.companyCode=='TW'}">
	        	${jmiMember.miUserName }
	        </c:if>
	        </span>
	    </td>
	    <th>
	        <span class="text-font-style-tc"><jecs:label  key="miMember.sex"/></span>
	    </th>
	    <td>
	    	<span class="textbox">
	        	<!--form:errors path="sex" cssClass="fieldError"/-->
	         	<jecs:list name="sex" listCode="sex" value="${jmiMember.sex}" defaultValue="" styleClass="textbox-text"/>
	        </span>
	    </td>
	</tr>
	
    <tr class="edit_tr">
	    <th>
	    	<span class="text-font-style-tc"><jecs:label key="miMember.spouseName" /></span>
	    </th>
	    <td>
	    	<span class="textbox">
	        <!--form:errors path="postalcode" cssClass="fieldError"/-->
	        <form:input path="spouseName" id="spouseName" cssClass="textbox-text"/>
	        </span>	
	    </td>
	    <th>
	        <span class="text-font-style-tc"><jecs:label key="miMember.spouseIdno" /></span>
	    </th>
	    <td>
	    	<span class="textbox">
	        <!--form:errors path="postalcode" cssClass="fieldError"/-->
	        <form:input path="spouseIdno" id="spouseIdno" cssClass="textbox-text"/>
	        </span>
	    </td>
	</tr>
	
<%-- 
    <c:if test="${sessionScope.sessionLogin.companyCode=='PH' || 
    				sessionScope.sessionLogin.companyCode=='TW'|| 
    				sessionScope.sessionLogin.companyCode=='US'|| 
    				sessionScope.sessionLogin.companyCode=='JP'}">
    	     <tr><th>
		   <jecs:label key="miMember.birthday"  />
	    </th>
	    <td align="left">
		<form:input path="birthday" id="birthday"  cssClass="textbox-text"/>
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
	    
    <c:if test="${sessionScope.sessionLogin.companyCode=='TW' || sessionScope.sessionLogin.companyCode=='JP'}">
		 <jecs:label key="miMember.identityType" />
	</c:if>
	
    <c:if test="${sessionScope.sessionLogin.companyCode=='US'}">
		 <jecs:label key="miMember.companyName" />
	</c:if>
	<c:if test="${sessionScope.sessionLogin.companyCode=='PH'}">
		<jecs:label key="miMember.nationality"  />
	</c:if>
	
	
	    </th>
	    <td align="left">
    <c:if test="${sessionScope.sessionLogin.companyCode=='TW' || sessionScope.sessionLogin.companyCode=='JP'}">
	 		<jecs:list name="identityType" listCode="identitytype.tw" value="${jmiMember.identityType}" defaultValue=""/>	
	</c:if>
	
    <c:if test="${sessionScope.sessionLogin.companyCode=='US'}">
		<form:input path="companyName" id="companyName" cssClass="textbox-text"/>
	</c:if>
	<c:if test="${sessionScope.sessionLogin.companyCode=='PH'}">
		<jecs:list name="nationality" id="nationality" listCode="nationality" value="${jmiMember.nationality}" defaultValue="PH"/>
	</c:if>
	
	    </td></tr>
    </c:if>
 --%>
 <%-- 
    <c:if test="${sessionScope.sessionLogin.companyCode=='TW' || sessionScope.sessionLogin.companyCode=='JP'}">
    

    
	     <tr><th>
    <c:if test="${sessionScope.sessionLogin.companyCode=='TW'}">
		   <jecs:label key="miMember.villageAddr"  />
		  </c:if>
    <c:if test="${sessionScope.sessionLogin.companyCode=='JP'}">
		   <jecs:label key="miMember.mobile.mail"  />
    </c:if>
	    </th>
	    <td align="left">
		 	 <form:input path="villageAddr" id="villageAddr" cssClass="textbox-text"/>
	    </td><th>
	    
    <c:if test="${sessionScope.sessionLogin.companyCode=='TW'}">
		 <jecs:label key="miMember.townAddr" />
		 </c:if>
	    </th>
	    <td align="left">
    <c:if test="${sessionScope.sessionLogin.companyCode=='TW'}">
		 	 <form:input path="townAddr" id="townAddr" cssClass="textbox-text"/>
		 </c:if>
	    </td></tr>
    
	    </c:if>
--%>  
<%--  
    <c:if test="${sessionScope.sessionLogin.companyCode=='TW'}">
    
	     <tr><th>
		  
	    </th>
	    <td align="left">
		 	 <input type="checkbox" id="sameIdInfo" name="sameIdInfo" value="true" onclick="sameAsIdInfo()"><jecs:locale key="same.as.idinfo"  />
	    </td><th>
		  
	    </th>
	    <td align="left">
		 	
	    </td></tr>
	     <tr><th>
		   <jecs:label key="miMember.commProvince"  />
	    </th>
	    <td align="left">
			 <form:select path="commProvince"  cssClass="textbox-text" onchange="getCommCity();">
							<form:option label="" value=""/>
				            <form:options items="${alStateProvinces}" itemValue="stateProvinceId" itemLabel="stateProvinceName"/>
			</form:select>
	    </td><th>
		 <jecs:label key="miMember.commCity" />
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
		   <jecs:label key="miMember.commAddr"  />
	    </th>
	    <td align="left">
		 	 <form:input path="commAddr" id="commAddr" cssClass="textbox-text"/>
	    </td><th>
		 <jecs:label key="miMember.commPostalcode" />
	    </th>
	    <td align="left">
		 	 <form:input path="commPostalcode" id="commPostalcode" cssClass="textbox-text"/>
	    </td></tr>
	    
    </c:if>
--%>    
<%-- 
    <c:if test="${sessionScope.sessionLogin.companyCode=='TW' || sessionScope.sessionLogin.companyCode=='JP'}">   
	     <tr><th>
		   <jecs:label key="miMember.companyName"  />
	    </th>
	    <td align="left">
		 	 <form:input path="companyName" id="companyName" cssClass="textbox-text"/>
	    </td><th>
		    <c:if test="${sessionScope.sessionLogin.companyCode=='TW' }">
			  	<jecs:label key="miMember.personCharge" required="true" />
		    </c:if>
		    <c:if test="${sessionScope.sessionLogin.companyCode=='JP' }">
			  	<jecs:label key="miMember.personCharge.comName" required="true" />
		    </c:if>
	    </th>
	    <td align="left">
		 	 <form:input path="personCharge" id="personCharge" cssClass="textbox-text"/>
	    </td></tr>
	    
	     <tr><th>
		    <c:if test="${sessionScope.sessionLogin.companyCode=='TW' }">
			   <jecs:label key="miMember.companyAddr" required="true" />
		    </c:if>
		    <c:if test="${sessionScope.sessionLogin.companyCode=='JP' }">
			   <jecs:label key="miMember.companyAddr.depart" required="true" />
		    </c:if>
	    </th>
	    <td align="left">
		 	 <form:input path="companyAddr" id="companyAddr" cssClass="textbox-text"/>
	    </td><th>
		    <c:if test="${sessionScope.sessionLogin.companyCode=='TW' }">
			  	<jecs:label key="miMember.uniteNumber" required="true" />
		    </c:if>
		    <c:if test="${sessionScope.sessionLogin.companyCode=='JP' }">
			  	<jecs:label key="miMember.uniteNumber.position" required="true" />
		    </c:if>
	    </th>
	    <td align="left">
		 	 <form:input path="uniteNumber" id="uniteNumber" cssClass="textbox-text"/>
	    </td></tr>

    </c:if>
  --%>
  	<tr class="edit_tr">
	    <th>
	        <span class="text-font-style-tc"><jecs:label  key="miMember.checkNo"/></span>
	    </th>
	    <td>
	    	<span class="textbox">${jmiMember.checkNo }</span>
	    </td>
	    <th>
	    	<span class="text-font-style-tc"><jecs:label  key="logType.C"/></span>
	    </th>
	    <td>
	    	<span class="textbox">${jmiMember.checkDate }</span>
	    </td>
	</tr>
    <tr class="edit_tr">
	    <th>
	        <span class="text-font-style-tc"><jecs:label  key="miMember.papertype"/></span>
	    </th>
	    <td>
	        <span class="textbox">
		    <c:if test="${sessionScope.sessionLogin.companyCode=='TW'}">
		         <jecs:list name="papertype" listCode="papertype.tw" value="${jmiMember.papertype}" defaultValue="" styleClass="textbox-text"/>	
		    </c:if>
		    <c:if test="${sessionScope.sessionLogin.companyCode!='TW'}">
		         <jecs:list name="papertype" listCode="papertype" value="${jmiMember.papertype}" defaultValue="" styleClass="textbox-text"/>
		    </c:if>
	    	</span>
	    </td>
	    <th>
	    	<span class="text-font-style-tc"><jecs:label  key="miMember.papernumber"  required="true"/></span>
	    </th>
	    <td>
	    	<span class="textbox">
	        <!--form:errors path="papernumber" cssClass="fieldError"/-->
	        <form:input path="papernumber" id="papernumber" cssClass="textbox-text"/> tips:修改身份证时请注意瓜藤网激活手机
	        </span>
	    </td>
	</tr>

    <tr class="edit_tr">
	    <th>
	        <span class="text-font-style-tc"><jecs:label  key="miMember.createNo"/></span>
	    </th>
	    <td>
	    	<span class="textbox">${jmiMember.createNo }</span>
	    </td>
	    <th>
	        <span class="text-font-style-tc"><jecs:label  key="miMember.createTime"/></span>
	    </th>
	    <td>
	        <span class="textbox">${jmiMember.createTime }</span>
	    </td>
	</tr>

	<tr class="edit_tr">
		<th>
			<span class="text-font-style-tc"><jecs:label  key="miMember.email"/></span>
	    </th>
	    <td>
	    	<span class="textbox">
	        <!--form:errors path="email" cssClass="fieldError"/-->
	        <form:input path="email" id="email" cssClass="textbox-text"/>
	        </span>
	    </td>
	    <th>
	    	<span class="text-font-style-tc"><jecs:label  key="miMember.faxtele"/></span>
    	</th>
	    <td>
	    	<!--form:errors path="faxtele" cssClass="fieldError"/-->
			<span class="textbox"><form:input path="faxtele" id="faxtele" cssClass="textbox-text"/></span>
	    </td>
	</tr>
	<tr class="edit_tr">
		<th>
	        <span class="text-font-style-tc"><jecs:label  key="miMember.phone" /></span>
	    </th>
	    <td>
	    	<span class="textbox">
	        <!--form:errors path="phone" cssClass="fieldError"/-->
	        <form:input path="phone" id="phone" cssClass="textbox-text"/>
	        </span>
	    </td>
	    <th>
	        <span class="text-font-style-tc"><jecs:label  key="miMember.exitDate"/></span>
	    </th>
	    <td>
	      <span class="textbox">${jmiMember.exitDate }</span>
	    </td>
	</tr>
	
    <tr class="edit_tr">
	    <th>
	        <span class="text-font-style-tc-tare"><jecs:label  key="miMember.remark"/></span>
	    </th>
	    <td colspan="3">
	    	<span class="text-font-style-tc-right">
		        <!--form:errors path="remark" cssClass="fieldError"/-->
		        <form:textarea path="remark"  cssClass="textarea_border"/>
	        </span>
	    </td>
	</tr>
	<tr class="edit_tr">
    	<th>
        	<span class="text-font-style-tc"><jecs:label  key="miMember.openBank" /></span>
    	</th>
	    <td>
	        <span class="textbox">${jmiMember.bank }</span>
	    </td>
	    <th>
	        <span class="text-font-style-tc"><jecs:label  key="miMember.bcity"/></span>
	    </th>
	    <td>
	    	<span class="textbox">${jmiMember.bankaddress }</span>
	    </td>
	</tr>
    <tr class="edit_tr">
	    <th>
	        <span class="text-font-style-tc"><jecs:label  key="miMember.bnum"/></span>
	    </th>
	    <td>
	       <span class="textbox"> ${jmiMember.bankcard }</span>
	    </td>
		<th>
	        <span class="text-font-style-tc"><jecs:label  key="miMember.bname" /></span>
	    </th>
	    <td>
	    	<span class="textbox">${jmiMember.bankbook }</span>
	    </td>
	</tr>

	<jecs:power powerCode="changeMemberPayBank">
    <tr class="edit_tr">
	    <th>
	    	<span class="text-font-style-tc"><jecs:label key="aiAgent.pbNo"/></span>
	    </th>
		<td>
			<span class="textbox">
			<select name="pbNo" class="textbox-text">
				<option value=""><jecs:locale key="list.please.select" /></option>
	            <c:forEach items="${fiPayBanks }" var="fi">
					<option value="${fi.accountCode }"
						<c:if test="${fi.accountCode==jmiMember.pbNo}">selected</c:if>>
						${fi.accountCode }-${fi.bankName }
					</option>
				</c:forEach>
	        </select> 
	        </span>
	    </td>
		<th>
      		<span class="text-font-style-tc"><jecs:label key="aiAgent.pbNo1"/></span>
    	</th>
		<td>
			<span class="textbox">
			<select name="pbNo1" class="textbox-text">
				<option value=""><jecs:locale key="list.please.select" /></option>
            	<c:forEach items="${fiPayBanks }" var="fi">
				<option value="${fi.accountCode }"
					<c:if test="${fi.accountCode==jmiMember.pbNo1}">selected</c:if>>
					${fi.accountCode }-${fi.bankName }
				</option>
				</c:forEach>
        	</select> 
        	</span>
    	</td>
   	</tr>
    <tr class="edit_tr">
    	<th>
      		<span class="text-font-style-tc"><jecs:label key="aiAgent.pbNo2"/></span>
    	</th>
		<td>
			<span class="textbox">
			<select name="pbNo2" class="textbox-text">
				<option value=""><jecs:locale key="list.please.select" /></option>
	            <c:forEach items="${fiPayBanks }" var="fi">
					<option value="${fi.accountCode }"
						<c:if test="${fi.accountCode==jmiMember.pbNo2}">selected</c:if>>
						${fi.accountCode }-${fi.bankName }
					</option>
				</c:forEach>
	        </select>
	        </span>
	    </td>
	    <th>
	    	<c:if test="${jmiMember.isstore=='1' }">
	    	<span class="text-font-style-tc">
	    		<jecs:label  key="aiAgent.deadlineDate"  required="true"/>
	    	</span>
	    	</c:if>
	    </th>
	    <td>
	    	<c:if test="${jmiMember.isstore=='1' }">
	    		<span class="textbox">
	    			<form:input path="deadlineDate" id="deadlineDate" cssClass="textbox-text"/>
	    		</span>
	    	</c:if>
	    </td>
	</tr>
	</jecs:power>
  	<jecs:power powerCode="changeMemberStore">
    <tr class="edit_tr">
	    <th>
	        <span class="text-font-style-tc"><jecs:label  key="miMember.store.type"  required="true"/></span>
	    </th>
	    <td>
	    	<span class="textbox">
	    		<jecs:list name="isstore" listCode="isstore" value="${jmiMember.isstore}" defaultValue="" styleClass="textbox-text"/>
	    	</span>
	    </td>
	    <th>
	    	<jecs:power powerCode="changeMemberShopType">
        	<span class="text-font-style-tc"><jecs:label  key="miMember.shop.type"/></span>
	        </jecs:power>	
    	</th>
	    <td>
	    	<jecs:power powerCode="changeMemberShopType">
	    	<span class="textbox">
	        	<jecs:list name="shopType" listCode="shop.type" 
	        		value="${jmiMember.shopType}" defaultValue="" showBlankLine="true" styleClass="textbox-text"/>
	        </span>
	        </jecs:power>
	    </td>
	</tr>
    
    <tr class="edit_tr">
	    <th>
	        <span class="text-font-style-tc"><jecs:label  key="miMember.recommendStore" /></span>
	    </th>
	    <td>
	        <span class="textbox">
	        	<form:input path="recommendStore" id="recommendStore" cssClass="textbox-text"/>
			</span>
	    </td>
	    <th>
	    	<span class="text-font-style-tc"><jecs:label  key="miMember.subRecommendStore" /></span>
	    </th>
	    <td>
	    	<span class="textbox">
	    		<form:input path="subRecommendStore" id="subRecommendStore" cssClass="textbox-text"/>
	    	</span>
	    </td>
	</tr>
	</jecs:power>
  	
  	<input type="hidden" id="oldIsstore" name="oldIsstore" value="${oldIsstore }" />
	<input type="hidden" id="oldFreezeStatus" name="oldFreezeStatus" value="${oldFreezeStatus }" />
  	<input type="hidden" id="oldShopType" name="oldShopType" value="${oldShopType }" />
  	
  	<jecs:power powerCode="validFreezeWeek">
    <tr class="edit_tr">
	    <th>
	        <span class="text-font-style-tc"><jecs:label  key="jmiMember.startWeek"/></span>
	    </th>
	    <td>	
	        <span class="textbox"><form:input path="startWeek" id="startWeek" cssClass="textbox-text"/></span>
	    </td>
	    <th>
	        <span class="text-font-style-tc"><jecs:label  key="jmiMember.validWeek"/></span>
	    </th>
	    <td>	
	        <span class="textbox"><form:input path="validWeek" id="validWeek" cssClass="textbox-text"/></span>
	    </td>
	</tr>
    
    <tr class="edit_tr">
	    <th>
	       <span class="text-font-style-tc"><jecs:label  key="miMember.freezestatus"  /></span>
	    </th>
	    <td>	
	        <span class="textbox">
	        	<jecs:list name="freezeStatus" listCode="mimember.freezestatus" 
	        		value="${jmiMember.freezeStatus}" defaultValue="" styleClass="textbox-text"/>
	        </span>	
	    </td>
	    <th>
	       <span class="text-font-style-tc"><label>瓜藤网手机号:</label></span>
	    </th>
	    <td>	
	       <span class="textbox">
	       	<form:input path="ecMallPhone" id="ecMallPhone" cssClass="textbox-text"/>
	       </span>
	    </td>
	</tr>
	</jecs:power>
	
	
	 <tr class="edit_tr">
	    <th>
	       <span class="text-font-style-tc"><label>是否云店:</label></span>
	    </th>
	    <td>	
	        <span class="textbox">
	        	<%-- <jecs:list name="isCloudshop" listCode="yesno" value="${jmiMember.isCloudshop }" defaultValue="" styleClass="textbox-text"/> --%>
	        	<jecs:code listCode="yesno" value="${jmiMember.isCloudshop }"></jecs:code>
				<input type="hidden" name="oldIsCloudshop" value="${oldIsCloudshop}">
	        </span>	
	    </td>
	    <th>
	       <span class="text-font-style-tc"></span>
	    </th>
	    <td>	
	       <span class="textbox">
	       	
	       </span>
	    </td>
	</tr>
	
  	<tr>
  		<th>
	       <span class="text-font-style-tc-tare">
	       	<jecs:label  key="miMember.titleRemark"/>
	       </span>
	    </th>
	    <td colspan="3">	
	          <span class="text-font-style-tc-right">
	          	<form:textarea path="titleRemark"  cssClass="textarea_border"/>
	          </span>
	    </td>   
	</tr>
	<tr>
		<td colspan="4" align="center">
			<button type="submit" class="btn btn_sele" name="save">
				<jecs:locale key="operation.button.save"/>
			</button>
			<button type="button" class="btn btn_sele" name="cancel" 
				onclick="window.location.href='jmiMembers.html?strAction=memberSearch'">
				<jecs:locale key="operation.button.return"/>
			</button>
		</td>
	</tr>
	</tbody> 
</table>

</form:form>

<script type="text/javascript">
    //Form.focusFirstElement($('miMemberForm'));
    getIdCity();
			
			
			
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
		   function getPostalcode(selObj){
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
    <c:if test="${sessionScope.sessionLogin.companyCode=='TW'}">
    	getCommCity();
    </c:if>
    
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
			
			
	  function getPostalcodeJp(){
		   		var postalcode=document.getElementById('postalcode').value;
		   		jalPostalcodeManager.getJalPostalcodeByCode(postalcode,callGetPostalcodeJp);
		   }
		    function callGetPostalcodeJp(valid){
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

