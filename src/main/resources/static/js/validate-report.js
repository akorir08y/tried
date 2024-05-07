function exportLocalChurch(){
    var start_date = document.getElementById("start_date_local").value;
    var end_date = document.getElementById("end_date_local").value;
    var pin_local = document.getElementById("pass_local").value;

    if(start_date == null || end_date == null){
        alert("Make Sure you input the start and end dates");
        return false;
    }

    if (start_date >= end_date){
        alert("The Start Date must predate the End date");
        return false;
    }

    if(pin == ""){
        alert("Make Sure you enter the Pin");
        return false;
    }
}