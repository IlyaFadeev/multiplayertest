package core.interfaces;

import core.interfaces.observer.Observer;
import core.interfaces.root.Entity;
import java.util.List;

public interface PlayerEntity extends Entity , Observer {
    public List<AbilityEntity> getAbilities();
}
