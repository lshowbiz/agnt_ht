<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jmiMemberDetail.title"/></title>
<content tag="heading"><jecs:locale key="jmiMemberDetail.heading"/></content>

<script type="text/javascript" src="<c:url value='/dwr/util.js'/>" ></script> 
<script type="text/javascript" src="<c:url value='/dwr/engine.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/dwr/interface/alCityManager.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/dwr/interface/alDistrictManager.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/dwr/interface/jalTownManager.js'/>" ></script>
<%-- 
	<div class="searchBar">
		<input type="button" class="button" name="cancel" onclick="window.history.back()" value="<jecs:locale key="operation.button.return"/>" />
	
	</div>
--%>
<table class='detail' width="70%">
<tbody class="window">
	
	<tr class="edit_tr">
		<th>
	        <span class="text-font-style-tc"><jecs:label  key="miMember.memberNo"/></span>
	    </th>
	    <td>
	        <span class="textbox">${jmiMember.userCode }</span>
	    </td>
	    <th>
	        <span class="text-font-style-tc"><jecs:label  key="miMember.papertype"/></span>
	    </th>
	    <td>
	    	<span class="textbox">
	        <!--form:errors path="papertype" cssClass="fieldError"/-->
		    <c:if test="${sessionScope.sessionLogin.companyCode=='TW'}">
		        <jecs:code listCode="papertype.tw" value="${jmiMember.papertype}"/>
		    </c:if>
		    <c:if test="${sessionScope.sessionLogin.companyCode!='TW'}">
		        <jecs:code listCode="papertype" value="${jmiMember.papertype}"/>
		    </c:if>
		    </span>
	    </td>
	</tr>
   
    <tr class="edit_tr">
	    <th>
	        <span class="text-font-style-tc"><jecs:label  key="miMember.recommendNo"/></span>
	    </th>
	    <td>
	        <span class="textbox">${jmiMember.recommendNo }</span>
	    </td>
	    <th>
	        <span class="text-font-style-tc"><jecs:label  key="miMember.papernumber"  /></span>
	    </th>
	    <td>
	        <!--form:errors path="papernumber" cssClass="fieldError"/-->
	       <span class="textbox">${jmiMember.papernumber}</span>
	    </td>
	</tr>
    
    <tr class="edit_tr">
	    <th>
	        <span class="text-font-style-tc"><jecs:label  key="miMember.linkNo"/></span>
	    </th>
	    <td>
	        <span class="textbox">${jmiMember.linkNo }</span>
	    </td>
	    <th>
	        <span class="text-font-style-tc"><jecs:label  key="miMember.phone"  />
	    </th>
	    <td>
	        <!--form:errors path="phone" cssClass="fieldError"/-->
	       <span class="textbox">${jmiMember.phone}</span>
	    </td>
	</tr>


    <tr class="edit_tr">
	    <th>
	        <span class="text-font-style-tc"><jecs:label  key="miMember.cardType"/></span>
	    </th>
	    <td>
	        <span class="textbox"><jecs:code listCode="member.level" value="${jmiMember.memberLevel}"/></span>
	    </td>
	    <th>
	        <span class="text-font-style-tc"><jecs:label  key="miMember.faxtele"/></span>
	    </th>
	    <td>
	        <!--form:errors path="faxtele" cssClass="fieldError"/-->
	       <span class="textbox">${jmiMember.faxtele}</span>
	    </td>
	</tr>

    <tr class="edit_tr">
	    <th>
	        <span class="text-font-style-tc"><jecs:label  key="miMember.firstName"  /></span>
	    </th>
	    <td >
	        <!--form:errors path="firstName" cssClass="fieldError"/-->
	       <span class="textbox">${jmiMember.firstName}</span>
	    </td>
	    <th>
	        <span class="text-font-style-tc"><jecs:label  key="miMember.mobiletele"  /></span>
	    </th>
	    <td >
	        <!--form:errors path="mobiletele" cssClass="fieldError"/-->
	       <span class="textbox">${jmiMember.mobiletele}</span>
	    </td>
	</tr>


    <c:if test="${sessionScope.sessionLogin.companyCode=='JP'}">
    <tr class="edit_tr">
	    <th>
		   	<span class="text-font-style-tc"><jecs:label key="miMember.firstNameKana" required="true" /></span>
	    </th>
	    <td >
		 	<span class="textbox">${jmiMember.firstNameKana}</span>
	    </td>
	    <th>
		 	<span class="text-font-style-tc"><jecs:label key="miMember.lastNameKana" required="true" /></span>
	    </th>
	    <td >
			<span class="textbox">${jmiMember.lastNameKana}</span>
	    </td>
	</tr>
    </c:if>
    <tr class="edit_tr">
	    <th>
	        <span class="text-font-style-tc"><jecs:label  key="miMember.lastName"  /></span>
	    </th>
	    <td >
	        <!--form:errors path="lastName" cssClass="fieldError"/-->
	      <span class="textbox"> ${jmiMember.lastName}</span>
	    </td>
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
	    <td >
	        <!--form:errors path="officetele" cssClass="fieldError"/-->
			<span class="textbox">
			<c:forEach items="${alStateProvinces }" var="al">
	    	<c:if test="${al.stateProvinceId==jmiMember.province }">
	    		<c:out value="${al.stateProvinceName}" />
	    	</c:if>
	    	</c:forEach>
	    	</span>
	    	 <input type="hidden" value="${jmiMember.province}" id="province" name="province">
	    </td>
	</tr>


    <tr class="edit_tr">
	    <th>
	        <span class="text-font-style-tc"><jecs:label key="miMember.spouseName" /></span>
	    </th>
	    <td >
	        <!--form:errors path="postalcode" cssClass="fieldError"/-->
	       <span class="textbox"> ${jmiMember.spouseName }</span>
	    </td>
	    <th>
	    	<span class="text-font-style-tc">
	      	<c:if test="${sessionScope.sessionLogin.companyCode!='PH'}">
		 		<jecs:label key="miMember.idAddr2" required="true" />
		  	</c:if>
	      	<c:if test="${sessionScope.sessionLogin.companyCode=='PH'}">
		  		<jecs:label key="miMember.region" required="true" />
		  	</c:if>
		  	</span>
	    </th>
	    <td >
	        <!--form:errors path="province" cssClass="fieldError"/-->
			<span class="textbox">         
		       	<select name="city" id="city" onchange="getIdDistrict();" disabled="true" class="textbox-text">
					<option value=""><jecs:locale key="list.please.select"/></option>
				</select>
			</span>
	    </td>
	</tr>
    
    <tr class="edit_tr">
	    <th>
	        <span class="text-font-style-tc"><jecs:label key="miMember.spouseIdno" /></span>
	    </th>
	    <td >
	        <!--form:errors path="postalcode" cssClass="fieldError"/-->
	        <span class="textbox">${jmiMember.spouseIdno }</span>
	    </td>
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
	    <td >
	    	<span class="textbox">
			 <c:if test="${sessionScope.sessionLogin.companyCode=='PH'}">
			<select name="district" id="district" onchange="getIdTown();" disabled="true" class="textbox-text">
				<option value=""><jecs:locale key="list.please.select"/></option>
			</select>	
			 </c:if>
			 <c:if test="${sessionScope.sessionLogin.companyCode!='PH'}">
			<select name="district" id="district" disabled="true" class="textbox-text">
				<option value=""><jecs:locale key="list.please.select"/></option>
			</select>	
			 </c:if>
			 </span>
	    </td>
	</tr>

    <c:if test="${sessionScope.sessionLogin.companyCode=='PH'}">
    
	<tr class="edit_tr">
	    <th></th>
	    <td></td>
	    <th>
	       <span class="text-font-style-tc"><jecs:label  key="miMember.district" /></span>
	    </th>
	    <td >
	    	<span class="textbox">
		 	<select name="town" id="town"  disabled="true">
				<option value=""><jecs:locale key="list.please.select"/></option>
			</select>
	      	</span>
	    </td>
	</tr>
    
    </c:if>

    <tr class="edit_tr">
	    <th>
	        <span class="text-font-style-tc"><jecs:label  key="miMember.petName"  /></span>
	    </th>
	    <td >
	        <!--form:errors path="petName" cssClass="fieldError"/-->
	       <span class="textbox">${jmiMember.petName}</span>
	    </td>
	    <th>
	        <span class="text-font-style-tc"><jecs:label  key="busi.address"  /></span>
	    </th>
	    <td >
	        <!--form:errors path="address" cssClass="fieldError"/-->
	       <span class="textbox">${jmiMember.address}</span>
	    </td>
	</tr>
    
    
 <c:if test="${sessionScope.sessionLogin.companyCode=='JP' }">
	<tr class="edit_tr">
	 	<th></th>
	    <td></td>
	    <th>
	       <span class="text-font-style-tc"><jecs:label key="miMember.building" /></span>
	    </th>
	    <td >
	        <!--form:errors path="address" cssClass="fieldError"/-->
	       <span class="textbox">${jmiMember.building}</span>
	    </td>
	</tr>
    
 </c:if>

    <tr class="edit_tr">
	    <th>
	        <span class="text-font-style-tc"><jecs:label  key="miMember.sex"/></span>
	    </th>
	    <td >
	        <!--form:errors path="sex" cssClass="fieldError"/-->
	        <span class="textbox"><jecs:code listCode="sex" value="${jmiMember.sex}"/></span>
	    </td>
	    <th>
	        <span class="text-font-style-tc"><jecs:label  key="miMember.postalcode"  /></span>
	    </th>
	    <td >
	        <!--form:errors path="postalcode" cssClass="fieldError"/-->
	       <span class="textbox">${jmiMember.postalcode}</span>
	    </td>
	</tr>
