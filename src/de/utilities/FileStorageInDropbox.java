package de.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.DeleteResult;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.UploadErrorException;
import com.dropbox.core.v2.sharing.RequestedVisibility;
import com.dropbox.core.v2.sharing.SharedLinkMetadata;
import com.dropbox.core.v2.sharing.SharedLinkSettings;

public class FileStorageInDropbox {

	private static final String ACCESS_TOKEN = "NPi7qexicCAAAAAAAAAACc7y_xVxc-BwQq1X_r7aAqS50kb4g2oojTD0t7-d4LJK";
	private static final String PROJECT = "SportWeb/home";
	private DbxRequestConfig config;
	private DbxClientV2 client;
	
	public FileStorageInDropbox() {
		config = new DbxRequestConfig(PROJECT);
		client = new DbxClientV2(config, ACCESS_TOKEN);
	}

	public String uploadImage(File image) {

		String publicPath = "";
		String pathOnDropbox = "/images/" + image.getName();
		try {
			InputStream targetStream = new FileInputStream(image);
			FileMetadata metadata = client.files().uploadBuilder(pathOnDropbox).uploadAndFinish(targetStream);
			SharedLinkMetadata slm = client.sharing().createSharedLinkWithSettings(pathOnDropbox,
					SharedLinkSettings.newBuilder().withRequestedVisibility(RequestedVisibility.PUBLIC).build());
			publicPath = slm.getUrl().replace("dl=0", "raw=1");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UploadErrorException e) {
			e.printStackTrace();
		} catch (DbxException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return publicPath;
	}

	public void deleteFile(String path) {
		try {
			DeleteResult metadata = client.files().deleteV2(path);
		} catch (DbxException e) {
			e.printStackTrace();
		}
	}

}
