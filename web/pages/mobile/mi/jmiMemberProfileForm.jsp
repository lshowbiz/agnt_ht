<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE HTML>
<html>
	<head>
	    <meta name="viewport" content="width=device-width, initial-scale=1"> 
		<meta name="decorator" content="mobile"/>
	</head>
<style>
#tableId td{
	text-align: left;
}

</style>
<script type="text/javascript" src="<c:url value='/dwr/util.js'/>" ></script> 
<script type="text/javascript" src="<c:url value='/dwr/engine.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/dwr/interface/alCityManager.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/dwr/interface/alDistrictManager.js'/>" ></script>
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


<form:form commandName="jmiMember" method="post" action="editJmiMemberProfile.html" id="jmiMemberForm" onsubmit="if(isFormPosted()){return true;}{return false;}">

<input type="hidden" name="strAction" value="${param.strAction }"/>




<table id="tableId"   width="100%">


     <tr><th >
	  <jecs:label  key="miMember.memberNo"/>
    </th>
    <td>
	 	${ jmiMember.userCode} - 
		<jecs:code listCode="bd.cardtype" value="${jmiMember.cardType}"/>
    </td></tr>


     <tr><th>
	 <jecs:label key="miMember.recommendNo" required="true" />
    </th>
    <td >
		${jmiMember.recommendNo }
    </td></tr>


     <tr><th>
	   <jecs:label key="miMember.petName" required="true" />
    </th>
    <td >
			<form:input path="petName" id="petName" />
    </td></tr>


     <tr><th>
	 <jecs:label  key="miMember.sex"/>
    </th>
    <td >
    <jecs:list name="sex" listCode="sex" value="${jmiMember.sex}" defaultValue=""/>	
    </td></tr>
    

     <tr><th>
	    <jecs:label  key="miMember.papertype"/>
    </th>
    <td >
	   <jecs:code listCode="papertype" value="${jmiMember.papertype }"/>
    </td></tr>
    

     <tr>
     
     <th>
	 <jecs:label key="miMember.papernumber" required="true" />
    </th>
    <td>
	<c:if test="${empty papernumberModify }">
	 <form:input path="papernumber" id="papernumber" />
	</c:if>
	<c:if test="${not empty papernumberModify }">
		${ jmiMember.papernumber}
	</c:if>
    
    </td>
    </tr>
    

     <tr><th>
	     <jecs:label key="miMember.province" required="true" />
    </th>
    <td >
	 <form:select path="province"   onchange="getIdCity();">
					<form:option label="" value=""/>
		            <form:options items="${alStateProvinces}" itemValue="stateProvinceId" itemLabel="stateProvinceName"/>
		</form:select>
    </td></tr>


     <tr><th>
	 <jecs:label key="miMember.idAddr2" required="true" />
    </th>
    <td >
<div class="ui-select"><div data-theme="c" class="ui-btn ui-btn-icon-right ui-btn-corner-all ui-shadow ui-btn-up-c">
<span class="ui-btn-inner ui-btn-corner-all" aria-hidden="true"><span class="ui-btn-text" id="city_name_view">
<jecs:locale key="list.please.select"/></span><span class="ui-icon ui-icon-arrow-d ui-icon-shadow"></span></span>
<select name="city" id="city" onchange="getIdDistrict();"   data-role="none" >
			<option value=""><jecs:locale key="list.please.select"/></option>
		</select>
			</div></div>
    </td></tr>
    

     <tr><th>
	   <jecs:label key="miMember.district" />
    </th>
    <td >
		 
    
