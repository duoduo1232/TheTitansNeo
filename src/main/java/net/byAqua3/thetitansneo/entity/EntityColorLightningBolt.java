package net.byAqua3.thetitansneo.entity;

import net.byAqua3.thetitansneo.loader.TheTitansNeoEntities;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.level.Level;

public class EntityColorLightningBolt extends LightningBolt {
    
	private static final EntityDataAccessor<Float> DATA_EFFECT_RED_ID = SynchedEntityData.defineId(EntityColorLightningBolt.class, EntityDataSerializers.FLOAT);
	private static final EntityDataAccessor<Float> DATA_EFFECT_GREEN_ID = SynchedEntityData.defineId(EntityColorLightningBolt.class, EntityDataSerializers.FLOAT);
	private static final EntityDataAccessor<Float> DATA_EFFECT_BLUE_ID = SynchedEntityData.defineId(EntityColorLightningBolt.class, EntityDataSerializers.FLOAT);

    public EntityColorLightningBolt(EntityType<? extends EntityColorLightningBolt> entityType, Level level) {
        super(entityType, level);
    }
    
    public EntityColorLightningBolt(Level level, float red, float green, float blue) {
		super(TheTitansNeoEntities.COLOR_LIGHTNING_BLOT.get(), level);
		this.setRed(red);
		this.setGreen(green);
	    this.setBlue(blue);
		this.setDamage(50.0F);
	}

    public float getRed() {
        return this.entityData.get(DATA_EFFECT_RED_ID);
    }

    public void setRed(float red) {
        this.entityData.set(DATA_EFFECT_RED_ID, red);
    }
    
    public float getGreen() {
        return this.entityData.get(DATA_EFFECT_GREEN_ID);
    }

    public void setGreen(float green) {
        this.entityData.set(DATA_EFFECT_GREEN_ID, green);
    }
    
    public float getBlue() {
        return this.entityData.get(DATA_EFFECT_BLUE_ID);
    }

    public void setBlue(float blue) {
        this.entityData.set(DATA_EFFECT_BLUE_ID, blue);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
    	builder.define(DATA_EFFECT_RED_ID, 1.0F);
    	builder.define(DATA_EFFECT_GREEN_ID, 1.0F);
    	builder.define(DATA_EFFECT_BLUE_ID, 1.0F);
    }
    
    @Override
    protected void readAdditionalSaveData(net.minecraft.world.level.storage.ValueInput input) {
    	this.setRed(input.getFloatOr("red", 0.0F));
    	this.setGreen(input.getFloatOr("green", 0.0F));
    	this.setBlue(input.getFloatOr("blue", 0.0F));
    }

    @Override
    protected void addAdditionalSaveData(net.minecraft.world.level.storage.ValueOutput output) {
    	output.putFloat("red", this.getRed());
    	output.putFloat("green", this.getGreen());
    	output.putFloat("blue", this.getBlue());
    }
    
    @Override
    public boolean shouldRenderAtSqrDistance(double distance) {
        double d0 = 64.0 * getViewScale();
        return distance < d0 * d0;
    }
}
