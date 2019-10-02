<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML>
<html>
	<head>
	    <meta name="viewport" content="width=device-width, initial-scale=1"> 
		<meta name="decorator" content="mobile"/>
	</head>








<table width="100%">
		<tr>
			<th>
				<jecs:title key="alCompany.regName"/>
			</th>
			<td>
			<jecs:company name="companyCode" selected="${jmiMember.companyCode }" label="true"  withAA="false"  />	
			</td>
		</tr>
		
		<tr>
			<th>
				<jecs:title key="miMember.miRecommendRef"/>
			</th>
			<td>
				${jmiMember.recommendNo}
			</td>
		</tr>
		<tr>
			<th>
				<jecs:title key="bdSendRecord.cardType"/>
			</th>
			<td>
				<jecs:code listCode="bd.cardtype" value="${jmiMember.cardType}"/>
			</td>
		</tr>
			<tr>
			<th>
				<jecs:title key="miMember.memberNo"/>
			</th>
			<td>
				<font size="3" color="red">${jmiMember.userCode }</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<jecs:locale key="operation.notice.MarkMemberNo"/> 
			</td>
		</tr>
			<tr>
			<th>
				<jecs:title key="memberCreate.tips"/>
			</th>
			<td>
				<font size="3" color="red"><jecs:locale key="memberCreate.tips.detail"/> </font>
			</td>
		</tr>


     <tr>
   		 <td colspan="2" >
 <input type="button" class="button" name="back"  onclick="window.location.href='index.html'" value="<jecs:locale key="operation.button.return"/>" />

			
			
   		 </td>
    </tr>


</table>


</html>














