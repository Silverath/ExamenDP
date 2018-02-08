<%--
 * action-2.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form action="${requestURI}" modelAttribute="warningNote">

	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="ticker"/>
	<form:hidden path="trip"/>
	<form:hidden path="manager"/>
	
	
	<form:label path="title">
		<spring:message code="warningNote.title"/>
	</form:label>
	<form:input path="title"/>
	<form:errors cssClass="error" path="title"/>
	<br/>
	
	<form:label path="description">
		<spring:message code="warningNote.description"/>
	</form:label>
	<form:textarea path="description"/>
	<form:errors cssClass="error" path="description"/>
	<br/>
	
	<form:label path="moment">	
		<spring:message code="warningNote.moment"/>	
	</form:label>
	<form:input path="moment"/> (dd/MM/yyyy HH:mm)		
	<form:errors cssClass="error" path="moment"/>	
	<br/>
	
	<form:label path="gauge">
		<spring:message code="warningNote.gauge"/>
	</form:label>
	<form:input path="gauge"/>
	<form:errors cssClass="error" path="gauge"/>
	<br/>
	<br/>
	
	<input type="submit" name="save" value="<spring:message code="warningNote.save"/>"/>
	<input type="button" name="cancel" value="<spring:message code="warningNote.cancel"/>" onclick="javascript: relativeRedir('welcome/index.do');"/>
	<jstl:if test="${warningNote.id != 0}">
		<input type="submit" name="delete" value="<spring:message code="warningNote.delete"/>"/>
	</jstl:if>
	
</form:form>
