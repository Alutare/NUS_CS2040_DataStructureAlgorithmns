//Chan Wei Wen Kevin A0272202W

import java.util.*;

class CardTradingGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int totalCards = scanner.nextInt(); 
        int cardTypes = scanner.nextInt();
        int comboCount = scanner.nextInt(); 

        int[] cardFrequencies = new int[cardTypes + 1];
        // Array of Card Frequencies
        for (int i = 0; i < totalCards; i++) {
            int card = scanner.nextInt();
            cardFrequencies[card]++;
        }

        ArrayList<CardType> cardTypeList = new ArrayList<>();

        for (int i = 1; i <= cardTypes; i++) {
            Long purchaseCost = scanner.nextLong();
            Long sellingProfit = scanner.nextLong();
            CardType type = new CardType(i, purchaseCost, sellingProfit);
            type.incrementCount(cardFrequencies[type.getTypeId()]);
            type.calculateValues();
            cardTypeList.add(type);
        }

        Collections.sort(cardTypeList, new CardTypeComparator());

        long totalProfit = 0;

        for (int i = 0; i < comboCount; i++) {
            CardType type = cardTypeList.get(i);
            totalProfit -= type.getPurchaseCost();
        }
        for (int i = comboCount; i < cardTypes; i++) {
            CardType type = cardTypeList.get(i);
            totalProfit += type.getSellingProfit();
        }

        if (totalCards == 1) {
            System.out.println(0);
        } else {
            System.out.println(totalProfit);
        }
    }
}

class CardType {
    private final int typeId;
    private Long purchaseCost;
    private Long sellingProfit;
    private int cardCount;

    CardType(int typeId, Long purchaseCost, Long sellingProfit) {
        this.typeId = typeId;
        this.purchaseCost = purchaseCost;
        this.sellingProfit = sellingProfit;
        this.cardCount = 0;
    }

    int getTypeId() {
        return typeId;
    }

    Long getPurchaseCost() {
        return purchaseCost;
    }

    Long getSellingProfit() {
        return sellingProfit;
    }

    int getCardCount() {
        return cardCount;
    }

    void incrementCount(int number) {
        this.cardCount = this.cardCount + number;
    }

    void calculateValues() {
        purchaseCost = (2 - cardCount) * purchaseCost;
        sellingProfit = cardCount * sellingProfit;
    }

    Long getTotalValue() {
        return purchaseCost + sellingProfit;
    }

    public int compareTo(CardType type) {
        return this.getTotalValue().compareTo(type.getTotalValue());
    }

    public String toString() {
        return "TypeId: " + typeId + ", PurchaseCost: " + purchaseCost + ", SellingProfit: " + sellingProfit + ", CardCount: " + cardCount + ", TotalValue: " + this.getTotalValue();
    }
}

class CardTypeComparator implements Comparator<CardType> {
    public int compare(CardType type1, CardType type2) {
        int valueComparison = type1.compareTo(type2);
        if (valueComparison == 0) {
            return type1.getPurchaseCost().compareTo(type2.getPurchaseCost());
        }
        return valueComparison;
    }
}
