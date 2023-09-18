package resistorCheck;

import java.awt.Color;

public class Resistor extends ResColor {
	/* Color	1*band  2*band  3*band  Multiplier		Tolerance
	 * Black 	0 		0 		0 		1  		ohm 
	 * Brown 	1 		1 		1 		10 		ohm		±1%
	 * Red   	2 		2 		2 		100	 	ohm		±2%
	 * Orange 	3 		3 		3 		1k		ohm
	 * Yellow	4 		4 		4 		10k		ohm
	 * Green	5 		5 		5 		100k	ohm		±0.5%
	 * Blue		6 		6 		6 		1M		ohm		±0.25%
	 * Violet	7 		7 		7 		10M		ohm		±0.1%
	 * Grey		8 		8 		8 						±0.05%
	 * White	9 		9 		9
	 * Gold												±5%
	 * Silver											±10% 				
	 */
	private Color band[];
	private Color tolerance[];
	private Color ppm[];
	
	private static int maxDim=10;
	
	public Resistor() {
		super();
		// 1 band
		band = new Color[maxDim];
		band[0] = black;
		band[1] = brown;
		band[2] = red;
		band[3] = orange;
		band[4] = yellow;
		band[5] = green;
		band[6] = blue;
		band[7] = violet;
		band[8] = grey;
		band[9] = white;
		
		// tolerance
		tolerance = new Color[8];
		tolerance[0] = brown;
		tolerance[1] = red;
		tolerance[2] = green;
		tolerance[3] = blue;
		tolerance[4] = violet;
		tolerance[5] = grey;
		tolerance[6] = gold;
		tolerance[7] = silver;
		
		// ppm
		ppm = new Color[6];
		ppm[0] = brown;
		ppm[1] = red;
		ppm[2] = orange;
		ppm[3] = yellow;
		ppm[4] = blue;
		ppm[5] = violet;
	}
	
	public Resistor(Color[] band) {
		super();
		this.band = band;
	}
	
	

	public Resistor(Resistor r) {
		super();
		this.band = r.band;
	}
	
	public Color[] getTolerance() {
		return tolerance;
	}
	
	public Color changeColor(int i) {
		if (i < maxDim)
			return band[i];
		return null;
	}
	
	public Color changeTol(int i) {
		if (i < 8)
			return tolerance[i];
		return null;
	}
	
	public Color changePpm(int i) {
		if (i < 6)
			return ppm[i];
		return null;
	}
	
	public int getMaxDim() {
		return maxDim;
	}
	// getOhm for 3band and 4band resistors
	public double getOhm(int band1, int band2, int band3) {
		StringBuilder out=new StringBuilder();
		out.append(Integer.toString(band1));
		out.append(Integer.toString(band2));
		double mult;
		switch (band3) {
		case 0:
			mult=1;
			break;
		case 1:
			mult=Math.pow (10,1);
			break;
		case 2:
			mult=Math.pow(10,2);
			break;
		case 3:
			mult=Math.pow(10, 3);
			break;
		case 4:
			mult=Math.pow(10,4);
			break;
		case 5:
			mult=Math.pow(10,5);
			break;
		case 6:
			mult=Math.pow(10, 6);
			break;
		case 7:
			mult=Math.pow(10,7);
			break;
			default: mult=1;
		}
		String outConv=out.toString();
		return Integer.parseInt(outConv)*mult;
	}
	
	// getOhm for 5band resistors
	public double getOhm(int band1, int band2, int band3, int band4) {
		StringBuilder out=new StringBuilder();
		out.append(Integer.toString(band1));
		out.append(Integer.toString(band2));
		out.append(Integer.toString(band3));
		double mult;
		switch (band4) {
		case 0:
			mult=1;
			break;
		case 1:
			mult=Math.pow (10,1);
			break;
		case 2:
			mult=Math.pow(10,2);
			break;
		case 3:
			mult=Math.pow(10, 3);
			break;
		case 4:
			mult=Math.pow(10,4);
			break;
		case 5:
			mult=Math.pow(10,5);
			break;
		case 6:
			mult=Math.pow(10, 6);
			break;
		case 7:
			mult=Math.pow(10,7);
			break;
			default: mult=1;
		}
		String outConv=out.toString();
		return Integer.parseInt(outConv)*mult;
	}
	
	public String getTol(int tol) {
		String str="";
		switch (tol) {
		case 0:
			str="±1%";
			break;
		case 1:
			str="±2%";
			break;
		case 2:
			str="±0.5%";
			break;
		case 3:
			str="±0.25%";
			break;
		case 4:
			str="±0.1%";
			break;
		case 5:
			str="±0.05%";
			break;
		case 6:
			str="±5%";
			break;
		case 7:
			str="±10%";
			break;
		}
		return str;
	}
	public int getPPM(int ppmv) {
		int mult=0;
		switch (ppmv) {
		case 0:
			mult=100;
			break;
		case 1:
			mult=50;
			break;
		case 2:
			mult=15;
			break;
		case 3:
			mult=25;
			break;
		case 4:
			mult=10;
			break;
		case 5:
			mult=5;
			break;
		}
		return mult;
	}
	// csv exportation
	public String csvExp(int band1, int band2, int band3,String name) {
		return name + "," + getOhm(band1, band2, band3);
	}
	public String csvExp(int band1, int band2, int band3, int tolerance,String name) {
		return name + "," + getOhm(band1, band2, band3) + "," + getTol(tolerance);
	}
	
	public String csvExp(int band1, int band2, int band3,int band4, int tolerance,String name) {
		return name + "," + getOhm(band1, band2, band3) + "," + getTol(tolerance);
	}
	public String csvExp(int band1, int band2, int band3, int band4, int tolerance, int ppm,String name) {
		return name + "," + getOhm(band1, band2, band3,band4) + "," + getTol(tolerance) + ", ppm:" + getPPM(ppm);
	}
	
}
