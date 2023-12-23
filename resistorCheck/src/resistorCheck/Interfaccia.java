package resistorCheck;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;

import java.awt.BorderLayout;
import java.awt.Desktop;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.Font;

public class Interfaccia {
	private JFrame frmResistorCheck;
	private JTextField txtC1;
	private JTextField txtC2;
	private JTextField txtC3;
	private JTextField txtC4;
	private JTextField txtC5;
	private Resistor r = new Resistor();
	private int band1 = 0;
	private int band2 = 0;
	private int band3 = 0;
	private int band4 = 0;
	private int tolerance = 0;
	private int scale = 1000;
	private String chScale = "kΩ";
	
	private int ppm = 0;
	private Desktop d;
	private JTextField txtC6;
	private Help help = new Help();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interfaccia window = new Interfaccia();
					window.frmResistorCheck.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Interfaccia() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmResistorCheck = new JFrame();
		frmResistorCheck.setAutoRequestFocus(false);
		frmResistorCheck.setResizable(false);
		frmResistorCheck.setTitle("Resistor Calculator");
		frmResistorCheck.setBounds(100, 100, 1090, 431);
		frmResistorCheck.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		txtC1 = new JTextField();
		txtC2 = new JTextField();
		txtC3 = new JTextField();
		txtC4 = new JTextField();
		txtC5 = new JTextField();
		txtC6 = new JTextField();

		txtC1.setBackground(r.changeColor(band1));
		txtC2.setBackground(r.changeColor(band2));
		txtC3.setBackground(r.changeColor(band3));
		txtC4.setBackground(r.changeColor(band4));
		txtC5.setBackground(r.changeTol(tolerance));
		txtC6.setBackground(r.changePpm(ppm));

		JButton btnch1 = new JButton("change");
		JButton btnChange = new JButton("change");
		JButton btnChange_1 = new JButton("change");
		JButton btnChange_2 = new JButton("change");
		JButton btnChange_3 = new JButton("change");
		JButton btnChange_4 = new JButton("change");

		JLabel lblPpm = new JLabel("ppm");
		JLabel lblBand_2 = new JLabel("3 band");
		JLabel lblMult = new JLabel("Multiplier");
		JLabel lblTolerance = new JLabel("tolerance");

		JCheckBoxMenuItem chckbxmntmBand_2 = new JCheckBoxMenuItem("3 band");
		JCheckBoxMenuItem chckbxmntmBand = new JCheckBoxMenuItem("4 band (default)");
		chckbxmntmBand.setSelected(true);
		JCheckBoxMenuItem chckbxmntmBand_1 = new JCheckBoxMenuItem("5 band");
		JCheckBoxMenuItem chckbxmntmBand_3 = new JCheckBoxMenuItem("6 band");

		// 4 band mode by default
		txtC3.setVisible(false);
		txtC6.setVisible(false);
		btnChange_1.setVisible(false);
		btnChange_4.setVisible(false);
		lblPpm.setVisible(false);
		lblBand_2.setVisible(false);

		JTextPane textPane = new JTextPane();

		// menus
		JMenuBar menuBar = new JMenuBar();
		frmResistorCheck.getContentPane().add(menuBar, BorderLayout.NORTH);

		JMenu mnFile = new JMenu("file");
		menuBar.add(mnFile);

		JMenuItem mntmImport = new JMenuItem("Import");
		mntmImport.setVisible(false);
		mnFile.add(mntmImport);

