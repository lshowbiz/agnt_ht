<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="pmProductSaleList.title"/></title>
<content tag="heading"><jecs:locale key="pmProductSaleList.heading"/></content>
<meta name="menu" content="PmProductSaleMenu"/>


			
				
	
<form name="frm" action="<c:url value='/jpmProductSales.html'/>" >
<div class="searchBar">
<input type="hidden" id="strAction"  name="strAction" value="${param.strAction}" />
<input type="hidden" id="selectControl" name="selectControl" value="${param.selectControl}"/>
<input type="hidden" id="discount" name="discount" value="${param.discount}"/>	
<input type="hidden" id="discountFlag" name="discountFlag" value="${param.discountFlag}"/>	
<input type="hidden" id="companyCode" name="companyCode" value="${param.companyCode}"/>
		<div class="new_searchBar">	
			<jecs:title key="busi.product.productno"/>
			<input name="productNo" id="productNo" value="<c:out value='${param.productNo}'/>" cssClass="text medium"/> 
		</div>
		<div class="new_searchBar">
		<jecs:title key="pmProduct.productName"/>
			<input name="productName" id="productName" value="<c:out value='${param.productName}'/>" cssClass="text medium"/> 
		</div>
		<div class="new_searchBar">
		<button type="submit" class="btn btn_sele"  style="width:auto">
				        <jecs:locale  key='operation.button.search'/>
	    </button>
		<button type="button" class="btn btn_sele"  name="close" style="width:auto" onclick="closeMe();">
				        <jecs:locale key="operation.button.close"/>
	    </button>
	    </div>
		 <%-- <input type="submit" class="button" value="<jecs:locale  key='operation.button.search'/>"/>
		 <input type="button" class="button" name="close"  onclick="closeMe();" value="<jecs:locale key="operation.button.close"/>" />
			 --%> 
	 

</div>




<ec:table 
	tableId="jpmProductSaleListTable"
	items="jpmProductSaleList"
	var="pmProductSale"
	action="${pageContext.request.contextPath}/jpmProductSales.html"
	width="100%"
	form="frm"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
	
				<ec:row >
				<ec:column property="edit" title="button.select"  styleClass="centerColumn" viewsAllowed="html"	sortable="false">
							<img src="<c:url value='/images/icons/verify.gif'/>" 
								onclick="javascript:selectProduct('${pmProductSale.uniNo}','${pmProductSale.jpmProduct.productNo}',
																  '${pmProductSale.productName}','${pmProductSale.whoPrice }')" style="cursor: pointer;" title="<jecs:locale key="button.select"/>"/> 
					</ec:column>
    			<!--ec:column property="companyNo" title="pmProductSale.companyNo" /-->
    			<ec:column property="jpmProduct.productNo" title="busi.product.productno" />
    			<ec:column property="productName" title="pmProduct.productName" />
    				
    				
				</ec:row>

</ec:table>	
</form>


<script type="text/javascript">

   function editPmProductSale(uniNo,strAction){
   		<jecs:power powerCode="${strAction}">
					window.location="editJpmProductSale.html?strAction="+strAction+"&uniNo="+uniNo;
			</jecs:power>
		}

function selectProduct(uniNo,productNo,productName,price){
				var selectControl =$('selectControl').value;
				var discount = $('discount').value;
				var discountFlag = $('discountFlag').value;
			  if(selectControl=='sun'){
			  		window.opener.document.getElementById('productId').value=uniNo;
			  		window.opener.document.getElementById('productName').value=productName;
			  	}
				
				else if(selectControl=='1'){
					window.opener.document.getElementById('productNo').value=productNo;
    				window.opener.document.getElementById('productName').value=productName;
    				if(window.opener.document.getElementById('price')!=null){
    					window.opener.document.getElementById('price').value=price;
    				}
    			}
    		
    		this.close();
			}
			
			function returnAgent(uniNo,productName,price,pv){
				//	window.opener.document.getElementById('piProduct.productId').value=productId;
    			window.opener.document.getElementById('pmProductSale.productName').value=productName;
    			window.opener.document.getElementById('pmProductSale.uniNo').value=uniNo;
    			if(window.opener.document.getElementById('price')!=null){
    				window.opener.document.getElementById('price').value=price;
    			}
    			if(window.opener.document.getElementById('pv')!=null){
    				window.opener.document.getElementById('pv').value=pv;
    			}
			}
			
			function closeMe(){
					 this.close();
				}
</script>
