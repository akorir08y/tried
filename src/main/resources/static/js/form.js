var hosted_url = location.origin;

function validationForm(){
     var password = document.getElementById("password").value;
     if (password === ''){
            $(".responseDiv").html("<div class=\"alert alert-danger\">Pin Must be Filled Out</div>");
            $(".responseDiv").fadeOut(3000);
            return false;
     }else if(password.length < 4){
            $(".responseDiv").html("<div class=\"alert alert-danger\">Pin must 4 Digits</div>");
            $(".responseDiv").fadeOut(3000);
            return false;
     }
}


// Authenticate Registered Member Request(Client Side Way of Logging In)
function AuthMemberRequest(){

    // Form data
	var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;

    if(username == ""){
        $(".responseDiv").html("<div class=\"alert alert-danger\">Phone Must be Filled Out</div>");
        $(".responseDiv").fadeOut(3000);
         return false;
    }else if (password === ''){
        $(".responseDiv").html("<div class=\"alert alert-danger\">Pin Must be Filled Out</div>");
        $(".responseDiv").fadeOut(3000);
        return false;
    }else if(password.length < 4){
        $(".responseDiv").html("<div class=\"alert alert-danger\">Pin must 4 Digits</div>");
        $(".responseDiv").fadeOut(3000);
        return false;
    }

    // Form Validation
    /*
    var validator = $("#myForm").validate();
    validator.form();
    */



    // Phone Number and Amount
    console.log("Username: "+ username);
    console.log("Password: "+ password);

    // Ajax STK Push Request
    let myObj = { username: username, password:password };

    $.ajax({
        type: "POST",
        url: "/cfms/auth/login",
        datatype: "json",
        data: myObj,
        success: function(response){
            console.log(response);
            //var finalResponse = JSON.parse(response);

            if (response.state != null){
                document.getElementById("phone_number").value = username;
                document.getElementById("otp_id").style.display = "block";
            }else if(response.state == null){
                $(".responseDiv").html("<div class=\"alert alert-danger\">Error : "+response.error+"</div>");
                $(".responseDiv").fadeOut(3000);
            }

        },
        error: function(response){
            console.log(response);
        }

    });
}

function sendRegisterOTP(){
    startRegisterOTP();
    // Enable the OTP Input Fields

    // var validator = $("#otp_reg").validate();
    // validator.form();


    var otp_field12 = document.getElementById('otp-field13');

    const inputs = document.querySelectorAll(".otp-field input");
    inputs.forEach((input, index) => {
         input.disabled = false;
         input.value = "";
    });

    // Display the OTP Field
    otp_field12.style.display = "block";
    sendCode.style.display = "none";

    var username = document.getElementById("phone_number2").value;
    console.log("Sending OTP");
    console.log("Phone Number: " + username);

    // Ajax STK Push Request
    let myObj = { recipient: username};
    console.log(myObj);

         $.ajax({
                type: "POST",
                url: "/cfms/auth/otp",
                datatype: "json",
                data: myObj,
                success: function(response){
                    console.log(response);
                },
                error: function(response){
                    console.log(response);
                }

         });

}

// Register Member
// Authenticate Registered Member Request(Client Side Way of Logging In)
function AuthMemberRegister(){

    // var validator = $("#myForm2").validate();
    // validator.form();

    // Form data
	var username = document.getElementById("fullname").value;
	var email = document.getElementById("email").value;
    var password = document.getElementById("pin").value;
    var phone = document.getElementById("phone1").value;

    registeredNumbers();
    $("#myForm2").submit();
}

// OTP Timer
function startTimer(duration, display) {
    var username = document.getElementById("phone_number").value;
    var timer = duration, minutes, seconds;

    // getOTP();
    var x = setInterval(function () {
        minutes = parseInt(timer / 60, 10);
        seconds = parseInt(timer % 60, 10);

        minutes = minutes < 10 ? "0" + minutes : minutes;
        seconds = seconds < 10 ? "0" + seconds : seconds;

        display.textContent = minutes + ":" + seconds;


        if (--timer < 0) {
            timer = duration;
            document.getElementById('time').style.display='none';
            const inputs = document.querySelectorAll(".otp-field input");
            inputs.forEach((input, index) => {
                input.disabled = true;
            });
            document.getElementById('otp-field12').style.display='none';
            document.getElementById('sendCode').style.display ="block";
            clearInterval(x);
        }
    }, 1000);
}

