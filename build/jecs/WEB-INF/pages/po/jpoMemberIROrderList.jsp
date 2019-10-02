<%@ include file="/common/taglibs.jsp"%>



<title><jecs:locale key="jpoMemberOrderList.title"/></title>
<content tag="heading"><jecs:locale key="jpoMemberOrderList.heading"/></content>
<meta name="menu" content="JpoMemberOrderMenu"/>

<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script> 

<form action="jpoMemberIROrders.html" method="get" name="searchForm" id="searchForm">
<div class="searchBar">
	<jecs:title key="poMemberOrder.memberOrderNo"/>
	<input name="memberOrderNo" type="text" size="10" value="${param.memberOrderNo }"/>
	
<c:if test="${sessionScope.sessionLogin.userType=='C'}">
	<jecs:title key="miMember.memberNo"/>
	<input name="sysUser.userCode" type="text" value="${param['sysUser.userCode'] }" size="12"/>
</c:if>

	<jecs:locale key="poMemberOrder.Times"/><jecs:title key="label.dateselect.ex"/>
	<input id="startOrderTime" name="startOrderTime" type="text" size="10" maxlength="10" value="${param.startOrderTime }"/>
				<img src="./images/calendar/calendar7.gif" id="img_startOperatorTime" style="cursor: pointer;" title="<jecs:locale key="Calendar.TT.SEL_DATE"/>"/> 
				<script type="text/javascript"> 
					Calendar.setup({
					inputField     :    "startOrderTime", 
					ifFormat       :    "%Y-%m-%d",  
					button         :    "img_startOperatorTime", 
					singleClick    :    true
					}); 
				</script> - 
				<input id="endOrderTime" name="endOrderTime" type="text" size="10" maxlength="10" value="${param.endOrderTime }"/>
				<img src="./images/calendar/calendar7.gif" id="img_endOperatorTime" style="cursor: pointer;" title="<jecs:locale key="Calendar.TT.SEL_DATE"/>"/> 
				<script type="text/javascript"> 
					Calendar.setup({
					inputField     :    "endOrderTime", 
					ifFormat       :    "%Y-%m-%d",  
					button         :    "img_endOperatorTime", 
					singleClick    :    true
					}); 
				</script>	
				
<c:if test="${sessionLogin.userType=='C' }">
	<jecs:locale key="logType.BC"/>
	<input id="startOrderCheckTime" name="startOrderCheckTime" type="text" size="10" maxlength="10" value="${param.startOrderCheckTime }"/>
				<img src="./images/calendar/calendar7.gif" id="img_startOrderCheckTime" style="cursor: pointer;" title="<jecs:locale key="Calendar.TT.SEL_DATE"/>"/> 
				<script type="text/javascript"> 
					Calendar.setup({
					inputField     :    "startOrderCheckTime", 
					ifFormat       :    "%Y-%m-%d",  
					button         :    "img_startOrderCheckTime", 
					singleClick    :    true
					}); 
				</script> - 
				<input id="endOrderCheckTime" name="endOrderCheckTime" type="text" size="10" maxlength="10" value="${param.endOrderCheckTime }"/>
				<img src="./images/calendar/calendar7.gif" id="img_endOrderCheckTime" style="cursor: pointer;" title="<jecs:locale key="Calendar.TT.SEL_DATE"/>"/> 
				<script type="text/javascript"> 
					Calendar.setup({
					inputField     :    "endOrderCheckTime", 
					ifFormat       :    "%Y-%m-%d",  
					button         :    "img_endOrderCheckTime", 
					singleClick    :    true
					}); 
				</script>	
</c:if>

	<jecs:title key="pocounterorder.csstatus"/>
	<jecs:list name="status" listCode="po.status" value="${param.status}" defaultValue="0" showBlankLine="true"/>
	
	<input name="search" class="button" type="submit" value="<jecs:locale key="operation.button.search"/>" />

<jecs:power powerCode="addPoMemberIROrder">
	<input name="add" class="button" type="button" onclick="location.href='<c:url value="/editJpoMemberIROrder.html?strAction=addPoMemberIROrder"/>'" value="<jecs:locale  key='member.new.num'/>" />
</jecs:power>
</div>
</form>

<ec:table 
	tableId="jpoMemberOrderListTable"
	items="jpoMemberOrderList"
	var="jpoMemberOrder"
	autoIncludeParameters="true"
	action="${pageContext.request.contextPath}/jpoMemberIROrders.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
				<ec:row onclick="showTR($('tr${ROWCOUNT}'));">
    			<ec:column property="memberOrderNo" title="poMemberOrder.memberOrderNo" />
    			<ec:column property="orderType" title="poMemberOrder.orderType" styleClass="centerColumn">
    				<jecs:code listCode="po.all.order_type" value="${jpoMemberOrder.orderType}"/>
    			</ec:column>
    			<ec:column property="sysUser.userCode" title="miMember.memberNo" />
    			<ec:column property="amount" title="busi.order.amount" styleClass="numberColumn" />
    			<jecs:power powerCode="po.pay.by.jj">
    			<ec:column property="jjAmount" title="busi.order.jjAmount" />
