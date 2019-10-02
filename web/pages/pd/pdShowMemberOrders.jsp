<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jpoMemberOrderList.title"/></title>
<content tag="heading"><jecs:locale key="jpoMemberOrderList.heading"/></content>
<meta name="menu" content="JpoMemberOrderMenu"/>



<form name="jpoMemberOrderList" action="<c:url value='/pdExchangeOrders.html'/>" >
	<input type="hidden" id="strAction" name="strAction" value="${requestParaMap.strAction}"/>
	<div class="searchBar">
	<div class="new_searchBar">
	<jecs:label  key="customerRecord.customerNo"/>
		
						<input type="text" name="customCode"  style="cursor:pointer;width:80px;" id="customCode"/>
						<img src="<c:url value="/images/l_1.gif"/>" id="person"  onclick="searchUser()" style="cursor:pointer;" title="<jecs:locale key="operation.button.search"/>"/> 
						<input type="text" name="customName"  style="cursor:pointer;width:80px;" id="customName" readonly="true"/>
	</div>
	<div class="new_searchBar">
					 <button type="submit" class="btn btn_sele"  style="width:auto" >
										        <jecs:locale  key='operation.button.search'/>
				    </button>
						<%-- <input type="submit" class="button" value="<jecs:locale  key='operation.button.search'/>"/> --%>
	</div>
	</div>

<ec:table 
	tableId="jpoMemberOrderListTable"
	items="memberOrders"
	var="jpoMemberOrder"
	form="jpoMemberOrderList"
	action="${pageContext.request.contextPath}/pdExchangeOrders.html"
	width="100%"
	method="post"
	 showPagination="false"
	 sortable="false"
   imagePath="./images/extremetable/*.gif">
				<ec:row >
				
    			<ec:column property="memberOrderNo" title="poMemberOrder.memberOrderNo" />
    			<ec:column property="orderType" title="poMemberOrder.orderType" styleClass="centerColumn">
    				<jecs:code listCode="po.all.order_type" value="${jpoMemberOrder.orderType}"/>
    			</ec:column>
    			<ec:column property="sysUser.userCode" title="miMember.memberNo" />
    			<ec:column property="amount" title="busi.order.amount" />
    			<ec:column property="pvAmt" title="poMemberOrder.pvAmt" />
 
    			<ec:column property="status" title="pd.checkstatus">
    				<jecs:code listCode="po.status" value="${jpoMemberOrder.status}"/>
    			</ec:column>
    			<ec:column property="submitStatus" title="poMemberOrder.checkStatus">
    				<jecs:code listCode="po.checkstatus" value="${jpoMemberOrder.submitStatus}"/>
    			</ec:column>
    			
    			<ec:column property="edit" title="title.operation" sortable="false" styleClass="centerColumn" viewsAllowed="html"	>
							<!--<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:createPdExchangeOrder('${jpoMemberOrder.moId}')" 
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> -->
							<input type="button" class="btn btn_ins" onclick="createPdExchangeOrder('${jpoMemberOrder.moId}')" 
								style="cursor: pointer;" value="<jecs:locale  key='sysOperationLog.moduleName'/>"/>
						
					</ec:column>
					
    			<c:if test="${ROWCOUNT>0}">
						<c:if test="${ROWCOUNT%2!=0}"><tr id="tr${ROWCOUNT}"   class="odddetail"></c:if>
						<c:if test="${ROWCOUNT%2==0}"><tr id="tr${ROWCOUNT}"  class="evendetail"></c:if>
							<td colspan="7">
								<c:forEach items="${jpoMemberOrder.jpoMemberOrderList}" var="jpoMemberOrderDetail" varStatus="status">
									<c:if test="${!status.first}"><br/></c:if>
									&nbsp;&nbsp;<img src="<c:url value='/images/menu_tree/file12.gif'/>"/>&nbsp;&nbsp;<font color=#888888>[<font color="green">${jpoMemberOrderDetail.qty}</font>]${jpoMemberOrderDetail.jpmProductSaleTeamType.jpmProductSaleNew.jpmProduct.productNo}/${jpoMemberOrderDetail.jpmProductSaleTeamType.jpmProductSaleNew.jpmProduct.productName}</font>
								</c:forEach>
							</td>
						</tr>
				</c:if>
				</ec:row>

</ec:table>	
</form>	
<script type="text/javascript">

   function createPdExchangeOrder(moId){
   		 waiting();
					window.location="pdExchangeOrders.html?strAction=addPdExchangeOrder&moId="+moId;
		 
		}


function searchUser(){
    			var userCodeExample = $('customCode').value;
    			//open("<c:url value='/sysUserSelect.html'/>?selectControl=agentAndMember&userCode="+userCodeExample);
    			var ret = window.showModalDialog("<c:url value='/sysUserSelect.html'/>?strAction=companyUserSelect&selectControl=agentAndMember&userCode="+userCodeExample,null,"dialogHeight:400px;dialogWidth:1000px;");
    			
    			$('customCode').value=ret['userCode'];
    			$('customName').value=ret['userName'];
    			
    		}
</script>
