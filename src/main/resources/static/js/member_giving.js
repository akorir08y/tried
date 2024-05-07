function GetTotal()
{
    /*Footer Calculation*/
    var sum= 0;
    var amts =  document.getElementsByName("amt");


    for (let index = 0; index < amts.length; index++)
    {
        var amt = amts[index].value;
        sum = +(sum) +  +(amt) ;
    }

    document.getElementById("FTotal").value = sum;
}

function GetTotal1()
{
    /*Footer Calculation*/
    var sum= 0;
    var amts =  document.getElementsByName("amt");


    for (let index = 0; index < amts.length; index++)
    {
        var amt = amts[index].value;
        sum = +(sum) +  +(amt) ;
    }

    document.getElementById("FTotal1").value = sum;
}

function GetTotal2()
{
    /*Footer Calculation*/
    var sum= 0;
    var amts =  document.getElementsByName("amt");


    for (let index = 0; index < amts.length; index++)
    {
        var amt = amts[index].value;
        sum = +(sum) +  +(amt) ;
    }

    document.getElementById("FTotal2").value = sum;
}

function GetTotal3()
{
    /*Footer Calculation*/
    var sum= 0;
    var amts =  document.getElementsByName("amt");


    for (let index = 0; index < amts.length; index++)
    {
        var amt = amts[index].value;
        sum = +(sum) +  +(amt) ;
    }

    document.getElementById("FTotal3").value = sum;
}

function myFunction() {
  var home_church = document.getElementById("home_church");
  var host_church = document.getElementById("host_church");
  var member_giving = document.getElementById("member_giving");
  var home_church_view = document.getElementById("home_church_self");
  var home_church_others = document.getElementById("home_church_others");
  var self = document.getElementById("self");
  var others = document.getElementById("others");


  var name = "Cfms";
  var statement = "Is "+ name +" Your home church ?";

  var text = document.getElementById("church_code_section");
  //var member_giving = document.getElementById("start_section");

  if (home_church.checked == true && self.checked == true){
	  if(confirm(statement) == true){
		member_giving.style.display = "none";
		home_church_view.style.display = "block";
	  }
  }else if(home_church.checked == true && others.checked == true){
	  if(confirm(statement) == true){
		member_giving.style.display = "none";
		home_church_others.style.display = "block";
	  }
  }else{
	  text.style.display = "block";
	  member_giving.style.display = "none";
  }
}


function otherMember(){
	var other_member = document.getElementById("other_member");
	var identify_by = document.getElementById("identify_member_by");
	var identify_only = document.getElementById("identify_member_only");
	var associated_phone_number = document.getElementById("associated_phone_number");
	var cfms_id = document.getElementById("cfms_id_input");

	if(other_member.checked == true){
		identify_by.style.display = "block";
		associated_phone_number.style.display = "none";
	}else{
		identify_only.style.display = "block";
		associated_phone_number.style.display = "block";
		identify_by.style.display = "none";
		cfms_id.style.display = "none";
	}
}


function showIdentifier(){
	var phone_number = document.getElementById("phone_number");
	var cfms_identifier = document.getElementById("cfms_id");
	var identify_only = document.getElementById("identify_member_only");
	var cfms_id = document.getElementById("cfms_id_input");
	var associated_phone_number = document.getElementById("associated_phone_number");

	if(phone_number.checked == true){
		identify_only.style.display = "block";
		associated_phone_number.style.display = "block";
		cfms_id.style.display = "none";
	}else if(cfms_identifier.checked == true){
		identify_only.style.display = "block";
		cfms_id.style.display = "block";
		associated_phone_number.style.display = "none";
	}
}


function showIdentifier1(){
	var phone_number = document.getElementById("phone_number1");
	var cfms_identifier = document.getElementById("cfms_id1");
	var identify_only = document.getElementById("identify_member_only1");
	var cfms_id = document.getElementById("cfms_id_input1");
	var associated_phone_number = document.getElementById("associated_phone_number1");

	if(phone_number.checked == true){
		identify_only.style.display = "block";
		associated_phone_number.style.display = "block";
		cfms_id.style.display = "none";
	}else if(cfms_identifier.checked == true){
		identify_only.style.display = "block";
		cfms_id.style.display = "block";
		associated_phone_number.style.display = "none";
	}
}


