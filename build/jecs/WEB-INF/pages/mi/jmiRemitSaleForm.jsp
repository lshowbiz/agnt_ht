<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>


<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" />
<script type="text/javascript" src="./scripts/jquery/jquery-142min.js">
	
</script>
<script type="text/javascript" src="./scripts/calendar/calendar.js">
	
</script>
<script type="text/javascript"
	src="./scripts/calendar/calendar-setup.js">
	
</script>
<script type="text/javascript" src="./scripts/calendar/lang.jsp">
	
</script>

<title><jecs:locale key="editjmiRemitSale.title" /></title>
<content tag="heading"> <jecs:locale
	key="editjmiRemitSale.heading" /> </content>
<meta name="menu" content="editjmiRemitSale" />

			
			
<c:set var="buttons">

	<c:if test="${param.strAction == 'addJmiRemitSale'}">
		<button type="submit" class="btn btn_sele" name="save"
			onclick="bCancel=false" >
		<jecs:locale key="operation.button.save"/>
		</button>
	</c:if>

	<c:if test="${param.strAction == 'editJmiRemitSale'}">
		<button class="btn btn_sele" id="edit">修改</button>
	</c:if>

	<button  type="button" class="btn btn_sele" name="back"
		onclick="location='jmiRemitSale.html'">
		<jecs:locale key="operation.button.return"/>
	</button>
	
</c:set>

<spring:bind path="jmiRemitSale.*">
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

<form:form commandName="jmiRemitSale" method="post"
	action="editjmiRemitSale.html" id="jmiRemitSale"
	onsubmit="return verifyJRSForm()">

	<div id="titleBar">
		<c:out value="${buttons}" escapeXml="false" />
	</div>
	<input type="hidden" name="strAction" value="${param.strAction }" />

	<table class='detail' width="100%">

		<tr style="display: none">
			<th>id:</th>
			<td align="left"><form:input path="id" id="id"
					cssClass="text medium" readonly="true" /></td>
		</tr>

		<tr>
			<th>会员账号:</th>
			<td align="left"><c:choose>
					<c:when test="${param.strAction == 'editJmiRemitSale'}">
						<form:input path="userCode" id="userCode" readonly="true"
							cssClass="text medium" />
					</c:when>
					<c:otherwise>
						<form:input path="userCode" id="userCode" cssClass="text medium" />
					</c:otherwise>
				</c:choose></td>
		</tr>

		<tr>
			<th>(格式:201702)免重消开始时间:</th>
			<td align="left"><form:input path="startWeek" id="startWeek"
					cssClass="text medium" /></td>
		</tr>

		<tr>
			<th>免重消结束时间:</th>
			<td id="fnbdPeriod" align="left"><form:input path="endWeek"
					id="endWeek" cssClass="text medium" /></td>
		</tr>
		<tr>
			<th>备注:</th>
			<td align="left"><form:input path="remark" id="remark"
					cssClass="text medium" /></td>
		</tr>
		<tr style="display: none">
			<th>创建人:</th>
			<td align="left"><form:input path="createUser" id="createUser"
					cssClass="text medium" /></td>
		</tr>

	</table>

