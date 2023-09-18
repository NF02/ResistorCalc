package resistorCheck;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class Help {

 JFrame frame = new JFrame("Help");
 
 public Help(){
  frame.setAutoRequestFocus(false);
  frame.setResizable(false);
  float version = (float) 1.02;
  //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  frame.setSize(598,418);
  frame.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
  
  JTextArea txtrMitLicenseCopyright = new JTextArea();
  txtrMitLicenseCopyright.setWrapStyleWord(true);
  txtrMitLicenseCopyright.setEditable(false);
  txtrMitLicenseCopyright.setText("MIT License\n\nCopyright (c) 2023 Nicola Ferru aka NFVblog <ask dot nfvblog at outlook dot it>\n\nPermission is hereby granted, free of charge, to any person obtaining a copy\nof this software and associated documentation files (the \"Software\"), to deal\nin the Software without restriction, including without limitation the rights\nto use, copy, modify, merge, publish, distribute, sublicense, and/or sell\ncopies of the Software, and to permit persons to whom the Software is\nfurnished to do so, subject to the following conditions:\n\nThe above copyright notice and this permission notice shall be included in all\ncopies or substantial portions of the Software.\n\nTHE SOFTWARE IS PROVIDED \"AS IS\", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR\nIMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,\nFITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE\nAUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER\nLIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,\nOUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE\nSOFTWARE.");
  frame.getContentPane().add(txtrMitLicenseCopyright);
  
  JButton btnLicense = new JButton("License");
  btnLicense.addActionListener(new ActionListener() {
  	public void actionPerformed(ActionEvent e) {
  		txtrMitLicenseCopyright.setText("MIT License\n\nCopyright (c) 2023 Nicola Ferru aka NFVblog <ask dot nfvblog at outlook dot it>\n\nPermission is hereby granted, free of charge, to any person obtaining a copy\nof this software and associated documentation files (the \"Software\"), to deal\nin the Software without restriction, including without limitation the rights\nto use, copy, modify, merge, publish, distribute, sublicense, and/or sell\ncopies of the Software, and to permit persons to whom the Software is\nfurnished to do so, subject to the following conditions:\n\nThe above copyright notice and this permission notice shall be included in all\ncopies or substantial portions of the Software.\n\nTHE SOFTWARE IS PROVIDED \"AS IS\", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR\nIMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,\nFITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE\nAUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER\nLIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,\nOUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE\nSOFTWARE.");
  	}
  });
  frame.getContentPane().add(btnLicense);
  
  JButton btnVersion = new JButton("Version");
  btnVersion.addActionListener(new ActionListener() {
  	public void actionPerformed(ActionEvent e) {
  		txtrMitLicenseCopyright.setText("┏━┃┏━┛┏━┛┛┏━┛━┏┛┏━┃┏━┃┏━┛┏━┃┃  ┏━┛\n"
  				+ "┏┏┛┏━┛━━┃┃━━┃ ┃ ┃ ┃┏┏┛┃  ┏━┃┃  ┃\n"
  				+ "┛ ┛━━┛━━┛┛━━┛ ┛ ━━┛┛ ┛━━┛┛ ┛━━┛━━┛\n"
  				+ "Version "+ version + "alpha\n\n ResistorCalc is a calculator based on the color band system that is used to identify the\n"
  						+ "specifications of resistors, it is a very simple Java program that allows the calculation of\n resistors with"
  						+ "3, 4, 5, 6 color bands.");
  	}
  });
  frame.getContentPane().add(btnVersion);
  
  JButton btnInfo = new JButton("Info");
  btnInfo.addActionListener(new ActionListener() {
  	public void actionPerformed(ActionEvent e) {
  		txtrMitLicenseCopyright.setText("ResistorCalc is an educational software conceived and developed by Nicola Ferru to quickly\n"
  				+ "calculate commercial resistors using color band coding. This software allows you\n to calculate"
  				+ "even 3-band resistors which are of an atavistic type. By default the program is\n set"
  				+ "to calculate four-band resistors, but in the options menu it is possible to conveniently\n"
  				+ "select other types of resistors. Remembering that the more bands there are, the more\n"
  				+ "precise the manufacturer has been in representing the specifications.\n"
  				+ "The software is subject to license constraints which can be read both within the LICENSE\n file and"
  				+ "within the help -> version.");
  	}
  });
  frame.getContentPane().add(btnInfo);
  
  JButton btnClose = new JButton("Close");
  btnClose.addActionListener(new ActionListener() {
  	public void actionPerformed(ActionEvent e) {
  		frame.setVisible(false);
  	}
  });
  frame.getContentPane().add(btnClose);
  //frame.setVisible(true);
 }
 
 public void VisibleSet(boolean b) {
	 frame.setVisible(b);
 }
}