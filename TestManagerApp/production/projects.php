<!DOCTYPE html>
<html lang="en">
    <?php include "head.php" ?>

  <body class="nav-md">
    <div class="container body">
      <div class="main_container">
      <?php include "leftnav.php" ?>
      <?php include "topnav.php" ?>
        <!-- page content -->
        <div class="right_col" role="main">
          <div class="">
            <!--<div class="row">
              <div class="col-md-12">
                <div class="x_panel">
                  <div class="x_title">
                    <h2>Project Summary <small>Weekly progress</small></h2>
                    <div class="filter">
                      <div id="reportrange" class="pull-right" style="background: #fff; cursor: pointer; padding: 5px 10px; border: 1px solid #ccc">
                        <i class="glyphicon glyphicon-calendar fa fa-calendar"></i>
                        <span>December 30, 2014 - January 28, 2015</span> <b class="caret"></b>
                      </div>
                    </div>
                    <div class="clearfix"></div>
                  </div>
                  <div class="x_content">
                    <div class="col-md-12 col-sm-12 col-xs-12">
                      <div class="demo-container" style="height:280px">
                        <div id="chart_plot_02" class="demo-placeholder"></div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div> -->
            <div class="row">
                <div class="col-md-12">
                    <h4 class="page-head-line">Projects</h4>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12 col-sm-12 col-xs-12">
                    <div id="projectsGrid">
                        <div id="grid"></div>
                    </div>
                </div>
            </div>
          </div>
        </div>
        <!-- /page content -->
        <?php include "footer.php" ?>
      </div>
    </div>
    <?php include "scripts.php" ?>
    <script type="application/javascript">
        $(document).ready(function() {
            if (typeof(Storage) !== "undefined") {
                var userId = localStorage.getItem("username");
                if(userId && userId !== "") {
                    //do nothing
                } else {
                    window.location.href = "login.php"
                }
            }
        });
    </script>
    <script src="js/grid/projects.js"></script>
  </body>
</html>