<%--
    <c:if test="${sessionScope.sessionLogin.companyCode=='TW' || 
				    sessionScope.sessionLogin.companyCode=='PH' || 
				    sessionScope.sessionLogin.companyCode=='US'}">
	<tr class="edit_tr">
	     <th>
		   <span class="text-font-style-tc"><jecs:label key="miMember.birthday"  /></span>
	    </th>
	    <td >
	    	<span class="textbox"><fmt:formatDate value="${jmiMember.birthday}" pattern="yyyy-MM-dd"/></span>
		</td>
	    <th>
	    	<span class="text-font-style-tc">
		    <c:if test="${sessionScope.sessionLogin.companyCode=='TW'}">
				 <jecs:label key="miMember.identityType" />
		    </c:if>
		    <c:if test="${sessionScope.sessionLogin.companyCode=='US'}">
				 <jecs:label key="miMember.companyName" />
			</c:if>
			</span>
	    </th>
	    <td>
	    	<span class="textbox">
		    <c:if test="${sessionScope.sessionLogin.companyCode=='TW'}">
			    	 <jecs:code listCode="identitytype.tw" value="${jmiMember.identityType}"/>
		    </c:if>
		    <c:if test="${sessionScope.sessionLogin.companyCode=='US'}">
				${jmiMember.companyName}
			</c:if>
			</span>
	    </td>
	</tr>
    </c:if>

    
    <c:if test="${sessionScope.sessionLogin.companyCode=='TW' || sessionScope.sessionLogin.companyCode=='JP'}">
    <tr><th>
    <c:if test="${sessionScope.sessionLogin.companyCode=='TW'}">
		   <jecs:label key="miMember.villageAddr"  />
		  </c:if>
    <c:if test="${sessionScope.sessionLogin.companyCode=='JP'}">
		   <jecs:label key="miMember.mobile.mail"  />
    </c:if>
	    </th>
	    <td >
			${jmiMember.villageAddr}
	    </td><th>
    <c:if test="${sessionScope.sessionLogin.companyCode=='TW'}">
		 <jecs:label key="miMember.townAddr" />
    </c:if>
	    </th>
	    <td >
    <c:if test="${sessionScope.sessionLogin.companyCode=='TW'}">
	    	${jmiMember.townAddr}
    </c:if>
	    </td></tr>
	    
	    <c:if test="${sessionScope.sessionLogin.companyCode=='TW'}">
	     <tr><th>
		   <jecs:label key="miMember.commProvince"  />
	    </th>
	    <td >
			<c:forEach items="${alStateProvinces }" var="al">
	    	<c:if test="${al.stateProvinceId==jmiMember.commProvince }">
	    		<c:out value="${al.stateProvinceName}" />
	    	</c:if>
	    	</c:forEach>
	    	 <input type="hidden" value="${jmiMember.commProvince}" id="commProvince" name="commProvince">
	    </td><th>
		 <jecs:label key="miMember.commCity" />
	    </th>
	    <td >
		       	<select name="commCity" id="commCity" onchange="getCommDistrict();" disabled="true">
					<option value=""><jecs:locale key="list.please.select"/></option>
				</select>
	    	
	    </td></tr>
	    
	    
	     <tr><th>
		   <jecs:label key="miMember.commDistrict"  />
	    </th>
	    <td >
			<select name="commDistrict" id="commDistrict" disabled="true">
					<option value=""><jecs:locale key="list.please.select"/></option>
				</select>
	    </td><th>
		 
	    </th>
	    <td >
	    </td></tr>
	    
	     <tr><th>
		   <jecs:label key="miMember.commAddr"  />
	    </th>
	    <td >
			${jmiMember.commAddr}
	    </td><th>
		 <jecs:label key="miMember.commPostalcode" />
	    </th>
	    <td >
	    	${jmiMember.commPostalcode}
	    </td></tr>
	    
    </c:if>
	    
	    
	     <tr><th>
		   <jecs:label key="miMember.companyName"  />
	    </th>
	    <td >
			${jmiMember.companyName}
	    </td><th>
		    <c:if test="${sessionScope.sessionLogin.companyCode=='TW' }">
			  	<jecs:label key="miMember.personCharge" />
		    </c:if>
		    <c:if test="${sessionScope.sessionLogin.companyCode=='JP' }">
			  	<jecs:label key="miMember.personCharge.comName" />
		    </c:if>
	    </th>
	    <td >
	    	${jmiMember.personCharge}
	    </td></tr>
	    
	     <tr><th>
		    <c:if test="${sessionScope.sessionLogin.companyCode=='TW' }">
			   <jecs:label key="miMember.companyAddr" />
		    </c:if>
		    <c:if test="${sessionScope.sessionLogin.companyCode=='JP' }">
			   <jecs:label key="miMember.companyAddr.depart" />
		    </c:if>
	    </th>
	    <td >
			${jmiMember.companyAddr}
	    </td><th>
		    <c:if test="${sessionScope.sessionLogin.companyCode=='TW' }">
			  	<jecs:label key="miMember.uniteNumber" />
		    </c:if>
		    <c:if test="${sessionScope.sessionLogin.companyCode=='JP' }">
			  	<jecs:label key="miMember.uniteNumber.position"  />
		    </c:if>
	    </th>
	    <td >
	    	${jmiMember.uniteNumber}
	    </td></tr>
    </c:if>
    --%>
    

    <tr class="edit_tr">
	    <th>
	        <span class="text-font-style-tc"><jecs:label  key="miMember.memberStar"/></span>
	    </th>
	    <td>
	        <span class="textbox"><jecs:code listCode="pass.star" value="${jmiMember.memberStar}"/></span>
	    </td>
	    <th>
	        <span class="text-font-style-tc"><jecs:label  key="miMember.exitDate"/></span>
	    </th>
	    <td >
	        <!--form:errors path="exitDate" cssClass="fieldError"/-->
	        <span class="textbox">${jmiMember.exitDate }</span>
	    </td>
	</tr>

    <tr class="edit_tr">
	    <th>
	        <span class="text-font-style-tc"><jecs:label  key="miMember.email"/></span>
	    </th>
	    <td >
	        <!--form:errors path="email" cssClass="fieldError"/-->
	       <span class="textbox">${jmiMember.email}</span>
	    </td>
	    <th>
	        <span class="text-font-style-tc"><jecs:label  key="miMember.checkNo"/></span>
	    </th>
	    <td >
	        <!--form:errors path="checkNo" cssClass="fieldError"/-->
	        <span class="textbox">${jmiMember.checkNo }</span>
	    </td>
	</tr>


    <tr class="edit_tr">
	    <th>
	        <span class="text-font-style-tc"><jecs:label  key="miMember.openBank"  /></span>
	    </th>
	    <td >
	        <!--form:errors path="bank" cssClass="fieldError"/-->
	
	       <span class="textbox">${jmiMember.bank}</span>
	    </td>
	    <th>
	        <span class="text-font-style-tc"><jecs:label  key="logType.C"/></span>
	    </th>
	    <td >
	        <!--form:errors path="checkDate" cssClass="fieldError"/-->
	        <span class="textbox">${jmiMember.checkDate }</span>
	        
	    </td>
   	</tr>

    <tr class="edit_tr">
	    <th>
	        <span class="text-font-style-tc"><jecs:label  key="miMember.bcity"  /></span>
	    </th>
	    <td >
	        <!--form:errors path="bankaddress" cssClass="fieldError"/-->
	       <span class="textbox">${jmiMember.bankaddress}</span>
	    </td>
	    <th>
	        <span class="text-font-style-tc"><jecs:label  key="miMember.createNo"/></span>
	    </th>
	    <td >
	        <!--form:errors path="createNo" cssClass="fieldError"/-->
	        <span class="textbox">${jmiMember.createNo }</span>
	    </td>
	</tr>

    <tr class="edit_tr">
	    <th>
	        <span class="text-font-style-tc"><jecs:label  key="miMember.bnum"  /></span>
	    </th>
	    <td >
	        <!--form:errors path="bankcard" cssClass="fieldError"/-->
	       <span class="textbox">${jmiMember.bankcard}</span>
	    </td>
	    <th>
	        <span class="text-font-style-tc"><jecs:label  key="miMember.createTime"/></span>
	    </th>
	    <td >
	        <!--form:errors path="createTime" cssClass="fieldError"/-->
	        <span class="textbox">${jmiMember.createTime }</span>
	    </td>
	</tr>

    <tr class="edit_tr">
	    <th>
	        <span class="text-font-style-tc"><jecs:label  key="miMember.bname"  /></span>
	    </th>
	    <td >
	        <!--form:errors path="bankbook" cssClass="fieldError"/-->
	       <span class="textbox">${jmiMember.bankbook}</span>
	    </td>
	    <th>
	      <%--   <span class="text-font-style-tc-tare"><jecs:label  key="miMember.remark"/></span> --%>
	    </th>
	    <td >
	        <!--form:errors path="remark" cssClass="fieldError"/-->
	       <%--  <span class="text-font-style-tc-right">
	        <textarea class="textarea_border" >${jmiMember.remark }</textarea>
	        
	        </span> --%>
	    </td>
	</tr>
    
    <tr class="edit_tr">
	    <th>
	        <span class="text-font-style-tc"><jecs:label  key="is.active"  /></span>
	    </th>
	    <td >
	    	<span class="textbox">
	        <!--form:errors path="bankbook" cssClass="fieldError"/-->
	      		<c:if test="${jmiMember.active=='1'}">
	        		<jecs:locale  key="yesno.yes"/>
	    		</c:if>
			    <c:if test="${jmiMember.active=='0'}">
			        <jecs:locale  key="yesno.no"/>
			    </c:if>
	    	</span>
	    </td>
	    <th>
	        <span class="text-font-style-tc"><jecs:label  key="menu.logout"/></span>
	    </th>
	    <td >
	    	<span class="textbox">
	        <!--form:errors path="remark" cssClass="fieldError"/-->
	        	<c:if test="${empty jmiMember.exitDate}">
	        		<jecs:locale  key="yesno.no"/>
	    		</c:if>
			    <c:if test="${not empty jmiMember.exitDate}">
			        <jecs:locale  key="yesno.yes"/>
			    </c:if>
			</span>
	    </td>
	</tr>
    
	<tr class="edit_tr">
		<th>
       		<span class="text-font-style-tc"><jecs:label  key="miMember.gradeType"/> </span>
       	</th>
    	<td >
    		<span class="textbox"><jecs:code listCode="grade.type" value="${jmiMember.gradeType}"/></span>
    	</td>
	    <th>
	        <span class="text-font-style-tc"><jecs:label  key="miMember.store.type"  /></span>
	    </th>
	    <td >
        	<span class="textbox"><jecs:code listCode="isstore" value="${jmiMember.isstore}"/></span>
	    </td>
	</tr>


    <tr class="edit_tr">
	    <th>
	        <span class="text-font-style-tc"><jecs:label  key="member.memberType"  /></span>
	    </th>
	    <td >
	        <span class="textbox"><jecs:code listCode="membertype" value="${jmiMember.memberType}"/></span>
	    </td>
	    <th>
	    	<span class="text-font-style-tc"><jecs:label  key="jmiMember.oriCard"  /></span>
	    </th>
	    <td >
	    	<span class="textbox">
	    	<c:if test="${jmiMember.memberType=='2' }">
	        <jecs:code listCode="bd.cardtype" value="${jmiMember.oriCard}"/>
	        <c:if test="${jmiMember.oriCard=='0' && jmiMember.oriPv==35 }">
	        	330
	        </c:if>
	        <c:if test="${jmiMember.oriCard=='1' && jmiMember.oriPv==35 }">
	        	990
	        </c:if>
	        <c:if test="${jmiMember.oriCard=='1' && jmiMember.oriPv==105 }">
	        	1650
	        </c:if>
	        <c:if test="${jmiMember.oriCard=='2' && jmiMember.oriPv==0 }">
	        	3300
	        </c:if>
	        <c:if test="${jmiMember.oriCard=='3' && jmiMember.oriPv==0 }">
	        	6600
	        </c:if>
	    	</c:if>
	    	
	    	<c:if test="${jmiMember.memberType=='3' || jmiMember.memberType=='6' }">
	    		<jecs:code listCode="bd.cardtype" value="${jmiMember.oriCard}"/>
	    	</c:if>
	    	<c:if test="${jmiMember.memberType=='4' }">
	    		${jmiMember.oriCard }
	    	</c:if>
	    	</span>
	    </td>
	</tr>
	
	
    <c:if test="${sessionScope.sessionLogin.companyCode=='CN'}">
	<tr class="edit_tr">
	    <th>
        	<span class="text-font-style-tc"><jecs:label  key="jmiMember.startWeek"  /></span>
    	</th>
    	<td >	
        	<span class="textbox"><jecs:monthFormat monthType="w" month="${jmiMember.startWeek }" /></span>
    	</td>
    	<th>
        	<span class="text-font-style-tc"><jecs:label  key="jmiMember.validWeek"  /></span>
    	</th>
    	<td >	
        	<span class="textbox"><jecs:monthFormat monthType="w" month="${jmiMember.validWeek}" /></span>
    	</td>
   	</tr>
    
	<tr class="edit_tr">
		<th>
	        <span class="text-font-style-tc"><jecs:label  key="miMember.freezestatus"  /></span>
	    </th>
	    <td >	
	       <span class="textbox"><jecs:code listCode="mimember.freezestatus" value="${jmiMember.freezeStatus }"/></span>
	    </td>
	    <th>
	        <span class="text-font-style-tc"><jecs:label  key="miMember.customerLevel"  /></span>
	    </th>
	    <td >	
	       <span class="textbox"><jecs:code listCode="bd.customerlevel" value="${jmiMember.customerLevel}"/></span>
	    </td>
	</tr>
    
    </c:if>
    
    <c:if test="${sessionScope.sessionLogin.companyCode=='TW'}">
	<tr class="edit_tr">
		<th>
	       <span class="text-font-style-tc"> <jecs:label  key="miMember.shop.type"  /></span>
	    </th>
	    <td >	
	        <span class="textbox"><jecs:code listCode="shop.type" value="${jmiMember.shopType}"/></span>
	    </td>
	    <th></th>
	    <td ></td>
	</tr>
    
    </c:if>
    
    
  	<tr class="edit_tr">
  	    <th>
	       <span class="text-font-style-tc-tare"><jecs:label  key="miMember.titleRemark"  /></span>
	    </th>
	    <td>	
	     <span class="text-font-style-tc-right">
	        <textarea class="textarea_border" disabled="disabled">${jmiMember.titleRemark}</textarea>
	        
	        </span>
	    </td>
	    <th></th>
	    <td></td>
	</tr>
	
	
	 <tr class="edit_tr">
	    <th>
	         <span class="text-font-style-tc-tare"><jecs:label  key="miMember.remark"/></span>
	    </th>
	    <td >
	       <span class="text-font-style-tc-right">
	        <textarea class="textarea_border" disabled="disabled">${jmiMember.remark }</textarea>
	        
	        </span>
	    </td>
	    <th>
	       
	    </th>
	    <td >
	        <!--form:errors path="remark" cssClass="fieldError"/-->
	        
	    </td>
	</tr>
	
	<tr class="edit_tr" align="center">
		<td colspan="4">
			<button type="button" class="btn btn_sele" name="cancel" onclick="window.history.back()">
				<jecs:locale key="operation.button.return"/>
			</button>
		</td>
	</tr>
	</tbody>
</table>

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
		   
</script>