package main;

import java.util.List;

public interface Member {

    public void clickOn();
    public String getName();
    
    //these are for Group lol
    public boolean isGroup();
    public List<String> getMemList();
    public boolean isEmpty();
    public void addMember(Member m);
    public Member findGroup(String group);
    public Member findMember(String mem);
}
