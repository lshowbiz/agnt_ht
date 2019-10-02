<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="pdSendInfoDetailList.title"/></title>
<content tag="heading"><jecs:locale key="pdSendInfoDetailList.heading"/></content>
<meta name="menu" content="PdSendInfoDetailMenu"/>

<c:set var="buttons">
    <input type="button" class="button" style="margin-right: 5px"
        onclick="location.href='<c:url value="/editPdSendInfoDetail.html"/>'"
        value="<jecs:locale key="operation.button.add"/>"/>

    <input type="button" class="button" onclick="location.href='<c:url value="/mainMenu.html"/>'"
        value="<jecs:locale key="operation.button.done"/>"/>
</c:set>

<c:out value="${buttons}" escapeXml="false"/>

<ec:table 
	tableId="pdSendInfoDetailListTable"
	items="pdSendInfoDetailList"
	var="pdSendInfoDetail"
	action="${pageContext.request.contextPath}/pdSendInfoDetails.html"
	width="100%"
	rowsDisplayed="20" sort./images/extremetableecs/images/extremetable/*.gif">
				<ec:row onclick="javascript:editPdSendInfoDetail('${pdSendInfoDetail.uniNo}')">
    			<ec:column property="companyNo" title="pdSendInfoDetail.companyNo" />
    			<ec:column property="productNo" title="pdSendInfoDetail.productNo" />
    			<ec:column property="price" title="pdSendInfoDetail.price" />
    			<ec:column property="qty" title="pdSendInfoDetail.qty" />
				</ec:row>

</ec:table>	

<form name="form1" action="<c:url value='editPdSendInfoDetail.html'/>">
			<input type="hidden" name="strAction" id="strAction"/>
			<input type="hidden" name='uniNo' id='uniNo'/>
</form>

<script type="text/javascript">

   function editPdSendInfoDetail(uniNo){
					window.location="editPdSendInfoDetail.html?uniNo="+uniNo;
		}

</script>
