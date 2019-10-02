<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jpmSalePromoterList.title"/></title>
<content tag="heading"><jecs:locale key="jpmSalePromoterList.heading"/></content>
<meta name="menu" content="JpmSalePromoterMenu"/>

<script type="text/javascript">
function validCheckBox(){
	var ptIds = document.getElementsByName("proId");
	var allproId = document.getElementById("allproId");
	
	var num = document.getElementsByName("num");
	var allproNum = document.getElementById("allproNum");
	var ischked = false;
	var flag = document.getElementById('flag');
	for(var i=0; i<ptIds.length; i++){
		if(ptIds[i].checked){
			allproId.value+=","+ptIds[i].value;
			allproNum.value+=","+num[i].value+"&"+ptIds[i].value;
			if(num[i].value == ""){
				alert("数量不能为空！");
				return;
			}else if(! /^[1-9]+[0-9]*]*$/.test(num[i].value)){
				alert("数量填写错误！");
				return;
			}else{
				ischked=true;
			}
		}
	}
	
	if(ischked){
		var straction = document.getElementById("strAction").value;
		document.bindProductForm.action="${ctx}${servletPath}?strAction="+straction;
		flag.value="";
		document.bindProductForm.submit();
	}else {
		alert('<jecs:locale key="jpmSalePromoter.ischeck"/>!');
		return;
	}
}
</script>
<form name="bindProductForm" action="${ctx }${servletPath}" method="post">
<table border="1" width="100%">
	<tr>
		<td><jecs:locale key="jpmSalePromoter.presentname"/>:</td>
		<td><input type="text" id="presentname" name="presentname" value="${proName }"/></td>
		<td><jecs:locale key="jpmSalePromoter.presentno"/>:</td>
		<td><input type="text" id="presentno" name="presentno" value="${proNo }"/></td>
		<td>
			<input type="submit" value="<jecs:locale key="operation.button.search"/>"/>
			
		</td>
	</tr>

</table>


<div id="titleBar">
	<input type="button" value="<jecs:locale key="jpmSalePromoter.bindProduct"/>" onclick="validCheckBox()"/>
	<input type="button" value="<jecs:locale key="operation.button.return"/>" onclick="javascript:history.back();"/>
	<input type="hidden" name="strAction" id="strAction" value="${strAction }"/>
	<input type="hidden" name="flag" id="flag" value="${flag }"/>
	<input type="hidden" name="allproId" id="allproId"/>
		<input type="hidden" name="allproNum" id="allproNum"/>
	<input type="hidden" name="teams" id="teams" value="${salePromoter.teamno }"/>
	<input type="hidden" name="companys" id="companys" value="${salePromoter.companyno }"/>
	<input type="hidden" name="orderTypes" id="orderTypes" value="${salePromoter.ordertype }"/>
<input type="hidden" name="spno" id="spno" value="${spno }"/>
</div>
</form>
<ec:table 
	tableId="jpmProductListTable"
	items="jpmBindProductList"
	var="jpmProduct"
	action="${pageContext.request.contextPath}${servletPath }"
	autoIncludeParameters="true"
	width="100%"
	rowsDisplayed="20" sortable="false" imagePath="${ctx }/images/extremetable/*.gif">
	
				<ec:row >
					<ec:column property="proId" title="button.select">
						<input type="checkbox" id="proId" name="proId" value="${jpmProduct.productNo}">
					</ec:column>
	    			<ec:column property="productNo" title="jpmSalePromoter.presentno"/>
	    			
    				<ec:column property="productName" title="jpmSalePromoter.presentname"/>
    				
    				<ec:column property="proNum" title="jpmSalePromoter.presentnum">
    					<input type="text" id="num" name="num" value="${jpmProduct.proNum }" />
    				</ec:column>
	    				
				</ec:row>
	
</ec:table>	

