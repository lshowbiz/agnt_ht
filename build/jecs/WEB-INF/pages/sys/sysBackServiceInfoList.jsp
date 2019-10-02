<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="sysBackServiceInfoList.title"/></title>
<content tag="heading"><jecs:locale key="sysBackServiceInfoList.heading"/></content>
<meta name="menu" content="SysBackServiceInfoMenu"/>

<c:set var="buttons">
		<div class="new_searchBar" style="margin-left:20px;">
			<jecs:power powerCode="excuteBackServer">
			<button  class="btn btn_sele" style="margin-right: 5px"onclick="excuteServer();">
				<jecs:locale key="operation.button.execute"/>
			</button>
			</jecs:power>	        	
			<button  class="btn btn_sele" style="margin-right: 5px"onclick="refreshMe();">
				<jecs:locale key="button.refresh"/>
			</button>
		</div>
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

<form name="frm" id="frm" action="<c:url value='/sysBackServiceInfos.html'/>" >
<div class="searchBar">	
	<input type="hidden" id="strAction" name="strAction" value="${requestParaMap.strAction}"/>
	
	<div class="new_searchBar">
		<jecs:title  key="service.title.serviceName"/>
		<jecs:list listCode="sys.backserver.command" name="serviceCommand" id="serviceCommand" showBlankLine="true" value="${requestParaMap.serviceCommand}" defaultValue=""/>	
	</div>
	<div class="new_searchBar">
		<jecs:title  key="bdPeriod.wyear"/>
		<input type="text" name="wyear" readonly="readonly" value="${wyear}" id="wyear"/>
	</div>
	<div class="new_searchBar">
		<jecs:title  key="bdPeriod.wmonth"/>
		<input type="text" name="wmonth" readonly="readonly" value="${wmonth}" id="wmonth"/>
	</div>
	<c:out value="${buttons}" escapeXml="false"/>
	</div>
</form>
<ec:table 
	tableId="sysBackServiceInfoListTable"
	items="sysBackServiceInfoList"
	var="sysBackServiceInfo"
	action="${pageContext.request.contextPath}/sysBackServiceInfos.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
				<ec:row >
				
    			<ec:column property="exeFlag" title="busi.common.flag" />
    			<ec:column property="info" title="icon.information" />
    			
    			<ec:column property="command" title="service.title.serviceName" >
    				<jecs:code listCode="sys.backserver.command" value="${sysBackServiceInfo.command}"/>
    			</ec:column>
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editSysBackServiceInfo(bsiId){
   		<jecs:power powerCode="editSysBackServiceInfo">
					window.location="editSysBackServiceInfo.html?strAction=editSysBackServiceInfo&bsiId="+bsiId;
			</jecs:power>
		}
	function excuteServer(){
				$('strAction').value='excuteBackServer';
				if($('serviceCommand').value=='bonusCalculate' || $('serviceCommand').value=='bonusConfirm'){
							if(($('wyear').value == '') || ($('wmonth').value == '')||($('wyear').value == null) || ($('wmonth').value == null)){
										alert('<jecs:locale key="bdSendRecord.wyear.required"/>,'+'<jecs:locale key="operation.notice.js.wmonth.require"/>');
								}
					}
					$('frm').submit();
		}
		
	function refreshMe(){
				$('strAction').value='viewBackserver';
				$('frm').submit();
			}
</script>
