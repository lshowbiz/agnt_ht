<%@ page pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Welcome</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
    <link type="text/css" rel="stylesheet" href="scripts/JSCal2-1.8/css/jscal2.css" />
    <link type="text/css" rel="stylesheet" href="scripts/JSCal2-1.8/css/border-radius.css" />
    <script src="scripts/JSCal2-1.8/js/jscal2.js"></script>
    <script src="scripts/JSCal2-1.8/js/lang/lang.jsp"></script>
    <link id="skin-steel" title="Steel" type="text/css" rel="alternate stylesheet" href="scripts/JSCal2-1.8/css/steel/steel.css" />
	
	<style>
		#ad li {
		    text-align: left;
		    margin-left: 10px;
		    line-height: 20px;
		}
		#wrap {display:block;bottom:1px;right:14px!important;right:18px;width:199px;
		height:160px;line-height:30px;position:fixed;
		border:1px solid #999999;text-align:center;color:red; background:#E4E4E4}
	</style>

</head>

<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td valign="top"><table border="0" cellspacing="0" cellpadding="0">
      <tr>
        <jecs:locale key="sys.welcome" args="${sessionScope.sessionLogin.jmiMember.petName }" argTransFlag="true"/>
        </td>
      </tr>
      <tr>
        <td height="22" style="border-bottom:1px solid #ccc"><img src="images/a_03.jpg" width="28" height="22" /><span class="t3"><jecs:locale key="system.thing"/></span></td>
      </tr>
      <tr>
        <td class="k1"><p>${systemThingMsg }</p></td>
      </tr><%--
      <tr>
        <td height="22" style="border-bottom:1px solid #ccc"><img src="images/a_06.jpg" width="28" height="22" /><span class="t3"><jecs:locale key="bd.e.point.explain"/></span></td>
      </tr>
      <tr>
        <td class="k1"><p><jecs:locale key="bd.e.point"/>:<span class="t2"> ${coin }</span></p></td>
      </tr>
      --%><tr>
        <td height="22" style="border-bottom:1px solid #ccc"><img src="images/a_08.jpg" width="29" height="22" /><span class="t3"><jecs:locale key="system.upgrade"/></span></td>
      </tr>
      <tr>
      	<td class="k1">
      <c:if test="${days != '0' }">
        <p>${systemUpgradeDay }</p>
      </c:if>
		<p>${systemLeader }</p>
		<p>${systemLeader1 }</p>
		<c:if test="${not empty systemLeader11 }">
		<p>${systemLeader11 }</p>
		</c:if>
		<p>${systemLeader2 }</p>
		
		<!-- double -->
		<c:if test="${doubleView=='1' }">
			<p>${systemLeader22 }</p>
		</c:if>
		
		
		<p><b>${systemLeader3}</b></p>
		</td>
      </tr>
      <tr>
        <td height="22" style="border-bottom:1px solid #ccc"><img src="images/a_12.jpg" width="30" height="22" /><span class="t3"><jecs:locale key="system.safe"/></span></td>
      </tr>
      <tr>
        <td class="k1"><p>${systemSafe1 }</p></td>
      </tr>
    </table></td>
    <td width="209" bgcolor="#f6f6f6" valign="top">
    <div id="jszq">
    <jecs:label key="system.week"/>${mapCAL.week }  <jecs:label key="system.month"/>${mapCAL.month }
	</div>
	<div id="cont"></div>
	  <script type="text/javascript">
		var LEFT_CAL = Calendar.setup({
				cont: "cont",
				weekNumbers: false,
				fdow: 6,
				bottomBar:false
				// titleFormat: "%B %Y"
		});
		LEFT_CAL.selection.set([
			//20090507,               // a single date
			[ ${mapCAL.selection1 }, ${mapCAL.selection2 } ], // a date range (May 12..16)
		]);
	  </script>
		<%--<div id="ad" style="margin-top: 30px;">
			<li style="font-size: 14px; font-weight: bold; line-height: 22px;
			border-bottom: solid 1px #ccc;">新闻动态</li>
			<c:forEach items="${amNews }" var="new" varStatus="s">
				<c:if test="${s.count < 11 }">
					<li><a href="${new.url }" target="_blank">${new.subject }</a></li>
				</c:if>
			</c:forEach>
			<c:if test="${fn:length(amNews) >= 11 }">
				<li><a href="http://joylifeglobal.cn/" target="_blank">更多>></a></li>
			</c:if>
			<!-- <li><a href="http://www.joylifeglobal.cn/list.php?catid=33" target="_blank"><img src="images/gg/gg1.gif"></a></li>
			<li><a href="http://www.daoheint.com/" target="_blank"><img src="images/gg/gg2.gif"></a></li>-->		
 		</div>--%>
    </td>
  </tr>
</table>

<c:if test="${str365FTitle!=null}">
	<div id="wrap">

	<div>
	  <div align="left" style="color: #000000;margin-left:5px;"><img src="images/found1.gif"/><jecs:locale key="title.365foundtiontitle"/></div>
	</div>
	<div>
		<div align="left"><p style="height:80px; margin-left:5px;margin-right:5px;"><jecs:locale key="title.365foundtion"/></p></div>
	</div>
	<div>
		<div align="center"><a href="editFoundationOrder.html?ftype=1"><jecs:locale key="operation.button.part"/><a/></div>
	</div>
</div>
</c:if>
