package tamaized.aov.common.core.abilities.astro;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
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
import tamaized.aov.common.entity.EntitySpellAoVParticles;
import tamaized.aov.common.entity.EntitySpellVanillaParticles;
import tamaized.aov.proxy.CommonProxy;
import tamaized.aov.registry.SoundEvents;

import java.util.List;

public class AspectedHelios extends AbilityBase {

	private static final ResourceLocation icon = new ResourceLocation(AoV.modid, "textures/spells/aspectedhelios.png");

	private static final int charges = 2;
	private static final int distance = 20;
	private static final int heal = 8;

	public AspectedHelios() {
		super(

				new TextComponentTranslation(getStaticName()),

				new TextComponentTranslation(""),

				new TextComponentTranslation("aov.spells.global.charges", charges),

				new TextComponentTranslation("aov.spells.global.range", distance),

				new TextComponentTranslation("aov.spells.global.healing", heal),

				new TextComponentTranslation(""),

				new TextComponentTranslation("aov.spells.aspectedhelios.desc")

		);
	}

	public static String getStaticName() {
		return "aov.spells.aspectedhelios.name";
	}

	@Override
	public ResourceLocation getIcon() {
		return icon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getName() {
		return I18n.format(getStaticName());
	}

	@Override
	public int getCoolDown() {
		return 2;
	}

	@Override
	public int getMaxCharges() {
		return charges;
	}

	@Override
	public int getChargeCost() {
		return 1;
	}

	@Override
	public double getMaxDistance() {
		return distance;
	}

	@Override
	public boolean usesInvoke() {
		return false;
	}

	@Override
	public boolean cast(Ability ability, EntityPlayer caster, EntityLivingBase target) {
		IAoVCapability cap = caster.hasCapability(CapabilityList.AOV, null) ? caster.getCapability(CapabilityList.AOV, null) : null;
		if (cap == null)
			return false;
		EntityLivingBase e = target != null && IAoVCapability.selectiveTarget(cap, target) ? target : caster;
		List<EntityLivingBase> list = e.world.getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(e.getPosition().add(-distance, -distance, -distance), e.getPosition().add(distance, distance, distance)));
		for (EntityLivingBase entity : list) {
			if (entity == caster || IAoVCapability.selectiveTarget(cap, entity)) {
				entity.heal(heal);
				entity.world.spawnEntity(new EntitySpellAoVParticles(entity.world, entity, CommonProxy.ParticleType.Heart, 0x00FF87FF, 5));
				entity.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 600));
			}
			cap.addExp(caster, 15, this);
		}
		SoundEvents.playMovingSoundOnServer(SoundEvents.aspectedhelios, e);
		return true;
	}

}
