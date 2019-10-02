<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jpoMemberOrderDetail.title"/></title>
<content tag="heading"><jecs:locale key="jpoMemberOrderDetail.heading"/></content>
<link rel="stylesheet" href="./css/user.css">
<link rel="stylesheet" href="load_images/WebResource.css">
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css">

<script src="<c:url value='/dwr/util.js'/>"></script> 
<script src="<c:url value='/dwr/engine.js'/>"></script>
<script src="<c:url value='/dwr/interface/alCityManager.js'/>"></script>
<script src="<c:url value='/dwr/interface/alDistrictManager.js'/>"></script>
<script src="<c:url value='/dwr/interface/jalTownManager.js'/>"></script>

<!--<script src="load_images/prototype.js"></script>-->
<script src="load_images/load_pic.js"></script>
<script src="./scripts/calendar/calendar.js"></script> 
<script src="./scripts/calendar/calendar-setup.js"></script> 
<script src="./scripts/calendar/lang.jsp"></script> 
<script>
<!--
var objPeerTip=new PeerTip();// -->
Close_PIC=function(Name)
{
	var iobj=eval(Name);
	var span_obj=eval(Name);	
	span_obj.style.display="none";	
}
</script>
<c:set var="buttons">

<c:if test='${empty jpoMemberOrder.moId}'>
<jecs:power powerCode="addPoMemberUOrder">
	<button type="submit" class="btn btn_sele" name="save"  onclick="bCancel=false">
		<jecs:locale key="operation.button.save"/>
	</button>
</jecs:power>
</c:if>

<c:if test='${jpoMemberOrder.status=="1" && jpoMemberOrder.submitStatus =="1" && not empty jpoMemberOrder.moId}'>
<jecs:power powerCode="editPoMemberUOrder">
	<button type="submit" class="btn btn_sele" name="save"  onclick="bCancel=false">
		<jecs:locale key="operation.button.save"/>
	</button>
</jecs:power>
<jecs:power powerCode="deletePoMemberFOrder">
	<button type="submit" class="btn btn_sele" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'PoMemberUOrder')">
		<jecs:locale key="operation.button.delete"/>
	</button>
</jecs:power>
</c:if>

<button type="button" class="btn btn_sele" name="back"  onclick="window.location.href='jpoMemberUOrders.html?needReload=1'">
	<jecs:locale key="operation.button.return"/>
</button>
</c:set>

<spring:bind path="jpoMemberOrder.*">
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
<script>
function searchMiMember(){
	$('jpoMemberOrderForm').method = "GET";
	$('jpoMemberOrderForm').submit();
}
</script>
<script>
  var aSub = Array();
  function set_amt(nQty,no,productNo)
  {	
     if(productNo=="P01170300101CN0")
	  {
			if(nQty.value>0)
			{
			 alert("<jecs:locale key="product.notEnough.tip"/>");
			}
	  }

    if (!isFinite(nQty.value) || nQty.value < 0 ){
	  	nQty.value=aSub[no]['qty'];
	}else {
	  nQty.value=Number(nQty.value);
	  aSub[no]['qty']=Number(nQty.value);
	}
	amt_count();
  }
  function amt_count(){
	var nPriceAmt=Number(0);
	var nPvAmt=Number(0);
	var nPvQty=Number(0);
	for (i=0;i<aSub.length;i++) {
		if(aSub[i]!=undefined){
		  nPriceAmt+=Number(aSub[i]['qty'])*Number(aSub[i]['price']);
		  nPvAmt+=Number(aSub[i]['qty'])*Number(aSub[i]['pv']);
		  nPvQty+=Number(aSub[i]['qty']);
	  	}
	}
    $('total_price').innerHTML=nPriceAmt;
    $('total_pv').innerHTML=nPvAmt;
    $('total_qty').innerHTML=nPvQty;
   
  }
