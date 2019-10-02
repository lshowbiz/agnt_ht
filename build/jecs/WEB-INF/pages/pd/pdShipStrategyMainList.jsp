<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="pdShipStrategyMainList.title"/></title>
<content tag="heading"><jecs:locale key="pdShipStrategyMainList.heading"/></content>
<meta name="menu" content="PdShipStrategyMainMenu"/>


<form name="frm" action="<c:url value='/pdShipStrategyMains.html'/>" >
<div class="searchBar">
<div class="new_searchBar"> 
	<jecs:title key="sysListValue.valueTitle"/>
	<input name="valueTitle"  value="<c:out value='${param.valueTitle}'/>" cssClass="text medium"/>	
</div>
<div class="new_searchBar"> 	
	<jecs:title key="pdShipStrategyMain.importance"/>
	<jecs:list listCode="pdshipstrategymain.importance" name="importance" id="importance" showBlankLine="true" value="${param.importance}" defaultValue=""/>
</div>
<div class="new_searchBar"> 
     <button type="submit" class="btn btn_sele"  style="width:auto" >
		       <jecs:locale  key='operation.button.search'/>
    </button>
</div>
<%-- 	<input type="submit" class="button" value="<jecs:locale  key='operation.button.search'/>"/>
 --%>
 </div>
	<ec:table 
		tableId="pdShipStrategyMainListTable"
		items="pdShipStrategyMainList"
		var="pdShipStrategyMain"
		action="${pageContext.request.contextPath}/pdShipStrategyMains.html"
		width="100%"
		form="frm"
		retrieveRowsCallback="limit"
		rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
		<ec:row >
   			<ec:column property="valueCode" title="sysListValue.valueCode" />
   			<ec:column property="valueTitle" title="sysListValue.valueTitle" />
   			<ec:column property="exCompanyCode" title="sysListValue.exCompanyCode" />
   			<ec:column property="orderNo" title="sysModule.orderNo" />
   			<ec:column property="priority" title="pd.priority" />
   			<ec:column property="importance" title="pdShipStrategyMain.importance" >
   				<jecs:code listCode="pdshipstrategymain.importance" value="${pdShipStrategyMain.importance}"/>
   			</ec:column>
   			<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
				<img src="<c:url value='/images/icons/editIcon.gif'/>" 
					onclick="javascript:editPdShipStrategyMain('${pdShipStrategyMain.valueId}')"
					style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
			</ec:column>
		</ec:row>
	</ec:table>	
</form>
<script type="text/javascript">

   function editPdShipStrategyMain(valueId){
   		<jecs:power powerCode="editPdShipStrategyMain">
					window.location="editPdShipStrategyMain.html?strAction=editPdShipStrategyMain&valueId="+valueId;
			</jecs:power>
		}

</script>
