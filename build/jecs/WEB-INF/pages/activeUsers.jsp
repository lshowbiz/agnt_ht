<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><jecs:locale key="activeUsers.title"/></title>
    <content tag="heading"><jecs:locale key="activeUsers.heading"/></content>
    <meta name="menu" content="AdminMenu"/>
</head>
<body id="activeUsers"/>

<p><jecs:locale key="activeUsers.message"/></p>

<div class="separator"></div>

<input type="button" onclick="location.href='mainMenu.html'" value="<jecs:locale key="button.done"/>"/>
    
<display:table name="applicationScope.userNames" id="user" cellspacing="0" cellpadding="0"
    defaultsort="1" class="table" pagesize="50" requestURI="">

    <display:column property="username" escapeXml="true" style="width: 30%" titleKey="user.username" sortable="true"/>
    <display:column titleKey="activeUsers.fullName" sortable="true">
        <c:out value="${user.firstName} ${user.lastName}" escapeXml="true"/>
        <c:if test="${not empty user.email}">
        <a href="mailto:<c:out value="${user.email}"/>">
            <img src="<c:url value="/images/iconEmail.gif"/>" 
                alt="<jecs:locale key="icon.email"/>" styleClass="icon"/></a>
        </c:if>
    </display:column>
        
    <display:setProperty name="paging.banner.item_name" value="user" />
    <display:setProperty name="paging.banner.items_name" value="users" />
</display:table>
