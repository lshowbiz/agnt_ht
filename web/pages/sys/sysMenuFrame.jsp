<%@ taglib uri="/WEB-INF/core.tld" prefix="c"%>
<frameset cols="160,*">
	<frame src="<c:url value="/sys/menuTree.html?for=menuList"/>" name="menuFrame" frameborder="yes" scrolling="auto" marginwidth="0" >
	<frame src="<c:url value="/sys/menuList.html?action=list&parentId=0"/>" name="listFrame" frameborder="yes" scrolling="auto" marginwidth="0">
</frameset>
