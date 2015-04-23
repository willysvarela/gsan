
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

import gsan.cadastro.imovel.FiltroFonteAbastecimento;
import gsan.cadastro.imovel.FonteAbastecimento;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.util.filtro.ParametroSimples;
import gsan.util.filtro.ParametroSimplesDiferenteDe;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AtualizarFonteAbastecimentoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		AtualizarFonteAbastecimentoActionForm atualizarFonteAbastecimentoActionForm = (AtualizarFonteAbastecimentoActionForm) actionForm;

		FonteAbastecimento fonteAbastecimento= (FonteAbastecimento) sessao.getAttribute("atualizarFonteAbastecimento");
		
		Collection colecaoPesquisa = null;
		
		if(atualizarFonteAbastecimentoActionForm.getCodigo()!= null 
				&& !atualizarFonteAbastecimentoActionForm.getCodigo().equals("")){
			fonteAbastecimento.setId(new Integer(atualizarFonteAbastecimentoActionForm.getCodigo()));
		}else{
			fonteAbastecimento.setId(null);
		}
		fonteAbastecimento.setDescricao(atualizarFonteAbastecimentoActionForm.getDescricao());
		fonteAbastecimento.setDescricaoAbreviada(atualizarFonteAbastecimentoActionForm.getDescricaoAbreviada());
		fonteAbastecimento.setIndicadorUso(new Short (atualizarFonteAbastecimentoActionForm.getIndicadorUso()));
		fonteAbastecimento.setIndicadorCalcularVolumeFixo(atualizarFonteAbastecimentoActionForm.getIndicadorCalcularVolumeFixo());
		fonteAbastecimento.setIndicadorPermitePoco(atualizarFonteAbastecimentoActionForm.getIndicadorPermitePoco());
		
		String codigoFonteAbastecimento= atualizarFonteAbastecimentoActionForm.getCodigo();		
        String descricaoFonteAbastecimento = atualizarFonteAbastecimentoActionForm.getDescricao();
        String descricaoAbreviadaFonteAbastecimento = atualizarFonteAbastecimentoActionForm.getDescricaoAbreviada();
        Short indicadorUsoFonteAbastecimento  = atualizarFonteAbastecimentoActionForm.getIndicadorUso();
		
        fonteAbastecimento.setDescricao(descricaoFonteAbastecimento);
        fonteAbastecimento.setDescricaoAbreviada(descricaoAbreviadaFonteAbastecimento);
       
        if(codigoFonteAbastecimento != null && !codigoFonteAbastecimento.equals("")){
        	fonteAbastecimento.setId(new Integer(codigoFonteAbastecimento));
        }else{
        	fonteAbastecimento.setId(null);
        }
        
        fonteAbastecimento.setIndicadorUso(new Short(indicadorUsoFonteAbastecimento));

        fonteAbastecimento.setUltimaAlteracao(new Date());
        

        FiltroFonteAbastecimento filtroFonteAbas= new FiltroFonteAbastecimento();
			filtroFonteAbas.adicionarParametro(
				new ParametroSimples(FiltroFonteAbastecimento.DESCRICAO, descricaoFonteAbastecimento));
			filtroFonteAbas.adicionarParametro(new ParametroSimplesDiferenteDe(FiltroFonteAbastecimento.ID, fonteAbastecimento.getId()));
		
		colecaoPesquisa = (Collection)
		this.getFachada().pesquisar(filtroFonteAbas, FonteAbastecimento.class.getName());
				
		if( colecaoPesquisa !=null && !colecaoPesquisa.isEmpty()){
			throw new ActionServletException("atencao.descricao_existente", null, descricaoFonteAbastecimento);
		}
		
		
		FiltroFonteAbastecimento filtroFonteAbastecimento = new FiltroFonteAbastecimento();
			filtroFonteAbastecimento.adicionarParametro(
					new ParametroSimples(FiltroFonteAbastecimento.ID, codigoFonteAbastecimento));
			filtroFonteAbastecimento.adicionarParametro(new ParametroSimplesDiferenteDe(FiltroFonteAbastecimento.ID, fonteAbastecimento.getId()));
			
		colecaoPesquisa = (Collection) 
			this.getFachada().pesquisar(filtroFonteAbastecimento, FonteAbastecimento.class.getName());
		
	
		if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()) {
			throw new ActionServletException("atencao.codigo_existente", null, codigoFonteAbastecimento+"");
		}
		
		fachada.atualizar(fonteAbastecimento);

		
		montarPaginaSucesso(httpServletRequest, "Fonte de Abastecimento"
				+ descricaoFonteAbastecimento + " atualizado com sucesso.",
				"Realizar outra Manuten��o da Fonte de Abastecimento",
				"exibirFiltrarFonteAbastecimentoAction.do?menu=sim");        
        
		return retorno;
	}
}