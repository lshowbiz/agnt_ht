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
				alt="<jecs:locale key="icon.warning"/>" class="icon" />
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

<div class="searchBar">

<form action="jpoMemberOrdersChangeDate.html" method="get" name="searchForm" id="searchForm">

<div class="new_searchBar">	
<jecs:label key="poMemberOrder.memberOrderNo"/>
<input name="memberOrderNo" type="text" size="10" value="${param.memberOrderNo }"/>
</div>
<div class="new_searchBar">	
<jecs:label key="miMember.memberNo"/>
<input name="sysUser.userCode" type="text" value="${param['sysUser.userCode'] }" size="12"/>
</div>
<div class="new_searchBar">	
<jecs:list listCode="pmo.logtype" name="logType" value="${param.logType}" defaultValue="" style="width:auto;"/>
 <input name="startLogTime" id="startDealDate" type="text" 
			value="${param.startLogTime }" style="cursor: pointer;width:70px;"
			onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
			-
			<input name="endLogTime" id="endDealDate" type="text"
			value="${param.endLogTime }" style="cursor: pointer;width:70px;"
			onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
			</div>
			
<div class="new_searchBar">				
<jecs:label key="poMemberOrder.orderType"/>
<jecs:list name="orderType" listCode="po.all.order_type" value="${param.orderType}" defaultValue="0" showBlankLine="true"/>
</div>
<input type="hidden" name="search" id="search" value="search"/>
<div class="new_searchBar">	
<button name="sear" class="btn btn_sele" style="width: auto;" type="submit"  ><jecs:locale key="operation.button.search"/></button>
</div>
</form>
</div>

<div class="searchBar" style="float:left;">
<div class="new_searchBar">
<form action="jpoMemberOrdersChangeDate.html" method="post" name="jpoMemberOrderCheck" id="jpoMemberOrderCheck">
<!-- <table class='grid_table'>
	<tr class='grid_span'>
		<td colspan='10'> -->
		
		<%--
		<jecs:power powerCode="editPayMode">
		<jecs:list listCode="po.paymode" name="editPayMode" value="${param.editPayMode}" defaultValue="0"/>
			<span onclick="javascript:$('strAction').value='editPayMode';readCheck();" style="cursor:pointer">
				<img alt="<jecs:locale key="function.menu.editPayMode"/>" src="images/newIcons/plus_16.gif" border="0" align="absmiddle">
				<font color=black><jecs:locale key="function.menu.editPayMode"/></font>
			</span>
		</jecs:power>
		--%>
	<jecs:power powerCode="UpdateAudits">
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		 <input name="logCreateTime" id="logCreateTime" type="text" 
			value="${param.logCreateTime }" style="cursor: pointer;width:auto;"
			onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})"/>
			
		<%-- <input id="logCreateTime" name="logCreateTime" type="text" value="${param.logCreateTime }" size="18" class="readonly" readonly/>
		<img src="./images/calendar/calendar7.gif" id="img_logCreateTime" style="cursor: pointer;" title="<jecs:locale key="Calendar.TT.SEL_DATE"/>"/> 
				<script type="text/javascript"> 
					Calendar.setup({
					inputField     :    "logCreateTime", 
					ifFormat       :    "%Y-%m-%d %H:%M:00",  
					button         :    "img_logCreateTime", 
					showsTime	   :	true,
					singleClick    :    true
					}); 
				</script> --%>
			<span onclick="javascript:$('strAction').value='UpdateAudits';readCheck();" style="cursor:pointer">
				<%-- <img alt="<jecs:locale key="button.editConfirmDate"/>" src="images/icons/edit.gif" border="0" align="absmiddle"> --%>
				<button class="btn btn_ins" >
					<jecs:locale key="button.editConfirmDate"/>
				</button>
			</span>
		</jecs:power>	
<!-- 		</td>
</tr>
</table> -->
<input type="hidden" name="strAction" id="strAction" value="" />
<input type="hidden" name="orderId" id="orderId" value=""/>
</form>
</div>
</div>

