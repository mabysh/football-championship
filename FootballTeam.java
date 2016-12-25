package footballchampionship;

public class FootballTeam {
    private String name;
    private int games;
    private int scoredGoals;
    private int missedGoals;
	private int goalsDifference;
    private int points;

    public FootballTeam(String name) {
        this.name = name;
		this.games = 0;
		this.scoredGoals = 0;
		this.missedGoals = 0;
		this.goalsDifference = 0;
		this.points = 0;
    }

	public FootballTeam(String name, int games, int scoredGoals, int missedGoals, int goalsDifference, int points) {
		this.name = name;
		this.games = games;
		this.scoredGoals = scoredGoals;
		this.missedGoals = missedGoals;
		this.goalsDifference = goalsDifference;
		this.points = points;
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
        if (result.equals("WIN")) {
            points = points + 3;
        } else if (result.equals("DEADHEAT")) {
            points++;
        }
        games++;
        scoredGoals = scoredGoals + scored;
        missedGoals = missedGoals + missed;
        goalsDifference = scoredGoals - missedGoals;
    }
	
}
