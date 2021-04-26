package backend;

public class Watermon extends Engimon {
    public Watermon(String name, String p1name, String p2name, String p1spc, String p2spc, int exp, int px, int py) {
        super(name, p1name, p2name, p1spc, p2spc,"Watermon", exp, Element.WATER, Element.NONE, px, py, "BYURBYURR");

        SkillGacha sg = new SkillGacha();
        skill = sg.getSkillGen(Element.WATER, Element.NONE);
        this.icon = "res/w.png";
    }

    public Watermon(int x, int px, int py) {
        super("Wild Watermon", "Watermon Ibu", "Watermon Ibu juga", "Watermon", "Watermon", "Watermon", x, Element.WATER, Element.NONE, px, py, "BYURBYURR");

        SkillGacha sg = new SkillGacha();
        skill = sg.getSkillGen(Element.WATER, Element.NONE);
        this.icon = "res/w.png";
    }

    public Watermon(String name, String p1name, String p2name, String p1spc, String p2spc, int exp, int px, int py, int health) {
        super(name, p1name, p2name, p1spc, p2spc,"Watermon", exp, Element.WATER, Element.NONE, px, py, "BYURBYURR", health);

        SkillGacha sg = new SkillGacha();
        skill = sg.getSkillGen(Element.WATER, Element.NONE);
        this.icon = "res/w.png";
    }

    public Watermon(int x, int px, int py, int health) {
        super("Wild Watermon", "Watermon Ibu", "Watermon Ibu juga", "Watermon", "Watermon", "Watermon", x, Element.WATER, Element.NONE, px, py, "BYURBYURR", health);

        SkillGacha sg = new SkillGacha();
        skill = sg.getSkillGen(Element.WATER, Element.NONE);
        this.icon = "res/w.png";
    }
}