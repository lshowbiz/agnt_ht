<%@ page language="java" errorPage="/error.jsp" contentType="text/html; charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jpoUserCouponList.title"/></title>
<content tag="heading"><jecs:locale key="jpoUserCouponList.heading"/></content>
<meta name="menu" content="JpoUserCouponMenu"/>

<form action="" method="get" name="searchForm" id="searchForm">
	<div class="searchBar">
		<div class="new_searchBar">
			<jecs:title key="jpoUserCoupon.userCode" />
			<input name="userCode" type="text" value="${param.userCode}" size="10" />
		</div>
		<div class="new_searchBar">
			<jecs:title key="jpmCouponInfo.cpName" />
			<input name="cpName" type="text" value="${param.cpName}" size="10" />
		</div>
		<div class="new_searchBar">
			使用状态:
			<jecs:list name="status" listCode="jpousercouponlist.status" value="${param.status}" defaultValue="" showBlankLine="true"/>	
		</div>
		<div class="new_searchBar">
			启用状态:
			<jecs:list name="avlestatus" listCode="jpousercouponinfo.avlestatuslist" value="${param.avlestatus}" defaultValue="" showBlankLine="true"/>	
		</div>
		<!-- 创建时间区间 -->
		<div class="new_searchBar">
			创建时间区间:
			<input id="createStartTime" name="createStartTime" type="text" 
				value="${param.createStartTime }" style="cursor: pointer;width:75px;" 
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',startDate:'%y-%M-%d 00:00:00',readOnly:true})"/>
				- 
			<input id="createEndTime" name="createEndTime" type="text" 
				value="${param.createEndTime }" style="cursor: pointer;width:75px;" 
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',startDate:'%y-%M-%d 00:00:00',readOnly:true})"/>
		</div>
		<!-- 使用时间区间 -->
		<div class="new_searchBar">
			使用时间区间:
			<input id="syStartTime" name="syStartTime" type="text" 
				value="${param.syStartTime }" style="cursor: pointer;width:75px;" 
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',startDate:'%y-%M-%d 00:00:00',readOnly:true})"/>
				- 
			<input id="syEndTime" name="syEndTime" type="text" 
				value="${param.syEndTime }" style="cursor: pointer;width:75px;" 
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',startDate:'%y-%M-%d 00:00:00',readOnly:true})"/>
		</div>
		<div class="new_searchBar">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<button type="submit" class="btn btn_sele" name="cancel">
				<jecs:locale key="operation.button.search" />
			</button>
			<button type="button" class="btn btn_long" name="cancel" onclick="batchAudit()">
				启用/禁用
			</button>
			<script type="text/javascript">
			function batchAudit(){
				var selectedId = document.getElementsByName("selectedId");
				var selectStr = '';
				for(var i=0;i<selectedId.length;i++){ 
					if(selectedId[i].checked){
						selectStr += selectedId[i].value+",";
					}
				}  
				if(selectStr==''){
					alert("<jecs:locale key='amMessage.discuss.select'/>");//?
					return;
				}else{
					if(confirm('确定要执行此操作吗?')){
						window.location="jpoUserCoupons.html?strAction=updateCoupon&ids="+selectStr; 
					}else{
						return;
					}
				}
			}
			</script>
			<button type="button" class="btn btn_sele" name="cancel" onclick="window.location='jpoUserCoupons.html?strAction=glCoupon'">
				批量赠送代金券
			</button>
		</div>
	</div>
</form>
</div>
<ec:table 
	tableId="jpoUserCouponListTable"
	items="jpoUserCoupons"
	var="jpoUserCoupon"
	action="${pageContext.request.contextPath}/jpoUserCoupons.html"
	width="100%"
	autoIncludeParameters="true"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="false" imagePath="./images/extremetable/*.gif">
				<ec:row >
				<ec:column property="1" title="alCharacterKey.select1" sortable="false" styleClass="centerColumn">
					<c:if test="${jpoUserCoupon.status!=1}">
						<input type="checkbox" name="selectedId" id="selectedId" value="${jpoUserCoupon.id}" class="checkbox"/>
					</c:if>
				</ec:column>
				<ec:column property="able_status" title="启用状态">
					<c:if test="${jpoUserCoupon.able_status==''||jpoUserCoupon.able_status=='Y'}">
					启用
    				</c:if>
    				<c:if test="${jpoUserCoupon.able_status=='N'}">
					禁用
    				</c:if>
    			</ec:column>
    			<ec:column property="user_code" title="jpoUserCoupon.userCode"/>
    			<ec:column property="cp_name" title="jpmCouponInfo.cpName"/>
    			<ec:column property="cp_value" title="jpmCouponInfo.cpValue"/>
    			<ec:column property="status" title="使用状态">
    				<jecs:code listCode="jpousercouponlist.status" value="${jpoUserCoupon.status}" />
    			</ec:column>
    			<ec:column property="create_time" title="pd.createTime"/>
    			<ec:column property="sy_time" title="使用时间"/> 
    			<ec:column property="remark" title="jpoUserCoupon.remark"/>
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editJpoUserCoupon(id){
   		<jecs:power powerCode="editJpoUserCoupon">
					window.location="editJpoUserCoupon.html?strAction=editJpoUserCoupon&id="+id;
			</jecs:power>
		}

</script>
