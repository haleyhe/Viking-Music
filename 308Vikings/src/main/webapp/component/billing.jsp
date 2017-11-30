<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <c:url var="home" value="/" scope="request" />
        <script type="text/javascript" src="<c:url value="/js/app.js" />"></script>
    </head>
    <body>
        <div class=pages id=profilepage>
          <div class="container">

          <ul class="tabs">
            <li class="tab-link current" data-tab="tab-1">Payment</li>
            <li class="tab-link" data-tab="tab-2">View Monthly Summary</li>
          </ul>

          <div id="tab-1" class="tab-content current">
            <ul class=userinfo>
              <li style="font-size: 2em; font-weight: bold">Payments</li>
              <li id="field">Username</li>
              <li >viking123</li>
              <li id="field">Email</li>
              <li>viking123@gmail.com</li>
              <li id="field">Date of Birth</li>
              <li>09/30/17</li>
              <li id="field">Country</li>
              <li>US</li>
            </ul>
          </div>
          <div id="tab-2" class="tab-content">
            <ul class=recentlyplayedartist>
              <li style="font-size: 2em; font-weight: bold">View Monthly Summary</li>
            </ul>
          </div>
          </div>
        </div>
    </body>
</html>
