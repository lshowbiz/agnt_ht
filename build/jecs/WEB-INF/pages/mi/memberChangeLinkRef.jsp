<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="miLinkRefList.title"/></title>
<content tag="heading"><jecs:locale key="miLinkRefList.heading"/></content>


<form action="memberChangeLinkRef.html" method="post" name="miLinkRefForm" id="miLinkRefForm" onsubmit="if(isFormPosted()){return true;}{return false;}">

<div class="searchBar">
	<div class="new_searchBar">
		<jecs:label key="miMember.memberNo"/>
		<input size="10" name="memberNo" id="memberNo" type="text" value=""/>
	</div>
	<div class="new_searchBar">
		<jecs:label key="miMember.newLinkNo"/>
		<input size="10" id="linkNo" name="linkNo" type="text" value=""/>
	</div>
	<div class="new_searchBar">
		<jecs:label key="miMember.checkRecommendRef" />
		<input type="checkbox" name="noCheckRommendRef" value="1"  style="width: 30px;"/>
	</div>
	<div class="new_searchBar" style="margin-left: 40px">
		<button name="submit" class="btn btn_ins" type="submit">
			<jecs:locale key="operation.button.save"/>
		</button>
	</div>
</div>

</form>
