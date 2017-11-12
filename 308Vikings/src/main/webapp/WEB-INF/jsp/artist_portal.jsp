<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <c:url var="home" value="/" scope="request" />
        <script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.2.1.min.js"></script>
         <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=0.5, user-scalable=no">
        <link href='http://fonts.googleapis.com/css?family=Julius Sans One:400;300' rel='stylesheet' type='text/css'>
        <link href="https://fonts.googleapis.com/css?family=Raleway:300,400" rel="stylesheet" type='text/css'>
        <link href="<c:url value="/css/style.css" />" rel="stylesheet">
        <script type="text/javascript" src="http://code.jquery.com/jquery-1.9.1.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular.min.js"></script>
        <script type="text/javascript" src="<c:url value="/js/artist_startup.js" />"></script>
        <script type="text/javascript" src="<c:url value="/js/app.js" />"></script>
        <script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.2.1.min.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <title>Vikings Artists</title>
    </head>
    <body>
        congrats you logged in
    </body>
    <script>
       window.onload = function () {
            if (! localStorage.justOnce) {
                localStorage.setItem("justOnce", "true");
                window.location.reload();
            }
        };
    </script>
</html>
