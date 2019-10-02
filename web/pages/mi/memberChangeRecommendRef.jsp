<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="miRecommendRefList.title"/></title>
<content tag="heading"><jecs:locale key="miRecommendRefList.heading"/></content>


<form action="memberChangeRecommendRef.html" method="post" name="miRecommendRefForm" id="miRecommendRefForm" onsubmit="if(isFormPosted()){return true;}{return false;}">

<div class="searchBar">
	<div class="new_searchBar">
		<jecs:label key="miMember.memberNo"/>
		<input size="10" name="memberNo" id="memberNo" type="text" value=""/>
	</div>
	<div class="new_searchBar">
		<jecs:label key="miMember.newRecommendNo"/>
		<input size="10" id="recommendNo" name="recommendNo" type="text" value=""/>
	</div>
	<div class="new_searchBar">
		<jecs:label key="miMember.checkRecommendRef"/>
		<input type="checkbox" name="noCheckLinkRef" value="1" style="width: 30px"/>
	</div>
	<div class="new_searchBar" style="margin-left: 45px">
		<button name="submit" class="btn btn_ins" type="submit">
			<jecs:locale key="operation.button.save"/>
		</button>
	</div>
</div>

</form>
