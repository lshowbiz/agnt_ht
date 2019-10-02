<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jmiStoreDetail.title"/></title>
<content tag="heading"><jecs:locale key="jmiStoreDetail.heading"/></content>



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
<script type="text/javascript" src="<c:url value='/dwr/interface/jbdMemberLinkCalcHistManager.js'/>" ></script>


<script type="text/javascript" >
		   function searchMiMember(){
		   var loadinfo = "loading....." ; 
		   var userCode=document.getElementById('miMember.userCode').value;
		   jmiMemberManager.getJmiMember(userCode,callBack);
		   jbdMemberLinkCalcHistManager.getLastHonorStar(userCode,callBackStar);
		       DWRUtil.useLoadingMessage(loadinfo);  
		   }
		   
		     function callBackStar(valid){
		     	if(valid!=null){
		     		
		     		
		     		switch(valid) {
					  case '0':
					  document.getElementById('honorStar').innerText="<jecs:locale key='null.star'/>";
					  break;
					  case '1':
					  document.getElementById('honorStar').innerText="<jecs:locale key='honor.star1'/>";
					  break;
					  case '2':
					  document.getElementById('honorStar').innerText="<jecs:locale key='honor.star2'/>";
					  break;
					  case '3':
					  document.getElementById('honorStar').innerText="<jecs:locale key='honor.star3'/>";
					  break;
					  case '4':
					  document.getElementById('honorStar').innerText="<jecs:locale key='honor.star4'/>";
					  break;
					  case '5':
					  document.getElementById('honorStar').innerText="<jecs:locale key='honor.star5'/>";
					  break;
					  case '6':
					  document.getElementById('honorStar').innerText="<jecs:locale key='honor.star6'/>";
					  break;
					  case '7':
					  document.getElementById('honorStar').innerText="<jecs:locale key='honor.star7'/>";
					  break;
					  case '8':
					  document.getElementById('honorStar').innerText="<jecs:locale key='honor.star8'/>";
					  break;
					  case '9':
					  document.getElementById('honorStar').innerText="<jecs:locale key='honor.star9'/>";
					  break;
					  default:
					  document.getElementById('honorStar').innerText='';
					  }
		     		
		     	}
		     	
		     	
		     	
		     	
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

				   document.getElementById('papernumber').innerText=valid.papernumber;
			   }
			   }
			   
			   
			  function checkStore(strAction){
					$('#strAction').val(strAction);
					$('#jmiStoreForm').submit();
				}
			
</script>


<form:form commandName="jmiStore" method="post" action="editJmiStore.html" onsubmit="return validateOthers();" id="jmiStoreForm">
<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<c:if test="${param.strAction!='viewJmiStore' && empty jmiStoreExist }">
				<button type="submit" class="btn btn_sele" name="save"  onclick="bCancel=false">
					<jecs:locale key="operation.button.save"/>
				</button>
				</c:if>
		</jecs:power>
		
		<c:if test="${param.strAction=='viewJmiStore'}">
		<jecs:power powerCode="checkJmiStore">
			<button type="button" class="btn btn_sele" name="save"  onclick="checkStore('checkJmiStore')">
				<jecs:locale key="button.audit"/>
			</button>
		</jecs:power>
		
		<jecs:power powerCode="unCheckJmiStore">
			<button type="button" class="btn btn_sele" name="save"  onclick="checkStore('unCheckJmiStore')">
				<jecs:locale key="button.notCheck"/>
			</button>
		</jecs:power>
		
		<jecs:power powerCode="confirmJmiStore">
			<button type="button" class="btn btn_sele" name="save"  onclick="checkStore('confirmJmiStore')">
				<jecs:locale key="operation.button.confirm"/>
			</button>
		</jecs:power>
		
		<jecs:power powerCode="unConfirmJmiStore">
			<button type="button" class="btn btn_sele" name="save"  onclick="checkStore('unConfirmJmiStore')">
				<jecs:locale key="button.bdUnCheck"/>
			</button>
		</jecs:power>
		</c:if>
		<button type="button" class="btn btn_sele" name="cancel" onclick="window.history.back()">
			<jecs:locale key="operation.button.return"/>
		</button>
</c:set>

<spring:bind path="jmiStore.*">
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

<%--
<div class="searchBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
--%>
<input type="hidden" name="strAction" value="${param.strAction }" id="strAction"/>

<form:hidden path="id"/>



<fieldset style="border-top: 2px solid #cccccc;border-right: 0px ;border-bottom: 0px ;border-left: 0px ;font-family: Arial, Helvetica, sans-serif;font-weight:bold;">
<legend ><jecs:locale key="member.base.info"/>:</legend>


