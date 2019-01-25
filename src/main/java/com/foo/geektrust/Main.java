package com.foo.geektrust;

import java.util.Iterator;
import java.util.Set;

import static com.foo.geektrust.Gender.FEMALE;

public class Main {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("You must provide both name and the relation you enquired");
            return;
        }

        Family shanFamily = new Family(Main::populate);
        final String name = args[0];
        final String relation = args[1].toUpperCase();
        final Family.FamilyMember Ish = shanFamily.get(name);

        final Set<Family.FamilyMember> set = Relation.valueOf(relation).to(Ish);

        Iterator<Family.FamilyMember> ite = set.iterator();

        System.out.println("The " + relation + " of " + name + " are as follows:");
        while (ite.hasNext()) {
            System.out.println(ite.next().getName());
        }
    }

    private static void populate(Family family) {
        Family.FamilyMember kingShan = family.addMember("King Shan", Gender.MALE);
        Family.FamilyMember queenAnga = family.addMember("Queen Anga", FEMALE);
        Family.FamilyMember Ish = family.addMember("Ish", Gender.MALE);
        Family.FamilyMember Chit = family.addMember("Chit", Gender.MALE);
        Family.FamilyMember Ambi = family.addMember("Ambi", FEMALE);
        Family.FamilyMember Vich = family.addMember("Vich", Gender.MALE);
        Family.FamilyMember Lika = family.addMember("Lika", FEMALE);
        Family.FamilyMember Satya = family.addMember("Satya", FEMALE);
        Family.FamilyMember Vyan = family.addMember("Vyan", Gender.MALE);
        Family.FamilyMember Drita = family.addMember("Drita", Gender.MALE);
        Family.FamilyMember Jaya = family.addMember("Jaya", FEMALE);
        Family.FamilyMember Vrita = family.addMember("Vrita", Gender.MALE);
        Family.FamilyMember Vila = family.addMember("Vila", Gender.MALE);
        Family.FamilyMember Jinki = family.addMember("Jinki", FEMALE);
        Family.FamilyMember Chika = family.addMember("Chika", FEMALE);
        Family.FamilyMember Kpila = family.addMember("Kpila", Gender.MALE);
        Family.FamilyMember Satvi = family.addMember("Satvi", FEMALE);
        Family.FamilyMember Asva = family.addMember("Asva", Gender.MALE);
        Family.FamilyMember Savya = family.addMember("Savya", Gender.MALE);
        Family.FamilyMember Krpi = family.addMember("Krpi", FEMALE);
        Family.FamilyMember Saayan = family.addMember("Saayan", Gender.MALE);
        Family.FamilyMember Mina = family.addMember("Mina", FEMALE);
        Family.FamilyMember Jata = family.addMember("Jata", Gender.MALE);
        Family.FamilyMember Driya = family.addMember("Driya", FEMALE);
        Family.FamilyMember Mnu = family.addMember("Mnu", Gender.MALE);
        Family.FamilyMember Lavnya = family.addMember("Lavnya", FEMALE);
        Family.FamilyMember Gru = family.addMember("Gru", Gender.MALE);
        Family.FamilyMember Kriya = family.addMember("Kriya", Gender.MALE);
        Family.FamilyMember Misa = family.addMember("Misa", Gender.MALE);

        // Assigning Relations
        kingShan.setSpouse(queenAnga);
        kingShan.setParent(Ish);
        queenAnga.setParent(Ish);
        kingShan.setParent(Chit);
        queenAnga.setParent(Chit);
        kingShan.setParent(Vich);
        queenAnga.setParent(Vich);
        kingShan.setParent(Satya);
        queenAnga.setParent(Satya);

        Chit.setSpouse(Ambi);
        Chit.setParent(Drita);
        Ambi.setParent(Drita);
        Chit.setParent(Vrita);
        Ambi.setParent(Vrita);

        Vich.setSpouse(Lika);
        Vich.setParent(Vila);
        Lika.setParent(Vila);
        Vich.setParent(Chika);
        Lika.setParent(Chika);

        Satya.setSpouse(Vyan);
        Satya.setParent(Satvi);
        Vyan.setParent(Satvi);
        Satya.setParent(Savya);
        Vyan.setParent(Savya);
        Satya.setParent(Saayan);
        Vyan.setParent(Saayan);

        Jaya.setSpouse(Drita);
        Jaya.setParent(Jata);
        Drita.setParent(Jata);
        Jaya.setParent(Driya);
        Drita.setParent(Driya);

        Vila.setSpouse(Jinki);
        Vila.setParent(Lavnya);
        Jinki.setParent(Lavnya);

        Chika.setSpouse(Kpila);
        Satvi.setSpouse(Asva);

        Savya.setSpouse(Krpi);
        Savya.setParent(Kriya);
        Krpi.setParent(Kriya);

        Saayan.setSpouse(Mina);
        Saayan.setParent(Misa);
        Mina.setParent(Misa);

        Driya.setSpouse(Mnu);
        Lavnya.setSpouse(Gru);
    }
}
