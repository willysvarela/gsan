/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gest�o de Servi�os de Saneamento
*
* This file is part of GSAN, an integrated service management system for Sanitation
*
* GSAN is free software; you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation; either version 2 of the License.
*
* GSAN is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program; if not, write to the Free Software
* Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA
*/

/*
* GSAN - Sistema Integrado de Gest�o de Servi�os de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Aryed Lins de Ara�jo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Cl�udio de Andrade Lira
* Denys Guimar�es Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* Fab�ola Gomes de Ara�jo
* Fl�vio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento J�nior
* Homero Sampaio Cavalcanti
* Ivan S�rgio da Silva J�nior
* Jos� Edmar de Siqueira
* Jos� Thiago Ten�rio Lopes
* K�ssia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* M�rcio Roberto Batista da Silva
* Maria de F�tima Sampaio Leite
* Micaela Maria Coelho de Ara�jo
* Nelson Mendon�a de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael Corr�a Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Ara�jo
* Raphael Veras Rossiter
* Roberto Sobreira Barbalho
* Rodrigo Avellar Silveira
* Rosana Carvalho Barbosa
* S�vio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
*
* Este programa � software livre; voc� pode redistribu�-lo e/ou
* modific�-lo sob os termos de Licen�a P�blica Geral GNU, conforme
* publicada pela Free Software Foundation; vers�o 2 da
* Licen�a.
* Este programa � distribu�do na expectativa de ser �til, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia impl�cita de
* COMERCIALIZA��O ou de ADEQUA��O A QUALQUER PROP�SITO EM
* PARTICULAR. Consulte a Licen�a P�blica Geral GNU para obter mais
* detalhes.
* Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU
* junto com este programa; se n�o, escreva para Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
* 02111-1307, USA.
*/  
package gcom.util;

import gcom.cadastro.DbVersaoBase;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.sistemaparametro.NacionalFeriado;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.interceptor.ObjetoTransacao;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.seguranca.parametrosistema.ParametroSistema;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.GeradorHQLCondicional;
import gcom.util.filtro.PersistenciaUtil;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;
import java.util.concurrent.CopyOnWriteArraySet;

import org.hibernate.CallbackException;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.JDBCException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.StatelessSession;
import org.hibernate.exception.GenericJDBCException;

/**
 * Repositorio para as fun��es utilit�rias do sistema
 * 
 * @author rodrigo
 */
public class RepositorioUtilHBM implements IRepositorioUtil {

	private static RepositorioUtilHBM instancia;

	/**
	 * Construtor da classe RepositorioAcessoHBM
	 */
	protected RepositorioUtilHBM() {
	}

	/**
	 * Retorna o valor de instancia
	 * 
	 * @return O valor de instancia
	 */
	public static RepositorioUtilHBM getInstancia() {
		if (instancia == null) {
			instancia = new RepositorioUtilHBM();
		}

		return instancia;
	}

	/**
	 * Retorna a contagem m�xima de registros de uma determinada classe no
	 * sistema
	 * 
	 * @param classe
	 *            Classe (.class) que ser� feita a contagem
	 * @return N�mero de registros
	 * @exception ErroRepositorioException
	 *                Erro no mecanismo hibernate
	 */

	public int registroMaximo(Class classe) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		/** Retorna o �nico registro do novo SistemaParametros.
		 * 
		 * @return Descri��o do retorno
		 * @exception ErroRepositorioException
		 *                Descri��o da exce��o
		 */
	
		try {

			Query query = session.createQuery("select count(*) from "
					+ classe.getName());

			Integer retorno = (Integer) query.list().iterator().next();

			return retorno.intValue();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

	}

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param filtro
	 *            Descri��o do par�metro
	 * @param pacoteNomeObjeto
	 *            Descri��o do par�metro
	 * @param limite
	 *            Descri��o do par�metro
	 * @return Descri��o do retorno
	 * @exception ErroRepositorioException
	 *                Descri��o da exce��o
	 */
	public Collection limiteMaximoFiltroPesquisa(Filtro filtro,
			String pacoteNomeObjeto, int limite)
			throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();

