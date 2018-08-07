/**
 * UI for jquery DatePicker
 */
  $( function() {	  
    $( "#datepicker" ).datepicker({ 
    	dateFormat: 'dd-mm-yy',
    	changeYear: true,
    	yearRange: "1960:"+new Date().getFullYear()
    		});
  } );