package com.foo.geektrust;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Stream;

import com.foo.geektrust.Family.FamilyMember;

import static java.util.stream.Collectors.toSet;
import static java.util.stream.Stream.of;

public enum Relation {
    MOTHER(member -> FamilyStructure.mother(of(member))),
    FATHER(member -> FamilyStructure.father(of(member))),
    SPOUSE(member -> FamilyStructure.spouse(of(member))),
    BROTHERS(member -> FamilyStructure.brothers(of(member))),
    SISTER(member -> FamilyStructure.sisters(of(member))),
    SON(member -> FamilyStructure.sons(of(member))),
    DAUGHTER(member -> FamilyStructure.daughters(of(member))),
    CHILDREN(member -> FamilyStructure.children(of(member))),

    GRANDSON(member -> SON.to(FamilyStructure.children(of(member)))),
    GRANDDAUGHTER(member -> DAUGHTER.to(FamilyStructure.children(of(member)))),
    COUSIN(member -> union(
            CHILDREN.to(FamilyStructure.siblings(FamilyStructure.father(of(member)))),
            CHILDREN.to(FamilyStructure.siblings(FamilyStructure.mother(of(member)))))),
    BROTHER_IN_LAW(member -> union(
            BROTHERS.to(FamilyStructure.spouse(of(member))),
            SPOUSE.to(FamilyStructure.sisters(of(member))))),
    SISTER_IN_LAW(member -> union(
            SISTER.to(FamilyStructure.spouse(of(member))),
            SPOUSE.to(FamilyStructure.brothers(of(member))))),
    PATERNAL_UNCLE(member -> union(
            BROTHERS.to(FamilyStructure.father(of(member))),
            BROTHER_IN_LAW.to(FamilyStructure.father(of(member)))));

    private Function<Family.FamilyMember, Stream<Family.FamilyMember>> function;

    Relation(Function<Family.FamilyMember, Stream<Family.FamilyMember>> relationFn) {
        this.function = relationFn;
    }

    public Set<Family.FamilyMember> to(Family.FamilyMember member) {
    	Stream<FamilyMember> apply = this.function.apply(member);
        return apply
                .filter(o -> o != null)
                .collect(toSet());
    }

    private Stream<Family.FamilyMember> to(Stream<Family.FamilyMember> members) {
        return members
                .map(this::to)
                .flatMap(Collection::stream);
    }

    private static Stream<Family.FamilyMember> union(Stream<Family.FamilyMember> ...members) {
        return Arrays.asList(members)
                .stream()
                .reduce(Stream::concat)
                .orElseThrow(RuntimeException::new);

    }
}