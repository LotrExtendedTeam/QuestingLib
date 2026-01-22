package com.lotrextendedteam.questinglib.restrictions;

public abstract class QuestingRestriction {
	private final String restrictionId;
	
	public QuestingRestriction(String restrictionId) {
		this.restrictionId = restrictionId;
	}

	abstract boolean canApply();

	public String getRestrictionId() {
		return restrictionId;
	}
}
