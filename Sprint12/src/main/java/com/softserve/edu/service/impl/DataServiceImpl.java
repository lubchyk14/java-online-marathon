package com.softserve.edu.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.softserve.edu.entity.Communication;
import com.softserve.edu.entity.Entity;
import com.softserve.edu.entity.Solution;
import com.softserve.edu.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


public class DataServiceImpl implements DataService {
    private List<Entity> students=new ArrayList<>();
    private List<Entity> mentors=new ArrayList<>();
    private List<Entity> sprints=new ArrayList<>();
    private List<Communication> communication=new ArrayList<>();
    private List<Solution> solutions =new ArrayList<>();


    public void addStudent(String studentName) {
        students.add(Entity.of(studentName));
    }

    public void addMentor(String mentorName) {
        mentors.add(Entity.of(mentorName));
    }

    public void addSprint(String sprintName) {
        sprints.add(Entity.of(sprintName));
    }

    public int getStudentId(String studentName){
        return getIdByName(studentName,students).orElseThrow(IllegalArgumentException::new);
    }
    public int getMentorId(String mentorName){
        return getIdByName(mentorName,mentors).orElseThrow(IllegalArgumentException::new);
    }

    private Optional<Integer> getIdByName(String name, List<Entity> list){
        return list.stream().filter(e->e.getName().equals(name)).map(e->e.getId()).findFirst();
    }

    public void addCommunication(String studentName, String mentorName) {
        int studentId = getIdByName(studentName,students).orElseThrow(()->new IllegalArgumentException());
        int mentorId = getIdByName(mentorName,mentors).orElseThrow(()->new IllegalArgumentException());
        communication.add(new Communication(studentId,mentorId));
    }

    public void addSolution(String studentName, String sprintName, int score) {
        int studentId = getIdByName(studentName,students).orElseThrow(()-> new IllegalArgumentException());
        int sprintId = getIdByName(sprintName,sprints).orElseThrow(()->new IllegalArgumentException());
        solutions.add(new Solution(studentId,sprintId,score));
    }

    public List<Entity> getStudents() {
        return students;
    }

    public List<Entity> getMentors() {
        return mentors;
    }

    public List<Entity> getSprints() {
        return sprints;
    }

    public List<Communication> getCommunication() {
        return communication;
    }

    public List<Solution> getSolutions() {
        return solutions;
    }

    public void setStudents(List<Entity> students) {
        this.students = students;
    }

    public void setMentors(List<Entity> mentors) {
        this.mentors = mentors;
    }

    public void setSprints(List<Entity> sprints) {
        this.sprints = sprints;
    }

    public void setCommunication(List<Communication> communication) {
        this.communication = communication;
    }

    public void setSolutions(List<Solution> solutions) {
        this.solutions = solutions;
    }
}
