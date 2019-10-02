<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jpoProductNumLimitList.title"/></title>
<content tag="heading"><jecs:locale key="jpoProductNumLimitList.heading"/></content>
<meta name="menu" content="JpoProductNumLimitMenu"/>

<form action="" method="get" name="searchForm" id="searchForm">
<div class="searchBar">

<div class="new_searchBar">	
<jecs:title key="jpmSalePromoter.presentno" />
<input name="productNo" type="text" value="${param.productNo }" size="12"/>
</div>

<div class="new_searchBar">	
<jecs:title key="jpmProduct.productName"  />
<input name="productName" type="text" value="${param.productName }" size="12"/>
</div>

<div class="new_searchBar">	
<button name="search" class="btn btn_sele" type="submit" ><jecs:locale key="operation.button.search"/></button>
	<jecs:power powerCode="addJpoProductNumLimit">
			    <button type="button" class="btn btn_ins" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editJpoProductNumLimit.html"/>?strAction=addJpoProductNumLimit'"
			        ><jecs:locale key="button.add"/></button>
		</jecs:power>
		</div>
</div>
</form>

<ec:table 
	tableId="jpoProductNumLimitListTable"
	items="jpoProductNumLimitList"
	var="jpoProductNumLimit"
	action="${ctx}/jpoProductNumLimits.html"
	width="100%"
	autoIncludeParameters="true"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editJpoProductNumLimit('${jpoProductNumLimit.id}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="productNo" title="jpmSalePromoter.presentno" />
    			<ec:column property="productName" title="jpmProduct.productName" />
    			<ec:column property="num" title="jpmWineSettingInf.productQty" />
    			<ec:column property="startime" title="jpmSalePromoter.startime" >
    				<fmt:formatDate value="${jpoProductNumLimit.startime }" pattern="yyyy-MM-dd HH:mm:ss"/>
    			</ec:column>
    			<ec:column property="endtime" title="jpmSalePromoter.endtime" >
    				<fmt:formatDate value="${jpoProductNumLimit.endtime }" pattern="yyyy-MM-dd HH:mm:ss"/>
    			</ec:column>
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editJpoProductNumLimit(id){
   		<jecs:power powerCode="editJpoProductNumLimit">
					window.location="editJpoProductNumLimit.html?strAction=editJpoProductNumLimit&id="+id;
			</jecs:power>
		}

</script>
