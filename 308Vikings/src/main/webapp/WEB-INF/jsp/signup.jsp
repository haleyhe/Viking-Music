<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Vikings - Sign Up</title>
    </head>
    <body>
        <h1>Vikings - Sign Up</h1>
        <form:form action="registerUser" method="post" modelAttribute="newUser">
          <table>
            <tr>
                <td>Username</td>
                <td><form:input path="username" /></td>
            </tr>
            <tr>
                <td>Email</td>
                <td><form:input path="email" /></td>
            </tr>
            <tr>
                <td>ZIP</td>
                <td><form:input path="zip" /></td>
            </tr>
            <tr>
                <td>Password</td>
                <td><form:password path="password" /></td>
            </tr>
            <tr>
                <td></td>
                <td><form:button>Submit</form:button></td>
            </tr>
          </table>
        </form:form>
    </body>
</html>
