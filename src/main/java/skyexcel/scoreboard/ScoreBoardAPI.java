package skyexcel.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.scoreboard.*;

public class ScoreBoardAPI {
    private ScoreboardManager manager;

    private Scoreboard board;


    private Objective obj;

    private String name;

    private String criteria;

    public ScoreBoardAPI(String name, String criteria) {

        this.name = name;
        this.criteria = criteria;
    }

    public ScoreBoardAPI newScoreBoard(DisplaySlot displaySlot) {
        if (board == null) {
            manager = Bukkit.getScoreboardManager();
            board = manager.getNewScoreboard();
            Objective obj = board.registerNewObjective(name, criteria);
            obj.setDisplaySlot(displaySlot);
            this.obj = obj;
        }
        return this;
    }

    public ScoreBoardAPI newLine(String entry, int line) {
        Score score = obj.getScore(entry);
        score.setScore(line);

        return this;
    }

    public Scoreboard getBoard() {
        return board;
    }
}
