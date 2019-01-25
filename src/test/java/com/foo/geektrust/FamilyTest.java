package com.foo.geektrust;


import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Set;

import static com.foo.geektrust.Gender.FEMALE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FamilyTest {
    private  static Family shanFamily;

    @BeforeClass
    public static void setUp(){
        shanFamily= new Family(FamilyTest::populate);
    }

    @Test
    public void shouldHaveNoFamilyMembersWhenABlankFamilyIsGiven() {
        final Family blankFamily = new Family();
        assertEquals(blankFamily.getAllFamilyMemberNames().size(), 0);
    }

    @Test
    public void member_Ish_should_have_two_brothers(){
        final Family.FamilyMember Ish = shanFamily.get("Ish");
        final Family.FamilyMember Chit = shanFamily.get("Chit");
        final Family.FamilyMember Vich = shanFamily.get("Vich");

        final Set<Family.FamilyMember> set = Relation.BROTHERS.to(Ish);

        assertEquals(set.size(),2);

        assertTrue(set.contains(Chit));
        assertTrue(set.contains(Vich));

    }

    @Test
    public void shouldReturnAnEmptySetWhenTheRelationIsUnreal(){
        //for instance Ish does not have spouse
        final Family.FamilyMember Ish = shanFamily.get("Ish");

        final Set<Family.FamilyMember> set = Relation.SPOUSE.to(Ish);

        assertTrue(set.isEmpty());
    }

    @Test(expected = MemberNotFoundInFamilyException.class)
    public void shouldRaiseAnExceptionWhenAFamilyMemberNotFound(){
        final Family.FamilyMember Ish = shanFamily.get("Luke");
    }

    //although in this task we just test the brother relationship, I'd like to create the same structure
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