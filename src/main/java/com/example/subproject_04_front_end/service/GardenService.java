package com.example.subproject_04_front_end.service;



import com.example.subproject_04_front_end.entity.Garden;
import com.example.subproject_04_front_end.entity.Tree;

import java.util.List;


public interface GardenService {
    List<Garden> findAll();
    Garden findById(String id);
    Garden save(Garden garden);
    void deleteById(String id);
    Garden insert(Garden garden);
    List<Tree> findTreeByGardenId(String id);
}
