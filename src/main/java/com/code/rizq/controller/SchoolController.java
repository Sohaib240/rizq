package com.code.rizq.controller;

import com.code.rizq.model.School;
import com.code.rizq.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class SchoolController {

    @Autowired
    private SchoolRepository schoolRepository;

    @GetMapping("/tutorials/{id}")
    public ResponseEntity<School> getSchoolById(@PathVariable("id") Integer id) {
        Optional<School> school = schoolRepository.findById(id);
        return school.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/tutorials")
    public ResponseEntity<List<School>> getAllSchools() {
        return new ResponseEntity<>(schoolRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping("/tutorials/add")
    public ResponseEntity<School> addNewSchool(@RequestBody School school) {
        return new ResponseEntity<>(schoolRepository.save(school), HttpStatus.OK);
    }

    @PostMapping("/tutorials/update/{id}")
    public ResponseEntity<School> updateSchoolById(@PathVariable Integer id, @RequestBody School school) {
        Optional<School> dbEntry = schoolRepository.findById(id);
        if (dbEntry.isPresent()) {
            School existingSchool = dbEntry.get();
            existingSchool.setAddress(school.getAddress());
            existingSchool.setPrincipleName(school.getPrincipleName());
            existingSchool.setName(school.getName());
            return new ResponseEntity<>(schoolRepository.save(school), HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/tutorials/{id}")
    public ResponseEntity<HttpStatus> deleteSchool(@PathVariable("id") Integer id) {
        try {
            schoolRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
