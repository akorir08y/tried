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
        html += "<tr><td width=\"33%\"><select class=\"login_input\" aria-label=\"Default Select Example\" id=\"mobileprovider\">";
        html += "<option value=\"Safaricom\">Safaricom</option><option value=\"Airtel\">Airtel</option>";
        html += "<option value=\"Equitel\">Equitel</option><option value=\"TIGO\">TIGO</option>";
        html += "<option value=\"MTN\">MTN</option></select></td>";
        html += "<td width=\"33%\"><select class=\"login_input\"  aria-label=\"Default Select Example\" id=\"PreferredLanguage\" ";
        html += "style=\"padding-left:5px;\"><option value=\"English\" selected>English</option>";
        html += "<option value=\"Kiswahili\">Kiswahili</option><option value=\"French\">French</option>";
        html += "<option value=\"Kinyandarua\">Kinyandarua</option><option value=\"Buganda\">Buganda</option></select></td>";
        html += "<td class=\"now\"><input type=\"radio\" name=\"flexRadioDefault\" id=\"flexRadioDefault1\" checked>";
        html += "<label class=\"form-check-label\" for=\"flexRadioDefault1\">Normal</label>";
        html += "<input type=\"radio\" name=\"flexRadioDefault\" id=\"flexRadioDefault1\">";
        html += "<label class=\"form-check-label\" for=\"flexRadioDefault1\">Secret</label></td></tr>";
        html += "<tr><td><b>Phone Owner</b></td><td><b>Church Member</b></td><td><b>Other Phone Number</b></td></tr>";
        html += "<tr><td><input class=\"form-check-input\" type=\"checkbox\" value=\"\" id=\"phoneOwner\" checked>";
        html += "<label for=\"phoneOwner\" class=\"label_input\">Phone Owner</label></td>";
        html += "<td><input class=\"form-check-input\" type=\"checkbox\" value=\"\" id=\"churchMember\" checked>";
        html += "<label for=\"church_member\" class=\"label_input\">Church Member</label></td>";
        html += "<td><input type=\"text\" id=\"otherPhoneNumber\" name=\"otherPhoneNumber\" placeholder=\"Other Phone Number\" value='"+data.otherPhone+"'></td></tr>";
        html += "<tr><td><b>Giving Receipt To</b></td><td><b>Residence</b></td></tr>";
        html += "<tr><td><input class=\"form-check-input\" type=\"radio\" name=\"flexRadioDefault1\" id=\"self\" checked>";
        html += "<label class=\"label_input\" for=\"flexRadioDefault2\">Self</label>";
        html += "<input class=\"form-check-input\" type=\"radio\" name=\"flexRadioDefault1\" id=\"family\">";
        html += "<label class=\"label_input\" for=\"flexRadioDefault2\">Family</label><br>";
        html += "<input class=\"form-check-input\" type=\"radio\" name=\"flexRadioDefault1\" id=\"anonymous\">";
        html += "<label class=\"label_input\" for=\"flexRadioDefault2\">Anonymous</label></td>";
        html += "<td><input type=\"text\" id=\"residence\" name=\"residence\" placeholder=\"Residence\"></td></tr><tr>";
        html += "<td><button type=\"button\" class=\"btn\" onclick=\"getBack()\" style=\"width:100%;\">Back</button></td>";
        html += "<td><button type=\"button\" class=\"btn\" onclick=\"displayDetails()\" style=\"width:100%;\">Submit</button></td>";
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