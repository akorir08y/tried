var hosted_url = location.origin;

// Authenticate Personnel Request(Client Side Way of Logging In)
function AuthPersonnelRequest(){

    // Form data
	var username = document.getElementById("personnel_username").value;
    var password = document.getElementById("personnel_password").value;

    if(username === ""){
        alert("Username Must be Filled Out");
         return false;
    }else if (password === ''){
        alert("Pin Must be Filled Out");
        return false;
    }

    // Phone Number and Amount
    console.log("Username: "+ username);
    console.log("Password: "+ password);

    // Ajax STK Push Request
    let myObj = { username: username, password:password };

    $.ajax({
        type: "POST",
        url: "/cfms-web/auth/auth-personnel",
        datatype: "json",
        data: myObj,
        success: function(response){
            console.log(response);
            if (response.status === 0){
                document.getElementById("otp_personnel_login").style.display = "block";
                document.getElementById("phone_number_login_personnel").value = response.payload.personnelPhone;
                sendPersonnelOTP();
            }else{
                alert("Check your Login Credentials. Then Try and Login Again");
            }

        },
        error: function(response){
            console.log(response);
        }

    });
}

// Send Login OTP
function sendPersonnelOTP(){
    startPersonnelOTP();
    var otp_field12 = document.getElementById('otp-field14');

    const inputs = document.querySelectorAll(".otp-field input");
    inputs.forEach((input, index) => {
         input.disabled = false;
         input.value = "";
    });

    // Display the OTP Field
    otp_field12.style.display = "block";

    var username = document.getElementById("personnel_username").value;
    var password = document.getElementById("personnel_password").value;
    console.log("Phone Number: " + username);

    // Ajax STK Push Request
    let myObj = { username: username, password: password};

    $.ajax({
         type: "POST",
         url: "/cfms-web/auth/otp-personnel",
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
function startPersonnelOTP() {
    document.getElementById('time4').style.display='block';
    var fiveMinutes = 60 * 1;
    display = document.querySelector('#time4');
    startPersonnelTimer(fiveMinutes, display);
}

// Login OTP Timer
function startPersonnelTimer(duration, display) {
    document.getElementById('otp-field14').style.display='block';

    var timer = duration, minutes, seconds;
    document.getElementById('send_personnel_otp_button').style.display = "none";

    var x = setInterval(function () {
        minutes = parseInt(timer / 60, 10);
        seconds = parseInt(timer % 60, 10);

        minutes = minutes < 10 ? "0" + minutes : minutes;
        seconds = seconds < 10 ? "0" + seconds : seconds;

        display.textContent = minutes + ":" + seconds;


        if (--timer < 0) {
            timer = duration;
            document.getElementById('time4').style.display='none';
            const inputs = document.querySelectorAll(".otp-field input");
            inputs.forEach((input, index) => {
                input.disabled = true;
            });
            document.getElementById('otp-field14').style.display='none';
            document.getElementById('verify_personnel_otp_button').style.width = "100%";
            document.getElementById('send_personnel_otp_button').style.display = "block";
            display.textContent = "00 : 00";
            clearInterval(x);
        }
    }, 1000);
}

// Confirm the Login OTP Pin
function confirmPersonnelOTP(){
     // Form data
     var username = document.getElementById("phone_number_login_personnel").value;
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
                if(response === "OTP is valid!"){
                    // alert("OTP has been Verified");
                    sendToPersonnel()
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
function sendToPersonnel(){
    // Send to Server
    var username = document.getElementById("personnel_username").value;
    var password = document.getElementById("personnel_password").value;

    $("#personnel_login_form").submit();
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
function passwordVisibility5(){
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
