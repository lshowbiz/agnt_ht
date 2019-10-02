<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="sysPowerList.title"/></title>
<content tag="heading"><jecs:locale key="sysPowerList.heading"/></content>
<meta name="menu" content="SysPowerMenu"/>

<div class="topNavBar">
	<jecs:locale key="sysModule.current.selected"/>:<jecs:locale key="${sysModule.moduleName }"/>
</div>

<c:if test="${not empty sysModule.moduleId and sysModule.moduleId!=0}">
	<div id="titleBar">
		<jecs:power powerCode="addSysPower">
			<input type="button" class="button" onclick="location.href='<c:url value="/editSysPower.html"/>?strAction=addSysPower&moduleId=${sysModule.moduleId }'" <jecs:localemessage key="member.new.num"/>"/>
		</jecs:power>
	</div>
	
	<ec:table 
		tableId="sysPowerListTable"
		items="sysPowerList"
		var="sysPower"
		action="${pageContext.request.contextPath}/sysPowers.html"
		width="100%"
		retrieveRowsCallback="limit"
		rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
		<ec:row onclick="javascript:editSysPower('${sysPower.powerId}')">
			<ec:column property="powerCode" title="sysPower.powerCode" />
			<ec:column property="powerName" title="sysPower.powerName" >
				<jecs:locale key="${sysPower.powerName}"/>
			</ec:column>
			<ec:column property="powerDesc" title="sysPower.powerDesc" />
			<ec:column property="isDefault" title="title.default" styleClass="centerColumn">
				<jecs:code listCode="yesno" value="${sysPower.isDefault}"/>
			</ec:column>
			<ec:column property="isDefault" title="sysUsrTypePow.userType">
				<c:if test="${not empty sysPower.sysUsrTypePows}">
					<c:forEach items="${sysPower.sysUsrTypePows}" var="sysUsrTypePowVar" varStatus="sysUsrTypePowVarStatus">
						<c:if test="${sysUsrTypePowVarStatus.index>0}">,</c:if>
		    			${sysUsrTypePowVar.userType }
		    		</c:forEach>
				</c:if>
				<c:if test="${empty sysPower.sysUsrTypePows}">&nbsp;</c:if>
			</ec:column>
			<ec:column property="isDefault" title="sysListValue.exCompanyCode">
				<c:if test="${not empty sysPower.sysCompanyPows}">
					<c:forEach items="${sysPower.sysCompanyPows}" var="sysCompanyPowVar" varStatus="sysCompanyPowVarStatus">
						<c:if test="${sysCompanyPowVarStatus.index>0}">,</c:if>
		    			${sysCompanyPowVar.companyCode }
		    		</c:forEach>
				</c:if>
				<c:if test="${empty sysPower.sysCompanyPows}">&nbsp;</c:if>
			</ec:column>
		</ec:row>
	</ec:table>	
</c:if>

<script type="text/javascript">

function editSysPower(powerId){
	<jecs:power powerCode="editSysPower">
	window.location="editSysPower.html?strAction=editSysPower&powerId="+powerId;
	</jecs:power>
}

</script>