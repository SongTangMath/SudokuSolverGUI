package solver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class GameFrame extends JFrame{
	public char[][]board=new char[9][9] ;
	public char[][]ans=new char[9][9] ;
	public JButton[][] buttons=new JButton[9][9] ;
public GameFrame() {
	for(int i=0;i<9;i++)
		for(int j=0;j<9;j++)board[i][j]='.';
	this.setTitle("TS's SudokuSolver");
	this.setLocationByPlatform(true);
	this.setLocationRelativeTo(null);
	this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	Font favor=new Font("Consolas",Font.PLAIN,25);
	JPanel panel=new JPanel(null);
	

	JButton solve=new JButton("solve");
	solve.setFont(favor);
	solve.setBounds(70,450, 150,50);
	panel.add(solve);
	JButton setPlain=new JButton("setPlain");
	setPlain.setFont(favor);
	setPlain.setBounds(220,450, 150,50);
	panel.add(setPlain);
	JButton stepRet=new JButton("stepRet");
	stepRet.setFont(favor);
	stepRet.setBounds(370,450, 150,50);
	panel.add(stepRet);
	
	
	solve.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			Q37 q37=new Q37();
			q37.solveSudoku(board);
			if(q37.foundSoluton) {
				ans=q37.solution;
			for(int i=0;i<9;i++)
				for(int j=0;j<9;j++)
					buttons[i][j].setText(ans[i][j]+"");
		}
			else JOptionPane.showMessageDialog(null, "No Solution Found", 					
					"Warning", JOptionPane.ERROR_MESSAGE);
		}
		
	});
	setPlain.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			for(int i=0;i<9;i++)
				for(int j=0;j<9;j++) {
					buttons[i][j].setText(".");
					board[i][j]='.';
				}
			
		}
		
	});
	stepRet.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			for(int i=0;i<9;i++)
				for(int j=0;j<9;j++) 
					buttons[i][j].setText(""+board[i][j]);
					
				
			
		}
		
	});
	
	for(int i=0;i<9;i++)
		for(int j=0;j<9;j++) {
			buttons[i][j]=new JButton(".");
			buttons[i][j].setBounds(70+50*i, 50*j, 50, 50);
			buttons[i][j].setFont(favor);
			buttons[i][j].setName(""+i+j);
			buttons[i][j].addActionListener(
					new ButtonListener(this,buttons[i][j]));
			panel.add(buttons[i][j]);
			
		}
	this.setSize(700, 700);
	this.setResizable(false);
	this.setContentPane(panel);
	//this.pack();
	this.setVisible(true);
}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GameFrame newgame=new GameFrame();
	}

}
class ButtonListener implements ActionListener{
private JButton  button;
private GameFrame frame;
public ButtonListener( GameFrame frame,JButton  button) {
	this.frame=frame;
	this.button=button;
}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
			String s=JOptionPane.showInputDialog("input a num");
			if(!s.equals("")) {
				char ch=s.charAt(0);
				if('0'<=ch&&ch<='9') {
					button.setText(ch+"");
					String name=button.getName();
					int i=name.charAt(0)-'0';
					int j=name.charAt(1)-'0';
					frame.board[i][j]=ch;
				}
				else button.setText(".");
			}
		
	}
	
}


