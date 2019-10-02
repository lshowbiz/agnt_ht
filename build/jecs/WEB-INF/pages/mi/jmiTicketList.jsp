<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>



<ec:table 
	tableId="jmiTicketListTable"
	items="jmiTickets"
	var="jmiTicket"
	action="${pageContext.request.contextPath}/jmiTickets.html"
	width="100%"
	autoIncludeParameters="true"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
				<ec:exportXls fileName="jmiTicket.xls"></ec:exportXls>
				<ec:row >
    			<ec:column property="userCode" title="会员编号" />
    			<ec:column property="ticketType" title="门票类型" cell="com.joymain.jecs.util.ectable.EcTableCell">
    				<jecs:code listCode="tickettype" value="${jmiTicket.ticketType }" ></jecs:code>
    			</ec:column>
    			<ec:column property="applyUserCode" title="申请人编号" />
    			<ec:column property="userName" title="申请人姓名" />
    			<ec:column property="papernumber" title="身份证"  escapeAutoFormat="true"/>
    			<ec:column property="censusProvince" title="户籍所在省" cell="com.joymain.jecs.util.ectable.EcTableCell">
    				<jecs:region regionType="p" regionId="${jmiTicket.censusProvince }"></jecs:region>
    			</ec:column>
    			<ec:column property="censusCity" title="户籍所在市" cell="com.joymain.jecs.util.ectable.EcTableCell">
    				
    				<jecs:region regionType="c" regionId="${jmiTicket.censusCity }"></jecs:region>
    			</ec:column>
    			<ec:column property="censusDistrict" title="户籍所在区" cell="com.joymain.jecs.util.ectable.EcTableCell">
    				
    				<jecs:region regionType="d" regionId="${jmiTicket.censusDistrict }"></jecs:region>
    			</ec:column>
    			<ec:column property="censusAddress" title="户籍街道名称" />
    			<ec:column property="province" title="现居住省" cell="com.joymain.jecs.util.ectable.EcTableCell">
    				<jecs:region regionType="p" regionId="${jmiTicket.province }"></jecs:region>
    			</ec:column>
    			<ec:column property="city" title="现居住市" cell="com.joymain.jecs.util.ectable.EcTableCell">
    				<jecs:region regionType="c" regionId="${jmiTicket.city }"></jecs:region>
    			</ec:column>
    			<ec:column property="district" title="现居住市区" cell="com.joymain.jecs.util.ectable.EcTableCell">
    				<jecs:region regionType="d" regionId="${jmiTicket.district }"></jecs:region>
    			</ec:column>
    			<ec:column property="address" title="现居住街道" />
    			<ec:column property="mobiletele" title="手机号码" />
    			<ec:column property="remark" title="备注" />
				</ec:row>

</ec:table>	
