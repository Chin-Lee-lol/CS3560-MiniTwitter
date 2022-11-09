package main;

import java.awt.Color;
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

public class UserView_Panel {

  
    JButton Follow, postButton;
    JTextField user, tweet;
    JPanel followList, newsFeed;
    JLabel FollowText, NewsText;
    ListView lv = ListView.getInstance();
    NewsFeed nf = NewsFeed.getInstance();
    JFrame userPanel;
    DefaultListModel<Object> model = new DefaultListModel<>();
    DefaultListModel<Object> model2 = new DefaultListModel<>();
    User thisUser;
    UserView_Panel(User u){
	
	thisUser = u;
	userPanel = new JFrame(u.getName() + "'s View");
	
//	FollowText = new JLabel();
//	FollowText.setText("Current Following: ");
//	FollowText.setBounds(20, 30, 300, 50);
//	userPanel.add(FollowText);
//	
//	NewsText = new JLabel();
//	NewsText.setText("News Feed: ");
//	NewsText.setBounds(20, 30, 300, 50);
//	userPanel.add(NewsText);

	followList = new JPanel();
	followList.setBackground(Color.gray);
	followList.setBounds(10,300,300,200);
	refreshFollowingList();
	
	newsFeed = new JPanel();
	newsFeed.setBackground(Color.gray);
	newsFeed.setBounds(10,300,300,200);
	refreshNewsFeed();
	
	
	Follow = new JButton("Follow User");
	Follow.setBounds(420, 10, 150, 40);
	Follow.setFocusable(false);
	MouseListener addFollower = new MouseAdapter() {
	      @Override
	      public void mouseClicked(MouseEvent me) {
		if (!(user.getText().equals("")))
		{	
		    User target = (User) lv.findMember(user.getText());
		    if (target == null)
			System.out.println("Can't find that user D:");
		    else
		    {
			System.out.println("You have followed " + target.getName());
			u.follow(target);
			refreshFollowingList();
			refreshNewsFeed();
		    }
		   
		    
		    user.setText("");
		}
	      }
	};
	Follow.addMouseListener(addFollower);
	userPanel.add(Follow);
	
	postButton = new JButton("Post Tweet");
	postButton.setBounds(420, 310, 150, 40);
	postButton.setFocusable(false);
	MouseListener postTweet = new MouseAdapter() {
	      @Override
	      public void mouseClicked(MouseEvent me) {
		  if (!(tweet.getText().equals("")))
		  {
		      nf.postMessage(u, tweet.getText());
		      tweet.setText("");
		      refreshNewsFeed();
		  }
	      }
	};
	postButton.addMouseListener(postTweet);
	userPanel.add(postButton);	
	
	user = new JTextField();
	user.setBounds(20, 10,400,40);
	userPanel.add(user);
	
	tweet = new JTextField();
	tweet.setBounds(20, 310,400,40);
	userPanel.add(tweet);
	
	userPanel.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	userPanel.setLayout(null);
	userPanel.setSize(600,700);    	    
	userPanel.setVisible(true);	
    }
    
    public void refreshNewsFeed() {
	model2.clear();
	for (Object s : nf.printNewsFeed(thisUser, thisUser.getFollowing())) {
	    model2.addElement(s);
	}
	JList list2 = new JList<>(model2);
	list2.setBounds(20, 355, 550, 300);
	userPanel.add(list2);
    }
    
    public void refreshFollowingList() {
	model.clear();
	for (Object s : thisUser.getFollowing().toArray()) {
	    model.addElement(s);
	}   
	JList list = new JList<>(model);
	list.setBounds(20, 55, 550, 250);
	userPanel.add(list);

    }
}
