import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class UnoCardImageMapper {

    private Map<String, String> cardImageMap;

    public UnoCardImageMapper(String directoryPath) {
        cardImageMap = new HashMap<>();
        loadCardImages(directoryPath);
    }

    private void loadCardImages(String directoryPath) {
        File dir = new File(directoryPath);

        if (dir.exists() && dir.isDirectory()) {
            // Get all files from the directory
            File[] files = dir.listFiles((d, name) -> name.toLowerCase().endsWith(".png") || name.toLowerCase().endsWith(".jpg") || name.toLowerCase().endsWith(".jpeg"));
            if (files != null) {
                for (File file : files) {
                    // Assume the filename without extension is the name of the card
                    String cardName = file.getName().substring(0, file.getName().lastIndexOf('.'));
                    cardImageMap.put(cardName, file.getAbsolutePath());
                }
            }
        } else {
            System.err.println("The provided directory path does not exist or is not a directory.");
        }
    }

    public String getCardImagePath(String cardName) {
        return cardImageMap.get(cardName);
    }

    // Add a method to list all card names
    public void listAllCards() {
        cardImageMap.keySet().forEach(System.out::println);
    }

    // Example usage
    public static void main(String[] args) {
        UnoCardImageMapper mapper = new UnoCardImageMapper("E:\\study\\SYSC3110 NEW\\milestone2\\uno_cards\\uno_cards");

        // List all available card names
        mapper.listAllCards();

        // Retrieve the path of a specific UNO card
        String cardName = "red_3"; // Example card name
        String cardImagePath = mapper.getCardImagePath(cardName);
        System.out.println("The path of the " + cardName + " card is: " + cardImagePath);
    }
}
