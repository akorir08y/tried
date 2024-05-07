var hosted_url = location.origin;

function getSingleAccount(account_name,department,status,priority,duration){
    var phone_number = document.getElementById("phone_number").value;
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;

    var single_account = document.getElementById("single_account");
    var new_single_account = document.getElementById("new_single_account");
    var all_accounts = document.getElementById("all_accounts");


    new_single_account.style.display = "none";
    all_accounts.style.display = "none";
    single_account.style.display = "block";

    console.log("Status: " + status);
    var statused = status.trim();

	var profile_data = {
	    username: username,
	    phone_number: phone_number,
	    password: password,
	};

	$.post(hosted_url + "/cfms-web/personnel/department-accounts",profile_data ,function(data, status){

        // Trust Fund Accounts
        var request = data.departments;
        var html = new String("");

        html += "<div class=\"cardHeader\">";
        html += "<h2 style=\"text-align:center;margin-top:10px;margin-bottom:10px\">Update Local Church Account</h2></div>";
        html += "<div class=\"responseDiv\" style=\"display:none;\"></div><br><hr>";
        html += "<table><form id=\"special_trust_fund_form\"><tr><td><label class=\"label_input\"><b>Account Name</b></label></td>";
        html += "<td><input type=\"hidden\" name=\"previous_account_name\" id=\"previous_account_name\" value='"+account_name+"'>";
        html += "<td><input type=\"text\" class=\"login_input\" pattern=\"[A-Za-z0-9]\" id=\"account_name\" name=\"account_name\" value='"+account_name+"'></td></tr>";
        html += "<tr><td><label class=\"label_input\"><b>Department</b></label></td>";
        html += "<td colspan=\"2\"><select id=\"department_name\">";

        for (i=0;i < request.length;i++){
            if(request[i] == account_name){
                html += "<option value="+request[i]+">"+ request[i] + "</option>";
            }else{
                html += "<option value="+request[i]+">"+ request[i] + "</option>";
            }
        }
        html += "</select></td></tr>";
        html += "<tr>";
        html += "<td><label class=\"label_input\"><b>Priority</b></label></td>";
        html += "<td colspan=\"2\"><select id=\"priority\">";
        for (i=0;i < 100; i++){
            if(i == priority){
                html += "<option value="+i+" selected>"+i+"</option>";
            }else{
                html += "<option value="+i+">"+i+"</option>";
            }
        }
        html += "</select></td></tr>";
        html += "<tr><td style=\"font-size:12px;\"><b>Duration</b></td>";
        if(duration == 'Permanent'){
            html += "<td colspan=\"2\"><input type=\"checkbox\" class=\"login_input\" id=\"duration\" name=\"duration1\" checked></td></tr>";
        }else{
            html += "<td colspan=\"2\"><input type=\"checkbox\" class=\"login_input\" id=\"duration\" name=\"duration1\"></td></tr>";
        }
        html += "<tr><td style=\"font-size:12px;\"><b>Status</b></td></tr>";


        if(statused == 'ACTIVATED'){
            html += "<tr colspan=\"2\"><td><input class=\"form-check-input\" type=\"radio\" name=\"status\" id=\"active_status\" value=\"ACTIVATED\" checked>";
            html += "<label class=\"form-check-label\" for=\"church_member\">Active</label></td>";
            html += "<td><input class=\"form-check-input\" type=\"radio\" name=\"status\" id=\"inactive_status\">";
            html += "<label class=\"form-check-label\" for=\"church_member\">Inactive</label></td></tr>";
        }else{
            html += "<tr colspan=\"2\"><td><input class=\"form-check-input\" type=\"radio\" name=\"status\" id=\"active_status\" value=\"INACTIVATED\">";
            html += "<label class=\"form-check-label\" for=\"church_member\">Active</label></td>";
            html += "<td><input class=\"form-check-input\" type=\"radio\" name=\"status\" id=\"inactive_status\" checked>";
            html += "<label class=\"form-check-label\" for=\"church_member\">Inactive</label></td></tr>";
        }

        html += "<tr><td><button type=\"button\" class=\"btn\" onclick=\"backToAllAccounts()\">Back</button>  <button type=\"button\" class=\"btn\" onclick=\"saveAccount()\" id=\"save_account\">Save Account</button></td></tr>";
        html += "</form><tbody></table>";

        all_accounts.style.display = "none";
        single_account.style.display = "block";
        $("#single_account").html(html);
	});
}


