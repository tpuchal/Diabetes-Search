package mainpck;

import java.util.concurrent.ThreadLocalRandom;

public class Neuron {
	Double Bias = new Double(1.0);
	Double Inputs[];
	Double Weights[];
	int size;
	double learningRate;
	BipolarFunction Func;

	public Neuron(int size, double learningRate, boolean bi) {
		this.size = size;
		this.Inputs = new Double[this.size];
		this.Weights = new Double[this.size + 1];
		if(bi){ this.Bias=1.0; } else {this.Bias=0.0;} ;
		
		this.learningRate = learningRate;
		Func = new BipolarFunction();
		initializeNeuron();

	}

	public void setInputs(Double Inputs[]) {

		for (int i = 0; i < this.Inputs.length; i++) {
			this.Inputs[i] = Inputs[i];
		}
	}

	public Double NETCalc(Double[] Inputs, Double[] Weights) {
		Double NET = 0.0;

		for (int i = 0; i < this.size; i++) {
			NET += Inputs[i] * Weights[i];
		}

		NET += Bias * Weights[this.size];
		return NET;

	}

	public void initializeNeuron() {
		for (int i = 0; i < this.size + 1; i++) {
			Double d = ThreadLocalRandom.current().nextDouble(-1.9, 0.9);
			this.Weights[i] = d;
		}
	}

	public Double derivativeFunc(Double[] Inputs) {
		
		return this.learningRate*this.Func.apply(this.NETCalc(Inputs, Weights))*(1.0-Func.apply(this.NETCalc(Inputs, Weights)));

	}

	public Double getOutput() {
		return Func.apply(NETCalc(this.Inputs, this.Weights));
	}

}
