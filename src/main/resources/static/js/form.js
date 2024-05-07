var hosted_url = location.origin;

// Authenticate Registered Member Request(Client Side Way of Logging In)
function AuthMemberRequest(){

    // Form data
	var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;

    if(username === ""){
        alert("Phone Must be Filled Out");
         return false;
    }else if(username.length != 12){
        alert("Phone Number Must be Greater than 10 Characters and Less than 12 Characters");
        return false;
    }else if(!username.startsWith("254")){
        alert("Check for any spaces and the number starts with 254");
        return false;
    }else if (password === ''){
        alert("Pin Must be Filled Out");
        return false;
    }else if(password.length < 4){
        alert("Pin must 4 Digits");
        return false;
    }

    // Phone Number and Amount
    console.log("Username: "+ username);
    console.log("Password: "+ password);

    // Ajax STK Push Request
    let myObj = { username: username, password:password };

    $.ajax({
        type: "POST",
        url: "/cfms-web/auth/login",
        datatype: "json",
        data: myObj,
        success: function(response){
            console.log(response);
            console.log(response.state);

            if (response.state !== null){
                document.getElementById("phone_number_login").value = username;
                document.getElementById("otp_login").style.display = "block";
                sendOTP();
            }else{
                alert(response.error);
            }

        },
        error: function(response){
            console.log(response);
        }

    });
}

// Send Login OTP
function sendOTP(){
    startOTP();
    var otp_field12 = document.getElementById('otp-field11');

    const inputs = document.querySelectorAll(".otp-field input");
    inputs.forEach((input, index) => {
         input.disabled = false;
         input.value = "";
    });

    // Display the OTP Field
    otp_field12.style.display = "block";

    var username = document.getElementById("phone_number_login").value;
    console.log("Phone Number: " + username);

    // Ajax STK Push Request
    let myObj = { recipient: username};

    $.ajax({
         type: "POST",
         url: "/cfms-web/auth/otp",
         datatype: "json",
         data: myObj,
         success: function(response){
              console.log(response);
         },
         error: function(response){
              alert(response);
         }
    });
}

// Start Login OTP Function
function startOTP() {
    document.getElementById('time').style.display='block';
    var fiveMinutes = 60 * 1;
    display = document.querySelector('#time');
    startTimer(fiveMinutes, display);
}

// Login OTP Timer
function startTimer(duration, display) {
    document.getElementById('otp-field11').style.display='block';

    var timer = duration, minutes, seconds;
    document.getElementById('send_otp_button').style.display = "none";

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
            document.getElementById('otp-field11').style.display='none';
            document.getElementById('verify_otp_button').style.width = "100%";
            document.getElementById('send_otp_button').style.display = "block";
            display.textContent = "00 : 00";
            clearInterval(x);
        }
    }, 1000);
}

// Confirm the Login OTP Pin
function confirmOTP(){
     // Form data
     var username = document.getElementById("phone_number_login").value;
     const inputs = document.querySelectorAll(".otp-field input");
     var password = "";
     inputs.forEach((input, index) => {
           password += input.value;
     });

     console.log("Validating Login OTP");
     // Phone Number and Amount

     // Ajax STK Push Request
     let myObj = { phone_number: username, otp:password };
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
                    // alert("OTP has been Verified");
                    sendToServer();
                }else{
                    alert(""+response+". Try Again");
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

    $("#login_form").submit();
}

// Send the Registration OTP
function sendRegisterOTP(){
    var username = document.getElementById("phone_number_register").value;

    if(username == ""){
        alert("Enter your Phone Number");
        return false;
    }

    if(!username.startsWith("254")){
        alert("The Phone Number Should Start with 254");
        return false;
    }

    if(username.length != 12){
        alert("The Length of the Phone Number Should 12 Characters");
        return false;
    }

    startRegisterOTP();
    var otp_field12 = document.getElementById('otp-field12');

    const inputs = document.querySelectorAll(".otp-field input");
    inputs.forEach((input, index) => {
         input.disabled = false;
         input.value = "";
    });

    // Display the OTP Field
    otp_field12.style.display = "block";

    console.log("Phone Number: " + username);

    // Ajax STK Push Request
    let myObj = { recipient: username};
    console.log(myObj);
    console.log("Processing");

    $.ajax({
         type: "POST",
         url: "/cfms-web/auth/otp",
         datatype: "json",
         data: myObj,
         success: function(response){
             console.log(response);
         },
         error: function(response){
             alert(response);
         }
    });

}

