<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jpoMemberOrderList.title" />
</title>
<content tag="heading">
<jecs:locale key="jpoMemberOrderList.heading" />
</content>
<meta name="menu" content="JpoMemberOrderMenu" />

<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" />
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script>
<script type="text/javascript"
	src="./scripts/calendar/calendar-setup.js"> </script>
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script>
<script type="text/javascript" src="<c:url value='/dwr/util.js'/>"></script>
<script type="text/javascript" src="<c:url value='/dwr/engine.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/dwr/interface/alCityManager.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/dwr/interface/alDistrictManager.js'/>"></script>

<form action="" method="get" name="searchForm" id="searchForm">
	<div class="searchBar">		
		<c:if test="${sessionScope.sessionLogin.userType=='C'}">
			<div class="new_searchBar">
				<jecs:label key="miMember.agentNo" />
				<input name="agentNo" type="text" value="${param.agentNo }" size="12" />
			</div>
		</c:if>
		<div class="new_searchBar">
			<jecs:label key="bdBounsDeduct.wweek" />
			<input name="period" type="text" size="10" value="${param.period }" />
		</div>
		<div class="new_searchBar">
			<jecs:label key="logType.BC" />
			<input name="startLogTime" id="startLogTime" type="text" 
					value="${param.startLogTime }" style="cursor: pointer;width:70px;"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
				-
			<input name="endLogTime" id="endLogTime" type="text" 
					value="${param.endLogTime }" style="cursor: pointer;width:70px;"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
		</div>
		<div class="new_searchBar">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button name="search" class="btn btn_sele" type="submit">
				<jecs:locale key="operation.button.search"/>
			</button>
			<c:if test="${not empty pmProductList }">
				<a
					href="<c:url value="jfiSunOrderStatistic.html"><c:param name="agentNo" value="${param.agentNo}"/><c:param name="period" value="${param.period}"/><c:param name="search" value="null"/></c:url>"><img
						border="0" src="images/newIcons/search_16.gif"
						alt="<jecs:locale key="function.menu.view"/>" />
				</a>
			</c:if>
		</div>
	</div>
</form>

<form action="" method="get" name="jfiOrderPeriodForm"
	id="jfiOrderPeriodForm">
	<ec:table tableId="jpoMemberOrderListTable" items="pmProductList"
		var="pmProduct" autoIncludeParameters="true"
		retrieveRowsCallback="limit" form="jfiOrderPeriodForm"
		action="${pageContext.request.contextPath}/jfiSunOrderPeriodStatistic.html"
		width="100%" showPagination="false" sortable="false"
		imagePath="./images/extremetable/*.gif">
		<ec:exportXls fileName="poMemberOrderPeriod.xls" />
		<ec:row>
			<ec:column property="product_no" title="busi.product.productno" />
			<ec:column property="product_name" title="pmProduct.productName" />
			<ec:column property="qty" title="poOrder.count"
				styleClass="numberColumn" cell="number" format="###,###,##0"
				calc="total" calcTitle="poOrder.amtCount" />
			<ec:column property="s_price" title="pd.price"
				styleClass="numberColumn" cell="number" format="###,###,##0.00"
				calc="total" calcTitle="poOrder.amtCount">
				<fmt:formatNumber pattern="###,###,##0.00"
					value="${pmProduct.s_price }"></fmt:formatNumber>
			</ec:column>
			<ec:column property="sprice" title="busi.finance.amount"
				styleClass="numberColumn" cell="number" format="###,###,##0.00"
				calc="total" calcTitle="poOrder.amtCount">
				<fmt:formatNumber pattern="###,###,##0.00"
					value="${pmProduct.sprice }"></fmt:formatNumber>
			</ec:column>
		</ec:row>
	</ec:table>
</form>
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