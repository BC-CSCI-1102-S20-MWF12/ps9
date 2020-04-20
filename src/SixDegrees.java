import java.io.*;
import java.util.*;

public class SixDegrees {

  // ------------------------------
  // INSTANCE VARIABLE: people HashMap
  // ------------------------------
  // This HashMap stores the graph itself.
  // Each key is a String corresponding to an actor's name.
  // Each value is an ArrayList of PersonMovie objects.
  // This ArrayList is how we store the adjacency list for the current key.
  // Each PersonMovie object contains the name of an actor and a
  // movie that actor was in with the actor in the key.
  HashMap<String, ArrayList<PersonMovie>> people;


  // ----------------------------------------
  // INNER CLASS FOR ADJACENCY LIST ELEMENTS
  // ----------------------------------------
  // The PersonMovie class stores an actor
  // and one movie that actor was in.
  public class PersonMovie {
    String person;
    String movie;

    public PersonMovie(String p, String m) {
      this.person = p;
      this.movie = m;
    }
  }

  // ------------------------------
  // CONSTRUCTOR
  // ------------------------------
  // Constructor - initializes the HashMap for storing the graph.
  public SixDegrees() {
    this.people = new HashMap<String, ArrayList<PersonMovie>>();
  }

  // ------------------------------
  // METHOD FOR READING IN IMDB
  // ------------------------------
  // This method reads in the IMDB file and creates the graph.
  // The IMDB file has the 999 most popular movies from 2006-2016.
  public void populateGraph(String s) throws IOException {

    // read in the file line by line
    BufferedReader br = new BufferedReader(new FileReader(s));
    String line;
    while ((line = br.readLine()) != null) {

      // split the line on tab
      String[] parts = line.split("\\t+");

      // the first element is the movie
      String movie = parts[0];

      // the second element is a comma-sepaated list of actors
      String[] actors = parts[1].split(",");

      // add an edge between each actor and every other actor
      for (int i = 0; i < actors.length-1; i++) {
        for (int j=i+1; j < actors.length; j++) {
          addEdge(actors[i], actors[j], movie);
        }
      }
    }
  }


  // -------------------------------------
  // METHODS FOR CREATING NODES AND EDGES
  // -------------------------------------
  // This method just creates a "node", i.e., an entry in the
  // HashMap with an actor as the key and an empty adjacency list.
  public void createNode(String a) {
    ArrayList<PersonMovie> ll = new ArrayList<PersonMovie>();
    people.put(a, ll);
  }

  // This method adds an edge to actor a's list to actor b in the selected movie.
  // And it adds an edge to actor b's list to actor a in the selected movie.
  public void addEdge(String a, String b, String movie) {

    // if neither actor is in the graph yet, create a vertex for them
    if (!people.containsKey(a)) {
      createNode(a);
    }
    if (!people.containsKey(b)) {
      createNode(b);
    }

    // Get the current adjacency list for actor a, and add a new
    // PersonMovie object to it for person b and the selected movie
    ArrayList<PersonMovie> ll = people.get(a);
    PersonMovie pm = new PersonMovie(b, movie);
    ll.add(pm);
    people.put(a, ll);

    // get the current adjacency list for actor b, and add a new
    // PersonMovie object to it for person a and the selected movie
    ArrayList<PersonMovie> ll2 = people.get(b);
    pm = new PersonMovie(a, movie);
    ll2.add(pm);
    people.put(b, ll2);

  }


  // -------------------------------------
  // RANDOM WALK METHOD
  // -------------------------------------
  // This method performs a random walk of length=steps,
  // starting at actor a, and returns the final actor.
  public ArrayList<String> randomWalk(String a, int steps) {
    ArrayList<String> visitedActors = new ArrayList<String>();
    // while the number of steps is greater than 0
    while (steps > 0) {
      steps--;

      // Get a random person from the adjacency list.
      ArrayList<PersonMovie> al = people.get(a);
      int rando = (int)(Math.random() * al.size());
      PersonMovie pm = al.get(rando);

      // If you like you can print out the relationship like this:
      //System.out.println(a + " was in " + pm.movie + " with " + pm.person + " (" + rando + "/" + al.size() + ")");

      // Add the actor to the list of visited actors.
      visitedActors.add(pm.person);

      // Move on to that person, and keep walking.
      a = pm.person;
    }
    return visitedActors;
  }

  // --------------------------------
  //     YOUR METHODS GO HERE!!!
  // --------------------------------

  // --------------------
  // TASK 1: min and max
  // --------------------

  /// print out the actor(s) with the maximum degree
  // signature: public void maxDegree()
  // instructions are in the problem set README
  // 10-25 lines of code, not including comments


  // print out the actor(s) with the minimum degree
  // signature: public void minDegree()
  // Instructions are in the problem set.
  // 10-25 lines of code, not including comments


