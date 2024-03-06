var hosted_url = location.origin;

function generateOfferingStatement(){

    var phone = document.getElementById("phone").value;
    var pin = document.getElementById("pass").value;
    var start_date = document.getElementById("start_date").value;
    var end_date = document.getElementById("end_date").value;

    if(start_date == null || end_date == null){
        alert("Make Sure you input the start and end dates");
        return false;
    }

    if (start_date >= end_date){
        alert("The Start Date must predate the End date");
        return false;
    }

    var dates = {
        start_date: start_date,
        end_date: end_date,
        phone_number: phone,
        pin: pin
    }

    var loader = document.getElementById("loader_spin");
    var contented = document.getElementById("content2");
    contented.style.display = "none";
    loader.style.display = "block";

     $.post(hosted_url + "/cfms/auth/statement",dates ,function(data, status){
             console.log(data);
             loader.style.display = "none";
             contented.style.display = "block";

             let container = $("#content2");
             let html = new String("");
             html += "<table id=\"statement\"><tbody id=\"myTable\">";

             $.each(data.payload.members, (i, statement) => {
                  console.log("Statement:" +statement);
                  html += "<tr tabindex=\"0\"><td>";
                  html += "<span class='left'>"+statement.narration+"</span>";
                  html += "<span class='right-blue'>"+statement.amountRefunded +"(Balance: "+ statement.balance + ")</span><br><br>";
                  html += "<span class='left-below'>Receipt No: "+statement.receiptNumber+"</span><br>";
                  html += "<span class='right'>"+statement.transactionDate+"</span>";
                  html += "</td></tr>";
             });
             html += "</tbody></table>";
             container.html(html); //Append the table to the container element

        });
}


function exportStatement(){

    var start_date = document.getElementById("start_date").value;
    var end_date = document.getElementById("end_date").value;

    if(start_date == null || end_date == null){
        alert("Make Sure you input the start and end dates");
        return false;
    }

    if (start_date >= end_date){
        alert("The Start Date must predate the End date");
        return false;
    }
}

function passwordVisibility(){
	var x = document.getElementById("pin");
	var icon = document.getElementById("toggle-password");

	if (x.type === "password") {
		x.type = "text";
	} else {
		x.type = "password";
	}
}


function passwordVisibility1(){
	var x = document.getElementById("confirm_pin");
	var icon = document.getElementById("toggle-password1");

	if (x.type === "password") {
		x.type = "text";
	} else {
		x.type = "password";
	}
}
