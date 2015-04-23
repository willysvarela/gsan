package gsan.batch.cobranca;

import gsan.seguranca.acesso.usuario.Usuario;
import gsan.tarefa.TarefaBatch;
import gsan.tarefa.TarefaException;
import gsan.util.ConstantesJNDI;
import gsan.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;

/**
 * [UC1588] Gerar Divida Ativa
 * 
 * @author Ana Maria
 * @date 20/02/2014
 *
 */
public class TarefaBatchGerarDividaAtiva extends TarefaBatch {

	private static final long serialVersionUID = 1L;

	@Deprecated
	public TarefaBatchGerarDividaAtiva(){
		super(null, 0);
	}
	
	public TarefaBatchGerarDividaAtiva(
			Usuario usuario, int idFuncionalidadeIniciada) {
		super(usuario, idFuncionalidadeIniciada);
	}
	
	@Override
	public Object executar() throws TarefaException {

			enviarMensagemControladorBatch(
                ConstantesJNDI.BATCH_GERAR_DIVIDA_ATIVA,
                new Object[] {this.getIdFuncionalidadeIniciada(), this.getUsuario()});
			
			return null;
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
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("BaixaGerarDividaAtivaBatch", this);
	}

}
