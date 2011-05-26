package com.jpa.testclasses;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.jpa.first.Job;
import com.jpa.first.Todo;

public class JobTest {
	private static final String PERSISTENCE_UNIT_NAME = "people";

	public static void main(String[] args) {
		EntityManagerFactory factory;

		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();
		// Read the existing entries and write to console
		Query q = em.createQuery("select t from Job t");
		List<Job> todoList = q.getResultList();
		for (Job todo : todoList) {
			System.out.println(todo.getJobDescr()+"--" + todo.getSalery());
		}
		System.out.println("Size: " + todoList.size());

		// Create new todo
		em.getTransaction().begin();
		Job todo = new Job();
		todo.setJobDescr("This is a test");
		todo.setSalery(200);
		em.persist(todo);
		em.getTransaction().commit();

		em.close();
	}
}
