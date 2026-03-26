package br.mil.mar.svpm.website;

import android.os.Bundle;
import android.net.http.SslError;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.getcapacitor.BridgeActivity;

public class MainActivity extends BridgeActivity {

    private static final String MAIN_URL = "https://websitesvpm.marinha.mil.br/";
    private static final String FALLBACK_URL = "file:///android_asset/public/index.html";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WebView webView = this.bridge.getWebView();

        // Permitir carregamento de arquivos locais (necessário para fallback React)
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setAllowContentAccess(true);
        webView.getSettings().setAllowFileAccessFromFileURLs(true);
        webView.getSettings().setAllowUniversalAccessFromFileURLs(true);

        final boolean[] pageLoaded = {false};

        webView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageFinished(WebView view, String url) {
                // Se qualquer página HTTPS válida carregou, consideramos sucesso
                if (url != null && url.startsWith("https://")) {
                    pageLoaded[0] = true;
                }
            }

            @Override
            public void onReceivedError(WebView view,
                                        WebResourceRequest request,
                                        WebResourceError error) {

                if (request.isForMainFrame()) {
                    view.loadUrl(FALLBACK_URL);
                }
            }

            @Override
            public void onReceivedSslError(WebView view,
                                           SslErrorHandler handler,
                                           SslError error) {

                handler.cancel();
                view.loadUrl(FALLBACK_URL);
            }

            @Override
            public void onReceivedHttpError(WebView view,
                                            WebResourceRequest request,
                                            WebResourceResponse response) {

                if (request.isForMainFrame() &&
                        response.getStatusCode() >= 400) {

                    view.loadUrl(FALLBACK_URL);
                }
            }
        });

        // Timeout inteligente (8 segundos)
        webView.postDelayed(() -> {
            if (!pageLoaded[0]) {
                webView.stopLoading();
                webView.loadUrl(FALLBACK_URL);
            }
        }, 8000);

        webView.loadUrl(MAIN_URL);
    }
}
