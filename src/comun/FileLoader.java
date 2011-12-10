package comun;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.zkoss.util.media.Media;
import org.zkoss.zul.Fileupload;
import org.zkoss.zul.Image;

/**
 * @author Miguel B
 *
 */
public class FileLoader {

	private static final int MAX_FILE_SIZE = 20480000;
	private static final int BUFFER_SIZE = 1024;
	private static final String SAVE_PATH = "C:\\fundalaraTemp\\archivos\\";

	public void cargarImagen(Image img) {

		try {
			Media media = Fileupload.get();
			if (media == null) {
				return;
			}
			String type = media.getContentType().split("/")[0];
			if (type.equals("image")) {
				if (media.getByteData().length >  MAX_FILE_SIZE) {
					return;
				}
				org.zkoss.image.Image picture = (org.zkoss.image.Image) media;
				img.setContent(picture);
			}
			guardarArchivo(media);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void guardarArchivo(Media media) {
		BufferedInputStream in = null;
		BufferedOutputStream out = null;
		try {
			InputStream fin = media.getStreamData();
			in = new BufferedInputStream(fin);
			File baseDir = new File(SAVE_PATH);
			if (!baseDir.exists()) {
				baseDir.mkdirs();
			}
			File file = new File(SAVE_PATH + media.getName());
			OutputStream fout = new FileOutputStream(file);
			out = new BufferedOutputStream(fout);
			byte buffer[] = new byte[BUFFER_SIZE];
			int ch = in.read(buffer);
			while (ch != -1) {
				out.write(buffer, 0, ch);
				ch = in.read(buffer);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (out != null)
					out.close();

				if (in != null)
					in.close();

			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

	}

}