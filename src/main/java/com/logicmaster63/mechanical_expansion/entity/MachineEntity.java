package com.logicmaster63.mechanical_expansion.entity;

import com.logicmaster63.mechanical_expansion.machines.Machine;
import net.minecraft.entity.passive.EntityAmbientCreature;
import net.minecraft.world.World;

public class MachineEntity<T extends Machine> extends EntityAmbientCreature {

    private T machine;

    public MachineEntity(World world) {
        super(world);
        setSize(0.9F, 1.4F);
    }

    public MachineEntity(T machine, World world) {
        super(world);
        this.machine = machine;
    }

    @Override
    public void onEntityUpdate() {
        super.onEntityUpdate();
        if(machine != null)
            machine.update();
    }

    public T getMachine() {
        return machine;
    }
}