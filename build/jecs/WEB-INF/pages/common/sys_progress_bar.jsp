<%@ include file="/common/taglibs.jsp"%>
<%
String progressBarId=request.getParameter("progressBarId");
Object progressTotalCount=session.getAttribute(progressBarId+"ProgressTotalCount");
Object progressCurrentCount=session.getAttribute(progressBarId+"ProgressCurrentCount");

double totalCount=0;
double currentCount=0;
if(progressTotalCount!=null && progressCurrentCount!=null){
	totalCount=Double.parseDouble(progressTotalCount.toString());
	currentCount=Double.parseDouble(progressCurrentCount.toString());
}
out.print("{\"currentCount\":"+currentCount+",\"totalCount\":\""+totalCount+"\"}");
%>