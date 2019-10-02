<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=utf-8" language="java"%>
<title><jecs:locale key="fiBcoinJournalDetail.title" />
</title>
<content tag="heading">
<jecs:locale key="fiBcoinJournalDetail.heading" />
</content>

<form id="form1" name="form1" enctype="multipart/form-data"
	method="post" action="">
	<div id="titleBar">
		<c:out value="${buttons}" escapeXml="false" />
	</div>

	<table class='detail' width="70%">
		<tbody class="window">				
			<tr class="edit_tr">
				<th>
					<span class="text-font-style-tc">
						<label for="money" class="required">
							<span class="req">*</span>
							<jecs:locale key="label.member.or.agent.code" />
							:
						</label>
					</span>
				</th>
				<td>
					<span class="textbox">
				 		<input type="text" name="userCode" id="userCode" class="textbox-text"/>
				 	</span>
				</td>		
				<th>
					<span class="text-font-style-tc">
						<label for="money" class="required">
							<span class="req">*</span>
							<jecs:locale key="busi.finance.amount" />
							:
						</label>
					</span>
				</th>
				<td>
					<span class="textbox">
				 		<input type="text" name="amount" id="amount" maxlength="8" class="textbox-text"/>
				 	</span>
				</td>		
			</tr>
			<tr class="edit_tr">
				<th>
					<span class="text-font-style-tc-tare">
						<jecs:label key="bdBounsDeduct.summary" />
					</span>
				</th>
				<td colspan="3" >
					<span class="text-font-style-tc-right">
				 		<textarea name="remark" id="remark" cols="45" rows="5" class="textarea_border"></textarea>
				 	</span>
				</td>			
			</tr>
			<tr class="edit_tr">
				<th>
					<span class="text-font-style-tc-tare">
						<jecs:label key="bdBounsDeduct.description" />
					</span>
				</th>
				<td colspan="3" >
					<span class="text-font-style-tc-right">
				 		<textarea name="description" id="description" cols="45" rows="5" class="textarea_border"></textarea>
				 	</span>
				</td>			
			</tr>
			<tr class="edit_tr">
				<th>
					<label for="xlsFile" class="required">
						<jecs:locale key="fiPayData.importFile" />
						:
					</label>
				</th>
				<td colspan="3" >
				 	<input type="file" name="xlsFile" id="xlsFile" size="50" />
					<font color='red'>xls导入文件格式：会员编号，金额，摘要(全为必填项，第一行为标题)</font>
				</td>			
			</tr>
			<tr>
				<td class="command" colspan="4" align="center">
					<button type="submit" class="btn btn_sele" name="save"
					value="<jecs:locale key="operation.button.save"/>" ><jecs:locale key="operation.button.save"/></button>
					<button type="button" class="btn btn_sele" name="cancel" onclick="history.back();" value="<jecs:locale key="operation.button.cancel"/>" >
			<jecs:locale key="operation.button.cancel"/>
			</button>
				</td>
			</tr>
		</tbody>
	</table>
</form>