package com.lotrextendedteam.questinglib.categories;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import com.lotrextendedteam.questinglib.Quest;
import com.lotrextendedteam.questinglib.QuestContext;

public abstract class QuestingCategory implements QuestingNode {
	private final int weight;
	private final List<String> restrictionIds = new ArrayList<>();

	protected QuestingCategory(int weight) {
		this.weight = weight;
	}

	@Override
	public int getWeight() {
		return weight;
	}

	public void registerRestriction(String restrictionId) {
		restrictionIds.add(restrictionId);
	}

	@Override
	public boolean isValid(QuestContext context, Map<String, Predicate<QuestContext>> globalRestrictions) {
		for (String id : restrictionIds) {
			Predicate<QuestContext> restriction = globalRestrictions.get(id);
			if (restriction == null || !restriction.test(context)) {
				return false;
			}
		}
		return isValidInternal(context);
	}

	protected abstract boolean isValidInternal(QuestContext context);

	public abstract Quest generateQuest(QuestContext context);

	@Override
	public List<QuestingCategory> getValidCategories(QuestContext context, Map<String, Predicate<QuestContext>> globalRestrictions) {
		return isValid(context, globalRestrictions) ? Collections.singletonList(this) : Collections.emptyList();
	}
}