<ec:table 
	tableId="jpoMemberOrderListTable"
	items="jpoMemberOrderList"
	var="jpoMemberOrder"
	autoIncludeParameters="true"
	action="${pageContext.request.contextPath}/jpoMemberOrdersChangeDate.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="500" sortable="true" imagePath="./images/extremetable/*.gif">
				<ec:row onclick="showTR($('tr${ROWCOUNT}'));">
				<ec:column property="1" title="alCharacterKey.select1" sortable="false" styleClass="centerColumn">
					<c:if test="${jpoMemberOrder.locked!='1' && jpoMemberOrder.status=='2' }">
					<input type="checkbox" name="selectedId" id="selectedId" value="${jpoMemberOrder.moId}" class="checkbox"/>
					</c:if>
				</ec:column>
    			<ec:column property="memberOrderNo" title="poMemberOrder.memberOrderNo" sortable="false"/>
    			<ec:column property="orderType" title="poMemberOrder.orderType" sortable="false" styleClass="centerColumn">
    				<jecs:code listCode="po.all.order_type" value="${jpoMemberOrder.orderType}"/>
    			</ec:column>
    			<ec:column property="sysUser.userCode" title="miMember.memberNo" sortable="false"/>
    			<ec:column property="amount" title="busi.order.amount" sortable="false"/>
    			<ec:column property="pvAmt" title="poMemberOrder.pvAmt" sortable="false"/>
<%--
    			<ec:column property="payMode" title="fiPayAdvice.dealType" styleClass="centerColumn">
    				<jecs:code listCode="po.paymode" value="${jpoMemberOrder.payMode}"/>
    			</ec:column>
--%>
    			<ec:column property="checkTime" title="logType.BC" styleClass="centerColumn" sortable="false">
    				<fmt:formatDate value="${jpoMemberOrder.checkTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
    			</ec:column>
    			<ec:column property="checkDate" title="logType.C" styleClass="centerColumn" sortable="false">
    				<fmt:formatDate value="${jpoMemberOrder.checkDate }" pattern="yyyy-MM-dd"/>
    			</ec:column>
    			<ec:column property="checkDate" title="poMemberOrder.period" styleClass="centerColumn" sortable="false">
    				<jecs:period dateStr="${jpoMemberOrder.checkDate}"/>
    			</ec:column>
				<ec:column property="edit" title="title.operation" sortable="false" style="width:100px;" viewsAllowed="html">
					
    				<jecs:power powerCode="viewPoMemberOrder">
						<a href="jpoMemberOrderView.html?strAction=viewPoMemberOrder&moId=${jpoMemberOrder.moId}"><img border="0" src="images/newIcons/search_16.gif" alt="<jecs:locale key="function.menu.view"/>" /></a>
    				</jecs:power>
				</ec:column>
    			
    			<%--<ec:column property="submitStatus" title="poMemberOrder.checkStatus" styleClass="centerColumn">
    				<jecs:code listCode="po.checkstatus" value="${jpoMemberOrder.submitStatus}"/>
    			</ec:column>--%>
				
				</ec:row>
				<c:if test="${ROWCOUNT>0}">
						<c:if test="${ROWCOUNT%2!=0}"><tr id="tr${ROWCOUNT}" style="display:none" class="odddetail"></c:if>
						<c:if test="${ROWCOUNT%2==0}"><tr id="tr${ROWCOUNT}" style="display:none" class="evendetail"></c:if>
								<td colspan="8">
								<c:forEach items="${jpoMemberOrder.jpoMemberOrderList}" var="jpoMemberOrderDetail" varStatus="status">
									<c:if test="${!status.first}"><br/></c:if>
									&nbsp;&nbsp;<img src="<c:url value='/images/menu_tree/file12.gif'/>"/>&nbsp;&nbsp;<font color=#888888>[<font color="green">${jpoMemberOrderDetail.qty}</font>]${jpoMemberOrderDetail.jpmProductSaleTeamType.jpmProductSaleNew.jpmProduct.productNo}/${jpoMemberOrderDetail.jpmProductSaleTeamType.jpmProductSaleNew.productName}</font>
								</c:forEach>
							</td>
						</tr>
				</c:if>

</ec:table>	
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
			$('jpoMemberOrderCheck').submit();
			}
		}
	}else{
		alert('<jecs:locale key="poAssistMemberOrder.mustCheck"/>');
	}
}
</script>