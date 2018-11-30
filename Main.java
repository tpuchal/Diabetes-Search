package mainpck;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("diabetes_data.data"));

		// GUI
		MainFrame frame = new MainFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		ArrayList<Double[]> testList = new ArrayList<Double[]>();
		
		for (int i = 0; i < 768; i++) {
			Double[] tab = new Double[10];
			String[] tmp = br.readLine().split(",");
			// Data normalization
			tab[0] = (Double.parseDouble(tmp[0]) - 3.8) / 3.4;
			tab[1] = (Double.parseDouble(tmp[1]) - 120.9) / 32.0;
			tab[2] = (Double.parseDouble(tmp[2]) - 69.1) / 19.4;
			tab[3] = (Double.parseDouble(tmp[3]) - 20.5) / 16.0;
			tab[4] = (Double.parseDouble(tmp[4]) - 79.8) / 115.2;
			tab[5] = (Double.parseDouble(tmp[5]) - 32.0) / 7.9;
			tab[6] = (Double.parseDouble(tmp[6]) - 0.5) / 0.3;
			tab[7] = (Double.parseDouble(tmp[7]) - 33.2) / 11.8;
			tab[8] = Double.parseDouble(tmp[8]);
			tab[9] = 0.0;

			testList.add(tab);
		}
		br.close();

		NeuralNetwork neuralNetwork = new NeuralNetwork(0.2);

		// learning
		// recordList.clone();
		// we shuffle the records and use 75% of our dataset for network's
		// learning purposes
		Collections.shuffle(testList);
		for (int j = 0; j < 400; j++) {
			for (int i = 0; i < 576; i++) {
				neuralNetwork.learn(testList.get(i));
			}
		}

		// after learning we delete records that are used for learning
		for (int i = 0; i < 576; i++) {
			testList.remove(0);
		}

		for (Double[] d : testList) {
			MainFrame.getModel().addRow(d);
		}
		
		// Testing
		int correctClassifications = 0;
		for (int i = 191; i >= 0; i--) {
			System.out.println(neuralNetwork.getOutput(testList.get(i)) + " : " + testList.get(i)[8]);
			if (neuralNetwork.getOutput(testList.get(i)).equals(testList.get(i)[8])) {
				MainFrame.getModel().setValueAt(1.0, i, 9);
				correctClassifications++;
			}
		}

		MainFrame.getLabel().setText("Number of correct classifications(out of 192) : " + correctClassifications);
		System.out.println("Number of correct classifications(out of 192) : " + correctClassifications);

	}
}
