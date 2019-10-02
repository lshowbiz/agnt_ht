<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jmiAddrBookList.title"/></title>
<content tag="heading"><jecs:locale key="jmiAddrBookList.heading"/></content>
<meta name="menu" content="JmiAddrBookMenu"/>


<form action="" method="get" name="miMemberForm" id="miMemberForm">
	<div class="searchBar">
		<c:if test="${sessionScope.sessionLogin.userType=='C'}">
			<div class="new_searchBar"> 	
				<jecs:title key="miMember.memberNo"/>
				<input name="userCode" type="text" value="${param.userCode}" size="10"/>
			</div>	
		</c:if>
		<div class="new_searchBar">
			<button type="submit" class="btn btn_sele" name="cancel">
				<jecs:locale key="operation.button.search"/>
			</button>
			<jecs:power powerCode="addJmiAddrBook">
				<button name="search" class="btn btn_ins" type="button" 
					onclick="location.href='editJmiAddrBook.html?strAction=addJmiAddrBook'">
					<jecs:locale key="member.new.num"/>
				</button>
			</jecs:power>
		</div>
	</div>
</form>

<ec:table 
	tableId="jmiAddrBookListTable"
	items="jmiAddrBookList"
	var="jmiAddrBook"
	action="${pageContext.request.contextPath}/jmiAddrBooks.html"
	width="100%"
	retrieveRowsCallback="limit"
	autoIncludeParameters="true"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
				<ec:row>
	    			<ec:column property="jmiMember.userCode" title="miMember.memberNo" />
	    			<ec:column property="firstName" title="shipping.firstName" />
	    			<ec:column property="lastName" title="shipping.lastName"/>
	    			<ec:column property="province" title="shipping.province">
		    			<c:forEach items="${alStateProvinces }" var="al">
					    	<c:if test="${al.stateProvinceId==jmiAddrBook.province }">
					    		<c:out value="${al.stateProvinceName}" /> 
					    	</c:if>
			    		</c:forEach>
	    			</ec:column>
	    			<ec:column property="city" title="shipping.city">
	    				
	    			</ec:column>
	    			
					<c:if test="${sessionScope.sessionLogin.companyCode!='JP'}">
	    				<ec:column property="district" title="shipping.district"/>
	    			</c:if>
	    			<ec:column property="address" title="shipping.address">
	    				<jecs:substr key="${jmiAddrBook.address }" length="25"/>
	    			</ec:column>
	    			<ec:column property="postalcode" title="shipping.postalcode" />
	    			<ec:column property="mobiletele" title="miMember.mobiletele" />
	    			
	    			<%-- 
	    			<ec:column property="email" title="miMember.email" />
	    			<ec:column property="phone" title="miMember.phone" />
	    			<ec:column property="1" title="fiBillAccount.isDefault" style="text-align:center">
	    				<c:if test="${jmiAddrBook.isDefault=='1' }">
	    					<font color='red'><jecs:locale key="yesno.yes"/></font>
	    				</c:if>
	    			</ec:column>
    				--%>
					<ec:column property="1" title="title.operation" sortable="false">
					
						<img src="images/newIcons/pencil_16.gif" onclick="window.location.href='editJmiAddrBook.html?fabId=${jmiAddrBook.fabId}&strAction=editJmiAddrBook';" alt="<jecs:locale key="button.edit"/>" style="cursor:pointer"/>
						
					
					</ec:column>
				</ec:row>

</ec:table>	


