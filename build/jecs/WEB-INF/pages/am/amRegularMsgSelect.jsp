<%@ include file="/common/taglibs.jsp"%>
<c:set var="requestMap" value="${requestMap}" scope="session"/>
<c:set var="amRegularMsgListTable_totalRows" value="${amRegularMsgListTable_totalRows}" scope="session"/>
<c:set var="amRegularMsgList" value="${amRegularMsgList}" scope="session"/>

<frameset rows="0,*">

<frame name="amRegularMsgSelect" src="about:blank" />
<frame name="amRegularMsgSelectMain" scrolling="yes" noresize target="_self" src="common/amRegularMsgList.jsp" />

</frameset>


