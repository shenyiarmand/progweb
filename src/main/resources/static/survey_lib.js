$(document).ready(
		function() {
			
			getAllSurvey();	
					

			function getAllSurvey()
			 {		
				$.ajax({
					type : "GET",
					contentType : "application/json",
					url : "/survey",
					dataType : 'json',
					headers: {
					    "Authorization": "Bearer "+localStorage.getItem("token")
					},
					success : function(result) {						
						var trHTML = '';
						$("#tBody").empty();
						for (i = 0; i < result.data.length; i++) {
                                trHTML +=
                                    '<tr><td class="text-center">'
                                    + result.data[i].id
                                    + '</td><td class="text-center">'
                                    + result.data[i].title
                                    + '</td><td>'
                                    + result.data[i].description
                                    + '</td><td class="text-center">'
                                    + result.data[i].city
                                    + '</td><td class="text-center">'
                                    + result.data[i].state
                                    + '</td><td class="text-center">'
                                    + result.data[i].country
                                    + '</td><td class="text-center">'
                                    + result.data[i].zip 
                                    + '</td><td class="text-center">'
                                    + result.data[i].surveyDate
                                    + '</td><td class="text-center">'
                                    + '<button type="button" class="btn btn-success" id="btnEdit">Edit</button>'
                                    + '</td><td class="text-center">'
                                    + '<button type="button" class="btn btn-danger" id="btnDelete">Delete</button>'
                                    + '</td></tr>';
                            }                        
                        $('#tBody').append(trHTML);						
					},
					error : function(e) {						
						console.log("ERROR: ", e);
					}
				});
			}
			

			$("#formNewSurvey").submit(function(event) {				
				event.preventDefault();				
				saveSurvey();
			});
			
			function saveSurvey() 
			{
				var formData = {
					title : $("#title").val(),
					description : $("#description").val(),
					city : $("#city").val(),
					state : $("#state").val(),
					country : $("#country").val(),
					zip : $("#zip").val(),
					surveyDate : $("#surveyDate").val()					
				}
				
				var id = $("#id").val();
				
				if(id.length != 0)
				{
					$.ajax({
						type : "PUT",
						contentType : "application/json",
						url : "/survey/"+id,
						data : JSON.stringify(formData),
						dataType : 'json',
						headers: {
						    "Authorization": "Bearer "+localStorage.getItem("token")
						},
						success : function(result) {	
								
							$("#divSuccessMsg").html("<div class='alert alert-primary alert-dismissible fade show'>Survey '"+$("#title").val()
											+"' Successfully Updated !! <button type='button' class='close' data-dismiss='alert'>&times;</button> </div>");							
							
							document.getElementById("formNewSurvey").reset();
							getAllSurvey();
							
						},
						error : function(e) {
							alert("Error!")
							console.log("ERROR: ", e);
						}
					});
				}
				else
				{
					$.ajax({
						type : "POST",
						contentType : "application/json",
						url : "/survey",
						data : JSON.stringify(formData),
						dataType : 'json',
						headers: {
						    "Authorization": "Bearer "+localStorage.getItem("token")
						},
						success : function(result) {					
							if (result.status == "success") {
																
								$("#divSuccessMsg").html("<div class='alert alert-primary alert-dismissible fade show'>Survey '"+result.data.title
											+"' Successfully Saved !! <button type='button' class='close' data-dismiss='alert'>&times;</button> </div>");	
											
								document.getElementById("formNewSurvey").reset();
								getAllSurvey();
							}
						},
						error : function(e) {
							alert("Error!")
							console.log("ERROR: ", e);
						}
					});
				}
			}
			

			$('.table tbody').on('click','#btnDelete',function() 
			{
		        var currow = $(this).closest('tr');   
		        var id = currow.find('td:eq(0)').text();	
		        deleteSurvey(id);
			});
				 
			function deleteSurvey(id)
			 {	
				$.ajax({
					type : "DELETE",
					contentType : "application/json",
					url : "/survey/"+id,
					dataType : 'json',
					headers: {
					    "Authorization": "Bearer "+localStorage.getItem("token")
					},
					success : function(result) {						
						if (result.status == "success") {
														
							$("#divSuccessMsg").html("<div class='alert alert-primary alert-dismissible fade show'>Survey '"+result.data
											+"' Successfully Deleted !! <button type='button' class='close' data-dismiss='alert'>&times;</button> </div>");						
							getAllSurvey();
						} 											
					},
					error : function(e) {						
						console.log("ERROR: ", e);
					}
				});
			}
	

		$('.table tbody').on('click','#btnEdit',function() 
			{
		        var currow = $(this).closest('tr');   
		        
		        $("#id").val(currow.find('td:eq(0)').text());
		        $("#title").val(currow.find('td:eq(1)').text());
				$("#description").val(currow.find('td:eq(2)').text());
				$("#city").val(currow.find('td:eq(3)').text());
				$("#state").val(currow.find('td:eq(4)').text());
				$("#country").val(currow.find('td:eq(5)').text());
				$("#zip").val(currow.find('td:eq(6)').text());
				$("#surveyDate").val(currow.find('td:eq(7)').text());
				$("#btnSave").text('Update');		        	
			});
			
})