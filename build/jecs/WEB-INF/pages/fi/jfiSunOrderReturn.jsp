<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jpoMemberOrderList.title" /></title>
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
		<div class="new_searchBar">
			<jecs:label key="poMemberOrder.memberOrderNo" />
			<input name="memberOrderNo" type="text" size="10"
				value="${param.memberOrderNo }" />
		</div>
		<c:if test="${sessionScope.sessionLogin.userType=='C'}">
			<div class="new_searchBar">
				<jecs:label key="miMember.agentNo" />
				<input name="agentNo" type="text" value="${param.agentNo }" size="12" />
			</div>
		</c:if>
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
			<jecs:label key="order.returnTime" />
			<input name="startReturnTime" id="startReturnTime" type="text" 
					value="${param.startReturnTime }" style="cursor: pointer;width:70px;"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
				-
			<input name="endReturnTime" id="endReturnTime" type="text" 
					value="${param.endReturnTime }" style="cursor: pointer;width:70px;"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
		</div>
		<div class="new_searchBar">
			<jecs:label key="poMemberOrder.orderType" />
			<jecs:list name="sorderType" listCode="fi.sun.order"
				value="${param.sorderType}" defaultValue="0" showBlankLine="true" />
		</div>
		<div class="new_searchBar">
			<jecs:label key="pd.ordertype.prRefund" />
			<jecs:list name="isDisable" listCode="yesno"
				value="${param.isDisable}" defaultValue="" />
		</div>
		<div class="new_searchBar">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button name="search" class="btn btn_sele" type="submit">
				<jecs:locale key="operation.button.search"/>
			</button>
		</div>
	</div>
</form>

<ec:table tableId="jpoMemberOrderListTable" items="jpoMemberOrderList"
	var="jpoMemberOrder" autoIncludeParameters="true"
	action="${pageContext.request.contextPath}/jfiSunOrderReturn.html"
	width="100%" retrieveRowsCallback="limit" rowsDisplayed="20"
	sortable="true" imagePath="./images/extremetable/*.gif">
	<ec:row onclick="showTR($('tr${ROWCOUNT}'));">
		<ec:exportXls fileName="poMemberOrderReturn.xls" />
		<ec:column property="memberOrderNo"
			title="poMemberOrder.memberOrderNo" />
		<ec:column property="sorderType" title="poMemberOrder.orderType"
			styleClass="centerColumn">
			<jecs:code listCode="fi.sun.order"
				value="${jpoMemberOrder.sorderType}" />
		</ec:column>
		<ec:column property="agentNo" title="miMember.agentNo" />
		<c:if test='${param.isDisable=="0"}'>
			<ec:column property="samount" title="busi.order.amount" />
		</c:if>
		<c:if test='${param.isDisable!="0"}'>
			<ec:column property="sramount" title="busi.order.amount" />
		</c:if>
		<ec:column property="checkTime" title="logType.BC"
			styleClass="centerColumn" />
		<ec:column property="edit" title="title.operation" sortable="false"
			style="width:100px;" viewsAllowed="html">
			<c:if test="${jpoMemberOrder.isDisable=='0' }">
				<a
					href="jfiSunOrderView.html?strAction=viewFiSunOrder&moId=${jpoMemberOrder.moId}"><img
						border="0" src="images/newIcons/search_16.gif"
						alt="<jecs:locale key="function.menu.view"/>" /> </a>
			</c:if>
			<a href="editJfiSunOrder.html?moId=${jpoMemberOrder.moId}"><img
					border="0"
					src="images/icons/<c:if test="${jpoMemberOrder.isDisable==0}">creatOrder.gif</c:if><c:if test="${jpoMemberOrder.isDisable==1}">edit_ok.png</c:if>" />
			</a>
		</ec:column>
	</ec:row>
	<c:if test="${ROWCOUNT>0}">
		<c:if test="${ROWCOUNT%2!=0}">
			<tr id="tr${ROWCOUNT}" style="display: none" class="odddetail">
		</c:if>
		<c:if test="${ROWCOUNT%2==0}">
			<tr id="tr${ROWCOUNT}" style="display: none" class="evendetail">
		</c:if>
		<td colspan="10">
			<c:forEach items="${jpoMemberOrder.jfiSunOrderList}"
				var="jpoMemberOrderDetail" varStatus="status">
				<c:if test="${!status.first}">
					<br />
				</c:if>
									&nbsp;&nbsp;<img
					src="<c:url value='/images/menu_tree/file12.gif'/>" />&nbsp;&nbsp;<font
					color=#888888>[<font color="green">${jpoMemberOrderDetail.qty}</font>]${jpoMemberOrderDetail.jpmProductSale.jpmProduct.productNo}/${jpoMemberOrderDetail.jpmProductSale.productName}</font>
			</c:forEach>
		</td>
		</tr>
	</c:if>

</ec:table>

<ec:table tableId="pmProductListTable" items="pmProductList"
	var="pmProduct"
	action="${pageContext.request.contextPath}/jfiSunOrderStatistic.html"
	width="100%" showPagination="false" sortable="false"
	imagePath="./images/extremetable/*.gif">
	<ec:row>
		<ec:column property="product_no" title="busi.product.productno" />
		<ec:column property="product_name" title="pmProduct.productName" />
		<c:if test='${param.isDisable=="0"}'>
			<ec:column property="qty" title="poOrder.count"
				styleClass="numberColumn" cell="number" format="###,###,##0"
				calc="total" calcTitle="poOrder.amtCount" />
			<ec:column property="sprice" title="pd.price"
				styleClass="numberColumn" cell="number" format="###,###,##0.00"
				calc="total" calcTitle="poOrder.amtCount">
				<fmt:formatNumber pattern="###,###,##0.00"
					value="${pmProduct.sprice }"></fmt:formatNumber>
			</ec:column>
		</c:if>
		<c:if test='${param.isDisable!="0"}'>
			<ec:column property="rqty" title="poOrder.count"
				styleClass="numberColumn" cell="number" format="###,###,##0"
				calc="total" calcTitle="poOrder.amtCount" />
			<ec:column property="srprice" title="pd.price"
				styleClass="numberColumn" cell="number" format="###,###,##0.00"
				calc="total" calcTitle="poOrder.amtCount">
				<fmt:formatNumber pattern="###,###,##0.00"
					value="${pmProduct.srprice }"></fmt:formatNumber>
			</ec:column>
		</c:if>
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