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
package gsan.relatorio.micromedicao;

import gsan.cadastro.sistemaparametro.SistemaParametro;
import gsan.fachada.Fachada;
import gsan.micromedicao.hidrometro.FiltroHidrometroLocalArmazenagem;
import gsan.micromedicao.hidrometro.HidrometroLocalArmazenagem;
import gsan.relatorio.ConstantesRelatorios;
import gsan.relatorio.RelatorioDataSource;
import gsan.relatorio.RelatorioVazioException;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.tarefa.TarefaException;
import gsan.tarefa.TarefaRelatorio;
import gsan.util.ConstantesSistema;
import gsan.util.agendadortarefas.AgendadorTarefas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * Title: GCOM
 * </p>
 * <p>
 * Description: Sistema de Gest�o Comercial
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * </p>
 * 
 * @author Arthur Carvalho
 * @version 1.0
 */

public class RelatorioManterLocalArmazenagemHidrometro extends TarefaRelatorio {
	private static final long serialVersionUID = 1L;
	public RelatorioManterLocalArmazenagemHidrometro(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_LOCAL_ARMAZENAGEM_HIDROMETRO_MANTER);
	}
	
	@Deprecated
	public RelatorioManterLocalArmazenagemHidrometro() {
		super(null, "");
	}

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param situacao pagamento
	 *            Description of the Parameter
	 * @param SituacaoPagamentoParametros
	 *            Description of the Parameter
	 * @return Descri��o do retorno
	 * @exception RelatorioVazioException
	 *                Descri��o da exce��o
	 */

	public Object executar() throws TarefaException {

		// ------------------------------------
		//		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		FiltroHidrometroLocalArmazenagem filtroHidrometroLocalArmazenagem = (FiltroHidrometroLocalArmazenagem) getParametro("filtroHidrometroLocalArmazenagem");
		HidrometroLocalArmazenagem hidrometroLocalArmazenagemParametros = (HidrometroLocalArmazenagem) getParametro("hidrometroLocalArmazenagemParametros");
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		
		// valor de retorno
		byte[] retorno = null;

		// cole��o de beans do relat�rio
		List relatorioBeans = new ArrayList();

		RelatorioManterLocalArmazenagemHidrometroBean relatorioBean = null;

		Fachada fachada = Fachada.getInstancia();

		Collection colecaoHidrometroLocalArmazenagem = fachada.pesquisar(filtroHidrometroLocalArmazenagem,
				HidrometroLocalArmazenagem.class.getName());

		// se a cole��o de par�metros da analise n�o for vazia
		if (colecaoHidrometroLocalArmazenagem != null && !colecaoHidrometroLocalArmazenagem.isEmpty()) {

			// coloca a cole��o de par�metros da analise no iterator
			Iterator hidrometroLocalArmazenagemIterator = colecaoHidrometroLocalArmazenagem.iterator();

			// la�o para criar a cole��o de par�metros da analise
			while (hidrometroLocalArmazenagemIterator.hasNext()) {

				HidrometroLocalArmazenagem hidrometroLocalArmazenagem = (HidrometroLocalArmazenagem) hidrometroLocalArmazenagemIterator.next();

				String indicadorOficina = "";
				
				if(hidrometroLocalArmazenagem.getIndicadorOficina().equals(ConstantesSistema.SIM)){
					indicadorOficina = "SIM";
				} else {
					indicadorOficina = "N�O";
				}
				
				String indicadorUso = "";
				
				if(hidrometroLocalArmazenagem.getIndicadorUso().equals(ConstantesSistema.SIM)){
					indicadorUso = "SIM";
				} else {
					indicadorUso = "N�O";
				}
				
				relatorioBean = new RelatorioManterLocalArmazenagemHidrometroBean(
						// ID
						hidrometroLocalArmazenagem.getId().toString(), 
						
						// Descri��o
						hidrometroLocalArmazenagem.getDescricao(), 
						
						//Descri��o Abreviada
						hidrometroLocalArmazenagem.getDescricaoAbreviada(),
						
						//Indicador de Uso
						indicadorUso,
						
						// Indicador Oficina
						indicadorOficina);
						
				// adiciona o bean a cole��o
				relatorioBeans.add(relatorioBean);
			}
		}

		// Par�metros do relat�rio
		Map parametros = new HashMap();

		// adiciona os par�metros do relat�rio
		// adiciona o laudo da an�lise
		SistemaParametro sistemaParametro = fachada
				.pesquisarParametrosDoSistema();

		parametros.put("imagem", sistemaParametro.getImagemRelatorio());

		parametros.put("id",
				hidrometroLocalArmazenagemParametros.getId() == null ? "" : ""
						+ hidrometroLocalArmazenagemParametros.getId());
		
		
		if (hidrometroLocalArmazenagemParametros.getDescricao() != null){
			parametros.put("descricao", hidrometroLocalArmazenagemParametros.getDescricao());
		} else {
			parametros.put("descricao", "");
		}
		
		
		if (hidrometroLocalArmazenagemParametros.getDescricaoAbreviada() != null) {
			parametros.put("descricaoAbreviada", hidrometroLocalArmazenagemParametros.getDescricaoAbreviada());
		} else {
			parametros.put("descricaoAbreviada", "");
		}
		
		
		
		String indicadorOficina= "";

		if (hidrometroLocalArmazenagemParametros.getIndicadorOficina() != null
				&& !hidrometroLocalArmazenagemParametros.getIndicadorOficina().equals("")) {
			if (hidrometroLocalArmazenagemParametros.getIndicadorOficina().equals(new Short("1"))) {
				indicadorOficina = "Sim";
			} else if (hidrometroLocalArmazenagemParametros.getIndicadorOficina().equals(
					new Short("2"))) {
				indicadorOficina = "N�o";
			}
		}

		parametros.put("indicadorOficina", indicadorOficina);

		String indicadorUso = "";

		if (hidrometroLocalArmazenagemParametros.getIndicadorUso() != null
				&& !hidrometroLocalArmazenagemParametros.getIndicadorUso().equals("")) {
			if (hidrometroLocalArmazenagemParametros.getIndicadorUso().equals(new Short("1"))) {
				indicadorUso = "Ativo";
			} else if (hidrometroLocalArmazenagemParametros.getIndicadorUso().equals(
					new Short("2"))) {
				indicadorUso = "Inativo";
			}
		}

		parametros.put("indicadorUso", indicadorUso);
		
		// cria uma inst�ncia do dataSource do relat�rio

		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = this.gerarRelatorio(
				ConstantesRelatorios.RELATORIO_LOCAL_ARMAZENAGEM_HIDROMETRO_MANTER, parametros,
				ds, tipoFormatoRelatorio);
		
		// ------------------------------------
		// Grava o relat�rio no sistema
//		try {
//			persistirRelatorioConcluido(retorno, Relatorio.MANTER_LOCALIDADE,
//					idFuncionalidadeIniciada);
//		} catch (ControladorException e) {
//			e.printStackTrace();
//			throw new TarefaException("Erro ao gravar relat�rio no sistema", e);
//		}
		// ------------------------------------

		// retorna o relat�rio gerado
		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		int retorno = 0;

//		retorno = Fachada.getInstancia().totalRegistrosPesquisa(
//				(FiltroLocalidade) getParametro("filtroLocalidade"),
//				Localidade.class.getName());

		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioManterLocalArmazenagemHidrometro", this);
	}

}