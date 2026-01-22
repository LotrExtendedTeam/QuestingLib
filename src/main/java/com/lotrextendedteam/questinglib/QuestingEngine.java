package com.lotrextendedteam.questinglib;

import java.util.HashMap;
import java.util.Map;

import com.lotrextendedteam.questinglib.categories.QuestingCategory;
import com.lotrextendedteam.questinglib.restrictions.QuestingRestriction;

public class QuestingEngine {
	private final Map<String, QuestingCategory> questCategories = new HashMap<>();
	private final Map<String, QuestingRestriction> questRestrictions = new HashMap<>();

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
}
