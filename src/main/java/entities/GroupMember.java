/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

/**
 *
 * @author Christian
 */
@Entity
@NamedQuery(name = "GroupMember.deleteAllRows", query = "DELETE from GroupMember")
public class GroupMember implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String StudentId;
    private ColorType Color;
   // private String email;
    
    public enum ColorType
            {
            Green,
            Yellow,
            Red
            }

    public GroupMember() {
    }

    public GroupMember(String name, String StudentId, ColorType Color/*, String email*/) {
        this.name = name;
        this.StudentId = StudentId;
        this.Color = Color;
       // this.email = email;
    }
    /**
     * add withuot id and email (email is secret and given by backend)
     * @param name
     * @param StudentId
     * @param Color 
     */
//    public GroupMember(String name, String StudentId, ColorType Color) {
//        this.name = name;
//        this.StudentId = StudentId;
//        this.Color = Color;
//    }
    
    
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStudentId() {
        return StudentId;
    }

    public ColorType getColor() {
        return Color;
    }

//    public String getEmail() {
//        return email;
//    }

    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GroupMember)) {
            return false;
        }
        GroupMember other = (GroupMember) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.GroupMember[ id=" + id + " ]";
    }
    
}
