package gcom.gui.portal.caema;

import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * [UC1300] Verificar Autenticidade da Certid�o Negativa de D�bito
 * 
 * @author Mariana Victor
 * @date 15/03/2012
 */
public class ExibirValidarCertidaoNegativaDebitoPortalCaemaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirValidarCertidaoNegativaDebitoPortalCaemaAction");
		
		httpServletRequest.setAttribute("voltarInformacoes", true);
		
		ValidarCertidaoNegativaDebitoPortalCaemaActionForm 
			form = (ValidarCertidaoNegativaDebitoPortalCaemaActionForm) actionForm;
		
		form.setMatriculaImovel("");
		form.setNumeroAutenticacao("");
		
		return retorno;
	}
}