<table class='detail' width="70%">
<tbody class="window">

    <tr class="edit_tr"><th>
	     <span class="text-font-style-tc"><jecs:label  key="miMember.memberNo"/></span>
    </th>
    <td >
    	<c:if test="${param.strAction=='addJmiStore' &&  sessionScope.sessionLogin.userType=='C'}">
   			<span class="textbox">
   			<form:input path="jmiMember.userCode" id="miMember.userCode" cssClass="textbox-text"/>
   			<img src="<c:url value="/images/l_1.gif"/>" id="person"  onclick="searchMiMember();" 
   			style="cursor: pointer;" title="<jecs:locale key="operation.button.search"/>"/> 
   			</span>
   		</c:if>
    	<c:if test="${param.strAction!='addJmiStore' ||  sessionScope.sessionLogin.userType!='C'}">
	 	 	<span class="textbox">${jmiStore.jmiMember.userCode }</span>
    	</c:if>
    </td><th>
        <span class="text-font-style-tc"><jecs:label key="bdCalculatingSubDetail.name" /></span>
    </th>
    <td >
    	<span class="textbox">${jmiStore.jmiMember.miUserName }</span>
    	<span id="miUserName"></span>
    </td></tr>
    
    <tr class="edit_tr"><th>
		<span class="text-font-style-tc"><jecs:label  key="miMember.sex"/></span>
    </th>
    <td >
      <span class="textbox"> <jecs:code listCode="sex" value="${jmiStore.jmiMember.sex}"/></span>
      <span id="sex"></span>
    </td><th>
        <span class="text-font-style-tc"><jecs:label  key="bdPinTitleRecord.pinTitle"/></span>
    </th>
    <td >
    	<span class="textbox"><jecs:code listCode="honor.star.zero" value="${jmiStore.honorStar}"/></span>
    	<span id="honorStar"></span>
    </td></tr>
    
    
    
    <tr class="edit_tr"><th>
	    <span class="text-font-style-tc"><jecs:label  key="miMember.papernumber"/></span>
    </th>
    <td >
       <span class="textbox">${jmiStore.jmiMember.papernumber }</span>
       <span id="papernumber"></span>
    </td><th>
         <span class="text-font-style-tc"><jecs:label  key="miMember.postalcode"/></span>
    </th>
    <td >
    	<c:if test="${modifyStatus=='yes' }">
    	<span class="textbox"><form:input path="postalcode" id="postalcode" cssClass="textbox-text"/></span>
    	 </c:if>
    	<c:if test="${modifyStatus=='no' }">
    		<span class="textbox">${jmiStore.postalcode }</span>
    	</c:if>	
    </td></tr>


    <tr class="edit_tr">
	    <th>
		     <span class="text-font-style-tc"><jecs:label  key="store.mailAddr"/></span>
	    </th>
	    <td>
	    	<c:if test="${modifyStatus=='yes' }">
	       <span class="textbox"><form:input path="mailAddr" id="mailAddr" cssClass="textbox-text" cssStyle="width:500px"/></span>
	    	 </c:if>
	    	<c:if test="${modifyStatus=='no' }">
	    		<span class="textbox">${jmiStore.mailAddr }</span>
	    	</c:if>	
	    </td>
	    <th>
	    	 <span class="text-font-style-tc"><jecs:label  key="miMember.email"/></span>
    	</th>
	    <td>
	    	<c:if test="${modifyStatus=='yes' }">
       			<span class="textbox"><form:input path="email" id="email" cssClass="textbox-text" cssStyle="width:500px"/></span>
    	 	</c:if>
    		<c:if test="${modifyStatus=='no' }">
    			<span class="textbox">${jmiStore.email }</span>
    		</c:if>	
	    </td>
    </tr>

    <tr class="edit_tr"><th>
	    <span class="text-font-style-tc"><jecs:label  key="miMember.mobiletele" required="true"/></span>
    </th>
    <td >
    	<c:if test="${modifyStatus=='yes' }">
       <span class="textbox"><form:input path="mobiletele" id="mobiletele" cssClass="textbox-text"/></span>
    	 </c:if>
    	<c:if test="${modifyStatus=='no' }">
    		<span class="textbox">${jmiStore.mobiletele }</span>
    	</c:if>	
    </td><th>
        <span class="text-font-style-tc"><jecs:label  key="miMember.faxtele"/></span>
    </th>
    <td >
    	<c:if test="${modifyStatus=='yes' }">
    	 <span class="textbox"><form:input path="faxtele" id="faxtele" cssClass="textbox-text"/></span>
    	 </c:if>
    	<c:if test="${modifyStatus=='no' }">
    		<span class="textbox">${jmiStore.faxtele }</span>
    	</c:if>	
    </td></tr>
    
    <tr class="edit_tr">
	    <th>
		    <span class="text-font-style-tc"><jecs:label  key="store.subStoreAddr" required="true"/></span>
	    </th>
	    <td>
	    	<c:if test="${modifyStatus=='yes' }">
	       <span class="textbox"><form:input path="subStoreAddr" id="subStoreAddr" cssClass="textbox-text" cssStyle="width:500px"/></span>
	    	 </c:if>
	    	<c:if test="${modifyStatus=='no' }">
	    		<span class="textbox">${jmiStore.subStoreAddr }</span>
	    	</c:if>	
	    </td>
	    <th><span class="text-font-style-tc"><jecs:label  key="store.storeAddr" required="true"/></span></th>
	    <td>
	    	<span class="textbox">
    	<c:if test="${modifyStatus=='yes' }">
        <form:select path="province"  cssClass="textbox-text" onchange="getCity()" >
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
    	 <jecs:locale key="subStore.province"/>
    	
    	<c:if test="${modifyStatus=='yes' }">
	        <select name="city" id="city" onchange="getDistrict()"  class="textbox-text">
				<option value=""><jecs:locale key="list.please.select"/></option>
			</select>
    	</c:if>
    	<c:if test="${modifyStatus=='no' }">
	        <select name="city" id="city" onchange="getDistrict()" disabled="true" class="textbox-text">
				<option value=""><jecs:locale key="list.please.select"/></option>
			</select>
    	</c:if>
    	 <jecs:locale key="subStore.city"/>
    	
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
    	 <jecs:locale key="subStore.district"/>
    	
    	<c:if test="${modifyStatus=='yes' }">
       <form:input path="address" id="address" cssClass="textbox-text" />
    	 </c:if>
    	<c:if test="${modifyStatus=='no' }">
    		${jmiStore.address }
    	</c:if>	
    	</span>
	    </td>
    </tr>
    
    <tr class="edit_tr"><th>
	    <span class="text-font-style-tc"><jecs:label  key="subStore.businessArea"/></span>
    </th>
    <td >
    	<c:if test="${modifyStatus=='yes' }">
       <span class="textbox"><form:input path="businessArea" id="businessArea" cssClass="textbox-text" /></span>
    	 </c:if>
    	<c:if test="${modifyStatus=='no' }">
    		<span class="textbox">${jmiStore.businessArea }</span>
    	</c:if>	
    	 <jecs:locale key="busi.unit.m"/>
    </td><th>
	    <span class="text-font-style-tc"><jecs:label  key="subStore.personQty"/></span>
    </th>
    <td >
    	<c:if test="${modifyStatus=='yes' }">
       <span class="textbox"><form:input path="personTotal" id="personTotal" cssClass="textbox-text" /></span>
    	 </c:if>
    	<c:if test="${modifyStatus=='no' }">
    		<span class="textbox">${jmiStore.personTotal }</span>
    	</c:if>	
    	 <jecs:locale key="busi.unit.wan"/>
    </td></tr>
    
    
    <tr class="edit_tr"><th>
	    <span class="text-font-style-tc"><jecs:label  key="store.cityType"/></span>
    </th>
    <td >
    	<c:if test="${modifyStatus=='yes' }">
         <span class="textbox">
         	<jecs:list name="citytype" listCode="store.citytype" value="${jmiStore.cityType}" 
         		defaultValue="" showBlankLine="true" styleClass="textbox-text"/>
         	</span>	
    	 </c:if>
    	<c:if test="${modifyStatus=='no' }">
       <span class="textbox"><jecs:code listCode="store.citytype" value="${jmiStore.cityType}"/></span>
    	</c:if>	
    </td><th>
	    <span class="text-font-style-tc"><jecs:label  key="subStore.averageIncome"/></span>
    </th>
    <td >
    	<c:if test="${modifyStatus=='yes' }">
       <span class="textbox"><form:input path="averageIncome" id="averageIncome" cssClass="textbox-text" /></span>
    	 </c:if>
    	<c:if test="${modifyStatus=='no' }">
    		<span class="textbox">${jmiStore.averageIncome }</span>
    	</c:if>	
    	 <jecs:locale key="busi.unit.yuan"/>
    	
    </td></tr>
    
    
    <tr class="edit_tr"><th>
	    <span class="text-font-style-tc"><jecs:label  key="subStore.isdeal"/></span>
    </th>
    <td >
    	<span class="textbox">
    	<c:if test="${modifyStatus=='yes' }">
         <jecs:list name="isdeal" listCode="yesno" value="${jmiStore.isdeal}" 
         	styleClass="textbox-text" defaultValue="" showBlankLine="true"/>	
    	</c:if>
    	<c:if test="${modifyStatus=='no' }">
	 	 	 <jecs:code listCode="yesno" value="${jmiStore.isdeal}"/>
    	</c:if>
    	</span>
    </td><th>
	    <span class="text-font-style-tc"><jecs:label  key="subStore.specificBusiness"/></span>
    </th>
    <td >
    	<c:if test="${modifyStatus=='yes' }">
       	 <span class="textbox"><form:input path="business" id="business" cssClass="textbox-text"/></span>
    	</c:if>
    	<c:if test="${modifyStatus=='no' }">
    		<span class="textbox">${jmiStore.business }</span>
    	</c:if>	
    </td></tr>

