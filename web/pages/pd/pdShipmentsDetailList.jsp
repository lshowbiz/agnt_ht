<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="pdShipmentsDetailList.title"/></title>
<content tag="heading"><jecs:locale key="pdShipmentsDetailList.heading"/></content>
<meta name="menu" content="PdShipmentsDetailMenu"/>
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script> 
<c:set var="buttons">
		<jecs:power powerCode="addPdShipmentsDetail">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editPdShipmentsDetail.html"/>?strAction=addPdShipmentsDetail'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power>
		<input type="submit" class="button" value="<jecs:locale  key='operation.button.search'/>"/>
</c:set>
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>

<form name="searchForm" id="searchForm" action="<c:url value='/pdShipmentsDetails.html'/>" >
		<input type="hidden" id="strAction" name="strAction" value="${requestParaMap.strAction}"/>
		
<div class="searchBar">
<table class ="search" width="100%" >
		<tr>
				<th><jecs:locale key="sys.agent.agentNo"/></th>
				<td><input type="text" name="userCode" id="userCode" value="<c:out value='${requestParaMap.userCode}'/>" cssClass="text medium"/></td>
					
				<th><jecs:locale key="pd.orderType"/></th>
				<td><jecs:list listCode="pd.ordertype" name="orderType" id="orderType" value="${requestParaMap.orderType}" showBlankLine="true" defaultValue=""/>	</td>
				
				<th><jecs:locale key="busi.order.orderno"/></th>
				<td><input type="text" name="orderNo" id="orderNo" value="<c:out value='${requestParaMap.orderNo}'/>" cssClass="text medium"/></td>	
					
				<th><jecs:locale key="busi.product.productno"/></th>	
				<td><input type="text" name="productNo" id="productNo" value="<c:out value='${requestParaMap.productNo}'/>"  onclick="selectProduct();" cssClass="text medium"/></td>	
					
					
				<th><jecs:locale key="pd.createTime"/></th>	
					<td><input name="createTimeStart" size='11' id="createTimeStart"  value="${requestParaMap.createTimeStart}">
      	<img src="./images/calendar/calendar7.gif" id="img_startTime" style="cursor: pointer;" title="<jecs:locale key="Calendar.TT.SEL_DATE"/>"/> 
				<script type="text/javascript"> 
					Calendar.setup({
					inputField     :    "createTimeStart", 
					ifFormat       :    "%Y-%m-%d",  
					button         :    "img_startTime", 
					singleClick    :    true
					}); 
				</script>
				- <input name="createTimeEnd" size='11'  id="createTimeEnd"  value="${requestParaMap.createTimeEnd}">
      	<img src="./images/calendar/calendar7.gif" id="img_endTime" style="cursor: pointer;" title="<jecs:locale key="Calendar.TT.SEL_DATE"/>"/> 
				<script type="text/javascript"> 
					Calendar.setup({
					inputField     :    "createTimeEnd", 
					ifFormat       :    "%Y-%m-%d",  
					button         :    "img_endTime", 
					singleClick    :    true
					}); 
				</script></td>
		</tr>
</table>	
</div>
<ec:table 
	tableId="pdShipmentsDetailListTable"
	items="pdShipmentsDetailList"
	var="pdShipmentsDetail"
	action="${pageContext.request.contextPath}/pdShipmentsDetails.html"
	width="100%"
	retriev./images/extremetableowsDisplayed="20" sortable="true" imagePath="/jecs/images/extremetable/*.gif">
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/newIcons/pencil_16.gif'/>" 
								onclick="javascript:editPdShipmentsDetail('${pdShipmentsDetail.sdId}')"
								style="cursor: pointer;" title="<wecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="companyCode" title="pdShipmentsDetail.companyCode" />
    			<ec:column property="userCode" title="pdShipmentsDetail.userCode" />
    			<ec:column property="orderNo" title="pdShipmentsDetail.orderNo" />
    			<ec:column property="orderType" title="pd.orderType" >
    				<jecs:code listCode="pd.ordertype" value="${pdShipmentsDetail.orderType}"/>
    			</ec:column>
    			<ec:column property="productNo" title="busi.product.productno" />
    				<ec:column property="productName" title="pmProduct.productName" sortable="false">
    					${compamyProductMap[pdShipmentsDetail.productNo]}
    					
    					
    				</ec:column>
    			
    			<ec:column property="price" title="pd.price" styleClass="numberColumn"/>
    			<ec:column property="quantity" title="pd.qty" styleClass="numberColumn"/>
    			<ec:column property="remainQuantity" title="pdShipmentsDetail.remainQuantity" styleClass="numberColumn"/>
    			<ec:column property="createTime" title="pd.createTime" />
				</ec:row>

</ec:table>	
</form>

<script type="text/javascript">

   function editPdShipmentsDetail(sdId){
   		<jecs:power powerCode="editPdShipmentsDetail">
					window.location="editPdShipmentsDetail.html?strAction=editPdShipmentsDetail&sdId="+sdId;
			</jecs:power>
		}


function selectProduct(){
    		open("<c:url value='/pmProducts.html?strAction=selectProduct&selectControl=1'/>","","height=400, width=600, top=20, left=20, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no");
    		
    	}
</script>
