<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="alCurrencyList.title"/></title>
<content tag="heading"><jecs:locale key="alCurrencyList.heading"/></content>
<meta name="menu" content="AlCurrencyMenu"/>

<c:set var="buttons">
    <input type="button" style="margin-right: 5px"
        onclick="location.href='<c:url value="/editAlCurrency.html"/>'"
        value="<jecs:locale key="member.new.num"/>"/>

    <input type="button" onclick="location.href='<c:url value="/mainMenu.html"/>'"
        value="<jecs:locale key="button.done"/>"/>
</c:set>

<c:out value="${buttons}" escapeXml="false"/>

<display:table name="alCurrencyList" cellspacing="0" cellpadding="0" requestURI=""
    id="alCurrencyList" pagesize="25" class="table alCurrencyList" export="true">

    <display:column property="currencyId" escapeXml="true" sortable="true"
        url="/editAlCurrency.html" paramId="currencyId" paramProperty="currencyId"
        titleKey="alCurrency.currencyId"/>
    <display:column property="currencyCode" escapeXml="true" sortable="true"
         titleKey="alCurrency.currencyCode"/>
    <display:column property="currencyName" escapeXml="true" sortable="true"
         titleKey="alCurrency.currencyName"/>
    <display:column property="exchangeRate" escapeXml="true" sortable="true"
         titleKey="alCurrency.exchangeRate"/>
    <display:column property="currencySymbol" escapeXml="true" sortable="true"
         titleKey="alCurrency.currencySymbol"/>
    <display:column property="currencyStyle" escapeXml="true" sortable="true"
         titleKey="alCurrency.currencyStyle"/>
    <display:column property="currencyKey" escapeXml="true" sortable="true"
         titleKey="alCurrency.currencyKey"/>
    <display:setProperty name="paging.banner.item_name" value="alCurrency"/>
    <display:setProperty name="paging.banner.items_name" value="alCurrencys"/>
</display:table>

<c:out value="${buttons}" escapeXml="false"/>

<script type="text/javascript">
    highlightTableRows("alCurrencyList");
</script>
