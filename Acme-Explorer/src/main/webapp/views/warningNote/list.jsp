<%--
 * action-1.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<jsp:useBean id="now" class="java.util.Date"/>

<security:authentication property="principal" var="loggedactor"/>

<display:table name="warningNotes" id="row" requestURI="${requestURI}"
	pagesize="5">
	
	<jstl:if test="${row.gauge == 1}">
		<jstl:set var="style" value="background-color:green;" />
	</jstl:if>
	
	<jstl:if test="${row.gauge == 2}">
		<jstl:set var="style" value="background-color:yellow;" />
	</jstl:if>
	
	<jstl:if test="${row.gauge == 3}">
		<jstl:set var="style" value="background-color:red;" />
	</jstl:if>
	
	<security:authorize access="hasRole('MANAGER')">
	<spring:message code="warningNote.moment" var="momentHeader" />
 	<jstl:choose>
		<jstl:when test="${pageContext.response.locale.language=='en'}">
	 		<display:column property="moment" title="${momentHeader}" 
	 		 sortable="true" format="{0,date,yy/MM/dd HH:mm}"  style="${style}"/> 
	 	</jstl:when>
	 	<jstl:otherwise>
	 		<display:column property="moment" title="${momentHeader}"
	 		sortable="true" format="{0,date,dd-MM-yy HH:mm}" style="${style}"/> 
	 	</jstl:otherwise>
 	</jstl:choose>
	</security:authorize>
	
	<spring:message code="warningNote.gauge" var="gaugeHeader" />
	<display:column property="gauge" title="${gaugeHeader}" sortable="true" style="${style}"/>
	
	<spring:message code="warningNote.title" var="titleHeader" />
	<display:column property="title" title="${titleHeader}" style="${style}"/>
	
	<spring:message code="warningNote.description" var="descriptionHeader" />
	<display:column property="description" title="${descriptionHeader}" style="${style}"/>
	
	<security:authorize access="hasRole('MANAGER')">
	
	<spring:message code="warningNote.trip" var="tripHeader" />
	<display:column property="trip.title" title="${tripHeader}" style="${style}"/>
	<spring:message code="warningNote.edit" var="editHeader" />
	<display:column title="${editHeader}">
	<jstl:if test="${row.manager.userAccount.username==loggedactor.username}">
		<a href="warningNote/manager/edit.do?warningNoteId=${row.id}">
			<spring:message code="warningNote.edit" />
		</a>
	</jstl:if>
	</display:column>
	
	
	</security:authorize>
</display:table>