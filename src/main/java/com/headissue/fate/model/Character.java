package com.headissue.fate.model;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Character extends HasAspects {
    
    private String name;

    @OneToOne
    //@JoinColumn(name = "high_concept_aspect_id")
    private Aspect highConcept;

    @OneToOne
    //@JoinColumn(name = "dilemma_aspect_id")
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

    @OneToMany
    private Set<MeasurableAspect> stress = new HashSet<>();

    @OneToMany
    private Set<MeasurableAspect> consequences = new HashSet<>();

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

    public Set<MeasurableAspect> getStress() {
        return stress;
    }

    public void setStress(Set<MeasurableAspect> stress) {
        this.stress = stress;
    }

    public Set<MeasurableAspect> getConsequences() {
        return consequences;
    }

    public void setConsequences(Set<MeasurableAspect> consequences) {
        this.consequences = consequences;
    }
}
