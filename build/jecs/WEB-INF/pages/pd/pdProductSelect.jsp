<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="pmProductSaleList.title"/></title>
<content tag="heading"><jecs:locale key="pmProductSaleList.heading"/></content>
<meta name="menu" content="PmProductSaleMenu"/>		

<form name="frm" action="<c:url value='/pdExchangeOrderDetails.html'/>" >
<div class="searchBar">
	<input type="hidden" id="strAction"  name="strAction" value="${param.strAction}" />
	<input type="hidden" id="orderNo"  name="orderNo" value="${param.orderNo}" />
	<div class="new_searchBar">
	<jecs:title key="busi.product.productno"/>
	<input name="productNo" id="productNo" value="<c:out value='${param.productNo}'/>" cssClass="text medium"/> 
	</div>
	<div class="new_searchBar" style="white-space:nowrap;width:500px;">
	<jecs:title key="pmProduct.productName"/>
	<input name="productName" id="productName" value="<c:out value='${param.productName}'/>" cssClass="text medium"/> 
	
<%-- 	<input type="submit" class="button" value="<jecs:locale  key='operation.button.search'/>"/>
 --%>	
     <button type="submit" class="btn btn_sele"  style="width:auto">
		   <jecs:locale  key='operation.button.search'/>
    </button>
<%-- 	<input type="button" class="button" name="close"  onclick="closeMe();" value="<jecs:locale key="operation.button.close"/>" />
 --%>
      <button type="button" class="btn btn_sele"  style="width:auto" name="close" onclick="closeMe();">
		   <jecs:locale key="operation.button.close"/>
      </button>
     </div>
    </div>
</form>

<ec:table 
	tableId="pdProductSelectTable"
	items="pdProductSaleList"
	var="pmProductSale"
	action="${pageContext.request.contextPath}/pdExchangeOrderDetails.html"
	width="100%"
	autoIncludeParameters="true"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
		<ec:row >
			<ec:column property="edit" title="button.select"  styleClass="centerColumn" viewsAllowed="html"	sortable="false">
						<img src="<c:url value='/images/icons/verify.gif'/>" 
							onclick="javascript:selectProduct('${pmProductSale.jpmProductSaleNew.jpmProduct.productNo}',
															  '${pmProductSale.jpmProductSaleNew.productName}',
															  '${pmProductSale.pv}','${pmProductSale.price }',
															  '${pmProductSale.jpmProductSaleNew.jpmProduct.erpProductNo }',
															  '${pmProductSale.jpmProductSaleNew.jpmProduct.erpPrice }')" style="cursor: pointer;" title="<jecs:locale key="button.select"/>"/> 
			</ec:column>
   			<ec:column property="jpmProductSaleNew.jpmProduct.productNo" title="busi.product.productno" />
   			<ec:column property="jpmProductSaleNew.productName" title="pmProduct.productName" />
   			<ec:column property="price" title="pd.price" styleClass="numberColumn"/>	
   			<ec:column property="pv" title="PV" styleClass="numberColumn"/>
		</ec:row>
</ec:table>	

<script type="text/javascript">
        function selectProduct(productNo,productName,pv,price,erpProductNo,erpPrice){
                
  		        window.opener.document.getElementById('productNo').value=productNo;
				window.opener.document.getElementById('productName').value=productName;
   				window.opener.document.getElementById('pv').value=pv;
   				window.opener.document.getElementById('price').value=price;
   				window.opener.document.getElementById('erpProductNo').value=erpProductNo;
   				window.opener.document.getElementById('erpPrice').value=erpPrice;
   				
    		    this.close();
		}

		function closeMe(){
 		       this.close();
		}
		
</script>
