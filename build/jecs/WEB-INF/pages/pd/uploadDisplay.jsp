<%@ include file="/common/taglibs.jsp"%>

<title><wecs:locale key="display.title"/></title>
<content tag="heading"><wecs:locale key="display.heading"/></content>
<meta name="menu" content="FileUpload"/>

<p>Finished.Below is a list of attributes that were gathered in UPS file upload!</p>

<div class="separator"></div>

<table class="detail" cellpadding="5">
  
    <tr>
        <td></td>
        <td class="buttonBar">
            <input type="button" name="done" id="done" value="Done"
                onclick="location.href='mainMenu.html'" />
            <input type="button" style="width: 120px" value="Upload Another"
                onclick="location.href='upsFileUpload.html?strAction=uploadTrackNo'" />
        </td>
    </tr>
</table>