function getChurchCode(){
	var church_code = document.getElementById("churchCode").value;
	var church_others = document.getElementById("home_church_others");
	var church_section = document.getElementById("church_code_section");

	if(church_code.length == 5){
		church_section.style.display = "none";
		church_others.style.display = "block";
	}
}


function getTrustFundAccounts(){
	document.getElementById("home_church_self").style.display = "none";
	var data = {
		"phoneOneModeOfPayment": "M-PESA",
		"departmentalAccounts": "[\"Church Meeting Account::509189\",\"Laptop  Account::508749\",\"Pen Account::512069\",\"Development Account::13825\",\"Kamungei Account::13834\",\"Children Account::351108\",\"Adventurers Account::366035\",\"Lunch Fellowship  Account::472328\",\"Camp Meeting Expenses Account::395883\",\"Laptop Account::513527\"]",
		"churchName": "Cfms",
		"conferenceName": "Cfms Project Kenya Conference",
		"districtName": "Cfms Kenya District",
		"conferenceNumber": "CFMS",
		"otherPhoneNumber": "+254775351396",
		"memberName": "Andrew Keitany",
		"isPessonnel": true,
		"groups": "[\"Moi University::1016\"]",
		"districtNumber": "CFMS1",
		"churchNumber": "29001",
		"otherPhoneModeOfPayment": "Orange Money",
		"membershipNumber": "CN3196",
		"phoneNumber": "254707981971",
		"trustFundAccounts": "[\"Conference Development Account::426234\",\"Thirteenth Sabbath Account::191295\",\"Tithe Account::127989\",\"Camp Meeting Account::127991\",\"Tithe Account::13822\",\"Camp Meeting Account::13824\",\"Combined Offerings Account::13823\",\"Combined Offerings Account::127990\"]",
		"sessionNumber": 11749295,
		"specialTrustFundAccounts": "[\"UEAB Account::457992\",\"Djibouti Mission Account::454913\"]",
		"function": "mobileRequestChurchDetails",
		"availableMeansOfPayment": "[M-PESA]",
		"status": 0
	}

	// Trust Fund Accounts
	var request = data.trustFundAccounts;
	request = request.replace('[', '');
	request = request.replace(']', '');
	const departments = request.split(',');
	var html = new String("");
	const accounts = [];

	html += "<div class=\"cardHeader\">";
	html += "<h2 style=\"text-align:center;margin-top:10px;margin-bottom:10px\">Giving Offerings</h2></div>";
	html += "<div class=\"responseDiv\" style=\"display:none;\"></div><br><hr>";
	html += "<table><form id=\"trust_fund_form\" name=\"trust_funds_form\"><tr><td><label class=\"label_input\"><b> To: </b></label></td>";
	html += "<td><input type=\"text\" class=\"login_input\" id=\"churchName3\" name=\"churchName3\" placeholder=\"Cfms\" readonly></td></tr>";
	html += "<tr><td><label class=\"label_input\"><b> For: </b></label></td>";
	html += "<td><input type=\"text\" id=\"memberName2\" name=\"churchCode\" placeholder=\"Andrew Keitany\" readonly></td></tr>";
	html += "</tr><tr>";
	html += "<td colspan=\"2\"><h2 style=\"text-align: center;font-size:14px;padding-top:10px;padding-bottom:10px;\">Trust Funds</h2></td>";
	html += "</tr><tr><td colspan=\"2\" style=\"text-align:center\"><hr></tr>";
	html += "<tr><td style=\"border-bottom:2px solid black;padding-top:7px;\"><b>Contribution Type</b></td>";
	html += "<td style=\"border-bottom:2px solid black;text-align:right;padding-top:7px;\"><b>Amount Offered</b></td></tr>";

	for (i=0;i < departments.length; i++){
		departments[i] = departments[i].replace('"','');
		departments[i] = departments[i].replace('"','');
		departments[i] = departments[i].split("::")
		accounts.push(departments[i][0]);
    }

    console.log("Trust Fund Accounts: "+ accounts);

    let uniqueChars = [...new Set(accounts)];
    console.log("Unique Accounts: "+ uniqueChars);

    for(i=0;i < uniqueChars.length;i++){
        html += "<tr>";
        html += "<td style=\"font-size:12px;padding-top:10px;\"><b>"+ uniqueChars[i] +"</b><input type=\"hidden\" class=\"trust_funds\" value='"+uniqueChars[i]+"'></td>";
        html += "<td style=\"padding-left:15px;padding-top:10px;\">";
        html += "<input type=\"number\" id='"+uniqueChars[i]+"' class=\"amt\" name=\"amt\" placeholder=\"\" onfocus=\"GetTotal()\" onkeydown=\"GetTotal()\"  onkeyup=\"GetTotal()\">";
        html += "</td></tr>";
	}
	html += "<tr><td style=\"font-size:12px;\"><b>Total</b></td>";
	html += "<td style=\"padding-left:15px;padding-top:10px;\"><input type=\"number\" class=\"login_input\" id=\"FTotal\" name=\"churchCode\" placeholder=\"\" readonly></td></tr>";
	html += "<tr><td><button type=\"button\" class=\"btn\" onclick=\"getPaymentDiv()\" id=\"payment_button\">Confirm</button></td></tr>";
	html += "</form><tbody></table>";

	$("#trust_funds_div").html(html);

}


