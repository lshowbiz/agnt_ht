<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="foundationOrderList.title" />
</title>
<content tag="heading">
<jecs:locale key="foundationOrderList.heading" />
</content>
<meta name="menu" content="FoundationOrderMenu" />
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="./js/jquery-1.4.2.min.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script> 
<c:set var="buttons">
	<!--<jecs:power powerCode="addFoundationOrder">
		<input type="button" class="button" style="margin-right: 5px"
			onclick="location.href='<c:url value="/editFoundationOrder.html"/>?strAction=addFoundationOrder'"
			value="<jecs:locale key="button.add"/>" />
	</jecs:power>-->
</c:set>



<form name="frm" id="frm"
	action="<c:url value='/foundationOrders.html'/>">
	
	<div class="searchBar">
	<%-- 
		<table width="100%" border="0">
			<tr>
				<td>
	--%>
						<input type="hidden" id="strAction" name="strAction"
							value="${param.strAction}" />
					<div class="new_searchBar">
						<jecs:title key="foundationOrder.userCode" />
						<input name="userCode" id="userCode"
							value="<c:out value='${param.userCode}'/>" cssClass="text medium" />
					</div>
						<!-- 
						<jecs:title key="foundationOrder.payType" />
						<jecs:list listCode="foundationpaytype" name="payType" id="payType"
							showBlankLine="false" value="${param.payType}"
							defaultValue="" />
							 -->	
					<div class="new_searchBar">
						<jecs:title key="foundationOrder.status" />
						<jecs:list listCode="foundationpayresult" name="status" id="status"
							showBlankLine="false" value="${param.status}"
							defaultValue="" />
					</div>
					<div class="new_searchBar">	
						<label><jecs:locale key="foundationItem.name" />:</label>
						<select name="fiId">
							<option value=""></option>
							<c:forEach items="${foundationOrderItems}" var="foi">
								<option value="${foi.fiId}" <c:if test="${foi.fiId eq param.fiId}">selected</c:if>>
										${foi.name}
								</option>
							</c:forEach>
						</select>
					</div>
					<div class="new_searchBar">
						<jecs:title key="foundationOrder.startTime"/>
							
							<input name="createTimeStart" id="createTimeStart" type="text" 
									value="${param.createTimeStart}" style="cursor: pointer;width:76px;"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
									-
									<input name="createTimeEnd" id="createTimeEnd" type="text"
									value="${param.createTimeEnd}" style="cursor: pointer;width:76px;"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
					<!--  
						<input name="createTimeStart" size='11' id="createTimeStart"  value="${param.createTimeStart}">
				      	<img src="./images/calendar/calendar7.gif" id="img_startTime" style="cursor: pointer;" title="<jecs:locale key="Calendar.TT.SEL_DATE"/>"/> 
								<script type="text/javascript"> 
									Calendar.setup({
									inputField     :    "createTimeStart", 
									ifFormat       :    "%Y-%m-%d",  
									button         :    "img_startTime", 
									singleClick    :    true
									}); 
								</script>
								- <input name="createTimeEnd" size='11'  id="createTimeEnd"  value="${param.createTimeEnd}">
				      	<img src="./images/calendar/calendar7.gif" id="img_endTime" style="cursor: pointer;" title="<jecs:locale key="Calendar.TT.SEL_DATE"/>"/> 
								<script type="text/javascript"> 
									Calendar.setup({
									inputField     :    "createTimeEnd", 
									ifFormat       :    "%Y-%m-%d",  
									button         :    "img_endTime", 
									singleClick    :    true
									}); 
								</script>
					-->
					</div>
						<div class="new_searchBar">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="submit" class="btn btn_sele" style="width:auto"><jecs:locale  key='operation.button.search'/></button>
								<c:out value="${buttons}" escapeXml="false" />
						</div>
<%-- 						
					</td>	
			</tr>
		</table>
--%>
	</div>
