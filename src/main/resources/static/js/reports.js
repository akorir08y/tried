var hosted_url = location.origin;

function validateTrustFundMonthForm() {
  var month = document.getElementbyId("select_month").value;
  if (month == "") {
    alert("Month must be filled out");
    return false;
  }
}

function TrustFundReportPDF(){
    var start_date = document.getElementById("select_month").value;
    var all_accounts = document.getElementById("all_accounts").value;
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;
    var phone_number = document.getElementById("phone_number").value;
    document.getElementById("pdf_input").value = "PDF";

    if (start_date == "") {
        alert("All Fields Must be Filled Out");
        return false;
    }

    $("#trust_fund_month_form").submit();
}

function TrustFundReportExcel(){
    var start_date = document.getElementById("select_month").value;
    document.getElementById("pdf_input").value = "Excel";

    if (start_date == "") {
        alert("All Fields Must be Filled Out");
        return false;
    }

    $("#trust_fund_month_form").submit();
}


function TrustFundDateToDateReportPDF(){
    var start_date = document.getElementById("from-date").value;
    var end_date = document.getElementById("end-date").value;
    document.getElementById("pdf_date_input").value = "PDF";

    if (start_date == "") {
        alert("Start Date Must be Filled Out");
        return false;
    }else if (end_date == "") {
        alert("End Date Must be Filled Out");
        return false;
    }

    $("#trust_fund_date_to_date_form").submit();
}

function TrustFundDateToDateReportExcel(){
    var start_date = document.getElementById("select_month").value;
    var all_accounts = document.getElementById("all_accounts").value;
    document.getElementById("pdf_date_input").value = "Excel";

    if (start_date == "") {
        alert("Start Date Must be Filled Out");
        return false;
    }else if (end_date == "") {
        alert("End Date Must be Filled Out");
        return false;
    }

    $("#trust_fund_date_to_date_form").submit();
}


function getRange(){
    var trust_dated = document.getElementById("trust_dated");
    var trust_ranger = document.getElementById("trust_ranger");

    trust_ranger.style.display = "none";
    trust_dated.style.display = "block";
}


function getDateToDate(){
    var trust_dated = document.getElementById("trust_dated");
    var trust_ranger = document.getElementById("trust_ranger");

    trust_ranger.style.display = "block";
    trust_dated.style.display = "none";
}

function TrustFundTranscriptReportExcel(){
    var start_date = document.getElementById("from-date-transcript").value;
    var end_date = document.getElementById("to-date-transcript").value;
    document.getElementById("input_transcript").value = "Excel";

    if (start_date == "" || end_date == "") {
        alert("All Dates Must be Filled Out");
        return false;
    }

    $("#trust_fund_transcript").submit();
}


function TrustFundTranscriptReportPDF(){
    var start_date = document.getElementById("from-date-transcript").value;
    var end_date = document.getElementById("to-date-transcript").value;
    document.getElementById("input_transcript").value = "PDF";

    if (start_date == "" || end_date == "") {
        alert("All Dates Must be Filled Out");
        return false;
    }

    $("#trust_fund_transcript").submit();
}

function nonTrustFundReportExcel(){
    var start_date = document.getElementById("from-date-non-trust").value;
    var end_date = document.getElementById("to-date-non-trust").value;
    var account_name = document.getElementById("account_name").value;
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;
    var phone_number = document.getElementById("phone_number").value;
    document.getElementById("input").value = "Excel";

    if (start_date == "" || end_date == "") {
        alert("All Fields Must be Filled Out");
        return false;
    }

    $("#non_trust_fund").submit();
}


function nonTrustFundReportPDF(){
    var start_date = document.getElementById("from-date-non-trust").value;
    var end_date = document.getElementById("to-date-non-trust").value;
    var account_name = document.getElementById("account_name").value;
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;
    var phone_number = document.getElementById("phone_number").value;
     document.getElementById("input").value = "PDF";

    if (start_date == "" || end_date == "") {
        alert("All Fields Must be Filled Out");
        return false;
    }

    $("#non_trust_fund").submit();
}