</jecs:power>
<%--
    			<ec:column property="payMode" title="fiPayAdvice.dealType" styleClass="centerColumn">
    				<jecs:code listCode="po.paymode" value="${jpoMemberOrder.payMode}"/>
    			</ec:column>
--%>
<c:if test="${sessionScope.sessionLogin.userType=='C'}">
				<ec:column property="1" title="pd.ordertype.prRefund" styleClass="centerColumn">
					<c:set var="jprRefund" value="0" />
					<c:forEach items="${jpoMemberOrder.jprRefund}" var="jprRefundList" varStatus="status">
						<c:if test="${jprRefundList.refundStatus==2 }">
							<c:set var="jprRefund" value="1" />
						</c:if>
					</c:forEach>
					
					<c:if test="${jprRefund == 1 }">
						<jecs:locale key="yesno.yes"/>
					</c:if>
					<c:if test="${jprRefund == 0 }">
						<jecs:locale key="yesno.no"/>
					</c:if>
				</ec:column>
</c:if>
    			<ec:column property="status" title="pd.checkstatus" styleClass="centerColumn">
    				<jecs:code listCode="po.status" value="${jpoMemberOrder.status}"/>
    			</ec:column>
				<ec:column property="edit" title="title.operation" sortable="false" style="width:100px;" viewsAllowed="html">
					
    				<jecs:power powerCode="viewPoMemberOrder">
						<a href="jpoMemberOrderView.html?strAction=viewPoMemberOrder&moId=${jpoMemberOrder.moId}"><img border="0" src="images/newIcons/search_16.gif" alt="<jecs:locale key="function.menu.view"/>" /></a>
    				</jecs:power>
					<c:if test="${jpoMemberOrder.status=='1' && jpoMemberOrder.isPay!='1' && jpoMemberOrder.submitStatus=='1'}">
   		<jecs:power powerCode="editPoMemberIROrder">
							<img src="<c:url value='/images/newIcons/pencil_16.gif'/>" 
								onclick="javascript:editJpoMemberIROrder('${jpoMemberOrder.moId}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
		</jecs:power>
					</c:if>
					<jecs:power powerCode="jfiPayByCoin">
					<c:if test="${jpoMemberOrder.status=='1'}">
							<img src="<c:url value='/images/newIcons/coins.gif'/>" 
								onclick="window.location='jfiPayByCoin.html?strAction=jfiPayByCoin&orderId=${jpoMemberOrder.moId}';"
								style="cursor: pointer;" title="<jecs:locale key="btn.pay.now"/>"/> 
					</c:if>
					</jecs:power>
				</ec:column>
    			
    			<%--<ec:column property="submitStatus" title="poMemberOrder.checkStatus" styleClass="centerColumn">
    				<jecs:code listCode="po.checkstatus" value="${jpoMemberOrder.submitStatus}"/>
    			</ec:column>--%>
				
				</ec:row>
				<c:if test="${ROWCOUNT>0}">
						<c:if test="${ROWCOUNT%2!=0}"><tr id="tr${ROWCOUNT}" style=
						"display:none" class="odddetail"></c:if>
						<c:if test="${ROWCOUNT%2==0}"><tr id="tr${ROWCOUNT}" style="display:none" class="evendetail"></c:if>
								<td colspan="5">
								<c:forEach items="${jpoMemberOrder.jpoMemberOrderList}" var="jpoMemberOrderDetail" varStatus="status">
									<c:if test="${!status.first}"><br/></c:if>
									&nbsp;&nbsp;<img src="<c:url value='/images/menu_tree/file12.gif'/>"/>&nbsp;&nbsp;<font color=#888888>[<font color="green">${jpoMemberOrderDetail.qty}</font>]${jpoMemberOrderDetail.jpmProductSale.jpmProduct.productNo}/${jpoMemberOrderDetail.jpmProductSale.productName}</font>
								</c:forEach>
							</td>
						</tr>
				</c:if>

</ec:table>	

<script type="text/javascript">

   function editJpoMemberIROrder(moId){
   		<jecs:power powerCode="editPoMemberIROrder">
					window.location="editJpoMemberIROrder.html?strAction=editPoMemberIROrder&moId="+moId;
			</jecs:power>
		}

</script>
