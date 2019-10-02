<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jmiAssureDetail.title" />
</title>
<content tag="heading">
<jecs:locale key="jmiAssureDetail.heading" />
</content>
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" />
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script>
<script type="text/javascript"
	src="./scripts/calendar/calendar-setup.js"> </script>
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script>
<script type="text/javascript" src="./scripts/jquery/jquery-142min.js"> </script>
<c:set var="buttons">
	<c:if test="${param.strAction != 'viewJmiAssure'}">
		<jecs:power powerCode="${param.strAction}">
			<input type="submit" class="button" name="save"
				onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
		</jecs:power>
	</c:if>
	<input type="button" class="button" name="back"
		onclick="javascript:	history.back();"
		value="<jecs:locale  key="operation.button.return"/>" />
</c:set>

<spring:bind path="jmiAssure.*">
	<c:if test="${not empty status.errorMessages}">
		<div class="error">
			<c:forEach var="error" items="${status.errorMessages}">
				<img src="<c:url value="/images/iconWarning.gif"/>"
					alt="<jecs:locale key="icon.warning"/>" class="icon" />
				<c:out value="${error}" escapeXml="false" />
				<br />
			</c:forEach>
		</div>
	</c:if>
</spring:bind>

<form:form commandName="jmiAssure" method="post"
	action="editJmiAssure.html" id="jmiAssureForm">
 
	<input type="hidden" name="strAction" value="${param.strAction }" />

	<table class='detail' width="70%">
		<form:hidden path="id" />
		<tbody class="window">
			<tr class="edit_tr">
				<th>
					<span class="text-font-style-tc">
						<jecs:label key="jmiAssure.assureType" />
					</span>
				</th>
				<td>
					<span class="textbox">
						<jecs:list listCode="assure_type" name="assureType" id="assureType"
						value="${jmiAssure.assureType}" defaultValue="1"
						onchange="getAssureContent(this.options[this.options.selectedIndex].value)"
						disabled="${disabled }" styleClass="textbox-text"/>
					</span>
				</td>
				<th>
					<span class="text-font-style-tc">
						<jecs:label key="jmiAssure.assureContent" />
					</span>
				</th>
				<td >
					<span id="td_assureContent" class="textbox"></span>
				</td>
			</tr>
			<tr class="edit_tr">
				<th>
					<span class="text-font-style-tc">
						<jecs:label key="jmiAssure.userCode" />
					</span>
				</th>
				<td>
					<span class="textbox">
						<c:choose>
							<c:when test="${param.strAction == 'viewJmiAssure'}">
								${jmiAssure.userCode }
							</c:when>
							<c:otherwise>
								<form:input path="userCode" id="userCode" cssClass="textbox-text" />
							</c:otherwise>
						</c:choose>
					</span>
				</td>
				<th>
					<span class="text-font-style-tc">
						<jecs:label key="jmiAssure.isFlag" />
					</span>
				</th>
				<td>
					<span class="textbox">
						<jecs:list listCode="assure_is_flag" name="isFlag"
						value="${jmiAssure.isFlag}" defaultValue="1"
						disabled="${disabled }" styleClass="textbox-text"/>
					</span>
				</td>
			</tr>
			
			<tr class="edit_tr" >
				<th>
					<span class="text-font-style-tc">
						<jecs:label key="jmiAssure.startTime" />
					</span>
				</th>
				<td>
					
						<c:choose>
							<c:when test="${param.strAction == 'viewJmiAssure'}">
								<span class="textbox"><fmt:formatDate value="${jmiAssure.startTime}" type="date"
									dateStyle="medium" />
									</span>
							</c:when>
							<c:otherwise>
								<span class="textbox">
									<input name="startTime" id="startTime" type="text" 
									value="${param.startTime }" class="textbox-text" 
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
								</span>
							</c:otherwise>
						</c:choose>
					
				</td>
				<th>
					<span class="text-font-style-tc">
						<jecs:label key="jmiAssure.endTime" />
					</span>
				</th>
				<td>
					<c:choose>
							<c:when test="${param.strAction == 'viewJmiAssure'}">
								<span class="textbox">
								<fmt:formatDate value="${jmiAssure.endTime}" type="date"
									dateStyle="medium" />
								</span>
							</c:when>
							<c:otherwise>
								<span class="textbox">
									<input name="endTime" id="endTime" type="text" 
									value="${param.endTime }" class="textbox-text"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
								</span>
							</c:otherwise>
						</c:choose>
					
				</td>
			</tr>
			<tr class="edit_tr"  id="tr_assureOrderCode" style="display: none">
				<th>
					<span class="text-font-style-tc-tare">
						<jecs:label key="jmiAssure.assureOrderCode" />
					</span>
				</th>
				<td colspan="3">
					<span class="text-font-style-tc-right">
					<form:textarea path="assureOrderCode" id="assureOrderCode" cssClass="textarea_border" />
					</span>
				</td>
			</tr>
			<tr class="edit_tr">
				<th>
					<span class="text-font-style-tc-tare">
						<jecs:label key="jmiAssure.memo" />
					</span>
				</th>
				<td colspan="3">
					<span class="text-font-style-tc-right">
						<form:textarea path="memo" id="memo" cssClass="textarea_border" />
					</span>
				</td>
			</tr>
			<tr>
				<td class="command" colspan="4" align="center">
					<c:if test="${param.strAction != 'viewJmiAssure'}">
						<jecs:power powerCode="${param.strAction}">
							<input type="submit" class="btn btn_sele" name="save"
								onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
						</jecs:power>
					</c:if>
					<input type="button" class="btn btn_sele" name="back"
						onclick="javascript:	history.back();"
						value="<jecs:locale  key="operation.button.return"/>" />					
				</td>
			</tr>
		</tbody>
	</table>
