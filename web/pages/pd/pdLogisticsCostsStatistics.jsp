<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jpoMemberOrderList.title"/></title>
<content tag="heading"><jecs:locale key="jpoMemberOrderList.heading"/></content>
<meta name="menu" content="JpoMemberOrderMenu"/>

<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script> 
<script type="text/javascript" src="<c:url value='/dwr/util.js'/>" ></script> 
<script type="text/javascript" src="<c:url value='/dwr/engine.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/dwr/interface/alCityManager.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/dwr/interface/alDistrictManager.js'/>" ></script>

<form action="" method="get" name="searchForm" id="searchForm">
<input name="search" type="text" size="10" value="search" style="display:none;"/>

<div class="searchBar">
 <div class="new_searchBar">
<jecs:label key="poMemberOrder.memberOrderNo"/>
<input name="memberOrderNo" type="text" size="10" value="${param.memberOrderNo }"/>
</div>
<c:if test="${sessionScope.sessionLogin.userType=='C'}">
 <div class="new_searchBar">
<jecs:label key="miMember.memberNo"/>
<input name="sysUser.userCode" type="text" value="${param['sysUser.userCode'] }" size="12"/>
</div>
</c:if>
 <div class="new_searchBar">
 &nbsp; &nbsp; 
     <jecs:list listCode="pmo.logtype" name="logType" value="${param.logType}" defaultValue="" style="cursor:pointer;width:80px;"/>:
<%--      <jecs:label key="label.dateselect.ex"/> --%>
     <input name="startLogTime" id="startLogTime" type="text" value="${param.startLogTime }" style="cursor:pointer;width:71px;"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
	        - 
	        <input name="endLogTime" id="endLogTime" type="text" value="${param.endLogTime }" style="cursor:pointer;width:71px;"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
     
 </div>
<%-- <jecs:list listCode="pmo.logtype" name="logType" value="${param.logType}" defaultValue=""/><jecs:label key="label.dateselect.ex"/>
			<input id="startLogTime" name="startLogTime" type="text" size="8" maxlength="10" value="${param.startLogTime }"/>
			<img src="./images/calendar/calendar7.gif" id="img_startOperatorTime" style="cursor: pointer;" title="<jecs:locale key="Calendar.TT.SEL_DATE"/>"/> 
			<script type="text/javascript"> 
				Calendar.setup({
				inputField     :    "startLogTime", 
				ifFormat       :    "%Y-%m-%d",  
				button         :    "img_startOperatorTime", 
				singleClick    :    true
				}); 
			</script> - 
			<input id="endLogTime" name="endLogTime" type="text" size="8" maxlength="10" value="${param.endLogTime }"/>
			<img src="./images/calendar/calendar7.gif" id="img_endOperatorTime" style="cursor: pointer;" title="<jecs:locale key="Calendar.TT.SEL_DATE"/>"/> 
			<script type="text/javascript"> 
				Calendar.setup({
				inputField     :    "endLogTime", 
				ifFormat       :    "%Y-%m-%d",  
				button         :    "img_endOperatorTime", 
				singleClick    :    true
				}); 
			</script> --%>

<jecs:power powerCode="po.all.order_type">
 <div class="new_searchBar">
	<jecs:label key="poMemberOrder.orderType"/>
	<jecs:list name="orderType" listCode="po.all.order_type" value="${param.orderType}" defaultValue="0" showBlankLine="true"/>
 </div>
</jecs:power>
<div class="new_searchBar">
<jecs:label key="pocounterorder.csstatus"/>
<jecs:list name="status" listCode="po.status" value="${param.status}" defaultValue="0" showBlankLine="true"/>
</div>
<jecs:power powerCode="po.order.area">
<div class="new_searchBar">
<jecs:label key="shipping.province"/>
	        <select name="province" id="province" onchange="getCity()">
				<option value=""><jecs:locale key='list.please.select'/></option>
				<c:forEach items="${alStateProvinces }" var="al">
					<c:if test="${al.stateProvinceId != param.province }">
						<option value="${al.stateProvinceId }">${al.stateProvinceName }</option>
					</c:if>
					<c:if test="${al.stateProvinceId == param.province }">
						<option value="${al.stateProvinceId }" selected>${al.stateProvinceName }</option>
					</c:if>
				</c:forEach>
			</select>
</div>
<div class="new_searchBar">
<jecs:label key="shipping.city"/>
	        <select name="city" id="city" onchange="getDistrict()">
				<option value=""><jecs:locale key="list.please.select"/></option>
			</select>	
</div>
<div class="new_searchBar">
<jecs:label key="shipping.district"/>
	        <select name="district" id="district" >
				<option value=""><jecs:locale key="list.please.select"/></option>
			</select>	
</div>
</jecs:power>

