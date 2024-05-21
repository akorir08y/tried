var hosted_url = location.origin;

function proposeFundsTransfer(){
    var transfer_to_conference = document.getElementById("transfer_to_conference");
    var department_to_bank = document.getElementById("department_to_bank");
    var department_to_another = document.getElementById("department_to_another");
    var withdrawal_from_bank = document.getElementById("withdrawal_from_bank");
    var transfer_to_conference_div = document.getElementById("transfer_to_conference_div");
    var fund_transfer = document.getElementById("fund_transfer");
    var transfer_to_local_bank = document.getElementById("transfer_to_local_bank");

    if(transfer_to_conference.checked == true){
        transfer_to_conference_div.style.display = "block";
        fund_transfer.style.display = "none";
    }else if(transfer_to_local_bank == true){
        transfer_to_local_bank.style.display = "block";
        fund_transfer.style.display = "none";
    }else{
        alert("Other Options are not available yet");
    }

}


function getSourceAccount(){
    var source_account = document.getElementById("source_account").value;
    var cash_option = document.getElementById("cash_option");
    var bank_option = document.getElementById("bank_option");

    if(source_account == "Cash"){
    	cash_option.style.display = "block";
    	bank_option.style.display = "none";
    }else if(source_account == "Bank Deposits"){
    	cash_option.style.display = "none";
        bank_option.style.display = "block";
    }
}


function getSourceNonAccount(){
    var source_account = document.getElementById("source_account_non").value;
    var cash_option = document.getElementById("cash_option1");
    var bank_option = document.getElementById("bank_option1");

    if(source_account == "Cash"){
    	cash_option.style.display = "block";
    	bank_option.style.display = "none";
    }else if(source_account == "Bank Deposits"){
    	cash_option.style.display = "none";
        bank_option.style.display = "block";
    }
}


function selectTrustFunds(){
    var start_date = document.getElementById("start_date").value;
    var end_date = document.getElementById("end_date").value;
    var source_account = document.getElementById("source_account").value;
    var cash_source_account = document.getElementById("cash_source_account").value;
    var bank_source_account = document.getElementById("bank_source_account").value;
    var account_name = new String("");
    var account_number = new String("");
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;
    var phone_number = document.getElementById("phone_number").value;
    var loader_spin_transfer = document.getElementById("loader_spin_transfer");
    var payment_div = document.getElementById("payment_div");

    if(start_date == "" || end_date == ""){
        alert("Ensure All the Dates are Set");
        return false;
    }

    var cash_account = cash_source_account.split("::");
    var bank_account = bank_source_account.split("::");

    if(cash_source_account !== ""){
        account_name = cash_account[0];
        account_name = account_name.replace(" Account", "");
        account_name = account_name.replace(" ACCOUNT", "");
        account_number = cash_account[1].split("(");
        account_number = account_number[0];
    }else if(bank_source_account !== ""){
        account_name = bank_account[0];
        account_name = account_name.replace(" Account", "");
        account_name = account_name.replace(" ACCOUNT", "");
        account_number = bank_account[1].split("(")
        account_number = account_number[0];
    }


    var select_trust_funds = {
        start_date: start_date,
        end_date: end_date,
        account_name: account_name,
        account_number: account_number,
        username: username,
        password: password,
        phone_number: phone_number
    };

    console.log(select_trust_funds);
    loader_spin_transfer.style.display = 'block';

    alert("Getting Available Trust Funds for Transfer");

    $.post(hosted_url + "/cfms-web/personnel/select-trust-funds",select_trust_funds ,function(data, status){
        if(data != null){
            loader_spin_transfer.style.display = "none";
            var preferred = data.payload.fundDistribution;
            var total_amount = data.payload.amount;

            var entries = Object.entries(preferred);
            console.log("Entries: "+ entries);

            var filled_values = [];

            for(var i=0; i < entries.length;i++){
                if(!entries[i][0].startsWith("opening")){
                    filled_values.push(entries[i]);
                }
            }

            var html = new String("");

            html += "<table><tbody><tr><td colspan=\"2\" style=\"text-align:center\">";
            html += "<hr><br><br></td></tr><tr><td style=\"border-bottom:2px solid black;padding-top:7px;\">";
            html += "<b>Account (Balance)</b></td><td style=\"border-bottom:2px solid black;text-align:right;padding-top:7px;\">";
            html += "<b>Amount Offered</b></td></tr>";

            for(var i=0; i < filled_values.length;i++){
                filled_values[i][0] = filled_values[i][0].replace("_", " ");
                var capital = filled_values[i][0].substring(0, 1);
                filled_values[i][0] = filled_values[i][0].substring(1, filled_values[i][0].length);
                filled_values[i][0] = capital.toUpperCase() + filled_values[i][0];

                html += "<tr><td><b style=\"font-size:10px;\">Opening Balance "+filled_values[i][0]+"</b></td>";
                html += "<td style=\"font-size:10px;text-align:left;padding-left:20px;\"><b style=\"font-size:10px;\">KES 0</b></td></tr>";
                html += "<tr><td style=\"font-size:12px;padding-top:10px;\"><b>"+filled_values[i][0]+" ("+filled_values[i][1]+")</td>";
                html += "<td style=\"padding-left:15px;padding-top:10px;\"><input type=\"number\" id="+filled_values[i][0]+" class=\"amt\" value="+filled_values[i][1]+" readonly></td></tr>";
            }

            html += "<tr><td><br></td></td>";
            html += "<tr><td style=\"font-size:12px;padding-top:10px;\"><b>Total Amount</b></td>";
            html += "<td><input type=\"number\" id=\"total_amount\" value="+total_amount+" readonly></td></tr>";
            html += "<tbody></table>";

            $("#content2").html(html);
            payment_div.style.display = "block";

        }else{
            alert("No Funds Available for Transfer");
            window.location.reload();
        }
    });
}


