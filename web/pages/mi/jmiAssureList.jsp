<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jmiAssureList.title"/></title>
<content tag="heading"><jecs:locale key="jmiAssureList.heading"/></content>
<meta name="menu" content="JmiAssureMenu"/>
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script> 
<script type="text/javascript" src="<c:url value='/dwr/util.js'/>" ></script> 
<script type="text/javascript" src="<c:url value='/dwr/engine.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/dwr/interface/jmiAssureManager.js'/>" ></script>
<script type="text/javascript" src="./scripts/jquery/jquery-142min.js"> </script>
<script type="text/javascript" src="./scripts/jquery/jquery-form.js"> </script>
<%-- 
<c:set var="buttons">
		<jecs:power powerCode="addJmiAssure">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editJmiAssure.html"/>?strAction=addJmiAssure'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power>
		<jecs:power powerCode="addJmiAssure">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/jmiAssureListImport.html"/>?strAction=addJmiAssure'"
			        value="<jecs:locale key="button.import"/>"/>
		</jecs:power>
</c:set>
--%>
<form action="jmiAssures.html" method="get" name="searchForm" id="searchForm">
<div class="searchBar">
	<div class="new_searchBar">
		<jecs:label key="jmiAssure.userCode" />
		<input name="userCode" type="text" size="10" value="${param.userCode}"/>
	</div>
	<div class="new_searchBar">
		<jecs:label key="jmiAssure.startTime.endTime" />
		<input name="startTime" id="startTime" type="text" value="${param.startTime }" 
			style="cursor: pointer;width:75px;" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
	     - 
	    <input name="endTime" id="endTime" type="text" value="${param.endTime }" 
	    	style="cursor: pointer;width:75px;" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
	 </div>
	 <div class="new_searchBar">
		<jecs:label key="jmiAssure.assureType" />
		<jecs:list listCode="assure_type" name="assureType" value="${param.assureType}" defaultValue="" showBlankLine="false"/>
	 </div>
	 <div class="new_searchBar">
		<jecs:label key="jmiAssure.isFlag" />
		<jecs:list listCode="assure_is_flag" name="isFlag" value="${param.isFlag}" defaultValue="" showBlankLine="false"/>
	 </div>
	 <div class="new_searchBar">
	 	<button type="submit" class="btn btn_sele"><jecs:locale  key='operation.button.search'/></button> 
		<%--<input type="submit" class="button" value="<jecs:locale  key='operation.button.search'/>" />--%>
		<jecs:power powerCode="addJmiAssure">
			<button type="button" class="btn btn_ins" style="margin-right: 5px" 
				onclick="location.href='<c:url value="/editJmiAssure.html"/>?strAction=addJmiAssure'">
				<jecs:locale key="button.add"/>
			</button>
			<%-- 
			 <input type="button" class="button" style="margin-right: 5px" 
				 onclick="location.href='<c:url value="/editJmiAssure.html"/>?strAction=addJmiAssure'"
				 value="<jecs:locale key="button.add"/>"/>
			--%>
		</jecs:power>
		<button type="button" onclick="deleteJmiAssure();" class="btn btn_ins">删除</button>
		<jecs:power powerCode="addJmiAssure">
			<button type="button" class="btn btn_long" style="margin-right: 5px" 
				onclick="location.href='<c:url value="/jmiAssureListImport.html"/>?strAction=addJmiAssure'">
				<jecs:locale key="button.import"/>
			</button>
		</jecs:power>
		
		<button type="button" onclick="syncJmiAssure();" class="btn btn_long">同步达成担保</button>
		<%-- 
		<input type="button" onclick="deleteJmiAssure();" class="button" value="删除选中记录" />
		<input type="button" onclick="syncJmiAssure();" class="button" value="同步是否达成担保" />
		--%>
	</div>
	<input id="jmiAssureIds" name="jmiAssureIds" type="hidden"/>
	<input id="strAction" name="strAction" type="hidden"/>
