package com.softserve.edu.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.softserve.edu.dto.SprintScore;
import com.softserve.edu.entity.Entity;
import com.softserve.edu.entity.Solution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softserve.edu.dto.AverageScore;
import com.softserve.edu.dto.MentorStudent;
import com.softserve.edu.dto.StudentScore;
import com.softserve.edu.service.DataService;
import com.softserve.edu.service.MarathonService;

@Service
public class MarathonServiceImpl implements MarathonService {

    private DataService dataService;

    @Autowired
    public MarathonServiceImpl(DataService dataService) {
        this.dataService = dataService;
    }

    public List<String> getStudents() {
        return dataService.getStudents().stream().map(Entity::getName).collect(Collectors.toList());
    }

    public List<String> getMentors() {
        return dataService.getMentors().stream().map(Entity::getName).collect(Collectors.toList());
    }

    public StudentScore studentResult(String studentName) {
        int studentId = dataService.getStudentId(studentName);
        Map<Integer,String> map = dataService.getStudents()
                .stream()
                .collect(Collectors.toMap(Entity::getId,Entity::getName));
        Map<Integer,String> mapSprint = dataService.getSprints()
                .stream()
                .collect(Collectors.toMap(Entity::getId,Entity::getName));

        return new StudentScore(studentName,dataService.getSolutions().stream()
                            .filter(e-> map.get(e.getIdStudent()).equals(studentName))
                            .map(s->new SprintScore(mapSprint.get(s.getIdSprint()),s.getScore()))
                            .collect(Collectors.toList()));
    }

    public List<StudentScore> allStudentsResult() {
        Map<Integer,String > studentsMap = dataService.getStudents()
                                            .stream()
                                            .collect(Collectors.toMap(Entity::getId,Entity::getName));
        Map<Integer,String > sprintsMap = dataService.getSprints()
                .stream()
                .collect(Collectors.toMap(Entity::getId,Entity::getName));
        Map<String,List<SprintScore>> studentSprintScores = new HashMap<>();

        dataService.getSolutions().stream()
                .peek(s->{
                    String student = studentsMap.get(s.getIdStudent());
                    String sprint = sprintsMap.get(s.getIdSprint());
                    System.out.println(student+" "+sprint);
                    if(studentSprintScores.containsKey(student)){
                        studentSprintScores.get(student).add(new SprintScore(sprint,s.getScore()));
                    }else{
                        List<SprintScore> l = new ArrayList<>();
                        l.add(new SprintScore(sprint,s.getScore()));
                        studentSprintScores.put(student,l);
                    }
                })
                .collect(Collectors.toList());
        return dataService.getStudents().stream()
                .map(Entity::getName)
                .map(s->new StudentScore(s,studentSprintScores.get(s)))
                .collect(Collectors.toList());

    }

    public AverageScore studentAverage(String studentName) {
        int studentId = dataService.getStudentId(studentName);
        double average = dataService.getSolutions().stream()
                .filter(s->s.getIdStudent()==studentId)
                .mapToInt(s->s.getScore())
                .average()
                .orElseThrow(IllegalArgumentException::new);
        return new AverageScore(studentName,average);
    }

    public List<AverageScore> allStudentsAverage() {
        Map <Integer,String> idStudents = dataService.getStudents()
                .stream()
                .collect(Collectors.toMap(Entity::getId,Entity::getName));
        Map<Integer,List<Solution>> map = dataService.getSolutions().stream()
                .collect(Collectors.groupingBy(Solution::getIdStudent));
        return map.keySet().stream()
                .map(s->
                        {
                            String name = idStudents.get(s);
                            Double average = map.get(s).stream()
                                    .mapToInt(Solution::getScore)
                                    .average()
                                    .orElseThrow(IllegalArgumentException::new);
                            return new AverageScore(name,average);
                        })
                .collect(Collectors.toList());
    }

    public MentorStudent mentorStudents(String mentorName) {
        Map <Integer,String> idStudents = dataService.getStudents()
                .stream()
                .collect(Collectors.toMap(Entity::getId,Entity::getName));
        Integer mentorId = dataService.getMentorId(mentorName);
        return new MentorStudent(mentorName,dataService.getCommunication().stream()
                .filter(c->c.getIdMentor()==mentorId)
                .map(c->idStudents.get(c.getIdStudent()))
                .collect(Collectors.toList()));
    }
}
