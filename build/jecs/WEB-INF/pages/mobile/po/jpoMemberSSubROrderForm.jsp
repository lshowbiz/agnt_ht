<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<meta name="viewport" content="width=device-width, initial-scale=1"> 
<meta name="decorator" content="mobile"/>

<title><jecs:locale key="jpoMemberOrderDetail.title"/></title>
<content tag="heading"><jecs:locale key="jpoMemberOrderDetail.heading"/></content>
<script type="text/javascript" src="<c:url value='/dwr/util.js'/>" ></script> 
<script type="text/javascript" src="<c:url value='/dwr/engine.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/dwr/interface/alCityManager.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/dwr/interface/alDistrictManager.js'/>" ></script>
<link rel="stylesheet" href="./css/user.css" type="text/css"></link>
<LINK href="load_images/WebResource.css" type=text/css rel=stylesheet>
<SCRIPT src="load_images/prototype.js" type=text/javascript></SCRIPT>
<SCRIPT src="load_images/load_pic.js" type=text/javascript></SCRIPT>
<SCRIPT type=text/javascript>
var objPeerTip=new PeerTip();
Close_PIC=function(Name)
{
	var iobj=eval(Name);
	var span_obj=eval(Name);	
	span_obj.style.display="none";	
}
</SCRIPT>
<style>
	.ui-btn-innerA {
		width: 50px;
	}
	
	li {
		width: 120px;
		height: 34px;
	    list-style: none;
	}
	
	#body .tabContainer li.currentBtn a {
	    height: 1px;
	}
	#wrap, #main {
		width: 320px;
	}
	table {
  		border-collapse:collapse;
  	}
  	#tableId th {
  		text-align: right;
  	}
  	
  	.ui-checkbox {
  		width: 170px;
  	}

</style>
<c:set var="buttons">

<c:if test='${empty jpoMemberOrder.moId}'>
<jecs:power powerCode="addPoMemberSSubROrder">
				<input data-inline="true" type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="operation.button.save"/>" />
</jecs:power>
</c:if>

<jecs:power powerCode="addPoMemberSSubROrderLC">
<c:if test='${empty jpoMemberOrder.moId}'>
	<input data-inline="true" type="button" class="button" name="addLC"  
	onclick="window.location.href='editJpoMemberSSubROrderLC.html?strAction=addPoMemberSSubROrderLC'" 
	value="兰超" />
</c:if>
</jecs:power>
<c:if test='${jpoMemberOrder.status=="1" && jpoMemberOrder.submitStatus =="1" && not empty jpoMemberOrder.moId}'>
<jecs:power powerCode="editPoMemberSSubROrder">
				<input data-inline="true" type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="operation.button.save"/>" />
</jecs:power>
<jecs:power powerCode="deletePoMemberSSubROrder">
				<input data-inline="true" type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'PoMemberSSubROrder')" value="<jecs:locale key="operation.button.delete"/>" />
</jecs:power>
</c:if>

