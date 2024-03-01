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

  var text = document.getElementById("church_code_section");
  //var member_giving = document.getElementById("start_section");

  if (home_church.checked == true && self.checked == true){
      $.post(hosted_url + "/cfms/auth/check-name",profile_data ,function(data, status){
          var statement = "Is "+ data.payload.church_name +" Your home church ?";
          if(confirm(statement) == true){
            member_giving.style.display = "none";
            home_church_view.style.display = "block";
            document.getElementById("church_name").value = data.payload.church_name;
            document.getElementById("member_name").value = data.payload.member_name;
            document.getElementById("church_name_trust").value = data.payload.church_name;
            document.getElementById("member_name_trust").value = data.payload.member_name;

          }else{
             $(".responseDiv").show();
             $(".responseDiv").html("You can set your home church in the Transfer Church Section");
             $(".responseDiv").fadeOut(3000);
          }
	  }
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


function submitHomeOthers(){
    var phone = document.getElementById("phone1").value;
    var cfms_member_id = document.getElementById("cfms_id_member").value;

    var profile_data = {
        phone_number: phone;
    };

    var profile_data1 = {
        cfms_member_id: cfms_member_id
    };

    if (phone != ""){
        $.post(hosted_url + "/cfms/auth/check-phone",profile_data ,function(data, status){
              var statement = "Is "+ data.payload.member_name +" the Church Member you wish to contribute for?";
              if(confirm(statement) == true){
                    member_giving.style.display = "none";
                    home_church_others.style.display = "block";
                    document.getElementById("church_name").value = data.payload.church_name;
                    document.getElementById("member_name").value = data.payload.member_name;
              }else{
                    $(".responseDiv").show();
                    $(".responseDiv").html("The Number Entered is not Associated with any Church Member");
                    $(".responseDiv").fadeOut(3000);
              }
        }
    }else if(cfms_member_id != ""){
        $.post(hosted_url + "/cfms/auth/check-member-id",profile_data ,function(data, status){
              var statement = "Is "+ data.payload.member_name +" the Church Member you wish to contribute for?";
              if(confirm(statement) == true){
                    member_giving.style.display = "none";
                    home_church_others.style.display = "block";
                    document.getElementById("church_name").value = data.payload.church_name;
                    document.getElementById("member_name").value = data.payload.member_name;
              }else{
                    $(".responseDiv").show();
                    $(".responseDiv").html("Member Details were not found");
                    $(".responseDiv").fadeOut(3000);
              }
        }
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
	var phone = document.getElementById("personal_no").value;
    var profile_data = {
        phone_number: phone
    };

	$.post(hosted_url + "/cfms/auth/check-name",profile_data ,function(data, status){

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
        html += "<td><input type=\"text\" class=\"login_input\" id=\"church_name_trust\" name=\"church_name_trust\" readonly></td></tr>";
        html += "<tr><td><label class=\"label_input\"><b> For: </b></label></td>";
        html += "<td><input type=\"text\" id=\"member_name_trust\" name=\"churchCode\"  readonly></td></tr>";
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

}


function getNonTrustFundAccounts(){
	var phone = document.getElementById("personal_no").value;
	document.getElementById("home_church_self").style.display = "none";
    var profile_data = {
        phone_number: phone
    };

    $.post(hosted_url + "/cfms/auth/check-name",profile_data ,function(data, status){

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
}



function getSpecialTrustFundAccounts(){
	var phone = document.getElementById("personal_no").value;
	document.getElementById("home_church_self").style.display = "none";
    var profile_data = {
        phone_number: phone
    };

    $.post(hosted_url + "/cfms/auth/check-name",profile_data ,function(data, status){

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
}


function getBothFundAccounts(){
    var phone = document.getElementById("personal_no").value;
	document.getElementById("home_church_self").style.display = "none";
    var profile_data = {
        phone_number: phone
    };

    $.post(hosted_url + "/cfms/auth/check-name",profile_data ,function(data, status){
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
            //departments[i] = departments[i].split("::");
            html += "<tr>";
            html += "<td style=\"font-size:12px;padding-top:10px;\"><b>"+ departments[i] +"</b><input type=\"hidden\" class=\"trust_funds1\" value='"+departments[i][0]+"'></td>";
            html += "<td style=\"padding-left:15px;padding-top:10px;\">";
            html += "<input type=\"number\" id='"+departments[i]+"' class=\"amt\" name=\"amt\" placeholder=\"\" onfocus=\"GetTotal3()\" onkeydown=\"GetTotal3()\"  onkeyup=\"GetTotal3()\">";
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
            // departments1[i] = departments1[i].split("::")
            html += "<tr>";
            html += "<td style=\"font-size:12px;padding-top:10px;\"><b>"+ departments1[i] +"</b><input type=\"hidden\" name=\"non_trust_funds1\" value='"+departments1[i]+"'></td>";
            html += "<td style=\"padding-left:15px;padding-top:10px;\">";
            html += "<input type=\"number\" id='"+departments1[i]+"' class=\"login_input\" name=\"amt\" placeholder=\"\" onfocus=\"GetTotal3()\" onkeydown=\"GetTotal3()\"  onkeyup=\"GetTotal3()\">";
            html += "</td></tr>";
        }
        html += "<tr><td style=\"font-size:12px;\"><b>Total</b></td>";
        html += "<td style=\"padding-left:15px;padding-top:10px;\"><input type=\"number\" class=\"login_input\" id=\"FTotal3\" name=\"churchCode\" placeholder=\"\" readonly></td></tr>";
        html += "<tr><td><button type=\"button\" class=\"btn\" onclick=\"getPaymentDiv3()\" id=\"payment_button3\">Confirm</button></td></tr>";
        html += "</form><tbody></table>";

        $("#trust_funds_div").html(html);
    }
}


function saveTrustFundSummary(){
    var funds = document.getElementsByClassName("trust_funds");
    var amt = document.getElementsByClassName("amt");
	var funds1 = [];
	var amt1 = [];

	var object = {};


	for (var i = 0; i < amt.length; i++) {
		if(amt[i].value != ''){
			var amount_id = amt[i].id;
			amount_id = amount_id.replace(' ','');
			amount_id = amount_id.replace(' ','');
			console.log("The Desired Id: "+ amount_id);
			console.log("The Desired Value: "+amt[i].value);
		}
    }

	/*
	$.ajax({
        type: "POST",
        url: "/unions/saveUnion",
        datatype: "json",
        data: {
            description:description,
            qty:qty,
            rate:rate,
            amt:amt,
            physical_address:addresses[1],
            phone:phones[1],
            name:name,
            total:total

        },
        success: function(response){
            console.log(response);

        },
        error: function(response){
            console.log(response);
        }

    });
    */
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
	var onlyInputs = document.querySelectorAll('#trust_funds_div input');
	var phone = document.getElementById('personal_no').value

    onlyInputs.forEach(input => {
      	input.readOnly = true;
    });


	var payment_div = document.getElementById("payment_div");
	var total = document.getElementById("FTotal").value;

	document.getElementById("payment_button").style.display = "none";

	payment_div.style.display = "block";
	var payment_info = "Ensure "+ total +"/= has been deposited on the mobile money account for "+phone;
	document.getElementById("payment_line").innerHTML = payment_info;
}


function getPaymentDiv1(){
	const onlyInputs = document.querySelectorAll('#trust_funds_div input');

    onlyInputs.forEach(input => {
      	input.readOnly = true;
    });

	var payment_div = document.getElementById("payment_div");
	var total = document.getElementById("FTotal1").value;
	$('#non_trust_fund_form :input').attr('readonly','readonly');
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
	$('#special_trust_fund_form :input').attr('readonly','readonly');
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
	$('#both_funds_form :input').attr('readonly','readonly');
	document.getElementById("payment_button3").style.display = "none";
	payment_div.style.display = "block";
	var payment_info = "Ensure "+ total +"/= has been deposited on the mobile money account for 254707981971";
	document.getElementById("payment_line").innerHTML = payment_info;
}


function getChurchCode(){
    var phone = document.getElementById("personal_no").value;
    var code = document.getElementById("church_code_host").value;

    if (code.length == 5){
        var content = {
            phone_number: phone,
            church_code: code
        }

        $.post(hosted_url + "/cfms/auth/check-church", content ,function(data, status){
            var statement = "Is "+ data.payload.church_name +" the host church you wish to contribute to?";
            if(confirm(statement) == true){
                
           }
        });
    }
}

// Get the Payment Details
function getPaymentNumber(){
	var registered = document.getElementById("registered_number");
	var my_other_number = document.getElementById("my_other_number");
	var phone = document.getElementById("personal_no");
	var phone2 = document.getElementById("personal_no2");
	var other_number = document.getElementById("other_number2");
	var total = document.getElementById("total_value").value;
	var alternative_number = document.getElementById("alternative_number");

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