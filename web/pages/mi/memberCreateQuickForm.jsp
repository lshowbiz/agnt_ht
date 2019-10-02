<%@ include file="/common/taglibs.jsp"%>


<title><jecs:locale key="jmiMemberDetail.title"/></title>
<content tag="heading"><jecs:locale key="jmiMemberDetail.heading"/></content>

        <script type="text/javascript" src="<c:url value='/scripts/global_js.js'/>"></script>
<script type="text/javascript" src="<c:url value='/dwr/util.js'/>" ></script> 
<script type="text/javascript" src="<c:url value='/dwr/engine.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/dwr/interface/alCityManager.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/dwr/interface/alDistrictManager.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/dwr/interface/jalTownManager.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/dwr/interface/jalPostalcodeManager.js'/>" ></script>

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

<form:form commandName="jmiMember" method="post" action="memberCreateQuick.html"  id="jmiMemberForm" onsubmit="if(isFormPosted()){return true;}{return false;}">
	<div class="searchBar">
		<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="operation.button.save"/>" />
	
	</div>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<form:hidden path="userCode"/>



	<table class="detail" width="100%">
	<tr>
		<th> <jecs:label key="miMember.recommendNo" required="true" /></th>
		<td><form:input path="jmiRecommendRef.recommendJmiMember.userCode" id="recommendNo" cssClass="text medium"/>
        <input type="button" class="button" onclick="miSelectMember(this.form)" value="<jecs:locale key="operation.button.search"/>"/>
        <script type="text/javascript">
		function miSelectMember(theForm){
			if(theForm.recommendNo.value==""){
				alert('<jecs:locale key="operation.notice.js.orderNo.miMember.memberNoError"/>');
				theForm.recommendNo.focus();
				return;
			}
			var pars=new Array();
			pars[0]="<jecs:locale key='function.menu.findMembers'/>";
			pars[1]="miSelectRecommendRef.html?strAction=miSelectRecommendRef&memberNo=" + theForm.recommendNo.value;
			pars[2]=window;
			var ret=showDialog("<%=request.getContextPath()%>",pars,810,550,1);
			if(ret!=undefined){
				theForm.linkNo.value=ret;
			}
		}
		
		</script></td>
	</tr>



	<tr>
		<th>  <jecs:label key="miMember.linkNo" required="true" /></th>
		<td><form:input path="jmiLinkRef.linkJmiMember.userCode" id="linkNo" cssClass="text medium"/></td>
	</tr>


	<tr>
		<th> <jecs:label key="miMember.firstName" required="true" /> </th>
		<td> <form:input path="firstName" id="firstName" cssClass="text medium"/></td>
	</tr>
	<tr>
		<th> <jecs:label key="miMember.lastName" required="true" /> </th>
		<td><form:input path="lastName" id="lastName" cssClass="text medium"/></td>
	</tr>
	<tr>
		<th>  <jecs:label  key="miMember.papertype"/> </th>
		<td>  <jecs:list name="papertype" id="papertype" listCode="papertype" value="${jmiMember.papertype}" defaultValue="1"/></td>
	</tr>
	<tr>
		<th> <jecs:label key="miMember.papernumber" required="true" /> </th>
		<td><form:input path="papernumber" id="papernumber" cssClass="text medium" /></td>
	</tr>

    
    </table>
    
    
    
    
    </form:form>
    
    

	
	
	
   

