/*
* Copyright (C) 2007-2007 the GSAN � Sistema Integrado de Gest�o de Servi�os de Saneamento
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
* Foundation, Inc., 59 Temple Place � Suite 330, Boston, MA 02111-1307, USA
*/

/*
* GSAN � Sistema Integrado de Gest�o de Servi�os de Saneamento
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
package gsan.gui.relatorio.atendimentopublico.ordemservico;

import gsan.gui.ActionServletException;
import gsan.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gsan.relatorio.RelatorioVazioException;
import gsan.relatorio.atendimentopublico.ordemservico.FiltrarBoletimCustoPavimentoHelper;
import gsan.relatorio.atendimentopublico.ordemservico.RelatorioBoletimCustoPavimento;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.tarefa.TarefaRelatorio;
import gsan.util.SistemaException;
import gsan.util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1109] Filtrar Dados para Gera��o Boletim de Custo de Repavimenta��o
 * 
 * @author Hugo Leonardo
 *
 * @date 03/01/2011
 */

public class GerarRelatorioBoletimCustoRepavimentacaoPorTipoPavimentoAction extends ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = null;
		   
		httpServletRequest.setAttribute("telaSucessoRelatorio",true);
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		   
		// Form
		GerarRelatorioBoletimCustoRepavimentacaoPorTipoPavimentoForm form = (GerarRelatorioBoletimCustoRepavimentacaoPorTipoPavimentoForm) actionForm;
		
		FiltrarBoletimCustoPavimentoHelper helper = new FiltrarBoletimCustoPavimentoHelper();
		
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		
		boolean peloMenosUmParametroInformado = false;
		
		// Ano M�s Refer�ncia Gera��o
		if ( form.getMesAnoReferenciaGeracao() != null && 
				!form.getMesAnoReferenciaGeracao().equals("")) {
			
			String anoMesGeracao = Util.formatarMesAnoParaAnoMesSemBarra(form.getMesAnoReferenciaGeracao());
			helper.setMesAnoReferenciaGeracao(anoMesGeracao);
			peloMenosUmParametroInformado = true;
		}
		
		// Unidade Repavimentadora
		if(form.getIdUnidadeRepavimentadora() != null && !form.getIdUnidadeRepavimentadora().equals("-1")){
			
			helper.setIdUnidadeRepavimentadora(form.getIdUnidadeRepavimentadora());
			peloMenosUmParametroInformado = true;
		}
		
		// Tipo de Boletim
		if(form.getIndicadorTipoBoletim() != null && 
				!form.getIndicadorTipoBoletim().equals("")){
			
			helper.setIndicadorTipoBoletim(form.getIndicadorTipoBoletim());
			peloMenosUmParametroInformado = true;
		}

		// Erro caso o usu�rio mandou filtrar sem nenhum par�metro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}
		 
		TarefaRelatorio relatorio = new RelatorioBoletimCustoPavimento((Usuario)
				(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));

		if (tipoRelatorio  == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}
		
		relatorio.addParametro("tipoFormatoRelatorio",Integer.parseInt(tipoRelatorio));
		
		relatorio.addParametro("filtrarBoletimCustoPavimentoHelper", helper);
		
		relatorio.addParametro("mesAnoGeracao", form.getMesAnoReferenciaGeracao());
		
		relatorio.addParametro("usuario", usuario);
		
		String mesAno1Anterior = Util.somaMesMesAnoComBarra(form.getMesAnoReferenciaGeracao(), -1);
		String mesAno2Anterior = Util.somaMesMesAnoComBarra(form.getMesAnoReferenciaGeracao(), -2);
		String mesAno3Anterior = Util.somaMesMesAnoComBarra(form.getMesAnoReferenciaGeracao(), -3);
		
		String mesAnoAnteriores = ""+mesAno3Anterior + " - " + mesAno2Anterior + " - " + mesAno1Anterior;
		
		relatorio.addParametro("mesAnoAnteriores", mesAnoAnteriores);
		
		try {	
			
			retorno = processarExibicaoRelatorio(relatorio, tipoRelatorio, httpServletRequest, 
						httpServletResponse, actionMapping);
	
		} catch (SistemaException ex) {
			// manda o erro para a p�gina no request atual
			reportarErros(httpServletRequest, "erro.sistema");
	
			// seta o mapeamento de retorno para a tela de erro de popup
			retorno = actionMapping.findForward("telaErroPopup");
	
		} catch (RelatorioVazioException ex1) {
			throw new ActionServletException("atencao.pesquisa.nenhumresultado", null, "");
		}
			
			return retorno;
		}
	
}