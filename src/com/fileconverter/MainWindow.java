package com.fileconverter;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.SwingConstants;
import java.awt.Color;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField inputFileTextField;
	private JTextField outputFolderTextField;
	private JComboBox<String> fromComboBox;
	private JComboBox<String> toComboBox;
	private JButton inputBrowseButton;
	private JButton outputBrowseButton;
	private JButton convertButton;

	private JLabel statusLabel;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				MainWindow frame = new MainWindow();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public MainWindow() {

		setTitle("File Converter");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 380);
		setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBackground(new Color(0x88, 0xA4, 0xC2));
		contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel titleLabel = new JLabel("FILE CONVERTER");
		titleLabel.setForeground(new Color(255, 255, 255));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
		titleLabel.setBounds(210, 20, 250, 35);
		contentPane.add(titleLabel);

		JLabel inputFileLabel = new JLabel("Input file");
		inputFileLabel.setForeground(new Color(255, 255, 255));
		inputFileLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		inputFileLabel.setBounds(30, 66, 100, 25);
		contentPane.add(inputFileLabel);

		inputFileTextField = new JTextField();
		inputFileTextField.setForeground(new Color(136, 164, 194));
		inputFileTextField.setBackground(new Color(255, 255, 255));
		inputFileTextField.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		inputFileTextField.setBounds(30, 91, 500, 30);
		contentPane.add(inputFileTextField);

		inputBrowseButton = new JButton("...");
		inputBrowseButton.setBackground(new Color(255, 255, 255));
		inputBrowseButton.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		inputBrowseButton.setBounds(540, 91, 60, 30);
		contentPane.add(inputBrowseButton);

		// Input file browse
		
		inputBrowseButton.addActionListener(e -> {

			JFileChooser fileChooser = new JFileChooser();

			fileChooser.setDialogTitle("Select a file");

			int result = fileChooser.showOpenDialog(this);

			if (result == JFileChooser.APPROVE_OPTION) {

				File selectedFile = fileChooser.getSelectedFile();

				inputFileTextField.setText(selectedFile.getAbsolutePath());

				// Auto detect file ext
				String fileName = selectedFile.getName();

				int dotIndex = fileName.lastIndexOf(".");

				if (dotIndex > 0) {

					String extension = fileName
							.substring(dotIndex + 1)
							.toUpperCase();

					// Auto select detected format
					for (int i = 0; i < fromComboBox.getItemCount(); i++) {

						if (fromComboBox.getItemAt(i).equals(extension)) {

							fromComboBox.setSelectedItem(extension);
							break;
						}
					}
				}

				statusLabel.setText("Status: File selected.");

			}
		});

		// Convert from label
		JLabel fromLabel = new JLabel("Convert from");
		fromLabel.setForeground(new Color(255, 255, 255));
		fromLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		fromLabel.setBounds(30, 128, 150, 25);
		contentPane.add(fromLabel);

		// Convert from combo box
		fromComboBox = new JComboBox<>();
		fromComboBox.setForeground(new Color(136, 164, 194));
		fromComboBox.setBackground(new Color(255, 255, 255));
		fromComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 12));

		fromComboBox.setBounds(30, 153, 250, 30);

		fromComboBox.addItem("ODT");
		fromComboBox.addItem("ODS");
		fromComboBox.addItem("ODP");
		fromComboBox.addItem("DOC");
		fromComboBox.addItem("DOCX");
		fromComboBox.addItem("XLS");
		fromComboBox.addItem("XLSX");
		fromComboBox.addItem("PPT");
		fromComboBox.addItem("PPTX");
		fromComboBox.addItem("PDF");

		contentPane.add(fromComboBox);

		// Convert to label
		JLabel toLabel = new JLabel("Convert to");
		toLabel.setForeground(new Color(255, 255, 255));
		toLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		toLabel.setBounds(320, 128, 150, 25);
		contentPane.add(toLabel);

		// Convert to combo box
		toComboBox = new JComboBox<>();
		toComboBox.setForeground(new Color(136, 164, 194));
		toComboBox.setBackground(new Color(255, 255, 255));
		toComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 12));

		toComboBox.setBounds(320, 153, 250, 30);

		toComboBox.addItem("ODT");
		toComboBox.addItem("ODS");
		toComboBox.addItem("ODP");
		toComboBox.addItem("DOC");
		toComboBox.addItem("DOCX");
		toComboBox.addItem("XLS");
		toComboBox.addItem("XLSX");
		toComboBox.addItem("PPT");
		toComboBox.addItem("PPTX");
		toComboBox.addItem("PDF");

		toComboBox.setSelectedItem("PDF");

		contentPane.add(toComboBox);

		JLabel outputFolderLabel = new JLabel("Output folder");
		outputFolderLabel.setForeground(new Color(255, 255, 255));
		outputFolderLabel.setBounds(30, 194, 150, 25);
		contentPane.add(outputFolderLabel);

		outputFolderTextField = new JTextField();
		outputFolderTextField.setForeground(new Color(136, 164, 194));
		outputFolderTextField.setBackground(new Color(255, 255, 255));
		outputFolderTextField.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		outputFolderTextField.setBounds(30, 219, 500, 30);
		contentPane.add(outputFolderTextField);

		outputBrowseButton = new JButton("...");
		outputBrowseButton.setBackground(new Color(255, 255, 255));
		outputBrowseButton.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		outputBrowseButton.setBounds(540, 219, 60, 30);
		contentPane.add(outputBrowseButton);

		// Output file browse

		outputBrowseButton.addActionListener(e -> {

			JFileChooser folderChooser = new JFileChooser();

			folderChooser.setDialogTitle("Select output folder");

			// Only allow selecting dir
			folderChooser.setFileSelectionMode(
					JFileChooser.DIRECTORIES_ONLY
			);

			int result = folderChooser.showOpenDialog(this);

			if (result == JFileChooser.APPROVE_OPTION) {

				File selectedFolder = folderChooser.getSelectedFile();

				outputFolderTextField.setText(
						selectedFolder.getAbsolutePath()
				);

				statusLabel.setText("Status: Output folder selected.");
			}
		});

		convertButton = new JButton("CONVERT");
		convertButton.setForeground(new Color(136, 164, 194));
		convertButton.setBackground(new Color(255, 255, 255));

		convertButton.setFont(
				new Font("Segoe UI", Font.BOLD, 12)
		);

		convertButton.setBounds(240, 261, 170, 40);

		contentPane.add(convertButton);

		// Convert btn

		convertButton.addActionListener(e -> {

		    String inputFile = inputFileTextField.getText();
		    String outputFolder = outputFolderTextField.getText();

		    if (inputFile.isEmpty()) {

		        JOptionPane.showMessageDialog(
		                this,
		                "Please select an input file.",
		                "Missing file",
		                JOptionPane.WARNING_MESSAGE
		        );

		        return;
		    }

		    if (outputFolder.isEmpty()) {

		        JOptionPane.showMessageDialog(
		                this,
		                "Please select an output folder.",
		                "Missing folder",
		                JOptionPane.WARNING_MESSAGE
		        );

		        return;
		    }

		    String fromFormat =
		            (String) fromComboBox.getSelectedItem();

		    String toFormat =
		            (String) toComboBox.getSelectedItem();

		    // Check input file exists
		    File input = new File(inputFile);

		    if (!input.exists()) {

		        JOptionPane.showMessageDialog(
		                this,
		                "The selected input file does not exist.",
		                "File error",
		                JOptionPane.ERROR_MESSAGE
		        );

		        return;
		    }

		    // Update status
		    statusLabel.setText(
		            "Status: Converting " + fromFormat + " to " + toFormat + "..."
		    );

		    // Start conversion
		    boolean success = LibreOfficeConverter.convert(
		            inputFile,
		            outputFolder,
		            toFormat
		    );

		    if (success) {

		        statusLabel.setText(
		                "Status: Conversion completed successfully."
		        );

		        JOptionPane.showMessageDialog(
		                this,
		                "The file was converted successfully!",
		                "Conversion complete",
		                JOptionPane.INFORMATION_MESSAGE
		        );

		    } else {

		        statusLabel.setText(
		                "Status: Conversion failed."
		        );

		        JOptionPane.showMessageDialog(
		                this,
		                "The conversion failed.\n\n"
		                + "Make sure LibreOffice is installed.",
		                "Conversion error",
		                JOptionPane.ERROR_MESSAGE
		        );
		    }
		});

		statusLabel = new JLabel("Status: Ready");
		statusLabel.setForeground(new Color(255, 255, 255));
		statusLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		statusLabel.setBounds(30, 306, 570, 25);
		contentPane.add(statusLabel);
	}
}