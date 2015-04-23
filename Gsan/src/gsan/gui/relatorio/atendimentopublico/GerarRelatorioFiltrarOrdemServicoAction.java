package gsan.gui.relatorio.atendimentopublico;

import gsan.atendimentopublico.ordemservico.bean.PesquisarOrdemServicoHelper;
import gsan.gui.ActionServletException;
import gsan.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gsan.relatorio.atendimentopublico.RelatorioFiltrarOrdemServico;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.tarefa.TarefaRelatorio;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class GerarRelatorioFiltrarOrdemServicoAction extends ExibidorProcessamentoTarefaRelatorio {
	
	@Override
	public ActionForward execute(
			ActionMapping mapping, 
			ActionForm actionForm, 
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception {

		Usuario usuario = (Usuario) request.getSession(false).getAttribute("usuarioLogado");
		HttpSession session = request.getSession();
		
		int tipoRelatorio = TarefaRelatorio.TIPO_PDF;
		try {
			tipoRelatorio = Integer.parseInt(request.getParameter("tipoRelatorio")); 
		} catch (NumberFormatException e) { }
		
		PesquisarOrdemServicoHelper pesquisarOrdemServicoHelper = (PesquisarOrdemServicoHelper) session.getAttribute("pesquisarOrdemServicoHelper");
		if (pesquisarOrdemServicoHelper == null) {
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}
		
		RelatorioFiltrarOrdemServico relatorio = new RelatorioFiltrarOrdemServico(usuario);
		relatorio.addParametro("pesquisarOrdemServicoHelper", pesquisarOrdemServicoHelper);
		relatorio.addParametro("tipoRelatorio", tipoRelatorio);
		
		return processarExibicaoRelatorio(
				relatorio, tipoRelatorio, request, response, mapping);
	}

}
