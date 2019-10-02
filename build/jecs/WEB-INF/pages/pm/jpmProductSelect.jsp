<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jpmProductList.title" />
</title>
<content tag="heading">
<jecs:locale key="jpmProductList.heading" />
</content>
<meta name="menu" content="jpmProductMenu" />

<c:choose>
	<c:when test="${param.selectControl=='1' || param.selectControl=='' || param.selectControl==null}">
		<form name="frm" action="<c:url value='/jpmProducts.html'/>">
			<div class="searchBar">
		
							<input type="hidden" id="strAction" name="strAction"
								value="${param.strAction}" />
							<input type="hidden" id="selectControl" name="selectControl"
								value="1" />
							<input type="hidden" id="smNo" name="smNo" value="${param.smNo}" />
							<input type="hidden" id="combineFlag" name="combineFlag" value="${param.combineFlag}" />
						<div class="new_searchBar">
							<jecs:title key="busi.product.productno" />
							<input name="productNo" id="productNo"
								value="<c:out value='${param.productNo}'/>" cssClass="text medium" />
		               </div>
		               <div class="new_searchBar">  
							<jecs:title key="pmProduct.productName" />
							<input name="productName" id="productName"
								value="<c:out value='${param.productName}'/>"
								cssClass="text medium" />
						</div>
						 <div class="new_searchBar">		
		                    <button type="submit" class="btn btn_sele" style="width:auto">
				                  <jecs:locale  key='operation.button.search'/>
				           </button>
				            <button name="button" class="btn btn_sele" onclick="closeMe();" >
									   <jecs:locale  key='operation.button.close'/>
							</button>
							<%-- <input type="submit" class="button"
								value="<jecs:locale  key='operation.button.search'/>" /> --%>
		               </div>   
				<%-- <table width="100%" border="0">
					<tr>
						<td>
							<input type="hidden" id="strAction" name="strAction"
								value="${param.strAction}" />
							<input type="hidden" id="selectControl" name="selectControl"
								value="1" />
							<input type="hidden" id="smNo" name="smNo" value="${param.smNo}" />
							<input type="hidden" id="combineFlag" name="combineFlag" value="${param.combineFlag}" />
							
							<jecs:title key="busi.product.productno" />
							<input name="productNo" id="productNo"
								value="<c:out value='${param.productNo}'/>" cssClass="text medium" />
		
							<jecs:title key="pmProduct.productName" />
							<input name="productName" id="productName"
								value="<c:out value='${param.productName}'/>"
								cssClass="text medium" />
		
							<input type="submit" class="button"
								value="<jecs:locale  key='operation.button.search'/>" />
						</td>
		
					</tr>
				</table> --%>
			</div>
			<ec:table tableId="jpmProductListTable" items="jpmProductList"
				var="jpmProduct"
				action="${pageContext.request.contextPath}/jpmProducts.html"
				width="100%" form="frm" retrieveRowsCallback="limit"
				rowsDisplayed="20" sortable="true"
				imagePath="./images/extremetable/*.gif">
				<ec:row>
					<ec:column property="edit" title="button.select"
						styleClass="centerColumn" viewsAllowed="html" sortable="false">
						<img src="<c:url value='/images/newIcons/tick_16.gif'/>"
							onclick="javascript:selectProduct('${jpmProduct.productNo}','${jpmProduct.productName}')"
							style="cursor: pointer;" title="<jecs:locale key="button.select"/>" />
					</ec:column>
					<ec:column property="productNo" title="busi.product.productno" />
					<ec:column property="productName" title="pmProduct.productName" />
					<ec:column property="smNo" title="pm.smNo">
						<jecs:code listCode="pmProduct.smno" value="${pmProduct.smNo}" />
					</ec:column>
				</ec:row>
			</ec:table>
		</form>
		<script type="text/javascript">
			function selectProduct(productNo, productName) {
				var selectControl = $('selectControl').value;
				if (selectControl == '1' || selectControl==null || selectControl=='' ||selectControl=='null') {
					if (window.opener.document.getElementById('productNo') != null) {
						window.opener.document.getElementById('productNo').value = productNo;
					}
					if (window.opener.document.getElementById('productName') != null) {
						window.opener.document.getElementById('productName').value = productName;
					}
					if (window.opener.document.getElementById('subProductNo') != null) {
						window.opener.document.getElementById('subProductNo').value = productNo;
					}
				} else if (selectControl == '2') {
					if (window.opener.document
							.getElementById('jpmProductSaleNew.jpmProduct.productNo') != null) {
						window.opener.document
								.getElementById('jpmProductSaleNew.jpmProduct.productNo').value = productNo;
					}
				}
		
				this.close();
			}
			function closeMe(){
				this.close();
			}
		</script>
	</c:when>
	<c:when test="${param.selectControl=='2'}">
		<form name="frm" action="<c:url value='/jpmProducts.html?strAction=selectProduct&selectControl=2'/>">
			<div class="searchBar">
							<input type="hidden" id="strAction" name="strAction"
								value="${param.strAction}" />
							<input type="hidden" id="selectControl" name="selectControl"
								value="${param.selectControl}" />
							<input type="hidden" id="smNo" name="smNo" value="${param.smNo}" />
							
						<div class="new_searchBar">
							<jecs:title key="busi.product.productno" />
							<input name="productNo" id="productNo"
								value="<c:out value='${param.productNo}'/>" cssClass="text medium" />
		                </div>
		                <div class="new_searchBar" style="white-space:nowrap;">
							<jecs:title key="pmProduct.productName" />
							<input name="productName" id="productName"
								value="<c:out value='${param.productName}'/>"
								cssClass="text medium" />
		                
		                    <button type="submit"  class="btn btn_sele" style="width:auto">
				                <jecs:locale  key='operation.button.search'/>
			                </button>
					   </div>
				
				<%-- <table width="100%" border="0">
					<tr>
						<td>
							<input type="hidden" id="strAction" name="strAction"
								value="${param.strAction}" />
							<input type="hidden" id="selectControl" name="selectControl"
								value="${param.selectControl}" />
							<input type="hidden" id="smNo" name="smNo" value="${param.smNo}" />
							<jecs:title key="busi.product.productno" />
							<input name="productNo" id="productNo"
								value="<c:out value='${param.productNo}'/>" cssClass="text medium" />
		
							<jecs:title key="pmProduct.productName" />
							<input name="productName" id="productName"
								value="<c:out value='${param.productName}'/>"
								cssClass="text medium" />
		
							<input type="submit" class="button"
								value="<jecs:locale  key='operation.button.search'/>" />
						</td>
		
					</tr>
				</table> --%>
			</div>
			<ec:table tableId="jpmProductSaleNewListTable" items="jpmProductSaleNewList"
				var="jpmProductSaleNew"
				action="${pageContext.request.contextPath}/jpmProducts.html"
				width="100%" form="frm" retrieveRowsCallback="limit"
				rowsDisplayed="20" sortable="true"
				imagePath="./images/extremetable/*.gif">
				<ec:row>
					<ec:column property="edit" title="button.select"
						styleClass="centerColumn" viewsAllowed="html" sortable="false">
						<img src="<c:url value='/images/newIcons/tick_16.gif'/>"
							onclick="javascript:selectProduct('${jpmProductSaleNew.jpmProduct.productNo}','${jpmProductSaleNew.jpmProduct.productName}','${jpmProductSaleNew.uniNo }')"
							style="cursor: pointer;" title="<jecs:locale key="button.select"/>" />
					</ec:column>
					<ec:column property="companyCode" title="sys.company.code" />
					<ec:column property="jpmProduct.productNo" title="busi.product.productno" />
					<ec:column property="productName" title="pmProduct.productName" />
				</ec:row>
			</ec:table>
		</form>
		<script type="text/javascript">
			function selectProduct(productNo, productName,uniNo) {
				var selectControl = $('selectControl').value;
				if (window.opener.document
						.getElementById('jpmProductSaleNew.jpmProduct.productNo') != null) {
					window.opener.document
							.getElementById('jpmProductSaleNew.jpmProduct.productNo').value = productNo;
				}
				if (window.opener.document
						.getElementById('uniNo') != null) {
					window.opener.document
							.getElementById('uniNo').value = uniNo;
				}
		
				this.close();
			}
		</script>
	</c:when>
	<c:when test="${param.selectControl=='3'}">
		<form name="frm" action="<c:url value='/jpmProducts.html?strAction=selectProduct&selectControl=2'/>">
			<div class="searchBar">
				
				<!-- <table width="100%" border="0">
					<tr>
						<td> -->
							<input type="hidden" id="strAction" name="strAction"
								value="${param.strAction}" />
							<input type="hidden" id="selectControl" name="selectControl"
								value="${param.selectControl}" />
							<input type="hidden" id="smNo" name="smNo" value="${param.smNo}" />
							<div class="new_searchBar" style="white-space:nowrap;">
							<jecs:title key="busi.product.productno" />
							<input name="productNo" id="productNo"
								value="<c:out value='${param.productNo}'/>" cssClass="text medium" />
		                   </div>
		                   <div class="new_searchBar" style="white-space:nowrap;">
							<jecs:title key="pmProduct.productName" />
							<input name="productName" id="productName"
								value="<c:out value='${param.productName}'/>"
								cssClass="text medium" />
		
							<button type="submit" class="btn btn_sele" style="width:auto"><jecs:locale  key='operation.button.search'/></button>
							</div>
						<!-- </td>
		
					</tr>
				</table> -->
			</div>
			<ec:table tableId="jpmProductSaleNewListTable" items="jpmProductSaleNewList"
				var="jpmProductSaleNew"
				action="${pageContext.request.contextPath}/jpmProducts.html"
				width="100%" form="frm" retrieveRowsCallback="limit"
				rowsDisplayed="20" sortable="true"
				imagePath="./images/extremetable/*.gif">
				<ec:row>
					<ec:column property="edit" title="button.select"
						styleClass="centerColumn" viewsAllowed="html" sortable="false">
						<img src="<c:url value='/images/newIcons/tick_16.gif'/>"
							onclick="javascript:selectProduct('${jpmProductSaleNew.jpmProduct.productNo}','${jpmProductSaleNew.jpmProduct.productName}','${jpmProductSaleNew.uniNo }')"
							style="cursor: pointer;" title="<jecs:locale key="button.select"/>" />
					</ec:column>
					<ec:column property="companyCode" title="sys.company.code" />
					<ec:column property="jpmProduct.productNo" title="busi.product.productno" />
					<ec:column property="productName" title="pmProduct.productName" />
				</ec:row>
			</ec:table>
		</form>
		<script type="text/javascript">
			function selectProduct(productNo, productName,uniNo) {
				var selectControl = $('selectControl').value;
				if (window.opener.document
						.getElementById('jpmProductSaleNew.jpmProduct.productNo') != null) {
					window.opener.document
							.getElementById('jpmProductSaleNew.jpmProduct.productNo').value = productNo;
				}
				if (window.opener.document
						.getElementById('relationUniNo') != null) {
					window.opener.document
							.getElementById('relationUniNo').value = uniNo;
				}
		
				this.close();
			}
		</script>
	</c:when>
  
 <c:when test="${param.selectControl=='4'}">
    <form name="frm" action="<c:url value='/jpmProducts.html?strAction=selectProduct&selectControl=4&productCategory=${param.productCategory}'/>">
      <div class="searchBar">
        
        <!-- <table width="100%" border="0">
          <tr>
            <td> -->
              <input type="hidden" id="strAction" name="strAction"
                value="${param.strAction}" />
              <input type="hidden" id="selectControl" name="selectControl"
                value="${param.selectControl}" />
              <input type="hidden" id="smNo" name="smNo" value="${param.smNo}" />
              <div class="new_searchBar" style="white-space:nowrap;">
              <jecs:title key="busi.product.productno" />
              <input name="productNo" id="productNo"
                value="<c:out value='${param.productNo}'/>" cssClass="text medium" />
              </div>
              <div class="new_searchBar" style="white-space:nowrap;">
              <jecs:title key="pmProduct.productName" />
              <input name="productName" id="productName"
                value="<c:out value='${param.productName}'/>"
                cssClass="text medium" />
    
              <input type="submit" class="button"
                value="<jecs:locale  key='operation.button.search'/>" />
                </div>
            <!-- </td>
    
          </tr>
        </table> -->
      </div>
      <ec:table tableId="jpmProductSaleNewListTable" items="jpmProductSaleNewList"
        var="jpmProductSaleNew"
        action="${pageContext.request.contextPath}/jpmProducts.html"
        width="100%" form="frm" retrieveRowsCallback="limit"
        rowsDisplayed="20" sortable="true"
        imagePath="${ctx}/images/extremetable/*.gif">
        <ec:row>
          <ec:column property="edit" title="button.select"
            styleClass="centerColumn" viewsAllowed="html" sortable="false">
            <img src="<c:url value='/images/newIcons/tick_16.gif'/>"
              onclick="javascript:selectProduct('${jpmProductSaleNew.jpmProduct.productNo}','${jpmProductSaleNew.jpmProduct.productName}','${jpmProductSaleNew.uniNo }')"
              style="cursor: pointer;" title="<jecs:locale key="button.select"/>" />
          </ec:column>
          <ec:column property="companyCode" title="sys.company.code" />
          <ec:column property="jpmProduct.productNo" title="busi.product.productno" />
          <ec:column property="productName" title="pmProduct.productName" />
        </ec:row>
      </ec:table>
    </form>
    <script type="text/javascript">
      function selectProduct(productNo, productName,uniNo) {
       // var selectControl = $('selectControl').value;
        if (window.opener.document
            .getElementById('productNo') != null) {
          window.opener.document
              .getElementById('productNo').value = productNo;
        }
        if (window.opener.document
            .getElementById('productName') != null) {
          window.opener.document
              .getElementById('productName').value = productName;
        }
    
        this.close();
      }
    </script>
  </c:when>
  <c:when test="${param.selectControl=='5'}">
    <form name="frm" action="<c:url value='/jpmProducts.html?strAction=selectProduct&selectControl=5&productCategory=${param.productCategory}'/>">
      <div class="searchBar">
        
       <!--  <table width="100%" border="0">
          <tr>
            <td> -->
              <input type="hidden" id="strAction" name="strAction"
                value="${param.strAction}" />
              <input type="hidden" id="selectControl" name="selectControl"
                value="${param.selectControl}" />
              <input type="hidden" id="smNo" name="smNo" value="${param.smNo}" />
              <div class="new_searchBar" style="white-space:nowrap;">
              <jecs:title key="busi.product.productno" />
              <input name="productNo" id="productNo"
                value="<c:out value='${param.productNo}'/>" cssClass="text medium" />
              </div>
              <div class="new_searchBar" style="white-space:nowrap;">
              <jecs:title key="pmProduct.productName" />
              <input name="productName" id="productName"
                value="<c:out value='${param.productName}'/>"
                cssClass="text medium" />
    
              <input type="submit" class="button"
                value="<jecs:locale  key='operation.button.search'/>" />
                </div>
           <!--  </td>
    
          </tr>
        </table> -->
      </div>
      <ec:table tableId="jpmProductSaleNewListTable" items="jpmProductSaleNewList"
        var="jpmProductSaleNew"
        action="${pageContext.request.contextPath}/jpmProducts.html"
        width="100%" form="frm" retrieveRowsCallback="limit"
        rowsDisplayed="20" sortable="true"
        imagePath="/jecs/images/extremetable/*.gif">
        <ec:row>
          <ec:column property="edit" title="button.select"
            styleClass="centerColumn" viewsAllowed="html" sortable="false">
            <img src="<c:url value='/images/newIcons/tick_16.gif'/>"
              onclick="javascript:selectProduct('${jpmProductSaleNew.jpmProduct.productNo}','${jpmProductSaleNew.jpmProduct.productName}','${jpmProductSaleNew.jpmProduct.productStyle}','${jpmProductSaleNew.jpmProduct.unitNo }')"
              style="cursor: pointer;" title="<jecs:locale key="button.select"/>" />
          </ec:column>
          <ec:column property="companyCode" title="sys.company.code" />
          <ec:column property="jpmProduct.productNo" title="busi.product.productno" />
          <ec:column property="productName" title="pmProduct.productName" />
        </ec:row>
      </ec:table>
    </form>
    <script type="text/javascript">
      function selectProduct(productNo, productName,productStyle,unitNo) {
       // var selectControl = $('selectControl').value;
       var trId = '${param.trId}';
       var $trId = window.opener.$('#'+trId);
        if($trId.find(':input[name$="subName"]').val()==''){
	       $trId.find(':input[name$="subName"]').val(productName);
        }
        $trId.find('input[name$="productNo"]').val(productNo);
        $trId.find('input[name$="productName"]').val(productName);
        $trId.find('input[name$="specification"]').val(productStyle);
        $trId.find(':input[name$="unit"]').val(unitNo);
        this.close();
      }
    </script>
  </c:when>
</c:choose>

