package gsan.gui.atendimentopublico.ordemservico;

import gsan.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento;
import gsan.atendimentopublico.registroatendimento.FiltroAtendimentoMotivoEncerramento;
import gsan.fachada.Fachada;
import gsan.gui.GcomAction;
import gsan.util.ConstantesSistema;
import gsan.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirPesquisarServicoTipoMotivoEncerramentoAction extends
		GcomAction {

	/**
	 * 
	 * 
	 * [UC0410] Pesquisar o motivo de encerramento de servi�o tipo.
	 * 
	 * @author Nathalia Santos
	 * @date 20/07/2011
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
		HttpSession sessao = httpServletRequest.getSession(false);
		Fachada fachada = Fachada.getInstancia();
		ActionForward retorno = actionMapping
				.findForward("exibirServicoTipoMotivoEncerramentoPopup");

 		FiltroAtendimentoMotivoEncerramento filtroAtendimentoMotivoEncerramento = new FiltroAtendimentoMotivoEncerramento();
 		filtroAtendimentoMotivoEncerramento.adicionarParametro(new ParametroSimples(FiltroAtendimentoMotivoEncerramento.INDICADOR_USO, ConstantesSistema.SIM));
		Collection<AtendimentoMotivoEncerramento> motivosEncerramentos = fachada.pesquisar(filtroAtendimentoMotivoEncerramento, AtendimentoMotivoEncerramento.class.getName());
		
		sessao.setAttribute("collMotivosEncerramentos",motivosEncerramentos);
		
		return retorno;
	}

}
