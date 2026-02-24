package com.lotrextendedteam.questinglib.example;

import com.lotrextendedteam.questinglib.Quest;
import com.lotrextendedteam.questinglib.QuestContext;
import com.lotrextendedteam.questinglib.QuestingEngine;

public class ExampleQuesting {
	private static QuestingEngine engine;

	public static void main(String[] args) {
		engine = QuestingEngine.initializeNewEngine().registerQuestCategory("killQuest", new KillQuestingCategory(1));
		QuestContext context = new QuestContext();
		context.put("killCount", 24);
		Quest quest =engine.generateQuest(context);
		if(quest!=null) {
	        System.out.println(quest.getString());
		}
    }
}
