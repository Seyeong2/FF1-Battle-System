package editor;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;


/**
 * TileSetGrid
 * @author nhydock
 *
 *	Grid used for choosing tiles to map
 */
public class MapGrid extends JComponent implements MouseListener, MouseMotionListener {

	BufferedImage tileSet;	//original tileset
	
	Image dbImage;			//image with grid drawn on it
	
	int tileSelected;		//the tile selected
	int x, y;				//the tile selected
	int width = 1;			//width of the tileset
	int height = 1;			//height of the tileset
	
	final static int TILE_DIMENSION = 32;			//drawn size
	final static int ORIGINAL_DIMENSIONS = 16; 		//tile size on the original tileset
	
	int[][] tiles;
	
	MapEditorGUI parent;	//parent gui
	
	public MapGrid(MapEditorGUI p)
	{
		parent = p;
		setVisible(true);
	}
	
	public void newMap(int w, int h)
	{
		width = w;
		height = h;
		tiles = new int[w][h];
		dbImage = null;
	}
	
	/**
	 * Set tile set to be used
	 */
	public void setTileSet(BufferedImage ts)
	{
		tileSet = ts;
		width = ts.getWidth()/ORIGINAL_DIMENSIONS;
		height = ts.getHeight()/ORIGINAL_DIMENSIONS;
		dbImage = null;
	}
	
	/**
	 * Draw Tile
	 */
	@Override
	public void mouseClicked(MouseEvent arg0) {
		tiles[x][y] = parent.tileSetIndex;
	}

	/**
	 * Select Tile to draw in
	 */
	@Override
	public void mouseMoved(MouseEvent arg0) {
		x = arg0.getX()/TILE_DIMENSION;
		y = arg0.getY()/TILE_DIMENSION;
	}
	
	/*
	 * DO NOTHING METHODS
	 */
	@Override
	public void mouseEntered(MouseEvent arg0) {}
	@Override
	public void mouseExited(MouseEvent arg0) {}
	@Override
	public void mousePressed(MouseEvent arg0) {}
	@Override
	public void mouseReleased(MouseEvent arg0) {}

	@Override
	public void mouseDragged(MouseEvent arg0) {}

	/**
	 * Draws the actual grid and tiles
	 */
	public void paint(Graphics g)
	{
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		if (dbImage == null)
		{
			dbImage = createImage(width*TILE_DIMENSION, height*TILE_DIMENSION);
			
			Graphics g2 = dbImage.getGraphics();
			int n, k;	//n = x on the tileset, k = y on the tileset
			for (int x = 0; x < width; x++)
				for (int y = 0; y < height; y++)
				{
					n = tiles[x][y]%width;
					k =	tiles[x][y]/width;
					g2.drawImage(tileSet, x*TILE_DIMENSION, y*TILE_DIMENSION, TILE_DIMENSION, TILE_DIMENSION, n*ORIGINAL_DIMENSIONS, k*ORIGINAL_DIMENSIONS, ORIGINAL_DIMENSIONS, ORIGINAL_DIMENSIONS, null);
				}
			
			for (int i = 1; i < width; i++)
				g2.drawLine(i*TILE_DIMENSION, 0, i*TILE_DIMENSION, height*TILE_DIMENSION);
		
			for (int i = 1; i < height; i++)
				g2.drawLine(0, i*TILE_DIMENSION, width*TILE_DIMENSION, i*TILE_DIMENSION);
		}
		
		g.drawImage(dbImage, 0, 0, null);
		g.setColor(Color.YELLOW);
		g.drawRect(x*TILE_DIMENSION, y*TILE_DIMENSION, TILE_DIMENSION, TILE_DIMENSION);
	}

}
