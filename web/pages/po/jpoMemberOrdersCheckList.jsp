<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jpoMemberOrderList.title"/></title>
<content tag="heading"><jecs:locale key="jpoMemberOrderList.heading"/></content>
<meta name="menu" content="JpoMemberOrderMenu"/>

<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script> 

<c:if test="${not empty errorList || not empty successList }">
	<div class="error" id="errorMessages">
		<c:forEach var="error" items="${successList}">
			<img src="<c:url value="images/newIcons/tick_16.gif"/>"
				alt="<jecs:locale key="opration.notice.js.orderNo.auditSuccess"/>" class="icon" />
			<c:out value="${error}" escapeXml="false" />
			<br />
		</c:forEach>
		<c:forEach var="error" items="${errorList}">
			<img src="<c:url value="images/newIcons/warning_16.gif"/>"
				alt="<jecs:locale key="icon.warning"/>" class="icon" />
			<c:out value="${error}" escapeXml="false" />
			<br />
		</c:forEach>
	</div>
</c:if>

<form action="jpoMemberOrdersCheck.html" method="get" name="searchForm" id="searchForm">
<div class="searchBar">
<div class="new_searchBar">	
<jecs:title key="poMemberOrder.memberOrderNo"/>
<input name="memberOrderNo" size="10" type="text" value="${param.memberOrderNo }"/>
</div>
<div class="new_searchBar">	
<jecs:title key="miMember.memberNo"/>
<input name="sysUser.userCode" type="text" value="${param['sysUser.userCode'] }" size="12"/>
</div>

<jecs:power powerCode="po.check.petName">
<div class="new_searchBar">	
<jecs:title key="miMember.firstNameKana"/>
<input name="sysUser.jmiMember.firstNameKana" type="text" value="${param['sysUser.jmiMember.firstNameKana'] }" size="12"/>
</div>
<div class="new_searchBar">		
<jecs:title key="miMember.lastNameKana"/>
<input name="sysUser.jmiMember.lastNameKana" type="text" value="${param['sysUser.jmiMember.lastNameKana'] }" size="12"/>
</div>
</jecs:power>
<div class="new_searchBar">	
<jecs:title key="poMemberOrder.orderType"/>
<jecs:list name="orderType" listCode="po.all.order_type" value="${param.orderType}" defaultValue="0" showBlankLine="true"/>
</div>
<div class="new_searchBar">	
<jecs:title key="busi.poMemberOrder.dealType"/>
<jecs:list name="isPay" listCode="yesno" value="${param.isPay}" defaultValue="" showBlankLine="true"/>
</div>
<div class="new_searchBar">	
<jecs:title key="jfiBankbook.mode"/>
<jecs:list name="mode" listCode="memberorder.mode" value="${param.mode}" defaultValue="1"/>
</div>
<div class="new_searchBar">	
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

<input name="search" type="hidden" value="<jecs:locale key="operation.button.search"/>" />
<button name="search1" class="btn btn_sele" style="width: auto;" type="submit" >
	<jecs:locale key="operation.button.search"/>
</button>


<button name="search2" class="btn btn_ins" style="width: auto;" type="button" onclick="javascript:$('submitType').value='2';readCheck();" >
	<jecs:locale key="button.audit"/>
</button>
<input type="hidden" name="submitType" id="submitType" value="" />
<input type="hidden" name="orderId" id="orderId" value=""/>
</div>
</div>
</form>

