$(function (){
	var path = window.location.pathname;
	$('.nav-link[href = "'+path+'"]').parent().addClass('active');
});
