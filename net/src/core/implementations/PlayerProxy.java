package core.implementations;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import core.interfaces.AbilityEntity;
import core.interfaces.PlayerEntity;
import core.interfaces.observer.Listener;
import core.interfaces.root.Entity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlayerProxy implements PlayerEntity {

    transient private List<Listener> listeners;
    transient private Actor actor;
    List<Ability> abilities;
    private String name;
    private float x;
    private float y;

    public PlayerProxy(Actor actor) {
        this.name = actor.getName();
        this.actor = actor;
        listeners = Collections.synchronizedList(new ArrayList<Listener>());
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
        return this.x;
    }

    public void setX(float x) {
        this.x = x;
    }

    @Override
    public float getY() {
        return this.y;
    }

    @Override
    public void updateX(float x) {
        this.x = x;
    }

    @Override
    public void updateY(float y) {
        this.y = y;
    }

    @Override
    public void updateXY(float x, float y) {
        this.x = x;
        this.y = y;
        actor.addAction(Actions.moveTo(x, y, 0.1f, Interpolation.linear));
        notifyListeners(this); //Обновляем информацию о игроке
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


    @Override
    public void notifyListeners(Entity entity) {
        for(Listener listener : this.listeners) {
            listener.update(entity);
        }
    }

    @Override
    public void addListener(Listener listener) {
        System.out.println(listener);
        listeners.add(listener);
    }

    @Override
    public void removeListener(int index) {
        listeners.remove(index);
    }
}
