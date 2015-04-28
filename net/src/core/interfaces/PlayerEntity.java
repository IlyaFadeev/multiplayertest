package core.interfaces;

import core.interfaces.root.Entity;
import java.util.List;

public interface PlayerEntity extends Entity {
    public List<AbilityEntity> getAbilities();
}
