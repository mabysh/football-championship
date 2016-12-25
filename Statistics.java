package footballchampionship;

import java.io.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

public class Statistics {
    private JFrame frame;
    private JPanel cardsPanel, buttonsPanel, infoPanel, tablePanel, matchPanel, matchPanelCenter, matchPanelCenter2, matchPanelCenter3;
    private JButton infoButton, tableButton, addButton, saveButton, addResults;
    private JTable infoTable, tableTable;
    private JScrollPane jsp, jsList1, jsList2;
	private JList<String> teamList1, teamList2;
	private JTextField goals1, goals2;	
	private JLabel vs, versus;
	private Font bigFont, middleFont;

    static final String INFOPANEL = "Team Information";
    static final String TABLEPANEL = "Results Table";
    static final String ADDPANEL = "Add Game Results";

    ButtonListener bl = new ButtonListener();
	ListListener ll = new ListListener();

    private FootballTeam chalsea, manUtd, arsenal, totten, liverp, manCity, aston, sunder, stoke, wigan, burnley, bolton, fulham, everton, birmin, blackb, wolver, hull, westHam, ports;

    private String[] columnNames = { "Position", "Team", "Games", "Goals Difference", "Points"};


    private FootballTeam[] teamArray  = {chalsea, manUtd, arsenal, totten, liverp, manCity, aston, sunder, stoke, wigan, burnley, bolton, fulham, everton, birmin, blackb, wolver, hull, westHam, ports};
	private String[] listEntries = new String[20];

