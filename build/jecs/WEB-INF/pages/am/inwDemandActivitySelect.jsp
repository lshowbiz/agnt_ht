<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="inwDemandList.title"/></title>
<content tag="heading"><jecs:locale key="inwDemandList.heading"/></content>
<meta name="menu" content="InwDemandMenu"/>
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script> 

<form name="inwDemandActivityListTableForm" id="inwDemandActivityListTableForm" action="<c:url value='/inwDemandsActivity.html'/>" >

<input type="hidden" name="strAction" value="${strAction}"/>

<div class="searchBar">&nbsp;&nbsp;
	 
		<jecs:label  key="pd.checkstatus"/>
		<jecs:list listCode="inwdemand.verify" name="verify" id="verify" showBlankLine="true" value="${param.verify}" defaultValue=""/>	
		
		<jecs:label  key="inwDemandsort.spece"/>
		
		<select name="demandsortId" id="demandsortId">
			     <option label=""value=""/>
			     <c:forEach items="${demandsortList}" var="demandsort">
			         <option value="${demandsort.id}">${demandsort.name }</option>
			     </c:forEach>
	    </select>

		
	  	<jecs:locale  key="inwDemand.name"/>:<input type="text" name="name" value="${param.name}" size="10"/>
		<jecs:label  key="busi.message.issuetime"/>
			<input id="postTime" name="postTime" readonly value="${param.postTime }" size="10"/>
		    <img src="./images/calendar/calendar7.gif" id="img_startSearchTime"  style="cursor: pointer;" title="<jecs:locale key="Calendar.TT.SEL_DATE"/>"/> 
		        	<script type="text/javascript">        	 
					Calendar.setup({
					inputField     :    "postTime", 
					ifFormat       :    "%Y-%m-%d",  
					button         :    "img_startSearchTime", 
					singleClick    :    true
					});
				</script>
				
			<input id="postTimeEnd" name="postTimeEnd" readonly value="${param.postTimeEnd }" size="10"/>
		    <img src="./images/calendar/calendar7.gif" id="img_endSearchTime" style="cursor: pointer;" title="<jecs:locale key="Calendar.TT.SEL_DATE"/>"/> 
		        	<script type="text/javascript">        	 
					Calendar.setup({
						
					inputField     :    "postTimeEnd", 
					ifFormat       :    "%Y-%m-%d",  
					button         :    "img_endSearchTime", 
					singleClick    :    true
					}); 
				</script> 		
			<input type="submit" class="button" value="<jecs:locale  key='operation.button.search'/>" />
		
</div>
</form>


<ec:table 
	tableId="inwDemandActivityListTable"
	items="inwDemandList"
	var="inwDemand"
	action="${pageContext.request.contextPath}/inwDemandsActivity.html"
	width="100%"
	autoIncludeParameters="true"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="false" imagePath="./images/extremetable/*.gif">
				<ec:row >
					<ec:column property="_1" title="button.select">
				          <img src="images/newIcons/tick_16.gif"  onclick="selectInwDemand('${inwDemand.id}','${inwDemand.name}')"  alt="<jecs:locale key="button.select"/>"style="cursor:pointer"/>
					</ec:column>
				    <ec:column property="namesort" title="inwDemandsort.spece" />
					<ec:column property="post_user_code" title="inwDemand.postUserCode" />
					<ec:column property="post_time" title="inwDemand.postTime" />
	    			<ec:column property="name" title="inwDemand.name" />
	    			<ec:column property="summary" title="inwDemand.summary" />
	    			<ec:column property="reviewed_user_code" title="inwDemand.reviewedUserCode" />
	    			<ec:column property="reviewed_time" title="pd.checkTime" />
	    			<ec:column property="verify" title="inwDemand.verify" >
			    				<c:if test="${inwDemand.verify=='N'}"><font color="red"></c:if>
			    				<jecs:code listCode="inwdemand.verify" value="${inwDemand.verify}"/>
			    				<c:if test="${amAnnounce.status=='Y'}"></font></c:if>
	    			</ec:column>
				</ec:row>

</ec:table>


<script type="text/javascript">
 
    window.onload = function setValueId(){
            var demandsortId = "<%= request.getAttribute("demandsortId")%>";
            document.getElementById("demandsortId").value=demandsortId;
    }
	
	function selectInwDemand(demandId,demandName){
	        window.opener.document.getElementById("demandId").value=demandId;
	        window.opener.document.getElementById("demandName").value=demandName;
	        this.close();
	}
	
</script>
