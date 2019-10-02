<%@ include file="/common/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="utf-8">

	<body>
		<form action="" method="get" name="searchForm" id="searchForm">
			<input type="hidden" name="strAdviceCodes" value="" />
			<input type="hidden" name="strAction" value="orderSearchT" />
		</form>

		<form id="poMemberOrderSearchListTable"
			action="/jecs/poMemberOrderTSearch.html" method="get">

			<div class="eXtremeTable">
				<table id="poMemberOrderSearchListTable_table" border="0"
					cellspacing="0" cellpadding="4" class="tableRegion" width="100%">
					<thead>
						<tr>
							<td class="tableHeader">
								<jecs:locale key="sysOperationLog.moduleName"/>					</td>
							<td class="tableHeader">
								<jecs:locale key="download.file"/>	</td>
							<td class="tableHeader"><jecs:locale key="sysDataLog.changeType"/></td>
						</tr>
					</thead>
					<tbody class="tableBody">





						<tr class="odd" >
							<td class="centerColumn">
							<img src="../images/icons/download.gif" onclick="window.location.href='';"  style="cursor:pointer"/>
					</td>

							<td>W9_Form_1.pdf</td>

							<td>pdf</td>

						</tr>



						<tr class="even" >
							<td class="centerColumn"><img src="../images/icons/download.gif" onclick="window.location.href='';"  style="cursor:pointer"/>
					</td>

							<td>W9_Form_2.pdf</td>

							<td>pdf</td>

						</tr>

						<%--<tr class="odd" >
							<td class="centerColumn"><img src="../images/icons/download.gif" onclick="window.location.href='http://www.winalite.com/download/memberdownload/20090702/Customer Service Feedback Form.pdf';"  style="cursor:pointer"/>
					</td>

							<td>Customer Service Feedback Form.pdf</td>

							<td>pdf</td>

						</tr>--%>




						<tr style="padding: 0px;">
							<td colspan="4">&nbsp;							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</form>

	</body>
</html>
