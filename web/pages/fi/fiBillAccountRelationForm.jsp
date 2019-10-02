<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="fiBillAccountRelationDetail.title"/></title>
<content tag="heading"><jecs:locale key="fiBillAccountRelationDetail.heading"/></content>

<script type="text/javascript" src="<c:url value='/dwr/util.js'/>" ></script> 
<script type="text/javascript" src="<c:url value='/dwr/engine.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/dwr/interface/alCityManager.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/dwr/interface/alDistrictManager.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/dwr/interface/jalTownManager.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/dwr/interface/jalPostalcodeManager.js'/>" ></script>

<spring:bind path="fiBillAccountRelation.*">
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

<form:form commandName="fiBillAccountRelation" method="post" action="editFiBillAccountRelation.html" onsubmit="return validateFiBillAccountRelation(this)" id="fiBillAccountRelationForm">

<input type="hidden" name="strAction" value="${param.strAction }"/>
<input type="hidden" name="billAccountCode" value="${fiBillAccountRelation.fiBillAccount.billAccountCode}"/>
<table class='detail' width="100%">

<form:hidden path="relationId"/>

	<tr><th>
        <jecs:label  key="fiBillAccount.billAccountCode"/>
    </th>
    <td align="left">
        	${fiBillAccountRelation.fiBillAccount.billAccountCode}
    </td></tr>
    
    <tr><th>
        <jecs:label  key="fiBillAccount.accountName"/>
    </th>
    <td align="left">
        ${fiBillAccountRelation.fiBillAccount.accountName}
    </td></tr>
    
    <tr><th>
        <jecs:label  key="fiBillAccount.fiBillAccountRelations"/>
    </th>
    <td align="left">
        <jecs:locale  key="fiBillAccountRelation.province"/>:
        <form:select path="province"  cssClass="text medium" onchange="getCommCity();">
			<form:option label="" value=""/>
            <form:options items="${alStateProvinces}" itemValue="stateProvinceId" itemLabel="stateProvinceName"/>
		</form:select>
		 
		<jecs:locale  key="fiBillAccountRelation.city"/> 
        <select name="city" id="city" onchange="getCommDistrict();">
			<option value=""><jecs:locale key="list.please.select"/></option>
		</select>
        
        <jecs:locale  key="fiBillAccountRelation.district"/>
        <select name="district" id="district" onchange="getCommPostalcode(this);">
			<option value=""><jecs:locale key="list.please.select"/></option>
		</select>
        
    </td></tr>
    
    <tr><th>
        <jecs:label key="sysOperationLog.moduleName"/>
    </th>
    <td align="left">
    
    	<jecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.add"/>" />
		</jecs:power>
		
       <input type="button" class="button" name="cancel" onclick="history.back();" value="<fmt:message key="operation.button.cancel"/>" />
    </td></tr>

</table>
</form:form>
<script type="text/javascript">
					   
		   function getCommCity(){
			
		   var commProvince=document.getElementById('province').value;
		   	alCityManager.getAlCityByProvinceId(commProvince,callCommCity);
		   
		   }
		   function callCommCity(valid){
			   var cityElemment=$('city');
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
		   	var city=document.getElementById('city').value;
		   		alDistrictManager.getAlDistrictByCityId(city,callBackCommDistrict);
		   }
		   function callBackCommDistrict(valid){
			   var districtElemment=document.getElementById('district');
    		
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
</script>

