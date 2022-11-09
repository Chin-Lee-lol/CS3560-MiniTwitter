package main;

public class AddUserButton implements Button{
    
    private static AddUserButton instance;
    private ListView lv;

    
    private AddUserButton() {
	lv = ListView.getInstance();
	System.out.println("Rendering add user button");
    }
	
    public static AddUserButton getInstance() {
        if (instance == null) {
        	instance = new AddUserButton();
        }
        return instance;
    }

    @Override
    public void add(String n, String group) {
	// TODO Auto-generated method stub
	User u1 = new User();
	// maybe do ask for names here
	u1.setName(n);
	group = group.replace(" ", "");
	group = group.replace("-", "");
	group = group.replace("<", "");
	group = group.replace(">", "");
	Member m = lv.findGroup(group);
	if (m == null)
	    lv.addMember(u1);
	else
	    m.addMember(u1);	
    }

}
