package main;

public interface Visitor {
    public int visitUser(ListView lv);
    public int visitGroup(ListView lv);
    public int visitMessage(NewsFeed nf);
    public float visitPositive(NewsFeed nf);

}
