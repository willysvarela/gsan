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
package gsan.gui.relatorio.micromedicao;

import gsan.cadastro.empresa.Empresa;
import gsan.cadastro.empresa.FiltroEmpresa;
import gsan.gui.micromedicao.leitura.FiltrarLeituristaActionForm;
import gsan.micromedicao.FiltroLeiturista;
import gsan.micromedicao.Leiturista;
import gsan.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gsan.relatorio.RelatorioVazioException;
import gsan.relatorio.micromedicao.RelatorioManterLeiturista;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.tarefa.TarefaRelatorio;
import gsan.util.SistemaException;
import gsan.util.Util;
import gsan.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Title: GCOM
 * Description: Sistema de Gest�o Comercial
 * Copyright: Copyright (c) 2004
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * 
 * @author Arthur Carvalho
 * @date 29/12/2009
 * @version 1.0
 */

public class GerarRelatorioLeituristaAction extends
		ExibidorProcessamentoTarefaRelatorio {

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param actionMapping
	 *            Descri��o do par�metro
	 * @param actionForm
	 *            Descri��o do par�metro
	 * @param httpServletRequest
	 *            Descri��o do par�metro
	 * @param httpServletResponse
	 *            Descri��o do par�metro
	 * @return Descri��o do retorno
	 */

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// cria a vari�vel de retorno
		ActionForward retorno = null;

		// Mudar isso quando tiver esquema de seguran�a
		HttpSession sessao = httpServletRequest.getSession(false);

		FiltrarLeituristaActionForm filtrarLeituristaActionForm = (FiltrarLeituristaActionForm) actionForm;

		FiltroLeiturista filtroLeiturista = (FiltroLeiturista) sessao
				.getAttribute("filtroLeiturista");

		// Inicio da parte que vai mandar os parametros para o relat�rio

		Leiturista leituristaParametros = new Leiturista();

		String idFuncionario = "";
		String descricaoFuncionario = "";
		// Id Funcionario
		if ( filtrarLeituristaActionForm.getIdFuncionario() != null &&
				!filtrarLeituristaActionForm.getIdFuncionario().equals("") ) {
			idFuncionario = filtrarLeituristaActionForm.getIdFuncionario();
			//Descricao do Funcionario
			if ( filtrarLeituristaActionForm.getDescricaoFuncionario() != null && 
					!filtrarLeituristaActionForm.getDescricaoFuncionario().equals("") ) {
				descricaoFuncionario = filtrarLeituristaActionForm.getDescricaoFuncionario();
			}
		}
		
		String descricaoEmpresa = "";
		//Descricao da Empresa
		if ( filtrarLeituristaActionForm.getEmpresaID() != null &&
				!filtrarLeituristaActionForm.getEmpresaID().equals("") ) {
			FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
			filtroEmpresa.adicionarParametro( new ParametroSimples ( FiltroEmpresa.ID,
					filtrarLeituristaActionForm.getEmpresaID()));
			
			Collection colecaoEmpresa = getFachada().pesquisar(filtroEmpresa, Empresa.class.getName());
			Empresa empresa = (Empresa) Util.retonarObjetoDeColecao(colecaoEmpresa);
			descricaoEmpresa = empresa.getDescricao();
		}
		
		String idCliente = "";
		String descricaoCliente = "";
		// Id Cliente
		if ( filtrarLeituristaActionForm.getIdCliente() != null &&
				!filtrarLeituristaActionForm.getIdCliente().equals("") ) {
			idCliente= filtrarLeituristaActionForm.getIdCliente();
			//Descricao do Cliente
			if ( filtrarLeituristaActionForm.getDescricaoCliente() != null && 
					!filtrarLeituristaActionForm.getDescricaoCliente().equals("") ) {
				descricaoCliente = filtrarLeituristaActionForm.getDescricaoCliente();
			}
		}

		//codigo ddd do municipio
		String ddd = "";
		if ( filtrarLeituristaActionForm.getDdd() != null &&
				!filtrarLeituristaActionForm.getDdd().equals("") ) {
			ddd = filtrarLeituristaActionForm.getDdd();
		}
		
		//numero do telefone 
		String numeroTelefone = "";
		if ( filtrarLeituristaActionForm.getTelefone() != null &&
				!filtrarLeituristaActionForm.getTelefone().equals("") ) {
			numeroTelefone = filtrarLeituristaActionForm.getTelefone();
		}
		
		//numero do IMEI 
		String numeroIMEI = "";
		if ( filtrarLeituristaActionForm.getImei() != null &&
				!filtrarLeituristaActionForm.getImei().equals("") ) {
			numeroIMEI = filtrarLeituristaActionForm.getImei();
			
			leituristaParametros.setNumeroImei(new Long(numeroIMEI));
		}
		
		String indicadorUso = "";
		if ( filtrarLeituristaActionForm.getIndicadorUso() != null && 
				!filtrarLeituristaActionForm.getIndicadorUso().equals("") ) {
			indicadorUso = filtrarLeituristaActionForm.getIndicadorUso();
		
			leituristaParametros.setIndicadorUso(new Short(indicadorUso));
		}
		
		// seta os parametros que ser�o mostrados no relat�rio

		leituristaParametros.setCodigoDDD(ddd);
		leituristaParametros.setNumeroFone(numeroTelefone);
		
		// Fim da parte que vai mandar os parametros para o relat�rio

		// cria uma inst�ncia da classe do relat�rio
		RelatorioManterLeiturista relatorioManterLeiturista = new RelatorioManterLeiturista(
				(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		
		relatorioManterLeiturista.addParametro("filtroLeiturista",
				filtroLeiturista);
		relatorioManterLeiturista.addParametro("leituristaParametros",
				leituristaParametros);
		relatorioManterLeiturista.addParametro("idFuncionario",
				idFuncionario);
		relatorioManterLeiturista.addParametro("descricaoFuncionario",
				descricaoFuncionario);
		relatorioManterLeiturista.addParametro("descricaoEmpresa",
				descricaoEmpresa);
		relatorioManterLeiturista.addParametro("idCliente",
				idCliente);
		relatorioManterLeiturista.addParametro("descricaoCliente",
				descricaoCliente);

		// chama o met�do de gerar relat�rio passando o c�digo da analise
		// como par�metro
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorioManterLeiturista.addParametro("tipoFormatoRelatorio", Integer
				.parseInt(tipoRelatorio));
		try {
			retorno = processarExibicaoRelatorio(relatorioManterLeiturista,
					tipoRelatorio, httpServletRequest, httpServletResponse,
					actionMapping);

		} catch (SistemaException ex) {
			// manda o erro para a p�gina no request atual
			reportarErros(httpServletRequest, "erro.sistema");

			// seta o mapeamento de retorno para a tela de erro de popup
			retorno = actionMapping.findForward("telaErroPopup");

		} catch (RelatorioVazioException ex1) {
			// manda o erro para a p�gina no request atual
			reportarErros(httpServletRequest, "erro.relatorio.vazio");

			// seta o mapeamento de retorno para a tela de aten��o de popup
			retorno = actionMapping.findForward("telaAtencaoPopup");
		}

		// devolve o mapeamento contido na vari�vel retorno
		return retorno;
	}

}