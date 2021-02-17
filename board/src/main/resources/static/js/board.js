var currentGame;
var boardConsole;

// simple init board function
function initBoard(stateWrapper) {
	var board = stateWrapper.state.board;
	if (board == undefined) {
		return;
	}

	var alph = ["a", "b", "c", "d", "e", "f", "g", "h", "i"];
	for (var j = 0; j < board.length; j++) {
		for (var i = 0; i < alph.length; i++) {
			var cell = $('#' + alph[i] + (j + 1));
			var type = board[j][i];

			if (type != 'EMPTY' && type != 'THRONE') {
				var icon = cell.find("i");
				if (icon.length > 0) {
					icon.addClass('d-none');
				}
			} else {
				var icon = cell.find("i");
				if (icon.length > 0) {
					icon.removeClass('d-none');
				}
			}

			var cellImg = cell.find("img");
			if (cellImg.length > 0) {
				cellImg.remove();
			}

			cell.append(newPawn(type));
		}
	}
}

// simple move pawn function
function movePawn(from, to) {
	var fromCell = $("#" + from);
	var toCell = $("#" + to);

	if (fromCell == undefined || toCell == undefined) {
		return;
	}

	var fromImg = fromCell.find("img");
	if (fromImg.length == 0) {
		return;
	}

	var fromIcon = fromCell.find("i");
	if (fromIcon.length > 0) {
		fromIcon.removeClass('d-none');
	}

	var toImg = toCell.find("img");
	if (toImg.length > 0) {
		toImg.remove();
	}

	var toIcon = toCell.find("i");
	if (toIcon.length > 0) {
		toIcon.addClass('d-none');
	}

	fromImg.appendTo(toCell);
}

// remove pawn from a cell
function removePawn(from) {
	var fromCell = $("#" + from);
	if (fromCell.length == 0) {
		return;
	}

	var fromImg = fromCell.find("img");
	if (fromImg.length == 0) {
		return;
	}

	fromImg.remove();

	var fromIcon = fromCell.find("i");
	if (fromIcon.length > 0) {span
		fromIcon.removeClass('d-none');
	}
}

// create new pawn based on type
function newPawn(type) {
	if (type == 'BLACK') return '<img class="pawn-1a3e" src="images/black.png"></img>';

	if (type == 'WHITE') return '<img class="pawn-1a3e" src="images/white.png"></img>';

	if (type == 'KING') return '<img class="pawn-1a3e" src="images/king.png"></img>';

	if (type == 'EMPTY') return '';

	return;
}

// express a pawn by type ('BLACK', 'WHITE', 'KING', 'THRONE')
function createType(pawn) {
	if (pawn.attr('src').endsWith('images/black.png')) return 'BLACK';

	if (pawn.attr('src').endsWith('images/white.png')) return 'WHITE';

	if (pawn.attr('src').endsWith('images/king.png')) return 'KING';

	return 'EMPTY';
}

// create the board matrix from the current state
function createCurrentBoardMatrix() {
	var board = [];
	for (var i = 0; i < 9; i++) {
		board[i] = new Array(9);
	}
	var alph = ["a", "b", "c", "d", "e", "f", "g", "h", "i"];
	for (var j = 0; j < 9; j++) {
		for (var i = 0; i < alph.length; i++) {
			var cell = $('#' + alph[i] + (j + 1));
			var icon = cell.find("i");
			var value = 'EMPTY';
			if (icon.length > 0 && icon.hasClass('fa-plus')) {
				value = 'THRONE';
			}

			var cellImg = cell.find("img");
			if (cellImg.length > 0) {
				value = createType(cellImg);
			}
			board[j][i] = value;
		}
	}

	return board;
}

// simple equals between arrays
function arrayEquals(a, b) {
	return Array.isArray(a) &&
		Array.isArray(b) &&
		a.length === b.length &&
		a.every((val, index) => val === b[index]);
}

// simple equals between matrix
function matrixEquals(m1, m2) {
	return m1.every((val, index) => arrayEquals(val, m2[index]));
}

// insert 'WHITE' or 'BLACK' player name in the corresponding span
function insertName(name, player) {
	if (player == 'WHITE' || player == 'BLACK') {
		$('#' + (player == 'WHITE' ? 'w' : 'b') + '-name').text(name);
	} else {
		return;
	}
}

// update the spawn for 'WHITE' or 'BLACK' player
function updateLastAction(player, from, to) {
	if (player == 'WHITE' || player == 'BLACK') {
		var lastAct = $('#' + (player == 'WHITE' ? 'w' : 'b') + '-last-act');
		lastAct.removeClass('d-none');
		lastAct.find('.from').html(from);
		lastAct.find('.to').html(to);
		
		stopTimer(player);
	} else {
		return;
	}
}

// return the currentGame
function getCurrentGame() {
	return currentGame;
}

// initialize currentGame variable
function initCurrentGame(stateWrapper) {
	currentGame = new Object();
	currentGame.uuid = stateWrapper.uuid;
	currentGame.whitePlayerName = stateWrapper.whitePlayerName;
	currentGame.blackPlayerName = stateWrapper.blackPlayerName;
	
	insertName(currentGame.whitePlayerName, 'WHITE');
	insertName(currentGame.blackPlayerName, 'BLACK');
	
	writeInConsole("BOARD INITIALIZED", false);
}

// check if the currentGame correspond to stateWrapper
function checkCurrentGame(stateWrapper) {
	if (currentGame == null)
		return false;

	return currentGame.uuid == stateWrapper.uuid &&
		currentGame.whitePlayerName == stateWrapper.whitePlayerName && 
		currentGame.blackPlayerName == stateWrapper.blackPlayerName;
}

// show the crown for 'WHITE' or 'BLACK' player
function showCrown(player, other) {
	if (player == 'WHITE' || player == 'BLACK') {
		$('#' + (player == 'WHITE' ? 'w' : 'b') + '-crown').removeClass("d-none");
		if(other) $('#' + (player == 'WHITE' ? 'b' : 'w') + '-crown').addClass("d-none");
	} else {
		return;
	}
}

// wirte on simple console
function writeInConsole(text, jsonobj) {
	if(boardConsole == null) {
		boardConsole = $('#board-console');
	}
	
	boardConsole.find('.blank-line').remove();
	if(jsonobj) {
		boardConsole.append('<div class="board-console-line"><pre class="text-light">$ ' + JSON.stringify(text, undefined, 2) + '</pre></div>');
	} else {
		boardConsole.append('<div class="board-console-line text-light">$ ' + text + '</div>');
	}
	boardConsole.append('<div class="board-console-line blank-line">$ _</div>');
	boardConsole.scrollTop(boardConsole.scrollTop() + $(".blank-line").offset().top);
}

// stupid download inner text
function downloadInnerText(filename, elId, mimeType) {
    var elHtml = $('#' + elId).text();
    var link = document.createElement('a');
    mimeType = mimeType || 'text/plain';

    link.setAttribute('download', filename);
    link.setAttribute('href', 'data:' + mimeType + ';charset=utf-8,' + encodeURIComponent(elHtml));
    link.click(); 
}
