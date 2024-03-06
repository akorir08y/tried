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
    var amts =  document.getElementsByName("amt1");


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
    var amts =  document.getElementsByName("amt2");


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
    var sum1 = 0;
    var amts =  document.getElementsByName("amt3");
    var amts1 =  document.getElementsByName("amt4");


    for (let index = 0; index < amts.length; index++){
        var amt = amts[index].value;
        sum = +(sum) +  +(amt) ;
    }

    for (let index = 0; index < amts1.length; index++){
        var amt1 = amts1[index].value;
        sum1 = +(sum1) +  +(amt1) ;
    }

    var sum2 = sum + sum1;
    document.getElementById("FTotal3").value = sum2;
}

function myFunction() {
  var home_church = document.getElementById("home_church");
  var host_church = document.getElementById("host_church");
  var member_giving = document.getElementById("member_giving");
  var home_church_view = document.getElementById("home_church_self");
  var home_church_others = document.getElementById("home_church_others");
  var self = document.getElementById("self");
  var others = document.getElementById("others");
  var phone = document.getElementById("personal_no").value;

  var text = document.getElementById("church_code_section_self");
  var text1 = document.getElementById("church_code_section_others");
  //var member_giving = document.getElementById("start_section");
  var profile_data = {
      phone_number: phone
  };

  if (home_church.checked == true && self.checked == true){
      $.post(hosted_url + "/cfms/auth/check-name",profile_data ,function(data, status){
          var statement = "Is "+ data.payload.church_name +" Your home church ?";
          console.log(data.payload);
          if(confirm(statement) == true){
            member_giving.style.display = "none";
            home_church_view.style.display = "block";
            var member_name = data.payload.member_name;
            var church_name = data.payload.church_name;
            document.getElementById("church_name").value = church_name;
            document.getElementById("member_name").value = member_name;

          }else{
             $(".responseDiv").show();
             $(".responseDiv").html("You can set your home church in the Transfer Church Section");
             $(".responseDiv").fadeOut(3000);
          }
	  });
  }else if(home_church.checked == true && others.checked == true){
      $.post(hosted_url + "/cfms/auth/check-name",profile_data ,function(data, status){
                var statement = "Is "+ data.payload.church_name +" Your home church ?";
                if(confirm(statement) == true){
                  member_giving.style.display = "none";
                  home_church_others.style.display = "block";
                  document.getElementById("church_name_home").value = data.payload.church_name;
                }else{
                   $(".responseDiv").show();
                   $(".responseDiv").html("You can set your home church in the Transfer Church Section");
                   $(".responseDiv").fadeOut(3000);
                }
      });
  }else if(host_church.checked == true && self.checked == true){
	  text.style.display = "block";
	  member_giving.style.display = "none";
  }else{
   	  text1.style.display = "block";
   	  member_giving.style.display = "none";
  }

}


function otherMember(){
	var other_member = document.getElementById("other_member");
	var identify_by = document.getElementById("identify_member_by");
	var identify_only = document.getElementById("identify_member_only");
	var associated_phone_number = document.getElementById("associated_phone_number");
	var identifier_option = document.getElementById("identifier-option");
	var identifier_option1 = document.getElementById("identifier-option1");
	var identifier_option_head = document.getElementById("identifier-option-head");
	var cfms_id = document.getElementById("cfms_id_input");

	if(other_member.checked == true){
		associated_phone_number.style.display = "none";
		identify_only.style.display = "block";
		identifier_option_head.style.display = "block";
        identifier_option.style.display = "block";
        identifier_option1.style.display = "block";
	}else{
		identifier_option_head.style.display = "none";
		identify_only.style.display = "block";
		identifier_option.style.display = "none";
		identifier_option1.style.display = "none";
		associated_phone_number.style.display = "block";
		cfms_id.style.display = "none";
	}
}


function otherMember1(){
	var other_member = document.getElementById("other_member1");
	var identify_by = document.getElementById("identify_by1");
	var identify_only = document.getElementById("identify_member_only1");
	var associated_phone_number = document.getElementById("associated_phone_number1");
	var identifier_option = document.getElementById("phone_number_div");
	var identifier_option1 = document.getElementById("cfms_member_div");
	var cfms_id = document.getElementById("cfms_id_input1");

	if(other_member.checked == true){
		associated_phone_number.style.display = "none";
		identify_only.style.display = "block";
        identifier_option.style.display = "block";
        identifier_option1.style.display = "block";
        identify_by.style.display = "block";
	}else{
		identify_by.style.display = "none";
		identify_only.style.display = "block";
		identifier_option.style.display = "none";
		identifier_option1.style.display = "none";
		identify_by.style.display = "none";
		associated_phone_number.style.display = "block";
		cfms_id.style.display = "none";
	}
}


