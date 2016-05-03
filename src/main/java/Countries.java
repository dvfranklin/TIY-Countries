import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Countries {
    public static void main(String[] args) throws IOException{

        // open countries.txt, create arraylist of country objects, hashmap them to their first letter
        File input = new File("countries.txt");
        ArrayList<Country> countries = new ArrayList<>(createArray(input));
        HashMap<String, ArrayList<Country>> countryKey = new HashMap<>(createHash(countries));

        // asks user for a letter, then writes all countries beginning with that letter to JSON
        fileToWrite(countryKey);


        // this code was just to test that JSON could be parsed

        /*Gson gson = new GsonBuilder().create();
        File test = new File("a_countries.json");
        Scanner readFile = new Scanner(test);


        readFile.useDelimiter("\\Z");
        String contents = readFile.next();
        Type listType = new TypeToken<ArrayList<Country>>(){}.getType();
        ArrayList<Country> newCountries = gson.fromJson(contents, listType);
        System.out.println(newCountries);
        readFile.close();*/
    }


    /**
     * Takes a character (x) from the user, finds all countries beginning with that letter,
     * and outputs them in JSON format to (x)_countries.json
     * @param countryKey HashMap mapping characters to an ArrayList of countries beginning with that character
     * @throws IOException Error associated with writing to a file
     */
    private static void fileToWrite(HashMap<String, ArrayList<Country>> countryKey) throws IOException{
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter a single letter: ");
        String letterString = scanner.next().toLowerCase();
        if(letterString.length() > 1){
            // exception
        }
        char letter = letterString.trim().charAt(0);

        Gson gson = new GsonBuilder().create();
        Type countryType = new TypeToken<ArrayList<Country>>(){}.getType();
        writeFile(letter + "_countries.json", gson.toJson(countryKey.get(""+letter), countryType));
    }


    /**
     * Writes String to a file
     * @param fileName The location of the file to written to
     * @param fileContent The String to be written to the file
     * @throws IOException Error associated with writing to an external file
     */
    private static void writeFile(String fileName, String fileContent) throws IOException {
        File f = new File(fileName);
        FileWriter fw = new FileWriter(f);
        fw.write(fileContent);
        fw.close();
    }


    /**
     * Reads through a file, parses abbreviations & country names and creates an ArrayList of Country objects
     * @param input The file to be parsed
     * @return An ArrayList of Country objects
     * @throws IOException Error associated with reading from an external file
     */
    private static ArrayList<Country> createArray(File input) throws IOException{
        ArrayList<Country> countries = new ArrayList<>();
        Scanner parseCountries = new Scanner(input);

        // use either | or new line as delimiter
        parseCountries.useDelimiter("[\\n\\|]");

        // read through file, assign first value to Country.abbreviation, second value to Country.countryName
        // create each Country object and add it to the ArrayList
        while(parseCountries.hasNext()){
            String abbreviation = parseCountries.next();
            String countryName = parseCountries.next();
            Country country = new Country(abbreviation, countryName);
            countries.add(country);
        }
        parseCountries.close();

        return countries;
    }

    /**
     * Creates a HashMap associating characters with an ArrayList of Countries that begin with that character
     * @param countries An ArrayList of countries to be mapped
     * @return HashMap pairing charaters with countries
     */
    private static HashMap<String, ArrayList<Country>> createHash(ArrayList<Country> countries){
        HashMap<String, ArrayList<Country>> countryKey = new HashMap();

        // loop through each letter of the alphabet
        for(char alphabet = 'a'; alphabet <= 'z'; alphabet++) {
            //create a new ArrayList for each letter
            ArrayList<Country> hashCountries = new ArrayList<>();

            //if country begins with this letter, add it to the ArrayList, then map the ArrayList to the letter
            for (Country c : countries) {
                if (c.getCountryName().charAt(0) == alphabet) {
                    hashCountries.add(c);
                    countryKey.put("" + alphabet, hashCountries);
                }
            }
        }

        return countryKey;
    }

}
