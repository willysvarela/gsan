package gsan.gui.cadastro;

import gsan.cadastro.empresa.Empresa;
import gsan.cadastro.empresa.FiltroEmpresa;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.util.ConstantesSistema;
import gsan.util.filtro.ComparacaoTexto;
import gsan.util.filtro.ComparacaoTextoCompleto;
import gsan.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0777] Pesquisar Empresa
 * 
 * @see gsan.gui.cadastro.ExibirPesquisarEmpresaAction
 * @see gsan.gui.cadastro.PesquisarEmpresaActionForm
 * 
 * @author Victor Cisneiros
 * @date 19/05/2008
 */
public class PesquisarEmpresaAction extends GcomAction {
	
	public ActionForward execute(
			ActionMapping mapping, 
			ActionForm form, 
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		ActionForward retorno = mapping.findForward("exibirResultadoPesquisaEmpresa");
		
		PesquisarEmpresaActionForm aform = (PesquisarEmpresaActionForm) form;
		String idEmpresa = aform.getIdEmpresa();
		String nomeEmpresa = aform.getDescricaoEmpresa();
		String tipoPesquisa = aform.getTipoPesquisa();
		
		Fachada fachada = Fachada.getInstancia();
		FiltroEmpresa filtro = new FiltroEmpresa(FiltroEmpresa.DESCRICAO);
		
		boolean peloMenosUmParametroInformado = false;
		
		if (idEmpresa != null && !idEmpresa.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			
			filtro.adicionarParametro(new ParametroSimples(
				FiltroEmpresa.ID, new Integer(idEmpresa)));
		}
		
		if (nomeEmpresa != null && !nomeEmpresa.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			
			if (tipoPesquisa != null && 
				tipoPesquisa.equals(String.valueOf(ConstantesSistema.TIPO_PESQUISA_COMPLETA)) ) {
				filtro.adicionarParametro(new ComparacaoTextoCompleto(
						FiltroEmpresa.DESCRICAO, nomeEmpresa));
			} else {
				filtro.adicionarParametro(new ComparacaoTexto(
						FiltroEmpresa.DESCRICAO, nomeEmpresa));
			}
		}
		
        if (!peloMenosUmParametroInformado) {
            throw new ActionServletException("atencao.filtro.nenhum_parametro_informado");
        }
        
        String className = Empresa.class.getName();
        
        Collection empresas = fachada.pesquisar(filtro, Empresa.class.getName()); 
        
        if (empresas != null && !empresas.isEmpty()) {
        	Map resultado = controlarPaginacao(request, retorno, filtro, className);
        	
        	empresas = (Collection) resultado.get("colecaoRetorno");
        	retorno = (ActionForward) resultado.get("destinoActionForward");
        	
        	request.getSession().setAttribute("empresas", empresas);
        } else {
            throw new ActionServletException("atencao.pesquisa.nenhumresultado", null, "empresa");
        }
		
		return retorno;
	}

}
