package com.lotrextendedteam.questinglib.example;

import java.util.UUID;

import com.lotrextendedteam.questinglib.Quest;

public class KillQuest extends Quest {
	private final int killCount;
	private final String killTarget;

	public KillQuest(UUID id, int killCount, String killTarget) {
		super(id);
		this.killCount = killCount;
		this.killTarget = killTarget;
	}

	@Override
	public String getString() {
		return "KillQuest:"+killTarget+" "+killCount+" times";
	}

}