</div>
</form>
<ec:table 
	tableId="jmiAssureListTable"
	items="jmiAssureList"
	var="jmiAssure"
	action="${pageContext.request.contextPath}/jmiAssures.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="images/extremetable/*.gif">
				<ec:row >
				<ec:column property="id" title="alCharacterKey.select1" sortable="false"  styleClass="centerColumn">
					<input type="checkbox" name="selectedId" id="selectedId" onclick="getIds()" value="${jmiAssure.id}" class="checkbox"/>
				</ec:column>

    			<ec:column property="assureType" title="jmiAssure.assureType" >
    				<jecs:code listCode="assure_type" value="${jmiAssure.assureType}"/>
    			</ec:column>
    			<ec:column property="assureContent" title="jmiAssure.assureContent" >
    				<jecs:code listCode="assure_content" value="${jmiAssure.assureContent}"/>
    			</ec:column>
    			<ec:column property="userCode" title="jmiAssure.userCode" />
   				<ec:column property="startTime" title="jmiAssure.startTime">
   					<fmt:formatDate value="${jmiAssure.startTime}" type="date" dateStyle="medium"/>
    			</ec:column>
    			<ec:column property="endTime" title="jmiAssure.endTime" >
    				<fmt:formatDate value="${jmiAssure.endTime}" type="date" dateStyle="medium"/>
    			</ec:column>
    			<ec:column property="isFlag" title="jmiAssure.isFlag" >
    				<jecs:code listCode="assure_is_flag" value="${jmiAssure.isFlag}"/>
    			</ec:column>
   				<%-- <ec:column property="assureOrderCode" title="jmiAssure.assureOrderCode" /> --%>
   				<ec:column property="createTime" title="jmiAssure.createTime" >
    				<fmt:formatDate value="${jmiAssure.createTime}" type="date" dateStyle="medium"/>
    			</ec:column>
    			<ec:column property="createUserCode" title="jmiAssure.createUserCode" />
    			
    			<ec:column property="1" title="title.operation" sortable="false" >
					
					<jecs:power powerCode="miMemberDetailView">
					<img src="images/newIcons/search_16.gif" onclick="window.location.href='<c:url value="/editJmiAssure.html"/>?strAction=viewJmiAssure&id=${jmiAssure.id }';" alt="<jecs:locale key="function.menu.view"/>" style="cursor:pointer"/>
					</jecs:power>
				</ec:column>
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editJmiAssure(id){
   		<jecs:power powerCode="editJmiAssure">
					window.location="editJmiAssure.html?strAction=editJmiAssure&id="+id;
			</jecs:power>
		}
   
   
   function syncJmiAssure(){
	   if(confirm("是否同步!")){
		  var ids = getIds();
		  if(''==ids){
			  alert('请选择要同步是否已达成担保的记录!');
		  }else{
			  $('#strAction').val('syncJmiAssure');
			  $('#jmiAssureIds').val(ids);
			  $('#searchForm').submit();
		  }
	   }
	}

   function deleteJmiAssure(){
	   if(confirm("<jecs:locale key="amMessage.confirmdelete"/>")){
		  var ids = getIds();
		  if(''==ids){
			  alert('请选择要删除的记录!');
		  }else{
			  //window.location="editJmiAssure.html?strAction=deleteJmiAssure&ids="+ids;
			  //$('#searchForm').attr('action','editJmiAssure.html');
			  $('#strAction').val('deleteJmiAssure');
			  $('#jmiAssureIds').val(ids);
			  $('#searchForm').submit();
		  }
	   }
	}
   function switchAll() {
	   	if ($("#selectedAll").attr("checked")) {  
	        $(":checkbox").attr("checked", true);
	        getIds();
	    } else {  
	        $(":checkbox").attr("checked", false);  
	    }
   }
   function getIds() {
	   var adIds = "";  
       $("input:checkbox[name=selectedId]:checked'").each(function(i){  
           if(0==i){  
               adIds = $(this).val();  
           }else{  
               adIds += (","+$(this).val());  
           }  
       });  
       $("#jmiAssureIds").val(adIds);
       return adIds;
   }
</script>