</table>

</fieldset>



    
 <c:if test="${param.strAction!='viewJmiStore' }">
<fieldset style="border-top: 2px solid #cccccc;border-right: 0px ;border-bottom: 0px ;border-left: 0px ;font-family: Arial, Helvetica, sans-serif;font-weight:bold;">
<legend ><jecs:locale key="register.memberInfoIden.agree"/>:</legend>


<table class='detail' width="70%">
<tbody class="window">
    <tr class="edit_tr"><th>
	    
    </th>
    <td >

    	

<div style="overflow-y:auto;  height:200px; " > 
	
<jsp:include flush="true" page="store_attachment0.jsp"></jsp:include>

</div>
    </td></tr>
   
    <tr class="edit_tr"><th>
	    
    </th>
    <td >
	
		<input type="checkbox" value="agree" id="agree" name="agree">
		<jecs:locale key="register.agree"/>
	
    </td></tr>
   
</tbody>
</table>

</fieldset>   
  </c:if>  



<c:if test="${sessionScope.sessionLogin.userType=='C' }">
<div id="backInfo">
<fieldset  style="border-top: 2px solid #cccccc;border-right: 0px ;border-bottom: 0px ;border-left: 0px ;font-family: Arial, Helvetica, sans-serif;font-weight:bold;">



