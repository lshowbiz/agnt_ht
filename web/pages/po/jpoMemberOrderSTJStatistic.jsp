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
<script type='text/javascript' src='<c:url value="/dwr/interface/pdWarehouseStockManager.js"/>'></script>

<input type="hidden" name="userCodeV" value="${sessionScope.sessionLogin.userCode }"/>

<form action="" method="get" name="searchForm" id="searchForm">
<div class="searchBar">
<jecs:label key="poMemberOrder.memberOrderNo"/>
<input name="memberOrderNo" type="text" size="10" value="${param.memberOrderNo }"/>
<c:if test="${sessionScope.sessionLogin.userType=='C'}">
<jecs:label key="miMember.memberNo"/>
<input name="sysUser.userCode" type="text" value="${param['sysUser.userCode'] }" size="12"/>
</c:if>
<jecs:list listCode="pmo.logtype" name="logType" value="${param.logType}" defaultValue=""/><jecs:label key="label.dateselect.ex"/>
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
			</script>
	
	<jecs:label key="jporder.isretreatorder"/>
	<jecs:list name="isRetreatOrder" listCode="po.isretreatorder" value="${param.isRetreatOrder}" defaultValue="0" showBlankLine="true"/>
	
	<jecs:locale key="stj.productflag"/>
	<jecs:list name="productFlag" listCode="stj.product.type" defaultValue="" value="${param.productFlag}" showBlankLine="true"></jecs:list>
	
	<input name="search" class="button" type="submit" value="<jecs:locale key="operation.button.search"/>" />

	</div>
	</form>
	
	<c:set var="footTotalVar">
	<tr>
		<td align="right" class="footer" colspan="7">
			<jecs:locale key="report.allTotal"/>
			(<b><fmt:formatNumber pattern="###,##0.00">${sumMap.amount + sumMap.jjamount}</fmt:formatNumber></b>)
		</td>
		<td align="right" class="footer">
			<b>
				<c:if test="${not empty sumMap.amount}">
					<fmt:formatNumber pattern="###,##0.00">${sumMap.amount}</fmt:formatNumber>
				</c:if>
				<c:if test="${empty sumMap.amount}">
					0.00
				</c:if>
			</b>
		</td>
	<jecs:power powerCode="po.pay.by.jj">
		<td align="right" class="footer"><b>
			<c:if test="${not empty sumMap.jjamount}">
				<fmt:formatNumber pattern="###,##0.00">${sumMap.jjamount}</fmt:formatNumber>
			</c:if>
			<c:if test="${empty sumMap.jjamount}">
				0.00
			</c:if></b>
		</td>
	</jecs:power>
		<%-- <td align="right" class="footer"><b>
			<c:if test="${not empty sumMap.discountAmount}">
				<fmt:formatNumber pattern="###,##0.00">${sumMap.discountAmount}</fmt:formatNumber>
			</c:if>
			<c:if test="${empty sumMap.discountAmount}">
				0.00
			</c:if></b>
		</td> --%>
		<td align="right" class="footer"><b>
			<c:if test="${not empty sumMap.pvamt}">
				<fmt:formatNumber pattern="###,##0.00">${sumMap.pvamt}</fmt:formatNumber>
			</c:if>
			<c:if test="${empty sumMap.pvamt}">
				0.00
			</c:if></b>
		</td>
		<td class="footer">&nbsp;</td>
		<td class="footer">&nbsp;</td>
		<td class="footer">&nbsp;</td>
		<td class="footer">&nbsp;</td>
		<td class="footer">&nbsp;</td>
		<td class="footer">&nbsp;</td>
		<td class="footer">&nbsp;</td>
		<td class="footer">&nbsp;</td>

		
	</tr>
	</c:set>
	
	<ec:table 
		tableId="jpoMemberOrderListTable"
		items="jpoMemberOrderList"
		var="jpoMemberOrder"
		autoIncludeParameters="true"
		action="${pageContext.request.contextPath}/jpoMemberOrderSTJStatistic.html"
		width="100%"
		rowsDisplayed="20" sortable="true" imagePath="${ctx }/images/extremetable/*.gif" footer="${footTotalVar}">
		<ec:row >
			<ec:exportXls fileName="poMemberOrderSTJList.xls"/>
	    	<ec:column property="memberorderno" title="poMemberOrder.memberOrderNo" />
			
	    	<c:if test="${sessionScope.sessionLogin.companyCode=='PH'}">
		    		<ec:column property="transactionNumber" title="Transaction Number" />
		    </c:if>
			<ec:column property="ordertype" title="poMemberOrder.orderType" styleClass="centerColumn" cell="com.joymain.jecs.util.ectable.EcTableCell">
				<jecs:code listCode="po.all.order_type" value="${jpoMemberOrder.ordertype}"/>
			</ec:column>
		
			<ec:column property="usercode" title="miMember.memberNo" />
			<ec:column property="username" title="bdCalculatingSubDetail.name" />
			<ec:column property="createtime" title="mi.createtime" />
		
			<ec:column property="papertype" title="miMember.papertype" cell="com.joymain.jecs.util.ectable.EcTableCell">
				 <jecs:code listCode="papertype" value="${jpoMemberOrder.papertype}"/>	
			</ec:column>
			<ec:column property="papernumber" title="miMember.papernumber" />
			
			<ec:column property="amount" title="busi.order.amount" />
			 	<ec:column property="jjamount" title="busi.order.jjAmount" />
			<ec:column property="pvamt" title="poMemberOrder.pvAmt" />
			<ec:column property="productflag" title="stjpo.productflag" />
			
			<ec:column property="movienum" title="po.movienum" />
			<c:if test="${sessionScope.sessionLogin.userType=='C'}">
				<ec:column property="isretreatorder" title="pd.ordertype.prRefund" sortable="false" styleClass="centerColumn" cell="com.joymain.jecs.util.ectable.EcTableCell">
				  <jecs:code listCode="po.isretreatorder" value="${jpoMemberOrder.isretreatorder}"/>
				</ec:column>
			</c:if>
			<ec:column property="orderusercode" title="fiPayData.createrName" />
			<ec:column property="checkusercode" title="fiTransferAccount.checkerName" />
			<ec:column property="checktime" title="logType.BC" styleClass="centerColumn" />
			<ec:column property="checkdate" title="logType.C" styleClass="centerColumn" />
			
			<ec:column property="firstname" title="shipping.firstName" styleClass="centerColumn" viewsDenied="html"/>
			<ec:column property="lastname" title="shipping.lastName" styleClass="centerColumn" viewsDenied="html"/>
			<ec:column property="province" title="shipping.province" styleClass="centerColumn" viewsDenied="html" cell="com.joymain.jecs.util.ectable.EcTableCell">
				<jecs:region regionType="p" regionId="${jpoMemberOrder.province}"></jecs:region>
			</ec:column>
			<ec:column property="city" title="shipping.city" styleClass="centerColumn" viewsDenied="html" cell="com.joymain.jecs.util.ectable.EcTableCell">
				<jecs:region regionType="c" regionId="${jpoMemberOrder.city}"></jecs:region>
			</ec:column>
			<ec:column property="district" title="shipping.district" styleClass="centerColumn" viewsDenied="html" cell="com.joymain.jecs.util.ectable.EcTableCell">
				<jecs:region regionType="d" regionId="${jpoMemberOrder.district}"></jecs:region>
			</ec:column>
			<ec:column property="address" title="shipping.address" styleClass="centerColumn" viewsDenied="html"/>
			<ec:column property="phone" title="miMember.phone" styleClass="centerColumn" viewsDenied="html"/>
			<ec:column property="mobiletele" title="miMember.mobiletele" styleClass="centerColumn" viewsDenied="html"/>
			
			<ec:column property="edit" title="title.more" sortable="false" style="width:100px;" viewsAllowed="html">
						
	 		<jecs:power powerCode="viewPoMemberOrder">
				<a href="jpoMemberOrderView.html?strAction=viewPoMemberOrder&moId=${jpoMemberOrder.moid}">
					<img border="0" src="images/newIcons/search_16.gif" alt="<jecs:locale key="function.menu.view"/>" />
				</a>
	 		</jecs:power>
	 		
		</ec:column>			
		</ec:row>
					<c:if test="${ROWCOUNT>0}">
							<c:if test="${ROWCOUNT%2!=0}"><tr id="tr${ROWCOUNT}" style="display:none" class="odddetail"></c:if>
							<c:if test="${ROWCOUNT%2==0}"><tr id="tr${ROWCOUNT}" style="display:none" class="evendetail"></c:if>
									<td colspan="13">
									<c:forEach items="${jpoMemberOrder.jpoMemberOrderList}" var="jpoMemberOrderDetail" varStatus="status">
										<c:if test="${!status.first}"><br/></c:if>
										&nbsp;&nbsp;<img src="<c:url value='/images/menu_tree/file12.gif'/>"/>&nbsp;&nbsp;
										<font color=#888888>
										[<font color="green">${jpoMemberOrderDetail.qty}</font>]
										${jpoMemberOrderDetail.jpmProductSaleTeamType.jpmProductSaleNew.jpmProduct.productNo}/${jpoMemberOrderDetail.jpmProductSaleTeamType.jpmProductSaleNew.productName}
										</font>
									</c:forEach>
								</td>
							</tr>
					</c:if>
	
	</ec:table>	
	
	<ec:table 
		tableId="pmProductListTable"
		items="pmProductList"
		var="pmProduct"
		autoIncludeParameters="true"
		action="${pageContext.request.contextPath}/jpoMemberOrderStatistic.html"
		width="100%"
		showPagination="false" sortable="false" imagePath="./images/extremetable/*.gif">
							<ec:row>
	
					<ec:exportXls fileName="jpoMemberOrderStatisticProduct.xls"/>
	
	    			<ec:column property="product_no" title="busi.product.productno"/>
	    			<ec:column property="product_name" title="pmProduct.productName"/>
	    			<ec:column property="qty" title="poOrder.count" styleClass="numberColumn" cell="number" format="###,###,##0" calc="total" calcTitle="poOrder.amtCount"/>
	    			<ec:column property="price" title="pd.price" styleClass="numberColumn" cell="number" format="###,###,##0.00" calc="total" calcTitle="poOrder.amtCount">
	    				<fmt:formatNumber pattern="###,###,##0.00" value="${pmProduct.price }"></fmt:formatNumber>
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

	
	var speed = 1000; 
	var wait = 10; 
	function updateinfo(obj){
		alert(1);
		while(wait>0)
			obj.value = "press"+wait;
			obj.disabled = false;
			window.setTimeout("updateinfo("+obj+")",speed);
			wait--;
		}
		obj.disabled = true;
		/*if(wait == 0){
		 	obj.value = "press";
			obj.disabled = false;
		}else{
			obj.value = "press"+wait;
			wait--;
		  	window.setTimeout("updateinfo("+obj+")",speed);
		}*/
	
	/**function updateinfo(obj){
	  if(wait == 0){
		  obj.value = "press";
		  obj.disabled = false;
	  }
	  else{
		  obj.value = "press"+wait;
		  wait--;
		  window.setTimeout("updateinfo("+obj+")",speed);
	  }
	}**/
	
	function exportOrder(){
		var proInfoObj = document.getElementById('proInfo');
		proInfoObj.value='0';
		document.searchForm.action='jpoMemberOrderStatisticAll.html';
		document.searchForm.submit();
	}
	<c:if test="${not empty param.province }">
		getCity();
	</c:if>

	/**order&info xls**/
	function exportOrderAndInfo(type){
		var logDesc = document.getElementById("logDesc").value;
		logDesc = logDesc.replace(/[ ]/g,""); 
		if(logDesc==null || logDesc==''){
			alert("<jecs:locale key='pdexportlog.beizhutishi'/>");
			document.getElementById("logDesc").focus();
			return false;
		}
		//waiting();
		var memberOrderNo = document.getElementsByName("memberOrderNo")[0].value;
		var userCode = document.getElementsByName("sysUser.userCode")[0].value;
		var logType = document.getElementsByName("logType")[0].value;
		var startLogTime = document.getElementsByName("startLogTime")[0].value;
		var endLogTime = document.getElementsByName("endLogTime")[0].value;
		var orderType = document.getElementsByName("orderType")[0].value;
		var status = document.getElementsByName("status")[0].value;
		var isRetreatOrder = document.getElementsByName("isRetreatOrder")[0].value;
		var province = document.getElementsByName("province")[0].value;
		var city = document.getElementsByName("city")[0].value;

		var district = document.getElementsByName("district")[0].value;
		var productType = document.getElementsByName("productType")[0].value;
		var payByCoin = document.getElementsByName("payByCoin")[0].value;
		var payByJJ = document.getElementsByName("payByJJ")[0].value;
		var memberType = document.getElementsByName("team")[0].value;
		var userCodeV = document.getElementsByName("userCodeV")[0].value;
		alert("<jecs:locale key='pdexportlog.info'/>");
		//waitingEnd();
		document.getElementById("downloadId").href='${pageContext.request.contextPath}/pdShipFees.html?strAction=pdExportLog&logType='+type;
		var str = pdWarehouseStockManager.exportOrderAndInfo(memberOrderNo,userCode,logType,startLogTime,endLogTime,
															orderType,status,isRetreatOrder,province,city,
															district,productType,payByCoin,payByJJ,memberType,userCodeV,type,logDesc,
															validateExportOrderAndInfo);
	}
	
	function validateExportOrderAndInfo(ret){
		//alert(ret);
		/**if(ret!=null && ret!=''){
			var url = '<a href=\'${pageContext.request.contextPath}/pdShipFees.html?strAction=pdExportLog\' target=\'blank\'><jecs:locale key="button.download"/></a>';
			document.getElementById('exportOrderAndInfoId').innerHTML=url;
			//waitingEnd();
		}**/
	}
		
