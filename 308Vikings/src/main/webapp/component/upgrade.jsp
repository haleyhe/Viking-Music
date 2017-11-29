<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
  <head>
    <c:url var="home" value="/" scope="request"/>
  </head>
  <body>
    <div class="pages" id="historyPage">
      <div style="margin-left: 50px; margin-top: 20px; margin-bottom: 10px;">

        <div class="pages">
          <div class='container' style="padding: 20px;">

            <div id="upgrade-form-container" ng-hide="user.premium">
              <h1>
                Upgrade to Premium now
              </h1>
              <form id="upgrade-form">
                <div id="address-container">
                  First Name: <input type="text" ng-model="payment.name.firstName" required/> <br>
                  Last Name: <input type="text" ng-model="payment.name.lastName" required/> <br>
                  Addres Line 1: <input type="text" ng-model="street1" required/> <br>
                  Addres Line 2 (optional): <input type="text" ng-model="street2"/> <br>
                  City <input type="text" ng-model="payment.billingAddress.city" required/> <br>
                  State <input type="text" ng-model="payment.billingAddress.state" required/> <br>
                  Zipcode <input type="text" ng-model="payment.billingAddress.zip" required/> <br>
                  Phone Number <input type="text" ng-model="payment.phoneNum" required/> <br><br>
                </div>

                <div id="credit-card-container">
                  Credit Card Type <select ng-model="payment.creditCardCompany" ng-options="x for x in ccCompanies" form="upgrade-form"></select> <br>
                  Credit Card Number: <input type="text" ng-model="payment.cardNumber" required/> <br>
                  CVV: <input type="text" ng-model="payment.cvv" required/> <br>
                  Expiration Date: <input type="number"  min="1" max="12" placeholder="MM"ng-model="expiration.month" required/>
                  <input type="number"  min="1" max="31" placeholder="YY" ng-model="expiration.year" required/> <br>
                </div>
                <button type="submit" ng-click="upgrade()" class="pageButton"> Upgrade</button>
                <button type="submit" ng-click="resetForm()" class="pageButton"> Reset</button>
              </form>
            </div>

            <div id="upgraded-container" ng-show="user.premium">
                <h1> Congratulations! You are now a premium user! </h1>

                <form id ='downgrade-form'>
                  <button type="submit" ng-click="downgrade()" class="pageButton"> Downgrade</button>
                </form>
            </div>

          </div>
        </div>
      </div>
    </body>
  </html>
