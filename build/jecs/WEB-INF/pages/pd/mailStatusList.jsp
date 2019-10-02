<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="mailStatusList.title"/></title>
<content tag="heading"><jecs:locale key="mailStatusList.heading"/></content>
<meta name="menu" content="MailStatusMenu"/>

<c:set var="buttons">
		       <%-- <input type="button" class="button" style="margin-right: 5px"
			        onclick="javascript:window.close()"
			        value="<jecs:locale key="operation.button.close"/>"/>
			         --%>
			  	<button type="button" class="btn btn_sele"  onclick="javascript:window.close()" >
			  	    <jecs:locale key="operation.button.close"/>
			  	</button>
			  
</c:set>
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>
<div class="searchBar">
<table width="100%" border="0">
	 <c:forEach items="${list}" var="mailStatus">
		      <tr>
					<th width="20%"><font size="4"><jecs:locale  key="pd.logisticsDo"/></font></th><th width="60%"><font size="4">${mailStatus.mail_no}</font></th><th width="20%"></th>
			  </tr>
			  <c:forEach items="${mailStatus.items}" var="items" end="0" >
				        <tr>
				            <td width="20%"></td>
				            <td width="60%">${items.remark}</td>
				            <td width="20%"></td>
						</tr>
			   </c:forEach>
			   <tr>
							<td align="center"><font style="font-weight:bold;" size="4"><jecs:locale  key="bdCaculateLog.cretaeTime"/></font></td>
							<td align="center"><font style="font-weight:bold;" size="4"><jecs:locale  key="schedule.replace"/></font></td>
							<td align="center"><font style="font-weight:bold;" size="4"><jecs:locale  key="linkmanEvent.description"/></font></td>
			  </tr>
			  <c:forEach items="${mailStatus.items}" var="items" begin="1">
			      <c:choose>
				         <c:when test="${empty items.acceptTime && empty items.acceptAddress && empty  items.remark}">
				                <tr>
									<th width="20%"></th>
									<th width="60%"><jecs:locale key="pd.noLogisticsInformation"/></th>
									<th width="20%"></th>
								</tr> 
				         </c:when>
				         <c:otherwise>
					             <tr>
									<td width="20%">${items.acceptTime}</td>
									<td width="60%">${items.acceptAddress}</td>
									<td width="20%">${items.remark}</td>
								</tr>
				         </c:otherwise>
			      </c:choose>
			   </c:forEach>
			   <tr><td width="20%"></td><td width="60%"></td><td width="20%"></td></tr>
	 </c:forEach>
</table>
</div>
<script type="text/javascript">

</script>