</script>
<script>
function jmiAddrBookFill(selectIndex){
	if(jmiAddrBook[selectIndex]==undefined){
		return;
	}
	$('firstName').value = jmiAddrBook[selectIndex].firstName;
	$('lastName').value = jmiAddrBook[selectIndex].lastName;
	for(var i = 0 ; i < $('province').options.length ; i++){
		if($('province').options[i].value == jmiAddrBook[selectIndex].province){
			$('province').selectedIndex = i;
			tmpSelect = '1';
			getCity();
		}
	}
	//$('province').selectedIndex = jmiAddrBook[selectIndex].province;
	$('city').value = jmiAddrBook[selectIndex].city;
	<c:if test="${sessionScope.sessionLogin.companyCode=='PH'}">
	$('town').value = jmiAddrBook[selectIndex].town;
	</c:if>
	$('address').value = jmiAddrBook[selectIndex].address;
	<c:if test="${sessionScope.sessionLogin.companyCode=='JP'}">
	$('building').value = jmiAddrBook[selectIndex].building;
	</c:if>
	$('postalcode').value = jmiAddrBook[selectIndex].postalcode;
	$('phone').value = jmiAddrBook[selectIndex].phone;
	$('email').value = jmiAddrBook[selectIndex].email;
	$('mobiletele').value = jmiAddrBook[selectIndex].mobiletele;
}
</script>

