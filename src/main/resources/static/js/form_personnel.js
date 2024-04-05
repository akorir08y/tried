var hosted_url = location.origin;

// OTP Timer
function startPersonnelTimer(duration, display) {
    var timer = duration, minutes, seconds;
    var username2 = document.getElementById("mobile_number").innerText;


    var x = setInterval(function () {
        minutes = parseInt(timer / 60, 10);
        seconds = parseInt(timer % 60, 10);

        minutes = minutes < 10 ? "0" + minutes : minutes;
        seconds = seconds < 10 ? "0" + seconds : seconds;

        display.textContent = minutes + ":" + seconds;


        if (--timer < 0) {
            timer = duration;
            const inputs = document.querySelectorAll(".otp-field input");
            inputs.forEach((input, index) => {
                input.disabled = true;
                //input.classList.add("disabled");
            });
            document.getElementById('otp-field12').style.display='none';
            document.getElementById('time').style.display='none';
            clearInterval(x);
        }
    }, 1000);
}

// OTP Timer
function startPersonnelResetTimer(duration, display) {
    var timer = duration, minutes, seconds;
    var username2 = document.getElementById("mobile_number").innerText;
    var phone_number = "Resend OTP Code to Number" + username2.substring(0, 6) + "***" +
     username2.substring(9,12);
    // getOTP();
    var x = setInterval(function () {
        minutes = parseInt(timer / 60, 10);
        seconds = parseInt(timer % 60, 10);

        minutes = minutes < 10 ? "0" + minutes : minutes;
        seconds = seconds < 10 ? "0" + seconds : seconds;

        display.textContent = minutes + ":" + seconds;


        if (--timer < 0) {
            timer = duration;
            document.getElementById("sendCode").innerHTML = phone_number;
            document.getElementById('time1').style.display='none';
            const inputs = document.querySelectorAll(".otp-field input");
            inputs.forEach((input, index) => {
                input.disabled = true;
                // input.classList.add("disabled");
            });
            document.getElementById('otp-field11').style.display='none';
            document.getElementById('time1').style.display='none';
            clearInterval(x);

        }
    }, 1000);
}

// Start OTP Function
function startPersonnelOTP() {
    // document.getElementById('otp-zone').style.display='block';
    document.getElementById('time').style.display='block';
    var fiveMinutes = 60 * 1;
    display = document.querySelector('#time');
    startPersonnelTimer(fiveMinutes, display);
}

// Start OTP Function
function startPersonnelResetOTP() {
    //document.getElementById('otp-zone').style.display='block';
    document.getElementById('time1').style.display='block';
    var fiveMinutes = 60 * 1;
    display = document.querySelector('#time1');
    startPersonnelResetTimer(fiveMinutes, display);
}

function sendPersonnelOTP(){
    startPersonnelOTP();
    // Enable the OTP Input Fields
    const inputs = document.querySelectorAll(".otp-field input");
    inputs.forEach((input, index) => {
         input.disabled = false;
         input.value = "";
    });

    var username = document.getElementById("phone_number").value;
    var sendCode = document.getElementById('sendCode');
    var otp_field12 = document.getElementById('otp-field12');


    console.log("Sending OTP");
    console.log("Phone Number: " + username);

    // Display the OTP Field
    otp_field12.style.display = "block";

    var trimmed = username.trim();

    // Ajax STK Push Request
    let myObj = { recipient: trimmed};
    console.log(myObj);

         $.ajax({
                type: "POST",
                url: "/cfms-web/auth/otp",
                datatype: "json",
                data: myObj,
                success: function(response){
                    console.log(response);
                },
                error: function(response){
                    console.log(response);
                     $(".responseDiv").html("<div class=\"alert alert-danger\">OTP has not been sent. Please Try Again</div>");
                }

         });

}

function sendPersonnelResetOTP(){
    startPersonnelResetOTP();
    // Enable the OTP Input Fields
    const inputs = document.querySelectorAll(".otp-field input");
    inputs.forEach((input, index) => {
         input.disabled = false;
         input.value = "";
    });

    var phone_number = document.getElementById("mobile_number").innerText;
    var trimmed = phone_number.trim();
    var sendCode = document.getElementById('sendCode1');
    console.log("Sending OTP");

    // Ajax STK Push Request
    let myObj = { recipient: trimmed};
    console.log(myObj);

         $.ajax({
                type: "POST",
                url: "/cfms-web/auth/otp",
                datatype: "json",
                data: myObj,
                success: function(response){
                    console.log(response);
                    sendCode.style.display = "none";
                },
                error: function(response){
                    console.log(response);
                    $(".responseDiv").html("<div class=\"alert alert-success\">Error Sending OTP. Please Try Again</div>");
                }

         });

}

