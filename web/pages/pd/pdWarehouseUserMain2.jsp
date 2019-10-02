<%@ include file="/common/taglibs.jsp"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><jecs:locale key="alCountryList.title"/></title>
</head>


<frameset cols="200,*" frameborder="1">
	<frame src="<c:url value='/sys_my_man_m_tree.html'/>?strAction=pdWarehouseUserTree2&companyCode=${companyCode}" name="userTreeFrame" id ='userTreeFrame' frameborder="1"/>
	<frame src="" id="pdWarehouseContorl" name="pdWarehouseContorl" frameborder="1"/>
	
	
</frameset>




<script type="text/javascript">

   function editPdWarehouseUser(wuId){
   		<jecs:power powerCode="editPdWarehouseUser">
					window.location="editPdWarehouseUser.html?strAction=editPdWarehouseUser&wuId="+wuId;
			</jecs:power>
		}

</script>
<noframes><body>
</body>
</noframes>
</html>