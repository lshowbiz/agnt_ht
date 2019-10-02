<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jpoShippingFeeDetail.title"/></title>
<content tag="heading"><jecs:locale key="jpoShippingFeeDetail.heading"/></content>

<script type="text/javascript" src="<c:url value='/dwr/util.js'/>" ></script> 
<script type="text/javascript" src="<c:url value='/dwr/engine.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/dwr/interface/alCityManager.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/dwr/interface/alDistrictManager.js'/>" ></script>
<fmt:setBundle basename="ApplicationResources" var="resource_fmt"/> 
<c:set var="buttons">

<c:if test='${empty jpoShippingFee.jpsId}'>
<jecs:power powerCode="addJpoShippingFee">
	<button type="button" class="btn btn_sele" name="save"  onclick="bCancel=false;validateFiled(1);" >
		<jecs:locale key="operation.button.save"/>
	</button>
</jecs:power>
</c:if>
<c:if test='${not empty jpoShippingFee.jpsId}'>
<jecs:power powerCode="editJpoShippingFee">
	<button type="button" class="btn btn_sele" name="save"  onclick="bCancel=false;validateFiled(2);" >
		<jecs:locale key="operation.button.save"/>
	</button>
</jecs:power>
<jecs:power powerCode="deleteJpoShippingFee">
	<button type="submit" class="btn btn_sele" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'JpoShippingFee')" >
		<jecs:locale key="operation.button.delete"/>
	</button>
</jecs:power>
</c:if>

<button type="button" class="btn btn_sele" name="back"  onclick="window.location.href='jpoShippingFees.html?needReload=1'" >
	<jecs:locale key="operation.button.return"/>
</button>

</c:set>

<spring:bind path="jpoShippingFee.*">
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

<script>
function onSubmitCheck(formId){
	if(validateJpoMemberOrder(formId)){
		if(isFormPosted()){
			return true;
		}else{
		return false;
		}
	}else{
		return false;
	}
}
</script>
<form:form commandName="jpoShippingFee" method="post" action="editJpoShippingFee.html" onsubmit="return onSubmitCheck(this);" id="jpoShippingFeeForm">

<input type="hidden" name="strAction" value="${param.strAction }"/>

<table class='detail' width="70%">

<form:hidden path="jpsId"/>

    <tbody class="window" >
  
    <tr class="edit_tr">
			<th><span class="text-font-style-tc">
		        <jecs:label  key="shipping.province" required="true"/>
		    </span></th>
    <td><span class="textbox">
    	<c:choose>
    		<c:when test="${param.strAction eq 'editJpoShippingFee'}">
    			${provicneName }
    		</c:when>
    		<c:otherwise>
    			<form:select cssClass="textbox-text" path="province"   onchange="getCity()" >
					<form:option label="" value=""><jecs:locale key='list.please.select'/></form:option>
		            <form:options items="${alStateProvinces}" itemValue="stateProvinceId" itemLabel="stateProvinceName" />
				</form:select>
    		</c:otherwise>
    	</c:choose>
    	
    </span></td>
    
    <th><span class="text-font-style-tc">
        <jecs:label  key="shipping.city" required="true"/>
    </span></th>
    <td><span class="textbox">
    	<c:choose>
    		<c:when test="${param.strAction eq 'editJpoShippingFee'}">
    			${cityName }
    		</c:when>
    		<c:otherwise>
    			 <!--form:errors path="city" cssClass="fieldError"/-->
		        <select class="textbox-text" name="city" id="city" onchange="getDistrict()">
					<option value=""><jecs:locale key="list.please.select"/></option>
				</select>
    		</c:otherwise>
    	</c:choose>
       
    </span></td></tr>

   <tr class="edit_tr"><th><span class="text-font-style-tc">
        <jecs:label  key="shipping.district" required="true"/>
    </span></th>
    <td><span class="textbox">
    	<c:choose>
    		<c:when test="${param.strAction eq 'editJpoShippingFee'}">
    			${disName }
    		</c:when>
    		<c:otherwise>
    			<select class="textbox-text" name="district" id="district" >
					<option value=""><jecs:locale key="list.please.select"/></option>
				</select>
    		</c:otherwise>
    	</c:choose>
        	
    </span></td>

	<th><span class="text-font-style-tc">
        <jecs:label  key="pomember.feeType" required="true"/>
    </span></th>
    <td><span class="textbox">
    	<c:choose>
    		<c:when test="${param.strAction eq 'editJpoShippingFee'}">
    			<c:set var="isdisb" value="true"/>
    		</c:when>
    		<c:otherwise>
    			<c:set var="isdisb" value="false"/>
    		</c:otherwise>
    	</c:choose>
		<%-- <jecs:list name="shippingType" listCode="feetype" value="${jpoShippingFee.shippingType}" defaultValue="0"/> --%>
		<jecs:list styleClass="textbox-text" name="shippingType" listCode="ship.logisticsstrategy" value="${jpoShippingFee.shippingType}" defaultValue="0" disabled="${isdisb }"/>
    </span></td></tr>

    <tr class="edit_tr"><th><span class="text-font-style-tc">
        <jecs:label  key="pomember.shippingFee" required="true"/>
    </span></th>
    <td><span class="textbox">
        <!--form:errors path="shippingFee" cssClass="fieldError"/-->
        <form:input cssClass="textbox-text" path="shippingFee" id="shippingFee" maxlength="4" />
    </span></td>

	<th><span class="text-font-style-tc">
        <jecs:label  key="pomember.shippingWeight" required="true"/>
    </span></th>
    <td><span class="textbox">
        <!--form:errors path="shippingFee" cssClass="fieldError"/-->
        <form:input cssClass="textbox-text" path="shippingWeight" id="shippingWeight" maxlength="4" />
    </span></td></tr>

    <tr class="edit_tr"><th><span class="text-font-style-tc">
        <jecs:label  key="pomember.shippingRefee" required="true"/>
    </span></th>
    <td><span class="textbox">
        <!--form:errors path="shippingFee" cssClass="fieldError"/-->
        <form:input cssClass="textbox-text" path="shippingRefee" id="shippingRefee" maxlength="5"  />
    </span></td>

	<th><span class="text-font-style-tc">
        <jecs:label  key="pomember.shippingReweight" required="true"/>
    </span></th>
    <td><span class="textbox">
        <!--form:errors path="shippingFee" cssClass="fieldError"/-->
        <form:input cssClass="textbox-text" path="shippingReweight" id="shippingReweight" maxlength="4" />
    </span></td></tr>
    
    <tr>
		<td class="command" align="center" colspan="4" >
			<c:out value="${buttons}" escapeXml="false"/>
		</td>
	</tr>