function tracingReportExcel(){
    var start_date = document.getElementById("from-date-trace").value;
    var end_date = document.getElementById("to-date-trace").value;
    document.getElementById("input_trace").value = "Excel";

    if (start_date == "" || end_date == "") {
        alert("All Fields Must be Filled Out");
        return false;
    }

    $("#transaction-tracing").submit();
}


function tracingReportPDF(){
    var start_date = document.getElementById("from-date-trace").value;
    var end_date = document.getElementById("to-date-trace").value;
    document.getElementById("input_trace").value = "PDF";

    if (start_date == "" || end_date == "") {
        alert("All Dates Must be Filled Out");
        return false;
    }

    $("#transaction-tracing").submit();
}


function specificAccountSummaryPDF(){
    var start_date = document.getElementById("from-date-specific").value;
    var end_date = document.getElementById("to-date-specific").value;
    document.getElementById("input_specific").value = "PDF";

    if (start_date == "" || end_date == "") {
        alert("All Dates Must be Filled Out");
        return false;
    }

    $("#specific_account_summary").submit();
}


function specificAccountSummaryExcel(){
    var start_date = document.getElementById("from-date-specific").value;
    var end_date = document.getElementById("to-date-specific").value;
    document.getElementById("input_specific").value = "Excel";

    if (start_date == "" || end_date == "") {
        alert("All Dates Must be Filled Out");
        return false;
    }

    $("#specific_account_summary").submit();
}

// local_church_off_statement
function getLocalChurchOfferingStatement(){
    var phone = document.getElementById("phone_local").value;
    var pin = document.getElementById("pass_local").value;
    var start_date = document.getElementById("start_date_local").value;
    var end_date = document.getElementById("end_date_local").value;
    var username = document.getElementById("local_username").value;
    var password = document.getElementById("local_password").value;

    if(start_date == null || end_date == null){
        alert("Make Sure you input the start and end dates");
        return false;
    }

    if(pin == ""){
        alert("Make Sure you enter the Pin");
        return false;
    }

    if (start_date >= end_date){
        alert("The Start Date must predate the End date");
        return false;
    }

    var dates = {
        start_date: start_date,
        end_date: end_date,
        phone_number: phone,
        pin: pin,
        username: username,
        password: password
    };

    var loader = document.getElementById("loader_spin_local_church");
    var contented = document.getElementById("content2");
    contented.style.display = "none";
    loader.style.display = "block";

    console.log(dates)

    $.post(hosted_url + "/cfms-web/personnel/local_church_offering_statement",dates ,function(data, status){
         if(data != null){
             console.log(data);
             loader.style.display = "none";
             contented.style.display = "block";

             let container = $("#content2");
             let html = new String("");
             html += "<table id=\"statement\"><tbody id=\"myTable\" myTable_local>";

             $.each(data.payload.members, (i, statement) => {
                  console.log("Statement:" +statement);
                  html += "<tr tabindex=\"0\" class=\"con\"><td>";
                  html += "<span class='left'>"+statement.narration+"</span>";
                  html += "<span class='right-blue'>"+statement.amountRefunded +"(Balance: "+ statement.balance + ")</span><br><br>";
                  html += "<span class='left-below'>Receipt No: "+statement.receiptNumber+"</span><br>";
                  html += "<span class='right'>"+statement.transactionDate+"</span>";
                  html += "</td></tr>";
             });
             html += "</tbody></table>";
             container.html(html); //Append the table to the container element
             }else{
                loader.style.display = "none";
                contented.style.display = "block";

                let container = $("#content2");
                let html = new String("");
                html += "<p>No Data to Display</p>";
                container.html(html);
             }

    });
}


