package core.implementations;

import core.interfaces.AbilityEntity;
import core.interfaces.PlayerEntity;
import java.util.List;

public class Player implements PlayerEntity {

    List<Ability> abilities;
    private String name;
    private float x;
    private float y;

    public Player(String name) {
        this.name = name;
    }

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

    public void setY(float y) {
        this.y = y;
    }

    public void setAbilities(List<Ability> abilities) {
        this.abilities = abilities;
    }



    @Override
    public List<AbilityEntity> getAbilities() {
        return null;
    }


}
