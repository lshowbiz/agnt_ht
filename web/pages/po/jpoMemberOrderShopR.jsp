<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jpoMemberOrderList.title"/></title>
<content tag="heading"><jecs:locale key="jpoMemberOrderList.heading"/></content>
<meta name="menu" content="JpoMemberOrderMenu"/>

<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script> 

<form action="jpoMemberOrderShopR.html" method="get" name="searchForm" id="searchForm">
<table class="detail" width="70%">
	<tbody class="window" >
	
		<tr class="edit_tr">
			<th><span class="text-font-style-tc"><jecs:label key="jpo.fyear"/></span></th>
			<td >
				<span class="textbox"><input name="fyear" type="text" value="${param['fyear'] }" size="12" class="textbox-text"/></span>
				<jecs:locale key="jpo.fyearformat"/>
			</td>
		</tr>
		<tr>		
			<td class="command" colspan="4" align="center">
				<input name="search" class="btn btn_sele" type="submit" value="<jecs:locale key="button.report"/>" />
			</td>
		</tr>
		
</tbody>
	</table>
</form>