<ec:table 
	tableId="jpoMemberOrderListTable"
	items="jpoMemberOrderList"
	var="jpoMemberOrder"
	autoIncludeParameters="true"
	action="${pageContext.request.contextPath}/jpoMemberOrdersCheck.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
		<ec:row onclick="showTR($('tr${ROWCOUNT}'));">
		<ec:column property="1" title="alCharacterKey.select1" sortable="false" styleClass="centerColumn">
			<c:if test="${jpoMemberOrder.locked!='1' && jpoMemberOrder.status=='1' || jpoMemberOrder.status=='3' || jpoMemberOrder.status=='4'}">
				<input type="checkbox" name="selectedId" id="selectedId" value="${jpoMemberOrder.moId}" class="checkbox"/>
			</c:if>
		</ec:column>
    	<ec:column property="memberOrderNo" title="poMemberOrder.memberOrderNo" />
    	<ec:column property="orderType" title="poMemberOrder.orderType" styleClass="centerColumn">
    		<jecs:code listCode="po.all.order_type" value="${jpoMemberOrder.orderType}"/>
    	</ec:column>
    	<ec:column property="sysUser.userCode" title="miMember.memberNo" />
    	<ec:column property="amount" title="busi.order.amount" styleClass="numberColumn" />
    	<ec:column property="pvAmt" title="poMemberOrder.pvAmt" styleClass="numberColumn" />
    	<ec:column property="payMode" title="poMemberOrder.payMode" styleClass="centerColumn">
    		<jecs:code listCode="po.paymode" value="${jpoMemberOrder.payMode}"/>
    	</ec:column>
    	<ec:column property="status" title="pd.checkstatus" styleClass="centerColumn">
    		<jecs:code listCode="po.status" value="${jpoMemberOrder.status}"/>
    	</ec:column>
		<ec:column property="edit" title="title.operation" sortable="false" style="width:100px;" viewsAllowed="html">
    		<jecs:power powerCode="viewPoMemberOrder">
				<a href="jpoMemberOrderView.html?strAction=viewPoMemberOrder&moId=${jpoMemberOrder.moId}"><img border="0" src="images/newIcons/search_16.gif" alt="<jecs:locale key="function.menu.view"/>" /></a>
    		</jecs:power>
		</ec:column>
		</ec:row>

		<c:if test="${ROWCOUNT>0}">
			<c:if test="${ROWCOUNT%2!=0}"><tr id="tr${ROWCOUNT}" style="display:none" class="odddetail"></c:if>
			<c:if test="${ROWCOUNT%2==0}"><tr id="tr${ROWCOUNT}" style="display:none" class="evendetail"></c:if>
				<td colspan="9">
					<c:forEach items="${jpoMemberOrder.jpoMemberOrderList}" var="jpoMemberOrderDetail" varStatus="status">
						<c:if test="${!status.first}"><br/></c:if>
						&nbsp;&nbsp;<img src="<c:url value='/images/menu_tree/file12.gif'/>"/>&nbsp;&nbsp;<font color=#888888>[<font color="green">${jpoMemberOrderDetail.qty}</font>]${jpoMemberOrderDetail.jpmProductSaleTeamType.jpmProductSaleNew.jpmProduct.productNo}/${jpoMemberOrderDetail.jpmProductSaleTeamType.jpmProductSaleNew.productName}</font>
					</c:forEach>
				</td>
			</tr>
		</c:if>

</ec:table>	
    <% 
    if(request.getAttribute("exception")!=null){
    	Exception exception = (Exception) request.getAttribute("exception");
    %>
<div id="main" style="display:none">
	<h1><jecs:locale key="errorPage.heading"/></h1>
    <pre><% exception.printStackTrace(new java.io.PrintWriter(out)); %></pre>
</div>
    <%
    }
    %>
    <% 
    if(request.getAttribute("appException")!=null){
    	com.joymain.jecs.util.exception.AppException appException = (com.joymain.jecs.util.exception.AppException) request.getAttribute("appException");
    %>
<div id="main" style="display:none">
	<h1><jecs:locale key="errorPage.heading"/></h1>
    <pre><% appException.printStackTrace(new java.io.PrintWriter(out)); %></pre>
</div>
    <%
    }
    %>
<script type="text/javascript">

function readCheck(poMemberOrderCheck){
	var elements=document.getElementsByName("selectedId");
	$('orderId').value = '';
	for(var i=0;i<elements.length;i++){
		if(elements[i].checked){
			$('orderId').value += elements[i].value + ",";
		}
	}
	if($('orderId').value !=''){
		if(confirm("<jecs:locale key="aiAgent.confirm"/>")){
			if(isFormPosted()){
			$('searchForm').action = 'jpoMemberOrdersCheck.html?strAction=auditStr';
			$('searchForm').method="post";
			$('searchForm').submit();
			}
		}
	}else{
		alert('<jecs:locale key="poAssistMemberOrder.mustCheck"/>');
	}
}
</script>