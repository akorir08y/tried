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

function checkVisitorName(){
    var funds_div = document.getElementById("trust_funds_div");
    var phone = document.getElementById("visitor_phone").value;
    var profile_data = {
        phone_number: phone
    };

    $.post(hosted_url + "/cfms-web/auth/check-name",profile_data ,function(data, status){
            if(data.payload != null){
                var statement = "Is "+ data.payload.member_name + " the Visitor you wish to contribute for?";
                if(confirm(statement) == true){
                    funds_div.style.display = "none";
                    document.getElementById("member_name").value =  data.payload.member_name;
                    document.getElementById("member_phone").value = phone;
                }
            }else{
                $(".responseDiv").show();
                $(".responseDiv").html("The Number Entered is not Associated with any Visitor");
                $(".responseDiv").fadeOut(3000);
            }
    });
}



function checkGuestName(){
    var funds_div = document.getElementById("trust_funds_div");
    var name = document.getElementById("guest_name").value;
    var phone = document.getElementById("guest_phone").value;
    var profile_data = {
        phone_number: phone
    };

    $.post(hosted_url + "/cfms-web/auth/check-name",profile_data ,function(data, status){
            if(data.payload != null){
                var statement = "The member "+ data.payload.member_name + " is already registered to "+data.payload.church_code+".";
                statement += "Would you like to contribute for them as a guest?";
                if(confirm(statement) == true){
                    funds_div.style.display = "block";
                    document.getElementById("member_name").value =  data.payload.member_name;
                    document.getElementById("member_phone").value = phone;
                }
            }else{
                cash_receipting.style.display = "none";
                guest.style.display = "block";
            }
    });
}


function getBothFundAccounts(){
    var phone = document.getElementById("personal_no").value;
    var profile_data = {
        phone_number: phone
    };

	$.post(hosted_url + "/cfms-web/auth/check-name",profile_data ,function(data, status){
        // Trust Fund Accounts
        var request = data.trustFundAccounts;
        var request1 = data.departmentalAccounts;
        var request2 = data.specialTrustFundAccounts;
        request = request.replace('[', '');
        request = request.replace(']', '');
        request1 = request1.replace('[', '');
        request1 = request1.replace(']', '');
        request2 = request2.replace('[', '');
        request2 = request2.replace(']', '');
        const departments = request.split(',');
        const departments1 = request1.split(',');
        const departments2 = request2.split(',');
        var html = new String("");
        var accounts = [];
        var accounts1 = [];
        var accounts2 = [];

        html += "<div class=\"cardHeader\">";
        html += "<h2 style=\"text-align:center;margin-top:10px;margin-bottom:10px\">Funds Distribution</h2></div>";
        html += "<div class=\"responseDiv\" style=\"display:none;\"></div><br><hr>";
        html += "<table>";
        html += "<tr><td><label class=\"label_input\"><b> For: </b></label></td>";
        html += "<td><input type=\"text\" id=\"member_name\" name=\"member_name\" readonly>";
        html += "<input type=\"hidden\" id=\"member_phone\" name=\"member_phone\" readonly></td></tr>";
        html += "</tr><tr></table>";
        html += "<table><tr><td colspan=\"2\"><h2 style=\"text-align: center;font-size:14px;padding-top:10px;padding-bottom:10px;\">Trust Funds</h2><span class=\"right-icon\"";
        html += "onclick=\"trustFundView()\" id=\"add_icon\" style=\"display:none;\"><ion-icon name=\"add-circle-outline\"></ion-icon></span><span class=\"right-icon\" onclick=\"trustFundView()\" id=\"remove_icon\"><ion-icon name=\"remove-circle-outline\"></ion-icon></span</td></tr></table>";
        html += "<div id=\"trust_funds_view\" style=\"display:block;\"><table>";
        html += "<table><tr><td colspan=\"2\" style=\"text-align:center\"><hr></tr>";
        html += "<tr><td style=\"border-bottom:2px solid black;padding-top:7px;\"><b>Contribution Type</b></td>";
        html += "<td style=\"border-bottom:2px solid black;text-align:right;padding-top:7px;\"><b>Amount Offered</b></td></tr>";


        for (i=0;i < departments.length; i++){
            departments[i] = departments[i].replace('"','');
            departments[i] = departments[i].replace('"','');
            departments[i] = departments[i].split("::");
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
        html += "</div>";

        html += "<table><tr>";
        html += "<td colspan=\"2\"><h2 style=\"text-align: center;font-size:14px;padding-top:10px;padding-bottom:10px;\">Non Trust Funds</h2><span class=\"right-icon\"";
        html += "onclick=\"nonTrustFundView()\" id=\"add_icon1\" style=\"display:none;\"><ion-icon name=\"add-circle-outline\"></ion-icon></span><span class=\"right-icon\" onclick=\"nonTrustFundView()\" id=\"remove_icon1\"><ion-icon name=\"remove-circle-outline\"></ion-icon></span</td></tr></table>";
        html += "<div id=\"non_trust_funds_view\"  style=\"display:block;\">";
        html += "<table><tr><td colspan=\"2\" style=\"text-align:center\"><hr></tr>";
        html += "<tr><td style=\"border-bottom:2px solid black;padding-top:7px;\"><b>Contribution Type</b></td>";
        html += "<td style=\"border-bottom:2px solid black;text-align:right;padding-top:7px;\"><b>Amount Offered</b></td></tr>";

        for (i=0;i < departments1.length; i++){
            departments1[i] = departments1[i].replace('"','');
            departments1[i] = departments1[i].replace('"','');
            departments1[i] = departments1[i].split("::");
            accounts1.push(departments1[i][0]);
        }

        let uniqueChars1 = [...new Set(accounts1)];

        for(i=0;i < uniqueChars1.length;i++){
            html += "<tr>";
            html += "<td style=\"font-size:12px;padding-top:10px;\"><b>"+ uniqueChars1[i] +"</b><input type=\"hidden\" class=\"non_trust_funds1\" value='"+uniqueChars1[i]+"'></td>";
            html += "<td style=\"padding-left:15px;padding-top:10px;\">";
            html += "<input type=\"number\" id='"+uniqueChars1[i]+"' class=\"amt\" name=\"amt\" placeholder=\"\" onfocus=\"GetTotal3()\" onkeydown=\"GetTotal3()\"  onkeyup=\"GetTotal3()\">";
            html += "</td></tr>";
        }

        html += "</form><tbody></table>";
        html += "</div>";

        html += "<table><tr>";
        html += "<td colspan=\"2\"><h2 style=\"text-align: center;font-size:14px;padding-top:10px;padding-bottom:10px;\">Special Trust Funds</h2></td>";
        html += "</tr><tr><td colspan=\"2\" style=\"text-align:center\"><hr></tr>";
        html += "<tr><td style=\"border-bottom:2px solid black;padding-top:7px;\"><b>Contribution Type</b></td>";
        html += "<td style=\"border-bottom:2px solid black;text-align:right;padding-top:7px;\"><b>Amount Offered</b></td></tr>";

        for (i=0;i < departments2.length; i++){
            departments2[i] = departments2[i].replace('"','');
            departments2[i] = departments2[i].replace('"','');
            departments2[i] = departments2[i].split("::");
            accounts2.push(departments2[i][0]);
        }

        let uniqueChars2 = [...new Set(accounts2)];

        for(i=0;i < uniqueChars2.length;i++){
            html += "<tr>";
            html += "<td style=\"font-size:12px;padding-top:10px;\"><b>"+ uniqueChars2[i] +"</b><input type=\"hidden\" class=\"special_trust_funds1\" value='"+uniqueChars2[i]+"'></td>";
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