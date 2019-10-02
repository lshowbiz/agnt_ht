<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="mainMenu.title"/></title>
<content tag="heading"><jecs:locale key="mainMenu.heading"/></content>

<p><jecs:locale key="mainMenu.message"/></p>

<div class="separator"></div>

<ul class="glassList">
    <li>
        <a href="<c:url value="/editProfile.html"/>"><jecs:locale key="menu.user"/></a>
    </li>
    <li>
        <a href="<c:url value="/selectFile.html"/>"><jecs:locale key="menu.selectFile"/></a>
    </li>
</ul>
