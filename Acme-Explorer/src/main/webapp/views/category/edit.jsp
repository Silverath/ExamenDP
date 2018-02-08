<%--
 * edit.jsp
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
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<form:form action="category/administrator/edit.do" modelAttribute="category">

<form:hidden path="id"/>
<form:hidden path="version"/>
<form:hidden path="trips"/>

<form:label path="name">
<spring:message code="category.name"/>
</form:label>
<form:input path="name"/>
<form:errors cssClass="error" path="name"/>
<br><br>	
	
<form:label path="parent">
<spring:message code="category.parent"/>
</form:label>
    	
<form:select name="parent" path="parent">

     <jstl:forEach var="category" items="${categories}">
     	
    		<form:option value="${category.id}" label="${map.get(category.id)}"/>
    	
     </jstl:forEach>
</form:select>
<form:errors cssClass="error" path="parent"/>
<br><br>	
<input type="submit" name="save" value="<spring:message code="category.save"/>"/>

<jstl:if test="${category.id>0}">

<input type="submit" name="delete" value="<spring:message code="category.delete"/>"/>

</jstl:if>

<input type="button" name="cancel" value="<spring:message code="category.cancel"/>" onclick="javascript: relativeRedir('category/administrator/list.do')" />		
</form:form>