// Start Register OTP Function
function startRegisterOTP() {
    // document.getElementById('otp-zone').style.display='block';
    document.getElementById('time1').style.display='block';
    var fiveMinutes = 60 * 1;
    display = document.querySelector('#time1');
    startRegisterTimer(fiveMinutes, display);
}

// Start Register Timer
// OTP Timer
function startRegisterTimer(duration, display) {
    document.getElementById('otp-field12').style.display='none';
    // display.textContent = "00:00";
    var username = document.getElementById("phone_number_register").value;
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
            document.getElementById("send_otp_register").innerText = "Resend OTP";
            document.getElementById('time1').style.display='none';
            const inputs = document.querySelectorAll(".otp-field input");
            inputs.forEach((input, index) => {
                input.disabled = true;
            });
            document.getElementById('otp-field12').style.display='none';
            display.textContent = "00 : 00";
            clearInterval(x);
        }
    }, 1000);
}


// Confirm Register OTP
// Get the OTP Pin
function confirmRegisterOTP(){
     // Form data
     var username = document.getElementById("phone_number_register").value;
     const inputs = document.querySelectorAll(".otp-field input");
     var register = document.getElementById('register');
     var password = "";
     inputs.forEach((input, index) => {
           password += input.value;
     });

     console.log("Validating Register OTP");
     // Phone Number and Amount
     console.log("Username: "+ username);
     console.log("Password: "+ password);

     // Ajax STK Push Request
     let myObj = { phone_number: username, otp:password };
     console.log(myObj);

     $.ajax({
            type: "POST",
            url: "/cfms-web/auth/otp-pin-register",
            datatype: "json",
            data: myObj,
            success: function(response){
                console.log(response);
                if(response === "Valid"){
                    alert("OTP has been Verified");
                    document.getElementById("otp_register").style.display = "none";
                    document.getElementById("phone").value = username;
                    document.getElementById("register_div").style.display = "block";
                }else if(response === "Invalid"){
                    alert(response + "Try Again");
                }else{
                    alert(response);
                    register.classList.remove('show-register');
                }
            },
            error: function(response){
                console.log(response);
            }
     });
}

// Register Member
// Authenticate Registered Member Request(Client Side Way of Logging In)
function AuthMemberRegister(){

    // Form data
	var username = document.getElementById("fullname").value;
	var email = document.getElementById("email").value;
    var password = document.getElementById("pin").value;
    var confirm_password = document.getElementById("confirm_pin").value;
    var phone = document.getElementById("phone").value;
    var church_code = document.getElementById("church_code").value;

    if(username == ""){
        alert("Enter your Full Name");
        return false;
    }else if(/\s/.test(username) == false){
        alert("The Full Name needs to be Two Names with a Space in Between");
        return false;
    }else if(password == ""){
        alert("Enter Your Pin");
        return false;
    }else if(confirm_password == ""){
        alert("Enter Your Pin Confirmation");
        return false;
    }else if(phone == ""){
        alert("Enter your Phone Number");
        return false;
    }else if(password !== confirm_password){
        alert("The Pins do not Match");
        return false;
    }else if(church_code == ""){
        alert("Enter the Church Code");
        return false;
    }

    // registeredNumbers();
    $("#form-register").submit();
}

// Send Reset OTP
function sendResetOTP(){
    var username = document.getElementById("phone_number_reset").value;

    if(username == ""){
        alert("Enter your Phone Number");
        return false;
    }else if(username.length != 12){
        alert("Make sure the Phone Number is 12 Characters");
        return false;
    }else if(!username.startsWith("254")){
        alert("Check for any spaces and the number starts with 254");
        return false;
    }

    console.log("Sending Reset OTP");

    startResetOTP();
    // Enable the OTP Input Fields
    const inputs = document.querySelectorAll(".otp-field input");
    inputs.forEach((input, index) => {
         input.disabled = false;
         input.value = "";
    });



    // Ajax STK Push Request
    let myObj = { recipient: username};
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
                    alert(response);
                }

         });

}

