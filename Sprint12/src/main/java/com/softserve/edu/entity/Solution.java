package com.softserve.edu.entity;

public class Solution {
    private final int idStudent;
    private final int idSprint;
    private final int score;

    public Solution(int idStudent, int idSprint, int score) {
        this.idStudent = idStudent;
        this.idSprint = idSprint;
        this.score = score;
    }

    public int getIdStudent() {
        return idStudent;
    }

    public int getIdSprint() {
        return idSprint;
    }

    public int getScore() {
        return score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Solution)) return false;

        Solution solution = (Solution) o;

        if (idStudent != solution.idStudent) return false;
        if (idSprint != solution.idSprint) return false;
        return score == solution.score;
    }

    @Override
    public int hashCode() {
        int result = idStudent;
        result = 31 * result + idSprint;
        result = 31 * result + score;
        return result;
    }

    @Override
    public String toString() {
        return "Solution{" +
                "idStudent=" + idStudent +
                ", idSprint=" + idSprint +
                ", score=" + score +
                '}';
    }
}
