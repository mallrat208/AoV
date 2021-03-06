package tamaized.aov.common.core.abilities.healer.Cores;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
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
import tamaized.aov.common.core.abilities.IAura;
import tamaized.aov.common.helper.ParticleHelper;
import tamaized.aov.proxy.CommonProxy;
import tamaized.aov.registry.SoundEvents;

import java.util.List;

public class PosEnergyAura extends AbilityBase implements IAura {

	private final static int charges = 6;
	private final static int range = 10;
	private final static int dmg = 2;
	private final static int life = 45;

	public PosEnergyAura() {
		super(

				new TextComponentTranslation(getStaticName()),

				new TextComponentTranslation(""),

				new TextComponentTranslation("aov.spells.global.charges", charges),

				new TextComponentTranslation("aov.spells.global.range", range),

				new TextComponentTranslation("aov.spells.global.healing", dmg),

				new TextComponentTranslation("aov.spells.global.length", life),

				new TextComponentTranslation(""),

				new TextComponentTranslation("aov.spells.posaura.desc")

		);
	}

	public static String getStaticName() {
		return "aov.spells.posaura.name";
	}

	@Override
	public boolean cast(Ability ability, EntityPlayer player, EntityLivingBase e) {
		IAoVCapability cap = player.getCapability(CapabilityList.AOV, null);
		if (cap == null)
			return false;
		cap.addAura(createAura(ability));
		SoundEvents.playMovingSoundOnServer(SoundEvents.aura, player);
		cap.addExp(player, 30, this);
		return true;
	}

	@Override
	public void castAsAura(EntityPlayer caster, IAoVCapability cap, int life) {
		int tick = (PosEnergyAura.life * 20) - life;
		if (tick > 0 && tick % 20 == 0) {
			ParticleHelper.spawnParticleMesh(ParticleHelper.MeshType.BURST, CommonProxy.ParticleType.Heart, caster.world, caster.getPositionVector(), range, 0xFFFF00FF);
			int a = (int) (dmg * (1f + (cap.getSpellPower() / 100f)));
			List<EntityLivingBase> list = caster.world.getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(caster.getPosition().add(-range, -range, -range), caster.getPosition().add(range, range, range)));
			for (EntityLivingBase entity : list) {
				if (entity.isEntityUndead())
					entity.attackEntityFrom(DamageSource.MAGIC, a);
				else if (IAoVCapability.selectiveTarget(cap, entity))
					entity.heal(a);
			}
		}
	}

	@Override
	public int getLife() {
		return life;
	}

	@Override
	public ResourceLocation getIcon() {
		return new ResourceLocation(AoV.modid, "textures/spells/posaura.png");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getName() {
		return I18n.format(getStaticName());
	}

	@Override
	public int getCoolDown() {
		return 60;
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
		return range;
	}

	@Override
	public boolean usesInvoke() {
		return false;
	}

}
