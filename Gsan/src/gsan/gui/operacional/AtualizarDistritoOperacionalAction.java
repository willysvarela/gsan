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
package gsan.gui.operacional;
import gsan.fachada.Fachada;
import gsan.gui.GcomAction;
import gsan.operacional.DistritoOperacional;
import gsan.operacional.SetorAbastecimento;
import gsan.seguranca.acesso.usuario.Usuario;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;



/**
 * [UC0522] Manter Distrito Operacional [SB0001]Atualizar Distrito Operacional
 *
 * @author Eduardo Bianchi
 * @date 09/02/2007
 */


public class AtualizarDistritoOperacionalAction extends GcomAction{
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		AtualizarDistritoOperacionalActionForm atualizarDistritoOperacionalActionForm = (AtualizarDistritoOperacionalActionForm) actionForm;
		
		Fachada fachada = Fachada.getInstancia();
		
		HttpSession sessao = httpServletRequest.getSession(false);		
		
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
		
		DistritoOperacional distritoOperacional = (DistritoOperacional) sessao.getAttribute("distritoOperacional");
		
		String[] idDistritoOperacional = new String[1];
		
		idDistritoOperacional[0] = distritoOperacional.getId().toString();
		
		//Atualiza a entidade com os valores do formul�rio		
		distritoOperacional.setDescricao(atualizarDistritoOperacionalActionForm.getDescricao());
		distritoOperacional.setDescricaoAbreviada(atualizarDistritoOperacionalActionForm.getDescricaoAbreviada());
		distritoOperacional.setId(new Integer(atualizarDistritoOperacionalActionForm.getCodigoDistritoOperacional()));
		distritoOperacional.setIndicadorUso(new Short(atualizarDistritoOperacionalActionForm.getIndicadorUso()));
		SetorAbastecimento setorAbastecimento = new SetorAbastecimento();
		setorAbastecimento.setId(new Integer(atualizarDistritoOperacionalActionForm.getSetorAbastecimento()));
		distritoOperacional.setSetorAbastecimento(setorAbastecimento);
		//distritoOperacional.setUltimaAlteracao(Util.converteStringParaDateHora(atualizarDistritoOperacionalActionForm.getUltimaAlteracao()));
				
		//atualiza na base de dados de Municipio
		 fachada.atualizarDistritoOperacional(distritoOperacional,usuarioLogado);
		
		//Monta a p�gina de sucesso
		montarPaginaSucesso(httpServletRequest, "Distrito Operacional de c�digo " + idDistritoOperacional[0] + " atualizado com sucesso.", "Realizar outra Manuten��o de Distrito Operacional",
				"exibirFiltrarDistritoOperacionalAction.do?menu=sim");
		
		return retorno;
	}
	
}	      
    



