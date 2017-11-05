/**
 * Created by kanika on 5/3/17.
 */
$(function(){
    $(document).ready(function () {
        var crudServiceBaseUrl = window.options.apiBaseUrl,
            dataSource = new kendo.data.DataSource({
                transport: {
                    read:  {
                        url: crudServiceBaseUrl + "/projectdetails",
                        dataType: "json"
                    }
                },
                requestEnd: function(e) {
                    var type = e.type;
                    if(type && type !== "read") {
                        this.read();
                    }
                },
                error: function(e) {
                    alert("Failed to perform the action.");
                    console.log(e);
                },
                batch: true,
                pageSize: 20,
                schema: {
                    model: {
                        id: "id",
                        fields: {
                            id: { type:"number", editable: false },
                            projectName: { type: "string" },
                            projectDescription: { type: "string" },
                            bugId: { type: "number" },
                            bugName: { type: "string" },
                            bugDescription: { type: "string" },
                            testcaseId: { type: "number" },
                            testcaseName: { type: "string" },
                            testcaseDescription: { type: "string" }
                        }
                    }
                }
            });

        $("#grid").kendoGrid({
            dataSource: dataSource,
            sortable: {
                mode: "single",
                allowUnsort: true
            },
            pageable: true,
            columns: [
                { field: "projectName", title: "Project Name" },
                { field: "projectDescription", title: "Project Description" },
                { field: "bugName", title: "Bug Name" },
                { field: "bugDescription", title: "Bug Description" },
                { field: "testcaseName", title: "Testcase Name" },
                { field: "testcaseDescription", title: "Testcase Description" }
                ]
        });
    });
});