function getNonTrustFundAccounts(){
	document.getElementById("home_church_self").style.display = "none";
	var data = {
		"phoneOneModeOfPayment": "M-PESA",
		"departmentalAccounts": "[\"Church Meeting  Account::509189\",\"Laptop  Account::508749\",\"Pen Account::512069\",\"Development Account::13825\",\"Kamungei Account::13834\",\"Children Account::351108\",\"Adventurers Account::366035\",\"Lunch Fellowship  Account::472328\",\"Camp Meeting Expenses Account::395883\",\"Laptop Account::513527\"]",
		"churchName": "Cfms",
		"conferenceName": "Cfms Project Kenya Conference",
		"districtName": "Cfms Kenya District",
		"conferenceNumber": "CFMS",
		"otherPhoneNumber": "+254775351396",
		"memberName": "Andrew Keitany",
		"isPessonnel": true,
		"groups": "[\"Moi University::1016\"]",
		"districtNumber": "CFMS1",
		"churchNumber": "29001",
		"otherPhoneModeOfPayment": "Orange Money",
		"membershipNumber": "CN3196",
		"phoneNumber": "254707981971",
		"trustFundAccounts": "[\"Conference Development Account::426234\",\"Thirteenth Sabbath Account::191295\",\"Tithe Account::127989\",\"Camp Meeting Account::127991\",\"Tithe Account::13822\",\"Camp Meeting Account::13824\",\"Combined Offerings Account::13823\",\"Combined Offerings Account::127990\"]",
		"sessionNumber": 11749295,
		"specialTrustFundAccounts": "[\"UEAB Account::457992\",\"Djibouti Mission Account::454913\"]",
		"function": "mobileRequestChurchDetails",
		"availableMeansOfPayment": "[M-PESA]",
		"status": 0
	}

	// Trust Fund Accounts
	var request = data.departmentalAccounts;
	request = request.replace('[', '');
	request = request.replace(']', '');
	const departments = request.split(',');
	var html = new String("");
	var accounts = [];

	html += "<div class=\"cardHeader\">";
	html += "<h2 style=\"text-align:center;margin-top:10px;margin-bottom:10px\">Giving Offerings</h2></div>";
	html += "<div class=\"responseDiv\" style=\"display:none;\"></div><br><hr>";
	html += "<table><form id=\"non_trust_fund_form\"><tr><td><label class=\"label_input\"><b> To: </b></label></td>";
	html += "<td><input type=\"text\" class=\"login_input\" id=\"churchName3\" name=\"churchName3\" placeholder=\"Cfms\" readonly></td></tr>";
	html += "<tr><td><label class=\"label_input\"><b> For: </b></label></td>";
	html += "<td><input type=\"text\" id=\"memberName2\" name=\"churchCode\" placeholder=\"Andrew Keitany\" readonly></td></tr>";
	html += "</tr><tr>";
	html += "<td colspan=\"2\"><h2 style=\"text-align: center;font-size:14px;padding-top:10px;padding-bottom:10px;\">Non Trust Funds</h2></td>";
	html += "</tr><tr><td colspan=\"2\" style=\"text-align:center\"><hr></tr>";
	html += "<tr><td style=\"border-bottom:2px solid black;padding-top:7px;\"><b>Contribution Type</b></td>";
	html += "<td style=\"border-bottom:2px solid black;text-align:right;padding-top:7px;\"><b>Amount Offered</b></td></tr>";

	for (i=0;i < departments.length; i++){
    		departments[i] = departments[i].replace('"','');
    		departments[i] = departments[i].replace('"','');
    		departments[i] = departments[i].split("::")
    		accounts.push(departments[i][0]);
    }

    let uniqueChars = [...new Set(accounts)];

    for(i=0;i < uniqueChars.length;i++){
        html += "<tr>";
        html += "<td style=\"font-size:12px;padding-top:10px;\"><b>"+ uniqueChars[i] +"</b><input type=\"hidden\" class=\"non_trust_funds\" value='"+uniqueChars[i]+"'></td>";
        html += "<td style=\"padding-left:15px;padding-top:10px;\">";
        html += "<input type=\"number\" id='"+uniqueChars[i]+"' class=\"amt\" name=\"amt\" placeholder=\"\" onfocus=\"GetTotal1()\" onkeydown=\"GetTotal1()\"  onkeyup=\"GetTotal1()\">";
        html += "</td></tr>";
    }

	html += "<tr><td style=\"font-size:12px;\"><b>Total</b></td>";
	html += "<td style=\"padding-left:15px;padding-top:10px;\"><input type=\"number\" class=\"login_input\" id=\"FTotal1\" name=\"churchCode\" placeholder=\"\" readonly></td></tr>";
	html += "<tr><td><button type=\"button\" class=\"btn\" onclick=\"getPaymentDiv1()\" id=\"payment_button1\">Confirm</button></td></tr>";
	html += "</form><tbody></table>";

	$("#trust_funds_div").html(html);

}



