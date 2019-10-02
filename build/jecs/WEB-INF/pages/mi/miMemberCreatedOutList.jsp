<%@ page language="java" errorPage="/error.jsp" contentType="text/html; charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>JOYMAIN INTERNATIONAL</title>
<style type="text/css">
<!--
	body {margin:0; font-family:Verdana, Geneva, sans-serif; font-size:13px;}
	img {border:0; vertical-align:middle;}
	button { cursor:pointer; vertical-align:middle; color:#090;}
	.regButton { background:url(images/joinUS/regButtonBg.jpg) center center; border:1px double #dddddd; padding:4px 12px 2px 12px;}
	.regButtonOver { background:url(images/joinUS/regButtonBgOver.jpg) center center;border:none; border:1px double #dddddd; padding:4px 12px 2px 12px;}
	.register {margin:0;padding:4px; border-collapse:collapse;}
	.register .required { font-weight:bold; color:#060; font-size:13px;}
	.register .title { background:#f0f0f0; border-bottom:2px solid #9c0;padding:6px 2px 2px 2px; font-size:15px; color:#060; font-weight:bold;}
	.register th {font-weight:normal; color:#999; border-bottom:1px solid #f0f0f0; padding-left:32px; text-align:right;}
	.register td { border-bottom:1px solid #f0f0f0;padding:2px; text-align:left; width:50%}
-->
</style>
</head>

<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td>
    	<div style="float:left; background:url(images/joinUS/logo.jpg);width:354px;height:76px;" title="Joymain international" /></div>
    	<div style="float:right;padding:27px 27px 0 0;">语言选择:<img src="images/joinUS/regTopIco.jpg" width="33" height="26" align="absmiddle" alt="" />
       <select name="characterCode" id="characterCode" onchange="location.href='?lang='+this.value">
        <c:forEach items="${alCharacterCodings}" var="alCharacterCodingVar">
			<option value="${alCharacterCodingVar.characterCode }"<c:if test="${alCharacterCodingVar.characterCode==sessionScope.sessionLogin.defCharacterCoding }"> selected</c:if>>${alCharacterCodingVar.characterName }</option>
        </c:forEach>
        </select>
        </div>
    </td>
  </tr>
  <tr>
    <td style="background:url(images/joinUS/regMenuBg.jpg); height:36px; padding:0 12px 0 12px" nowrap>
    	<img src="images/joinUS/regMenuIcon.jpg" border="0" align="absmiddle" style="float:left;" />
        <p style="color:#360;margin:10px 0 0 0;">A.选择注册公司及填写推荐者编号 &gt;&gt;B.填写基本数据 &gt;&gt;  <span style="color:#fff">C.取得会员编号及完成</span> </p>
    </td>
  </tr>
  <tr>
    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td valign="top" style="padding:4px 4px 4px 9px;">
        <table width="100%" border="0" cellspacing="0" cellpadding="0" style="background:url(images/joinUS/regCententTitleBg.jpg);">
          <tr>
            <td width="56" height="56" align="right"><img src="images/joinUS/regCententTitleIco4.jpg" width="56" height="56" alt="" /></td>
            <td style="font-size:16px; font-family:Verdana, Geneva, sans-serif; font-weight:bold; padding:0 0 0 12px; color:#333">注册完成</td>
            <td align="right" width="6"><img src="images/joinUS/regCententTitleRight.jpg" width="6" height="56" alt="" /></td>
          </tr>
        </table>
        <table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td valign="middle" align="center">
            	<!--centent start-->
                <table width="100%" border="0" cellspacing="2" cellpadding="2">
                <tr>
                  <td nowrap>
                      <table width="100%" border="0" align="center" class="register">
                        <tr>
                            <td colspan="2" class="title">&nbsp;</td>
                        </tr>
                        <tr>
                            <th class="required"><jecs:title key="alCompany.regName"/></th>
                            <td nowrap="nowrap">
                            <jecs:company name="companyCode" selected="${resJmiMember.companyCode }" label="true"  withAA="false"  />	
							 </td>
                        </tr>
                        <tr>
                            <th class="required"><jecs:title key="miMember.miRecommendRef"/></th>
                            <td nowrap="nowrap">
                                ${resJmiMember.recommendNo}
                            </td>
                        </tr>
                        <tr>
                            <th class="required"><jecs:title key="bdSendRecord.cardType"/></th>
                            <td nowrap="nowrap">
                                <jecs:code listCode="bd.cardtype" value="${resJmiMember.cardType}"/>
                            </td>
                        </tr>
                        <tr>
                            <th class="required"><jecs:title key="miMember.memberNo"/></th>
                            <td nowrap="nowrap">
                               <font size="3" color="red">${resJmiMember.userCode }</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<jecs:locale key="operation.notice.MarkMemberNo"/> 
                            </td>
                        </tr>
                      
                        <tr>
                            <th class="required"><jecs:title key="memberCreate.tips"/></th>
                            <td nowrap="nowrap">
                               <font size="3" color="red"><jecs:locale key="memberCreate.tips.detail"/> </font>
                            </td>
                        </tr>
						
                        <tr>
                            <td colspan="2"><hr size="1"/></td>
                        </tr>
                    </table>
                  </td>
                </tr>
                <tr>
                  <td align="center" nowrap>
                    <button class="regButton" onmouseover="this.className='regButtonOver'" onmouseout="this.className='regButton'" 
                    	type="submit" onclick="window.location.href='login.html'" value="" name=""><img src="images/joinUS/icon/arrowRight.gif" border="0" align="absmiddle" /><b>完成</b></button>
                  </td>
                 </tr>
                </table>
            	<!--centent end-->
            </td>
          </tr>
        </table>

        </td>
        
      </tr>
    </table></td>
  </tr>
  <tr>
    <td>
    <table width="100%" border="0" cellspacing="0" cellpadding="0" style="background:url(images/joinUS/regFooterBg.jpg);">
      <tr>
        <td align="left"><img src="images/joinUS/regFooterLeft.jpg" width="7" height="32" alt="" /></td>
        <td width="100%" align="center">Copyright &copy; 2009 Joymain International. All rights reserved.</td>
        <td align="right"><img src="images/joinUS/regFooterRight.jpg" width="8" height="32" alt="" /></td>
      </tr>
    </table>
    </td>
  </tr>
</table>
</body>
</html>
