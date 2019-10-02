<%@ include file="/common/taglibs.jsp"%>

<title><wecs:locale key="display.title"/></title>
<content tag="heading"><wecs:locale key="display.heading"/></content>
<meta name="menu" content="FileUpload"/>
<p>Finished!</p>

<div class="searchBar">
<c:choose>
<c:when test="${flagnum eq '7'}">
	<input type="button" class="button" name="cancel"  onclick="history.back()" value="<jecs:locale key="operation.button.return"/>" />
</c:when>
<c:otherwise>
	<input type="button" name="done" id="done" value="Done"
                onclick="done();" />
</c:otherwise>
</c:choose>


</div>
<script type="text/javascript">
	function done(){
				window.opener.location.reload();
				top.close();
		}
	</script>
