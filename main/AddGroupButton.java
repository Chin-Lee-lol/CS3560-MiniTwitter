package main;

public class AddGroupButton implements Button{
    
    private static AddGroupButton instance;
    private ListView lv;

    
    private AddGroupButton() {
	lv = ListView.getInstance();
	System.out.println("Rendering add group button");
    }
	
    public static AddGroupButton getInstance() {
        if (instance == null) {
        	instance = new AddGroupButton();
        }
        return instance;
    }

    @Override
    public void add(String n, String group) {
	// TODO Auto-generated method stub
	Group g1 = new Group();
	// maybe do ask for names here
	g1.setName("<"+n+">");
	group = group.replace(" ", "");
	group = group.replace("-", "");
	group = group.replace("<", "");
	group = group.replace(">", "");
	Member m = lv.findGroup(group);
	if (m == null)
	    lv.addMember(g1);
	else
	    m.addMember(g1);	

	
    }

}
