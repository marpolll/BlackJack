package com.marpol.caim;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.marpol.models.Card;
import com.marpol.utlis.AnsiConsole;

public class CardImageService {
	private List<CardImage> cardImageList;

	public CardImageService() {
		cardImageList = new ArrayList<>();
	}

	public void loadCardImage() {
		String cardFile = "src/data/cardData.txt";
		InputStream is = null;
		Scanner scan = null;

		try {
			is = new FileInputStream(cardFile);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
		scan = new Scanner(is);

		while (scan.hasNext()) {
			String line = scan.nextLine();
			String[] card = line.split(",");
			CardImage cardImage = new CardImage();
			cardImage.setCardImage(card);

			cardImageList.add(cardImage);
		}
		scan.close();
	}

	public Card drawCardRankSet(Card card) {

		List<CardImage> cardImageList = this.getCardImageList();
		if (card.getRank().equals("잭")) {
			card.setCardImage(cardImageList.get(10).getCardImage());
		} else if (card.getRank().equals("퀸")) {
			card.setCardImage(cardImageList.get(11).getCardImage());
		} else if (card.getRank().equals("킹")) {
			card.setCardImage(cardImageList.get(12).getCardImage());
		} else { // 잭, 퀸, 킹을 제외한 카드 이미지는 cardImageList 의 (value - 1)의 위치에 담겨있다
			card.setCardImage(cardImageList.get(card.getValue() - 1).getCardImage());
		}

		return card;
	}

	public Card drawCardSuitSet(Card card) {
		String[] standardImges = card.getCardImage();
		String[] changeImges = new String[standardImges.length];

		for (int i = 0; i < standardImges.length; i++) {

			String standardImg = standardImges[i];

			if(card.getSuit().equals("클럽")) {
				changeImges[i] = (standardImg.replace("&", AnsiConsole.GREEN("♣")));
			} else if(card.getSuit().equals("스페이드")) {
				changeImges[i] = (standardImg.replace("&", AnsiConsole.BLACK("♠")));
			} else if(card.getSuit().equals("하트")) {
				changeImges[i] = (standardImg.replace("&", AnsiConsole.RED("♥")));
			} else {
				changeImges[i] = (standardImg.replace("&", AnsiConsole.BLUE("◈")));
			}
		}

		card.setCardImage(changeImges);

		return card;
	}

	public List<CardImage> getCardImageList() {
		if (cardImageList.isEmpty()) {
			this.loadCardImage();
			return cardImageList;
		}
		return cardImageList;
	}

}