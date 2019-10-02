<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="pdShUrlList.title"/></title>
<content tag="heading"><jecs:locale key="pdShUrlList.heading"/></content>
<meta name="menu" content="PdShUrlMenu"/>

<form  action="pdShUrls.html" method="get" name="pdShUrlQuery" id="pdShUrlQuery">
<div class="searchBar">
       <div class="new_searchBar">   
         <jecs:title key="pdShUrl.valueCode"></jecs:title>
         <input name="valueCode" id="valueCode" value="${param.valueCode }" cssClass="text medium"/>
       </div>
       <div class="new_searchBar">   
         <jecs:title key="pdShUrl.valueTitle"></jecs:title>
         <input name="valueTitle" id="valueTitle" value="${param.valueTitle }" cssClass="text medium"/>       
       </div>
        <div class="new_searchBar"> 
                <button type="button" class="btn btn_sele"  style="width:auto" onclick="pdShUrlQueryQ(document.pdShUrlQuery)">
					        <jecs:locale key="operation.button.search"/>
			    </button>
			    <button type="button" class="btn btn_ins"  style="width:auto" onclick="pdShUrlAdd();">
					       <jecs:locale key="member.new.num"/>
			    </button>
        </div>
         <%-- <input name="search" type="button" class="button" onclick="pdShUrlQueryQ(document.pdShUrlQuery)" value="<jecs:locale key="operation.button.search"/>"/>
         <input type="button" name="add"  class="button" onclick="pdShUrlAdd()" value="<jecs:locale key="member.new.num"/>" /> --%>
</div>
</form>

<ec:table 
	tableId="pdShUrlListTable"
	items="pdShUrlList"
	var="pdShUrl"
	action="${pageContext.request.contextPath}/pdShUrls.html"
	width="100%"
	autoIncludeParameters="true"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="false" imagePath="./images/extremetable/*.gif">
				<ec:row >
    			<ec:column property="valueCode" title="pdShUrl.valueCode" />
    			<ec:column property="valueTitle" title="pdShUrl.valueTitle" />
    			<ec:column property="createTime" title="pd.createTime" />
    			<ec:column property="shUrl" title="pdShUrl.shUrl" />
                <ec:column property="_1" title="sysOperationLog.moduleName">
					 <img src="images/newIcons/search_16.gif" onclick="pdShUrlQueryDetail('${pdShUrl.id}')" alt="<jecs:locale key="function.menu.view"/>" style="cursor:pointer"/>
                     &nbsp;&nbsp;&nbsp;&nbsp;
					 <img src="images/newIcons/pencil_16.gif" onclick="pdShUrlQueryUpdate('${pdShUrl.id}')" alt="<jecs:locale key="operation.button.edit"/>" style="cursor:pointer"/>
                </ec:column>
				</ec:row>
</ec:table>	

<script type="text/javascript">

     function pdShUrlQueryQ(theForm){
          theForm.submit();
     }
     
     function pdShUrlAdd(){
           window.location.href = "editPdShUrl.html?strAction=pdShUrlAdd";         
     }
     
     function pdShUrlQueryDetail(id){
           window.location.href = "editPdShUrl.html?strAction=pdShUrlQueryDetail&id="+id; 
     }
     
     function pdShUrlQueryUpdate(id){
           window.location.href = "editPdShUrl.html?strAction=pdShUrlQueryUpdate&id="+id; 
     }
  
</script>
