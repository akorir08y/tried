var hosted_url = location.origin;

function getSingleAccount(account_name,department,status,priority,duration){
    var phone_number = document.getElementById("phone_number").value;
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;

    var single_account = document.getElementById("single_account");
    var all_accounts = document.getElementById("all_accounts");

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
        html += "<td><input type=\"text\" class=\"login_input\" id=\"account_name\" name=\"account_name\" value='"+account_name+"'></td></tr>";	html += "<tr><td><label class=\"label_input\"><b>Department</b></label></td>";
        html += "<td colspan=\"2\"><select id=\"department_name\">";

        for (i=0;i < request.length;i++){
            if(request[i] == account_name){
                html += "<option value="+request[i]+">"+ request[i] + "</option>";
            }else{
                html += "<option value="+request[i]+">"+ request[i] + "</option>";
            }
        }
        html += "</select id=\"priority\"></td></tr>";
        html += "<tr>";
        html += "<td><label class=\"label_input\"><b>Priority</b></label></td>";
        html += "<td colspan=\"2\"><select>";
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
            html += "<tr colspan=\"2\"><td><input class=\"form-check-input\" type=\"radio\" name=\"status\" id=\"active_status\" checked>";
            html += "<label class=\"form-check-label\" for=\"church_member\">Active</label></td>";
            html += "<td><input class=\"form-check-input\" type=\"radio\" name=\"status\" id=\"inactive_status\">";
            html += "<label class=\"form-check-label\" for=\"church_member\">Inactive</label></td></tr>";
        }else{
            html += "<tr colspan=\"2\"><td><input class=\"form-check-input\" type=\"radio\" name=\"status\" id=\"active_status\">";
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
    var department_name = document.getElementById("department_name").value;
    var status = document.getElementsByName("status");
    var status_value;
    for (var i = 0; i < status.length; i++) {
        if (status[i].checked == true) {
          status_value = status[i].value;
        }
    }

    if (duration.checked == true) {
        duration = "Permanent";
    }else{
        duration = "Permanent";
    }

    var department_info = {
        account_name: account_name,
        department: department_name,
        priority: priority,
        duration: duration,
        status: status
    }

    console.log(JSON.stringify(department_info));
}


function backToAllAccounts(){
    var all_accounts = document.getElementById("all_accounts");
    var single_account = document.getElementById("single_account");

    all_accounts.style.display = "block";
    single_account.style.display = "none";
}