<%@ include file="/common/taglibs.jsp" %>

<head>
    <title><jecs:locale key="flushCache.title"/></title>
    <content tag="heading"><jecs:locale key="flushCache.heading"/></content>
    <meta name="menu" content="AdminMenu"/>
</head>

<cache:flush/>
<div class="message">
    <img src="<c:url value="/images/iconInformation.gif"/>"
        alt="<jecs:locale key="icon.information"/>" class="icon" />
    <jecs:locale key="flushCache.message"/>
</div>
<script type="text/javascript">
    window.setTimeout("history.back()", 2000);
</script>
