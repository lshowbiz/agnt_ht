<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jmiAddrBookDetail.title"/></title>
<content tag="heading"><jecs:locale key="jmiAddrBookDetail.heading"/></content>

<script type="text/javascript" src="<c:url value='/dwr/util.js'/>" ></script> 
<script type="text/javascript" src="<c:url value='/dwr/engine.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/dwr/interface/alCityManager.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/dwr/interface/alDistrictManager.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/dwr/interface/jalTownManager.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/dwr/interface/jalPostalcodeManager.js'/>" ></script>
		

<spring:bind path="jmiAddrBook.*">
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

<form:form commandName="jmiAddrBook" method="post" action="editJmiAddrBook.html"  id="jmiAddrBookForm" onsubmit="return onSubmitCheck(this);">
	<%--
	<div class="searchBar">	
		<jecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="operation.button.save"/>" />
		</jecs:power>

		<jecs:power powerCode="deleteJmiAddrBook">
				<c:if test="${not empty jmiAddrBook.fabId}">
					<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'JmiAddrBook')" value="<jecs:locale key="operation.button.delete"/>" />
				</c:if>
		</jecs:power>
		
		<input type="button" class="button" name="cancel" onclick="window.history.back()" value="<jecs:locale key="operation.button.return"/>" />
	</div>
	--%>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<table class='detail' width="70%">

