$(document).ready(
		function() {

			$("#formUserRegistration").submit(function(event) {				
				event.preventDefault();
				userRegistration();
			});
			
			$("#formUserLogin").submit(function(event) {				
				event.preventDefault();
				userLogin();
			});

			function userRegistration() 
			{
				var formData = {
					firstName : $("#firstName").val(),
					lastName : $("#lastName").val(),
					emailId : $("#emailId").val(),
					mobile : $("#mobile").val(),
					password : $("#password").val()
				}

				$.ajax({
					type : "POST",
					contentType : "application/json",
					url : "/user/signup",
					data : JSON.stringify(formData),
					dataType : 'json',
					success : function(result) {					
						if (result.status == "success") {
														
							$("#divSuccessMsg").html("<div class='alert alert-primary alert-dismissible fade show'>'"+result.data
											+"' Successfully Registred !! Congrats !!<button type='button' class='close' data-dismiss='alert'>&times;</button> </div>");	
											
							document.getElementById("formUserRegistration").reset();
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
			
			function userLogin()
			 {
				$.ajax({
					type : "POST",
					contentType : "application/json",
					url : "/oauth/token?grant_type=password"
							+"&client-id=64b04dd8-cf4f-4356-958b-765e3d8f4338"
							+"&username="+$("#emailId").val()
							+"&password="+ $("#password").val(),
					dataType : 'json',
					headers: {
					    "Authorization": "Basic NjRiMDRkZDgtY2Y0Zi00MzU2LTk1OGItNzY1ZTNkOGY0MzM4OjQ1OTcxNGExLTFhMmQtNGYyZS1hNTdlLTgxNWJmYzcxYTYyNQ=="
					},
					success : function(result) {						
						console.log(result);
						document.getElementById("formUserLogin").reset();
						localStorage.setItem("token", result.access_token);
						window.location.href ="/profile";					
					},
					error : function(e) {
						$("#divFailedMsg").html("<div class='alert alert-danger alert-dismissible fade show'>"
											+"Bad Credentials !! Try Again !!<button type='button' class='close' data-dismiss='alert'>&times;</button> </div>");	
								
						document.getElementById("formUserLogin").reset();
						console.log("ERROR: ", e);
					}
				});
			}			

})