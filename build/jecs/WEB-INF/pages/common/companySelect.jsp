<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="alCompanyList.title"/></title>
<content tag="heading"><jecs:locale key="alCompanyList.heading"/></content>
<meta name="menu" content="AlCompanyMenu"/>






<ec:table 
	items="alCompanyList"
	var="alCompany"
	
	width="100%"
	rowsDisplayed="20" sortable="t./images/extremetableges/extremetable/*.gif">
				<ec:row onclick="javascript:selectCompany('${alCompany.companyCode}','${alCompany.companyName}')">
				
    			<ec:column property="companyCode" title="alCompany.companyCode" />
    			<ec:column property="companyName" title="alCompany.companyName" />
    			
				</ec:row>

</ec:table>	


<script type="text/javascript">
    function selectCompany(companyCode,companyName){
    	window.opener.document.getElementById('companyNo').value=companyCode;
    	window.opener.document.getElementById('companyName').value=companyName;
    	this.close();
					//window.location="editPiSalesModel.html?smId="+smId;
		}
</script>
