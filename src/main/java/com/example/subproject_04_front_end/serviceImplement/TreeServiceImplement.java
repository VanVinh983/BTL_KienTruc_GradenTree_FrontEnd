package com.example.subproject_04_front_end.serviceImplement;


import com.example.subproject_04_front_end.entity.GardenAndTreesVo;
import com.example.subproject_04_front_end.entity.Tree;
import com.example.subproject_04_front_end.service.TreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TreeServiceImplement implements TreeService {
    @Autowired
    private RestTemplate restTemplate;
    private  String treeUrl;
    private  String gardenUrl;
    public TreeServiceImplement(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.treeUrl = "http://ec2-3-1-202-184.ap-southeast-1.compute.amazonaws.com:8282/api/trees";
        this.gardenUrl = "http://ec2-3-1-202-184.ap-southeast-1.compute.amazonaws.com:8282/api/gardens";
    }

    @Override
    public List<Tree> findAll() {
        ResponseEntity<List<Tree>> responseEntity = restTemplate.exchange(treeUrl+"/list", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Tree>>() {
                });
        return responseEntity.getBody();
    }

    @Override
    public Tree findById(String id) {
        return restTemplate.getForObject(treeUrl+"/"+id,Tree.class);
    }

    @Override
    public Tree save(Tree tree) {
        restTemplate.put(treeUrl+"/edit",tree,Tree.class);
        return tree;
    }

    @Override
    public void deleteById(String id) {
        Map<String,String> map = new HashMap<>();
        map.put("id",id);
        restTemplate.delete(treeUrl+"/delete/"+"{id}",map);
    }

    @Override
    public Tree insert(Tree tree) {
        return  restTemplate.postForObject(treeUrl+"/create",tree,Tree.class);
    }

    @Override
    public GardenAndTreesVo getTreeByGardenid(String id) {
        return restTemplate.getForObject(gardenUrl+"/getVo/"+id, GardenAndTreesVo.class);
    }
}
