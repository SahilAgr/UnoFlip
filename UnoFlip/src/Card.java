public class Card {

    private int cardNum;
    private Colour colour;
    private Special special;
    public enum Colour{RED,YELLOW,BLUE,GREEN}
    public enum Special{WILD,REVERSE,SKIP, WILD_DRAW_TWO_CARDS}

    public Card(int cardNumber, Colour colour, Special specialCard){
        if(cardNumber <= 9 && cardNumber >= 0){
            this.cardNum = cardNumber;
        }
        else {
            System.out.println("Cannot create a card with a number of: " + cardNumber + " in UNO");
        }

        this.colour = colour;
        this.special = specialCard;

    }

    public int getCardNum(){
        return this.cardNum;
    }

    public Colour getCardColour(){
        return this.colour;
    }

    public Special getSpecialType(){
        return this.special;
    }

    public String toString(){
        return "Card Number: " + getCardNum() + ", Card Colour: " + getCardColour() + ", Card Special Type: " + getSpecialType();
    }
}