function getSpecialTrustFundAccounts(){
	document.getElementById("home_church_self").style.display = "none";
	var data = {
		"phoneOneModeOfPayment": "M-PESA",
		"departmentalAccounts": "[\"Church Meeting  Account::509189\",\"Laptop  Account::508749\",\"Pen Account::512069\",\"Development Account::13825\",\"Kamungei Account::13834\",\"Children Account::351108\",\"Adventurers Account::366035\",\"Lunch Fellowship  Account::472328\",\"Camp Meeting Expenses Account::395883\",\"Laptop Account::513527\"]",
		"churchName": "Cfms",
		"conferenceName": "Cfms Project Kenya Conference",
		"districtName": "Cfms Kenya District",
		"conferenceNumber": "CFMS",
		"otherPhoneNumber": "+254775351396",
		"memberName": "Andrew Keitany",
		"isPessonnel": true,
		"groups": "[\"Moi University::1016\"]",
		"districtNumber": "CFMS1",
		"churchNumber": "29001",
		"otherPhoneModeOfPayment": "Orange Money",
		"membershipNumber": "CN3196",
		"phoneNumber": "254707981971",
		"trustFundAccounts": "[\"Conference Development Account::426234\",\"Thirteenth Sabbath Account::191295\",\"Tithe Account::127989\",\"Camp Meeting Account::127991\",\"Tithe Account::13822\",\"Camp Meeting Account::13824\",\"Combined Offerings Account::13823\",\"Combined Offerings Account::127990\"]",
		"sessionNumber": 11749295,
		"specialTrustFundAccounts": "[\"UEAB Account::457992\",\"Djibouti Mission Account::454913\"]",
		"function": "mobileRequestChurchDetails",
		"availableMeansOfPayment": "[M-PESA]",
		"status": 0
	}

	// Trust Fund Accounts
	var request = data.specialTrustFundAccounts;
	request = request.replace('[', '');
	request = request.replace(']', '');
	const departments = request.split(',');
	var html = new String("");
	var accounts = [];

	html += "<div class=\"cardHeader\">";
	html += "<h2 style=\"text-align:center;margin-top:10px;margin-bottom:10px\">Giving Offerings</h2></div>";
	html += "<div class=\"responseDiv\" style=\"display:none;\"></div><br><hr>";
	html += "<table><form id=\"special_trust_fund_form\"><tr><td><label class=\"label_input\"><b> To: </b></label></td>";
	html += "<td><input type=\"text\" class=\"login_input\" id=\"churchName3\" name=\"churchName3\" placeholder=\"Cfms\" readonly></td></tr>";
	html += "<tr><td><label class=\"label_input\"><b> For: </b></label></td>";
	html += "<td><input type=\"text\" id=\"memberName2\" name=\"churchCode\" placeholder=\"Andrew Keitany\" readonly></td></tr>";
	html += "</tr><tr>";
	html += "<td colspan=\"2\"><h2 style=\"text-align: center;font-size:14px;padding-top:10px;padding-bottom:10px;\">Special Trust Funds</h2></td>";
	html += "</tr><tr><td colspan=\"2\" style=\"text-align:center\"><hr></tr>";
	html += "<tr><td style=\"border-bottom:2px solid black;padding-top:7px;\"><b>Contribution Type</b></td>";
	html += "<td style=\"border-bottom:2px solid black;text-align:right;padding-top:7px;\"><b>Amount Offered</b></td></tr>";


    for (i=0;i < departments.length; i++){
    	departments[i] = departments[i].replace('"','');
    	departments[i] = departments[i].replace('"','');
    	departments[i] = departments[i].split("::")
    	accounts.push(departments[i][0]);
    }

    let uniqueChars = [...new Set(accounts)];

    for(i=0;i < uniqueChars.length;i++){
        html += "<tr>";
        html += "<td style=\"font-size:12px;padding-top:10px;\"><b>"+ uniqueChars[i] +"</b><input type=\"hidden\" class=\"special_trust_funds\" value='"+uniqueChars[i]+"'></td>";
        html += "<td style=\"padding-left:15px;padding-top:10px;\">";
        html += "<input type=\"number\" id='"+uniqueChars[i]+"' class=\"amt\" name=\"amt\" placeholder=\"\" onfocus=\"GetTotal2()\" onkeydown=\"GetTotal2()\"  onkeyup=\"GetTotal2()\">";
        html += "</td></tr>";
    }

	html += "<tr><td style=\"font-size:12px;\"><b>Total</b></td>";
	html += "<td style=\"padding-left:15px;padding-top:10px;\"><input type=\"number\" class=\"login_input\" id=\"FTotal2\" name=\"churchCode\" placeholder=\"\" readonly></td></tr>";
	html += "<tr><td><button type=\"button\" class=\"btn\" onclick=\"getPaymentDiv2()\" id=\"payment_button2\">Confirm</button></td></tr>";
	html += "</form><tbody></table>";

	$("#trust_funds_div").html(html);

}


