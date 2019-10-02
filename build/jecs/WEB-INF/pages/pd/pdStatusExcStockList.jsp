<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="pdStatusExcStockList.title"/></title>
<content tag="heading"><jecs:locale key="pdStatusExcStockList.heading"/></content>
<meta name="menu" content="PdStatusExcStockMenu"/>

<c:set var="buttons">
		<jecs:power powerCode="addPdStatusExcStock">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editPdStatusExcStock.html"/>?strAction=addPdStatusExcStock'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power>
</c:set>
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>
<ec:table 
	tableId="pdStatusExcStockListTable"
	items="pdStatusExcStockList"
	var="pdStatusExcStock"
	action="${pageContext.request.contextPath}/pdStatusExcStocks.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sort./images/extremetableecs/images/extremetable/*.gif">
				<ec:row >
				<c:if test="${sessionScope.sessionLogin.isManager}">
    			<ec:column property="companyCode" title="sys.company.code" />
    		</c:if>
    			<ec:column property="createUrsCode" title="pd.createUserNo" />
    			<ec:column property="createTime" title="pd.createTime" >
    				<fmt:formatDate value="${pdStatusExcStock.createTime}" pattern="yyyy-MM-dd"/>
    			</ec:column>
    			
    			 <ec:column property="checkUrsCode" title="busi.user.check" />
    			<ec:column property="pdWarehouse.warehouseNo" title="busi.warehouse.warehouseno" />
    			<ec:column property="checkTime" title="pd.checkTime" >
    				<fmt:formatDate value="${pdStatusExcStock.checkTime}" pattern="yyyy-MM-dd"/>
    			</ec:column>
    			 
    			 
    			<ec:column property="okUrsCode" title="pdStatusExcStock.okUrsCode" />
    				
    			 <ec:column property="okTime" title="pd.okTime" >
    				<fmt:formatDate value="${pdStatusExcStock.okTime}" pattern="yyyy-MM-dd"/>
    			</ec:column>
    		 
    		 <ec:column property="orderFlag" title="pd.orderflag" >
    				<jecs:code listCode="pd.orderflag.exchangeorder" value="${pdStatusExcStock.orderFlag}"/>
    			</ec:column>
    			
    				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/newIcons/pencil_16.gif'/>" 
								onclick="javascript:editPdStatusExcStock('${pdStatusExcStock.seNo}')"
								style="cursor: pointer;" title="<wecs:locale key="button.edit"/>"/> 
					</ec:column>
				
				<c:if test="${ROWCOUNT>0}">
						<c:if test="${ROWCOUNT%2!=0}"><tr class="odddetail" style="display:none" id="tr${ROWCOUNT}"></c:if>  
						<c:if test="${ROWCOUNT%2==0}"><tr class="evendetail" style="display:none" id="tr${ROWCOUNT}"></c:if> 
							<td align="right" valign="top">&nbsp;</td>
							<c:if test="${sessionScope.sessionLogin.isManager}">
								<td colspan="9">
							</c:if>
							<c:if test="${!sessionScope.sessionLogin.isManager}">
								<td colspan="8">
							</c:if>
							
								<c:forEach items="${pdStatusExcStock.pdStatusExcStockDetails}" var="pdStatusExcStockDetail" varStatus="status">
									<c:if test="${!status.first}"><br/></c:if>
										
									<font color=#888888>[<font color="green">${pdStatusExcStockDetail.qty}</font>]${pdStatusExcStockDetail.productNo}/
										${compamyProductMap[pdStatusExcStockDetail.productNo]}
										</font>
									
								</c:forEach>
							</td>
							
						</tr>
				</c:if>
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editPdStatusExcStock(seNo){
   		<jecs:power powerCode="editPdStatusExcStock">
					window.location="editPdStatusExcStock.html?strAction=editPdStatusExcStock&seNo="+seNo;
			</jecs:power>
		}

</script>
