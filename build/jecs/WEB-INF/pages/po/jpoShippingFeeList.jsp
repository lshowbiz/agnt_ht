<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jpoShippingFeeList.title"/></title>
<content tag="heading"><jecs:locale key="jpoShippingFeeList.heading"/></content>
<meta name="menu" content="JpoShippingFeeMenu"/>
<script type="text/javascript" src="<c:url value='/dwr/util.js'/>" ></script> 
<script type="text/javascript" src="<c:url value='/dwr/engine.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/dwr/interface/alCityManager.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/dwr/interface/alDistrictManager.js'/>" ></script>

<form action="" method="get" name="searchForm" id="searchForm">
<div class="searchBar">
<div class="new_searchBar">
<jecs:title key="shipping.province"/>
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
<jecs:title key="shipping.city"/>
	        <select name="city" id="city" onchange="getDistrict()">
				<option value=""><jecs:locale key="list.please.select"/></option>
			</select>
			</div>
			
<div class="new_searchBar">
<jecs:title key="shipping.district"/>
	        <select name="district" id="district" >
				<option value=""><jecs:locale key="list.please.select"/></option>
			</select>	
			</div>
			
<div class="new_searchBar">			
<jecs:title key="pomember.feeType"/>
<%-- <jecs:list name="shippingType" listCode="feetype" value="${param.shippingType }" defaultValue="0" showBlankLine="true"/> --%>
<jecs:list name="shippingType" listCode="ship.logisticsstrategy" value="${jpoShippingFee.shippingType}" defaultValue="" showBlankLine="true"/>
</div>


<%--<jecs:title key="pomember.fee"/>
 <input name="shippingFee" type="text" value="${param.shippingFee }" size="12"/> --%>
 
<div class="new_searchBar">
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

<input name="search" type="hidden" value="<jecs:locale key="operation.button.search"/>" />
<button name="search" class="btn btn_sele" type="submit" >
	<jecs:locale key="operation.button.search"/>
</button>
<jecs:power powerCode="addJpoShippingFee">
<button name="add" class="btn btn_ins" type="button" onclick="location.href='<c:url value="//editJpoShippingFee.html?strAction=addJpoShippingFee"/>'">
	<jecs:locale key="member.new.num"/>
</button>
</jecs:power>
</div>
</div>
</form>


<ec:table 
	tableId="jpoShippingFeeListTable"
	items="jpoShippingFeeList"
	var="jpoShippingFee"
	action="${pageContext.request.contextPath}/jpoShippingFees.html"
	width="100%"
	autoIncludeParameters="true"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
				<ec:row >
    			<ec:column property="province" title="shipping.province">
		    			<c:forEach items="${alStateProvinces }" var="al">
					    	<c:if test="${al.stateProvinceId==jpoShippingFee.province }">
					    		<c:out value="${al.stateProvinceName}" /> 
					    	</c:if>
			    		</c:forEach>
    			</ec:column>
    			<ec:column property="city" title="shipping.city" />
    			<ec:column property="district" title="shipping.district" />
    			<ec:column property="shippingType" title="pomember.feeType">
    				<jecs:code listCode="feetype" value="${jpoShippingFee.shippingType }"/>
    			</ec:column>
    			<ec:column property="shippingFee" title="pomember.fee">
    				<fmt:formatNumber value="${jpoShippingFee.shippingFee}" type="number" pattern="###,###,##0.00"/>
    			</ec:column>
				<ec:column property="edit" title="title.operation" sortable="false" style="width:100px;" viewsAllowed="html">
							<img src="<c:url value='/images/newIcons/pencil_16.gif'/>" 
								onclick="javascript:editJpoShippingFee('${jpoShippingFee.jpsId}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
				</ec:column>
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
<script type="text/javascript">

   function editJpoShippingFee(jpsId){
   		<jecs:power powerCode="editJpoShippingFee">
					window.location="editJpoShippingFee.html?strAction=editJpoShippingFee&jpsId="+jpsId;
			</jecs:power>
		}

</script>
