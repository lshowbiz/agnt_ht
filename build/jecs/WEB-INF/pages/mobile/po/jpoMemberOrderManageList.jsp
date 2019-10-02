<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML>
<html>
	<head>
	    <meta name="viewport" content="width=device-width, initial-scale=1"> 
	    <meta name="decorator" content="mobile"/>
	</head>

	<body>
		<div data-role="content" style="text-align: center; font-size: 16px; padding: 0;">	
			<div data-position="fixed" style="text-align: center;">
				<a href="${ctx}/jpoMemberFOrders.html" style="width: 140px"
				 data-role="button" data-theme="c" data-inline="true" rel="external">会员首购单</a>
				<a href="${ctx}/jpoMemberUOrders.html" style="width: 140px"
				data-role="button" data-theme="c" data-inline="true">会员升级单</a>
			</div>
			
			<div data-position="fixed" style="text-align: center;">
				<a href="${ctx}/jpoMemberROrders.html"  style="width: 140px"
				data-role="button" data-theme="c" data-inline="true">会员重消单</a>
				<a href="${ctx}/jpoMemberSFOrders.html" style="width: 140px"
				 data-role="button" data-theme="c" data-inline="true">一级店首购</a>
			</div>
			
			<div data-position="fixed" style="text-align: center;">
				<a href="${ctx}/jpoMemberSROrders.html" style="width: 140px"
				 data-role="button" data-theme="c" data-inline="true">一级店重消</a>
				<a href="${ctx}/jpoMemberSSubFOrders.html" style="width: 140px"
				 data-role="button" data-theme="c" data-inline="true">二级店首购</a>
			</div>
			
			<div data-position="fixed" style="text-align: center;">
				<a href="${ctx}/jpoMemberSSubUOrders.html" style="width: 140px"
				 data-role="button" data-theme="c" data-inline="true">二级店升级</a>
				<a href="${ctx}/jpoMemberSSubROrders.html" style="width: 140px"
				 data-role="button" data-theme="c" data-inline="true">二级店重消</a>
			</div>
			
			<div data-position="fixed" style="text-align: center;">
				<a href="${ctx}/jpoMemberAOrders.html" style="width: 140px"
				 data-role="button" data-theme="c" data-inline="true">辅消品</a>
				<a href="${ctx}/jpoMemberRSOrders.html" style="width: 140px"
				 data-role="button" data-theme="c" data-inline="true">续约单</a>
			</div>
			
		</div>
	</body>
</html>