<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="vtVoteDetail.title"/></title>
<content tag="heading"><jecs:locale key="vtVoteDetail.heading"/></content>

<LINK href="styles/vote-2.css" type=text/css rel=stylesheet>

<style type="text/css">
table.detail2 {
	
}

table.detail1 {
	
	font-weight: normal;
	border-collapse:collapse;
	position: relative;
}

table.detail1 th {
	width:300px;
	border: 1px solid #dadada;
	color: #333333;
	text-align: right;
	background: #f0f0f0;
	height: 20px;
	padding: 4px;
	white-space: nowrap;
}

table.detail1 th label {
	width:100%;
	text-align: right;
}

table.detail1 th.tallCell {
    vertical-align: top;
}

table.detail1 th.command{
	border: 1px solid #dadada;
	color: #165AB3;
	text-align: right;
	background: #f0f0f0;
	height: 20px;
	padding: 4px;
}

table.detail1 td {
	
	color: black;
	
}

table.detail1 td.moveOptions {
    text-align: center;
    width: 50px;
    padding: 4px;
}

table.detail1 td.moveOptions button {
    margin-bottom: 3px;
    width: 45px;
    white-space: nowrap;
}

table.detail1 td.command{
	border: 1px solid #dadada;
	color: black;
	padding: 4px;
	background: #f0f0f0;
}

table.detail1 td.title{
	border: 1px solid #dadada;
	color: #6B91C9;
	background: #E1E9F4;
	height: 20px;
	padding: 4px;
	white-space: nowrap;
	font-weight: bold;
}

table.detail1 td.buttonBar {
    padding-top: 10px;
}

table.detail1 td.updateStatus {
    font-size: 11px;
    color: #c0c0c0;
}

table.detail1Sub td{
	border: none;
	padding: 1px;
}

table.inSideTable {
	font-weight: normal;
	border-collapse:collapse;
	position: relative;
}

table.inSideTable td {
	border: 1px none #dadada;
	color: black;
	padding: 4px;
}
</style>

<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script> 

<spring:bind path="vtVote.*">
    <c:if test="${not empty status.errorMessages}">
    <div class="error">    
        <c:forEach var="error" items="${status.errorMessages}">
            <img src="<c:url value="/images/iconWarning.gif"/>"
                alt="<jecs:locale key="icon.warning"/>" class="icon" />
            <c:out value="${error}" escapeXml="false"/><br />
        </c:forEach>
    </div>
    </c:if>
</spring:bind>

<form:form commandName="vtVote" method="post" action="editVtVotePoll.html" id="vtVoteForm">
<%-- 
<div id="titleBar" class="searchBar">
			<c:if test="${empty detailCount }">
				<jecs:power powerCode="${param.strAction}">
						<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="menu.vote.poll"/>" />
				</jecs:power>
			
			</c:if>
		
		<input type="button" class="button" name="cancel" onclick="window.history.back()" value="<jecs:locale key="operation.button.return"/>" />
