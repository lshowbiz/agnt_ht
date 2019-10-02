<%@ include file="/common/taglibs.jsp"%>
<c:set var="requestMap" value="${requestMap}" scope="session"/>
<c:set var="sysUserListTable_totalRows" value="${sysUserListTable_totalRows}" scope="session"/>
<c:set var="selectControl" value="${selectControl}" scope="request"/>
<c:set var="userCode" value="${userCode}" scope="request"/>
<frameset rows="0,*">

<frame name="sysUserSelect" src="about:blank" />
<frame name="sysUserSelectMain" scrolling="yes" noresize target="_self" src="<c:url value='/sysUserSearch.html'/>?strAction=companyUserSelect&selectControl=${selectControl}&userCode=${userCode}" />

</frameset>