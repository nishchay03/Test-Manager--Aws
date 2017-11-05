/**
 * Created by kanika on 5/3/17.
 */
$(function(){
    $(document).ready(function () {
       var crudServiceBaseUrl = window.options.apiBaseUrl,
       //var crudServiceBaseUrl = "http://localhost:8080/TestManagerMT/rest",
           dataSource = new kendo.data.DataSource({
               transport: {
                   read:  {
                       url: crudServiceBaseUrl + "/projects",
                       dataType: "json"
                   },
                   destroy: {
                       url: function(e) {
                           var id = e.models[0].id;
                           return crudServiceBaseUrl + "/projects/" + id;
                       },
                       contentType: "application/json",
                       type: "DELETE"
                   },
                   update: {
                       url: crudServiceBaseUrl + "/projects",
                       contentType: "application/json",
                       type: "PUT"
                   },
                   create: {
                       url: crudServiceBaseUrl + "/projects",
                       contentType: "application/json",
                       type: "POST"
                   },
                   parameterMap: function(options, operation) {
                       if (operation !== "read" && options.models) {
                           $.each(options.models, function(index, value ) {
                               if(value.startDate) {
                                   var d = value.startDate;
                                   var seconds = Math.round(d.getTime() / 1000);
                                   value.startDate = seconds;
                               }
                               if(value.endDate) {
                                   var d1 = value.endDate;
                                   var seconds1 = Math.round(d1.getTime() / 1000);
                                   value.endDate = seconds1;
                               }

                               if(operation == "create") {
                                   delete value.id;
                               }
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
                           if (item.startDate) {
                               var utcSeconds = item.startDate;
                               var d = new Date(0); // The 0 there is the key, which sets the date to the epoch
                               d.setUTCSeconds(utcSeconds);
                               item.startDate = d;
                           }
                           if (item.endDate) {
                               var utcSeconds = item.endDate;
                               var d = new Date(0); // The 0 there is the key, which sets the date to the epoch
                               d.setUTCSeconds(utcSeconds);
                               item.endDate = d;
                           }
                       }
                       return data;
                   },
                   model: {
                       id: "id",
                       fields: {
                           id: { editable: false },
                           name: { type: "string" },
                           owner: { type: "string" },
                           description: { type: "string" },
                           hours: { type: "number" },
                           rate: { type: "number" },
                           startDate: { type: "date" },
                           endDate: { type: "date" }
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
           //height: 550,
           toolbar: [{name: "create", text: "Create Project"}],
           //toolbar: ["create"],
           columns: [
               { field: "name", title: "Name", format: "{0:c}", width: "120px" },
               { field: "description", title: "Description" },
               { field: "owner", title: "Owner", format: "{0:c}", width: "90px" },
               { field: "hours", title:"Work Hours", width: "85px" },
               { field: "rate", title:"Hourly rate", width: "85px" },
               { field: "startDate", title:"Posted On", template: "#= kendo.toString(kendo.parseDate(startDate), 'MM/dd/yyyy') #", width: "110px" },
               { field: "endDate", title:"Deadline", template: "#= kendo.toString(kendo.parseDate(startDate), 'MM/dd/yyyy') #", width: "110px" },
               { command: ["edit", "destroy"], title: "&nbsp;", width: "180px" }],
           editable: "inline"
       });
   });
});