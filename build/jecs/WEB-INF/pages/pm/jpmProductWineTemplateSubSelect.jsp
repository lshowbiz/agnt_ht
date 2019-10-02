<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jpmProductWineTemplateSubList.title"/></title>
<content tag="heading"><jecs:locale key="jpmProductWineTemplateSubList.heading"/></content>
<meta name="menu" content="JpmProductWineTemplateSubMenu"/>
<form name="frm" action="${pageContext.request.contextPath}/jpmProductWineTemplateSubs.html">


<div class="titleBar">
    
        <table width="100%" border="0">
          <tr>
            <td>
              <input type="hidden" id="strAction" name="strAction"
                value="${param.strAction}" />
              <input type="hidden" id="isInvalid" name="isInvalid"
                value="${param.isInvalid}" />
             <%--  <jecs:label key="busi.product.productno" />
              <input name="productNo" id="productNo"
                value="<c:out value='${param.productNo}'/>" cssClass="text medium" />
    
              <jecs:label key="pmProduct.productName" />
              <input name="productName" id="productName"
                value="<c:out value='${param.productName}'/>"
                cssClass="text medium" />
    
              <input type="submit" class="button"
                value="<jecs:locale  key='operation.button.search'/>" />
            </td>
     --%>
          </tr>
        </table>
      </div>
<ec:table 
	tableId="jpmProductWineTemplateSubListTable"
	items="jpmProductWineTemplateSubList"
	var="jpmProductWineTemplateSub"
	action="${pageContext.request.contextPath}/jpmProductWineTemplateSubs.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="/jecs/images/extremetable/*.gif">
				<ec:row >
				<ec:column property="edit" title="button.select" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/newIcons/tick_16.gif'/>" 
								onclick="selectJpmProductWineTemplateSub('${jpmProductWineTemplateSub.subNo}','${jpmProductWineTemplateSub.subName}')"
								style="cursor: pointer;" title="<jecs:locale key="button.select"/>"/> 
					</ec:column>
          
    			<ec:column property="subName" title="jpmProductWineTemplatesub.subName" />
    			<ec:column property="price" title="jpmProductWineTemplatesub.price" />
    			<ec:column property="specification" title="jpmProductWineTemplatesub.specification" />
    			<ec:column property="num" title="jpmProductWineTemplatesub.num" />
    			<ec:column property="unit" title="jpmProductWineTemplatesub.unit" >
                  <jecs:code listCode="pmproduct.unitno" value="${jpmProductWineTemplateSub.unit}"></jecs:code>
                </ec:column>
<%--     			<ec:column property="lossRatio" title="jpmProductWineTemplateSub.lossRatio" />
    			<ec:column property="isMainMaterial" title="jpmProductWineTemplateSub.isMainMaterial" />
    			<ec:column property="isSendMaterial" title="jpmProductWineTemplateSub.isSendMaterial" />
    			<ec:column property="isDelegateOut" title="jpmProductWineTemplateSub.isDelegateOut" />
    			<ec:column property="isFeatureItem" title="jpmProductWineTemplateSub.isFeatureItem" />
    			<ec:column property="isMustSelected" title="jpmProductWineTemplateSub.isMustSelected" />
    			<ec:column property="isDefaultSelected" title="jpmProductWineTemplateSub.isDefaultSelected" />
    			<ec:column property="isNumChange" title="jpmProductWineTemplateSub.isNumChange" />
    			<ec:column property="numMax" title="jpmProductWineTemplateSub.numMax" />
    			<ec:column property="numMin" title="jpmProductWineTemplateSub.numMin" /> --%>
				</ec:row>

</ec:table>	
</form>
<script type="text/javascript">
   function selectJpmProductWineTemplateSub(subNo,subName){
       window.opener.$('#subNo').val(subNo);
       window.opener.$('#subName').val(subName);
       this.close();
   }
</script>
