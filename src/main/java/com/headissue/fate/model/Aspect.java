package com.headissue.fate.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.util.Objects;

@Entity
public class Aspect extends AuditModel{

    @OneToOne
    private Character discoveredBy;
    
    private int freeInvocations;
    
    private boolean isBuff;

    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
            Objects.equals(discoveredBy.getId(), aspect.discoveredBy.getId()) &&
            Objects.equals(description, aspect.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(discoveredBy.getId(), freeInvocations, isBuff, description);
    }

    @Override
    public String toString() {
        return "Aspect{" +
            "discoveredBy=" + discoveredBy.getId() +
            ", freeInvocations=" + freeInvocations +
            ", isBuff=" + isBuff +
            ", description='" + description + '\'' +
            '}';
    }
}
