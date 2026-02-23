package com.lotrextendedteam.questinglib.categories;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import com.lotrextendedteam.questinglib.QuestContext;

public interface QuestingNode {

	boolean isValid(QuestContext context, Map<String, Predicate<QuestContext>> globalRestrictions);
	
	List<QuestingCategory> getValidCategories(QuestContext context, Map<String, Predicate<QuestContext>> globalRestrictions);

	int getWeight();
}
