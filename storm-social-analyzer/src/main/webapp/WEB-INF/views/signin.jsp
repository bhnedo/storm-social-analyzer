<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<form id="signin" action="<c:url value="/signin/authenticate" />" method="post">
	
	<fieldset>
		<label for="login">Username</label>
		<input id="login" name="j_username" type="text" value="storm" size="25" <c:if test="${not empty signinErrorMessage}">value="${SPRING_SECURITY_LAST_USERNAME}"</c:if> />
		<label for="password">Password</label>
		<input id="password" name="j_password" type="password" size="25" value="storm" />	
	</fieldset>
	<button type="submit">Sign In</button>
</form>

	
	