<form:hidden path="fabId"/>
	<tbody class="window" >
    <c:if test="${sessionScope.sessionLogin.userType=='C'}">
	<tr class="edit_tr">
	    <th>
	    	<span class="text-font-style-tc"><jecs:label  key="miMember.memberNo" required="true"/></span>
	    </th>
	    <td>
	    	<span class="textbox">
	        <!--form:errors path="firstName" cssClass="fieldError"/-->
	        <c:if test="${not empty jmiAddrBook.fabId}">
	        	${jmiAddrBook.jmiMember.userCode }
	        </c:if>
	        <c:if test="${empty jmiAddrBook.fabId}">
	        	<form:input path="jmiMember.userCode" id="jmiMember.userCode" cssClass="textbox-text"/>
	        </c:if>
	        </span>
	    </td>
	    <th>
	    	<span class="text-font-style-tc"><jecs:label  key="shipping.firstName" required="true"/></span>
	    </th>
	    <td> 
		    <!--form:errors path="firstName" cssClass="fieldError"/-->
	        <span class="textbox"><form:input path="firstName" id="firstName" cssClass="textbox-text"/></span>
       	</td>
	</tr>
	</c:if>
	<tr class="edit_tr">
		<th>
        	<span class="text-font-style-tc"><jecs:label  key="shipping.lastName" required="true"/></span>
    	</th>
    	<td>
	        <!--form:errors path="lastName" cssClass="fieldError"/-->
	        <span class="textbox"><form:input path="lastName" id="lastName" cssClass="textbox-text"/></span>
    	</td>
   		<th>
    		<c:if test="${sessionScope.sessionLogin.companyCode=='PH'}">
		        <span class="text-font-style-tc"><jecs:label  key="shipping.island" required="true"/></span>
		    </c:if>
		    <c:if test="${sessionScope.sessionLogin.companyCode!='PH'}">
		        <span class="text-font-style-tc"><jecs:label  key="shipping.province" required="true"/></span>
		    </c:if>
    	</th>
    	<td>
    		<span class="textbox">
	        <!--form:errors path="province" cssClass="fieldError"/-->
	        <form:select path="province"  cssClass="textbox-text" onchange="getCity()">
				<form:option label="" value=""><jecs:locale key='list.please.select'/></form:option>
	            <form:options items="${alStateProvinces}" itemValue="stateProvinceId" itemLabel="stateProvinceName" />
			</form:select>
			</span>
    	</td>
    </tr>

    <tr class="edit_tr">
    	<th>
		    <c:if test="${sessionScope.sessionLogin.companyCode=='PH'}">
		        <span class="text-font-style-tc"><jecs:label  key="shipping.region" required="true"/></span>
		    </c:if>
		    <c:if test="${sessionScope.sessionLogin.companyCode!='PH'}">
		        <span class="text-font-style-tc"><jecs:label  key="shipping.city" required="true"/></span>
		    </c:if>
    	</th>
    	<td>
    		<span class="textbox">
	        <!--form:errors path="city" cssClass="fieldError"/-->
	        <select name="city" id="city" onchange="getDistrict()" class="textbox-text">
				<option value=""><jecs:locale key="list.please.select"/></option>
			</select>
			</span>
    	</td>
   		<th>
		    <c:if test="${sessionScope.sessionLogin.companyCode=='PH'}">
		        <span class="text-font-style-tc"><jecs:label  key="shipping.province" required="true"/></span>
		    </c:if>
		    <c:if test="${sessionScope.sessionLogin.companyCode!='PH'}">
		        <span class="text-font-style-tc"><jecs:label  key="shipping.district" required="true"/></span>
		    </c:if>
    	</th>
    	<td>
    		<span class="textbox">
        	<!--form:errors path="city" cssClass="fieldError"/-->
    		<c:if test="${sessionScope.sessionLogin.companyCode=='PH'}">
        		<select name="district" id="district" onchange="getTown();" class="textbox-text">
    		</c:if>
    		<c:if test="${sessionScope.sessionLogin.companyCode!='PH'}">
        		<select name="district" id="district" onchange="getPostalcodeTW(this);" class="textbox-text">
    		</c:if>
				<option value=""><jecs:locale key="list.please.select"/></option>
			</select>	
			</span>
    	</td>
    </tr>
    <%-- 
    <c:if test="${sessionScope.sessionLogin.companyCode=='PH'}">
	    <tr><th>
	        <jecs:label  key="shipping.city" required="true"/>
	    </th>
	    <td align="left">
	        <select name="town" id="town" >
				<option value=""><jecs:locale key="list.please.select"/></option>
			</select>	
	    </td></tr>
    </c:if>
	--%>
    <tr class="edit_tr">
    	<th>
        	<span class="text-font-style-tc"><jecs:label  key="shipping.address" required="true"/></span>
    	</th>
	    <td>
	        <!--form:errors path="address" cssClass="fieldError"/-->
	        <span class="textbox"><form:input path="address" id="address" cssClass="textbox-text"/></span>
	    </td>
	
	<%-- 
    <c:if test="${sessionScope.sessionLogin.companyCode=='JP'}">
	    <tr><th>
	        <jecs:label  key="miMember.building" />
	    </th>
	    <td align="left">
	  		  <form:input path="building" id="building" cssClass="text medium"/>
	    </td></tr>
    </c:if>
	--%>
    	<th>
        	<span class="text-font-style-tc"><jecs:label  key="shipping.postalcode" required="true"/></span>
    	</th>
    	<td>
	        <!--form:errors path="postalcode" cssClass="fieldError"/-->
	        <span class="textbox">
	        	<form:input path="postalcode" id="postalcode" cssClass="textbox-text"  onchange="regPostalcode();"/>
	        </span>
       		<div id="divPostalcode" style="display:inline"></div>
	 		<c:if test="${sessionScope.sessionLogin.companyCode=='JP' }">
	 	 		<input type="button" class="button" onclick="getPostalcode();" 
	 	 			value="<jecs:locale key="operation.button.search"/>" />
	 		</c:if>
    	</td>
    </tr>

    <tr class="edit_tr">
    	<th>
        	<span class="text-font-style-tc"><jecs:label  key="miMember.phone"/></span>
    	</th>
    	<td align="left">
	        <!--form:errors path="phone" cssClass="fieldError"/-->
	        <span class="textbox"><form:input path="phone" id="phone" cssClass="textbox-text"/></span>
	    </td>
		<th>
        	<span class="text-font-style-tc"><jecs:label  key="miMember.email"/></span>
    	</th>
	    <td>
	        <!--form:errors path="email" cssClass="fieldError"/-->
	        <span class="textbox"><form:input path="email" id="email" cssClass="textbox-text"/></span>
	    </td>
	</tr>

    <tr class="edit_tr">
    	<th>
        	<span class="text-font-style-tc"><jecs:label  key="miMember.mobiletele" required="true"/></span>
    	</th>
	    <td align="left">
	        <!--form:errors path="mobiletele" cssClass="fieldError"/-->
	        <span class="textbox">
	        	<form:input path="mobiletele" id="mobiletele" cssClass="textbox-text"/>
	        	<jecs:locale key="miMember.mobile.phone.remark" />
	        </span>
	    </td>
	    <th>
        	<span class="text-font-style-tc"><jecs:label  key="fiBillAccount.isDefault"/></span>
    	</th>
    	<td>
        	<span class="textbox"><form:checkbox path="isDefault" id="isDefault" value='1'/></span>
    	</td>
    </tr>
    
    <tr class="edit_tr">
    	<td class="command" colspan="4" align="center">
    		<jecs:power powerCode="${param.strAction}">
    			<button type="submit" class="btn btn_sele" name="save" onclick="bCancel=false">
    				<jecs:locale key="operation.button.save"/>
    			</button>
			</jecs:power>
			<jecs:power powerCode="deleteJmiAddrBook">
				<c:if test="${not empty jmiAddrBook.fabId}">
					<button type="submit" class="btn btn_sele" name="delete" 
						onclick="bCancel=true;return confirmDelete(this.form,'JmiAddrBook')">
						<jecs:locale key="operation.button.delete"/>
					</button>
				</c:if>
			</jecs:power>
			<button type="button" class="btn btn_sele" name="cancel" onclick="window.history.back()">
				<jecs:locale key="operation.button.return"/>
			</button>
    	</td>
    </tr>
    </tbody>
