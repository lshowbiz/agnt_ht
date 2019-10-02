<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jpoMemberOrderDetail.title"/></title>
<content tag="heading"><jecs:locale key="jpoMemberOrderDetail.heading"/></content>
<script type="text/javascript" src="<c:url value='/dwr/util.js'/>" ></script> 
<script type="text/javascript" src="<c:url value='/dwr/engine.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/dwr/interface/alCityManager.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/dwr/interface/alDistrictManager.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/dwr/interface/jalTownManager.js'/>" ></script>
<LINK href="load_images/WebResource.css" type=text/css rel=stylesheet>
<SCRIPT src="load_images/prototype.js" type=text/javascript></SCRIPT>
<SCRIPT src="load_images/load_pic.js" type=text/javascript></SCRIPT>
<link rel="stylesheet" href="styles/tree_grid.css" type="text/css" />
<SCRIPT type=text/javascript>
<!--
var objPeerTip=new PeerTip();// -->
Close_PIC=function(Name)//�رտ���
{
	var iobj=eval(Name);
	var span_obj=eval(Name);	
	span_obj.style.display="none";	
}
</SCRIPT>
<c:set var="buttons">
<script>
function nextSave(){
	if(Number($('total_price').innerText)>=Number($('total_price_s').innerText)*${lcDiscount }){
	if(Number($('total_price').innerText)!=Number($('total_price_s').innerText)*${lcDiscount }){
		   if (!confirm("<jecs:locale key='insufficient.error'/>")){ 
				return false;
			}
		}
		if(isFormPosted()){
			return true;
		}else{
			return false;
		}
	}else{
		alert('<jecs:locale key="samount.error"/>');
		return false;
	}
}
</script>

<c:if test='${empty jpoMemberOrder.moId}'>
<jecs:power powerCode="addPoMemberUOrderLC">
				<input type="submit" class="button" name="save"  onclick="return nextSave();" value="<jecs:locale key="operation.button.save"/>" />
</jecs:power>
</c:if>
<c:if test='${jpoMemberOrder.status=="1" && jpoMemberOrder.submitStatus =="1" && not empty jpoMemberOrder.moId}'>
<jecs:power powerCode="editPoMemberUOrderLC">
				<input type="submit" class="button" name="save"  onclick="return nextSave();" value="<jecs:locale key="operation.button.save"/>" />
</jecs:power>
<jecs:power powerCode="deletePoMemberUOrderLC">
				<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'PoMemberUOrderLC')" value="<jecs:locale key="operation.button.delete"/>" />
</jecs:power>
</c:if>

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
  var aSub2 = Array();
  var aSub3 = Array();
  function set_amt(nQty,no,step)
  {	
  	if(step==2){
	    if (!isFinite(nQty.value) || nQty.value < 0 ){
		  	nQty.value=aSub2[no]['qty'];
		}else {
		  nQty.value=Number(nQty.value);
		  aSub2[no]['qty']=Number(nQty.value);
		}
  	}else{
	    if (!isFinite(nQty.value) || nQty.value < 0 ){
		  	nQty.value=aSub3[no]['qty'];
		}else {
		  nQty.value=Number(nQty.value);
		  aSub3[no]['qty']=Number(nQty.value);
		}
  	}
	amt_count(step);
  }
  function amt_count(step){
	if(step==2){
		var nPriceAmt=Number(0);
		var nPvAmt=Number(0);
		var nPvQty=Number(0);
		for (i=0;i<aSub2.length;i++) {
			if(aSub2[i]!=undefined){
			  nPriceAmt+=Number(aSub2[i]['qty'])*Number(aSub2[i]['price']);
			  nPvAmt+=Number(aSub2[i]['qty'])*Number(aSub2[i]['pv']);
			  nPvQty+=Number(aSub2[i]['qty']);
		  	}
		}
    	$('total_price').innerHTML=Math.round(nPriceAmt,2);
    	$('total_pv').innerHTML=Math.round(nPvAmt,2);
    	$('total_price_pre').innerHTML=Math.round(nPriceAmt/${lcDiscount },2);
	}else{
		var nPriceAmt=Number(0);
		var nPvAmt=Number(0);
		var nPvQty=Number(0);
		for (i=0;i<aSub3.length;i++) {
			if(aSub3[i]!=undefined){
			  nPriceAmt+=Number(aSub3[i]['qty'])*Number(aSub3[i]['price']);
			  nPvAmt+=Number(aSub3[i]['qty'])*Number(aSub3[i]['pv']);
			  nPvQty+=Number(aSub3[i]['qty']);
		  	}
		}
		$('total_price_s').innerHTML=Math.round(nPriceAmt,2);
	}
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
			return true;
	}
	if(validateJpoMemberOrder(formId)){
	<c:if test="${empty jpoMemberOrder.moId && sessionLogin.userType=='M' }">
		if($('agree').checked==false){
			alert('<jecs:locale key="jpoMemberOrder.mustAgree"/>');
			return false;
		}
	</c:if>
			return true;
	}else{
		return false;
	}
}
</script>
<form:form commandName="jpoMemberOrder" method="post" action="editJpoMemberUOrderLC.html" id="jpoMemberOrderForm">

