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
package gcom.gui.cadastro.unidade;

import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action de exibir manter unidade oraganizacional
 * @author Ana Maria 
 * @created 21/11/2006
 */

public class ExibirManterUnidadeOrganizacionalAction extends GcomAction {
	
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("manterUnidadeOrganizacional");
        
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();

        Collection colecaoUnidadeOrganizacional = null;
        
        FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
        
		if (sessao.getAttribute("filtroUnidadeOrganizacional") != null) {
			filtroUnidadeOrganizacional = (FiltroUnidadeOrganizacional) sessao
					.getAttribute("filtroUnidadeOrganizacional");

			// 1� Passo - Pegar o total de registros atrav�s de um count da
			// consulta que aparecer� na tela
			Integer totalRegistros = fachada
					.pesquisarUnidadeOrganizacionalFiltroCount(filtroUnidadeOrganizacional);

			// 2� Passo - Chamar a fun��o de Pagina��o passando o total de
			// registros
			retorno = this.controlarPaginacao(httpServletRequest, retorno,
					totalRegistros);
			
			// 3� Passo - Obter a cole��o da consulta que aparecer� na tela
			// passando o numero de paginas
			// da pesquisa que est� no request
			colecaoUnidadeOrganizacional = fachada
						.pesquisarUnidadeOrganizacionalFiltro(filtroUnidadeOrganizacional,
								(Integer) httpServletRequest
										.getAttribute("numeroPaginasPesquisa"));
			
			// [FS0003] Nenhum registro encontrado				
			if (colecaoUnidadeOrganizacional == null || colecaoUnidadeOrganizacional.isEmpty()) {
				throw new ActionServletException(
						"atencao.pesquisa.nenhumresultado");
			}

			String identificadorAtualizar = (String)sessao.getAttribute("indicadorAtualizar");
			
			if (colecaoUnidadeOrganizacional.size()== 1 && identificadorAtualizar != null){
				// caso o resultado do filtro s� retorne um registro 
				// e o check box Atualizar estiver selecionado
				//o sistema n�o exibe a tela de manter, exibe a de atualizar 
				retorno = actionMapping.findForward("atualizarUnidadeOrganizacional");
				UnidadeOrganizacional unidadeOrganizacional = (UnidadeOrganizacional)colecaoUnidadeOrganizacional.iterator().next();
				sessao.setAttribute("idRegistroAtualizacao", new Integer (unidadeOrganizacional.getId()).toString());
				sessao.removeAttribute("manter");				
			}else{
				sessao.setAttribute("collectionUnidadeOrganizacional", colecaoUnidadeOrganizacional);
				sessao.setAttribute("manter", "manter");
			}
			
			sessao.setAttribute("collectionUnidadeOrganizacional", colecaoUnidadeOrganizacional);
			sessao.setAttribute("totalRegistros", totalRegistros);
			sessao.setAttribute("numeroPaginasPesquisa",httpServletRequest
					.getAttribute("numeroPaginasPesquisa"));
			
		}
		/*// Parte da verifica��o do filtro
		FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = null;

		// Verifica se o filtro foi informado pela p�gina de filtragem de unidade organizacional
		if (sessao.getAttribute("filtroUnidadeOrganizacional") != null) {
			filtroUnidadeOrganizacional = (FiltroUnidadeOrganizacional) sessao
					.getAttribute("filtroUnidadeOrganizacional");
		}

		if ((retorno != null)&&(retorno.getName().equalsIgnoreCase("manterUnidadeOrganizacional"))) {
		
			filtroUnidadeOrganizacional.adicionarCaminhoParaCarregamentoEntidade("unidadeTipo");
			 
			filtroUnidadeOrganizacional.setCampoOrderBy(FiltroUnidadeOrganizacional.ID,FiltroUnidadeOrganizacional.DESCRICAO);

			Map resultado = controlarPaginacao(httpServletRequest, retorno,
					filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());
			collectionUnidadeOrganizacional = (Collection) resultado.get("colecaoRetorno");
			retorno = (ActionForward) resultado.get("destinoActionForward");
			

			// [FS0003] Nenhum registro encontrado				
			if (collectionUnidadeOrganizacional == null || collectionUnidadeOrganizacional.isEmpty()) {
				throw new ActionServletException(
						"atencao.pesquisa.nenhumresultado");
			}
			
			String identificadorAtualizar = (String)sessao.getAttribute("indicadorAtualizar");
			
			if (collectionUnidadeOrganizacional.size()== 1 && identificadorAtualizar != null){
				// caso o resultado do filtro s� retorne um registro 
				// e o check box Atualizar estiver selecionado
				//o sistema n�o exibe a tela de manter, exibe a de atualizar 
				retorno = actionMapping.findForward("atualizarUnidadeOrganizacional");
				UnidadeOrganizacional unidadeOrganizacional = (UnidadeOrganizacional)collectionUnidadeOrganizacional.iterator().next();
				sessao.setAttribute("idRegistroAtualizacao", new Integer (unidadeOrganizacional.getId()).toString());
				sessao.removeAttribute("manter");				
			}else{
				sessao.setAttribute("collectionUnidadeOrganizacional", collectionUnidadeOrganizacional);
				sessao.setAttribute("manter", "manter");
			}

		}
*/
		sessao.removeAttribute("UseCase");
		return retorno;
    }

}