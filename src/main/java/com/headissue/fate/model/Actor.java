package com.headissue.fate.model;


import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Actor extends AuditModel implements HasAspects {

    // FIXME all to aspects
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
        Actor actor = (Actor) o;
        return edge == actor.edge &&
            refresh == actor.refresh &&
            stress == actor.stress &&
            Objects.equals(name, actor.name) &&
            Objects.equals(highConcept, actor.highConcept) &&
            Objects.equals(dilemma, actor.dilemma) &&
            Objects.equals(approaches, actor.approaches) &&
            Objects.equals(stunts, actor.stunts) &&
            Objects.equals(owner, actor.owner) &&
            Objects.equals(consequences, actor.consequences);
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

    public void update(Actor actor) {
        //this.name = character.name;
    }

}
