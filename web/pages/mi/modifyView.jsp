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
	 <tr>
    <td align="center">&nbsp;</td>
    <td align="center">&nbsp;</td>
    <td align="center">&nbsp;</td>
  </tr>
 
  <c:if test="${oldInfo != null}">
  	<tr><th></th><td>${oldInfo['MI_USER_NAME']}</td><td>${newInfo['MI_USER_NAME']}</td>           </tr>
  	<tr><th><jecs:label  key="miMember.petName"/></th><td>${oldInfo['PET_NAME']}</td><td>${newInfo['PET_NAME']}</td>                   </tr>
    <tr><th><jecs:label  key="miMember.sex"/></th><td>${oldInfo['SEX']}</td><td>${newInfo['SEX']}</td>                             </tr>
    <tr><th><jecs:label  key="miMember.email"/></th><td>${oldInfo['EMAIL']}</td><td>${newInfo['EMAIL']}</td>                         </tr>
    <tr><th><jecs:label  key="miMember.papernumber"  required="true"/></th><td>${oldInfo['PAPERNUMBER']}</td><td>${newInfo['PAPERNUMBER']}</td>             </tr>
    <tr><th><jecs:label  key="miMember.openBank" /></th><td>${oldInfo['BANK']}</td><td>${newInfo['BANK']}</td>                           </tr>
    <tr><th></th><td>${oldInfo['BANK_CITY']}</td><td>${newInfo['BANK_CITY']}</td>                 </tr>
    <tr><th></th><td>${oldInfo['BANK_PROVINCE']}</td><td>${newInfo['BANK_PROVINCE']}</td>         </tr>
    <tr><th><jecs:label  key="miMember.bcity"  /></th><td>${oldInfo['BANKADDRESS']}</td><td>${newInfo['BANKADDRESS']}</td>             </tr>
    <tr><th><jecs:label  key="miMember.bnum"  /></th><td>${oldInfo['BANKCARD']}</td><td>${newInfo['BANKCARD']}</td>                   </tr>
    <tr><th><jecs:label  key="miMember.bname" /></th><td>${oldInfo['BANKBOOK']}</td><td>${newInfo['BANKBOOK']}</td>                   </tr>
    <tr><th><jecs:label  key="miMember.phone"  required="true"/></th><td>${oldInfo['PHONE']}</td><td>${newInfo['PHONE']}</td>                         </tr>
    <tr><th><jecs:label  key="miMember.faxtele"/></th><td>${oldInfo['FAXTELE']}</td><td>${newInfo['FAXTELE']}</td>                     </tr>
    <tr><th><jecs:label  key="miMember.mobiletele"  required="true"/></th><td>${oldInfo['MOBILETELE']}</td><td>${newInfo['MOBILETELE']}</td>               </tr>
    <tr><th><jecs:label  key="miMember.province"  required="true"/></th><td>${oldInfo['PROVINCE']}</td><td>${newInfo['PROVINCE']}</td>                   </tr>
    <tr><th><jecs:label  key="busi.city"  required="true"/></th><td>${oldInfo['CITY']}</td><td>${newInfo['CITY']}</td>                           </tr>
    <tr><th><jecs:label  key="busi.address"  required="true"/></th><td>${oldInfo['ADDRESS']}</td><td>${newInfo['ADDRESS']}</td>                     </tr>
    <tr><th><jecs:label  key="miMember.postalcode"  required="true"/></th><td>${oldInfo['POSTALCODE']}</td><td>${newInfo['POSTALCODE']}</td>               </tr>
    <tr><th><jecs:label  key="miMember.exitDate"/></th><td>${oldInfo['EXIT_DATE']}</td><td>${newInfo['EXIT_DATE']}</td>                 </tr>
    
     
    
    
    <tr><th></th><td>${oldInfo['DISTRICT']}</td><td>${newInfo['DISTRICT']}</td>                   </tr>
  </c:if>

</table>



