package main;

import java.util.ArrayList;
import java.util.List;

public class NewsFeed extends Subject{
    
    private static NewsFeed instance;
    
    public static NewsFeed getInstance() {
	if (instance == null) {
	    instance = new NewsFeed();
	}
	    return instance;
	}
    
    private NewsFeed() {}
    
    private List<String> senders = new ArrayList<String>();
    private List<String> messages = new ArrayList<String>();
    private String lastUser;
    
    public List<String> getSenders() {
	return senders;
    }
    
    public void setSenders(List<String> senders) {
	this.senders = senders;
    }
    
    public List<String> getMessages() {
	return messages;
    }
    
    public void setMessages(List<String> messages) {
	this.messages = messages;
    }
    
    public void postMessage(User u, String message) {
	senders.add(u.getName());
	messages.add(message);
	notifyObservers();
	List<User> follower = u.getFollowers();
	System.out.print(u.getName());
	for (int i = 0; i< follower.size(); i++)
	    System.out.print(", " + follower.get(i).getName());
	System.out.println(" news feed has been updated.");
	lastUser = u.getName();
    }
    
    public String getLastUpdateID() {
	return lastUser;
    }
    
    public List<String> printNewsFeed(User u, List<String> following) {
	List<String> res = new ArrayList<String>();
	res.add(u.getName()+ "'s News Feed:");
	for (int i = 0; i < senders.size(); i++)
	{
	    if (u.getName() == senders.get(i) || following.contains(senders.get(i)))
		res.add(senders.get(i) + ": " + messages.get(i));
	}
	return res;
    }
    
    public int getMessageCount()
    {
	return messages.size();
    }
    
    public float getPositiveCount()
    {
	float positive = 0;
	for (String s : messages)
	{
	    if (s.toLowerCase().contains("good") || s.toLowerCase().contains("nice") || s.toLowerCase().contains("cool")
		|| s.toLowerCase().contains("best") || s.toLowerCase().contains("thanks")|| s.toLowerCase().contains("great")
		|| s.toLowerCase().contains("excellent")|| s.toLowerCase().contains("splendid")|| s.toLowerCase().contains("awesome")
		|| s.toLowerCase().contains("amazing")) {	
		positive++;
	    }
	}
	return positive/(float)getMessageCount();
    }
    
    public int acceptMessage(Visitor visitor) {
	return visitor.visitMessage(this);
    }
    
    public float acceptPositive(Visitor visitor) {
	return visitor.visitPositive(this);
    }

}
