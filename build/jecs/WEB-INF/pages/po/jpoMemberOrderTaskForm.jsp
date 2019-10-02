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
				<c:if test="${onOff=='1' }">
				<input type="submit" class="button" name="save" id="save" onclick="bCancel=false" value="<jecs:locale key="msnstatus.1"/>" />
				</c:if>
				<c:if test="${onOff=='0' }">
				<input type="submit" class="button" name="save" id="save" onclick="bCancel=false" value="<jecs:locale key="msnstatus.0"/>" />
				</c:if>
				
		<input type="button" class="button" name="cancel" onclick="window.location.href='jpoMemberOrderTasks.html'" value="<jecs:locale key="operation.button.return"/>" />

</c:set>

<spring:bind path="jpoMemberOrderTask.*">
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

		if(isFormPosted()){
			return true;
		}else{
			return false;
		}
}
</script>
<form:form commandName="jpoMemberOrderTask" method="post" action="editJpoMemberOrderTask.html" onsubmit="return onSubmitCheck(this);" id="jpoMemberOrderTaskForm">

<input type="hidden" name="strAction" value="${param.strAction }"/>
	<div class="searchBar">
			<c:out value="${buttons}" escapeXml="false"/>
	</div>
<table class='detail' id="tableId" width="100%">

<form:hidden path="motId"/>


    <tr><th>
        <jecs:label  key="miMember.memberNo"/>
    </th>
    <td align="left">
    		${jpoMemberOrderTask.userCode }
    </td></tr>



    <tr><th>
        <jecs:label  key="jpoMemberOrderTask.time"/>
    </th>
    <td align="left">
         <jecs:locale  key="jmiMember.di"/>
         
				<c:if test="${onOff=='1' }">
         <jecs:list name="actionWeek" id="actionWeek" listCode="action.week" value="${jpoMemberOrderTask.actionWeek }" defaultValue="1"/>	
				</c:if>
				<c:if test="${onOff=='0' }">
        <jecs:code listCode="action.week" value="${jpoMemberOrderTask.actionWeek}"/>
				</c:if>
         
          <jecs:locale  key="bdCaculateLog.wweek"/>
          
          <jecs:locale  key="jmiMember.di"/>
          
				<c:if test="${onOff=='1' }">
          <jecs:list name="actionDate" id="actionDate" listCode="action.date" value="${jpoMemberOrderTask.actionDate }" defaultValue="1"/>	
				</c:if>
				<c:if test="${onOff=='0' }">
        <jecs:code listCode="action.date" value="${jpoMemberOrderTask.actionDate}"/>
				</c:if>
          
          <jecs:locale  key="system.day"/>
    </td></tr>


    <tr><th>&nbsp;
        
    </th>
    <td align="left">
<c:if test="${not empty jmiAddrBooks && onOff=='1'  }">
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
<c:if test="${empty jmiAddrBooks && onOff=='0' }">
	&nbsp;
</c:if>
    </td></tr>

    <tr><th>
        <jecs:label  key="shipping.firstName"/>
    </th>
    <td align="left">
				<c:if test="${onOff=='1' }">
        <form:input path="firstName" id="firstName" cssClass="text medium"/>
				
				</c:if>
				<c:if test="${onOff=='0' }">
		${jpoMemberOrderTask.firstName }
				</c:if>
    </td></tr>

    <tr><th>
        <jecs:label  key="shipping.lastName"/>
    </th>
    <td align="left">
				<c:if test="${onOff=='1' }">
        <form:input path="lastName" id="lastName" cssClass="text medium"/>
				</c:if>
				<c:if test="${onOff=='0' }">
		${jpoMemberOrderTask.lastName }
				</c:if>
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
				<c:if test="${onOff=='1' }">
        <form:select path="province"  cssClass="text medium" onchange="getCity()">
					<form:option label="" value=""><jecs:locale key='list.please.select'/></form:option>
		            <form:options items="${alStateProvinces}" itemValue="stateProvinceId" itemLabel="stateProvinceName" />
		</form:select>
				</c:if>
				<c:if test="${onOff=='0' }">
		    			<c:forEach items="${alStateProvinces }" var="al">
					    	<c:if test="${al.stateProvinceId==jpoMemberOrderTask.province }">
					    		<c:out value="${al.stateProvinceName}" /> 
					    	</c:if>
			    		</c:forEach>
				</c:if>
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
				<c:if test="${onOff=='1' }">
        <select name="city" id="city" onchange="getDistrict()">
			<option value=""><jecs:locale key="list.please.select"/></option>
		</select>
				</c:if>
				<c:if test="${onOff=='0' }">
		${jpoMemberOrderTask.city }
				</c:if>
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
				<c:if test="${onOff=='1' }">
        <select name="district" id="district" <c:if test="${sessionScope.sessionLogin.companyCode=='PH'}">onchange="getTown()"</c:if>>
			<option value=""><jecs:locale key="list.please.select"/></option>
		</select>
				</c:if>
				<c:if test="${onOff=='0' }">
		${jpoMemberOrderTask.district }
				</c:if>
    </td></tr>

    <c:if test="${sessionScope.sessionLogin.companyCode=='PH'}">
    <tr><th>
	       <jecs:label  key="shipping.city"/>
    </th>
    <td align="left">
				<c:if test="${onOff=='1' }">
        <select name="town" id="town" >
			<option value=""><jecs:locale key="list.please.select"/></option>
		</select>	
				</c:if>
				<c:if test="${onOff=='0' }">
		${jpoMemberOrderTask.town }
				</c:if>
    </td></tr>
    </c:if>

    <tr><th>
        <jecs:label  key="shipping.address"/>
    </th>
    <td align="left">
				<c:if test="${onOff=='1' }">
        <form:input path="address" id="address" cssClass="text medium"/>
				</c:if>
				<c:if test="${onOff=='0' }">
		${jpoMemberOrderTask.address }
				</c:if>
    </td></tr>

    <tr><th>
        <jecs:label  key="shipping.postalcode"/>
    </th>
    <td align="left">
				<c:if test="${onOff=='1' }">
        <form:input path="postalcode" id="postalcode" cssClass="text medium"/>
				</c:if>
				<c:if test="${onOff=='0' }">
		${jpoMemberOrderTask.postalcode }
				</c:if>
    </td></tr>

    <tr><th>
        <jecs:label  key="miMember.phone"/>
    </th>
    <td align="left">
				<c:if test="${onOff=='1' }">
        <form:input path="phone" id="phone" cssClass="text medium"/>
				</c:if>
				<c:if test="${onOff=='0' }">
		${jpoMemberOrderTask.phone }
				</c:if>
    </td></tr>

    <tr><th>
        <jecs:label  key="miMember.mobiletele"/>
    </th>
    <td align="left">
				<c:if test="${onOff=='1' }">
        <form:input path="mobiletele" id="mobiletele" cssClass="text medium"/><jecs:locale key="miMember.mobile.phone.remark" />
				</c:if>
				<c:if test="${onOff=='0' }">
		${jpoMemberOrderTask.mobiletele }
				</c:if>
    </td></tr>

    <tr><th>
        <jecs:label  key="miMember.email"/>
    </th>
    <td align="left">
				<c:if test="${onOff=='1' }">
        <form:input path="email" id="email" cssClass="text medium"/>
				</c:if>
				<c:if test="${onOff=='0' }">
		${jpoMemberOrderTask.email }
				</c:if>
    </td></tr>