		JMenuItem mntmExport = new JMenuItem("Export");
		mntmExport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter csvFilter = new FileNameExtensionFilter(".csv", "csv");
				chooser.setFileFilter(csvFilter);
				chooser.setCurrentDirectory(new java.io.File("."));
				chooser.setDialogTitle("Save File");
				chooser.showSaveDialog(textPane);
				chooser.setAcceptAllFileFilterUsed(false);
				// file instance
				File f = chooser.getSelectedFile();
				// string builder
				StringBuilder tmpFile = new StringBuilder();
				String name = JOptionPane.showInputDialog("Enter resistor name:", null);
				if (f.exists() && !f.isDirectory()) {
					try (Scanner myFile = new Scanner(f)) {
						while (myFile.hasNextLine())
							tmpFile.append(myFile.nextLine() + "\n");
						if (chckbxmntmBand.isSelected() == true) {
							tmpFile.append(r.csvExp(band1, band2, band3, tolerance, name));
						} else if (chckbxmntmBand_1.isSelected() == true) {
							tmpFile.append(r.csvExp(band1, band2, band3, band4, tolerance, name));
						} else if (chckbxmntmBand_2.isSelected() == true) {
							tmpFile.append(r.csvExp(band1, band2, band3, null));
						} else if (chckbxmntmBand_3.isSelected() == true) {
							tmpFile.append(r.csvExp(band1, band2, band3, band4, tolerance, ppm, name));
						}

						String str = tmpFile.toString();
						try (FileWriter fw = new FileWriter(f)) {
							fw.write(str);
							fw.close();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					tmpFile.append("Name, Resistor Value, Tolerance, PPM\n");
					if (chckbxmntmBand.isSelected() == true) {
						tmpFile.append(r.csvExp(band1, band2, band3, tolerance, name));
					} else if (chckbxmntmBand_1.isSelected() == true) {
						tmpFile.append(r.csvExp(band1, band2, band3, band4, tolerance, name));
					} else if (chckbxmntmBand_2.isSelected() == true) {
						tmpFile.append(r.csvExp(band1, band2, band3, name));
					} else if (chckbxmntmBand_3.isSelected() == true) {
						tmpFile.append(r.csvExp(band1, band2, band3, band4, tolerance, ppm, name));
					}
					String str = tmpFile.toString();
					try (FileWriter fw = new FileWriter(f)) {
						fw.write(str);
						fw.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		});
		mnFile.add(mntmExport);

		JMenuItem mntmExit = new JMenuItem("exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnFile.add(mntmExit);

		JMenu mnOption = new JMenu("Option");
		menuBar.add(mnOption);

		JMenu mnResistorTypes = new JMenu("Resistor types");
		mnOption.add(mnResistorTypes);

		// 3band resistor (atavistic resistors)
		chckbxmntmBand_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chckbxmntmBand.setSelected(false);
				chckbxmntmBand_1.setSelected(false);
				chckbxmntmBand_3.setSelected(false);
				txtC3.setVisible(false);
				txtC5.setVisible(false);
				txtC6.setVisible(false);
				lblTolerance.setVisible(false);
				lblPpm.setVisible(false);
				lblBand_2.setVisible(false);
				btnChange_1.setVisible(false);
				btnChange_4.setVisible(false);
				btnChange_3.setVisible(false);
			}
		});

