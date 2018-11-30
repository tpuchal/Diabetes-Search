package mainpck;

import java.util.function.Function;
public class BipolarFunction implements Function<Double,Double>{

	@Override
	public Double apply(Double arg) {
		
		Double one = 1.0;
		Double eul = Math.E;
		Double tmp = (-1.0)* arg;
		Double power = Math.pow(eul, tmp);
		
		return one/(one+power);
	}

}
