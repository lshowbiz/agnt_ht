<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jpmCardSeqList.title"/></title>
<content tag="heading"><jecs:locale key="jpmCardSeqList.heading"/></content>
<meta name="menu" content="JpmCardSeqMenu"/>


<div id="searchBar">

</div>
<ec:table 
	tableId="jpmCardSeqListTable"
	items="jpmCardSeqList"
	var="jpmCardSeq"
	action="${pageContext.request.contextPath}/jpmCardSeqs.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
				<ec:row >
    			<ec:column property="cardNo" title="jpmCardSeq.cardNo" />
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editJpmCardSeq(id){
   		<jecs:power powerCode="editJpmCardSeq">
					window.location="editJpmCardSeq.html?strAction=editJpmCardSeq&id="+id;
			</jecs:power>
		}

</script>
