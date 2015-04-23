package gsan.gui.cadastro.cliente;

import gsan.gui.GcomAction;
import gsan.util.ConstantesSistema;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * @author Fernando Fontelles Filho
 * @date 02/12/09
 */



public class ExibirFiltrarRamoAtividadeAction extends GcomAction{
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping.findForward("filtrarRamoAtividade");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		FiltrarRamoAtividadeActionForm form = (FiltrarRamoAtividadeActionForm) actionForm;
		
		//	C�digo para checar ou n�o o ATUALIZAR e Passar o valor do Indicador de Uso como "TODOS"
		
		String primeiraVez = httpServletRequest.getParameter("menu");
		if (primeiraVez != null && !primeiraVez.equals("")) {
			sessao.setAttribute("indicadorAtualizar", "1");
			form.setTipoPesquisa(ConstantesSistema.TIPO_PESQUISA_INICIAL.toString());
			
		}
		
		return retorno;
	}
	
}



