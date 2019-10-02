
<%@ include file="/common/taglibs.jsp"%>
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
	<c:if test="${not empty sessionScope.redirectTager }">
	<script type="text/javascript">
		window.parent.location.href="login.html";
	</script> 
	<c:remove var="redirectTager" scope="session"/>
</c:if>	   
        
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
<%-- 
	<div id="titleBar" class="searchBar">
		<input type="submit" class="button" name="save" value="<jecs:locale key="operation.button.save"/>" />
	</div>
--%>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<fieldset style="border-top: 2px solid #cccccc;border-right: 0px ;border-bottom: 0px ;border-left: 0px ;font-family: Arial, Helvetica, sans-serif;font-weight:bold;">
<legend ><jecs:locale key="member.base.info"/>:</legend>
<table class='detail' width="70%">
	<tbody class="window">
	<tr class="edit_tr">
		<th>
	  		<span class="text-font-style-tc"><jecs:label  key="miMember.memberNo"/></span>
    	</th>
	    <td>
	    	<span class="textbox">
			 	${ jmiMember.userCode} - 
				<jecs:code listCode="bd.cardtype" value="${jmiMember.cardType}"/>
			</span>
	    </td>
	  	<th>
	 		<span class="text-font-style-tc"><jecs:label key="miMember.recommendNo" required="true" /></span>
    	</th>
	    <td>
			<span class="textbox">${jmiMember.recommendNo }</span>
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
	    	<span class="textbox"><jecs:list styleClass="textbox-text" name="sex" listCode="sex" value="${jmiMember.sex}" defaultValue=""/></span>	
	    </td>
	</tr>
     <tr class="edit_tr">
     	<th>
	    	<span class="text-font-style-tc"><jecs:label  key="miMember.papertype"/></span>
    	</th>
	    <td>
		   <span class="textbox"><jecs:code listCode="papertype" value="${jmiMember.papertype }"/></span>
	    </td>
	   	<th>
	 		<span class="text-font-style-tc"><jecs:label key="miMember.papernumber" required="true" /></span>
    	</th>
    	<td>
    		<span class="textbox">
			<c:if test="${empty papernumberModify }">
			 	<form:input path="papernumber" id="papernumber" cssClass="textbox-text"/>
			</c:if>
			<c:if test="${not empty papernumberModify }">
				${ jmiMember.papernumber}
			</c:if>
			</span>
    	</td>
   	</tr>
   
    <tr class="edit_tr">
	    <th>
		     <span class="text-font-style-tc"><jecs:label key="miMember.province" required="true" /></span>
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
		 	<span class="text-font-style-tc"><jecs:label key="miMember.idAddr2" required="true" /></span>
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
	   		<span class="text-font-style-tc"><jecs:label key="miMember.district" /></span>
    	</th>
    	<td>
    		<span class="textbox">
			<select name="district" id="district" class="textbox-text">
				<option value=""><jecs:locale key="list.please.select"/></option>
			</select>
			</span>
    	</td>
    	<th>
	   		<span class="text-font-style-tc"><jecs:label key="miMember.idAddr" required="true" /></span>
    	</th>
    	<td>
	  		<span class="textbox"><form:input path="address" id="address" cssClass="textbox-text"/></span>
    	</td>
    </tr>
    <tr class="edit_tr">
    	<th>
	    	<span class="text-font-style-tc"><jecs:label key="miMember.postalcode" /></span>
    	</th>
	    <td>
		 	<span class="textbox"><form:input path="postalcode" id="postalcode" cssClass="textbox-text"/></span>
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
    	<td align="left">
	  		<span class="textbox"><form:input path="mobiletele" id="mobiletele" cssClass="textbox-text"/></span>
    	</td>
    </tr>
	<tr class="edit_tr">
		<th>
	    	<span class="text-font-style-tc"><jecs:label  key="miMember.email"/></span>
    	</th>
	    <td>
		 	<span class="textbox"><form:input path="email" id="email" cssClass="textbox-text"/></span>
	    </td>
	   	<th><span class="text-font-style-tc"><jecs:label key="miMember.customerLevel"/></span></th>
    	<td>
    		<span class="textbox">
    			<jecs:code listCode="bd.customerlevel" value="${jmiMember.customerLevel}"/>
    		</span>
    	</td>
    </tr>
	<tr class="edit_tr">
		<th>
	  		<span class="text-font-style-tc"><jecs:label key="miMember.spouseName" /></span>
		</th>
    	<td><span class="textbox">${ jmiMember.spouseName}</span></td>
    	<th>
			<span class="text-font-style-tc"><jecs:label key="miMember.spouseIdno" /></span>
    	</th>
    	<td><span class="textbox">${ jmiMember.spouseIdno}</span></td>
    </tr>
    <tr class="edit_tr">
    	<th>
	  		<span class="text-font-style-tc"><jecs:label  key="member.ugrade.time"/></span>
    	</th>
    	<td><span class="textbox">${days }</span></td>
	    <th>
		    <c:if test="${ jmiMember.cardType!='0'}" >
			<span class="text-font-style-tc"><jecs:label  key="jmiMember.validWeek"/></span>
			</c:if>
	    </th>
	    <td id="validMonthYearId" >
	    	<span class="textbox">
		    <c:if test="${ jmiMember.cardType!='0'}" >
	    	<input type="hidden" value="<jecs:monthFormat month='${jmiMember.validWeek}' monthType='w' />" 
	    		id="validMonthYear" name="validMonthYear"/>
	    	<script type="text/javascript">
				document.getElementById('validMonthYearId').innerHTML='<span class="textbox">'+
					document.getElementById('validMonthYear').value.substr(0, 4)+
					'<jecs:locale  key="bdPeriod.wyear"/><jecs:locale  key="jmiMember.di"/>'+
					document.getElementById('validMonthYear').value.substr(4, 6)+
					'<jecs:locale  key="bdPeriod.wmonth"/>'+'</span>';
			</script>
	 		</c:if>
	 		</span>
		</td>
    </tr>
    </tbody>   
