<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="pdSendExtraList.title"/></title>
<content tag="heading"><jecs:locale key="pdSendExtraList.heading"/></content>
<meta name="menu" content="PdSendExtraMenu"/>

<c:set var="buttons">
		<jecs:power powerCode="addPdSendExtra">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editPdSendExtra.html"/>?strAction=addPdSendExtra&siNo=${param.siNo}'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power>
</c:set>
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>
<ec:table 
	tableId="pdSendExtraListTable"
	items="pdSendExtraList"
	var="pdSendExtra"
	action="${pageContext.request.contextPath}/pdSendExtras.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editPdSendExtra('${pdSendExtra.uniId}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="siNo" title="pdSendInfo.siNo" />
    			<ec:column property="productNo" title="busi.product.productno" >
    				${pdSendExtra.productNo}/${compamyProductMap[pdSendExtra.productNo]}
    		</ec:column>
    			<ec:column property="qty" title="pd.qty" />
    			
    			<ec:column property="createTime" title="pd.createTime" />
    			<ec:column property="remark" title="busi.common.remark" />
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editPdSendExtra(uniId){
   		<jecs:power powerCode="editPdSendExtra">
					window.location="editPdSendExtra.html?strAction=editPdSendExtra&uniId="+uniId;
			</jecs:power>
		}

</script>