// local_church_off_statement
function getMemberSpecificOfferingStatement(){
    var phone = document.getElementById("phone_member").value;
    var pin = document.getElementById("pass_member").value;
    var start_date = document.getElementById("start_date_member").value;
    var end_date = document.getElementById("end_date_member").value;
    var username = document.getElementById("member_username").value;
    var password = document.getElementById("member_password").value;
    var member_name = document.getElementById("member_name").value;
    var member_number = document.getElementById("member_number").value;

    if(start_date == null || end_date == null){
        alert("Make Sure you input the start and end dates");
        return false;
    }

    if(pin == ""){
        alert("Make Sure you enter the Pin");
        return false;
    }

    if (start_date >= end_date){
        alert("The Start Date must predate the End date");
        return false;
    }

    var dates = {
        start_date: start_date,
        end_date: end_date,
        phone_number: phone,
        treasurer_pin: pin,
        username: username,
        password: password,
        member_name: member_name,
        member_number: member_number
    };

    var loader = document.getElementById("loader_spin_local_member");
    var contented = document.getElementById("content2");
    contented.style.display = "none";
    loader.style.display = "block";

    console.log(dates)

    $.post(hosted_url + "/cfms-web/personnel/get-member-statement",dates ,function(data, status){
         if(data != null){
             console.log(data);
             loader.style.display = "none";
             contented.style.display = "block";

             let container = $("#content2");
             let html = new String("");
             html += "<table id=\"statement\"><tbody id=\"myInput_member\">";

             $.each(data.payload.members, (i, statement) => {
                  console.log("Statement:" +statement);
                  html += "<tr tabindex=\"0\" class=\"con\"><td>";
                  html += "<span class='left'>"+statement.narration+"</span>";
                  html += "<span class='right-blue'>"+statement.amountRefunded +"(Balance: "+ statement.balance + ")</span><br><br>";
                  html += "<span class='left-below'>Receipt No: "+statement.receiptNumber+"</span><br>";
                  html += "<span class='right'>"+statement.transactionDate+"</span>";
                  html += "</td></tr>";
             });
             html += "</tbody></table>";
             container.html(html); //Append the table to the container element
             }else{
                loader.style.display = "none";
                contented.style.display = "block";

                let container = $("#content2");
                let html = new String("");
                html += "<p>No Data to Display</p>";
                container.html(html);
             }

    });
}

function showNonTrustFund(){
    var trust_fund_transcript = document.getElementById("trust_fund_transcript_report");
    var non_trust_fund_report = document.getElementById("non_trust_fund_report");
    var local_offering_report = document.getElementById("local_offering_report");
    var trust_summary_reports = document.getElementById("trust_summary_reports");
    var specific_account_summary_reports = document.getElementById("specific_account_summary_reports");
    var transactions_trace_summary_report = document.getElementById("transactions_trace_summary_report");
    var local_church_statement = document.getElementById("local_church_statement");
    var member_statement = document.getElementById("member_statement");
    var member_statement_field = document.getElementById("member_statement_field");
    var generated_member_statement = document.getElementById("generated_member_statement");

    generated_member_statement.style.display = "none";
    member_statement_field.style.display="none";
    local_church_statement.style.display = "none";
    member_statement.style.display = "none";
    trust_fund_transcript.style.display = "none";
    local_offering_report.style.display = "none";
    trust_summary_reports.style.display = "none";
    specific_account_summary_reports.style.display = "none";
    transactions_trace_summary_report.style.display = "none";
    non_trust_fund_report.style.display = "block";
}


function showTrustFund(){
    var trust_fund_transcript = document.getElementById("trust_fund_transcript_report");
    var non_trust_fund_report = document.getElementById("non_trust_fund_report");
    var local_offering_report = document.getElementById("local_offering_report");
    var trust_summary_reports = document.getElementById("trust_summary_reports");
    var specific_account_summary_reports = document.getElementById("specific_account_summary_reports");
    var transactions_trace_summary_report = document.getElementById("transactions_trace_summary_report");
    var local_church_statement = document.getElementById("local_church_statement");
    var member_statement = document.getElementById("member_statement");
    var member_statement_field = document.getElementById("member_statement_field");
    var generated_member_statement = document.getElementById("generated_member_statement");

    generated_member_statement.style.display = "none";
    member_statement_field.style.display="none";
    local_church_statement.style.display = "none";
    member_statement.style.display = "none";
    trust_fund_transcript.style.display = "none";
    local_offering_report.style.display = "none";
    specific_account_summary_reports.style.display = "none"
    non_trust_fund_report.style.display = "none";
    transactions_trace_summary_report.style.display = "none";
    trust_summary_reports.style.display = "block"

}


