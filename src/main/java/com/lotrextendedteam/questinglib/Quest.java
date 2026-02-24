package com.lotrextendedteam.questinglib;

import java.util.UUID;

public abstract class Quest {
	private final UUID id;

	public Quest(UUID id) {
		this.id = id;
	}

	public UUID getId() { 
		return id;
	}

	public abstract String getString();
}