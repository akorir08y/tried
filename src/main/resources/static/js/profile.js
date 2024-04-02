var hosted_url = location.origin;

function getMemberProfile(phone_number){
    var all_members = document.getElementById("all_members");
    var member_profile = document.getElementById("member_profile");

    const dated = {
        phone_number: phone_number
    };


    $.post(hosted_url + "/cfms/personnel/profile",dated ,function(data, status){
        all_members.style.display = "none";
        member_profile.style.display = "block";
        var html = new String("");

        html += "<div class=\"cardHeader\">";
        html += "<h2 style=\"text-align:center;\">Profile Information</h2>";
        html += "</div><div class=\"responseDiv\" style=\"display:none;\"></div>";
        html += "<br><hr>";
        html += "<table width=\"100%\">";
        html += "<tbody><tr>";
        html += "<td><b>Full Name</b></td>";
        html += "<td><b>Email</b></td><td><b>Phone Number</b></td></tr><tr>";
        html += "<td width=\"33%\"><input type=\"text\" id=\"fullname\" placeholder=\"Full Name\" value='"+ data.fullName +"' required></td>";
        html += "<td width=\"33%\"><input type=\"email\" id=\"email\" placeholder=\"Email\" required></td>";
        html += "<td width=\"33%\"><input type=\"text\" id=\"phone\" placeholder=\"Phone Number\" value='"+ phone_number +"' required></td>";
        html += "</tr><tr><td><b>Church Code</b></td><td><b>Mobile Service Provider</b></td>";
        html += "<td><b>Church Group</b></td></tr><tr>";
        html += "<td width=\"33%\"><input type=\"text\" id=\"church_code\" placeholder=\"Church Code\" value='"+ data.churchCode +"' readonly></td>";
        html += "<td width=\"33%\"><select class=\"login_input\" aria-label=\"Default Select Example\" id=\"mobileprovider\">";
        html += "<option value=\"Safaricom\" selected>Safaricom</option><option value=\"Airtel\">Airtel</option>";
        html += "<option value=\"Equitel\">Equitel</option><option value=\"TIGO\">TIGO</option>";
        html += "<option value=\"MTN\">MTN</option></select></td>";
        html += "<td width=\"33%\"><select class=\"login_input\" id=\"church_group\">";
        html += "<option value=\"Moi University\">Moi University</option>";
        html += "<option value=\"None\">None</option></select></td></tr>";
        html += "<tr><td><b>Mobile Service Provider</b></td><td><b>Preferred Language</b></td>";
        html += "<td><b>Phone Number Privacy</b></td></tr>";
        html += "<tr><td width=\"33%\"><select class=\"login_input\" aria-label=\"Default Select Example\" id=\"mobileprovider1\">";
        html += "<option value=\"Safaricom\">Safaricom</option><option value=\"Airtel\">Airtel</option>";
        html += "<option value=\"Equitel\">Equitel</option><option value=\"TIGO\">TIGO</option>";
        html += "<option value=\"MTN\">MTN</option></select></td>";
        html += "<td width=\"33%\"><select class=\"login_input\"  aria-label=\"Default Select Example\" id=\"preferredLanguage\" ";
        html += "style=\"padding-left:5px;\"><option value=\"English\" selected>English</option>";
        html += "<option value=\"Kiswahili\">Kiswahili</option><option value=\"French\">French</option>";
        html += "<option value=\"Kinyandarua\">Kinyandarua</option><option value=\"Buganda\">Buganda</option></select></td>";
        html += "<td class=\"now\"><input type=\"radio\" name=\"phone_number_privacy\" id=\"normal\" value=\"Normal\" checked>";
        html += "<label class=\"form-check-label\" for=\"flexRadioDefault1\">Normal</label>";
        html += "<input type=\"radio\" name=\"phone_number_privacy\" id=\"secret\" value=\"Secret\">";
        html += "<label class=\"form-check-label\" for=\"flexRadioDefault1\">Secret</label></td></tr>";
        html += "<tr><td><b>Phone Owner</b></td><td><b>Church Member</b></td><td><b>Other Phone Number</b></td></tr>";
        html += "<tr><td><input class=\"form-check-input\" type=\"checkbox\" id=\"phoneOwner\" checked>";
        html += "<label for=\"phoneOwner\" class=\"label_input\">Phone Owner</label></td>";
        html += "<td><input class=\"form-check-input\" type=\"checkbox\" id=\"churchMember\" checked>";
        html += "<label for=\"church_member\" class=\"label_input\">Church Member</label></td>";
        html += "<td><input type=\"text\" id=\"otherPhoneNumber\" name=\"otherPhoneNumber\" placeholder=\"Other Phone Number\" value='"+data.otherPhone+"'></td></tr>";
        html += "<tr><td><b>Giving Receipt To</b></td><td><b>Residence</b></td></tr>";
        html += "<tr><td><input class=\"form-check-input\" type=\"radio\" name=\"receipt_to\" id=\"self\" value=\"Self\" checked>";
        html += "<label class=\"label_input\" for=\"flexRadioDefault2\">Self</label>";
        html += "<input class=\"form-check-input\" type=\"radio\" name=\"receipt_to\" id=\"family\" value=\"Family\">";
        html += "<label class=\"label_input\" for=\"flexRadioDefault2\">Family</label><br>";
        html += "<input class=\"form-check-input\" type=\"radio\" name=\"receipt_to\" id=\"anonymous\" value=\"Anonymous\">";
        html += "<label class=\"label_input\" for=\"flexRadioDefault2\">Anonymous</label></td>";
        html += "<td><input type=\"text\" id=\"residence\" name=\"residence\" placeholder=\"Residence\"></td></tr><tr>";
        html += "<td><button type=\"button\" class=\"btn\" onclick=\"getBack()\" style=\"width:70%;\">Back</button></td>";
        html += "<td><button type=\"button\" class=\"btn\" onclick=\"submitMemberProfile()\" style=\"width:70%;\">Submit</button></td>";
        html += "<td><button type=\"button\" class=\"btn\" onclick=\"submitMemberProfile()\" style=\"width:70%;\">Transfer Church</button></td>";
        html += "</tr></tbody></table>";
        $("#member_profile").html(html);

	});
}


