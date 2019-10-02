<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jfi99billTempList.title"/></title>
<content tag="heading"><jecs:locale key="jfi99billTempList.heading"/></content>
<meta name="menu" content="Jfi99billTempMenu"/>

<c:set var="buttons">
		<jecs:power powerCode="addJfi99billTemp">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editJfi99billTemp.html"/>?strAction=addJfi99billTemp'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power>
</c:set>
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>
<ec:table 
	tableId="jfi99billTempListTable"
	items="jfi99billTempList"
	var="jfi99billTemp"
	action="${pageContext.request.contextPath}/jfi99billTemps.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
				<ec:row onclick="javascript:editJfi99billTemp('${jfi99billTemp.orderId}')">
    			<ec:column property="payerName" title="jfi99billTemp.payerName" />
    			<ec:column property="payerContact" title="jfi99billTemp.payerContact" />
    			<ec:column property="orderAmount" title="jfi99billTemp.orderAmount" />
    			<ec:column property="bankId" title="jfi99billTemp.bankId" />
    			<ec:column property="status" title="jfi99billTemp.status" />
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editJfi99billTemp(orderId){
   		<jecs:power powerCode="editJfi99billTemp">
					window.location="editJfi99billTemp.html?strAction=editJfi99billTemp&orderId="+orderId;
			</jecs:power>
		}

</script>
