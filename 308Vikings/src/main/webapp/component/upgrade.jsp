<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
  <head>
    <c:url var="home" value="/" scope="request"/>
  </head>
  <body>
      <div style="margin-left: 50px; margin-top: 20px; margin-bottom: 10px;">

        <div class="pages">
          <div class='container' style="padding: 20px;">
            <div id="upgrade-form-container" ng-hide="premium">
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
                  <input type="number"  min="1" max="99" placeholder="YY" ng-model="expiration.year" required/> <br>
                </div>
                <button type="submit" ng-click="upgrade()" class="pageButton"> Upgrade</button>
                <button type="submit" ng-click="resetForm()" class="pageButton"> Reset</button>
              </form>
            </div>

            <div id="upgraded-container" ng-show="premium">
                <h1> You are a premium user! </h1>
                <hr class="style14" style="width:70%">
                <div>
                    <h2> Payment Information </h2>
                    <ul class=menutabs>
                    <li><b>Name:</b> {{payment.name.firstName}} {{payment.name.lastName}}</li>
                    <li><b>Card Number:</b> {{payment.cardNumber}}</li>
                    <li><b>Expiration Date:</b> {{payment.expirationDate | date:'MM/yy'}} </li>
                    <li><b>Address:</b> {{payment.billingAddress.street}} </li>
                    <li><b>City:</b> {{payment.billingAddress.city}}</li>
                    <li><b>State:</b> {{payment.billingAddress.state}}</li>
                    <li><b>Zipcode:</b> {{payment.billingAddress.zip}}</li>
                    <li><b>Phone Number:</b> {{payment.phoneNum}}</li>
                  </ul>

                </div>
                <form id ='downgrade-form'>
                  <button type="submit" ng-click="downgrade()" class="pageButton"> Downgrade</button>
                </form>
            </div>

          </div>
        </div>
      </div>
    </body>
  </html>