function submitHomeOthers(){
    var phone = document.getElementById("phone1").value;
    var cfms_member_id = document.getElementById("cfms_id_member").value;
    var home_church_others = document.getElementById("home_church_others");
    var home_church_self = document.getElementById("home_church_self");

    var profile_data = {
        phone_number: phone
    };

    var profile_data1 = {
        cfms_member_id: cfms_member_id
    };

    if (phone != ""){
        $.post(hosted_url + "/cfms/auth/check-phone",profile_data ,function(data, status){
              var statement = "Is "+ data.payload.member_name +" the Church Member you wish to contribute for?";
              if(confirm(statement) == true){
                    home_church_others.style.display = "none";
                    home_church_self.style.display = "block";
                    document.getElementById("church_name").value = data.payload.church_name;
                    document.getElementById("member_name").value = data.payload.member_name;
              }else{
                    $(".responseDiv").show();
                    $(".responseDiv").html("The Number Entered is not Associated with any Church Member");
                    $(".responseDiv").fadeOut(3000);
              }
        });
    }else if(cfms_member_id != ""){
        $.post(hosted_url + "/cfms/auth/check-member-id",profile_data1   ,function(data, status){
              var statement = "Is "+ data.payload.member_name +" the Church Member you wish to contribute for?";
              if(confirm(statement) == true){
                    home_church_others.style.display = "none";
                    home_church_self.style.display = "block";
                    document.getElementById("church_name").value = data.payload.church_name;
                    document.getElementById("member_name").value = data.payload.member_name;
              }else{
                    $(".responseDiv").show();
                    $(".responseDiv").html("Member Details were not found");
                    $(".responseDiv").fadeOut(3000);
              }
        });
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


function getTrustFundAccounts(data){
	document.getElementById("home_church_self").style.display = "none";
	var phone = document.getElementById("personal_no").value;
	var phone1 = document.getElementById("personal_no2").value;
	var self = document.getElementById("self");
	var church_name = new String("");
	var member_name = new String("");
	if(self.checked == true){
        church_name = document.getElementById("church_name").value;
        member_name = document.getElementById("member_name").value;
    }else{
        church_name = document.getElementById("church_name").value;
        member_name = document.getElementById("member_name").value;
    }

	    console.log(data);

	    var trust_funds = new String("");
	    var trust_funds = "[\"Conference Development Account::426234\",\"Thirteenth Sabbath Account::191295\",";
	    trust_funds += "\"Tithe Account::127989\",\"Camp Meeting Account::127991\",\"Tithe Account::13822\",\"Camp Meeting Account::13824\",";
	    trust_funds += "\"Combined Offerings Account::13823\",\"Combined Offerings Account::127990\"]";

        // Trust Fund Accounts
        var request = trust_funds;
        var request1 =request.slice(1,request.length - 1);
        const departments = request1.split(',');
        var html = new String("");
        var accounts = [];


        html += "<div class=\"cardHeader\">";
        html += "<h2 style=\"text-align:center;margin-top:10px;margin-bottom:10px\">Giving Offerings</h2></div>";
        html += "<div class=\"responseDiv\" style=\"display:none;\"></div><br><hr>";
        html += "<table><form id=\"trust_fund_form\" name=\"trust_funds_form\"><tr><td><label class=\"label_input\"><b> To: </b></label></td>";
        html += "<td><input type=\"text\" class=\"login_input\" id=\"church_name_trust\" name=\"church_name_trust\" value='"+church_name+"' readonly></td></tr>";
        html += "<tr><td><label class=\"label_input\"><b> For: </b></label></td>";
        html += "<td><input type=\"text\" id=\"member_name_trust\" name=\"churchCode\" value='"+member_name+"'  readonly></td></tr>";
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
            html += "<td style=\"font-size:12px;padding-top:10px;\"><b>"+ uniqueChars[x] +"</b><input type=\"hidden\" class=\"trust_funds\" value='"+uniqueChars[x]+"'></td>";
            html += "<td style=\"padding-left:15px;padding-top:10px;\">";
            html += "<input type=\"number\" id='"+uniqueChars[x]+"' class=\"amt\" name=\"amt\" placeholder=\"\" onfocus=\"GetTotal()\" onkeydown=\"GetTotal()\"  onkeyup=\"GetTotal()\">";
            html += "</td></tr>";
        }

        html += "<tr><td style=\"font-size:12px;\"><b>Total</b></td>";
        html += "<td style=\"padding-left:15px;padding-top:10px;\"><input type=\"number\" class=\"login_input\" id=\"FTotal\" name=\"churchCode\" placeholder=\"\" readonly></td></tr>";
        html += "<tr><td><button type=\"button\" class=\"btn\" onclick=\"getBackToFunds()\">Back</button><button type=\"button\" class=\"btn\" onclick=\"getPaymentDiv()\" id=\"payment_button\">Confirm</button></td></tr>";
        html += "<tbody></form></table>";

        $("#trust_funds_div").html(html);


}


function getNonTrustFundAccounts(data){
	var phone = document.getElementById("personal_no").value;
	document.getElementById("home_church_self").style.display = "none";
    var self = document.getElementById("self");
    var church_name = new String("");
    var member_name = new String("");
    if(self.checked == true){
        church_name = document.getElementById("church_name").value;
        member_name = document.getElementById("member_name").value;
    }else{
        church_name = document.getElementById("church_name").value;
        member_name = document.getElementById("member_name").value;
    }


        // Trust Fund Accounts
    var request = data.departmentalAccounts;
    var request1 = request.slice(1,request.length - 1);
    const departments = request1.split(',');
    var html = new String("");
    var accounts = [];

        html += "<div class=\"cardHeader\">";
        html += "<h2 style=\"text-align:center;margin-top:10px;margin-bottom:10px\">Giving Offerings</h2></div>";
        html += "<div class=\"responseDiv\" style=\"display:none;\"></div><br><hr>";
        html += "<table><form id=\"non_trust_fund_form\"><tr><td><label class=\"label_input\"><b> To: </b></label></td>";
        html += "<td><input type=\"text\" class=\"login_input\" id=\"church_name_non_trust\" name=\"churchName3\" value='"+church_name+"' readonly></td></tr>";
        html += "<tr><td><label class=\"label_input\"><b> For: </b></label></td>";
        html += "<td><input type=\"text\" id=\"member_name_non_trust\" name=\"churchCode\" value='"+member_name+"' readonly></td></tr>";
        html += "</tr><tr>";
        html += "<td colspan=\"2\"><h2 style=\"text-align: center;font-size:14px;padding-top:10px;padding-bottom:10px;\">Non Trust Funds</h2></td>";
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
        for(i=0;i < uniqueChars.length;i++){
            html += "<tr>";
            html += "<td style=\"font-size:12px;padding-top:10px;\"><b>"+ uniqueChars[i] +"</b><input type=\"hidden\" class=\"non_trust_funds\" ></td>";
            html += "<td style=\"padding-left:15px;padding-top:10px;\">";
            html += "<input type=\"number\" id='"+uniqueChars[i]+"' class=\"amt1\" name=\"amt1\" placeholder=\"\" onfocus=\"GetTotal1()\" onkeydown=\"GetTotal1()\"  onkeyup=\"GetTotal1()\">";
            html += "</td></tr>";
        }
        html += "<tr><td style=\"font-size:12px;\"><b>Total</b></td>";
        html += "<td style=\"padding-left:15px;padding-top:10px;\"><input type=\"number\" class=\"login_input\" id=\"FTotal1\" name=\"churchCode\" placeholder=\"\" readonly></td></tr>";
        html += "<tr><td><button type=\"button\" class=\"btn\" onclick=\"getBackToFunds()\">Back</button><button type=\"button\" class=\"btn\" onclick=\"getPaymentDiv1()\" id=\"payment_button1\">Confirm</button></td></tr>";
        html += "<tbody></form></table>";

        $("#trust_funds_div").html(html);
}



function getSpecialTrustFundAccounts(data){
	var phone = document.getElementById("personal_no").value;
	document.getElementById("home_church_self").style.display = "none";
    var self = document.getElementById("self");
    var church_name = new String("");
    var member_name = new String("");
    if(self.checked == true){
        church_name = document.getElementById("church_name").value;
        member_name = document.getElementById("member_name").value;
    }else{
        church_name = document.getElementById("church_name").value;
        member_name = document.getElementById("member_name").value;
    }

    console.log(data);
        // Trust Fund Accounts
        var request = data.specialTrustFundAccounts;
        var request1 = request.slice(1,request.length - 1);
        const departments = request1.split(',');
        var html = new String("");
        var accounts = [];

        html += "<div class=\"cardHeader\">";
        html += "<h2 style=\"text-align:center;margin-top:10px;margin-bottom:10px\">Giving Offerings</h2></div>";
        html += "<div class=\"responseDiv\" style=\"display:none;\"></div><br><hr>";
        html += "<table><form id=\"special_trust_fund_form\"><tr><td><label class=\"label_input\"><b> To: </b></label></td>";
        html += "<td><input type=\"text\" class=\"login_input\" id=\"church_name_special_trust\" name=\"churchName3\" value='"+church_name+"' readonly></td></tr>";
        html += "<tr><td><label class=\"label_input\"><b> For: </b></label></td>";
        html += "<td><input type=\"text\" id=\"member_name_special_trust\" name=\"churchCode\" value='"+member_name+"' readonly></td></tr>";
        html += "</tr><tr>";
        html += "<td colspan=\"2\"><h2 style=\"text-align: center;font-size:14px;padding-top:10px;padding-bottom:10px;\">Special Trust Funds</h2></td>";
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
        for(i=0;i < uniqueChars.length;i++){
            html += "<tr>";
            html += "<td style=\"font-size:12px;padding-top:10px;\"><b>"+ uniqueChars[i] +"</b><input type=\"hidden\" class=\"special_trust_funds\" value='"+departments[i]+"'></td>";
            html += "<td style=\"padding-left:15px;padding-top:10px;\">";
            html += "<input type=\"number\" id='"+uniqueChars[i]+"' class=\"amt2\" name=\"amt2\" placeholder=\"\" onfocus=\"GetTotal2()\" onkeydown=\"GetTotal2()\"  onkeyup=\"GetTotal2()\">";
            html += "</td></tr>";
        }
        html += "<tr><td style=\"font-size:12px;\"><b>Total</b></td>";
        html += "<td style=\"padding-left:15px;padding-top:10px;\"><input type=\"number\" class=\"login_input\" id=\"FTotal2\" name=\"churchCode\" placeholder=\"\" readonly></td></tr>";
        html += "<tr><td><button type=\"button\" class=\"btn\" onclick=\"getBackToFunds()\">Back</button><button type=\"button\" class=\"btn\" onclick=\"getPaymentDiv2()\" id=\"payment_button2\">Confirm</button></td></tr>";
        html += "</form><tbody></table>";

        $("#trust_funds_div").html(html);

}


function getBothFundAccounts(data){
    var phone = document.getElementById("personal_no").value;
	document.getElementById("home_church_self").style.display = "none";
    var self = document.getElementById("self");
	var church_name = new String("");
	var member_name = new String("");
	if(self.checked == true){
        church_name = document.getElementById("church_name").value;
        member_name = document.getElementById("member_name").value;
    }else{
        church_name = document.getElementById("church_name").value;
        member_name = document.getElementById("member_name").value;
    }

    var trust_funds = new String("");
    var trust_funds = "[\"Conference Development Account::426234\",\"Thirteenth Sabbath Account::191295\",";
    trust_funds += "\"Tithe Account::127989\",\"Camp Meeting Account::127991\",\"Tithe Account::13822\",\"Camp Meeting Account::13824\",";
    trust_funds += "\"Combined Offerings Account::13823\",\"Combined Offerings Account::127990\"]";

        // Trust Fund Accounts
        var request = trust_funds;
        var request1 = data.departmentalAccounts;
        var request2 = request.slice(1,request.length - 1);
        var request3 = request1.slice(1,request1.length - 1);
        const departments = request2.split(',');
        const departments1 = request3.split(',');
        var html = new String("");
        var accounts = [];
        var accounts2 = [];

        html += "<div class=\"cardHeader\">";
        html += "<h2 style=\"text-align:center;margin-top:10px;margin-bottom:10px\">Giving Offerings</h2></div>";
        html += "<div class=\"responseDiv\" style=\"display:none;\"></div><br><hr>";
        html += "<table><form id=\"both_funds_form\"><tr><td><label class=\"label_input\"><b> To: </b></label></td>";
        html += "<td><input type=\"text\" class=\"login_input\" id=\"church_name_both_funds\" name=\"churchName3\" value='"+church_name+"' readonly></td></tr>";
        html += "<tr><td><label class=\"label_input\"><b> For: </b></label></td>";
        html += "<td><input type=\"text\" id=\"member_name_both_funds\" name=\"churchCode\" value='"+member_name+"' readonly></td></tr>";
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
        for(i=0;i < uniqueChars.length;i++){
            html += "<tr>";
            html += "<td style=\"font-size:12px;padding-top:10px;\"><b>"+ uniqueChars[i] +"</b><input type=\"hidden\" class=\"trust_funds1\" value='"+departments[i][0]+"'></td>";
            html += "<td style=\"padding-left:15px;padding-top:10px;\">";
            html += "<input type=\"number\" id='"+uniqueChars[i]+"' class=\"amt3\" name=\"amt3\" placeholder=\"\" onfocus=\"GetTotal3()\" onkeydown=\"GetTotal3()\"  onkeyup=\"GetTotal3()\">";
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
               departments1[i] = departments1[i].split("::");
               accounts2.push(departments1[i][0]);
        }

        let uniqueChars2 = [...new Set(accounts2)];
        for(i=0;i < uniqueChars2.length;i++){
            html += "<tr>";
            html += "<td style=\"font-size:12px;padding-top:10px;\"><b>"+ uniqueChars2[i] +"</b><input type=\"hidden\" name=\"non_trust_funds1\" value='"+departments1[i]+"'></td>";
            html += "<td style=\"padding-left:15px;padding-top:10px;\">";
            html += "<input type=\"number\" id='"+uniqueChars2[i]+"' class=\"amt4\" name=\"amt4\" placeholder=\"\" onfocus=\"GetTotal3()\" onkeydown=\"GetTotal3()\"  onkeyup=\"GetTotal3()\">";
            html += "</td></tr>";
        }
        html += "<tr><td style=\"font-size:12px;\"><b>Total</b></td>";
        html += "<td style=\"padding-left:15px;padding-top:10px;\"><input type=\"number\" class=\"login_input\" id=\"FTotal3\" name=\"churchCode\" placeholder=\"\" readonly></td></tr>";
        html += "<tr><td><button type=\"button\" class=\"btn\" onclick=\"getBackToFunds()\">Back</button><button type=\"button\" class=\"btn\" onclick=\"getPaymentDiv3()\" id=\"payment_button3\">Confirm</button></td></tr>";
        html += "</form><tbody></table>";

        $("#trust_funds_div").html(html);
}


function saveTrustFundSummary(){
    var amt = document.getElementsByClassName("amt");
    var church_code_mine = document.getElementById("church_code_mine").value;
    var phone_number = document.getElementById("personal_no").value;
    var church_code_host = document.getElementById("church_code_host").value;
    var church_code_others = document.getElementById("church_code_others").value;
    var total = document.getElementById("FTotal").value;
	var funds1 = [];
	var amt1 = [];
	var church_code = new String("");
	var contribute = new String("");
	var self = document.getElementById("self");
	var others = document.getElementById("others");

    if(self.checked == true){
        church_code = church_code_mine;
        contribute = "Self";
    }else if(others.checked == true){
        contribute = "Others";

        if (church_code_host == ""){
            church_code = church_code_others;
        }else{
            church_code = church_code_host;
        }
    }

	for (var i = 0; i < amt.length; i++) {
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

    var funds = {
        phone_number: phone_number,
        amount : total,
        church_code: church_code,
        contribute: contribute,
        trust_funds: funds1,
        fund_amount: amt1
    }

    console.log("Funds Object: " + funds);
    console.log("Funds Object String: " + JSON.stringify(funds));

	$.post(hosted_url + "/cfms/auth/member_receive_funds",funds ,function(data, status){
        $(".responseDiv").show();
        $(".responseDiv").html("Trust Fund Successfully Saved");
        $(".responseDiv").fadeOut(3000);
	});
}


function saveNonTrustFundSummary(){
    var amt = document.getElementsByClassName("amt1");
    var church_code_mine = document.getElementById("church_code_mine").value;
    var church_code_host = document.getElementById("church_code_host").value;
    var church_code_others = document.getElementById("church_code_others").value;
    var total = document.getElementById("FTotal1").value;
	var funds1 = [];
	var amt1 = [];
	var church_code = new String("");
	var contribute = new String("");
	var self = document.getElementById("self");
	var others = document.getElementById("others");

    if(self.checked == true){
        church_code = church_code_mine;
        contribute = "Self";
    }else if(others.checked == true){
        contribute = "Others";

        if (church_code_host == ""){
            church_code = church_code_others;
        }else{
            church_code = church_code_host;
        }
    }

	for (var i = 0; i < amt.length; i++) {
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

    var funds = {
        phone_number: phone_number,
        amount : total,
        church_code: church_code,
        contribute: contribute,
        non_trust_funds: funds1,
        fund_amount1: amt1
    }

	$.post(hosted_url + "/cfms/auth/member_receive_funds",funds ,function(data, status){
        $(".responseDiv").show();
        $(".responseDiv").html("Non Trust Fund Successfully Saved");
        $(".responseDiv").fadeOut(3000);
	});
}

function saveSpecialTrustFundSummary(){
    var funds = document.getElementsByClassName("special_trust_funds");
    var amt = document.getElementsByClassName("amt2");
    var church_code_mine = document.getElementById("church_code_mine").value;
    var church_code_host = document.getElementById("church_code_host").value;
    var church_code_others = document.getElementById("church_code_others").value;
    var total = document.getElementById("FTotal2").value;
	var funds1 = [];
	var amt1 = [];
	var church_code = new String("");
	var contribute = new String("");
	var self = document.getElementById("self");
	var others = document.getElementById("others");

    if(self.checked == true){
        church_code = church_code_mine;
        contribute = "Self";
    }else if(others.checked == true){
        contribute = "Others";

        if (church_code_host == ""){
            church_code = church_code_others;
        }else{
            church_code = church_code_host;
        }
    }

	for (var i = 0; i < amt.length; i++) {
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

    var funds = {
        phone_number: phone_number,
        amount : total,
        church_code: church_code,
        contribute: contribute,
        non_trust_funds: funds1,
        fund_amount1: amt1
    }

	$.post(hosted_url + "/cfms/auth/member_receive_funds",funds ,function(data, status){
        $(".responseDiv").show();
        $(".responseDiv").html("Special Trust Fund Successfully Saved");
        $(".responseDiv").fadeOut(3000);
	});
}

function saveBothFundsSummary(){
    var amt = document.getElementsByClassName("amt3");
    var amt1 = document.getElementsByClassName("amt4");
    var church_code_mine = document.getElementById("church_code_mine").value;
    var church_code_host = document.getElementById("church_code_host").value;
    var church_code_others = document.getElementById("church_code_others").value;
    var total = document.getElementById("FTotal3").value;
	var funds1 = [];
	var funds2 = [];
	var amt2 = [];
	var church_code = new String("");
	var contribute = new String("");
	var self = document.getElementById("self");
	var others = document.getElementById("others");

    if(self.checked == true){
        church_code = church_code_mine;
        contribute = "Self";
    }else if(others.checked == true){
        contribute = "Others";

        if (church_code_host == ""){
            church_code = church_code_others;
        }else{
            church_code = church_code_host;
        }
    }

	for (var i = 0; i < amt.length; i++) {
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


    for (var i = 0; i < amt1.length; i++) {
    	if(amt1[i].value != ''){
    		var amount_id = amt1[i].id;
    		amount_id = amount_id.replace(' ','');
    		amount_id = amount_id.replace(' ','');
    		console.log("The Desired Id: "+ amount_id);
    		funds2.push(amount_id);
    		console.log("The Desired Value: "+amt1[i].value);
    		amt2.push(amt[i].value);
    	}
    }

    var funds = {
        phone_number: phone_number,
        amount : total,
        church_code: church_code,
        contribute: contribute,
        trust_funds: funds1,
        non_trust_funds: funds2,
        fund_amount1: amt1,
        fund_amount2: amt2
    }

	$.post(hosted_url + "/cfms/auth/member_receive_funds",funds ,function(data, status){
        $(".responseDiv").show();
        $(".responseDiv").html("Both Trust Funds Successfully Saved");
        $(".responseDiv").fadeOut(3000);
	});
}


function getFundAccount(){

	var home_church_self = document.getElementById("home_church_self");
	var trust_funds = document.getElementById("trust_funds");
	var non_trust_funds = document.getElementById("non_trust_funds");
	var both_funds = document.getElementById("both_funds");
	var special_trust_funds = document.getElementById("special_trust_funds");
	var phone = document.getElementById("personal_no").value;

    var profile_data = {
        phone_number: phone
    };

    $.post(hosted_url + "/cfms/auth/check-account",profile_data ,function(data, status){
        if(trust_funds.checked == true){
            getTrustFundAccounts(data);
            home_church_self.style.display = "none";
        }else if(non_trust_funds.checked == true){
            getNonTrustFundAccounts(data);
            home_church_self.style.display = "none";
        }else if(both_funds.checked == true){
            getBothFundAccounts(data);
            home_church_self.style.display = "none";
        }else if(special_trust_funds.checked == true){
            getSpecialTrustFundAccounts(data);
            home_church_self.style.display = "none";
        }
    });

}


function getTrustFunds(total){

    var html = new String("");
    html += "<table><tr><td colspan=\"4\" style=\"padding-top:10px;\"><b>Initiate Payment From:</b></td></tr>";
    html += "<tr><td colspan=\"2\">";
    html += "<input class=\"form-check-input\" type=\"radio\" name=\"flexRadioDefault4\" id=\"registered_number\" onclick=\"getPaymentNumber()\" checked>";
    html += "<label class=\"form-check-label\" for=\"visiting_member\">My Registered Number</label></td></tr>";
    html += "<tr><td style=\"text-align:left;\">";
    html += "<input class=\"form-check-input\" type=\"radio\" name=\"flexRadioDefault4\" id=\"my_other_number\" onclick=\"getPaymentNumber()\">";
    html += "<label class=\"form-check-label\" for=\"guest\">My Other Number</label></td></tr>";
    html += "<tr><td><input class=\"form-check-input\" type=\"radio\" name=\"flexRadioDefault4\" id=\"other_number\" onclick=\"getPaymentNumber()\">";
    html += "<label class=\"form-check-label\" for=\"anonymous\">Other Number</label></td></tr>";
    html += "<tr><td><br></td></tr>";
    html += "<tr style=\"display:none;width:100%;\" id=\"alternative_number\">";
    html += "<td width=\"50%\"><label class=\"label_input\"><b> Phone Number: </b></label></td>";
    html += "<td width=\"50%\"><input type=\"text\" id=\"other_number2\" name=\"other_number2\" placeholder=\"+254\"></td>";
    html += "</tr><tr><td><br></td></tr>";
    html += "<tr><td><br><p id=\"payment_line\"></p><input type=\"hidden\" id=\"total_value\" name=\"total_value\" value='"+total+"'></td></tr>";
    html += "<tr><td><button type=\"button\" class=\"btn\" onclick=\"saveTrustFundSummary()\">Confirm</button></td></tr>"
    html += "</table>";

    $("#payment_div").show();
    $("#payment_div").html(html);
}


function getNonTrustFunds(total){

    var html = new String("");
    html += "<table><tr><td colspan=\"4\" style=\"padding-top:10px;\"><b>Initiate Payment From:</b></td></tr>";
    html += "<tr><td colspan=\"2\">";
    html += "<input class=\"form-check-input\" type=\"radio\" name=\"flexRadioDefault4\" id=\"registered_number\" onclick=\"getPaymentNumber()\" checked>";
    html += "<label class=\"form-check-label\" for=\"visiting_member\">My Registered Number</label></td></tr>";
    html += "<tr><td style=\"text-align:left;\">";
    html += "<input class=\"form-check-input\" type=\"radio\" name=\"flexRadioDefault4\" id=\"my_other_number\" onclick=\"getPaymentNumber()\">";
    html += "<label class=\"form-check-label\" for=\"guest\">My Other Number</label></td></tr>";
    html += "<tr><td><input class=\"form-check-input\" type=\"radio\" name=\"flexRadioDefault4\" id=\"other_number\" onclick=\"getPaymentNumber()\">";
    html += "<label class=\"form-check-label\" for=\"anonymous\">Other Number</label></td></tr>";
    html += "<tr><td><br></td></tr>";
    html += "<tr style=\"display:none;width:100%;\" id=\"alternative_number\">";
    html += "<td width=\"50%\"><label class=\"label_input\"><b> Phone Number: </b></label></td>";
    html += "<td width=\"50%\"><input type=\"text\" id=\"other_number2\" name=\"other_number2\" placeholder=\"+254\"></td>";
    html += "</tr><tr><td><br></td></tr>";
    html += "<tr><td><br><p id=\"payment_line\"></p><input type=\"hidden\" id=\"total_value\" name=\"total_value\" value='"+total+"'></td></tr>";
    html += "<tr><td><button type=\"button\" class=\"btn\" onclick=\"saveNonTrustFundSummary()\">Confirm</button></td></tr>"
    html += "</table>";

    $("#payment_div").show();
    $("#payment_div").html(html);
}


function getSpecialTrustFunds(total){

    var html = new String("");
    html += "<table><tr><td colspan=\"4\" style=\"padding-top:10px;\"><b>Initiate Payment From:</b></td></tr>";
    html += "<tr><td colspan=\"2\">";
    html += "<input class=\"form-check-input\" type=\"radio\" name=\"flexRadioDefault4\" id=\"registered_number\" onclick=\"getPaymentNumber()\" checked>";
    html += "<label class=\"form-check-label\" for=\"visiting_member\">My Registered Number</label></td></tr>";
    html += "<tr><td style=\"text-align:left;\">";
    html += "<input class=\"form-check-input\" type=\"radio\" name=\"flexRadioDefault4\" id=\"my_other_number\" onclick=\"getPaymentNumber()\">";
    html += "<label class=\"form-check-label\" for=\"guest\">My Other Number</label></td></tr>";
    html += "<tr><td><input class=\"form-check-input\" type=\"radio\" name=\"flexRadioDefault4\" id=\"other_number\" onclick=\"getPaymentNumber()\">";
    html += "<label class=\"form-check-label\" for=\"anonymous\">Other Number</label></td></tr>";
    html += "<tr><td><br></td></tr>";
    html += "<tr style=\"display:none;width:100%;\" id=\"alternative_number\">";
    html += "<td width=\"50%\"><label class=\"label_input\"><b> Phone Number: </b></label></td>";
    html += "<td width=\"50%\"><input type=\"text\" id=\"other_number2\" name=\"other_number2\" placeholder=\"+254\"></td>";
    html += "</tr><tr><td><br></td></tr>";
    html += "<tr><td><br><p id=\"payment_line\"></p><input type=\"hidden\" id=\"total_value\" name=\"total_value\" value='"+total+"'></td></tr>";
    html += "<tr><td><button type=\"button\" class=\"btn\" onclick=\"saveSpecialTrustFundSummary()\">Confirm</button></td></tr>"
    html += "</table>";

    $("#payment_div").show();
    $("#payment_div").html(html);
}


function getBothTrustFunds(total){

    var html = new String("");
    html += "<table><tr><td colspan=\"4\" style=\"padding-top:10px;\"><b>Initiate Payment From:</b></td></tr>";
    html += "<tr><td colspan=\"2\">";
    html += "<input class=\"form-check-input\" type=\"radio\" name=\"flexRadioDefault4\" id=\"registered_number\" onclick=\"getPaymentNumber()\" checked>";
    html += "<label class=\"form-check-label\" for=\"visiting_member\">My Registered Number</label></td></tr>";
    html += "<tr><td style=\"text-align:left;\">";
    html += "<input class=\"form-check-input\" type=\"radio\" name=\"flexRadioDefault4\" id=\"my_other_number\" onclick=\"getPaymentNumber()\">";
    html += "<label class=\"form-check-label\" for=\"guest\">My Other Number</label></td></tr>";
    html += "<tr><td><input class=\"form-check-input\" type=\"radio\" name=\"flexRadioDefault4\" id=\"other_number\" onclick=\"getPaymentNumber()\">";
    html += "<label class=\"form-check-label\" for=\"anonymous\">Other Number</label></td></tr>";
    html += "<tr><td><br></td></tr>";
    html += "<tr style=\"display:none;width:100%;\" id=\"alternative_number\">";
    html += "<td width=\"50%\"><label class=\"label_input\"><b> Phone Number: </b></label></td>";
    html += "<td width=\"50%\"><input type=\"text\" id=\"other_number2\" name=\"other_number2\" placeholder=\"+254\"></td>";
    html += "</tr><tr><td><br></td></tr>";
    html += "<tr><td><br><p id=\"payment_line\"></p><input type=\"hidden\" id=\"total_value\" name=\"total_value\" value='"+total+"'></td></tr>";
    html += "<tr><td><button type=\"button\" class=\"btn\" onclick=\"saveBothFundsSummary()\">Confirm</button></td></tr>"
    html += "</table>";

    $("#payment_div").show();
    $("#payment_div").html(html);
}


function getPaymentDiv(){
	var onlyInputs = document.querySelectorAll('#trust_funds_div input');
	var phone = document.getElementById('personal_no').value;
	var phone1 = document.getElementById('personal_no2').value

    onlyInputs.forEach(input => {
      	input.readOnly = true;
    });


	// var payment_div = document.getElementById("payment_div");
	var total = new String("");
	total = document.getElementById("FTotal").value;

	document.getElementById("payment_button").style.display = "none";
	getTrustFunds(total);
	var payment_info = "Ensure "+ total +"/= has been deposited on the mobile money account for "+phone;
	document.getElementById("payment_line").innerHTML = payment_info;
}


function getPaymentDiv1(){
	const onlyInputs = document.querySelectorAll('#trust_funds_div input');
	var phone = document.getElementById("personal_no");

    onlyInputs.forEach(input => {
      	input.readOnly = true;
    });

	var payment_div = document.getElementById("payment_div");
	var total = document.getElementById("FTotal1").value;

	document.getElementById("payment_button1").style.display = "none";
	getNonTrustFunds(total);
	payment_div.style.display = "block";
	document.getElementById("payment_line").innerHTML =
	"Ensure "+ total +"/= has been deposited on the mobile money account for "+phone;
}

function getPaymentDiv2(){
	const onlyInputs = document.querySelectorAll('#trust_funds_div input');
	var phone = document.getElementById("personal_no").value;

    onlyInputs.forEach(input => {
      	input.readOnly = true;
    });

	var payment_div = document.getElementById("payment_div");
	var total = document.getElementById("FTotal2").value;


	document.getElementById("payment_button2").style.display = "none";
	getSpecialTrustFunds(total);
	payment_div.style.display = "block";
	document.getElementById("payment_line").innerHTML =
	"Ensure "+ total +"/= has been deposited on the mobile money account for "+phone;
}

function getPaymentDiv3(){
	const onlyInputs = document.querySelectorAll('#trust_funds_div input');
	var phone = document.getElementById("personal_no").value;

    onlyInputs.forEach(input => {
      	input.readOnly = true;
    });

	var payment_div = document.getElementById("payment_div");
	var total = document.getElementById("FTotal3").value;

	document.getElementById("payment_button3").style.display = "none";
	getBothTrustFunds(total);
	payment_div.style.display = "block";
	document.getElementById("payment_line").innerHTML =
	"Ensure "+ total +"/= has been deposited on the mobile money account for "+phone;
}


function getChurchCode(){
    var phone = document.getElementById("personal_no").value;
    var code = document.getElementById("church_code_host").value;
    var church_code_section = document.getElementById("church_code_section_self");
    var host_church_self = document.getElementById("host_church_self");

    getMemberName();

    if (code.length == 5){
        var content = {
            phone_number: phone,
            church_code: code
        }

        $.post(hosted_url + "/cfms/auth/check-church", content ,function(data, status){
             if(data != null){
                var statement = "Is "+ data.churchName +" the host church you wish to contribute to?";
                if(confirm(statement) == true){
                    document.getElementById("church_name1").value = data.churchName;
                    church_code_section.style.display = "none";
                    host_church_self.style.display = "block";
                }
            }else{
                 $(".responseDiv").show();
                 $(".responseDiv").html("Unable to Locate the Church");
                 $(".responseDiv").fadeOut(3000);
            }
        });
    }
}

function getChurchCodeOthers(){
    var phone = document.getElementById("personal_no").value;
    var code = document.getElementById("church_code_others").value;
    var church_code_section = document.getElementById("church_code_section_others");
    var host_church_others = document.getElementById("host_church_others")

    if (code.length == 5){
        var content = {
            phone_number: phone,
            church_code: code
        }

        $.post(hosted_url + "/cfms/auth/check-church", content ,function(data, status){
            if (data != null){
                var statement = "Is "+ data.churchName +" the host church you wish to contribute to?";
                if(confirm(statement) == true){
                    document.getElementById("church_name_others").value = data.churchName;
                    church_code_section.style.display = "none";
                    host_church_others.style.display = "block";
                }
            }else{
                $(".responseDiv").show();
                $(".responseDiv").html("Unable to Locate the Church");
                $(".responseDiv").fadeOut(3000);
            }
        });
    }
}



// Get the Payment Details
function getPaymentNumber(){
	var registered = document.getElementById("registered_number");
	var my_other_number = document.getElementById("my_other_number");
	var phone = document.getElementById("personal_no").value;
	var phone2 = document.getElementById("personal_no2").value;
	var other_number = document.getElementById("other_number");
	var alternative_number = document.getElementById("alternative_number");
	var total = document.getElementById("total_value").value;


	if(registered.checked == true){
		var payment_info = "Ensure "+ total +"/= has been deposited on the mobile money account for "+phone;
		document.getElementById("payment_line").innerHTML = payment_info;
		alternative_number.style.display = "none";
	}else if(my_other_number.checked == true){
		var payment_info = "Ensure "+ total +"/= has been deposited on the mobile money account for "+phone2;
		document.getElementById("payment_line").innerHTML = payment_info;
		alternative_number.style.display = "none";
	}else if(other_number.checked == true){
		var payment_info = "Ensure "+ total +"/= has been deposited on the mobile money account for "+phone2;
		document.getElementById("payment_line").innerHTML = payment_info;
		alternative_number.style.display = "block";
	}
}


function getMemberName(){
    var phone = document.getElementById("personal_no").value;
    var profile_data = {
        phone_number: phone    }

    $.post(hosted_url + "/cfms/auth/check-phone",profile_data ,function(data, status){
        document.getElementById("member_name1").value = data.payload.member_name;
    });
}


function getOfferingPage(){
    var anonymous = document.getElementById("anonymous");
    var guest = document.getElementById("guest");
    var visiting_member = document.getElementById("visiting_member");
    var home_church_self = document.getElementById("home_church_self");
    var church_name = document.getElementById("church_name_mine").value;
    var host_church_self = document.getElementById("host_church_self");

    if (anonymous.checked == true || guest.checked == true || visiting_member.checked == true) {
        home_church_self.style.display = "block";
        host_church_self.style.display = "none";
        document.getElementById("church_name").value = church_name;
        document.getElementById("member_name").value = "Self";
    }
}


function goBackToHomePage(){
    var member_giving = document.getElementById("member_giving");
    var church_code_section_others = document.getElementById("church_code_section_others");
    var church_code_section_self = document.getElementById("church_code_section_self");
    var home_church_self = document.getElementById("home_church_self");
    var home_church_others = document.getElementById("home_church_others");
    var host_church_self = document.getElementById("host_church_self");

    home_church_others.style.display = "none";
    home_church_self.style.display = "none";
    church_code_section_self.style.display = "none";
    church_code_section_others.style.display = "none";
    home_church_self.style.display = "none";
    member_giving.style.display = "block";
}

function getBackToFunds(){
    var trust_funds = document.getElementById("trust_funds_div");
    var payment_div = document.getElementById("payment_div");
    var home_church_self = document.getElementById("home_church_self");

    trust_funds.style.display = "none";
    payment_div.style.display = "none";
    home_church_self.style.display = "block";
}