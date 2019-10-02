<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="miMember.title"/></title>
<content tag="heading"><jecs:locale key="miMember.heading"/></content>

<script>
function loading(){
	var str = '<br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src=images/indicator_smallwaitanim.gif alt=Loading  align=absmiddle/>';
	str += '&nbsp;&nbsp;<jecs:locale key="button.loading"/>';
	document.getElementById("kkk").innerHTML=str;
	miMemberForm.submit();
} 
</script>
<form action="memberCheckRef.html?strAction=${param.strAction }" method="post" name="miMemberForm" id="miMemberForm">
<div id="kkk" class="searchBar">&nbsp;&nbsp;
	<div class="new_searchBar" style="margin-left: 40px">
		<button name="submit" class="btn btn_sele" onclick='loading();' type="submit">
			<jecs:locale key="button.checkRef"/>
		</button>
	</div>
</div>


</form>
