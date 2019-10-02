<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="pdLogisticsBaseNumList.title"/></title>
<content tag="heading"><jecs:locale key="pdLogisticsBaseNumList.heading"/></content>
<meta name="menu" content="PdLogisticsBaseNumMenu"/>

<c:set var="buttons">
		<jecs:power powerCode="addPdLogisticsBaseNum">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editPdLogisticsBaseNum.html"/>?strAction=addPdLogisticsBaseNum'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power>
</c:set>
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>
<ec:table 
	tableId="pdLogisticsBaseNumListTable"
	items="pdLogisticsBaseNumList"
	var="pdLogisticsBaseNum"
	action="${pageContext.request.contextPath}/pdLogisticsBaseNums.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="/jecs/images/extremetable/*.gif">
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editPdLogisticsBaseNum('${pdLogisticsBaseNum.numId}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="baseId" title="pdLogisticsBaseNum.baseId" />
    			<ec:column property="pdlogisticsbasenumNo" title="pdLogisticsBaseNum.pdlogisticsbasenumNo" />
    			<ec:column property="name" title="pdLogisticsBaseNum.name" />
    			<ec:column property="status" title="pdLogisticsBaseNum.status" />
    			<ec:column property="mailTime" title="pdLogisticsBaseNum.mailTime" />
    			<ec:column property="otherOne" title="pdLogisticsBaseNum.otherOne" />
    			<ec:column property="otherTwo" title="pdLogisticsBaseNum.otherTwo" />
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editPdLogisticsBaseNum(numId){
   		<jecs:power powerCode="editPdLogisticsBaseNum">
					window.location="editPdLogisticsBaseNum.html?strAction=editPdLogisticsBaseNum&numId="+numId;
			</jecs:power>
		}

</script>
