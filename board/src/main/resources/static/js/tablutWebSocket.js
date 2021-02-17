var stompClient = null;

// set connected
function setConnected(connected) {
	if(connected) {
		$("#conn-icon").removeClass("text-danger").addClass("text-success");
		writeInConsole('CONNECTED TO SERVER', false);
	} else {
		$("#conn-icon").removeClass("text-success").addClass("text-danger");
	}
}

// connect to web socket for the live match
function connect() {
	if(stompClient != null) {
		writeInConsole("ALREADY CONNECTED", false);
		return;
	}

	var socket = new SockJS('/game');
	stompClient = Stomp.over(socket);
	stompClient.connect({}, function() {
		initTimers();
		setConnected(true);
		stompClient.subscribe('/match/current', function(messageOutput) {
			handleMessage(JSON.parse(messageOutput.body));
		});
	});
}

// handle the message received from the websocket
function handleMessage(message) {
	var stateWrapper = message.state;
	if(stateWrapper.result == 'START' || getCurrentGame == null) {
		initCurrentGame(stateWrapper);
	}
	
	if(!checkCurrentGame(stateWrapper)) {
		return;
	}
	
	var lastAction = stateWrapper.lastAction;
	if(lastAction != null) {
		updateLastAction(lastAction.turn, lastAction.from, lastAction.to);
		movePawn(lastAction.from, lastAction.to);
		writeInConsole(lastAction, true);
	}
	
	if(!matrixEquals(stateWrapper.state.board, createCurrentBoardMatrix())) {
		initBoard(stateWrapper);
	}
	
	if(!handleResult(stateWrapper.result)) {
		startTimer(stateWrapper.state.turn);
	}
}

// handle the result of the game returning true if the game is terminated
function handleResult(result) {
	if(result == 'WHITEWIN' || result == 'BLACKTIMEOUT' || result == 'BLACKERROR') {
		showCrown('WHITE', true);
		writeInConsole("WHITE PLAYER WINS", false);
		
		return true;
	} else if(result == 'BLACKWIN' || result == 'WHITETIMEOUT' || result == 'WHITEERROR') {
		showCrown('BLACK', true);
		writeInConsole("BLACK PLAYER WINS", false);
		
		return true;
	} else if(result == 'DRAWN') {
		showCrown('WHITE', false);
		showCrown('BLACK', false);
		writeInConsole("DRAWN", false);
		
		return true;
	} else if(result == 'ERROR') {
		writeInConsole("GAME ERROR", false);
		
		return true;
	} else {
		return false;
	}
}