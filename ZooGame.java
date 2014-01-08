import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * ZooGame is an interactive game that demonstrates the ability for a program to
 * learn. It is a very basic implementation of Artificial Intelligence using
 * trees as a data structure, and recursion to traverse the trees. Requires
 * ZooGameNode
 * 
 * @author Joshua Michael Waggoner
 * 
 */
public class ZooGame
{

	/**
	 * Main method creates the basic tree and calls the startGame method,
	 * passing in the root from the tree as a parameter.
	 * 
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException
	{
		//
		ZooGameNode y1 = new ZooGameNode("frog", null, null);
		ZooGameNode n1 = new ZooGameNode("moose", null, null);
		ZooGameNode root = new ZooGameNode(
				"Alright, lets play... Does it live in water?", y1, n1);
		System.out
				.println("********************************\nZoo Game by Joshua M. Waggoner\n"
						+ "CS 2050 - Fall 2012\n********************************\n\n"
						+ "Welcome to The Zoo Game! :)");
		startGame(root);
	}// end main

	/**
	 * The method startGame decides which type of game to play, either
	 * interactively or with a file. If the input is an undesireable answer, the
	 * method is recursively called again, with the root as a parameter.
	 * Otherwise, according to the answer, the correct play method is called,
	 * and the game begins.
	 * 
	 * @param root
	 *            - the root passed in.
	 * @throws FileNotFoundException
	 */
	@SuppressWarnings({})
	public static void startGame(ZooGameNode root) throws FileNotFoundException
	{

		// input gets the answer from the method

		playInteractively(root);
		System.exit(1);

	}// end startGame

