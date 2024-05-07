// Get the modal
var modal = document.getElementById("myModal");
// var modal1 = document.getElementById("myModal1");

// Get the button that opens the modal
var btn = document.getElementById("myBtn");

// var btn1 = document.getElementById("reset_password");

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];
// var span1 = document.getElementsByClassName("close")[1];

// When the user clicks the button, open the modal 
btn.onclick = function() {
  modal.style.display = "block";
  // modal1.style.display = "none";
}

/*
btn1.onclick = function() {
  modal.style.display = "none";
  modal1.style.display = "block";
  var mobile_number = document.getElementById('mobile_number').value;
  document.getElementById('personnel_reset_div').innerHTML = "OTP Sent to "+ mobile_number;
}
*/

// When the user clicks on <span> (x), close the modal
span.onclick = function() {
  modal.style.display = "none";
}

// span1.onclick = function() {
//   modal1.style.display = "none";
// }

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
  if (event.target == modal) {
    modal.style.display = "none";
  }
}


function showResetPassword(){
	var otp_verify1 = document.getElementById("otp_verify1");
	var reset_password_div = document.getElementById("reset_password_div");

	var personnel_reset_div = document.getElementById('personnel_reset_div');
	var mobile_number = document.getElementById('mobile_number').value;

	personnel_reset_div.innerHTML = "Send OTP to "+ mobile_number;

	otp_verify1.style.display = "none";
	reset_password_div.style.display = "block";
	
}


function showLogin(){
	var otp_verify1 = document.getElementById("otp_verify1");
	var reset_password_div = document.getElementById("reset_password_div");

	var personnel_reset_div = document.getElementById('personnel_reset_div');
    var mobile_number = document.getElementById('mobile_number').value;

    personnel_reset_div.innerHTML = "Send OTP to "+ mobile_number;

	otp_verify1.style.display = "block";
	reset_password_div.style.display = "none";
	
}


function passwordVisibility(){
	var x = document.getElementById("password");
	var icon = document.getElementById("togglePassword")
	icon.classList.toggle("bi-eye");
	if (x.type === "password") {
		x.type = "text";
	} else {
		x.type = "password";
	}
}

// Reset Pin
function passwordVisibility1(){
	var x = document.getElementById("password1");
	var icon = document.getElementById("togglePassword1")
	icon.classList.toggle("bi-eye");
	if (x.type === "password") {
		x.type = "text";
	} else {
		x.type = "password";
	}
}

// Register Pin
function passwordVisibility2(){
	var x = document.getElementById("password2");
	var icon = document.getElementById("togglePassword2")
	icon.classList.toggle("bi-eye");
	if (x.type === "password") {
		x.type = "text";
	} else {
		x.type = "password";
	}
}