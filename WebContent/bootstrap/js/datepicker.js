            $(function() {
            	  
            	  var today = new Date();
            	  var dd = today.getDate();
            	  var mm = today.getMonth()+1; 
            	  var yyyy = today.getFullYear();
            	  if(dd<10){
            	        dd='0'+dd
            	    } 
            	    if(mm<10){
            	        mm='0'+mm
            	    } 

            	today = yyyy+'-'+dd+'-'+mm+' 00:00:00';
            	end = yyyy+'-'+dd+'-'+mm+' 23:23:59';
            	var dateFormat = "YYYY-DD-MM HH:mm:ss";
          	    var CurrDate = today;
          	    var MinDate = today;
          	    var MaxDate = end;
          	    var selected = document.getElementById('starttime').value
            	  dateCurr = moment(CurrDate, dateFormat);
            	  dateMin = moment(MinDate, dateFormat);
            	  dateMax = moment(MaxDate, dateFormat);
            	  
            	  $("#datetimepicker1").datetimepicker({
            	    format: dateFormat,
            	    date: dateCurr,
            	    minDate: dateMin,
            	    maxDate: dateMax,
            	    stepping: 15
            	  });
            	  
            	  $('#datetimepicker').datetimepicker({
            		format: dateFormat,
              	    date: dateCurr,
              	    minDate: dateMin,
              	    maxDate: dateMax,
              	    stepping: 15
                     
                  });
            	});