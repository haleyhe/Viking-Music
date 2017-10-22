<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Vikings - Profile Page</title>
    </head>

    <body>
        <h1>Vikings - Profile</h1>
        <p><b>User ID:</b> ${user.id}</p>
        <p><b>Username:</b> ${user.username}</p>
        <p><b>Email:</b> ${user.email}</p>
        <p><b>ZIP:</b> ${user.zip}</p>
        <p><b>Premium:</b> ${user.premium}</p>
        <p><b>Admin:</b> ${user.admin}</p>
    </body>
</html>