function getBothFundAccounts(){
	document.getElementById("home_church_self").style.display = "none";
	var data = {
		"phoneOneModeOfPayment": "M-PESA",
		"departmentalAccounts": "[\"Church Meeting  Account::509189\",\"Laptop  Account::508749\",\"Pen Account::512069\",\"Development Account::13825\",\"Kamungei Account::13834\",\"Children Account::351108\",\"Adventurers Account::366035\",\"Lunch Fellowship  Account::472328\",\"Camp Meeting Expenses Account::395883\",\"Laptop Account::513527\"]",
		"churchName": "Cfms",
		"conferenceName": "Cfms Project Kenya Conference",
		"districtName": "Cfms Kenya District",
		"conferenceNumber": "CFMS",
		"otherPhoneNumber": "+254775351396",
		"memberName": "Andrew Keitany",
		"isPessonnel": true,
		"groups": "[\"Moi University::1016\"]",
		"districtNumber": "CFMS1",
		"churchNumber": "29001",
		"otherPhoneModeOfPayment": "Orange Money",
		"membershipNumber": "CN3196",
		"phoneNumber": "254707981971",
		"trustFundAccounts": "[\"Conference Development Account::426234\",\"Thirteenth Sabbath Account::191295\",\"Tithe Account::127989\",\"Camp Meeting Account::127991\",\"Tithe Account::13822\",\"Camp Meeting Account::13824\",\"Combined Offerings Account::13823\",\"Combined Offerings Account::127990\"]",
		"sessionNumber": 11749295,
		"specialTrustFundAccounts": "[\"UEAB Account::457992\",\"Djibouti Mission Account::454913\"]",
		"function": "mobileRequestChurchDetails",
		"availableMeansOfPayment": "[M-PESA]",
		"status": 0
	}

	// Trust Fund Accounts
	var request = data.trustFundAccounts;
	var request1 = data.departmentalAccounts;
	request = request.replace('[', '');
	request = request.replace(']', '');
	request1 = request1.replace('[', '');
	request1 = request1.replace(']', '');
	const departments = request.split(',');
	const departments1 = request1.split(',');
	var html = new String("");
	var accounts = [];
	var accounts2 = [];

	html += "<div class=\"cardHeader\">";
	html += "<h2 style=\"text-align:center;margin-top:10px;margin-bottom:10px\">Giving Offerings</h2></div>";
	html += "<div class=\"responseDiv\" style=\"display:none;\"></div><br><hr>";
	html += "<table><form id=\"both_funds_form\"><tr><td><label class=\"label_input\"><b> To: </b></label></td>";
	html += "<td><input type=\"text\" class=\"login_input\" id=\"churchName3\" name=\"churchName3\" placeholder=\"Cfms\" readonly></td></tr>";
	html += "<tr><td><label class=\"label_input\"><b> For: </b></label></td>";
	html += "<td><input type=\"text\" id=\"memberName2\" name=\"churchCode\" placeholder=\"Andrew Keitany\" readonly></td></tr>";
	html += "</tr><tr>";
	html += "<td colspan=\"2\"><h2 style=\"text-align: center;font-size:14px;padding-top:10px;padding-bottom:10px;\">Trust Funds</h2></td>";
	html += "</tr><tr><td colspan=\"2\" style=\"text-align:center\"><hr></tr>";
	html += "<tr><td style=\"border-bottom:2px solid black;padding-top:7px;\"><b>Contribution Type</b></td>";
	html += "<td style=\"border-bottom:2px solid black;text-align:right;padding-top:7px;\"><b>Amount Offered</b></td></tr>";

    for (i=0;i < departments.length; i++){
       	departments[i] = departments[i].replace('"','');
       	departments[i] = departments[i].replace('"','');
       	departments[i] = departments[i].split("::")
       	accounts.push(departments[i][0]);
    }

    let uniqueChars = [...new Set(accounts)];

    for(i=0;i < uniqueChars.length;i++){
        html += "<tr>";
        html += "<td style=\"font-size:12px;padding-top:10px;\"><b>"+ uniqueChars[i] +"</b><input type=\"hidden\" class=\"trust_funds1\" value='"+uniqueChars[i]+"'></td>";
        html += "<td style=\"padding-left:15px;padding-top:10px;\">";
        html += "<input type=\"number\" id='"+uniqueChars[i]+"' class=\"amt\" name=\"amt\" placeholder=\"\" onfocus=\"GetTotal3()\" onkeydown=\"GetTotal3()\"  onkeyup=\"GetTotal3()\">";
        html += "</td></tr>";
    }


	html += "<tbody></table>";

	html += "<table><tr>";
	html += "<td colspan=\"2\"><h2 style=\"text-align: center;font-size:14px;padding-top:10px;padding-bottom:10px;\">Non Trust Funds</h2></td>";
	html += "</tr><tr><td colspan=\"2\" style=\"text-align:center\"><hr></tr>";
	html += "<tr><td style=\"border-bottom:2px solid black;padding-top:7px;\"><b>Contribution Type</b></td>";
	html += "<td style=\"border-bottom:2px solid black;text-align:right;padding-top:7px;\"><b>Amount Offered</b></td></tr>";

    for (i=0;i < departments1.length; i++){
       	departments1[i] = departments1[i].replace('"','');
       	departments1[i] = departments1[i].replace('"','');
       	departments1[i] = departments1[i].split("::")
       	accounts2.push(departments1[i][0]);
    }

    let uniqueChars2 = [...new Set(accounts2)];
    for(i=0;i < uniqueChars2.length;i++){
        html += "<tr>";
        html += "<td style=\"font-size:12px;padding-top:10px;\"><b>"+ uniqueChars2[i] +"</b><input type=\"hidden\" class=\"trust_funds1\" value='"+uniqueChars2[i]+"'></td>";
        html += "<td style=\"padding-left:15px;padding-top:10px;\">";
        html += "<input type=\"number\" id='"+uniqueChars2[i]+"' class=\"amt\" name=\"amt\" placeholder=\"\" onfocus=\"GetTotal3()\" onkeydown=\"GetTotal3()\"  onkeyup=\"GetTotal3()\">";
        html += "</td></tr>";
    }

	html += "<tr><td style=\"font-size:12px;\"><b>Total</b></td>";
	html += "<td style=\"padding-left:15px;padding-top:10px;\"><input type=\"number\" class=\"login_input\" id=\"FTotal3\" name=\"churchCode\" placeholder=\"\" readonly></td></tr>";
	html += "<tr><td><button type=\"button\" class=\"btn\" onclick=\"getPaymentDiv3()\" id=\"payment_button3\">Confirm</button></td></tr>";
	html += "</form><tbody></table>";

	$("#trust_funds_div").html(html);

}


