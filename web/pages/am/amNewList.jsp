<%@ page pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="amNewList.title"/></title>
<content tag="heading"><jecs:locale key="amNewList.heading"/></content>
<meta name="menu" content="AmNewMenu"/>

<c:set var="buttons">
	
		<jecs:power powerCode="addAmNew">
			    <button type="button" class="btn btn_ins" style="margin-right: 5px;width:auto"
			        onclick="location.href='<c:url value="/editAmNew.html"/>?strAction=addAmNew'"><jecs:locale key="button.add"/>
			     </button>
		</jecs:power>
	
</c:set>
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>
<ec:table 
	tableId="amNewListTable"
	items="amNewList"
	var="amNew"
	action="${pageContext.request.contextPath}/amNews.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
	<ec:row >
		<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
					<img src="<c:url value='/images/icons/editIcon.gif'/>" 
						onclick="javascript:editAmNew('${amNew.newId}')"
						style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
			</ec:column>
  			<ec:column property="category" title="类别" />
  			<ec:column property="subject" title="主题" />
  			<ec:column property="urlA" title="url">
  				<a href="${amNew.url}" target="_blank">${amNew.url}</a>
  			</ec:column>
  			<ec:column property="createTime" title="创建时间" format="yyyy-MM-dd HH:mm" cell="date" />
  			<ec:column property="newOrder" title="排序" />
	</ec:row>

</ec:table>	

<script type="text/javascript">

   function editAmNew(newId){
   		<jecs:power powerCode="editAmNew">
					window.location="editAmNew.html?strAction=editAmNew&newId="+newId;
			</jecs:power>
		}

</script>
