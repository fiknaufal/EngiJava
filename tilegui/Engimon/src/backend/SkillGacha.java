package backend;

import java.util.Vector;
import java.util.concurrent.ThreadLocalRandom;

public class SkillGacha {
    private Vector<Skill> skillDB = new Vector<Skill>();

    public SkillGacha(){
        Vector<Element> v1 = new Vector<Element>();
        v1.add(Element.FIRE);
        v1.add(Element.WATER);
        v1.add(Element.GROUND);
        v1.add(Element.ICE);
        v1.add(Element.ELECTRIC);

        Vector<Element> v2 = new Vector<Element>();
        v2.add(Element.FIRE);
        v2.add(Element.GROUND);

        Vector<Element> v3 = new Vector<Element>();
        v3.add(Element.WATER);
        v3.add(Element.ICE);

        Vector<Element> v4 = new Vector<Element>();
        v4.add(Element.WATER);
        v4.add(Element.ELECTRIC);

        Vector<Element> v5 = new Vector<Element>();
        v5.add(Element.GROUND);
        v5.add(Element.ELECTRIC);

        Vector<Element> v6 = new Vector<Element>();
        v6.add(Element.FIRE);
        v6.add(Element.ICE);

        Vector<Element> v7 = new Vector<Element>();
        v7.add(Element.FIRE);
        v7.add(Element.GROUND);
        v7.add(Element.ELECTRIC);

        Vector<Element> v8 = new Vector<Element>();
        v8.add(Element.WATER);
        v8.add(Element.GROUND);
        v8.add(Element.ICE);

        Vector<Element> v9 = new Vector<Element>();
        v9.add(Element.WATER);
        v9.add(Element.GROUND);
        v9.add(Element.ICE);

        Skill s1 = new Skill("Pound", 1, 1, v1);
        Skill s2 = new Skill("Fireball", 2, 1, v2);
        Skill s3 = new Skill("Aqua Tail", 2, 1, v3);
        Skill s4 = new Skill("Thunder Bolt", 2, 1, v4);
        Skill s5 = new Skill("Rock Blast", 2, 1, v5);
        Skill s6 = new Skill("Todoroki Strike", 2, 1, v6);
        Skill s7 = new Skill("Fire Tornado", 3, 1, v7);
        Skill s8 = new Skill("Blizzard", 3, 1, v8);
        Skill s9 = new Skill("Banjir Bandang", 3, 1, v9);

        skillDB.add(s1);
        skillDB.add(s2);
        skillDB.add(s3);
        skillDB.add(s4);
        skillDB.add(s5);
        skillDB.add(s6);
        skillDB.add(s7);
        skillDB.add(s8);
        skillDB.add(s9);
    }

    public Skill getRandomSkill(Element e1, Element e2){
        if (e2 == Element.NONE){
            return skillDB.get(e1.getValue());
        }
        else{
            if (e2 == Element.ELECTRIC){
                return skillDB.get(6);
            }else if (e2 == Element.ICE){
                return skillDB.get(7);
            }else{
                return skillDB.get(8);
            }
        }
    }

    public Vector<Skill> getSkillGen(Element e1, Element e2){
        Vector<Skill> temp = new Vector<Skill>();
        if (e2 == Element.NONE){
            if (e1 == Element.FIRE){
                temp.add(skillDB.get(0));
                temp.add(skillDB.get(1));
                temp.add(skillDB.get(5));
                temp.add(skillDB.get(6));
            }else if (e1 == Element.WATER){
                temp.add(skillDB.get(0));
                temp.add(skillDB.get(2));
                temp.add(skillDB.get(3));
                temp.add(skillDB.get(7));
            }else if (e1 == Element.ICE){
                temp.add(skillDB.get(0));
                temp.add(skillDB.get(2));
                temp.add(skillDB.get(5));
                temp.add(skillDB.get(7));
            }else if (e1 == Element.GROUND){
                temp.add(skillDB.get(0));
                temp.add(skillDB.get(1));
                temp.add(skillDB.get(4));
                temp.add(skillDB.get(6));
            }else{
                temp.add(skillDB.get(0));
                temp.add(skillDB.get(3));
                temp.add(skillDB.get(4));
                temp.add(skillDB.get(6));
            }
        }else{
            if (e2 == Element.ELECTRIC){
                temp.add(skillDB.get(1));
                temp.add(skillDB.get(3));
                temp.add(skillDB.get(5));
                temp.add(skillDB.get(6));
            }else if (e2 == Element.ICE){
                temp.add(skillDB.get(2));
                temp.add(skillDB.get(5));
                temp.add(skillDB.get(7));
                temp.add(skillDB.get(8));
            }else{
                temp.add(skillDB.get(2));
                temp.add(skillDB.get(3));
                temp.add(skillDB.get(6));
                temp.add(skillDB.get(8));
            }
        }
        temp.setSize(ThreadLocalRandom.current().nextInt(1,4));
        return temp;
    }
}
