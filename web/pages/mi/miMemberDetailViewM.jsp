<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jmiMemberDetail.title"/></title>
<content tag="heading"><jecs:locale key="jmiMemberDetail.heading"/></content>

<script type="text/javascript" src="<c:url value='/dwr/util.js'/>" ></script> 
<script type="text/javascript" src="<c:url value='/dwr/engine.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/dwr/interface/alCityManager.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/dwr/interface/alDistrictManager.js'/>" ></script>

	<div class="searchBar">
		<input type="button" class="button" name="cancel" onclick="window.history.back()" value="<jecs:locale key="operation.button.return"/>" />
	
	</div>

<table class='detail' width="100%">


    <tr><th>
        <jecs:label  key="miMember.memberNo"/>
    </th>
    <td align="left">
        ${jmiMember.userCode }
    </td><th>
        <jecs:label  key="miMember.papertype"/>
    </th>
    <td align="left">
        <!--form:errors path="papertype" cssClass="fieldError"/-->
        <jecs:code listCode="papertype" value="${jmiMember.papertype}"/>
    </td></tr>
    

    <tr><th>
        <jecs:label  key="miMember.recommendNo"/>
    </th>
    <td align="left">
        ${jmiMember.recommendNo }
    </td><th>
        <jecs:label  key="miMember.papernumber"  />
    </th>
    <td align="left">
        <!--form:errors path="papernumber" cssClass="fieldError"/-->
       ${jmiMember.papernumber}
    </td></tr>
    
    <tr><th>
        <jecs:label  key="miMember.linkNo"/>
    </th>
    <td align="left">
        ${jmiMember.linkNo }
    </td><th>
        <jecs:label  key="miMember.phone"  />
    </th>
    <td align="left">
        <!--form:errors path="phone" cssClass="fieldError"/-->
       ${jmiMember.phone}
    </td></tr>


    <tr><th>
        <jecs:label  key="miMember.cardType"/>
    </th>
    <td align="left">
        <jecs:code listCode="bd.cardtype" value="${jmiMember.cardType}"/>
    </td><th>
        <jecs:label  key="miMember.faxtele"/>
    </th>
    <td align="left">
        <!--form:errors path="faxtele" cssClass="fieldError"/-->
       ${jmiMember.faxtele}
    </td></tr>

    <tr><th>
        <jecs:label  key="miMember.firstName"  />
    </th>
    <td align="left">
        <!--form:errors path="firstName" cssClass="fieldError"/-->
       ${jmiMember.firstName}
    </td><th>
        <jecs:label  key="miMember.mobiletele"  />
    </th>
    <td align="left">
        <!--form:errors path="mobiletele" cssClass="fieldError"/-->
       ${jmiMember.mobiletele}
    </td></tr>

    <tr><th>
        <jecs:label  key="miMember.lastName"  />
    </th>
    <td align="left">
        <!--form:errors path="lastName" cssClass="fieldError"/-->
       ${jmiMember.lastName}
    </td><th>
        <jecs:label  key="miMember.province"  />
    </th>
    <td align="left">
        <!--form:errors path="officetele" cssClass="fieldError"/-->

		<c:forEach items="${alStateProvinces }" var="al">
    	<c:if test="${al.stateProvinceId==jmiMember.province }">
    		<c:out value="${al.stateProvinceName}" />
    	</c:if>
    	</c:forEach>
    	 <input type="hidden" value="${jmiMember.province}" id="province" name="province">
    </td></tr>


    <tr><th>
        <jecs:label key="miMember.spouseName" />
    </th>
    <td align="left">
        <!--form:errors path="postalcode" cssClass="fieldError"/-->
        ${jmiMember.spouseName }
    </td><th>
          <jecs:label  key="busi.city"  />
    </th>
    <td align="left">
        <!--form:errors path="province" cssClass="fieldError"/-->
		         
		       	<select name="city" id="city" onchange="getIdDistrict();" disabled="true">
					<option value=""><jecs:locale key="list.please.select"/></option>
				</select>
    </td></tr>
    
    <tr><th>
        <jecs:label key="miMember.spouseIdno" />
    </th>
    <td align="left">
        <!--form:errors path="postalcode" cssClass="fieldError"/-->
        ${jmiMember.spouseIdno }
    </td><th>
       <jecs:label  key="miMember.district"  />
    </th>
    <td align="left">
    	
	 <select name="district" id="district"  disabled="true">
			<option value=""><jecs:locale key="list.please.select"/></option>
		</select>
      
    </td></tr>


    <tr><th>
        <jecs:label  key="miMember.petName"  />
    </th>
    <td align="left">
        <!--form:errors path="petName" cssClass="fieldError"/-->
       ${jmiMember.petName}
    </td><th>
        <jecs:label  key="busi.address"  />
    </th>
    <td align="left">
        <!--form:errors path="address" cssClass="fieldError"/-->
       ${jmiMember.address}
    </td></tr>

    <tr><th>
        <jecs:label  key="miMember.sex"/>
    </th>
    <td align="left">
        <!--form:errors path="sex" cssClass="fieldError"/-->
        <jecs:code listCode="sex" value="${jmiMember.sex}"/>
    </td><th>
        <jecs:label  key="miMember.postalcode"  />
    </th>
    <td align="left">
        <!--form:errors path="postalcode" cssClass="fieldError"/-->
       ${jmiMember.postalcode}
    </td></tr>


    <tr><th>
        <%--<jecs:label  key="miMember.birthday"  />--%>
    </th>
    <td align="left">

    </td><th>
        <jecs:label  key="miMember.exitDate"/>
    </th>
    <td align="left">
        <!--form:errors path="exitDate" cssClass="fieldError"/-->
        ${jmiMember.exitDate }
    </td></tr>

    <tr><th>
        <jecs:label  key="miMember.email"/>
    </th>
    <td align="left">
        <!--form:errors path="email" cssClass="fieldError"/-->
       ${jmiMember.email}
    </td><th>
        <jecs:label  key="miMember.checkNo"/>
    </th>
    <td align="left">
        <!--form:errors path="checkNo" cssClass="fieldError"/-->
        ${jmiMember.checkNo }
    </td></tr>


    <tr><th>
        <jecs:label  key="miMember.openBank"  />
    </th>
    <td align="left">
        <!--form:errors path="bank" cssClass="fieldError"/-->

       ${jmiMember.bank}
    </td><th>
        <jecs:label  key="logType.C"/>
    </th>
    <td align="left">
        <!--form:errors path="checkDate" cssClass="fieldError"/-->
        ${jmiMember.checkDate }
        
    </td></tr>

    <tr><th>
        <jecs:label  key="miMember.bcity"  />
    </th>
    <td align="left">
        <!--form:errors path="bankaddress" cssClass="fieldError"/-->
       ${jmiMember.bankaddress}
    </td><th>
        <jecs:label  key="miMember.createNo"/>
    </th>
    <td align="left">
        <!--form:errors path="createNo" cssClass="fieldError"/-->
        ${jmiMember.createNo }
    </td></tr>

    <tr><th>
        <jecs:label  key="miMember.bnum"  />
    </th>
    <td align="left">
        <!--form:errors path="bankcard" cssClass="fieldError"/-->
       ${jmiMember.bankcard}
    </td><th>
        <jecs:label  key="miMember.createTime"/>
    </th>
    <td align="left">
        <!--form:errors path="createTime" cssClass="fieldError"/-->
        ${jmiMember.createTime }
    </td></tr>

    <tr><th>
        <jecs:label  key="miMember.bname"  />
    </th>
    <td align="left">
        <!--form:errors path="bankbook" cssClass="fieldError"/-->
       ${jmiMember.bankbook}
    </td><th>
        <jecs:label  key="miMember.remark"/>
    </th>
    <td align="left">
        <!--form:errors path="remark" cssClass="fieldError"/-->
        ${jmiMember.remark }
    </td></tr>
    
    <tr><th>
        <jecs:label  key="is.active"  />
    </th>
    <td align="left">
        <!--form:errors path="bankbook" cssClass="fieldError"/-->
      <c:if test="${jmiMember.active=='1'}">
        <jecs:locale  key="yesno.yes"/>
    </c:if>
    <c:if test="${jmiMember.active=='0'}">
        <jecs:locale  key="yesno.no"/>
    </c:if>
    </td><th>
        <jecs:label  key="menu.logout"/>
    </th>
    <td align="left">
        <!--form:errors path="remark" cssClass="fieldError"/-->
          <c:if test="${empty jmiMember.exitDate}">
        <jecs:locale  key="yesno.no"/>
    </c:if>
    <c:if test="${not empty jmiMember.exitDate}">
        <jecs:locale  key="yesno.yes"/>
    </c:if>
    </td></tr>
    
	<tr><th>
        <jecs:label  key="member.ugrade.time"/>
    </th>
    <td align="left">
    	 ${days }
    </td>
    	<c:if test="${jmiMember.isstore=='1' }">
	    <th>
	        <jecs:label  key="aiAgent.deadlineDate"  />
	    </th>
	    <td align="left">
	        <!--form:errors path="deadlineDate" cssClass="fieldError"/-->
        ${jmiMember.deadlineDate }
	    </td>
	</c:if>
	</tr>


    <tr><th>
        <jecs:label  key="member.memberType"  />
    </th>
    <td align="left">
    	<c:if test="${jmiMember.memberType=='2' }">M</c:if>
    </td><th>
    	<jecs:label  key="jmiMember.oriCard"  />
    </th>
    <td align="left">
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
    </td></tr>
	
    
	<%--<th class="command"><jecs:label key="sysOperationLog.moduleName" /></th>
	<td class="command">


	
	</td>--%>
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

		   }
</script>