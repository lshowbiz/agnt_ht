<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jmiMemberDetail.title"/></title>
<content tag="heading"><jecs:locale key="jmiMemberDetail.heading"/></content>
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script> 


<script type="text/javascript" src="<c:url value='/dwr/util.js'/>" ></script> 
<script type="text/javascript" src="<c:url value='/dwr/engine.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/dwr/interface/alCityManager.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/dwr/interface/alDistrictManager.js'/>" ></script>

<spring:bind path="jmiMember.*">
    <c:if test="${not empty status.errorMessages}">
    <div class="error">    
        <c:forEach var="error" items="${status.errorMessages}">
            <img src="<c:url value="images/newIcons/warning_16.gif"/>"
                alt="<jecs:locale key="icon.warning"/>" class="icon" />
            <c:out value="${error}" escapeXml="false"/><br />
        </c:forEach>
    </div>
    </c:if>
</spring:bind>

<form method="post" action="editJmiMemberActivation.html"  id="jmiMemberForm" target="_parent">

	<div class="searchBar">	
		

	</div>
<input type="hidden" name="strAction" value="${param.strAction }"/>
	
<table class='detail' width="100%">
	<tr>
		<th><jecs:label key="jbdSendRecord.acitve"/></th>
		<td><input type="button" class="button" name="save" value="&nbsp;&nbsp;<jecs:locale key="yesno.yes"/>&nbsp;&nbsp;"  onclick="submitForm('yes','<jecs:locale key="jbdSendRecord.acitve.yes"/>');"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
		<%
		java.util.Calendar startc=java.util.Calendar.getInstance();
		startc.set(2010, 1, 10, 00, 00, 00);
		java.util.Date startDate=startc.getTime();
		
		
		java.util.Date  curDate=new java.util.Date();
			if(curDate.before(startDate)){
			%>
		<input type="button" class="button" name="save" value="&nbsp;&nbsp;<jecs:locale key="yesno.no"/>&nbsp;&nbsp;"  onclick="submitForm('no','<jecs:locale key="jbdSendRecord.acitve.no"/>');"/>	
			<%
			}
			%>
			
			
		</td>
		
<input type="hidden" id="yesno" name="yesno" value=""/>
	</tr>
	
</table>

  
</form>

<script type="text/javascript">
	function submitForm(yesno,str){
	
			$('yesno').value=yesno;
			if(window.confirm(str)){
				$('jmiMemberForm').submit();
			}
		 
	}
	
</script>
