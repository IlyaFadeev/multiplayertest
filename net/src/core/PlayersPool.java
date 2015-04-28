package core;

import core.interfaces.root.Entity;
import core.interfaces.root.EntityPool;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Класс представляет собой пул сущностей игроков
 */
public class PlayersPool implements EntityPool {

    private ArrayList<Entity> players;

    public PlayersPool() {
        this.players = new ArrayList<Entity>();
    }

    @Override
    public void add(Entity entity) {
        this.players.add(entity);
    }

    @Override
    public void remove(int index) {
        this.players.remove(index);
    }

    @Override
    public Entity get(int index) {
        return this.players.get(index);
    }

    @Override
    public void set(int index , Entity entity) {
        this.players.set(index , entity);
    }

    @Override
    public Entity getEntityByName(String name) {
        for(Entity entity : players) {
            if (entity.getName().equals(name)) {
                return entity;
            }
        }
        return null;
    }

    @Override
    public Iterator<Entity> iterator() {
        return this.players.iterator();
    }
}
