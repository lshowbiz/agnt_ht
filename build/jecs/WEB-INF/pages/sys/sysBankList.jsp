<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="sysBankList.title"/></title>
<content tag="heading"><jecs:locale key="sysBankList.heading"/></content>
<meta name="menu" content="SysBankMenu"/>

<c:set var="buttons">
		<jecs:power powerCode="addSysBank">
			    <button type="button" class="btn btn_ins" 
			        onclick="location.href='<c:url value="editSysBank.html"/>?strAction=addSysBank&companyCode=${param.companyCode }'"
			        ><jecs:locale key="member.new.num"/></button>
		</jecs:power>
</c:set>
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div> 
<ec:table 
	tableId="sysBankListTable"
	items="sysBankList"
	var="sysBank" 
	action="${pageContext.request.contextPath}/sys/sysBanks.html"
	width="100%"
	retrieveRowsCallback="limit"
	autoIncludeParameters="true"
	rowsDisplayed="20" sortable="true" imagePath="${pageContext.request.contextPath}/images/extremetable/*.gif">
				<ec:row >
				
								<ec:column property="1" title="title.operation" sortable="false" styleClass="centerColumn" >
				<jecs:power powerCode="editSysBank">
			<img src="../images/newIcons/pencil_16.gif" onclick="javascript:editSysBank('${sysBank.bankId}')" alt="<jecs:locale key="button.edit"/>" style="cursor:pointer"/>
			</jecs:power>

				</ec:column>
				
				
				
    			<ec:column property="bankKey" title="sysListValue.valueCode" />
    			<ec:column property="bankValue" title="sysListValue.valueTitle" />
    			<ec:column property="companyCode" title="sys.common.companyCode" />
    			<ec:column property="orderNo" title="sysListValue.orderNo" />
				</ec:row>

</ec:table>	



<script type="text/javascript">

   function editSysBank(bankId){
   		<jecs:power powerCode="editSysBank">
					window.location="editSysBank.html?strAction=editSysBank&bankId="+bankId;
			</jecs:power>
		}

</script>
