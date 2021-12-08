package com.example.subproject_04_front_end.serviceImplement;


import com.example.subproject_04_front_end.entity.Garden;
import com.example.subproject_04_front_end.entity.Tree;
import com.example.subproject_04_front_end.service.GardenService;
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
public class GardenServiceImplement implements GardenService {
    @Autowired
    private RestTemplate restTemplate;
    private String treeUrl;
    private String gardenUrl;
    public GardenServiceImplement(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.treeUrl = "http://ec2-3-1-202-184.ap-southeast-1.compute.amazonaws.com:8282/api/trees";
        this.gardenUrl = "http://ec2-3-1-202-184.ap-southeast-1.compute.amazonaws.com:8282/api/gardens";
    }

    @Override
    public List<Garden> findAll() {
        ResponseEntity<List<Garden>> responseEntity = restTemplate.exchange(gardenUrl+"/list", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Garden>>() {
                });
        return responseEntity.getBody();
    }

    @Override
    public Garden findById(String id) {
        return restTemplate.getForObject(gardenUrl+"/"+id,Garden.class);
    }

    @Override
    public Garden save(Garden garden) {
        restTemplate.put(gardenUrl+"/edit",garden,Garden.class);
        return garden;
    }

    @Override
    public void deleteById(String id) {
        Map<String,String> map = new HashMap<>();
        map.put("id",id);
        restTemplate.delete(gardenUrl+"/delete/"+"{id}",map);
    }

    @Override
    public Garden insert(Garden garden) {
        return restTemplate.postForObject(gardenUrl+"/create",garden,Garden.class);
    }

    @Override
    public List<Tree> findTreeByGardenId(String id) {
        ResponseEntity<List<Tree>> responseEntity = restTemplate.exchange(treeUrl+"/byGardenId/"+id, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Tree>>() {
                });
        return responseEntity.getBody();
    }
}
