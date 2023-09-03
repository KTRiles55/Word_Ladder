// Kenneth Riles

import java.io.*;
import java.util.*;
public class TestWordLadder {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		buildWordMap();
		WordLadder hp = new WordLadder();
		
		hp.loadWordMap("wordmap.txt");
		System.out.println(hp);  
		
		File readFile = new File("infile.txt");
		Scanner in = new Scanner(readFile);
		
	   // tests each pair of words from file to find possible ladder
		while(in.hasNext())
		{
			String start = in.next();
			String target = in.next();
			
		if(hp.findLadder(start, target)) 
			System.out.println("Path found");
		else
			System.out.println("No path found"); 
		}
		in.close();
	}
	
	
   /** Read all the words form \“dictionary.txt\” and add them
    to a LinkedList called \“dict\”.
    Create an output file called \“wordmap.txt\” that contains
    each word followed by list of words that differ by one 
    character form the given word. Sample ouput:
    
    lice line lime like live mice vice nice
    deck neck 
    cord card cold core 
    bent 
    band land sand 
  */ 

	private static void buildWordMap() throws IOException
	{
		LinkedList<String> dict = new LinkedList<>();
		File inFile = new File("dictionary.txt");
		File outFile = new File("wordmap.txt");
		// Left as exercise	
		
		try
		{
		Scanner input = new Scanner(inFile);
		while (input.hasNext())
			{
				dict.add(input.next());
			}
		input.close();
		
			outFile.createNewFile();
			Iterator<String> firstIter = dict.iterator();
			LinkedList<String> temp = new LinkedList<>();
			if (outFile.canWrite())
			{
				FileWriter result = new FileWriter(outFile); 
				
			   // Iterate through linked list and then iterate again to find similar words
				while(firstIter.hasNext())
				{
					String nextWord = firstIter.next();
					result.write(nextWord);
					temp.add(nextWord);
					
					Iterator<String> secondIter = dict.iterator();
					while(secondIter.hasNext())
					{
						String comparableWord = secondIter.next();
						if (isAnEdge(nextWord, comparableWord) == true)
						{
						   // prevents duplicate words from being added on the same line
							if (!((temp.contains(nextWord))&&(temp.contains(comparableWord))))
							{
								result.write(" " + comparableWord);
					            temp.add(comparableWord);
							}
						}
					}
					
					temp.clear();
					result.write("\n");	
				}
				
				result.flush();
				result.close();
			}
		}
		
		catch(Exception error)
		{
			System.out.println("Exception occurred. Please fix error: " + error.getMessage());
		}
	}
   
   /** The method checks to see if words w1 & w2 differ by only 
    one character. For example:
   “lice” & “mice” returns true.
   “lice” & “mike” reurns false. 
   */

	public static boolean isAnEdge(String w1, String w2) {
        // Left as exercise   
		int mismatches = 0;
		int i = 0;
		
		if (w1.length() != w2.length())
		{
			return false;
		}
		
		else
		{
			do
			{
				if (w1.charAt(i) != w2.charAt(i))
				{
					mismatches++;
				}
			
				if (mismatches > 1)
				{
				return false;
				}
			
				i++;
			} while (i < w1.length());
			return true;
		}
	}
}
