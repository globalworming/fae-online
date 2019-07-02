package com.headissue.fate.model;


import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Character extends AuditModel implements HasAspects {
    
    private String name;

    @OneToOne
    private Aspect highConcept;

    @OneToOne
    private Aspect dilemma;

    @OneToMany
    private Set<MeasurableAspect> approaches;

    // list of delimited strings
    private String stunts;
    
    private int edge;

    private int refresh;

    @OneToOne
    //@JoinColumn(name = "player_id")
    private Player owner;

    private int stress;

    @OneToMany
    private Set<MeasurableAspect> consequences = new HashSet<>();

    @OneToMany
    private Set<Aspect> aspects = new HashSet<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Aspect getHighConcept() {
        return highConcept;
    }

    public void setHighConcept(Aspect highConcept) {
        this.highConcept = highConcept;
    }

    public Aspect getDilemma() {
        return dilemma;
    }

    public void setDilemma(Aspect dilemma) {
        this.dilemma = dilemma;
    }

    public Set<MeasurableAspect> getApproaches() {
        return approaches;
    }

    public void setApproaches(Set<MeasurableAspect> approaches) {
        this.approaches = approaches;
    }

    public String getStunts() {
        return stunts;
    }

    public void setStunts(String stunts) {
        this.stunts = stunts;
    }

    public int getEdge() {
        return edge;
    }

    public void setEdge(int edge) {
        this.edge = edge;
    }

    public int getRefresh() {
        return refresh;
    }

    public void setRefresh(int refresh) {
        this.refresh = refresh;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public int getStress() {
        return stress;
    }

    public void setStress(int stress) {
        this.stress = stress;
    }

    public Set<MeasurableAspect> getConsequences() {
        return consequences;
    }

    public void setConsequences(Set<MeasurableAspect> consequences) {
        this.consequences = consequences;
    }

    @Override
    public Set<Aspect> getAspects() {
        return aspects;
    }

    @Override
    public void setAspects(Set<Aspect> aspects) {
        this.aspects = aspects;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Character character = (Character) o;
        return edge == character.edge &&
            refresh == character.refresh &&
            stress == character.stress &&
            Objects.equals(name, character.name) &&
            Objects.equals(highConcept, character.highConcept) &&
            Objects.equals(dilemma, character.dilemma) &&
            Objects.equals(approaches, character.approaches) &&
            Objects.equals(stunts, character.stunts) &&
            Objects.equals(owner, character.owner) &&
            Objects.equals(consequences, character.consequences);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, highConcept, dilemma, approaches, stunts, edge, refresh, owner, stress, consequences);
    }

    @Override
    public String toString() {
        return "Character{" +
            "name='" + name + '\'' +
            ", highConcept=" + highConcept +
            ", dilemma=" + dilemma +
            ", approaches=" + approaches +
            ", stunts='" + stunts + '\'' +
            ", edge=" + edge +
            ", refresh=" + refresh +
            ", owner=" + owner +
            ", stress=" + stress +
            ", consequences=" + consequences +
            '}';
    }

    public void update(Character character) {
        //this.name = character.name;
    }

}
