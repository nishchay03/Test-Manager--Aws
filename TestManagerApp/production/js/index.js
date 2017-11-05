/**
 * Created by kanika on 5/4/17.
 */
$(function(){
    $(document).ready(function () {
        var url = window.options.apiBaseUrl + "/projectdetails";

        $.get(url, function (data) {
            var projects = {},
                testcasesCounter = 0,
                bugsCounter = 0,
                testcases = [],
                bugs = [],
                projectBugsMap = {},
                projectTestcasesMap = {};

            $.each(data, function(idx, val){
                projects[val.id] = val.projectName;

                //this is a bugs
                if(val.bugName !== "NA") {
                    bugsCounter++;
                    if(projectBugsMap.hasOwnProperty(val.id)) {
                        projectBugsMap[val.id] += 1;
                    } else {
                        projectBugsMap[val.id] = 1;
                    }
                } else { //this is a testcases
                    testcasesCounter++;
                    if(projectTestcasesMap.hasOwnProperty(val.id)) {
                        projectTestcasesMap[val.id] += 1;
                    } else {
                        projectTestcasesMap[val.id] = 1;
                    }
                }
            });


            $.each(projectBugsMap, function(key){
                var value = projectBugsMap[key];
                bugs.push({"name": projects[key], "count" : value });

            });

            $.each(projectTestcasesMap, function(key){
                var value = projectTestcasesMap[key];
                testcases.push({"name": projects[key], "count" : value });
            });

            createTestcasesChart(testcases);
            createBugsChart(bugs);

            setDashboard(projects, bugsCounter, testcasesCounter);
        });

        function setDashboard(projects, bugs, testcases) {
            $("#projectTotal").text(Object.keys(projects).length);
            $("#bugsTotal").text(bugs);
            $("#testcasesTotal").text(testcases);
        }


        function createBugsChart(projectBugs) {
            $("#bugsPerProjectChart").kendoChart({
                dataSource: {
                    data: projectBugs
                },
                title: {
                    //align: "left",
                    text: "Bugs filed per Project"
                },
                legend: {
                    visible: false
                },
                seriesDefaults: {
                    type: "column",
                    labels: {
                        visible: true,
                        background: "transparent"
                    }
                },
                series: [{
                    field: "count",
                    //colorField: "userColor"
                }],
                valueAxis: {
                    max: 28,
                    majorGridLines: {
                        visible: false
                    },
                    visible: false
                },
                categoryAxis: {
                    field: "name",
                    majorGridLines: {
                        visible: false
                    },
                    line: {
                        visible: false
                    }
                }
            });
        }

        function createTestcasesChart(projectTestcases) {
            $("#testCasesPerProjectChart").kendoChart({
                dataSource: {
                    data: projectTestcases
                },
                title: {
                    text: "TestCases created per Project"
                },
                legend: {
                    visible: false
                },
                seriesDefaults: {
                    type: "line",
                    labels: {
                        visible: true,
                        //format: "{0}%",
                        background: "transparent"
                    }
                },
                series: [{
                    field: "count",
                    name: "United States"
                }],
                valueAxis: {
                    labels: {
                        //format: "{0}%"
                    },
                    line: {
                        visible: false
                    }
                },
                categoryAxis: {
                    field: "name",
                    majorGridLines: {
                        visible: false
                    }
                }
            });
        }
    });
});