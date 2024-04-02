var hosted_url = location.origin;

function validateTrustFundMonthForm() {
  var month = document.getElementbyId("select-month").value;
  if (month == "") {
    alert("Name must be filled out");
    return false;
  }
}

function nonTrustFundReportExcel(){
    var start_date = document.getElementById("from-date-non-trust").value;
    var end_date = document.getElementById("to-date-non-trust").value;
    var account_name = document.getElementById("account_name").value;
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;
    var phone_number = document.getElementById("phone_number").value;
    var input = "Excel"

    if (start_date == "" || end_date == "") {
        alert("All Fields Must be Filled Out");
        return false;
    }

    var non_trust_fund = {
        start_date: start_date,
        end_date: end_date,
        account_name: account_name,
        input: input,
        username: username,
        password: password,
        phone_number: phone_number
    }

    $.get(hosted_url + "/cfms/auth/exporting-non-trust-fund",non_trust_fund ,function(data, status){
        $(".responseDiv").show();
        $(".responseDiv").html("Export Excel is Successful");
        $(".responseDiv").fadeOut(3000);
    });
}

function nonTrustFundReportExcel(){
    var start_date = document.getElementById("from-date-non-trust").value;
    var end_date = document.getElementById("to-date-non-trust").value;
    var account_name = document.getElementById("account_name").value;
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;
    var phone_number = document.getElementById("phone_number").value;
    var input = "Excel"

    if (start_date == "" || end_date == "") {
        alert("All Fields Must be Filled Out");
        return false;
    }

    var non_trust_fund = {
        start_date: start_date,
        end_date: end_date,
        account_name: account_name,
        input: input,
        username: username,
        password: password,
        phone_number: phone_number
    }

    $.get(hosted_url + "/cfms/auth/exporting-non-trust-fund",non_trust_fund ,function(data, status){
        $(".responseDiv").show();
        $(".responseDiv").html("Export Excel is Successful");
        $(".responseDiv").fadeOut(3000);
    });
}


function nonTrustFundReportPDF(){
    var start_date = document.getElementById("from-date-non-trust").value;
    var end_date = document.getElementById("to-date-non-trust").value;
    var account_name = document.getElementById("account_name").value;
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;
    var phone_number = document.getElementById("phone_number").value;
    var input = "PDF"

    if (start_date == "" || end_date == "") {
        alert("All Fields Must be Filled Out");
        return false;
    }

    var non_trust_fund = {
        start_date: start_date,
        end_date: end_date,
        account_name: account_name,
        input: input,
        username: username,
        password: password,
        phone_number: phone_number
    }

    $.get(hosted_url + "/cfms/auth/exporting-non-trust-fund",non_trust_fund ,function(data, status){
        $(".responseDiv").show();
        $(".responseDiv").html("Export PDF is Successful");
        $(".responseDiv").fadeOut(3000);
    });
}



function tracingReportExcel(){
    var start_date = document.getElementById("from-date-transcript").value;
    var end_date = document.getElementById("to-date-transcript").value;
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;
    var phone_number = document.getElementById("phone_number").value;
    var input = "Excel"

    if (start_date == "" || end_date == "") {
        alert("All Fields Must be Filled Out");
        return false;
    }

    var non_trust_fund = {
        start_date: start_date,
        end_date: end_date,
        account_name: account_name,
        input: input,
        username: username,
        password: password,
        phone_number: phone_number
    }

    $.get(hosted_url + "/cfms/auth/exporting-transaction-tracing",non_trust_fund ,function(data, status){
        $(".responseDiv").show();
        $(".responseDiv").html("Export Excel is Successful");
        $(".responseDiv").fadeOut(3000);
    });
}


function tracingReportPDF(){
    var start_date = document.getElementById("from-date-transcript").value;
    var end_date = document.getElementById("to-date-transcript").value;
    var input = "PDF"
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;
    var phone_number = document.getElementById("phone_number").value;

    if (start_date == "" || end_date == "") {
        alert("All Fields Must be Filled Out");
        return false;
    }

    var non_trust_fund = {
        start_date: start_date,
        end_date: end_date,
        account_name: account_name,
        input: input,
        username: username,
        password: password,
        phone_number: phone_number
    }

    $.get(hosted_url + "/cfms/auth/exporting-transaction-tracing",non_trust_fund ,function(data, status){
        $(".responseDiv").show();
        $(".responseDiv").html("Export PDF is Successful");
        $(".responseDiv").fadeOut(3000);
    });
}


function showNonTrustFund(){
    var trust_fund_transcript = document.getElementById("trust_fund_transcript_report");
    var non_trust_fund_report = document.getElementById("non_trust_fund_report");
    var local_offering_report = document.getElementById("local_offering_report");
    var trust_summary_reports = document.getElementById("trust_summary_reports");
    var specific_account_summary_reports = document.getElementById("specific_account_summary_reports");
    var transactions_trace_summary_report = document.getElementById("transactions_trace_summary_report");

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

    local_offering_report.style.display = "none";
    trust_summary_reports.style.display = "none";
    non_trust_fund_report.style.display = "none";
    transactions_trace_summary_report.style.display = "none";
    trust_fund_transcript.style.display = "none";
    specific_account_summary_reports.style.display = "block";
}