package footballchampionship;

public class FootballTeam {
    private String name;
    private int games = 0;
    private int scoredGoals = 0;
    private int missedGoals = 0;
    private int goalsDifference;
    private int points = 0;

    public FootballTeam(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public int getGames() {
        return games;
    }
    public int getScoredGoals() {
        return scoredGoals;
    }
    public int getMissedGoals() {
        return missedGoals;
    }
    public int getGoalsDifference() {
        return goalsDifference;
    }
    public int getPoints() {
        return points;
    }

    public void matchPlayed(String result, int scored, int missed) {
        if (result.equals("win")) {
            points = points + 3;
        } else if (result.equals("deadheat")) {
            points++;
        }
        games++;
        scoredGoals = scoredGoals + scored;
        missedGoals = missedGoals + missed;
        goalsDifference = scoredGoals - missedGoals;
    }

}
