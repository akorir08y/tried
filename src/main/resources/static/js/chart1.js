var hosted_url = location.origin;

function getPreviousMonthName(dt){
     monthNamelist = [ "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" ];
    return monthNamelist[dt.getMonth()-1];
};

var ctx = document.getElementById('myChart5');
var ctx1 = document.getElementById('myChart7');
var ctx2 = document.getElementById('myChart6');

// Personal Information
var phone_number = document.getElementById("phone_number").value;
var username = document.getElementById("username").value;
var password = document.getElementById("password").value;

var personal = {
    phone_number: phone_number,
    username: username,
    password: password
};

$.get(hosted_url + "/cfms/auth/report/local_church_offering",personal,function(data, status){
    var local_church_funds = data.localChurchFunds;
    var trust_funds = data.localTrustFunds;
    var local_church_keys = Object.keys(local_church_funds);
    var local_church_values = Object.values(local_church_funds);
    var trust_funds_keys = Object.keys(trust_funds);
    var trust_funds_values = Object.values(trust_funds);
    var sized = data.transactions - 2;
    var local_funds = local_church_keys.length;
    var colors = [];
    var colors1 = [];

    document.getElementById("transactions2").innerHTML = sized;
    document.getElementById("total_offering_amount").innerHTML = "Kshs: " + data.totalAmount;
    document.getElementByid("total_offering_amount").innerHTML = "Kshs: " + data.USSD;
    document.getElementByid("total_offering_amount").innerHTML = "Kshs: " + data.Cash;

    for (var i=0;i<local_church_keys.length;i++){
        colors.push('rgba(54, 162, 235, 1)');
    }

    for (var i=0;i<trust_funds_keys.length;i++){
        colors1.push('rgba(54, 162, 235, 1)');
    }

      new Chart(ctx, {
        type: 'pie',
        data: {
          labels: ['Cash', 'USSD',],
          datasets: [{
            label: 'Kshs',
            data: [data.Cash, data.USSD],
            backgroundColor:[
            'rgba(0, 0, 0, 0.35)',
            'rgba(54, 162, 235, 1)'],
          }]
        },
        options: {
            responsive: true,
            maintainAspectRatio: false,
            events: ['mousemove', 'mouseout', 'click', 'touchstart', 'touchmove'],
            plugins: {
              legend: {
                position: 'top',
            },
            title: {
                display: true,
                text: 'Local Church Offering USSD and Cash Distribution'
            },
            tooltip: {
                mode: 'index',
                intersect: false
            }
            }
        }
        ,animations: {
          tension: {
            duration: 1000,
            easing: 'easeInOutBounce',
            from: 1,
            to: 0,
            loop: true
          }
        }

      });

       new Chart(ctx1, {
        type: 'bar',
        data: {
          labels: local_church_keys,
          datasets: [{
            label: 'Kshs',
            data: local_church_values,
            backgroundColor:colors,
          }]
        },
        options: {
            maintainAspectRatio: true,
            responsive: true,
            plugins: {
              tooltip: {
                mode: 'index',
                intersect: false
              },
              title: {
                display: true,
                text: 'Local Church Non Trust Fund Distribution'
              }
            },
            hover: {
              mode: 'index',
              intersec: false
            },
            scales: {
              x: {
                title: {
                  display: true,
                  text: 'Local Church Non Trust Fund Accounts'
                }
              },
              y: {
                title: {
                  display: true,
                  text: 'Kshs'
                },
                // the data minimum used for determining the ticks is Math.min(dataMin, suggestedMin)
                suggestedMin: 500,

                // the data maximum used for determining the ticks is Math.max(dataMax, suggestedMax)
                suggestedMax: 500,
              }
            }
        }
        ,animations: {
          tension: {
            duration: 1000,
            easing: 'easeInOutBounce',
            from: 1,
            to: 0,
            loop: true
          }
        },
        events: ['mousemove', 'mouseout', 'click', 'touchstart', 'touchmove'],
      });

       new Chart(ctx2, {
        type: 'bar',
        data: {
          labels: trust_funds_keys,
          datasets: [{
            label: 'Kshs',
            data: trust_funds_values,
            backgroundColor:colors1,
          }]
        },
        options: {
            maintainAspectRatio: true,
            responsive: true,
            plugins: {
              tooltip: {
                mode: 'index',
                intersect: false
              },
              title: {
                display: true,
                text: 'Local Church Trust Fund Distribution'
              }
            },
            hover: {
              mode: 'index',
              intersec: false
            },
            scales: {
              x: {
                title: {
                  display: true,
                  text: 'Local Church Trust Fund Accounts'
                }
              },
              y: {
                title: {
                  display: true,
                  text: 'Kshs'
                },
                // the data minimum used for determining the ticks is Math.min(dataMin, suggestedMin)
                suggestedMin: 500,

                // the data maximum used for determining the ticks is Math.max(dataMax, suggestedMax)
                suggestedMax: 500,
              }
            }
        }
        ,animations: {
          tension: {
            duration: 1000,
            easing: 'easeInOutBounce',
            from: 1,
            to: 0,
            loop: true
          }
        },
        events: ['mousemove', 'mouseout', 'click', 'touchstart', 'touchmove'],
      });
});

