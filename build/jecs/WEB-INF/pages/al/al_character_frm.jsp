<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><jecs:locale key="menu.al.alLanguageManagement"/></title>
</head>

<frameset cols="150,*">
	<frame src="../alCharacterTypes.html" name="frmCharacterType" frameborder="0"/>
	<frameset rows="340,*">
		<frame src="../alCharacterKeys.html?strAction=listAlCharacterKeys" name="frmCharacterKey" frameborder="0" scrolling="auto" marginwidth="0"/>
		<frame src="../editAlCharacterValue.html" name="frmCharacterValue" frameborder="1" scrolling="auto" marginwidth="0" marginheight="0"/>
	</frameset>
</frameset>

<noframes>
<body>
</body>
</noframes>

</html>