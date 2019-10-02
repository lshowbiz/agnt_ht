<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE HTML>
<html>
	<head>
	    <meta name="viewport" content="width=device-width, initial-scale=1"> 
		<meta name="decorator" content="mobile"/>
	</head>
	
	

<ul data-role="listview" style="margin: 0px;" >



<c:forEach items="${bdSendRecordList }" var="bdSendRecord">
	
  <li><a href="editJbdMemberLinkCalcHist.html?userCode=${bdSendRecord.jmiMember.userCode }&wweek=${bdSendRecord.wweek }">
    <h3>${ bdSendRecord.wweek}</h3>
    <p>
    
    	<jecs:title key="bdSendRecord.cardType"  />
					<c:if test="${bdSendRecord.cardType=='0' }">
    					<font color='#999999'><jecs:code listCode="bd.cardtype" value="0"/></font>
    				</c:if>
					<c:if test="${bdSendRecord.cardType=='1' }">
    					<font color='#009900'><jecs:code listCode="bd.cardtype" value="1"/></font>
    				</c:if>
    				<c:if test="${bdSendRecord.cardType=='2' }">
    					<font color='#006666'><jecs:code listCode="bd.cardtype" value="2"/></font>
    				</c:if>
    				<c:if test="${bdSendRecord.cardType=='3' }">
    					<font color='#0033CC'><jecs:code listCode="bd.cardtype" value="3"/></font>
    				</c:if>
    				<c:if test="${bdSendRecord.cardType=='4' }">
    					<font color='#CC0000'><jecs:code listCode="bd.cardtype" value="4"/></font>
    				</c:if>
    				<c:if test="${bdSendRecord.cardType=='6' }">
    					<font color='#FF00FF'><jecs:code listCode="bd.cardtype" value="6"/></font>
    				</c:if>
    	</p>
    	<p>
    	<jecs:title key="miMember.freezestatus"  />
    	<jecs:code listCode="mimember.freezestatus" value="${bdSendRecord.freezeStatus}"/>
    	</p>
    	<p>
    		<jecs:title key="fiBankbookTemp.status"  />
	    	<c:if test="${bdSendRecord.registerStatus=='1'||bdSendRecord.registerStatus=='3' }">
	    					<font color='#CC0000'>
							<jecs:locale key="bdSendRecord.unSend"/>
							</font>
	    				</c:if>
	    				<c:if test="${bdSendRecord.registerStatus=='2' }">
	    				<font color='#009900'>
							<jecs:locale key="bdSendRecord.sended"/>
						</font>
	    				</c:if>
	    				<c:if test="${bdSendRecord.registerStatus=='4' }">
							<jecs:locale key="busi.bd.notSend"/>
	    	</c:if>
    	</p>
    	<p>
    		<jecs:title key="bdSendRecord.bonusMoney"  />
	    	<fmt:formatNumber value="${bdSendRecord.remittanceMoney}" type="number" pattern="###,###,##0.00"/>
    	</p>
    	<p>
    		<jecs:title key="bdSendRecord.sendDate"  />
	    	<fmt:formatDate value="${bdSendRecord.sendDate}" pattern="yyyy-MM-dd"/>
    	</p>
    	
    	
  </a></li>
  

</c:forEach>


  
</ul>


</html>
