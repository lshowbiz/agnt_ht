<%@page import="java.util.Random"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html; charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ page import="com.joymain.jecs.pm.model.*,com.joymain.jecs.util.wine.*" %>
<script type="text/javascript" src="<c:url value='/dwr/util.js'/>"></script>
<script type="text/javascript" src="<c:url value='/dwr/engine.js'/>"></script>
<script type="text/javascript" src="<c:url value='/dwr/interface/jpmWineOrderInterfaceManager.js'/>"></script>
<title><jecs:locale key="jpmWineOrderInterfaceList.title" /></title>
<content tag="heading">
<jecs:locale key="jpmWineOrderInterfaceList.heading" /></content>
<meta name="menu" content="JpmWineOrderInterfaceMenu" />

<ec:table tableId="jpmWineOrderInterfaceListTable" items="jpmWineOrderInterfaceList" var="jpmWineOrderInterface"
  action="${pageContext.request.contextPath}/jpmWineOrderInterfaces.html" width="100%" retrieveRowsCallback="limit"
  rowsDisplayed="20" sortable="true" imagePath="/jecs/images/extremetable/*.gif">
  <ec:row>
    <ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
    <c:if test = "${jpmWineOrderInterface.status ne 0}">
      <img src="<c:url value='/images/icons/editIcon.gif'/>"
        onclick="javascript:editJpmWineOrderInterface('${jpmWineOrderInterface.moId}')" style="cursor: pointer;"
        title="<jecs:locale key="button.edit"/>" />
    </c:if>
    </ec:column>
    <ec:column property="memberOrderNo" title="jpmWineOrderInterface.memberOrderNo" />
    <ec:column property="billCode" title="jpmWineOrderInterface.billCode" />
    <ec:column property="dbillDate" title="jpmWineOrderInterface.dbillDate" />
    <ec:column property="userCode" title="jpmWineOrderInterface.userCode" />
    <ec:column property="membername" title="jpmWineOrderInterface.membername" />
    <ec:column property="status" title="jpmWineOrderInterface.status" >
      <jecs:code listCode="jpmwineorderinterface.status" value="${jpmWineOrderInterface.status}"/>
    </ec:column>
  </ec:row>

</ec:table>
<!-- <input type="button" value="add" onClick="add();"/> -->
<script type="text/javascript">
	function editJpmWineOrderInterface(moId) {
	jpmWineOrderInterfaceManager.rePushOrderToERP(moId,
				function(res) {
					if("0"==res){
						alert("重新发送到ERP成功");
						window.location="jpmWineOrderInterfaces.html";
					}else{
						alert("重新发送到ERP失败");
					}
				});
	}
	/* function add(){
		window.location="jpmWineOrderInterfaces.html?test=add";
	}
	if('${param.test}'=='add'){
		window.location="jpmWineOrderInterfaces.html";
	} */
</script>
<%-- <%
if("add".equals(request.getParameter("test"))){
    JpmWineOrderInterface o = testInit();
    new com.joymain.jecs.util.wine.WineInterfaceUtil().saveAndSendOrder(o, 0);
    
}
%>
<%!
private JpmWineOrderInterface testInit() {
    JpmWineOrderInterface o = new JpmWineOrderInterface();
    for (int i = 0; i < 3; i++) {
        JpmWineOrderListInterface oc = new JpmWineOrderListInterface();
        oc.setPrice(12.2d);
        if (i == 0) {
            oc.setProductId("t3010101");
        } else if (i == 1) {
            oc.setProductId("p2010101");
        } else {
            oc.setProductId("CN923231870001");
        }
        oc.setQty(15);
        oc.setJpmWineOrderInterface(o);
        o.getJpmWineOrderListInterfaceSet().add(oc);
    }

    o.setMemberOrderNo("bookId" + new Random().nextInt());
    o.setResultCode(1);
    o.setResultDescription("32");
    o.setStatus(0);
    o.setUserCode("CN92323187");
    return o;
}
%> --%>
