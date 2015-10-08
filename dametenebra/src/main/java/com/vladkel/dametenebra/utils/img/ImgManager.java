package com.vladkel.dametenebra.utils.img;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * @author eliott
 *
 */
public class ImgManager {

	private File src;

	private final static String destination_full = "data/img/full/";

	private final static String destination_mini = "data/img/mini/";

	private final static int MAX = 900;

	private final static int MIN = 600;

	public ImgManager() {
		super();
	}

	public ImgManager(File file) {
		super();
		this.setSrc(file);
	}

	public BufferedImage resize(int scale) {
		try {
			Image image = ImageIO.read(src);

			if (image.getHeight(null) > image.getWidth(null))
				return resize(image, MIN / scale, MAX / scale);
			else
				return resize(image, MAX / scale, MIN / scale);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean resizeAndWrite() {
		try {
			ImageIO.write(resize(1), "jpg", new File(destination_full + src.getName()));
			ImageIO.write(resize(6), "jpg", new File(destination_mini + src.getName()));
			return true;
		} catch (IOException e) {
			javax.swing.JOptionPane.showMessageDialog(null,
					"Une erreur est survenue pendant la génération des fichiers. Veuillez réessayer.");
		}
		return false;
	}

	public BufferedImage resize(Image img, int width, int height) {
		return getScaledInstance((BufferedImage) img, width, height);
	}

	public BufferedImage getScaledInstance(BufferedImage img, int targetWidth, int targetHeight) {

		int type = (img.getTransparency() == Transparency.OPAQUE) ? BufferedImage.TYPE_INT_RGB
				: BufferedImage.TYPE_INT_ARGB;
		BufferedImage ret = (BufferedImage) img;

		int w = img.getWidth(), h = img.getHeight();

		if (w < targetWidth || h < targetHeight)
			return ret;

		do {
			if (w > targetWidth) {
				w /= 2;
				if (w < targetWidth) {
					w = targetWidth;
				}
			}

			if (h > targetHeight) {
				h /= 2;
				if (h < targetHeight) {
					h = targetHeight;
				}
			}

			BufferedImage tmp = new BufferedImage(w, h, type);
			Graphics2D g = tmp.createGraphics();
			g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			g.drawImage(ret, 0, 0, w, h, null);
			g.dispose();

			ret = tmp;

		} while (w != targetWidth || h != targetHeight);

		return ret;
	}

	public File getSrc() {
		return src;
	}

	public void setSrc(File src) {
		this.src = src;
	}

}
