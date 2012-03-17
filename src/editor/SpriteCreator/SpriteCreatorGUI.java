package editor.SpriteCreator;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Vector;
import java.util.prefs.Preferences;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.ini4j.Ini;
import org.ini4j.IniPreferences;
import org.ini4j.InvalidFileFormatException;

import actors.Enemy;

import Map.Terrain;


import editor.ToolKit;
import engine.Sprite;
import engine.TileSet;

/**
 * EnemyEditorGUI
 * @author nhydock
 *
 *	Simple GUI for creating and editing enemies
 *	Most of the values are easily manipulated using spinners
 */
public class SpriteCreatorGUI extends JPanel implements ActionListener, MouseListener{
	
	public static String[] AVAILABLEENEMIES = new String[0];
	
	/*
	 * Buttons
	 */
	JButton newButton;
	JButton saveButton;
	JButton savePNGButton;
	JButton loadButton;
	JButton AddButton;			//add new layer
	JButton RemButton;			//remove layer
	JButton EdtButton;			//edit layer properties
	
	/*
	 * Enemy switcher
	 */
	JList layerList;
	JScrollPane layerListPane;
	
	/*
	 * Fields
	 */
	JTextField nameField;
	
	/*
	 * Sprite Layers
	 */
	final Vector<Layer> layers = new Vector<Layer>();
	
	/*
	 * Other
	 */
	Enemy activeEnemy;		//the currently active enemy for editing
	
	Font font = new Font("Arial", 1, 32);
	
	public SpriteCreatorGUI()
	{
		setLayout(null);
		
		/*
		 * Initialize fields 
		 */
		JLabel l = new JLabel("Enemies: ");
		l.setSize(200,16);
		l.setLocation(10,64);
		layerList = new JList(layers);
		layerList.addMouseListener(this);
		layerListPane = new JScrollPane(layerList);
		layerListPane.setSize(200, 252);
		layerListPane.setLocation(10, 92);
		
		add(l);
		add(layerListPane);
		refreshList();
		
		l = new JLabel("Name: ");
		l.setSize(l.getPreferredSize());
		l.setLocation(10, 10);
		nameField = new JTextField("");
		nameField.setSize(200, 24);
		nameField.setLocation(10, 36);
		
		add(l);
		add(nameField);
		
		
		/*
		 * Initialize Buttons
		 */
		RemButton = new JButton("-");
		RemButton.setSize(48,24);
		RemButton.setLocation(210-RemButton.getWidth(), 350);
		
		AddButton = new JButton("+");
		AddButton.setSize(48,24);
		AddButton.setLocation(RemButton.getX()-AddButton.getWidth(), 350);
		
		EdtButton = new JButton("Edit");
		EdtButton.setSize(100,24);
		EdtButton.setLocation(10, 350);
		
		AddButton.addActionListener(this);
		RemButton.addActionListener(this);
		EdtButton.addActionListener(this);
		
		add(AddButton);
		add(RemButton);
		add(EdtButton);
		
		int[] buttonSize = {230, 24};
		newButton = new JButton("New");
		newButton.setSize(buttonSize[0], buttonSize[1]);
		newButton.setLocation(650, 260);
		saveButton = new JButton("Save");
		saveButton.setSize(buttonSize[0], buttonSize[1]);
		saveButton.setLocation(650, 290);
		savePNGButton = new JButton("Save as .PNG");
		savePNGButton.setSize(buttonSize[0], buttonSize[1]);
		savePNGButton.setLocation(650, 320);
		loadButton = new JButton("Load");
		loadButton.setSize(buttonSize[0], buttonSize[1]);
		loadButton.setLocation(650, 350);
		
		newButton.addActionListener(this);
		saveButton.addActionListener(this);
		savePNGButton.addActionListener(this);
		loadButton.addActionListener(this);
		
		add(newButton);
		add(saveButton);
		add(savePNGButton);
		add(loadButton);

	}

	
	/**
	 * Handles most input
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == newButton)
		{
			final JOptionPane optionPane = new JOptionPane(
				    "There are already layers created, clicking New will wipe all these layers out." +
				    "\nAre you sure this is what you want to do?",
				    JOptionPane.QUESTION_MESSAGE,
				    JOptionPane.YES_NO_OPTION);
			if (((Integer)optionPane.getValue()).intValue() == 1)
				return;
			
			layers.removeAllElements();
			refreshList();
		}
		if (event.getSource() == AddButton)
		{
			new SpriteLayerDialog(this);
		}
		else if (event.getSource() == EdtButton)
		{
			new SpriteLayerDialog(this, layerList.getSelectedIndex());
		}
		else if (event.getSource() == RemButton)
		{
			layers.remove(layerList.getSelectedIndex());
			refreshList();
		}
	}
	
	public void refreshList()
	{
		layerList.setListData(layers);
		layerListPane.setViewportView(layerList);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {}


	@Override
	public void mouseEntered(MouseEvent arg0) {}


	@Override
	public void mouseExited(MouseEvent arg0) {}


	@Override
	public void mousePressed(MouseEvent arg0) {}

	@Override
	public void mouseReleased(MouseEvent arg0) {}
}