// Start Register Timer
// OTP Timer
function startRegisterTimer(duration, display) {
    var username = document.getElementById("phone_number2").value;
    var timer = duration, minutes, seconds;

    // getOTP();
    var x = setInterval(function () {
        minutes = parseInt(timer / 60, 10);
        seconds = parseInt(timer % 60, 10);

        minutes = minutes < 10 ? "0" + minutes : minutes;
        seconds = seconds < 10 ? "0" + seconds : seconds;

        display.textContent = minutes + ":" + seconds;


        if (--timer < 0) {
            timer = duration;
            document.getElementById("send_otp_button2").innerText = "Resend OTP";
            document.getElementById('time2').style.display='none';
            const inputs = document.querySelectorAll(".otp-field input");
            inputs.forEach((input, index) => {
                input.disabled = true;
            });
            document.getElementById('otp-field13').style.display='none';
            clearInterval(x);
        }
    }, 1000);
}

// OTP Timer
function startResetTimer(duration, display) {
    var timer = duration, minutes, seconds;
    // getOTP();
    var x = setInterval(function () {
        minutes = parseInt(timer / 60, 10);
        seconds = parseInt(timer % 60, 10);

        minutes = minutes < 10 ? "0" + minutes : minutes;
        seconds = seconds < 10 ? "0" + seconds : seconds;

        display.textContent = minutes + ":" + seconds;


        if (--timer < 0) {
            timer = duration;
            document.getElementById("send-otp").innerText = "Resend OTP";
            document.getElementById('time1').style.display='none';
            const inputs = document.querySelectorAll(".otp-field input");
            inputs.forEach((input, index) => {
                input.disabled = true;
            });
            document.getElementById('otp-field11').style.display='none';
            clearInterval(x);
        }
    }, 1000);
}



// OTP Timer
function startResetPinTimer(duration, display) {
    var timer = duration, minutes, seconds;
    // getOTP();
    var x = setInterval(function () {
        minutes = parseInt(timer / 60, 10);
        seconds = parseInt(timer % 60, 10);

        minutes = minutes < 10 ? "0" + minutes : minutes;
        seconds = seconds < 10 ? "0" + seconds : seconds;

        display.textContent = minutes + ":" + seconds;


        if (--timer < 0) {
            timer = duration;
            document.getElementById("send_otp_button").innerText = "Resend OTP";
            document.getElementById('time').style.display='none';
            const inputs = document.querySelectorAll(".otp-field input");
            inputs.forEach((input, index) => {
                input.disabled = true;
            });
            document.getElementById('otp-field11').style.display='none';
            clearInterval(x);
        }
    }, 1000);
}

// Start OTP Function
function startOTP() {
    // document.getElementById('otp-zone').style.display='block';
    document.getElementById('time').style.display='block';
    var fiveMinutes = 60 * 1;
    display = document.querySelector('#time');
    startTimer(fiveMinutes, display);
}

// Start OTP Function
function startRegisterOTP() {
    // document.getElementById('otp-zone').style.display='block';
    document.getElementById('time2').style.display='block';
    var fiveMinutes = 60 * 1;
    display = document.querySelector('#time2');
    startRegisterTimer(fiveMinutes, display);
}

// Start OTP Function
function startResetOTP() {
    //document.getElementById('otp-zone').style.display='block';
    document.getElementById('time1').style.display='block';
    var fiveMinutes = 60 * 1;
    display = document.querySelector('#time1');
    startResetTimer(fiveMinutes, display);
}

// Start OTP Function for Reset Pin
function startResetPinOTP() {
    //document.getElementById('otp-zone').style.display='block';
    document.getElementById('time').style.display='block';
    var fiveMinutes = 60 * 1;
    display = document.querySelector('#time');
    startResetPinTimer(fiveMinutes, display);
}

