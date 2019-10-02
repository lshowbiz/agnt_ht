<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE HTML>
<%@ include file="/common/taglibs.jsp"%>
<html>
    <head>
    	<meta name="viewport" content="width=device-width, initial-scale=1"> 
        <%@ include file="/common/mobile/meta.jsp" %>
		<link href="<c:url value='/images/favicon.ico'/>" rel="shortcut icon" />
		<link rel="stylesheet" href="${ctx}/scripts/jquerymobile/jquery.mobile-1.0.min.css" />
		<link rel="stylesheet" href="${ctx}/styles/m-style.css" />
		<script src="${ctx}/scripts/jquerymobile/jquery-1.7.1.min.js"></script>
		<script src="${ctx}/scripts/jquerymobile/jquery.mobile-1.0.min.js"></script>
		<script type="text/javascript">
			var confirmDeleteMsg="<jecs:locale key="confirm.delete"/>";
			function openDetail(el){
				el.slideDown(1000);
			} 
		</script>		 
        <decorator:head/>
		<title>Joy Life Back Office</title>
    </head>
	<body<decorator:getProperty property="body.id" writeEntireProperty="true"/><decorator:getProperty property="body.class" writeEntireProperty="true"/>>
		
		<div data-role="page" style="width: 320px;">
		<div data-role="header" data-position="fixed">
		<jsp:include page="/common/mobile/header.jsp"/>
		</div>
		<div data-role="content" style="text-align: center; font-size: 16px; padding: 5px 0;">	
	
		<div data-role="content" style="text-align: center; font-size: 16px; padding: 0;">	
			<div data-position="fixed" style="text-align: center;">
				<a href="amMessages.html?strAction=agentAmMessage" style="width: 130px"
				data-role="button" data-theme="c" data-inline="true">信息发送</a>
			</div>
			
			<div data-position="fixed" style="text-align: center;">
				<a href="memberCreate.html"  style="width: 130px"
				data-role="button" rel="external" data-theme="c" data-inline="true">会员注册</a>
			</div>
			
			<div data-position="fixed" style="text-align: center;">
				<a href="bdSendRecords.html"  rel="external" style="width: 130px"
				 data-role="button" data-theme="c" data-inline="true">奖金查询</a>
			</div>
			
			<div data-position="fixed" style="text-align: center;">
				<a href="miRecommendRefs.html" rel="external" style="width: 130px"
				 data-role="button" data-theme="c" data-inline="true">推荐网络</a>
			</div>
			<div data-position="fixed" style="text-align: center;">
				<a href="miLinkRefs.html"  rel="external"  style="width: 130px"
				 data-role="button" data-theme="c" data-inline="true">安置网络</a>
			</div>
			<div data-position="fixed" style="text-align: center;">
				<a href="editJmiMemberProfile.html" style="width: 130px"
				 data-role="button"  rel="external"  data-theme="c" data-inline="true">个人设置</a>
			</div>
			
			<div data-position="fixed" style="text-align: center;">
				 <a href="${ctx}/jpoMemberOrderManages.html" style="width: 130px"
				 data-role="button" data-theme="c" data-inline="true" rel="external">订单管理</a>
			</div>
			
			
		</div>
		</div>
		<%--<div data-role="footer" data-position="fixed" style="text-align: center;">
		<jsp:include page="/common/mobile/footer.jsp"/>
		</div>--%>
		</div>
		
</body>
</html>




















