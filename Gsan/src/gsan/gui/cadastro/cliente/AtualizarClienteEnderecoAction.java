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
package gsan.gui.cadastro.cliente;

import gsan.cadastro.cliente.Cliente;
import gsan.cadastro.cliente.ClienteEndereco;
import gsan.cadastro.cliente.FiltroClienteEndereco;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.seguranca.acesso.PermissaoEspecial;
import gsan.seguranca.acesso.usuario.FiltroUsuarioPemissaoEspecial;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.seguranca.acesso.usuario.UsuarioPermissaoEspecial;
import gsan.util.ConstantesSistema;
import gsan.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

/**
 * Description of the Class
 * 
 * @author Rodrigo
 */
public class AtualizarClienteEnderecoAction extends GcomAction {

	/**
	 * Description of the Method
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param actionForm
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 * @param httpServletResponse
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// localiza o action no objeto actionmapping
		ActionForward retorno = actionMapping
				.findForward("gerenciadorProcesso");

		HttpSession sessao = httpServletRequest.getSession(false);
		
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");

        Fachada fachada = Fachada.getInstancia();
        
		Collection colecaoEnderecos = (Collection) sessao
				.getAttribute("colecaoEnderecos");

		if (colecaoEnderecos != null && !colecaoEnderecos.isEmpty()) {

			DynaValidatorForm clienteActionForm = (DynaValidatorForm) actionForm;

			// Radio que indica qual o endereco de correspondencia
			Long enderecoCorrespondenciaSelecionado = (Long) clienteActionForm
					.get("enderecoCorrespondenciaSelecao");
	        String nome = clienteActionForm.get("nome").toString();

			// Se o end. de correspondencia for escolhido ent�o o objeto deve
			// ser alterado
			if (enderecoCorrespondenciaSelecionado != null) {
				Iterator iterator = colecaoEnderecos.iterator();

				// Varre a colec�o para descobrir o objeto que tem o endere�o de
				// correspondencia
				while (iterator.hasNext()) {
					ClienteEndereco clienteEndereco = (ClienteEndereco) iterator
							.next();

					if (obterTimestampIdObjeto(clienteEndereco) == enderecoCorrespondenciaSelecionado
							.longValue()) {
						// Indica que o objeto possui o endere�o de
						// correspondencia
						clienteEndereco
								.setIndicadorEnderecoCorrespondencia(ConstantesSistema.INDICADOR_ENDERECO_CORRESPONDENCIA);
					} else {
						// Indica que o objeto n�o possui o endere�o de
						// correspondencia
						clienteEndereco
								.setIndicadorEnderecoCorrespondencia(ConstantesSistema.INDICADOR_NAO_ENDERECO_CORRESPONDENCIA);
					}
				}

			} else {
				// Nenhum endere�o foi indicado como endere�o de correspondencia
				throw new ActionServletException(
						"atencao.endereco_correspondencia.nao_selecionado");

			}
			

			/**
			 * Autor: Mariana Victor
			 * Data:  28/12/2010
			 * RM_3320 - [FS0010] Verificar Duplicidade de cliente
			 */
			if (this.getSistemaParametro().getIndicadorDuplicidadeCliente().toString()
					.equals(ConstantesSistema.SIM.toString())) {
				
				FiltroUsuarioPemissaoEspecial filtroUsuarioPemissaoEspecial = new FiltroUsuarioPemissaoEspecial();
				filtroUsuarioPemissaoEspecial.adicionarParametro(new ParametroSimples(FiltroUsuarioPemissaoEspecial.USUARIO_ID, usuario.getId()));
				filtroUsuarioPemissaoEspecial.adicionarParametro(new ParametroSimples(FiltroUsuarioPemissaoEspecial.PERMISSAO_ESPECIAL_ID, PermissaoEspecial.INSERIR_CLIENTE_COM_MESMO_NOME_E_ENDERECO));
										
				Collection colecaoUsuarioPermisao = fachada.pesquisar(filtroUsuarioPemissaoEspecial, UsuarioPermissaoEspecial.class.getName());
				
				if (colecaoUsuarioPermisao == null || colecaoUsuarioPermisao.isEmpty()) {
					FiltroClienteEndereco filtroClienteEndereco = new FiltroClienteEndereco();
					filtroClienteEndereco.adicionarParametro(new ParametroSimples(FiltroClienteEndereco.NOME, nome.toUpperCase()));

					filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTipo");
					filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTitulo");
					filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");
					filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro.municipio.unidadeFederacao");
					filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.cep");
					filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("perimetroInicial.logradouroTipo");
					filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("perimetroInicial.logradouroTitulo");
					filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("perimetroFinal.logradouroTipo");
					filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("perimetroFinal.logradouroTitulo");
					filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("enderecoTipo");
					filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("cliente");
					
					Collection<ClienteEndereco> colecaoClienteEndereco = fachada.pesquisar(filtroClienteEndereco, ClienteEndereco.class.getName());
					
					if (colecaoClienteEndereco != null && !colecaoClienteEndereco.isEmpty()){
						Iterator iterator = colecaoClienteEndereco.iterator();

						// Pega o cliente que foi selecionado para atualiza��o
						Cliente clienteAtualizacao = (Cliente) sessao
								.getAttribute("clienteAtualizacao");
						
						while (iterator.hasNext()) {
							ClienteEndereco clienteEnderecoIterator = (ClienteEndereco) iterator.next();
							
							Iterator iteratorEnderecos = colecaoEnderecos.iterator();
							while (iteratorEnderecos.hasNext()) {
								ClienteEndereco clienteEndereco = (ClienteEndereco) iteratorEnderecos
										.next();
								
								if (clienteEndereco.getEnderecoFormatado().equals(
										clienteEnderecoIterator.getEnderecoFormatado())
										&& !clienteAtualizacao.getId().equals(
												clienteEnderecoIterator.getCliente().getId())) {
									throw new ActionServletException("atencao.duplicidade.cliente", null,
										"Cliente");
								}
							}
						}
					}	
					
				}
				
			}

		} else {
			// O usu�rio deve preencher pelo menos um endere�o e marc�-lo como
			// endere�o de correspond�ncia
			// Mostra o erro
			throw new ActionServletException(
					"atencao.endereco_correspondencia.nao_selecionado");

		}
		return retorno;
	}
}
