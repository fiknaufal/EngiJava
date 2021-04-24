public class FireElectricmon extends Engimon {
    public FireElectricmon(String name, String p1name, String p2name, String p1spc, String p2spc, int exp, int px, int py) {
        super(name, p1name, p2name, p1spc, p2spc,"FireElectricmon", exp, Elements.FIRE, Elements.ELECTRIC, px, py, "ROAR AYAY");

        SkillGacha sg = new SkillGacha();
        skill = sg.getSkillGen(Elements.FIRE, Elements.ELECTRIC);
    }

    public FireElectricmon(int x, int px, int py) {
        super("Wild FireElectricmon", "FireElectricmon Ibu", "FireElectricmon Ibu juga", "FireElectricmon", "FireElectricmon", "FireElectricmon", x, Elements.FIRE, Elements.ELECTRIC, px, py, "ROAR AYAY");

        SkillGacha sg = new SkillGacha();
        skill = sg.getSkillGen(Elements.FIRE, Elements.ELECTRIC);
    }
}