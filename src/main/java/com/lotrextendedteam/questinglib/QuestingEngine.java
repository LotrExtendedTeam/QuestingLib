package com.lotrextendedteam.questinglib;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.lotrextendedteam.questinglib.categories.QuestingCategoryGroup;
import com.lotrextendedteam.questinglib.categories.QuestingNode;
import com.lotrextendedteam.questinglib.categories.QuestingCategory;

public class QuestingEngine {
	private final Map<String, QuestingCategoryGroup> questCategoryGroups = new HashMap<>();
	private final Map<String, Predicate<QuestContext>> questRestrictions = new HashMap<>();
	private final Random random = new Random();

	private QuestingEngine() {}

	public static QuestingEngine initializeNewEngine() {
		return new QuestingEngine();
	}

	public QuestingEngine registerQuestCategory(String name, QuestingCategoryGroup category) throws IllegalArgumentException {
		if(questCategoryGroups.containsKey(name)) {
			throw new IllegalArgumentException("A quest category of the name [" + name + "] already exists!");
		}
		questCategoryGroups.put(name, category);
		return this;
	}

	public QuestingEngine registerQuestRestriction(String name, Predicate<QuestContext> restriction) throws IllegalArgumentException  {
		if(questRestrictions.containsKey(name)) {
			throw new IllegalArgumentException("A quest restriction of the name [" + name + "] already exists!");
		}
		questRestrictions.put(name, restriction);
		return this;
	}

	public Quest generateQuest(QuestContext context) {
	    // Step 1: valid groups
	    List<QuestingCategoryGroup> validGroups = questCategoryGroups.values().stream()
	            .filter(group -> group.isValid(context, questRestrictions))
	            .collect(Collectors.toList());

	    QuestingCategoryGroup chosenGroup = weightedPickSafe(validGroups);
	    if (chosenGroup == null) return null;

	    // Step 2: valid categories
	    List<QuestingCategory> validCategories = chosenGroup.getValidCategories(context, questRestrictions);

	    QuestingCategory chosenCategory = weightedPickSafe(validCategories);
	    if (chosenCategory == null) return null;

	    // Step 3: quest pool
	    List<Quest> pool = chosenCategory.generateQuests(context);
	    if (pool == null || pool.isEmpty()) return null;

	    return pool.get(random.nextInt(pool.size()));
	}

	private <T extends QuestingNode> T weightedPickSafe(List<T> list) {
	    if (list == null || list.isEmpty()) return null;
	    int total = list.stream() .mapToInt(T::getWeight).filter(w -> w > 0).sum();
	    if (total <= 0) return null; // prevents nextInt(0)
	    int roll = random.nextInt(total);
	    int running = 0;

	    for (T item : list) {
	        int weight = item.getWeight();
	        if (weight <= 0) continue;
	        running += weight;
	        if (roll < running) return item;
	    }
	    return null; // fully safe fallback
	}
}