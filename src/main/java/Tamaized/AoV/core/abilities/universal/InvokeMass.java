package Tamaized.AoV.core.abilities.universal;

import Tamaized.AoV.AoV;
import Tamaized.AoV.capabilities.CapabilityList;
import Tamaized.AoV.capabilities.aov.IAoVCapability;
import Tamaized.AoV.core.abilities.Ability;
import Tamaized.AoV.core.abilities.AbilityBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;

public class InvokeMass extends AbilityBase {

	private final static int charges = -1;

	public InvokeMass() {
		super(getStaticName(),

				TextFormatting.YELLOW + getStaticName(),

				"",

				TextFormatting.DARK_PURPLE + "While Active, certain spells",

				TextFormatting.DARK_PURPLE + "and abilities have double",

				TextFormatting.DARK_PURPLE + "range and are cast as",

				TextFormatting.DARK_PURPLE + "an AoE (Area of Effect).",

				TextFormatting.RED + "This also doubles the cost and cooldown."

		);
	}

	@Override
	public void cast(Ability ability, EntityPlayer player, EntityLivingBase e) {
		if (player.world.isRemote) {
			sendPacketTypeSelf(ability);
		} else {
			IAoVCapability cap = player.getCapability(CapabilityList.AOV, null);
			if (cap == null) return;
			cap.toggleInvokeMass();
		}
	}

	@Override
	public ResourceLocation getIcon() {
		return new ResourceLocation(AoV.modid + ":textures/spells/InvokeMass.png");
	}

	@Override
	public String getName() {
		return getStaticName();
	}

	public static String getStaticName() {
		return "Invoke Mass";
	}

	@Override
	public int getCoolDown() {
		return 1;
	}

	@Override
	public int getMaxCharges() {
		return charges;
	}

	@Override
	public int getChargeCost() {
		return 0;
	}

	@Override
	public double getMaxDistance() {
		return 0;
	}

	@Override
	public boolean usesInvoke() {
		return false;
	}

}
