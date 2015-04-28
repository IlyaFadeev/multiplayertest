package core.interfaces.root;


/**
 * Интерфейс содержит фукнциональность единую для всех сущностей
 */
public interface Entity {
    public String getName();
    public float getX();
    public float getY();
    public void updateX(float x);
    public void updateY(float y);
}
