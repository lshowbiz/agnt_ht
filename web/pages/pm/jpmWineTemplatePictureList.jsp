<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jpmWineTemplatePictureList.title"/></title>
<content tag="heading"><jecs:locale key="jpmWineTemplatePictureList.heading"/></content>
<meta name="menu" content="JpmWineTemplatePictureMenu"/>

<c:set var="buttons">
		<jecs:power powerCode="addJpmWineTemplatePicture">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editJpmWineTemplatePicture.html"/>?strAction=addJpmWineTemplatePicture'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power>
</c:set>
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>
<ec:table 
	tableId="jpmWineTemplatePictureListTable"
	items="jpmWineTemplatePictureList"
	var="jpmWineTemplatePicture"
	action="${pageContext.request.contextPath}/jpmWineTemplatePictures.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="/jecs/images/extremetable/*.gif">
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editJpmWineTemplatePicture('${jpmWineTemplatePicture.idf}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="pictureName" title="jpmWineTemplatePicture.pictureName" />
    			<%-- <ec:column property="subNo" title="jpmWineTemplatePicture.subNo" /> --%>
    			<ec:column property="subName" title="jpmWineTemplatePicture.subName" />
    			<ec:column property="pictureNameSrc" title="jpmWineTemplatePicture.pictureNameSrc" />
          
     			<ec:column property="pictureNameDist" title="jpmWineTemplatePicture.pictureView" >
                  <img width="120px;" height="90px;" src="${requestScope.fileUrl}${jpmWineTemplatePicture.pictureNameDist}">
                </ec:column>
          
    			<ec:column property="isInvalid" title="jpmWineTemplatePicture.isInvalid" >
                <jecs:code listCode="jpmwinetemplatepicture.isinvalid" value="${jpmWineTemplatePicture.isInvalid}"/>
          </ec:column>
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editJpmWineTemplatePicture(idf){
   		<jecs:power powerCode="editJpmWineTemplatePicture">
					window.location="editJpmWineTemplatePicture.html?strAction=editJpmWineTemplatePicture&idf="+idf;
			</jecs:power>
		}

</script>
