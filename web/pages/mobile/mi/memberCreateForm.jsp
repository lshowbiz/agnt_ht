<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML>
<html>
	<head>
	    <meta name="viewport" content="width=device-width, initial-scale=1"> 
		<meta name="decorator" content="mobile"/>
	</head>

<link rel="stylesheet" href="styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="scripts/calendar/lang.jsp"> </script> 

<script type="text/javascript" src="scripts/global.js"></script>
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

<form:form commandName="jmiMember" method="post" action="memberCreate.html"  id="jmiMemberForm" onsubmit="if(isFormPosted()){return true;}{return false;}" >

<input type="hidden" name="strAction" value="${param.strAction }"/>


<form:hidden path="userCode"/>


<table width="100%">




    <tr>
	    <th>
		   	 <jecs:label key="miMember.recommendNo" required="true" />
	    </th>
	    <td >
	   		  <form:input path="jmiRecommendRef.recommendJmiMember.userCode" id="recommendNo" />
	    </td>
	 </tr>
	 
    <tr>
	    <th>
			 <jecs:label key="miMember.linkNo" required="true" />
	    </th>
	    <td >
			 <form:input path="jmiLinkRef.linkJmiMember.userCode" id="linkNo" />
	    </td>
    </tr>
    
    
    
    
	     <tr><th>
		   <jecs:label key="miMember.firstName" required="true" />
	    </th>
	    <td >
		 <form:input path="firstName" id="firstName" />
	    </td>
	    </tr>
	 
    <tr>
	    <th>
		 <jecs:label key="miMember.lastName" required="true" />
	    </th>
	    <td >
		<form:input path="lastName" id="lastName" />
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
	  <jecs:list name="papertype" id="papertype" listCode="papertype" value="${jmiMember.papertype}" defaultValue="1"/>
    </td>
    </tr>
    
    <tr><th>
	 <jecs:label key="miMember.papernumber" required="true" />
    </th>
    <td >
	 <form:input path="papernumber" id="papernumber"  />
    </td></tr>
    
    

     <tr><th>
     
	     <jecs:label key="miMember.province" required="true" />
    </th>
    <td >
	 <form:select path="province"   onchange="getIdCity();">
					<form:option label="" value=""><jecs:locale key="list.please.select"/></form:option>
		            <form:options items="${alStateProvinces}" itemValue="stateProvinceId" itemLabel="stateProvinceName"/>
		</form:select>
    </td>
    </tr>
    

     <tr>
    
    <th>
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
    

     <tr><th >
		     <jecs:label key="miMember.district" required="true" />
    </th>
    <td >
    
    
<div class="ui-select"><div data-theme="c" class="ui-btn ui-btn-icon-right ui-btn-corner-all ui-shadow ui-btn-up-c">
<span class="ui-btn-inner ui-btn-corner-all" aria-hidden="true"><span class="ui-btn-text" id="district_name_view">
<jecs:locale key="list.please.select"/></span><span class="ui-icon ui-icon-arrow-d ui-icon-shadow"></span></span>

		<select name="district" id="district" data-role="none" onchange="getDistrictOnName();">
				<option value=""><jecs:locale key="list.please.select"/></option>
			</select>
			</div></div>
    
    
    
    
    </td>
    
    </tr>
    
    
    
     <tr>
    <th>
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
    </td>
    </tr>
    

     <tr>
    <th>
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
	<jecs:label key="miMember.mobiletele"/>
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
	     <jecs:label key="miMember.pwd1" required="true" />
    </th>
    <td  nowrap="nowrap" width="250">
	 <form:password path="sysUser.password"  id="password1"/>
    </td>
    </tr>
    

     <tr><th>
	 <jecs:label key="label.affirmNewPassword" required="true" />
    </th>
    <td >
	 <input id="password1Confirm" name="password1Confirm" type="password" class="text medium">
    </td></tr>



     <tr><th>
	    <jecs:label key="miMember.pwd2" required="true" />
    </th>
    <td >
	 <form:password path="sysUser.password2"  id="password2"/>
    </td></tr>
    

     <tr><th>
	 <jecs:label key="label.affirmAdvancedPassword" required="true" />
    </th>
    <td >
	 <input id="password2Confirm" name="password2Confirm" type="password" class="text medium">
    </td></tr>


     <tr>
   		 <td colspan="2" >
   		 
	<fieldset class="ui-grid-a">
			<div class="ui-block-a"><input  type="submit" name="save"  value="<jecs:locale key="operation.button.save"/>"  /></div>
			<div class="ui-block-b"><input type="button"  name="cancel"  onclick="window.history.back()" value="<jecs:locale key="operation.button.return"/>" /> </div>	
</fieldset>
			
			
   		 </td>
    </tr>


	
    
</table>
<script>
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
		   
		   
		   function getCity(){
		   var province=document.getElementById('shippingProvince').value;

		   	alCityManager.getAlCityByProvinceId(province,callBackCity);
		   
		   }
		   function callBackCity(valid){
			   var cityElemment=document.getElementById('shippingCity');
        		cityElemment.options.length=0;
          		var o=new Option("<jecs:locale key="list.please.select"/>","");
           		cityElemment.options.add(o);  
           		
           		
			   var districtElemment=document.getElementById('shippingDistrict');
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
getDistrictOnName();
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



 //   Form.focusFirstElement($('jmiMemberForm'));
    

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
	
			getIdCity();
			document.getElementById('jmiMemberForm').setAttribute('data-ajax',false); 
</script>

</html>