	/**
	 * The playInteractively method plays an interactive game with the user.
	 * 
	 * @param root
	 *            - the root passed in
	 */
	@SuppressWarnings("resource")
	public static void playInteractively(ZooGameNode root)
	{
		// curr to keep track of the current node during traversal
		ZooGameNode curr = root;
		// scan is a variable for the input
		Scanner scan = new Scanner(System.in);
		// while not a leaf
		while (curr.getNoReference() != null)
		{
			// Print initial question
			System.out.println("" + curr.getData());
			// Get next line
			String line = getYesOrNoAnswer();
			// if answer is yes
			if (line.compareToIgnoreCase("yes") == 0)
			{
				// curr moves down to the left child
				curr = curr.getYesReference();
				// if it's not a leaf
				while (curr.getYesReference() != null)
				{
					// output
					System.out.println(curr.getData());
					// answer gets the next line
					String answer = getYesOrNoAnswer();
					// if yes
					if (answer.compareToIgnoreCase("yes") == 0)
					{
						// curr moves down to it's left child
						curr = curr.getYesReference();
					}// end if
						// otherwise
					else if (answer.compareToIgnoreCase("no") == 0)
					{
						// curr moves down to it's right child
						curr = curr.getNoReference();
					}// end else if
				}// end while not a leaf

				// we have reached a leaf, so we make a quess
				System.out.println("Is it a " + curr.getData() + "?");
				// answer gets the next line
				String answer = getYesOrNoAnswer();
				// if the answer is yes, then congratulations.
				if (answer.compareToIgnoreCase("yes") == 0)
				{
					System.out.println("Congratualtions. I was thinking of a "
							+ curr.getData() + ".");
					System.out.println("Do you want to play again?");
					String playAgain = getYesOrNoAnswer();
					// System.out.println(playAgain);
					if (playAgain.compareToIgnoreCase("yes") == 0)
					{
						curr = root;
					}
					else
					{
						// Quit.
						System.out.println("Goodebye!");
						return;
					}
				}
				// But if the guess was incorrect...
				else if (answer.compareToIgnoreCase("no") == 0)
				{
					// ask what animal is it
					System.out
							.println("Then what kind of animal is it? (just the name please)");
					// animal gets the next line
					String animal = scan.nextLine();
					// create a new node for the new animal
					ZooGameNode newNode = new ZooGameNode(animal, null, null);
					// Ask what distinguishes the animal new from from the
					// current animal
					System.out
							.println("Give me a question that distinguishes a "
									+ animal + " from a " + curr.getData()
									+ ".");
					// question gets the next line
					String question = scan.nextLine();
					// ask for the correct answer for the new animal
					System.out
							.println("What is the correct answer for the question relating to a"
									+ animal + "?");
					// correct_answer gets the next line
					String correct_answer = getYesOrNoAnswer();

					// set nodes. If the corect answer is no...
					if (correct_answer.compareToIgnoreCase("no") == 0)
					{
						// set the current node to reference the new node for
						// the new animal
						curr.setNoReference(newNode);
						// temp to hold the data in the current cell
						String temp_data = curr.getData();
						// set the curr data to the question that distinguishes
						// the two
						curr.setData(question);
						// create the new node for the old animal
						ZooGameNode replacementNode = new ZooGameNode(
								temp_data, null, null);
						// set the current node to reference the old animal as
						// it's left child
						curr.setYesReference(replacementNode);
					}// end if

					// set nodes. If the correct answer for the animal were
					// inserting is yes...
					if (correct_answer.compareToIgnoreCase("yes") == 0)
					{
						// set the yes reference to the new animal node
						curr.setYesReference(newNode);
						// temp to hold the data in the current cell
						String temp_data = curr.getData();
						// set the current node to the question
						curr.setData(question);
						// create a new node to hold the old animal
						ZooGameNode replacementNode = new ZooGameNode(
								temp_data, null, null);
						// set the curr to reference the old animal as it's
						// right child
						curr.setNoReference(replacementNode);
					}// end if

					// do they want to play again?
					System.out.println("Do you want to play again?");
					// playAgain gets the next line
					String playAgain = getYesOrNoAnswer();
					// If they do want to play again
					if (playAgain.compareToIgnoreCase("yes") == 0)
					{
						// reset the curr to the root
						curr = root;
					}// end if
						// otherwise...
					else
					{
						System.out.println("Goodbye!");
						return;
					}// end else
				}// end else if
			}// end if for yes side

			// if no atinitial question
			if (line.compareToIgnoreCase("no") == 0)
			{
				// curr moves down to the left child
				curr = curr.getNoReference();
				// if it's not a leeaf
				while (curr.getNoReference() != null)
				{
					// output
					System.out.println(curr.getData());
					// answer gets next line
					String answer = getYesOrNoAnswer();
					// if yes
					if (answer.compareToIgnoreCase("yes") == 0)
					{
						// curr moves down to it's left child
						curr = curr.getYesReference();
					}// end if
						// if no...
					else if (answer.compareToIgnoreCase("no") == 0)
					{
						// curr moves down to it's right child
						curr = curr.getNoReference();
					}// end else if
				}// end while not a leaf
					// output
				System.out.println("Is it a " + curr.getData());
				// answer gets the next line.
				String answer = getYesOrNoAnswer();
				// if the answer is yes, then congratulations
				if (answer.compareToIgnoreCase("yes") == 0)
				{
					// output
					System.out.println("Congradualtions. I was thinking of a "
							+ curr.getData() + ".");
					System.out.println("Do you want to play again?");
					// playAgain gets the next line
					String playAgain = getYesOrNoAnswer();
					// if yes...
					if (playAgain.compareToIgnoreCase("yes") == 0)
					{
						// reset curr to root
						curr = root;
						// if no...
					}
					else
					{
						// quit.
						System.out.println("Goodbye!");
						return;
					}// end if/else
				}// end if for yes

				// But if the guess was incorrect...
				else if (answer.compareToIgnoreCase("no") == 0)
				{
					// We ask what animal is it
					System.out
							.println("Then what kind of animal is it? (just the name please)");
					// The answer is referenced by "animal"
					String animal = scan.nextLine();
					// We need a new node to store the new animal, so we pass it
					// the animal
					ZooGameNode newNode = new ZooGameNode(animal, null, null);
					// Ask what distinguishes the animal from hand from the
					// current animal
					System.out
							.println("Give me a question that distinguishes a "
									+ animal + " from a " + curr.getData()
									+ ".");
					// question gets the next line
					String question = scan.nextLine();
					// Ask for the correct answer for the new animal and store
					// it in correct answer
					System.out
							.println("What is the correct answer for the question relating to a"
									+ animal + "?");
					// correct_answer gets the next line
					String correct_answer = getYesOrNoAnswer();

					// set nodes. If the corect answer is no...
					if (correct_answer.compareToIgnoreCase("no") == 0)
					{
						// set the current node to reference the new node for
						// the new animal
						curr.setNoReference(newNode);
						// temp to hold the data in the current cell, so we can
						// replace the data, but copy the old
						// data into a new cell for the old animal.
						String temp_data = curr.getData();
						// set the curr data to the question that distinguishes
						// the two
						curr.setData(question);
						// Create the new node for the old animal
						ZooGameNode replacementNode = new ZooGameNode(
								temp_data, null, null);
						// set the current node to reference the old animal as
						// it's left child
						curr.setYesReference(replacementNode);
					}// end if

					// if the correct answer for the animal were inserting is
					// yes...
					if (correct_answer.compareToIgnoreCase("yes") == 0)
					{
						// setThe yes reference to the new animal node
						curr.setYesReference(newNode);
						// temp to hold the data in the current cell
						String temp_data = curr.getData();
						// set the current node to the question
						curr.setData(question);
						// Create a new node to hold the old animal
						ZooGameNode replacementNode = new ZooGameNode(
								temp_data, null, null);
						// set the curr to reference the old animal as it's
						// right child
						curr.setNoReference(replacementNode);
					}// end if

					// Do they want to play again?
					System.out.println("Do you want to play again?");
					// playAgain gets the next line.
					String playAgain = getYesOrNoAnswer();
					// If yes
					if (playAgain.compareToIgnoreCase("yes") == 0)
					{
						// reset the curr to the root
						curr = root;
					}// end if
						// If no...
					else
					{
						// output
						System.out.println("Goodbye!");
						return;
					}
				}// end else if
			}// end if for yes side
		}// end while
	}// end playInteractively()

