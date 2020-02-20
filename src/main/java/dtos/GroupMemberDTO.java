/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import entities.GroupMember;

/**
 *
 * @author Christian
 */
public class GroupMemberDTO {
    private Long id;
    private String name;
    private String StudentId;
    private GroupMember.ColorType Color;

    public GroupMemberDTO(Long id, String name, String StudentId, GroupMember.ColorType Color) {
        this.id = id;
        this.name = name;
        this.StudentId = StudentId;
        this.Color = Color;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStudentId() {
        return StudentId;
    }

    public GroupMember.ColorType getColor() {
        return Color;
    }

    @Override
    public String toString() {
        return "GroupMemberDTO{" + "id=" + id + ", name=" + name + ", StudentId=" + StudentId + ", Color=" + Color + '}';
    }
    
}
