package com.example.subproject_04_front_end.service;


import com.example.subproject_04_front_end.entity.GardenAndTreesVo;
import com.example.subproject_04_front_end.entity.Tree;

import java.util.List;

public interface TreeService {
    List<Tree> findAll();
    Tree findById(String id);
    Tree save(Tree tree);
    void deleteById(String id);
    Tree insert(Tree tree);
    GardenAndTreesVo getTreeByGardenid(String id);
}
