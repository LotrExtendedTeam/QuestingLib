package com.lotrextendedteam.questinglib.example;

import java.util.Random;
import java.util.UUID;

import com.lotrextendedteam.questinglib.Quest;
import com.lotrextendedteam.questinglib.QuestContext;
import com.lotrextendedteam.questinglib.categories.QuestingCategory;

public class KillQuestingCategory extends QuestingCategory{

	protected KillQuestingCategory(int weight) {
		super(weight);
	}

	@Override
	protected boolean isValidInternal(QuestContext context) {
		return true;
	}

	@Override
	public Quest generateQuest(QuestContext context) {
		return new KillQuest(UUID.randomUUID(), new Random().nextInt(context.get("killCount"))+1, "EvilBees");
	}

}
