var hosted_url = location.origin;

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
	
  // All Receipting Options	
  var church_member = document.getElementById("church_member");
  var visitor = document.getElementById("visiting_member");
  var guest = document.getElementById("guest");
  var group = document.getElementById("group");
  var family = document.getElementById("family");
  var department = document.getElementById("department");
  var institution = document.getElementById("institution");
  
  
  // Respective Section  
  var cash_receipting = document.getElementById("cash_receipting");
  var visitor_div = document.getElementById("home_church_others");
  var guest_div = document.getElementById("home_church_others1");
  var group_div = document.getElementById("home_church_others2");
  var family_div = document.getElementById("home_church_others3");
  var department_div = document.getElementById("home_church_others4");
  var institution_div = document.getElementById("home_church_others5");
  
  // Logic to Enable Pages  
  if (visitor.checked == true){
		cash_receipting.style.display = "none";
		visitor_div.style.display = "block";
  }else if(guest.checked == true){
		cash_receipting.style.display = "none";
		guest_div.style.display = "block";     
  }else if(group.checked == true){
		cash_receipting.style.display = "none";
		group_div.style.display = "block";   
  }else if(family.checked == true){
		cash_receipting.style.display = "none";
		family_div.style.display = "block";   
  }else if(department.checked == true){
		cash_receipting.style.display = "none";
		department_div.style.display = "block";   
  }else if(institution.checked == true){
		cash_receipting.style.display = "none";
		institution_div.style.display = "block";   
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
		departments[i] = departments[i].split("::");
		accounts.push(departments[i][0]);
	}
	
	let uniqueChars = [...new Set(accounts)];
	console.log("Unique Characters: "+uniqueChars);
	
	for(x=0;x < uniqueChars.length;x++){
		console.log("Unique Characters "+ i +" : " +uniqueChars[x]);
		html += "<tr>";
		html += "<td style=\"font-size:12px;padding-top:10px;\"><b>"+ uniqueChars[x] +"</b><input type=\"hidden\" class=\"trust_funds\" value='"+uniqueChars[i]+"'></td>";
		html += "<td style=\"padding-left:15px;padding-top:10px;\">";
		html += "<input type=\"number\" id='"+uniqueChars[x]+"' class=\"amt\" name=\"amt\" placeholder=\"\" onfocus=\"GetTotal()\" onkeydown=\"GetTotal()\"  onkeyup=\"GetTotal()\">";
		html += "</td></tr>";
	}
	
	html += "<tr><td style=\"font-size:12px;\"><b>Total</b></td>";
	html += "<td style=\"padding-left:15px;padding-top:10px;\"><input type=\"number\" class=\"login_input\" id=\"FTotal\" name=\"churchCode\" placeholder=\"\" readonly></td></tr>";
	html += "<tr><td><button type=\"button\" class=\"btn\" onclick=\"getPaymentDiv()\" id=\"payment_button\">Confirm</button></td></tr>";
	html += "<tbody></form></table>";
	
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
		// departments[i] = departments[i].split("::")
		html += "<tr>";
		html += "<td style=\"font-size:12px;padding-top:10px;\"><b>"+ departments[i] +"</b><input type=\"hidden\" class=\"non_trust_funds\" ></td>";
		html += "<td style=\"padding-left:15px;padding-top:10px;\">";
		html += "<input type=\"number\" id='"+departments[i]+"' class=\"login_input\" name=\"amt\" placeholder=\"\" onfocus=\"GetTotal1()\" onkeydown=\"GetTotal1()\"  onkeyup=\"GetTotal1()\">";
		html += "</td></tr>";
	}
	html += "<tr><td style=\"font-size:12px;\"><b>Total</b></td>";
	html += "<td style=\"padding-left:15px;padding-top:10px;\"><input type=\"number\" class=\"login_input\" id=\"FTotal1\" name=\"churchCode\" placeholder=\"\" readonly></td></tr>";
	html += "<tr><td><button type=\"button\" class=\"btn\" onclick=\"getPaymentDiv1()\" id=\"payment_button1\">Confirm</button></td></tr>";
	html += "<tbody></form></table>";
	
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
		// departments[i] = departments[i].split("::")
		html += "<tr>";
		html += "<td style=\"font-size:12px;padding-top:10px;\"><b>"+ departments[i] +"</b><input type=\"hidden\" class=\"special_trust_funds\" value='"+departments[i]+"'></td>";
		html += "<td style=\"padding-left:15px;padding-top:10px;\">";
		html += "<input type=\"number\" id='"+departments[i]+"' class=\"amt\" name=\"amt\" placeholder=\"\" onfocus=\"GetTotal2()\" onkeydown=\"GetTotal2()\"  onkeyup=\"GetTotal2()\">";
		html += "</td></tr>";
	}
	html += "<tr><td style=\"font-size:12px;\"><b>Total</b></td>";
	html += "<td style=\"padding-left:15px;padding-top:10px;\"><input type=\"number\" class=\"login_input\" id=\"FTotal2\" name=\"churchCode\" placeholder=\"\" readonly></td></tr>";
	html += "<tr><td><button type=\"button\" class=\"btn\" onclick=\"getPaymentDiv2()\" id=\"payment_button2\">Confirm</button></td></tr>";
	html += "</form><tbody></table>";
	
	$("#trust_funds_div").html(html);
	
}


