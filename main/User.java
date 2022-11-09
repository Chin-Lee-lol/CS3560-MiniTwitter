package main;

import java.util.ArrayList;
import java.util.List;

public class User implements Member, Observer{
    
    private String name;
    private List<User> following = new ArrayList<User>();
    private List<User> followers = new ArrayList<User>();   
    
    @Override
    public void clickOn() {
	System.out.println("User being clicked");
	
    }

    @Override
    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }


    @Override
    public void update(Subject subject) {
	// TODO Auto-generated method stub
	if (subject instanceof NewsFeed) {
	    ((NewsFeed) subject).printNewsFeed(this, getFollowing());
	}
    }

    public List<String> getFollowing() {
	List<String> res = new ArrayList<String>();
	if (following.isEmpty())
	{
	    User lol = new User();
	    lol.setName("Current Following:");
	    following.add(lol);
	}
	for (User u : following)
	    res.add(u.getName());
	return res;
    }

    public void setFollowing(List<User> following) {
	this.following = following;
    }

    public List<User> getFollowers() {
	return followers;
    }

    public void setFollowers(List<User> followers) {
	this.followers = followers;
    }
    
    public void follow(User u) {
	u.addFollower(this);
	following.add(u);
    }
    
    public void addFollower(User u) {
	followers.add(u);
    }
    
    // code below not really use lol mostly from implement for group
    @Override
    public boolean isGroup() { return false; }

    @Override
    public List<String> getMemList() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public boolean isEmpty() {
	// TODO Auto-generated method stub
	return false;
    }

    @Override
    public void addMember(Member m) {
	// TODO Auto-generated method stub
	
    }

    @Override
    public Member findGroup(String group) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Member findMember(String mem) {
	// TODO Auto-generated method stub
	return null;
    }

}