<input data-inline="true" type="button" class="button" name="back"  onclick="window.location.href='jpoMemberSSubROrders.html?needReload=1'" value="<jecs:locale key="operation.button.return"/>" />

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
  function set_amt(nQty,no)
  {	
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
	$('address').value = jmiAddrBook[selectIndex].address;
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
<form:form commandName="jpoMemberOrder" method="post" action="editJpoMemberSSubROrder.html" onsubmit="return onSubmitCheck(this);" id="jpoMemberOrderForm">

<input type="hidden" name="strAction" value="${param.strAction }"/>
	
<table class='detail' id="tableId" width="100%">

<form:hidden path="moId"/>

<c:if test='${param.strAction=="editPoMemberSSubROrder"}'>
    <tr><th>
        <jecs:label  key="poMemberOrder.memberOrderNo"/>
    </th>
    <td align="left">
    	${jpoMemberOrder.memberOrderNo }
    </td></tr>
</c:if>

    <tr><th>
        <jecs:label  key="miMember.memberNo"/>
    </th>
    <td align="left">
    	<c:if test='${param.strAction=="editPoMemberSSubROrder"}'>
    		${jpoMemberOrder.sysUser.userCode }
    	</c:if>
    	<c:if test='${param.strAction=="addPoMemberSSubROrder"}'>
        	<c:if test="${sessionLogin.userType=='M' }">
        		${jpoMemberOrder.sysUser.userCode }
        	</c:if>
        	<c:if test="${sessionLogin.userType=='C' }">
        		<form:input path="sysUser.userCode" id="sysUser.userCode" cssClass="text medium"/>
        		<img src="<c:url value="/images/l_1.gif"/>" id="person"  onclick="searchMiMember();" style="cursor: pointer;" title="<jecs:locale key="operation.button.search"/>"/> 
	    		${jpoMemberOrder.sysUser.userName}
        	</c:if>
    	</c:if>
    </td></tr>

    <tr><th>
        <jecs:label  key="poMemberOrder.orderType"/>
    </th>
    <td align="left">
    	<c:if test='${param.strAction=="editPoMemberSSubROrder"}'>
    		<jecs:code listCode="po.s.order_type" value="${jpoMemberOrder.orderType}"/>
    	</c:if>
    	<c:if test='${param.strAction=="addPoMemberSSubROrder"}'>
        	<jecs:code listCode="po.s.order_type" value="${jpoMemberOrder.orderType}"/>
    	</c:if>
    </td></tr>

<c:if test='${param.strAction=="editPoMemberSSubROrder"}'>
    <tr><th>
        <jecs:label  key="bonus.order.amount"/>
    </th>
    <td align="left">
        ${jpoMemberOrder.amount }
    </td></tr>

    <tr><th>
        <jecs:label  key="poMemberOrder.pvAmt"/>
    </th>
    <td align="left">
        ${jpoMemberOrder.pvAmt }
    </td></tr>
</c:if>
<%--
    <tr><th>
        <jecs:label  key="busi.order.payMode"/>
    </th>
    <td align="left">
    	<c:if test='${param.strAction=="editPoMemberSSubROrder"}'>
    		<jecs:code listCode="po.paymode" value="${jpoMemberOrder.payMode}"/>
    	</c:if>
    	<c:if test='${param.strAction=="addPoMemberSSubROrder"}'>
        	<jecs:list name="payMode" listCode="po.paymode" value="${jpoMemberOrder.payMode}" defaultValue="5"/>
    	</c:if>
    </td></tr>
--%>

    <tr><th>
        <jecs:label  key="po.shipment.type"/>
    </th>
    <td align="left">
    	<jecs:list name="pickup" id="pickup" onchange="changePickup()" listCode="po.shipment.type" value="${jpoMemberOrder.pickup}" defaultValue="0"/>
    </td></tr>
    
    <tr><th>&nbsp;
        
    </th>
    <td align="left">
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
	&nbsp;
</c:if>
    </td></tr>

    <tr><th>
        <jecs:label  key="shipping.firstName"/>
    </th>
    <td align="left">
        <form:input path="firstName" id="firstName" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="shipping.lastName"/>
    </th>
    <td align="left">
        <form:input path="lastName" id="lastName" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="shipping.province"/>
    </th>
    <td align="left">
        <form:select path="province"  cssClass="text medium" onchange="getCity()">
					<form:option label="" value=""><jecs:locale key='list.please.select'/></form:option>
		            <form:options items="${alStateProvinces}" itemValue="stateProvinceId" itemLabel="stateProvinceName" />
		</form:select>
    </td></tr>

    <tr><th>
        <jecs:label  key="shipping.city"/>
    </th>
    <td align="left">
        <select name="city" id="city" onchange="getDistrict()">
			<option value=""><jecs:locale key="list.please.select"/></option>
		</select>
    </td></tr>

    <tr><th>
        <jecs:label  key="shipping.district"/>
    </th>
    <td align="left">
        <select name="district" id="district" >
			<option value=""><jecs:locale key="list.please.select"/></option>
		</select>	
    </td></tr>

    <tr><th>
        <jecs:label  key="shipping.address"/>
    </th>
    <td align="left">
        <form:input path="address" id="address" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="shipping.postalcode"/>
    </th>
    <td align="left">
        <form:input path="postalcode" id="postalcode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="miMember.phone"/>
    </th>
    <td align="left">
        <form:input path="phone" id="phone" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="miMember.mobiletele"/>
    </th>
    <td align="left">
        <form:input path="mobiletele" id="mobiletele" cssClass="text medium"/><jecs:locale key="miMember.mobile.phone.remark" />
    </td></tr>

    <tr><th>
        <jecs:label  key="miMember.email"/>
    </th>
    <td align="left">
        <form:input path="email" id="email" cssClass="text medium"/>
    </td></tr>

<c:if test="${sessionLogin.userType=='C' }">
    <tr><th>
        <jecs:label  key="fiPayData.createrName"/>
    </th>
    <td align="left">
        ${jpoMemberOrder.orderUserCode }
    </td></tr>
</c:if>

<c:if test="${sessionLogin.userType=='M' }">
    <tr><th>
         条款:
    </th>
    <td align="left">
    	<c:if test="${empty jpoMemberOrder.moId }">
    		<label for="agree"><jecs:locale key="jpoMemberOrder.agree.msg"/></label>
    		<input type="checkbox" name="agree" id="agree" />
    	</c:if>
    	<c:if test="${not empty jpoMemberOrder.moId }">
    		<label for="agree"><jecs:locale key="jpoMemberOrder.agree.msg"/></label>
    		<input type="checkbox" name="agree" id="agree" checked/>
    	</c:if>
    </td></tr>
</c:if>

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
               <tr  style="background-color:#E6E6E6;font-size: 9pt;vertical-align: top;">
               <td>  <jecs:locale  key="pmProduct.productName"/></td>
               <td>  <jecs:locale  key="pd.price"/></td>
               <td>  <jecs:locale  key="busi.direct.pv"/></td>
               <td>  <jecs:locale  key="poOrder.count"/></td></tr>
          <c:forEach  items="${pms.value}"  var="item"  varStatus='status'>
         <c:if test='${status.count%2==0}'> 
         <tr class="even" >
         </c:if>
         <c:if test='${status.count%2!=0}'>
            <tr  class="add">
        </c:if>
        <td>${item.jpmProduct.productNo}<br>${item.productName}</td>
        <td>${item.substoreMpPrice}</td>
          <td>${item.substoreMpPv}</td>
          <td align="left">
            <input type='text' class='text medium' name='qty' value='0' size='3' onchange="set_amt(this,'${item.uniNo }');" id=qty<c:out value="${item.uniNo }" />>
				    <input type="hidden" name="g_no" id="g_no[<c:out value="${item.uniNo }" />]" value="${item.uniNo }" />
				    <script>
							aSub[<c:out value="${item.uniNo }" />] = Array();
						aSub[<c:out value="${item.uniNo }" />]['g_no']='<c:out value="${item.uniNo }" />';
						aSub[<c:out value="${item.uniNo }" />]['qty']='0';
						aSub[<c:out value="${item.uniNo }" />]['price']='<c:out value="${item.substoreMpPrice }" />';
						aSub[<c:out value="${item.uniNo }" />]['pv']='<c:out value="${item.substoreMpPv }" />';
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
<script type="text/javascript" src="./scripts/tab.js"></script>
</div>
<div class="sidebar">
</div>
	
	<div class="searchBar">
			<c:out value="${buttons}" escapeXml="false"/>
	</div>
</div>
</form:form>

<script type="text/javascript">
    <c:forEach  var="pol" items="${jpoMemberOrder.jpoMemberOrderList}" varStatus="status">
	$('qty<c:out value="${pol.jpmProductSale.uniNo }" />').value='<c:out value="${pol.qty }" />';
	aSub[<c:out value="${pol.jpmProductSale.uniNo }" />]['qty']='<c:out value="${pol.qty }" />';
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
		var districtElemment=document.getElementById('district');
    	districtElemment.options.length=0;
		districtElemment.value='';
		var o=new Option("<jecs:locale key="list.please.select"/>","");
		districtElemment.options.add(o);  
		
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
		var districtElemment=document.getElementById('district');
    	districtElemment.options.length=0;
		districtElemment.value='';
		var o=new Option("<jecs:locale key="list.please.select"/>","");
		districtElemment.options.add(o);  
		
		var city=document.getElementById('city').value;
		alDistrictManager.getAlDistrictByCityId(city,callBackDistrict);
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
<%-- <v:javascript formName="jpoMemberOrder" cdata="false" dynamicJavascript="true" staticJavascript="false"/> --%>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