</div>
--%>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<table class='detail1' width="70%">
	<tbody class="window">
	<form:hidden path="vtId"/>
	
	    <tr>
		    <th>
		    	
		        <jecs:label  key="customerRecord.subject"/>
		    </th>
	    <td>
	   		${vtVote.subject }
		 </td></tr>
	
	    <tr><th>
	       <jecs:label  key="vtVote.description"/>
	    </th>
	    <td>
	       
	   	${vtVote.description } 
		
		</td></tr>
	
	    
	    
	    
	    <tr><th>
	        <jecs:label  key="vtVote.voteWay"/>
	    </th>
	    <td>
	     
	        	<jecs:code listCode="vote.way" value="${vtVote.optionNum}"/>
	    </td></tr>
	    
	    <tr><th>
	        <jecs:label  key="vtVote.voteTime.scope"/>
	    </th>
	    <td>
	    	
	        <fmt:formatDate value="${vtVote.startTime }" pattern="yyyy-MM-dd"/>   -   <fmt:formatDate value="${vtVote.endTime }" pattern="yyyy-MM-dd"/>
	
		 </td></tr>
		   
	    <tr><th>
	        <jecs:label  key="vtVote.voteTime.scope"/>
	    </th>
	    <td>
	    	<table border="0" cellspacing="0" cellpadding="0" >
					<c:set var="lengthData" value="{"/>
				<c:forEach items="${vtVote.vtVoteDetails }" var="vtVoteDetailVar" varStatus="varStatus">
					<c:set var="count" value="0"/>
					<c:set var="rate" value="0"/>
					<c:set var="slipStr" value=","/>
					
					
					<c:forEach items="${resList }" var="resVar">
						<c:if test="${vtVoteDetailVar.vdId==resVar['vd_id'] }">
							<c:set var="count" value="${resVar['note_count'] }"/>
							<c:set var="rate" value="${resVar['detail_rate'] }"/>
						</c:if>
					</c:forEach>
					  <tr>
					    <td align="right">${vtVoteDetailVar.content }:</td>
					    <c:if test="${not empty detailCount || not empty viewVoteResult}">
						    <td>
						    <div class="tpbg5" >
							<table cellSpacing=0 cellPadding=0 border=0>
							  <tbody>
							  <tr>
							    <td style="width: 2px;line-height: 10px;height: 10px"><img 
							      src="images/newIcons/v${(varStatus.count-1)%10+1 }.gif" style="vertical-align:baseline"></td>
							    <td class="vbg${(varStatus.count-1)%10+1 }" id="length${varStatus.count }" style="width: 0px;line-height: 10px;height: 10px"></td>
							    <td style="width: 2px;line-height: 10px;height: 10px"><img 
							      src="images/newIcons/v${(varStatus.count-1)%10+1  }b.gif" style="vertical-align:baseline"></td></tr></tbody></table></div>
						    </td>
						    <td> ${count } (${rate }%)</td>
					    </c:if>
					    
					    <c:if test="${varStatus.count==1 }">
					    	<c:set var="slipStr" value=""/>
					    </c:if>
						<c:set var="lengthData" value='${lengthData }${slipStr }"length${varStatus.count }":${rate }'/>
						<c:if test="${empty detailCount }">
						    <td>
						    	<c:if test="${vtVote.optionNum==1 }">
						    		<input id="selectedOption${varStatus.count }" name="selectedOption" type="radio" value="${vtVoteDetailVar.vdId }">
						    	</c:if>
						    	<c:if test="${vtVote.optionNum!=1 }">
						    		<input id="selectedOption${varStatus.count }" name="selectedOption"  type="checkbox" value="${vtVoteDetailVar.vdId }">
						    	</c:if>
						    </td>
					    </c:if>
					  </tr>
				</c:forEach>
	  
						<c:set var="lengthData" value='${lengthData }}'/>
	</table>
	
	     
	
		 </td></tr>
		 
		 <tr>		
			<td class="command" colspan="4" align="center">
				 <c:if test="${empty detailCount }">
				<jecs:power powerCode="${param.strAction}">
						<button type="submit" class="btn btn_sele" name="save"  onclick="bCancel=false" ><jecs:locale key="menu.vote.poll"/></button>
				</jecs:power>
			
			</c:if>
		
				<button type="button" class="btn btn_sele" name="cancel" onclick="window.history.back()"><jecs:locale key="operation.button.return"/></button>
			</td>
		</tr>
		 
		
	 </tbody>
</table>
<%-- 
<table class='detail1' width="100%">

