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
package gsan.gui.cadastro;


import gsan.cadastro.empresa.Empresa;
import gsan.cadastro.empresa.FiltroEmpresa;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.filtro.ComparacaoTexto;
import gsan.util.filtro.ComparacaoTextoCompleto;
import gsan.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0774]FILTRAR Situacao de Pagamento
 * 
 * @author Arthur Carvalho
 * @date 08/05/08
 */

public class FiltrarEmpresaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirManterEmpresa");

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		FiltrarEmpresaActionForm filtrarEmpresaActionForm = (FiltrarEmpresaActionForm) actionForm;

		FiltroEmpresa filtroEmpresa = new FiltroEmpresa();

		boolean peloMenosUmParametroInformado = false;

		String id = filtrarEmpresaActionForm.getId();
		String descricao = filtrarEmpresaActionForm.getDescricao();
		String descricaoAbreviada = filtrarEmpresaActionForm.getDescricaoAbreviada();
		String email = filtrarEmpresaActionForm.getEmail();
		String indicadorEmpresaPrincipal = filtrarEmpresaActionForm.getIndicadorEmpresaPrincipal();	
		String indicadorUso = filtrarEmpresaActionForm.getIndicadorUso();
		String tipoPesquisa = filtrarEmpresaActionForm.getTipoPesquisa();

		
		// ID
		if (id != null && !id.trim().equals("")) {
				peloMenosUmParametroInformado = true;
				filtroEmpresa.adicionarParametro(new ParametroSimples(
						FiltroEmpresa.ID, id));
		}
		// Descricao
		if (descricao != null && !descricao.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			if (tipoPesquisa != null
					&& tipoPesquisa
							.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA
									.toString())) {

				filtroEmpresa
						.adicionarParametro(new ComparacaoTextoCompleto(
								FiltroEmpresa.DESCRICAO, descricao));
			} else {

				filtroEmpresa.adicionarParametro(new ComparacaoTexto(
						FiltroEmpresa.DESCRICAO, descricao));
			}
		}
		
		// Descricao Abreviada
		if (descricaoAbreviada != null
				&& !descricaoAbreviada.trim().equalsIgnoreCase("")) {

			peloMenosUmParametroInformado = true;
			filtroEmpresa
					.adicionarParametro(new ComparacaoTextoCompleto(
							FiltroEmpresa.DESCRICAO_ABREVIADA, descricaoAbreviada));
		} 
		//	E-mail
		if (email != null
				&& !email.trim().equalsIgnoreCase("")) {

			peloMenosUmParametroInformado = true;
			filtroEmpresa
					.adicionarParametro(new ComparacaoTextoCompleto(
							FiltroEmpresa.EMAIL,
							email));
		}
		
		//	Indicador Empresa Principal
		if (indicadorEmpresaPrincipal != null
					&& !indicadorEmpresaPrincipal.trim().equals("")) {

				peloMenosUmParametroInformado = true;
				filtroEmpresa
						.adicionarParametro(new ComparacaoTextoCompleto(
								FiltroEmpresa.INDICADOR_EMPRESA_PRINCIPAL,
								indicadorEmpresaPrincipal));

		}	
		// Indicador uso
		if (indicadorUso != null && !indicadorUso.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroEmpresa.adicionarParametro(new ParametroSimples(
					FiltroEmpresa.INDICADORUSO, indicadorUso));
		}
		
		Collection <Empresa> colecaoEmpresa = fachada
				.pesquisar(filtroEmpresa, Empresa.class
						.getName());

		// Verificar a existencia de um Grupo de cadastro
		if (colecaoEmpresa.isEmpty()) {
			throw new ActionServletException(
					"atencao.pesquisa_inexistente", null,"Empresa");
		}

		// Filtragem sem parametros
		if (!peloMenosUmParametroInformado == true) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");

		}
		
		// Pesquisa sem registros

		if (colecaoEmpresa == null
				|| colecaoEmpresa.isEmpty()) {
			throw new ActionServletException(
					"atencao.pesquisa.nenhumresultado", null, "Empresa");
		} else {
			httpServletRequest.setAttribute("colecaoEmpresa",
					colecaoEmpresa);
			Empresa Empresa = new Empresa();
			Empresa = (Empresa) Util
					.retonarObjetoDeColecao(colecaoEmpresa);
			String idRegistroAtualizar = Empresa.getId().toString();
			sessao.setAttribute("idRegistroAtualizar", idRegistroAtualizar);
		}

		sessao.setAttribute("filtroEmpresa", filtroEmpresa);

		httpServletRequest.setAttribute("filtroEmpresa",
				filtroEmpresa);

		
		return retorno;
	}
}
