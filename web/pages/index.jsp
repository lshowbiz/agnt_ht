<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%@ page language="java" errorPage="/error.jsp" contentType="text/html; charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/meta.jsp" %>
	<title><jecs:locale key="webapp.name"/></title>
	<link rel="stylesheet" href="./styles/default.css" />
    <link rel="stylesheet" href="./ext/resources/css/ext-all.css" />
    
    <style>
    html, body {
        font:normal 12px verdana;
        margin:0;
        padding:0;
        border:0 none;
        overflow:hidden;
        height:100%;
    }
    p {
        margin:5px;
    }
    .floatDiv {position:absolute;left:24px;bottom:10px;width:100px;height:89px;background: #DBDBDB url('images/kf.jpg') left center no-repeat;}
    .floatDiv a { display: block;width: 100px;height: 89px;}
    .floatDiv span { position: absolute; right: 0;top: 0; display: block;width: 11px;height: 11px;cursor: pointer;}
    </style>
	<script>
		function closeIt(){
			var floatDiv = document.getElementById('floatDiv');
			floatDiv.style.display = 'none';
		}
	</script>
    <script src="./ext/adapter/ext/ext-base.js"></script>
    <script src="./ext/ext-all.js"></script>
	<script src="./ext/ux/TabCloseMenu.js"></script>
	<script src="./ext/ux/statusbar/StatusBar.js"></script>
</head>
<body>
    <!-- use class="x-hide-display" to prevent a brief flicker of the content -->
	<div id="north" class="x-hide-display">
	<table  border="0" cellpadding="0" cellspacing="0" width="100%">
      <tbody><tr>
        <td height="92" valign="top" width="500"><img src="images/main/ec-img.png" height="92" width="349"></td>
        <td height="92" valign="middle" width="165"><img src="images/main/logo.png" height="40" width="155"></td>
        <td height="92" valign="middle" width="150">
		  <table border="0" cellpadding="0" cellspacing="0" width="100%">
          <tbody>
            <tr>
              <td class="header_t2"><a href="#" <jecs:power powerCode="bdNewOldPeriod">onclick="javascript:addTab('<jecs:locale key='menu.bd_new_old_period'/>','<c:url value='/bd_new_old_period.html?bdPeriodListTable_p=2'/>','9_9_9')";</jecs:power>> <c:set var="tmpWeek">${bdPeriod.fweek}</c:set>
				  	<c:if test="${fn:length(tmpWeek)==2}">${bdPeriod.fyear}${bdPeriod.fweek}</c:if>
				  	<c:if test="${fn:length(tmpWeek)==1}">${bdPeriod.fyear}0${bdPeriod.fweek}</c:if></a></td>
            </tr>
            <tr>
              <td class="header_t1">Month-${bdPeriod.fmonth }</td>
            </tr>
            <tr>
              <td class="header_t1"><fmt:formatDate value="${bdPeriod.startTime }" pattern="yyyy/MM/dd"/>-<fmt:formatDate value="${bdPeriod.endTime }" pattern="yyyy/MM/dd"/></td>
            </tr>
          </tbody>
        </table></td>
        <td align="right" >
			<table width="427" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="95%" align="right">${sessionScope.sessionLogin.companyCode } 
						<c:if test="${sessionScope.sessionLogin.userType=='M' }">
							${sessionScope.sessionLogin.jmiMember.petName }
						</c:if>
						<c:if test="${sessionScope.sessionLogin.userType=='C' }">
							${sessionScope.sessionLogin.userName }
						</c:if> 
						${sessionScope.sessionLogin.userCode } / ${alCompany.companyName } / ${sessionScope.sessionLogin.operatorSysUser.userCode} | 
						[<a href="${ctx}/logout.jsp">
							<font color="blue"><jecs:locale key="menu.logout"/></font>
						</a>] <%-- | 
						<a href="#" onClick="window.contentFrm.location='${fn:toLowerCase(sessionScope.sessionLogin.companyCode) }/index.jsp';">
							<jecs:locale key="button.help"/>
						</a>--%>
					</td> 
					<td width="5%" align="right"></td>
				</tr>
			</table>
			<form action="<c:url value='/index.html'/>" method="post" name="indexForm">
			<table width="427" border="0" cellspacing="0" cellpadding="0" style="margin-top:8px">
            <tr>
				<td style="text-align:right;">
			  		<jecs:locale key="view.langage"/>:<!-- è¯­è¨ -->		
					<select style="width:auto" name="characterCode" onChange="changeCharacterCode(this.form)">
						<c:forEach items="${alCharacterCodings}" var="alCharacterCodingVar"> <option value="${alCharacterCodingVar.characterCode }"
							<c:if test="${alCharacterCodingVar.characterCode==sessionScope.sessionLogin.defCharacterCoding }"> selected</c:if>
							>${alCharacterCodingVar.characterName }    
						  </option>
						</c:forEach>
					</select>
				</td>
              	<td width="20">&nbsp;</td>
			<c:if test='${not empty alCompanys}'>
				<td style="text-align:right;">
					<jecs:locale key="view.system"/>:<!-- ç³»ç» -->
					<c:if test="${not empty alCompanys}">
						<select style="width:auto" name="companyCode" onChange="changeCompanyCode(this.form)">
							<c:forEach items="${alCompanys}" var="alCompanyVar"> 
								<option value="${alCompanyVar.companyCode }"
								<c:if test="${alCompanyVar.companyCode==sessionScope.sessionLogin.companyCode }"> selected</c:if>>
								<c:if test="${alCompanyVar.companyCode=='AA'}"><jecs:locale key="sysMenu.belongType.managerCenter"/></c:if>
								<c:if test="${alCompanyVar.companyCode!='AA'}">${alCompanyVar.companyName }</c:if>
							  </option>
							</c:forEach>
						</select>
					</c:if>
				</td>
			</c:if>
				<td width="20">&nbsp;</td>
		  </tr>
          </table></td>
        <td valign="top" width="1">        </td>
        </tr>
    </tbody></table></form>
  
  
  
  	<!-----一级菜单nav------>

	<div class="nav">
		<div class="nav_c">
		
			<c:forEach items="${sysModules }" var="topMenu" varStatus="status">
				<a href="javascript:subMenuShow('${status.count-1}');"  class="nav_fram fl"><jecs:locale key="${topMenu.module_name}"/></a>
			</c:forEach>
			
		</div>		
	</div>		
	<div class="nav_line"></div>
  		<!-----一级菜单导航end------>
    </div>
    
    <div id="west" class="x-hide-display">
    	<c:if test="${sessionScope.sessionLogin.userCode=='roottt'}">
			<div class="floatDiv" id="floatDiv">
        		<span onclick="closeIt()"></span>
        		<a href="http://120.236.141.99:8181/EliteWebChat/nanjing/customer.jsp?customername=${sessionScope.sessionLogin.userName}&customer_id=${sessionScope.sessionLogin.userCode}&queue=1" target="blank">111</a>
        	</div>
        </c:if>
        <iframe name="treeFrm" frameborder="0" src="sys_menu.html" width="100%"  height="100%"></iframe>
    </div>
    
    <div id="center1" class="x-hide-display"  style="height: 100%;">
      <c:if test="${sessionLogin.userType=='C' }">
		<iframe name="contentFrm" frameborder="0" src="<c:url value="/amAnnounces.html?strAction=browserAmAnnounce"/>" width="100%" height="100%" ></iframe>
	  </c:if>
	  <c:if test="${sessionLogin.userType=='M' }">
		<iframe name="contentFrm" frameborder="0" src="<c:url value="/workspace.html?strAction=workspace"/>" width="100%" height="100%" ></iframe>
	  </c:if>
    </div>
    
	<%-- <div id="east" class="x-hide-display">
		<div id="quickin">
		<ul>
			<jecs:power powerCode="agentAmMessage">
				<li><a href="#" onclick="javascript:addTab('<jecs:locale key='menu.am.agentAmMessage'/>','<c:url value='/amMessages.html?strAction=agentAmMessage'/>','1675_1184')"><img src="images/icon06.jpg" /><br /><span>
				<jecs:locale key="menu.am.agentAmMessage"/></span></a></li>
			</jecs:power>
			
			<jecs:power powerCode="memberCreate">
				<li><a href="#" onclick="javascript:addTab('<jecs:locale key='menu.memberCreate'/>','<c:url value='/memberCreate.html'/>','1683_1155')"><img src="images/icon01.jpg" /><br /><span>
				<jecs:locale key="menu.memberCreate"/></span></a></li>
			</jecs:power>
			<jecs:power powerCode="bdOrganiseStatusQuery">
				<li><a href="#" onclick="javascript:addTab('<jecs:locale key='function.menu.organization.search'/>','<c:url value='/bd_organise_status.html'/>','1699_2140')"><img src="images/icon03.jpg" /><br /><span>
				<jecs:locale key="function.menu.organization.search"/></span></a></li>
			</jecs:power>
			<jecs:power powerCode="bdBonusTreeQuery">
				<li><a href="#" onclick="javascript:addTab('<jecs:locale key='function.menu.bdBonusTreeQuery'/>','<c:url value='/bd_bonus_tree.html'/>','1699_2151')"><img src="images/icon04.jpg" /><br /><span>
				<jecs:locale key="function.menu.bdBonusTreeQuery"/></span></a></li>
			</jecs:power>
			<jecs:power powerCode="poMemberROrders">
				<li><a href="#" onclick="javascript:addTab('<jecs:locale key='function.menu.poMemberROrders'/>','<c:url value='/jpoMemberROrders.html'/>','661_736')"><img src="images/icon02.jpg" /><br /><span>
				<jecs:locale key="function.menu.poMemberROrders"/></span></a></li>
			</jecs:power>
			<jecs:power powerCode="bdSendRecords">
				<li><a href="#" onclick="javascript:addTab('<jecs:locale key='function.menu.bonusSearch'/>','<c:url value='/bdSendRecords.html'/>','7203_1141')"><img src="images/icon05.jpg" /><br /><span>
				<jecs:locale key="function.menu.bonusSearch"/></span></a></li>
			</jecs:power>
		</ul>
		</div>
    </div> --%>
    
    <div id="props-panel" class="x-hide-display" style="width:200px;height:200px;overflow:hidden;"></div>
    <div id="south" class="x-hide-display"></div>
    <div id="kf">
		<c:if test="${ sessionScope.sessionLogin.userCode=='root'}">
			<!-- <script src="http://chat.53kf.com/kf.php?arg=zm2009&style=1&charset=utf-8&username=${sessionScope.sessionLogin.userCode}-${sessionScope.sessionLogin.userName}"></script> -->
		</c:if>
	</div>        
	<div class="mask" id="mask"></div>
<script>
function refreshPage(){
	window.contentFrm.location.reload();
}

function goHome(){
<c:if test="${sessionLogin.userType=='C' }">
	window.contentFrm.location="amAnnounces.html?strAction=browserAmAnnounce";
</c:if>
<c:if test="${sessionLogin.userType=='M' }">
	window.contentFrm.location="workspace.html?strAction=workspace";
</c:if>
}

function changeCharacterCode(theForm){
	theForm.submit();
}

function changeCompanyCode(theForm){
	theForm.submit();
}
</script>

<c:if test="${not empty bankinfo || not empty activeinfo }">
<script>
var str='';

<c:if test="${not empty bankinfo }">
	str+='<jecs:locale key="bankinfo.member"/>';
</c:if>

<c:if test="${not empty bankinfo }">
	str+='\n';
</c:if>

<c:if test="${not empty activeinfo }">
	str+='${activeinfo}';
</c:if>

alert(str);
</script>
</c:if>
<c:if test="${not empty ylMemberInfo }">
<script>
alert('<jecs:locale key="ylMemberInfo.info"/>');
</script>
</c:if>
<c:if test="${ sessionScope.sessionLogin.userType=='M'&& sessionScope.sessionLogin.jmiMember.memberType=='2' }">
<%
	java.util.Calendar startc=java.util.Calendar.getInstance();
	startc.set(2010, 1, 13, 00, 00, 00);
	java.util.Date startDate=startc.getTime();
	
	java.util.Calendar endc=java.util.Calendar.getInstance();
	endc.set(2010, 1, 27, 00, 00, 00);
	java.util.Date endDate=endc.getTime();
	
	java.util.Date  curDate=new java.util.Date();
	if(curDate.before(endDate)&&curDate.after(startDate)){
%>
			
<script>
	window.open ('jmmessage.jsp', 'newwindow', 'height=320, width=580, top=0, left=0, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=n o, status=no')
</script>
<%
	}						
%>
<%
	java.util.Calendar startc1=java.util.Calendar.getInstance();
	startc1.set(2010, 1, 6, 00, 00, 00);
	java.util.Date startDate1=startc1.getTime();
	
	
	java.util.Calendar endc1=java.util.Calendar.getInstance();
	endc1.set(2010, 1, 13, 00, 00, 00);
	java.util.Date endDate1=endc1.getTime();
 
	if(curDate.before(endDate1)&&curDate.after(startDate1)){
%>
			
<script>
	window.open ('jmmessage2.jsp', 'newwindow1', 'height=320, width=980, top=0, left=0, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=n o, status=no')
</script>
<%
	}
%> 
</c:if>

<c:if test="${not empty memberFreeze }">

<script>
	alert('<jecs:locale key="memberFreeze.tips" args="${memberFreeze }" />')
</script>
</c:if>

<c:if test="${not empty memberFreeze1 }">

<script>
	alert('<jecs:locale key="${memberFreeze1 }" />')
</script>
</c:if>

<c:if test="${not empty addrTips }">

<script>
	alert('<jecs:locale key="jmiSubStore.addrTips"/>')
</script>
</c:if>

<c:if test="${not empty tips3276 }">

<script>
	alert('<jecs:locale key="tips3276" args="${tips3276 }" />')
</script>
</c:if>

<c:if test="${not empty memberPV10 }">

<script>
	alert('<jecs:locale key="order.10.chongxiao"/>')
</script>
</c:if>

<c:if test="${not empty noReplyNum }">

<script>
	alert('${noReplyNum}')
</script>
</c:if>


<c:if test="${not empty othersTips }">

<script>
	alert('<jecs:locale key="jmiSubStore.othersTips"/>')
</script>
</c:if>

<c:if test="${not empty jmiStoreTips }">

<script>
	alert('<jecs:locale key="jmiStore.orderTips"/>')
</script>
</c:if>

<c:if test="${ sessionScope.sessionLogin.userType=='C' && sessionScope.sessionLogin.companyCode=='TW'}">
<script> 
	open('<c:url value="pdWarehouseStockReports.html?strAction=warehouseStockReport&groupType=productNo"/>');
</script> 
</c:if>

<c:if test="${ sessionScope.sessionLogin.userType=='M' && sessionScope.sessionLogin.companyCode=='CN'}">
<script> 
	open('<c:url value="notice.jsp"/>');
</script> 
</c:if>

<%--<c:if test="${ sessionScope.sessionLogin.userType=='M' && sessionScope.sessionLogin.companyCode=='CN'}">
<script> 
	open('<c:url value="vtVotePolls.html"/>');
</script> 
</c:if>--%>
   <script>
	var viewport;
	var tabpanel;
	Ext.QuickTips.init();
    Ext.onReady(function(){
        //Ext.state.Manager.setProvider(new Ext.state.CookieProvider());
        
        viewport = new Ext.Viewport({
            layout: 'border',
            items: [
            // create instance immediately
            new Ext.BoxComponent({
                region: 'north',
				contentEl: 'north',
                height: 130
            }), {
                // lazily created panel (xtype:'panel' is default)
                region: 'south',
                height: 28,
                title: '',
                margins: '0 0 0 0',
				layout: 'fit',
				items:
				new Ext.ux.StatusBar({
					defaultText: 'Copyright &copy 2009 - 2011 JoyLife International. All rights reserved.',
					id: 'right-statusbar',
					statusAlign: 'right', // the magic config
					items: [{
						text: 'Login Time:<fmt:formatDate value="${date }" pattern="yyyy-MM-dd HH:mm:ss"/>(Hong Kong Time)'
					}, '-', 'IP Address:${ipAddress}', ' ', ' ']
				})
				
            }, 
            /* {
                region: 'east',
				contentEl: 'east',
                title: '<jecs:locale key="quickEntry"/>',
                collapsible: true,
				autoScroll:true,   
                split: true,
                width: 100, // give east and west regions a width
                minSize: 80,
                maxSize: 300,
                margins: '0 5 0 0',
                layout: 'fit' // specify layout manager for items
                
            }, */
            {
                region: 'west',
                id: 'west-panel', // see Ext.getCmp() below
				contentEl: 'west',
                title: '${webappName }',
                split: true,
                width: 200,
                minSize: 175,
                maxSize: 400,
                collapsible: true,
                margins: '0 0 0 5',
                layout: 'fit'
                
            },
            // in this instance the TabPanel is not wrapped by another panel
            // since no title is needed, this Panel is added directly
            // as a Container
            tabpanel = new Ext.TabPanel({
                region: 'center', // a center region is ALWAYS required for border layout
                deferredRender: false,
                activeTab: 0,     // first tab initially active

				resizeTabs:true, // turn on tab resizing
				minTabWidth: 55,
				tabWidth:135,
				enableTabScroll:true,
				//width:600,
				//height:150,
				defaults: {autoScroll:true},
                items: [{
                    contentEl: 'center1',
                    title: '<jecs:locale key="mainPage"/>',
					margins:'0 0 0 0',
                    autoScroll: true
                }],plugins: new Ext.ux.TabCloseMenu()
            })]
        });
		
    });

	function addTab(tabTitle,UrlText,id){	
		tabpanel.add({   
			 title:tabTitle,
			 tabTip:tabTitle,
			 id:'tab_'+id,   
			 iconCls: 'tabs',                     
			 margins:'0 0 0 0',   
			 autoScroll:true,     
			 closable:true, 
			 layout: 'fit',
			 html:'<iframe src="'+UrlText+'" width="100%" height="100%" scrollbar="no" frameborder="0"></iframe>'  
		}).show();  
	}
	//-->
	document.getElementById("west").style.height = "100%";
	document.getElementById("west").style.position = "relative";
	function subMenuShow(num){
		//console.log('============='); 
		window.top.treeFrm.window.viewTree(num);
	}
	</script>
</body>
</html>