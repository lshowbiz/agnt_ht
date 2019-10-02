<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="sunProductInfoList.title" />
</title>
<content tag="heading">
<jecs:locale key="sunProductInfoList.heading" />
</content>
<meta name="menu" content="SunProductInfoMenu" />

<c:set var="buttons">
	<jecs:power powerCode="addSunProductInfo">
		<button name="search" class="btn btn_ins" onclick="location.href='<c:url value="/editSunProductInfo.html"/>?strAction=addSunProductInfo'">
			<jecs:locale key="button.add"/>
		</button>
	</jecs:power>
</c:set>
<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false" />
</div>
<ec:table tableId="sunProductInfoListTable" items="sunProductInfoList"
	var="sunProductInfo"
	action="${pageContext.request.contextPath}/sunProductInfos.html"
	width="100%" retrieveRowsCallback="limit" rowsDisplayed="20"
	sortable="true" imagePath="/jecs/images/extremetable/*.gif">
	<ec:row>
		<ec:column property="jpmProductSale.productName"
			title="pmProduct.productName" />
		<ec:column property="discount" title="pmProduct.discountPrice" />
		<ec:column property="beginDate" title="customerFollow.createTime" />
		<ec:column property="endDate" title="customerFollow.endTime" />
	</ec:row>

</ec:table>

<script type="text/javascript">

   function editSunProductInfo(jpiId){
   		<jecs:power powerCode="editSunProductInfo">
					window.location="editSunProductInfo.html?strAction=editSunProductInfo&jpiId="+jpiId;
			</jecs:power>
		}

</script>
