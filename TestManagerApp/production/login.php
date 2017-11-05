<!DOCTYPE html>
<html lang="en">
<?php include "head.php" ?>
  <body class="login">
    <div>
      <a class="hiddenanchor" id="signin"></a>
      <div class="login_wrapper">
        <div class="animate form login_form">
          <section class="login_content">
            <form>
              <h1>Tester Login</h1>
              <div>
                <input id="usernameId" type="text" class="form-control" placeholder="Username" required="" />
              </div>
              <div>
                <input type="password" class="form-control" placeholder="Password" required="" />
              </div>
              <div>
                <a id="loginId" class="btn btn-default submit">Log in</a>
                <a class="reset_pass" href="#">Lost your password?</a>
              </div>
              <div class="clearfix"></div>
              <div class="separator">
                <p class="change_link">New to site?
                  <a href="signup.php" class="to_register"> Create Account </a>
                </p>
                <div class="clearfix"></div>
                <br />
                <div>
                  <h1><i class="fa fa-paw"></i></h1>
                    <p>CMPE 281 Cloud Technologies (Team 6)</p>
                </div>
              </div>
            </form>
          </section>
        </div>
      </div>
    </div>
  <?php include "scripts.php" ?>
  <script type="application/javascript">
      $(document).ready(function() {
          if (typeof(Storage) !== "undefined") {
              localStorage.setItem("username", "");
          }

          $("#loginId").on("click", function(e) {
              e.preventDefault();
              var userId = $("#usernameId").val();

              if (typeof(Storage) !== "undefined") {
                  localStorage.setItem("username", userId);
              }

              window.location.href = "index.php";
          });
      });
  </script>
  </body>
</html>