<jecs:power powerCode="po.product_type">
<div class="new_searchBar">
	<jecs:label key="jpoMemberOrder.productType"/>
	<jecs:list name="productType" listCode="po.producttype" value="${param.productType}" defaultValue="ALL"/>
</div>
</jecs:power>
<div class="new_searchBar">
	<jecs:label key="jfiPayByCoin.Button"/>
	<jecs:list name="payByCoin" listCode="yesno" value="${param.payByCoin}" defaultValue="" showBlankLine="true"/>
</div>	
<jecs:power powerCode="po.pay.by.jj">
<div class="new_searchBar">
	<jecs:label key="jfiPayByJJ.Button"/>
	<jecs:list name="payByJJ" listCode="yesno" value="${param.payByJJ}" defaultValue="" showBlankLine="true"/>
</div>
</jecs:power>
<div class="new_searchBar">
 <button type="submit" class="btn btn_sele"  style="width:auto" >
       <jecs:locale key="operation.button.search"/>
 </button>
</div>

<%-- <input name="search" class="button" type="submit" value="<jecs:locale key="operation.button.search"/>" /> --%>

</div>
</form>
<c:set var="footTotalVar">
<tr>
	<td align="right" class="footer" colspan="4"></td>
	<td align="right" class="footer"></td>
			
<td class="footer">&nbsp;</td>
	<td align="right" class="footer"><jecs:locale key="report.allTotal"/><b>
		<c:if test="${not empty sumMap.amountFee}">
			<fmt:formatNumber pattern="###,##0.00">${sumMap.amountFee}</fmt:formatNumber>
		</c:if>
		<c:if test="${empty sumMap.amountFee}">
			0.00
		</c:if></b>
	</td>
	
	
</tr>
</c:set>


<ec:table 
	tableId="jpoMemberOrderListTable"
	items="jpoMemberOrderFeeList"
	var="jpoMemberOrderFee"
	autoIncludeParameters="true"
	action="${pageContext.request.contextPath}/pdLogisticsCostsStatistics.html"
	width="100%"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif" footer="${footTotalVar}">
				<ec:row onclick="showTR($('tr${ROWCOUNT}'));">
<jecs:power powerCode="poMemberOrderStatistic.xls">
				<ec:exportXls fileName="poMemberOrderStatistic.xls"/>
</jecs:power>
	            <ec:column property="jpoMemberOrder.sysUser.userCode" title="miMember.memberNo" />
    			<ec:column property="jpoMemberOrder.memberOrderNo" title="poMemberOrder.memberOrderNo" />
    			<ec:column property="jpoMemberOrder.sysUser.userName" title="bdCalculatingSubDetail.name" />
    			
    			
	            <ec:column property="jpoMemberOrder.province" title="shipping.province" >
	            ${alStateProvinceMap[jpoMemberOrderFee.jpoMemberOrder.province] }
	            </ec:column>
	             <ec:column property="jpoMemberOrder.city" title="shipping.city" >
	            ${alCityMap[jpoMemberOrderFee.jpoMemberOrder.city] }
	            </ec:column>
    			<ec:column property="jpoMemberOrder.address" title="shipping.address" styleClass="centerColumn" />
    		
    			<ec:column property="fee" title="poOrder.mailFee"  styleClass="centerColumn"  />
    		
    			
    				<jecs:power powerCode="jpoRecommendPHInvoice">
    					<c:if test="${jpoMemberOrder.orderType=='1'||jpoMemberOrder.orderType=='2'}">
    						<a href="jpoRecommendPHInvoice.html?strAction=jpoRecommendPHInvoice&moId=${jpoMemberOrder.moId}" target="_blank"><img border="0" src="images/icons/printer.gif" alt="<jecs:locale key="button.print"/>" /></a>
    					</c:if>
    				</jecs:power>
				
    			
    	
				
				</ec:row>
	

</ec:table>	


<script>
	var tmpSelect = "";
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
		}
		var cityElemment=$('city');
		for (var i=0;i<cityElemment.options.length;i++) {
			if(tmpSelect=='1'){
				if (cityElemment.options[i].value == jmiAddrBook[$('province').selectedIndex].city) {
					cityElemment.options[i].selected=true;
					break;        
				}
			}else{
				if (cityElemment.options[i].value == '${param.city }') {
					cityElemment.options[i].selected=true;
					break;        
				}
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
		}
		var districtElemment=$('district');
		for (var i=0;i<districtElemment.options.length;i++) {
			if(tmpSelect=='1'){
				if (districtElemment.options[i].value == jmiAddrBook[$('province').selectedIndex].district ) {  
					districtElemment.options[i].selected=true;
					break;        
				}
			}else{
				if (districtElemment.options[i].value == '${param.district }' ) {  
					districtElemment.options[i].selected=true;
					break;        
				}
			}
		}
	}
	
	<c:if test="${not empty param.province }">
		getCity();
	</c:if>
</script>