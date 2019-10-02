<%@ page pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<style>
	#subject, #url {
		width: 370px;
	}
</style>

<title><jecs:locale key="amNewDetail.title"/></title>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<link rel="stylesheet" href="${ctx }/styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="${ctx }/scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="${ctx }/scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="${ctx }/scripts/calendar/lang.jsp"> </script> 
<content tag="heading"><jecs:locale key="amNewDetail.heading"/></content>

<%-- 
<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" onclick="bCancel=false" value="保存" />
		</jecs:power>
	<c:if test="${param.strAction eq 'editAmNew'}">
		<jecs:power powerCode="deleteAmNew">
				<input type="submit" class="button" onclick="bCancel=true;return confirmDelete(this.form,'AmNew')" value="删除" />
		</jecs:power>
	</c:if>
	<input type="button" class="button" onclick="history.back();" value="返回" />
</c:set>
--%>
<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<button type="submit" class="btn btn_sele" onclick="bCancel=false">保存</button>
		</jecs:power>
	<c:if test="${param.strAction eq 'editAmNew'}">
		<jecs:power powerCode="deleteAmNew">
				<button type="submit" class="btn btn_sele" onclick="bCancel=true;return confirmDelete(this.form,'AmNew')">删除</button>
		</jecs:power>
	</c:if>
	<button type="button" class="btn btn_sele" onclick="history.back();">返回</button>
</c:set>


<spring:bind path="amNew.*">
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

<form:form commandName="amNew" method="post" action="editAmNew.html" onsubmit="return validateAmNew(this)" id="amNewForm">
<%-- 
<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
--%>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('AmNew')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
<table class='detail' width="70%">
	<tbody class="window">
		<form:hidden path="newId"/>
		
		    <tr class="edit_tr">
			    <th>
			        	<span class="text-font-style-tc">类别</span>
			    </th>
			    <td>
			        <!--form:errors path="category" cssClass="fieldError"/-->
			        <%-- <form:input path="category" id="category" cssClass="text medium"/> --%>
			        <span class="textbox"><jecs:list styleClass="textbox-text" name="category" listCode="news_category" defaultValue="" value="${amNew.category}"></jecs:list></span>
			    </td>
			    
			    <th>
			       		<span class="text-font-style-tc">主题</span>
			    </th>
			    <td>
			        <!--form:errors path="subject" cssClass="fieldError"/-->
			        <span class="textbox"><form:input path="subject" id="subject" cssClass="textbox-text" /></span>
			    </td>
		    </tr>
		
		    <tr class="edit_tr">
			    <th>
			        <span class="text-font-style-tc">url</span>
			    </th>
			    <td>
			        <!--form:errors path="url" cssClass="fieldError"/-->
			        <span class="textbox"><form:input path="url" id="url" cssClass="textbox-text" /></span>
			    </td>
		    
			    <th>
			        <span class="text-font-style-tc">排序</span>
			    </th>
			    <td>
			        <!--form:errors path="newOrder" cssClass="fieldError"/-->
			        <span class="textbox"><form:input path="newOrder" id="newOrder" cssClass="textbox-text"/></span>
			    </td>
			    </tr>
		    <tr class="edit_tr">
		    <th>
		       		<span class="text-font-style-tc">创建时间</span>
		    </th>
		    <td>
		    <%-- 
		        <!--form:errors path="newOrder" cssClass="fieldError"/-->
		        --%>
		        <%-- <form:input path="createTime" id="createTime" cssClass="text medium" readonly="true"/> --%>
		        
		        <span class="textbox"><input name="createTime" id="createTimeStart"  class="textbox-text"
		        value="<fmt:formatDate pattern='yyyy-MM-dd HH:mm' value='${amNew.createTime}' />"
		        onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',readOnly:true})"></span>
		        
		        <%-- 
		        <span class="textbox"><input name="createTime" id="createTimeStart"  class="textbox-text"
		        value="<fmt:formatDate pattern='yyyy-MM-dd HH:mm' value='${amNew.createTime}' />"
		        readonly="readonly"></span>
		      	<img src="${ctx}/images/calendar/calendar7.gif" id="img_startTime" style="cursor: pointer;" title="<jecs:locale key="Calendar.TT.SEL_DATE"/>"/> 
				<script type="text/javascript"> 
					Calendar.setup({
					inputField     :    "createTimeStart", 
					ifFormat       :    "%Y-%m-%d %H:%M",  
					daFormat       :    "%Y-%m-%d %H:%M",  
					button         :    "img_startTime", 
					singleClick    :    true,
					showsTime    :    true,
					timeFormat    :    "24"
					}); 
				</script>
				--%>
		    </td>
		    </tr>
		    
		    
		    
		    <tr>		
	    <td class="command" colspan="4" align="center">
	    	<c:out value="${buttons}" escapeXml="false"/>
	    </td>
    </tr>
	</tbody>
</table>
<!--/fieldset-->

<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('AmNew')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('amNewForm'));
</script>

<%-- <v:javascript formName="amNew" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script> --%>
