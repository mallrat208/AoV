package tamaized.aov.common.core.skills.healer.cores;

import tamaized.aov.common.core.abilities.AbilityBase;
import tamaized.aov.common.core.skills.AoVSkill;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;

import java.util.ArrayList;
import java.util.List;

public class HealerSkillCore1 extends AoVSkill {

	private static final List<AbilityBase> spells = new ArrayList<AbilityBase>();

	static {
		spells.add(AbilityBase.cureLightWounds);
	}

	public HealerSkillCore1() {
		super(spells,

				TextFormatting.AQUA + "Class Core: Healer",

				TextFormatting.RED + "Obtaining this Skill",

				TextFormatting.RED + "prevents you from taking",

				TextFormatting.RED + "skills from any other class!",

				"",

				TextFormatting.GREEN + "+1 Charge",

				"",

				TextFormatting.YELLOW + "Added Spell: Cure Light Wounds"

		);
	}

	@Override
	public String getName() {
		return "HealerSkillCore1";
	}

	@Override
	protected Buffs setupBuffs() {
		return new Buffs(1, 0, 0, 0, false);
	}

	@Override
	public ResourceLocation getIcon() {
		return AbilityBase.cureLightWounds.getIcon();
	}

	@Override
	public boolean isClassCore() {
		return true;
	}

	@Override
	public AoVSkill getParent() {
		return null;
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
		return 0;
	}

}