// Start Reset OTP Function
function startResetOTP() {
    //document.getElementById('otp-zone').style.display='block';
    document.getElementById('time2').style.display='block';
    var fiveMinutes = 60 * 1;
    display = document.querySelector('#time2');
    startResetTimer(fiveMinutes, display);
}

// Reset OTP Timer
function startResetTimer(duration, display) {
    document.getElementById('otp-field13').style.display='block';

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
            document.getElementById("send-otp-reset").innerText = "Resend OTP";
            document.getElementById('time2').style.display='none';
            const inputs = document.querySelectorAll(".otp-field input");
            inputs.forEach((input, index) => {
                input.disabled = true;
            });
            document.getElementById('otp-field13').style.display='none';
            display.textContent = "00 : 00";
            clearInterval(x);
        }
    }, 1000);
}




// OTP Timer
function startResetPinTimer(duration, display) {
    document.getElementById('otp-field11').style.display='block';
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
            display.textContent = "00:00";
            clearInterval(x);
        }
    }, 1000);
}

// Start OTP Function for Reset Pin
function startResetPinOTP() {
    //document.getElementById('otp-zone').style.display='block';
    document.getElementById('time').style.display='block';
    var fiveMinutes = 60 * 1;
    display = document.querySelector('#time');
    startResetPinTimer(fiveMinutes, display);
}

