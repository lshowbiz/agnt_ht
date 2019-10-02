<%@ include file="/common/taglibs.jsp"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><jecs:locale key="alCountryList.title"/></title>
</head>


<frameset cols="150,*" frameborder="1">
	<frame src="<c:url value='pdShipStrategys.html'/>?strAction=pdShipStrategyList" id="strategyList" name="strategyList" frameborder="1"/>
	<frame src="" name="strategyContorl" id ='strategyContorl' frameborder="1"/>
	
	
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