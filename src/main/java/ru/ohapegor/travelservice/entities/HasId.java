package ru.ohapegor.travelservice.entities;

public interface HasId {
    Long getId();

    void setId(Long id);

    default boolean isNew() {
        return getId() == null;
    }
}
