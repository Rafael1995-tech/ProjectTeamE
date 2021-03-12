/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//This is the badge class depicted in the supplemental video


package edu.jsu.mcis.cs310.tas_sp21;

public class Badge {
    
  String id;

   String description;

    public Badge(String id, String description) {
        this.id = id;
        this.description = description;
    }

    public Badge(String id) {
        this.id = id;
    }

    public Badge() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "#" + id + " (" + description + ")";
    }

    
}
