package core;

import core.interfaces.root.Entity;
import core.interfaces.root.EntityPool;

import java.util.Iterator;

/**
 * Класс представляет собой пул сущностей
 * в данный момент функционирующих заклинаний/умений
 */
public class AbilityPool implements EntityPool {

    //TODO: Реализовать методы
    @Override
    public void add(Entity entity) {

    }

    @Override
    public void remove(int index) {

    }

    @Override
    public Entity get(int index) {
        return null;
    }

    @Override
    public void set(int index, Entity entity) {

    }

    @Override
    public Iterator<Entity> iterator() {
        return null;
    }
}
