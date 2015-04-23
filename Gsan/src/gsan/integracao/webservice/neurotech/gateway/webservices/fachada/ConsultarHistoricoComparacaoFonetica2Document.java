/*
 * An XML document type.
 * Localname: consultarHistoricoComparacaoFonetica2
 * Namespace: http://fachada.webservices.gateway.neurotech.com.br
 * Java type: gsan.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFonetica2Document
 *
 * Automatically generated - do not modify.
 */
package gsan.integracao.webservice.neurotech.gateway.webservices.fachada;


/**
 * A document containing one consultarHistoricoComparacaoFonetica2(@http://fachada.webservices.gateway.neurotech.com.br) element.
 *
 * This is a complex type.
 */
public interface ConsultarHistoricoComparacaoFonetica2Document extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(ConsultarHistoricoComparacaoFonetica2Document.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s2BF1201EEE8AB6EDBFBEB6610A3939BA").resolveHandle("consultarhistoricocomparacaofonetica2b145doctype");
    
    /**
     * Gets the "consultarHistoricoComparacaoFonetica2" element
     */
    gsan.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFonetica2Document.ConsultarHistoricoComparacaoFonetica2 getConsultarHistoricoComparacaoFonetica2();
    
    /**
     * Sets the "consultarHistoricoComparacaoFonetica2" element
     */
    void setConsultarHistoricoComparacaoFonetica2(gsan.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFonetica2Document.ConsultarHistoricoComparacaoFonetica2 consultarHistoricoComparacaoFonetica2);
    
    /**
     * Appends and returns a new empty "consultarHistoricoComparacaoFonetica2" element
     */
    gsan.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFonetica2Document.ConsultarHistoricoComparacaoFonetica2 addNewConsultarHistoricoComparacaoFonetica2();
    
    /**
     * An XML consultarHistoricoComparacaoFonetica2(@http://fachada.webservices.gateway.neurotech.com.br).
     *
     * This is a complex type.
     */
    public interface ConsultarHistoricoComparacaoFonetica2 extends org.apache.xmlbeans.XmlObject
    {
        public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
            org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(ConsultarHistoricoComparacaoFonetica2.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s2BF1201EEE8AB6EDBFBEB6610A3939BA").resolveHandle("consultarhistoricocomparacaofonetica2bcadelemtype");
        
        /**
         * Gets the "pIdentificador" element
         */
        int getPIdentificador();
        
        /**
         * Gets (as xml) the "pIdentificador" element
         */
        org.apache.xmlbeans.XmlInt xgetPIdentificador();
        
        /**
         * True if has "pIdentificador" element
         */
        boolean isSetPIdentificador();
        
        /**
         * Sets the "pIdentificador" element
         */
        void setPIdentificador(int pIdentificador);
        
        /**
         * Sets (as xml) the "pIdentificador" element
         */
        void xsetPIdentificador(org.apache.xmlbeans.XmlInt pIdentificador);
        
        /**
         * Unsets the "pIdentificador" element
         */
        void unsetPIdentificador();
        
        /**
         * Gets the "pLogin" element
         */
        java.lang.String getPLogin();
        
        /**
         * Gets (as xml) the "pLogin" element
         */
        org.apache.xmlbeans.XmlString xgetPLogin();
        
        /**
         * Tests for nil "pLogin" element
         */
        boolean isNilPLogin();
        
        /**
         * True if has "pLogin" element
         */
        boolean isSetPLogin();
        
        /**
         * Sets the "pLogin" element
         */
        void setPLogin(java.lang.String pLogin);
        
        /**
         * Sets (as xml) the "pLogin" element
         */
        void xsetPLogin(org.apache.xmlbeans.XmlString pLogin);
        
        /**
         * Nils the "pLogin" element
         */
        void setNilPLogin();
        
        /**
         * Unsets the "pLogin" element
         */
        void unsetPLogin();
        
        /**
         * Gets the "pSenha" element
         */
        java.lang.String getPSenha();
        
        /**
         * Gets (as xml) the "pSenha" element
         */
        org.apache.xmlbeans.XmlString xgetPSenha();
        
        /**
         * Tests for nil "pSenha" element
         */
        boolean isNilPSenha();
        
        /**
         * True if has "pSenha" element
         */
        boolean isSetPSenha();
        
        /**
         * Sets the "pSenha" element
         */
        void setPSenha(java.lang.String pSenha);
        
        /**
         * Sets (as xml) the "pSenha" element
         */
        void xsetPSenha(org.apache.xmlbeans.XmlString pSenha);
        
        /**
         * Nils the "pSenha" element
         */
        void setNilPSenha();
        
        /**
         * Unsets the "pSenha" element
         */
        void unsetPSenha();
        
        /**
         * Gets the "pConsulta" element
         */
        gsan.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.ConsultaWSComparacaoFonetica getPConsulta();
        
        /**
         * Tests for nil "pConsulta" element
         */
        boolean isNilPConsulta();
        
        /**
         * True if has "pConsulta" element
         */
        boolean isSetPConsulta();
        
        /**
         * Sets the "pConsulta" element
         */
        void setPConsulta(gsan.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.ConsultaWSComparacaoFonetica pConsulta);
        
        /**
         * Appends and returns a new empty "pConsulta" element
         */
        gsan.integracao.webservice.neurotech.gerenciadorconsulta.webservices.entidades.xsd.ConsultaWSComparacaoFonetica addNewPConsulta();
        
        /**
         * Nils the "pConsulta" element
         */
        void setNilPConsulta();
        
        /**
         * Unsets the "pConsulta" element
         */
        void unsetPConsulta();
        
        /**
         * Gets the "pAlgoritmo" element
         */
        java.lang.String getPAlgoritmo();
        
        /**
         * Gets (as xml) the "pAlgoritmo" element
         */
        org.apache.xmlbeans.XmlString xgetPAlgoritmo();
        
        /**
         * Tests for nil "pAlgoritmo" element
         */
        boolean isNilPAlgoritmo();
        
        /**
         * True if has "pAlgoritmo" element
         */
        boolean isSetPAlgoritmo();
        
        /**
         * Sets the "pAlgoritmo" element
         */
        void setPAlgoritmo(java.lang.String pAlgoritmo);
        
        /**
         * Sets (as xml) the "pAlgoritmo" element
         */
        void xsetPAlgoritmo(org.apache.xmlbeans.XmlString pAlgoritmo);
        
        /**
         * Nils the "pAlgoritmo" element
         */
        void setNilPAlgoritmo();
        
        /**
         * Unsets the "pAlgoritmo" element
         */
        void unsetPAlgoritmo();
        
        /**
         * A factory class with static methods for creating instances
         * of this type.
         */
        
        public static final class Factory
        {
            public static gsan.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFonetica2Document.ConsultarHistoricoComparacaoFonetica2 newInstance() {
              return (gsan.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFonetica2Document.ConsultarHistoricoComparacaoFonetica2) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
            
            public static gsan.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFonetica2Document.ConsultarHistoricoComparacaoFonetica2 newInstance(org.apache.xmlbeans.XmlOptions options) {
              return (gsan.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFonetica2Document.ConsultarHistoricoComparacaoFonetica2) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
            
            private Factory() { } // No instance of this class allowed
        }
    }
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static gsan.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFonetica2Document newInstance() {
          return (gsan.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFonetica2Document) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static gsan.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFonetica2Document newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (gsan.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFonetica2Document) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static gsan.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFonetica2Document parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (gsan.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFonetica2Document) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static gsan.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFonetica2Document parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (gsan.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFonetica2Document) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static gsan.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFonetica2Document parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (gsan.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFonetica2Document) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static gsan.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFonetica2Document parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (gsan.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFonetica2Document) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static gsan.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFonetica2Document parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (gsan.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFonetica2Document) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static gsan.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFonetica2Document parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (gsan.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFonetica2Document) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static gsan.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFonetica2Document parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (gsan.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFonetica2Document) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static gsan.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFonetica2Document parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (gsan.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFonetica2Document) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static gsan.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFonetica2Document parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (gsan.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFonetica2Document) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static gsan.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFonetica2Document parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (gsan.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFonetica2Document) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static gsan.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFonetica2Document parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (gsan.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFonetica2Document) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static gsan.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFonetica2Document parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (gsan.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFonetica2Document) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static gsan.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFonetica2Document parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (gsan.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFonetica2Document) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static gsan.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFonetica2Document parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (gsan.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFonetica2Document) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static gsan.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFonetica2Document parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (gsan.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFonetica2Document) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static gsan.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFonetica2Document parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (gsan.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarHistoricoComparacaoFonetica2Document) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
