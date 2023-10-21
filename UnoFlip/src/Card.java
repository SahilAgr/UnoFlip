public class Card {
    private Rank rank;
    private Colour colour;
    private Special special;
    public enum Colour{RED,YELLOW,BLUE,GREEN,BLACK}
    public enum Special{DRAW_ONE,REVERSE,SKIP,FLIP,WILD,WILD_DRAW_TWO_CARDS}

    public enum Rank{ONE,TWO,THREE,FOUR,FIVE,SIX,SEVEN,EIGHT,NINE}

    public Card(Rank rank, Colour colour, Special specialCard){

        this.rank = rank;
        this.colour = colour;
        this.special = specialCard;

    }

    public Rank getCardNum(){
        return this.rank;
    }

    public Colour getCardColour(){
        return this.colour;
    }

    public Special getSpecialType(){
        return this.special;
    }

    public String toString(){
        if (this.special == null){
            return getCardColour() + " " + getCardNum();
        }
        else {
            return getCardColour() + " " + getSpecialType();
        }
    }
}