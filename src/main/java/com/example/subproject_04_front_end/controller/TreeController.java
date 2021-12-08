package com.example.subproject_04_front_end.controller;


import com.example.subproject_04_front_end.entity.Garden;
import com.example.subproject_04_front_end.entity.GardenAndTreesVo;
import com.example.subproject_04_front_end.entity.Tree;
import com.example.subproject_04_front_end.service.GardenService;
import com.example.subproject_04_front_end.service.TreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class TreeController{
    @Autowired
    private GardenService gardenService;

    @Autowired
    private TreeService treeService;
    @GetMapping("/tree")
    public String getAllTrees(Model model){
        List<Tree> trees = treeService.findAll();
        List<Garden> gardens = gardenService.findAll();
        model.addAttribute("trees", trees);
        model.addAttribute("gardens", gardens);
        return "tree";
    }
    @GetMapping("/form-add-tree")
    public String formAddTree(Model model){
        List<Garden> gardens = gardenService.findAll();
        Tree tree = new Tree();
        model.addAttribute("gardens", gardens);
        model.addAttribute("tree", tree);
        return "form-create-tree";
    }

    @PostMapping("/add-tree")
    public String addTree(Model model, @ModelAttribute("tree") @Validated Tree tree, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            List<Garden> gardens = gardenService.findAll();
            model.addAttribute("gardens", gardens);
            model.addAttribute("tree", tree);
            return "form-create-tree";
        }else {
            treeService.insert(tree);
            return "redirect:/tree";
        }
    }

    @GetMapping("/getByGardenId/{id}")
    public String getByGardenId(Model model, @PathVariable("id") String id){
        System.out.println("id la"+ id);
        GardenAndTreesVo gardenAndTreesVo = treeService.getTreeByGardenid(id);
        model.addAttribute("trees", gardenAndTreesVo.getTrees());
        model.addAttribute("gardens", gardenService.findAll());
        model.addAttribute("garden", gardenAndTreesVo.getGarden());
        return "tree";
    }
    @GetMapping("/deletetree/{id}/{gardenid}")
    public String deleteTree(@PathVariable("id") String id, Model model, @PathVariable("gardenid") String gardenId){
        treeService.deleteById(id);
        if (!gardenId.equals("null")){
            return "redirect:/getByGardenId/"+gardenId;
        }else {
            return "redirect:/tree";
        }
    }
    @GetMapping("/show-form-update-tree/{id}")
    public String formUpdateTree(@PathVariable("id") String id, Model model){
        Tree tree = treeService.findById(id);
        model.addAttribute("tree", tree);
        model.addAttribute("gardens", gardenService.findAll());
        return "form-update-tree";
    };

    @PostMapping("/updatetree")
    public String updateTree(@ModelAttribute("tree") @Validated Tree tree, Model model, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            model.addAttribute("tree", tree);
            model.addAttribute("gardens", gardenService.findAll());
            return "form-update-tree";
        }else {
            Tree tree1 = treeService.save(tree);
            return "redirect:/getByGardenId/" + tree1.getIdGarden();
        }
    }
}
