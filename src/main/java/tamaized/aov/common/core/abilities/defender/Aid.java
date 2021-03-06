package tamaized.aov.common.core.abilities.defender;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tamaized.aov.AoV;
import tamaized.aov.common.capabilities.CapabilityList;
import tamaized.aov.common.capabilities.aov.IAoVCapability;
import tamaized.aov.common.core.abilities.Ability;
import tamaized.aov.common.core.abilities.AbilityBase;
import tamaized.aov.common.helper.ParticleHelper;
import tamaized.aov.proxy.CommonProxy;
import tamaized.aov.registry.AoVPotions;
import tamaized.aov.registry.SoundEvents;

import java.util.List;

public class Aid extends AbilityBase {

	private final static String name = "aov.spells.aid.name";
	private final static int charges = 5;
	private final static double range = 3;

	public Aid() {
		super(

				new TextComponentTranslation(name),

				new TextComponentTranslation(""),

				new TextComponentTranslation("aov.spells.global.charges", charges),

				new TextComponentTranslation("aov.spells.global.range", range),

				new TextComponentTranslation(""),

				new TextComponentTranslation("aov.spells.aid.desc")

		);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getName() {
		return I18n.format(name);
	}

	@Override
	public double getMaxDistance() {
		return range;
	}

	@Override
	public int getMaxCharges() {
		return charges;
	}

	@Override
	public boolean usesInvoke() {
		return true;
	}

	protected int getParticleColor() {
		return 0xFFFFFFFF;
	}

	@Override
	public boolean cast(Ability ability, EntityPlayer player, EntityLivingBase e) {
		IAoVCapability cap = player.hasCapability(CapabilityList.AOV, null) ? player.getCapability(CapabilityList.AOV, null) : null;
		if (cap == null)
			return false;
		if (cap.getInvokeMass())
			castAsMass(player, cap);
		else if (e == null) {
			addPotionEffects(player);
		} else {
			if (IAoVCapability.selectiveTarget(cap, e))
				addPotionEffects(e);
		}
		SoundEvents.playMovingSoundOnServer(SoundEvents.cast_2, player);
		cap.addExp(player, 12, this);
		return true;
	}

	private void addPotionEffects(EntityLivingBase entity) {
		entity.addPotionEffect(new PotionEffect(MobEffects.ABSORPTION, 20 * (60 * 5)));
		entity.addPotionEffect(new PotionEffect(AoVPotions.aid, 20 * (60 * 5)));
	}

	private void castAsMass(EntityLivingBase target, IAoVCapability cap) {
		int range = (int) (getMaxDistance() * 2);
		ParticleHelper.spawnParticleMesh(ParticleHelper.MeshType.BURST, CommonProxy.ParticleType.Fluff, target.world, target.getPositionVector(), range, getParticleColor());
		List<EntityLivingBase> list = target.world.getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(target.getPosition().add(-range, -range, -range), target.getPosition().add(range, range, range)));
		for (EntityLivingBase entity : list) {
			if (IAoVCapability.selectiveTarget(cap, entity)) {
				addPotionEffects(entity);
				cap.addExp(target, 12, this);
			}
		}
	}

	@Override
	public int getChargeCost() {
		return 1;
	}

	@Override
	public int getCoolDown() {
		return 12;
	}

	@Override
	public ResourceLocation getIcon() {
		return new ResourceLocation(AoV.modid, "textures/spells/aid.png");
	}
}
