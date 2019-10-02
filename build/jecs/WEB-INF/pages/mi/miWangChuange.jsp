<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jmiRecommendRefList.title"/></title>
<content tag="heading"><jecs:locale key="jmiRecommendRefList.heading"/></content>
<meta name="menu" content="JmiRecommendRefMenu"/>
<%-- 
<c:set var="buttons">
    <input type="button" class="button" style="margin-right: 5px"
        onclick="window.open('http://dt.p.cn/consume/Order/NewCommonOrderShow.aspx?d=61643a54141b1');"
        value="<jecs:locale key="wc.button.open"/>"/>
</c:set>
--%>
<div id="titleBar">
<button type="button" class="btn btn_long" 
	onclick="window.open('http://dt.p.cn/consume/Order/NewCommonOrderShow.aspx?d=61643a54141b1');">
	<jecs:locale key="wc.button.open"/>
</button>
<%-- 
<c:out value="${buttons}" escapeXml="false"/>
--%>
</div>

