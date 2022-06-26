package org.wecancodeit.librarydemo.rest.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.wecancodeit.librarydemo.models.Campus;
import org.wecancodeit.librarydemo.repositories.CampusRepository;

import javax.annotation.Resource;
import java.util.Collection;

@RestController
public class CampusRestController {

    @Resource
    private CampusRepository campusRepo;

    @GetMapping("/api/campuses")
    public Collection<Campus> getCampuses() {
        return (Collection<Campus>) campusRepo.findAll();
    }

    @GetMapping("/api/campuses/{campusId}")
    public Campus getCampus(@PathVariable Long campusId){
        return campusRepo.findById(campusId).get();
    }
}