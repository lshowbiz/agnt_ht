<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jpmProductCombinationList.title"/></title>
<content tag="heading"><jecs:locale key="jpmProductCombinationList.heading"/></content>
<meta name="menu" content="JpmProductCombinationMenu"/>

<%-- 
<c:set var="buttons">
		<jecs:power powerCode="addJpmProductCombination">
			<c:if test="${pmProduct.lockFlag != '1'}">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editJpmProductCombination.html"/>?strAction=addJpmProductCombination&parentProductNo=${param.productNo}'"
			        value="<jecs:locale key="button.add"/>"/>
			</c:if>
		</jecs:power>
</c:set>
--%>
<c:set var="buttons">
		<jecs:power powerCode="addJpmProductCombination">
			<c:if test="${pmProduct.lockFlag != '1'}">
			    <button type="button" class="btn btn_ins" style="width:auto"
			        onclick="location.href='<c:url value="/editJpmProductCombination.html"/>?strAction=addJpmProductCombination&parentProductNo=${param.productNo}'"><jecs:locale key="button.add"/></button>
			</c:if>
		</jecs:power>
</c:set>
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>
<ec:table 
	tableId="jpmProductCombinationListTable"
	items="jpmProductCombinationList"
	var="jpmProductCombination"
	action="${pageContext.request.contextPath}/jpmProductCombinations.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
				<ec:row >
				<ec:parameter name="strAction" value="${param.strAction}"/>
				<ec:parameter name="productNo" value="${param.productNo}"/>
				
    			<ec:column property="productno" title="jpmProductCombination.productNo" />
    			<ec:column property="subproductno" title="jpmProductCombination.subProductNo" />
    				<ec:column property="subproductname" title="pmProduct.productName" >
    					${compamyProductMap[jpmProductCombination.subProductNo]}
    				</ec:column>
    					
    				<ec:column property="qty" title="pd.qty" />
    					
    						<c:if test="${pmProduct.lockFlag != '1'}">
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editJpmProductCombination('${jpmProductCombination.jpcid}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
				</c:if>
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editJpmProductCombination(jpcId){
   		<jecs:power powerCode="editJpmProductCombination">
					window.location="editJpmProductCombination.html?strAction=editJpmProductCombination&jpcId="+jpcId;
			</jecs:power>
		}

</script>
