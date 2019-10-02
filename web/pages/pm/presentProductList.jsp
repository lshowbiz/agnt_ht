<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jpmSalePromoterList.title"/></title>
<content tag="heading"><jecs:locale key="jpmSalePromoterList.heading"/></content>
<meta name="menu" content="JpmSalePromoterMenu"/>
<script type="text/javascript">
function unBindProduct(){
	var ptIdObjArr = document.getElementsByName("sid");
	var ptAll = document.getElementById("idAll");
	var spno = document.getElementById("spno");
	var ischked=false;
	for(var i=0; i<ptIdObjArr.length; i++){
		if(ptIdObjArr[i].checked){
			ptAll.value+=","+ptIdObjArr[i].value;
			ischked=true;
		}
	}
	
	if(ischked){
		if(confirm ('<jecs:locale key="jpmSalePromoter.isDel"/>?')){
			window.location="${ctx}${servletPath}?strAction=${strAction}&idAll="+ptAll.value+"&spno="+spno.value;
		}
	}else {
		alert('<jecs:locale key="jpmSalePromoter.ischeck"/>!');
		return;
	}
}
</script>

<div id="titleBar">
<c:set var="buttons">
	<input type="button" class="button" style="margin-right: 5px"
	  onclick="unBindProduct()"
	  value="<jecs:locale key="jpmSalePromoter.unBind"/>"/>
</c:set>
<c:out value="${buttons}" escapeXml="false"/>
<input type="button" value="<jecs:locale key="operation.button.return"/>" onclick="javascript:history.back();"/>
<input type="hidden" name="spno" id="spno" value="${sp.spno }">
<input type="hidden" name="idAll" id="idAll">
</div>

<table border="1" width="100%" >
	<tr>
		<td><jecs:locale key="jpmSalePromoter.promoterId"/></td>
		<td><jecs:locale key="jpmSalePromoter.startime"/></td>
		<td><jecs:locale key="jpmSalePromoter.endtime"/></td>
		<c:if test="${strAction != 'unBindSalePresent'}">
		<td>
			<c:choose>
				<c:when test="${strAction eq 'unBindOrderPV' }">
					<jecs:locale key="jpmSalePromoter.amount"/>	
				</c:when>
				<c:when test="${strAction eq 'unBindSalePromoter'}">
					<jecs:locale key="jpmSalePromoter.discount"/>	
				</c:when>
				<c:otherwise></c:otherwise>
			</c:choose>
			
		</td>
		</c:if>
		<c:if test="${sp.presentname !=null }">
		<td><jecs:locale key="jpmProductSaleNew.productName"/></td>
		<td><jecs:locale key="jpmSalePromoter.presentno"/></td>
		</c:if>
	</tr>
	<tr>
		<td>${sp.spno }</td>
		<td><fmt:formatDate value="${sp.startime }" pattern="yyyy-MM-dd"/></td>
		<td><fmt:formatDate value="${sp.endtime }" pattern="yyyy-MM-dd"/></td>
		<c:if test="${strAction != 'unBindSalePresent'}">
		<td>
			<c:choose>
				<c:when test="${strAction eq 'unBindOrderPV' }">
					${sp.amount }	
				</c:when>
				<c:when test="${strAction eq 'unBindSalePromoter'}">
					${sp.discount }	
				</c:when>
				<c:otherwise></c:otherwise>
			</c:choose>
		</td>
		</c:if>
		<c:if test="${sp.presentname !=null }">
		<td>${sp.presentname }</td>
		<td>${sp.presentno }</td>	
		</c:if>
	</tr>
</table>

<ec:table tableId="presentProductListTable" items="presentProductList" 
	var="jpmPresentPorductBind" autoIncludeParameters="true"
	action="${pageContext.request.contextPath}${servletPath }" width="100%"
	rowsDisplayed="20" sortable="false" imagePath="${ctx }/images/extremetable/*.gif">
	<ec:row >
		<ec:column property="ID" title="button.select">
			<input type="checkbox" id="sid" name="sid" value="${jpmPresentPorductBind.id }">
		</ec:column>
    	<ec:column property="presentName" title="jpmSalePromoter.presentName" />
    	<ec:column property="presentNo" title="jpmSalePromoter.presentNum" />
    	<ec:column property="spNum" title="jpmSalePromoter.sproNum" />
 
	</ec:row>

</ec:table>	