function sendOTP(){
    startOTP();
    // Enable the OTP Input Fields
    // var validator = $("#otp_log").validate();
    // validator.form();


    var otp_field12 = document.getElementById('otp-field12');
    var sendCode = document.getElementById('sendCode');

    const inputs = document.querySelectorAll(".otp-field input");
    inputs.forEach((input, index) => {
         input.disabled = false;
         input.value = "";
    });

    // Display the OTP Field
    otp_field12.style.display = "block";
    sendCode.style.display = "none";

    var username = document.getElementById("phone_number").value;
    console.log("Sending OTP");
    console.log("Phone Number: " + username);

    // Ajax STK Push Request
    let myObj = { recipient: username};
    console.log(myObj);

         $.ajax({
                type: "POST",
                url: "/cfms/auth/otp",
                datatype: "json",
                data: myObj,
                success: function(response){
                    console.log(response);
                },
                error: function(response){
                    console.log(response);
                }

         });

}

function sendResetOTP(){
    startResetOTP();
    // Enable the OTP Input Fields
    const inputs = document.querySelectorAll(".otp-field input");
    inputs.forEach((input, index) => {
         input.disabled = false;
         input.value = "";
    });

    var username = document.getElementById("phone_number1").value;
    console.log("Sending OTP");
    console.log("Phone Number: " + username);

    // Ajax STK Push Request
    let myObj = { recipient: username};
    console.log(myObj);

         $.ajax({
                type: "POST",
                url: "/cfms/auth/otp",
                datatype: "json",
                data: myObj,
                success: function(response){
                    console.log(response);
                },
                error: function(response){
                    console.log(response);
                }

         });

}

// Send Reset Pin
function sendResetPinOTP(){
    startResetPinOTP();
    // Enable the OTP Input Fields
    const inputs = document.querySelectorAll(".otp-field input");
    inputs.forEach((input, index) => {
         input.disabled = false;
         input.value = "";
    });

    var username = document.getElementById("phone_number").value;
    console.log("Sending OTP");
    console.log("Phone Number: " + username);

    // Ajax STK Push Request
    let myObj = { recipient: username};
    console.log(myObj);

         $.ajax({
                type: "POST",
                url: "/cfms/auth/otp",
                datatype: "json",
                data: myObj,
                success: function(response){
                    console.log(response);
                },
                error: function(response){
                    console.log(response);
                }

         });

}

// Get the OTP Pin
function confirmOTP(){

     // Form data
     var username = document.getElementById("phone_number").value;
     const inputs = document.querySelectorAll(".otp-field input");
     var password = "";
     inputs.forEach((input, index) => {
           password += input.value;
     });

     console.log("Validating OTP");
     // Phone Number and Amount
     console.log("Username: "+ username);
     console.log("Password: "+ password);

     // Ajax STK Push Request
     let myObj = { phone_number: username, otp:password };
     console.log(myObj);

     $.ajax({
            type: "POST",
            url: "/cfms/auth/otp-pin",
            datatype: "json",
            data: myObj,
            success: function(response){
                console.log(response);
                // var finalResponse = JSON.parse(response);
                if(response === "OTP is valid!"){
                    $(".responseDiv").html("OTP has been Verified");
                    $(".responseDiv").fadeOut(3000);
                    sendToServer();
                }else{
                    $(".responseDiv").html(""+response+". Try Again</div>");
                    $(".responseDiv").fadeOut(3000);
                }
            },
            error: function(response){
                console.log(response);
            }

     });
}


// Confirm Register OTP
// Get the OTP Pin
function confirmRegisterOTP(){

     // Form data
     var username = document.getElementById("phone_number2").value;
     const inputs = document.querySelectorAll(".otp-field input");
     var password = "";
     inputs.forEach((input, index) => {
           password += input.value;
     });

     console.log("Validating OTP");
     // Phone Number and Amount
     console.log("Username: "+ username);
     console.log("Password: "+ password);

     // Ajax STK Push Request
     let myObj = { phone_number: username, otp:password };
     console.log(myObj);

     $.ajax({
            type: "POST",
            url: "/cfms/auth/otp-pin",
            datatype: "json",
            data: myObj,
            success: function(response){
                console.log(response);
                // var finalResponse = JSON.parse(response);
                if(response === "OTP is valid!"){
                    $(".responseDiv").html("<div class=\"alert alert-success\">OTP has been Verified</div>");
                    $(".responseDiv").fadeOut(3000);
                    document.getElementById("otp_verification").style.display = "none";
                    document.getElementById("phone1").value = username;
                    document.getElementById("phone").value = username;
                    document.getElementById("register_div").style.display = "block";

                }else{
                    $(".responseDiv").html("<div class=\"alert alert-danger\">Login Response : "+response+". Try Again</div>");
                    $(".responseDiv").fadeOut(3000);
                }
            },
            error: function(response){
                console.log(response);
            }

     });
}

