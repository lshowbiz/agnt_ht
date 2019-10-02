<%@ include file="/common/taglibs.jsp"%>
<c:set var="requestMap" value="${requestMap}" scope="session"/>
<c:set var="sysUserListTable_totalRows" value="${sysUserListTable_totalRows}" scope="session"/>
<c:set var="sysUserList" value="${sysUserList}" scope="session"/>

<frameset rows="0,*">

<frame name="sysUserSelect" src="about:blank" />
<frame name="sysUserSelectMain" scrolling="yes" noresize target="_self" src="common/sysUserSearch.jsp" />

</frameset>


