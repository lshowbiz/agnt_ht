<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="sysBackServiceInfoList.title"/></title>
<content tag="heading"><jecs:locale key="sysBackServiceInfoList.heading"/></content>
<meta name="menu" content="SysBackServiceInfoMenu"/>

<c:set var="buttons">
		
	 
		<input type="button" class="button" style="margin-right: 5px"
			        onclick="excuteServer();"
			        value="<jecs:locale key="operation.button.execute"/>"/>
       	
		<input type="button" class="button" style="margin-right: 5px"
			        onclick="refreshMe();"
			        value="<jecs:locale key="button.refresh"/>"/>
</c:set>
<c:if test="${not empty messages}">
	 <div class="error">    
	<c:forEach var="msg" items="${messages}">
		<c:if test = "${not empty msg}">
		<img src="<c:url value="/images/iconInformation.gif"/>"
                alt="<jecs:locale key="icon.information"/>" class="icon" />
            <c:out value="${msg}" escapeXml="false"/><br />
		</c:if>
		
		
	</c:forEach>
</div>
	<c:remove var="messages" scope="session" />
</c:if>

<form name="frm" id="frm" action="<c:url value='/sysBackServers.html'/>" >
<div class="searchBar">	

	<input type="hidden" id="strAction" name="strAction" value="${param.strAction}"/>
	<input type="hidden" id="excuteFlag" name="excuteFlag"  value="view"/>
	<c:out value="${buttons}" escapeXml="false"/>
	</div>
</form>
<ec:table 
	tableId="sysBackServiceInfoListTable"
	items="sysBackServiceInfoList"
	var="sysBackServiceInfo"
	action="${pageContext.request.contextPath}/sysBackServers.html"
	width="100%"

./images/extremetableble="true" imagePath="/jecs/images/extremetable/*.gif">
				<ec:row >
				
    			<ec:column property="exeFlag" title="busi.common.flag" />
    			<ec:column property="info" title="icon.information" />
    			
    			<ec:column property="command" title="service.title.serviceName" >
    				<jecs:code listCode="sys.backserver.command" value="${sysBackServiceInfo.command}"/>
    			</ec:column>
				</ec:row>

</ec:table>	

<script type="text/javascript">

   
	function excuteServer(){
				$('excuteFlag').value='excute';
				
				$('frm').submit();
		}
		
	function refreshMe(){
				$('excuteFlag').value='view';
				$('frm').submit();
			}
</script>
