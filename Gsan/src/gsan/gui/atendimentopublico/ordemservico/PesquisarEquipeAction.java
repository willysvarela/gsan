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
package gsan.gui.atendimentopublico.ordemservico;

import gsan.atendimentopublico.ordemservico.Equipe;
import gsan.atendimentopublico.ordemservico.FiltroEquipe;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.util.filtro.ComparacaoTexto;
import gsan.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0377] Pesquisar Equipe
 * 
 * @author Leonardo Regis
 * @date 31/07/2006
 * 
 */
public class PesquisarEquipeAction extends GcomAction {

	/**
	 * [UC0377] Pesquisar Equipe
	 * 
	 * @author Leonardo Regis
	 * @date 31/07/2006
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return ActionForward
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("exibirResultadoPesquisaEquipe");

		HttpSession sessao = httpServletRequest.getSession(false);

		PesquisarEquipeActionForm pesquisarEquipeActionForm = (PesquisarEquipeActionForm) actionForm;

		FiltroEquipe filtroEquipe = new FiltroEquipe();

		Fachada fachada = Fachada.getInstancia();

		boolean peloMenosUmParametroInformado = false;
		
		// Verifica se o campo id foi informado
		if (pesquisarEquipeActionForm.getEquipeId() != null 
				&& !pesquisarEquipeActionForm.getEquipeId().trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroEquipe.adicionarParametro(new ParametroSimples(FiltroEquipe.ID, pesquisarEquipeActionForm.getEquipeId()));
		}
		// Verifica se o campo nome foi informado
		if (pesquisarEquipeActionForm.getNomeEquipe() != null 
				&& !pesquisarEquipeActionForm.getNomeEquipe().trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroEquipe.adicionarParametro(new ComparacaoTexto(FiltroEquipe.NOME, pesquisarEquipeActionForm.getNomeEquipe()));
		}
		// Verifica se o campo placaVeiculo foi informado
		if (pesquisarEquipeActionForm.getPlacaVeiculo() != null 
				&& !pesquisarEquipeActionForm.getPlacaVeiculo().trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroEquipe.adicionarParametro(new ComparacaoTexto(FiltroEquipe.PLACA_VEICULO,	pesquisarEquipeActionForm.getPlacaVeiculo()));
		}
		// Verifica se o campo cargaTrabalho foi informado
		if (pesquisarEquipeActionForm.getCargaTrabalhoDia() != null 
				&& !pesquisarEquipeActionForm.getCargaTrabalhoDia().trim().equals("")) {
			int cargaTrabalhoMinutos = new Integer(pesquisarEquipeActionForm.getCargaTrabalhoDia()).intValue()*60;
			Equipe equipe = new Equipe();
			equipe.setCargaTrabalho(cargaTrabalhoMinutos);
			fachada.validarInsercaoEquipe(equipe);
			peloMenosUmParametroInformado = true;
			
			filtroEquipe.adicionarParametro(new ParametroSimples(FiltroEquipe.CARGA_TRABALHO, cargaTrabalhoMinutos));
		}
		// Verifica se o campo unidadeOrganizacional foi informado
		if (pesquisarEquipeActionForm.getUnidadeOrganizacionalId() != null 
				&& !pesquisarEquipeActionForm.getUnidadeOrganizacionalId().trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroEquipe.adicionarParametro(new ParametroSimples(FiltroEquipe.ID_UNIDADE_ORGANIZACIONAL, pesquisarEquipeActionForm.getUnidadeOrganizacionalId()));
		}
		// Verifica se o campo perfilServico foi informado
		if (pesquisarEquipeActionForm.getTipoPerfilServicoId() != null 
				&& !pesquisarEquipeActionForm.getTipoPerfilServicoId().trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroEquipe.adicionarParametro(new ParametroSimples(FiltroEquipe.ID_SERVICO_PERFIL_TIPO, pesquisarEquipeActionForm.getTipoPerfilServicoId()));
		}
		// Erro caso o usu�rio mandou Pesquisar sem nenhum par�metro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException("atencao.filtro.nenhum_parametro_informado");
		}

		Collection colecaoEquipe = (Collection) fachada.pesquisar(filtroEquipe, Equipe.class.getName());
		if (colecaoEquipe == null || colecaoEquipe.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null,	"Equipe");
		} else {
			
			// Aciona o controle de pagina��o para que sejam pesquisados apenas
			// os registros que aparecem na p�gina
			Map resultado = controlarPaginacao(httpServletRequest, retorno,
													filtroEquipe, Equipe.class.getName());
			colecaoEquipe = (Collection) resultado.get("colecaoRetorno");
			retorno = (ActionForward) resultado.get("destinoActionForward");

			sessao.setAttribute("colecaoEquipe", colecaoEquipe);
		}
		

		return retorno;
	}

}
