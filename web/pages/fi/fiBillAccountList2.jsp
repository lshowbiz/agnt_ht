<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="fiBillAccountList.title"/></title>
<content tag="heading"><jecs:locale key="fiBillAccountList.heading"/></content>
<meta name="menu" content="FiBillAccountMenu"/>


<form action="businessList.html" method="post" name="fiBillAccountSearchForm" id="fiBillAccountSearchForm">
<div class="searchBar">
商户号：
<input name="billAccountCode" type="text" value="${param['billAccountCode'] }" size="14"/>
<span id="jxsshbh">经销商编号：</span>
<input name="userCode" id="userCode" type="text" value="${param['userCode'] }" size="14"/>
<select name="province" id="province" class="mySelect">
	<option value="" selected="selected"><ng:locale key="list.please.select"/></option>
	<c:forEach items="${alStateProvinces }" var="alStateProvinceVar">
		 <option value="${alStateProvinceVar.stateProvinceId }" 
		 <c:if test="${alStateProvinceVar.stateProvinceId eq param['province']}">selected</c:if>>${alStateProvinceVar.stateProvinceName }</option>
	</c:forEach>
</select>
							

<jecs:label key="fiBillAccount.accountName"/>
<input name="accountName" type="text" value="${param['accountName'] }" size="14"/>

平台类型：<jecs:list name="providerType" listCode="paycompany" value="${param.providerType}" defaultValue="" showBlankLine="true"/>
商户类型：
<select id="businessType" name="businessType">
	<option value="1" <c:if test='${param["businessType"] == 1}'>selected="selected"</c:if> >非全额</option>
	<option value="2" <c:if test='${param["businessType"] == 2}'>selected="selected"</c:if> >全额</option>
</select>
		
<input name="search" class="button" type="submit" value="<jecs:locale key="operation.button.search"/>"/>
<jecs:power powerCode="addFiBillAccount">
	<input type="button" class="button" onclick="location.href='<c:url value="/editBusiness.html"/>?strAction=add'" value="<jecs:locale key="button.add"/>"/>
</jecs:power>
</div>
</form>
<div style="margin: 2px">
	<ec:table 
		tableId="fiBillAccountListTable"
		items="fiBillAccountList"
		var="fiBillAccount"
		action="${pageContext.request.contextPath}/businessList.html"
		width="100%"
		retrieveRowsCallback="limit"
		rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
					<ec:row >
	    			<ec:column property="bill_account_code" title="商户号" />
	    			<ec:column property="business_type1" title="${param.businessType == 1 ? '省份' : '经销商编号'}" >
	    				<c:if test="${fiBillAccount.business_type==1}">
							<jecs:region regionType="p" regionId="${fiBillAccount.province}"></jecs:region>
						</c:if>
	    				<c:if test="${fiBillAccount.business_type==2}">${fiBillAccount.user_code }</c:if>
					</ec:column>
	    			<ec:column property="max_money" title="最大限额" />
					<ec:column property="providerType" title="平台类型" >
						<jecs:code listCode="paycompany" value="${fiBillAccount.provider_type}"/>
					</ec:column>
	    			<ec:column property="status" title="状态" >
	    				<c:if test="${fiBillAccount.status==0}">启用</c:if>
	    				<c:if test="${fiBillAccount.status==1}">禁用</c:if>
	    			</ec:column>
					<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
					<jecs:power powerCode="editFiBillAccount">
						<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editFiBillAccount('${fiBillAccount.account_id}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</jecs:power>
					</ec:column>
				</ec:row>
	</ec:table>	
</div>
<script type="text/javascript">
var $businessType = document.getElementById("businessType");
var $userCode = document.getElementById("userCode");
var $province = document.getElementById("province");

$businessType.onchange=function(){
	var _str =   "经销商编号";
	if($businessType.value == 1){
		_str = "省份";
		$userCode.disabled=true;
		$province.disabled=false;
		$userCode.style.display="none";
		$province.style.display="inline";
	}else{
		$userCode.disabled=false;
		$province.disabled=true;
		$userCode.style.display="inline";
		$province.style.display="none";
	}
	document.getElementById("jxsshbh").innerHTML = _str;
};
$businessType.onchange();
function editFiBillAccount(accountId){
	<jecs:power powerCode="editFiBillAccount">
			window.location="editBusiness.html?strReq=edit&accountId="+accountId;
	</jecs:power>
}
</script>
<%--http://localhost:8081/jmhg_pay/businessList.html--%>