// Get the OTP Pin
function confirmPersonnelOTP(){

     // Form data
     var username = document.getElementById("phone_number").value;
     var trimmed = username.trim();
     const inputs = document.querySelectorAll(".otp-field input");
     var password = "";
     inputs.forEach((input, index) => {
             password += input.value;
     });

     // Phone Number and Amount
     console.log("Username: "+ username);
     console.log("Password: "+ password);

     // Ajax STK Push Request
     let myObj = { phone_number: trimmed, otp:password };
     console.log(myObj);

     $.ajax({
            type: "POST",
            url: "/cfms-web/auth/otp-pin",
            datatype: "json",
            data: myObj,
            success: function(response){
                console.log(response);
                if(response === "OTP is valid!"){
                    sendToServer();
                }else{
                    $(".responseDiv").html("<div class=\"alert alert-danger\">Login Response : "+response+". Try Again</div>");
                }
            },
            error: function(response){
                console.log(response);
            }

     });
}

function sendToServer(){
    // Send to Server
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;

    console.log("Username: "+ username);
    console.log("Password: "+ password);

    $("#myForm").submit();
}


// Get the OTP Pin
function confirmPersonnelResetOTP(){

     // Form data
     var username = document.getElementById("mobile_number").innerText;
     var trimmed = username.trim();
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
     let myObj = { phone_number: trimmed, otp:password };
     console.log(myObj);

     $.ajax({
            type: "POST",
            url: "/cfms-web/auth/otp-pin",
            datatype: "json",
            data: myObj,
            success: function(response){
                console.log(response);
                // var finalResponse = JSON.parse(response);
                if(response === "OTP is valid!"){
                    $('#exampleModalToggle3').modal('hide');
                    myModal.toggle();
                }else{
                    $(".responseDiv").html("<div class=\"alert alert-danger\">"+response+". Try Again</div>");
                }
            },
            error: function(response){
                console.log(response);
            }

     });
}


// Reset Member Pin
// Authenticate Registered Member Request(Client Side Way of Logging In)
function personnelLogin(){

    // Form data
	var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;

    var username2 = document.getElementById("mobile_number").innerText;
    var trimmed = username2.trim();
    console.log("Username: " + username2);

    // Phone Number and Amount
    console.log("Username: "+ username);
    console.log("Password: "+ password);

    // Ajax STK Push Request
    let myObj = { user: username, password:password };
    console.log(myObj)

    $.ajax({
        type: "POST",
        url: "/cfms-web/auth/personnel/login",
        datatype: "json",
        data: myObj,
        success: function(response){
            console.log(response);
            if (response.payload != null){
                document.getElementById("phone_number").value = username2;
                document.getElementById("otp_id").style.display = "block";
            }else{
                $(".responseDiv").html("<div class=\"alert alert-danger\">Check Your Login Credentials Again</div>");
            }
        },
        error: function(response){
            console.log(response);
        }

    });
}


// Reset Personnel Password
function resetPersonnelPassword(){

    // Form data
	var username = document.getElementById("username2").value;
    var password = document.getElementById("password2").value;

    // Phone Number and Amount
    console.log("Username: "+ username);
    console.log("Password: "+ password);

    // Ajax STK Push Request
    let myObj = { username: username, password:password };
    console.log(myObj)

    $.ajax({
        type: "POST",
        url: "/cfms-web/auth/personnel/reset",
        datatype: "json",
        data: myObj,
        success: function(response){
            console.log(response);
            $(".responseDiv").html("<div class=\"alert alert-success\">Login Response : "+response.notice+"</div>");
        },
        error: function(response){
            console.log(response);
        }

    });
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
	var x = document.getElementById("password2");
	var icon = document.getElementById("togglePassword2");
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


function loadResetFields(){

    var username2 = document.getElementById("mobile_number").innerText;
    var trimmed = username2.trim();

    var phone_number = "Send OTP Code to Number "+ trimmed.substring(0, 6) + "***" + trimmed.substring(9, 12);
    document.getElementById("sendCode1").innerHTML = phone_number;
    document.getElementById("phone_number1").innerHTML = trimmed;
}



function getMemberOffering(){

}