function showLocalChurchOffering(){
    var trust_fund_transcript = document.getElementById("trust_fund_transcript_report");
    var non_trust_fund_report = document.getElementById("non_trust_fund_report");
    var local_offering_report = document.getElementById("local_offering_report");
    var trust_summary_reports = document.getElementById("trust_summary_reports");
    var specific_account_summary_reports = document.getElementById("specific_account_summary_reports");
    var transactions_trace_summary_report = document.getElementById("transactions_trace_summary_report");
    var local_church_statement = document.getElementById("local_church_statement");
    var member_statement = document.getElementById("member_statement");
    var member_statement_field = document.getElementById("member_statement_field");
    var generated_member_statement = document.getElementById("generated_member_statement");

    generated_member_statement.style.display = "none";
    member_statement_field.style.display="none";
    local_church_statement.style.display = "none";
    member_statement.style.display = "none";
    trust_fund_transcript.style.display = "none";
    trust_summary_reports.style.display = "none";
    specific_account_summary_reports.style.display = "none";
    non_trust_fund_report.style.display = "none";
    transactions_trace_summary_report.style.display = "none";
    local_offering_report.style.display = "block";
}



function showTransactionTracingReport(){
    var trust_fund_transcript = document.getElementById("trust_fund_transcript_report");
    var non_trust_fund_report = document.getElementById("non_trust_fund_report");
    var local_offering_report = document.getElementById("local_offering_report");
    var trust_summary_reports = document.getElementById("trust_summary_reports");
    var specific_account_summary_reports = document.getElementById("specific_account_summary_reports");
    var transactions_trace_summary_report = document.getElementById("transactions_trace_summary_report");
    var local_church_statement = document.getElementById("local_church_statement");
    var member_statement = document.getElementById("member_statement");
    var member_statement_field = document.getElementById("member_statement_field");
    var generated_member_statement = document.getElementById("generated_member_statement");

    generated_member_statement.style.display = "none";
    member_statement_field.style.display="none";
    local_church_statement.style.display = "none";
    member_statement.style.display = "none";
    local_offering_report.style.display = "none";
    trust_summary_reports.style.display = "none";
    specific_account_summary_reports.style.display = "none"
    non_trust_fund_report.style.display = "none";
    trust_fund_transcript.style.display = "none";
    transactions_trace_summary_report.style.display = "block"
}



function showTrustFundTranscriptReport(){
    var trust_fund_transcript = document.getElementById("trust_fund_transcript_report");
    var non_trust_fund_report = document.getElementById("non_trust_fund_report");
    var local_offering_report = document.getElementById("local_offering_report");
    var trust_summary_reports = document.getElementById("trust_summary_reports");
    var specific_account_summary_reports = document.getElementById("specific_account_summary_reports");
    var transactions_trace_summary_report = document.getElementById("transactions_trace_summary_report");
    var local_church_statement = document.getElementById("local_church_statement");
    var member_statement = document.getElementById("member_statement");
    var member_statement_field = document.getElementById("member_statement_field");
    var generated_member_statement = document.getElementById("generated_member_statement");

    generated_member_statement.style.display = "none";
    member_statement_field.style.display="none";
    local_church_statement.style.display = "none";
    member_statement.style.display = "none";
    local_offering_report.style.display = "none";
    trust_summary_reports.style.display = "none";
    specific_account_summary_reports.style.display = "none"
    non_trust_fund_report.style.display = "none";
    transactions_trace_summary_report.style.display = "none";
    trust_fund_transcript.style.display = "block";
}



