<%@ page pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>


<div data-role="header" class="ui-header ui-bar-a" role="banner">
<a data-icon="arrow-l" class="ui-btn-left ui-btn ui-btn-up-a ui-btn-icon-left ui-btn-corner-all ui-shadow" href="index.html" data-theme="a" data-corners="true" data-shadow="true" data-iconshadow="true" data-inline="false" data-wrapperels="span">
<span class="ui-btn-inner ui-btn-corner-all">
<span class="ui-btn-text">菜单</span>
<span class="ui-icon ui-icon-arrow-l ui-icon-shadow">
</span>
</span>
</a>
<h1 class="ui-title" tabindex="0" role="heading" aria-level="1">欢迎 ${sessionScope.sessionLogin.userName }</h1>
<a data-icon="arrow-l" href="logout.jsp" data-theme="a" data-corners="true" data-shadow="true" data-iconshadow="true" data-inline="false" data-wrapperels="span">

<span class="ui-btn-text">退出</span>

</span>
</span>
</a>
</div>