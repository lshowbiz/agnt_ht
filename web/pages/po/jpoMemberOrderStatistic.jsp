<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" contentType="text/html; charset=utf-8"%>
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
<script type='text/javascript' src='<c:url value="/dwr/interface/jpoMemberOrderManager.js"/>'></script>

<input type="hidden" name="userCodeV" value="${sessionScope.sessionLogin.userCode }"/>

<form action="" method="get" name="searchForm" id="searchForm">
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
<div class="new_searchBar">   
<jecs:label key="miMember.papernumber" />
	<input name="sysUser.jmiMember.papernumber" value="${param['sysUser.jmiMember.papernumber'] }"/>
	</div>
	
</c:if>
<div class="new_searchBar">  
<jecs:list listCode="pmo.logtype" name="logType" value="${param.logType}" defaultValue="" style="width:auto;"/> 

			<input name="startLogTime" id="startDealDate" type="text" 
			value="${param.startLogTime }" style="cursor: pointer;width:77px;"
			onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
			-
			<input name="endLogTime" id="endDealDate" type="text"
			value="${param.endLogTime }" style="cursor: pointer;width:77px;"
			onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
</div>
			<%-- <jecs:label key="label.dateselect.ex"/>
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
<div class="new_searchBar">  	
	<jecs:label key="poMemberOrder.orderType"/>
	<jecs:list name="orderType" listCode="po.all.order_type" value="${param.orderType}" defaultValue="0" showBlankLine="true"/>
	</div>