function getBack(){
    var all_members = document.getElementById("all_members");
    var member_profile = document.getElementById("member_profile");

    member_profile.style.display = "none";
    all_members.style.display = "block";
}


function submitMemberProfile(){
    var fullname = document.getElementById("fullname").value;
    var email = document.getElementById("email").value;
    var phone = document.getElementById("phone").value;
    var church_code = document.getElementById("church_code").value;
    var mobile_provider = document.getElementById("mobileprovider").value;
    var group = document.getElementById("church_group").value;
    var mobile_provider1 = document.getElementById("mobileprovider1").value;
    var language = document.getElementById("preferredLanguage").value;
    var phone_privacy = document.getElementsByName("phone_number_privacy");
    var phone_privacy_value;
    for (var i = 0; i < phone_privacy.length; i++) {
      if (phone_privacy[i].checked) {
        phone_privacy_value = phone_privacy[i].value;
      }
    }
    var phone_owner = document.getElementById("phoneOwner");
    if (phone_owner.checked == true){
        phone_owner = true;
    }else{
        phone_owner = false;
    }
    var church_member = document.getElementById("churchMember");
    if (church_member.checked == true){
        church_member = "true";
    }else{
        church_member = "false";
    }
    var other_phone = document.getElementById("otherPhoneNumber").value;
    var residence = document.getElementById("residence").value;
    var receipt_to = document.getElementsByName("receipt_to");
    var receipt_to_value;
    for (var i = 0; i < receipt_to.length; i++) {
        if (receipt_to[i].checked) {
           receipt_to_value = receipt_to[i].value;
        }
    }

    var profile_data = {
        fullname: fullname,
        email: email,
        churchCode: church_code,
        phone: phone,
        phone_number_privacy: phone_privacy_value,
        language: language,
        phoneOwner: phone_owner,
        churchMember: church_member,
        otherPhoneNumber: other_phone,
        receipt_to: receipt_to_value,
        residence: residence
    };

    console.log(profile_data);
    document.body.scrollTop = 0;
    document.documentElement.scrollTop = 0;

    $.post(hosted_url + "/cfms/auth/saveProfile",profile_data ,function(data, status){
        if(data.status == 0){
            $(".responseDiv").show();
            $(".responseDiv").html(fullname + " profile updated successfully");
            $(".responseDiv").fadeOut(3000);
        }
    });

}



function TransferChurch(code,code2){
    var name = document.getElementById("fullname").value;
    var phone_number = document.getElementById("phone").value;

    var statement = "Is "+ name +" the church you wish to transfer your details to?";

    var content = {
        phone_number: phone_number,
        church_code: code2
    }
    if(code != code2){
         if(confirm(statement) == true){
             $.post(hosted_url + "/cfms/auth/member-transfer", content ,function(data, status){
                   $(".responseDiv1").show();
                   $(".responseDiv1").html("Transfer Successful");
                   $(".responseDiv1").fadeOut(3000);
             });
         }
    }else if(code === code2){
        $(".responseDiv1").show();
        $(".responseDiv1").html("You are already Registered to this Church Code");
        $(".responseDiv1").fadeOut(3000);
    }
}