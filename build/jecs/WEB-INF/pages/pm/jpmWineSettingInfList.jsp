<%@page import="com.joymain.jecs.webapp.util.SessionLogin"%>
<%@page import="com.joymain.jecs.webapp.util.ConfigUtil"%>
<%@page import="com.joymain.jecs.Constants"%>
<%@page import="com.joymain.jecs.pm.model.*"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html; charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" src="<c:url value='/dwr/util.js'/>"></script>
<script type="text/javascript" src="<c:url value='/dwr/engine.js'/>"></script>
<script type="text/javascript" src="<c:url value='/dwr/interface/jpmWineSettingInfManager.js'/>"></script>
<title><jecs:locale key="jpmWineSettingInfList.title" /></title>
<content tag="heading">
<jecs:locale key="jpmWineSettingInfList.heading" /></content>
<meta name="menu" content="JpmWineSettingInfMenu" />

<ec:table tableId="jpmWineSettingInfListTable" items="jpmWineSettingInfList" var="jpmWineSettingInf"
  action="${pageContext.request.contextPath}/jpmWineSettingInfs.html" width="100%" retrieveRowsCallback="limit"
  rowsDisplayed="20" sortable="true" imagePath="/jecs/images/extremetable/*.gif">
  <ec:row>
    <ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
      <c:if test="${jpmWineSettingInf.status ne 0}">
        <img src="<c:url value='/images/icons/editIcon.gif'/>"
          onclick="javascript:editJpmWineSettingInf('${jpmWineSettingInf.settingId}')" style="cursor: pointer;"
          title="<jecs:locale key="button.edit"/>" />
      </c:if>
    </ec:column>
    <ec:column property="productId" title="jpmWineSettingInf.productId" />
    <ec:column property="productName" title="jpmWineSettingInf.productName" />
    <ec:column property="productQty" title="jpmWineSettingInf.productQty" />
    <ec:column property="unitNo" title="jpmWineSettingInf.unitNo" />
    <ec:column property="status" title="jpmWineSettingInf.status">
      <jecs:code listCode="jpmwinesettinginf.status" value="${jpmWineSettingInf.status}" />
    </ec:column>
  </ec:row>

</ec:table>
<!-- <input type="button" value="add" onClick="add();" /> -->
<script type="text/javascript">
	function editJpmWineSettingInf(settingId) {
		jpmWineSettingInfManager.rePushSettingToERP(settingId, function(res) {
			if ("0" == res) {
				alert("重新发送到ERP成功");
				window.location = "jpmWineSettingInfs.html";
			} else {
				alert("重新发送到ERP失败");
			}
		});
	}
/* 	function add() {
		window.location = "jpmWineSettingInfs.html?test=add";
	} */
/* 	if ('${param.test}' == 'add') {
		window.location = "jpmWineSettingInfs.html";
	} */
</script>
<%-- <%
    if ("add".equals(request.getParameter("test"))) {
        new com.joymain.jecs.util.wine.WineInterfaceUtil().saveAndSendSettingBill(testInit(), 0);
    }
%>
<%!private JpmWineSettingInf testInit() {
        JpmWineSettingInf o = new JpmWineSettingInf();
        for (int i = 0; i < 3; i++) {
            JpmWineSettingListInf oc = new JpmWineSettingListInf();
            oc.setMaterialNo("材料号" + i);
            oc.setMemo("测试一下而已" + i);
            oc.setQty(25);
            oc.setSdate("2013-12-13");
            oc.setEdate("2013-12-25");
            oc.setJpmWineSettingListInf(o);
            o.getJpmWineSettingListInfSet().add(oc);
        }
        o.setProductId(123456L);
        o.setProductName("productName");
        o.setProductQty(25);
        o.setResultcode(12);
        o.setResultdescription("应该不会保存到表里面去的");
        o.setStatus(12);
        o.setUnitNo("unitNo");

        return o;
    }%> --%>
