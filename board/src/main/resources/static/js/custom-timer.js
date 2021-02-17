var $wTimer, wTimer;
var $bTimer, bTimer;
var startTime = 60;

// init the timers
function initTimers() {
	$wTimer = $('#w-timer');

	wTimer = new Timer({
		onstart: function(millisec) {
			var sec = Math.round(millisec / 1000);
			$wTimer.text(sec);
		},
		ontick: function(millisec) {
			var sec = Math.round(millisec / 1000);
			$wTimer.text(sec);
		},
		onstop: function() {
			$wTimer.text(startTime);
		},
		onend: function() {
			$wTimer.text('0');
		}
	});

	$bTimer = $('#b-timer');

	bTimer = new Timer({
		onstart: function(millisec) {
			var sec = Math.round(millisec / 1000);
			$bTimer.text(sec);
		},
		ontick: function(millisec) {
			var sec = Math.round(millisec / 1000);
			$bTimer.text(sec);
		},
		onstop: function() {
			$bTimer.text(startTime);
		},
		onend: function() {
			$bTimer.text('0');
		}
	});
}

// start 'WHITE' or 'BLACK' timer
function startTimer(player) {
	if(player == 'WHITE') {
		wTimer.start(startTime);
	}
	
	if(player == 'BLACK') {
		bTimer.start(startTime);
	}
	return;
}

// stop 'WHITE' or 'BLACK' timer
function stopTimer(player) {
	if(player == 'WHITE') {
		wTimer.stop();
	}
	
	if(player == 'BLACK') {
		bTimer.stop();
	}
	return;

}

// restart 'WHITE' or 'BLACK' timer
function restartTimer(player) {
	if(player == 'WHITE') {
		wTimer.stop();
		wTimer.start(startTime);
	}
	
	if(player == 'BLACK') {
		bTimer.stop();
		bTimer.start(startTime);
	}
	return;

}