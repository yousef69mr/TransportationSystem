package sw2phases;

import java.util.ArrayList;

public interface Subject {

    void attach(Observer observer);
    void unAttach(Observer observer);
    void notifyAllObservers();
}
