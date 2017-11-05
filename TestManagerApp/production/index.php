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
            <?php include "toptitles.php" ?>
            <div class="row">
                <div class="col-md-6 col-sm-6 col-xs-6">
                    <div class="dashboard_graph">
                        <div id="testCasesChart">
                            <div class="demo-section k-content wide">
                                <div id="testCasesPerProjectChart"></div>
                            </div>
                        </div>
                        <div class="clearfix"></div>
                    </div>
                </div>
                <div class="col-md-6 col-sm-6 col-xs-6">
                    <div class="dashboard_graph">
                        <div id="bugsChart">
                            <div class="demo-section k-content wide">
                                <div id="bugsPerProjectChart"></div>
                            </div>
                        </div>
                        <div class="clearfix"></div>
                    </div>
                </div>
            </div>
            <br/>
            <div class="row">
                <div class="col-md-12 col-sm-12 col-xs-12">
                    <div class="dashboard_graph">
                        <div>
                            <h4>Projects Overview</h4>
                        </div>
                        <div id="grid"></div>
                        <div class="clearfix"></div>
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
<script src="js/index.js"></script>
<script src="js/grid/projectDetails.js"></script>
</body>
</html>
