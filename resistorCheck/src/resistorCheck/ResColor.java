package resistorCheck;

import java.awt.Color;

public class ResColor {
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
	protected static final Color black 	= new Color(0, 	 0,   0);
	protected static final Color brown 	= new Color(102, 51,  0); 
	protected static final Color red   	= new Color(255, 0,	  0);
	protected static final Color orange = new Color(255, 102, 0);
	protected static final Color yellow = new Color(255, 255, 0);
	protected static final Color green  = new Color(0,	 204, 0);
	protected static final Color blue	= new Color(0,	 0,	  255);
	protected static final Color violet = new Color(102, 0,   153);
	protected static final Color grey  	= new Color(153, 153, 153);
	protected static final Color white 	= new Color(255, 255, 255);
	protected static final Color gold	= new Color(255, 204, 51);
	protected static final Color silver = new Color(204, 204, 204);
	
}
