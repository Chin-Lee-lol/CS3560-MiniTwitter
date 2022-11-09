package main;

import java.util.ArrayList;
import java.util.List;

public abstract class Subject {

    private List<Observer> observers = new ArrayList<Observer>();
    
    public void attatch(Observer observer) {
	observers.add(observer);
    }
    
    public void detach(Observer observer) {
	observers.remove(observer);
    }
    
    protected void notifyObservers() {
	for (Observer ob: observers) {
	    ob.update(this);
	}
    }
}
