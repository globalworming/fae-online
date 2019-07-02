package com.headissue.fate.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.util.Objects;

@Entity
public class Aspect extends AuditModel implements HasName {

    public Aspect() {
    }

    public Aspect(String name) {
        this.name = name;
    }

    @OneToOne
    private Character discoveredBy;
    
    private int freeInvocations;
    
    private boolean isBuff;

    private String name;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public Character getDiscoveredBy() {
        return discoveredBy;
    }

    public void setDiscoveredBy(Character discoveredBy) {
        this.discoveredBy = discoveredBy;
    }

    public int getFreeInvocations() {
        return freeInvocations;
    }

    public void setFreeInvocations(int freeInvocations) {
        this.freeInvocations = freeInvocations;
    }

    public boolean isBuff() {
        return isBuff;
    }

    public void setBuff(boolean buff) {
        isBuff = buff;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aspect aspect = (Aspect) o;
        return freeInvocations == aspect.freeInvocations &&
            isBuff == aspect.isBuff &&
            Objects.equals(discoveredBy, aspect.discoveredBy) &&
            Objects.equals(name, aspect.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(discoveredBy != null ? discoveredBy.getId() : null, freeInvocations, isBuff, name);
    }

    @Override
    public String toString() {
        return "Aspect{" +
            "discoveredBy=" + discoveredBy != null ? String.valueOf(discoveredBy.getId()) : null +
            ", freeInvocations=" + freeInvocations +
            ", isBuff=" + isBuff +
            ", name='" + name + '\'' +
            '}';
    }

    // FIXME h7e objectsetter ?
    public void update(Aspect aspect) {
        this.name = aspect.name;
    }
}
