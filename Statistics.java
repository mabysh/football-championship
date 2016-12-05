package footballchampionship;

import java.util.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

public class Statistics {
    private JFrame frame;
    private JPanel cardsPanel, buttonsPanel, infoPanel, tablePanel,
        addPanel, savePanel;
    private JButton infoButton, tableButton, addButton, saveButton;
    private JTable infoTable, tableTable;
	private JScrollPane jsp;

    static final String INFOPANEL = "Team Information";
    static final String TABLEPANEL = "Results Table";
    static final String ADDPANEL = "Add Game Results";
    static final String SAVEPANEL = "Save to File";

    ButtonListener bl = new ButtonListener();

    private FootballTeam chalsea, manUtd, arsenal, totten, liverp, 
	    manCity, aston, sunder, stoke, wigan, burnley, bolton,
	     fulham, everton, birmin, blackb, wolver, hull, westHam, 
	     ports;

    private String[] columnNames = { "Position",
					    			"Team",
									"Games",
									"Goals Difference",
									"Points"};


	private FootballTeam[] teamArray = { chalsea, manUtd, arsenal, totten, liverp, manCity, aston, sunder, stoke, wigan, burnley, bolton, fulham, everton, birmin, blackb, wolver, hull, westHam, ports };

    public static void main(String[] args) {
        Statistics stat = new Statistics();
        stat.setUpGui();
    }

    class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            CardLayout cl = (CardLayout)cardsPanel.getLayout();
            if (e.getSource() == infoButton) {
                cl.show(cardsPanel, (String)INFOPANEL);
            } else if (e.getSource() == tableButton) {
                cl.show(cardsPanel, (String)TABLEPANEL);
            } else if (e.getSource() == addButton) {
                cl.show(cardsPanel, (String)ADDPANEL);
            } else {
                cl.show(cardsPanel, (String)SAVEPANEL);
            }
        }
    }

    class TheTableModel extends AbstractTableModel {
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
				case 0 : result = (row + 1);
						 break;
				case 1 : result = t.getName();
						 break;
				case 2 : result = t.getGames();
						 break;
				case 3 : result = t.getGoalsDifference();
						 break;
				case 4 : result = t.getPoints();
						 break;
				default : result = 0;
			}
				return result;
			
        }
		public String getColumnName(int col) {
			return columnNames[col];
		}
    }

    private void setUpGui() {
        createComponents();
        addComponents();
        registerListeners();
	createTeams();

    }
    private void createComponents() {
        //set up the frame
        frame = new JFrame("Footbal Championship Statistics");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 500);
        //create panels
        buttonsPanel = new JPanel(new GridLayout(1,4));
        cardsPanel = new JPanel(new CardLayout());
        infoPanel = new JPanel(new BorderLayout());
        infoPanel.setBackground(Color.BLUE);
        tablePanel = new JPanel();
        tablePanel.setBackground(Color.GREEN);
        addPanel = new JPanel();
        addPanel.setBackground(Color.RED);
        savePanel = new JPanel();
        savePanel.setBackground(Color.YELLOW);
        //create main buttons
        infoButton = new JButton("Team Information");
        tableButton = new JButton("Results Table");
        addButton = new JButton("Add Game Result");
        saveButton = new JButton("Save To File");
		//creating table
		JTable jt = new JTable(10, 3);
		TheTableModel mymodel = new TheTableModel();
		infoTable = new JTable(mymodel);
		jsp = new JScrollPane(jt);
		infoTable.setFillsViewportHeight(true);
    }
    private void addComponents() {
        //add panels
        frame.add(BorderLayout.NORTH, buttonsPanel);
        frame.add(BorderLayout.CENTER, cardsPanel);
        cardsPanel.add(infoPanel, INFOPANEL);
        cardsPanel.add(tablePanel, TABLEPANEL);
        cardsPanel.add(addPanel, ADDPANEL);
        cardsPanel.add(savePanel, SAVEPANEL);
        //add buttons
        buttonsPanel.add(infoButton);
        buttonsPanel.add(tableButton);
        buttonsPanel.add(addButton);
        buttonsPanel.add(saveButton);
		//setting up info panel
		infoPanel.add(Box.createHorizontalStrut(20), BorderLayout.EAST);
		infoPanel.add(Box.createHorizontalStrut(20), BorderLayout.WEST);
		infoPanel.add(Box.createVerticalStrut(20), BorderLayout.NORTH);
		infoPanel.add(Box.createVerticalStrut(20), BorderLayout.SOUTH);
		infoPanel.add(jsp, BorderLayout.CENTER);

		frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    private void registerListeners() {
        //registrating action listeners
        infoButton.addActionListener(bl);
        tableButton.addActionListener(bl);
        addButton.addActionListener(bl);
        saveButton.addActionListener(bl);
    }
    private void createTeams() {
	chalsea = new FootballTeam("Chalsea");	
	manUtd = new FootballTeam("Man Utd");	
	arsenal = new FootballTeam("Arsenal");	
	totten = new FootballTeam("Tottenham");	
	liverp = new FootballTeam("Liverpool");	
	manCity = new FootballTeam("Man City");	
	aston = new FootballTeam("Aston Villa");	
	sunder = new FootballTeam("Sunderland");	
	stoke = new FootballTeam("Stoke");	
	wigan = new FootballTeam("Wigan");	
	burnley = new FootballTeam("Burnley");	
	bolton = new FootballTeam("Bolton");	
	fulham = new FootballTeam("Fulham");	
	everton = new FootballTeam("Everton");	
	birmin = new FootballTeam("Birmingham");	
	blackb = new FootballTeam("Blackburn");	
	wolver = new FootballTeam("Wolverham");	
	hull = new FootballTeam("Hull");	
	westHam = new FootballTeam("West Ham");	
	ports = new FootballTeam("Portsmouth");	

    }
    
}

//sentance that does not mean anything


	
