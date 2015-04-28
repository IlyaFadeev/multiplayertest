package core.interfaces.root;

/**
 * Интерфейс содержит единую спецификацию для всех пулов сущностей
 */
public interface EntityPool extends Iterable<Entity> {
    public void add(Entity entity);
    public void remove(int index);
    public Entity get(int index);
    public void set(int index , Entity entity);
    public Entity getEntityByName(String name);
    public boolean contains(Entity entity);
}
