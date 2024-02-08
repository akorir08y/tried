function callPage(){
		$.ajax({
			url: "register-member.html",
			type: "GET",
			dataType: "html",
			withCredentials: true,
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


function fetch(){
	var request = new XMLHttpRequest();
	request.onreadystatechange = function(){
	    if(this.readyState === 4 && this.status === 200){
	        var response = this.responseText;
	        document.getElementById("content").innerHTML=response;
	    }
    };
    request.open("GET", "/cfms/authenticate/profile", true);
	request.send();
}


function fetch1(){
	$.ajax({
    	url: "/cfms/authenticate/profile",
    	type: "GET",
    	dataType: "html",
    	withCredentials: true,
    	success: function(response){
    		console.log('The page was loaded', response);
    		$('#content').html(response);
    	},
    	error: function(error){
    		console.log('The page was not loaded');
    	},
    	complete: function(xhr, status){
    		console.log('The request is complete');
    	}
    });
}

function offeringStatement(){
	var request = new XMLHttpRequest();
	request.onreadystatechange = function(){
	    if(this.readyState === 4 && this.status === 200){
	        var response = this.responseText;
	        document.getElementById("content").innerHTML=response;
	    }
    };
    request.open("GET", "/cfms/authenticate/statement", true);
	request.send();
}

function offeringStatement1(){
	$.ajax({
    	url: "/cfms/authenticate/statement",
    	type: "GET",
    	dataType: "html",
    	withCredentials: true,
    	success: function(response){
    		console.log('The page was loaded', response);
    		$('#content').html(response);
    	},
    	error: function(error){
    		console.log('The page was not loaded');
    	},
    	complete: function(xhr, status){
    		console.log('The request is complete');
    	}
    });
}


function getNavigation(){
	document.getElementById("content1").style.display = "none";
	document.getElementById("content2").style.display = "block";
}

function getNavigation1(){
	document.getElementById("content1").style.display = "block";
	document.getElementById("content2").style.display = "none";
}


