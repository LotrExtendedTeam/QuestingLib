package com.lotrextendedteam.questinglib.restrictions;

import com.lotrextendedteam.questinglib.QuestContext;

public abstract class QuestingRestriction {
	private final String restrictionId;

	public QuestingRestriction(String restrictionId) {
		this.restrictionId = restrictionId;
	}

	public abstract boolean canApply(QuestContext context);

	public String getRestrictionId() {
		return restrictionId;
	}
}