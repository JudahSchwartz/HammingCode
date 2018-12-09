import java.util.*;

public class SortedList {
    LinkedList<Node> backingList = new LinkedList<>();
    static Map<Character, String> finalMap = new HashMap<>();
    static Stack<Character> binaryStack = new Stack<>();
    public SortedList(Map<Character,Long> letters)
    {
        for (var x: letters.keySet()) {
            add(new Node(x,letters.get(x)));
        }
    }

    public Node remove()
    {
        return backingList.remove();
    }
    public int size()
    {
        return backingList.size();
    }
    public void add(Node node) {
        var iterator = backingList.listIterator();
        boolean added = false;
        while (iterator.hasNext()&& !added)
        {
            if(iterator.next().value >= node.value)
            {
                iterator.previous();//TODO kludgey
                iterator.add(node);
                added = true;
            }
        }
        if(!added)
        {
            backingList.add(node);
        }
    }


    void printToMap(Node n)
    {
        if(n.character == '*')
        {
            binaryStack.push('0');
            printToMap(n.left);
            binaryStack.pop();
            binaryStack.push('1');
            printToMap(n.right);
            binaryStack.pop();
        }
        else
        {
            StringBuilder code = new StringBuilder();
            for(Character c : binaryStack)
            {
                code.append(c);
            }
            finalMap.put(n.character, code.toString());
        }

    }
    public Map<Character,String> getBinaryValues() {
        printToMap(backingList.get(0));
        return finalMap;
    }
}
