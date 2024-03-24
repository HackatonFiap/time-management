package fiap.hackaton.grupo32.hackatoncompany.application.rest.filter;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.*;
import java.util.UUID;

@Component
@Slf4j
@WebFilter("/*")
public class RestControllerFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        CopyableHttpServletRequestWrapper wrappedRequest = new CopyableHttpServletRequestWrapper(request);
        ContentCachingResponseWrapper wrapperResponse = new ContentCachingResponseWrapper(response);

        loggingHandlerHttpRequest(wrappedRequest);

        //Reseta o inputStream apos copiar o request body
        wrappedRequest.resetStream();

        filterChain.doFilter(wrappedRequest, wrapperResponse);

        loggingHandlerHttpResponse(wrapperResponse);

        //Copia o response body para o outputStream apos pegar o response
        wrapperResponse.copyBodyToResponse();
    }

    //Realiza a copia da request e reseta o input stream do httpservlet
    private static final class CopyableHttpServletRequestWrapper extends HttpServletRequestWrapper {

        private byte[] rawData;
        private final HttpServletRequest request;
        private CopyableServletInputStream servletInputStream;

        private static final class CopyableServletInputStream extends ServletInputStream {

            private final InputStream stream;

            public CopyableServletInputStream(InputStream stream) {
                this.stream = stream;
            }

            @Override
            public boolean isFinished() {
                try {
                    int remainingBytes = stream.available();
                    return 0 == remainingBytes;
                } catch (IOException ex) {
                    return false;
                }
            }

            @Override
            public boolean isReady() {
                return true;
            }

            @Override
            public void setReadListener(ReadListener listener) {
            }

            @Override
            public int read() throws IOException {
                return stream.read();
            }
        }

        private CopyableHttpServletRequestWrapper(HttpServletRequest request) {
            super(request);
            this.request = request;
        }

        @Override
        public ServletInputStream getInputStream() throws IOException {
            copyBodyDataIfNecessary();
            return servletInputStream;
        }

        @Override
        public BufferedReader getReader() throws IOException {
            copyBodyDataIfNecessary();
            return new BufferedReader(new InputStreamReader(servletInputStream));
        }

        public void copyBodyDataIfNecessary() throws IOException {
            if (null == rawData) {
                rawData = IOUtils.toByteArray(this.request.getInputStream());
                servletInputStream = new CopyableServletInputStream(new ByteArrayInputStream(rawData));
            }
        }

        public void resetStream() throws IOException {
            this.servletInputStream = new CopyableServletInputStream(new ByteArrayInputStream(rawData));
        }
    }

    private void loggingHandlerHttpRequest(CopyableHttpServletRequestWrapper requestWrapper) throws IOException {
        var url = requestWrapper.getRequestURL().toString();
        var remoteIp = requestWrapper.getRemoteAddr();
        var uuid = UUID.randomUUID().toString();
        MDC.put("UUID", uuid);

        String requestBody = IOUtils.toString(requestWrapper.getReader());
        JsonElement requestBodyJson = JsonParser.parseString(requestBody);

        log.info("START");
        log.info("URL: {} :: request ID [{}] :: IP solicitante: {}", url, uuid, remoteIp);
        log.info("Request: {}", requestBodyJson);
    }

    private void loggingHandlerHttpResponse(ContentCachingResponseWrapper responseWrapper) throws UnsupportedEncodingException {
        byte[] responseBody = responseWrapper.getContentAsByteArray();
        String responseBodyStr = new String(responseBody, responseWrapper.getCharacterEncoding());

        log.info("Response: {}", responseBodyStr);
        log.info("Status Code: {}", responseWrapper.getStatus());
        log.info("END");

    }
}
