package engijava;

public class Skill {
    private String skillName;
    private int basePower;
    private int masteryLevel;
    private Vector<Element>elements;

    public Skill() {
        skillName = "Stand still";
        basePower = 10;
        masteryLevel = 1;
        elements = new Vector<Element>();
        elements.add(FIRE);
        elements.add(WATER);
        elements.add(ELECTRIC);
        elements.add(GROUND);
        elements.add(ICE);
    }

    public Skill(String sName, int bPower, int mLevel, String elmt) {
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
            elements.add(elmts[i]);
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

    public Vector<Element> getElement() {
        return elements;
    }

    public void masteryLevelUp() {
        masteryLevel++;
    }

    public boolean isEqual(Skill s) {
        return skillName == s.getSkillName();
    }
}