function saveTrustFundSummary(){
    var funds = document.getElementsByClassName("trust_funds");
    var amt = document.getElementsByClassName("amt");
    var phone_number = "254707981971";
    var contribute = "Self";
    var total = document.getElementById("FTotal").value;
    var church_code = "29001"

	var funds1 = [];
	var amt1 = [];


	for (var i = 0; i < amt.length; i++) {
	    console.log(amt.length);
		if(amt[i].value != ''){
			var amount_id = amt[i].id;
			amount_id = amount_id.replace(' ','');
			amount_id = amount_id.replace(' ','');
			console.log("The Desired Id: "+ amount_id);
			funds1.push(amount_id);
			console.log("The Desired Value: "+amt[i].value);
			amt1.push(amt[i].value);
		}
    }

    console.log(funds1.length);
    console.log(funds1);
    console.log(amt1.length);
    console.log(amt1);

	$.ajax({
        type: "POST",
        url: "/cfms-web/auth/member_receive_funds",
        datatype: "json",
        data: {
            contribute: contribute,
            phone_number: phone_number,
            amount: total,
            church_code:church_code,
            trust_funds:funds1,
            fund_amount:amt1,

        },
        success: function(response){
            console.log(response);

        },
        error: function(response){
            console.log(response);
        }

    });
}