function selectNonTrustFunds(){
    var start_date = document.getElementById("start_date_non").value;
    var end_date = document.getElementById("end_date_non").value;
    var source_account = document.getElementById("source_account_non").value;
    var cash_source_account = document.getElementById("cash_source_account_non").value;
    var bank_source_account = document.getElementById("bank_source_account_non").value;
    var account_name = new String("");
    var account_number = new String("");
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;
    var phone_number = document.getElementById("phone_number").value;
    var loader_spin_transfer = document.getElementById("loader_spin_transfer_non");
    var payment_div = document.getElementById("payment_div1");

    if(start_date == "" || end_date == ""){
        alert("Ensure All the Dates are Set");
        return false;
    }

    var cash_account = cash_source_account.split("::");
    var bank_account = bank_source_account.split("::");

    if(cash_source_account !== ""){
        account_name = cash_account[0];
        account_name = account_name.replace(" Account", "");
        account_name = account_name.replace(" ACCOUNT", "");
        account_number = cash_account[1].split("(");
        account_number = account_number[0];
    }else if(bank_source_account !== ""){
        account_name = bank_account[0];
        account_name = account_name.replace(" Account", "");
        account_name = account_name.replace(" ACCOUNT", "");
        account_number = bank_account[1].split("(")
        account_number = account_number[0];
    }


    var select_non_trust_funds = {
        start_date: start_date,
        end_date: end_date,
        account_name: account_name,
        account_number: account_number,
        username: username,
        password: password,
        phone_number: phone_number
    };

    console.log(select_non_trust_funds);
    loader_spin_transfer.style.display = 'block';

    alert("Getting Available Non Trust Funds for Transfer");

    $.post(hosted_url + "/cfms-web/personnel/select-trust-funds",select_non_trust_funds ,function(data, status){
        if(data != null){
            loader_spin_transfer.style.display = "none";
            var preferred = data.payload.fundDistribution;
            var total_amount = data.payload.amount;

            var entries = Object.entries(preferred);
            console.log("Entries: "+ entries);

            var filled_values = [];

            for(var i=0; i < entries.length;i++){
                if(!entries[i][0].startsWith("opening")){
                    filled_values.push(entries[i]);
                }
            }

            var html = new String("");

            html += "<table><tbody><tr><td colspan=\"2\" style=\"text-align:center\">";
            html += "<hr><br><br></td></tr><tr><td style=\"border-bottom:2px solid black;padding-top:7px;\">";
            html += "<b>Account (Balance)</b></td><td style=\"border-bottom:2px solid black;text-align:right;padding-top:7px;\">";
            html += "<b>Amount Offered</b></td></tr>";

            for(var i=0; i < filled_values.length;i++){
                filled_values[i][0] = filled_values[i][0].replace("_", " ");
                var capital = filled_values[i][0].substring(0, 1);
                filled_values[i][0] = filled_values[i][0].substring(1, filled_values[i][0].length);
                filled_values[i][0] = capital.toUpperCase() + filled_values[i][0];

                html += "<tr><td><b style=\"font-size:10px;\">Opening Balance "+filled_values[i][0]+"</b></td>";
                html += "<td style=\"font-size:10px;text-align:left;padding-left:20px;\"><b style=\"font-size:10px;\">KES 0</b></td></tr>";
                html += "<tr><td style=\"font-size:12px;padding-top:10px;\"><b>"+filled_values[i][0]+" ("+filled_values[i][1]+")</td>";
                html += "<td style=\"padding-left:15px;padding-top:10px;\"><input type=\"number\" id="+filled_values[i][0]+" class=\"amt\" value="+filled_values[i][1]+" readonly></td></tr>";
            }

            html += "<tr><td><br></td></td>";
            html += "<tr><td style=\"font-size:12px;padding-top:10px;\"><b>Total Amount</b></td>";
            html += "<td><input type=\"number\" id=\"total_amount\" value="+total_amount+" readonly></td></tr>";
            html += "<tbody></table>";

            $("#content2").html(html);
            payment_div.style.display = "block";

        }else{
            alert("No Funds Available for Transfer");
            window.location.reload();
        }
    });
}


