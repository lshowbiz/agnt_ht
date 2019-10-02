<%@ include file="/common/taglibs.jsp"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><jecs:locale key="alCountryList.title"/></title>
</head>


<frameset cols="150,*" frameborder="1">
	<frame src="<c:url value='amMessagePermits.html'/>?strAction=amMessagePermitTree" id="amMessagePermitList" name="amMessagePermitList" frameborder="1"/>
	<frame src="" name="amMessagePermitContorl" id ='amMessagePermitContorl' frameborder="1"/>
	
	
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