<table class='detail' width="70%">
<tbody class="window">
    <tr class="edit_tr">
    	<th>
	    	<span class="text-font-style-tc"><jecs:label  key="busi.user.check"/></span>
    	</th>
	    <td>
			<span class="textbox">${jmiStore.checkUser } - ${jmiStore.checkTime }</span>
	    </td>
	    <th>
		 	<span class="text-font-style-tc"><jecs:label key="pd.confirmUserNo"/></span>
	    </th>
	    <td>
			<span class="textbox">${jmiStore.confirmUser } - ${jmiStore.confirmTime }</span>
	    </td>
	</tr>
   
    <tr class="edit_tr">
	    <th>
		    <span class="text-font-style-tc-tare"><jecs:label  key="jmiSubStore.checkRemark"/></span>
	    </th>
	    <td colspan="3">
			<span class="text-font-style-tc-right">
				<form:textarea path="checkRemark" cssClass="textarea_border"/>
			</span>
	    </td>
	</tr>
	<tr class="edit_tr">
		<th >
			<span class="text-font-style-tc-tare"><jecs:label key="jmiSubStore.confirmRemark"/></span>
		</th>
		<td colspan="3"><span class="text-font-style-tc-right"><form:textarea path="confirmRemark" cssClass="textarea_border"/></span></td>
	</tr>
    <tr class="edit_tr">
    	<td class="command" colspan="4" align="center">
    		<c:out value="${buttons}" escapeXml="false"/>
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
			        if(cityId=='${jmiStore.city}'){
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
				   if('${jmiStore.district}'==districtId){
				   		o.selected=true;
				   } 
			   }
		   
		
		   }
		   
		 
<c:if test="${not empty jmiStore.province }">
			getCity();
</c:if>  
		
		
		
					
function validateOthers(){
	if( document.getElementById('businessArea').value!=''&& !javaValidNumber(document.getElementById('businessArea').value)){
		alert("<jecs:locale key="register.us.select.number"/>");
		document.getElementById('businessArea').focus();
		return false;
	}
	if( document.getElementById('averageIncome').value!=''&& !javaValidNumber(document.getElementById('averageIncome').value)){
		alert("<jecs:locale key="register.us.select.number"/>");
		document.getElementById('averageIncome').focus();
		return false;
	}
	if( document.getElementById('personTotal').value!=''&& !javaValidNumber(document.getElementById('personTotal').value)){
		alert("<jecs:locale key="register.us.select.number"/>");
		document.getElementById('personTotal').focus();
		return false;
	}
	return true;
}
		
		
		
		
		   
		</script>
