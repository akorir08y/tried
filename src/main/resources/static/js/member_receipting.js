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


function getAllFundAccounts(){
    var phone = document.getElementById("phone_number").value;
    var profile_data = {
        phone_number: phone
    };

	$.post(hosted_url + "/cfms-web/auth/check-account",profile_data ,function(data, status){

	    var trust_funds = "[\"Tithe Account::127989\",\"Combined Offerings Account::127990\",\"Camp Meeting Account::13824\",";
        trust_funds += "\"Conference Development Account::426234\",\"Thirteenth Sabbath Account::191295\",";
        trust_funds += "\"Combined Offerings Account::13823\"]";

        // Trust Fund Accounts
        var request = trust_funds;
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
            departments[i][0] = departments[i][0].replace(' Account', '');
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
            departments1[i][0] = departments1[i][0].replace(' Account', '');
            accounts1.push(departments1[i][0]);
        }

        let uniqueChars1 = [...new Set(accounts1)];

        for(i=0;i < uniqueChars1.length;i++){
            html += "<tr>";
            html += "<td style=\"font-size:12px;padding-top:10px;\" colspan=\"2\"><b>"+ uniqueChars1[i] +" Account</b><input type=\"hidden\" class=\"non_trust_funds1\" value='"+uniqueChars1[i]+"'></td>";
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
            departments2[i][0] = departments2[i][0].replace(' Account', '');
            accounts2.push(departments2[i][0]);
        }

        let uniqueChars2 = [...new Set(accounts2)];

        for(i=0;i < uniqueChars2.length;i++){
            html += "<tr>";
            html += "<td style=\"font-size:12px;padding-top:10px;\" colspan=\"2\"><b>"+ uniqueChars2[i] +" Account</b><input type=\"hidden\" class=\"special_trust_funds1\" value='"+uniqueChars2[i]+"'></td>";
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


function getAllFundAccountsReceipted(){
    var phone = document.getElementById("phone_number").value;
    var profile_data = {
        phone_number: phone
    };

	$.post(hosted_url + "/cfms-web/auth/check-account",profile_data ,function(data, status){
	    var trust_funds = "[\"Tithe Account::127989\",\"Combined Offerings Account::127990\",\"Camp Meeting Account::13824\",";
        trust_funds += "\"Conference Development Account::426234\",\"Thirteenth Sabbath Account::191295\",";
        trust_funds += "\"Combined Offerings Account::13823\"]";

        // Trust Fund Accounts
        var request =  trust_funds;
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

        html += "<div id=\"receipted_funds\">";
        html += "<div class=\"cardHeader\">";
        html += "<h2 style=\"text-align:center;margin-top:10px;margin-bottom:10px\">Funds Distribution</h2></div>";
        html += "<div class=\"responseDiv\" style=\"display:none;\"></div><br><hr>";
        html += "<table>";
        html += "<tr><td colspan=\"2\"><label class=\"label_input\"><b> For: </b></label></td>";
        html += "<td colspan=\"2\"><input type=\"text\" id=\"member_name\" name=\"member_name\" placeholder=\"\" readonly>";
        html += "<input type=\"hidden\" id=\"member_phone\" name=\"member_phone\" placeholder=\"\"><input type=\"hidden\" id=\"member_id\" name=\"member_id\" placeholder=\"\"></td></tr>";
        html += "<tr><td colspan=\"2\"><label class=\"label_input\"><b> Total: </b></label></td>";
        html += "<td colspan=\"2\"><input type=\"text\" id=\"receipting_total\" name=\"receipting_total\" placeholder=\"\" readonly>";
        html += "</td></tr></table>";
        html += "<table><tr><td colspan=\"2\"><h2 style=\"text-align: center;font-size:14px;padding-top:10px;padding-bottom:10px;\">Trust Funds</h2><span class=\"right-icon\"";
        html += "onclick=\"trustFundViewNone()\" id=\"add_icon_none\" style=\"display:block;\"><ion-icon name=\"add-circle-outline\" class=\"add-circle\"></ion-icon></span><span class=\"right-icon\" onclick=\"trustFundViewNone()\" id=\"remove_icon_none\" style=\"display:none;\"><ion-icon name=\"remove-circle-outline\" class=\"remove-circle\"></ion-icon></span</td></tr></table>";
        html += "<div id=\"trust_funds_view_none\" style=\"display:none;\"><table>";
        html += "<tr><td colspan=\"4\" style=\"text-align:center\"><hr></tr>";
        html += "<tr><td style=\"border-bottom:2px solid black;padding-top:7px;\" colspan=\"2\"><b>Contribution Type</b></td>";
        html += "<td style=\"border-bottom:2px solid black;text-align:right;padding-top:7px;\" colspan=\"2\"><b>Amount Offered</b></td></tr>";

        for (i=0;i < departments.length; i++){
            departments[i] = departments[i].replace('"','');
            departments[i] = departments[i].replace('"','');
            departments[i] = departments[i].split("::");
            departments[i][0] = departments[i][0].replace(' Account', '');
            accounts.push(departments[i][0]);
        }

        var uniqueChars = [...new Set(accounts)];
        console.log("Unique Characters: "+uniqueChars);

        for(i=0;i < uniqueChars.length;i++){
            html += "<tr>";
            html += "<td style=\"font-size:12px;padding-top:10px;\" colspan=\"2\"><b>"+ uniqueChars[i] +" Account</b><input type=\"hidden\" class=\"trust_funds1\" value='"+uniqueChars[i]+"'></td>";
            html += "<td style=\"padding-left:15px;padding-top:10px;\" colspan=\"2\">";
            html += "<input type=\"number\" id='"+uniqueChars[i]+"' class=\"amt\" name=\"amt\" placeholder=\"\" onfocus=\"GetTotal4()\" onkeydown=\"GetTotal4()\"  onkeyup=\"GetTotal4()\"  readonly>";
            html += "</td></tr>";
        }
        html += "<tbody></table>";
        html += "</table>";
        html += "</div>";

        html += "<table><tr>";
        html += "<td colspan=\"2\"><h2 style=\"text-align: center;font-size:14px;padding-top:10px;padding-bottom:10px;\">Non Trust Funds</h2><span class=\"right-icon\"";
        html += "onclick=\"nonTrustFundViewNone()\" id=\"add_icon_none1\" style=\"display:block;\"><ion-icon name=\"add-circle-outline\" class=\"add-circle\"></ion-icon></span><span class=\"right-icon\" onclick=\"nonTrustFundViewNone()\" id=\"remove_icon_none1\" style=\"display:none;\"><ion-icon name=\"remove-circle-outline\" class=\"remove-circle\"></ion-icon></span</td></tr></table>";
        html += "<div id=\"non_trust_funds_view_none\"  style=\"display:none;\">";
        html += "<table><tr><td colspan=\"4\" style=\"text-align:center\"><hr></tr>";
        html += "<tr><td style=\"border-bottom:2px solid black;padding-top:7px;\" colspan=\"2\"><b>Contribution Type</b></td>";
        html += "<td style=\"border-bottom:2px solid black;text-align:right;padding-top:7px;\" colspan=\"2\"><b>Amount Offered</b></td></tr>";

        for (i=0;i < departments1.length; i++){
            departments1[i] = departments1[i].replace('"','');
            departments1[i] = departments1[i].replace('"','');
            departments1[i] = departments1[i].split("::");
            accounts1.push(departments1[i][0]);
        }

        var uniqueChars1 = [...new Set(accounts1)];
        for(i=0;i < uniqueChars1.length;i++){
            html += "<tr>";
            html += "<td style=\"font-size:12px;padding-top:10px;\" colspan=\"2\"><b>"+ uniqueChars1[i] +" Account</b><input type=\"hidden\" class=\"non_trust_funds1\" value='"+uniqueChars1[i]+"'></td>";
            html += "<td style=\"padding-left:15px;padding-top:10px;\" colspan=\"2\">";
            html += "<input type=\"number\" id='"+uniqueChars1[i]+"' class=\"amt1\" name=\"amt1\" placeholder=\"\" onfocus=\"GetTotal4()\" onkeydown=\"GetTotal4()\"  onkeyup=\"GetTotal4()\"  readonly>";
            html += "</td></tr>";
        }

        html += "</form><tbody></table>";
        html += "</div>";

        html += "<table><tr>";
        html += "<td colspan=\"4\"><h2 style=\"text-align: center;font-size:14px;padding-top:10px;padding-bottom:10px;\">Special Trust Funds</h2><span class=\"right-icon\"";
        html += "onclick=\"specialTrustFundViewNone()\" id=\"add_icon_none2\" style=\"display:none;\"><ion-icon name=\"add-circle-outline\" class=\"add-circle\"></ion-icon></span><span class=\"right-icon\" onclick=\"specialTrustFundViewNone()\" id=\"remove_icon_none2\"><ion-icon name=\"remove-circle-outline\" class=\"remove-circle\"></ion-icon></span</td></tr></table>";
        html += "<div id=\"special_trust_funds_view\"  style=\"display:none;\">";
        html += "</tr><tr><td colspan=\"4\" style=\"text-align:center\"><hr></tr>";
        html += "<tr><td style=\"border-bottom:2px solid black;padding-top:7px;\" colspan=\"2\"><b>Contribution Type</b></td>";
        html += "<td style=\"border-bottom:2px solid black;text-align:right;padding-top:7px;\" colspan=\"2\"><b>Amount Offered</b></td></tr>";

        for (i=0;i < departments2.length; i++){
            departments2[i] = departments2[i].replace('"','');
            departments2[i] = departments2[i].replace('"','');
            departments2[i] = departments2[i].split("::");
            accounts2.push(departments2[i][0]);
        }

        var uniqueChars2 = [...new Set(accounts2)];
        for(i=0;i < uniqueChars2.length;i++){
            html += "<tr>";
            html += "<td style=\"font-size:12px;padding-top:10px;\" colspan=\"2\"><b>"+ uniqueChars2[i] +" Account</b><input type=\"hidden\" class=\"special_trust_funds1\" value='"+uniqueChars2[i]+"'></td>";
            html += "<td style=\"padding-left:15px;padding-top:10px;\" colspan=\"2\">";
            html += "<input type=\"number\" id='"+uniqueChars2[i]+"' class=\"amt2\" name=\"amt2\" placeholder=\"\" onfocus=\"GetTotal4()\" onkeydown=\"GetTotal4()\"  onkeyup=\"GetTotal4()\"  readonly>";
            html += "</td></tr>";
        }

        html += "</div>"
        html += "<tr><td style=\"font-size:12px;\" colspan=\"2\"><b>Distribution Total</b></td>";
        html += "<td style=\"padding-left:15px;padding-top:10px;\" colspan=\"2\"><input type=\"number\" class=\"login_input\" id=\"FTotal3\" name=\"churchCode\" placeholder=\"\" readonly></td></tr>";
        html += "<tr><td colspan=\"2\"><button type=\"button\" class=\"btn\" onclick=\"getMemberReceipting()\" id=\"payment_button3\">Confirm</button></td></tr>";
        html += "</form><tbody></table></div>";

        $("#trust_funds_div_receipted").html(html);
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
            statement += ". Receipt a Guest who is not an Active Member";
            alert(statement);
            guest_div.style.display = "block";
        }else if(data.error != null){
            getGuestName(name,phone,phone);
            guest_div.style.display = "none";
            funds_div.style.display = "block";
            add_button.style.display = "block";
        }
   });
}


function checkVisitorName(){
	var visitor_div = document.getElementById("home_church_others");
    var funds_div = document.getElementById("trust_funds_div");
    var phone = document.getElementById("visitor_phone").value;
	var member_name = document.getElementById("member_name").value;
	var member_phone = document.getElementById("member_phone").value;
	var church_code = document.getElementById("church_code").value;

	var profile_data1 = {
	    phone_number: phone
	};

    $.post(hosted_url + "/cfms-web/auth/check-member",profile_data1   ,function(data, status){
        if(data.error == null){
              if(church_code !== data.churchNumber){
                  var statement = "Is "+ data.memberName +" the Visitor you wish to contribute for?";
                  if(confirm(statement) == true){
                        getVisitorName(data.memberName,data.phoneNumber,data.membershipNumber);
                        visitor_div.style.display = "none";
                        funds_div.style.display = "block";
                  }
              }else{
                   var stated = "Cannot Receipt the Member of the Same Church as a Visiting Member";
                   alert(stated);
              }
        }else if(data.error != null){
            alert(data.error);
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


function GetTotal5()
{
    /*Footer Calculation*/
    var sum= 0;
    var sum1 = 0;
	var sum2 = 0;
	var sum3 = 0;
    var amts =  document.getElementsByName("amt_edit");
    var amts1 =  document.getElementsByName("amt_edit1");
	var amts2 =  document.getElementsByName("amt_edit2");


    for (let index = 0; index < amts.length; index++){
        var amt = amts[index].value;
        sum = +(sum) +  +(amt);
    }

    for (let index = 0; index < amts1.length; index++){
        var amt1 = amts1[index].value;
        sum1 = +(sum1) +  +(amt1);
    }

	for (let index = 0; index < amts2.length; index++){
        var amt2 = amts2[index].value;
        sum2 = +(sum2) +  +(amt2);
    }

    sum3 = sum + sum1 + sum2;
    console.log(sum3)
    document.getElementById("FTotal_edit3").value = sum3;
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


function checkTotals2(){
	var receipting_total = document.getElementById("receipting_total_edit").value;
	var sum3 = document.getElementById("FTotal_edit3").value;
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
    document.getElementById("loader_spin_receipting").style.display = "block";
    if(name == null){
        alert("The Member Name is Not Available")
        return false;
    }

    if(id == null){
        alert("The Membership Number is Not Available")
        return false;
    }

    if(phone == null){
        alert("The Member Phone Number is Not Available")
        return false;
    }

    document.getElementById("all_members").style.display = "none";
    document.getElementById("cash_receipting").style.display = "none";
    checkCashReceipted(id, name, phone);
}


function getVisitorName(name,phone,id){
    if(name == null){
        alert("The Member Name is Not Available")
        return false;
    }

    if(id == null){
        alert("The Membership Number is Not Available")
        return false;
    }

    if(phone == null){
        alert("The Member Phone Number is Not Available")
        return false;
    }


    document.getElementById("home_church_others").style.display = "none";
    document.getElementById("cash_receipting").style.display = "none";
    checkCashReceipted(id, name, phone);
}

function getGuestName(name,id,phone){
    if(name == null){
        alert("The Member Name is Not Available")
        return false;
    }

    if(id == null){
        alert("The Membership Number is Not Available")
        return false;
    }

    if(phone == null){
        alert("The Member Phone Number is Not Available")
        return false;
    }

    document.getElementById("home_church_others1").style.display = "none";
    document.getElementById("cash_receipting").style.display = "none";
    checkCashReceipted(id, name, phone);
}

function checkCashReceipted(member_id,member_name,phone_number){
    var today = new Date().toISOString().slice(0, 10);
    var add_button = document.getElementById("add_button");

    var dates = {
        phone_number: phone_number,
        cfms_member_number: member_id,
        start_date: today,
        end_date: today
    };

    $.post(hosted_url + "/cfms-web/personnel/cash-receipted",dates   ,function(data, status){
        if(isEmpty(data) === true){
            document.getElementById("member_name").value = member_name;
            document.getElementById("member_id").value = member_id;
            document.getElementById("member_phone").value = phone_number;
            document.getElementById("trust_funds_div").style.display = "block";
            document.getElementById("loader_spin_receipting").style.display = "none";
            add_button.style.display = "block";
        }else if(isEmpty(data) === false){
            getSubmittedReceipts(data, member_id, phone_number);
            document.getElementById("loader_spin_receipting").style.display = "none";
            document.getElementById("member_name_edited").innerHTML = member_name;
            document.getElementById("member_id_edited").innerHTML = member_id;
            document.getElementById("member_phone_edited").innerHTML = phone_number;
        }
    });


}


function isEmpty(obj) {
  for (const prop in obj) {
    if (Object.hasOwn(obj, prop)) {
      return false;
    }
  }
  return true;
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
    var password = document.getElementById("password").value;
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
    		funds1.push(amount_id);
    		amount_id = amount_id.replace(' Account','');
    		amt3.push(amt[i].value);
        }
    }

    for (var i = 0; i < amt1.length; i++) {
       	if(amt1[i].value != ''){
       		var amount_id = amt1[i].id;
       		amount_id = amount_id.replace(' Account','');
       		funds2.push(amount_id);
       		amt4.push(amt1[i].value);
       	}
    }

    for (var i = 0; i < amt2.length; i++) {
       	if(amt2[i].value != ''){
       		var amount_id = amt2[i].id;
       		amount_id = amount_id.replace(' Account','');
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

    alert("Receipting Process Initiated");

    if(church_member.checked == true || visiting_member.checked == true){
        $.post(hosted_url + "/cfms-web/personnel/member_receipt_funds",funds ,function(data, status){
            if(data.status == 0){
                 alert("Member Successfully Receipted");
                 window.location.reload();
            }else{
                 alert("Member Receipting UnSuccessful");
            }
        });
    }else if(guest.checked == true){
        $.post(hosted_url + "/cfms-web/personnel/member_receipt_funds_guest",funds ,function(data, status){
             if(data.status == 0){
                 alert("Guest Successfully Receipted");
                 window.location.reload();
             }else{
                 alert("Member Receipting UnSuccessful");
             }
        });
    }

}


function getMemberEditReceipting(){
    checkTotals2();

    // Essential Information for Receipting
    var receipt_number = document.getElementById("receipt_numbers").value;
    var phone_number = document.getElementById("phone_number").value;
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;
    var total_amount = document.getElementById("FTotal_edit3").value;
    var contribute = new String("");
    var contribute_type = new String("");
    var receiver_id = new String("");
    var receiver_name = new String("");
    var receiver_number = new String("");
    var amt = document.getElementsByClassName("amt_edit");
    var amt1 = document.getElementsByClassName("amt1_edit");
    var amt2 = document.getElementsByClassName("amt2_edit");

    // Check for Member
    var church_member = document.getElementById("church_member");
    var visiting_member = document.getElementById("visiting_member");
    var guest = document.getElementById("guest");

    // Contributor Type
    if(church_member.checked == true){
        contribute = "Self";
        contribute_type = "Member";
        receiver_id = document.getElementById("member_id_edit").value;
        receiver_name = document.getElementById("member_name_edit").value;
        receiver_number = document.getElementById("member_phone_edit").value;
    }else if(visiting_member.checked == true){
        contribute = "Self";
        contribute_type = "Member";
        receiver_id = document.getElementById("member_id_edit").value;
        receiver_name = document.getElementById("member_name_edit").value;
        receiver_number = document.getElementById("member_phone_edit").value;
    }else if(guest.checked == true){
        contribute = "Self";
        contribute_type = "Guest"
        receiver_name = document.getElementById("member_name_edit").value;
        receiver_number = document.getElementById("member_phone_edit").value;
        receiver_id = document.getElementById("member_id_edit").value;
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
    		amount_id = amount_id.replace(' Account','');
    		funds1.push(amount_id);
    		amt3.push(amt[i].value);
        }
    }

    for (var i = 0; i < amt1.length; i++) {
       	if(amt1[i].value != ''){
       		var amount_id = amt1[i].id;
       		amount_id = amount_id.replace(' Account','');
       		funds2.push(amount_id);
       		amt4.push(amt1[i].value);
       	}
    }

    for (var i = 0; i < amt2.length; i++) {
       	if(amt2[i].value != ''){
       		var amount_id = amt2[i].id;
       		amount_id = amount_id.replace(' Account','');
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
         fund_amount2: amt5,
         receipt_number: receipt_number
    }

    alert("Receipt Editing Process Initiated");


    if(church_member.checked == true || visiting_member.checked == true){
        $.post(hosted_url + "/cfms-web/personnel/member_receipt_funds_edit",funds ,function(data, status){
            if(data.status == 0){
                 alert("Member Receipt Edited Successfully");
                 window.location.reload();
            }else{
                 alert("Receipt Editing UnSuccessful");
            }
        });
    }else if(guest.checked == true){
        $.post(hosted_url + "/cfms-web/personnel/member_receipt_funds_edit_guest",funds ,function(data, status){
             if(data.status == 0){
                 alert("Guest Receipt Edited Successfully");
                 window.location.reload();
             }else{
                 alert("Guest Receipt Editing UnSuccessful");
             }
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

function trustFundViewNone(){
	var trust_fund_view = document.getElementById("trust_funds_view_none");
	var add_icon = document.getElementById("add_icon_none");
	var remove_icon = document.getElementById("remove_icon_none");

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

function nonTrustFundViewNone(){
	var non_trust_fund_view = document.getElementById("non_trust_funds_view_none");
	var add_icon = document.getElementById("add_icon_none1");
	var remove_icon = document.getElementById("remove_icon_none1");

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


function specialTrustFundViewNone(){
	var non_trust_fund_view = document.getElementById("special_trust_funds_view");
	var add_icon = document.getElementById("add_icon_none2");
	var remove_icon = document.getElementById("remove_icon_none2");

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

function showCashReceipting() {
    var transaction_tracing = document.getElementById("transaction_tracing");
    var cash_receipting = document.getElementById("cash_receipting");
    var cash_reconciliation = document.getElementById("cash_reconciliation");
    var trust_funds_div = document.getElementById("trust_funds_div");

    transaction_tracing.style.display = "none";
    cash_reconciliation.style.display = "none";
    trust_funds_div.style.display = "none";
    cash_receipting.style.display = "block";
}

function showFundDetails(identifier){
    var id = document.getElementById("view"+identifier);

    if(id.style.display === "none"){
        id.style.display = "block";
    }else{
        id.style.display = "none";
    }
}

function getFirstAndLastDate(){
    var date = new Date();
    var firstDay = new Date(date.getFullYear(), date.getMonth(), 1);
    var lastDay = new Date(date.getFullYear(), date.getMonth() + 1, 0);
    console.log(firstDay);
    console.log(lastDay)
}

function getSubmittedReceipts(receipted, member_id, phone_number){
	var receipt_numbers = Object.keys(receipted);
	var receipted_content = Object.values(receipted)

    var date = new Date();
	var firstDay = new Date(date.getFullYear(), date.getMonth(), 2);
    var lastDay = new Date(date.getFullYear(), date.getMonth() + 1, 1);

    firstDay = firstDay.toISOString().slice(0, 10);
    lastDay = lastDay.toISOString().slice(0, 10);

	var stringed = JSON.stringify(receipted);

	var html = new String("");
	html += "<div id=\"receipted_member\">This User Has Already Been Receipted Today<br><br></div>";
	html += "<div id=\"receipting_title\" style=\"display:none;\">";
    html += "You can only edit an unreconciled transaction. Select the transaction date range to proceed<br></div>";
    html += "<table>";
    html += "<tr><td><b>From Date</b></td><td><b>To Date</b></tr>";
    html += "<tr><td><input type=\"date\" name=\"\" id=\"from_date_receipt\" value="+ firstDay +"></td>";
    html += "<td><input type=\"date\" name=\"\" id=\"to_date_receipt\" value="+lastDay+"></td>";
    html += "</table>";
    html += "<div id=\"edit_receipt_select\">";
	html += "<div class=\"cardHeader\">";
    html += "<h2 style=\"text-align:center;margin-top:10px;margin-bottom:10px\">Available Receipts</h2></div>";
    html += "<table><tr><td>Receipt Numbers</td>";
	html += "<td><select id=\"receipt_numbers\" name=\"receipt_numbers\" onchange=\'changeInfo("+ stringed +")\' onclick=\'changeInfo("+ stringed +")\'>";
	for(var i = 0;i < receipt_numbers.length;i++){
	    html += "<option value="+receipt_numbers[i]+">"+ receipt_numbers[i] +"</option>";
	}
	html += "</select></td></tr>";
	html += "<tr><td colspan=\"2\"><button class=\"btn\" onclick=\"getSubmittedReceiptsEdit("+ member_id +","+ phone_number +")\">Get Receipts</button>    ";
	html += "<button class=\"btn-red\" onclick=\"deleteCashReceipt()\">Delete Receipt</button></td></tr>";
	html += "</table>";
	html += "</div>";

	document.getElementById("receipted_data").innerHTML = html;
}


function editSubmittedReceipts(){
    var trust_funds_div = document.getElementById("trust_funds_div");

    trust_funds_div.style.display = "none";

    var date = new Date();
	var firstDay = new Date(date.getFullYear(), date.getMonth(), 2);
    var lastDay = new Date(date.getFullYear(), date.getMonth() + 1, 1);

    firstDay = firstDay.toISOString().slice(0, 10);
    lastDay = lastDay.toISOString().slice(0, 10);

	var html = new String("");
	html += "<div id=\"receipting_title\">";
    html += "You can only edit an unreconciled transaction. Select the transaction date range to proceed<br></div>";
    html += "<table>";
    html += "<tr><td><b>From Date</b></td><td><b>To Date</b></tr>";
    html += "<tr><td><input type=\"date\" name=\"\" id=\"from_date_receipt\" value="+ firstDay +"></td>";
    html += "<td><input type=\"date\" name=\"\" id=\"to_date_receipt\" value="+lastDay+"></td>";
    html += "<tr><td><button class=\"btn\" onclick=\"getReceiptsEdited()\">Get Receipts</button></td></tr>";
    html += "</table>";

	document.getElementById("receipted_data").innerHTML = html;

}

function getReceiptsEdited(){
    var from_date = document.getElementById("from_date_receipt").value;
    var to_date = document.getElementById("to_date_receipt").value;
    var member_id = document.getElementById("member_id").value;
    var phone_number = document.getElementById("member_phone").value;

    if(from_date == "" || to_date == ""){
        alert("Please Enter the Start and End Dates");
        return false;
    }


    var dates = {
        phone_number: phone_number,
        cfms_member_number: member_id,
        start_date: from_date,
        end_date: to_date
    };

    console.log(dates);

    $.post(hosted_url + "/cfms-web/personnel/cash-receipted",dates   ,function(receipted, status){
        console.log(receipted);
        if(isEmpty(receipted) === false){
            var receipt_numbers = Object.keys(receipted);
            var receipted_content = Object.values(receipted);

            var stringed = JSON.stringify(receipted);

            var html = new String("");
            html += "<br><br><hr>";
            html += "<div id=\"edit_receipt_selected\">";
            html += "<div class=\"cardHeader\">";
            html += "<h2 style=\"text-align:center;margin-top:10px;margin-bottom:10px\">Available Receipts</h2></div>";
            html += "<table><tr><td>Receipt Numbers</td>";
            html += "<td><select id=\"receipt_numbers\" name=\"receipt_numbers\" onchange=\'changeEditInfo("+ stringed +")\' onclick=\'changeEditInfo("+ stringed +")\'>";
            for(var i = 0;i < receipt_numbers.length;i++){
                html += "<option value="+receipt_numbers[i]+">"+ receipt_numbers[i] +"</option>";
            }
            html += "</select></td></tr>";
            html += "<tr><td colspan=\"2\"><button class=\"btn-red\" onclick=\"deleteCashReceipted()\">Delete Receipt</button></td></tr>";
            //html += "<tr><td colspan=\"2\"><button class=\"btn\" onclick=\"getSubmittedReceiptsEdit("+ member_id +","+ phone_number +")\">Get Receipts</button></td></tr>";
            html += "</table>";
            html += "</div>";

            document.getElementById("edit_receipt_select").innerHTML = html;
            document.getElementById("edit_receipt_select").style.display = "block;"
        }else if(isEmpty(receipted) === true){
            alert("No Receipted Available for this Period");
        }
    });

}


function getSubmittedReceiptsEdit( member_id, phone_number){
    var from_date = document.getElementById("from_date_receipt").value;
    var to_date = document.getElementById("to_date_receipt").value;


    var dates = {
        phone_number: phone_number,
        cfms_member_number: member_id,
        start_date: from_date,
        end_date: to_date
    };

    $.post(hosted_url + "/cfms-web/personnel/cash-receipted",dates   ,function(receipted, status){
        console.log(data);
        var receipt_numbers = Object.keys(receipted);
        var receipted_content = Object.values(receipted);

        var stringed = JSON.stringify(receipted);

        var html = new String("");
        html += "<br><br><hr>";
        html += "<div id=\"receipt_edited\">";
        html += "<div class=\"cardHeader\">";
        html += "<h2 style=\"text-align:center;margin-top:10px;margin-bottom:10px\">Available Receipts</h2></div>";
        html += "<table><tr><td>Receipt Numbers</td>";
        html += "<td><select id=\"receipt_numbers\" name=\"receipt_numbers\" onchange=\'changeEditInfo("+ stringed +")\' onclick=\'changeEditInfo("+ stringed +")\'>";
        for(var i = 0;i < receipt_numbers.length;i++){
            html += "<option value="+receipt_numbers[i]+">"+ receipt_numbers[i] +"</option>";
        }
        html += "</select></td></tr>";
        html += "<tr><td colspan=\"2\"><button class=\"btn\" onclick=\"getSubmittedReceiptsEdit("+ member_id +","+ phone_number +")\"></button></td>";
        html += "<tr><td colspan=\"2\"><button class=\"btn-red\" onclick=\"deleteCashReceipt()\">Delete Receipt</button></td></tr>";
        html += "</table>";
        html += "</div>";

        document.getElementById("receipted_edit").innerHTML = html;
    });

}


function changeInfo(receipted){
    console.log("Change Info Check");

	var receipt_numbers = document.getElementById("receipt_numbers").value;
	var phone = document.getElementById("phone_number").value;
	var loader_spin_receipting = document.getElementById("loader_spin_receipting1");
	var receipting_title = document.getElementById("receipting_title");
	var member_phone = document.getElementById("member_phone_edited").innerHTML;
	var member_name = document.getElementById("member_name_edited").innerHTML;
	var member_id = document.getElementById("member_id_edited").innerHTML;

	var data = JSON.parse(receipted[receipt_numbers]);

	console.log(data)

	var request;
	var request1;
	var request2;
	var sum = 0.0;

    // Trust Fund Accounts
	if(data.hasOwnProperty("trustFunds")){
		request = data.trustFunds;
	}else{
		request = null;
	}

    // Non Trust Fund Accounts
	if(data.hasOwnProperty("nonTrustFunds")){
        request1 = data.nonTrustFunds;
	}else{
		request1 = null;
	}

    // Special Trust Fund Accounts
	if(data.hasOwnProperty("specialTrustFunds")){
		request2 = data.specialTrustFunds;
	}else{
		request2 = null;
	}

	var trustFund;
	var nonTrustFund;
	var specialTrustFund;
	var trustFundKeys;
    var nonTrustFundKeys;
    var specialTrustFundKeys;


	if(request != null){
		trustFund = Object.entries(request);
		trustFundKeys = Object.keys(request);
	}

	if(request1 != null){
		nonTrustFund = Object.entries(request1);
		nonTrustFundKeys = Object.keys(request1);
	}

	if(request2 != null){
		specialTrustFund = Object.entries(request2);
		specialTrustFundKeys = Object.keys(request2);
	}

    var profile_data = {
        phone_number: phone
    };

    loader_spin_receipting.style.display = "block";

	$.post(hosted_url + "/cfms-web/auth/check-account",profile_data ,function(data, status){
	    console.log(data);
        document.getElementById("receipted_member").style.display = "none";
	    loader_spin_receipting.style.display = "none";

	    var trust_funds = "[\"Tithe Account::127989\",\"Combined Offerings Account::127990\",\"Camp Meeting Account::13824\",";
        trust_funds += "\"Conference Development Account::426234\",\"Thirteenth Sabbath Account::191295\",";
        trust_funds += "\"Combined Offerings Account::13823\"]";

        // Trust Fund Accounts
        var requested =  trust_funds;
        var requested1 = data.departmentalAccounts;
        var requested2 = data.specialTrustFundAccounts;
        var requested3 = requested.slice(1,requested.length - 1);
        var requested4 = requested1.slice(1,requested1.length - 1);
        var requested5 = requested2.slice(1,requested2.length - 1);
        const departments = requested3.split(',');
        const departments1 = requested4.split(',');
        const departments2 = requested5.split(',');

        var accounts = [];
        var accounts1 = [];
        var accounts2 = [];

        receipting_title.style.display = "block";

        var html = new String("");
        html += "<br><br><hr>";
        html += "<div class=\"cardHeader\">";
        html += "<h2 style=\"text-align:center;margin-top:10px;margin-bottom:10px\">Funds Distribution</h2></div>";
        html += "<div class=\"responseDiv\" style=\"display:none;\"></div><br><hr>";
        html += "<table>";
        html += "<tr><td colspan=\"2\"><label class=\"label_input\"><b> For: </b></label></td>";
        html += "<td colspan=\"2\"><input type=\"text\" id=\"member_name_edit\" name=\"member_name\" placeholder=\"\" value='"+member_name+"' readonly>";
        html += "<input type=\"hidden\" id=\"member_phone_edit\" name=\"member_phone_edit\" placeholder=\"\" value='"+member_phone+"'><input type=\"hidden\" id=\"member_id_edit\" name=\"member_id_edit\" placeholder=\"\" value='"+member_id+"'></td></tr>";
        html += "<tr><td colspan=\"2\"><label class=\"label_input\"><b> Total: </b></label></td>";
        html += "<td colspan=\"2\"><input type=\"number\" id=\"receipting_total_edit\" name=\"receipting_total_edit\" placeholder=\"\">";
        html += "</td></tr></table>";
        html += "<table><tr><td colspan=\"2\"><h2 style=\"text-align: center;font-size:14px;padding-top:10px;padding-bottom:10px;\">Trust Funds</h2><span class=\"right-icon\"";
        html += "onclick=\"trustFundViewNone()\" id=\"add_icon_none\" style=\"display:none;\"><ion-icon name=\"add-circle-outline\" class=\"add-circle\"></ion-icon></span><span class=\"right-icon\" onclick=\"trustFundViewNone()\" id=\"remove_icon_none\"><ion-icon name=\"remove-circle-outline\" class=\"remove-circle\"></ion-icon></span</td></tr></table>";
        html += "<div id=\"trust_funds_view_none\"><table>";
        html += "<tr><td colspan=\"4\" style=\"text-align:center\"><hr></tr>";
        html += "<tr><td style=\"border-bottom:2px solid black;padding-top:7px;\" colspan=\"2\"><b>Contribution Type</b></td>";
        html += "<td style=\"border-bottom:2px solid black;text-align:right;padding-top:7px;\" colspan=\"2\"><b>Amount Offered</b></td></tr>";

        if(trustFund != null){
            for (i=0;i < departments.length; i++){
                departments[i] = departments[i].replace('"','');
                departments[i] = departments[i].replace('"','');
                departments[i] = departments[i].split("::");
                if(departments[i][0].includes("Account")){
                    departments[i][0] = departments[i][0].replace(' Account','');
                }else{
                    departments[i][0] = departments[i][0];
                }
                accounts.push(departments[i][0]);
            }

            console.log(trustFund);
            console.log(accounts);

            var uniqueChars = [...new Set(accounts)];
            console.log("Unique Characters: "+uniqueChars);

            for(i=0;i < uniqueChars.length;i++){
                if(trustFundKeys.includes(accounts[i])){
                    html += "<tr>";
                    html += "<td style=\"font-size:12px;padding-top:10px;\" colspan=\"2\"><b>"+ uniqueChars[i] +"</b><input type=\"hidden\" class=\"trust_funds1\" value='"+uniqueChars[i]+"'></td>";
                    html += "<td style=\"padding-left:15px;padding-top:10px;\" colspan=\"2\">";
                    html += "<input type=\"number\" id='"+uniqueChars[i]+"' class=\"amt_edit\" name=\"amt_edit\" placeholder=\"\" onfocus=\"GetTotal5()\" onkeydown=\"GetTotal5()\"  onkeyup=\"GetTotal5()\"  value="+request[uniqueChars[i]]+">";
                    html += "</td></tr>";
                    sum += parseInt(request[uniqueChars[i]]);
                }else{
                    html += "<tr>";
                    html += "<td style=\"font-size:12px;padding-top:10px;\" colspan=\"2\"><b>"+ uniqueChars[i] +"</b><input type=\"hidden\" class=\"trust_funds1\" value='"+uniqueChars[i]+"'></td>";
                    html += "<td style=\"padding-left:15px;padding-top:10px;\" colspan=\"2\">";
                    html += "<input type=\"number\" id='"+uniqueChars[i]+"' class=\"amt_edit\" name=\"amt_edit\" placeholder=\"\" onfocus=\"GetTotal5()\" onkeydown=\"GetTotal5()\"  onkeyup=\"GetTotal5()\">";
                    html += "</td></tr>";
                }
            }
            html += "<tbody></table>";
            html += "</table>";
            html += "</div>";
        }else{
            html += "";
        }

        html += "<table><tr>";
        html += "<td colspan=\"2\"><h2 style=\"text-align: center;font-size:14px;padding-top:10px;padding-bottom:10px;\">Non Trust Funds</h2><span class=\"right-icon\"";
        html += "onclick=\"nonTrustFundViewNone()\" id=\"add_icon_none1\" style=\"display:none;\"><ion-icon name=\"add-circle-outline\" class=\"add-circle\"></ion-icon></span><span class=\"right-icon\" onclick=\"nonTrustFundViewNone()\" id=\"remove_icon_none1\"><ion-icon name=\"remove-circle-outline\" class=\"remove-circle\"></ion-icon></span</td></tr></table>";
        html += "<div id=\"non_trust_funds_view_none\" >";
        html += "<table><tr><td colspan=\"4\" style=\"text-align:center\"><hr></tr>";
        html += "<tr><td style=\"border-bottom:2px solid black;padding-top:7px;\" colspan=\"2\"><b>Contribution Type</b></td>";
        html += "<td style=\"border-bottom:2px solid black;text-align:right;padding-top:7px;\" colspan=\"2\"><b>Amount Offered</b></td></tr>";

        if(nonTrustFund != null){
            for (i=0;i < departments1.length; i++){
                departments1[i] = departments1[i].replace('"','');
                departments1[i] = departments1[i].replace('"','');
                departments1[i] = departments1[i].split("::");
                if(departments1[i][0].includes("Account")){
                    departments1[i][0] = departments1[i][0].replace(' Account','');
                }else{
                    departments1[i][0] = departments1[i][0];
                }
                accounts1.push(departments1[i][0]);
            }

            console.log(nonTrustFund);
            console.log(accounts1);

            var uniqueChars1 = [...new Set(accounts1)];
            for(i=0;i < uniqueChars1.length;i++){
                if(nonTrustFundKeys.includes(accounts1[i])){
                    html += "<tr>";
                    html += "<td style=\"font-size:12px;padding-top:10px;\" colspan=\"2\"><b>"+ uniqueChars1[i] +" Account</b><input type=\"hidden\" class=\"non_trust_funds1\" value='"+uniqueChars1[i]+"'></td>";
                    html += "<td style=\"padding-left:15px;padding-top:10px;\" colspan=\"2\">";
                    html += "<input type=\"number\" id='"+uniqueChars1[i]+"' class=\"amt_edit1\" name=\"amt_edit1\" placeholder=\"\" onfocus=\"GetTotal5()\" onkeydown=\"GetTotal5()\"  onkeyup=\"GetTotal5()\" value="+parseFloat(request1[uniqueChars1[i]])+">";
                    html += "</td></tr>";
                    sum += parseInt(request1[uniqueChars1[i]]);
                }else{
                    html += "<tr>";
                    html += "<td style=\"font-size:12px;padding-top:10px;\" colspan=\"2\"><b>"+ uniqueChars1[i] +" Account</b><input type=\"hidden\" class=\"non_trust_funds1\" value='"+uniqueChars1[i]+"'></td>";
                    html += "<td style=\"padding-left:15px;padding-top:10px;\" colspan=\"2\">";
                    html += "<input type=\"number\" id='"+uniqueChars1[i]+"' class=\"amt_edit1\" name=\"amt_edit1\" placeholder=\"\" onfocus=\"GetTotal5()\" onkeydown=\"GetTotal5()\"  onkeyup=\"GetTotal5()\">";
                    html += "</td></tr>";
                }
            }

            html += "</form><tbody></table>";
            html += "</div>";
        }else{
            html += "";
        }

        html += "<table><tr>";
        html += "<td colspan=\"4\"><h2 style=\"text-align: center;font-size:14px;padding-top:10px;padding-bottom:10px;\">Special Trust Funds</h2><span class=\"right-icon\"";
        html += "onclick=\"specialTrustFundViewNone()\" id=\"add_icon_none2\" style=\"display:none;\"><ion-icon name=\"add-circle-outline\" class=\"add-circle\"></ion-icon></span><span class=\"right-icon\" onclick=\"specialTrustFundViewNone()\" id=\"remove_icon_none2\" style=\"display:block;\"><ion-icon name=\"remove-circle-outline\" class=\"remove-circle\"></ion-icon></span</td></tr></table>";
        html += "<div id=\"special_trust_funds_view\">";
        html += "<table><tr><td colspan=\"4\" style=\"text-align:center\"><hr></tr>";
        html += "<tr><td style=\"border-bottom:2px solid black;padding-top:7px;\" colspan=\"2\"><b>Contribution Type</b></td>";
        html += "<td style=\"border-bottom:2px solid black;text-align:right;padding-top:7px;\" colspan=\"2\"><b>Amount Offered</b></td></tr>";

        if(specialTrustFund != null){
            for (i=0;i < departments2.length; i++){
                departments2[i] = departments2[i].replace('"','');
                departments2[i] = departments2[i].replace('"','');
                departments2[i] = departments2[i].split("::");
                if(departments2[i][0].includes("Account")){
                    departments2[i][0] = departments2[i][0].replace(' Account','');
                }else{
                    departments2[i][0] = departments2[i][0];
                }
                accounts2.push(departments2[i][0]);
            }

            var uniqueChars2 = [...new Set(accounts2)];
            for(i=0;i < uniqueChars2.length;i++){
                if(specialTrustFundKeys.includes(accounts2[i])){
                    html += "<tr>";
                    html += "<td style=\"font-size:12px;padding-top:10px;\" colspan=\"2\"><b>"+ uniqueChars2[i] +" Account</b><input type=\"hidden\" class=\"special_trust_funds1\" value='"+uniqueChars2[i]+"'></td>";
                    html += "<td style=\"padding-left:15px;padding-top:10px;\" colspan=\"2\">";
                    html += "<input type=\"number\" id='"+uniqueChars2[i]+"' class=\"amt_edit2\" name=\"amt_edit2\" placeholder=\"\" onfocus=\"GetTotal5()\" onkeydown=\"GetTotal5()\"  onkeyup=\"GetTotal5()\"  value="+parseFloat(request2[uniqueChars2[i]])+">";
                    html += "</td></tr>";
                    sum += parseInt(request2[uniqueChars2[i]]);
                }else{
                    html += "<tr>";
                    html += "<td style=\"font-size:12px;padding-top:10px;\" colspan=\"2\"><b>"+ uniqueChars2[i] +" Account</b><input type=\"hidden\" class=\"special_trust_funds1\" value='"+uniqueChars2[i]+"'></td>";
                    html += "<td style=\"padding-left:15px;padding-top:10px;\" colspan=\"2\">";
                    html += "<input type=\"number\" id='"+uniqueChars2[i]+"' class=\"amt_edit2\" name=\"amt_edit2\" placeholder=\"\" onfocus=\"GetTotal5()\" onkeydown=\"GetTotal5()\"  onkeyup=\"GetTotal5()\">";
                    html += "</td></tr>";
                }
            }
        }else{
            html += "";
        }

        html += "</table></div>"
        html += "<table><tr><td style=\"font-size:12px;\" colspan=\"2\"><b>Distribution Total</b></td>";
        html += "<td style=\"padding-left:15px;padding-top:10px;\" colspan=\"2\"><input type=\"text\" class=\"login_input\" id=\"FTotal_edit3\" name=\"churchCode\" placeholder=\"\"  value="+sum+" readonly></td></tr>";
        html += "<tr><td colspan=\"2\"><button type=\"button\" class=\"btn\" onclick=\"getMemberEditReceipting()\" id=\"payment_button3\">Confirm</button></td></tr>";
        html += "</table>";

        $("#trust_funds_div_edit").html(html);
        console.log(sum);
        document.getElementById("receipting_total_edit").value = parseFloat(sum);
    });

}


function changeEditInfo(receipted){
    console.log("Change Info Check");

	var receipt_numbers = document.getElementById("receipt_numbers").value;
	var phone = document.getElementById("phone_number").value;
	var loader_spin_receipting = document.getElementById("loader_spin_receipting2");
	var receipting_title = document.getElementById("receipting_title");
	var member_phone = document.getElementById("member_phone").value;
	var member_name = document.getElementById("member_name").value;
	var member_id = document.getElementById("member_id").value;

	var data = JSON.parse(receipted[receipt_numbers]);

	console.log(data)

	var request;
	var request1;
	var request2;
	var sum = 0.0;

    // Trust Fund Accounts
	if(data.hasOwnProperty("trustFunds")){
		request = data.trustFunds;
	}else{
		request = null;
	}

    // Non Trust Fund Accounts
	if(data.hasOwnProperty("nonTrustFunds")){
        request1 = data.nonTrustFunds;
	}else{
		request1 = null;
	}

    // Special Trust Fund Accounts
	if(data.hasOwnProperty("specialTrustFunds")){
		request2 = data.specialTrustFunds;
	}else{
		request2 = null;
	}

	var trustFund;
	var nonTrustFund;
	var specialTrustFund;
	var trustFundKeys;
    var nonTrustFundKeys;
    var specialTrustFundKeys;


	if(request != null){
		trustFund = Object.entries(request);
		trustFundKeys = Object.keys(request);
	}

	if(request1 != null){
		nonTrustFund = Object.entries(request1);
		nonTrustFundKeys = Object.keys(request1);
	}

	if(request2 != null){
		specialTrustFund = Object.entries(request2);
		specialTrustFundKeys = Object.keys(request2);
	}

    var profile_data = {
        phone_number: phone
    };

    loader_spin_receipting.style.display = "block";

	$.post(hosted_url + "/cfms-web/auth/check-account",profile_data ,function(data, status){
        document.getElementById("receipting_title").style.display = "none";
	    loader_spin_receipting.style.display = "none";

	    var trust_funds = "[\"Tithe Account::127989\",\"Combined Offerings Account::127990\",\"Camp Meeting Account::13824\",";
        trust_funds += "\"Conference Development Account::426234\",\"Thirteenth Sabbath Account::191295\",";
        trust_funds += "\"Combined Offerings Account::13823\"]";

        // Trust Fund Accounts
        var requested =  trust_funds;
        var requested1 = data.departmentalAccounts;
        var requested2 = data.specialTrustFundAccounts;
        var requested3 = requested.slice(1,requested.length - 1);
        var requested4 = requested1.slice(1,requested1.length - 1);
        var requested5 = requested2.slice(1,requested2.length - 1);
        const departments = requested3.split(',');
        const departments1 = requested4.split(',');
        const departments2 = requested5.split(',');

        var accounts = [];
        var accounts1 = [];
        var accounts2 = [];

        receipting_title.style.display = "block";

        var html = new String("");
        html += "<br><br><hr>";
        html += "<div class=\"cardHeader\">";
        html += "<h2 style=\"text-align:center;margin-top:10px;margin-bottom:10px\">Funds Distribution</h2></div>";
        html += "<div class=\"responseDiv\" style=\"display:none;\"></div><br><hr>";
        html += "<table>";
        html += "<tr><td colspan=\"2\"><label class=\"label_input\"><b> For: </b></label></td>";
        html += "<td colspan=\"2\"><input type=\"text\" id=\"member_name_edit\" name=\"member_name\" placeholder=\"\" value='"+member_name+"' readonly>";
        html += "<input type=\"hidden\" id=\"member_phone_edit\" name=\"member_phone_edit\" placeholder=\"\" value='"+member_phone+"'><input type=\"hidden\" id=\"member_id_edit\" name=\"member_id_edit\" placeholder=\"\" value='"+member_id+"'></td></tr>";
        html += "<tr><td colspan=\"2\"><label class=\"label_input\"><b> Total: </b></label></td>";
        html += "<td colspan=\"2\"><input type=\"number\" id=\"receipting_total_edit\" name=\"receipting_total_edit\" placeholder=\"\">";
        html += "</td></tr></table>";
        html += "<table><tr><td colspan=\"2\"><h2 style=\"text-align: center;font-size:14px;padding-top:10px;padding-bottom:10px;\">Trust Funds</h2><span class=\"right-icon\"";
        html += "onclick=\"trustFundViewNone()\" id=\"add_icon_none\" style=\"display:none;\"><ion-icon name=\"add-circle-outline\" class=\"add-circle\"></ion-icon></span><span class=\"right-icon\" onclick=\"trustFundViewNone()\" id=\"remove_icon_none\"><ion-icon name=\"remove-circle-outline\" class=\"remove-circle\"></ion-icon></span</td></tr></table>";
        html += "<div id=\"trust_funds_view_none\"><table>";
        html += "<tr><td colspan=\"4\" style=\"text-align:center\"><hr></tr>";
        html += "<tr><td style=\"border-bottom:2px solid black;padding-top:7px;\" colspan=\"2\"><b>Contribution Type</b></td>";
        html += "<td style=\"border-bottom:2px solid black;text-align:right;padding-top:7px;\" colspan=\"2\"><b>Amount Offered</b></td></tr>";

        if(trustFund != null){
            for (i=0;i < departments.length; i++){
                departments[i] = departments[i].replace('"','');
                departments[i] = departments[i].replace('"','');
                departments[i] = departments[i].split("::");
                if(departments[i][0].includes("Account")){
                    departments[i][0] = departments[i][0].replace(" Account","");
                }else{
                    departments[i][0] = departments[i][0];
                }
                accounts.push(departments[i][0]);
            }

            var uniqueChars = [...new Set(accounts)];
            console.log("Unique Characters: "+uniqueChars);

            for(i=0;i < uniqueChars.length;i++){
                if(trustFundKeys.includes(accounts[i])){
                    html += "<tr>";
                    html += "<td style=\"font-size:12px;padding-top:10px;\" colspan=\"2\"><b>"+ uniqueChars[i] +" Account</b><input type=\"hidden\" class=\"trust_funds1\" value='"+uniqueChars[i]+"'></td>";
                    html += "<td style=\"padding-left:15px;padding-top:10px;\" colspan=\"2\">";
                    html += "<input type=\"number\" id='"+uniqueChars[i]+"' class=\"amt_edit\" name=\"amt_edit\" placeholder=\"\" onfocus=\"GetTotal5()\" onkeydown=\"GetTotal5()\"  onkeyup=\"GetTotal5()\"  value="+request[uniqueChars[i]]+">";
                    html += "</td></tr>";
                    sum += parseInt(request[uniqueChars[i]]);
                }else{
                    html += "<tr>";
                    html += "<td style=\"font-size:12px;padding-top:10px;\" colspan=\"2\"><b>"+ uniqueChars[i] +" Account</b><input type=\"hidden\" class=\"trust_funds1\" value='"+uniqueChars[i]+"'></td>";
                    html += "<td style=\"padding-left:15px;padding-top:10px;\" colspan=\"2\">";
                    html += "<input type=\"number\" id='"+uniqueChars[i]+"' class=\"amt_edit\" name=\"amt_edit\" placeholder=\"\" onfocus=\"GetTotal5()\" onkeydown=\"GetTotal5()\"  onkeyup=\"GetTotal5()\">";
                    html += "</td></tr>";
                }
            }
            html += "<tbody></table>";
            html += "</table>";
            html += "</div>";
        }else{
            html += "";
        }

        html += "<table><tr>";
        html += "<td colspan=\"2\"><h2 style=\"text-align: center;font-size:14px;padding-top:10px;padding-bottom:10px;\">Non Trust Funds</h2><span class=\"right-icon\"";
        html += "onclick=\"nonTrustFundViewNone()\" id=\"add_icon_none1\" style=\"display:none;\"><ion-icon name=\"add-circle-outline\" class=\"add-circle\"></ion-icon></span><span class=\"right-icon\" onclick=\"nonTrustFundViewNone()\" id=\"remove_icon_none1\"><ion-icon name=\"remove-circle-outline\" class=\"remove-circle\"></ion-icon></span</td></tr></table>";
        html += "<div id=\"non_trust_funds_view_none\" >";
        html += "<table><tr><td colspan=\"4\" style=\"text-align:center\"><hr></tr>";
        html += "<tr><td style=\"border-bottom:2px solid black;padding-top:7px;\" colspan=\"2\"><b>Contribution Type</b></td>";
        html += "<td style=\"border-bottom:2px solid black;text-align:right;padding-top:7px;\" colspan=\"2\"><b>Amount Offered</b></td></tr>";

        if(nonTrustFund != null){
            for (i=0;i < departments1.length; i++){
                departments1[i] = departments1[i].replace('"','');
                departments1[i] = departments1[i].replace('"','');
                departments1[i] = departments1[i].split("::");
                if(departments1[i][0].includes("Account")){
                    departments1[i][0] = departments1[i][0].replace(" Account","");
                }else{
                    departments1[i][0] = departments1[i][0];
                }
                accounts1.push(departments1[i][0]);
            }

            var uniqueChars1 = [...new Set(accounts1)];
            for(i=0;i < uniqueChars1.length;i++){
                if(nonTrustFundKeys.includes(accounts1[i])){
                    html += "<tr>";
                    html += "<td style=\"font-size:12px;padding-top:10px;\" colspan=\"2\"><b>"+ uniqueChars1[i] +" Account</b><input type=\"hidden\" class=\"non_trust_funds1\" value='"+uniqueChars1[i]+"'></td>";
                    html += "<td style=\"padding-left:15px;padding-top:10px;\" colspan=\"2\">";
                    html += "<input type=\"number\" id='"+uniqueChars1[i]+"' class=\"amt_edit1\" name=\"amt_edit1\" placeholder=\"\" onfocus=\"GetTotal5()\" onkeydown=\"GetTotal5()\"  onkeyup=\"GetTotal5()\" value="+parseFloat(request1[uniqueChars1[i]])+">";
                    html += "</td></tr>";
                    sum += parseInt(request1[uniqueChars1[i]]);
                }else{
                    html += "<tr>";
                    html += "<td style=\"font-size:12px;padding-top:10px;\" colspan=\"2\"><b>"+ uniqueChars1[i] +" Account</b><input type=\"hidden\" class=\"non_trust_funds1\" value='"+uniqueChars1[i]+"'></td>";
                    html += "<td style=\"padding-left:15px;padding-top:10px;\" colspan=\"2\">";
                    html += "<input type=\"number\" id='"+uniqueChars1[i]+"' class=\"amt_edit1\" name=\"amt_edit1\" placeholder=\"\" onfocus=\"GetTotal5()\" onkeydown=\"GetTotal5()\"  onkeyup=\"GetTotal5()\">";
                    html += "</td></tr>";
                }
            }

            html += "</form><tbody></table>";
            html += "</div>";
        }else{
            html += "";
        }

        html += "<table><tr>";
        html += "<td colspan=\"4\"><h2 style=\"text-align: center;font-size:14px;padding-top:10px;padding-bottom:10px;\">Special Trust Funds</h2><span class=\"right-icon\"";
        html += "onclick=\"specialTrustFundViewNone()\" id=\"add_icon_none2\" style=\"display:none;\"><ion-icon name=\"add-circle-outline\" class=\"add-circle\"></ion-icon></span><span class=\"right-icon\" onclick=\"specialTrustFundViewNone()\" id=\"remove_icon_none2\" style=\"display:block;\"><ion-icon name=\"remove-circle-outline\" class=\"remove-circle\"></ion-icon></span</td></tr></table>";
        html += "<div id=\"special_trust_funds_view\">";
        html += "<table><tr><td colspan=\"4\" style=\"text-align:center\"><hr></tr>";
        html += "<tr><td style=\"border-bottom:2px solid black;padding-top:7px;\" colspan=\"2\"><b>Contribution Type</b></td>";
        html += "<td style=\"border-bottom:2px solid black;text-align:right;padding-top:7px;\" colspan=\"2\"><b>Amount Offered</b></td></tr>";

        if(specialTrustFund != null){
            for (i=0;i < departments2.length; i++){
                departments2[i] = departments2[i].replace('"','');
                departments2[i] = departments2[i].replace('"','');
                departments2[i] = departments2[i].split("::");
                if(departments2[i][0].includes("Account")){
                    departments2[i][0] = departments2[i][0].replace(" Account","");
                }else{
                    departments2[i][0] = departments2[i][0];
                }
                accounts2.push(departments2[i][0]);
            }

            var uniqueChars2 = [...new Set(accounts2)];
            for(i=0;i < uniqueChars2.length;i++){
                if(specialTrustFundKeys.includes(accounts2[i])){
                    html += "<tr>";
                    html += "<td style=\"font-size:12px;padding-top:10px;\" colspan=\"2\"><b>"+ uniqueChars2[i] +" Account</b><input type=\"hidden\" class=\"special_trust_funds1\" value='"+uniqueChars2[i]+"'></td>";
                    html += "<td style=\"padding-left:15px;padding-top:10px;\" colspan=\"2\">";
                    html += "<input type=\"number\" id='"+uniqueChars2[i]+"' class=\"amt_edit2\" name=\"amt_edit2\" placeholder=\"\" onfocus=\"GetTotal5()\" onkeydown=\"GetTotal5()\"  onkeyup=\"GetTotal5()\"  value="+parseFloat(request2[uniqueChars2[i]])+">";
                    html += "</td></tr>";
                    sum += parseInt(request2[uniqueChars2[i]]);
                }else{
                    html += "<tr>";
                    html += "<td style=\"font-size:12px;padding-top:10px;\" colspan=\"2\"><b>"+ uniqueChars2[i] +" Account</b><input type=\"hidden\" class=\"special_trust_funds1\" value='"+uniqueChars2[i]+"'></td>";
                    html += "<td style=\"padding-left:15px;padding-top:10px;\" colspan=\"2\">";
                    html += "<input type=\"number\" id='"+uniqueChars2[i]+"' class=\"amt_edit2\" name=\"amt_edit2\" placeholder=\"\" onfocus=\"GetTotal5()\" onkeydown=\"GetTotal5()\"  onkeyup=\"GetTotal5()\">";
                    html += "</td></tr>";
                }
            }
        }else{
            html += "";
        }

        html += "</table></div>"
        html += "<table><tr><td style=\"font-size:12px;\" colspan=\"2\"><b>Distribution Total</b></td>";
        html += "<td style=\"padding-left:15px;padding-top:10px;\" colspan=\"2\"><input type=\"text\" class=\"login_input\" id=\"FTotal_edit3\" name=\"churchCode\" placeholder=\"\"  value="+sum+" readonly></td></tr>";
        html += "<tr><td colspan=\"2\"><button type=\"button\" class=\"btn\" onclick=\"getMemberEditReceipting()\" id=\"payment_button3\">Confirm</button></td></tr>";
        html += "</table>";

        $("#trust_funds_div_edited").html(html);
        document.getElementById("receipting_total_edit").value = parseFloat(sum);
    });

}

function showTransactionTracing(){
    var transaction_tracing = document.getElementById("transaction_tracing");
    var cash_receipting = document.getElementById("cash_receipting");
    var cash_reconciliation = document.getElementById("cash_reconciliation");
    var trust_funds_div = document.getElementById("trust_funds_div");
    var trust_funds_div_edit = document.getElementById("trust_funds_div_edit");
    var receipted_data = document.getElementById("receipted_data");

    receipted_data.style.display = "none";
    trust_funds_div_edit.style.display = "none";
    cash_reconciliation.style.display = "none";
    cash_receipting.style.display = "none";
    trust_funds_div.style.display = "none";
    transaction_tracing.style.display = "block";
}


function showReconciliation(){
    var transaction_tracing = document.getElementById("transaction_tracing");
    var cash_receipting = document.getElementById("cash_receipting");
    var cash_reconciliation = document.getElementById("cash_reconciliation");
    var trust_funds_div = document.getElementById("trust_funds_div");
    var trust_funds_div_edit = document.getElementById("trust_funds_div_edit");
    var receipted_data = document.getElementById("receipted_data");

    receipted_data.style.display = "none";
    trust_funds_div_edit.style.display = "none";
    cash_receipting.style.display = "none";
    transaction_tracing.style.display = "none";
    trust_funds_div.style.display = "none";
    cash_reconciliation.style.display = "block";
}

function showMoreDetails(){
    var phone_number_trace = document.getElementById("phone_number_trace");
    var receipt_number_trace = document.getElementById("receipt_number_trace");
    var associated_phone_div = document.getElementById("associated_phone_div");
    var associated_receipt_div = document.getElementById("associated_receipt_div");

    if(phone_number_trace.checked == true){
        associated_receipt_div.style.display = "none";
        associated_phone_div.style.display = "block";
    }else if(receipt_number_trace.checked == true){
        associated_phone_div.style.display = "none";
        associated_receipt_div.style.display = "block";
    }
}

function traceTransactionPhone(){
    var start_date = document.getElementById("start_date").value;
    var end_date = document.getElementById("end_date").value;
    var associated_phone_number = document.getElementById("associated_phone_number").value;
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;
    var phone_number = document.getElementById("phone_number").value;
    var content_receipt = document.getElementById("content_receipt");
    var loader_spin_tracing = document.getElementById("loader_spin_tracing");

    content_receipt.style.display = "none";
    loader_spin_tracing.style.display = "block";

    associated_phone_number = associated_phone_number.trim();

    var tracing_data = {
        start_date: start_date,
        end_date: end_date,
        username: username,
        password: password,
        phone_number: phone_number,
        associated_phone_number: associated_phone_number
    };

    console.log(tracing_data);

    $.post(hosted_url + "/cfms-web/personnel/transaction_tracing_phone",tracing_data ,function(data, status){

        if(data.payload.transactions.length != 0){
            loader_spin_tracing.style.display = "none";

            var tracing = data.payload.transactions;
            var trans_array = data.payload.transactions;
            var html = new String("");
            html += "<br>";
            html += "<div class=\"cardHeader\">";
            html += "<h2>Transaction Tracing List Using Phone Number</h2></div>";
            html += "<br>";
            html += "<input type=\"text\" placeholder=\"Search here\" id=\"myInput_Trace\">"
            html += "<br><br><div id=\"transaction_tracing_result\">";




            for(var i=0; i < trans_array.length; i++){
                var trans_details = data.payload.transactions[i].transactionDetails;
                var special_trust_funds = trans_details.specialTrustFunds;
                var trust_funds = trans_details.trustFunds;
                var non_trust_funds = trans_details.nonTrustFunds;


                html += "<div class=\"con\" onclick=\"showFundDetails("+i+")\">";
                html += "<table>";
                html += "<tr colspan=\"2\">";
                html += "<td style=\"text-align:left;\">"+tracing[i].cfms_transaction_id+" </td><td style=\"text-align:right;\"> "+tracing[i].giving_status+"</td>";
                html += "</tr></table>";
                html += "<div id=\"view"+i+"\" style=\"display:none;\">";
                html += "<table>";
                html += "<tr>";
                html += "<td style=\"text-align:left;\">Member Number</td><td style=\"text-align:right;\">"+tracing[i].receiver_id+"</td>";
                html += "</tr>";
                html += "<tr>";
                html += "<td style=\"text-align:left;\">Settlement Status</td><td style=\"text-align:right;\">"+tracing[i].giving_status+"</td>";
                html += "</tr>";
                if(trust_funds != null){
                    trust_funds = Object.entries(trust_funds);
                    for(var x=0; x<trust_funds.length; x++){
                        html += "<tr>";
                        html += "<td style=\"text-align:left;\">"+trust_funds[x][0]+"</td><td style=\"text-align:right;\">"+trust_funds[x][1]+".0</td>";
                        html += "</tr>";
                    }
                }

                if(non_trust_funds != null){
                    non_trust_funds = Object.entries(non_trust_funds);
                    for(var x=0; x < non_trust_funds.length; x++){
                        html += "<tr>";
                        html += "<td style=\"text-align:left;\">"+non_trust_funds[x][0]+"</td><td style=\"text-align:right;\">"+non_trust_funds[x][1]+".0</td>";
                        html += "</tr>";
                    }
                }

                if(special_trust_funds != null){
                    special_trust_funds = Object.entries(special_trust_funds);
                    for(var x=0; x < special_trust_funds.length; x++){
                        html += "<tr>";
                        html += "<td style=\"text-align:left;\">"+special_trust_funds[x][0]+"</td><td style=\"text-align:right;\">"+special_trust_funds[x][1]+".0</td>";
                        html += "</tr>";
                    }
                }
                html += "</table>"
                html += "</div></div>";
            }

            html +"</div>";

            $("#content_phone").html(html);

        }else if(data.payload.transactions.length == 0){
            loader_spin_tracing.style.display = "none";
            alert("No Funds to Display for this Phone Number. Check the Dates or the Phone Number");
        }

    });

}



function traceTransactionReceipt(){
    var start_date = document.getElementById("start_date").value;
    var end_date = document.getElementById("end_date").value;
    var associated_receipt_number = document.getElementById("associated_receipt_number").value;
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;
    var phone_number = document.getElementById("phone_number").value;
    var content_phone = document.getElementById("content_phone");
    var loader_spin_tracing = document.getElementById("loader_spin_tracing")

    content_phone.style.display = "none";
    loader_spin_tracing.style.display = "block";

    associated_receipt_number = associated_receipt_number.trim();

    var tracing_data = {
        start_date: start_date,
        end_date: end_date,
        username: username,
        password: password,
        phone_number: phone_number,
        associated_receipt_number: associated_receipt_number
    };

    console.log(tracing_data);

    $.post(hosted_url + "/cfms-web/personnel/transaction_tracing_receipt",tracing_data ,function(data, status){
        if(data.payload.transactions.length != 0){
            loader_spin_tracing.style.display = "none";


            var tracing = data.payload.transactions[0];
            var trans_details = data.payload.transactions[0].transactionDetails;
            var special_trust_funds = trans_details.specialTrustFunds;
            var trust_funds = trans_details.trustFunds;
            var non_trust_funds = trans_details.nonTrustFunds;


            var html = new String("");
            html += "<br>";
            html += "<h2>Transaction Tracing List Using Receipt Number</h2>";
            html += "<br>";
            html += "<div class=\"con\">";
            html += "<table>";
            html += "<tr onclick=\"showFundDetails("+1+")\" colspan=\"2\">";
            html += "<td style=\"text-align:left;\">"+tracing.cfms_transaction_id+"</td><td style=\"text-align:right;\"> "+tracing.giving_status+"</td>";
            html += "</tr></table>";
            html += "<div id=\"view1\" style=\"none\">";
            html += "<table>";
            html += "<tr>";
            html += "<td style=\"text-align:left;\">Member Number</td><td style=\"text-align:right;\">"+tracing.receiver_id+"</td>";
            html += "</tr>";
            html += "<tr>";
            html += "<td style=\"text-align:left;\">Settlement Status</td><td style=\"text-align:right;\">"+tracing.giving_status+"</td>";
            html += "</tr>";
            if(trust_funds != null){
                trust_funds = Object.entries(trust_funds);
                for(var x=0; x<trust_funds.length; x++){
                    html += "<tr>";
                    html += "<td style=\"text-align:left;\">"+trust_funds[x][0]+"</td><td style=\"text-align:right;\">"+trust_funds[x][1]+".0</td>";
                    html += "</tr>";
                }
            }

            if(non_trust_funds != null){
                non_trust_funds = Object.entries(non_trust_funds);
                for(var x=0; x < non_trust_funds.length; x++){
                    html += "<tr>";
                    html += "<td style=\"text-align:left;\">"+non_trust_funds[x][0]+"</td><td style=\"text-align:right;\">"+non_trust_funds[x][1]+".0</td>";
                    html += "</tr>";
                }
            }

            if(special_trust_funds != null){
                special_trust_funds = Object.entries(special_trust_funds);
                for(var x=0; x < special_trust_funds.length; x++){
                    html += "<tr>";
                    html += "<td style=\"text-align:left;\">"+special_trust_funds[x][0]+"</td><td style=\"text-align:right;\">"+special_trust_funds[x][1]+".0</td>";
                    html += "</tr>";
                }
            }
            html += "</table>"
            html += "</div></div>";
            $("#content_receipt").html(html);

        }else if(data.payload.transactions.length == 0){
            loader_spin_tracing.style.display = "none";
            alert("No Receipt Number Available. Try Again and Check the Dates or Receipt Number");
        }

    });
}

// Delete Cash Receipt
function deleteCashReceipt(){
    var receipt_number = document.getElementById("receipt_numbers").value;
    var member_id = document.getElementById("member_id_edited").innerHTML;
    var member_phone = document.getElementById("member_phone_edited").innerHTML;

    var fund_data = {
        cfms_member_number: member_id,
        phone_number: member_phone,
        receipt_number: receipt_number
    };

    alert("Receipt Deletion Process Initiated");

    $.post(hosted_url + "/cfms-web/personnel/cash-receipted-delete",fund_data ,function(data, status){
        console.log(data);
        if(data.status == 0){
            alert("Cash Receipt Successfully Deleted");
            window.location.reload();
        }else{
            alert("Cash Receipt Deletion UnSuccessful");
        }

    });
}


// Delete Cash Receipt
function deleteCashReceipted(){
    var receipt_number = document.getElementById("receipt_numbers").value;
    var member_id = document.getElementById("member_id").value;
    var member_phone = document.getElementById("member_phone").value;

    var fund_data = {
        cfms_member_number: member_id,
        phone_number: member_phone,
        receipt_number: receipt_number
    };

    alert("Receipt Deletion Process Initiated");

    $.post(hosted_url + "/cfms-web/personnel/cash-receipted-delete",fund_data ,function(data, status){
        if(data.status == 0){
            alert("Cash Receipt Successfully Deleted");
            window.location.reload();
        }else{
            alert("Cash Receipt Deletion UnSuccessful");
        }

    });
}


function displayReconciliation(){
    var continue_reconciliation = document.getElementById("continue_reconciliation");
    var recon_header = document.getElementById("recon_header");

    continue_reconciliation.style.display = "none";
    recon_header.innerHTML = "Continuing Reconciliation";
}


function getReconciliation(){
    var start_date = document.getElementById("start_date_recon").value;
    var end_date = document.getElementById("end_date_recon").value;
    var actual_amount = document.getElementById("actual_amount").value;

    var recon = {
        start_date: start_date,
        end_date: end_date,
        actual_amount: actual_amount
    };

    console.log(recon);
}