<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jpoMemberOrderDetail.title"/></title>
<content tag="heading"><jecs:locale key="jpoMemberOrderDetail.heading"/></content>
<link rel="stylesheet" href="./css/user.css">
<link rel="stylesheet" href="load_images/WebResource.css">
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css">

<script src="<c:url value='/dwr/util.js'/>"></script> 
<script src="<c:url value='/dwr/engine.js'/>"></script>
<script src="<c:url value='/dwr/interface/alCityManager.js'/>"></script>
<script src="<c:url value='/dwr/interface/alDistrictManager.js'/>"></script>
<script src="<c:url value='/dwr/interface/jalTownManager.js'/>"></script>

<!--<script src="load_images/prototype.js"></script>-->
<script src="load_images/load_pic.js"></script>
<script src="./scripts/calendar/calendar.js"></script> 
<script src="./scripts/calendar/calendar-setup.js"></script> 
<script src="./scripts/calendar/lang.jsp"></script> 
<script>
<!--
var objPeerTip=new PeerTip();// -->
Close_PIC=function(Name)
{
	var iobj=eval(Name);
	var span_obj=eval(Name);	
	span_obj.style.display="none";	
}
</script>







<form:form commandName="jpoMemberOrder" method="post" action="editJpoMemberROrder.html" onsubmit="return onSubmitCheck(this);" id="jpoMemberOrderForm">

<input type="hidden" name="strAction" value="${param.strAction }"/>
	<div class="searchBar">
			<c:out value="${buttons}" escapeXml="false"/>
	</div>







<div id="wrap">
<div id="body">	
<div id="main" style="overflow:hidden;">
<div id="tabCot_product" class="tab">
<div class="tabContainer">
	<ul class="tabHead" id="tabCot_product-li-currentBtn-">
	<c:forEach items="${poMemberMeetingUserCode}" var="pm" varStatus="status">
	<c:if test="${status.count==1}">
	<li class="currentBtn">
	</c:if>
	<c:if test="${status.count!=1}">
	<li class="">
	</c:if>
	<a href="javascript:void(0)"><jecs:code listCode="pomember.meeting" value="${pm.key}"/></a></li>
 </c:forEach>
		
	</ul>
</div>

<c:forEach  items="${poMemberMeetingUserCode}" var="pms" varStatus="sta">

<div id="tabCot_product_${sta.count}"  class="tabCot" />
        <table>
        <tr  style="background-color:#E6E6E6;font-size: 9pt;vertical-align: top;"><td><jecs:locale  key="jpoOrder.usercode"/></td>
        <td><jecs:locale  key="jpoOrder.count"/></td>
        <td><jecs:locale  key="jpoOrderteam.count"/></td>
        </tr>
        <tr>
          <c:forEach  items="${pms.value}"  var="item"  varStatus='status'> 
             <td>
                ${item} </td>
          <c:if test="${status.count%3==0}">
            </tr>
            <tr>
         </c:if>
          </c:forEach>
          
        </table>
        </div>
</c:forEach>
<div  class="tabCotF">

		</td>
	</tr>

</table>
</div>


</div>
</div>
<div class="clear"></div>	
</div>
</div>
<div class="noprint">	
<script type="text/javascript" src="./scripts/tab.js"></script>
</div>
<div class="sidebar">
</div>
</div>

</form:form>



<v:javascript formName="jpoMemberOrder" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