function fundsTransferToConference(){
    var start_date = document.getElementById("start_date").value;
    var end_date = document.getElementById("end_date").value;
    var cash_source_account = document.getElementById("cash_source_account").value;
    var bank_source_account = document.getElementById("bank_source_account").value;
    var account_name = new String("");
    var account_number = new String("");
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;
    var phone_number_one = document.getElementById("phone_number").value;
    var phone_number_two = document.getElementById("other_number_fund").value;
    var total_amount = document.getElementById("total_amount").value;
    var amt = document.getElementsByClassName("amt");
    var registered_number = document.getElementById("registered_number");
    var my_other_number = document.getElementById("my_other_number");

    var phone_number = new String("");

    if(start_date == "" || end_date == ""){
        alert("Ensure All the Dates are Set");
        return false;
    }

    var cash_account = cash_source_account.split("::");
    var bank_account = bank_source_account.split("::");

    if(cash_source_account !== ""){
        account_name = cash_account[0];
        account_name = account_name.replace(" Account", "");
        account_name = account_name.replace(" ACCOUNT", "");
        account_number = cash_account[1].split("(");
        account_number = account_number[0];
    }else if(bank_source_account !== ""){
        account_name = bank_account[0];
        account_name = account_name.replace(" Account", "");
        account_name = account_name.replace(" ACCOUNT", "");
        account_number = bank_account[1].split("(")
        account_number = account_number[0];
    }

    // The Funds in Question
    var funds1 = [];
    var amt3 = [];

    for (var i = 0; i < amt.length; i++) {
      	if(amt[i].value != ''){
       		var amount_id = amt[i].id;
       		funds1.push(amount_id);
       		amt3.push(amt[i].value);
        }
    }

    if(registered_number.checked == true) {
        phone_number = phone_number_one;
    }else if(my_other_number.checked == true) {
        phone_number = phone_number_two;
    }

    var funds = {
         start_date: start_date,
         end_date: end_date,
         account_name: account_name,
         account_number: account_number,
         username: username,
         password: password,
         phone_number: phone_number,
         trust_funds: funds1,
         fund_amount: amt3,
         total_amount: total_amount
    }

    alert("Funds Transfer Initiated");

    $.post(hosted_url + "/cfms-web/personnel/member_receive_funds_conference",funds ,function(data, status){
        if(data.status == 0){
            alert("Funds Transfer Successful");
            window.location.reload();
        }else{
            alert("Transfer Unsuccessful");
            window.location.reload();
        }
    });

}


