<!DOCTYPE HTML>
<%@ include file="/common/taglibs.jsp"%>
<html>
    <head>
        <%@ include file="/common/mobile/meta.jsp" %>
        
		<link href="<c:url value='/images/favicon.ico'/>" rel="shortcut icon" />
		<link rel="stylesheet" href="${ctx}/scripts/jquerymobile/jquery.mobile-1.0.min.css" />
		<link rel="stylesheet" href="${ctx}/styles/m-style.css" />
		<script src="${ctx}/scripts/global.js"></script>
		<script src="${ctx}/scripts/jquerymobile/jquery-1.7.1.min.js"></script>
		<!-- <script>
			$(document).bind('mobileinit',function(){
			   $.mobile.selectmenu.prototype.options.nativeMenu = false;
			});
		</script> -->
		<script src="${ctx}/scripts/jquerymobile/jquery.mobile-1.0.min.js"></script>
		<script type="text/javascript">
			var confirmDeleteMsg="<jmos:locale key='confirm.delete'/>";
			function openDetail(el){
				el.slideDown(1000);
			} 
		</script>	
		<script src="${ctx}/scripts/mobile/validateJpoMember.js"></script>	 
        <decorator:head/>
		<title>ÓÃ»§µÇÂ¼</title>
    </head>
	<body<decorator:getProperty property="body.id" writeEntireProperty="true"/><decorator:getProperty property="body.class" writeEntireProperty="true"/>>
		
		<div data-role="page" style="width: 320px;">
		<div data-role="header" data-position="fixed">
		<jsp:include page="/common/mobile/header.jsp"/>
		</div>
		<div data-role="content" style="text-align: center; font-size: 16px; padding: 5px 0;">	
		<decorator:body/>
		</div>
		<%--<div data-role="footer" data-position="fixed" style="text-align: center;">
		<jsp:include page="/common/mobile/footer.jsp"/>
		</div>--%>
		</div>
		
</body>
</html>
