/*
 * An XML document type.
 * Localname: consultarLog
 * Namespace: http://fachada.webservices.gateway.neurotech.com.br
 * Java type: gsan.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarLogDocument
 *
 * Automatically generated - do not modify.
 */
package gsan.integracao.webservice.neurotech.gateway.webservices.fachada.impl;
/**
 * A document containing one consultarLog(@http://fachada.webservices.gateway.neurotech.com.br) element.
 *
 * This is a complex type.
 */
public class ConsultarLogDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements gsan.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarLogDocument
{
    
    public ConsultarLogDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName CONSULTARLOG$0 = 
        new javax.xml.namespace.QName("http://fachada.webservices.gateway.neurotech.com.br", "consultarLog");
    
    
    /**
     * Gets the "consultarLog" element
     */
    public gsan.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarLogDocument.ConsultarLog getConsultarLog()
    {
        synchronized (monitor())
        {
            check_orphaned();
            gsan.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarLogDocument.ConsultarLog target = null;
            target = (gsan.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarLogDocument.ConsultarLog)get_store().find_element_user(CONSULTARLOG$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "consultarLog" element
     */
    public void setConsultarLog(gsan.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarLogDocument.ConsultarLog consultarLog)
    {
        synchronized (monitor())
        {
            check_orphaned();
            gsan.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarLogDocument.ConsultarLog target = null;
            target = (gsan.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarLogDocument.ConsultarLog)get_store().find_element_user(CONSULTARLOG$0, 0);
            if (target == null)
            {
                target = (gsan.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarLogDocument.ConsultarLog)get_store().add_element_user(CONSULTARLOG$0);
            }
            target.set(consultarLog);
        }
    }
    
    /**
     * Appends and returns a new empty "consultarLog" element
     */
    public gsan.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarLogDocument.ConsultarLog addNewConsultarLog()
    {
        synchronized (monitor())
        {
            check_orphaned();
            gsan.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarLogDocument.ConsultarLog target = null;
            target = (gsan.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarLogDocument.ConsultarLog)get_store().add_element_user(CONSULTARLOG$0);
            return target;
        }
    }
    /**
     * An XML consultarLog(@http://fachada.webservices.gateway.neurotech.com.br).
     *
     * This is a complex type.
     */
    public static class ConsultarLogImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements gsan.integracao.webservice.neurotech.gateway.webservices.fachada.ConsultarLogDocument.ConsultarLog
    {
        
        public ConsultarLogImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName PIDENTIFICADOR$0 = 
            new javax.xml.namespace.QName("http://fachada.webservices.gateway.neurotech.com.br", "pIdentificador");
        private static final javax.xml.namespace.QName PLOGIN$2 = 
            new javax.xml.namespace.QName("http://fachada.webservices.gateway.neurotech.com.br", "pLogin");
        private static final javax.xml.namespace.QName PSENHA$4 = 
            new javax.xml.namespace.QName("http://fachada.webservices.gateway.neurotech.com.br", "pSenha");
        private static final javax.xml.namespace.QName PLOGIDS$6 = 
            new javax.xml.namespace.QName("http://fachada.webservices.gateway.neurotech.com.br", "pLogIds");
        
        
        /**
         * Gets the "pIdentificador" element
         */
        public int getPIdentificador()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(PIDENTIFICADOR$0, 0);
                if (target == null)
                {
                    return 0;
                }
                return target.getIntValue();
            }
        }
        
        /**
         * Gets (as xml) the "pIdentificador" element
         */
        public org.apache.xmlbeans.XmlInt xgetPIdentificador()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlInt target = null;
                target = (org.apache.xmlbeans.XmlInt)get_store().find_element_user(PIDENTIFICADOR$0, 0);
                return target;
            }
        }
        
