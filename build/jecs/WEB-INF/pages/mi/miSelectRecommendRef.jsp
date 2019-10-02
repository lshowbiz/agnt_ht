<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="miRecommendRefList.title"/></title>
<content tag="heading"><wecs:locale key="miRecommendRefList.heading"/></content>
<script>
  function return_data(m_no)
  {
    document.getElementById('m_no').value = m_no;
	document.getElementById('search_form').submit();
  }
  function return_data_per(m_no)
  {
	window.location.href='miSelectRecommendRef.html?strAction=miSelectRecommendRef&memberNo='+m_no;
  }
  function windowClose(m_no){
	window.opener.document.getElementById("linkNo").value = m_no; 
	window.open('','_parent',''); 
	window.close();
  }
</script>
<br /><br />
<c:if test="${not empty miLinkRefForm }">
<form method='POST' name='search_form'  id='search_form'  action='miSelectRecommendRef.html'>
<input type='hidden' name='memberNo' value='${param.memberNo }' id=memberNo>
<input type='hidden' name='strAction' value='${param.strAction }' id=strAction>
<input type='hidden' name='m_no' value='' id='m_no'>
<table width="780" border="0" cellpadding="0" cellspacing="0" align="center">
  <tr>
    <td colspan="4"></td><td  height="223" colspan="4" align="center" valign="top" ><table width="125" height="100%" border="0" cellpadding="4"background='images/organise/tb_1.jpg' style="background-repeat: no-repeat;background-position: left top;" cellspacing="5">
      <tr>
        <td align="center" valign="top" ><font color="blue">${miLinkRefForm.miLinkRef.jmiMember.userCode }</font><br />
              <b>${miLinkRefForm.miLinkRef.jmiMember.petName }</b><br />
          <c:if test="${param.strAction=='miSelectRecommendRef'}">
          <hr style='color:#888888;height:1px;' />
          <font color="blue">Group:${miLinkRefForm.gpv }pv</font><br />
          </c:if>
        </td>
      </tr>
    </table></td>
    <td colspan="4"></td>
  </tr>
  <tr>
    <td colspan="11" height="50" background='images/organise/tb.jpg' style="background-repeat: no-repeat;background-position: center;">&nbsp;</td>
  </tr>
  <tr>
  <c:set var="subWTotal" value="0"/>
  <c:forEach items="${miLinkRefList }" var="mi" varStatus="miStatus">
  <c:set var="subWTotal" value="${subWTotal+1 }"/>
  
    <td width="120" height="250" valign="top" background='images/organise/tb_2.jpg'><table width="100%" border="0" cellpadding="5" cellspacing="5">
      <tr>
        <td>

          
          <c:if test="${param.strAction=='miSelectRecommendRef'}">
<b>${mi.miLinkRef.jmiMember.petName }</b><br />
          <hr style='color:#888888;height:1px;' />
          <font color="blue">Group:${mi.gpv }pv</font><br />
          </c:if>
          <center>
            <a href='#' onclick="return_data('${mi.miLinkRef.jmiMember.userCode }');return false;"><img title='' border="0" src='images/organise/go-down.png' /></img></a>
            <c:if test="${sessionScope.sessionLogin.companyCode=='US' }">
            <a href='#' onclick="return_data_per('${mi.miLinkRef.jmiMember.userCode }');return false;"><img title='' border="0" src='images/organise/go-down1.png' /></img></a>
            
            </c:if>
            
          </center>
          <hr style='color:#888888;height:1px;' />
          <c:if test="${not empty mi.memberNo.userCode }">
          <button title='' style='cursor:pointer;' onclick="windowClose('${mi.memberNo.userCode}');">
            <table width="67" border="0" cellpadding="0" cellspacing="0">
                <tr>
                  <td align="center"><font color="blue">${mi.memberNo.userCode}</font><br />
                      <b>${mi.memberNo.jmiMember.petName}</b>
				  </td>
                </tr>
            </table>
          </button>
          </c:if>
        </td>
      </tr>
    </table></td>


    <c:if test="${miStatus.count!=6 }">
    <td width="10" height="220">&nbsp;</td>
    </c:if>
  </c:forEach>
  <c:forEach begin= "${subWTotal}"   step= "1"   end= "5" varStatus="miStatus">
    <td width="120" height="220" background='images/organise/tb_3.jpg'>&nbsp;</td>
    <c:if test="${miStatus.count!=6 }">
    <td width="10" height="220">&nbsp;</td>
    </c:if>
  </c:forEach>
  </tr>
</table>
</form>
</c:if>
<c:if test="${empty miLinkRefForm }">
<div align="center"><br /><br /><jecs:locale key="operation.notice.js.orderNo.miMember.memberNoError"/></div>
</c:if>