		// 4band resistor
		mnResistorTypes.add(chckbxmntmBand_2);
		chckbxmntmBand.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chckbxmntmBand_2.setSelected(false);
				chckbxmntmBand_1.setSelected(false);
				chckbxmntmBand_3.setSelected(false);
				txtC3.setVisible(false);
				txtC6.setVisible(false);
				txtC5.setVisible(true);
				lblBand_2.setVisible(false);
				lblTolerance.setVisible(true);
				lblPpm.setVisible(false);
				btnChange_1.setVisible(false);
				btnChange_4.setVisible(false);
				btnChange_3.setVisible(true);

			}
		});
		mnResistorTypes.add(chckbxmntmBand);

		// 5band resistor
		chckbxmntmBand_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chckbxmntmBand.setSelected(false);
				chckbxmntmBand_2.setSelected(false);
				chckbxmntmBand_3.setSelected(false);
				btnChange_2.setVisible(true);
				btnChange_3.setVisible(true);
				lblTolerance.setVisible(true);
				txtC3.setVisible(true);
				btnChange_1.setVisible(true);
				txtC5.setVisible(true);
				lblBand_2.setVisible(true);
				// disable ppm
				lblPpm.setVisible(false);
				txtC6.setVisible(false);
				btnChange_4.setVisible(false);
			}
		});
		mnResistorTypes.add(chckbxmntmBand_1);

		// 6band resistor
		chckbxmntmBand_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chckbxmntmBand.setSelected(false);
				chckbxmntmBand_2.setSelected(false);
				chckbxmntmBand_1.setSelected(false);
				btnch1.setEnabled(true);
				btnChange.setVisible(true);
				lblTolerance.setVisible(true);
				lblBand_2.setVisible(true);
				btnChange_1.setVisible(true);
				btnChange_2.setVisible(true);
				btnChange_3.setVisible(true);
				txtC3.setVisible(true);
				txtC5.setVisible(true);
				// enable ppm
				lblPpm.setVisible(true);
				txtC6.setVisible(true);
				btnChange_4.setVisible(true);
			}
		});
		mnResistorTypes.add(chckbxmntmBand_3);
		
		JMenu mnUnit = new JMenu("Unit of measure");
		mnOption.add(mnUnit);
		
		/* select scale */
		JCheckBoxMenuItem chckbxOhm = new JCheckBoxMenuItem("Ohm");
		JCheckBoxMenuItem chckbxKOhm = new JCheckBoxMenuItem("KOhm");
		JCheckBoxMenuItem chckbxMOhm = new JCheckBoxMenuItem("MOhm");
		
		mnUnit.add(chckbxOhm);
		chckbxOhm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				chckbxKOhm.setSelected(false);
				chckbxMOhm.setSelected(false);
				scale = 1;
				chScale = "Ω";
			}
		});
		
		chckbxKOhm.setSelected(true);
		mnUnit.add(chckbxKOhm);
		chckbxKOhm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				chckbxOhm.setSelected(false);
				chckbxMOhm.setSelected(false);
				scale = 1000;
				chScale = "kΩ";
			}
		});
		mnUnit.add(chckbxMOhm);
		chckbxMOhm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				chckbxKOhm.setSelected(false);
				chckbxMOhm.setSelected(false);
				scale = 10000;
				chScale = "MΩ";
			}
		});

		JMenu mnHelp = new JMenu("help");
		menuBar.add(mnHelp);

		JMenuItem mntmManual = new JMenuItem("Manual");
		mntmManual.setVisible(false);
		mnHelp.add(mntmManual);

		JMenu mnReference = new JMenu("Reference");
		mnHelp.add(mnReference);

		JMenuItem mntmGithubPage = new JMenuItem("Github page");
		mntmGithubPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				d = Desktop.getDesktop();
				try {
					d.browse(new URI("https://www.github.com/nf02/"));
				} catch (IOException | URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mnReference.add(mntmGithubPage);

		JMenuItem mntmDone = new JMenuItem("Done");
		mntmDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				d = Desktop.getDesktop();
				try {
					d.browse(new URI("https://www.ko-fi.com/nfvblog"));
				} catch (IOException | URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mnReference.add(mntmDone);

		JMenuItem mntmVersion = new JMenuItem("Version");
		mntmVersion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				help.VisibleSet(true);
			}
		});
		mnHelp.add(mntmVersion);

		/* desktop pane */
		JDesktopPane desktopPane = new JDesktopPane();
		frmResistorCheck.getContentPane().add(desktopPane, BorderLayout.CENTER);

		/** colors **/

		txtC1.setEditable(false);
		txtC1.setBounds(19, 103, 97, 100);
		desktopPane.add(txtC1);
		txtC1.setColumns(10);

		txtC2.setEditable(false);
		txtC2.setColumns(10);
		txtC2.setBounds(159, 103, 97, 100);
		desktopPane.add(txtC2);

		txtC3.setEditable(false);
		txtC3.setColumns(10);
		txtC3.setBounds(304, 103, 97, 100);
		desktopPane.add(txtC3);

		txtC4.setEditable(false);
		txtC4.setColumns(10);
		txtC4.setBounds(444, 103, 97, 100);
		desktopPane.add(txtC4);

		txtC5.setEditable(false);
		txtC5.setColumns(10);
		txtC5.setBounds(581, 103, 97, 100);
		desktopPane.add(txtC5);

		/** buttons **/

		txtC6.setEditable(false);
		txtC6.setColumns(10);
		txtC6.setBounds(731, 103, 97, 100);
		desktopPane.add(txtC6);

		btnch1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (band1 < r.getMaxDim()) {
					band1++;
					txtC1.setBackground(r.changeColor(band1));
				} else {
					band1 = 0;
					txtC1.setBackground(r.changeColor(band1));
				}
			}
		});
		btnch1.setBounds(16, 251, 100, 27);
		desktopPane.add(btnch1);

		btnChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (band2 < r.getMaxDim()) {
					band2++;
					txtC2.setBackground(r.changeColor(band2));
				} else {
					band2 = 0;
					txtC2.setBackground(r.changeColor(band2));
				}
			}
		});
		btnChange.setBounds(156, 251, 100, 27);
		desktopPane.add(btnChange);

		btnChange_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (band3 < r.getMaxDim()) {
					band3++;
					txtC3.setBackground(r.changeColor(band3));
				} else {
					band3 = 0;
					txtC3.setBackground(r.changeColor(band3));
				}
			}
		});
		btnChange_1.setBounds(301, 251, 100, 27);
		desktopPane.add(btnChange_1);

		btnChange_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (band4 < 7) {
					band4++;
					txtC4.setBackground(r.changeColor(band4));
				} else {
					band4 = 0;
					txtC4.setBackground(r.changeColor(band4));
				}
			}
		});
		btnChange_2.setBounds(441, 251, 100, 27);
		desktopPane.add(btnChange_2);

		btnChange_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tolerance < 7) {
					tolerance++;
					txtC5.setBackground(r.changeTol(tolerance));
				} else {
					tolerance = 0;
					txtC5.setBackground(r.changeTol(tolerance));
				}
			}
		});
		btnChange_3.setBounds(581, 251, 100, 27);
		desktopPane.add(btnChange_3);

		btnChange_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (ppm < 5) {
					ppm++;
					txtC6.setBackground(r.changePpm(ppm));
					System.out.println(ppm);
				} else {
					ppm = 0;
					txtC6.setBackground(r.changePpm(ppm));
				}
			}
		});
		btnChange_4.setBounds(731, 251, 100, 27);
		desktopPane.add(btnChange_4);

		textPane.setEditable(false);
		textPane.setBounds(885, 49, 180, 281);
		desktopPane.add(textPane);

		JButton btnLoad = new JButton("load");
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxmntmBand.isSelected() == true) {
					textPane.setText("Resistor value: " + r.getOhm(band1, band2, band4)/scale  + chScale + "\n" + "Tolerance: "
							+ r.getTol(tolerance));
				} else if (chckbxmntmBand_1.isSelected() == true) {
					textPane.setText("Resistor value: " + r.getOhm(band1, band2, band3, band4)/scale + chScale + "\n" + "Tolerance: "
							+ r.getTol(tolerance));
				} else if (chckbxmntmBand_2.isSelected() == true) {
					textPane.setText("Resistor value: " + r.getOhm(band1, band2, band4)/scale + chScale + "\n");
				} else if (chckbxmntmBand_3.isSelected() == true) {
					textPane.setText("Resistor value: " + r.getOhm(band1, band2, band3, band4)/scale + chScale + "\n" + "Tolerance: "
							+ r.getTol(tolerance) + "\nPPM: " + r.getPPM(ppm));
				}
			}
		});
		btnLoad.setBounds(371, 318, 100, 27);
		desktopPane.add(btnLoad);

		JLabel lblBand = new JLabel("1 band");
		lblBand.setBounds(37, 75, 60, 15);
		desktopPane.add(lblBand);

		JLabel lblBand_1 = new JLabel("2 band");
		lblBand_1.setBounds(176, 75, 60, 15);
		desktopPane.add(lblBand_1);

		lblBand_2.setBounds(326, 76, 60, 15);
		desktopPane.add(lblBand_2);

		lblMult.setBounds(462, 75, 69, 15);
		desktopPane.add(lblMult);

		lblTolerance.setBounds(594, 75, 84, 15);
		desktopPane.add(lblTolerance);

		lblPpm.setBounds(760, 76, 84, 15);
		desktopPane.add(lblPpm);

		JLabel lblcNicolaFerru = new JLabel("©️ Nicola Ferru");
		lblcNicolaFerru.setBounds(19, 324, 170, 15);
		desktopPane.add(lblcNicolaFerru);

		JLabel lblOutput = new JLabel("output");
		lblOutput.setBounds(885, 22, 60, 15);
		desktopPane.add(lblOutput);

		JLabel lblResistorcalc = new JLabel("ResistorCalc");
		lblResistorcalc.setFont(new Font("DejaVu Sans", Font.ITALIC, 21));
		lblResistorcalc.setBounds(932, 338, 152, 33);
		desktopPane.add(lblResistorcalc);

	}

	public static void showDialogError(String message) {

	}
}
