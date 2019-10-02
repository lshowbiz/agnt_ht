<%@ include file="/common/taglibs.jsp"%>
<c:if test="${isNotify == true }">
${returnMsg }
</c:if>
<c:if test="${isNotify == false }">
<script>window.close();</script>
</c:if>