  // --------------------
  // TASK 2: most popular
  // --------------------
  // print out the 5 most popular actors based on number of visits in a random walk
  // signature: public void mostPopular()
  // Instructions are in the problem set.
  // 30-50 lines of code, not including comments
  // Note: this code will pick a random actor from the people HashMap and save it to key.
  //       ArrayList<String> allActors = new ArrayList<String>(people.keySet());
  //       String key = allActors.get(new Random().nextInt(allActors.size()));



  // ------------------------------------
  // TASK 3: is graph connected with DFS
  // ------------------------------------
  // Determine whether the graph is connected by doing a DFS from some actor.
  // The algorithm is set out for you below.
  // 15-25 lines of code, bot including comments.
  public boolean isGraphConnectedDFS(String a) {


    // In DFS, you use a stack. Conveniently, in Java, there is a Stack
    // class that works just as you would expect!
    Stack<String> actorstack = new Stack<String>();

    // You also use nee to keep track of every actor you end up visiting.
    ArrayList<String> visited = new ArrayList<String>();

    // Start by adding the starting actor, a, to the stack.

    // While the stack is not empty

      // Pop off the actor at the top of the stack.

      // Get the adjacency list for that actor.

      // For each PersonMovie in the adjacency list...

          // If that actor has not yet been visited.
            // Add that actor to the stack.
            // Add that actor to the visited list.

    // When the stack is finally empty, you've visited every actor
    // that is connected to the input actor, a.

    // For every actor in the people instance variable.
      // Check to see if that actor was visited in your DFS.
      // If you encounter some actor that was not visited,
      // print out that there is no path between these two actors,
      // and therefore the graph was not connected.
      // return false

    // If you get to this point, the graph must have been connected so return true.
    return true;
  }


  // -------------------------------
  // TASK 4: shortest path with BFS
  // -------------------------------
  // Find the shortest path between two actors using breadth-first search.
  // You need to print out the full path from actor a to actor b.
  // You also need to keep track of the length of the path.
  // The algorithm is set out for you below.
  // 15-30 lines of code, not including comments
  public void findShortestPath(String a, String b) {

    // For each actor you encounter, keep track of how you got there
    // (i.e., from which PersonMovie, i.e., which other actor and what movie).
    // You also use this to keep track of which actors you have visited.
    // Use a HashMap<String, PersonMovie> to keep track.
    HashMap<String,PersonMovie> camefrom = new HashMap<String,PersonMovie>();

    // In BFS, you use a queue. I *know* this is confusing, but the appropriate
    // implementation of the Queue interface in Java is called LinkedList.
    // Use LinkedList<String> to do your search.
    LinkedList<String> queue = new LinkedList<String>();


    // Start by adding the starting actor, a, to the queue (i.e., the LinkedList).

    // While the queue is not empty

      // poll() off the actor at the front of the queue
      // (The way to get the front of a LinkedList is the method called poll)

      // Get the adjacency list for that actor from the instance variable "people"

      // For each PersonMovie in that adjacency list...

        // Check to see if it's the actor, b, you are looking for.
          // If so, you are ready to print out the path that took you here.
          // e.g., print out "Person X was in Movie Y with Person Z".
          // Use the camefrom variable to help you do this.
          // Use a while loop to back track through camefrom.
          // After printing, return because you are done with your search.

        // Otherwise...
            // Check to see if that actor has already been visited (i.e., does
            // that actor appear as a key in camefrom)

            // If not,
              // add that actor to the queue.

              // And add that actor to camefrom with the current actor
              // and the movie that they were in together as the value.

    // If you end up with an empty queue, there was no path found between a and b.
    System.out.println("No path found beteen " + a + " and " + b + "!");
  }



  // --------------------------------
  // MAIN METHOD
  // --------------------------------
  public static void main (String[] args) throws IOException {

    // This is just to remind you how to run the program because
    // you will often forget it needs imdb.txt as an arugment.
    if (args.length == 0) {
      System.out.println("\nHey CS2 students! Run the program like this:");
      System.out.println("\t java SixDegrees imdb.txt\n");
      throw new IllegalArgumentException();
    }

    // Create the object.
    SixDegrees sd = new SixDegrees();

    // Populate the graph.
    sd.populateGraph(args[0]);

    // Take a random walk of length=25 starting at Kevin Bacon.
    // Which actors do you visit?
    ArrayList<String> someactors = sd.randomWalk("Kevin Bacon", 25);
    System.out.println("\nHere are some people who are connected to Kevin Bacon:\n" + someactors + "\n");


    // -------------------------------------------
    // UNCOMMENT THESE TO TEST YOUR IMPLEMENTATION
    // -------------------------------------------
    // System.out.println("Max Degree");
    // sd.maxDegree();
    // System.out.println("\nMin Degree");
    // sd.minDegree();
    // System.out.println("\nMost Popular:");
    // sd.mostPopular();
    // System.out.println();
    // sd.findShortestPath("Mark Wahlberg", "Lewis MacDougall");
    // System.out.println();
    // sd.findShortestPath("Storm Acheche Sahlstrom", "Alon Aboutboul");
    // System.out.println();
    // sd.isGraphConnected("Matt Damon");

  }

}
