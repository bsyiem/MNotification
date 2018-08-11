function sendData(url,requestType,data){
	
	/*
	* The following two vars are for csrf related issues when posting data via ajax
	* the meta headers are included in the basic.jsp that is used as the default sitemesh decorator
	*/
	var csrfToken = $("meta[name='_csrf']").attr("content");
    var csrfHeader = $("meta[name='_csrf_header']").attr("content");
	
	//sets the csrf token before sending the ajax request
	$.ajaxSetup({
	    beforeSend: function(xhr) {
	        xhr.setRequestHeader(csrfHeader, csrfToken);
	    }
	});
	
	//sends a ajax post request
	$.ajax({
		url : url,
		type: requestType,
		data: data,
		success: function(msg){
			window.location.reload(true);
		}
	});
}