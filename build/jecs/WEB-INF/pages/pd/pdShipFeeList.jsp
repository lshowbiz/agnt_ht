<%@ page pageEncoding="UTF-8"%> 
<%@ include file="/common/taglibs.jsp"%> 

<title><jecs:locale key="pdShipFeeList.title"/></title>
<content tag="heading"><jecs:locale key="pdShipFeeList.heading"/></content>
<meta name="menu" content="PdShipFeeMenu"/>

<c:set var="buttons">
<div class="searchBar">
        <jecs:power powerCode="addPdShipFee">
            <div class="new_searchBar">
                <button type="button" class="btn btn_ins"  style="width:auto" onclick="pdPdShipFeeAdd();">
					        <jecs:locale key="button.add"/>
			    </button>
			</div>
		</jecs:power>
</div>
		<%-- <jecs:power powerCode="addPdShipFee">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editPdShipFee.html"/>?strAction=addPdShipFee'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power> --%>
</c:set>
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>
<%-- <form action="${pageContext.request.contextPath}/pdShipFees.html?strAction=pdShipFeeList" method="post">
	订单金额：<input name="amount" />
	订单类型：<input name="orderType" />
	临界金额：<input name="criticalPoint" />
	省份编码：<input name="provinceName" />
	<input type="submit" />
</form>
物流费是多少了-----：${testfee } --%>
<ec:table 
	tableId="pdShipFeeListTable"
	items="pdShipFeeList"
	var="pdShipFee"
	action="${pageContext.request.contextPath}/pdShipFees.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="false" imagePath="${pageContext.request.contextPath}/images/extremetable/*.gif">
		<input type="hidden" name="strAction" value="pdShipFeeList" />
		<ec:row style="text-align: center;">
			<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
						<img src="<c:url value='/images/icons/editIcon.gif'/>" 
							onclick="javascript:editPdShipFee('${pdShipFee.psfId}')"
							style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
			</ec:column>
			<ec:column property="editA" title="省份名字">
				<c:forEach items="${alStateProvinces}" var="p">
					<c:if test="${p.stateProvinceId eq pdShipFee.provinceName}">
		    			${p.stateProvinceName}
					</c:if>
				</c:forEach>
			</ec:column>
   			<ec:column property="provinceName" title="省份编码" />
   			<ec:column property="fee" title="物流费" />
		</ec:row>
</ec:table>	

<script type="text/javascript">
	function editPdShipFee(psfId) {
 		<jecs:power powerCode="editPdShipFee">
			window.location="editPdShipFee.html?strAction=editPdShipFee&psfId="+psfId;
		</jecs:power>
	}
	
	function pdPdShipFeeAdd(){
		window.location="editPdShipFee.html?strAction=addPdShipFee"
	}
</script>
