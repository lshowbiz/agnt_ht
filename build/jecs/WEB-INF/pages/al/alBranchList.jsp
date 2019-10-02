<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="alBranchList.title"/></title>
<content tag="heading"><jecs:locale key="alBranchList.heading"/></content>
<meta name="menu" content="AlBranchMenu"/>

<c:set var="buttons">
    <input type="button" style="margin-right: 5px"
        onclick="location.href='<c:url value="/editAlBranch.html"/>'"
        value="<jecs:locale key="member.new.num"/>"/>

    <input type="button" onclick="location.href='<c:url value="/mainMenu.html"/>'"
        value="<jecs:locale key="button.done"/>"/>
</c:set>

<c:out value="${buttons}" escapeXml="false"/>

<display:table name="alBranchList" cellspacing="0" cellpadding="0" requestURI=""
    id="alBranchList" pagesize="25" class="table alBranchList" export="true">

    <display:column property="branchId" escapeXml="true" sortable="true"
        url="/editAlBranch.html" paramId="branchId" paramProperty="branchId"
        titleKey="alBranch.branchId"/>
    <display:column property="companyCode" escapeXml="true" sortable="true"
         titleKey="alBranch.companyCode"/>
    <display:column property="branchCode" escapeXml="true" sortable="true"
         titleKey="alBranch.branchCode"/>
    <display:column property="branchName" escapeXml="true" sortable="true"
         titleKey="alBranch.branchName"/>
    <display:setProperty name="paging.banner.item_name" value="alBranch"/>
    <display:setProperty name="paging.banner.items_name" value="alBranchs"/>
</display:table>

<c:out value="${buttons}" escapeXml="false"/>

<script type="text/javascript">
    highlightTableRows("alBranchList");
</script>
