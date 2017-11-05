/**
 * Created by kanika on 5/3/17.
 */
$(function() {
    $(document).ready(function() {
        $("#grid").hide();

        var crudServiceBaseUrl = window.options.apiBaseUrl,
            $grid = $("#grid"),
            $dropdown = $("#projectsDropDown");


        $dropdown.kendoDropDownList({
            dataTextField: "name",
            dataValueField: "id",
            optionLabel: "Select a project...",
            change: function (e) {
                var value = this.value();
                $("#hiddenInputId").val(value);

                if(value && value !== 0) {
                    $("#grid").show();
                    $grid.data("kendoGrid").dataSource.read();
                } else {
                    $("#grid").hide();
                    $grid.data("kendoGrid").dataSource.data([]);
                }
            },
            dataSource: {
                transport: {
                    read: {
                        dataType: "json",
                        url: crudServiceBaseUrl + "/projects",
                    }
                }
            }
        });


        var crudServiceBaseUrl = window.options.apiBaseUrl,
            //var crudServiceBaseUrl = "http://localhost:8080/TestManagerMT/rest",
            dataSource = new kendo.data.DataSource({
                transport: {
                    read:  {
                        url: function() {
                            var projectId = $("#hiddenInputId").val();
                            return crudServiceBaseUrl + "/testcases/" + projectId;
                        },
                        dataType: "json"
                    },
                    destroy: {
                        url: function(e) {
                            var id = e.models[0].id;
                            return crudServiceBaseUrl + "/testcases/" + id;
                        },
                        contentType: "application/json",
                        type: "DELETE"
                    },
                    update: {
                        url: crudServiceBaseUrl + "/testcases",
                        contentType: "application/json",
                        type: "PUT"
                    },
                    create: {
                        url: crudServiceBaseUrl + "/testcases",
                        contentType: "application/json",
                        type: "POST"
                    },
                    parameterMap: function(options, operation) {
                        if (operation !== "read" && options.models) {
                            var projectId = $("#hiddenInputId").val();

                            $.each(options.models, function(index, value ) {
                                if(value.createdAt) {
                                    var d = value.createdAt;
                                    var seconds = Math.round(d.getTime() / 1000);
                                    value.createdAt = seconds;
                                }

                                if(operation == "create") {
                                    delete value.id;
                                }

                                value.projectId = parseInt(projectId);

                            });

                            return kendo.stringify(options.models[0]);
                            //return { models: kendo.stringify(options.models) };
                        }
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
                    data: function (data) {
                        for (var i = 0; i < data.length; i++) {
                            var item = data[i];
                            if (item.createdAt) {
                                var utcSeconds = item.createdAt;
                                var d = new Date(0); // The 0 there is the key, which sets the date to the epoch
                                d.setUTCSeconds(utcSeconds);
                                item.createdAt = d;
                            }
                        }
                        return data;
                    },
                    model: {
                        id: "id",
                        fields: {
                            id: { editable: false },
                            name: { type: "string" },
                            description: { type: "string" },
                            owner: { type: "string" },
                            createdAt: { type: "date" },
                            projectId: {type: "number"}
                        }
                    }
                }
            });

        $grid.kendoGrid({
            dataSource: dataSource,
            sortable: {
                mode: "single",
                allowUnsort: true
            },
            pageable: true,
            autoBind: false,
            //height: 550,
            toolbar: [{name: "create", text: "Create TestCases"}],
            columns: [
                { field: "name", title: "Name", format: "{0:c}", width: "200px" },
                { field: "description", title: "Description" },
                { field: "owner", title: "Owner", format: "{0:c}", width: "90px" },
                { field: "createdAt", title:"Created On", template: "#= kendo.toString(kendo.parseDate(createdAt), 'MM/dd/yyyy') #", width: "110px" },
                { command: ["edit", "destroy"], title: "&nbsp;", width: "180px" }],
            editable: "inline"
        });
    });
});
