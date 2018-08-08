/**
 * Function to hide the top navigation bar on smaller devices when the OnScreen keyboard is active
 */
$(function (){
	$('input').focus(function (){
		$('.navbar.fixed-top').addClass('d-xs-none');
	});
});

/**
 * Function to show the top navigation bar on smaller devices when the OnScreen keyboard is inactive
 */
$(function (){
	$('input').blur(function (){
		$('.navbar.fixed-top').removeClass('d-xs-none');
	});
});