    private File teamData = new File("teamData.txt");

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
            } else if (e.getSource() == saveButton) {
				writeData();
            } else if (e.getSource() == addResults) {
				addMatchResults();
			}

        }
    }

	class ListListener implements ListSelectionListener {
		public void valueChanged(ListSelectionEvent e) {
			changeLabelText();
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
			    default : result = "***";
		    }
			    return result;
        }
		public String getColumnName(int col) {
		    return columnNames[col];
		}
	}
 
    private void setUpGui() {
		loadTeams();
        createComponents();
        addComponents();
        registerListeners();
    }

    private void createComponents() {
        //set up the frame
        frame = new JFrame("Footbal Championship Statistics");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 426);
		frame.setResizable(false);
        //create panels
        buttonsPanel = new JPanel(new GridLayout(1,4));
        cardsPanel = new JPanel(new CardLayout());
        infoPanel = new JPanel(new BorderLayout());
        infoPanel.setBackground(Color.BLUE);
        tablePanel = new JPanel();
        tablePanel.setBackground(Color.GREEN);
        matchPanel = new JPanel(new BorderLayout());
        matchPanel.setBackground(Color.RED);
		matchPanelCenter = new JPanel(new BorderLayout());
        matchPanelCenter.setBackground(Color.RED); 
		matchPanelCenter2 = new JPanel(new BorderLayout());
        matchPanelCenter2.setBackground(Color.RED);
		matchPanelCenter3 = new JPanel(new BorderLayout(50, 50));
        matchPanelCenter3.setBackground(Color.RED);

        //create main buttons
        infoButton = new JButton("Team Information");
        tableButton = new JButton("Results Table");
        addButton = new JButton("Add Game Result");
        saveButton = new JButton("Save To File");
		//creating table
		TheTableModel mymodel = new TheTableModel();
		infoTable = new JTable(mymodel);
		jsp = new JScrollPane(infoTable);
		infoTable.setFillsViewportHeight(true);
		//create match panel components
		for (int i = 0; i < teamArray.length; i++) {
			listEntries[i] = teamArray[i].getName();
		}
		teamList1 = new JList<String>(listEntries);
		teamList2 = new JList<String>(listEntries);
		jsList1 = new JScrollPane(teamList1);
		jsList2 = new JScrollPane(teamList2);
		jsList1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		jsList2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		jsList1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jsList2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		teamList1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		teamList2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		bigFont = new Font(Font.MONOSPACED, Font.BOLD, 60);
		middleFont = new Font(Font.MONOSPACED, Font.BOLD, 20);
		goals1 = new JTextField(2);
		goals2 = new JTextField(2);
		vs = new JLabel(":", JLabel.CENTER);
		vs.setFont(bigFont);
		goals1.setFont(bigFont);
		goals2.setFont(bigFont);
		addResults = new JButton("Add Match Reasults");
		versus = new JLabel("Team1 vs Team2", JLabel.CENTER);
		versus.setFont(middleFont);	
    }
    
    private void addComponents() {
        //add panels
        frame.add(BorderLayout.NORTH, buttonsPanel);
        frame.add(BorderLayout.CENTER, cardsPanel);
        cardsPanel.add(infoPanel, INFOPANEL);
        cardsPanel.add(tablePanel, TABLEPANEL);
        cardsPanel.add(matchPanel, ADDPANEL);
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
		        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
		//configure matchPanel
		matchPanel.add(Box.createHorizontalStrut(20), BorderLayout.EAST);
		matchPanel.add(Box.createHorizontalStrut(20), BorderLayout.WEST);
		matchPanel.add(Box.createVerticalStrut(20), BorderLayout.NORTH);
		matchPanel.add(Box.createVerticalStrut(20), BorderLayout.SOUTH);
		matchPanel.add(matchPanelCenter, BorderLayout.CENTER);
		matchPanelCenter.add(jsList1, BorderLayout.WEST);	
		matchPanelCenter.add(jsList2, BorderLayout.EAST);	
		matchPanelCenter.add(matchPanelCenter2, BorderLayout.CENTER);
		matchPanelCenter2.add(Box.createHorizontalStrut(60), BorderLayout.EAST);
		matchPanelCenter2.add(Box.createHorizontalStrut(60), BorderLayout.WEST);
		matchPanelCenter2.add(Box.createVerticalStrut(60), BorderLayout.NORTH);
		matchPanelCenter2.add(Box.createVerticalStrut(60), BorderLayout.SOUTH);
		matchPanelCenter2.add(matchPanelCenter3, BorderLayout.CENTER);
		matchPanelCenter3.add(goals2, BorderLayout.EAST);
		matchPanelCenter3.add(vs, BorderLayout.CENTER);
		matchPanelCenter3.add(goals1, BorderLayout.WEST);
		matchPanelCenter3.add(addResults, BorderLayout.SOUTH);
		matchPanelCenter3.add(versus, BorderLayout.NORTH);
    }
    
    private void registerListeners() {
        //registrating action listeners
        infoButton.addActionListener(bl);
        tableButton.addActionListener(bl);
        addButton.addActionListener(bl);
        saveButton.addActionListener(bl);
		teamList1.addListSelectionListener(ll);
		teamList2.addListSelectionListener(ll);
    }
    
    private void loadTeams() {
		if (teamData.exists()) {
		    readData();
		} else {
		    newTeams();
		}
    }
    
    private void writeData() {
	try {
	    BufferedWriter bw = new BufferedWriter(new FileWriter(teamData));
	    String str = "";
	    for (FootballTeam t : teamArray) {
		str += t.getName() + "/" + t.getGames() + "/" + t.getScoredGoals() + "/" + t.getMissedGoals() + "/" + t.getGoalsDifference() + "/" + t.getPoints() + "\n";
	    }
	    bw.write(str);
        JOptionPane.showMessageDialog(frame, "Success: data saved", "Information", JOptionPane.INFORMATION_MESSAGE);
   bw.close();
	} catch (IOException e) {
        JOptionPane.showMessageDialog(frame, "Failed to write data", "error", JOptionPane.WARNING_MESSAGE);
	}
    }

    private void readData() {
	try {
	    BufferedReader br = new BufferedReader(new FileReader(teamData));
		String str = null;
		String[] arr = null;
		int team = 0;
		while ((str = br.readLine()) != null) {
			arr = str.split("/");
			teamArray[team] = new FootballTeam(arr[0], Integer.parseInt(arr[1]), Integer.parseInt(arr[2]), Integer.parseInt(arr[3]), Integer.parseInt(arr[4]), Integer.parseInt(arr[5]));
			team++;
		}
		br.close();
	}catch (IOException e) {
        JOptionPane.showMessageDialog(frame, "Read data failed. Creating teams from scratch...", "error", JOptionPane.WARNING_MESSAGE);
	    newTeams();
	}
    }
    
    private void newTeams() {
		teamArray[0] = new FootballTeam("Chalsea");
		teamArray[1] = new FootballTeam("Man Utd");
		teamArray[2] = new FootballTeam("Arsenal");
		teamArray[3] = new FootballTeam("Tottenham");
		teamArray[4] = new FootballTeam("Liverpool");
		teamArray[5] = new FootballTeam("Man City");
		teamArray[6] = new FootballTeam("Aston Villa");
		teamArray[7] = new FootballTeam("Sunderland");
		teamArray[8] = new FootballTeam("Stoke");
		teamArray[9] = new FootballTeam("Wigan");
		teamArray[10] = new FootballTeam("Burnley");
		teamArray[11] = new FootballTeam("Bolton");
		teamArray[12] = new FootballTeam("Fulham");
		teamArray[13] = new FootballTeam("Everton");
		teamArray[14] = new FootballTeam("Birmingham");
		teamArray[15] = new FootballTeam("Blackburn");
		teamArray[16] = new FootballTeam("Wolverham");
		teamArray[17] = new FootballTeam("Hull");
		teamArray[18] = new FootballTeam("West Ham");
		teamArray[19] = new FootballTeam("Portsmouth");
    }

	private void changeLabelText() {
		String str;
		String sel1 = (String) teamList1.getSelectedValue();
		String sel2 = (String) teamList2.getSelectedValue();
		if (sel2 == null)
			sel2 = "...";
		else if (sel1 == null)
			sel1 = "...";
		str = "";
		str = sel1 + " " + "vs" + " " + sel2;
		versus.setText(str);
	}

	private void addMatchResults() {
		String team1 = (String) teamList1.getSelectedValue();
		String team2 = (String) teamList2.getSelectedValue();
		String score1 = teamList1.getText();
		String score2 = teamList2.getText();
		if (team1 == null || team2 == null || score1 == null || score2 == null || score1 < 0 || score2 < 0) {
			JOptionPane.showMessageDialog(frame, "Please, set correct values.", "Error", JOptionPane.WARNING_MESSAGE);
			break;
		} else if (team1.equals(team2)) {
			JOptionPane.showMessageDialog(frame, "Team can not play whith itself!", "Error", JOptionPane.WARNING_MESSAGE);
			break;
		}

	}
}
