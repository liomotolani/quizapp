package com.arissa.quizapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.LinkedList;

@SpringBootApplication
public class QuizappApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuizappApplication.class, args);

		int n = 5;
		int m = 3;
		int[] movies = {4, 5, 5};
		LinkedList<Integer> stackList = new LinkedList<>();

		// Initialize the stackList with numbers from 1 to n
		for (int i = 1; i <= n; i++) {
			stackList.add(i);
		}
		System.out.println(stackList);

		StringBuilder result = new StringBuilder();

		// Simulate watching m movies and moving them to the top

		for (int i = 0; i < m; i++) {
			int movie = movies[i];
			System.out.println(stackList);
			System.out.println(movie);
			int count = stackList.indexOf(movie);
			System.out.println(count);
			result.append(count).append(",");
			stackList.remove(count);
			stackList.addFirst(movie);
		}
		System.out.println(result.toString());


	}

}
