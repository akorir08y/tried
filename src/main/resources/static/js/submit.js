var hosted_url = location.origin;

function submitProfile(){


    var fullname = document.getElementById("fullname").value;
    var email = document.getElementById("email").value;
    var phone = document.getElementById("phone").value;
    var pin = document.getElementById("pin").value;
    var church_code = document.getElementById("church_code").value;
    var mobile_provider = document.getElementById("mobileprovider").value;
    var group = document.getElementById("church_group").value;
    var language = document.getElementById("preferredLanguage").value;
    var phone_privacy = document.getElementsByName("phone_number_privacy");
    var phone_privacy_value;
    for (var i = 0; i < phone_privacy.length; i++) {
      if (phone_privacy[i].checked) {
        phone_privacy_value = phone_privacy[i].value;
      }
    }
    var phone_owner = document.getElementById("phoneOwner");
    var church_member = document.getElementById("churchMember");
    var other_phone = document.getElementById("otherPhoneNumber").value;
    var residence = document.getElementById("residence").value;
    var receipt_to = document.getElementsByName("receipt_to");
    var receipt_to_value;
    for (var i = 0; i < receipt_to.length; i++) {
        if (receipt_to[i].checked) {
            receipt_to_value = receipt_to[i].value;
        }
    }

    if(phone_owner.checked == true){
        phone_owner = true;
    }else{
        phone_owner = false;
    }

    if(church_member.checked == true){
        church_member = "true";
    }else{
        church_member = "false";
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

    $.post(hosted_url + "/cfms-web/auth/saveProfile",profile_data ,function(data, status){
        if(data.status == 0){
            alert("Profile Updated Successfully");s
            window.location.reload();
        }else{
            alert("Profile Update Unsuccessful");
            window.location.reload();
        }
    });

}

function submitProfiles(){
    $("#profile").submit();
}

function exportStatement(){

}