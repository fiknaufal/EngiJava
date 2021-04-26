import java.util.ArrayList;
import java.util.Vector;

public class Skill {
    private String skillName;
    private int basePower;
    private int masteryLevel;
    private ArrayList<Element> elements = new ArrayList<>();

    public Skill() {
        skillName = "Stand still";
        basePower = 10;
        masteryLevel = 1;
        elements = new ArrayList<Element>();
        elements.add(Element.FIRE);
        elements.add(Element.WATER);
        elements.add(Element.ELECTRIC);
        elements.add(Element.GROUND);
        elements.add(Element.ICE);
    }

    public Skill(String sName, int bPower, int mLevel, Element elmt) {
        skillName = sName;
        basePower = bPower;
        masteryLevel = mLevel;
        elements.add(elmt);
    }

    public Skill(String sName, int bPower, int mLevel, Vector<Element> elmts) {
        skillName = sName;
        basePower = bPower;
        masteryLevel = mLevel;

        for (int i = 0; i < elmts.size(); i++) {
            elements.add(elmts.get(i));
        }
    }

    public String getSkillName() {
        return skillName;
    }

    public int getBasePower(){
        return basePower;
    }

    public int getMasteryLevel(){
        return masteryLevel;
    }

    public ArrayList<Element> getElement() {
        return elements;
    }

    public void masteryLevelUp() {
        if (masteryLevel < 3)
            masteryLevel++;
    }

    public boolean isEqual(Skill s) {
        return skillName == s.getSkillName();
    }

    public static Element stringToElement(String s) {
        switch(s){
            case "FIRE":
                return Element.FIRE;
            case "WATER":
                return Element.FIRE;
            case "ELECTRIC":
                return Element.FIRE;
            case "ICE":
                return Element.FIRE;
            case "GROUND":
                return Element.FIRE;
            case "NONE":
                return Element.NONE;
        }
        return Element.NONE;
    }
}