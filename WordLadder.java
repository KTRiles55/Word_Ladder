// Kenneth Riles

import java.io.*;
import java.util.*;
public class WordLadder {
   private ArrayList<Node> list;
   private LinkedList<Vertex> route = new LinkedList<>();
	
	
   public WordLadder() {
      list=new ArrayList<>();
   }
	
   /** Open filename as input and do the following while there are
       lines in the file.
       1) split each line into tokend (split by blankspace)
       2) create a Node (vertex) using the first token.
       3) add the rest of the tokens to the path of vertex.
       4) add the vertex to the ArrayList (list)
   */
       
   public void loadWordMap(String fileName) throws IOException {
      File infile = new File(fileName);
      // Left as exercise
      Scanner input = new Scanner(infile);
      ArrayList<String> lines = new ArrayList<>();
      while (input.hasNext())
      {
    	  lines.add(input.nextLine());
      }
      
      	Iterator<String> iter = lines.iterator();
      	while(iter.hasNext())
      	{
    	  StringTokenizer tokens = new StringTokenizer(iter.next(), " ");
      	  String firstToken = tokens.nextToken(); 
      	  Vertex v = new Vertex(firstToken);
      	  Node newNode = new Node(v); 
      	  while (tokens.hasMoreTokens())
      	  {
    	  	 String nextToken = tokens.nextToken();
    	  	 Vertex newV = new Vertex(nextToken);
    	  	 newNode.addPath(newV);
      	  }
      	  list.add(newNode);
      	  
      }
      input.close();
   }
	
   /** implement findLadder() method using HPAir algoritmh
       discussed in class.
   */
   public boolean findLadder(String start,String end) {
      // Left as exercise   
	  
	  Vertex startingPlace = new Vertex(start);
	  Node position = new Node(startingPlace);
	  Vertex destination = new Vertex(end);
	  Node finalPosition = new Node(destination);
	  
	  boolean pathFound = false; 
	  
	// If one of the words are not in the dictionary
	  if ((list.indexOf(position) == -1) || (list.indexOf(finalPosition) == -1))
	  {
		  System.out.print("\nThere is no ladder between \"" + start + "\" and \"" + end + "\".\n");
		  return false;
	  }
	  
	  Stack<Vertex> locations = new Stack<>();
	  Stack<Vertex> visitedLocations = new Stack<>();
	  // push starting word to stack  
	  locations.push(startingPlace);
	  visitedLocations.push(startingPlace);  
	  
	  while(!(locations.isEmpty()))
	  { 
		  // pops word from stack after leaving its position in each iteration and adds to route
		 
		 Vertex city = locations.pop();  
		 if (!(city.isVisited()))
		 {
			 route.add(city);
			 city.setVisited(true);
			 
		 }
		 
		 // if path is found, then stack remains empty and function returns true
		 if (startingPlace.equals(destination))
		 {
			 pathFound = true; 
		 }
		 
		 else
		 {
			 // retrieves word at starting word position
			 Iterator<Node> it = list.listIterator(list.indexOf(position));
			 
			 try
			 {
				Node n = it.next();
		 
			 	ArrayList<Vertex> temp = new ArrayList<>();
			 	for (int i = 0; i < n.getPath().size(); ++i)
			 	{
			 		Vertex pathW = n.getPath().get(i);
			 		
			 	   // adds only unvisited words
			 		if (!(visitedLocations.contains(pathW)))
			 		{
			 			temp.add(pathW);	
			 		}
			 	}
			 	
			 	
			 	// iterates through path to find a different word path
			 	Iterator<Vertex> it2 = temp.iterator();
			 	if (it2.hasNext())
			 	{
			 		Vertex pathLoc = it2.next();
			 		if (!(pathLoc.isVisited()))
			 		{
			 		  // add word to stack
			 			locations.push(pathLoc);
			 			visitedLocations.push(pathLoc); 
						
			 		  // set new word from path as a node for a different word path to traverse
			 			position.setCity(pathLoc); 
			 		}
			 	}
			 	
			 	else
				{
			 	   // if all words in path are visited, backtrack to previous word on route
					route.remove(startingPlace);
					position.setCity(route.getLast());
					locations.push(route.getLast());
				}
				
				startingPlace = position.getCity(); 
			 }
	
			 
			 catch(NoSuchElementException err)
			 {
				 System.out.print("\nThe path has reached a dead-end!");
			 }
		 }
	  }
	  	 
	    // reset all visited cities back to unvisited and empty other stack
	  	 while(!(visitedLocations.isEmpty()))
	  	 {
	  		 visitedLocations.pop().setVisited(false);
	  	 }
	  	 
	     System.out.print("\nstart word: \"" + start + "\"\nend word: \"" + end + "\"\n");
	     if (pathFound)
	     {
	    	 System.out.print(route + "\n");
	    	 route.clear();
	    	 return true;
	     }
	     
	     route.clear();
	     return false;
	  }
	
   public String toString() {
      String result="";
      for(int i=0;i<list.size();++i) {
         result += list.get(i).toString()+"\n";
      }
      return result;
   }

}

class Node{
   private Vertex city;
   private ArrayList<Vertex> path;
   public Node() {
      path = new ArrayList<>();
   }
	
   public Node(Vertex city) {
      this.city = city;
      path = new ArrayList<>();
   }
	
   public void setCity(Vertex city) {
      this.city = city;
   }
	
   public Vertex getCity() {
      return city;
   }
	
   public ArrayList<Vertex> getPath(){ 
      return path;
   }
	
   public void addPath(Vertex v) {
      path.add(v);
   }
	
   public String toString() {
      return city +"-->"+path;
   }
	
   public boolean equals(Object e) {
      Vertex v = ((Node)e).getCity();
      return this.city.equals(v);
   }
}
