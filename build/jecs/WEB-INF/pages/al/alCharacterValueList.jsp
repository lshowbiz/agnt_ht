<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="alCharacterValueList.title"/></title>
<content tag="heading"><jecs:locale key="alCharacterValueList.heading"/></content>
<meta name="menu" content="AlCharacterValueMenu"/>

<c:set var="buttons">
    <input type="button" style="margin-right: 5px"
        onclick="location.href='<c:url value="/editAlCharacterValue.html"/>'"
        value="<jecs:locale key="member.new.num"/>"/>

    <input type="button" onclick="location.href='<c:url value="/mainMenu.html"/>'"
        value="<jecs:locale key="button.done"/>"/>
</c:set>

<c:out value="${buttons}" escapeXml="false"/>

<form name="formAlCharacterValue" action="">
<input type="hidden" name="keyId" value="${param.keyId }"/>
<display:table name="alCharacterValueList" cellspacing="0" cellpadding="0" requestURI=""
    id="alCharacterValueList" pagesize="25" class="table alCharacterValueList" export="true">

    <display:column escapeXml="false" sortable="true" titleKey="alCharacterValue.characterCode">
         ${alCharacterValueList[1].characterCode}
         <input type="hidden" name=characterCode value="${alCharacterValueList[1].characterCode }"/>
    </display:column>
    <display:column escapeXml="false" sortable="true" titleKey="alCharacterValue.characterName">
         ${alCharacterValueList[0].characterName}
    </display:column>
    <display:column escapeXml="false" sortable="true" titleKey="alCharacterValue.characterValue">
         <input type="hidden" name="valueId" value="${alCharacterValueList[1].valueId }"/>
         <input type="text" name="characterValue" value="${alCharacterValueList[1].characterValue }"/>
    </display:column>
    <display:setProperty name="paging.banner.item_name" value="alCharacterValue"/>
    <display:setProperty name="paging.banner.items_name" value="alCharacterValues"/>
</display:table>

</form>

<c:out value="${buttons}" escapeXml="false"/>

<script type="text/javascript">
    highlightTableRows("alCharacterValueList");
</script>
