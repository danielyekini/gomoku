let state;

async function loadState() {
    const res = await fetch('/api/state');
    state = await res.json();
    render();
}

async function newGame() {
    const res = await fetch('/api/new', {method: 'POST'});
    state = await res.json();
    render();
}

async function makeMove(x, y) {
    const res = await fetch('/api/move', {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify({x, y})
    });
    state = await res.json();
    render();
}

function render() {
    const boardElem = document.getElementById('board');
    boardElem.innerHTML = '';
    for (let y = 0; y < state.board.length; y++) {
        const row = document.createElement('tr');
        for (let x = 0; x < state.board[y].length; x++) {
            const cell = document.createElement('td');
            cell.addEventListener('click', () => makeMove(x, y));
            const val = state.board[y][x];
            if (val === 1) cell.textContent = '●';
            if (val === 2) cell.textContent = '○';
            row.appendChild(cell);
        }
        boardElem.appendChild(row);
    }
    const status = document.getElementById('status');
    status.textContent = state.winType !== 'NOWIN'
        ? `Winner: ${state.winType}`
        : `Current Player: ${state.currentPlayer}`;
}

document.getElementById('newGame').addEventListener('click', newGame);

newGame();