<div class="ui-select"><div data-theme="c" class="ui-btn ui-btn-icon-right ui-btn-corner-all ui-shadow ui-btn-up-c">
<span class="ui-btn-inner ui-btn-corner-all" aria-hidden="true"><span class="ui-btn-text" id="district_name_view">
<jecs:locale key="list.please.select"/></span><span class="ui-icon ui-icon-arrow-d ui-icon-shadow"></span></span>

		<select name="district" id="district" data-role="none" onchange="getDistrictOnName();">
				<option value=""><jecs:locale key="list.please.select"/></option>
			</select>
			</div></div>
    </td></tr>


     <tr><th>
	   <jecs:label key="miMember.idAddr" required="true" />
    </th>
    <td >
	  <form:input path="address" id="address" />
    </td></tr>
    

     <tr><th>
	    <jecs:label key="miMember.postalcode" />
    </th>
    <td >
	 <form:input path="postalcode" id="postalcode" />
    </td></tr>


     <tr><th>
	 <jecs:label key="miMember.phone" />
    </th>
    <td >
	 <form:input path="phone" id="phone" />
    </td></tr>


     <tr><th>
	     <jecs:label  key="miMember.faxtele"/>
    </th>
    <td >
	 <form:input path="faxtele" id="faxtele" />
    </td></tr>


     <tr><th>
	<jecs:label key="miMember.mobiletele" required="true" />
    </th>
    <td >
	  <form:input path="mobiletele" id="mobiletele" />
    </td></tr>

     <tr><th>
	    <jecs:label  key="miMember.email"/>
    </th>
    <td >
	 <form:input path="email" id="email" />
    </td></tr>

	
     <tr><th>
	  <jecs:label key="miMember.spouseName" />
	    
    </th>
    <td >
	 ${ jmiMember.spouseName}
    </td></tr>


     <tr><th>
	<jecs:label key="miMember.spouseIdno" />
    </th>
    <td >
	 ${ jmiMember.spouseIdno}
    </td></tr>
    
     <tr><th>
	  <jecs:label  key="member.ugrade.time"/>
    </th>
    <td >
	 ${days }
    </td></tr>


     <tr>
	    <th>
	    <c:if test="${ jmiMember.cardType!='0'}" >
			 <jecs:label  key="jmiMember.validWeek"/>
			</c:if>
	    </th>
	    <td >
	    
	    <c:if test="${ jmiMember.cardType!='0'}" >
	 		${fn:substring(jmiMember.validWeek, 0, 4)}
	 		<jecs:locale  key="bdPeriod.wyear"/><jecs:locale  key="jmiMember.di"/>
	 		${fn:substring(jmiMember.validWeek, 4, 6)}
	 		<jecs:locale  key="bdPeriod.wmonth"/>
	 	</c:if>
		
	    </td>
    </tr>
    
    
        <c:if test="${sessionScope.sessionLogin.companyCode=='CN'}">
	     <tr><th>
		    <jecs:label key="miMember.customerLevel"/>
	    </th>
	    <td >
		 	 <jecs:code listCode="bd.customerlevel" value="${jmiMember.customerLevel}"/>
	    </td></tr>

    
    </c:if>
       
       
     <tr>
   		 <td colspan="2" >
	<fieldset class="ui-grid-a">
			<div class="ui-block-a"><input  type="submit" name="save"  value="<jecs:locale key="operation.button.save"/>"  /></div>
			<div class="ui-block-b"><input type="button"  name="cancel"  onclick="javascript:location='<c:url value='/index.html'/>'" value="<jecs:locale key="operation.button.return"/>" /> </div>	
</fieldset>
   		 </td>
    </tr>
       
       
</table>



<c:if test="${ empty jmiMember.bank ||  empty jmiMember.bankbook ||   empty jmiMember.bankcard || empty jmiMember.bankaddress ||  not empty bankViewUnLimit }">
       

