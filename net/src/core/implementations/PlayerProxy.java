package core.implementations;

import com.badlogic.gdx.scenes.scene2d.Actor;
import core.interfaces.AbilityEntity;
import core.interfaces.PlayerEntity;
import java.util.List;

public class PlayerProxy implements PlayerEntity {

    private Actor actor;
    List<Ability> abilities;
    private String name;
    private float x;
    private float y;

    public PlayerProxy(Actor actor) {
        this.name = actor.getName();
        this.actor = actor;
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

    @Override
    public void updateX(float x) {
        this.x = x;
    }

    @Override
    public void updateY(float y) {
        this.y = y;
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