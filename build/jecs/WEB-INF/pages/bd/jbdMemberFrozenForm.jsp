<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" language="java"%>

<form:form commandName="jbdMemberFrozen" method="post" action="editJbdMemberFrozen.html" enctype="multipart/form-data">
	<input type="hidden" id="strAction" name="strAction" value="${param.strAction}"/>
	<c:if test="${param.strAction=='addJbdMemberFrozen'}">
		<table class='detail' width="70%">
			<tbody class="window">				
				<tr class="edit_tr">
					<th>
						<span class="text-font-style-tc">
							会员编号：
						</span>
					</th>
					<td colspan="3" >
						<span class="textbox">
					 		<input type="text" name="userCode" class="textbox-text" value="${jbdMemberFrozen.userCode}" maxlength="20" size="20"/>
						</span>
					</td>			
				</tr>
				<tr>
					<td class="command" colspan="4" align="center">
						<button type="submit" class="btn btn_sele" name="save">
							保存
						</button>
						<button type="button" class="btn btn_sele" name="back" onclick="window.location='${pageContext.request.contextPath}/jbdMemberFrozens.html?strAction=viewJbdMemberFrozen'">
							返回
						</button>
					</td>
				</tr>
			</tbody>
		</table>
	</c:if>
	<c:if test="${param.strAction=='importJbdMemberFrozen'}">
		<table class='detail' width="70%">
			<tbody class="window">				
				<tr class="edit_tr">
					<th width="40%">
						 导入文件：
					</th>
					<td colspan="3" >
					 	 <input type="file" name="xlsFile" id="xlsFile" size="50"/>
					</td>			
				</tr>
				<tr class="edit_tr">
					<th>
						 备注：
					</th>
					<td colspan="3" >
					 	 第1行为抬头不导入，从第1列第2行起为导入的会员编号，空行不导入。
					</td>			
				</tr>
				<tr>
					<td class="command" colspan="4" align="center">
						<button type="submit" class="btn btn_sele" name="save">
							导入
						</button>
						<button type="button" class="btn btn_sele" name="back" onclick="window.location='${pageContext.request.contextPath}/jbdMemberFrozens.html?strAction=viewJbdMemberFrozen'">
							返回
						</button>
					</td>
				</tr>
			</tbody>
		</table>
	</c:if>
</form:form>