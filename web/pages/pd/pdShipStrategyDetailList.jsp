<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="pdShipStrategyDetailList.title"/></title>
<content tag="heading"><jecs:locale key="pdShipStrategyDetailList.heading"/></content>
<meta name="menu" content="PdShipStrategyDetailMenu"/>

<c:set var="buttons">
<div class="searchBar">
		<jecs:power powerCode="confirmPdShipStrategyList">
			<div class="new_searchBar">
				<jecs:title key="jpmProductSaleImage.status" />
				<jecs:list listCode="jmimemberteam.status" name="status2" id="status2"
				value="${param.status2}" defaultValue="0" />
			</div>	
		</jecs:power>
		<div class="new_searchBar">
			<jecs:power powerCode="confirmPdShipStrategyList">
						<button type="button" class="btn btn_long"  style="width:auto" onclick="batchPrint('${param.ssId}');">
										        <jecs:locale  key='pdShipStrategyDetail.batchAudit'/>
					    </button>
			</jecs:power>
			<jecs:power powerCode="addPdShipStrategyDetail">
						 <button type="button" class="btn btn_ins"  style="width:auto" onclick="pdShipStrategyDetailAdd('${param.ssId }');">
							        <jecs:locale key="button.add"/>
					     </button>
			</jecs:power>
        </div>
</div>
		<%-- <jecs:power powerCode="addPdShipStrategyDetail">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editPdShipStrategyDetail.html"/>?strAction=addPdShipStrategyDetail&ssId=${param.ssId }'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power>
		<jecs:power powerCode="confirmPdShipStrategyList">
			<jecs:locale key="jpmProductSaleImage.status" />
			<jecs:list listCode="jmimemberteam.status" name="status2" id="status2"
			value="${param.status2}" defaultValue="0" />
	  		<input type="button" class="button" onclick="batchPrint('${param.ssId}');" value="<jecs:locale  key='pdShipStrategyDetail.batchAudit'/>"/>
		</jecs:power> --%>
</c:set>
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>

<ec:table 
	tableId="pdShipStrategyDetailListTable"
	items="pdShipStrategyDetailList"
	var="pdShipStrategyDetail"
	action="${pageContext.request.contextPath}/pdShipStrategyDetails.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
				<ec:row >
				<ec:parameter name="strAction" value="${param.strAction}"/>
				<ec:parameter name="ssId" value="${param.ssId}"/>
				<ec:column property="1" title="alCharacterKey.select1" sortable="false" styleClass="centerColumn">
					<input type="checkbox" name="selectedId" id="selectedId" value="${pdShipStrategyDetail.ssdId}" class="checkbox"/>
				</ec:column>
    			<ec:column property="ssId" title="pdShipStrategyDetail.ssId" >
    			<jecs:code listCode="ship.strategy" value="${pdShipStrategyDetail.ssId}"/>
    			</ec:column>
    			<ec:column property="shipArea" title="alStateProvince.stateProvinceName" >
    			<c:forEach items="${alStateProvinces}" var="province">
    				<c:if test="${province.stateProvinceId == pdShipStrategyDetail.shipArea}">
    					${province.stateProvinceName}
    				</c:if>
    			</c:forEach>
    			</ec:column>
    			<ec:column property="warehouseNo" title="busi.warehouse.warehouseno" />
    			<ec:column property="shNo" title="pd.shno" >
    				<jecs:code listCode="pd.shno" value="${pdShipStrategyDetail.shNo}"/>
    			</ec:column>
    			<ec:column property="status" title="jpmProductSaleTeamType.state">
					<jecs:code listCode="jmimemberteam.status"
						value="${pdShipStrategyDetail.status}" />
				</ec:column>
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
						<img src="<c:url value='/images/icons/editIcon.gif'/>" 
							onclick="javascript:editPdShipStrategyDetail('${pdShipStrategyDetail.ssdId}', '${pdShipStrategyDetail.ssId}')"
							style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
				</ec:column>
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editPdShipStrategyDetail(ssdId, ssId){
   		<jecs:power powerCode="editPdShipStrategyDetail">
					window.location="editPdShipStrategyDetail.html?strAction=editPdShipStrategyDetail&ssdId="+ssdId + "&ssId="+ssId;
			</jecs:power>
		}

		function batchPrint(ssId){
	   		var status2 = document.getElementById("status2");
	   	    var selectedId = document.getElementsByName("selectedId");
			var selectStr = '';
			for(var i=0;i<selectedId.length;i++){ 
				if(selectedId[i].checked){
					selectStr += selectedId[i].value+",";
				}
			}  
			if(selectStr==''){
				alert("<jecs:locale key='amMessage.discuss.select'/>");//?
				return;
			} 
			//window.open("<c:url value='/pdOrderPrints.html'/>?strAction=printSendInfo&siNo="+selectStr);	
			//window.location="editJpmProductSaleNew.html?strAction=confirmJpmProductSaleNew&uniNoStr="+selectStr+"&status2="+status2.value;
	   		window.location="editPdShipStrategyDetail.html?strAction=confirmPdShipStrategyList&uniNoStr="+selectStr+"&status2="+status2.value+"&ssId="+ssId;
	   		
	   }
	   
	  function pdShipStrategyDetailAdd(ssId){
	       	window.location="editPdShipStrategyDetail.html?strAction=addPdShipStrategyDetail&ssId="+ssId;
	  }
</script>
