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

        if (specialCard == Special.WILD || specialCard == Special.WILD_DRAW_TWO_CARDS) {
            this.imagePath = basePath + specialToString(specialCard) + ".png";
        } else {
            String fileNamePart;
            if (specialCard != null) {

                fileNamePart = specialToString(specialCard);
            } else {

                fileNamePart = rankToString(rank);
            }

            String colourString = (colour != null) ? colour.toString().toLowerCase() : "";

            // Adjust the path based on card type
            if (type == Type.LIGHT) {
                this.imagePath = basePath + colourString + "_" + fileNamePart + ".png";
            } else if (type == Type.DARK) {
                this.imagePath = basePath + "dark_" + colourString + "_" + fileNamePart + ".png";
            }
        }
    }


    public Type getType(){
        return this.type;
    }

    private String rankToString(Rank rank) {
        // Convert rank enums to string as per your file naming convention
        // Example:
        switch (rank) {
            case ONE: return "one";
            case TWO: return "two";
            case THREE: return "three";
            case FOUR: return "four";
            case FIVE: return "five";
            case SIX: return "six";
            case SEVEN: return "seven";
            case EIGHT: return "eight";
            case NINE: return "nine";
            // Add cases for other ranks...
            default: return rank.toString().toLowerCase();
        }
    }

    private String specialToString(Special special) {
        switch (special) {
            case DRAW_ONE: return "draw_one";
            case REVERSE: return "reverse";
            case SKIP: return "skip";
            case FLIP: return "flip";
            case WILD: return "wild";
            case WILD_DRAW_TWO_CARDS: return "wild_draw_two";

            // Add cases for other ranks...
            default: return special.toString().toLowerCase();
        }

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