</table>

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
			        if(cityId=='${jmiAddrBook.city}'){
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
				   if('${jmiAddrBook.district}'==districtId){
				   		o.selected=true;
				   } 
			   }
		   
		
		   } 
		   function getTown(){
		   	var district=document.getElementById('district').value;
		   	jalTownManager.getJalTownByDistrictId(district,callBackTown);
		   }
		   function callBackTown(valid){
			   var townElemment=document.getElementById('town');
    
        		townElemment.options.length=0;
     
          		var o=new Option("<jecs:locale key="list.please.select"/>","");
           		townElemment.options.add(o);  
           
		       for (var i=0;i<valid.length;i++) {
		        var townId= valid[i].townId;	   
		        var townName=valid[i].townName;   
		        var o=new Option(townName,townId);
		        townElemment.options.add(o);       
				   if('${jmiAddrBook.town}'==townId){
				   		o.selected=true;
				   } 
			   }
		   
		
		   }
		</script>



	
		<script>
			getCity();
			
    <c:if test="${sessionScope.sessionLogin.companyCode=='PH'}">
    getTown();
    </c:if>
    function getPostalcodeTW(selObj){
		    	alDistrictManager.getAlDistrict(selObj.options[selObj.selectedIndex].value,callBackPostalcode);
		    }
		    function callBackPostalcode(valid){
		    	if(valid.postalcode!=null){
		    		document.getElementById('postalcode').value=valid.postalcode;
		    	}
		    }
		    
		    
			
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
					 getCity();
					 for(var i = 0 ; i < document.getElementById("city").options.length ; i++){
						if(document.getElementById("city").options[i].value ==valid.alCity.cityId){
							document.getElementById("city").selectedIndex = i;
						}
					}
	DWREngine.setAsync(true);
		    	}
		    	
		    }
	    
		    
		    
		    
		    function onSubmitCheck(){
				var str = document.getElementById("divPostalcode").innerHTML;
				if(str != ""){
					alert(str);
					return false;
				}
			}
			
			function regPostalcode(){
				document.getElementById("divPostalcode").innerHTML="";
				var postalcode = document.getElementById('postalcode').value;
				var reg = /^\d{6}$/;
				if(!reg.test(postalcode)){
					document.getElementById("divPostalcode").innerHTML="<jecs:locale key='postalcode.formatError'/>";
				}
			}
			
			function regMobile(){
				document.getElementById("divMobile").innerHTML="";
				var mobile = document.getElementById('mobiletele').value;
				var reg = /^((\+?86)|(\(\+86\)))?1\d{10}$/;
				if(!reg.test(mobile)){
					document.getElementById("divMobile").innerHTML="<jecs:locale key='mobiletele.formatError'/>";
				}
			}
			
		</script>


<!--/fieldset-->

<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JmiAddrBook')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('jmiAddrBookForm'));
</script>