// Form Authentication for Redirection

function sendToServer(){
    // Send to Server
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;

    console.log("Username: "+ username);
    console.log("Password: "+ password);

    $("#myForm").submit();
}


// Successfully Logging Out the User
function logoutUser(){
    // Logout User Without Session
    console.log("Logout Process Initiated")
    $("#myForm").submit();
}


function registeredNumbers(){
    var phone_number = document.getElementById("phone").value;

     $.post(hosted_url + "/cfms/auth/check-number",phone_number ,function(data, status){
           $(".responseDiv").html("<div class=\"alert alert-danger\">"+response+". Try Again</div>");
           $(".responseDiv").fadeOut(3000);
     });

}

// Transfer Church
function checkChurch(){

    var phone = document.getElementById("phone").value;
    var code = document.getElementById("church_code_transfer").value;

    console.log("Check Church Code: "+code);

    if (code.length == 5){
        const content = {
            phone_number: phone,
            church_code: code
        }

        $.post(hosted_url + "/cfms/auth/check-status", content ,function(data, status){
           console.log(data);
           if(data != "Yes"){
                $(".responseDiv1").show();
                $(".responseDiv1").html(data);
                $(".responseDiv1").fadeOut(3000);
           }
        });
    }
}

function enableHeader(){
    const boxes = document.getElementsByClassName('responseDiv');

    for (const box of boxes) {
        box.style.display = 'block';
    }
}

function displayChurchName(){
    var phone = document.getElementById("phone").value;
    var code = document.getElementById("church_code").value;
    var code2 = document.getElementById("church_code_transfer").value;

    console.log("Check Church Code in Display: "+code);

    if (code.length == 5){
        const content = {
           phone_number: phone,
           church_code: code2
        }

        $.post(hosted_url + "/cfms/auth/check-church", content ,function(data, status){
            console.log("Church Details: " +data)
            console.log("Church Name: " +data.churchName);
            TransferChurch(data.churchName, phone, code, code2);
        });
    }
}

function TransferChurch(name,phone_number,code,code2){
    var statement = "Is "+ name +" the church you wish to transfer your details to?";

    var content = {
        phone_number: phone_number,
        church_code: code2
    }
    if(code != code2){
         if(confirm(statement) == true){
             $.post(hosted_url + "/cfms/auth/member-transfer", content ,function(data, status){
                   $(".responseDiv1").show();
                   $(".responseDiv1").html("Transfer Successful");
                   $(".responseDiv1").fadeOut(3000);
             });
         }
    }else if(code === code2){
        $(".responseDiv1").show();
        $(".responseDiv1").html("You are already Registered to this Church Code");
        $(".responseDiv1").fadeOut(3000);
    }
}


// Get the OTP Pin
function confirmResetOTP(){

     // Form data
     var username = document.getElementById("phone_number1").value;
     const inputs = document.querySelectorAll(".otp-field input");
     var password = "";
     inputs.forEach((input, index) => {
             password += input.value;
     });

      var myModal = new bootstrap.Modal(document.getElementById('exampleModalToggle4'), {
             keyboard: true,
             backdrop: true
      });

     console.log("Validating OTP");
     // Phone Number and Amount
     console.log("Username: "+ username);
     console.log("Password: "+ password);

     // Ajax STK Push Request
     let myObj = { phone_number: username, otp:password };
     console.log(myObj);

     $.ajax({
            type: "POST",
            url: "/cfms/auth/otp-pin",
            datatype: "json",
            data: myObj,
            success: function(response){
                console.log(response);
                // var finalResponse = JSON.parse(response);
                if(response === "OTP is valid!"){
                    document.getElementById("username1").value = username;
                    $('#exampleModalToggle3').modal('hide');
                    myModal.toggle();
                }else{
                    $(".responseDiv").html("<div class=\"alert alert-danger\">"+response+". Try Again</div>");
                    $(".responseDiv").fadeOut(3000);
                }
            },
            error: function(response){
                console.log(response);
            }

     });
}

