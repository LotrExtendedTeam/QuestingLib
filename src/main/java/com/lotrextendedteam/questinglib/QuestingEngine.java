package com.lotrextendedteam.questinglib;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.lotrextendedteam.questinglib.categories.QuestingCategory;
import com.lotrextendedteam.questinglib.categories.QuestingSubCategory;
import com.lotrextendedteam.questinglib.restrictions.QuestingRestriction;

public class QuestingEngine {
	private final Map<String, QuestingCategory> questCategories = new HashMap<>();
	private final Map<String, QuestingRestriction> questRestrictions = new HashMap<>();
	private final Random random = new Random();

	private QuestingEngine() {}

	public static QuestingEngine initializeEngine() {
		return new QuestingEngine();
	}

	public QuestingEngine registerQuestCategory(String name, QuestingCategory category) throws IllegalArgumentException {
		if(questCategories.containsKey(name)) {
			throw new IllegalArgumentException("A quest category of the name [" + name + "] already exists!");
		}
		questCategories.put(name, category);
		return this;
	}

	public QuestingEngine registerQuestRestriction(String name, QuestingRestriction restriction) throws IllegalArgumentException  {
		if(questRestrictions.containsKey(name)) {
			throw new IllegalArgumentException("A quest restriction of the name [" + name + "] already exists!");
		}
		questRestrictions.put(name, restriction);
		return this;
	}

	public Quest generateQuest(QuestContext context) {
		// Step 1: Filter categories by their own restrictions
		List<QuestingCategory> validCategories = new ArrayList<>();
		for (QuestingCategory category : questCategories.values()) {
			if (category.passesRestrictions(context, questRestrictions)) {
				validCategories.add(category);
			}
		}

		if (validCategories.isEmpty()) return null;

		QuestingCategory chosenCategory = weightedPickCategory(validCategories);

		// Step 2: Filter subcategories
		List<QuestingSubCategory> validSubs =
				chosenCategory.getValidSubCategories(context, questRestrictions);

		if (validSubs.isEmpty()) return null;

		QuestingSubCategory chosenSub = weightedPickSub(validSubs);

		// Step 3: Generate & pick quest
		List<Quest> pool = chosenSub.generateQuests(context);
		return pool.get(random.nextInt(pool.size()));
	}

	private QuestingCategory weightedPickCategory(List<QuestingCategory> list) {
		int total = list.stream().mapToInt(QuestingCategory::getWeight).sum();
		int roll = random.nextInt(total);
		int running = 0;

		for (QuestingCategory cat : list) {
			running += cat.getWeight();
			if (roll < running) return cat;
		}
		return list.get(0);
	}

	private QuestingSubCategory weightedPickSub(List<QuestingSubCategory> list) {
		int total = list.stream().mapToInt(QuestingSubCategory::getWeight).sum();
		int roll = random.nextInt(total);
		int running = 0;

		for (QuestingSubCategory sub : list) {
			running += sub.getWeight();
			if (roll < running) return sub;
		}
		return list.get(0);
	}
}