<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@ include file="/common/taglibs.jsp"%>
<meta name="viewport" content="width=device-width, initial-scale=1"> 
<meta name="decorator" content="mobile"/>
<script>
	function nextFun(val) {
		var validLevel = changeLevelValue();
		if (validLevel == "0") {
			alert('<jecs:locale key="level.error"/>');
			document.getElementById("seleceLevel").focus();
		} else {
			var userVal = document.getElementById("seleceLevel").value;
			var ctx = "${pageContext.request.contextPath}";
			if (val == 'CN') {
				location.href = ctx
						+ "/editJpoMemberFOrder.html?strAction=addPoMemberFOrder&userVal="
						+ userVal;
			} else {
				if (validLevel != "6") {
					alert('<jecs:locale key="levelLc.error"/>');
					document.getElementById("seleceLevel").focus();
				} else {
					<jecs:power powerCode="addPoMemberFOrderLC">
					<c:if test='${empty jpoMemberOrder.moId}'>
					location.href = ctx
							+ "/editJpoMemberFOrderLC.html?strAction=addPoMemberFOrderLC&userVal="
							+ userVal; // 'LC'
					</c:if>
					</jecs:power>
				}
			}
		}
	}

	function changeLevelValue() {
		var valLevel = document.getElementById("seleceLevel").value;
		return valLevel;
	}
</script>
<style>
	.ui-select {
		width: 314px;
	}
</style>

<form action="jpoMemberFOrders.html" method="get" name="searchForm" id="searchForm">
<c:set var="buttons">
	<c:if test="${sessionScope.sessionLogin.userType!='C'}">
		<c:if test="${fn:substring(sessionScope.sessionLogin.jmiMember.jmiRecommendRef.treeIndex,0,36)=='00100000000000000b00n00000u003000013'}">
			<c:if test="${sessionScope.sessionLogin.companyCode=='CN' }">
				<input type="button" onclick="nextFun('LC');" value="<jecs:locale key="operation.button.addLC"/>" />
			</c:if>
			<c:if test="${sessionScope.sessionLogin.companyCode!='CN' }">
				<input type="button" onclick="nextFun('CN');" value="<jecs:locale key="button.next"/>" />
			</c:if>
		</c:if>
		<c:if test="${fn:substring(sessionScope.sessionLogin.jmiMember.jmiRecommendRef.treeIndex,0,36)!='00100000000000000b00n00000u003000013'}">
			<input type="button" onclick="nextFun('CN');" value="<jecs:locale key="button.next"/>" />
		</c:if>
	</c:if>
	
	<input  type="button" class="button" name="back"  onclick="window.location.href='jpoMemberFOrders.html?needReload=1'" value="<jecs:locale key="operation.button.return"/>" />
</c:set>

<input type="hidden" name="strAction" value="${param.strAction }"/>
<table class='detail' width="100%">
  <tr>
	  <td>
	  	<jecs:locale key="miMember.cardType"/>
	  </td>
   </tr>
   <tr>
	  <td align="left">
	    <select id="seleceLevel"  onChange= "changeLevelValue()">
	     <option value='0'>   </option>
	      <option value='1'><jecs:locale key="bd.cardType1"/> </option>
	      <option value='2'><jecs:locale key="bd.cardType2"/></option>
	      <option value='3'><jecs:locale key="bd.cardType3"/></option>
	      <option value='4'><jecs:locale key="bd.cardType4"/></option>
	      <option value='6'><jecs:locale key="bd.cardType6"/></option>
	    </select>
	  </td>
   </tr>
</table>
<c:out value="${buttons}" escapeXml="false"/>
</form>

