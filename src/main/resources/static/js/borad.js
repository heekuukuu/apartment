  let index = {
     init: function(){
        $("#btn-save").on("click",() => {
         this.save();
         });
         $("#btn-update").on("click",()=> {
           this.update();
           });
         },



         save: function() {
         let data = {
               title: $("#title").val(),
               category: $("#category").val(),
               content: $("#content"). val(),
               };
             $.ajax({
                 type:"POST",
                 url: "/api/board",
                 data: JSON.stringify(data),
                 contentType: "application/json; charset=utf-8",
                 })
                 .done(function(resp) {
                   alert("Success Save Point");
                   location.href = "/";
                    }).fail(function(error){
                     alert("Failed Save Post");
                     });
                  },


                 update: function() {
                   let id =$("#id").val();
                   let data = {
                       title: $("#title").val(),
                        category: $("#category").val(),
                        content: $("content").val(),
                        };

                   $.ajax({
                        type: "PUT",
                         url: "/api/board/" + id,
                         data: JSON.stringify(data),
                         contentType: "application/json; charset=utf-8",
                         }).done(function(resp){
                           alert("Success Update Post");
                           location.href ="/";
                           }).fail(function(error) {
                              alert("Failed Update Post");
                           })
                         },

                 deleteById: function(boardId) {
                  $.ajax({
                          type: "DELETE",
                          url: '/api/board/${boardId}',
                          contentType: "application/json; charset=utf-8",
                          }).done(function(resp){
                          alert("Success Delete Post");
                          location.href ="/";
                          }).fail(function(error){
                          alert("Failed Delete Post");
                          });
                          },
     };

     index.init();