</script>
<script>
var numw = 5; 
var numw2 = 5; 
function settime1(val,type) { 
	var logDesc = document.getElementById("logDesc").value;
	logDesc = logDesc.replace(/[ ]/g,""); 
	if(logDesc==null || logDesc==''){
		return false;
	}
	
	if (numw == 0) { 
		//val.removeAttribute("disabled"); 
		val.value='<jecs:locale key="toolbar.order.xls"/>_new'; 
		numw = 0; 
	} else { 
		val.setAttribute("disabled", true); 
		val.value='<jecs:locale key="pdexportlog.tishi"/>(' + numw + ")"; 
		numw--; 
	} 
	setTimeout(function() { 
		if(numw>=0){
			settime1(val) 
		}
	},1000)
}

function settime2(val,type) { 
	var logDesc = document.getElementById("logDesc").value;
	logDesc = logDesc.replace(/[ ]/g,""); 
	if(logDesc==null || logDesc==''){
		return false;
	}
	
	if (numw2 == 0) { 
		//val.removeAttribute("disabled"); 
		val.value='<jecs:locale key="toolbar.text.xls"/>_new'; 
		numw2 = 0; 
	} else { 
		val.setAttribute("disabled", true); 
		val.value='<jecs:locale key="pdexportlog.tishi"/>(' + numw2 + ")"; 
		numw2--; 
	} 
	setTimeout(function() { 
		if(numw2>=0){
			settime2(val) 
		}
	},1000)
} 
</script>