</table>

<c:set var="footTotalVar">
<tr>
	<td align="right" class="footer">&nbsp;</td>
	<td align="right" class="footer">&nbsp;</td>
	<td align="right" class="footer"><jecs:locale key="poOrder.amtCount"/></td>
	<td class="footer" align="right">
		<b><span id='total_price'>0</span></b>
	</td>
	<td class="footer" align="right">
		<b><span id='total_pv'>0</span></b>
	</td>
	<td class="footer" align="right">
		<b><span id='total_qty'>0</span></b>
	</td>
</tr>
</c:set>
<ec:table 
	tableId="piProductListTable"
	items="jpmProductSales"
	var="pi"
	action="${pageContext.request.contextPath}/editPoMemberROrder.html"
	width="100%" form = "poMemberOrderForm"
	showPagination="false" sortable="false" imagePath="./images/extremetable/*.gif" footer="${footTotalVar }">
				<ec:row>
    			<ec:column property="1"   title="busi.product.pic">
                	<c:if test="${not empty pi.imageLink}">
                    	<img src="images/attachimg.gif" alt="${pi.productName}" border="0" onmouseover="objPeerTip.show(event);" onmouseout="objPeerTip.close()"  JTipAjax="pic.jsp?pic=${pi.imageLink}" JTipWidth="" />
                    </c:if>
                </ec:column>
    			<ec:column property="jpmProduct.productNo" title="busi.product.productno"/>
    			<ec:column property="productName" title="pmProduct.productName">
    				${pi.productName }
    			</ec:column>
    			<ec:column property="mpPrice" title="pd.price" styleClass="numberColumn">
    				<fmt:formatNumber value="${pi.mpPrice}" type="number" pattern="###,###,##0.00"/>
    			</ec:column>
    			<ec:column property="mpPv" title="busi.direct.pv" styleClass="numberColumn"/>
    			<ec:column property="qty" title="poOrder.count" styleClass="centerColumn">
				    <input type='text' class='text medium' name='qty' value='0' size='3' onchange="set_amt(this,'${pi.uniNo }');" id=qty<c:out value="${pi.uniNo }" /> <c:if test="${onOff=='0' }">disabled="disabled"</c:if> >
				    <input type="hidden" name="g_no" id="g_no[<c:out value="${pi.uniNo }" />]" value="${pi.uniNo }" />
				    <script>
						aSub[<c:out value="${pi.uniNo }" />] = Array();
						aSub[<c:out value="${pi.uniNo }" />]['g_no']='<c:out value="${pi.uniNo }" />';
						aSub[<c:out value="${pi.uniNo }" />]['qty']='0';
						aSub[<c:out value="${pi.uniNo }" />]['price']='<c:out value="${pi.mpPrice }" />';
						aSub[<c:out value="${pi.uniNo }" />]['pv']='<c:out value="${pi.mpPv }" />';
					</script>
    			</ec:column>
				</ec:row>
</ec:table>	









</form:form>

<script type="text/javascript">
    <c:forEach  var="pol" items="${jpoMemberOrderTask.jpoMemberOrderListTask}" varStatus="status">
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
				if (cityElemment.options[i].value == '${jpoMemberOrderTask.city }') {
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
					if (districtElemment.options[i].value == '${jpoMemberOrderTask.district }' ) {  
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
					if (townElemment.options[i].value == '${jpoMemberOrderTask.town }' ) {  
						townElemment.options[i].selected=true;
						break;        
					}
				}
			}
	}
	<c:if test="${ empty jpoMemberOrderTask.motId }">
		getCity();
	</c:if>
	

</script>
<v:javascript formName="jpoMemberOrderTask" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