<div class="new_searchBar">  	
	<jecs:label key="pocounterorder.csstatus"/>
	<jecs:list name="status" listCode="po.status" value="${param.status}" defaultValue="0" showBlankLine="true"/>
	</div>
	<div class="new_searchBar">  
	<jecs:label key="jporder.isretreatorder"/>
	<jecs:list name="isRetreatOrder" listCode="po.isretreatorder" value="${param.isRetreatOrder}" defaultValue="0" showBlankLine="true"/>
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
					<c:if test="${alcity != null}">
						<option value="${alcity.cityId }" selected="selected">${alcity.cityName }</option>
					</c:if>
				</select>	
				</div>
	<div class="new_searchBar">  
	<jecs:label key="shipping.district"/>
		        <select name="district" id="district" >
					<option value=""><jecs:locale key="list.please.select"/></option>
					<c:if test="${aldistrict != null}">
						<option value="${aldistrict.districtId }" selected="selected">${aldistrict.districtName }</option>
					</c:if>
				</select>
				</div>	
	</jecs:power>
	<div class="new_searchBar">  
	<jecs:power powerCode="po.product_type">
		<jecs:label key="jpoMemberOrder.productType"/>
		<jecs:list name="productType" listCode="po.producttype" value="${param.productType}" defaultValue="ALL"/>
	</jecs:power>
	</div>
	<div class="new_searchBar">  
		<jecs:label key="jfiPayByCoin.Button"/>
		<jecs:list name="payByCoin" listCode="yesno" value="${param.payByCoin}" defaultValue="" showBlankLine="true"/>
		 </div>
	<div class="new_searchBar">  
	<jecs:power powerCode="po.pay.by.jj">
		<jecs:label key="jfiPayByJJ.Button"/>
		<jecs:list name="payByJJ" listCode="yesno" value="${param.payByJJ}" defaultValue="" showBlankLine="true"/>
	</jecs:power>
	</div>
	<div class="new_searchBar">  
	<!-- &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; -->
	<jecs:title key="jpmProductSaleTeamType.teamCode"/>
	<jecs:list name="team" listCode="membertype" value="${param.team}" defaultValue="" showBlankLine="true"/>
	</div>
	
	<div class="new_searchBar">  
	<!-- &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; -->
	<jecs:label key="po.ismobile"/>
	<jecs:list name="isMobile" listCode="po.ismobile" defaultValue="" value="${param.isMobile}" showBlankLine="true"></jecs:list>
	</div>
	
	<div class="new_searchBar">  
	<!-- &nbsp;&nbsp;&nbsp;&nbsp; -->
	<!-- dengmh update by 2016/5/17 Filter returnablestatus value is 1 -->
	<jecs:label key="po.returnablestatus"/>
	<jecs:list name="returnableStatus" listCode="po.returnablestatus" defaultValue="" value="${param.returnableStatus}" showBlankLine="true"></jecs:list>
	</div>
	
	<div class="new_searchBar">  
	<!-- &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; -->
	<jecs:label key="po.exchangeFlag"/>
	<jecs:list name="exchangeFlag" listCode="yesno" value="${param.exchangeFlag}" defaultValue="" showBlankLine="true"/>
	</div>
	<!--
	<jecs:locale key="stj.productflag"/>
	<jecs:list name="productFlag" listCode="stj.product.type" defaultValue="" value="${param.productFlag}" showBlankLine="true"></jecs:list>
	-->
	<div class="new_searchBar">  
	<button name="search" class="btn btn_sele" type="submit" >
		<jecs:locale key="operation.button.search"/>
	</button>
	<jecs:power powerCode="jpoMemberOrderStatisticAll">
	<input type="hidden" id="proInfo" name="proInfo" value="1"/>
	
	<button name="export" class="btn btn_ins" type="button" onclick="searchForm.action='jpoMemberOrderStatisticAll.html';searchForm.submit();searchForm.action='';" >
		<jecs:locale key="toolbar.text.xls"/>
	</button>
	<button name="export" class="btn btn_ins" type="button" onclick="exportOrder();searchForm.action='';" >
		<jecs:locale key="toolbar.order.xls"/>
	</button>
	<button name="import" class="btn btn_long" type="button" onclick="location.href='batchUpdateMemberOrder.html';" >
		<jecs:locale key="order.returnableStatus.import"/>
	</button>
	
	<!-- 
	&nbsp;
	<br/>
	<jecs:locale key="poMemberOrder.remark"/>:
	<input name="logDesc" id="logDesc" type="text" size="60" />
	<input name="export2" class="button" type="button" onclick="settime2(this,2);exportOrderAndInfo(2);" value="<jecs:locale key="toolbar.text.xls"/>_new" />
	<input name="export3" class="button" type="button" onclick="settime1(this,1);exportOrderAndInfo(1);" value="<jecs:locale key="toolbar.order.xls"/>_new" />
	
	<font id="exportOrderAndInfoId"></font>
	<a href="${pageContext.request.contextPath}/pdShipFees.html?strAction=pdExportLog" id="downloadId" target="_blank"><font color="red"><jecs:locale key="button.download"/></font></a>
	 --> 
	</jecs:power>
	</div>
	
	</div>
	
	</form>
	
	<c:set var="footTotalVar">
	<tr>
		<td align="right" class="footer" colspan="6">
			<jecs:locale key="report.allTotal"/>
			(<b><fmt:formatNumber pattern="###,##0.00">${sumMap.amount + sumMap.jjAmount + sumMap.productPointAmount  + sumMap.cpValue}</fmt:formatNumber></b>)
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
			<c:if test="${not empty sumMap.jjAmount}">
				<fmt:formatNumber pattern="###,##0.00">${sumMap.jjAmount}</fmt:formatNumber>
			</c:if>
			<c:if test="${empty sumMap.jjAmount}">
				0.00
			</c:if></b>
		</td>
	</jecs:power>

		<td align="right" class="footer"><b>
			<c:if test="${not empty sumMap.discountAmount}">
				<fmt:formatNumber pattern="###,##0.00">${sumMap.discountAmount}</fmt:formatNumber>
			</c:if>
			<c:if test="${empty sumMap.discountAmount}">
				0.00
			</c:if></b>
		</td>
		<td align="right" class="footer"><b>
			<c:if test="${not empty sumMap.productPointAmount}">
				<fmt:formatNumber pattern="###,##0.00">${sumMap.productPointAmount}</fmt:formatNumber>
			</c:if>
			<c:if test="${empty sumMap.productPointAmount}">
				0.00
			</c:if></b>
		</td>
		<td align="right" class="footer"><b>
			<c:if test="${not empty sumMap.pvAmt}">
				<fmt:formatNumber pattern="###,##0.00">${sumMap.pvAmt}</fmt:formatNumber>
			</c:if>
			<c:if test="${empty sumMap.pvAmt}">
				0.00
			</c:if></b>
		</td>
		<td align="right" class="footer"><b>
			<c:if test="${not empty sumMap.cpValue}">
				<fmt:formatNumber pattern="###,##0.00">${sumMap.cpValue}</fmt:formatNumber>
			</c:if>
			<c:if test="${empty sumMap.cpValue}">
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
		action="${pageContext.request.contextPath}/jpoMemberOrderStatistic.html"
		width="100%"
		retrieveRowsCallback="limit"
		rowsDisplayed="20" sortable="true" imagePath="${ctx }/images/extremetable/*.gif" footer="${footTotalVar}">
		<ec:row onclick="showTR($('tr${ROWCOUNT}'));">
	    	<ec:column property="memberOrderNo" title="poMemberOrder.memberOrderNo" />
	    	<c:if test="${sessionScope.sessionLogin.companyCode=='PH'}">
		    		<ec:column property="transactionNumber" title="Transaction Number" />
		    </c:if>
			<ec:column property="orderType" title="poMemberOrder.orderType" style="width:90px;">
				<jecs:code listCode="po.all.order_type" value="${jpoMemberOrder.orderType}"/>
			</ec:column>
			<ec:column property="isMobile" title="po.ismobile" styleClass="centerColumn">
				<jecs:code listCode="po.ismobile" value="${jpoMemberOrder.isMobile}"/>
			</ec:column>
			<ec:column property="sysUser.userCode" title="miMember.memberNo" />
			<ec:column property="sysUser.userName" title="bdCalculatingSubDetail.name" />
			<ec:column property="sysUser.jmiMember.memberType" title="miMember.memberType" >
				<jecs:code listCode="teamcode" value="${jpoMemberOrder.teamCode}" />
			</ec:column>
			<ec:column property="amount" title="busi.order.amount" />
			<jecs:power powerCode="po.pay.by.jj">
			    			<ec:column property="jjAmount" title="busi.order.jjAmount" />
			</jecs:power>
	
			<ec:column property="discountAmount" title="poMemberOrder.discountAmount" />
			
			<%-- add by lihao ,会员订单页面添加产品积分 --%>
	
			<%-- <ec:column property="productPointAmount" title="fiProductPointTemp.productamt"/> --%>
			<ec:column property="productPointAmount" title="fiProductPointTemp.productamt">
			<c:if test="${not empty jpoMemberOrder.productPointAmount}">
				${jpoMemberOrder.productPointAmount}
			</c:if>
			<c:if test="${empty jpoMemberOrder.productPointAmount}">
				0
			</c:if>
			</ec:column>
			
			<ec:column property="pvAmt" title="poMemberOrder.pvAmt" />
			<ec:column property="cpValue" title="代金券" />
			<c:if test="${sessionScope.sessionLogin.userType=='C'}">
				<ec:column property="isRetreatOrder" title="pd.ordertype.prRefund" sortable="false" styleClass="centerColumn" cell="com.joymain.jecs.util.ectable.EcTableCell">
				  <jecs:code listCode="po.isretreatorder" value="${jpoMemberOrder.isRetreatOrder}"/>
				</ec:column>
			</c:if>
			
			<ec:column property="exchangeFlag" title="po.exchangeFlag" styleClass="centerColumn"  cell="com.joymain.jecs.util.ectable.EcTableCell">
				<c:choose>
					<c:when test="${fn:trim(jpoMemberOrder.exchangeFlag) eq '1'}">
						<font color="red"><jecs:locale key="po.exchangeFlag.1"/></font>
					</c:when>
					<c:otherwise>
						<jecs:locale key="po.exchangeFlag.0"/>
					</c:otherwise>
				</c:choose> 
			</ec:column>
			
			<ec:column property="status" title="pd.checkstatus" styleClass="centerColumn"  cell="com.joymain.jecs.util.ectable.EcTableCell">
				<%-- <jecs:code listCode="po.status" value="${jpoMemberOrder.status}"/>--%>
				<c:choose>
					<c:when test="${fn:trim(jpoMemberOrder.status) eq '1'}">
						<jecs:locale key="busi.mi.check.0"/>
					</c:when>
					<c:when test="${fn:trim(jpoMemberOrder.status) eq '2'}">
						<jecs:locale key="amannounce.status.1"/>
					</c:when>
					<c:otherwise>
						<jecs:locale key="operation.button.cancels"/>
					</c:otherwise>
				</c:choose> 
			</ec:column>
			<ec:column property="checkDate" title="poMemberOrder.period" styleClass="centerColumn">
				<jecs:period dateStr="${jpoMemberOrder.checkDate}"/>
			</ec:column>
			<ec:column property="orderUserCode" title="fiPayData.createrName" styleClass="centerColumn" viewsDenied="html"/>
			<ec:column property="returnableStatus" title="shipping.returnableStatus" styleClass="centerColumn">
				<c:choose>
					<c:when test="${fn:trim(jpoMemberOrder.returnableStatus) eq '1'}">
						<jecs:locale key="shipping.returnable"/>
					</c:when>
					<c:otherwise>
						<jecs:locale key="shipping.returnablezc"/>
					</c:otherwise>
				</c:choose> 
			</ec:column>
			<ec:column property="checkTime" title="logType.BC" styleClass="centerColumn"/>
			<ec:column property="checkDate" title="logType.C" styleClass="centerColumn"/>
			<ec:column property="firstName" title="shipping.firstName" styleClass="centerColumn" viewsDenied="html"/>
			<ec:column property="lastName" title="shipping.lastName" styleClass="centerColumn" viewsDenied="html"/>
			<ec:column property="province" title="shipping.province" styleClass="centerColumn" viewsDenied="html"/>
			<ec:column property="city" title="shipping.city" styleClass="centerColumn" viewsDenied="html"/>
			<ec:column property="district" title="shipping.district" styleClass="centerColumn" viewsDenied="html"/>
			<ec:column property="address" title="shipping.address" styleClass="centerColumn" viewsDenied="html"/>
			<ec:column property="phone" title="miMember.phone" styleClass="centerColumn" viewsDenied="html"/>
			<ec:column property="mobiletele" title="miMember.mobiletele" styleClass="centerColumn" viewsDenied="html"/>
			<ec:column property="remark" title="busi.common.remark" style="width:80px;"/>
			
			<ec:column property="edit" title="title.operation" sortable="false" style="width:80px;" viewsAllowed="html" >
						
	 		<jecs:power powerCode="viewPoMemberOrder">
				<a href="jpoMemberOrderView.html?strAction=viewPoMemberOrder&moId=${jpoMemberOrder.moId}">
					<img border="0" src="images/newIcons/search_16.gif" alt="<jecs:locale key="function.menu.view"/>" />
				</a>
				
	 		</jecs:power>
		    
	 		<jecs:power powerCode="jpoRecommendPHInvoice">
	 			<c:if test="${jpoMemberOrder.orderType=='1'||jpoMemberOrder.orderType=='2'}">
	 				<a href="jpoRecommendPHInvoice.html?strAction=jpoRecommendPHInvoice&moId=${jpoMemberOrder.moId}" target="_blank">
	 					<img border="0" src="images/icons/printer.gif" alt="<jecs:locale key="button.print"/>" />
	 				</a>
	 			</c:if>
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
	
				<%-- 	<ec:exportXls fileName="jpoMemberOrderStatisticProduct.xls"/> --%>
	
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
		console.log(orderType)
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

	function orderSelfHelpExchangeYes(memberOrderNo){
		if(confirm('<jecs:locale key="jpo.selfhelpexchangeno.yes"/>')){
			jpoMemberOrderManager.orderSelfHelpExchangeYes(memberOrderNo,reResult);
		}
		
	}

	function orderSelfHelpExchangeNo(memberOrderNo){
		if(confirm('<jecs:locale key="jpo.selfhelpexchangeno.no"/>')){
			jpoMemberOrderManager.orderSelfHelpExchangeNo(memberOrderNo,reResult);
		}
	}

	function reResult(){
		 window.location.reload();
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