function getAllFundAccounts(){
    var phone = document.getElementById("phone_number").value;
    var profile_data = {
        phone_number: phone
    };

	$.post(hosted_url + "/cfms-web/auth/check-account",profile_data ,function(data, status){
        // Trust Fund Accounts
        var request = data.trustFundAccounts;
        var request1 = data.departmentalAccounts;
        var request2 = data.specialTrustFundAccounts;
        var request3 = request.slice(1,request.length-1);
        var request4 = request1.slice(1,request1.length-1);
        var request5 = request2.slice(1,request2.length-1);
        const departments = request3.split(',');
        const departments1 = request4.split(',');
        const departments2 = request5.split(',');
        var html = new String("");
        var accounts = [];
        var accounts1 = [];
        var accounts2 = [];

        html += "<div class=\"cardHeader\">";
        html += "<h2 style=\"text-align:center;margin-top:10px;margin-bottom:10px\">Funds Distribution</h2></div>";
        html += "<div class=\"responseDiv\" style=\"display:none;\"></div><br><hr>";
        html += "<table>";
        html += "<tr><td colspan=\"2\"><label class=\"label_input\"><b> For: </b></label></td>";
        html += "<td colspan=\"2\"><input type=\"text\" id=\"member_name\" name=\"member_name\" placeholder=\"\" readonly>";
        html += "<input type=\"hidden\" id=\"member_phone\" name=\"member_phone\" placeholder=\"\"><input type=\"hidden\" id=\"member_id\" name=\"member_id\" placeholder=\"\"></td></tr>";
        html += "<tr><td colspan=\"2\"><label class=\"label_input\"><b> Total: </b></label></td>";
        html += "<td colspan=\"2\"><input type=\"text\" id=\"receipting_total\" name=\"receipting_total\" placeholder=\"\">";
        html += "</td></tr></table>";
        html += "<table><tr><td colspan=\"2\"><h2 style=\"text-align: center;font-size:14px;padding-top:10px;padding-bottom:10px;\">Trust Funds</h2><span class=\"right-icon\"";
        html += "onclick=\"trustFundView()\" id=\"add_icon\" style=\"display:none;\"><ion-icon name=\"add-circle-outline\" class=\"add-circle\"></ion-icon></span><span class=\"right-icon\" onclick=\"trustFundView()\" id=\"remove_icon\"><ion-icon name=\"remove-circle-outline\" class=\"remove-circle\"></ion-icon></span</td></tr></table>";
        html += "<div id=\"trust_funds_view\" style=\"display:block;\"><table>";
        html += "<tr><td colspan=\"4\" style=\"text-align:center\"><hr></tr>";
        html += "<tr><td style=\"border-bottom:2px solid black;padding-top:7px;\" colspan=\"2\"><b>Contribution Type</b></td>";
        html += "<td style=\"border-bottom:2px solid black;text-align:right;padding-top:7px;\" colspan=\"2\"><b>Amount Offered</b></td></tr>";


        for (i=0;i < departments.length; i++){
            departments[i] = departments[i].replace('"','');
            departments[i] = departments[i].replace('"','');
            departments[i] = departments[i].split("::");
            accounts.push(departments[i][0]);
        }

        let uniqueChars = [...new Set(accounts)];
        console.log("Unique Characters: "+uniqueChars);

        for(i=0;i < uniqueChars.length;i++){
            html += "<tr>";
            html += "<td style=\"font-size:12px;padding-top:10px;\" colspan=\"2\"><b>"+ uniqueChars[i] +"</b><input type=\"hidden\" class=\"trust_funds1\" value='"+uniqueChars[i]+"'></td>";
            html += "<td style=\"padding-left:15px;padding-top:10px;\" colspan=\"2\">";
            html += "<input type=\"number\" id='"+uniqueChars[i]+"' class=\"amt\" name=\"amt\" placeholder=\"\" onfocus=\"GetTotal4()\" onkeydown=\"GetTotal4()\"  onkeyup=\"GetTotal4()\">";
            html += "</td></tr>";
        }
        html += "<tbody></table>";
        html += "</table>";
        html += "</div>";

        html += "<table><tr>";
        html += "<td colspan=\"2\"><h2 style=\"text-align: center;font-size:14px;padding-top:10px;padding-bottom:10px;\">Non Trust Funds</h2><span class=\"right-icon\"";
        html += "onclick=\"nonTrustFundView()\" id=\"add_icon1\" style=\"display:none;\"><ion-icon name=\"add-circle-outline\" class=\"add-circle\"></ion-icon></span><span class=\"right-icon\" onclick=\"nonTrustFundView()\" id=\"remove_icon1\"><ion-icon name=\"remove-circle-outline\" class=\"remove-circle\"></ion-icon></span</td></tr></table>";
        html += "<div id=\"non_trust_funds_view\"  style=\"display:block;\">";
        html += "<table><tr><td colspan=\"4\" style=\"text-align:center\"><hr></tr>";
        html += "<tr><td style=\"border-bottom:2px solid black;padding-top:7px;\" colspan=\"2\"><b>Contribution Type</b></td>";
        html += "<td style=\"border-bottom:2px solid black;text-align:right;padding-top:7px;\" colspan=\"2\"><b>Amount Offered</b></td></tr>";

        for (i=0;i < departments1.length; i++){
            departments1[i] = departments1[i].replace('"','');
            departments1[i] = departments1[i].replace('"','');
            departments1[i] = departments1[i].split("::");
            accounts1.push(departments1[i][0]);
        }

        let uniqueChars1 = [...new Set(accounts1)];

        for(i=0;i < uniqueChars1.length;i++){
            html += "<tr>";
            html += "<td style=\"font-size:12px;padding-top:10px;\" colspan=\"2\"><b>"+ uniqueChars1[i] +"</b><input type=\"hidden\" class=\"non_trust_funds1\" value='"+uniqueChars1[i]+"'></td>";
            html += "<td style=\"padding-left:15px;padding-top:10px;\" colspan=\"2\">";
            html += "<input type=\"number\" id='"+uniqueChars1[i]+"' class=\"amt1\" name=\"amt1\" placeholder=\"\" onfocus=\"GetTotal4()\" onkeydown=\"GetTotal4()\"  onkeyup=\"GetTotal4()\">";
            html += "</td></tr>";
        }

        html += "</form><tbody></table>";
        html += "</div>";

        html += "<table><tr>";
        html += "<td colspan=\"4\"><h2 style=\"text-align: center;font-size:14px;padding-top:10px;padding-bottom:10px;\">Special Trust Funds</h2></td>";
        html += "</tr><tr><td colspan=\"4\" style=\"text-align:center\"><hr></tr>";
        html += "<tr><td style=\"border-bottom:2px solid black;padding-top:7px;\" colspan=\"2\"><b>Contribution Type</b></td>";
        html += "<td style=\"border-bottom:2px solid black;text-align:right;padding-top:7px;\" colspan=\"2\"><b>Amount Offered</b></td></tr>";

        for (i=0;i < departments2.length; i++){
            departments2[i] = departments2[i].replace('"','');
            departments2[i] = departments2[i].replace('"','');
            departments2[i] = departments2[i].split("::");
            accounts2.push(departments2[i][0]);
        }

        let uniqueChars2 = [...new Set(accounts2)];

        for(i=0;i < uniqueChars2.length;i++){
            html += "<tr>";
            html += "<td style=\"font-size:12px;padding-top:10px;\" colspan=\"2\"><b>"+ uniqueChars2[i] +"</b><input type=\"hidden\" class=\"special_trust_funds1\" value='"+uniqueChars2[i]+"'></td>";
            html += "<td style=\"padding-left:15px;padding-top:10px;\" colspan=\"2\">";
            html += "<input type=\"number\" id='"+uniqueChars2[i]+"' class=\"amt2\" name=\"amt2\" placeholder=\"\" onfocus=\"GetTotal4()\" onkeydown=\"GetTotal4()\"  onkeyup=\"GetTotal4()\">";
            html += "</td></tr>";
        }

        html += "<tr><td style=\"font-size:12px;\" colspan=\"2\"><b>Distribution Total</b></td>";
        html += "<td style=\"padding-left:15px;padding-top:10px;\" colspan=\"2\"><input type=\"number\" class=\"login_input\" id=\"FTotal3\" name=\"churchCode\" placeholder=\"\" readonly></td></tr>";
        html += "<tr><td colspan=\"2\"><button type=\"button\" class=\"btn\" onclick=\"getMemberReceipting()\" id=\"payment_button3\">Confirm</button></td></tr>";
        html += "</form><tbody></table>";

        $("#trust_funds_div").html(html);
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



function getPaymentDiv3(){
	const onlyInputs = document.querySelectorAll('#trust_funds_div input');
   
    onlyInputs.forEach(input => {
      	input.readOnly = true;
    });
	
	var payment_div = document.getElementById("payment_div");
	var total = document.getElementById("FTotal3").value;
	$('#both_funds_form :input').attr('readonly','readonly');
	document.getElementById("payment_button3").style.display = "none";
	payment_div.style.display = "block";
	var payment_info = "Ensure "+ total +"/= has been deposited on the mobile money account for 254707981971";
	document.getElementById("payment_line").innerHTML = payment_info;
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


function getMemberProfile(){
	
	var html = new String("");
	html += "<div class=\"cardHeader\">";
	html += "<h2 style=\"text-align:center;\">Profile Information</h2>";
	html += "</div><div class=\"responseDiv\" style=\"display:none;\"></div>";
	html += "<br><hr>";
	html += "<table width=\"100%\">";
	html += "<tbody><tr>";	
	html += "<td><b>Full Name</b></td>";
	html += "<td><b>Email</b></td><td><b>Phone Number</b></td></tr><tr>";
	html += "<td width=\"33%\"><input type=\"text\" id=\"fullname\" placeholder=\"Full Name\" required></td>";
	html += "<td width=\"33%\"><input type=\"email\" id=\"email\" placeholder=\"Email\" required></td>";
	html += "<td width=\"33%\"><input type=\"text\" id=\"phone\" placeholder=\"Phone Number\" required></td>";
	html += "</tr><tr><td><b>Church Code</b></td><td><b>Mobile Service Provider</b></td>";			
	html += "<td><b>Church Group</b></td></tr><tr>";
	html += "<td width=\"33%\"><input type=\"text\" id=\"church_code\" placeholder=\"Church Code\" required></td>";
	html += "<td width=\"33%\"><select class=\"login_input\" aria-label=\"Default Select Example\" id=\"mobileprovider\">";
	html += "<option value=\"Safaricom\">Safaricom</option><option value=\"Airtel\">Airtel</option>";
	html += "<option value=\"Equitel\">Equitel</option><option value=\"TIGO\">TIGO</option>";
	html += "<option value=\"MTN\">MTN</option></select></td>";
	html += "<td width=\"33%\"><select class=\"login_input\" id=\"church_group\">";
	html += "<option value=\"Moi University\">Moi University</option>";
	html += "<option value=\"None\">None</option></select></td></tr>";
	html += "<tr><td><b>Mobile Service Provider</b></td><td><b>Preferred Language</b></td>";
	html += "<td><b>Phone Number Privacy</b></td></tr>";
	html += "<tr><td width=\"33%\"><select class=\"login_input\" aria-label=\"Default Select Example\" id=\"mobileprovider\">";
	html += "<option value=\"Safaricom\">Safaricom</option><option value=\"Airtel\">Airtel</option>";
	html += "<option value=\"Equitel\">Equitel</option><option value=\"TIGO\">TIGO</option>";
	html += "<option value=\"MTN\">MTN</option></select></td>";
	html += "<td width=\"33%\"><select class=\"login_input\"  aria-label=\"Default Select Example\" id=\"PreferredLanguage\" "; 
	html += "style=\"padding-left:5px;\"><option value=\"English\">English</option>";
	html += "<option value=\"Kiswahili\">Kiswahili</option><option value=\"French\">French</option>";						  
	html += "<option value=\"Kinyandarua\">Kinyandarua</option><option value=\"Buganda\">Buganda</option></select></td>";
	html += "<td class=\"now\"><input type=\"radio\" name=\"flexRadioDefault\" id=\"flexRadioDefault1\">";
	html += "<label class=\"form-check-label\" for=\"flexRadioDefault1\">Normal</label>";
	html += "<input type=\"radio\" name=\"flexRadioDefault\" id=\"flexRadioDefault1\">";
	html += "<label class=\"form-check-label\" for=\"flexRadioDefault1\">Secret</label></td></tr>";
	html += "<tr><td><b>Phone Owner</b></td><td><b>Church Member</b></td><td><b>Other Phone Number</b></td></tr>";
	html += "<tr><td><input class=\"form-check-input\" type=\"checkbox\" value=\"\" id=\"phoneOwner\">";
	html += "<label for=\"phoneOwner\" class=\"label_input\">Phone Owner</label></td>";
	html += "<td><input class=\"form-check-input\" type=\"checkbox\" value=\"\" id=\"churchMember\">";
	html += "<label for=\"church_member\" class=\"label_input\">Church Member</label></td>";
	html += "<td><input type=\"text\" id=\"otherPhoneNumber\" name=\"otherPhoneNumber\" placeholder=\"Other Phone Number\"></td></tr>";
	html += "<tr><td><b>Giving Receipt To</b></td><td><b>Residence</b></td></tr>";
	html += "<tr><td><input class=\"form-check-input\" type=\"radio\" name=\"flexRadioDefault1\" id=\"self\">";
	html += "<label class=\"label_input\" for=\"flexRadioDefault2\">Self</label>";
	html += "<input class=\"form-check-input\" type=\"radio\" name=\"flexRadioDefault1\" id=\"family\">";
	html += "<label class=\"label_input\" for=\"flexRadioDefault2\">Family</label><br>";
	html += "<input class=\"form-check-input\" type=\"radio\" name=\"flexRadioDefault1\" id=\"anonymous\">";
	html += "<label class=\"label_input\" for=\"flexRadioDefault2\">Anonymous</label></td>";
	html += "<td><input type=\"text\" id=\"residence\" name=\"residence\" placeholder=\"Residence\"></td></tr><tr>";
	html += "<td><button type=\"button\" class=\"btn\" onclick=\"displayDetails()\" style=\"width:50%;\">Submit</button></td>";
	html += "</tr></tbody></table>";				
	$("#member_profile").html(html);					
}


function checkGuestName(){
	var guest_div = document.getElementById("home_church_others1");
    var funds_div = document.getElementById("trust_funds_div");
	var name = document.getElementById("guest_name").value;
    var phone = document.getElementById("guest_phone").value;

   var profile_data1 = {
   	    phone_number: phone
   	};

   $.post(hosted_url + "/cfms-web/auth/check-member",profile_data1   ,function(data, status){
        if(data.error == null){
            var statement = "The member "+ data.memberName +" is already registered to "+ data.churchNumber;
            statement += " and can be receipted for as a Visiting Member. Would you Still like to ";
            statement += "contribute for them as a guest";
            if(confirm(statement) == true){
                  getGuestName(data.memberName,data.phoneNumber,data.membershipNumber);
                  guest_div.style.display = "none";
                  funds_div.style.display = "block";
            }
        }else if(data.error != null){
               getGuestName(name,phone,phone);
               guest_div.style.display = "none";
               funds_div.style.display = "block";
        }
   });
}


function checkVisitorName(){
	var visitor_div = document.getElementById("home_church_others");
    var funds_div = document.getElementById("trust_funds_div");
    var phone = document.getElementById("visitor_phone").value;
	var member_name = document.getElementById("member_name").value;
	var member_phone = document.getElementById("member_phone").value;

	var profile_data1 = {
	    phone_number: phone
	};

    $.post(hosted_url + "/cfms-web/auth/check-member",profile_data1   ,function(data, status){
          var statement = "Is "+ data.memberName +" the Visitor you wish to contribute for?";
          if(confirm(statement) == true){
                getVisitorName(data.memberName,data.phoneNumber,data.membershipNumber);
                visitor_div.style.display = "none";
                funds_div.style.display = "block";
          }else{
                $(".responseDiv").show();
                $(".responseDiv").html("Member Details were not found");
                $(".responseDiv").fadeOut(3000);
          }
    });
}


function GetTotal4()
{
    /*Footer Calculation*/
    var sum= 0;
    var sum1 = 0;
	var sum2 = 0;
    var amts =  document.getElementsByName("amt");
    var amts1 =  document.getElementsByName("amt1");
	var amts2 =  document.getElementsByName("amt2");


    for (let index = 0; index < amts.length; index++){
        var amt = amts[index].value;
        sum = +(sum) +  +(amt) ;
    }

    for (let index = 0; index < amts1.length; index++){
        var amt1 = amts1[index].value;
        sum1 = +(sum1) +  +(amt1) ;
    }
	
	for (let index = 0; index < amts2.length; index++){
        var amt2 = amts2[index].value;
        sum2 = +(sum2) +  +(amt2) ;
    }
	
	var receipting_total = document.getElementById("receipting_total").value;
    var sum3 = sum + sum1 + sum2;
    document.getElementById("FTotal3").value = sum3;
	 
}


function checkTotals(){
	var receipting_total = document.getElementById("receipting_total").value;
	var sum3 = document.getElementById("FTotal3").value;
	if(receipting_total != sum3){
		alert('The total does not match the Funds distribution total');
		return false;
	}else{
		return true
	}	
}


function getChurchMembers(){
    cash_receipting.style.display = "none";
    all_members.style.display = "block";
}

function getMemberName(name,id,phone){
    document.getElementById("all_members").style.display = "none";
    document.getElementById("cash_receipting").style.display = "none";
    document.getElementById("member_name").value = name;
    document.getElementById("member_id").value = id;
    document.getElementById("member_phone").value = phone;
    document.getElementById("trust_funds_div").style.display = "block";
}


function getVisitorName(name,id,phone){
    document.getElementById("home_church_others").style.display = "none";
    document.getElementById("cash_receipting").style.display = "none";
    document.getElementById("member_name").value = name;
    document.getElementById("member_id").value = id;
    document.getElementById("member_phone").value = phone;
    document.getElementById("trust_funds_div").style.display = "block";
}

function getGuestName(name,id,phone){
    document.getElementById("home_church_others1").style.display = "none";
    document.getElementById("cash_receipting").style.display = "none";
    document.getElementById("member_name").value = name;
    document.getElementById("member_id").value = id;
    document.getElementById("member_phone").value = phone;
    document.getElementById("trust_funds_div").style.display = "block";
}


function receiptingFunction(){
    var church_member = document.getElementById("church_member");
    var visiting_member = document.getElementById("visiting_member");
    var guest = document.getElementById("guest");
    var home_church_others = document.getElementById("home_church_others");
    var cash_receipting = document.getElementById("cash_receipting");
    var home_church_others1 = document.getElementById("home_church_others1");

    if (church_member.checked == true){
        getChurchMembers();
    }else if(visiting_member.checked == true){
        cash_receipting.style.display = "none";
        home_church_others.style.display = "block";
    }else if(guest.checked == true){
        cash_receipting.style.display = "none";
        home_church_others1.style.display = "block";
    }
}


function goBackToReceipting(){
    var home_church_others = document.getElementById("home_church_others");
    var cash_receipting = document.getElementById("cash_receipting");
    var home_church_others1 = document.getElementById("home_church_others1");
    var all_members = document.getElementById("all_members");

    cash_receipting.style.display = "block";
    home_church_others.style.display = "none";
    all_members.style.display = "none";
    home_church_others1.style.display = "none";
}


function getMemberReceipting(){
    checkTotals();

    // Essential Information for Receipting
    var phone_number = document.getElementById("phone_number").value;
    var username = document.getElementById("username").value;
    var password = document.getElementById("username").value;
    var total_amount = document.getElementById("FTotal3").value;
    var contribute = new String("");
    var contribute_type = new String("");
    var receiver_id = new String("");
    var receiver_name = new String("");
    var receiver_number = new String("");
    var amt = document.getElementsByClassName("amt");
    var amt1 = document.getElementsByClassName("amt1");
    var amt2 = document.getElementsByClassName("amt2");

    // Check for Member
    var church_member = document.getElementById("church_member");
    var visiting_member = document.getElementById("visiting_member");
    var guest = document.getElementById("guest");

    // Contributor Type
    if(church_member.checked == true){
        contribute = "Self";
        contribute_type = "Member";
        receiver_id = document.getElementById("member_id").value;
        receiver_name = document.getElementById("member_name").value;
        receiver_number = document.getElementById("member_phone").value;
    }else if(visiting_member.checked == true){
        contribute = "Self";
        contribute_type = "Member";
        receiver_id = document.getElementById("member_id").value;
        receiver_name = document.getElementById("member_name").value;
        receiver_number = document.getElementById("member_phone").value;
    }else if(guest.checked == true){
        contribute = "Self";
        contribute_type = "Guest"
        receiver_name = document.getElementById("member_name").value;
        receiver_number = document.getElementById("member_phone").value;
        receiver_id = document.getElementById("member_id").value;
    }


    // The Funds in Question
    var funds1 = [];
    var funds2 = [];
    var funds3 = [];
    var amt3 = [];
   	var amt4 = [];
   	var amt5 = [];


    for (var i = 0; i < amt.length; i++) {
    	if(amt[i].value != ''){
    		var amount_id = amt[i].id;
    		amount_id = amount_id.replace(' ','');
    	    amount_id = amount_id.replace(' ','');
    		funds1.push(amount_id);
    		amt3.push(amt[i].value);
        }
    }


    for (var i = 0; i < amt1.length; i++) {
       	if(amt1[i].value != ''){
       		var amount_id = amt1[i].id;
       		amount_id = amount_id.replace(' ','');
       		amount_id = amount_id.replace(' ','');
       		funds2.push(amount_id);
       		amt4.push(amt1[i].value);
       	}
    }

    for (var i = 0; i < amt2.length; i++) {
       	if(amt2[i].value != ''){
       		var amount_id = amt2[i].id;
       		amount_id = amount_id.replace(' ','');
       		amount_id = amount_id.replace(' ','');
       		funds3.push(amount_id);
       		amt5.push(amt2[i].value);
       	}
    }

    var funds = {
         phone_number: phone_number,
         username: username,
         password: password,
         amount : total_amount,
         contribute: contribute,
         contribute_type: contribute_type,
         receiver_id: receiver_id,
         receiver_name: receiver_name,
         receiver_number: receiver_number,
         trust_funds: funds1,
         non_trust_funds: funds2,
         special_trust_funds: funds3,
         fund_amount: amt3,
         fund_amount1: amt4,
         fund_amount2: amt5
    }

    // console.log("Funds Object: "+ JSON.stringify(funds));
    if(church_member.checked == true || visiting_member.checked == true){
        $.post(hosted_url + "/cfms-web/auth/member_receipt_funds",funds ,function(data, status){
             $(".responseDiv").show();
             $(".responseDiv").html("Member Successfully Receipted");
             $(".responseDiv").fadeOut(3000);
        });
    }else if(guest.checked == true){
        $.post(hosted_url + "/cfms-web/auth/member_receipt_funds_guest",funds ,function(data, status){
             $(".responseDiv").show();
             $(".responseDiv").html("Guest Successfully Receipted");
             $(".responseDiv").fadeOut(3000);
        });
    }

}


function trustFundView(){
	var trust_fund_view = document.getElementById("trust_funds_view");
	var add_icon = document.getElementById("add_icon");
	var remove_icon = document.getElementById("remove_icon");

	if(trust_fund_view.style.display === "block"){
		trust_fund_view.style.display = "none";
		remove_icon.style.display = "none"
		add_icon.style.display = "block";
	}else{
		trust_fund_view.style.display = "block"
		add_icon.style.display = "none";
		remove_icon.style.display = "block";
	}
}


function nonTrustFundView(){
	var non_trust_fund_view = document.getElementById("non_trust_funds_view");
	var add_icon = document.getElementById("add_icon1");
	var remove_icon = document.getElementById("remove_icon1");

	if(non_trust_fund_view.style.display === "block"){
		non_trust_fund_view.style.display = "none";
		remove_icon.style.display = "none"
		add_icon.style.display = "block";
	}else{
		non_trust_fund_view.style.display = "block"
		add_icon.style.display = "none";
		remove_icon.style.display = "block";
	}
}