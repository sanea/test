package ru.yandex.component.downloader.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;

import ru.yandex.component.downloader.DownloadRequest;
import ru.yandex.component.downloader.model.DownloadResponse;
import ru.yandex.component.downloader.model.Status;

public class HTTPDownloadRequest implements DownloadRequest {

	public static final String URL_PROPERTY = "url";
	private final Properties props;

	public HTTPDownloadRequest(Properties _props) {
		this.props = _props;
	}

	@Override
	public DownloadResponse call() throws Exception {
		StringBuilder sb = new StringBuilder();
		DownloadResponse downloadResponse = new DownloadResponse();
		try {
			URL url = new URL(props.getProperty(URL_PROPERTY));
			URLConnection uc = url.openConnection();
			downloadResponse.setStatus(Status.started);
			BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));

			String inputLine = null;

			while ((inputLine = in.readLine()) != null) {
				sb.append(inputLine);
				Thread.sleep(10000);
			}
			in.close();
			downloadResponse.setContent(sb.toString());
			downloadResponse.setStatus(Status.finished);
		} catch (IOException | InterruptedException e) {
			downloadResponse.setErrorMessage(e.getMessage());
			downloadResponse.setStatus(Status.error);
		}
		return downloadResponse;
	}
}