</tbody>
</table>
</form:form>
<script>
		   function getCity(){
				if(document.getElementById('province')!=null){   
				   var province=document.getElementById('province').value;
				   alCityManager.getAlCityByProvinceId(province,callBackCity);
				}
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
			   }
		   		var fabId=$('jpsId');
		   		if(fabId.value!=''){
		   				var cityElemment=$('city');
				       for (var i=0;i<cityElemment.options.length;i++) {
			
				       	  if (cityElemment.options[i].value == '${jpoShippingFee.city}') {  
				       	  	cityElemment.options[i].selected=true;
				       	  	break;        
				       	  }
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
			   }
		   
		   		var jpsId=$('jpsId');
		   		if(jpsId.value!=''){
		   				var districtElemment=$('district');
				       for (var i=0;i<districtElemment.options.length;i++) {
				       	  if (districtElemment.options[i].value == '${jpoShippingFee.district}') {  
				       	  	districtElemment.options[i].selected=true;
				       	  	break;        
				       	  }
					   }
		   		}
		   }
		   
		   function validateFiled(type){
			   var reg = new RegExp("^[0-9]+(.[0-9]{1,2})?$");  
			   if(type=='1'){//1:add   2:edit
				   var province = document.getElementById('province').value;
				   if(''==province || province==null){  
						alert('<fmt:message key="message.required" bundle="${resource_fmt}"/>');
						document.getElementById('province').focus();
						return ; 
				   	}
				   var city = document.getElementById('city').value;
				   if(''==city || city==null){  
						alert('<fmt:message key="message.required" bundle="${resource_fmt}"/>');
						document.getElementById('city').focus();
						return ; 
				   	}
				   var district = document.getElementById('district').value;
				   if(''==district || district==null){  
						alert('<fmt:message key="message.required" bundle="${resource_fmt}"/>');
						document.getElementById('district').focus();
						return ; 
				   	}
			   }
			   
				var sf = document.getElementById('shippingFee').value;
				if(''==sf || sf==null || !reg.test(sf)){  
					alert('<fmt:message key="message.num" bundle="${resource_fmt}"/>');
					document.getElementById('shippingFee').focus();
					return ; 
			   	}
				
				var sw = document.getElementById('shippingWeight').value;
				if(''==sw || sw==null || !reg.test(sw)){
					alert('<fmt:message key="message.num" bundle="${resource_fmt}"/>'); 
					document.getElementById('shippingWeight').focus();
					return ;
				}
				var srf = document.getElementById('shippingRefee').value;
				if(''==srf || srf==null || !reg.test(srf)){
					alert('<fmt:message key="message.num" bundle="${resource_fmt}"/>'); 
					document.getElementById('shippingRefee').focus();
					return ;
				}
				var srw = document.getElementById('shippingReweight').value;
				if(''==srw || srw==null || !reg.test(srw)){
					alert('<fmt:message key="message.num" bundle="${resource_fmt}"/>');
					document.getElementById('shippingReweight').focus();
					return ;
				}
				document.forms["jpoShippingFeeForm"].submit();
		   }
		</script>



<c:if test="${not empty jpoShippingFee.jpsId }">
	
		<script>
			getCity();
		</script>
		
</c:if>
<script type="text/javascript">
    Form.focusFirstElement($('jpoShippingFeeForm'));
</script>

<v:javascript formName="jpoShippingFee" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