        /**
         * True if has "pIdentificador" element
         */
        public boolean isSetPIdentificador()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(PIDENTIFICADOR$0) != 0;
            }
        }
        
        /**
         * Sets the "pIdentificador" element
         */
        public void setPIdentificador(int pIdentificador)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(PIDENTIFICADOR$0, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(PIDENTIFICADOR$0);
                }
                target.setIntValue(pIdentificador);
            }
        }
        
        /**
         * Sets (as xml) the "pIdentificador" element
         */
        public void xsetPIdentificador(org.apache.xmlbeans.XmlInt pIdentificador)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlInt target = null;
                target = (org.apache.xmlbeans.XmlInt)get_store().find_element_user(PIDENTIFICADOR$0, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlInt)get_store().add_element_user(PIDENTIFICADOR$0);
                }
                target.set(pIdentificador);
            }
        }
        
        /**
         * Unsets the "pIdentificador" element
         */
        public void unsetPIdentificador()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(PIDENTIFICADOR$0, 0);
            }
        }
        
        /**
         * Gets the "pLogin" element
         */
        public java.lang.String getPLogin()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(PLOGIN$2, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "pLogin" element
         */
        public org.apache.xmlbeans.XmlString xgetPLogin()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(PLOGIN$2, 0);
                return target;
            }
        }
        
        /**
         * Tests for nil "pLogin" element
         */
        public boolean isNilPLogin()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(PLOGIN$2, 0);
                if (target == null) return false;
                return target.isNil();
            }
        }
        
        /**
         * True if has "pLogin" element
         */
        public boolean isSetPLogin()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(PLOGIN$2) != 0;
            }
        }
        
        /**
         * Sets the "pLogin" element
         */
        public void setPLogin(java.lang.String pLogin)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(PLOGIN$2, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(PLOGIN$2);
                }
                target.setStringValue(pLogin);
            }
        }
        
        /**
         * Sets (as xml) the "pLogin" element
         */
        public void xsetPLogin(org.apache.xmlbeans.XmlString pLogin)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(PLOGIN$2, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(PLOGIN$2);
                }
                target.set(pLogin);
            }
        }
        
        /**
         * Nils the "pLogin" element
         */
        public void setNilPLogin()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(PLOGIN$2, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(PLOGIN$2);
                }
                target.setNil();
            }
        }
        
        /**
         * Unsets the "pLogin" element
         */
        public void unsetPLogin()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(PLOGIN$2, 0);
            }
        }
        
        /**
         * Gets the "pSenha" element
         */
        public java.lang.String getPSenha()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(PSENHA$4, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "pSenha" element
         */
        public org.apache.xmlbeans.XmlString xgetPSenha()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(PSENHA$4, 0);
                return target;
            }
        }
        
        /**
         * Tests for nil "pSenha" element
         */
        public boolean isNilPSenha()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(PSENHA$4, 0);
                if (target == null) return false;
                return target.isNil();
            }
        }
        
        /**
         * True if has "pSenha" element
         */
        public boolean isSetPSenha()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(PSENHA$4) != 0;
            }
        }
        
        /**
         * Sets the "pSenha" element
         */
        public void setPSenha(java.lang.String pSenha)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(PSENHA$4, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(PSENHA$4);
                }
                target.setStringValue(pSenha);
            }
        }
        
        /**
         * Sets (as xml) the "pSenha" element
         */
        public void xsetPSenha(org.apache.xmlbeans.XmlString pSenha)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(PSENHA$4, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(PSENHA$4);
                }
                target.set(pSenha);
            }
        }
        
        /**
         * Nils the "pSenha" element
         */
        public void setNilPSenha()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(PSENHA$4, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(PSENHA$4);
                }
                target.setNil();
            }
        }
        
        /**
         * Unsets the "pSenha" element
         */
        public void unsetPSenha()
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(PSENHA$4, 0);
            }
        }
        
        /**
         * Gets array of all "pLogIds" elements
         */
        public java.lang.String[] getPLogIdsArray()
        {
            synchronized (monitor())
            {
                check_orphaned();
                java.util.List targetList = new java.util.ArrayList();
                get_store().find_all_element_users(PLOGIDS$6, targetList);
                java.lang.String[] result = new java.lang.String[targetList.size()];
                for (int i = 0, len = targetList.size() ; i < len ; i++)
                    result[i] = ((org.apache.xmlbeans.SimpleValue)targetList.get(i)).getStringValue();
                return result;
            }
        }
        
        /**
         * Gets ith "pLogIds" element
         */
        public java.lang.String getPLogIdsArray(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(PLOGIDS$6, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) array of all "pLogIds" elements
         */
        public org.apache.xmlbeans.XmlString[] xgetPLogIdsArray()
        {
            synchronized (monitor())
            {
                check_orphaned();
                java.util.List targetList = new java.util.ArrayList();
                get_store().find_all_element_users(PLOGIDS$6, targetList);
                org.apache.xmlbeans.XmlString[] result = new org.apache.xmlbeans.XmlString[targetList.size()];
                targetList.toArray(result);
                return result;
            }
        }
        
        /**
         * Gets (as xml) ith "pLogIds" element
         */
        public org.apache.xmlbeans.XmlString xgetPLogIdsArray(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(PLOGIDS$6, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                return (org.apache.xmlbeans.XmlString)target;
            }
        }
        
        /**
         * Tests for nil ith "pLogIds" element
         */
        public boolean isNilPLogIdsArray(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(PLOGIDS$6, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                return target.isNil();
            }
        }
        
        /**
         * Returns number of "pLogIds" element
         */
        public int sizeOfPLogIdsArray()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(PLOGIDS$6);
            }
        }
        
        /**
         * Sets array of all "pLogIds" element
         */
        public void setPLogIdsArray(java.lang.String[] pLogIdsArray)
        {
            synchronized (monitor())
            {
                check_orphaned();
                arraySetterHelper(pLogIdsArray, PLOGIDS$6);
            }
        }
        
        /**
         * Sets ith "pLogIds" element
         */
        public void setPLogIdsArray(int i, java.lang.String pLogIds)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(PLOGIDS$6, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                target.setStringValue(pLogIds);
            }
        }
        
        /**
         * Sets (as xml) array of all "pLogIds" element
         */
        public void xsetPLogIdsArray(org.apache.xmlbeans.XmlString[]pLogIdsArray)
        {
            synchronized (monitor())
            {
                check_orphaned();
                arraySetterHelper(pLogIdsArray, PLOGIDS$6);
            }
        }
        
        /**
         * Sets (as xml) ith "pLogIds" element
         */
        public void xsetPLogIdsArray(int i, org.apache.xmlbeans.XmlString pLogIds)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(PLOGIDS$6, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                target.set(pLogIds);
            }
        }
        
        /**
         * Nils the ith "pLogIds" element
         */
        public void setNilPLogIdsArray(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(PLOGIDS$6, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                target.setNil();
            }
        }
        
        /**
         * Inserts the value as the ith "pLogIds" element
         */
        public void insertPLogIds(int i, java.lang.String pLogIds)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = 
                    (org.apache.xmlbeans.SimpleValue)get_store().insert_element_user(PLOGIDS$6, i);
                target.setStringValue(pLogIds);
            }
        }
        
        /**
         * Appends the value as the last "pLogIds" element
         */
        public void addPLogIds(java.lang.String pLogIds)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(PLOGIDS$6);
                target.setStringValue(pLogIds);
            }
        }
        
        /**
         * Inserts and returns a new empty value (as xml) as the ith "pLogIds" element
         */
        public org.apache.xmlbeans.XmlString insertNewPLogIds(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().insert_element_user(PLOGIDS$6, i);
                return target;
            }
        }
        
        /**
         * Appends and returns a new empty value (as xml) as the last "pLogIds" element
         */
        public org.apache.xmlbeans.XmlString addNewPLogIds()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(PLOGIDS$6);
                return target;
            }
        }
        
        /**
         * Removes the ith "pLogIds" element
         */
        public void removePLogIds(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(PLOGIDS$6, i);
            }
        }
    }
}
