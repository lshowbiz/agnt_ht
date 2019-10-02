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
<jecs:power powerCode="addPoMemberFOrder">
				<button type="submit" class="btn btn_sele" name="save"  onclick="bCancel=false" style="font-size:14px;height:30px;">
					 <jecs:locale key="operation.button.save"/>
				</button>
</jecs:power>
</c:if>

<c:if test='${jpoMemberOrder.status=="1" && jpoMemberOrder.submitStatus =="1" && not empty jpoMemberOrder.moId}'>
<jecs:power powerCode="editPoMemberFOrder">
				<button type="submit" class="btn btn_sele" name="save"  onclick="bCancel=false" style="font-size:14px;height:30px;" >
					<jecs:locale key="operation.button.save"/>
				</button>
				
</jecs:power>
<jecs:power powerCode="deletePoMemberFOrder">
				<button type="submit" class="btn btn_sele" name="delete" style="font-size:14px;height:30px;" onclick="bCancel=true;return confirmDelete(this.form,'PoMemberFOrder');" >
					<jecs:locale key="operation.button.delete"/>
				</button>
</jecs:power>
</c:if>

<button type="button" class="btn btn_sele" name="back"  onclick="history.back()" style="font-size:14px;height:30px;">
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
	var userCode = $('sysUser.userCode').value;
	window.location.href="editJpoMemberFOrder.html?strAction=addPoMemberFOrder&sysUser.userCode="+userCode;
}
</script>

