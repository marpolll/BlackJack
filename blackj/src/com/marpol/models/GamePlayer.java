package com.marpol.models;

import java.util.List;

public interface GamePlayer {

	public void drawPlayerCard(Card card);

	public List<Card> getPlayerCardList();

	public int getPlayerScore();

	public String getName();
}
