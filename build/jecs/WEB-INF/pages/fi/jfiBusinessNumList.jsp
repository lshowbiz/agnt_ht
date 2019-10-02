<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jfiBusinessNumList.title"/></title>
<content tag="heading"><jecs:locale key="jfiBusinessNumList.heading"/></content>
<meta name="menu" content="JfiBusinessNumMenu"/>

<c:set var="buttons">
		<jecs:power powerCode="addJfiBusinessNum">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editJfiBusinessNum.html"/>?strAction=addJfiBusinessNum'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power>
</c:set>
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>
<ec:table 
	tableId="jfiBusinessNumListTable"
	items="jfiBusinessNumList"
	var="jfiBusinessNum"
	action="${pageContext.request.contextPath}/jfiBusinessNums.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="/jecs/images/extremetable/*.gif">
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editJfiBusinessNum('${jfiBusinessNum.businessId}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="paymentCompany" title="jfiBusinessNum.paymentCompany" />
    			<ec:column property="merchantId" title="jfiBusinessNum.merchantId" />
    			<ec:column property="merchantName" title="jfiBusinessNum.merchantName" />
    			<ec:column property="merchantType" title="jfiBusinessNum.merchantType" />
    			<ec:column property="password" title="jfiBusinessNum.password" />
    			<ec:column property="password2" title="jfiBusinessNum.password2" />
    			<ec:column property="busicode" title="jfiBusinessNum.busicode" />
    			<ec:column property="address" title="jfiBusinessNum.address" />
    			<ec:column property="maxMoney" title="jfiBusinessNum.maxMoney" />
    			<ec:column property="merchantStatus" title="jfiBusinessNum.merchantStatus" />
    			<ec:column property="createName" title="jfiBusinessNum.createName" />
    			<ec:column property="createTime" title="jfiBusinessNum.createTime" />
    			<ec:column property="operateName" title="jfiBusinessNum.operateName" />
    			<ec:column property="operateTime" title="jfiBusinessNum.operateTime" />
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editJfiBusinessNum(businessId){
   		<jecs:power powerCode="editJfiBusinessNum">
					window.location="editJfiBusinessNum.html?strAction=editJfiBusinessNum&businessId="+businessId;
			</jecs:power>
		}

</script>
