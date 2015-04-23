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
 * Magno Jean Gouveia Silveira
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
 * Deigo Maciel
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
package gsan.gui.micromedicao.leitura;

import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.micromedicao.leitura.FiltroLeituraAnormalidade;
import gsan.micromedicao.leitura.LeituraAnormalidade;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * /**
 * <p>
 * <b>[UC0191]</b> Manter Anormalidade de Leitura
 * </p>
 * <p>
 * Esta funcionalidade permite atualizar uma Anormalidade de Leitura
 * </p>
 * 
 * @author Thiago Ten�rio
 * @since 31/10/2006
 */
public class ExibirManterAnormalidadeLeituraAction extends GcomAction {

	/**
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("exibirManterAnormalidadeLeitura");

		HttpSession sessao = httpServletRequest.getSession(false);

		/*
		 * Recupera o filtro passado pelo FiltrarResolucaoDiretoriaAction para
		 * ser efetuada pesquisa
		 */
		FiltroLeituraAnormalidade filtroLeituraAnormalidade = (FiltroLeituraAnormalidade) sessao.getAttribute("filtroLeituraAnormalidade");

		/*
		 * Aciona o controle de pagina��o para que sejam pesquisados apenas os
		 * registros que aparecem na p�gina
		 */
		Collection colecaoAnormalidadeLeitura = new ArrayList();
		if (filtroLeituraAnormalidade != null && !filtroLeituraAnormalidade.equals("")) {
			Map resultado = controlarPaginacao(httpServletRequest, retorno, filtroLeituraAnormalidade, LeituraAnormalidade.class.getName());
			colecaoAnormalidadeLeitura = (Collection) resultado.get("colecaoRetorno");
			retorno = (ActionForward) resultado.get("destinoActionForward");
		}
		/*
		 * Verifica se a cole��o retornada pela pesquisa � nula, em caso
		 * afirmativo comunica ao usu�rio que n�o existe nenhuma resolu��o de
		 * diretoria cadastrado para a pesquisa efetuada e em caso negativo e se
		 * atender a algumas condi��es seta o retorno para o
		 * ExibirAtualizarResolucaoDiretoriaAction, se n�o atender manda a
		 * cole��o pelo request para ser recuperado e exibido pelo jsp.
		 */
		if (colecaoAnormalidadeLeitura != null && !colecaoAnormalidadeLeitura.isEmpty()) {

			/*
			 * Verifica se a cole��o cont�m apenas um objeto, se est� retornando
			 * da pagina��o (devido ao esquema de pagina��o de 10 em 10 faz uma
			 * nova busca), evitando, assim, que caso haja 11 elementos no
			 * retorno da pesquisa e o usu�rio selecione o link para ir para a
			 * segunda p�gina ele n�o v� para tela de atualizar.
			 */
			if (colecaoAnormalidadeLeitura.size() == 1
					&& (httpServletRequest.getParameter("page.offset") == null || httpServletRequest.getParameter("page.offset").equals("1"))) {
				/*
				 * Verifica se o usu�rio marcou o checkbox de atualizar no jsp
				 * resolucao_diretoria_filtrar. Caso todas as condi��es sejam
				 * verdadeiras seta o retorno para o
				 * ExibirAtualizarResolucaoDiretoriaAction e em caso negativo
				 * manda a cole��o pelo request.
				 */
				if (httpServletRequest.getParameter("indicadorAtualizar") != null) {
					retorno = actionMapping.findForward("exibirAtualizarAnormalidadeLeitura");
					LeituraAnormalidade leituraAnormalidade = (LeituraAnormalidade) colecaoAnormalidadeLeitura.iterator().next();
					sessao.setAttribute("leituraAnormalidade", leituraAnormalidade);
					sessao.removeAttribute("manter");
				} else {
					httpServletRequest.setAttribute("colecaoAnormalidadeLeitura", colecaoAnormalidadeLeitura);
					sessao.setAttribute("colecaoAnormalidadeLeitura", colecaoAnormalidadeLeitura);
					sessao.removeAttribute("manter");
				}
			} else {
				httpServletRequest.setAttribute("colecaoAnormalidadeLeitura", colecaoAnormalidadeLeitura);
				sessao.setAttribute("colecaoAnormalidadeLeitura", colecaoAnormalidadeLeitura);
				sessao.setAttribute("manter", true);
			}
		} else {
			// Caso a pesquisa n�o retorne nenhum objeto comunica ao usu�rio;
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}

		return retorno;

	}

}
