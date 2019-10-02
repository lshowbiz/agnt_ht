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
<title><jecs:locale key="jmiRemitSale.title" /></title>
<content tag="heading"> <jecs:locale
	key="jmiRemitSale.heading" /> </content>
<meta name="menu" content="jmiRemitSale" />


<!-- 搜索栏 -->
<form action="<c:url value='/jmiRemitSale.html'/>" method="get"
	name="searchForm" id="searchForm" >
	<div class="searchBar">
		<div class="new_searchBar">
			会员编号:
			<input name="userCode" type="text" size="10" value="${param.userCode }" /> 
		</div>
		<div class="new_searchBar">
			备注:
			<input name="remark" type="text" size="10" value="${param.remark }" /> 
		</div>
		<div class="new_searchBar">
			创建人:
			<input name="createUser" type="text" size="10" value="${param.createUser }" />
		</div>
	
		<div class="new_searchBar">
			免重消开始时间:
		<input id="startWeek" name="startWeek" type="text" value="${param.startWeek }" size="10" maxlength="10"/>
		</div>
		<div class="new_searchBar">
			免重消结束时间: 
			<input id="endWeek" name="endWeek" type="text" size="10" maxlength="10" value="${param.endWeek }"/>		
		</div>
		
		<%-- <div class="new_searchBar">
			创建时间:<input id="createTime"
			name="createTime" type="text" size="10" maxlength="10"
			value="${param.createTime }" /> 
			<img src="./images/calendar/calendar7.gif" id="img_createTime"
			style="cursor: pointer;" title="<jecs:locale key="Calendar.TT.SEL_DATE"/>" />
			<script type="text/javascript">
			Calendar.setup({
				inputField : "createTime",
				ifFormat : "%Y-%m-%d",
				button : "img_createTime",
				singleClick : true
			});
			</script>
		</div> --%>
		<div class="new_searchBar">
			创建时间：
			<input name="createTime" id="createTime" type="text" 
			value="${param.logCreateTime }" style="cursor: pointer;width:auto;"
			onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
		</div>
		<div class="new_searchBar">
			<!-- <input name="search" class="button" type="submit" value="查询" /> -->
			<button type="submit" class="btn btn_sele" name="cancel">
					<jecs:locale key="operation.button.search"/>
			</button>
			<jecs:power powerCode="addJmiRemitSale">
				<button type="button" class="btn btn_ins"
					onclick="location.href='${pageContext.request.contextPath}/editjmiRemitSale.html?strAction=addJmiRemitSale'">
					<jecs:locale key="member.new.num"/>
				</button>
			</jecs:power>
			 <jecs:power powerCode="remitSaleImport"> 
			<button type="button" class="btn btn_sele" name="import"
				onclick="location.href='${pageContext.request.contextPath}/jmiRemitSaleImport.html?strAction=remitSaleImport'"/>
				<jecs:locale key="button.importMember" />
			</button>
			</jecs:power> 
			<jecs:power powerCode="remitSaleTeamImport">
			<button type="button" class="btn btn_sele" name="import"
				onclick="location.href='${pageContext.request.contextPath}/jmiRemitSaleImport.html?strAction=remitSaleTeamImport'"/>
				<jecs:locale key="button.importTeam" />
			</button>
			</jecs:power>
		</div>
		
</form>
</div>




<!-- 列表栏 -->
<div>
	<ec:table tableId="jmiRemitSaleTable" items="jmiRemitSaleList"
		var="jmiRemitSale"
		action="${pageContext.request.contextPath}/jmiRemitSale.html"
		width="100%" autoIncludeParameters="true" retrieveRowsCallback="limit"
		rowsDisplayed="20" sortable="true"
		imagePath="./images/extremetable/*.gif">
		<ec:row>
			<ec:column property="userCode" title="会员编号" />
			<ec:column property="startWeek" title="免重消开始时间" />
			<ec:column property="endWeek" title="免重消结束时间" />
			<ec:column property="createTime" title="创建时间">
				<c:out value="${fn:substring(jmiRemitSale.createTime, 0, 16)}" />
			</ec:column>
			<ec:column property="remark" title="备注" />
			<ec:column property="createUser" title="创建人" />
			<ec:column property="id" title="id" headerStyle="display:none"
				style="display:none" />
			<ec:column property="edit" title="title.operation" sortable="false"
				viewsAllowed="html">
				<img src="<c:url value='/images/icons/editIcon.gif'/>"
					onclick="javascript:editJmiRemitSale('${jmiRemitSale.id}','${jmiRemitSale.userCode}','${jmiRemitSale.endWeek}')"
					style="cursor: pointer;" title="<jecs:locale key="修改资料" />" />
				<img id="delete" src="<c:url value='/images/icons/deleteIcon.gif'/>"
					onclick="javascript:deleteJmiRemitSale('${jmiRemitSale.id}','${jmiRemitSale.userCode}','${jmiRemitSale.startWeek}')"
					style="cursor: pointer;" title="<jecs:locale key="删除记录" />" />
			</ec:column>

		</ec:row>
	</ec:table>


</div>
<script type="text/javascript">
	 $(document).ready(function() {

		FunbdPeriod();

	}) 
	//获取最新结算周
	var bdPeriodflag = 0;
	
	function FunbdPeriod() {
		bdPeriodflag = 0;
		$.ajax({
			url : "ajaxjmiRemitSale.html?AjaxMethod=bdPeriod",
			type : "get",
			dataType : "json",
			async : false,
			success : function(msg) {
				bdPeriodflag = parseInt(msg.bdPeriod);

			}
		});
	}
	
	function editJmiRemitSale(id, userCode, endWeek) {
		var changeConfirm = confirm("更新会员" + userCode + "资料");
		var flag = 0;
		if (changeConfirm) {
			$.ajax({
				url : "ajaxjmiRemitSale.html?AjaxMethod=permitEdit&id=" + id
						+ "&userCode=" + userCode + "&endWeek=" + endWeek,
				type : "get",
				dataType : "json",
				async : false,
				success : function(msg) {
					flag = parseInt(msg.permitEditFlag);
				}
			});
			if (flag == 2) {
				alert("会员" + userCode + "超过有限期，无法修改");
			} else {
				window.location = "${pageContext.request.contextPath}/editjmiRemitSale.html?strAction=editJmiRemitSale&id="
						+ id;
			}
		}

	}

	function deleteJmiRemitSale(id,userCode,startWeek) {

		if (parseInt(startWeek) > parseInt(bdPeriodflag)) {

			var changeConfirm = confirm("删除会员" + userCode + "资料");
			var flag = 0;
			if (changeConfirm) {
				$
						.ajax({
							url : "ajaxjmiRemitSale.html?AjaxMethod=deleteJmiRemitSale&id="
									+ id,
							type : "get",
							dataType : "json",
							async : false,
							success : function(msg) {
								flag = parseInt(msg.deleteJmiRemitSaleFlag);
							}
						});
				if (flag == 1) {
					alert("删除成功");
					window.location = "${pageContext.request.contextPath}/jmiRemitSale.html";

				} else {
					alert("删除失败");
				}
			}
		} else {
			alert("免重消开始时间已结算，无法删除记录");
		}

	}

	
	
</script>