<form:hidden path="vtId"/>

    <tr><th>
        <jecs:label  key="customerRecord.subject"/>
    </th>
    <td align="left" style="border: 1px solid #dadada;padding: 4px;">
   	${vtVote.subject }   
	 </td></tr>

    <tr><th>
        <jecs:label  key="vtVote.description"/>
    </th>
    <td align="left"  style="border: 1px solid #dadada;padding: 4px;">
       
   	${vtVote.description } 
	
	</td></tr>

    
    
    
    <tr><th>
        <jecs:label  key="vtVote.voteWay"/>
    </th>
    <td align="left"  style="border: 1px solid #dadada;padding: 4px;">
     
        	<jecs:code listCode="vote.way" value="${vtVote.optionNum}"/>
    </td></tr>
    
    <tr><th>
        <jecs:label  key="vtVote.voteTime.scope"/>
    </th>
    <td align="left"  style="border: 1px solid #dadada;padding: 4px;">
    	
        <fmt:formatDate value="${vtVote.startTime }" pattern="yyyy-MM-dd"/>   -   <fmt:formatDate value="${vtVote.endTime }" pattern="yyyy-MM-dd"/>

	 </td></tr>
	   
    <tr><th>
        <jecs:label  key="vtVote.voteTime.scope"/>
    </th>
    <td align="left" style="border: 1px solid #dadada;padding: 4px;">
    	<table border="0" cellspacing="0" cellpadding="0" >
				<c:set var="lengthData" value="{"/>
			<c:forEach items="${vtVote.vtVoteDetails }" var="vtVoteDetailVar" varStatus="varStatus">
				<c:set var="count" value="0"/>
				<c:set var="rate" value="0"/>
				<c:set var="slipStr" value=","/>
				
				
				<c:forEach items="${resList }" var="resVar">
					<c:if test="${vtVoteDetailVar.vdId==resVar['vd_id'] }">
						<c:set var="count" value="${resVar['note_count'] }"/>
						<c:set var="rate" value="${resVar['detail_rate'] }"/>
					</c:if>
				</c:forEach>
				  <tr>
				    <td align="right">${vtVoteDetailVar.content }:</td>
				    <c:if test="${not empty detailCount || not empty viewVoteResult}">
					    <td>
					    <div class="tpbg5" >
						<table cellSpacing=0 cellPadding=0 border=0>
						  <tbody>
						  <tr>
						    <td style="width: 2px;line-height: 10px;height: 10px"><img 
						      src="images/newIcons/v${(varStatus.count-1)%10+1 }.gif" style="vertical-align:baseline"></td>
						    <td class="vbg${(varStatus.count-1)%10+1 }" id="length${varStatus.count }" style="width: 0px;line-height: 10px;height: 10px"></td>
						    <td style="width: 2px;line-height: 10px;height: 10px"><img 
						      src="images/newIcons/v${(varStatus.count-1)%10+1  }b.gif" style="vertical-align:baseline"></td></tr></tbody></table></div>
					    </td>
					    <td> ${count } (${rate }%)</td>
				    </c:if>
				    
				    <c:if test="${varStatus.count==1 }">
				    	<c:set var="slipStr" value=""/>
				    </c:if>
					<c:set var="lengthData" value='${lengthData }${slipStr }"length${varStatus.count }":${rate }'/>
					<c:if test="${empty detailCount }">
					    <td>
					    	<c:if test="${vtVote.optionNum==1 }">
					    		<input id="selectedOption${varStatus.count }" name="selectedOption" type="radio" value="${vtVoteDetailVar.vdId }">
					    	</c:if>
					    	<c:if test="${vtVote.optionNum!=1 }">
					    		<input id="selectedOption${varStatus.count }" name="selectedOption"  type="checkbox" value="${vtVoteDetailVar.vdId }">
					    	</c:if>
					    </td>
				    </c:if>
				  </tr>
			</c:forEach>
  
					<c:set var="lengthData" value='${lengthData }}'/>
</table>

     

	 </td></tr>
</table>
--%>
</form:form>
<script type="text/javascript">
    Form.focusFirstElement($('vtVoteForm'));


</script>

<script>
var timer;
var length=0;
var lengthdata = ${lengthData};
function setLength()
{
	var step = 4;
	length = length+step;
	var flag = 0;
	for(var i in lengthdata)
	{
		if(length <= lengthdata[i]+step)
		{
			flag = 1;
			if(length > lengthdata[i])
			{
				$(i).style.width = lengthdata[i]*2+"px";
			}
			else
			{
				$(i).style.width = length*2+"px";
			}
		}
	}
	if(flag == 0)
	{
		clearInterval(timer);
	}
}
<c:if test="${not empty detailCount || not empty viewVoteResult }">
			timer = setInterval("setLength()" , 20);
</c:if>			
</script>
