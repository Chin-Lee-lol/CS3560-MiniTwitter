package main;

public class CountVisitor implements Visitor{

    @Override
    public int visitUser(ListView lv) {
	// TODO Auto-generated method stub
	return lv.getMemCount();
    }

    @Override
    public int visitGroup(ListView lv) {
	// TODO Auto-generated method stub
	return lv.getGroupCount();
    }

    @Override
    public int visitMessage(NewsFeed nf) {
	// TODO Auto-generated method stub
	return nf.getMessageCount();
    }

    @Override
    public float visitPositive(NewsFeed nf) {
	// TODO Auto-generated method stub
	return nf.getPositiveCount() * 100;
	
    }

}
