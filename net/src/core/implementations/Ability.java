package core.implementations;

import core.interfaces.AbilityEntity;

public class Ability implements AbilityEntity {

    private String name;
    private float x;
    private float y;

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    @Override
    public float getY() {
        return y;
    }

    @Override
    public void updateX(float x) {

    }

    @Override
    public void updateY(float y) {

    }

    public void setY(float y) {
        this.y = y;
    }
}
