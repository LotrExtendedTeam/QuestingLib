package com.lotrextendedteam.questinglib.categories;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.lotrextendedteam.questinglib.Quest;
import com.lotrextendedteam.questinglib.QuestContext;
import com.lotrextendedteam.questinglib.restrictions.QuestingRestriction;

public abstract class QuestingSubCategory {
	private final int weight;
	private final List<String> restrictionIds = new ArrayList<>();

	protected QuestingSubCategory(int weight) {
		this.weight = weight;
	}

	public int getWeight() {
		return weight;
	}

	public void registerRestriction(String restrictionId) {
		restrictionIds.add(restrictionId);
	}

	public boolean passesRestrictions(QuestContext context, Map<String, QuestingRestriction> globalRestrictions) {
		for (String id : restrictionIds) {
			QuestingRestriction restriction = globalRestrictions.get(id);
			if (restriction == null || !restriction.canApply(context)) {
				return false;
			}
		}
		return true;
	}

	public boolean isValid(QuestContext context, Map<String, QuestingRestriction> globalRestrictions) {
		return passesRestrictions(context, globalRestrictions) && isValidInternal(context);
	}

	protected abstract boolean isValidInternal(QuestContext context);

	public abstract List<Quest> generateQuests(QuestContext context);
}