package main;

import java.util.ArrayList;
import java.util.List;


public class ListView {
    
    private List<Member> memList = new ArrayList<Member>(); // 
    private List<String> idList = new ArrayList<String>();
    private static ListView instance;
    private int memCount = 0, groupCount = 0;
    private boolean valid = true;
    
    private ListView() {
	User root = new User();
	root.setName("<Root>");
	memList.add(root);	
    }
	
    public static ListView getInstance() {
	if (instance == null) {
	    instance = new ListView();
	}
	    return instance;
	}
    
    public void validateCheck(String id) {
	if (!valid)
	    return;
	if (id.contains(" "))
	{
	    valid = false;
	    return;
	}
	if (idList.contains(id))
	{
	    valid = false;
	    return;
	}
	idList.add(id);	
    }
    
    public String getValidate()
    {
	if (valid)
	    return "Cool valid";
	return "no valid D:";
    }

    public Member findGroup(String group){
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
		return m;
	    }
	    
	    if (m.isGroup())
	    {
		result = m.findGroup(group);
	    }
	    
	}
	return result;
    }
    
    public Member findMember(String mem){
	String g;
	mem = mem.replace(" ", "");
	mem = mem.replace("-", "");
	mem = mem.replace("<", "");
	mem = mem.replace(">", ""); 
	Member result = null;
	for (Member m: memList)
	{
	    g = m.getName().replace(" ", "");
	    g = g.replace("-", "");
	    g = g.replace("<", "");
	    g = g.replace(">", ""); 
	    if (g.equals(mem) && !(m.isGroup()))
	    {
		return m;
	    }
	    
	    if (m.isGroup())
	    {
		result = m.findMember(mem);
	    }
	    
	}
	return result;
    }

    public List<String> getMemList() {
	List<String> result = new ArrayList<String>();
	for (Member m: memList)
	{
	    if(m.getName() == "<Root>")
	    	result.add(m.getName());
	    else
		result.add("- " + m.getName());
	    
	    if (m.isGroup() && !(m.isEmpty()))
	    {
		result.addAll(m.getMemList());
	    }
	}
	return result;
    }

    public void setMemList(List<Member> memList) {
	this.memList = memList;
    }
    
    public void addMember(Member mem) {
	memList.add(mem);
    }
    
    
    public int acceptChat(Visitor visitor) {
	return visitor.visitUser(this);
    }
    
    public int acceptGroup(Visitor visitor) {
	return visitor.visitGroup(this);
    }
    

    public int getMemCount() {
	return memCount;
    }

    public void setMemCount(int memCount) {
	this.memCount = memCount;
    }

    public int getGroupCount() {
	return groupCount;
    }

    public void setGroupCount(int groupCount) {
	this.groupCount = groupCount;
    }
}
