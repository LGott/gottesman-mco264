package recursion;

import java.io.File;

public class RecurFile {

	public void displayDir(String path) {

		//Open the file using the path name that is passed in from the user
		File dir = new File(path);

		//return an array of all the files in the given directory into a File array so that we can iterate through it 
		File[] fileList = dir.listFiles();

		if ((fileList != null) && (fileList.length > 0)) {

			for (File aFile : fileList) {

				//If the file is a directory, then print out the name and length of it
				if (aFile.isDirectory()) {
					System.out.println("[Directory Name: " + aFile.getName() + ": " + aFile.length() + "]");

					//Then, recursively call the method again, passing the path of the file so that
					//it can print out all the files in the directory
					displayDir(aFile.getAbsolutePath());

				} else { //Otherwise, just print out the name and length of the file 
					System.out.println(aFile.getName() + ": " + aFile.length());
				}
			}
		}
	}

	public static void main(String[] args) {
		RecurFile fileDisplay = new RecurFile(); //Instantiate a new instance 

		fileDisplay.displayDir("C:/Users/Leba Gottesman/Documents"); //Call the displayDir method, passing in the path for my documents
	}
}
