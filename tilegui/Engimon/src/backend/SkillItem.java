package backend;

public class SkillItem {
    protected Skill skill;
    protected int jumlah;

    public SkillItem(Skill s, int j) {
        skill = s;
        jumlah = j;

    }

    public boolean isEqual(final SkillItem s) {
        return skill.isEqual(s.skill);
    }

    public Skill getSkill() {
        return skill;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void add(int n) {
        jumlah += n;
    }

    public void learnSkill(Engimon e) {
        boolean can = false;
        for(int i = 0; i < skill.getElement().size(); i++){
            if (skill.getElement().get(i) == e.getElement1() || skill.getElement().get(i) == e.getElement2()) {
                can = true;
            }
        }

        if (can) {
            if (e.addSkill(skill)) {
                jumlah -= 1;
            }
        }
        else {
            System.out.println("Gagal mempelajari skill.\nEngimon memiliki elemen berbeda!");
        }
    }
}