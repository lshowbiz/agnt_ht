<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="pdNotChangeProductList.title"/></title>
<content tag="heading"><jecs:locale key="pdNotChangeProductList.heading"/></content>
<meta name="menu" content="PdNotChangeProductMenu"/>

<form action="pdNotChangeProducts.html" method="get" name="pdNotChangeProductForm1" id="pdNotChangeProductForm1">
<input type="hidden" name="strAction" value="pdNotChangeProductQuery"/>
<div class="searchBar">&nbsp;&nbsp;
<div class="new_searchBar">
      <jecs:title key="jpmProductSaleNew.productNo"/>
      <input name="productNo" type="text" value="${param.productNo }" size="15"/>
</div>
<div class="new_searchBar">      
      <jecs:title key="pmProduct.productName"/>
      <input name="productName" type="text" value="${param.productName }" size="20"/>
</div>
<div class="new_searchBar">
      <button type="button" class="btn btn_sele"  style="width:auto" onclick="checkpdNotChangeProduct(document.pdNotChangeProductForm1)">
		        <jecs:locale key="operation.button.search"/>
    </button>
    <button type="button" class="btn btn_ins"  style="width:auto" onclick="pdNotChangeProductAdd();">
		        <jecs:locale key="button.add"/>
    </button>
</div>      
      <%-- <input name="search" type="button" class="button" onclick="checkpdNotChangeProduct(document.pdNotChangeProductForm1)" value="<jecs:locale key="operation.button.search"/>"/>
      <input type="button" class="button" style="margin-right: 5px" onclick="location.href='<c:url value="/editPdNotChangeProduct.html"/>?strAction=addPdNotChangeProduct'" value="<jecs:locale key="button.add"/>"/> --%>
</div>
</form>

<ec:table 
	tableId="pdNotChangeProductListTable"
	items="pdNotChangeProductList"
	var="pdNotChangeProduct"
	action="${pageContext.request.contextPath}/pdNotChangeProducts.html"
	width="100%"
	autoIncludeParameters="true"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="false" imagePath="./images/extremetable/*.gif">
				<ec:exportXls fileName="pdNotChangeProductList.xls" encoding="UTF-8"/>
				<ec:row >
				
    			<ec:column property="productno" title="jpmProductSaleNew.productNo" />
    			<ec:column property="productname" title="pmProduct.productName" />
    			<ec:column property="createusercode" title="fiBillAccount.createUserName" />
    			<ec:column property="createtime" title="pd.createTime" />
    			<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editPdNotChangeProduct('${pdNotChangeProduct.id}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
				</ec:column>
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editPdNotChangeProduct(id){
			window.location="editPdNotChangeProduct.html?strAction=addPdNotChangeProduct&id="+id;
   }

   function checkpdNotChangeProduct(theForm){
            theForm.submit();
   }
   
   function pdNotChangeProductAdd(){
   			window.location="editPdNotChangeProduct.html?strAction=addPdNotChangeProduct";
   }

</script>
