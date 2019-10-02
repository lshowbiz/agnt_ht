<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jpmProductSaleLogList.title"/></title>
<content tag="heading"><jecs:locale key="jpmProductSaleLogList.heading"/></content>
<meta name="menu" content="JpmProductSaleLogMenu"/>

<c:set var="buttons">
		<jecs:power powerCode="addJpmProductSaleLog">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editJpmProductSaleLog.html"/>?strAction=addJpmProductSaleLog'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power>
</c:set>
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>


<form name="frm" action="<c:url value='/jpmProductSaleLogs.html'/>" >
		<input type="hidden" id="strAction" name="strAction" value="${requestParaMap.strAction}"/>
	<div class="searchBar">
	</div>
	
<ec:table 
	tableId="jpmProductSaleLogListTable"
	items="jpmProductSaleLogList"
	var="jpmProductSaleLog"
	form="frm"
	action="${pageContext.request.contextPath}/jpmProductSaleLogs.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/newIcons/pencil_16.gif'/>" 
								onclick="javascript:editJpmProductSaleLog('${jpmProductSaleLog.uniNo}')"
								style="cursor: pointer;" title="<wecs:locale key='button.edit'/>"/> 
					</ec:column>
    			<ec:column property="productNo" title="jpmProductSaleLog.productNo" />
    			<ec:column property="companyCode" title="jpmProductSaleLog.companyCode" />
    			<ec:column property="productName" title="jpmProductSaleLog.productName" />
    			<ec:column property="fpPrice" title="jpmProductSaleLog.fpPrice" />
    			<ec:column property="fpPv" title="jpmProductSaleLog.fpPv" />
    			<ec:column property="mpPrice" title="jpmProductSaleLog.mpPrice" />
    			<ec:column property="mpPv" title="jpmProductSaleLog.mpPv" />
    			<ec:column property="storeFpPrice" title="jpmProductSaleLog.storeFpPrice" />
    			<ec:column property="storeFpPv" title="jpmProductSaleLog.storeFpPv" />
    			<ec:column property="storeMpPrice" title="jpmProductSaleLog.storeMpPrice" />
    			<ec:column property="storeMpPv" title="jpmProductSaleLog.storeMpPv" />
    			<ec:column property="whoPrice" title="jpmProductSaleLog.whoPrice" />
    			<ec:column property="discountPrice" title="jpmProductSaleLog.discountPrice" />
    			<ec:column property="weight" title="jpmProductSaleLog.weight" />
    			<ec:column property="volume" title="jpmProductSaleLog.volume" />
    			<ec:column property="length" title="jpmProductSaleLog.length" />
    			<ec:column property="width" title="jpmProductSaleLog.width" />
    			<ec:column property="height" title="jpmProductSaleLog.height" />
    			<ec:column property="imageLink" title="jpmProductSaleLog.imageLink" />
    			<ec:column property="albumLink" title="jpmProductSaleLog.albumLink" />
    			<ec:column property="status" title="jpmProductSaleLog.status" />
    			<ec:column property="confirm" title="jpmProductSaleLog.confirm" />
    			<ec:column property="controlFirst" title="jpmProductSaleLog.controlFirst" />
    			<ec:column property="controlUpdate" title="jpmProductSaleLog.controlUpdate" />
    			<ec:column property="controlRepurchase" title="jpmProductSaleLog.controlRepurchase" />
    			<ec:column property="remark" title="jpmProductSaleLog.remark" />
    			<ec:column property="editUserCode" title="jpmProductSaleLog.editUserCode" />
    			<ec:column property="editTime" title="jpmProductSaleLog.editTime" />
				</ec:row>

</ec:table>	
</form>
<script type="text/javascript">

   function editJpmProductSaleLog(uniNo){
   		<jecs:power powerCode="editJpmProductSaleLog">
					window.location="editJpmProductSaleLog.html?strAction=editJpmProductSaleLog&uniNo="+uniNo;
			</jecs:power>
		}

</script>
