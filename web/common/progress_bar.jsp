<%@ include file="/common/taglibs.jsp"%>

<script type="text/javascript" src="./scripts/jQProgressBar/jquery.js"></script>
<script type="text/javascript" src="./scripts/jQProgressBar/jquery.progressbar.js"></script>

<span class="progressBar" id="spnProgressBar"></span>
<script type="text/javascript">
var pBarPercentage=0;
function showProgressBar() {
	$.get("<c:url value="/sys_progress_bar.html"/>?strAction=sysProgressBar&progressBarId=${progressBarId}", function(data) {
		if (!data)
			return;

		var response;
		eval ("response = " + data);

		if (!response) return;

		pBarPercentage = Math.floor(100 * parseInt(response['currentCount']) / parseInt(response['totalCount']));
		$("#spnProgressBar").progressBar(pBarPercentage);

	});
	if(pBarPercentage<100){
		setTimeout("showProgressBar()", 5000);
	}
}
</script>