	/**
	 * The getYesOrNoAnswer() method gets the next line from the scanner,
	 * validates that it is in fact a "yes" or "no", and returns the answer.
	 * 
	 * @return either the answer if it is an acceptable answer, or a recursive
	 *         method call that promts the user again for an answer.
	 */
	@SuppressWarnings("resource")
	private static String getYesOrNoAnswer()
	{
		// create a new scanner
		Scanner scan = new Scanner(System.in);
		// scan gets the next line
		String answer = scan.next();
		// if yes or no
		if (answer.compareToIgnoreCase("yes") == 0
				|| answer.compareToIgnoreCase("no") == 0)
		{
			// return the answer
			return answer;
		}// end if
			// otherwise, output to the user "must be yesy or no".
		else
		{
			System.out.println("You must enter a yes or no.");
			// return the method itself
			return getYesOrNoAnswer();
		}// end else
	}// end getYesOrNoAnswer

	/**
	 * The isAnimalAlreadyInList() is a recursive search method to determine if
	 * animal is in the tree or not.
	 * 
	 * @param root
	 *            - the root passed in.
	 * @param animal
	 *            - the animal passed in.
	 * @return either true if the animal is found, or a recursive call with
	 *         either the left or right child of current as a parameter.
	 */
	public Boolean isAlreadyAnAnimalInList(ZooGameNode root, String animal)
	{
		// if the data in the root equals the string passed in (the animal),
		// return true.
		// this is the base case, and is where we find the animal
		if (root.getData() == animal)
		{
			return true;
		}// end if

		// If the current root has a left child, call the recursive method with
		// the left child as the new parameter
		if (root.getYesReference() != null)
		{
			return isAlreadyAnAnimalInList(root.getYesReference(), animal);
		}// end if

		// For testing purposes only.
		// System.out.println(root.getData());

		// If the current root has a right child, call the recursive method with
		// the right child as the new parameter
		if (root.getNoReference() != null)
		{
			return isAlreadyAnAnimalInList(root.getNoReference(), animal);
		}// end if

		// if we do not find it, return false, allowing it to be inserted in the
		// tree
		return false;
	}// end method

}// end ZooGame