<script>
  var aSub = Array();

  function set_amt(nQty,no,productNo,typeV)
  {	
    if(productNo=="P01170300101CN0")
	  {
			if(nQty.value>0)
			{
			   alert("<jecs:locale key="product.notEnough.tip"/>");
			}
	  }
	 
	   if(${effectiveValid})//不强制购买事业锦囊
        {
        
         if(productNo=="P08520100101CN0")//事业锦囊
         { 
        
           if(nQty.value>0)
            {
              
               $('buyS').value="trueS";//购买了事业锦囊
           
            }else
            {
           
                 $('buyS').value="falseS";//没有购买事业锦囊
            }
         }
       
         if(typeV=='2')
         {
               $('buyF').value="trueF";//购买了辅销品

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

function checkCardType(){
<c:if test="${sessionScope.sessionLogin.companyCode=='CN'}">
	var str1 = "<jecs:locale key="jpoMemberOrder.forder1"/>";
	var str2 = "<jecs:locale key="jpoMemberOrder.forder2"/>";
	str1 = str1.replace('{0}',$('total_price').innerText);
	str1 = str1.replace('{1}',$('total_pv').innerText);
	if($('total_pv').innerText>=2750){
		str1 = str1.replace('{2}','<jecs:locale key="bd.cardType6"/>');
		return confirm(str1);
	}
	if($('total_pv').innerText>=1400){
		str1 = str1.replace('{2}','<jecs:locale key="bd.cardType4"/>');
		str2 = str2.replace('{0}','<jecs:locale key="bd.cardType6"/>');
		str2 = str2.replace('{1}',2750-$('total_pv').innerText);
		return confirm(str1 + str2);
	}
	if($('total_pv').innerText>=700){
		str1 = str1.replace('{2}','<jecs:locale key="bd.cardType3"/>');
		str2 = str2.replace('{0}','<jecs:locale key="bd.cardType4"/>');
		str2 = str2.replace('{1}',1400-$('total_pv').innerText);
		return confirm(str1 + str2);
	}
	if($('total_pv').innerText>=350){
		str1 = str1.replace('{2}','<jecs:locale key="bd.cardType2"/>');
		str2 = str2.replace('{0}','<jecs:locale key="bd.cardType3"/>');
		str2 = str2.replace('{1}',700-$('total_pv').innerText);
		return confirm(str1 + str2);
	}
	if($('total_pv').innerText>=70){
		str1 = str1.replace('{2}','<jecs:locale key="bd.cardType5"/>');
		str2 = str2.replace('{0}','<jecs:locale key="bd.cardType2"/>');
		str2 = str2.replace('{1}',350-$('total_pv').innerText);
		return confirm(str1 + str2);
	}
</c:if>
	return true;
}

function onSubmitCheck(formId){
  
	if($('pickup').value == '1'){
	<c:if test="${empty jpoMemberOrder.moId && sessionLogin.userType=='M' }">
	
		if($('agree').checked==false){
		
			alert('<jecs:locale key="jpoMemberOrder.mustAgree"/>');
			return false;
		}
	</c:if>
	
		/* if(checkCardType()==false){
			return false;
		}
		
		if(checkValue()==false){
			return false;
		} */
	
		if(isFormPosted()){
			return true;
		}else{
			return false;
		}
	}
	if(validateJpoMemberOrder(formId)){
	<c:if test="${empty jpoMemberOrder.moId && sessionLogin.userType=='M'}">
		if($('agree').checked==false){
			alert('<jecs:locale key="jpoMemberOrder.mustAgree"/>');
			return false;
		}
	</c:if>
	
		/* if(checkCardType()==false){
			return false;
		}
		
		if(checkValue()==false){
			return false;
		} */
	
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

<form:form commandName="jpoMemberOrder" method="post" action="editJpoMemberFOrder.html" onsubmit="return onSubmitCheck(this);" id="jpoMemberOrderForm">
<input type="hidden" name="strAction" value="${param.strAction }"/>
<input type="hidden" name="buyF"  id="buyF" value="falseF"/>
<input type="hidden" name="buyS"  id="buyS" value="falseS"/>
<input type="hidden" name="effectiveValid" id="effectiveValid"  value="${effectiveValid}"/>
	<div class="searchBar">
	
			<c:out value="${buttons}" escapeXml="false"/>
	</div>

<table class='detail' width="70%" >
  <tbody class="window" >
   
<form:hidden path="moId"/>

<c:if test='${param.strAction=="editPoMemberFOrder"}'>
     <tr class="edit_tr">
     <th><span class="text-font-style-tc">
        <jecs:label  key="poMemberOrder.memberOrderNo" required="true"/>
    </span></th>
    <td><span class="textbox">
    	${jpoMemberOrder.memberOrderNo }
    </span></td></tr>
</c:if>

 <tr class="edit_tr">
    <th><span class="text-font-style-tc">
        <jecs:label  key="miMember.memberNo" required="true"/></span></th>
    <td><span class="textbox">
    	<c:if test='${param.strAction=="editPoMemberFOrder"}'>
    		${jpoMemberOrder.sysUser.userCode }
    	</c:if>
    	<c:if test='${param.strAction=="addPoMemberFOrder"}'>
        	<c:if test="${sessionLogin.userType=='M' }">
        		${jpoMemberOrder.sysUser.userCode }
        	</c:if>
        	<c:if test="${sessionLogin.userType=='C' }">
        		<form:input cssClass="textbox-text" path="sysUser.userCode" id="sysUser.userCode" />
        	</c:if>
    	</c:if>
    </span>
    		<img src="<c:url value="/images/l_1.gif"/>" id="person"  onclick="searchMiMember();" style="cursor: pointer;" title="<jecs:locale key="operation.button.search"/>"/> 
	    	${jpoMemberOrder.sysUser.userName}
    </td>

     <th><span class="text-font-style-tc">
        <jecs:label  key="poMemberOrder.orderType"/>
   	 </span></th>
    <td><span class="textbox">
    	<c:if test='${param.strAction=="editPoMemberFOrder"}'>
    		<jecs:code listCode="po.order_type" value="${jpoMemberOrder.orderType}"/>
    	</c:if>
    	<c:if test='${param.strAction=="addPoMemberFOrder"}'>
        	<jecs:code listCode="po.order_type" value="${jpoMemberOrder.orderType}"/>
    	</c:if>
    </span></td></tr>

<c:if test='${param.strAction=="editPoMemberFOrder"}'>
     <tr class="edit_tr">
     <th><span class="text-font-style-tc">
        <jecs:label  key="bonus.order.amount"/>
    </span></th>
    <td><span class="textbox">
        ${jpoMemberOrder.amount }
    </span></td>

	<th><span class="text-font-style-tc">
        <jecs:label  key="poMemberOrder.pvAmt"/>
    </span></th>
    <td><span class="textbox">
        ${jpoMemberOrder.pvAmt }
    </span></td></tr>
</c:if>

     <tr class="edit_tr">
     <th><span class="text-font-style-tc">
        <jecs:label  key="po.shipment.type"/>
    </span></th>
    <td><span class="textbox">
    	<jecs:list styleClass="textbox-text" name="pickup" id="pickup" onchange="changePickup()" listCode="po.shipment.type" value="${jpoMemberOrder.pickup}" defaultValue="0"/>
    </span></td>
	<c:if test="${sessionLogin.userType=='C' }">
   
    <th><span class="text-font-style-tc">
        <jecs:label  key="fiPayData.createrName"/>
    </span></th>
    <td><span class="textbox">
        ${jpoMemberOrder.orderUserCode }
    </span></td>
	</c:if>
</tr>

<tr>
	<th>&nbsp;</th>
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
    									town:"${jmiAddrBook.town}",
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
          <a href='jmiAddrBooks.html' style="color:red;"><jecs:locale key="addrbook.tip"/></a>
	      &nbsp;
</c:if>
   </td></tr>

    <tr class="edit_tr">
    <th><span class="text-font-style-tc">
        <jecs:label  key="shipping.firstName" required="true"/>
    </span></th>
    <td><span class="textbox">
        <form:input cssClass="textbox-text" path="firstName" id="firstName" />
    </span></td>
 
 	<th><span class="text-font-style-tc">
        <jecs:label  key="shipping.lastName" required="true"/>
    </span></th>
    <td><span class="textbox">
        <form:input cssClass="textbox-text" path="lastName" id="lastName" />
    </span></td></tr>

    <tr class="edit_tr">
    <th><span class="text-font-style-tc">
	    <c:if test="${sessionScope.sessionLogin.companyCode=='PH'}">
	        <jecs:label  key="shipping.island" required="true"/>
	    </c:if>
	    <c:if test="${sessionScope.sessionLogin.companyCode!='PH'}">
	        <jecs:label  key="shipping.province" required="true"/>
	    </c:if>
    </span></th>
    <td><span class="textbox">
        <form:select cssClass="textbox-text" path="province"   onchange="getCity()">
					<form:option label="" value=""><jecs:locale key='list.please.select'/></form:option>
		            <form:options items="${alStateProvinces}" itemValue="stateProvinceId" itemLabel="stateProvinceName" />
		</form:select>
    </span></td>

	<th><span class="text-font-style-tc">
	    <c:if test="${sessionScope.sessionLogin.companyCode=='PH'}">
	        <jecs:label  key="shipping.region" required="true"/>
	    </c:if>
	    <c:if test="${sessionScope.sessionLogin.companyCode!='PH'}">
	        <jecs:label  key="shipping.city" required="true"/>
	    </c:if>
    </span></th>
    <td><span class="textbox">
        <select class="textbox-text" name="city" id="city" onchange="getDistrict()">
			<option value=""><jecs:locale key="list.please.select"/></option>
		</select>
    </span></td></tr>
<%-- 
<c:if test="${sessionScope.sessionLogin.companyCode!='JP'}">
    <tr><th><span class="text-font-style-tc">
	    <c:if test="${sessionScope.sessionLogin.companyCode=='PH'}">
	        <jecs:label  key="shipping.province" required="true"/>
	    </c:if>
	    <c:if test="${sessionScope.sessionLogin.companyCode!='PH'}">
	        <jecs:label  key="shipping.district" required="true"/>
	    </c:if>
    </span></th>
    <td><span class="textbox">
        <select class="textbox-text" name="district" id="district" <c:if test="${sessionScope.sessionLogin.companyCode=='PH'}">onchange="getTown()"</c:if>>
			<option value=""><jecs:locale key="list.please.select"/></option>
		</select>	
    </span></td></tr>
</c:if>
 --%>
    <c:if test="${sessionScope.sessionLogin.companyCode=='PH'}">
    <tr><th><span class="text-font-style-tc">
	       <jecs:label  key="shipping.city" required="true"/>
    </span></th>
    <td><span class="textbox">
        <select name="town" id="town" >
			<option value=""><jecs:locale key="list.please.select"/></option>
		</select>	
    </span></td></tr>
    </c:if>

    <tr class="edit_tr">
    <th><span class="text-font-style-tc">
	        <jecs:label  key="shipping.district" required="true"/>
    </span></th>
    <td><span class="textbox">
        <select class="textbox-text" name="district" id="district" <c:if test="${sessionScope.sessionLogin.companyCode=='PH'}">onchange="getTown()"</c:if>>
			<option value=""><jecs:locale key="list.please.select"/></option>
		</select>	
    </span></td>
    <th><span class="text-font-style-tc">
        <jecs:label  key="shipping.address" required="true"/>
    </span></th>
    <td><span class="textbox">
        <form:input cssClass="textbox-text" path="address" id="address" />
    </span></td>
	<tr>
       <th></th>
       <td colspan="3">
          <span style="color:red;"><jecs:locale key="setUp.tip"/></span>
        </td>
    </tr>
    
    <c:if test="${sessionScope.sessionLogin.companyCode=='JP'}">
    <tr><th><span class="text-font-style-tc">
        <jecs:label  key="shipping.building" required="true"/>
    </span></th>
    <td><span class="textbox">
        <form:input cssClass="textbox-text" path="building" id="building" />
    </span></td></tr>
    </c:if>

    <tr class="edit_tr">
    <th><span class="text-font-style-tc">
        <jecs:label  key="shipping.postalcode" required="true"/>
    </span></th>
    <td><span class="textbox">
        <form:input cssClass="textbox-text" path="postalcode" id="postalcode" />
    </span></td>

	<th><span class="text-font-style-tc">
        <jecs:label  key="miMember.phone" required="true"/>
    </span></th>
    <td><span class="textbox">
        <form:input cssClass="textbox-text" path="phone" id="phone" />
    </span></td></tr>

    <tr class="edit_tr">
    <th><span class="text-font-style-tc">
        <jecs:label  key="miMember.mobiletele" required="true"/>
    </span></th>
    <td><span class="textbox">
        <form:input cssClass="textbox-text" path="mobiletele" id="mobiletele" /><jecs:locale key="miMember.mobile.phone.remark" />
    </span></td>
	
	<th><span class="text-font-style-tc">
        <jecs:label  key="miMember.email"/>
    </span></th>
    <td><span class="textbox">
        <form:input cssClass="textbox-text" path="email" id="email" />
    </span></td></tr>



<c:if test="${sessionScope.sessionLogin.companyCode=='JP'}">
    <tr><th><span class="text-font-style-tc">
        <jecs:label  key="poMemberOrder.shippingPay"/>
    </span></th>
    <td><span class="textbox">
    	<c:if test="${empty jpoMemberOrder.moId }">
        	<jecs:list styleClass="textbox-text" name="shippingPay" id="shippingPay" listCode="yesno" value="${jpoMemberOrder.shippingPay }" defaultValue="0"/>
        </c:if>
    	<c:if test="${not empty jpoMemberOrder.moId }">
    		<jecs:code listCode="po.order_type" value="${jpoMemberOrder.shippingPay }"/>
    	</c:if>
    </span></td></tr>
    <tr><th><span class="text-font-style-tc">
        <jecs:label  key="poMemberOrder.shippingDay"/>
    </span></th>
    <td><span class="textbox">
									    	<form:hidden path="shippingDay" id="shippingDay"/>
									    	<span id="shippingDayLabel"></span>
									    	<img src="./images/calendar/calendar7.gif" id="img_endOperatorTime" style="cursor: pointer;" title="<jecs:locale key="Calendar.TT.SEL_DATE"/>"/> 
											<script> 
												Calendar.setup({
												inputField     :    "shippingDay", 
												ifFormat       :    "%Y-%m-%d",  
												button         :    "img_endOperatorTime", 
												singleClick    :    true,
												onUpdate       :    changeDate
												}); 
												function changeDate(){
													var arrayD1 = document.getElementById('shippingDay').value.split("-");
													var date1 = new Date(arrayD1[0],arrayD1[1],arrayD1[2]);
													var arrayD2 = "${toDay }".split("-");
													var date2 = new Date(arrayD2[0],arrayD2[1],arrayD2[2]);
													if(date1<date2){
														document.getElementById('shippingDay').value = "";
														document.getElementById('shippingDayLabel').innerText = "";
														alert("<jecs:locale key="poMemberOrder.shippingDay.error"/>");
													}else{
														document.getElementById('shippingDayLabel').innerText = document.getElementById('shippingDay').value;
													}
												}
											</script>
    </span></td></tr>
</c:if>

<c:if test="${sessionLogin.userType=='M' }">
    <tr><th><span class="text-font-style-tc">
        <jecs:label key="jpoMemberOrder.agree"/>
    </span></th>
    <td><span class="textbox">
    	<c:if test="${empty jpoMemberOrder.moId && sessionScope.sessionLogin.companyCode!='ID'}">
    		<input type="checkbox" name="agree" id="agree" /><jecs:locale key="jpoMemberOrder.agree.msg"/>
    	</c:if>
    	<c:if test="${not empty jpoMemberOrder.moId }">
    		<input type="checkbox" name="agree" id="agree" checked/><jecs:locale key="jpoMemberOrder.agree.msg"/>
    	</c:if>
    </span></td></tr>
</c:if>

</tbody>

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
					<a href="javascript:void(0);"><jecs:code listCode="pmproduct.productcategory" value="${pm.key}"/></a>
				</li>
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
        <tr  style="background-color:#E6E6E6;font-size: 9pt;vertical-align: top;"><td>  <jecs:locale  key="busi.product.pic"/></td><td>  <jecs:locale  key="busi.product.productno"/></span></td><td>  <jecs:locale  key="pmProduct.productName"/></span></td><td>  <jecs:locale  key="pd.price"/></span></td><td>  <jecs:locale  key="busi.direct.pv"/></td><td>  <jecs:locale  key="poOrder.count"/></span></td></tr>
          <c:forEach  items="${pms.value}"  var="item"  varStatus='status'>
        
         <c:if test='${status.count%2==0}'> 
         <tr class="even" >
         </c:if>
         <c:if test='${status.count%2!=0}'>
            <tr  class="add">
        </c:if>
        <td>
        </td>
        <td>${item.jpmProductSaleNew.jpmProduct.productNo}</td>
        <td>${item.jpmProductSaleNew.productName}
       <c:if test="${item.jpmProductSaleNew.hotFlag=='1'}">
           <img src="images/hot.gif"/>
       </c:if>
        
        </td>
         
        <td>${item.price}</td>
         <td>${item.pv}</td>
           
          <td>

           <c:if test="${item.jpmProductSaleNew.jpmProduct.productNo!='P08520100101CN0'}">
            <input type='text' class='text medium' name='qty' value='0' size='3' onchange="set_amt(this,'${item.pttId }','${item.jpmProductSaleNew.jpmProduct.productNo}','1');" id=qty<c:out value="${item.pttId}" />>
            </c:if>
        
         <c:if test="${item.jpmProductSaleNew.jpmProduct.productNo=='P08520100101CN0'   && sessionLogin.userType=='M'}">
		      
		       
		            <c:if test="${fn:substring(sessionScope.sessionLogin.jmiMember.jmiRecommendRef.treeIndex,0,21)!='001000000000000006006' }">
		                 <c:if test="${effectiveValid}">  <!-- 强制购买事业锦囊 -->
		          
		             <input type=text class='text medium' name='qty' value='0' size='3' onchange="set_amt(this,'${item.pttId }','${item.jpmProductSaleNew.jpmProduct.productNo}','1');" id=qty<c:out value="${item.pttId }" />>	              
		           </c:if>
		              <c:if test="${!effectiveValid}"><!--不强制购买事业锦囊 -->
		            
		                 1<input type=hidden class='text medium' name='qty' value='1' size='3' onchange="set_amt(this,'${item.pttId }','${item.jpmProductSaleNew.jpmProduct.productNo}','1');" id=qty<c:out value="${item.pttId }" />>	              
			    
			         </c:if>
			    
			        </c:if> 
		          
	               <c:if test="${fn:substring(sessionScope.sessionLogin.jmiMember.jmiRecommendRef.treeIndex,0,21)=='001000000000000006006' }">
	                 <input type='text' class='text medium' name='qty' value='0' size='3' onchange="set_amt(this,'${item.pttId }','${item.jpmProductSaleNew.jpmProduct.productNo}','1');" id=qty<c:out value="${item.pttId }" />>
	               </c:if>
          </c:if>
          <c:if test="${item.jpmProductSaleNew.jpmProduct.productNo=='P08520100101CN0'  && sessionLogin.userType=='C'}">
	                 <input type='text' class='text medium' name='qty' value='0' size='3' onchange="set_amt(this,'${item.pttId }','${item.jpmProductSaleNew.jpmProduct.productNo}','1');" id=qty<c:out value="${item.pttId }" />>
          </c:if>
           
         
            
				    <input type="hidden" name="g_no" id="g_no[<c:out value="${item.pttId }" />]" value="${item.pttId }" />
				    <script>
						aSub[<c:out value="${item.pttId }" />] = Array();
						aSub[<c:out value="${item.pttId }" />]['g_no']='<c:out value="${item.pttId }" />';
						aSub[<c:out value="${item.pttId }" />]['qty']='<c:if test="${item.jpmProductSaleNew.jpmProduct.productNo!='P08520100101CN0'}">0</c:if><c:if test="${item.jpmProductSaleNew.jpmProduct.productNo=='P08520100101CN0' &&  sessionLogin.userType=='M'}"><c:if test="${fn:substring(sessionScope.sessionLogin.jmiMember.jmiRecommendRef.treeIndex,0,21)!='001000000000000006006'}"><c:if test="${effectiveValid}">0</c:if><c:if test="${!effectiveValid}">1</c:if></c:if><c:if test="${fn:substring(sessionScope.sessionLogin.jmiMember.jmiRecommendRef.treeIndex,0,21)=='001000000000000006006'}">0</c:if></c:if><c:if test="${item.jpmProductSaleNew.jpmProduct.productNo=='P08520100101CN0' && sessionLogin.userType=='C'}">0</c:if>';
						aSub[<c:out value="${item.pttId }" />]['price']='<c:if test="${item.jpmProductSaleNew.jpmProduct.productNo!='P08520100101CN0'}"><c:out value="${item.price}" /></c:if><c:if test="${item.jpmProductSaleNew.jpmProduct.productNo=='P08520100101CN0'}">200</c:if>';
						aSub[<c:out value="${item.pttId }" />]['pv']='<c:if test="${item.jpmProductSaleNew.jpmProduct.productNo!='P08520100101CN0'}"><c:out value="${item.pv}" /></c:if><c:if test="${item.jpmProductSaleNew.jpmProduct.productNo=='P08520100101CN0'}">0</c:if>';
					</script>
					
          </td>
          
        </tr>
          </c:forEach>
	  
        </table>
        </div>
</c:forEach>
<!------------------------------------------ 首购单商品结束--------------------------->
<!---------------------------------------辅销单商品开始------------------------------->


<c:if test="${not empty jpmProductSalesF && effectiveValid}">

<div id="tabCot_productF" class="tabF">

<div class="tabContainerF">
<span style="color:red;"><jecs:locale key="f.product" />
	<ul class="tabHeadF" id="tabCot_productF-li-currentBtnF-">
	<c:forEach items="${jpmProductSalesF}" var="pm" varStatus="status">
	<c:if test="${status.count==1}">
	<li class="currentBtnF">
	</c:if>
	<c:if test="${status.count!=1}">
	<li class="">
	</c:if>
	<a href="javascript:void(0)"><jecs:code listCode="pmproduct.productcategory" value="${pm.key}"/></a></li>
 </c:forEach>
		
	</ul>
</div>




<c:forEach  items="${jpmProductSalesF}" var="pms" varStatus="sta">

  <c:if test="${sta.count==1}">
<div id="tabCot_productF_${sta.count}"  class="tabCotF" />
</c:if>
<c:if test="${sta.count!=1}">
     <div id="tabCot_productF_${sta.count}" class="tabCotF"  style="display: none;">
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
        <td>
        </td>
        <td>${item.jpmProductSaleNew.jpmProduct.productNo}</td>
        <td>${item.jpmProductSaleNew.productName}
         <c:if test="${item.jpmProductSaleNew.hotFlag=='1'}">
           <img src="images/hot.gif"/>
       </c:if>
        </td>
        <td><div id=changePrice<c:out value="${item.pttId }"/>>${item.price}</div></td>
          <td>${item.pv}</td>
          <td>
            <input type='text' class='text medium' name='qty' value='0' size='3' onchange="set_amt(this,'${item.pttId }','${item.jpmProductSaleNew.jpmProduct.productNo}','2');" id=qty<c:out value="${item.pttId }" />>
				    <input type="hidden" name="g_no" id="g_no[<c:out value="${item.pttId }" />]" value="${item.pttId }" />
				    <script>
						aSub[<c:out value="${item.pttId }" />] = Array();
						aSub[<c:out value="${item.pttId }" />]['g_no']='<c:out value="${item.pttId }" />';
						aSub[<c:out value="${item.pttId }" />]['qty']='0';
						aSub[<c:out value="${item.pttId }" />]['price']='<c:out value="${item.price }" />';
						aSub[<c:out value="${item.pttId }" />]['pv']='<c:out value="${item.pv }" />';
					    aSub[<c:out value="${item.pttId }" />]['productNo']='<c:out value="${item.jpmProductSaleNew.jpmProduct.productNo}" />';
					</script>
          </td>
        </tr>
          </c:forEach>
	  
        </table>
        </div>
    
</c:forEach>

</c:if>
<!-- -------------------------------------辅销单商品结束--------------------- -->
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
<script src="./scripts/tabF.js"></script>
</div>
<div class="sidebar">
</div>
</div>

</form:form>

<script>
    <c:forEach  var="pol" items="${jpoMemberOrder.jpoMemberOrderList}" varStatus="status">
			$('qty<c:out value="${pol.jpmProductSaleTeamType.pttId }" />').value='<c:out value="${pol.qty }" />';
			aSub[<c:out value="${pol.jpmProductSaleTeamType.pttId }" />]['qty']='<c:out value="${pol.qty }" />';
			<c:if test="${pol.jpmProductSaleTeamType.jpmProductSaleNew.jpmProduct.smNo=='A'}">
				<c:if test="${pol.jpmProductSaleTeamType.price*pol.qty>=200}">
				   $('buyF').value="trueF";//购买了辅销品 
			    </c:if> 
			    <c:if test="${pol.jpmProductSaleTeamType.price*pol.qty<200}">   
				   $('buyF').value="falseF";//购买了辅销品 
			    </c:if> 
			</c:if>
			<c:if test="${pol.jpmProductSaleTeamType.jpmProductSaleNew.jpmProduct.productNo=='P08520100101CN0'}">
			      $('buyS').value="trueS";//购买了事业锦囊
			</c:if>
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

function checkValue()
{
  
    var pv=parseInt("${pv}");
    var pagePv = parseInt($('total_pv').innerHTML);
    var buyS=document.getElementById('buyS').value;
    var buyF=document.getElementById('buyF').value;
 
    if(bCancel)//删除按钮
     {
      buyS="trueS";
      buyF="trueF"
     }
     bCancel=false;
    if(pagePv<pv)
    {
      alert('<jecs:locale key="samount.levelPv"/>${pv}pv');
      return false;
    }else
    {

      if(${not empty jpmProductSalesF && effectiveValid})
      {
       
	          if("${param.strAction}"!="deletePoMemberFOrder")
	         {
	             if(buyF=='falseF' && buyS=='falseS')
	           {
	        
	               alert('<jecs:locale key="not empty product"/>');
	               return false;
	            }else
	         {
	            return true;
	         }
	 
		      }else
		      {
		         return true;
		      }  
      }else
      {
          return true;
      } 
    }
    
}
</script>
<v:javascript formName="jpoMemberOrder" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script  src="<c:url value="/scripts/validator.jsp"/>"></script>



