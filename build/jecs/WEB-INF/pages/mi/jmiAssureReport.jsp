<%@ page language="java" errorPage="/error.jsp" contentType="text/html; charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="pdSendInfo.title" /></title>
<content tag="heading">
<jecs:locale key="pdSendInfo.heading" />
</content>
<meta name="menu" content="pdSendInfo" />


<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script> 
<script type="text/javascript" src="<c:url value='/dwr/util.js'/>" ></script> 
<script type="text/javascript" src="<c:url value='/dwr/engine.js'/>" ></script>
<script type="text/javascript" src="./scripts/jquery/jquery-142min.js"> </script>



<form method="post" commandName="jmiAssureReport" action="jmiAssureReport.html"  id="jmiAssureReport">
<spring:bind path="jmiAssureReport.*">
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


<table class="detail" width="70%">
<tbody class="window" >
		<tr class="edit_tr">
			<th><span class="text-font-style-tc"><jecs:label key="jmiAssure.assureType" /></span></th>
			<td>
				<span class="textbox">
					<jecs:list name="assure_type" id="assure_type" onchange="setAssureType(this.options[this.options.selectedIndex].value)" listCode="assure_type_report" value="${param.assure_type }" defaultValue="" styleClass="textbox-text"/>
				</span>
			</td>
			<th><span class="text-font-style-tc"><jecs:locale key="jmiAssure.userCode" /></span></th>
			<td>
        	<span class="textbox"><input name="userCode" type="text" size="10" value="${param.userCode}" class="textbox-text"/></span>
			</td>
		</tr>
		
		<tr id="time" class="edit_tr">
			
			<th><span class="text-font-style-tc"><jecs:label key="jmiAssure.startTime" /></span></th>
			<td>
				<span class="textbox">
					<input name="startTime" id="startTime" type="text" value="${param.startTime }" style="cursor: pointer;" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"  class="textbox-text"/>
				</span>
				
			</td>
			<th><span class="text-font-style-tc"><jecs:label key="jmiAssure.endTime" /></span></th>
			<td>
				<span class="textbox">
				<input name="endTime" id="endTime" type="text" value="${param.endTime }" style="cursor: pointer;" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})" 
				class="textbox-text"/>
				</span>
			</td>
		</tr>
		
		<tr>		
			<td class="command" colspan="4" align="center">
				<input type="hidden" name="strAction" id="strAction" value="${param.strAction}"/>
				<button type="button" name="button"  onclick="submitMe('xbwl')" class="btn btn_sele"><jecs:locale key="button.report"/></button>
			</td>
		</tr>
	</tbody>
</table>


</form>



<script type="text/javascript">
function submitMe(showFlag){
	var startTime= $('startTime').value;
	var endTime =$('endTime').value;
		$('#jmiAssureReport').submit();
	}
	
	function setAssureType(obj){
		if(''==obj){
			obj = $("#assure_type").val();
		}
		if('3'==obj){
			$("#time").show();
		}else{
			$("#time").hide();
		}
	}
	setAssureType('3');
</script>