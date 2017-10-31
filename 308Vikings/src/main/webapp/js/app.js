var main = function() {

$('#user-display-name a').click(function(){
	$('.pages').css("display","none");
    $('#profilepage').show();
});

$('ul.musictabs li').click(function(){
	var tab_id = $(this).attr('data-tab');

	$('ul.musictabs li').removeClass('current');
	$('.musictab-content').removeClass('current');

	$(this).addClass('current');
	$("#"+tab_id).addClass('current');
});

$('ul.tabs li').click(function(){
	var tab_id = $(this).attr('data-tab');

	$('ul.tabs li').removeClass('current');
	$('.tab-content').removeClass('current');

	$(this).addClass('current');
	$("#"+tab_id).addClass('current');
});

$('#browserTabs li').click(function(){
	$('.pages').css("display","none");
    $('#musicpage').show();

});


$('div.menutabs li').click(function(){
	var tab_id = $(this).attr('data-tab');
	$('div.menutabs li').removeClass('current');
	$('.menutab-content').removeClass('current');

	$(this).addClass('current');
	$("#"+tab_id).addClass('current');

	if($(this).attr('data-tab') == 'menutab-1'){
		$('.pages').css("display","none");
    	$('#recentlyplayedpage').show();
	}
	if($(this).attr('data-tab') == 'menutab-2'){
		$('.pages').css("display","none");
    	$('#songpage').show();
	}	
	if($(this).attr('data-tab') == 'menutab-3'){
		$('.pages').css("display","none");
    	$('#albumpage').show();
	}	
	if($(this).attr('data-tab') == 'menutab-4'){
		$('.pages').css("display","none");
    	$('#artistpage').show();
	}
	if($(this).attr('data-tab') == 'menutab-5'){
		$('.pages').css("display","none");
    	$('#concertpage').show();
	}	
});

$('.newPlaylist').click(function(){
	$('.pages').css("display","none");
    $('#playListPage').show();
});

$('.albumitems').click(function(){
	$('.pages').css("display","none");
    $('#indivAlbumPage').show();

});

$('.viewFullAlbum').click(function(){
	$('.pages').css("display","none");
    $('#indivAlbumPage').show();
});

$('.artistitems').click(function(){
	$('.pages').css("display","none");
    $('#libindivArtistPage').show();
});

$('.viewFullArtist').click(function(){
	$('.pages').css("display","none");
    $('#indivArtistPage').show();
});

$('.concerttable').click(function(){
	$('.pages').css("display","none");
    $('#indivConcertPage').show();

});


$('.dropdownbtn').click(
	function(){

	if($(this).hasClass('open')){
		$('.user-dropdown-menu').css("display", "none");
		$(this).removeClass('open');
	}
	else{
		$('.user-dropdown-menu').css("display", "block");
		$(this).addClass('open');
	}
});

$('.user-dropdown-menu').click(
	function(){
		$(this).removeClass('open');
		$('.user-dropdown-menu').css("display", "none");
});


$('.registerbtn').click(function(){
	$('.signup').css("display","block");
});

$('.login, .signupbtn').click(function(){
	$('.appPage').css("display", "block");
	$('.startUpPage').css("display", "none");
});

play = $('#play');
pause = $('#pause');
mute = $('#mute');
muted = $('#muted');
song = new Audio('download/AlanWalker-Fade.mp3');
duration = song.duration;

play.on('click', function(e) {
		e.preventDefault();
		if(song.paused){
			song.play();
		}
		else{
			song.pause();
		}
});

		
}

$(document).ready(main);