function fundsTransferToBank(){
    var start_date = document.getElementById("start_date_non").value;
    var end_date = document.getElementById("end_date_non").value;
    var cash_source_account = document.getElementById("cash_source_account_non").value;
    var bank_source_account = document.getElementById("bank_source_account_non").value;
    var account_name = new String("");
    var account_number = new String("");
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;
    var phone_number_one = document.getElementById("phone_number").value;
    var phone_number_two = document.getElementById("other_number_fund1").value;
    var total_amount = document.getElementById("total_amount").value;
    var amt = document.getElementsByClassName("amt");
    var registered_number = document.getElementById("registered_number1");
    var my_other_number = document.getElementById("my_other_number1");

    var phone_number = new String("");

    if(start_date == "" || end_date == ""){
        alert("Ensure All the Dates are Set");
        return false;
    }

    var cash_account = cash_source_account.split("::");
    var bank_account = bank_source_account.split("::");

    if(cash_source_account !== ""){
        account_name = cash_account[0];
        account_name = account_name.replace(" Account", "");
        account_name = account_name.replace(" ACCOUNT", "");
        account_number = cash_account[1].split("(");
        account_number = account_number[0];
    }else if(bank_source_account !== ""){
        account_name = bank_account[0];
        account_name = account_name.replace(" Account", "");
        account_name = account_name.replace(" ACCOUNT", "");
        account_number = bank_account[1].split("(")
        account_number = account_number[0];
    }

    // The Funds in Question
    var funds1 = [];
    var amt3 = [];

    for (var i = 0; i < amt.length; i++) {
      	if(amt[i].value != ''){
       		var amount_id = amt[i].id;
       		funds1.push(amount_id);
       		amt3.push(amt[i].value);
        }
    }

    if(registered_number.checked == true) {
        phone_number = phone_number_one;
    }else if(my_other_number.checked == true) {
        phone_number = phone_number_two;
    }

    var funds = {
         start_date: start_date,
         end_date: end_date,
         account_name: account_name,
         account_number: account_number,
         username: username,
         password: password,
         phone_number: phone_number,
         trust_funds: funds1,
         fund_amount: amt3,
         total_amount: total_amount
    }

    alert("Non Trust Funds Transfer Initiated");

    $.post(hosted_url + "/cfms-web/personnel/member_receive_funds_conference",funds ,function(data, status){
        if(data.status == 0){
            alert("Funds Transfer Successful");
            window.location.reload();
        }else{
            alert("Transfer Unsuccessful");
            window.location.reload();
        }
    });

}



function getMemberFirstAndLastDate(){
    var date = new Date();
    var firstDay = new Date(date.getFullYear(), date.getMonth(), 2);
    var lastDay = new Date(date.getFullYear(), date.getMonth() + 1, 1);

    firstDay = firstDay.toISOString().slice(0, 10);
    lastDay = lastDay.toISOString().slice(0, 10);


    // Local Church Statement Form Dates
    document.getElementById("start_date").value = firstDay;
    document.getElementById("end_date").value = lastDay;
}


// Get the Payment Details
function getPaymentNumber(){
	var registered = document.getElementById("registered_number");
	var my_other_number = document.getElementById("my_other_number");
	var phone = document.getElementById("phone_number").value;
	var total = document.getElementById("total_amount").value;
	var other_number_div = document.getElementById("other_number_div");


	if(registered.checked == true){
	    other_number_div.style.display = "none";
		var payment_info = "Ensure "+ total +"/= has been deposited on the mobile money account for "+phone;
		document.getElementById("payment_line").innerHTML = payment_info;
	}else if(my_other_number.checked == true){
        other_number_div.style.display = "block";
	}
}


// Get the Payment Details
function getPaymentNumber1(){
	var registered = document.getElementById("registered_number1");
	var my_other_number = document.getElementById("my_other_number1");
	var phone = document.getElementById("phone_number").value;
	var total = document.getElementById("total_amount").value;
	var other_number_div = document.getElementById("other_number_div1");


	if(registered.checked == true){
	    other_number_div.style.display = "none";
		var payment_info = "Ensure "+ total +"/= has been deposited on the mobile money account for "+phone;
		document.getElementById("payment_line1").innerHTML = payment_info;
	}else if(my_other_number.checked == true){
        other_number_div.style.display = "block";
	}
}


function getNumberPaid(){
    var typed_number = document.getElementById("other_number_fund").value;
    var total = document.getElementById("total_amount").value;

    var payment_info = "Ensure "+ total +"/= has been deposited on the mobile money account for "+typed_number;
    document.getElementById("payment_line").innerHTML = payment_info;
}



function getNumberPaid1(){
    var typed_number = document.getElementById("other_number_fund1").value;
    var total = document.getElementById("total_amount").value;

    var payment_info = "Ensure "+ total +"/= has been deposited on the mobile money account for "+typed_number;
    document.getElementById("payment_line1").innerHTML = payment_info;
}