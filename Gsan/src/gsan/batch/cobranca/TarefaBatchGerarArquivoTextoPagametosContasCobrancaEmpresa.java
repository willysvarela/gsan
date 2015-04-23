package gsan.batch.cobranca;

import gsan.cadastro.localidade.UnidadeNegocio;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.tarefa.TarefaBatch;
import gsan.tarefa.TarefaException;
import gsan.util.ConstantesJNDI;
import gsan.util.ConstantesSistema;
import gsan.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;
import java.util.Iterator;

public class TarefaBatchGerarArquivoTextoPagametosContasCobrancaEmpresa extends
		TarefaBatch {

	private static final long serialVersionUID = 1L;

	public TarefaBatchGerarArquivoTextoPagametosContasCobrancaEmpresa(
			Usuario usuario, int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchGerarArquivoTextoPagametosContasCobrancaEmpresa() {
		super(null, 0);
	}

	@Override
	protected Collection<Object> pesquisarTodasUnidadeProcessamentoBatch() {
		return null;
	}

	@Override
	protected Collection<Object> pesquisarTodasUnidadeProcessamentoReinicioBatch() {
		return null;
	}

	@Override
	public Object executar() throws TarefaException {

		Integer referenciaInicial = (Integer) getParametro("referenciaInicial");
		Integer referenciaFinal = (Integer) getParametro("referenciaFinal");
		Integer idEmpresa = (Integer) getParametro("idEmpresa");
		Collection colecaoUnidadeNegocio = (Collection) getParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH);

		Iterator it = colecaoUnidadeNegocio.iterator();

		while (it.hasNext()) {

			UnidadeNegocio unidadeNegocio = (UnidadeNegocio) it.next();

			enviarMensagemControladorBatch(
					ConstantesJNDI.BATCH_GERAR_ARQUIVO_TEXTO_PAGAMENTOS_CONTAS_COBRANCA_POR_EMPRESA,
					new Object[] { idEmpresa, referenciaInicial,
							referenciaFinal, this.getIdFuncionalidadeIniciada(), unidadeNegocio.getId() });

		}
		return null;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa(
				"TarefaBatchGerarArquivoTextoPagametosContasCobrancaEmpresa",
				this);

	}

}
