<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="pdLogisticsBaseDetailList.title"/></title>
<content tag="heading"><jecs:locale key="pdLogisticsBaseDetailList.heading"/></content>
<meta name="menu" content="PdLogisticsBaseDetailMenu"/>

<c:set var="buttons">
		<jecs:power powerCode="addPdLogisticsBaseDetail">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editPdLogisticsBaseDetail.html"/>?strAction=addPdLogisticsBaseDetail'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power>
</c:set>
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>
<ec:table 
	tableId="pdLogisticsBaseDetailListTable"
	items="pdLogisticsBaseDetailList"
	var="pdLogisticsBaseDetail"
	action="${pageContext.request.contextPath}/pdLogisticsBaseDetails.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="/jecs/images/extremetable/*.gif">
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editPdLogisticsBaseDetail('${pdLogisticsBaseDetail.detailId}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="numId" title="pdLogisticsBaseDetail.numId" />
    			<ec:column property="erpGoodsBn" title="pdLogisticsBaseDetail.erpGoodsBn" />
    			<ec:column property="productNo" title="pdLogisticsBaseDetail.productNo" />
    			<ec:column property="qty" title="pdLogisticsBaseDetail.qty" />
    			<ec:column property="createTime" title="pdLogisticsBaseDetail.createTime" />
    			<ec:column property="otherOne" title="pdLogisticsBaseDetail.otherOne" />
    			<ec:column property="otherTwo" title="pdLogisticsBaseDetail.otherTwo" />
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editPdLogisticsBaseDetail(detailId){
   		<jecs:power powerCode="editPdLogisticsBaseDetail">
					window.location="editPdLogisticsBaseDetail.html?strAction=editPdLogisticsBaseDetail&detailId="+detailId;
			</jecs:power>
		}

</script>
