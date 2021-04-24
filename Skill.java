import java.util.ArrayList;
import java.util.Vector;

public class Skill {
    private String skillName;
    private int basePower;
    private int masteryLevel;
    private ArrayList<Element> elements;

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
        masteryLevel++;
    }

    public boolean isEqual(Skill s) {
        return skillName == s.getSkillName();
    }
}