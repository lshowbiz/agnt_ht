<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="order.unlock"/></title>
<content tag="heading"><jecs:locale key="order.unlock"/></content>
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 

<form action="${ctx }/unLockOrder.html" method="post">
${session.successMessages }
	<table>
		<tr>
			<td><jecs:locale key="poMemberOrder.memberOrderNo"/>:</td>
			<td><input type="text" id="orderNo" name="orderNo"/></td>
		</tr>
		<tr>
			<td colspan="2">
				<jecs:locale key="sys.pwd.unlock"/>:<input type="radio" name="flag" value="0" checked="checked"/>
				<jecs:locale key="operation.button.check"/> :<input type="radio" name="flag" value="1"/>
			</td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit" value="<jecs:locale key="button.submit"/>"/></td>
		</tr>
	</table>
</form>
