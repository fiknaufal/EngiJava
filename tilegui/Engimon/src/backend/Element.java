package backend;
import java.util.HashMap;
import java.util.Map;

public enum Element {
    NONE(0), 
    FIRE(1), 
    WATER(2), 
    ELECTRIC(3), 
    GROUND(4), 
    ICE(5);

    private int value;
    private static Map<Integer, Element> map = new HashMap<Integer, Element>();

    private Element(int value) {
        this.value = value;
    }

    static {
        for (Element elmt : Element.values()) {
            map.put(elmt.value, elmt);
        }
    }

    public static Element valueOf(int elmt) {
        return (Element) map.get(elmt);
    }

    public int getValue() {
        return value;
    }
}
