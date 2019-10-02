<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jmiMemberList.title"/></title>
<content tag="heading"><jecs:locale key="jmiMemberList.heading"/></content>
<meta name="menu" content="JmiMemberMenu"/>


<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script> 

<form action="" method="get" name="miMemberForm" id="miMemberForm">
	<input type="hidden" id="strAction" name="strAction" value="statusView"/>
	<div class="searchBar">

	<jecs:label key="miMember.memberNo"/>
		<input name="userCode" type="text" value="${param.userCode}" size="10"/>	

		<input type="submit" class="button" value="<jecs:locale  key='operation.button.search'/>"/>
		
</div>



</form>
  <c:if test="${not empty errorMsgs}">
    <div class="error">    
        <c:forEach var="error" items="${errorMsgs}">
            <img src="<c:url value="/images/iconWarning.gif"/>"
                alt="<jecs:locale key="icon.warning"/>" class="icon" />
            <c:out value="${error}" escapeXml="false"/><br />
        </c:forEach>
        <c:remove var="errorMsgs" scope="session" />
    </div>
 </c:if>
<table class='detail' width="100%">
	<tr><th align='center'>
        <jecs:locale  key="miMember.memberNo"/>
    </th>
    <th>
        <jecs:locale  key="miMember.petName"   />
    </th>
    <th>
        <jecs:locale  key="is.active"  />
    </th>
    <th>
        <jecs:locale  key="miMember.exitDate"  />
    </th>
  </tr>
  <c:if test="${jmiMember != null}">
  	<tr><td>
        ${jmiMember.userCode}
    </td>
    <td>
        ${jmiMember.petName}
    </td>
    <td>
         <jecs:code listCode="yesno" value="${jmiMember.active}"/>
    </td>
    <td>
        ${jmiMember.exitDate}
    </td>
  </tr>
  </c:if>

</table>



