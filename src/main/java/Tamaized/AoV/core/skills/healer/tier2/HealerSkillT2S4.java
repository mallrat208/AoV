package Tamaized.AoV.core.skills.healer.tier2;

import net.minecraft.util.ResourceLocation;
import Tamaized.AoV.AoV;
import Tamaized.AoV.core.abilities.AbilityBase;
import Tamaized.AoV.core.skills.AoVSkill;
import Tamaized.AoV.core.skills.healer.cores.HealerSkillCore1;

import com.mojang.realmsclient.gui.ChatFormatting;

public class HealerSkillT2S4 extends AoVSkill{
	
	private static final ResourceLocation icon = new ResourceLocation(AoV.modid+":textures/skills/HealerT2S4.png");

	public HealerSkillT2S4() {
		super(getUnlocalizedName(), AoVSkill.getSkillFromName(HealerSkillCore1.getUnlocalizedName()), 1, 0, 4, false,
				new AbilityBase[]{
					
				},
				ChatFormatting.AQUA+"Cure Blind",
				ChatFormatting.RED+"Requires: 4 Points Spent in Tree",
				"",
				ChatFormatting.YELLOW+"Added Spell: Cure Blind"
				);
	}

	@Override
	protected void setupBuffs() {
		buffs = new Buffs(0, 0, 0, 0);
	}

	@Override
	public ResourceLocation getIcon() {
		return icon;
	}
	
	public static String getUnlocalizedName(){
		return "HealerSkillT2S4";
	}

}