function getFundAccount(){

	var home_church_self = document.getElementById("home_church_self");
	var trust_funds = document.getElementById("trust_funds");
	var non_trust_funds = document.getElementById("non_trust_funds");
	var both_funds = document.getElementById("both_funds");
	var special_trust_funds = document.getElementById("special_trust_funds");


	if(trust_funds.checked == true){
		getTrustFundAccounts();
		home_church_self.style.display = "none";
	}else if(non_trust_funds.checked == true){
		getNonTrustFundAccounts();
		home_church_self.style.display = "none";
	}else if(both_funds.checked == true){
		getBothFundAccounts();
		home_church_self.style.display = "none";
	}else if(special_trust_funds == true){
		getSpecialTrustFundAccounts();
		home_church_self.style.display = "none";
	}

}


function getPaymentDiv(){
    const onlyInputs = document.querySelectorAll('#trust_funds_div input');

    onlyInputs.forEach(input => {
      	input.readOnly = true;
    });
	var payment_div = document.getElementById("payment_div");
	var total = document.getElementById("FTotal").value;


	document.getElementById("payment_button").style.display = "none";

	payment_div.style.display = "block";
	var payment_info = "Ensure "+ total +"/= has been deposited on the mobile money account for 254707981971";
	document.getElementById("payment_line").innerHTML = payment_info;
}


function getPaymentDiv1(){
    const onlyInputs = document.querySelectorAll('#trust_funds_div input');

    onlyInputs.forEach(input => {
       	input.readOnly = true;
    });
	var payment_div = document.getElementById("payment_div");
	var total = document.getElementById("FTotal1").value;

	document.getElementById("payment_button1").style.display = "none";
	payment_div.style.display = "block";
	var payment_info = "Ensure "+ total +"/= has been deposited on the mobile money account for 254707981971";
	document.getElementById("payment_line").innerHTML = payment_info;
}

function getPaymentDiv2(){
    const onlyInputs = document.querySelectorAll('#trust_funds_div input');

    onlyInputs.forEach(input => {
       	input.readOnly = true;
    });
	var payment_div = document.getElementById("payment_div");
	var total = document.getElementById("FTotal2").value;
	document.getElementById("payment_button2").style.display = "none";
	payment_div.style.display = "block";
	var payment_info = "Ensure "+ total +"/= has been deposited on the mobile money account for 254707981971";
	document.getElementById("payment_line").innerHTML = payment_info;
}

function getPaymentDiv3(){
    const onlyInputs = document.querySelectorAll('#trust_funds_div input');

    onlyInputs.forEach(input => {
       	input.readOnly = true;
    });
	var payment_div = document.getElementById("payment_div");
	var total = document.getElementById("FTotal3").value;
	document.getElementById("payment_button3").style.display = "none";
	payment_div.style.display = "block";
	var payment_info = "Ensure "+ total +"/= has been deposited on the mobile money account for 254707981971";
	document.getElementById("payment_line").innerHTML = payment_info;
}