</form:form>
<script type="text/javascript">
	$(document).ready(
			function() {
				// $("#createUser").val("${sessionScope.sessionLogin.userCode}");
				FunbdPeriod();

				$("#endWeek").bind("blur", verifyEndWeek);

				$("#startWeek").bind("blur", verifyStartWeek);

				$("#edit").bind("click", ajaxUpdateMemberById);

				if ($("#startWeek").val() != 0 && $("#startWeek").val() != ""
						&& $("#startWeek").val() != null) {
					if (bdPeriodflag >= $("#startWeek").val()) {
						alert("免重消开始时间已超过结算期，不能修改");
						$("#startWeek").attr("readonly", "true");
					}
				}

			})
	//验证表单信息
	function verifyJRSForm() {

		var resultFlag = false;
		// 叠加判断链 i>0 都放回false
		var i = 0;
		if (verifyStartEndWeekFlag > 0) {
			i++
		}
		;
		if (!compareStarEndtWeek()) {
			i++
		}
		;

		if (ajaxVerifyJMIMEMBER() == 0) {
			i++;
			alert("会员不存在")
		}
		;

		ajaxVerifyStarEndtWeek();
		if (ajaxVerifyStarEndtWeekflag == 1
				|| ajaxVerifyStarEndtWeekflag == "1") {
			i++;
			alert($("#userCode").val() + "已存在免消期内")
		}
		
		;
		if (ajaxVerifyStarEndtWeekflag == 2
				|| ajaxVerifyStarEndtWeekflag == "2") {
			i++;
			alert($("#userCode").val() + "输入时间已结算，不能输入")
		}
		;
	
		if (ajaxVerifyStarEndtWeekflag == 3
				|| ajaxVerifyStarEndtWeekflag == "3") {
			i++;
			alert($("#userCode").val() + "输入时间包含其他免重消时间")
		}
		;
		 
		i > 0 ? resultFlag = false : resultFlag = true;
		return resultFlag;
	}
	//验证输入的免重消日期
	var verifyStartEndWeekFlag = 0;
	function verifyStartWeek() {
		verifyStartEndWeekFlag = 0;
		var re = new RegExp(/^\d{6}$/);
		var value = $(this).val();
		if (!re.test(value)) {
			alert("免重消日期输入错误");
			verifyStartEndWeekFlag++;
		}
		// 判断编辑时输入的开始免重消时间
		if ($("#startWeek").val() != 0 && $("#startWeek").val() != ""
				&& $("#startWeek").val() != null ) {
			if (bdPeriodflag >= $("#startWeek").val()) {
				alert("免重消开始时间不能输入已结算期");
				if( isNaN(oldStartWeekValue)){
					$("#startWeek").val("0")
				}else{
					$("#startWeek").val(oldStartWeekValue);
				}			
			}
		}
		
		return verifyStartEndWeekFlag;
	}
	function verifyEndWeek() {
		verifyStartEndWeekFlag = 0;
		var re = new RegExp(/^\d{6}$/);
		var value = $(this).val();
		if (!re.test(value)) {
			alert("免重消日期输入错误");
			verifyStartEndWeekFlag++;
		}
		return verifyStartEndWeekFlag;
	}
	//验证输入的免重消日期对比大小
	function compareStarEndtWeek() {
		var resultFlag = false;
		var value1 = $("#startWeek").val();
		var value2 = $("#endWeek").val();
		parseInt(value1) <= parseInt(value2) ? resultFlag = true
				: alert("免重消开始日期要小于等于结束日期");
		return resultFlag;
	}
	// AJAX 验证会员是否已存在
	var ajaxVerifyJMIMEMBERflag = 0;
	function ajaxVerifyJMIMEMBER() {
		ajaxVerifyJMIMEMBERflag = 0;
		var ajaxUserCode = $("#userCode").val();
		$.ajax({
					url : "ajaxjmiRemitSale.html?AjaxMethod=ajaxVerifyJMIMEMBER&userCode="
							+ $("#userCode").val()
							+ "&startWeek="
							+ $("#startWeek").val(),
					type : "get",
					dataType : "json",
					async : false,
					success : function(msg) {
						ajaxVerifyJMIMEMBERflag = parseInt(msg.ajaxVerifyJMIMEMBERflag);

					}
				})
		return ajaxVerifyJMIMEMBERflag;
	}

	// AJAX 验证会员已存在的免重消日期
	var ajaxVerifyStarEndtWeekflag = 0;
	function ajaxVerifyStarEndtWeek() {
		ajaxVerifyStarEndtWeekflag = 0;

		$.ajax({
					url : "ajaxjmiRemitSale.html?AjaxMethod=ajaxSearchStarEndWeek&startWeek="
							+ $("#startWeek").val()
							+ "&endWeek="
							+ $("#endWeek").val()
							+ "&userCode="
							+ $("#userCode").val(),
					type : "get",
					dataType : "json",
					async : false,
					success : function(msg) {
						ajaxVerifyStarEndtWeekflag = parseInt(msg.starEndWeekflag);
					
					}

				})

		return ajaxVerifyStarEndtWeekflag;
	}

	/*  修改逻辑 开始   */
	function verifyUpdateMemberForm() {
		var resultFlag = false;
		// 叠加判断链 i>0 都放回false
		var i = 0;
		if (verifyStartEndWeekFlag > 0) {
			i++;
		}
		;
		if (!compareStarEndtWeek()) {
			i++;
		}
		;

       if (ajaxVerifyJMIMEMBER() == 0) {
			i++;
			alert("会员不存在")
		}
		;

		if (bdPeriodflag >= $("#endWeek").val()) {
			i++;
			alert("免重消结束时间需要大于结算期");

		}
		i > 0 ? resultFlag = false : resultFlag = true;

		return resultFlag;
	}

	function ajaxUpdateMemberById(e) {
		e.preventDefault();
		var startWeek = $("#startWeek").val();
		var endWeek = $("#endWeek").val();
		var remark = $("#remark").val();
		if (verifyUpdateMemberForm()) {
			$.ajax({
						url : "ajaxjmiRemitSale.html?AjaxMethod=ajaxMemberById&remark="
								+ remark
								+ "&id="
								+ $("#id").val()
								+ "&endWeek="
								+ endWeek
								+ "&startWeek="
								+ startWeek
								+ "&userCode="
								+ $("#userCode").val(),
						type : "get",
						dataType : "json",
						async : false,
						success : function(msg) {
							flag = msg.ajaxUpdateMemberByIdFlag;
							alert(flag);
							if ("更新成功" == flag) {
								window.location.href = "jmiRemitSale.html";
							}
						}

					});

		}

	}
	/*  修改逻辑  结束  */

	//获取最新结算周
	var bdPeriodflag = 0;
	// 判断编辑时输入的开始免重消时间
	var oldStartWeekValue = 0
	var oldStartWeekFlag = 0;
	var MaxBdPeriodflag = 0 ;
	function FunbdPeriod() {
		bdPeriodflag = 0;
		$.ajax({
			url : "ajaxjmiRemitSale.html?AjaxMethod=bdPeriod",
			type : "get",
			dataType : "json",
			async : false,
			success : function(msg) {
				bdPeriodflag = parseInt(msg.bdPeriod);				
				 oldStartWeekValue = parseInt($("#startWeek").val());
				 MaxBdPeriodflag = parseInt(msg.bdPeriod.substring(0,4)+"52");				
			}
		});
	}
</script>
