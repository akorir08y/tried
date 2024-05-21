/*=============== SHOW MENU ===============*/
const navMenu = document.getElementById('nav-menu'),
      navToggle = document.getElementById('nav-toggle'),
      navClose = document.getElementById('nav-close')

/* Menu show */
navToggle.addEventListener('click', () =>{
   navMenu.classList.add('show-menu')
})

/* Menu hidden */
navClose.addEventListener('click', () =>{
   navMenu.classList.remove('show-menu')
})

/*=============== SEARCH ===============*/
const search = document.getElementById('search'),
      searchBtn = document.getElementById('search-btn'),
      searchClose = document.getElementById('search-close')

/* Search show */
searchBtn.addEventListener('click', () =>{
   search.classList.add('show-search')
})

/* Search hidden */
searchClose.addEventListener('click', () =>{
   search.classList.remove('show-search')
})

/*=============== LOGIN ===============*/
const login = document.getElementById('login'),
      loginBtn = document.getElementById('login-btn'),
      loginClose = document.getElementById('login-close'),
      loginLink = document.getElementById('login-link'),
      loginLinked = document.getElementById('login-linked')


/*=============== LOGIN ===============*/
const personnel_login = document.getElementById('personnel_login'),
      personnelBtn = document.getElementById('personnel-login-btn'),
      personnelClose = document.getElementById('personnel-login-close')

/*=============== REGISTER ===============*/
const register = document.getElementById('register'),
      registerBtn = document.getElementById('register-btn'),
      registerClose = document.getElementById('register-close')

/*=============== RESET PIN ===============*/
const reset_pin = document.getElementById('reset-pin'),
      resetBtn = document.getElementById('reset-pin-btn'),
      resetBtn2 = document.getElementById('reset-pin-btn2'),
      resetClose = document.getElementById('reset-pin-close')

/* Login Show */
loginBtn.addEventListener('click', () =>{
   login.classList.add('show-login')
})

/* Personnel Login Show */
personnelBtn.addEventListener('click', () =>{
   personnel_login.classList.add('show-personnel-login')
})

loginLink.addEventListener('click', () =>{
   register.classList.remove('show-register')
   login.classList.add('show-login')
})

loginLinked.addEventListener('click', () =>{
   reset_pin.classList.remove('show-reset')
   register.classList.remove('show-register')
   login.classList.add('show-login')
})

/* Login hidden */
loginClose.addEventListener('click', () =>{
   login.classList.remove('show-login')
})

/* Personnel Login hidden */
personnelClose.addEventListener('click', () =>{
   personnel_login.classList.remove('show-personnel-login')
})

/* Register show */
registerBtn.addEventListener('click', () =>{
   login.classList.remove('show-login')
   register.classList.add('show-register')
})

/* Register hidden */
registerClose.addEventListener('click', () =>{
   register.classList.remove('show-register')
})


/* Reset Button show */
resetBtn.addEventListener('click', () =>{
   reset_pin.classList.add('show-reset')
   login.classList.remove('show-login')
   register.classList.remove('show-register')
})

resetBtn2.addEventListener('click', () =>{
   reset_pin.classList.add('show-reset')
   login.classList.remove('show-login')
   register.classList.remove('show-register')
})


resetClose.addEventListener('click', () =>{
   reset_pin.classList.remove('show-reset')
})


function togglePasswordLogin(){
   var x = document.getElementById("password");
   var icon = document.getElementById("reset-icon3");
   icon.classList.toggle("ri-eye");
   if (x.type === "password") {
      x.type = "text";
   } else {
	  x.type = "password";
   }
}


function togglePasswordPinRegister(){
   var x = document.getElementById("pin");
   var icon = document.getElementById("reset-icon4");
   icon.classList.toggle("ri-eye");
   if (x.type === "password") {
      x.type = "text";
   } else {
	  x.type = "password";
   }
}

function togglePasswordConfirmPinRegister(){
   var x = document.getElementById("confirm_pin");
   var icon = document.getElementById("reset-icon5");
   icon.classList.toggle("ri-eye");
   if (x.type === "password") {
      x.type = "text";
   } else {
	  x.type = "password";
   }
}

function togglePasswordPinReset(){
   var x = document.getElementById("pin_reset");
   var icon = document.getElementById("reset-icon1");
   icon.classList.toggle("ri-eye");
   if (x.type === "password") {
      x.type = "text";
   } else {
	  x.type = "password";
   }
}


function togglePasswordConfirmPinReset(){
   var x = document.getElementById("confirm_pin_reset");
   var icon = document.getElementById("reset-icon2");
   icon.classList.toggle("ri-eye");
   if (x.type === "password") {
      x.type = "text";
   } else {
	  x.type = "password";
   }
}

function togglePersonnelLogin(){
   var x = document.getElementById("personnel_password");
   var icon = document.getElementById("reset-icon6");
   icon.classList.toggle("ri-eye");
   if (x.type === "password") {
      x.type = "text";
   } else {
	  x.type = "password";
   }
}