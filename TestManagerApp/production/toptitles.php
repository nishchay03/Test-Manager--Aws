<!-- top tiles -->
            <div class="row tile_count" style="margin-left:150px">
                <div class="col-md-4 col-sm-4 col-xs-6 tile_stats_count">
                    <span class="count_top"><i class="fa"></i> Total Projects</span>
                    <div id="projectTotal" class="count"></div>
                </div>
                <div class="col-md-4 col-sm-4 col-xs-6 tile_stats_count">
                    <span class="count_top"><i class="fa"></i> Total TestCases</span>
                    <div id="testcasesTotal" class="count"></div>
                </div>
                <div class="col-md-4 col-sm-4 col-xs-6 tile_stats_count">
                    <span class="count_top"><i class="fa"></i> Total Bugs Reported</span>
                    <div id="bugsTotal" class="count"></div>
                </div>
            </div>
            <style>
                .tile_count .tile_stats_count:before {
                    border-left: none;
                }

                .tile_count .tile_stats_count .count {
                    margin-left:27px;
                }

                .tile_count .tile_stats_count .count_top {
                    font-size:18px;
                }

            </style>
<!-- /top tiles -->