<!DOCTYPE html>
<html lang="en">
<?php include "head.php" ?>
<body class="login">
    <div>
        <a class="hiddenanchor" id="signup"></a>
        <div class="login_wrapper">
            <div id="register" class="animate form registration_form" style="z-index:0;opacity:1;">
              <section class="login_content">
                  <?php
                    if (isset($_REQUEST['user_id'])) {
                        $user_id = stripslashes($_REQUEST['user_id']);
                        echo '<div style="font-size:20px;">';
                        echo "<p>User ";
                        echo "<b>";
                        echo $user_id;
                        echo "</b>";
                        echo " has been created</p>";
                        echo '<a href="login.php">Click here</a> to login';
                        echo "</div>";
                  } else {
                  ?>
                    <form name="registration" action="" method="post" class="form-horizontal">
                      <h1>Create Account</h1>
                      <div>
                        <input name="user_id" type="text" class="form-control" placeholder="Username" required="" />
                      </div>
                      <div>
                        <input name="email" type="email" class="form-control" placeholder="Email" required="" />
                      </div>
                      <div>
                        <input name="password" type="password" class="form-control" placeholder="Password" required="" />
                      </div>
                      <div>
                        <input type="submit" class="btn btn-default submit" name="submit" value="Create" style="margin-left:130px;"/>
                      </div>
                      <div class="clearfix"></div>
                      <div class="separator">
                        <p class="change_link">Already a member ?
                          <a href="login.php" class="to_register"> Log in </a>
                        </p>
                        <div class="clearfix"></div>
                        <br />
                        <div>
                          <h1><i class="fa fa-paw"></i> </h1>
                            <p>CMPE 281 Cloud Technologies (Team 6)</p>
                        </div>
                      </div>
                    </form>
                  <?php } ?>
              </section>
            </div>
        </div>
    </div>
    </body>
</html>


