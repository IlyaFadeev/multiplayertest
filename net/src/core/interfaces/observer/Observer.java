package core.interfaces.observer;

import core.interfaces.root.Entity;

public interface Observer {
    public void notifyListeners(Entity entity);
    public void addListener(Listener listener);
    public void removeListener(int index);
}
