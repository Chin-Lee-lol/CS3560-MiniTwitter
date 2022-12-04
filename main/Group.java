package main;

import java.util.ArrayList;
import java.util.List;

public class Group implements Member {

    private List<Member> memList = new ArrayList<Member>();
    private String name;
    private static int count = 0;
    private long creationTime = 0;
    
    @Override
    public void clickOn() {
	System.out.println("Group being clikced");
	
    }
    
    @Override
    public void addMember(Member mem) {
	memList.add(mem);
    }
    
    @Override
    public List<String> getMemList() {
	count++;
	String res = "";
	List<String> result = new ArrayList<String>();
	
	for (Member m: memList)
	{
	    res = "";
	    for (int i = 0; i < count; i++)
	    {
		res += "     ";
	    }
	    res += "- " + m.getName();
	    result.add(res);
	    if (m.isGroup() && !(m.isEmpty()))
	    {
		result.addAll(m.getMemList());
	    }
	}
	count--;
	return result;
    }
    
    public void setMemList(List<Member> memList) {
	this.memList = memList;
    }
    
    @Override
    public String getName() {
	return name;
    }
    
    public void setName(String name) {
	this.name = name;
	creationTime = System.currentTimeMillis();
	System.out.println(name + " has been created on " + creationTime);
    }
    
    public String getCreationTime() {
	return String.valueOf(creationTime);
    }
    
    @Override
    public boolean isGroup() { return true;}
    
    @Override
    public boolean isEmpty() {
	if (memList.isEmpty())
	    return true;
	return false;
    }

    @Override
    public Member findGroup(String group) {
	String g;
	Member result = null;
	for (Member m: memList)
	{
	    g = m.getName().replace(" ", "");
	    g = g.replace("-", "");
	    g = g.replace("<", "");
	    g = g.replace(">", "");
	    if (g.equals(group) && m.isGroup())
	    {
		System.out.println("teha");
		return m;
	    }
	    
	    if (m.isGroup())
	    {
		result = m.findGroup(group);
	    }
	    
	}
	return result;
    }

    @Override
    public Member findMember(String mem) {
	// TODO Auto-generated method stub
	String g;
	Member result = null;
	for (Member m: memList)
	{
	    g = m.getName().replace(" ", "");
	    g = g.replace("-", "");
	    g = g.replace("<", "");
	    g = g.replace(">", "");
	    System.out.println("Now in group Looking for : " + mem + " found: " + g);
	    if (g.equals(mem) && !(m.isGroup()))
	    {
		System.out.println("teha");
		return m;
	    }
	    
	    if (m.isGroup())
	    {
		result = m.findMember(mem);
	    }
	    
	}
	return result;
    }

}
