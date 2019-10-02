<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html; charset=utf-8" %>

<%@ taglib uri="http://acegisecurity.org/authz" prefix="authz" %>
<%@ taglib uri="http://www.springmodules.org/tags/commons-validator" prefix="v" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://struts-menu.sf.net/tag-el" prefix="menu" %>

<%@ taglib uri="/WEB-INF/core.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/fmt-rt.tld" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/sql.tld" prefix="sql" %>
<%@ taglib uri="/WEB-INF/t.tld"   prefix="t" %>
<%@ taglib uri="/WEB-INF/fn.tld"   prefix="fn" %>
<%@ taglib uri="/WEB-INF/extremecomponents.tld" prefix="ec" %>
<%@ taglib uri="http://www.opensymphony.com/oscache" prefix="cache" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"%>
<%@ taglib uri="/WEB-INF/tld/jecs.tld" prefix="jecs" %>
<%@ taglib prefix="elf" uri="/WEB-INF/elfunc.tld"%>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>