function saveAccount(){
    var duration = document.getElementById("duration");
    var priority = document.getElementById("priority").value;
    var account_name = document.getElementById("account_name").value;
    var previous_account_name = document.getElementById("previous_account_name").value;
    var department_name = document.getElementById("department_name").value;
    var phone_number = document.getElementById("phone_number").value;
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;
    var active_status = document.getElementById("active_status");
    var inactive_status = document.getElementById("inactive_status");
    var status_value = new String("");


    if(active_status.checked == true){
        status_value = "ACTIVATED";
    }else if(inactive_status.checked == true){
        status_value = "INACTIVATED";
    }

    if (duration.checked == true) {
        duration = "Permanent";
    }else{
        duration = "Permanent";
    }

    var department_info = {
        account_name: account_name,
        previous_account_name: previous_account_name,
        department: department_name,
        priority: priority,
        duration: duration,
        status: status_value,
        username: username,
        password: password,
        phone_number: phone_number
    }

    console.log(department_info);

    $.post(hosted_url + "/cfms-web/personnel/update-account",department_info ,function(data, status){
        alert(account_name + " Updated Successfully");
        window.location.reload();
    });
}


function backToAllAccounts(){
    var all_accounts = document.getElementById("all_accounts");
    var single_account = document.getElementById("single_account");

    all_accounts.style.display = "block";
    single_account.style.display = "none";
}


function newSingleAccount(){
    var phone_number = document.getElementById("phone_number").value;
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;

    var new_single_account = document.getElementById("new_single_account");
    var single_account = document.getElementById("single_account");
    var all_accounts = document.getElementById("all_accounts");

    single_account.style.display = "none";
    all_accounts.style.display = "none";
    new_single_account.style.display = "block";

    console.log("Status: " + status);
    var statused = status.trim();

	var profile_data = {
	    username: username,
	    phone_number: phone_number,
	    password: password,
	};


	$.post(hosted_url + "/cfms-web/personnel/department-accounts",profile_data ,function(data, status){

        // Trust Fund Accounts
        var request = data.departments;
        var html = new String("");

        html += "<div class=\"cardHeader\">";
        html += "<h2 style=\"text-align:center;margin-top:10px;margin-bottom:10px\">New Local Church Account</h2></div>";
        html += "<div class=\"responseDiv\" style=\"display:none;\"></div><br><hr>";
        html += "<table><form id=\"special_trust_fund_form\"><tr><td><label class=\"label_input\"><b>Account Name</b></label></td>";
        html += "<td><input type=\"text\" class=\"login_input\" id=\"account_name\" pattern=\"[A-Za-z0-9]\" name=\"account_name\" value=''></td></tr>";
        html += "<tr><td><label class=\"label_input\"><b>Department</b></label></td>";
        html += "<td colspan=\"2\"><select id=\"department_name\">";

        for (i=0;i < request.length;i++){
              html += "<option value="+request[i]+">"+ request[i] + "</option>";
        }
        html += "</select></td></tr>";
        html += "<tr>";
        html += "<td><label class=\"label_input\"><b>Priority</b></label></td>";
        html += "<td colspan=\"2\"><select id=\"priority\">";
        for (i=0;i < 100; i++){
            html += "<option value="+i+">"+i+"</option>";
        }
        html += "</select></td></tr>";
        html += "<tr><td style=\"font-size:12px;\"><b>Duration</b></td>";
        html += "<td colspan=\"2\"><input type=\"checkbox\" class=\"login_input\" id=\"duration\" name=\"duration\" value='Permanent' checked></td></tr>";
        html += "<tr><td style=\"font-size:12px;\"><b>Status</b></td></tr>";
        html += "<tr colspan=\"2\"><td><input class=\"form-check-input\" type=\"radio\" name=\"status\" id=\"active_status\" checked>";
        html += "<label class=\"form-check-label\" for=\"church_member\">Active</label></td>";
        html += "<td><input class=\"form-check-input\" type=\"radio\" name=\"status\" id=\"inactive_status\">";
        html += "<label class=\"form-check-label\" for=\"church_member\">Inactive</label></td></tr>";
        html += "<tr><td><button type=\"button\" class=\"btn\" onclick=\"backToAllAccounts()\">Back</button>  <button type=\"button\" class=\"btn\" onclick=\"saveNewAccount()\" id=\"save_account\">Save Account</button></td></tr>";
        html += "</form><tbody></table>";

        all_accounts.style.display = "none";
        single_account.style.display = "block";
        $("#new_single_account").html(html);
	});
}

function saveNewAccount(){
    var account_name = document.getElementById("account_name").value;
    var department_name = document.getElementById("department_name").value;
    var priority = document.getElementById("priority").value;
    var duration = document.getElementById("duration").value;
    var active_status = document.getElementById("active_status");
    var inactive_status = document.getElementById("inactive_status");
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;
    var phone_number = document.getElementById("phone_number").value;
    var status = new String("");

    if (active_status.checked == true){
        status = "ACTIVATED";
    }else if(inactive_status.checked == true){
        status = "INACTIVATED";
    }else{
        status = "INACTIVATED";
    }

    var account_info = {
        account_name: account_name,
        department: department_name,
        priority: priority,
        duration: duration,
        status: status,
        username: username,
        password: password,
        phone_number: phone_number
    };

    console.log(account_info);

    $.post(hosted_url + "/cfms-web/personnel/create-account",account_info ,function(data, status){
          alert(account_name + " Created Successfully");
          window.location.reload();
    });

}