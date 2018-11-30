package mainpck;

import java.util.ArrayList;

public class NeuralNetwork {
	ArrayList<Neuron> Inputs;
	ArrayList<Neuron> HiddenLayer;
	Neuron Output;

	public NeuralNetwork(Double learningRate) {
		this.Inputs = new ArrayList<Neuron>();
		this.HiddenLayer = new ArrayList<Neuron>();
		for (int i = 0; i < 8; i++) {
			this.Inputs.add(new Neuron(8, learningRate, true));

			if (i % 2 == 0) {
				this.HiddenLayer.add(new Neuron(8, learningRate, false));
			}

		}
		Output = new Neuron(4, learningRate, false);
	}

	public Double getOutput(Double Inputs[]) {
		
		for (Neuron n : this.Inputs) {
			for (int i = 0; i < 8; i++) {
				n.Inputs[i] = Inputs[i];
			}
		}
		for (Neuron n : this.HiddenLayer) {
			for (int i = 0; i < 8; i++) {
				n.Inputs[i] = n.Func.apply(this.Inputs.get(i).NETCalc(Inputs, this.Inputs.get(i).Weights));
			}
		}
		for (int i = 0; i < 4; i++) {
			Output.Inputs[i] = Output.Func.apply(this.HiddenLayer.get(i).NETCalc(this.HiddenLayer.get(i).Inputs,
					this.HiddenLayer.get(i).Weights));
		}
		
		if(Output.Func.apply(Output.NETCalc(Output.Inputs, Output.Weights))<0.5){
			return 0.0;
		} 
		if(Output.Func.apply(Output.NETCalc(Output.Inputs, Output.Weights))>=0.5){
			return 1.0;
		}
//return Output.getOutput();
		return -1.0;
	}

	public void learn(Double[] tab) {
		// counting y
		Double[] out = new Double[8];
		int i = 0;
		for (Neuron n : Inputs) {
			out[i] = n.Func.apply((n.NETCalc(tab, n.Weights)));
			i++;
		}
		i = 0;
		Double[] out2 = new Double[HiddenLayer.size()];
		for (Neuron n : HiddenLayer) {
			out2[i] = n.Func.apply(n.NETCalc(out, n.Weights));
			i++;
		}
		Double[] out3 = { Output.Func.apply(Output.NETCalc(out2, Output.Weights)) };

		// counting errors
		Double error = (tab[8] - out3[0]) * Output.derivativeFunc(out2);

		Double[] error2 = new Double[HiddenLayer.size()];
		i = 0;
		for (Neuron n : HiddenLayer) {
			error2[i] = (error * Output.Weights[i]) * n.derivativeFunc(out);
			i++;
		}

		Double[] error3 = new Double[Inputs.size()];
		i = 0;
		for (int k = 0 ; k < Inputs.size() ; k++) {
			error3[k] = 0.0;
			for (int j = 0; j < HiddenLayer.size(); j++) {
				error3[k] += (error2[j] * HiddenLayer.get(j).Weights[k]) * Inputs.get(k).derivativeFunc(tab);
			}
		}

		// modifying weights

		for (int k = 0 ; k < Inputs.size() ; k++) {
			for (int j = 0; j < tab.length-1; j++) {
				Inputs.get(k).Weights[j] += Inputs.get(k).learningRate * error3[k] * tab[j];
			}
		}

		for (int k = 0 ; k < HiddenLayer.size() ; k++) {
			for (int j = 0; j < Inputs.size(); j++) {
				HiddenLayer.get(k).Weights[j] += HiddenLayer.get(k).learningRate * error2[k] * out[j];
			}
		}

		for (int j = 0; j < HiddenLayer.size(); j++) {
			Output.Weights[j] += Output.learningRate * error * out2[j];
		}
	}

}
