<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jpmSalePromoterList.title"/></title>
<content tag="heading"><jecs:locale key="jpmSalePromoterList.heading"/></content>
<meta name="menu" content="JpmSalePromoterMenu"/>


<form action="${ctx }${servletPath}" name="discountSearchForm" method="post">
<div class="searchBar">
<input type="hidden" name="strAction" id="strAction" value="serchDiscount">
<table border="1" width="100%">
	<tr>
		<td><jecs:locale key="jpmSalePromoter.startime"/>:</td>
		<td>
			<input type="text" id="stime" name="stime" value="<fmt:formatDate value="${stime }" pattern="yyyy-MM-dd"/>"/>
			
		</td>
		<td><jecs:locale key="jpmSalePromoter.endtime" />:</td>
		<td>
			<input type="text" id="endTime" name="endTime" value="<fmt:formatDate value="${endtime }" pattern="yyyy-MM-dd"/>"/>
			
		</td>
		<td><jecs:locale key="jpmSalePromoter.discount"/>:</td>
		<td><input type="text" id="discount" name="discount" value="${discount }"/></td>
		<td>
			<input type="submit" value="<jecs:locale key="operation.button.search"/>"/>
			<input type="button" value="<jecs:locale key="operation.button.return"/>" onclick="javascript:history.back();"/>
		</td>
	</tr>

</table>
</div>
</form>
<div id="titleBar">
<c:set var="buttons">
	<input type="button" class="button" style="margin-right: 5px"
		onclick="unBindProduct()"
		value="<jecs:locale key="jpmSalePromoter.unBind"/>"/>
			
</c:set>

<c:out value="${buttons}" escapeXml="false"/>

</div>
<input type="hidden" name="pttids" id="pttids">
<table border="1" width="100%" >
	<tr>
		<td>id</td>
		<td><jecs:locale key="jpmSalePromoter.discount"/></td>
		<td><jecs:locale key="jpmSalePromoter.startime"/></td>
		<td><jecs:locale key="jpmSalePromoter.endtime"/></td>
	</tr>
	<tr>
		<td>${sp.spno }</td>
		<td>${sp.discount }</td>
		<td>${sp.startime }</td>
		<td>${sp.endtime }</td>
	</tr>
</table>
<ec:table tableId="discountProductListTable" items="presentProductList" var="jpmSalePorductBind"
	action="${pageContext.request.contextPath}${servletPath }" w./images/extremetableed="20" sortable="false" imagePath="/jecs/images/extremetable/*.gif">
	<ec:row >
		<ec:column property="proChk" title="button.select">
			<input type="checkbox" id="pttid" name="pttid" value="${jpmSalePorductBind.pttid }" onclick="teamsCheck(this,'pttids');"/>
		</ec:column>
    	<ec:column property="presentNo" title="jpmSalePromoter.presentno" />
    	<ec:column property="presentName" title="jpmSalePromoter.presentname" />
    	<ec:column property="proPrice" title="pd.price" />
    	<%-- <ec:column property="proCategory" title="bdReconsumMoneyReport.typeCH" />--%>
	</ec:row>

</ec:table>	
<script type="text/javascript">
function unBindProduct(){
	var pttids = document.getElementById("pttids");
	if(pttids !=null){
		if(confirm ('<jecs:locale key="jpmSalePromoter.isDel"/>?')){
			window.location="${ctx}/discountSale.html?strAction=unBindSalePromoter&pttids="+pttids.value+"&spno="+${sp.spno};
		}
	}else {
		alert('<jecs:locale key="jpmSalePromoter.ischeck"/>!');
		return;
	}
}

function teamsCheck(obj,str){
	
	var teams = document.getElementById(str);
	if(obj.checked){
		teams.value+=","+obj.value;
	}else{
		var temKey = obj.value;
		var tValue = teams.value;
		var tem ="";
		var temArr = tValue.split(",");
		for(var i=1;i<temArr.length;i++){
			if(temKey !=temArr[i]){
				tem += ","+temArr[i];
			}	
		}
		teams.value = tem;
	}
}
</script>
