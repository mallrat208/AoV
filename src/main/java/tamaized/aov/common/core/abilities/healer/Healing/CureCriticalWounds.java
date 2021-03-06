package tamaized.aov.common.core.abilities.healer.Healing;

import net.minecraft.util.ResourceLocation;
import tamaized.aov.AoV;
import tamaized.aov.common.core.abilities.healer.CureWounds;

public class CureCriticalWounds extends CureWounds {

	public CureCriticalWounds() {
		super(CureCriticalWounds.getStaticName(), 4, 2, 10);
	}

	public static String getStaticName() {
		return "aov.spells.curecritwounds.name";
	}

	@Override
	public ResourceLocation getIcon() {
		return new ResourceLocation(AoV.modid, "textures/spells/curecritwounds.png");
	}

	@Override
	public int getCoolDown() {
		return 5;
	}

	@Override
	public int getChargeCost() {
		return 1;
	}

	@Override
	protected int getParticleColor() {
		return 0xFF1414FF;
	}

}
