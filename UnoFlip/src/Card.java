public class Card {
    private Integer cardNum;
    private Colour colour;
    private Special special;
    public enum Colour{RED,YELLOW,BLUE,GREEN,BLACK}
    public enum Special{WILD,REVERSE,SKIP, WILD_DRAW_TWO_CARDS}

    public Card(Integer cardNumber, Colour colour, Special specialCard){

        if(cardNumber == -1){
            // This condition is for the special cards only that don't have a number
            this.cardNum = -1;
        }
        else if(cardNumber <= 9 && cardNumber >= 0){
            this.cardNum = cardNumber;
        }
        else {
            System.out.println("Cannot create a card with a number of: " + cardNumber + " in UNO");
        }

        this.colour = colour;
        this.special = specialCard;

    }

    public Integer getCardNum(){
        return this.cardNum;
    }

    public Colour getCardColour(){
        return this.colour;
    }

    public Special getSpecialType(){
        return this.special;
    }

    public String toString(){
        return "Card Number: " + getCardNum() + ", Card Colour: " + getCardColour() + ", Card Special Type: " + getSpecialType() + "\n";
    }
}
