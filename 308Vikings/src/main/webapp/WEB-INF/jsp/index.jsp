<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome to Spring Web MVC project</title>
    </head>

    <body>
        <p>Hello World!</p>
        <p>Did I get a database connection? ${result}</p>
        <p><b><a href="<%=request.getContextPath() %>/signup">Sign Up</a></b></p>
        <p><b><a href="<%=request.getContextPath() %>/signin">Sign In</a></b></p>
    </body>
</html>