</table>
</fieldset>

<c:if test="${ empty jmiMember.bank ||  empty jmiMember.bankbook ||   
	empty jmiMember.bankcard || empty jmiMember.bankaddress ||  not empty bankViewUnLimit }">
       
<fieldset style="border-top: 2px solid #cccccc;border-right: 0px ;border-bottom: 0px ;border-left: 0px ;font-family: Arial, Helvetica, sans-serif;font-weight:bold;">
<legend ><jecs:locale key="member.bank.info"/>:</legend>

<input id="oldBank" name="oldBank" value="${oldBank }" type="hidden">
<input id="oldBankaddress" name="oldBankaddress" value="${oldBankaddress }" type="hidden">
<input id="oldBankbook" name="oldBankbook" value="${oldBankbook }" type="hidden">
<input id="oldBankcard" name="oldBankcard" value="${oldBankcard }" type="hidden">

<table class='detail' width="70%">
	<tbody class="window" >
    <tr class="edit_tr">
    	<th>
	    	<span class="text-font-style-tc"><jecs:label key="miMember.bankProvince" required="true" /></span>
    	</th>
    	<td align="left" nowrap="nowrap" width="250">
    		<span class="textbox">
        	<form:select path="bankProvince"  cssClass="textbox-text" onchange="getBankCity()" disabled="${bankProvinceModify }">
				<form:option label="" value=""/>
	            <form:options items="${alStateProvinces}" itemValue="stateProvinceId" itemLabel="stateProvinceName"/>
			</form:select>
			</span>
    	</td>
    	<th>
	 		<span class="text-font-style-tc"><jecs:label  key="miMember.bankCity"  required="true" /></span>
    	</th>
    	<td><span class="textbox">
			<select class="textbox-text" name="bankCity" id="bankCity" 	
				<c:if test="${not empty bankCityModify }"> disabled="true" </c:if> >
				<option value=""><jecs:locale key="list.please.select"/></option>
			</select>
		</span>
    	</td>
    </tr>
	<tr class="edit_tr">
		<th>
	    	<span class="text-font-style-tc"><jecs:label key="miMember.openBank" required="true" /></span>
    	</th>
    	<td>
	   		<c:if test="${empty bankModify }">
				<span class="textbox">
				<form:select path="bank"  cssClass="textbox-text">
					<form:option value=""></form:option>
		            <form:options items="${sysBankList}" itemValue="bankKey" itemLabel="bankValue"/>
		        </form:select>
		        </span>
			</c:if>
			<c:if test="${not empty bankModify }">
				<c:forEach items="${sysBankList }" var="curSysBankList">
					<c:if test="${curSysBankList.bankKey==jmiMember.bank }">
						${curSysBankList.bankValue }
					</c:if>
				</c:forEach>
			</c:if>
		</td>
		<th>
			<span class="text-font-style-tc"><jecs:label key="miMember.bcity" required="true" /></span>
    	</th>
    	<td>
			<c:if test="${empty bankaddressModify }">
	 			<span class="textbox"><form:input path="bankaddress" id="bankaddress" cssClass="textbox-text" /></span>
			</c:if>
			<c:if test="${not empty bankaddressModify }">
				<span class="textbox">${ jmiMember.bankaddress}</span>
			</c:if>
    	</td>
    </tr>

     <tr class="edit_tr">
     	<th>
	    	<span class="text-font-style-tc"><jecs:label key="miMember.bname" required="true" /></span>
    	</th>
    	<td><span class="textbox">${jmiMember.bankbook }</span></td>
    	<th>
	 		<span class="text-font-style-tc"><jecs:label key="miMember.bnum" required="true" /></span>
    	</th>
   	 	<td>
			<c:if test="${empty bankcardModify }">
				<span class="textbox"><form:input path="bankcard" id="bankcard" cssClass="textbox-text" /></span>
			</c:if>
			<c:if test="${not empty bankcardModify }">
				<span class="textbox">${ jmiMember.bankcard}</span>
			</c:if>
    	</td>
    </tr>
    <tr class="edit_tr">
    	<td class="command" colspan="4" align="center">
    		<button type="submit" class="btn btn_sele" name="save">
    			<jecs:locale key="operation.button.save"/>
    		</button>
    	</td>
    </tr>
   </tbody>   
</table>
</fieldset>
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
		   
		   
<c:if test="${ empty jmiMember.bank ||  empty jmiMember.bankbook ||   empty jmiMember.bankcard }">
		   getBankCity();
		   
	
</c:if>	   
	


</script>
</script>

