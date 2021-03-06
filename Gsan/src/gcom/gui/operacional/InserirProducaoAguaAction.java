/**
 * 
 */
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
package gcom.gui.operacional;

import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.Localidade;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.operacional.FiltroProducaoAgua;
import gcom.operacional.ProducaoAgua;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descri��o da classe
 * 
 * @author Vin�cius Medeiros
 * @date 09/06/2008
 */
public class InserirProducaoAguaAction extends GcomAction {

	/**
	 * Este caso de uso permite a inclus�o de uma Producao de Agua
	 * 
	 * [UC0812] Inserir Producao de Agua
	 * 
	 * 
	 * @author Vin�cius Medeiros
	 * @date 09/06/2008
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o caminho de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		InserirProducaoAguaActionForm inserirProducaoAguaActionForm = (InserirProducaoAguaActionForm) actionForm;

		// Mudar isso quando for implementado o esquema de seguran�a	
		HttpSession sessao = httpServletRequest.getSession(false);

		// Obt�m a inst�ncia da fachada
		Fachada fachada = Fachada.getInstancia();

		String anoMesReferencia = inserirProducaoAguaActionForm
				.getAnoMesReferencia();
		String localidadeID = inserirProducaoAguaActionForm.getLocalidadeID();
		String volumeProduzido = inserirProducaoAguaActionForm
				.getVolumeProduzido();
		
		ProducaoAgua producaoAgua = new ProducaoAgua();
		Collection colecaoPesquisa = null;
		Collection colecaoPesquisa2 = null;

		String mes = anoMesReferencia.substring(0, 2);
		String ano = anoMesReferencia.substring(3, 7);

		anoMesReferencia = ano + "" + mes;
		producaoAgua.setAnoMesReferencia(new Integer(anoMesReferencia));

		// Localidade
		if (localidadeID != null && !localidadeID.equalsIgnoreCase("")) {

			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.ID, localidadeID));

			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.INDICADORUSO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			// Retorna Localidade
			colecaoPesquisa = fachada.pesquisar(filtroLocalidade,
					Localidade.class.getName());

			if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
				
				throw new ActionServletException(
						"atencao.pesquisa.localidade_inexistente");
			
			} else {
			
				Localidade objetoLocalidade = (Localidade) Util
						.retonarObjetoDeColecao(colecaoPesquisa);
				producaoAgua.setLocalidade(objetoLocalidade);
			
			}
		}
		
		// Volume Produzido
		if (!"".equals(inserirProducaoAguaActionForm.getVolumeProduzido())) {
			
			producaoAgua.setVolumeProduzido(Util.formatarMoedaRealparaBigDecimal(volumeProduzido));
		
		} else {	
		
			throw new ActionServletException("atencao.required", null,
						"Volume Produzido");

		}
	
		producaoAgua.setUltimaAlteracao(new Date());

		FiltroProducaoAgua filtroProducaoAgua = new FiltroProducaoAgua();

		filtroProducaoAgua.adicionarParametro(
				new ParametroSimples(FiltroProducaoAgua.MES_ANO_REFERENCIA, 
						producaoAgua.getAnoMesReferencia()));
		
		filtroProducaoAgua.adicionarParametro(
				new ParametroSimples(FiltroProducaoAgua.LOCALIDADE, 
						producaoAgua.getLocalidade()));
		
		filtroProducaoAgua.adicionarParametro(
				new ParametroSimples(FiltroProducaoAgua.VOLUME_PRODUZIDO, 
						producaoAgua.getVolumeProduzido()));

		colecaoPesquisa2 = (Collection) fachada.pesquisar(
				filtroProducaoAgua,ProducaoAgua.class.getName());

		if (colecaoPesquisa2 != null && !colecaoPesquisa2.isEmpty()) {
			// Caso j� tenha sido cadastrada a Produ��o de �gua 
			throw new ActionServletException(
					"atencao.producao_agua_ja_cadastrada");
		
		} else {
		
			producaoAgua.setVolumeProduzido(Util.formatarMoedaRealparaBigDecimal(volumeProduzido));

			Integer idProducaoAgua = (Integer) fachada.inserir(producaoAgua);

			montarPaginaSucesso(httpServletRequest, "Produ��o de �gua " + idProducaoAgua
					+ " inserido com sucesso.",
					"Inserir outra Produ��o de �gua",
					"exibirInserirProducaoAguaAction.do?menu=sim",
					"exibirAtualizarProducaoAguaAction.do?idRegistroAtualizacao="
							+ idProducaoAgua,
					"Atualizar Produ��o de �gua Inserida");

			sessao.removeAttribute("InserirProducaoAguaActionForm");
		}

		return retorno;
	}
}
