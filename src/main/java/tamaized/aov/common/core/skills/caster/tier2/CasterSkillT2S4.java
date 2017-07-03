package tamaized.aov.common.core.skills.caster.tier2;

import tamaized.aov.AoV;
import tamaized.aov.common.core.abilities.AbilityBase;
import tamaized.aov.common.core.skills.AoVSkill;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;

import java.util.ArrayList;
import java.util.List;

public class CasterSkillT2S4 extends AoVSkill {

	private static final ResourceLocation icon = new ResourceLocation(AoV.modid + ":textures/skills/CasterT2S4.png");

	private static final List<AbilityBase> spells = new ArrayList<AbilityBase>();

	static {

	}

	public CasterSkillT2S4() {
		super(spells,

				TextFormatting.AQUA + "null",

				TextFormatting.RED + "Requires: 4 Points Spent in Tree",

				"",

				TextFormatting.YELLOW + "Added Spell: null"

		);
	}

	@Override
	protected Buffs setupBuffs() {
		return new Buffs(0, 0, 0, 0, false);
	}

	@Override
	public ResourceLocation getIcon() {
		return icon;
	}

	public String getName() {
		return "CasterSkillT2S4";
	}

	@Override
	public boolean isClassCore() {
		return false;
	}

	@Override
	public AoVSkill getParent() {
		return AoVSkill.caster_tier_1_4;
	}

	@Override
	public int getCost() {
		return 1;
	}

	@Override
	public int getLevel() {
		return 0;
	}

	@Override
	public int getSpentPoints() {
		return 4;
	}

}
