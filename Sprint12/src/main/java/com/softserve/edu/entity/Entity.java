package com.softserve.edu.entity;

public class Entity {
    private static int counter=1;
    private final int id=counter++;
    private final String name;

    public Entity(String name) {
        this.name = name;
    }

    public static Entity of(String name){
        return new Entity(name);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Entity)) return false;

        Entity entity = (Entity) o;

        if (id != entity.id) return false;
        return name != null ? name.equals(entity.name) : entity.name == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
