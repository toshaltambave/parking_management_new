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

            	today = yyyy+'-'+mm+'-'+dd+' 00:00:00';
            	end = yyyy+'-'+mm+'-'+dd+' 23:59:59';
            	var dateFormat = "YYYY-MM-DD HH:mm:ss";
          	    var CurrDate = today;
          	    var MinDate = today;
          	    var MaxDate = end;
          	    
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