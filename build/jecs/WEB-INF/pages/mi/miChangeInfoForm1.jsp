<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=utf-8" language="java" %>

<title><jecs:locale key="jmiBlacklistDetail.title"/></title>
<content tag="heading"><jecs:locale key="jmiBlacklistDetail.heading"/></content>

<c:set var="buttons">

				<input type="submit" name="_target1" value="下一步" class="button">
</c:set>

<spring:bind path="jmiMemberList.*">
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
${filemsg }
<c:forEach items="${ messages }" var="curVar">
	${curVar } <br/>
</c:forEach>


<form:form commandName="jmiMemberList" method="post" action="miChangeInfo.html" id="miChangeInfoForm" enctype="multipart/form-data">

<div class="searchBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
<input type="hidden" name="strAction" value="${param.strAction }"/>


<table class='detail' width="100%">


    <tr><th>
       请选择需要上传的文件：
    </th>
    <td align="left">
       <input type="file" name="file">
    </td></tr>

    <tr><th>
       修改类型
    </th>
    <td align="left">
       <select id="modifyType" name="modifyType"  >
       		<option value="">--请选择--</option>
       		<jecs:power powerCode="modifyMemberLevel">
       			<option value="modifyMemberLevel">修改会员级别</option>
       		</jecs:power>
<%--        		
       		<jecs:power powerCode="modifyActive">
       			<option value="modifyActive">修改会员死点状态</option>
       		</jecs:power>
       		
       		<jecs:power powerCode="modifyFreeze">
       			<option value="modifyFreeze">修改会员冻结状态</option>
       		</jecs:power>
       		
       		<jecs:power powerCode="modifyState">
       			<option value="modifyState">修改会员登陆状态</option>
       		</jecs:power>
       		
       		<jecs:power powerCode="modifyRemark">
       			<option value="modifyRemark">修改会员备注</option>
       		</jecs:power>
       		
       		<jecs:power powerCode="modifyIsstore">
       		     <option value="modifyIsstore">修改生活馆类型</option>
       		</jecs:power>
       		 --%>
       </select>
    </td></tr>

<tr><th>
        <jecs:label  key="busi.common.remark"/>
    </th>
    <td align="left">
        所导入的文件格式说明:<br/>
		1.第一行为抬头，不导入<br/>
		2.第1列为： 会员编号<br/>
    </td></tr>
</table>

</form:form>

