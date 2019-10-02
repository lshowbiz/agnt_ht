<%@ page language="java" errorPage="/error.jsp" contentType="text/html; charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>


<title><jecs:locale key="jmiAddrBookDetail.title"/></title>
<content tag="heading"><jecs:locale key="jmiAddrBookDetail.heading"/></content>

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

<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td>
    	<div style="float:left; background:url(images/joinUS/logo.jpg);width:354px;height:76px;" title="Joymain international" /></div>
    	<div style="float:right;padding:27px 27px 0 0;">语言选择:<img src="images/joinUS/regTopIco.jpg" width="33" height="26" align="absmiddle" alt="" />
        <select name="characterCode" onchange="location.href='?lang='+this.value">
			<option value="ar_SA">Arabic</option>
			<option value="bg_BG">Български</option>
			<option value="de_DE">Deutsch</option>
			<option value="en_AU">English (Australia)</option>
			<option value="en_GB" selected>English-GB</option>
			<option value="en_US">English-US</option>
			<option value="es_ES">Español</option>
			<option value="es_PE">Español (Perú)</option>
			<option value="es_US">Español (United States)</option>
			<option value="fr_FR">Français (France)</option>
			<option value="hi_IN">हिन्दी(India)</option>
			<option value="ja_JP">日本語</option>
			<option value="ko_KR">한국어(Korea)</option>
			<option value="lt_LT">Lietuvių(Lithuania)</option>
			<option value="ms_MY">Bahasa Melayu(Malay)</option>
			<option value="nl_NL">Nederlands</option>
			<option value="pl_PL">Polski(Poland)</option>
			<option value="pt_BR">pt_BR</option>
			<option value="pt_PT">Português(Portugal)</option>
			<option value="ru_RU">Русский язык(Russia)</option>
			<option value="th_TH">ภาษาไทย(Thailand)</option>
			<option value="vi_VN">Việt(Vietnam)</option>
			<option value="zh_CN">简体中文</option>
			<option value="zh_TW">繁體中文</option>
        
        </select>
        </div>
    </td>
  </tr>
  <tr>
    <td style="background:url(images/joinUS/regMenuBg.jpg); height:36px; padding:0 12px 0 12px" nowrap>
    	<img src="images/joinUS/regMenuIcon.jpg" border="0" align="absmiddle" style="float:left;" />
        <p style="color:#360;margin:10px 0 0 0;">A.选择注册公司及填写推荐者编号 &gt;&gt; B.填写基本数据 &gt;&gt; <span style="color:#fff">C.阅读会员条款</span> &gt;&gt; D.取得会员编号及完成注册</p>
    </td>
  </tr>
  <tr>
    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td valign="top" style="padding:4px 4px 4px 9px;">
        <table width="100%" border="0" cellspacing="0" cellpadding="0" style="background:url(images/joinUS/regCententTitleBg.jpg);">
          <tr>
            <td width="56" height="56" align="right"><img src="images/joinUS/regCententTitleIco3.jpg" width="56" height="56" alt="" /></td>
            <td style="font-size:16px; font-family:Verdana, Geneva, sans-serif; font-weight:bold; padding:0 0 0 12px; color:#333">阅读会员条款</td>
            <td align="right" width="6"><img src="images/joinUS/regCententTitleRight.jpg" width="6" height="56" alt="" /></td>
          </tr>
        </table>
        <form:form method="post">
        <table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td valign="middle" align="center">
            	<!--centent start-->
                <table width="100%" border="0" cellspacing="2" cellpadding="2">
                <tr>
                  <td nowrap>
                  	<div style="width:95%;height:100%; border:1px solid #ddd; padding:12px; margin:12px;">
                    	条款内容部分..........
                    </div>
                  </td>
                </tr>
                <tr>
                  <td align="center" nowrap style="border-top:2px solid #99cc00; background:#f0f0f0; height:32px;"><input name="" type="checkbox" value="" align="absmiddle" />
                  同意以上条款</td>
                </tr>
                <tr>
                  <td align="center" nowrap>
                  	<button class="regButton" onmouseover="this.className='regButtonOver'" onmouseout="this.className='regButton'" 
                    	type="button" ondblclick="window.location.href='./'" value="" name=""><img src="images/joinUS/icon/Delete.gif" border="0" align="absmiddle" />取消</button>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <button class="regButton" onmouseover="this.className='regButtonOver'" onmouseout="this.className='regButton'" 
                    	type="submit" onclick="javascript:$('_target0').name='_target1';" ><img src="images/joinUS/icon/arrowLeft.gif" border="0" align="absmiddle" />上一步</button>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <button class="regButton" onmouseover="this.className='regButtonOver'" onmouseout="this.className='regButton'" 
                    	type="submit" onclick="javascript:$('_target0').name='_target3';" ><img src="images/joinUS/icon/arrowRight.gif" border="0" align="absmiddle" /><b>下一步</b></button>                  </td>
                    	<input type="hidden" id="_target0">
                  </tr>
                </table>
            	<!--centent end-->
            </td>
          </tr>
        </table>
</form:form>
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

