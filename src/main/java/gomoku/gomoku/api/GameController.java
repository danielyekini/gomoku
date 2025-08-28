package gomoku.gomoku.api;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class GameController {
    private final GameService service;

    public GameController(GameService service) {
        this.service = service;
    }

    @PostMapping("/new")
    public GameState newGame() {
        return service.newGame();
    }

    @GetMapping("/state")
    public GameState getState() {
        return service.getState();
    }

    @PostMapping("/move")
    public GameState makeMove(@RequestBody Move move) {
        return service.play(move.x, move.y);
    }

    public record Move(int x, int y) {}
}