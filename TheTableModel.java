package javadev.footballchampionship;

import javax.swing.table.AbstractTableModel;

public class TheTableModel extends AbstractTableModel {
    private String[] columnNames = { "Position", "Team", "Games", "Goals Difference", "Points"};
	private FootballTeam[] teamArray;

	public TheTableModel(FootballTeam[] teamArray) {
		this.teamArray = teamArray;
	}
	public int getRowCount() {
		return teamArray.length;
	}
	public int getColumnCount() {
		return columnNames.length;
	}
	public Object getValueAt(int row, int column) {
		FootballTeam t = teamArray[row];
		Object result;
		switch (column) {
			case 0 : result = t.getIndex();
					 break;
			case 1 : result = t.getName();
					 break;
			case 2 : result = t.getGames();
					 break;
			case 3 : result = t.getGoalsDifference();
					 break;
			case 4 : result = t.getPoints();
					 break;
			default : result = "***";
		}
		return result;
	}
	public String getColumnName(int col) {
		return columnNames[col];
	}
	public void setValueAt(Object value, int row, int col) {
		FootballTeam t = teamArray[row];
		if (col == 0) {
			t.setIndex((int) value);
		}
	}
}

