import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        String message = "Four score and seven years ago our fathers brought forth on this continent, a new nation, conceived in Liberty, and dedicated to the proposition that all men are created equal.\n" +
                "\n" +
                "Now we are engaged in a great civil war, testing whether that nation, or any nation so conceived and so dedicated, can long endure. We are met on a great battle-field of that war. We have come to dedicate a portion of that field, as a final resting place for those who here gave their lives that that nation might live. It is altogether fitting and proper that we should do this.\n" +
                "\n" +
                "But, in a larger sense, we can not dedicate -- we can not consecrate -- we can not hallow -- this ground. The brave men, living and dead, who struggled here, have consecrated it, far above our poor power to add or detract. The world will little note, nor long remember what we say here, but it can never forget what they did here. It is for us the living, rather, to be dedicated here to the unfinished work which they who fought here have thus far so nobly advanced. It is rather for us to be here dedicated to the great task remaining before us -- that from these honored dead we take increased devotion to that cause for which they gave the last full measure of devotion -- that we here highly resolve that these dead shall not have died in vain -- that this nation, under God, shall have a new birth of freedom -- and that government of the people, by the people, for the people, shall not perish from the earth.";
        message = message.toLowerCase();
        Map<Character,Long> map = getLetterMap(message);
        SortedList list = new SortedList(map);
        while(list.size()>=2)
        {
            Node nodeOne = list.remove();
            Node nodeTwo = list.remove();
            list.add(new Node(nodeOne,nodeTwo));
        }
        Map<Character,String> dictionary = list.getBinaryValues();
        String encodedMessage = encode(dictionary,message);
        for(var x: dictionary.keySet().stream().sorted(Comparator.comparingInt(l -> dictionary.get(l).length())).collect(Collectors.toList()))
        {
            System.out.println(x + " ---- " + dictionary.get(x) );
        }

        System.out.println("Encoded message: " + encodedMessage );


        System.out.println("Decoded : " + decode(flipMap(dictionary),encodedMessage));

    }

    private static Map<String,Character> flipMap(Map<Character, String> dictionary) {
        Map<String, Character> flippedMap = new HashMap<>();
        for (var c : dictionary.keySet()) {
            flippedMap.put(dictionary.get(c),c);
        }
        return flippedMap;
    }

    private static String encode(Map<Character, String> dictionary, String message) {
        String encodedMessage = "";
        for (var c: message.toCharArray()) {
            encodedMessage += dictionary.get(c);
        }
        return encodedMessage;
    }
    private static String decode(Map<String, Character> dictionary,String s) {
        StringBuilder result = new StringBuilder();
        StringBuilder message = new StringBuilder();
        for(var letter: s.toCharArray())
        {
            message.append(letter);
            if(dictionary.keySet().contains(message.toString()))
            {
                result.append(dictionary.get(message.toString()));
                message.delete(0,message.length());
            }
        }
        return result.toString();
    }

    public static Map<Character,Long> getLetterMap(String message) {
        List<Character> characterList =message.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
       return characterList.stream().collect(Collectors.groupingBy(c -> c,Collectors.counting()));
    }
}