<input type="hidden" name="strAction" value="${param.strAction }"/>
<script>
function stepNext1(){
	bCancel=false;
	if(onSubmitCheck($('jpoMemberOrderForm'))==true){
		amt_count(3);
		$('step1').style.display='none';$('step2').style.display='inline';
	}
}

</script>
<div id="step1">
	<div class="searchBar">
		<input type="button" class="button" name="step1Next"  onclick="stepNext1();" value="<jecs:locale key="button.next"/>" />
<input type="button" class="button" name="back"  onclick="window.location.href='jpoMemberUOrders.html?needReload=1'" value="<jecs:locale key="operation.button.return"/>" />
	</div>
<table class='detail' id="tableId" width="100%">

<form:hidden path="moId"/>

<c:if test='${param.strAction=="editPoMemberUOrderLC"}'>
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
    	<c:if test='${param.strAction=="editPoMemberUOrderLC"}'>
    		${jpoMemberOrder.sysUser.userCode }
    	</c:if>
    	<c:if test='${param.strAction=="addPoMemberUOrderLC"}'>
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
    	<c:if test='${param.strAction=="editPoMemberUOrderLC"}'>
    		<jecs:code listCode="po.order_type" value="${jpoMemberOrder.orderType}"/>
    	</c:if>
    	<c:if test='${param.strAction=="addPoMemberUOrderLC"}'>
        	<jecs:code listCode="po.order_type" value="${jpoMemberOrder.orderType}"/>
    	</c:if>
    </td></tr>

<c:if test='${param.strAction=="editPoMemberUOrderLC"}'>
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
    	<c:if test='${param.strAction=="editPoMemberUOrderLC"}'>
    		<jecs:code listCode="po.paymode" value="${jpoMemberOrder.payMode}"/>
    	</c:if>
    	<c:if test='${param.strAction=="addPoMemberUOrderLC"}'>
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
	    <c:if test="${sessionScope.sessionLogin.companyCode=='PH'}">
	        <jecs:label  key="shipping.island" required="true"/>
	    </c:if>
	    <c:if test="${sessionScope.sessionLogin.companyCode!='PH'}">
	        <jecs:label  key="shipping.province" required="true"/>
	    </c:if>
    </th>
    <td align="left">
        <form:select path="province"  cssClass="text medium" onchange="getCity()">
					<form:option label="" value=""><jecs:locale key='list.please.select'/></form:option>
		            <form:options items="${alStateProvinces}" itemValue="stateProvinceId" itemLabel="stateProvinceName" />
		</form:select>
    </td></tr>

    <tr><th>
	    <c:if test="${sessionScope.sessionLogin.companyCode=='PH'}">
	        <jecs:label  key="shipping.region"/>
	    </c:if>
	    <c:if test="${sessionScope.sessionLogin.companyCode!='PH'}">
	        <jecs:label  key="shipping.city" required="true"/>
	    </c:if>
    </th>
    <td align="left">
        <select name="city" id="city" onchange="getDistrict()">
			<option value=""><jecs:locale key="list.please.select"/></option>
		</select>
    </td></tr>

    <tr><th>
	    <c:if test="${sessionScope.sessionLogin.companyCode=='PH'}">
	        <jecs:label  key="shipping.province"/>
	    </c:if>
	    <c:if test="${sessionScope.sessionLogin.companyCode!='PH'}">
	        <jecs:label  key="shipping.district"/>
	    </c:if>
    </th>
    <td align="left">
        <select name="district" id="district" <c:if test="${sessionScope.sessionLogin.companyCode=='PH'}">onchange="getTown()"</c:if>>
			<option value=""><jecs:locale key="list.please.select"/></option>
		</select>	
    </td></tr>

    <c:if test="${sessionScope.sessionLogin.companyCode=='PH'}">
    <tr><th>
	       <jecs:label  key="shipping.city"/>
    </th>
    <td align="left">
        <select name="town" id="town" >
			<option value=""><jecs:locale key="list.please.select"/></option>
		</select>	
    </td></tr>
    </c:if>

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
        <jecs:label key="jpoMemberOrder.agree"/>
    </th>
    <td align="left">
    	<c:if test="${empty jpoMemberOrder.moId }">
    		<input type="checkbox" name="agree" id="agree" /><jecs:locale key="jpoMemberOrder.agree.msg"/>
    	</c:if>
    	<c:if test="${not empty jpoMemberOrder.moId }">
    		<input type="checkbox" name="agree" id="agree" checked/><jecs:locale key="jpoMemberOrder.agree.msg"/>
    	</c:if>
    </td></tr>