// Confirm the Reset OTP Pin
function confirmResetOTP(){

     // Form data
     var username = document.getElementById("phone_number_reset").value;
     const inputs = document.querySelectorAll(".otp-field input");
     var password = "";
     inputs.forEach((input, index) => {
             password += input.value;
     });

     var login = document.getElementById('login');
     var register = document.getElementById('register');
     var reset_pin = document.getElementById('reset-pin');
     var reset_div = document.getElementById('reset_div');
     var otp_reset = document.getElementById('otp_reset');

     console.log("Validating OTP");
     // Phone Number and Amount
     console.log("Username: "+ username);
     console.log("Password: "+ password);

     // Ajax STK Push Request
     let myObj = { phone_number: username, otp:password };
     console.log(myObj);

     $.ajax({
          type: "POST",
          url: "/cfms-web/auth/otp-pin",
          datatype: "json",
          data: myObj,
          success: function(response){
              console.log(response);
              if(response === "OTP is valid!"){
                    otp_reset.style.display = "none";
                    reset_div.style.display = "block";
              }else{
                    alert(""+response+". Try Again");
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
	var username = document.getElementById("phone_number_reset").value;
	var password1 = document.getElementById("pin_reset").value
    var password2 = document.getElementById("confirm_pin_reset").value;
    var login = document.getElementById('login');
    var reset_pin = document.getElementById('reset-pin');

    if(password1 == ""){
        alert("Enter Your Pin");
        return false;
    }else if(password1 == ""){
        alert("Enter Your Pin Confirmation");
        return false;
    }else if(password1 !== password2){
        alert("The Pins do not Match");
        return false;
    }

    // Ajax STK Push Request
    let myObj = { previousAccessNumber: username, currentAccessNumber: username, pin:password1 };
    console.log(myObj)

    $.ajax({
        type: "POST",
        url: "/cfms-web/auth/reset",
        datatype: "json",
        data: myObj,
        success: function(response){
            console.log(response);
            if(response.state != null){
                alert(response.state);
                reset_pin.classList.remove('show-reset');
                login.classList.add('show-login');
            }else{
                alert("Pin Reset Failed");
            }

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

    var username = document.getElementById("phone_number_reset").value;
    console.log("Sending OTP");
    console.log("Phone Number: " + username);

    // Ajax STK Push Request
    let myObj = { recipient: username};
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
                }

         });

}




// Successfully Logging Out the User
function logoutUser(){
    // Logout User Without Session
    console.log("Logout Process Initiated")
    $("#myForm").submit();
}


function registeredNumbers(){
    var phone_number = document.getElementById("phone").value;

     $.post(hosted_url + "/cfms-web/auth/check-number",phone_number ,function(data, status){
           alert(""+response+". Try Again");

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

        $.post(hosted_url + "/cfms-web/auth/check-status", content ,function(data, status){
           console.log(data);
           if(data != "Yes"){
                alert(data);
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

        $.post(hosted_url + "/cfms-web/auth/check-church", content ,function(data, status){
            console.log("Church Details: " +data)
            console.log("Church Name: " +data.churchName);
            TransferChurch(data.churchName, phone, code, code2);
        });
    }
}


function checkChurchCode(){
    var phone_number = document.getElementById("phone").value;
    var church_code = document.getElementById("church_code").value;


    var content = {
        phone_number: phone_number,
        church_code: church_code
    };


    $.get(hosted_url + "/cfms-web/auth/check-church-code", content ,function(data, status){
        if(data == null){
            alert("The Church does not Exist");
            document.getElementById("church_code").value = "";
            return false;
        }else if(data != null){
            return true;
        }
    });
}

function TransferChurch(name,phone_number,code,code2){
    var statement = "Is "+ name +" the church you wish to transfer your details to?";

    var content = {
        phone_number: phone_number,
        church_code: code2
    }
    if(code != code2){
         if(confirm(statement) == true){
             $.post(hosted_url + "/cfms-web/auth/member-transfer", content ,function(data, status){
                   alert("Transfer Successful");
                   window.location.reload();
             });
         }
    }else if(code === code2){
        alert("You are already Registered to this Church Code");
    }
}




// Confirm Reset Member OTP
// Get the OTP Pin
function confirmResetPinOTP(){

     // Form data
     var username = document.getElementById("phone_number_reset").value;
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
            url: "/cfms-web/auth/otp-pin",
            datatype: "json",
            data: myObj,
            success: function(response){
                console.log(response);
                if(response === "OTP is valid!"){
                    document.getElementById("otp_id").style.display = "none";
                    document.getElementById("otp_top").style.display = "block";
                }else{
                    alert(""+response+". Try Again");

                }
            },
            error: function(response){
                console.log(response);
            }

     });
}




// Reset Member Pin
// Authenticate Registered Member Request(Client Side Way of Logging In)
function resetMemberPins(){

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
        url: "/cfms-web/auth/reset",
        datatype: "json",
        data: myObj,
        success: function(response){
            console.log(response);
            // var finalResponse = JSON.parse(response);
            alert(response.state);

        },
        error: function(response){
            console.log(response);
        }

    });
}

// Reset Member Pin
// Authenticate Registered Member Request(Client Side Way of Logging In)
function resetPin(){

    var username = document.getElementById("phone_numbered").value;
	var password = document.getElementById("pin").value;
    var confirm_password = document.getElementById("confirm_pin").value;

    if(confirm_password === password){

        let myObj = { previousAccessNumber: username, currentAccessNumber: username, pin:password };
        console.log(myObj)

        $.ajax({
            type: "POST",
            url: "/cfms-web/auth/reset",
            datatype: "json",
            data: myObj,
            success: function(response){
                console.log(response);
                if(response.state != null){
                    alert(response.state);
                    logoutUser();
                }else{
                    alert("Pin Reset Failed");
                }
            },
            error: function(response){
                console.log(response);
            }

        });
    }else{
        alert("The Pins entered do not Match");
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
	var x = document.getElementById("confirm_pin");
	var icon = document.getElementById("togglePassword3");
	icon.classList.toggle("bi-eye");
	if (x.type === "password") {
		x.type = "text";
	} else {
		x.type = "password";
	}
}


// Reset Password
function passwordVisibilityReset(){
	var x = document.getElementById("pin");
	var icon = document.getElementById("toggle-password-reset");
	icon.classList.toggle("bi-eye");
	if (x.type === "password") {
		x.type = "text";
	} else {
		x.type = "password";
	}
}

// Reset Password
function passwordVisibilityReset1(){
	var x = document.getElementById("confirm_pin");
	var icon = document.getElementById("toggle-password-reset1");
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