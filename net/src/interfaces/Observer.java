package interfaces;


public interface Observer {
    public void notifyObservers(String message);
    public void addListener(Listener listener);
    public void removeListener(Listener listener);
}
