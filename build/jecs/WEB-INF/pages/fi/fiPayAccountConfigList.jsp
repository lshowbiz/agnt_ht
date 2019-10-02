<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=utf-8" language="java"%>
<title><jecs:locale key="fiPayAccountConfigList.title"/></title>
<content tag="heading"><jecs:locale key="fiPayAccountConfigList.heading"/></content>
<meta name="menu" content="FiPayAccountConfigMenu"/>

<form action="fiPayAccountConfigs.html" method="get" name="fiPayAccountConfigSearchForm" id="fiPayAccountConfigSearchForm">
<div class="searchBar">

	
	省份：<select id="province" name="province" >
			<option  value=""></option>
			<c:forEach items="${alStateProvinces}" var="alStateProvince">
				<option  value="${alStateProvince.stateProvinceId}" <c:if test='${alStateProvince.stateProvinceId == param["province"]}'>selected="selected"</c:if>>
					${alStateProvince.stateProvinceName}
				</option>
			</c:forEach>
            
		</select>
	商户号：<input name="accountCode" type="text" value="${param['accountCode'] }" size="14"/>
			
	<input name="search" class="button" type="submit" value="<jecs:locale key="operation.button.search"/>"/>

	<jecs:power powerCode="unitFiPayAccountConfig">
	<input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/fiPayAccountConfigs.html"/>?strAction=unitFiPayAccountConfig'"
			        value="初始化"/>
</jecs:power>
</div>
</form>


			        
			        
<ec:table 
	tableId="fiPayAccountConfigListTable"
	items="fiPayAccountConfigList"
	var="fiPayAccountConfig"
	action="${pageContext.request.contextPath}/fiPayAccountConfigs.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="/jecs/images/extremetable/*.gif">
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editFiPayAccountConfig('${fiPayAccountConfig.province}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
				<ec:column property="provinceName" title="省份" />
    			<ec:column property="fiPayAccount.accountCode" title="商户号" />
    			<ec:column property="fiPayAccount.accountName" title="注册名称" />
    			<ec:column property="fiPayAccount.providerType" title="支付平台" >
					<jecs:code listCode="providertypes" value="${fiPayAccountConfig.fiPayAccount.providerType}"/>
				</ec:column>
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editFiPayAccountConfig(province){
   		<jecs:power powerCode="editFiPayAccountConfig">
					window.location="editFiPayAccountConfig.html?strAction=editFiPayAccountConfig&province="+province;
			</jecs:power>
		}

</script>
