package com.example.subproject_04_front_end.controller;


import com.example.subproject_04_front_end.entity.Garden;
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

import java.util.ArrayList;
import java.util.List;

@Controller
public class GardenController {
    @Autowired
    GardenService gardenService;

    @Autowired
    TreeService treeService;

    @GetMapping("/garden")
    public String getAllGardens(Model model){
        List<Garden> list = gardenService.findAll();
        for(int i = 0 ;i< list.size();i++){
            if(list.get(i).getName().equals("add")){
                list.remove(i);
            }
        }
        list.add(new Garden("add","",""));
        model.addAttribute("gardens",list);
        return "garden";
    }
    @GetMapping("/form-add-garden")
    public String formAddGarden(Model model){
        Garden garden = new Garden();
        model.addAttribute("garden",garden);
        return "form-add-garden";
    }
    @PostMapping("/add-garden")
    public String addGarden(Model model, @ModelAttribute("garden") @Validated Garden garden, BindingResult bindingResult){
        if(bindingResult.hasErrors()){

            return "form-add-garden";
        }else{
            gardenService.insert(garden);
            return "redirect:/garden";
        }
    }
    @GetMapping("/see-garden/{id}")
    public String seeGarden(Model model, @PathVariable("id") String id){
        model.addAttribute("gardenUpdate",gardenService.findById(id));
        List<Tree> treesWereNotGrown = gardenService.findTreeByGardenId(id);
        model.addAttribute("treesWereNotGrown",treesWereNotGrown);
        return "see-garden";
    }
    @PostMapping("/update-trees-in-garden")
    public String updateTreesInGarden(Model model,@ModelAttribute("gardenUpdate") Garden gardenUpdate,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            System.out.println(gardenUpdate);
            System.out.println("false");
        }else{
        }
        return "redirect:/garden";
    }
    @GetMapping("/form-update-garden/{id}")
    public String formUpdateGarden(Model model,@PathVariable("id") String id){
        model.addAttribute("gardenUpdate",gardenService.findById(id));
        return "form-update-garden";
    }
    @PostMapping("/update-garden")
    public String updateGarden(Model model, @ModelAttribute("gardenUpdate") @Validated Garden gardenUpdate, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            model.addAttribute("gardenUpdate",gardenService.findById(gardenUpdate.getId()));
            List<Tree> treesWereNotGrown = gardenService.findTreeByGardenId(gardenUpdate.getId());
            model.addAttribute("treesWereNotGrown",treesWereNotGrown);
            return "form-update-garden";

        }else{
            List<Tree> treesWereNotGrown = new ArrayList<>();
            model.addAttribute("treesWereNotGrown",treesWereNotGrown);
            model.addAttribute("gardenUpdate",gardenUpdate);
            gardenService.save(gardenUpdate);
            return "redirect:/garden";
        }
    }
    @GetMapping("/delete-garden/{id}")
    public String deleteGarden(@PathVariable("id") String id){
        gardenService.deleteById(id);
        return "redirect:/garden";
    }

}