</form:form>

<script type="text/javascript">
	
	function getAssureContent(obj) {
		//清空所有的选择项
		$("#assure_content_wjf").empty();
		$("#assure_content_yj").empty();
		$("#assure_content_xbwl").empty();
		$("#assure_content_bth").empty();
		if(''==obj){
			obj = $("#assureType").val();
		}
		//不退货担保没有业务开始时间也业务截止时间选项
		if(4 == obj) {
			/* var strAction = '${param.strAction}';
			if(strAction != 'viewJmiAssure') {
				$("#tr_startTime").hide();
				$("#tr_endTime").hide();
				$("#tr_assureContent").hide();
			} */
			$("#tr_startTime").hide();
			$("#tr_endTime").hide();
			$("#tr_assureContent").hide();
			$("#tr_assureOrderCode").show();
		} else {
			$("#tr_startTime").show();
			$("#tr_endTime").show();
			$("#tr_assureContent").show();
			$("#tr_assureOrderCode").hide();
		}
		var trobj = document.getElementById("td_assureContent");
		if(1==obj) {
			trobj.innerHTML='<jecs:list styleClass="textbox-text" listCode="assure_content_wjf" value="${jmiAssure.assureContent}" defaultValue=""  id="assure_content_wjf" name="assureContent" disabled="${disabled }"/>';
		} else if(2==obj) {
			trobj.innerHTML='<jecs:list styleClass="textbox-text" listCode="assure_content_yj" value="${jmiAssure.assureContent}"  defaultValue=""  id="assure_content_yj" name="assureContent" disabled="${disabled }"/>';
		} else if(3==obj) {
			trobj.innerHTML='<jecs:list styleClass="textbox-text" listCode="assure_content_xbwl" value="${jmiAssure.assureContent}" defaultValue=""  id="assure_content_xbwl" name="assureContent" disabled="${disabled }"/>';
		} else if(4==obj) {
			trobj.innerHTML='<jecs:list styleClass="textbox-text" listCode="assure_content_bth" value="${jmiAssure.assureContent}" defaultValue=""  id="assure_content_bth" name="assureContent" disabled="${disabled }"/>';
		} else if(''==obj) {
			trobj.innerHTML='<select class="textbox-text"></select>';
		}
	}
	getAssureContent('');
</script>

