package core.interfaces.root;


import java.io.Serializable;

/**
 * Интерфейс содержит фукнциональность единую для всех сущностей
 */
public interface Entity extends Serializable{
    public String getName();
    public float getX();
    public float getY();
    public void updateX(float x);
    public void updateY(float y);
    public void updateXY(float x , float y);
}
