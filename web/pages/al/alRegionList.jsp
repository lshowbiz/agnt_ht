<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="alRegionList.title"/></title>
<content tag="heading"><jecs:locale key="alRegionList.heading"/></content>
<meta name="menu" content="AlRegionMenu"/>

<c:set var="buttons">
    <input type="button" style="margin-right: 5px"
        onclick="location.href='<c:url value="/editAlRegion.html"/>'"
        value="<jecs:locale key="member.new.num"/>"/>

    <input type="button" onclick="location.href='<c:url value="/mainMenu.html"/>'"
        value="<jecs:locale key="button.done"/>"/>
</c:set>

<c:out value="${buttons}" escapeXml="false"/>

<display:table name="alRegionList" cellspacing="0" cellpadding="0" requestURI=""
    id="alRegionList" pagesize="25" class="table alRegionList" export="true">

    <display:column property="regionId" escapeXml="true" sortable="true"
        url="/editAlRegion.html" paramId="regionId" paramProperty="regionId"
        titleKey="alRegion.regionId"/>
    <display:column property="companyCode" escapeXml="true" sortable="true"
         titleKey="alRegion.companyCode"/>
    <display:column property="regionCode" escapeXml="true" sortable="true"
         titleKey="alRegion.regionCode"/>
    <display:column property="regionName" escapeXml="true" sortable="true"
         titleKey="alRegion.regionName"/>
    <display:setProperty name="paging.banner.item_name" value="alRegion"/>
    <display:setProperty name="paging.banner.items_name" value="alRegions"/>
</display:table>

<c:out value="${buttons}" escapeXml="false"/>

<script type="text/javascript">
    highlightTableRows("alRegionList");
</script>