function showSpecificAccountSummaryReport(){
    var trust_fund_transcript = document.getElementById("trust_fund_transcript_report");
    var non_trust_fund_report = document.getElementById("non_trust_fund_report");
    var local_offering_report = document.getElementById("local_offering_report");
    var trust_summary_reports = document.getElementById("trust_summary_reports");
    var specific_account_summary_reports = document.getElementById("specific_account_summary_reports");
    var transactions_trace_summary_report = document.getElementById("transactions_trace_summary_report");
    var local_church_statement = document.getElementById("local_church_statement");
    var member_statement = document.getElementById("member_statement");
    var member_statement_field = document.getElementById("member_statement_field");
    var generated_member_statement = document.getElementById("generated_member_statement");

    generated_member_statement.style.display = "none";
    member_statement_field.style.display="none";
    local_church_statement.style.display = "none";
    member_statement.style.display = "none";
    local_offering_report.style.display = "none";
    trust_summary_reports.style.display = "none";
    non_trust_fund_report.style.display = "none";
    transactions_trace_summary_report.style.display = "none";
    trust_fund_transcript.style.display = "none";
    specific_account_summary_reports.style.display = "block";
}


function showMemberStatement(){
    var trust_fund_transcript = document.getElementById("trust_fund_transcript_report");
    var non_trust_fund_report = document.getElementById("non_trust_fund_report");
    var local_offering_report = document.getElementById("local_offering_report");
    var trust_summary_reports = document.getElementById("trust_summary_reports");
    var specific_account_summary_reports = document.getElementById("specific_account_summary_reports");
    var transactions_trace_summary_report = document.getElementById("transactions_trace_summary_report");
    var local_church_statement = document.getElementById("local_church_statement");
    var member_statement = document.getElementById("member_statement");
    var member_statement_field = document.getElementById("member_statement_field");

    local_offering_report.style.display = "none";
    trust_summary_reports.style.display = "none";
    non_trust_fund_report.style.display = "none";
    transactions_trace_summary_report.style.display = "none";
    trust_fund_transcript.style.display = "none";
    specific_account_summary_reports.style.display = "none";
    local_church_statement.style.display = "none";
    member_statement.style.display = "block";
    member_statement_field.style.display="block";
}


function showLocalChurchOfferingStatement(){
    var trust_fund_transcript = document.getElementById("trust_fund_transcript_report");
    var non_trust_fund_report = document.getElementById("non_trust_fund_report");
    var local_offering_report = document.getElementById("local_offering_report");
    var trust_summary_reports = document.getElementById("trust_summary_reports");
    var specific_account_summary_reports = document.getElementById("specific_account_summary_reports");
    var transactions_trace_summary_report = document.getElementById("transactions_trace_summary_report");
    var local_church_statement = document.getElementById("local_church_statement");
    var member_statement = document.getElementById("member_statement");
    var member_statement_field = document.getElementById("member_statement_field");
    var generated_member_statement = document.getElementById("generated_member_statement");

    generated_member_statement.style.display = "none";
    member_statement_field.style.display="none";
    local_offering_report.style.display = "none";
    trust_summary_reports.style.display = "none";
    non_trust_fund_report.style.display = "none";
    transactions_trace_summary_report.style.display = "none";
    trust_fund_transcript.style.display = "none";
    specific_account_summary_reports.style.display = "none";
    member_statement.style.display = "none";
    local_church_statement.style.display = "block";
}


function getSpecificMemberStatement(member_name, member_number){
    var generated_member_statement = document.getElementById("generated_member_statement");
    document.getElementById("").value = member_name;

    generated_member_statement.style.display = "block";
}

function generateLocalChurchOffering(){
    var start_date = document.getElementById("from-date-offering").value;
    var end_date = document.getElementById("to-date-offering").value;
    var account_name_offering = document.getElementById("account-name-offering").value;

    var local_church_offering = {

    };

}



// Toggle Password Visibility
function passwordVisibility(){
	var x = document.getElementById("pass_local");
	var icon = document.getElementById("toggle-password");
	icon.classList.toggle("bi-eye");
	if (x.type === "password") {
		x.type = "text";
	} else {
		x.type = "password";
	}
}