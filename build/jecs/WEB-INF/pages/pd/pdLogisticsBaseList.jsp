<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="pdLogisticsBaseList.title"/></title>
<content tag="heading"><jecs:locale key="pdLogisticsBaseList.heading"/></content>
<meta name="menu" content="PdLogisticsBaseMenu"/>

<c:set var="buttons">
		<jecs:power powerCode="addPdLogisticsBase">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editPdLogisticsBase.html"/>?strAction=addPdLogisticsBase'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power>
</c:set>
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>
<ec:table 
	tableId="pdLogisticsBaseListTable"
	items="pdLogisticsBaseList"
	var="pdLogisticsBase"
	action="${pageContext.request.contextPath}/pdLogisticsBases.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="/jecs/images/extremetable/*.gif">
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editPdLogisticsBase('${pdLogisticsBase.baseId}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="memberOrderNo" title="pdLogisticsBase.memberOrderNo" />
    			<ec:column property="siNo" title="pdLogisticsBase.siNo" />
    			<ec:column property="status" title="pdLogisticsBase.status" />
    			<ec:column property="wmsDo" title="pdLogisticsBase.wmsDo" />
    			<ec:column property="statusTime" title="pdLogisticsBase.statusTime" />
    			<ec:column property="statusCode" title="pdLogisticsBase.statusCode" />
    			<ec:column property="statusName" title="pdLogisticsBase.statusName" />
    			<ec:column property="operator" title="pdLogisticsBase.operator" />
    			<ec:column property="otherOne" title="pdLogisticsBase.otherOne" />
    			<ec:column property="otherTwo" title="pdLogisticsBase.otherTwo" />
    			<ec:column property="otherThree" title="pdLogisticsBase.otherThree" />
    			<ec:column property="otherFour" title="pdLogisticsBase.otherFour" />
    			<ec:column property="otherFive" title="pdLogisticsBase.otherFive" />
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editPdLogisticsBase(baseId){
   		<jecs:power powerCode="editPdLogisticsBase">
					window.location="editPdLogisticsBase.html?strAction=editPdLogisticsBase&baseId="+baseId;
			</jecs:power>
		}

</script>