		Collection resultado = null;

		try {

			resultado = new ArrayList(new CopyOnWriteArraySet(GeradorHQLCondicional.gerarCondicionalQuery(filtro,
					"objeto", "from " + pacoteNomeObjeto + " as objeto",
					session).setMaxResults(limite).list()));

		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

		return resultado;
	}

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param classe
	 *            Descri��o do par�metro
	 * @param atributo
	 *            Descri��o do par�metro
	 * @param parametro1
	 *            Descri��o do par�metro
	 * @param parametro2
	 *            Descri��o do par�metro
	 * @return Descri��o do retorno
	 * @exception ErroRepositorioException
	 *                Descri��o da exce��o
	 */
	public int valorMaximo(Class classe, String atributo, String parametro1,
			String parametro2) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();

		try {
			Query query = session.createQuery("select max( tabela." + atributo
					+ ") from " + classe.getName() + " tabela"
					+ " where tabela." + parametro1 + " = " + parametro2);

			Integer retorno = (Integer) query.list().iterator().next();

			if (retorno == null
					|| retorno.toString().trim().equalsIgnoreCase("")) {
				retorno = new Integer(0);
			}
			return retorno.intValue();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param classe
	 *            Descri��o do par�metro
	 * @param atributo
	 *            Descri��o do par�metro
	 * @return Descri��o do retorno
	 * @exception ErroRepositorioException
	 *                Descri��o da exce��o
	 */
	public int valorMaximo(Class classe, String atributo)
			throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();

		try {
			Query query = session.createQuery("select max(" + atributo
					+ ") from " + classe.getName());

			Integer retorno = (Integer) query.list().iterator().next();

			return retorno.intValue();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}

	}

	/**
	 * Retorna o �nico registro do SistemaParametros.
	 * 
	 * @return Descri��o do retorno
	 * @exception ErroRepositorioException
	 *                Descri��o da exce��o
	 */
	public SistemaParametro pesquisarParametrosDoSistema()
			throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();

		try {
			return (SistemaParametro) session.createCriteria(
					SistemaParametro.class).uniqueResult();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}
	
	
	/** Retorna o �nico registro do novo SistemaParametros.
	 * 
	 * @return Descri��o do retorno
	 * @exception ErroRepositorioException
	 *                Descri��o da exce��o
	 */
	public ParametroSistema pesquisarParametrosDoSistemaNovo()
			throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();

		try {
			return (ParametroSistema) session.createCriteria(
				ParametroSistema.class).uniqueResult();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}
	
	public ParametroSistema pesquisarParametrosDoSistemaNovo(String constante)
			throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();

		try {
			
			Collection colParmSitema = null;
			
			Query query = session.createQuery("select parametroSistema from ParametroSistema parametroSistema "
					+ "where parametroSistema.codigoConstante like :constante");
			colParmSitema = query
					.setString("constante", constante)
					.list();
			
			ParametroSistema parmSistema = null;
			
			if(!Util.isVazioOrNulo(colParmSitema)){
				parmSistema = (ParametroSistema)colParmSitema.iterator().next();
			}
			
			return parmSistema;
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	/**
	 * Permite a inser��o de um objeto mapeado.
	 * 
	 * @param objeto Objeto a ser inserido no banco de dados
	 * @return Objeto ap�s ser inserido no banco de dados
	 * @exception ErroRepositorioException
	 */
	public Object inserir(Object objeto) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		return inserir(objeto, session);
	}

