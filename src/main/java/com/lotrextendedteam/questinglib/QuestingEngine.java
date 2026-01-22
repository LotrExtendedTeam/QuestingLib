package com.lotrextendedteam.questinglib;

import java.util.HashMap;
import java.util.Map;

public class QuestingEngine {
	// Integer is a placeholder in lieu of a proper construct 
	private static Map<String, Integer> questCategories = new HashMap<>();
	private static Map<String, Integer> questRestrictions = new HashMap<>();

	private QuestingEngine() {}

	public static QuestingEngine initializeEngine () {
		return new QuestingEngine();
	}

	public QuestingEngine registerQuestCategory(String name, int id) throws IllegalArgumentException {
		if(questCategories.containsKey(name)) {
			throw new IllegalArgumentException("A quest category of the name [" + name + "] already exists!");
		}
		questCategories.put(name, id);
		return this;
	}

	public QuestingEngine registerQuestRestriction(String name, int id) throws IllegalArgumentException  {
		if(questRestrictions.containsKey(name)) {
			throw new IllegalArgumentException("A quest restriction of the name [" + name + "] already exists!");
		}
		questRestrictions.put(name, id);
		return this;
	}
}
