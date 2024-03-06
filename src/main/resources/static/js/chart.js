var hosted_url = location.origin;

function getPreviousMonthName(dt){
     monthNamelist = [ "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" ];
    return monthNamelist[dt.getMonth()-1];
};


(async function() {
        const ctx = document.getElementById('myChart');
        const ctx1 = document.getElementById('myChart1');

        var date = new Date(); // date object
        var mName = getPreviousMonthName(date);

         $.get(hosted_url + "/cfms/auth/church-trust-funds" ,function(data, status){
                console.log(data);

                var uniqueChars = [...new Set(data.payload.transactions)];
                var transactions = uniqueChars.length - 3;
                document.getElementById("transactions").innerHTML = transactions;

                var ussd = {};
                var total = {};
                var cash = {};
                for(var i=0; i<data.payload.transactions.length; i++){
                    if(data.payload.transactions[i].modeOfPayment == null){
                        total = data.payload.transactions[i];
                        var total_receipted = total.totalReceiptedAmount.split(".");
                        var total_receipted_paid = total.totalReceiptedAmountPaid.split(".");
                        document.getElementById("total_amount").innerHTML = "Kshs: "+ total_receipted[0] + ".0";
                        document.getElementById("total_amount_paid").innerHTML = "Kshs: "+ total_receipted_paid[0] + ".0";
                    }

                    if(data.payload.transactions[i].modeOfPayment == "USSD"){
                        ussd = data.payload.transactions[i];
                    }

                    if(data.payload.transactions[i].modeOfPayment == "Cash"){
                        cash = data.payload.transactions[i];
                    }
                }

                new Chart(ctx, {
                    type: 'polarArea',
                    data: {
                      labels: ['USSD', 'Cash'],
                      datasets: [{
                        label: 'Kshs',
                        data: [ussd.totalReceiptedAmount, cash.totalReceiptedAmount],
                		backgroundColor:[
                		'rgba(255, 99, 132, 1)',
                		'rgba(54, 162, 235, 1)']
                      }]
                    },
                    options: {
                        responsive: true,
                        maintainAspectRatio: false,
                        plugins: {
                            legend: {
                        		position: 'top',
                            },
                        	title: {
                        		display: true,
                        		text: 'USSD and Cash Distribution in '+mName
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
                      labels: ['campMeeting','campMeetingPaid', 'combinedOfferings','combinedOfferingsPaid','tithe','tithePaid', 'thirteenthSabbath',
                      'thirteenthSabbathPaid', 'conferenceDevelopment', 'conferenceDevelopmentPaid', 'totalReceiptedAmount', 'totalReceiptedAmountPaid',
                       'balance'],
                      datasets: [{
                        label: 'Kshs',
                        data: [total.campMeeting, total.campMeetingPaid,total.combinedOfferings, total.combinedOfferingsPaid,
                        total.tithe, total.tithePaid, total.thirteenthSabbath, total.thirteenthSabbathPaid, total.conferenceDevelopment,
                        total.conferenceDevelopmentPaid, total.totalReceiptedAmount, total.totalReceiptedAmountPaid, total.balance],
                		backgroundColor:[
                		'rgba(255, 99, 132, 1)',
                		'rgba(54, 162, 235, 1)',
                		'rgba(255, 206, 86, 1)',
                		'rgba(253, 90, 102, 1)',
                		'rgba(52, 42, 135, 1)',
                		'rgba(245, 106, 36, 1)',
                		'rgba(250, 39, 42, 1)',
                		'rgba(59, 192, 205, 1)',
                		'rgba(230, 86, 86, 1)',
                		'rgba(220, 99, 99, 1)',
                		'rgba(32, 180, 135, 1)',
                		'rgba(205, 159, 34, 1)',
                		'rgba(215, 203, 16, 1)'],
                      }]
                    },
                    options: {
                        maintainAspectRatio: false,
                        responsive: true,
                        plugins: {
                            tooltip: {
                        	mode: 'index',
                        	intersect: false
                        },
                        title: {
                        		display: true,
                        		text: 'Total Funds Distribution in '+mName
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
                        		text: 'Funds'
                        	     }
                        	  },
                        	  y: {
                        		title: {
                        	    display: true,
                        	    text: 'Kshs * 500'
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
})();


/*
(async function() {
        const ctx = document.getElementById('myChart1');
        const ctx1 = document.getElementById('myChart2');

        var date = new Date(); // date object
        var mName = getPreviousMonthName(date);

         $.get(hosted_url + "/cfms/auth/church-trust-funds" ,function(data, status){
                console.log(data);

                var uniqueChars = [...new Set(data.payload.transactions)];
                var transactions = uniqueChars.length - 3;
                document.getElementById("transactions").innerHTML = transactions;

                var ussd = {};
                var total = {};
                var cash = {};
                for(var i=0; i<data.payload.transactions.length; i++){
                    if(data.payload.transactions[i].modeOfPayment == null){
                        total = data.payload.transactions[i];
                        var total_receipted = total.totalReceiptedAmount.split(".");
                        var total_receipted_paid = total.totalReceiptedAmountPaid.split(".");
                        document.getElementById("total_amount1").innerHTML = "Kshs: "+ total_receipted[0] + ".0";
                        document.getElementById("total_amount_paid1").innerHTML = "Kshs: "+ total_receipted_paid[0] + ".0";
                    }

                    if(data.payload.transactions[i].modeOfPayment == "USSD"){
                        ussd = data.payload.transactions[i];
                    }

                    if(data.payload.transactions[i].modeOfPayment == "Cash"){
                        cash = data.payload.transactions[i];
                    }
                }

                new Chart(ctx, {
                    type: 'polarArea',
                    data: {
                      labels: ['USSD', 'Cash'],
                      datasets: [{
                        label: 'Kshs',
                        data: [ussd.totalReceiptedAmount, cash.totalReceiptedAmount],
                		backgroundColor:[
                		'rgba(255, 99, 132, 1)',
                		'rgba(54, 162, 235, 1)']
                      }]
                    },
                    options: {
                        responsive: true,
                        maintainAspectRatio: false,
                        plugins: {
                            legend: {
                        		position: 'top',
                            },
                        	title: {
                        		display: true,
                        		text: 'USSD and Cash Distribution in '+mName
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
                      labels: ['Local Combined Offerings','Total Amount'],
                      datasets: [{
                        label: 'Kshs',
                        data: [total.campMeeting, total.campMeetingPaid,total.combinedOfferings, total.combinedOfferingsPaid,
                        total.tithe, total.tithePaid, total.thirteenthSabbath, total.thirteenthSabbathPaid, total.conferenceDevelopment,
                        total.conferenceDevelopmentPaid, total.totalReceiptedAmount, total.totalReceiptedAmountPaid, total.balance],
                		backgroundColor:[
                		'rgba(255, 99, 132, 1)',
                		'rgba(54, 162, 235, 1)',
                		],
                      }]
                    },
                    options: {
                        maintainAspectRatio: false,
                        responsive: true,
                        plugins: {
                            tooltip: {
                        	mode: 'index',
                        	intersect: false
                        },
                        title: {
                        		display: true,
                        		text: 'Total Funds Distribution in '+mName
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
                        		text: 'Funds'
                        	     }
                        	  },
                        	  y: {
                        		title: {
                        	    display: true,
                        	    text: 'Kshs * 500'
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
})();
*/