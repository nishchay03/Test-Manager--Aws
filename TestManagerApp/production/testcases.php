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
              <!-- <div class="row">
              <div class="col-md-12 col-sm-12 col-xs-12">
                <div class="dashboard_graph x_panel">
                  <div class="row x_title">
                    <div class="col-md-6">
                      <h3>Test Cases <small>testcases per number of projects</small></h3>
                    </div>
                    <div class="col-md-6">
                      <div id="reportrange" class="pull-right" style="background: #fff; cursor: pointer; padding: 5px 10px; border: 1px solid #ccc">
                        <i class="glyphicon glyphicon-calendar fa fa-calendar"></i>
                        <span>December 30, 2014 - January 28, 2015</span> <b class="caret"></b>
                      </div>
                    </div>
                  </div>
                  <div class="x_content">
                    <div class="demo-container" style="height:250px">
                      <div id="chart_plot_03" class="demo-placeholder"></div>
                    </div>
                  </div>
                </div>
              </div>
            </div> -->
              <div class="row">
                  <br/>
                  <br/>
              </div>
              <div class="row">
                  <div class="col-md-3 col-sm-3 col-xs-3">
                      <input id="projectsDropDown" style="width: 100%;" />
                  </div>
                  <div class="col-md-6 col-sm6 col-xs-6">
                      <br/><br/>
                  </div>
              </div>
              <div class="row">
                  <div class="col-md-12 col-sm-12 col-xs-12">
                      <div id="testcasesGrid">
                          <div id="grid"></div>
                      </div>
                  </div>
              </div>
          </div>
            <input id="hiddenInputId" style="display:none;"/>
        </div>
        <!-- /page content -->
        <?php include "footer.php" ?>
      </div>
    </div>
    <?php include "scripts.php" ?>
    <script src="js/grid/testcases.js"></script>
  </body>
</html>