<%-- 
	<div class="searchBar">
		<table width="100%" border="0">
			<tr>
				<td>
					<input type="hidden" id="strAction" name="strAction"
						value="${param.strAction}" />
					<jecs:title key="foundationOrder.userCode" />
					<input name="userCode" id="userCode"
						value="<c:out value='${param.userCode}'/>" cssClass="text medium" />
					<!-- 
					<jecs:title key="foundationOrder.payType" />
					<jecs:list listCode="foundationpaytype" name="payType" id="payType"
						showBlankLine="false" value="${param.payType}"
						defaultValue="" />
						 -->	
					<jecs:title key="foundationOrder.status" />
					<jecs:list listCode="foundationpayresult" name="status" id="status"
						showBlankLine="false" value="${param.status}"
						defaultValue="" />
					
					<jecs:locale key="foundationItem.name" />
					<select name="fiId">
						<option value=""></option>
						<c:forEach items="${foundationOrderItems}" var="foi">
							<option value="${foi.fiId}" <c:if test="${foi.fiId eq param.fiId}">selected</c:if>>
									${foi.name}
							</option>
						</c:forEach>
					</select>
					<jecs:title key="foundationOrder.startTime"/>
						<input name="createTimeStart" size='11' id="createTimeStart"  value="${param.createTimeStart}">
			      	<img src="./images/calendar/calendar7.gif" id="img_startTime" style="cursor: pointer;" title="<jecs:locale key="Calendar.TT.SEL_DATE"/>"/> 
							<script type="text/javascript"> 
								Calendar.setup({
								inputField     :    "createTimeStart", 
								ifFormat       :    "%Y-%m-%d",  
								button         :    "img_startTime", 
								singleClick    :    true
								}); 
							</script>
							- <input name="createTimeEnd" size='11'  id="createTimeEnd"  value="${param.createTimeEnd}">
			      	<img src="./images/calendar/calendar7.gif" id="img_endTime" style="cursor: pointer;" title="<jecs:locale key="Calendar.TT.SEL_DATE"/>"/> 
							<script type="text/javascript"> 
								Calendar.setup({
								inputField     :    "createTimeEnd", 
								ifFormat       :    "%Y-%m-%d",  
								button         :    "img_endTime", 
								singleClick    :    true
								}); 
							</script>
					<input type="submit" class="button"
						value="<jecs:locale  key='operation.button.search'/>" />
					<c:out value="${buttons}" escapeXml="false" />
					
				</td>	
			</tr>
		</table>
	</div>
--%>
</form>
	<ec:table tableId="foundationOrderListTable" items="foundationOrderList"
		var="foundationOrder"
		action="${pageContext.request.contextPath}/foundationOrders.html"
		width="100%" retrieveRowsCallback="limit" rowsDisplayed="20" 
		autoIncludeParameters="true" 
		sortable="true" imagePath="./images/extremetable/*.gif">
		
		<ec:exportXls fileName="fi_inc_exp_stat.xls"/>

		<ec:row>
			<ec:column property="jmiMember.userCode" title="foundationOrder.userCode" />
			<ec:column property="userName" sortable="false" title="sys.user.username" cell="com.joymain.jecs.util.ectable.EcTableCell">
				${ foundationOrder.jmiMember.firstName }${foundationOrder.jmiMember.lastName }
			</ec:column>
			<ec:column property="jmiMember.mobiletele" title="alCompany.phone" />
			<ec:column property="foundationItem.name" title="foundationItem.name" />
			<ec:column property="fiNum" title="foundationOrder.fiNum" />

			<ec:column property="status" title="foundationOrder.status" cell="com.joymain.jecs.util.ectable.EcTableCell">
				<jecs:code listCode="foundationpayresult"
					value="${foundationOrder.status}" />
			</ec:column> 
			<ec:column property="amount" title="foundationOrder.amount" />
			<ec:column property="createTime" title="foundationOrder.createTime" style="white-space:nowrap;">
				<fmt:formatDate value="${foundationOrder.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
			</ec:column>
			<ec:column property="startTime" title="foundationOrder.startTime" style="white-space:nowrap;">
				<fmt:formatDate value="${foundationOrder.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
			</ec:column>
			

		</ec:row>
	
	</ec:table>

