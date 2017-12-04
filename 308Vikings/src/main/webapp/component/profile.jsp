<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
  <head>
    <c:url var="home" value="/" scope="request"/>
  </head>
  <body>
    <div class="pages" id="profilePage">
      <div id="account-overview-container">
        <div class="container">
          <h1> Account Overview </h1>
          <form id="profile-form">
                Username: <input id="username-input" type="text" ng-model="editUser.username" readonly/> <br>
                Email: <input type="email" ng-model="editUser.email" placeholder="Email"/> <br>
                DOB: <input type="date" ng-model="editUser.dateOfBirth"/> <br>
                Location Zipcode <input type="text" ng-model="editUser.zip"> <br>
                <button type="submit" ng-click="updateUser()" class="pageButton"> Update </button>
                <button type="submit" ng-click="resetForm()" class="pageButton"> Reset</button>
          </form>
        </div>
        <br>
        <a ng-click="showChangePassword()"> Click Here to Change your Password </a>
      </div>

      <div id="change-password-container">
        <div class="container">
          <h1> Change Password </h1>
          <form name="reset-password-form">
                Old Password: <input type="password" ng-model="password.old" required/> <br>
                New Password: <input type="password" ng-model="password.new" required/> <br>
                Retype New Password: <input type="password" ng-model="password.retype" required/>
                <span ng-show="password.new != password.retype"> Passwords do not match. </span> <br>
                <button type="submit" ng-click="changePassword()"  ng-disabled="password.new != password.retype">
                  Change password
                </button>
          </form>
          <br>
          <a ng-click="showUpdateAccount()"> Back to overview </a>
        </div>
      </div>

      <br>
      <br>
      <div id="delete-account-container">
        <div class="container">
          <form id="profile-form">
                <button type="submit" ng-click="showDeleteConfirmation()" class="pageButton"> Delete Account</button>
          </form>
        </div>
      </div>

    </div>

    <!-- Delete Account Modal -->
    <div id="deleteAccountModal">
      <div class="delete modal">
        <div class=modal-content>
          <div>Delete Account</div>
          <hr class="style15" style="width:70%">
            <form id="delete-playlist-form">
                  <p> Are you sure you want to delete your account? </p>
                  <button type="submit" class="pageButton" ng-click="deleteAccount()">Yes</button>
                  <button class="pageButton" ng-click="closeDeleteConfirmation()">No</button>
                </div>
            </form>
        </div>
        </div>
    </div>
  </body>
</html>
