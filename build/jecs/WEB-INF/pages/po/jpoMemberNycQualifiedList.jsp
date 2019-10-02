<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jpoMemberNycList.title"/></title>
<content tag="heading"><jecs:locale key="jpoMemberNycList.heading"/></content>
<meta name="menu" content="JpoMemberNycMenu"/>
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" />
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script>
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script>
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script>

<c:set var="buttons">

</c:set>
<div class="searchBar">
<form action="jpoMemberNycQualified.html" method="get" name="searchForm" id="searchForm">
<div class="searchBar">
	<div class="new_searchBar">	
        <jecs:title key="bdBounsDeduct.wweek"/>
        <input type="text" name="jsweek" type="text" size="10" value="${param.jsweek}"/>
	</div>
	<div class="new_searchBar">
        <jecs:title key="miMember.memberNo"/>
        <input id="memberNo" name="memberNo" type="text" value="${param.memberNo }"/>
	</div>
	<div class="new_searchBar">
        <jecs:title key="poMemberOrder.memberOrderNo"/>
        <input id="orderNo" name="orderNo" type="text"  value="${param.orderNo }"/>
	</div>
	<div class="new_searchBar">
        <jecs:title key="miMember.recommendNo"/>
        <input id="recommendNo" name="recommendNo" type="text"  value="${param.recommendNo }"/>
	</div>
	<div class="new_searchBar">
        <jecs:title key="miMember.idNo"/>
        <input id="paperNumber" name="paperNumber" type="text" size="20" maxlength="20" value="${param.paperNumber }"/>
    </div>
	<div class="new_searchBar">
	    <jecs:title key="poMemberNyc.status"/>
	    <select name="status" >
	        <option value="0" ><jecs:locale key="poMemberNyc.status0"/></option>
	        <option value="1"><jecs:locale key="poMemberNyc.status1"/></option>
	    </select>
	</div>
	<div class="new_searchBar">
        <jecs:title key="poMemberNyc.qualify"/>
        <input id="qualify" name="qualify" type="text"  value="${param.qualify }"/>
	</div>
	<div class="new_searchBar" style="margin-left: 10px">
        <select name="dateType" style="width:80px">
            <option value="1"><jecs:locale key="logType.BC"/></option>
            <option value="2"><jecs:locale key="logType.C"/></option>
        </select>
		<input id="startTime" name="startTime" type="text" value="${param.startTime }"
        	style="cursor: pointer;width:75px;" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
         	-
        <input id="endTime" name="endTime" type="text" value="${param.endTime }"
        	style="cursor: pointer;width:75px;" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
	</div>
	<div class="new_searchBar">
		<%--<input name="search" class="button" type="submit" value="<jecs:locale key="operation.button.search"/>" />--%>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button name="search" class="btn btn_sele" type="submit">
			<jecs:locale key="operation.button.search"/>
		</button>
	    <button id="exportXls" name="export" class="btn btn_long" type="button"  >
	        <jecs:locale key="button.export"/>
	    </button>
	    <button type="button" class="btn btn_long" 
	    	onclick="location.href='<c:url value="/importJpoMemberNycQualify.html"/>'">
	    	<jecs:locale key="button.import"/>
	    </button>
	    <%-- 
		<input type="button" class="button" style="margin-right: 5px"
           onclick="location.href='<c:url value="/importJpoMemberNycQualify.html"/>'"
           value="<jecs:locale key="button.import"/>"/>
        --%>
    </div>
</div>
</form>

</div>
<ec:table

        tableId="jpoMemberNycQualifiedListTable"
        items="jpoMemberNycQualifiedList"
        var="jpoMemberNyc"
        action="${pageContext.request.contextPath}/jpoMemberNycQualified.html"
        width="100%"
        retrieveRowsCallback="limit"
        rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
    <ec:row >
        <ec:column property="1"  title="commom.id.select" >
            <input name="cid" type="checkbox" value="${jpoMemberNyc.id}">
        </ec:column>
        <ec:column property="jsWeek" title="bdBounsDeduct.wweek" />
        <ec:column property="userCode" title="poMemberNyc.memberNo" />
        <ec:column property="userName" title="poMemberNyc.userName" />
        <ec:column property="paperNumber" title="poMemberNyc.paperNumber" />
        <ec:column property="recommendNo" title="poMemberNyc.recommendNo" />
        <ec:column property="recommendName" title="poMemberNyc.recommendName" />
        <ec:column property="memberOrderNo" title="poMemberNyc.memberOrderNo" />
       <%----%>
	    <ec:column property="productNo" title="poMemberNyc.productNo" />
        <ec:column property="productName" title="poMemberNyc.productName" />
        
        <ec:column property="qualify" title="poMemberNyc.qualify">
        	<jecs:substr key="${jpoMemberNyc.qualify }" length="8"/>
        </ec:column>

        <ec:column property="13" title="poMemberNyc.status" >
            <c:if test="${jpoMemberNyc.status=='1'}">
                <jecs:locale key="poMemberNyc.status1"/>
            </c:if>
            <c:if test="${jpoMemberNyc.status=='0'}">
                <jecs:locale key="poMemberNyc.status0"/>
            </c:if>
        </ec:column>
        <ec:column property="remarks" title="poMemberNyc.remarks">
        	<jecs:substr key="${jpoMemberNyc.remarks }" length="10"></jecs:substr>
        </ec:column>
    </ec:row>

</ec:table>
<script type="text/javascript" src="./scripts/jquery/jquery-142min.js"> </script>
<script type="text/javascript">

    function editJpoMemberNyc(id){
        <jecs:power powerCode="editJpoMemberNyc">
        window.location="jpoMemberNycQualified.html?strAction=exportc&ids="+id;
        </jecs:power>
    }

    var DownLoadFile = function (options) {
        var config ={ method: 'post' };
        var $iframe = $('<iframe id="down-file-iframe" />');
        var $form = $('<form target="down-file-iframe" method="' + config.method + '" />');
        $(document.body).append($iframe);
        $iframe.append($form);
        $form.attr('action', options.url);
        for (var key in options.data) {
            $form.append('<input type="hidden" name="' + key + '" value="' + options.data[key] + '" />');
        }
        $form[0].submit();
        $iframe.remove();
    }
    
    $(document).ready(function () {
       $("#exportXls").click(function () {
           var ids=[];
           $("input[name='cid']:checked").each(function () {

               var row =[];
              $($(this).parent().parent()).children().each(function (i,td) {
                  if(i!=0){
                      row.push($(td).text());

                  }
              });
               ids.push(row.join("|"));
           });
           if(ids.length==0){
               alert("<jecs:locale key="poMemberNyc.must.selected"/>");
               return;
           }

           DownLoadFile({
               url:'${ctx}/jpoMemberNycQualified.html?action=export',
               data:{data:ids.join("||")}
                        });


           //console.log($("#jpoMemberNycQualifiedListTable_table")[0].children);

//           $("#jpoMemberNycQualifiedListTable_table").children().forEach(function (row) {
//               console.log(row);
//           });
           <jecs:power powerCode="jpoMemberNycQualifiedExport">
           //window.location="jpoMemberNycQualified.html?action=export&ids="+ids.join("','");
           </jecs:power>

       });
    });

</script>
