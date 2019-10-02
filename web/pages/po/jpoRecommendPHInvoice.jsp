<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
</head>
<style media="print">
	.noPrint { 
		display: none;
	}
</style>
<body>


<DIV STYLE="position: absolute; top: 0mm; left: 245mm; font: 9pt @Times New Roman; color: #000020; ">${jpoMemberOrder.memberOrderNo }</DIV>
<DIV STYLE="position: absolute; top: 31mm; left: 50mm; font: 9pt @Times New Roman; color: #000020; "><fmt:formatDate value="${curDate }" pattern="dd/MM/yyyy"/></DIV>
<DIV STYLE="position: absolute; top: 48mm; left: 100mm; font: 9pt @Times New Roman; color: #000020; ">${jpoMemberOrder.sysUser.userName }</DIV>
<DIV STYLE="position: absolute; top: 48mm; left: 272mm; font: 9pt @Times New Roman; color: #000020; ">${jpoMemberOrder.sysUser.userCode }</DIV>
<DIV STYLE="position: absolute; top: 57mm; left: 100mm; font: 9pt @Times New Roman; color: #000020; "><fmt:formatNumber value="${jpoMemberOrder.pvAmt*50/7 }" type="number" pattern="###,###,##0.00"/></DIV>

<DIV STYLE="position: absolute; top: 65mm; left: 70mm; font: 9pt @Times New Roman; color: #000020; ">${jpoMemberOrder.sysUser.jmiMember.jmiRecommendRef.recommendJmiMember.miUserName }</DIV>
<DIV STYLE="position: absolute; top: 65mm; left: 272mm; font: 9pt @Times New Roman; color: #000020; ">${jpoMemberOrder.sysUser.jmiMember.recommendNo }</DIV>


<DIV id="print" class="noPrint"><input type="button" value="<jecs:locale key="button.print"/>" onclick="window.print();"></DIV>
</body>
</html>
<script language="javascript">
window.print();
</script>