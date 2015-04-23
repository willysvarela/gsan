package gsan.relatorio.cobranca;

import gsan.cadastro.sistemaparametro.FiltroSistemaParametro;
import gsan.cadastro.sistemaparametro.SistemaParametro;
import gsan.cobranca.FiltroDocumentosReceberFaixaDiasVencidos;
import gsan.fachada.Fachada;
import gsan.financeiro.FaixaDocumentosAReceber;
import gsan.gui.ActionServletException;
import gsan.relatorio.ConstantesRelatorios;
import gsan.relatorio.RelatorioDataSource;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.tarefa.TarefaException;
import gsan.tarefa.TarefaRelatorio;
import gsan.util.Util;
import gsan.util.agendadortarefas.AgendadorTarefas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RelatorioAnalisePerdasCreditos extends TarefaRelatorio {
	
	private static final long serialVersionUID = 1L;
	
	public RelatorioAnalisePerdasCreditos(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_ANALISE_PERDAS_CREDITOS);
	}

	@Override
	public Object executar() throws TarefaException {
		
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		String mesAno = (String) getParametro("mesAno");
		
		//valor de retorno
		byte[] retorno = null;

		Fachada fachada = Fachada.getInstancia();
		
		int maiorAnoMesExistente = fachada.maiorAnoMesReferenciaDocumentosAReceberResumo();
 
		
		// Par�metros do relat�rio
		Map parametros = new HashMap();
		
		// adiciona os par�metros do relat�rio 
		// adiciona o laudo da an�lise
		FiltroSistemaParametro filtroSistemaParametro = new FiltroSistemaParametro();
		Collection colecaoSistemaParametro = fachada.pesquisar(filtroSistemaParametro,SistemaParametro.class.getName());
		
		SistemaParametro sistemaParametro = (SistemaParametro) colecaoSistemaParametro.iterator().next();
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("mesAnoReferencia",mesAno.substring(4,6)+"/"+mesAno.substring(0,4));
		
		List<FaixaDocumentosAReceber> faixas = new ArrayList<FaixaDocumentosAReceber>();
		Collection colecao = fachada.pesquisar(new FiltroDocumentosReceberFaixaDiasVencidos(FiltroDocumentosReceberFaixaDiasVencidos.ID), FaixaDocumentosAReceber.class.getName());
		faixas.addAll(colecao);
		
		if(Integer.parseInt(mesAno) > maiorAnoMesExistente){
			throw new ActionServletException("atencao.mes.ano.superior");
		}
		
		if(faixas.size() != 3){
			throw new ActionServletException("atencao.conteudo.tabela.faixa.invalido");
		}
		
		Collection<RelatorioAnalisePerdasCreditosBean> colecaoBean = fachada.gerarRelatorioAnalisePerdasCreditos(mesAno);
		
		if(colecaoBean == null || colecaoBean.isEmpty()){
			throw new ActionServletException("atencao.pesquisa.sem.resultado");
		}
		
		RelatorioDataSource ds = new RelatorioDataSource((java.util.List)colecaoBean);
		
		retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_ANALISE_PERDAS_CREDITOS,
		parametros, ds, tipoFormatoRelatorio);
		
		// retorna o relat�rio gerado
		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 1;

		return retorno;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioAnalisePerdasCreditos",
				this);
	}
	
}
