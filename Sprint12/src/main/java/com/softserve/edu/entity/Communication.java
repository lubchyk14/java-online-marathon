package com.softserve.edu.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


public class Communication {
    private final int idStudent;
    private final int idMentor;

    public Communication(int idStudent, int idMentor) {
        this.idStudent = idStudent;
        this.idMentor = idMentor;
    }

    public int getIdStudent() {
        return idStudent;
    }

    public int getIdMentor() {
        return idMentor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Communication)) return false;

        Communication that = (Communication) o;

        if (idStudent != that.idStudent) return false;
        return idMentor == that.idMentor;
    }

    @Override
    public int hashCode() {
        int result = idStudent;
        result = 31 * result + idMentor;
        return result;
    }
}
