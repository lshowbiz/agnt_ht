<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="pdCombinationDetailList.title"/></title>
<content tag="heading"><jecs:locale key="pdCombinationDetailList.heading"/></content>
<meta name="menu" content="PdCombinationDetailMenu"/>

<c:set var="buttons">
		<jecs:power powerCode="addPdCombinationDetail">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editPdCombinationDetail.html"/>?strAction=addPdCombinationDetail'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power>
</c:set>
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>
<ec:table 
	tableId="pdCombinationDetailListTable"
	items="pdCombinationDetailList"
	var="pdCombinationDetail"
	action="${pageContext.request.contextPath}/pdCombinationDetails.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sort./images/extremetableecs/images/extremetable/*.gif">
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editPdCombinationDetail('${pdCombinationDetail.uniNo}')"
								style="cursor: pointer;" title="<wecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="pcNo" title="pdCombinationDetail.pcNo" />
    			<ec:column property="productNo" title="pdCombinationDetail.productNo" />
    			<ec:column property="price" title="pdCombinationDetail.price" />
    			<ec:column property="qty" title="pdCombinationDetail.qty" />
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editPdCombinationDetail(uniNo){
   		<jecs:power powerCode="editPdCombinationDetail">
					window.location="editPdCombinationDetail.html?strAction=editPdCombinationDetail&uniNo="+uniNo;
			</jecs:power>
		}

</script>
