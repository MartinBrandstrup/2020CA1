/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.GroupMember;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/**
 *
 * @author Christian
 */
public class GroupMemberFacade {

    private static GroupMemberFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private GroupMemberFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static GroupMemberFacade getGroupmemberFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new GroupMemberFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

///////////////////////////methods////////////////////////////////
    /**
     * Used for DB test
     *
     * @return number of GroupMembers
     */
    public long getGroupMemberCount() {
        EntityManager em = emf.createEntityManager();
        try {
            long MembersCount = (long) em.createQuery("SELECT COUNT(c) FROM GroupMember c").getSingleResult();
            return MembersCount;
        } finally {
            em.close();
        }
    }

    public GroupMember addGroupMember(String name, String StudentId, GroupMember.ColorType Color)
    {
       // Return to after CA1 or if time     
//        String Email;
//        switch (StudentId) {
//            case "cph-cf124":
//                Email = "cph-cf124@cphbuisness.dk";
//                break;
//            case "cph-mb604":
//                Email = "cph-mb604@cphbuisness.dk";
//                break;
//            case "cph-ck209":
//                Email = "cph-ck209@cphbuisness.dk";
//                break;
//            default:
//                Email = "NewMember.dk";
//        }
        GroupMember m = new GroupMember(name, StudentId, Color/*, Email*/);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(m);
            em.getTransaction().commit();
            return m;
        } finally {
            em.close();
        }
    }

    public GroupMember findGroupMemberById(long id) {
        EntityManager em = emf.createEntityManager();

        try {
            GroupMember grpMeb = em.find(GroupMember.class, id);
            return grpMeb;
        } finally {
            em.close();
        }
    }

    /**
     * Retrives all members of the group, used for index page
     *
     * @return a list of GroupMembers
     */
    public List<GroupMember> getAllGroupMembers() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<GroupMember> query = em.createQuery("SELECT e From GroupMember e", GroupMember.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}
