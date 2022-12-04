package main;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ACP extends JFrame implements ActionListener{
    
    private JFrame admin = new JFrame("Admin Control Panel");
    private JPanel treeView;
    private JButton AddUser, AddGroup, UserView, validate, update, UserTotal, GroupTotal, MessageTotal, Positive;
    private JTextField user,group;
    private JLabel tvText;
    private String clickOn = "<Root>"; // initially select root lol
    static JList<Object> list;
    private DefaultListModel<Object> model = new DefaultListModel<>();
    
    private ListView lv = ListView.getInstance();
    private AddUserButton uB = AddUserButton.getInstance();
    private AddGroupButton gB = AddGroupButton.getInstance();
    private NewsFeed nf = NewsFeed.getInstance();
    
    private static ACP instance;
    private int count, uC = 0, gC = 0;
    private float positive;
    private Visitor visitor = new CountVisitor();
    
    public static ACP getInstance() {
	if (instance == null) {
	    synchronized (ACP.class) {
		instance = new ACP();
	    }
	}
	    return instance;
	}
    
    private ACP() { 
	
	tvText = new JLabel();
	tvText.setText("Tree View");
	tvText.setBounds(20, 0, 100, 50);
	admin.add(tvText);
	
        treeView = new JPanel();
        treeView.setBackground(Color.gray);
        treeView.setBounds(10,35,400,540);   
	list = refreshTreeView();
	admin.add(list);
		
	AddUser = new JButton("Add User");
	AddUser.setBounds(750, 20, 100, 50);
	AddUser.setFocusable(false);
	MouseListener addUserListener = new MouseAdapter() {
	      @Override
	      public void mouseClicked(MouseEvent me) {
		if (!(user.getText().equals("")))
		{
    		    uB.add(user.getText(), clickOn);
    		    lv.validateCheck(user.getText());
    		    user.setText("");
    		    refreshTreeView();
    		    clickOn = "<Root>";
    		    uC++;
		}
	      }
	};
	AddUser.addMouseListener(addUserListener);
	admin.add(AddUser);
	
	AddGroup = new JButton("Add Group");
	AddGroup.setBounds(750, 80, 100, 50);
	AddGroup.setFocusable(false);
	MouseListener addGroupListener = new MouseAdapter() {
	      @Override
	      public void mouseClicked(MouseEvent me) {
		if (!(group.getText().equals(""))) 
		{
		    gB.add(group.getText(), clickOn);
		    lv.validateCheck(group.getText());
		    group.setText("");
		    refreshTreeView();
		    clickOn = "<Root>";
		    gC++;
		}
	      }
	};
	AddGroup.addMouseListener(addGroupListener);
	admin.add(AddGroup);
	
	UserView = new JButton("Open User View");
	UserView.setBounds(550, 150, 200, 50);
	UserView.addActionListener(this);
	UserView.setFocusable(false);
	admin.add(UserView);
	
	validate = new JButton("Validate IDs");
	validate.setBounds(550, 250, 200, 50);
	validate.addActionListener(this);
	validate.setFocusable(false);
	admin.add(validate);
	
	update = new JButton("Last Updated User");
	update.setBounds(550, 320, 200, 50);
	update.addActionListener(this);
	update.setFocusable(false);
	admin.add(update);
	
	UserTotal = new JButton("Show User Total");
	UserTotal.setBounds(450, 400, 200, 50);
	UserTotal.addActionListener(this);
	UserTotal.setFocusable(false);
	admin.add(UserTotal);
	
	GroupTotal = new JButton("Show Group Total");
	GroupTotal.setBounds(650, 400, 200, 50);
	GroupTotal.addActionListener(this);
	GroupTotal.setFocusable(false);
	admin.add(GroupTotal);
	
	MessageTotal = new JButton("Show Message Total");
	MessageTotal.setBounds(450, 450, 200, 50);
	MessageTotal.addActionListener(this);
	MessageTotal.setFocusable(false);
	admin.add(MessageTotal);
	
	Positive = new JButton("Show Positive Percentage");
	Positive.setBounds(650, 450, 200, 50);
	Positive.addActionListener(this);
	Positive.setFocusable(false);
	admin.add(Positive);
	
	user = new JTextField();
	user.setBounds(450,27,300,40);
	user.addActionListener(this);
	admin.add(user);
	
	group = new JTextField();
	group.setBounds(450,87,300,40);
	group.addActionListener(this);
	admin.add(group);
	
        admin.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        admin.setLayout(null);
        //f.add(listView);
        admin.setSize(900,600);
        admin.setVisible(true);
        

	list.addListSelectionListener(new ListSelectionListener() {
	    @Override
	    public void valueChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting())
		    return;
		clickOn = (String) list.getSelectedValue();			
	    }           
        });
	
       
    }

    @Override
    public void actionPerformed(ActionEvent e) {	
	// TODO Auto-generated method stub	
	if (e.getSource() == UserView)
	{
	    User u = (User) lv.findMember(clickOn);
	    new UserView_Panel(u);	    
	}
	else if (e.getSource() == validate)
	{
	    System.out.println(lv.getValidate());
	    popUpWindow("Validate Check", lv.getValidate(), "");
	}
	else if (e.getSource() == UserTotal)
	{
	    count = lv.acceptChat(visitor);
	    popUpWindow("User Total", "Total User: ", uC);
	}
	else if (e.getSource() == GroupTotal)
	{
	    count = lv.acceptGroup(visitor);
	    popUpWindow("Group Total", "Total Group: ", gC);
	}
	else if (e.getSource() == MessageTotal)
	{
	    count = nf.acceptMessage(visitor);
	    popUpWindow("Message Total", "Total Message: ", count); 
	}
	else if (e.getSource() == Positive)
	{
	    positive = nf.acceptPositive(visitor);
	    popUpWindow("Positive Message Total", "Positive Percentage: ", positive + "%");	    
	}
	else if (e.getSource() == update)
	{
	    popUpWindow("Last updated user", "Last updated user: ", nf.getLastUpdateID());
	}
    }
    
    public JList<Object> refreshTreeView() {
	model.clear();
	for (Object s : lv.getMemList().toArray()) {
	    model.addElement(s);
	}
	JList list = new JList<>(model);
	list.setBounds(20, 35, 400, 500);
	return list;
    }
    
    public void popUpWindow(String name, String txt, Object count) {
	JFrame frame = new JFrame(name);
	
	JLabel text = new JLabel();
	text.setText(txt + count);
	text.setBounds(20, 0, 400, 50);
	frame.add(text);
	
	frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	frame.setLayout(null);
	frame.setSize(400,100);    	    
	frame.setVisible(true);
    }
}
