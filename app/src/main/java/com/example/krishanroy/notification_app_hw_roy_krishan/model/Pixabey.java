package com.example.krishanroy.notification_app_hw_roy_krishan.model;

import java.util.List;

public class Pixabey {
    private Integer totalHits;
    private List<Hit> hits = null;
    private Integer total;

    public Integer getTotalHits() {
        return totalHits;
    }

    public List<Hit> getHits() {
        return hits;
    }

    public Integer getTotal() {
        return total;
    }
}
