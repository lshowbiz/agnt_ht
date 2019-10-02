<%@ page language="java" errorPage="/error.jsp" contentType="text/html; charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="alCharacterTypeList.title"/></title>
<content tag="heading"><jecs:locale key="alCharacterTypeList.heading"/></content>
<meta name="menu" content="AlCharacterTypeMenu"/>

<link rel="StyleSheet" href="${pageContext.request.contextPath}/styles/dtree.css" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/dtree.js"></script>

<form action="" method="get" name="alCharacterTypeForm" id="alCharacterTypeForm">
<input type="hidden" name="typeId"/>
<%--<div id="titleBar">
	<jecs:power powerCode="addAlCharacterType">
    <input type="button" class="button" onclick="addAlCharacterType(this.form)" value="<jecs:locale key="member.new.num"/>"/>
    </jecs:power>
    <jecs:power powerCode="editAlCharacterType">
    <input type="button" class="button" onclick="editAlCharacterType(this.form)" value="<jecs:locale key="button.edit"/>"/>
    </jecs:power>
</div>--%>
 
<div class="dtree">
<script type="text/javascript">
	d = new dTree('d');
	d.add(0,-1,"<jecs:locale key="webapp.company.title"/>","");
	<c:forEach items="${applicationScope.companyList }" var="com">
		<c:if test="${sessionScope.sessionLogin.companyCode==com.companyCode or sessionScope.sessionLogin.companyCode=='AA'}">
		d.add(${com.companyId}, 0,"<jecs:locale key="${com.companyName }"/>","javascript:selectBankByCompany('${com.companyCode}')");
		</c:if>
	</c:forEach>
   
   document.write(d);
</script>

</div>

</form>

<script type="text/javascript">
function selectBankByCompany(companyCode){
	window.parent.frmCharacterKey.location="sysBanks.html?companyCode="+companyCode;
}
</script>