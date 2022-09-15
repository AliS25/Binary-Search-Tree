//Ali Sbeih
/**
 * A program that reads all the words in a text file
*/
//compare the running times of the two implementations for the task
// of measuring the size of vocabulary used in different texts.
public class VocabularyTester {
    public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("Error: received " + args.length +
					" arguments, expected 1.\n");
		}
		//a set for common words
		SimpleSSet<String> set=new BinarySearchTree<>();
		WordReader words= new WordReader(args[0]);

//timer and csvwriter for measuring the time of adding the words
		RunTimer rt = new RunTimer();
		CSVWriter csv = new CSVWriter("Binary-Search-Trees.csv");
		csv.addEntry("text size");
		csv.addEntry("time per storing the distinct words operation");
		csv.endLine();
//size variable for counting the total number of words in a text
int size=0;

//check if there is a next word that is not common then add it
		while (true) {
			String nextWord=words.nextWord();
			if(nextWord!=null){
				size++;
				rt.start();
				set.add(nextWord);
				rt.stop();
			}
			else break;
		}
		csv.addEntry(size);
		csv.addEntry(rt.getElapsedNanos()/size);
		csv.endLine();

		rt.reset();
        csv.close();

//print number of uncommon words
		System.out.println("The number of distinct words in the file "+args[0]+" is "+set.size()+" words!");





    }
}
