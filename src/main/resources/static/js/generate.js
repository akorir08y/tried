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

     $.post(hosted_url + "/cfms-web/auth/statement",dates ,function(data, status){

         if(data != null){
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
             }else{
                loader.style.display = "none";
                contented.style.display = "block";

                let container = $("#content2");
                let html = new String("");
                html += "<p>No Data to Display</p>";
                container.html(html);
             }

        });

}

function generateOfferingStatementSpecific(){

    var phone = document.getElementById("phone_specific").value;
    var pin = document.getElementById("pass_specific").value;
    var start_date = document.getElementById("start_date_specific").value;
    var end_date = document.getElementById("end_date_specific").value;
    var specific_account = document.getElementById("specific_account").value;
    var account_info = specific_account.split("#");
    var account_name = account_info[0];
    var account_number = account_info[1];


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
        pin: pin,
        account_name: account_name,
        account_number: account_number
    }

    var loader = document.getElementById("loader_spin1");
    var contented = document.getElementById("content3");
    contented.style.display = "none";
    loader.style.display = "block";

     $.post(hosted_url + "/cfms-web/auth/statement-specific",dates ,function(data, status){
            if(data != null){
                 console.log(data);
                 loader.style.display = "none";
                 contented.style.display = "block";

                 let container = $("#content3");
                 let html = new String("");
                 html += "<table id=\"statement\"><tbody id=\"myTable1\">";

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
              }else{
                    loader.style.display = "none";
                    contented.style.display = "block";

                    let container = $("#content2");
                    let html = new String("");
                    html += "<p>No Data to Display for "+account_name+" Account</p>";
                    container.html(html);
              }

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

function showOfferingStatement(){
    var general_account_statement = document.getElementById("general_account_statement");
    var specific_account_statement = document.getElementById("specific_account_statement");

    specific_account_statement.style.display = "none";
    general_account_statement.style.display = "block";
}

function showSpecificOfferingStatement(){
    var general_account_statement = document.getElementById("general_account_statement");
    var specific_account_statement = document.getElementById("specific_account_statement");

    specific_account_statement.style.display = "block";
    general_account_statement.style.display = "none";
}


// Get First and Last Date Statements
function getFirstAndLastDate(){
    var date = new Date();
    var firstDay = new Date(date.getFullYear(), date.getMonth(), 2);
    var lastDay = new Date(date.getFullYear(), date.getMonth() + 1, 1);

    firstDay = firstDay.toISOString().slice(0, 10);
    lastDay = lastDay.toISOString().slice(0, 10);

    // Member Offering Form Dates
    document.getElementById("start_date").value = firstDay;
    document.getElementById("end_date").value = lastDay;

    // Member Offering Specific Form Dates
    document.getElementById("start_date_specific").value = firstDay;
    document.getElementById("end_date_specific").value = lastDay;

}