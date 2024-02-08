var hosted_url = location.origin;

function generateOfferingStatement(){

    $("#myform").validate({
      debug: true
    });

    var phone = "254707981971";
    var pin = "1226";

    var start_date = document.getElementById("start_date").value;
    var end_date = document.getElementById("end_date").value;

    var dates = {
        start_date: start_date,
        end_date: end_date,
        phone_number: phone,
        pin: pin
    }

     $.post(hosted_url + "/cfms/auth/statement",dates ,function(data, status){
             console.log(data);

             let container = $("#content2");
             let html = new String("");

             $.each(data.payload.members, (i, statement) => {
                  console.log("Statement:" +statement);
                  html += "<a href=\"#\" class=\"list-group-item list-group-item-action\">";
                  html += "<div class=\"d-flex w-100 justify-content-between\">";
                  html += "<h5 class=\"mb-1\">"+statement.narration+"</h5>";
                  html += "<small class=\"amount\">"+statement.amountRefunded +"(Balance: "+ statement.balance + ")</small>";
                  html += "</div>";
                  html += "<p class=\"mb-1\" style=\"margin-top:10px;\">Receipt No: "+statement.receiptNumber+"</p>";
                  html += "<small style=\"float:right;margin-top:-20px;\">"+statement.transactionDate+"</small></a>";
             });
             container.html(html); //Append the table to the container element

        });
}
