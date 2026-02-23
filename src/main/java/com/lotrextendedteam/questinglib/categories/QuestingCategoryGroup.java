package com.lotrextendedteam.questinglib.categories;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.lotrextendedteam.questinglib.QuestContext;

public abstract class QuestingCategoryGroup implements QuestingNode {
	private final int weight;
	private final List<String> restrictionIds = new ArrayList<>();
	private final List<QuestingNode> children = new ArrayList<>();

	protected QuestingCategoryGroup(int weight) {
		this.weight = weight;
	}

	@Override
	public int getWeight() {
		return weight;
	}

	public void registerRestriction(String restrictionId) {
		restrictionIds.add(restrictionId);
	}

	public void registerCategory(QuestingNode pCategory) {
		children.add(pCategory);
	}

	@Override
	public List<QuestingCategory> getValidCategories(QuestContext context, Map<String, Predicate<QuestContext>> globalRestrictions) {
		 // If the group itself fails, whole branch fails
	    if (!isValid(context, globalRestrictions)) {
	        return Collections.emptyList();
	    }
	    return children.stream().map(child -> child.getValidCategories(context, globalRestrictions))
	            .flatMap(List::stream).collect(Collectors.toList());
	}

	@Override
	public boolean isValid(QuestContext context, Map<String, Predicate<QuestContext>> globalRestrictions) {
		for (String id : restrictionIds) {
			Predicate<QuestContext> restriction = globalRestrictions.get(id);
			if (restriction == null || !restriction.test(context)) {
				return false;
			}
		}
		return true;
	}
}