// Confirm Reset Member OTP
// Get the OTP Pin
function confirmResetPinOTP(){

     // Form data
     var username = document.getElementById("phone_number").value;
     const inputs = document.querySelectorAll(".otp-field input");
     var password = "";
     inputs.forEach((input, index) => {
             password += input.value;
     });

     console.log("Validating OTP");
     // Phone Number and Amount
     console.log("Username: "+ username);
     console.log("Password: "+ password);

     // Ajax STK Push Request
     let myObj = { phone_number: username, otp:password };
     console.log(myObj);

     $.ajax({
            type: "POST",
            url: "/cfms/auth/otp-pin",
            datatype: "json",
            data: myObj,
            success: function(response){
                console.log(response);
                if(response === "OTP is valid!"){
                    document.getElementById("otp_id").style.display = "none";
                    document.getElementById("otp_top").style.display = "block";
                }else{
                    $(".responseDiv").html("<div class=\"alert alert-danger\">"+response+". Try Again</div>");
                    $(".responseDiv").fadeOut(3000);
                }
            },
            error: function(response){
                console.log(response);
            }

     });
}


// Reset Member Pin
// Authenticate Registered Member Request(Client Side Way of Logging In)
function resetMemberPin(){

    // Form data
	var username = document.getElementById("username1").value;
    var password = document.getElementById("password1").value;

    // Phone Number and Amount
    console.log("Username: "+ username);
    console.log("Password: "+ password);

    // Ajax STK Push Request
    let myObj = { previousAccessNumber: username, currentAccessNumber: username, pin:password };
    console.log(myObj)

    $.ajax({
        type: "POST",
        url: "/cfms/auth/reset",
        datatype: "json",
        data: myObj,
        success: function(response){
            console.log(response);
            // var finalResponse = JSON.parse(response);
            $(".responseDiv").html("<div class=\"alert alert-success\">"+response.state+"</div>");
            $(".responseDiv").fadeOut(3000);
        },
        error: function(response){
            console.log(response);
        }

    });
}

// Reset Member Pin
// Authenticate Registered Member Request(Client Side Way of Logging In)
function resetPin(){

    var username = document.getElementById("phone").value;
	var password = document.getElementById("pin").value;
    var confirm_password = document.getElementById("confirm_pin").value;

    if(confirm_password === password){

        let myObj = { previousAccessNumber: username, currentAccessNumber: username, pin:password };
        console.log(myObj)

        $.ajax({
            type: "POST",
            url: "/cfms/auth/reset",
            datatype: "json",
            data: myObj,
            success: function(response){
                console.log(response);
                // var finalResponse = JSON.parse(response);
                $(".responseDiv").html(""+response.state+"");
                $(".responseDiv").fadeOut(3000);
                logoutUser();
            },
            error: function(response){
                console.log(response);
            }

        });
    }else{
        $(".responseDiv").html("The Pins entered do not Match");
        $(".responseDiv").fadeOut(3000);
    }
}


// Toggle Password Visibility
function passwordVisibility(){
	var x = document.getElementById("password");
	var icon = document.getElementById("togglePassword");
	icon.classList.toggle("bi-eye");
	if (x.type === "password") {
		x.type = "text";
	} else {
		x.type = "password";
	}
}


// Toggle Password Visibility
function passwordVisibility1(){
	var x = document.getElementById("password1");
	var icon = document.getElementById("togglePassword1");
	icon.classList.toggle("bi-eye");
	if (x.type === "password") {
		x.type = "text";
	} else {
		x.type = "password";
	}
}


// Toggle Password Visibility
function passwordVisibility2(){
	var x = document.getElementById("pin");
	var icon = document.getElementById("togglePassword2");
	icon.classList.toggle("bi-eye");
	if (x.type === "password") {
		x.type = "text";
	} else {
		x.type = "password";
	}
}


// Reset Password
function passwordVisibility3(){
	var x = document.getElementById("password2");
	var icon = document.getElementById("togglePassword3");
	icon.classList.toggle("bi-eye");
	if (x.type === "password") {
		x.type = "text";
	} else {
		x.type = "password";
	}
}


function clickEvent(first,last){
  if(first.value.length){
    document.getElementById(last).focus();
  }
}


function callPage(){
		$.ajax({
			url: "about",
			type: "GET",
			dataType: "text",
			success: function(response){
				console.log('The page was loaded', response);
				$('.content').html(response);
			},
			error: function(error){
				console.log('The page was not loaded');
			},
			complete: function(xhr, status){
				console.log('The request is complete');
			}
		});
	}