	/**
	 * Permite a inser��o de um objeto mapeado.
	 * 
	 * @param objeto Objeto a ser inserido no banco de dados
	 * @param session Sess�o que representa a conex�o com o banco de dados
	 * @return Objeto ap�s ser inserido no banco de dados
	 * @exception ErroRepositorioException
	 */
	public Object inserir(Object objeto, Session session) throws ErroRepositorioException {
		Object retorno = null;

		try {
			retorno = session.save(objeto);
			session.flush();

			return retorno;
		} catch (GenericJDBCException ex) {
			throw new ErroRepositorioException(ex, "Erro no Hibernate");
		} catch (CallbackException e) {
			throw new ErroRepositorioException(e, e.getMessage());
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	/**
	 * Obtem o pr�ximo valor do sequence do Banco do Imovel ou Cliente
	 * 
	 * @author Rafael Santos
	 * @since 17/11/2006
	 * 
	 * @param objeto
	 *            Descri��o do par�metro
	 * @return Descri��o do retorno
	 * @exception ErroRepositorioException
	 *                Descri��o da exce��o
	 */
	public Object obterNextVal(Object objeto) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
		Object retorno = null;
		String consulta = null;

		try {
			String dialect = HibernateUtil.getDialect();
			// verifica se o objeto � do tipo Imovel
			if (objeto instanceof Imovel) {
				if (dialect.toUpperCase().contains("ORACLE")){
					consulta = "select cadastro.seq_imovel.nextval as id from dual ";
				} else {
					consulta = "select nextval('cadastro.seq_imovel') as id ";
				}
				// verifica se o objeto � do tipo Cliente
			} else if (objeto instanceof Cliente) {
				if (dialect.toUpperCase().contains("ORACLE")){
					consulta = "select cadastro.seq_cliente.nextval as id from dual ";
				} else {
					consulta = "select nextval('cadastro.seq_cliente') as id ";
				}
			}

			retorno = session.createSQLQuery(consulta).addScalar("id",
					Hibernate.INTEGER).uniqueResult();

			return retorno;
		} catch (GenericJDBCException ex) {
			throw new ErroRepositorioException(ex, "Erro no Hibernate");
		} catch (CallbackException e) {
			throw new ErroRepositorioException(e, e.getMessage());
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	/**
	 * Retorna a inst�ncia persistida da classe informada, ou null se n�o encontrada.
	 * 
	 * @author Andr� Miranda
	 * @date 03/12/2015
	 * 
	 * @param classe Classe da inst�ncia a ser pesquisada
	 * @return id Chave prim�ria
	 * @throws ErroRepositorioException
	 */
	public <T extends Object> T pesquisar(Class<T> classe, Serializable id) throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();
		try {
			return (T) session.get(classe, id);
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param filtro
	 *            Descri��o do par�metro
	 * @param pacoteNomeObjeto
	 *            Descri��o do par�metro
	 * @return Descri��o do retorno
	 * @exception ErroRepositorioException
	 *                Descri��o da exce��o
	 */
	public Collection pesquisar(Filtro filtro, String pacoteNomeObjeto)
			throws ErroRepositorioException {
		// cria a cole��o de retorno
		Collection retorno = null;
		// obt�m a sess�o
		Session session = HibernateUtil.getSession();

		try {
			// pesquisa a cole��o de atividades e atribui a vari�vel "retorno"
			retorno = new ArrayList(new CopyOnWriteArraySet(GeradorHQLCondicional.gerarCondicionalQuery(filtro,
					"objeto", "from " + pacoteNomeObjeto + " as objeto",
					session).list()));

			// parametro usado para determinar se quer inicializar os atributos lazies
			if (filtro.isInitializeLazy()){
				inicializarPropriedadesLazies(retorno);
			}
			
			 } catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o
			HibernateUtil.closeSession(session);
		}
		// retorna a cole��o de atividades pesquisada(s)
		return retorno;
	}


	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param filtro
	 *            Descri��o do par�metro
	 * @param pacoteNomeObjeto
	 *            Descri��o do par�metro
	 * @return Descri��o do retorno
	 * @exception ErroRepositorioException
	 *                Descri��o da exce��o
	 */
	public Collection pesquisarGerencial(Filtro filtro, String pacoteNomeObjeto)
			throws ErroRepositorioException {
		// cria a cole��o de retorno
		Collection retorno = null;
		// obt�m a sess�o
		Session session = HibernateUtil.getSessionGerencial();

		try {
			// pesquisa a cole��o de atividades e atribui a vari�vel "retorno"
			retorno = new ArrayList(new CopyOnWriteArraySet(GeradorHQLCondicional.gerarCondicionalQuery(filtro,
					"objeto", "from " + pacoteNomeObjeto + " as objeto",
					session).list()));

			// parametro usado para determinar se quer inicializar os atributos lazies
			if (filtro.isInitializeLazy()){
				inicializarPropriedadesLazies(retorno);
			}
			
			 } catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o
			HibernateUtil.closeSession(session);
		}
		// retorna a cole��o de atividades pesquisada(s)
		return retorno;
	}
	
	/**
	 * Inicializar as propriedades lazies chamando o m�todo
	 * initializeLazy de cada objeto
	 * @param colecao
	 */
	private void inicializarPropriedadesLazies(Collection colecao){
		for (Iterator iter = colecao.iterator(); iter.hasNext();) {
			Object element = iter.next();
			if (element instanceof ObjetoTransacao){
				ObjetoTransacao objTrans = (ObjetoTransacao) element;
				objTrans.initializeLazy();
			}			
		}
	}

	/**
	 * Pesquisa um conjunto de entidades atrav�s de um array de c�digo
	 * 
	 * @param filtro
	 *            Descri��o do par�metro
	 * @param pacoteNomeObjeto
	 *            Descri��o do par�metro
	 * @return Descri��o do retorno
	 * @exception ErroRepositorioException
	 *                Descri��o da exce��o
	 */
	public Collection pesquisar(Collection ids, Filtro filtro,
			String pacoteNomeObjeto) throws ErroRepositorioException {
		// cria a cole��o de retorno
		Collection retorno = null;
		// obt�m a sess�o
		Session session = HibernateUtil.getSession();

		try {
			// pesquisa a cole��o de atividades e atribui a vari�vel "retorno"
			retorno = new ArrayList(new CopyOnWriteArraySet(session.createQuery(
					"from " + pacoteNomeObjeto + " where id IN (:ids)")
					.setParameterList("ids", ids).list()));

			// Carrega os objetos informados no filtro
			if (!filtro.getColecaoCaminhosParaCarregamentoEntidades().isEmpty()) {
				PersistenciaUtil
						.processaObjetosParaCarregamento(filtro
								.getColecaoCaminhosParaCarregamentoEntidades(),
								retorno);
			}
			// parametro usado para determinar se quer inicializar os atributos lazies
			if (filtro.isInitializeLazy()){
				inicializarPropriedadesLazies(retorno);
			}
			
		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o
			HibernateUtil.closeSession(session);
		}
		// retorna a cole��o de atividades pesquisada(s)
		return retorno;
	}

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param objeto
	 *            Descri��o do par�metro
	 * @exception ErroRepositorioException
	 *                Descri��o da exce��o
	 */
	public void atualizar(Object objeto) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		try {

			session.update(objeto);
			session.flush();

		} catch (CallbackException e) {
			throw new ErroRepositorioException(e, e.getMessage());
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate");

		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param id
	 *            Descri��o do par�metro
	 * @param pacoteNomeObjeto
	 *            Descri��o do par�metro
	 * @exception ErroRepositorioException
	 *                Descri��o da exce��o
	 */
	public void remover(int id, String pacoteNomeObjeto,
			OperacaoEfetuada operacaoEfetuada,
			Collection<UsuarioAcaoUsuarioHelper> acaoUsuarioHelper)
			throws ErroRepositorioException {

		// obt�m a sess�o
		Session session = HibernateUtil.getSession();

		try {

			Iterator iterator = session.createQuery(
					"from " + pacoteNomeObjeto + " where id = :id").setInteger(
					"id", id).iterate();

			if (!iterator.hasNext()) {
				throw new RemocaoRegistroNaoExistenteException();

			}

			while (iterator.hasNext()) {
				Object obj = iterator.next();
				if (obj instanceof ObjetoTransacao && operacaoEfetuada != null) {
					ObjetoTransacao objetoTransacao = (ObjetoTransacao) obj;
					objetoTransacao.setOperacaoEfetuada(operacaoEfetuada);
					Iterator it = acaoUsuarioHelper.iterator();
					while (it.hasNext()) {
						UsuarioAcaoUsuarioHelper helper = (UsuarioAcaoUsuarioHelper) it
								.next();
						objetoTransacao.adicionarUsuario(helper.getUsuario(),
								helper.getUsuarioAcao());
					}
				}
				iterator.remove();
			}
			session.flush();
			// restri��es no sistema
		} catch (JDBCException e) {
			// e.printStackTrace();
			// levanta a exce��o para a pr�xima camada
			throw new RemocaoInvalidaException(e);
			// erro no hibernate
		} catch (CallbackException e) {
			throw new ErroRepositorioException(e, e.getMessage());

		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o
			HibernateUtil.closeSession(session);
		}

	}

	/**
	 * 
	 * 
	 * @pram objeto Description of the Parameter
	 * @exception ErroRepositorioException
	 *                Description of the Exception
	 */
	public void remover(Object objeto) throws ErroRepositorioException {
		// obt�m a sess�o
		Session session = HibernateUtil.getSession();

		try {

			session.delete(objeto);
			session.flush();
			// restri��es no sistema
		} catch (JDBCException e) {
			// e.printStackTrace();
			// levanta a exce��o para a pr�xima camada
			throw new RemocaoInvalidaException(e);
			// erro no hibernate
		} catch (CallbackException e) {
			throw new ErroRepositorioException(e, e.getMessage());

		} catch (HibernateException e) {
			e.printStackTrace();
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o
			HibernateUtil.closeSession(session);
		}

	}

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 */

	/**
	 * Insere ou atualiza um registro na base dependendo da chave
	 * 
	 * @param objeto
	 *            Refer�ncia do objeto a ser inserida
	 * @return Description of the Return Value
	 * @exception ErroRepositorioException
	 *                Erro no mecanismo Hibernate
	 */
	public Object inserirOuAtualizar(Object objeto)
			throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();

		Object retorno = null;

		try {
			Object retornoMetodo = descobrirIdClasse(objeto);

			if (retornoMetodo == null) {
				retorno = session.save(objeto);
			} else {
				session.update(objeto);

			}
			session.flush();

			return retorno;
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ErroRepositorioException("Erro no Hibernate: "
					+ objeto.getClass().getName() + " falhou na inser��o");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	public Object descobrirIdClasse(Object objeto)
			throws ErroRepositorioException {
		Object retornoMetodo = null;

		try {
			retornoMetodo = objeto.getClass()
					.getMethod("getId", (Class[]) null).invoke(objeto,
							(Object[]) null);

		} catch (IllegalArgumentException e) {
			throw new ErroRepositorioException(e);
		} catch (SecurityException e) {
			throw new ErroRepositorioException(e);
		} catch (IllegalAccessException e) {
			throw new ErroRepositorioException(e);
		} catch (InvocationTargetException e) {
			throw new ErroRepositorioException(e);
		} catch (NoSuchMethodException e) {
			try {
				retornoMetodo = objeto.getClass().getMethod("getComp_id",
						(Class[]) null).invoke(objeto, (Object[]) null);
			} catch (IllegalArgumentException e1) {
				throw new ErroRepositorioException(e);
			} catch (SecurityException e1) {
				throw new ErroRepositorioException(e);
			} catch (IllegalAccessException e1) {
				throw new ErroRepositorioException(e);
			} catch (InvocationTargetException e1) {
				throw new ErroRepositorioException(e);
			} catch (NoSuchMethodException e1) {
				throw new ErroRepositorioException(e);
			}

		}

		return retornoMetodo;

	}

	/**
	 * Este m�todo de pesquisa serve para localizar qualquer objeto no sistema.
	 * Ele aceita como par�metro um offset que indica a p�gina desejada no
	 * esquema de pagina��o. A pagina��o procura 10 registros de casa vez.
	 * 
	 * @author Rodrigo Silveira
	 * @date 30/03/2006
	 * 
	 * @param filtro
	 *            Filtro da pesquisa
	 * @param pageOffset
	 *            Indicador da p�gina desejada do esquema de pagina��o
	 * @param pacoteNomeObjeto
	 *            Pacote do objeto
	 * @return Cole��o dos resultados da pesquisa
	 * @throws ErroRepositorioException
	 *             Exce��o do reposit�rio
	 */
	public Collection pesquisar(Filtro filtro, int pageOffset,
			String pacoteNomeObjeto) throws ErroRepositorioException {

		Collection retorno = null;

		Session session = HibernateUtil.getSession();

		try {

			retorno = new ArrayList(new CopyOnWriteArraySet(GeradorHQLCondicional.gerarCondicionalQuery(filtro,
					"objeto", "from " + pacoteNomeObjeto + " as objeto",
					session).setFirstResult(10 * pageOffset).setMaxResults(10)
					.list()));

			// Carrega os objetos informados no filtro
			if (!filtro.getColecaoCaminhosParaCarregamentoEntidades().isEmpty()) {
				PersistenciaUtil
						.processaObjetosParaCarregamento(filtro
								.getColecaoCaminhosParaCarregamentoEntidades(),
								retorno);
			}
			// parametro usado para determinar se quer inicializar os atributos lazies
			if (filtro.isInitializeLazy()){
				inicializarPropriedadesLazies(retorno);
			}
			
		} catch (HibernateException e) {

			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {

			HibernateUtil.closeSession(session);
		}

		return retorno;
	}

	/**
	 * Informa o n�mero total de registros de uma pesquisa, auxiliando o esquema
	 * de pagina��o
	 * 
	 * @author Rodrigo Silveira
	 * @date 30/03/2006
	 * 
	 * @param Filtro
	 *            da Pesquisa
	 * @param Pacote
	 *            do objeto pesquisado
	 * @return N�mero de registros da pesquisa
	 * @throws ErroRepositorioException
	 *             Exce��o do reposit�rio
	 */
	public int totalRegistrosPesquisa(Filtro filtro, String pacoteNomeObjeto)
			throws ErroRepositorioException {
		// cria a cole��o de retorno
		int retorno = 0;
		// obt�m a sess�o
		Session session = HibernateUtil.getSession();

		try {

			List camposOrderBy = new ArrayList();
			
			Collection caminhosParaCarregamentoEntidades = new TreeSet();

			camposOrderBy = filtro.getCamposOrderBy();
			
			caminhosParaCarregamentoEntidades = filtro.getColecaoCaminhosParaCarregamentoEntidades();

			filtro.limparCamposOrderBy();
			
			filtro.limparColecaoCaminhosParaCarregamentoEntidades();

			// pesquisa a cole��o de atividades e atribui a vari�vel "retorno"
			retorno = (Integer) GeradorHQLCondicional.gerarCondicionalQuery(
					filtro,
					"objeto",
					"select count(distinct objeto.id) from " + pacoteNomeObjeto
							+ " as objeto", session).uniqueResult();

			filtro.setCampoOrderBy((String[]) camposOrderBy
					.toArray(new String[camposOrderBy.size()]));
			
			filtro.setColecaoCaminhosParaCarregamentoEntidades(caminhosParaCarregamentoEntidades);
			

		} catch (HibernateException e) {
			// levanta a exce��o para a pr�xima camada
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			// fecha a sess�o
			HibernateUtil.closeSession(session);
		}
		// retorna a cole��o de atividades pesquisada(s)
		return retorno;

	}

	/**
	 * M�todo que insere uma Lista em Batch
	 * 
	 * inserirBatch
	 * 
	 * @author Roberta Costa
	 * @date 17/05/2006
	 * 
	 * @param list
	 * @throws ErroRepositorioException
	 */
	public void inserirBatch(List list) throws ErroRepositorioException {
		StatelessSession session = HibernateUtil.getStatelessSession();

		if (list != null && !list.isEmpty()) {
			Iterator it = list.iterator();
			try {
				while (it.hasNext()) {

					Object obj = it.next();

					session.insert(obj);

				}
			} catch (HibernateException e) {
				// levanta a exce��o para a pr�xima camada
				throw new ErroRepositorioException(e, "Erro no Hibernate");
			} finally {
				HibernateUtil.closeSession(session);
			}
		}
	}

	/**
	 * Recupera a cole��o de feriados nacionais
	 * 
	 * @author Pedro Alexandre
	 * @date 13/09/2006
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<NacionalFeriado> pesquisarFeriadosNacionais()
			throws ErroRepositorioException {
		Session session = HibernateUtil.getSession();

		try {
			return session.createCriteria(NacionalFeriado.class).list();
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	/**
	 * UC?? - ????????
	 * 
	 * @author R�mulo Aur�lio Filho
	 * @date 25/01/2007
	 * @descricao O m�todo retorna um objeto com a maior data de Implementacao
	 *            do Banco e sua ultima alteracao
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	
	
	
	public DbVersaoBase pesquisarDbVersaoBase() throws ErroRepositorioException {

		DbVersaoBase dbVersaoBase = null;

		Session session = HibernateUtil.getSession();
		
		String consulta = null;

		try {
			
			consulta = "SELECT dbvb " 
				+ " FROM DbVersaoBase dbvb " 
				+ " WHERE dbvb.versaoDataBase = " 
				+ " (SELECT MAX(dbvimp.versaoDataBase) " 
				+ " FROM DbVersaoBase dbvimp)";

			dbVersaoBase = (DbVersaoBase) session.createQuery(
					consulta).setMaxResults(1).uniqueResult();
			
		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		return dbVersaoBase;
	}
	
	/**
	 * 
	 * M�todo que calcula j� na base e levando em considera��o os feriados municipais e nacionais
	 * a quantidade de dias �teis entre duas data. M�TODO EXCLUSIVO PARA POSGTRES
	 * 
	 * @author Bruno Barros	 * 
	 * 
	 * @param dataInicio - Data inicial
	 * @param dataFim - Data final
	 * @param municipio - Id do munic�pio que ter� seus feriados analisados ( Tabela cadastro.municipio_feriado )
	 * 
	 * @return quantidade de dias �teis entre as duas datas
	 * 
	 * @throws ErroRepositorioException
	 */	
	public Integer calcularDiferencaDiasUteisEntreDuasDatas(Date dataInicio, Date dataFim, Integer municipio) throws ErroRepositorioException{
		
		
		Session session = HibernateUtil.getSession();
		Integer retorno = null;
		String sql;
		
		try {
			sql = "select countbusinessdays(:dataInicio,:dataFim, :municipio) as qtd_dias_uteis";			

			retorno = (Integer) session.createSQLQuery(sql).
					addScalar("qtd_dias_uteis", Hibernate.INTEGER).
					setDate("dataInicio", dataInicio).
					setDate("dataFim", dataFim).
					setInteger("municipio", municipio )
					.uniqueResult();

		} catch (HibernateException e) {
			throw new ErroRepositorioException(e, "Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;
	}		

	/**
	 * Obtem o valor do par�metro
	 * 
	 * @author Rafael Corr�a
	 * @date 17/04/2015
	 */
	public String obterValorParametro(String codigoConstante) throws ErroRepositorioException {

		Session session = HibernateUtil.getSession();
			
		String retorno = null;
		
		try{
			String consulta = "SELECT pasi_vlparametro as valorParametro "
							+ "FROM seguranca.parametro_sistema "
							+ "WHERE pasi_cdconstante = :codigoConstante";
			
			retorno = (String) session.createSQLQuery(consulta)
					.addScalar("valorParametro", Hibernate.STRING)
					.setString("codigoConstante", codigoConstante)
					.setMaxResults(1).uniqueResult();
			
		}catch (HibernateException e) {
			throw new ErroRepositorioException(	e,"Erro no Hibernate");
		} finally {
			HibernateUtil.closeSession(session);
		}
		
		return retorno;

	}
}
