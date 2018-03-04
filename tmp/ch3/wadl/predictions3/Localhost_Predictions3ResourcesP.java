
package predictions3;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import javax.ws.rs.core.UriBuilder;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;

@Generated(value = {
    "wadl|file:/home/mkalin/jwsur2/ch3/wadl/wadl113/samples/ant/../predictions.wadl"
}, comments = "wadl2java, http://wadl.java.net", date = "2013-03-02T19:16:12.894-06:00")
public class Localhost_Predictions3ResourcesP {

    /**
     * The base URI for the resource represented by this proxy
     * 
     */
    public final static URI BASE_URI;

    static {
        URI originalURI = URI.create("http://localhost:8080/predictions3/resourcesP/");
        // Look up to see if we have any indirection in the local copy
        // of META-INF/java-rs-catalog.xml file, assuming it will be in the
        // oasis:name:tc:entity:xmlns:xml:catalog namespace or similar duck type
        java.io.InputStream is = Localhost_Predictions3ResourcesP.class.getResourceAsStream("/META-INF/jax-rs-catalog.xml");
        if (is!=null) {
            try {
                // Ignore the namespace in the catalog, can't use wildcard until
                // we are sure we have XPath 2.0
                String found = javax.xml.xpath.XPathFactory.newInstance().newXPath().evaluate(
                    "/*[name(.) = 'catalog']/*[name(.) = 'uri' and @name ='" + originalURI +"']/@uri", 
                    new org.xml.sax.InputSource(is)); 
                if (found!=null && found.length()>0) {
                    originalURI = java.net.URI.create(found);
                }
                
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            finally {
                try {
                    is.close();
                } catch (java.io.IOException e) {
                }
            }
        }
        BASE_URI = originalURI;
    }

    public static Localhost_Predictions3ResourcesP.Root root(Client client, URI baseURI) {
        return new Localhost_Predictions3ResourcesP.Root(client, baseURI);
    }

    public static Localhost_Predictions3ResourcesP.Root root() {
        return root(Client.create(), BASE_URI);
    }

    public static Localhost_Predictions3ResourcesP.Root root(Client client) {
        return root(client, BASE_URI);
    }

    public static class Root {

        private Client _client;
        private UriBuilder _uriBuilder;
        private Map<String, Object> _templateAndMatrixParameterValues;
        private URI _uri;

        /**
         * Create new instance using existing Client instance
         * 
         */
        public Root(Client client, URI uri) {
            _client = client;
            _uri = uri;
            _uriBuilder = UriBuilder.fromUri(uri);
            _uriBuilder = _uriBuilder.path("/");
            _templateAndMatrixParameterValues = new HashMap<String, Object>();
        }

        private Root(Client client, UriBuilder uriBuilder, Map<String, Object> map) {
            _client = client;
            _uriBuilder = uriBuilder.clone();
            _templateAndMatrixParameterValues = map;
        }

        public Localhost_Predictions3ResourcesP.Root.DeleteIdD deleteIdD(String idD) {
            return new Localhost_Predictions3ResourcesP.Root.DeleteIdD(_client, _uriBuilder.buildFromMap(_templateAndMatrixParameterValues), idD);
        }

        public Localhost_Predictions3ResourcesP.Root.Create create() {
            return new Localhost_Predictions3ResourcesP.Root.Create(_client, _uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
        }

        public Localhost_Predictions3ResourcesP.Root.Update update() {
            return new Localhost_Predictions3ResourcesP.Root.Update(_client, _uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
        }

        public Localhost_Predictions3ResourcesP.Root.Xml xml() {
            return new Localhost_Predictions3ResourcesP.Root.Xml(_client, _uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
        }

        public Localhost_Predictions3ResourcesP.Root.XmlIdD xmlIdD(String idD) {
            return new Localhost_Predictions3ResourcesP.Root.XmlIdD(_client, _uriBuilder.buildFromMap(_templateAndMatrixParameterValues), idD);
        }

        public Localhost_Predictions3ResourcesP.Root.Json json() {
            return new Localhost_Predictions3ResourcesP.Root.Json(_client, _uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
        }

        public Localhost_Predictions3ResourcesP.Root.JsonIdD jsonIdD(String idD) {
            return new Localhost_Predictions3ResourcesP.Root.JsonIdD(_client, _uriBuilder.buildFromMap(_templateAndMatrixParameterValues), idD);
        }

        public Localhost_Predictions3ResourcesP.Root.Plain plain() {
            return new Localhost_Predictions3ResourcesP.Root.Plain(_client, _uriBuilder.buildFromMap(_templateAndMatrixParameterValues));
        }

        public static class Create {

            private Client _client;
            private UriBuilder _uriBuilder;
            private Map<String, Object> _templateAndMatrixParameterValues;
            private URI _uri;

            /**
             * Create new instance using existing Client instance
             * 
             */
            public Create(Client client, URI uri) {
                _client = client;
                _uri = uri;
                _uriBuilder = UriBuilder.fromUri(uri);
                _uriBuilder = _uriBuilder.path("/create");
                _templateAndMatrixParameterValues = new HashMap<String, Object>();
            }

            private Create(Client client, UriBuilder uriBuilder, Map<String, Object> map) {
                _client = client;
                _uriBuilder = uriBuilder.clone();
                _templateAndMatrixParameterValues = map;
            }

            public<T >T postXWwwFormUrlencodedAsTextPlain(Object input, GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("text/plain");
                resourceBuilder = resourceBuilder.type("application/x-www-form-urlencoded");
                ClientResponse response;
                response = resourceBuilder.method("POST", ClientResponse.class, input);
                return response.getEntity(returnType);
            }

            public<T >T postXWwwFormUrlencodedAsTextPlain(Object input, Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("text/plain");
                resourceBuilder = resourceBuilder.type("application/x-www-form-urlencoded");
                ClientResponse response;
                response = resourceBuilder.method("POST", ClientResponse.class, input);
                return response.getEntity(returnType);
            }

        }

        public static class DeleteIdD {

            private Client _client;
            private UriBuilder _uriBuilder;
            private Map<String, Object> _templateAndMatrixParameterValues;
            private URI _uri;

            /**
             * Create new instance using existing Client instance
             * 
             */
            public DeleteIdD(Client client, URI uri, String idD) {
                _client = client;
                _uri = uri;
                _uriBuilder = UriBuilder.fromUri(uri);
                _uriBuilder = _uriBuilder.path("/delete/{id: \\d+}");
                _templateAndMatrixParameterValues = new HashMap<String, Object>();
                _templateAndMatrixParameterValues.put("id: \\d+", idD);
            }

            private DeleteIdD(Client client, UriBuilder uriBuilder, Map<String, Object> map) {
                _client = client;
                _uriBuilder = uriBuilder.clone();
                _templateAndMatrixParameterValues = map;
            }

            /**
             * Get id: \d+
             * 
             */
            public String getIdD() {
                return ((String) _templateAndMatrixParameterValues.get("id: \\d+"));
            }

            /**
             * Duplicate state and set id: \d+
             * 
             */
            public Localhost_Predictions3ResourcesP.Root.DeleteIdD setIdD(String idD) {
                Map<String, Object> copyMap;
                copyMap = new HashMap<String, Object>(_templateAndMatrixParameterValues);
                UriBuilder copyUriBuilder = _uriBuilder.clone();
                copyMap.put("id: \\d+", idD);
                return new Localhost_Predictions3ResourcesP.Root.DeleteIdD(_client, copyUriBuilder, copyMap);
            }

            public<T >T deleteAsJson(GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("DELETE", ClientResponse.class);
                return response.getEntity(returnType);
            }

            public<T >T deleteAsJson(Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("DELETE", ClientResponse.class);
                return response.getEntity(returnType);
            }

        }

        public static class Json {

            private Client _client;
            private UriBuilder _uriBuilder;
            private Map<String, Object> _templateAndMatrixParameterValues;
            private URI _uri;

            /**
             * Create new instance using existing Client instance
             * 
             */
            public Json(Client client, URI uri) {
                _client = client;
                _uri = uri;
                _uriBuilder = UriBuilder.fromUri(uri);
                _uriBuilder = _uriBuilder.path("/json");
                _templateAndMatrixParameterValues = new HashMap<String, Object>();
            }

            private Json(Client client, UriBuilder uriBuilder, Map<String, Object> map) {
                _client = client;
                _uriBuilder = uriBuilder.clone();
                _templateAndMatrixParameterValues = map;
            }

            public<T >T getAsJson(GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                return response.getEntity(returnType);
            }

            public<T >T getAsJson(Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                return response.getEntity(returnType);
            }

        }

        public static class JsonIdD {

            private Client _client;
            private UriBuilder _uriBuilder;
            private Map<String, Object> _templateAndMatrixParameterValues;
            private URI _uri;

            /**
             * Create new instance using existing Client instance
             * 
             */
            public JsonIdD(Client client, URI uri, String idD) {
                _client = client;
                _uri = uri;
                _uriBuilder = UriBuilder.fromUri(uri);
                _uriBuilder = _uriBuilder.path("/json/{id: \\d+}");
                _templateAndMatrixParameterValues = new HashMap<String, Object>();
                _templateAndMatrixParameterValues.put("id: \\d+", idD);
            }

            private JsonIdD(Client client, UriBuilder uriBuilder, Map<String, Object> map) {
                _client = client;
                _uriBuilder = uriBuilder.clone();
                _templateAndMatrixParameterValues = map;
            }

            /**
             * Get id: \d+
             * 
             */
            public String getIdD() {
                return ((String) _templateAndMatrixParameterValues.get("id: \\d+"));
            }

            /**
             * Duplicate state and set id: \d+
             * 
             */
            public Localhost_Predictions3ResourcesP.Root.JsonIdD setIdD(String idD) {
                Map<String, Object> copyMap;
                copyMap = new HashMap<String, Object>(_templateAndMatrixParameterValues);
                UriBuilder copyUriBuilder = _uriBuilder.clone();
                copyMap.put("id: \\d+", idD);
                return new Localhost_Predictions3ResourcesP.Root.JsonIdD(_client, copyUriBuilder, copyMap);
            }

            public<T >T getAsJson(GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                return response.getEntity(returnType);
            }

            public<T >T getAsJson(Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                return response.getEntity(returnType);
            }

        }

        public static class Plain {

            private Client _client;
            private UriBuilder _uriBuilder;
            private Map<String, Object> _templateAndMatrixParameterValues;
            private URI _uri;

            /**
             * Create new instance using existing Client instance
             * 
             */
            public Plain(Client client, URI uri) {
                _client = client;
                _uri = uri;
                _uriBuilder = UriBuilder.fromUri(uri);
                _uriBuilder = _uriBuilder.path("/plain");
                _templateAndMatrixParameterValues = new HashMap<String, Object>();
            }

            private Plain(Client client, UriBuilder uriBuilder, Map<String, Object> map) {
                _client = client;
                _uriBuilder = uriBuilder.clone();
                _templateAndMatrixParameterValues = map;
            }

            public<T >T getAsTextPlain(GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("text/plain");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                return response.getEntity(returnType);
            }

            public<T >T getAsTextPlain(Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("text/plain");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                return response.getEntity(returnType);
            }

        }

        public static class Update {

            private Client _client;
            private UriBuilder _uriBuilder;
            private Map<String, Object> _templateAndMatrixParameterValues;
            private URI _uri;

            /**
             * Create new instance using existing Client instance
             * 
             */
            public Update(Client client, URI uri) {
                _client = client;
                _uri = uri;
                _uriBuilder = UriBuilder.fromUri(uri);
                _uriBuilder = _uriBuilder.path("/update");
                _templateAndMatrixParameterValues = new HashMap<String, Object>();
            }

            private Update(Client client, UriBuilder uriBuilder, Map<String, Object> map) {
                _client = client;
                _uriBuilder = uriBuilder.clone();
                _templateAndMatrixParameterValues = map;
            }

            public<T >T putXWwwFormUrlencodedAsTextPlain(Object input, GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("text/plain");
                resourceBuilder = resourceBuilder.type("application/x-www-form-urlencoded");
                ClientResponse response;
                response = resourceBuilder.method("PUT", ClientResponse.class, input);
                return response.getEntity(returnType);
            }

            public<T >T putXWwwFormUrlencodedAsTextPlain(Object input, Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("text/plain");
                resourceBuilder = resourceBuilder.type("application/x-www-form-urlencoded");
                ClientResponse response;
                response = resourceBuilder.method("PUT", ClientResponse.class, input);
                return response.getEntity(returnType);
            }

        }

        public static class Xml {

            private Client _client;
            private UriBuilder _uriBuilder;
            private Map<String, Object> _templateAndMatrixParameterValues;
            private URI _uri;

            /**
             * Create new instance using existing Client instance
             * 
             */
            public Xml(Client client, URI uri) {
                _client = client;
                _uri = uri;
                _uriBuilder = UriBuilder.fromUri(uri);
                _uriBuilder = _uriBuilder.path("/xml");
                _templateAndMatrixParameterValues = new HashMap<String, Object>();
            }

            private Xml(Client client, UriBuilder uriBuilder, Map<String, Object> map) {
                _client = client;
                _uriBuilder = uriBuilder.clone();
                _templateAndMatrixParameterValues = map;
            }

            public<T >T getAsXml(GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/xml");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                return response.getEntity(returnType);
            }

            public<T >T getAsXml(Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/xml");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                return response.getEntity(returnType);
            }

        }

        public static class XmlIdD {

            private Client _client;
            private UriBuilder _uriBuilder;
            private Map<String, Object> _templateAndMatrixParameterValues;
            private URI _uri;

            /**
             * Create new instance using existing Client instance
             * 
             */
            public XmlIdD(Client client, URI uri, String idD) {
                _client = client;
                _uri = uri;
                _uriBuilder = UriBuilder.fromUri(uri);
                _uriBuilder = _uriBuilder.path("/xml/{id: \\d+}");
                _templateAndMatrixParameterValues = new HashMap<String, Object>();
                _templateAndMatrixParameterValues.put("id: \\d+", idD);
            }

            private XmlIdD(Client client, UriBuilder uriBuilder, Map<String, Object> map) {
                _client = client;
                _uriBuilder = uriBuilder.clone();
                _templateAndMatrixParameterValues = map;
            }

            /**
             * Get id: \d+
             * 
             */
            public String getIdD() {
                return ((String) _templateAndMatrixParameterValues.get("id: \\d+"));
            }

            /**
             * Duplicate state and set id: \d+
             * 
             */
            public Localhost_Predictions3ResourcesP.Root.XmlIdD setIdD(String idD) {
                Map<String, Object> copyMap;
                copyMap = new HashMap<String, Object>(_templateAndMatrixParameterValues);
                UriBuilder copyUriBuilder = _uriBuilder.clone();
                copyMap.put("id: \\d+", idD);
                return new Localhost_Predictions3ResourcesP.Root.XmlIdD(_client, copyUriBuilder, copyMap);
            }

            public<T >T getAsXml(GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/xml");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                return response.getEntity(returnType);
            }

            public<T >T getAsXml(Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/xml");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                return response.getEntity(returnType);
            }

        }

    }

}
