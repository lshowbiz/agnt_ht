<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jpmProductList.title" />
</title>
<content tag="heading">
<jecs:locale key="jpmProductList.heading" />
</content>
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" />
<meta name="menu" content="jpmProductMenu" />


		<form name="frm" action="<c:url value='/jpmCouponInfos.html?strAction=selectJpmCouponInfo'/>">
			<div class="titleBar">
				
				<table width="100%" border="0">
					<tr>
						
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="hidden" id="strAction" name="strAction"
								value="${param.strAction}" />
		
							<jecs:label key="jpmCouponInfo.name" />
							&nbsp;&nbsp;
							<input name="cpName" id="cpName"
								value="<c:out value='${param.cpName}'/>" cssClass="text medium" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						 <button type="submit" class="btn btn_sele" ><jecs:locale  key='operation.button.search'/></button>
				<%-- 		 
							<input type="submit" class="button"
								value="<jecs:locale  key='operation.button.search'/>" />
--%>
						<%-- 
						<td>
							<span class="textbox"><form:input path="cpName"  id="cpName" cssClass="textbox-text" /></span>	
							<button type="submit" class="btn btn_sele" ><jecs:locale  key='operation.button.search'/></button>
						</td>
		--%>
					</tr>
				</table>
			</div>
			<ec:table tableId="jpmCouponInfoListTable" items="jpmCouponInfoList"
				var="jpmCouponInfo"
				action="${pageContext.request.contextPath}/jpmCouponInfos.html"
				width="100%" form="frm" retrieveRowsCallback="limit"
				rowsDisplayed="20" sortable="true"
				imagePath="./images/extremetable/*.gif">
				<ec:row>
					<ec:column property="edit" title="button.select"
						styleClass="centerColumn" viewsAllowed="html" sortable="false">
						<img src="<c:url value='/images/newIcons/tick_16.gif'/>"
							onclick="javascript:selectCouponInfo('${jpmCouponInfo.cpId}','${jpmCouponInfo.cpName}')"
							style="cursor: pointer;" title="<jecs:locale key="button.select"/>" />
					</ec:column>
					<ec:column property="cpName" title="jpmCouponInfo.name" />
					<ec:column property="cpValue" title="jpmCouponInfo.price" />
					<ec:column property="startTime" title="有效起始时间" />
					<ec:column property="endTime" title="有效截止时间" />
				</ec:row>
			</ec:table>
		</form>
	

 

    <script type="text/javascript">
      
      function selectCouponInfo(cpId,cpName){
    	  //回传选中的代金券id
    	  window.opener.document.getElementById("cpId").value=cpId;
    	  
    	  //将选中的代金券名称写到父页面
    	  window.opener.document.getElementById("cpName").value=cpName;
    	  this.close();
      }
    </script>