</c:if>

</table>
</div>
<script>
function stepBack1(){
	$('step2').style.display='none';
	$('step1').style.display='inline';
}
function stepNext2(){
	if(Number($('total_price').innerText)>=600){
		$('step2').style.display='none';
		$('step3').style.display='inline';
	}else{
		alert('<jecs:locale key="samount.notEnough"/>');
	}
}
</script>
<div id="step2" style="display:none;width: 100%">
	<div class="searchBar">
		<input type="button" class="button" name="step1Back"  onclick="stepBack1();" value="<jecs:locale key="register.perous"/>" />
		<input type="button" class="button" name="step2Next"  onclick="stepNext2();" value="<jecs:locale key="button.next"/>" />
		<input type="button" class="button" name="back"  onclick="window.location.href='jpoMemberUOrders.html?needReload=1'" value="<jecs:locale key="operation.button.return"/>" />
		<jecs:locale key="busi.order.amount"/><span id='total_price'>0</span>,<jecs:locale key="poMemberOrder.pvAmt"/><span id='total_pv'>0</span>
	</div>
<c:set var="varS" value="1" />
<c:forEach items="${jpmProductSales }" var="jpms" varStatus="varStatus">
<c:set var="varS" value="${varStatus.count }" />
<c:if test="${varStatus.count % 2 == 1  }">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
</c:if>
    <td><br />
    	<jecs:code listCode="pmproduct.productcategory" value="${jpms.key }"/>,<jecs:label key="pd.price"/>${jpms.value.productInfo.fpPrice },<jecs:label key="busi.direct.pv"/>${jpms.value.productInfo.fpPv }<br />
		<br />
		<table id="tablemain" class="tree_grid" border="0" cellspacing="0" cellpadding="0" width="40%">
			<thead>
				<tr>
					<th>style\size</th>
					<c:forEach items="${jpms.value.size }" var="size">
						<th>${size.PRODUCT_SIZE}</th>
					</c:forEach>
				</tr>
			</thead>
			<tbody id="treetable">
				<c:forEach items="${jpms.value.style }" var="style">
				<tr>
					<td>${style.PRODUCT_STYLE }</td>
					<c:forEach items="${jpms.value.size }" var="size">
						<td>
						<c:if test="${not empty jpms.value.jpmProductSales[style.PRODUCT_STYLE] && not empty jpms.value.jpmProductSales[style.PRODUCT_STYLE][size.PRODUCT_SIZE] }">
						    <input type='text' name='qty' value='0' size='2' onchange="set_amt(this,'${jpms.value.jpmProductSales[style.PRODUCT_STYLE][size.PRODUCT_SIZE].uniNo }2',2);" id=qty<c:out value="${jpms.value.jpmProductSales[style.PRODUCT_STYLE][size.PRODUCT_SIZE].uniNo }2" />>
						    <input type="hidden" name="g_no" id="g_no[<c:out value="${jpms.value.jpmProductSales[style.PRODUCT_STYLE][size.PRODUCT_SIZE].uniNo }" />2]" value="${jpms.value.jpmProductSales[style.PRODUCT_STYLE][size.PRODUCT_SIZE].uniNo }2" />
						    <br/>
						    <script>
								aSub2[<c:out value="${jpms.value.jpmProductSales[style.PRODUCT_STYLE][size.PRODUCT_SIZE].uniNo }" />2] = Array();
								aSub2[<c:out value="${jpms.value.jpmProductSales[style.PRODUCT_STYLE][size.PRODUCT_SIZE].uniNo }" />2]['g_no']='<c:out value="${jpms.value.jpmProductSales[style.PRODUCT_STYLE][size.PRODUCT_SIZE].uniNo }" />2';
								aSub2[<c:out value="${jpms.value.jpmProductSales[style.PRODUCT_STYLE][size.PRODUCT_SIZE].uniNo }" />2]['qty']='0';
								aSub2[<c:out value="${jpms.value.jpmProductSales[style.PRODUCT_STYLE][size.PRODUCT_SIZE].uniNo }" />2]['price']='<c:out value="${jpms.value.jpmProductSales[style.PRODUCT_STYLE][size.PRODUCT_SIZE].fpPrice }" />';
								aSub2[<c:out value="${jpms.value.jpmProductSales[style.PRODUCT_STYLE][size.PRODUCT_SIZE].uniNo }" />2]['pv']='<c:out value="${jpms.value.jpmProductSales[style.PRODUCT_STYLE][size.PRODUCT_SIZE].fpPv }" />';
							</script><%--
							<jecs:label key="pd.price"/>${jpms.value.jpmProductSales[style.PRODUCT_STYLE][size.PRODUCT_SIZE].fpPrice }<br/>
							<jecs:label key="busi.direct.pv"/>${jpms.value.jpmProductSales[style.PRODUCT_STYLE][size.PRODUCT_SIZE].fpPv }
						--%></c:if>
						<c:if test="${empty jpms.value.jpmProductSales[style.PRODUCT_STYLE] || empty jpms.value.jpmProductSales[style.PRODUCT_STYLE][size.PRODUCT_SIZE] }">
							&nbsp;
						</c:if>
						</td>
					</c:forEach>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</td>
