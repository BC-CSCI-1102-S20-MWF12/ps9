# Problem Set 9: Six Degrees of Separation

### Due Thursday, April 30 @ 11.59pm anywhere in the world

---

## Overview

It has been a long and very weird road, but you have arrived at the final problem set for CS2! In this problem set, you will be exploring a undirected graph built using a subset of the available data from IMDB, the Internet Movie Database. The data is stored in a text file I have provided called `imdb.txt`. It was downloaded from here: https://www.kaggle.com/PromptCloudHQ/imdb-data and modified to contain only the relevant information for this problem set.

In this graph of actors from the 999 most popular movies from 2006 to 2016, each vertex is an actor, and each edge is movie that unites that actor with another actor. Below is a visualization of a subgraph of this graph:

![picture](imdb.png)

The graph is implemented as an adjacency list. A `HashMap` object called `people` maps a `String` key (an actor's name) to a value that is an `ArrayList` of `PersonMovie` objects. The `ArrayList` is the adjacency list for that actor. Each `PersonMovie` object in the `ArrayList` stores the name of an actor and a movie that actor was in with the key actor. Thus, for every actor, we know every other actor they starred with and the name of the movie (or movies) that brough them together. This picture might make things a bit clearer. 

![diagram](diagram.png)


Note that the same actor can appear twice in a key's adjacency list if that actor appeared in multiple movies with the key actor. For instance, the adjacency list for Christian Bale would include a `PersonMovie` object for when he starred with Amy Adams in "American Hustle" and a `PersonMovie` object for when he starred with Amy Adams in "The Fighter".

Detailed comments about the implementation can be found in the `SixDegrees.java`.

---
## Tasks

Your tasks are set out below. I have written a lot of the code for you, and it is well commented. **Look in the `SixDegree.java` file as you read these instructions!**


### Task 1: Max and min degree
When we talk about graphs, we often like to talk about the number of edges coming out of a vertex. This is called the **degree** of the vertex. Write two methods with the following specifications:

```java
// print out the actor(s) with the maximum degree
public void maxDegree()

// print out the actor(s) with the minimum degree
public void minDegree()
```

This is very easy to calculate: just go through each key in the `people` instance variable and get the length of the associated `ArrayList` value that stores the adjacency list. Keep track of the actor (or actors) with the longest (max) or shortest (min) adjacency list.

### Task 2: Find the most "popular" actors with random walks
In class we learned that taking a random walk on a graph can tell us about the relative "popularity" of a particular vertex. If you randomly follow edges in the graph of actors , you'll notice that you will visit some actors more often than others, no matter where you start off in the graph. We can say that those actors are more popular -- they are the Kevin Bacons of our graph. 

I have written code that takes a random walk starting at a given vertex (actor) and ending after a certain number of steps. The method returns an `ArrayList` of all of the actors visited on that random walk. 

Write a method with the following specification:

```java
// print out the 5 most popular actors based on number of visits in a random walk
public void mostPopular()
```

In the method, create a `HashMap` variable that will map from actors (a `String`) to a count of how many times they were visited on a random walk (an `Integer`). Then take 10000 random walks using the method I have provided. For each walk, randomly select a key from the `people` instance variable, and start at that actor. (I have provided code for selecting a random key from a `HashMap`.) Then take a random walk with 10000 steps starting from that actor, using the provided method. After each random walk, add to your current tally for each actor in your `HashMap` variable. After 10000 walks, print out the top 5 most visited actors.

### Task 3:  `findShortestPath()` with breadth-first search
You will write code for breadth-first search to try to connect any two actors in the graph on a shortest path. I have provided skeleton code and very detailed comments in the `SixDegrees.java` file. I've also demonstrated how to do breadth-first search in class and in example code. Have fun!

### Task 4: `isGraphConnected()` with depth-first search
You will write code for a depth-first search to determine whether the graph is connected. Starting from one actor, you will visit every actor connected to that actor. If any actor in the full list of people is not found, you know that the graph is not connected. (Hint: start from an actor with a low degree to make it go faster.)

### Task 5: `main()` method
In the main method, I've provided some calls to the existing methods and some commented-out calls to the methods you'll be writing in tasks 1, 2, 3, and 4. Uncomment these commented-out calls to verify that your code works. I encourage you to include many different and interesting calls to the methods in your `main()` method, but I ask that you comment out everything except the provided calls when you push your code in order to make grading easier.

--- 

## Important notes on grading

1. You should submit only the files that are already in the repo. Do not move files out of their current directories. I will take off a point for every file that gets moved to a different directory.

2. Your code must compile. If you can't get something to compile, comment it out and explain what you were doing so we can try to give you partial credit.

3. Comment your code and indent propertly. Style will be worth 1 point.

4. Don't forget to comment out anything in the `main()` method that you wrote and uncomment what I have provided.

