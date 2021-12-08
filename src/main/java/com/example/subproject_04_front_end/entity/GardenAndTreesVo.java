package com.example.subproject_04_front_end.entity;

import java.util.List;

public class GardenAndTreesVo {
    private Garden garden;
    private List<Tree> trees;

    public GardenAndTreesVo() {
    }

    public Garden getGarden() {
        return garden;
    }

    public void setGarden(Garden garden) {
        this.garden = garden;
    }

    public List<Tree> getTrees() {
        return trees;
    }

    public void setTrees(List<Tree> trees) {
        this.trees = trees;
    }

    @Override
    public String toString() {
        return "GardenAndTreesVo{" +
                "garden=" + garden +
                ", trees=" + trees +
                '}';
    }
}