<input id="oldBank" name="oldBank" value="${oldBank }" type="hidden">
<input id="oldBankaddress" name="oldBankaddress" value="${oldBankaddress }" type="hidden">
<input id="oldBankbook" name="oldBankbook" value="${oldBankbook }" type="hidden">
<input id="oldBankcard" name="oldBankcard" value="${oldBankcard }" type="hidden">
<table class='detail' width="100%">

     <tr><th >
	    <jecs:label key="miMember.bankProvince" required="true" />
    </th>
    <td  nowrap="nowrap" width="250">
        <form:select path="bankProvince"   onchange="getBankCity()" disabled="${bankProvinceModify }">
					<form:option label="" value=""/>
		            <form:options items="${alStateProvinces}" itemValue="stateProvinceId" itemLabel="stateProvinceName"/>
		</form:select>
    </td><th>
	 <jecs:label  key="miMember.bankCity"  required="true" />
    </th>
    <td >
	
        <select name="bankCity" id="bankCity" 	<c:if test="${not empty bankCityModify }"> disabled="true" </c:if> >
			<option value=""><jecs:locale key="list.please.select"/></option>
		</select>
		
    </td></tr>

     <tr><th>
	    <jecs:label key="miMember.openBank" required="true" />
    </th>
    <td >
    
	<c:if test="${empty bankModify }">
					<form:select path="bank"  >
						<form:option value=""></form:option>
			            <form:options items="${sysBankList}" itemValue="bankKey" itemLabel="bankValue"/>
			        </form:select>
	</c:if>
	<c:if test="${not empty bankModify }">
		<c:forEach items="${sysBankList }" var="curSysBankList">
			<c:if test="${curSysBankList.bankKey==jmiMember.bank }">
				${curSysBankList.bankValue }
			</c:if>
		</c:forEach>
	</c:if>
	
    </td><th>
	<jecs:label key="miMember.bcity" required="true" />
    </th>
    <td >
	<c:if test="${empty bankaddressModify }">
	 <form:input path="bankaddress" id="bankaddress"  />
	</c:if>
	<c:if test="${not empty bankaddressModify }">
		${ jmiMember.bankaddress}
	</c:if>
    </td></tr>

     <tr><th>
	    <jecs:label key="miMember.bname" required="true" />
    </th>
    <td >
	 	${jmiMember.bankbook }
    </td><th>
	 <jecs:label key="miMember.bnum" required="true" />
    </th>
    <td >
	<c:if test="${empty bankcardModify }">
	 <form:input path="bankcard" id="bankcard"  />
	</c:if>
	<c:if test="${not empty bankcardModify }">
		${ jmiMember.bankcard}
	</c:if>
    </td></tr>
    


    
</table>

</c:if>
































</form:form>

<script type="text/javascript">
    //Form.focusFirstElement($('miMemberForm'));
    getIdCity();
			
			
			
			function getIdCity(){
			
		   var province=document.getElementById('province').value;
		   	alCityManager.getAlCityByProvinceId(province,callIdCity);
		   
		   }
		   function callIdCity(valid){
			   var cityElemment=document.getElementById('city');
        		cityElemment.options.length=0;
          		var o=new Option("<jecs:locale key="list.please.select"/>","");
           		cityElemment.options.add(o);  

           		
			   var districtElemment=document.getElementById('district');
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

		   function getDistrictOnName(){
			   var districtt=document.getElementById('district').value;
			   $("#district option").each(function(i){
				   var key=$(this).val();
					var val= $(this).text();
					if(districtt==key){
						$('#district_name_view').html(val);
					}
				})
		   
		   }


		   function getIdDistrict(){
		   
		   	   	var city=document.getElementById('city').value;
			   $("#city option").each(function(i){
				   var key=$(this).val();
					var val= $(this).text();
					if(city==key){
						$('#city_name_view').html(val);
					}
				})
		   
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
getDistrictOnName();

		   }
		   
		   
		    function getBankCity(){
		  	 var bankProvince=document.getElementById('bankProvince').value;
		   	 alCityManager.getAlCityByProvinceId(bankProvince,callBackBankCity);
		   
		   }
		   function callBackBankCity(valid){
			   var cityElemment=document.getElementById('bankCity');
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
		   
		   
<c:if test="${ empty jmiMember.bank ||  empty jmiMember.bankbook ||   empty jmiMember.bankcard }">
		   getBankCity();
		   
	
</c:if>	   
	
			document.getElementById('jmiMemberForm').setAttribute('data-ajax',false); 


</script>

