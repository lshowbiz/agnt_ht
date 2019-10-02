<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="alCountryList.title"/></title>
<content tag="heading"><jecs:locale key="alCountryList.heading"/></content>
<meta name="menu" content="AlCountryMenu"/>

<script src="./scripts/MzTreeView/jquery.js" type="text/javascript"></script>
<script src="./scripts/MzTreeView/jquery.thickbox.js" type="text/javascript"></script>
<script src="./scripts/MzTreeView/MzTreeView12.js" type="text/javascript" ></script>

<style>
html,body{
     margin:0px;
     height:100%;
}
</style>

<div id="treebox" style="width:100%; height:100%;overflow:auto;">
<script type="text/javascript">
tree = new MzTreeView("tree");

tree.icons["user"]  = "user.gif";

with(tree){
	N["0_A"]="T:<jecs:locale key="alCountry.allType"/>;C:onNodeSelect('0','0')";
	
	<c:forEach items="${alCountrys}" var="alVar">
		N["A_C#${alVar.countryId}"]="T:<jecs:locale key="${alVar.countryName}"/>;C:onNodeSelect('${alVar.countryId}','1')";
		<c:if test="${not empty alVar.alStateProvinces}">
			<c:forEach items="${alVar.alStateProvinces}" var="alSP">
				N["C#${alVar.countryId}_P#${alSP.stateProvinceId}"]="T:[${alSP.stateProvinceCode}]<jecs:locale key="${alSP.stateProvinceName}"/>;C:onNodeSelect('${alSP.stateProvinceId}','2')";
				<c:if test="${not empty alSP.alCitys}">
					<c:forEach items="${alSP.alCitys}" var="alcity">
						N["P#${alcity.alStateProvince.stateProvinceId}_CT#${alcity.cityId}"]="T:[${alcity.cityCode}]<jecs:locale key="${alcity.cityName}"/>;C:onNodeSelect('${alcity.cityId}','3')";
							<c:if test="${not empty alcity.alDistricts}">
								<c:forEach items="${alcity.alDistricts}" var="alDistrict">
									N["CT#${alDistrict.alCity.cityId}_AD#${alDistrict.districtId}"]="T:[${alDistrict.districtCode}]<jecs:locale key="${alDistrict.districtName}"/>";
								</c:forEach>
							</c:if>
					</c:forEach>
				</c:if>
			</c:forEach>
		</c:if>
	</c:forEach>
}
tree.wordLine = false;
tree.showRootFirst=true;
tree.setIconPath("./images/treeimages/");
tree.setTarget("frmCountryMain");
document.write(tree.toString());

<c:if test="${not empty param.masterUserCode}">
tree.focus('M#${param.masterUserCode}');
</c:if>

function onNodeSelect(nodeId,type){
	var redirectUrl;
	if(type=='0'){
		redirectUrl="alCountrys.html";
	}else if(type=='1'){
		redirectUrl="alStateProvinces.html?countryId="+nodeId;
	}else if(type=='2'){
		redirectUrl="alCitys.html?stateProvinceId="+nodeId;
	}else if(type=='3'){
		redirectUrl="alDistricts.html?cityId="+nodeId;
	}
	//alert(nodeId);
	window.parent.frmCountryMain.location=redirectUrl;
}
</script>
</div>