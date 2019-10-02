<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html; charset=utf-8" %>

<title><jecs:locale key="jpmMemberConfigList.title"/></title>
<content tag="heading"><jecs:locale key="jpmMemberConfigList.heading"/></content>
<meta name="menu" content="JpmMemberConfigMenu"/>
<style>
.ctab label{font-weight:bold;}
.hideTd span { margin:0 10px;}
.eXtremeTable .odd td, .eXtremeTable .even td,.eXtremeTable .highlight td { line-height:22px;}
.dk { display:none;}
.dk th { text-align:left; background-color:#CCC;}
.dk td { background-color:#CCC;}
.evendetail td { background-color:#CFDDF0;}
</style>
<!--
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div> -->

<table width="100%" border="0" class="ctab">
	<tr>
		<td><jecs:title key="jpmProductSaleTeamType.productName"/></td>
		<td>${model.productName}</td>
		<td><jecs:title key="jpmProductSaleTeamType.allCount"/></td>
		<td>${model.qty}</td>
		<td><jecs:title key="jpmProductSaleTeamType.wasConfigNum"/></td>
		<td>${model.wasConfigNum}</td>
		<td><jecs:title key="jpmProductSaleTeamType.configNum"/></td>
		<td>${model.configNum}</td>
		<td><jecs:title key="jpmProductSaleTeamType.allWeightCount"/></td>
		<td>${model.allWeightCount}</td>
		<td><jecs:title key="jpmProductSaleTeamType.wasWeightCount"/></td>
		<td>${model.wasWeightCount}</td>
		<td><jecs:title key="jpmProductSaleTeamType.weightCount"/></td>
		<td>${model.weightCount}</td>
		
		<c:if test="${ model.configNum != '0'}">
			<td><a href="jpmWineMemberOrders.html?strAction=addConfigPage&saveType=0&moId=${model.moId }&productName=${ model.productName }&productId=${ model.productId }&molId=${ model.molId }&productNo=${ model.productNo}" style="color:blue;"><jecs:locale key="config.add"/></a></td>
		</c:if>
		<c:if test="${ model.configNum == '0'}">
			<td><a href="javascript:void(0);" style="color:blue;"><jecs:locale key="config.add"/></a></td>
		</c:if>
	</tr>

</table>




<ec:table 
	tableId="jpmMemberConfigListTable"
	items="jpmMemberConfigList"
	var="jpmMemberConfig"
	action="${pageContext.request.contextPath}/jpmMemberConfigs.html"
	width="100%"
	retrieveRowsCallback="limit"		
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
		<ec:row onclick="showTR($('tr${ROWCOUNT}'));">
			<ec:column property="productName" title="jpmProductSaleTeamType.productName" />
			<ec:column property="amount" title="jpmMemberConfig.configNum" />
			<ec:column property="status" title="jpmMemberConfig.status" >
				<jecs:code listCode="jpmMemberConfig.status" value="${jpmMemberConfig.status}"/>
			</ec:column>
			
			<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
				<c:if test="${config.status ne '2 '}">
					<c:if test="${jpmMemberConfig.status eq '1 '}">
					<!-- <a href="javascript:void(0);" style="color:blue;" onclick="payment('payMemberConfig','${jpmMemberConfig.configNo }','${jpmMemberConfig.payment }');" title="<jecs:locale key="jpmMemberConfig.payment"/>" class="Payment"><jecs:locale key="jpmMemberConfig.payment"/></a> -->
						<!-- <a style="color:blue;" href="jpmWineMemberOrders.html?strAction=pay&productName=${jpmMemberConfig.productName}&configNo=${jpmMemberConfig.configNo}&molId=${ model.molId }&productNo=${ model.productNo }" ><jecs:locale key="jpmMemberConfig.payment"/></a> -->
					
					</c:if>
					<c:if test="${jpmMemberConfig.status eq '0 ' || jpmMemberConfig.status eq ''}">
					
						<c:if test="${jpmMemberConfig.oddWeight ne '0'}">
							<a style="color:blue;" href="jpmWineMemberOrders.html?strAction=addConfigPage&saveType=1&productName=${jpmMemberConfig.productName}&configNo=${jpmMemberConfig.configNo}&molId=${ model.molId }&productNo=${ model.productNo }" ><jecs:locale key="jpmMemberConfig.addSpecConfig"/></a>
						</c:if>
						<c:if test="${jpmMemberConfig.oddWeight eq '0'}">
							<a style="color:blue;" href="jpmWineMemberOrders.html?strAction=submitConfig&productName=${jpmMemberConfig.productName}&configNo=${jpmMemberConfig.configNo}&molId=${ model.molId }&productNo=${ model.productNo }" ><jecs:locale key="jpmMemberConfig.submitConfig"/></a>
						</c:if>
					
					</c:if>
				</c:if>
			</ec:column>
		</ec:row>

		<c:if test="${ROWCOUNT>0}">
			<c:forEach items="${jpmMemberConfig.jpmConfigSpecDetailed}" var="jpmConfigSpec" varStatus="status">
			<tr class="evendetail">
				<td style="text-indent:4em;"><b>规格名称：</b>${jpmConfigSpec.productTemplateName}</td>
				<td><b>配置重量：</b>${jpmConfigSpec.complateWeight}</td>
				<td><b>价格：</b>${jpmConfigSpec.price}</td>
				
				<c:if test="${config.status ne '2 '}">
					<c:if test="${jpmMemberConfig.status eq '1 '}">
					<td>
						<a href="jpmSendConsignments.html?userCode=${model.userCode }&specNo=${jpmConfigSpec.specNo}&specName=${jpmConfigSpec.productTemplateName }&molId=${model.molId}&productNo=${model.productNo}" class="ps" style="color:blue;" ><jecs:locale key="jpmMemberConfig.sendConsignment"/></a>
					</c:if>
					</td>
					<c:if test="${jpmMemberConfig.status eq '0 ' || jpmMemberConfig.status eq ''}">
					<td>
						<a href="jpmWineMemberOrders.html?strAction=addConfigPage&saveType=2&configNo=${jpmMemberConfig.configNo}&specNo=${jpmConfigSpec.specNo}&molId=${model.molId}&productNo=${model.productNo}" class="ps" style="color: blue;"><jecs:locale key="jpmMemberConfig.updateConfig"/></a>
						&nbsp;&nbsp;
						<a href="javascript:void(0);" onclick="del('jpmWineMemberOrders.html?strAction=delConfig','${jpmMemberConfig.configNo}','${jpmConfigSpec.specNo}','${model.molId}','${model.productNo}');" class="ps" style="color:red;"><jecs:locale key="operation.button.delete"/></a>
					</c:if>
					</td>
				</c:if>
			</tr>
			<tr class='dk'>
				<th>&nbsp;</th>
				<th>配件名称</th>
				<th>数量</th>
				<th>价格</th>
			</tr>
				<c:forEach items='${jpmConfigSpec.jpmConfigDetailedList}' var='jpmConfigDetailed' varStatus='status'>
				<tr class='dk'>
					<td>&nbsp;</td>
					<td>${jpmConfigDetailed.subName}</td>
					<td>${jpmConfigDetailed.subAmount}</td>
					<td>${jpmConfigDetailed.price}</td>
				</tr>
				</c:forEach>
			</c:forEach>
			
		</c:if>
		
</ec:table>	

<script>

   function editJpmMemberConfig(configNo){
   		<jecs:power powerCode="editJpmMemberConfig">
					window.location="editJpmMemberConfig.html?strAction=editJpmMemberConfig&configNo="+configNo;
			</jecs:power>
		}

</script>
<script>	
	function del(url,configNo,specNo,molId,productNo){
		if(confirm("<jecs:locale key="jpmMemberConfig.confirm.del"/>")){
			window.location=url+"&configNo="+configNo+"&specNo="+specNo+"&molId="+molId+"&productNo="+productNo;
		}else{
			return false;
		}
	}
	function payment(url,configNo,isPayment){
		if(false == isPayment || 'false' == isPayment){
			alert('<jecs:locale key="jpmMemberConfig.alert.isPayment"/>');
		}else{
			window.location=url+"?1=1&orderId="+configNo;
		}
	}
	function showItem(sel){
		var $dk = jQuery(sel).nextUntil('.evendetail');
		$dk.show();
	}
	function hideItem(sel){
		jQuery(sel).nextUntil('.evendetail').hide();
	}
	
	jQuery(function(){
		jQuery('.evendetail').click(function(){
			jQuery(this).next('.dk').is(":visible") ? hideItem(this) : showItem(this);
		});
		
		jQuery('.ps').click(function(e){
			e.stopPropagation(); 
		});
	});

</script>