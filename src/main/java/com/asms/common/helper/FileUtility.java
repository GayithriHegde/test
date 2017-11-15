package com.asms.common.helper;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.MemoryCacheImageOutputStream;

import org.apache.commons.io.FilenameUtils;
import org.jboss.logging.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUtility {

	
	private static final Logger logger = LoggerFactory.getLogger(FileUtility.class);
	public static String removeExtension(String s) {

		String separator = System.getProperty("file.separator");
		String filename;

		// Remove the path upto the filename.
		int lastSeparatorIndex = s.lastIndexOf(separator);
		if (lastSeparatorIndex == -1) {
			filename = s;
		} else {
			filename = s.substring(lastSeparatorIndex + 1);
		}

		// Remove the extension.
		int extensionIndex = filename.lastIndexOf(".");
		if (extensionIndex == -1)
			return filename;

		return filename.substring(0, extensionIndex);
	}
	
	public static String getExtension(String filename){
		String extension;
		extension = FilenameUtils.getExtension(filename);
		return extension;
	}

	public static void saveFile(InputStream inputStream, String serverLocation) throws Exception {
		OutputStream outpuStream = null;
		try {
			outpuStream = new FileOutputStream(new File(serverLocation));
			int read = 0;
			byte[] bytes = new byte[1024];
			while ((read = inputStream.read(bytes)) != -1) {
				outpuStream.write(bytes, 0, read);
			}
			outpuStream.flush();

		} finally {
			outpuStream.close();

		}

	}

	public static void compressAndUploadImage(InputStream in, String serverFilename) throws IOException {		
		
		BufferedImage sourceImage = ImageIO.read(in);
	
		boolean createDir = new File(serverFilename).getParentFile().mkdirs();
		logger.debug("sessionid:{} created directory for image compression upload: {} ", (String) MDC.get("sessionId"),createDir);
		double ratio = (double) sourceImage.getWidth() / sourceImage.getHeight();
		int height = sourceImage.getHeight();
		int width = sourceImage.getWidth();
		if (width < 1) {
			width = (int) (height * ratio + 0.4);
		} else if (height < 1) {
			height = (int) (width / ratio + 0.4);
		}
		Image scaled = sourceImage.getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING);
		BufferedImage bufferedScaled = new BufferedImage(scaled.getWidth(null), scaled.getHeight(null),
				BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = bufferedScaled.createGraphics();
		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
		g2d.drawImage(scaled, 0, 0, width, height, null);
		File dest = new File(serverFilename);
		dest.createNewFile();
		
		ImageWriter writer = null;
		FileImageOutputStream output = null;
		try {
			writer = ImageIO.getImageWritersByFormatName("jpg").next();
			ImageWriteParam param = writer.getDefaultWriteParam();
			param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
			param.setCompressionQuality(0.6f);
			output = new FileImageOutputStream(dest);
			writer.setOutput(output);
			IIOImage iioImage = new IIOImage(bufferedScaled, null, null);
			writer.write(null, iioImage, param);
			
		} catch (IOException ex) {
			throw ex;
		} finally {
			if (writer != null) {
				writer.dispose();
			}
			if (output != null) {
				output.close();
			}
		}

	}
	
	public static byte[] compressAndUploadImageS3(byte[] imageByteArray, String serverFilename) throws IOException {		
		InputStream in = new ByteArrayInputStream(imageByteArray);
		BufferedImage sourceImage = ImageIO.read(in);
	
		boolean createDir = new File(serverFilename).getParentFile().mkdirs();
		logger.debug("sessionid:{} created directory for image compression upload: {} for proposalid :{}", (String) MDC.get("sessionId"),createDir);
		double ratio = (double) sourceImage.getWidth() / sourceImage.getHeight();
		int height = sourceImage.getHeight();
		int width = sourceImage.getWidth();
		if (width < 1) {
			width = (int) (height * ratio + 0.4);
		} else if (height < 1) {
			height = (int) (width / ratio + 0.4);
		}
		Image scaled = sourceImage.getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING);
		BufferedImage bufferedScaled = new BufferedImage(scaled.getWidth(null), scaled.getHeight(null),
				BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = bufferedScaled.createGraphics();
		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
		g2d.drawImage(scaled, 0, 0, width, height, null);
		File dest = new File(serverFilename);
		dest.createNewFile();
		
		ImageWriter writer = null;
		FileImageOutputStream output = null;
		try {
			writer = ImageIO.getImageWritersByFormatName("jpg").next();
			ImageWriteParam param = writer.getDefaultWriteParam();
			param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
			param.setCompressionQuality(0.6f);
			
			ByteArrayOutputStream bos = new ByteArrayOutputStream(); 
			writer.setOutput(new MemoryCacheImageOutputStream(bos));         
			writer.write(null,new IIOImage(bufferedScaled,null,null),param); 
			
			return bos.toByteArray();
			
		} catch (IOException ex) {
			throw ex;
		} finally {
			if (writer != null) {
				writer.dispose();
			}
			if (output != null) {
				output.close();
			}
		}

	}
}
