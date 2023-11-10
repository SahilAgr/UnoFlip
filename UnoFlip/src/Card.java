import java.util.ArrayList;
import java.util.Scanner;

public class Card extends ArrayList<Card> {
    private String imagePath;

    private Rank rank;
    private Colour colour;
    private Special special;
    private Type type;

    public enum Type {
        LIGHT, DARK
    }
    public enum Colour{RED,YELLOW,BLUE,GREEN,BLACK}
    public enum Special{DRAW_ONE,REVERSE,SKIP,FLIP,WILD,WILD_DRAW_TWO_CARDS}

    public enum Rank{ONE,TWO,THREE,FOUR,FIVE,SIX,SEVEN,EIGHT,NINE}

    /**
     * Constructs a new Uno Card with a specified rank, colour, and special attributes.
     * @param rank The rank of the card (eg. ONE, TWO, etc.)
     * @param colour The colour of the card (eg. RED, YELLOW, GREEN, BLUE)
     * @param specialCard The special attributes of the card (eg. DRAW_ONE, SKIP, etc.)
     */
    public Card(Rank rank, Colour colour, Special specialCard, Type type){
        this.rank = rank;
        this.colour = colour;
        this.special = specialCard;
        this.type = type;
        String basePath = "uno_cards/";


        if (special != null) {
            this.imagePath = colour.toString().toLowerCase() + "_" + special.toString().toLowerCase() + ".png";
        } else {
            this.imagePath = colour.toString().toLowerCase() + "_" + rank.toString().toLowerCase() + ".png";
        }
    }

    public Type getType(){
        return this.type;
    }

    /**
     * Retrieves rank of card.
     * @return Rank of card.
     */
    public Rank getCardNum(){
        return this.rank;
    }

    /**
     * Retrieves colour of card.
     * @return Colour of card.
     */
    public Colour getCardColour(){
        return this.colour;
    }

    /**
     * Retrieves special attributes of card.
     * @return Special attributes of card.
     */
    public Special getSpecialType(){
        return this.special;
    }

    /**
     * Sets new colour of card, typically used for Wild Cards.
     * @param colour The new colour to set for the card.
     */
    public void setColour(Card.Colour colour){
        this.colour = colour;
    }

    public String getImagePath() {
        return this.imagePath;
    }

    /**
     * Converts card to a string representation.
     * @return String representation of card (includes rank, colour, and/or special attributes)
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.rank != null) {
            sb.append(this.rank);
            sb.append(" ");
        }
        if (this.colour != null) {
            sb.append(this.colour);
            sb.append(" ");
        }
        if (this.special != null) {
            sb.append(this.special);
        }
        return sb.toString().trim();  // Removes any trailing spaces
    }
}