<c:if test="${varStatus.count % 2 == 0  }">
  </tr>
</table>
</c:if>
</c:forEach>
<c:if test="${varS % 2 == 1  }">
  </tr>
</table>
</c:if>
</div>
<script>
function stepBack2(){
	$('step3').style.display='none';
	$('step2').style.display='inline';
}
</script>
<div id="step3" style="display:none">
	<div class="searchBar">
		<input type="button" class="button" name="step1Back"  onclick="stepBack2();" value="<jecs:locale key="register.perous"/>" />
		<c:out value="${buttons}" escapeXml="false"/>
		<input type="button" class="button" name="back"  onclick="window.location.href='jpoMemberUOrders.html?needReload=1'" value="<jecs:locale key="operation.button.return"/>" />
		<jecs:locale key="pre.amount"/><span id='total_price_pre'>0</span>,<jecs:locale key="busi.order.amount"/><span id='total_price_s'>0</span>
	</div>
<span><font color="red"><b><jecs:locale key="pre.amount.tip"/></b></font></span>
<c:forEach items="${jpmProductSales }" var="jpms" varStatus="varStatus">
<c:set var="varS" value="${varStatus.count }" />
<c:if test="${varStatus.count % 2 == 1  }">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
</c:if>
    <td><br />
    	<jecs:code listCode="pmproduct.productcategory" value="${jpms.key }"/>,<jecs:label key="pd.price"/>${jpms.value.productInfo.fpPrice },<jecs:label key="busi.direct.pv"/>0<br />
		<br />
		<table id="tablemain" class="tree_grid" border="0" cellspacing="0" cellpadding="0" width="40%">
			<thead>
				<tr>
					<th>style\size</th>
					<c:forEach items="${jpms.value.size }" var="size">
						<th>${size.PRODUCT_SIZE}</th>
					</c:forEach>
				</tr>
			</thead>
			<tbody id="treetable">
				<c:forEach items="${jpms.value.style }" var="style">
				<tr>
					<td>${style.PRODUCT_STYLE }</td>
					<c:forEach items="${jpms.value.size }" var="size">
						<td>
						<c:if test="${not empty jpms.value.jpmProductSales[style.PRODUCT_STYLE] && not empty jpms.value.jpmProductSales[style.PRODUCT_STYLE][size.PRODUCT_SIZE] }">
						    <input type='text' name='qty' value='0' size='2' onchange="set_amt(this,'${jpms.value.jpmProductSales[style.PRODUCT_STYLE][size.PRODUCT_SIZE].uniNo }3',3);" id=qty<c:out value="${jpms.value.jpmProductSales[style.PRODUCT_STYLE][size.PRODUCT_SIZE].uniNo }" />3>
						    <input type="hidden" name="g_no" id="g_no[<c:out value="${jpms.value.jpmProductSales[style.PRODUCT_STYLE][size.PRODUCT_SIZE].uniNo }" />3]" value="${jpms.value.jpmProductSales[style.PRODUCT_STYLE][size.PRODUCT_SIZE].uniNo }3" />
						    <br/>
						    <script>
								aSub3[<c:out value="${jpms.value.jpmProductSales[style.PRODUCT_STYLE][size.PRODUCT_SIZE].uniNo }" />3] = Array();
								aSub3[<c:out value="${jpms.value.jpmProductSales[style.PRODUCT_STYLE][size.PRODUCT_SIZE].uniNo }" />3]['g_no']='<c:out value="${jpms.value.jpmProductSales[style.PRODUCT_STYLE][size.PRODUCT_SIZE].uniNo }" />3';
								aSub3[<c:out value="${jpms.value.jpmProductSales[style.PRODUCT_STYLE][size.PRODUCT_SIZE].uniNo }" />3]['qty']='0';
								aSub3[<c:out value="${jpms.value.jpmProductSales[style.PRODUCT_STYLE][size.PRODUCT_SIZE].uniNo }" />3]['price']='<c:out value="${jpms.value.jpmProductSales[style.PRODUCT_STYLE][size.PRODUCT_SIZE].fpPrice }" />';
								aSub3[<c:out value="${jpms.value.jpmProductSales[style.PRODUCT_STYLE][size.PRODUCT_SIZE].uniNo }" />3]['pv']='0';
							</script><%--
							<jecs:label key="pd.price"/>${jpms.value.jpmProductSales[style.PRODUCT_STYLE][size.PRODUCT_SIZE].fpPrice }<br/>
							<jecs:label key="busi.direct.pv"/>0
						--%></c:if>
						<c:if test="${empty jpms.value.jpmProductSales[style.PRODUCT_STYLE] || empty jpms.value.jpmProductSales[style.PRODUCT_STYLE][size.PRODUCT_SIZE] }">
							&nbsp;
						</c:if>
						</td>
					</c:forEach>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</td>
<c:if test="${varStatus.count % 2 == 0  }">
  </tr>
</table>
</c:if>
</c:forEach>
<c:if test="${varS % 2 == 1  }">
  </tr>
</table>
</c:if>
</div>
</form:form>

<script type="text/javascript">
    <c:forEach  var="pol" items="${jpoMemberOrder.jpoMemberOrderList}" varStatus="status">
    <c:if test="${pol.productType != 'LC' }">
	$('qty<c:out value="${pol.jpmProductSale.uniNo }" />2').value='<c:out value="${pol.qty }" />';
	aSub2[<c:out value="${pol.jpmProductSale.uniNo }" />2]['qty']='<c:out value="${pol.qty }" />';
	</c:if>
	<c:if test="${pol.productType == 'LC' }">
	$('qty<c:out value="${pol.jpmProductSale.uniNo }" />3').value='<c:out value="${pol.qty }" />';
	aSub3[<c:out value="${pol.jpmProductSale.uniNo }" />3]['qty']='<c:out value="${pol.qty }" />';
	</c:if>
	</c:forEach>
	amt_count(2);
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
			cityElemment.options.add(o);  
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
			districtElemment.options.add(o);  
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
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
