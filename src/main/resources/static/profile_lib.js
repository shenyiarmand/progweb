$(document).ready(
		function() {
			
			getProfile();	
			
			function getProfile() 
			{
				$.ajax({
						type : "GET",
						contentType : "application/json",
						url : "/user",						
						dataType : 'json',
						headers: {
						    "Authorization": "Bearer "+localStorage.getItem("token")
						},
						success : function(result) {					
							if (result.status == "success") {
																							
								$("#id").val(result.data.id);
								$("#firstName").val(result.data.firstName);
								$("#lastName").val(result.data.lastName);
								$("#emailId").val(result.data.emailId);
								$("#mobile").val(result.data.mobile);
								$("#password").val("-------");
							}
						},
						error : function(e) {
							alert("Error!")
							console.log("ERROR: ", e);
						}
					});
			}
			
			$("#formUserUpdation").submit(function(event) {				
				event.preventDefault();
				updateUserInfo();
			});
			
			function updateUserInfo()
			{
				var formData = {
					firstName : $("#firstName").val(),
					lastName : $("#lastName").val(),
					emailId : $("#emailId").val(),
					mobile : $("#mobile").val()
				}

				$.ajax({
					type : "PUT",
					contentType : "application/json",
					url : "/user",
					data : JSON.stringify(formData),
					dataType : 'json',
					headers: {
						    "Authorization": "Bearer "+localStorage.getItem("token")
					},
					success : function(result) {					
						if (result.status == "success") {
														
							$("#divSuccessMsg").html("<div class='alert alert-primary alert-dismissible fade show'>"
											+"Profile Successfully Updated !! <button type='button' class='close' data-dismiss='alert'>&times;</button> </div>");	
											
						} else {
							$("#divRegistrationFailed").html("<strong>Error</strong>");
						}
						console.log(result);
					},
					error : function(e) {
						alert("Error!")
						console.log("ERROR: ", e);
					}
				});
			}
			
			$('#openSurvey').on('click',function() 
			{
				window.location.href ="/allSurvey";
			});
})