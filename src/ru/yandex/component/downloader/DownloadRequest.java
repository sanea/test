package ru.yandex.component.downloader;

import java.util.concurrent.Callable;

import ru.yandex.component.downloader.model.DownloadResponse;

public interface DownloadRequest extends Callable<DownloadResponse> {

}
