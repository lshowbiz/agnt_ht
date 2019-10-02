<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jpoMemberOrderList.title"/></title>
<content tag="heading"><jecs:locale key="jpoMemberOrderList.heading"/></content>
<meta name="menu" content="JpoMemberOrderMenu"/>

<c:set var="buttons">
		<jecs:power powerCode="addJpoMemberOrder">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editJpoMemberOrder.html"/>?strAction=addJpoMemberOrder'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power>
</c:set>
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>
<ec:table 
	tableId="jpoMemberOrderListTable"
	items="jpoMemberOrderList"
	var="jpoMemberOrder"
	action="${pageContext.request.contextPath}/jpoMemberOrders.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/newIcons/pencil_16.gif'/>" 
								onclick="javascript:editJpoMemberOrder('${jpoMemberOrder.moId}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="memberOrderNo" title="poMemberOrder.memberOrderNo" />
    			<ec:column property="orderType" title="poMemberOrder.orderType" styleClass="centerColumn">
    				<c:if test='${jpoMemberOrder.orderType==1 }'>
    					<font color='#009900'><jecs:code listCode="po.order_type" value="1"/></font>
    				</c:if>
    				<c:if test='${jpoMemberOrder.orderType==2 }'>
    					<font color='#006666'><jecs:code listCode="po.order_type" value="2"/></font>
    				</c:if>
    				<c:if test='${jpoMemberOrder.orderType==3 }'>
    					<font color='#0033CC'><jecs:code listCode="po.order_type" value="3"/></font>
    				</c:if>
    				<c:if test='${jpoMemberOrder.orderType==4 }'>
    					<font color='#FF00FF'><jecs:code listCode="po.order_type" value="4"/></font>
    				</c:if>
    			</ec:column>
    			<ec:column property="sysUser.userCode" title="miMember.memberNo" />
    			<ec:column property="amount" title="busi.order.amount" />
    			<ec:column property="pvAmt" title="poMemberOrder.pvAmt" />
<%--
    			<ec:column property="payMode" title="fiPayAdvice.dealType" styleClass="centerColumn">
    				<c:if test='${jpoMemberOrder.payMode==1 }'>
    					<font color='#009900'><jecs:code listCode="po.paymode" value="1"/></font>
    				</c:if>
    				<c:if test='${jpoMemberOrder.payMode==2 }'>
    					<font color='#006666'><jecs:code listCode="po.paymode" value="2"/></font>
    				</c:if>
    				<c:if test='${jpoMemberOrder.payMode==3 }'>
    					<font color='#0033CC'><jecs:code listCode="po.paymode" value="3"/></font>
    				</c:if>
    				<c:if test='${jpoMemberOrder.payMode==4 }'>
    					<font color='#FF00FF'><jecs:code listCode="po.paymode" value="4"/></font>
    				</c:if>
    			</ec:column>
--%>
    			<ec:column property="status" title="pd.checkstatus">
    				<jecs:code listCode="po.status" value="${jpoMemberOrder.status}"/>
    			</ec:column>
    			<ec:column property="remark" title="busi.common.remark" styleClass="centerColumn"/>
    			<ec:column property="submitStatus" title="poMemberOrder.checkStatus">
    				<jecs:code listCode="po.checkstatus" value="${jpoMemberOrder.submitStatus}"/>
    			</ec:column>
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editJpoMemberOrder(moId){
   		<jecs:power powerCode="editJpoMemberOrder">
					window.location="editJpoMemberOrder.html?strAction=editJpoMemberOrder&moId="+moId;
			</jecs:power>
		}

</script>
