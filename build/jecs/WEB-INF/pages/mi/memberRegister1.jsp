
<%@ include file="/common/taglibs.jsp"%>


<title><jecs:locale key="jmiAddrBookDetail.title"/></title>
<content tag="heading"><jecs:locale key="jmiAddrBookDetail.heading"/></content>


<style type="text/css">
<!--
	body {margin:0; font-family:Verdana, Geneva, sans-serif; font-size:13px;}
	img {border:0; vertical-align:middle;}
	button { cursor:pointer; vertical-align:middle; height:24px; color:#090;}
	button img { border-right:1px solid #eee; padding-right:4px; margin-right:4px;}
	.regButton { background:url(images/joinUS/regButtonBg.jpg) center center; border:1px double #dddddd; padding:4px 12px 2px 12px;}
	.regButtonOver { background:url(images/joinUS/regButtonBgOver.jpg) center center;border:none; border:1px double #dddddd; padding:5px 12px 2px 12px;}
-->
</style>

 <form:form commandName="jmiMember" action="memberRegister.html" method="post">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td>
    	<div style="float:left; background:url(images/joinUS/logo.jpg);width:354px;height:76px;" title="Joymain international" /></div>
    	<div style="float:right;padding:27px 27px 0 0;"> <jecs:label key="register.language.select" /><img src="images/joinUS/regTopIco.jpg" width="33" height="26" align="absmiddle" alt="" />
    	
       
       <select name="characterCode" onchange="javascript:$('_target0').name='_target0';$('lang').value=this.value;this.form.submit();">
        <c:forEach items="${alCharacterCodings}" var="alCharacterCodingVar">
			<option value="${alCharacterCodingVar.characterCode }"<c:if test="${alCharacterCodingVar.characterCode==sessionScope.sessionLogin.defCharacterCoding }"> selected</c:if>>${alCharacterCodingVar.characterName }</option>
        </c:forEach>
        </select>
        <input id="lang" name="lang" value="" type="hidden">
        </div>
    </td>
  </tr>
  <tr>
    <td style="background:url(images/joinUS/regMenuBg.jpg); height:36px; padding:0 12px 0 12px" nowrap>
    	<img src="images/joinUS/regMenuIcon.jpg" border="0" align="absmiddle" style="float:left;" />
        <p style="color:#360;margin:10px 0 0 0;"><span style="color:#fff"><jecs:locale key="register.select.a"/></span> &gt;&gt; <jecs:locale key="register.select.b"/> &gt;&gt; <jecs:locale key="register.select.c"/> &gt;&gt; <jecs:locale key="register.select.d"/></p>
    </td>
  </tr>
  <tr>
    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td valign="top" style="padding:4px 4px 4px 9px;">
        <table width="100%" border="0" cellspacing="0" cellpadding="0" style="background:url(images/joinUS/regCententTitleBg.jpg);">
          <tr>
            <td width="56" height="56" align="right"><img src="images/joinUS/regCententTitleIco.jpg" width="56" height="56" alt="" /></td>
            <td style="font-size:16px; font-family:Verdana, Geneva, sans-serif; font-weight:bold; padding:0 0 0 12px; color:#333"><jecs:locale key="register.us.legend.member.register" /></td>
            <td align="right" width="6"><img src="images/joinUS/regCententTitleRight.jpg" width="6" height="56" alt="" /></td>
          </tr>
        </table> 
    
<%--<spring:bind path="jmiMember.*">
    <c:if test="${not empty status.errorMessages}">
    <div class="error">    
        <c:forEach var="error" items="${status.errorMessages}">
            <img src="<c:url value="images/newIcons/warning_16.gif"/>"
                alt="<jecs:locale key="icon.warning"/>" class="icon" />
            <c:out value="${error}" escapeXml="false"/><br />
        </c:forEach>
    </div>
    </c:if>
</spring:bind>--%>
<form:errors path="companyCode" cssStyle="color:red"></form:errors>

<form:errors path="jmiRecommendRef.recommendJmiMember.userCode"  cssStyle="color:red"></form:errors>

        <table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td valign="middle" align="center">
            	<!--centent start-->
                <table border="0" cellspacing="2" cellpadding="2">
                <tr>
                  <td align="right" nowrap>  <jecs:label key="bdReconsumMoneyReport.company"/></td>
                  <td align="left" nowrap>
                  <%--<form:select path="companyCode" cssClass="text medium">
							<form:option label="" value=""/>
				            <form:options items="${applicationScope.companyList }" itemValue="companyCode" itemLabel="companyName"/>
                  </form:select>--%>
                  <select id="companyCode" name="companyCode" class="text medium">
                  	<option value=""></option>
                  	<option value="US">USA UNITEDSTATES</option>
                  </select>
                  </td>
                </tr>
                <tr>
                  <td align="right" nowrap><jecs:label key="miMember.recommendNo" /></td>
                  <td align="left" nowrap>
                  <form:input path="jmiRecommendRef.recommendJmiMember.userCode"/>
                  </td>
                </tr>
                <tr>
                  <td align="right" nowrap><jecs:label key="sysOperationLog.moduleName" /></td>
                  <td align="left" nowrap>
                  	<button class="regButton" onmouseover="this.className='regButtonOver'" onmouseout="this.className='regButton'" 
                    	type="submit" onclick="javascript:$('_target0').name='_cancel';"  value="" ><img src="images/joinUS/icon/Reply.gif" border="0" align="absmiddle" /><jecs:locale key="operation.button.return" /></button>
                  	<button class="regButton" onmouseover="this.className='regButtonOver'" onmouseout="this.className='regButton'" 
                    	type="submit" onclick="javascript:$('_target0').name='_target1';" name="_target1" value="" ><img src="images/joinUS/icon/arrowRight.gif" border="0" align="absmiddle" /><b><jecs:locale key="button.next" /></b></button>
                    	<input type="hidden" id="_target0">
                  </td>
                </tr>
                </table>
            	<!--centent end-->
            </td>
          </tr>
        </table>
		</form:form>
        </td>
        <td valign="top" width="30%" style="padding:10px;">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="50" height="42" align="left"><img src="images/joinUS/regRightTitleIco.jpg" width="50" height="42" alt="" /></td>
            <td width="100%" background="images/joinUS/regRightTitleBg.jpg" valign="middle" style="color:#990; font-size:16px; font-weight:bold;" nowrap="nowrap"><jecs:locale key="register.fillInfo"/></td>
            <td width="6" height="42" align="right"><img src="images/joinUS/regRightTitleRight.jpg" width="6" height="42" alt="" /></td>
          </tr>
          <tr>
            <td colspan="3" style="border-left:1px solid #99cc00;border-right:1px solid #99cc00;">
            <br />
            <ul style=" font-size:13px; color:#333; padding:6px; margin:24px 0 0 24px; list-style:decimal; line-height:24px;">
              <jecs:locale key="register.select.into.1"/>
            </ul>
            <br /><br /><br /><br /><br /><br /><br /><br /><br /><br />
            </td>
            </tr>
          <tr>
            <td colspan="3"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td style="background:url(images/joinUS/regRightBottomLeft.jpg); width:5px;height:5px;font-size:1px;">&nbsp;</td>
                <td style=" background:url(images/joinUS/regRightBottomBg.jpg);font-size:1px;">&nbsp;</td>
                <td style="background:url(images/joinUS/regRightBottomRight.jpg); width:5px;height:5px;font-size:1px;">&nbsp;</td>
              </tr>
            </table></td>
            </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
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

