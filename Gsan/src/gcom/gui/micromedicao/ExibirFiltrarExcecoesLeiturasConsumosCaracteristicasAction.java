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
package gcom.gui.micromedicao;

import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.FiltroCategoria;
import gcom.cadastro.imovel.FiltroImovelPerfil;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.FiltroLigacaoTipo;
import gcom.micromedicao.consumo.LigacaoTipo;
import gcom.micromedicao.medicao.FiltroMedicaoTipo;
import gcom.micromedicao.medicao.MedicaoTipo;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Description of the Class
 * 
 * @author Roberta Costa
 * @created 28 de Dezembro de 2005
 */
public class ExibirFiltrarExcecoesLeiturasConsumosCaracteristicasAction extends GcomAction {
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
        //Seta o mapeamento de retorno
        ActionForward retorno = actionMapping.findForward("filtrarExcecoesLeiturasConsumosCaracteristica");

        //HttpSession sessao = httpServletRequest.getSession(false);

        Fachada fachada = Fachada.getInstancia();
        
        //LeituraConsumoActionForm leituraConsumoActionForm = (LeituraConsumoActionForm) actionForm;
        
        //Perfil Imovel
		FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();

		filtroImovelPerfil.adicionarParametro(new ParametroSimples(
				FiltroImovelPerfil.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
		Collection<ImovelPerfil> imovelPerfis = fachada.pesquisar(
				filtroImovelPerfil, ImovelPerfil.class.getName());
		if (imovelPerfis.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado");
		} else {
			httpServletRequest.setAttribute("imovelPerfis", imovelPerfis);
		}
		
//		 Categoria Imovel
		FiltroCategoria filtroCategoria = new FiltroCategoria();

		filtroCategoria.adicionarParametro(new ParametroSimples(
				FiltroCategoria.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
		Collection<Categoria> categorias = fachada.pesquisar(filtroCategoria,
				Categoria.class.getName());
		if (categorias.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado");
		} else {
			httpServletRequest.setAttribute("categorias", categorias);
		}

		// Tipo Medicao
		FiltroMedicaoTipo filtroMedicaoTipo = new FiltroMedicaoTipo();
		filtroMedicaoTipo.adicionarParametro(new ParametroSimples(
				FiltroMedicaoTipo.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
		Collection<MedicaoTipo> medicaoTipos = fachada.pesquisar(
				filtroMedicaoTipo, MedicaoTipo.class.getName());
		if (medicaoTipos.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado");
		} else {
			httpServletRequest.setAttribute("medicaoTipos", medicaoTipos);
		}

		// Tipo Ligacao
		FiltroLigacaoTipo filtroLigacaoTipo = new FiltroLigacaoTipo();
		filtroLigacaoTipo.adicionarParametro(new ParametroSimples(
				FiltroLigacaoTipo.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
		Collection<LigacaoTipo> ligacaoTipos = fachada.pesquisar(
				filtroLigacaoTipo, LigacaoTipo.class.getName());
		if (ligacaoTipos.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado");
		} else {
			httpServletRequest.setAttribute("ligacaoTipos", ligacaoTipos);
		}

        return retorno;
    }
}