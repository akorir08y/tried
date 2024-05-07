function showTrustFunds(){
    var trust_fund_summary = document.getElementById("trust_fund_summary");
    var non_trust_fund_summary = document.getElementById("non_trust_fund_summary");
    var local_church_offering_summary = document.getElementById("local_church_offering_summary");

    non_trust_fund_summary.style.display = "none";
    local_church_offering_summary.style.display = "none";
    trust_fund_summary.style.display = "block";
}

function showNonTrustFunds(){
    var trust_fund_summary = document.getElementById("trust_fund_summary");
    var non_trust_fund_summary = document.getElementById("non_trust_fund_summary");
    var local_church_offering_summary = document.getElementById("local_church_offering_summary");

    trust_fund_summary.style.display = "none";
    local_church_offering_summary.style.display = "none";
    non_trust_fund_summary.style.display = "block";
}

function showLocalChurchOffering(){
    var local_church_offering_summary = document.getElementById("local_church_offering_summary");
    var trust_fund_summary = document.getElementById("trust_fund_summary");
    var non_trust_fund_summary = document.getElementById("non_trust_fund_summary");

    local_church_offering_summary.style.display = "block";
    trust_fund_summary.style.display = "none";
    non_trust_fund_summary.style.display = "none";
}

function showAllReports(){
    var local_church_offering_summary = document.getElementById("local_church_offering_summary");
    var trust_fund_summary = document.getElementById("trust_fund_summary");
    var non_trust_fund_summary = document.getElementById("non_trust_fund_summary");

    local_church_offering_summary.style.display = "block";
    trust_fund_summary.style.display = "block";
    non_trust_fund_summary.style.display = "block";
}