package com.softserve.edu.dto;

public class SprintScore {
    private final String sprintName;
    private final int score;

    public SprintScore(String sprintName, int score) {
        this.sprintName = sprintName;
        this.score = score;
    }

    public String getSprintName() {
        return sprintName;
    }

    public int getScore() {
        return score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SprintScore)) return false;

        SprintScore that = (SprintScore) o;

        if (score != that.score) return false;
        return sprintName != null ? sprintName.equals(that.sprintName) : that.sprintName == null;
    }

    @Override
    public int hashCode() {
        int result = sprintName != null ? sprintName.hashCode() : 0;
        result = 31 * result + score;
        return result;
    }
}
