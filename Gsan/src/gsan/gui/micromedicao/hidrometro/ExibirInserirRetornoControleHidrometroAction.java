package gsan.gui.micromedicao.hidrometro;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gsan.gui.GcomAction;

/**
 * @author Wallace Thierre
 * @created 29/07/2010
 */
public class ExibirInserirRetornoControleHidrometroAction extends GcomAction {

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
			ActionForm actionForm,	HttpServletRequest httpServletRequest, 
			HttpServletResponse httpServletResponse){

		//Seta o caminho de Retorno
		ActionForward retorno = actionMapping.findForward("exibirInserirRetornoControleHidrometro");

	
		return retorno;



	}

}
