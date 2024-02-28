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

function myFunction() {
  var home_church = document.getElementById("home_church");
  var host_church = document.getElementById("host_church");
  var member_giving = document.getElementById("member_giving");
  var home_church_view = document.getElementById("home_church_self");
  var home_church_others = document.getElementById("home_church_others");
  var self = document.getElementById("self");
  var others = document.getElementById("others");


  var name = "Cfms";
  var statement = "Is "+ name +" the church you wish to transfer your details to?";

  var text = document.getElementById("church_code_section");
  var member_giving = document.getElementById("member_giving");

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


function getCheckedAccounts(){

}

function getNonTrustFundAccounts(){
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
	request = request.replace('[', '');
	request = request.replace(']', '');
	const departments = request.split(',');
	var html = new String("");
	html += "<h1 style=\"text-align: center;font-size:12px;\">Trust Funds</h1>";
	html += "<hr><table><tbody><tr>";
	html += "<th style=\"padding-right:15px;font-size:12px;border-bottom:2px solid black;\">Contribution Type</th>";
	html += "<th style=\"padding-left:15px;font-size:12px;border-bottom:2px solid black;\">Amount Offered</th>"
	html += "</tr>"

	for (i=0;i < departments.length; i++){
		departments[i] = departments[i].replace('"','');
		departments[i] = departments[i].replace('"','');
		departments[i] = departments[i].split("::")
		html += "<tr>";
		html += "<td style=\"font-size:12px;padding-top:10px;\">"+ departments[i][0] +"</td>";
		html += "<td style=\"padding-left:15px;padding-top:10px;\">";
		html += "<input type=\"number\" class=\"login_input\" name=\"amt\" placeholder=\"\" onfocus=\"GetTotal()\" onkeydown=\"GetTotal()\"  onkeyup=\"GetTotal()\">";
		html += "</td></tr>";
	}
	html += "<tr><td style=\"font-size:12px;\">Total</td>";
	html += "<td style=\"padding-left:15px;padding-top:10px;\"><input type=\"number\" class=\"login_input\" id=\"FTotal\" name=\"churchCode\" placeholder=\"\" readonly></td></tr>";
	html += "<tbody></table>";

	$("#test").html(html);

}

function getTrustFundAccounts(){
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

	// Non Trust Fund Accounts
	var request = data.departmentalAccounts;
	request = request.replace('[', '');
	request = request.replace(']', '');
	const departments = request.split(',');
	var html = new String("");
	html += "<h1 style=\"text-align: center;font-size:12px;\">Non Trust Funds</h1>";
	html += "<hr><table><tbody><tr>";
	html += "<th style=\"padding-right:15px;font-size:12px;border-bottom:2px solid black;\">Contribution Type</th>";
	html += "<th style=\"padding-left:15px;font-size:12px;border-bottom:2px solid black;\">Amount Offered</th>"
	html += "</tr>"

	for (i=0;i < departments.length; i++){
		departments[i] = departments[i].replace('"','');
		departments[i] = departments[i].replace('"','');
		departments[i] = departments[i].split("::")
		html += "<tr>";
		html += "<td style=\"font-size:12px;padding-top:10px;\">"+ departments[i][0] +"</td>";
		html += "<td style=\"padding-left:15px;padding-top:10px;\">";
		html += "<input type=\"number\" class=\"login_input\" name=\"amt\" placeholder=\"\" onfocus=\"GetTotal1()\" onkeydown=\"GetTotal1()\"  onkeyup=\"GetTotal1()\">";
		html += "</td></tr>";
	}
	html += "<tr><td style=\"font-size:12px;\">Total</td>";
	html += "<td style=\"padding-left:15px;padding-top:10px;\"><input type=\"number\" class=\"login_input\" id=\"FTotal1\" name=\"churchCode\" placeholder=\"\" readonly></td></tr>";
	html += "<tbody></table>";

	$("#test").html(html);

}