<script>
function onSubmitCheck(formId){
	if($('pickup').value == '1'){
	<c:if test="${empty jpoMemberOrder.moId && sessionLogin.userType=='M' }">
		if($('agree').checked==false){
			alert('<jecs:locale key="jpoMemberOrder.mustAgree"/>');
			return false;
		}
	</c:if>
		if(isFormPosted()){
			return true;
		}else{
			return false;
		}
	}
	if(validateJpoMemberOrder(formId)){
	<c:if test="${empty jpoMemberOrder.moId && sessionLogin.userType=='M' }">
		if($('agree').checked==false){
			alert('<jecs:locale key="jpoMemberOrder.mustAgree"/>');
			return false;
		}
	</c:if>
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
<form:form commandName="jpoMemberOrder" method="post" action="editJpoMemberUOrder.html" onsubmit="return onSubmitCheck(this);" id="jpoMemberOrderForm">

<input type="hidden" name="strAction" value="${param.strAction }"/>
	<div class="searchBar">
			<c:out value="${buttons}" escapeXml="false"/>
	</div>
<table class='detail' id="tableId" width="100%">
	<tbody class="window">
	<form:hidden path="moId"/>

	<c:if test='${param.strAction=="editPoMemberUOrder"}'>
    <tr>
    	<th>
	    	<span class="text-font-style-tc">
	    	<jecs:label  key="poMemberOrder.memberOrderNo"/>
	    	</span>
    	</th>
	    <td>
	    	<span class="textbox">${jpoMemberOrder.memberOrderNo }</span>
	    </td>
	</tr>
	</c:if>

    <tr>
    	<th>
	    	<span class="text-font-style-tc">
	        	<jecs:label  key="miMember.memberNo" required="true"/>
	    	</span>
    	</th>
   	 	<td>
   	 		<span class="textbox">
	    	<c:if test='${param.strAction=="editPoMemberUOrder"}'>
	    		${jpoMemberOrder.sysUser.userCode }
	    	</c:if>
	    	<c:if test='${param.strAction=="addPoMemberUOrder"}'>
	        	<c:if test="${sessionLogin.userType=='M' }">
	        		${jpoMemberOrder.sysUser.userCode }
	        	</c:if>
	        	<c:if test="${sessionLogin.userType=='C' }">
	        		<form:input path="sysUser.userCode" id="sysUser.userCode" cssClass="textbox-text"/>
	        		<img src="<c:url value="/images/l_1.gif"/>" id="person"  onclick="searchMiMember();" 
	        		style="cursor: pointer;" title="<jecs:locale key="operation.button.search"/>"/> 
		    		${jpoMemberOrder.sysUser.userName}
	        	</c:if>
	    	</c:if>
    		</span>
    	</td>
   		<th>
   			<span class="text-font-style-tc">
        		<jecs:label  key="poMemberOrder.orderType"/>
        	</span>
    	</th>
    	<td>
    		<span class="textbox">
	    	<c:if test='${param.strAction=="editPoMemberUOrder"}'>
	    		<jecs:code listCode="po.order_type" value="${jpoMemberOrder.orderType}"/>
	    	</c:if>
	    	<c:if test='${param.strAction=="addPoMemberUOrder"}'>
	        	<jecs:code listCode="po.order_type" value="${jpoMemberOrder.orderType}"/>
	    	</c:if>
	    	</span>
    	</td>
    </tr>

<c:if test='${param.strAction=="editPoMemberUOrder"}'>
    <tr>
	    <th>
	    	<span class="text-font-style-tc">
	        	<jecs:label  key="bonus.order.amount"/>
	        </span>
	    </th>
	    <td>
	        <span class="textbox">${jpoMemberOrder.amount }</span>
	    </td>
	    <th>
	    	<span class="text-font-style-tc">
	        <jecs:label  key="poMemberOrder.pvAmt"/>
	        </span>
	    </th>
	    <td>
	        <span class="textbox">${jpoMemberOrder.pvAmt }</span>
	    </td>
    </tr>
</c:if>

<%--
    <tr><th>
        <jecs:label  key="busi.order.payMode"/>
    </th>
    <td >
    	<c:if test='${param.strAction=="editPoMemberUOrder"}'>
    		<jecs:code listCode="po.paymode" value="${jpoMemberOrder.payMode}"/>
    	</c:if>
    	<c:if test='${param.strAction=="addPoMemberUOrder"}'>
        	<jecs:list name="payMode" listCode="po.paymode" value="${jpoMemberOrder.payMode}" defaultValue="5"/>
    	</c:if>
    </td></tr>
--%>

    <tr>
    	<th>
    		<span class="text-font-style-tc">
        		<jecs:label  key="po.shipment.type"/>
        	</span>
    	</th>
    	<td>
    		<span class="textbox">
	    		<jecs:list name="pickup" id="pickup" onchange="changePickup()" styleClass="textbox-text"
	    			listCode="po.shipment.type" value="${jpoMemberOrder.pickup}" defaultValue="0"/>
    		</span>
    	</td>

    	<th>
    		<span class="text-font-style-tc"><jecs:label  key="fiPayData.createrName"/></span>
    	</th>
    	<td>
			<span class="textbox"> ${jpoMemberOrder.orderUserCode }</span>
	    </td>
    </tr>
	<tr>
		<th></th>
		<td>
			<c:if test="${not empty jmiAddrBooks }">
    		<select name="jmiAddrBook" id="jmiAddrBook" onchange="jmiAddrBookFill(this.selectedIndex);">
    		<option></option>
    		<c:forEach items="${jmiAddrBooks }" var="jmiAddrBook" varStatus="status">
    			<c:if test="${status.count-1==0 }">
	    			<script>
	    			var jmiAddrBook = Array();
	    			</script>
				</c:if>
					<script>
    					jmiAddrBook[${status.count}] = {
    									firstName:"${jmiAddrBook.firstName}",
    									lastName:"${jmiAddrBook.lastName}",
    									province:"${jmiAddrBook.province}",
    									city:"${jmiAddrBook.city}",
    									district:"${jmiAddrBook.district}",
    									address:"${jmiAddrBook.address}",
									    <c:if test="${sessionScope.sessionLogin.companyCode=='JP'}">
    									building:"${jmiAddrBook.building}",
									    </c:if>
    									postalcode:"${jmiAddrBook.postalcode}",
    									phone:"${jmiAddrBook.phone}",
    									email:"${jmiAddrBook.email}",
    									mobiletele:"${jmiAddrBook.mobiletele}"
    								  };
		    		</script>
			    		<option>${jmiAddrBook.firstName},${jmiAddrBook.lastName},${jmiAddrBook.postalcode},${jmiAddrBook.address}</option>
		    		</c:forEach>
    			</select>
			</c:if>
			<c:if test="${empty jmiAddrBooks }">
		 		<a href='jmiAddrBooks.html' style="color:red;"><jecs:locale key="addrbook.tip"/></a>&nbsp;
			</c:if>
		</td>
		<th></th><td></td>
	</tr>
    <tr>
	    <th>
	    	<span class="text-font-style-tc">
	        	<jecs:label  key="shipping.firstName" required="true"/>
	        </span>
	    </th>
	    <td >
	    	<span class="textbox">
	        	<form:input path="firstName" id="firstName" cssClass="textbox-text"/>
	        </span>
	    </td>

    	<th>
        	<span class="text-font-style-tc"><jecs:label  key="shipping.lastName" required="true"/></span>
    	</th>
	    <td>
	        <span class="textbox"><form:input path="lastName" id="lastName" cssClass="textbox-text"/></span>
	    </td>
	</tr>

    <tr>
    	<th>
    		<span class="text-font-style-tc">
	   			<jecs:label  key="shipping.province" required="true"/>
	   		</span>
	   	</th>
    	<td >
    		<span class="textbox">
		        <form:select path="province"  cssClass="textbox-text" onchange="getCity()">
					<form:option label="" value=""><jecs:locale key='list.please.select'/></form:option>
		            <form:options items="${alStateProvinces}" itemValue="stateProvinceId" itemLabel="stateProvinceName" />
				</form:select>
			</span>
    	</td>
    	<th>
	   		<span class="text-font-style-tc">
	    		<jecs:label  key="shipping.city" required="true"/>
	    	</span>
   	 	</th>
    	<td >
    		<span class="textbox">
		        <select name="city" id="city" onchange="getDistrict()" class="textbox-text"> 
					<option value=""><jecs:locale key="list.please.select"/></option>
				</select>
			</span>
    	</td>
    </tr>

    <tr>
    	<th>
    		<span class="text-font-style-tc">
		  		<jecs:label  key="shipping.district" required="true"/>
		    </span>
    	</th>
	    <td >
	    	<span class="textbox">
	        <select name="district" id="district" class="textbox-text">
				<option value=""><jecs:locale key="list.please.select"/></option>
			</select>
			</span>	
	    </td>
	    <th>
	    	<span class="text-font-style-tc">
        		<jecs:label  key="shipping.address" required="true"/>
        	</span>
    	</th>
    	<td>
        	<span class="textbox"><form:input path="address" id="address" cssClass="textbox-text"/></span>
    	</td>
    </tr>
    <tr>
      
       <td colspan="4" align="center">
          <span style="color:red;"><jecs:locale key="setUp.tip"/></span>
        </td>
    </tr>
   	<tr>
   		<th>
   			<span class="text-font-style-tc">
        		<jecs:label  key="shipping.postalcode" required="true"/>
        	</span>
    	</th>
	    <td >
	        <span class="textbox"><form:input path="postalcode" id="postalcode" cssClass="textbox-text"/></span>
	    </td>
	    <th>
	    	<span class="text-font-style-tc">
        	<jecs:label  key="miMember.phone" required="true"/>
        	</span>
    	</th>
	    <td >
	        <span class="textbox"><form:input path="phone" id="phone" cssClass="textbox-text"/></span>
	    </td>
	</tr>

    <tr>
	    <th>
	    	<span class="text-font-style-tc">
	        	<jecs:label  key="miMember.mobiletele" required="true"/>
	       	</span>
	    </th>
	    <td >
	    	<span class="textbox">
	        <form:input path="mobiletele" id="mobiletele" cssClass="textbox-text"/>
	        <jecs:locale key="miMember.mobile.phone.remark" />
	    	</span>
	    </td>
	    <th>
	    	<span class="text-font-style-tc">
        		<jecs:label  key="miMember.email"/>
        	</span>
    	</th>
	    <td >
	        <span class="textbox"><form:input path="email" id="email" cssClass="textbox-text"/></span>
	    </td>
	</tr>


   


</table>



<div id="wrap">
<div id="body">	
<div id="main" style="overflow:hidden;">
<div id="tabCot_product" class="tab">
<div class="tabContainer">
	<ul class="tabHead" id="tabCot_product-li-currentBtn-">
	<c:forEach items="${jpmProductSales}" var="pm" varStatus="status">
	<c:if test="${status.count==1}">
	<li class="currentBtn">
	</c:if>
	<c:if test="${status.count!=1}">
	<li class="">
	</c:if>
	<a href="javascript:void(0)"><jecs:code listCode="pmproduct.productcategory" value="${pm.key}"/></a></li>
 </c:forEach>
		
	</ul>
</div>


<c:forEach  items="${jpmProductSales}" var="pms" varStatus="sta">
     <c:if test="${sta.count==1}">
<div id="tabCot_product_${sta.count}"  class="tabCot" />
</c:if>
<c:if test="${sta.count!=1}">
     <div id="tabCot_product_${sta.count}" class="tabCot"  style="display: none;">
</c:if>

        <table>
        <tr  style="background-color:#E6E6E6;font-size: 9pt;vertical-align: top;"><td>  <jecs:locale  key="busi.product.pic"/></td><td>  <jecs:locale  key="busi.product.productno"/></td><td>  <jecs:locale  key="pmProduct.productName"/></td><td>  <jecs:locale  key="pd.price"/></td><td>  <jecs:locale  key="busi.direct.pv"/></td><td>  <jecs:locale  key="poOrder.count"/></td></tr>
          <c:forEach  items="${pms.value}"  var="item"  varStatus='status'>
         <c:if test='${status.count%2==0}'> 
         <tr class="even" >
         </c:if>
         <c:if test='${status.count%2!=0}'>
            <tr  class="add">
        </c:if>
        <td></td>
        <td>${item.jpmProductSaleNew.jpmProduct.productNo}</td>
        <td>${item.jpmProductSaleNew.productName}
           <c:if test="${item.jpmProductSaleNew.hotFlag=='1'}">
           <img src="images/hot.gif"/>
       </c:if>
        </td>
        <td>${item.price}</td>
          <td>${item.pv}</td>
          <td>
            <input type='text' class='textbox-text' name='qty' value='0' size='3' onchange="set_amt(this,'${item.pttId }','${item.jpmProductSaleNew.jpmProduct.productNo}');" id=qty<c:out value="${item.pttId }" />>
				    <input type="hidden" name="g_no" id="g_no[<c:out value="${item.pttId }" />]" value="${item.pttId }" />
				    <script>
						aSub[<c:out value="${item.pttId }" />] = Array();
						aSub[<c:out value="${item.pttId }" />]['g_no']='<c:out value="${item.pttId }" />';
						aSub[<c:out value="${item.pttId }" />]['qty']='0';
						aSub[<c:out value="${item.pttId }" />]['price']='<c:out value="${item.price }" />';
						aSub[<c:out value="${item.pttId }" />]['pv']='<c:out value="${item.pv }" />';
					</script>
          </td>
          
        </tr>
          </c:forEach>
	  
        </table>
        </div>
    
</c:forEach>
<div  class="tabCotF">
<table>
<tr class="even" >
	<td ></td>
    <td ></td>

	<td ><b><jecs:locale key="poOrder.amtCount"/></b></td>
	<td >
		<b><span id='total_price'>0</span>&nbsp;(<jecs:locale key="pd.price"/>)</b>
			
	</td>
	<td >
		<b><span id='total_pv'>0</span>&nbsp;(<jecs:locale key="busi.direct.pv"/>)</b>

	</td>
	<td>
		<b><span id='total_qty'>0</span>&nbsp;(<jecs:locale key="poOrder.count"/>)</b>
	</td>
</tr>
		</table>
		</td>
	</tr>

</table>
</div>


</div>
</div>
<div class="clear"></div>	
</div>
</div>
<div class="noprint">	
<script src="./scripts/tab.js"></script>
</div>
<div class="sidebar">
</div>
</div>
</form:form>

<script>
    <c:forEach  var="pol" items="${jpoMemberOrder.jpoMemberOrderList}" varStatus="status">
	$('qty<c:out value="${pol.jpmProductSaleTeamType.pttId }" />').value='<c:out value="${pol.qty }" />';
	aSub[<c:out value="${pol.jpmProductSaleTeamType.pttId }" />]['qty']='<c:out value="${pol.qty }" />';
	</c:forEach>
	amt_count();
</script>
<script>
  var tmpSelect = '';
	function getCity(){
		var cityElemment=document.getElementById('city');
		cityElemment.options.length=0;
		cityElemment.value='';
		var o=new Option("<jecs:locale key="list.please.select"/>","");
		cityElemment.options.add(o);
		<c:if test="${sessionScope.sessionLogin.companyCode!='JP'}"> 
		var districtElemment=document.getElementById('district');
    	districtElemment.options.length=0;
		districtElemment.value='';
		var o=new Option("<jecs:locale key="list.please.select"/>","");
		districtElemment.options.add(o);  
		</c:if>
		<c:if test="${sessionScope.sessionLogin.companyCode=='PH'}"> 
		var townElemment=document.getElementById('town');
    	townElemment.options.length=0;
		townElemment.value='';
		var o=new Option("<jecs:locale key="list.please.select"/>","");
		townElemment.options.add(o);  
		</c:if>
		
		var province=document.getElementById('province').value;
		alCityManager.getAlCityByProvinceId(province,callBackCity);
	}
	function callBackCity(valid){
		var cityElemment=document.getElementById('city');
		cityElemment.options.length=0;
		cityElemment.value='';
    	var o=new Option("<jecs:locale key="list.please.select"/>","");
		cityElemment.options.add(o);  
		for (var i=0;i<valid.length;i++) {
			var cityId= valid[i].cityId;	   
			var cityName=valid[i].cityName;   
			var o=new Option(cityName,cityId);
			if(cityId==20003846 || cityId==20003918 || cityId==20003925){
			
			}else{
				cityElemment.options.add(o);
			}
		}
		var cityElemment=$('city');
		for (var i=0;i<cityElemment.options.length;i++) {
			if(tmpSelect=='1'){
				if (cityElemment.options[i].value == jmiAddrBook[$('jmiAddrBook').selectedIndex].city) {
					cityElemment.options[i].selected=true;
					break;        
				}
			}else{
				if (cityElemment.options[i].value == '${jpoMemberOrder.city }') {
					cityElemment.options[i].selected=true;
					break;        
				}
			}
		}
		getDistrict();
	}
	function getDistrict(){
<c:if test="${sessionScope.sessionLogin.companyCode!='JP'}">
		var districtElemment=document.getElementById('district');
    	districtElemment.options.length=0;
		districtElemment.value='';
		var o=new Option("<jecs:locale key="list.please.select"/>","");
		districtElemment.options.add(o);  
		
		var city=document.getElementById('city').value;
		alDistrictManager.getAlDistrictByCityId(city,callBackDistrict);
</c:if>
	}
	function callBackDistrict(valid){
		var districtElemment=document.getElementById('district');
    	districtElemment.options.length=0;
		districtElemment.value='';
		var o=new Option("<jecs:locale key="list.please.select"/>","");
		districtElemment.options.add(o);  
		for (var i=0;i<valid.length;i++) {
			var districtId= valid[i].districtId;	   
			var districtName=valid[i].districtName;   
			var o=new Option(districtName,districtId);
			if(districtId==20003889 || districtId==20003876){
			
			}else{
				districtElemment.options.add(o);
			}
		}
		var districtElemment=$('district');
			for (var i=0;i<districtElemment.options.length;i++) {
				if(tmpSelect=='1'){
					if (districtElemment.options[i].value == jmiAddrBook[$('jmiAddrBook').selectedIndex].district ) {  
						districtElemment.options[i].selected=true;
						break;        
					}
				}else{
					if (districtElemment.options[i].value == '${jpoMemberOrder.district }' ) {  
						districtElemment.options[i].selected=true;
						break;        
					}
				}
			}
    <c:if test="${sessionScope.sessionLogin.companyCode=='PH'}">
		getTown();
	</c:if>
	}
	function getTown(){
		var townElemment=document.getElementById('town');
    	townElemment.options.length=0;
		townElemment.value='';
		var o=new Option("<jecs:locale key="list.please.select"/>","");
		townElemment.options.add(o);  
		
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
		}
		var townElemment=$('town');
			for (var i=0;i<townElemment.options.length;i++) {
				if(tmpSelect=='1'){
					if (townElemment.options[i].value == jmiAddrBook[$('jmiAddrBook').selectedIndex].town ) {  
						townElemment.options[i].selected=true;
						break;        
					}
				}else{
					if (townElemment.options[i].value == '${jpoMemberOrder.town }' ) {  
						townElemment.options[i].selected=true;
						break;        
					}
				}
			}
	}
	<c:if test="${not empty jpoMemberOrder.province }">
		getCity();
	</c:if>

function changePickup(){
	if ($('pickup').value == '1'){
		for(var i = 2 ; i < 13; i++){
			$('tableId').rows[$('tableId').rows.length - i].style.display = "none";
		}
	}else{
		for(var i = 2 ; i < 13; i++){
			$('tableId').rows[$('tableId').rows.length - i].style.display = "";
		}
	}
}
changePickup();
</script>
<v:javascript formName="jpoMemberOrder" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script  src="<c:url value="/scripts/validator.jsp"/>"></script>
