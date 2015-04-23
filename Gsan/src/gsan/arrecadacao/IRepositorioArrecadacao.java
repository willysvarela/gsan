/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
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
* GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Aryed Lins de Araújo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Cláudio de Andrade Lira
* Denys Guimarães Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* Fabíola Gomes de Araújo
* Flávio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento Júnior
* Homero Sampaio Cavalcanti
* Ivan Sérgio da Silva Júnior
* José Edmar de Siqueira
* José Thiago Tenório Lopes
* Kássia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* Márcio Roberto Batista da Silva
* Maria de Fátima Sampaio Leite
* Micaela Maria Coelho de Araújo
* Nelson Mendonça de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael Corrêa Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Araújo
* Raphael Veras Rossiter
* Roberto Sobreira Barbalho
* Rodrigo Avellar Silveira
* Rosana Carvalho Barbosa
* Sávio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
*
* Este programa é software livre; você pode redistribuí-lo e/ou
* modificá-lo sob os termos de Licença Pública Geral GNU, conforme
* publicada pela Free Software Foundation; versão 2 da
* Licença.
* Este programa é distribuído na expectativa de ser útil, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia implícita de
* COMERCIALIZAÇÃO ou de ADEQUAÇÃO A QUALQUER PROPÓSITO EM
* PARTICULAR. Consulte a Licença Pública Geral GNU para obter mais
* detalhes.
* Você deve ter recebido uma cópia da Licença Pública Geral GNU
* junto com este programa; se não, escreva para Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
* 02111-1307, USA.
*/  
package gsan.arrecadacao;

import gsan.arrecadacao.aviso.AvisoBancario;
import gsan.arrecadacao.aviso.bean.AvisoBancarioHelper;
import gsan.arrecadacao.aviso.bean.PagamentosDevolucoesHelper;
import gsan.arrecadacao.aviso.bean.ValoresArrecadacaoDevolucaoAvisoBancarioHelper;
import gsan.arrecadacao.banco.Agencia;
import gsan.arrecadacao.banco.Banco;
import gsan.arrecadacao.bean.ConsultarRelatorioAnalisePagamentoCartaoDebitoHelper;
import gsan.arrecadacao.bean.MovimentoArrecadadoresPorNSAHelper;
import gsan.arrecadacao.bean.PesquisarAnaliseArrecadacaoHelper;
import gsan.arrecadacao.bean.PesquisarAnaliseAvisosBancariosHelper;
import gsan.arrecadacao.bean.PesquisarAvisoBancarioPorContaCorrenteHelper;
import gsan.arrecadacao.debitoautomatico.DebitoAutomaticoMovimentoParcelamentoCliente;
import gsan.arrecadacao.pagamento.FiltroPagamento;
import gsan.arrecadacao.pagamento.GuiaPagamento;
import gsan.arrecadacao.pagamento.Pagamento;
import gsan.arrecadacao.pagamento.PagamentoCartaoDebito;
import gsan.arrecadacao.pagamento.PagamentoHistorico;
import gsan.cadastro.cliente.Cliente;
import gsan.cadastro.cliente.ClienteEndereco;
import gsan.cadastro.cliente.ClienteFone;
import gsan.cadastro.cliente.ClienteImovel;
import gsan.cadastro.endereco.LogradouroBairro;
import gsan.cadastro.endereco.LogradouroCep;
import gsan.cadastro.imovel.Imovel;
import gsan.cobranca.CobrancaDocumento;
import gsan.cobranca.CobrancaDocumentoItem;
import gsan.cobranca.DocumentoTipo;
import gsan.cobranca.parcelamento.ParcelamentoPagamentoCartaoCredito;
import gsan.faturamento.conta.Conta;
import gsan.faturamento.conta.Fatura;
import gsan.faturamento.credito.CreditoARealizar;
import gsan.faturamento.credito.CreditoRealizado;
import gsan.faturamento.debito.DebitoACobrar;
import gsan.faturamento.debito.DebitoCobrado;
import gsan.faturamento.debito.DebitoTipo;
import gsan.micromedicao.MovimentoRoteiroEmpresaFoto;
import gsan.micromedicao.bean.ConsultarArquivoTextoRoteiroEmpresaHelper;
import gsan.relatorio.arrecadacao.GuiaDevolucaoRelatorioHelper;
import gsan.relatorio.arrecadacao.RelatorioAnaliseArrecadacaoBean;
import gsan.relatorio.arrecadacao.RelatorioAnaliseAvisosBancariosBean;
import gsan.relatorio.arrecadacao.RelatorioAvisoBancarioPorContaCorrenteBean;
import gsan.relatorio.arrecadacao.RelatorioDocumentoNaoAceitosBean;
import gsan.relatorio.arrecadacao.RelatorioTranferenciaPagamentoBean;
import gsan.relatorio.arrecadacao.pagamento.GuiaPagamentoRelatorioHelper;
import gsan.relatorio.arrecadacao.pagamento.PagamentosBaixadosAutomaticamenteRelatorioHelper;
import gsan.util.ControladorException;
import gsan.util.ErroRepositorioException;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Interface para o repositório de cliente
 * 
 * @author Sávio Luiz
 * @created 22 de Abril de 2005
 */
public interface IRepositorioArrecadacao {

	public Integer pesquisarIdRegistroCodigo(String codigoRegistro)
			throws ErroRepositorioException;

	public Short pesquisarNumeroDiasFloat(Integer codigoBanco,
			Integer idFormaArrecadacao) throws ErroRepositorioException;

	public AvisoBancario pesquisarAvisoBancario(Integer codigoBanco,
			Date dataGeracaoArquivo, Date dataPrevistaCredito,Integer idArrecadadorMovimento,
			Integer idFormaArrecadacao)
			throws ErroRepositorioException;

	public Integer pesquisarExistenciaGuiaPagamento(Imovel imovel,
			Integer idDebitoTipo, BigDecimal valorPagamento) throws ErroRepositorioException;
	
	public GuiaPagamento pesquisarExistenciaGuiaPagamento(Imovel imovel,
			BigDecimal valorPagamento) throws ErroRepositorioException;
	
	public Integer pesquisarExistenciaGuiaPagamentoCliente(Integer idCliente,
			Integer idDebitoTipo) throws ErroRepositorioException;

	public Double pesquisarDeducoesAvisoBancario(String codigoAgente,
			Date dataLancamento, String numeroSequencial)
			throws ErroRepositorioException;

	public Short pesquisarValorMaximoNumeroSequencial(Date dataLancamento,
			String idArrecadador) throws ErroRepositorioException;

	/*public ArrecadadorContrato pesquisarNumeroSequecialArrecadadorContrato(
			Short idArrecadador) throws ErroRepositorioException;*/
	
	public ArrecadadorContrato pesquisarNumeroSequecialArrecadadorContrato(
			Integer idArrecadadorContrato) throws ErroRepositorioException;

	public Integer pesquisarIdArrecadacaoForma(String codigoArrecadacaoForma)
			throws ErroRepositorioException;

	public Integer verificarExistenciaAgencia(String codigoAgencia,Integer idBanco)
			throws ErroRepositorioException;

	public Integer verificarExistenciaBanco(Integer idBanco)
			throws ErroRepositorioException;

	public Integer pesquisarIdDepositoArrecadacao(Integer codigoBanco, String codigoConvenio)
	throws ErroRepositorioException;

	/**
	 * Este caso de uso cria um filtro do movimento dos arrecadadores
	 * 
	 * [UC0263] - Filtrar Movimento dos Arrecadadores
	 * 
	 * @author Raphael Rossiter
	 * @date 02/03/2006
	 * 
	 * @param filtroArrecadadorMovimento
	 * @return Uma coleção de objetos do tipo ArrecadadorMovimento de acordo com
	 *         os parâmetros recebidos através do filtro. Está consulta inclui
	 *         os movimentos abertos e fechados
	 * @throws ErroRepositorioException
	 */
	public Collection<ArrecadadorMovimento> filtrarMovimentoArrecadadores(
			FiltroArrecadadorMovimento filtroArrecadadorMovimento)
			throws ErroRepositorioException;

	/**
	 * Calcula o valor total dos avisos bancários de um determinado movimento
	 * 
	 * @author Raphael Rossiter
	 * @date 06/03/2006
	 * 
	 * @param arrecadadorMovimento
	 * @return Um BigDecimal que representa o somatório de todos os avisos
	 *         bancários de um determinado movimento
	 * @throws ErroRepositorioException
	 */
	public BigDecimal obterTotalArrecadacaoAvisoBancarioPorMovimentoArrecadadores(
			ArrecadadorMovimento arrecadadorMovimento)
			throws ErroRepositorioException;

	/**
	 * Obtém o número de registros em ocorrência de um determinado movimento
	 * (número de linhas da tabela ARRECADADOR_MOVIMENTO_ITEM com ARMV_ID =
	 * ARMV_ID da tabela ARRECADADOR_MOVIMENTO e AMIT_DSOCORRENCIA diferente de
	 * "OK")
	 * 
	 * @author Raphael Rossiter
	 * @date 06/03/2006
	 * 
	 * @param arrecadadorMovimento
	 * @param descricaoOcorrencia
	 * @return Um Integer que representa a quantidade de registros selecionados
	 * @throws ErroRepositorioException
	 */
	public Integer obterNumeroRegistrosEmOcorrenciaPorMovimentoArrecadadores(
			ArrecadadorMovimento arrecadadorMovimento,
			String descricaoOcorrencia) throws ErroRepositorioException;

	/**
	 * Obtém o número de registros que não foram aceitos de um determinado
	 * movimento (número de linhas da tabela ARRECADADOR_MOVIMENTO_ITEM com
	 * ARMV_ID = ARMV_ID da tabela ARRECADADOR_MOVIMENTO e AMIT_ICACEITACAO
	 * igual a 2 (NÃO))
	 * 
	 * @author Raphael Rossiter
	 * @date 08/03/2006
	 * 
	 * @param arrecadadorMovimento
	 * @return Um integer que representa a quantidade de registros selecionados
	 * @throws ControladorException
	 */
	public Integer obterNumeroRegistrosNaoAceitosPorMovimentoArrecadadores(
			ArrecadadorMovimento arrecadadorMovimento, Short indicadorAceitacao)
			throws ErroRepositorioException;

	/**
	 * Seleciona os avisos bancários de um determinado movimento
	 * 
	 * @author Raphael Rossiter
	 * @date 09/03/2006
	 * 
	 * @param arrecadadorMovimento
	 * @return Uma Collection com os avisos bancários de um determinado
	 *         movimento
	 * @throws ErroRepositorioException
	 */
	public Collection<AvisoBancario> obterAvisosBancariosPorArrecadadorMovimento(
			ArrecadadorMovimento arrecadadorMovimento)
			throws ErroRepositorioException;

	/**
	 * Calcula o valor total dos pagamentos associados a um determinado aviso
	 * bancário (soma (PGMT_VLPAGAMENTO) da tabela PAGAMENTO com AVBC_ID =
	 * AVBC_ID da tabela AVISO_BANCARIO)
	 * 
	 * @author Raphael Rossiter
	 * @date 09/03/2006
	 * 
	 * @param avisoBancario
	 * @return Um BigDecimal que representa o somatório de todos os pagamentos
	 *         de um determinado aviso
	 * @throws ErroRepositorioException
	 */
	public BigDecimal obterTotalPagamentoPorAvisoBancario(
			AvisoBancario avisoBancario) throws ErroRepositorioException;

	/**
	 * Calcula o valor total das devoluções associados a um determinado aviso
	 * bancário (soma (DEVL_VLDEVOLUCAO) da tabela DEVOLUCAO com AVBC_ID =
	 * AVBC_ID da tabela AVISO_BANCARIO)
	 * 
	 * @author Raphael Rossiter
	 * @date 09/03/2006
	 * 
	 * @param avisoBancario
	 * @return Um BigDecimal que representa o somatório de todos as devoluções
	 *         de um determinado aviso
	 * @throws ErroRepositorioException
	 */
	public BigDecimal obterTotalDevolucaoPorAvisoBancario(
			AvisoBancario avisoBancario) throws ErroRepositorioException;

	/**
	 * [UC0270] Apresentar Análise do Movimento dos Arrecadadores
	 * 
	 * O sistema seleciona os itens do movimento do arrecadador.
	 * 
	 * [SF0001] Consultar os Itens do Movimento do Arrecadador
	 * 
	 * @author Raphael Rossiter,Vivianne Sousa
	 * @data 20/03/2006,05/12/2006
	 * 
	 * @param arrecadadorMovimento
	 * @return Collection<ArrecadadorMovimentoItem>
	 */
	public Collection<ArrecadadorMovimentoItem> consultarItensMovimentoArrecadador(
			ArrecadadorMovimento arrecadadorMovimento, Integer idImovel,
			Short indicadorAceitacao, String descricaoOcorrencia)
			throws ErroRepositorioException;
	
	/**
	 * [UC0270] Apresentar Análise do Movimento dos Arrecadadores
	 * 
	 * O sistema seleciona os itens do movimento do arrecadador.
	 * 
	 * [SF0001] Consultar os Itens do Movimento do Arrecadador
	 * 
	 * @author Raphael Rossiter,Vivianne Sousa, Kassia Albuquerque
	 * @data 20/03/2006,05/12/2006,22/08/2007
	 * 
	 * @param arrecadadorMovimento
	 * @return Collection<ArrecadadorMovimentoItem>
	 */
	public Collection<ArrecadadorMovimentoItem> consultarItensMovimentoArrecadador(
			ArrecadadorMovimento arrecadadorMovimento, Integer idImovel,
			Short indicadorAceitacao, String descricaoOcorrencia,String codigoArrecadacaoForma)
			throws ErroRepositorioException;

	/**
	 * Faz a pesquisa de devolução fazendo os carregamentos de clienteContas,
	 * clienteImoveis, clientesGuiaPagamento
	 * 
	 * @author Rafael Corrêa
	 * @date
	 * 
	 * @param FiltroDevolucao
	 * @return Collection<Devolucao>
	 * @throws ErroRepositorioException
	 */
	public Collection<Devolucao> pesquisarDevolucao(
			FiltroDevolucao filtroDevolucao) throws ErroRepositorioException;

	/**
	 * Exclui os dados diários da arrecadação do ano/mês da arrecadação corrente
	 * 
	 * [UC0301] Gerar Dados Diários da Arrecadação
	 * 
	 * @author Pedro Alexandre
	 * @date 11/04/2006
	 * 
	 * @param anoMesReferenciaArrecadacao
	 * @throws ErroRepositorioException
	 */
	public void excluirDadosDiariosArrecadacaoPorAnoMesArrecadacao(
			int anoMesReferenciaArrecadacao) throws ErroRepositorioException;

	/**
	 * Acumula a quantidade e o valor dos pagamentos com ano/mês de referência
	 * da arrecadação igual ao ano/mês de referência da arrecadação corrente
	 * 
	 * [UC0301] Gerar Dados Diários da Arrecadação
	 * 
	 * @author Pedro Alexandre
	 * @date 11/04/2006
	 * 
	 * @param anoMesReferenciaArrecadacao
	 * @param idLocalidade
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection acumularQuantidadeEValorPagamentoPorAnoMesArrecadacao(
			int anoMesReferenciaArrecadacao, Integer idLocalidade)
			throws ErroRepositorioException;

	/**
	 * Acumula a quantidade e o valor das devolucoes com ano/mês de referência
	 * da arrecadação igual ao ano/mês de referência da arrecadação corrente
	 * 
	 * [UC0301] Gerar Dados Diários da Arrecadação
	 * 
	 * @author Francisco do Nascimento
	 * @date 01/04/2008
	 * 
	 * @param anoMesReferenciaArrecadacao
	 * @param idLocalidade
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection acumularQuantidadeEValorDevolucaoPorAnoMesArrecadacao(
			int anoMesReferenciaArrecadacao, Integer idLocalidade)
			throws ErroRepositorioException;
	
	/**
	 * [UC0300] Classificar Pagamentos e Devoluções
	 * 
	 * Atualiza a situação atual dos pagamentos (PGST_IDATUAL) com valor
	 * correspondente a pagamento classificado (tabela PAGAMENTO_SITUACAO) e
	 * atualiza o id da conta nos pagamentos (seta CNTA_ID da tabela PAGAMENTO
	 * para CNTA_ID da tabela CONTA)
	 * 
	 * [SF0002] Processar Pagamento de Conta
	 * 
	 * @author Raphael Rossiter, Pedro Alexandre
	 * @date 19/04/2006, 06/12/2006
	 * 
	 * @param mapPagamentosProcessados
	 * @return void
	 */
	public void processarPagamentoConta(
			Map<Integer, Collection> mapPagamentosProcessados)
			throws ErroRepositorioException;

	/**
	 * [UC0300] Classificar Pagamentos e Devoluções
	 * 
	 * Atualiza a situacao atual dos pagamentos (PGST_IDATUAL) com valor
	 * correspondente ao parâmetro passado
	 * 
	 * @author Raphael Rossiter
	 * @date 19/04/2006
	 * 
	 * @param idsPagamentos,
	 *            pagamentoSituacao
	 * @return void
	 */
	public void atualizarSituacaoPagamento(String[] idsPagamentos,
			Integer pagamentoSituacao) throws ErroRepositorioException;

	/**
	 * [UC0319] Gerar Movimento de Débito Automático para o banco
	 * 
	 * pesquisa todos os bancos que tenham contrato vigente para arrecadador
	 * contas com forma de arrecadação correspondente a debito automático
	 * 
	 * [SB0002] - Carregar Lista de Bancos
	 * 
	 * @author Sávio Luiz
	 * @date 18/04/2006
	 * 
	 * @return Coleção de Bancos
	 * @throws ErroRepositorioException
	 */

	public Collection<Banco> pesquisaBancosDebitoAutomatico()
			throws ErroRepositorioException;

	/**
	 * [UC0319] Gerar Movimento de Débito Automático para o banco
	 * 
	 * pesquisa os movimentos de débito automático para o banco,referentes ao
	 * grupo e ano/mês de faturamento informados
	 * 
	 * [SB0002] - Carregar Lista de Bancos
	 * 
	 * @author Sávio Luiz, Raphael Rossiter, Anderson Italo
	 * @date 18/04/2006, 01/12/2008, 04/02/2010
	 * 
	 * [FS0006]  Verificar a situação da conta
	 * Caso a situação da conta sejam normal (0) ou retificada (1) ou incluída (2), 
	 * gerar movimentos de débito automático para o banco.
	 * Caso contrário retornar para o passo correspondente no subfluxo.
	 * 
	 * @param idFaturamentoGrupo,anoMesReferenciaFaturamento,idBanco
	 * @return Coleção de DebitoAutomaticoMovimento
	 * @throws ErroRepositorioException
	 */

	public Collection pesquisaDebitoAutomaticoMovimento(
			Collection colecaoFaturamentoGrupo, Integer anoMesReferenciaFaturamento,
			Collection colecaoidsBanco) throws ErroRepositorioException;

	/**
	 * [UC0319] Filtrar Aviso Bancario
	 * 
	 * 
	 * @author Vivianne Sousa
	 * @date 20/04/2006
	 * 
	 * @param avisoBancarioHelper
	 * @return Coleção de DebitoAutomaticoMovimento
	 * @throws ErroRepositorioException
	 */

	public Collection filtrarAvisoBancarioAbertoFechado(
			AvisoBancarioHelper avisoBancarioHelper)
			throws ErroRepositorioException;

	/**
	 * [UC0319] Gerar Movimento de Débito Automático para o banco
	 * 
	 * pesquisa 2 campos do arrecadador contrato
	 * 
	 * 
	 * @author Sávio Luiz
	 * @date 18/04/2006
	 * 
	 * @param idFaturamentoGrupo,anoMesReferenciaFaturamento,idBanco
	 * @return Código do Convênio, numero sequencial de envio
	 * @throws ErroRepositorioException
	 */

	public Object[] pesquisaCamposArrecadadorContrato(Integer idBanco)
			throws ErroRepositorioException;

	/**
	 * [UC0319] Gerar Movimento de Débito Automático para o banco
	 * 
	 * pesquisa a agencia passando o id do banco
	 * 
	 * 
	 * @author Sávio Luiz
	 * @date 20/04/2006
	 * 
	 * @param idBanco
	 * @return Agencia
	 * @throws ErroRepositorioException
	 */

	public Agencia pesquisaAgenciaPorBanco(Integer idBanco)
			throws ErroRepositorioException;

	/**
	 * [UC0319] Gerar Movimento de Débito Automático para o banco
	 * 
	 * atualiza o numero sequencial arquivo envio debito automatico
	 * 
	 * 
	 * @author Sávio Luiz
	 * @date 20/04/2006
	 * 
	 * @param idBanco
	 * @return Código do Convênio, numero sequencial de envio
	 * @throws ErroRepositorioException
	 */

	public void atualizarNumeroSequencialArrecadadorContrato(
			Integer idArrecadadorContrato, Integer numeroSequencialArquivo)
			throws ErroRepositorioException;

	/**
	 * [UC0319] Gerar Movimento de Débito Automático para o banco
	 * 
	 * pesquisa o email do arrecadador contrato passando o código do banco
	 * 
	 * [SB0003] - Regerar arquivo TXT para um movimento de débito automático
	 * gerado anteriormente
	 * 
	 * @author Sávio Luiz
	 * @date 25/04/2006
	 * 
	 * @return String
	 * @throws ErroRepositorioException
	 */

	public String pesquisarEmailArrecadadorContrato(Short codigoBanco)
			throws ErroRepositorioException;

	/**
	 * [UC0300] Classificar Pagamentos e Devoluções
	 * 
	 * Atualizar Valor Excedente do Pagamento
	 * 
	 * [SF0009] Atualizar Valor Excedente do Pagamento
	 * 
	 * @author Raphael Rossiter
	 * @date 25/04/2006
	 * 
	 * @param pagamento
	 * @return void
	 */
	public void atualizarValorExcedentePagamento(Pagamento pagamento)
			throws ErroRepositorioException;

	/**
	 * [UC0300] Classificar Pagamentos e Devoluções
	 * 
	 * Atualizar Valor Excedente do Pagamento
	 * 
	 * [SF0009] Atualizar Valor Excedente do Pagamento
	 * 
	 * @author Raphael Rossiter, Pedro Alexandre
	 * @date 25/04/2006, 29/11/2006
	 * 
	 * @param colecaoPagamento
	 * @return void
	 */
	public void atualizarValorExcedentePagamento(
			Collection<Pagamento> colecaoPagamento)
			throws ErroRepositorioException;

	/**
	 * [UC0300] Classificar Pagamentos e Devoluções
	 * 
	 * Atualiza a situação atual dos pagamentos (PGST_IDATUAL) com valor
	 * correspondente a pagamento classificado (tabela PAGAMENTO_SITUACAO) e
	 * atualiza o id da guia de pagamento nos pagamentos (seta GPAG_ID da tabela
	 * PAGAMENTO para GPAG_ID da tabela GUIA_PAGAMENTO)
	 * 
	 * [SF0004] Processar Pagamento de Guia de Pagamento
	 * 
	 * @author Raphael Rossiter, Pedro Alexandre
	 * @date 26/04/2006, 11/12/2006
	 * 
	 * @param mapPagamentosProcessados
	 * @throws ErroRepositorioException
	 */
	public void processarPagamentoGuiaPagamento(
			Map<Integer, Collection> mapPagamentosProcessados)
			throws ErroRepositorioException;

	/**
	 * [UC0300] Classificar Pagamentos e Devoluções
	 * 
	 * O sistema seleciona a guia de pagamento correspondente ao pagamento
	 * através do imóvel, cliente e do tipo de débito (a partir da tabela
	 * GUIA_PAGAMENTO com IMOV_ID, CLIE_ID e DBTP_ID da tabela PAGAMENTO e
	 * DCST_IDATUAL com valor correspondente a normal da tabela
	 * DEBITO_CREDITO_SITUACAO)
	 * 
	 * [SF0003] Selecionar Guia de Pagamento pela Localidade, Imóvel, Cliente e
	 * Débito Tipo
	 * 
	 * @author Raphael Rossiter, Pedro Alexandre, Pedro Alexandre
	 * @date 26/04/2006, 14/03/2007, 05/06/2007
	 * 
	 * @param imovel
	 * @param cliente
	 * @param debitoTipo
	 * @param anoMesFaturamento
	 * 
	 * @return Collection<GuiaPagamento>
	 */
	public Collection<GuiaPagamento> selecionarGuiaPagamentoPelaLocalidadeImovelClienteDebitoTipo(
			Imovel imovel, Cliente cliente, DebitoTipo debitoTipo, Integer anoMesFaturamento)
			throws ErroRepositorioException;

	/**
	 * [UC0300] Classificar Pagamentos e Devoluções
	 * 
	 * Atualiza a situação atual dos pagamentos (PGST_IDATUAL) com valor
	 * correspondente a pagamento classificado (tabela PAGAMENTO_SITUACAO) e
	 * atualiza o id do débito a cobrar nos pagamentos (seta DBAC_ID da tabela
	 * PAGAMENTO para DBAC_ID da tabela DEBITO_A_COBRAR)
	 * 
	 * [SF0004] Processar Pagamento de Débito a Cobrar
	 * 
	 * @author Raphael Rossiter ,Pedro Alexandre
	 * @date 27/04/2006, 12/12/2006
	 * 
	 * @param mapPagamentosProcessados
	 * @throws ErroRepositorioException
	 */
	public void processarPagamentoDebitoACobrar(
			Map<Integer, Collection> mapPagamentosProcessados)
			throws ErroRepositorioException;

	/**
	 * [UC0300] Classificar Pagamentos e Devoluções
	 * 
	 * O sistema seleciona o débito a cobrar correspondente ao pagamento através
	 * do imóvel e do tipo de débito (a partir da tabela DEBITO_A_COBRAR com
	 * IMOV_ID e DBTP_ID da tabela PAGAMENTO e DCST_IDATUAL com valor
	 * correspondente a normal da tabela DEBITO_CREDITO_SITUACAO)
	 * 
	 * [SF0005] Selecionar Débito a Cobrar pela Localidade, Imóvel e Débito Tipo
	 * 
	 * @author Raphael Rossiter, Pedro Alexandre
	 * @date 26/04/2006, 05/06/2007
	 * 
	 * @param imovel,
	 * @param debitoTipo
	 * @param anoMesFaturamento
	 * 
	 * @return Collection<DebitoACobrar>
	 */
	public Collection<DebitoACobrar> selecionarDebitoACobrarPelaLocalidadeImovelDebitoTipo(
			Imovel imovel, DebitoTipo debitoTipo, Integer anoMesFaturamento)
			throws ErroRepositorioException;

	/**
	 * Atualizar Devolucao
	 * 
	 * @author Fernanda Paiva
	 * @created 03/05/2006
	 * 
	 * @param valor
	 *            arrecadacao
	 * 
	 * @exception controladorException
	 *                controlador Exception
	 */
	public void atualizaValorArrecadacaoAvisoBancaraio(BigDecimal valor,
			Integer codigoAvisoBancario) throws ErroRepositorioException;

	/**
	 * 
	 * Faz a pesquisa de guia de devolução para o relatório fazendo os
	 * carregamentos de clienteContas, clienteImoveis, clientesGuiaPagamento
	 * 
	 * @author Rafael Corrêa
	 * @date 11/09/2006
	 * 
	 * @param FiltroGuiaDevolucao
	 * @return Collection<GuiaDevolucao>
	 * @throws ErroRepositorioException
	 */
	public Collection<GuiaDevolucao> pesquisarGuiaDevolucaoRelatorio(
			FiltroGuiaDevolucao filtroGuiaDevolucao)
			throws ErroRepositorioException;

	/**
	 * [UC0324] - Filtrar Guia de Devolucao
	 * 
	 * [SF0001] - Seleciona Guias de Devolução do Cliente
	 * 
	 * Faz a pesquisa de guia de devolução fazendo os carregamentos de
	 * clienteContas, clienteImoveis, clientesGuiaPagamento
	 * 
	 * @author Rafael Corrêa
	 * @date
	 * 
	 * @param FiltroGuiaDevolucao
	 * @return Collection<GuiaDevolucao>
	 * @throws ErroRepositorioException
	 */
	public Collection<GuiaDevolucao> pesquisarGuiaDevolucao(
			FiltroGuiaDevolucao filtroGuiaDevolucao, Integer numeroPagina)
			throws ErroRepositorioException;

	/**
	 * [UC0324] - Filtrar Guia de Devolucao
	 * 
	 * [SF0001] - Seleciona Guias de Devolução do Cliente
	 * 
	 * Faz a pesquisa de guia de devolução fazendo os carregamentos de
	 * clienteContas, clienteImoveis, clientesGuiaPagamento
	 * 
	 * @author Rafael Corrêa
	 * @date
	 * 
	 * @param FiltroGuiaDevolucao
	 * @return Collection<GuiaDevolucao>
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarGuiaDevolucaoCount(
			FiltroGuiaDevolucao filtroGuiaDevolucao)
			throws ErroRepositorioException;

	/**
	 * Consulta ResumoArrecadacao para a geração do relatório '[UC0345] Gerar
	 * Relatório de Resumo Arrecadação' de acordo com a opção de totalização.
	 * 
	 * @author Vivianne Sousa, Diogo Peixoto
	 * @created 23/05/2006, 27/04/2011
	 * @param anoMesReferencia
	 * @param estadoMunicipio
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection consultarResumoArrecadacaoRelatorioPorEstado(
			int anoMesReferencia, boolean estadoMunicipio) throws ErroRepositorioException;

	/**
	 * Consulta ResumoArrecadacao para a geração do relatório '[UC0345] Gerar
	 * Relatório de Resumo Arrecadação' de acordo com a opção de totalização.
	 * 
	 * @author Vivianne Sousa
	 * @created 23/05/2006
	 * 
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection consultarResumoArrecadacaoRelatorioPorEstadoPorGerenciaRegional(
			int anoMesReferencia) throws ErroRepositorioException;

	/**
	 * Consulta ResumoArrecadacao para a geração do relatório '[UC0345] Gerar
	 * Relatório de Resumo Arrecadação' de acordo com a opção de totalização.
	 * 
	 * @author Vivianne Sousa
	 * @created 23/05/2006
	 * 
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection consultarResumoArrecadacaoRelatorioPorEstadoPorLocalidade(
			int anoMesReferencia) throws ErroRepositorioException;
	
	/**
	 * Consulta ResumoArrecadacao para a geração do relatório '[UC0345] Gerar
	 * Relatório de Resumo Arrecadação' de acordo com a opção de totalização.
	 * 
	 * @author Diogo Peixoto
	 * @created 20/04/2011
	 * 
	 * @param anoMesReferencia
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection consultarResumoArrecadacaoRelatorioPorEstadoPorMunicipio(int anoMesReferencia)
		throws ErroRepositorioException;
	
	/**
	 * Consulta ResumoArrecadacao para a geração do relatório '[UC0345] Gerar
	 * Relatório de Resumo Arrecadação' de acordo com a opção de totalização.
	 * 
	 * @author Diogo Peixoto
	 * @created 20/04/2011
	 * 
	 * @param anoMesReferencia
	 * @param idMunicipio
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection consultarResumoArrecadacaoRelatorioPorMunicipio(int anoMesReferencia, Integer idMunicipio)
		throws ErroRepositorioException;

	/**
	 * Consulta ResumoArrecadacao para a geração do relatório '[UC0345] Gerar
	 * Relatório de Resumo Arrecadação' de acordo com a opção de totalização.
	 * 
	 * @author Vivianne Sousa
	 * @created 23/05/2006
	 * 
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection consultarResumoArrecadacaoRelatorioPorGerenciaRegional(
			int anoMesReferencia, Integer gerenciaRegional)
			throws ErroRepositorioException;

	/**
	 * Consulta ResumoArrecadacao para a geração do relatório '[UC0345] Gerar
	 * Relatório de Resumo Arrecadação' de acordo com a opção de totalização.
	 * 
	 * @author Vivianne Sousa
	 * @created 23/05/2006
	 * 
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection consultarResumoArrecadacaoRelatorioPorGerenciaRegionalPorLocalidade(
			int anoMesReferencia, Integer gerenciaRegional)
			throws ErroRepositorioException;

	/**
	 * Consulta ResumoArrecadacao para a geração do relatório '[UC0345] Gerar
	 * Relatório de Resumo Arrecadação' de acordo com a opção de totalização.
	 * 
	 * @author Vivianne Sousa
	 * @created 23/05/2006
	 * 
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection consultarResumoArrecadacaoRelatorioPorLocalidade(
			int anoMesReferencia, Integer localidade)
			throws ErroRepositorioException;

	/**
	 * [UC0352] Emitir Conta
	 * 
	 * pesquisa o nome do banco e código da agencia passando o id do imóvel
	 * 
	 * [SB0017] - Gerar Linhas das contas com Débito Automático
	 * 
	 * @author Sávio Luiz
	 * @date 26/05/2006
	 * 
	 * @return String
	 * @throws ErroRepositorioException
	 */

	public Object[] pesquisarParmsDebitoAutomatico(Integer idImovel)
			throws ErroRepositorioException;

	/**
	 * Consulta a qtde de registros ResumoArrecadacao para a geração do
	 * relatório '[UC0345] Gerar Relatório de Resumo do Arrecadacao' de acordo
	 * com a opção de totalização.
	 * 
	 * @author Vivianne Sousa, Diogo Peixoto
	 * @created 02/06/2006, 20/04/2011
	 * 
	 * @param opcaoTotalizacao
	 * @param mesAnoReferencia
	 * @param gerenciaRegional
	 * @param localidade
	 * @param municipio
	 * @return
	 * @throws ControladorException
	 */
	public Integer consultarQtdeRegistrosResumoArrecadacaoRelatorio(int anoMesReferencia, Integer localidade, Integer gerenciaRegional,
			Integer municipio, String opcaoTotalizacao) throws ErroRepositorioException;

	/**
	 * [UC0276] - Encerrar Arrecadação do Mês
	 * 
	 * Inseri os resumos das arrecadações gerados pelo batch no sistema
	 * 
	 * @author Pedro Alexandre
	 * @date 17/05/2006
	 * 
	 * @param colecaoResumoArrecadacao
	 * @throws ErroRepositorioException
	 */
	public void inserirResumoArrecadacao(
			Collection<ResumoArrecadacao> colecaoResumoArrecadacao)
			throws ErroRepositorioException;

	/**
	 * [UC0276] - Encerrar Arrecadação do Mês
	 * 
	 * Verifica se já existe resumo da arrecadação para o ano/mês de referência
	 * da arrecadação
	 * 
	 * [FS0003] - Verificar a existência do resumo da arrecadação
	 * 
	 * @author Pedro Alexandre
	 * @date 16/05/2006
	 * 
	 * @param anoMesReferenciaArrecadacao
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarResumoArrecadacaoPorAnoMesArrecadacao(
			Integer anoMesReferenciaArrecadacao)
			throws ErroRepositorioException;

	/**
	 * [UC0276] - Encerrar Arrecadação do Mês
	 * 
	 * Pesquisa os pagamentos classificados de conta do ano/mês de referência da
	 * arrecadação com a situação atual(PGST_IDATUAL) igual a pagamento
	 * classificado ou baixar valor excedente e com o código da conta diferente
	 * de nulo (CNTA_ID <> NULL)
	 * 
	 * @author Pedro Alexandre
	 * @date 16/05/2006
	 * 
	 * @param anoMesReferenciaArrecadacao
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Pagamento> pesquisarPagamentosClassificadosContas(
			Integer anoMesReferenciaArrecadacao)
			throws ErroRepositorioException;

	/**
	 * [UC0276] - Encerrar Arrecadação do Mês
	 * 
	 * Pesquisa os pagamentos classificados de guia de pagamento do ano/mês de
	 * referência da arrecadação com a situação atual(PGST_IDATUAL) igual a
	 * pagamento classificado ou baixar valor excedente e com o código da guia
	 * de pagamento diferente de nulo (GPAG_ID <> NULL)
	 * 
	 * @author Pedro Alexandre
	 * @date 16/05/2006
	 * 
	 * @param anoMesReferenciaArrecadacao
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Pagamento> pesquisarPagamentosClassificadosGuiasPagamento(
			Integer anoMesReferenciaArrecadacao)
			throws ErroRepositorioException;

	/**
	 * [UC0276] - Encerrar Arrecadação do Mês
	 * 
	 * Pesquisa os pagamentos classificados de débito a cobrar do ano/mês de
	 * referência da arrecadação com a situação atual(PGST_IDATUAL) igual a
	 * pagamento classificado ou baixar valor excedente e com o código do débito
	 * a cobrar diferente de nulo (DBAC_ID <> NULL)
	 * 
	 * @author Pedro Alexandre
	 * @date 16/05/2006
	 * 
	 * @param anoMesReferenciaArrecadacao
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Pagamento> pesquisarPagamentosClassificadosDebitoACobrar(
			Integer anoMesReferenciaArrecadacao)
			throws ErroRepositorioException;

	/**
	 * [UC0276] - Encerrar Arrecadação do Mês
	 * 
	 * Pesquisa os pagamentos não classificados do mês, que são do ano/mês de
	 * referência da arrecadação com a situação atual(PGST_IDATUAL) diferente de
	 * pagamento classificado
	 * 
	 * @author Pedro Alexandre
	 * @date 16/05/2006
	 * 
	 * @param anoMesReferenciaArrecadacao
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Pagamento> pesquisarPagamentosNaoClassificadosMes(
			Integer anoMesReferenciaArrecadacao)
			throws ErroRepositorioException;

	/**
	 * [UC0276] - Encerrar Arrecadação do Mês
	 * 
	 * Pesquisa as devoluções classificadas do ano/mês de referência da
	 * arrecadação e com situação atual igual a devolução classificada ou
	 * devolução de outros valores
	 * 
	 * @author Pedro Alexandre
	 * @date 16/05/2006
	 * 
	 * @param anoMesReferenciaArrecadacao
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Devolucao> pesquisarDevolucoesClassificadas(
			Integer anoMesReferenciaArrecadacao)
			throws ErroRepositorioException;

	/**
	 * [UC0276] - Encerrar Arrecadação do Mês
	 * 
	 * Pesquisa as devoluções não classificadas do mês, para situação atual
	 * diferente de devolução classificada e devolução de outros valores
	 * 
	 * @author Pedro Alexandre
	 * @date 16/05/2006
	 * 
	 * @param anoMesReferenciaArrecadacao
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Devolucao> pesquisarDevolucoesNaoClassificadasMes(
			Integer anoMesReferenciaArrecadacao)
			throws ErroRepositorioException;

	/**
	 * [UC0276] - Encerrar Arrecadação do Mês
	 * 
	 * Pesquisa os pagamentos de contas efetuados em meses anteriores
	 * classificados no mês, que sãoos do ano/mês de referência anterior ao da
	 * arrecadação, que foram classificados no mês, com situação atual igual a
	 * pagamento classificado ou baixar valor excedente e com código da conta
	 * diferente de nulo (CNTA_ID <> NULL)
	 * 
	 * @author Pedro Alexandre
	 * @date 16/05/2006
	 * 
	 * @param anoMesReferenciaArrecadacao
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Pagamento> pesquisarPagamentosContasEfetuadosEmMesesAnterioresClassificadosMes(
			Integer anoMesReferenciaArrecadacao)
			throws ErroRepositorioException;

	/**
	 * [UC0276] - Encerrar Arrecadação do Mês
	 * 
	 * Pesquisa os pagamentos de guias de pagamento efetuados em meses
	 * anteriores classificados no mês, que são os do ano/mês de referência
	 * anterior ao da arrecadação, que foram classificados no mês, com situação
	 * atual igual a pagamento classificado ou baixar valor excedente e com
	 * código da guia de pagamento diferente de nulo (GPAG_ID <> NULL)
	 * 
	 * @author Pedro Alexandre
	 * @date 16/05/2006
	 * 
	 * @param anoMesReferenciaArrecadacao
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Pagamento> pesquisarPagamentosGuiasPagamentoEfetuadosEmMesesAnterioresClassificadosMes(
			Integer anoMesReferenciaArrecadacao)
			throws ErroRepositorioException;

	/**
	 * [UC0276] - Encerrar Arrecadação do Mês
	 * 
	 * Pesquisa os pagamentos de débitos a cobrar efetuados em meses anteriores
	 * classificados no mês, que são os do ano/mês de referência anterior ao da
	 * arrecadação, que foram classificados no mês, com situação atual igual a
	 * pagamento classificado ou baixar valor excedente e com código do débito a
	 * cobrar diferente de nulo (DBAC_ID <> NULL)
	 * 
	 * @author Pedro Alexandre
	 * @date 16/05/2006
	 * 
	 * @param anoMesReferenciaArrecadacao
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Pagamento> pesquisarPagamentosDebitoACobrarEfetuadosEmMesesAnterioresClassificadosMes(
			Integer anoMesReferenciaArrecadacao)
			throws ErroRepositorioException;

	/**
	 * [UC0276] - Encerrar Arrecadação do Mês
	 * 
	 * Pesquisa as devoluções efetuadas em meses anteriores classificadas no
	 * mês, que são as do ano/mês de referência anterior ao da arrecadação e que
	 * foram classificadas no mês, comsituação atual igual a devolução
	 * classificada ou devolução de outros valores.
	 * 
	 * @author Pedro Alexandre
	 * @date 16/05/2006
	 * 
	 * @param anoMesReferenciaArrecadacao
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Devolucao> pesquisarDevolucoesEfetuadasEmMesesAnterioresClassificadasNoMes(
			Integer anoMesReferenciaArrecadacao)
			throws ErroRepositorioException;

	/**
	 * [UC0276] - Encerrar Arrecadação do Mês
	 * 
	 * Pesquisa os pagamentos não classificados com baixa comandada, que são os
	 * que estão com a situação atual com o valor correspondente a baixar
	 * excedente
	 * 
	 * @author Pedro Alexandre
	 * @date 16/05/2006
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Pagamento> pesquisarPagamentosNaoClassificadosComBaixaComandada()
			throws ErroRepositorioException;

	/**
	 * [UC0276] - Encerrar Arrecadação do Mês
	 * 
	 * Pesquisa os pagamentos não classificados, que são os do ano/mês de
	 * referência igual ou anterior ao da arrecadação e que estão não
	 * classificados,com situação atual diferente de pagamento classificado e de
	 * baixar valor excedente
	 * 
	 * @author Pedro Alexandre
	 * @date 16/05/2006
	 * 
	 * @param anoMesReferenciaArrecadacao
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Pagamento> pesquisarPagamentosNaoClassificados(
			Integer anoMesReferenciaArrecadacao)
			throws ErroRepositorioException;

	/**
	 * [UC0276] - Encerrar Arrecadação do Mês
	 * 
	 * Pesquisa as devoluções não classificadas, que são as do ano/mês de
	 * referência igual ou anterior ao da arrecadação e que continuam não
	 * classificados, com situação atual com o valor diferente de devolução
	 * classificada e devolução de outros valores.
	 * 
	 * @author Pedro Alexandre
	 * @date 16/05/2006
	 * 
	 * @param anoMesReferenciaArrecadacao
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Devolucao> pesquisarDevolucoesNaoClassificadas(
			Integer anoMesReferenciaArrecadacao)
			throws ErroRepositorioException;

	/**
	 * Sequencial do tipo lançamento igual a 100
	 * 
	 * [UC0276] - Encerrar Arrecadação do Mês
	 * 
	 * Acumula o valor de água por categoria e localidade paa os pagamentos
	 * classificados de conta
	 * 
	 * @author Pedro Alexandre
	 * @date 22/05/2006
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaArrecadacao
	 * @param idCategoria
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal acumularValorAguaPagamentosClassificadosConta(
			Integer idLocalidade, Integer anoMesReferenciaArrecadacao,
			Integer idCategoria) throws ErroRepositorioException;

	/**
	 * Sequencial do tipo lançamento igual a 200
	 * 
	 * [UC0276] - Encerrar Arrecadação do Mês
	 * 
	 * Acumula o valor de esgoto por categoria e localidade paa os pagamentos
	 * classificados de conta
	 * 
	 * @author Pedro Alexandre
	 * @date 22/05/2006
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaArrecadacao
	 * @param idCategoria
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal acumularValorEsgotoPagamentosClassificadosConta(
			Integer idLocalidade, Integer anoMesReferenciaArrecadacao,
			Integer idCategoria) throws ErroRepositorioException;

	/**
	 * Sequencial do tipo lançamento igual a 300
	 * 
	 * [UC0276] - Encerrar Arrecadação do Mês
	 * 
	 * Acumula o valor do débitos cobrados por localidade, categoria e item
	 * contábil dos pagamentos classificados de conta para tipo de financiamento
	 * igual a serviço
	 * 
	 * @author Pedro Alexandre
	 * @date 22/05/2006
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaArrecadacao
	 * @param idLancamentoItemContabil
	 * @param idCategoria
	 * @return
	 * @throws ErroRepositorioException
	 */
	/*public BigDecimal acumularValorDebitoCobradoPagamentosClassificadosContaFinanciamentoTipoServico(
			Integer idLocalidade, Integer anoMesReferenciaArrecadacao,
			Integer idLancamentoItemContabil, Integer idCategoria)
			throws ErroRepositorioException;*/

	/**
	 * Sequencial do tipo lançamento igual a 400
	 * 
	 * [UC0276] - Encerrar Arrecadação do Mês
	 * 
	 * Acumula o valor do débitos cobrados por localidade, categoria dos
	 * pagamentos classificados de conta para tipo de financiamento igual a
	 * parcelamento de água
	 * 
	 * @author Pedro Alexandre
	 * @date 22/05/2006
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaArrecadacao
	 * @param idCategoria
	 * @return
	 * @throws ErroRepositorioException
	 */
	/*public BigDecimal acumularValorDebitoCobradoPagamentosClassificadosContaFinanciamentoTipoParcelamentoAgua(
			Integer idLocalidade, Integer anoMesReferenciaArrecadacao,
			Integer idCategoria) throws ErroRepositorioException;*/

	/**
	 * Sequencial do tipo lançamento igual a 500
	 * 
	 * [UC0276] - Encerrar Arrecadação do Mês
	 * 
	 * Acumula o valor do débitos cobrados por localidade, categoria dos
	 * pagamentos classificados de conta para tipo de financiamento igual a
	 * parcelamento de esgoto
	 * 
	 * @author Pedro Alexandre
	 * @date 22/05/2006
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaArrecadacao
	 * @param idCategoria
	 * @return
	 * @throws ErroRepositorioException
	 */
	/*public BigDecimal acumularValorDebitoCobradoPagamentosClassificadosContaFinanciamentoTipoParcelamentoEsgoto(
			Integer idLocalidade, Integer anoMesReferenciaArrecadacao,
			Integer idCategoria) throws ErroRepositorioException;*/

	/**
	 * Sequencial do tipo lançamento igual a 600
	 * 
	 * [UC0276] - Encerrar Arrecadação do Mês
	 * 
	 * Acumula o valor do débitos cobrados por localidade, categoria dos
	 * pagamentos classificados de conta para tipo de financiamento igual a
	 * parcelamento de serviço e grupo de parcelamento diferente de juros
	 * cobrados
	 * 
	 * @author Pedro Alexandre
	 * @date 22/05/2006
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaArrecadacao
	 * @param idCategoria
	 * @param idLancamentoItemContabil
	 * @return
	 * @throws ErroRepositorioException
	 */
	/*public BigDecimal acumularValorDebitoCobradoPagamentosClassificadosContaFinanciamentoTipoParcelamentoServicoGrupoParcelamentoDiferenteJurosCobrados(
			Integer idLocalidade, Integer anoMesReferenciaArrecadacao,
			Integer idCategoria, Integer idLancamentoItemContabil)
			throws ErroRepositorioException;*/

	/**
	 * Sequencial do tipo lançamento igual a 700
	 * 
	 * [UC0276] - Encerrar Arrecadação do Mês
	 * 
	 * Acumula o valor do débitos cobrados por localidade, categoria dos
	 * pagamentos classificados de conta para tipo de financiamento igual a
	 * parcelamento de serviço e grupo de parcelamento igual a juros cobrados
	 * 
	 * @author Pedro Alexandre
	 * @date 22/05/2006
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaArrecadacao
	 * @param idCategoria
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal acumularValorDebitoCobradoPagamentosClassificadosContaFinanciamentoTipoParcelamentoServicoGrupoParcelamentoIgualJurosCobrados(
			Integer idLocalidade, Integer anoMesReferenciaArrecadacao,
			Integer idCategoria) throws ErroRepositorioException;

	/**
	 * Sequencial do tipo lançamento igual a 800
	 * 
	 * [UC0276] - Encerrar Arrecadação do Mês
	 * 
	 * Acumula o valor dos créditos realizados por localidade e categoria para
	 * os pagamentos classificados de contas, para origem de crédito igual a
	 * contas pagas em duplicidade/excesso.
	 * 
	 * @author Pedro Alexandre
	 * @date 22/05/2006
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaArrecadacao
	 * @param idCategoria
	 * @return
	 * @throws ErroRepositorioException
	 */
	/*public BigDecimal acumularValorCreditoRealizadoPagamentosClassificadosContaOrigemCreditoContasPagasEmDuplicidadeExcesso(
			Integer idLocalidade, Integer anoMesReferenciaArrecadacao,
			Integer idCategoria) throws ErroRepositorioException;*/

	/**
	 * Sequencial do tipo lançamento igual a 900
	 * 
	 * [UC0276] - Encerrar Arrecadação do Mês
	 * 
	 * Acumula o valor dos créditos realizados por localidade, categoria e item
	 * contábil para os pagamentos classificados de contas, para origem de
	 * crédito igual a valores cobrados indevidamente.
	 * 
	 * @author Pedro Alexandre
	 * @date 22/05/2006
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaArrecadacao
	 * @param idCategoria
	 * @param idLancamentoItemContabil
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal acumularValorCreditoRealizadoPagamentosClassificadosContaOrigemCreditoValoresCobradosIndevidamente(
			Integer idLocalidade, Integer anoMesReferenciaArrecadacao,
			Integer idCategoria, Integer idLancamentoItemContabil)
			throws ErroRepositorioException;

	/**
	 * Sequencial do tipo lançamento igual a 1000
	 * 
	 * [UC0276] - Encerrar Arrecadação do Mês
	 * 
	 * Acumula o valor dos créditos realizados por localidade e categoria para
	 * os pagamentos classificados de contas, para origem de crédito igual a
	 * descontos concedidos.
	 * 
	 * @author Pedro Alexandre
	 * @date 22/05/2006
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaArrecadacao
	 * @param idCategoria
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal acumularValorCreditoRealizadoPagamentosClassificadosContaOrigemCredito(
			Integer idLocalidade, Integer anoMesReferenciaArrecadacao,
			Integer idCategoria,Integer idCreditoOrigem) throws ErroRepositorioException;

	

	

	
	
	/**
	 * Sequencial do tipo lançamento igual a 1700
	 * 
	 * [UC0276] - Encerrar Arrecadação do Mês
	 * 
	 * Acumula o valor da entrada do parcelamento por localidade e categoria dos
	 * pagamentos classificados de guias de pagamento com tipo de financiamento
	 * igual a entrada de parcelamento
	 * 
	 * @author Pedro Alexandre
	 * @date 22/05/2006
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaArrecadacao
	 * @param idCategoria
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal acumularValorEntradaParcelamentoPagamentosClassificadosGuiaPagamentoFinanciamentoTipoEntradaParcelamento(
			Integer idLocalidade, Integer anoMesReferenciaArrecadacao,
			Integer idCategoria) throws ErroRepositorioException;

	/**
	 * Sequencial do tipo lançamento igual a 1800
	 * 
	 * [UC0276] - Encerrar Arrecadação do Mês
	 * 
	 * Acumula o valor da guia de pagamento por localidade, categoria e item
	 * contábil dos pagamentos classificados de guias de pagamento com tipo de
	 * financiamento igual a serviço
	 * 
	 * @author Pedro Alexandre
	 * @date 22/05/2006
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaArrecadacao
	 * @param idLancamentoItemContabil
	 * @param idCategoria
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal acumularValorEntradaParcelamentoPagamentosClassificadosGuiaPagamentoFinanciamentoTipoServico(
			Integer idLocalidade, Integer anoMesReferenciaArrecadacao,
			Integer idLancamentoItemContabil, Integer idCategoria)
			throws ErroRepositorioException;

	/**
	 * Sequencial do tipo lançamento igual a 1900
	 * 
	 * [UC0276] - Encerrar Arrecadação do Mês
	 * 
	 * Acumula o valor que falta ser cobrado dos débitos a cobrar dos pagamentos
	 * classificados de débito a cobrar por localidade, categoria e item
	 * contábil
	 * 
	 * 
	 * @author Pedro Alexandre
	 * @date 23/05/2006
	 * 
	 * @param anoMesReferenciaArrecadacao
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal acumularValorQueFaltaSerCobradoPagamentosClassificadosDebitoACobrar(
			Integer idLocalidade, Integer anoMesReferenciaArrecadacao,
			Integer idLancamentoItemContabil, Integer idCategoria)
			throws ErroRepositorioException;


	




	/**
	 * Sequencial do tipo lançamento igual a 2600
	 * 
	 * [UC0276] - Encerrar Arrecadação do Mês
	 * 
	 * Pesquisa as devoluções classificadas para acumular o valor da devolução
	 * por categoria com situação igual a devolução classificada.
	 * 
	 * @author Pedro Alexandre
	 * @date 25/05/2006
	 * 
	 * @param anoMesReferenciaArrecadacao
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarDevolucoesClassificadasSituacaoAtualDevolucaoClassificada(
			Integer anoMesReferenciaArrecadacao, Integer idLocalidade)
			throws ErroRepositorioException;

	/**
	 * Sequencial do tipo lançamento igual a 2700
	 * 
	 * [UC0276] - Encerrar Arrecadação do Mês
	 * 
	 * Pesquisa as devoluções classificadas para acumular o valor da devolução
	 * por categoria e item contábil com situação igual a devolução de outros
	 * valores.
	 * 
	 * @author Pedro Alexandre
	 * @date 25/05/2006
	 * 
	 * @param anoMesReferenciaArrecadacao
	 * @param idLocalidade
	 * @param idLancamentoItemContabil
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarDevolucoesClassificadasSituacaoAtualDevolucaoOutrosValores(
			Integer anoMesReferenciaArrecadacao, Integer idLocalidade,
			Integer idLancamentoItemContabil) throws ErroRepositorioException;

	

	

	

	/**
	 * Sequencial do tipo lançamento igual a 3500
	 * 
	 * [UC0276] - Encerrar Arrecadação do Mês
	 * 
	 * Acumula o valor de água por localidade e categoria para os pagamentos de
	 * contas efetuados em meses anteriores classificados no mês.
	 * 
	 * @author Pedro Alexandre
	 * @date 23/05/2006
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaArrecadacao
	 * @param idCategoria
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal acumularValorAguaPagamentosContasEfetuadosEmMesesAnterioresClassificadosNoMes(
			Integer idLocalidade, Integer anoMesReferenciaArrecadacao,
			Integer idCategoria) throws ErroRepositorioException;

	/**
	 * Sequencial do tipo lançamento igual a 3600
	 * 
	 * [UC0276] - Encerrar Arrecadação do Mês
	 * 
	 * Acumula o valor de esgoto por localidade e categoria para os pagamentos
	 * de contas efetuados em meses anteriores classificados no mês.
	 * 
	 * @author Pedro Alexandre
	 * @date 23/05/2006
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaArrecadacao
	 * @param idCategoria
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal acumularValorEsgotoPagamentosContasEfetuadosEmMesesAnterioresClassificadosNoMes(
			Integer idLocalidade, Integer anoMesReferenciaArrecadacao,
			Integer idCategoria) throws ErroRepositorioException;

	/**
	 * Sequencial do tipo lançamento igual a 3700
	 * 
	 * [UC0276] - Encerrar Arrecadação do Mês
	 * 
	 * Acumula o valor dos débitos cobrados por localidade, categoria e item
	 * contábil para os pagamentos de contas efetuados em meses anteriores
	 * classificados no mês para tipo de financiamento igual a serviço.
	 * 
	 * @author Pedro Alexandre
	 * @date 23/05/2006
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaArrecadacao
	 * @param idLancamentoItemContabil
	 * @param idCategoria
	 * @return
	 * @throws ErroRepositorioException
	 */
	/*public BigDecimal acumularValorDebitoCobradoPagamentosContasEfetuadosEmMesesAnterioresClassificadosNoMesFinanciamentoTipoServico(
			Integer idLocalidade, Integer anoMesReferenciaArrecadacao,
			Integer idLancamentoItemContabil, Integer idCategoria)
			throws ErroRepositorioException;*/

	/**
	 * Sequencial do tipo lançamento igual a 3800
	 * 
	 * [UC0276] - Encerrar Arrecadação do Mês
	 * 
	 * Acumula o valor dos débitos cobrados por localidade e categoria para os
	 * pagamentos de contas efetuados em meses anteriores classificados no mês
	 * para tipo de financiamento igual a parcelamento de água.
	 * 
	 * @author Pedro Alexandre
	 * @date 23/05/2006
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaArrecadacao
	 * @param idCategoria
	 * @return
	 * @throws ErroRepositorioException
	 */
/*	public BigDecimal acumularValorDebitoCobradoPagamentosContasEfetuadosEmMesesAnterioresClassificadosNoMesFinanciamentoTipoParcelamentoAgua(
			Integer idLocalidade, Integer anoMesReferenciaArrecadacao,
			Integer idCategoria) throws ErroRepositorioException;
*/
	/**
	 * Sequencial do tipo lançamento igual a 3900
	 * 
	 * [UC0276] - Encerrar Arrecadação do Mês
	 * 
	 * Acumula o valor dos débitos cobrados por localidade e categoria para os
	 * pagamentos de contas efetuados em meses anteriores classificados no mês
	 * para tipo de financiamento igual a parcelamento de esgoto.
	 * 
	 * @author Pedro Alexandre
	 * @date 23/05/2006
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaArrecadacao
	 * @param idCategoria
	 * @return
	 * @throws ErroRepositorioException
	 */
/*	public BigDecimal acumularValorDebitoCobradoPagamentosContasEfetuadosEmMesesAnterioresClassificadosNoMesFinanciamentoTipoParcelamentoEsgoto(
			Integer idLocalidade, Integer anoMesReferenciaArrecadacao,
			Integer idCategoria) throws ErroRepositorioException;
*/
	/**
	 * Sequencial do tipo lançamento igual a 4000
	 * 
	 * [UC0276] - Encerrar Arrecadação do Mês
	 * 
	 * Acumula o valor dos débitos cobrados por localidade, categoria e item
	 * contábil para os pagamentos de contas efetuados em meses anteriores
	 * classificados no mês para tipo de financiamento igual a parcelamento de
	 * serviço e grupo de parcelamento diferente de juros cobrados.
	 * 
	 * @author Pedro Alexandre
	 * @date 23/05/2006
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaArrecadacao
	 * @param idCategoria
	 * @param idLancamentoItemContabil
	 * @return
	 * @throws ErroRepositorioException
	 */
/*	public BigDecimal acumularValorDebitoCobradoPagamentosContasEfetuadosEmMesesAnterioresClassificadosNoMesFinanciamentoTipoParcelamentoServicoGrupoParcelamentoDiferenteJurosCobrados(
			Integer idLocalidade, Integer anoMesReferenciaArrecadacao,
			Integer idCategoria, Integer idLancamentoItemContabil)
			throws ErroRepositorioException;*/

	/**
	 * Sequencial do tipo lançamento igual a 4100
	 * 
	 * [UC0276] - Encerrar Arrecadação do Mês
	 * 
	 * Acumula o valor dos débitos cobrados por localidade, categoria e item
	 * contábil para os pagamentos de contas efetuados em meses anteriores
	 * classificados no mês para tipo de financiamento igual a parcelamento de
	 * serviço e grupo de parcelamento igual a juros cobrados.
	 * 
	 * @author Pedro Alexandre
	 * @date 23/05/2006
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaArrecadacao
	 * @param idCategoria
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal acumularValorDebitoCobradoPagamentosContasEfetuadosEmMesesAnterioresClassificadosNoMesFinanciamentoTipoParcelamentoServicoGrupoParcelamentoIgualJurosCobrados(
			Integer idLocalidade, Integer anoMesReferenciaArrecadacao,
			Integer idCategoria) throws ErroRepositorioException;

	/**
	 * Sequencial do tipo lançamento igual a 4200
	 * 
	 * [UC0276] - Encerrar Arrecadação do Mês
	 * 
	 * Acumula o valor dos créditos realizados por localidade e categoria para
	 * os pagamentos de contas efetuados em meses anteriores classificados no
	 * mês, para origem do crédito igual a documentos pagos em
	 * duplicidade/excesso.
	 * 
	 * @author Pedro Alexandre
	 * @date 23/05/2006
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaArrecadacao
	 * @param idCategoria
	 * @return
	 * @throws ErroRepositorioException
	 */
/*	public BigDecimal acumularValorCreditoRealizadoPagamentosContasEfetuadosEmMesesAnterioresClassificadosNoMesOrigemCreditoContasPagasEmDuplicidadeExcesso(
			Integer idLocalidade, Integer anoMesReferenciaArrecadacao,
			Integer idCategoria) throws ErroRepositorioException;
*/
	/**
	 * Sequencial do tipo lançamento igual a 4300
	 * 
	 * [UC0276] - Encerrar Arrecadação do Mês
	 * 
	 * Acumula o valor dos créditos realizados por localidade, categoria e item
	 * contábil para os pagamentos de contas efetuados em meses anteriores
	 * classificados no mês, para origem do crédito igual a valores cobrados
	 * indevidamente.
	 * 
	 * @author Pedro Alexandre
	 * @date 23/05/2006
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaArrecadacao
	 * @param idCategoria
	 * @param idLancamentoItemContabil
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal acumularValorCreditoRealizadoPagamentosContasEfetuadosEmMesesAnterioresClassificadosNoMesOrigemCreditoValoresCobradosIndevidamente(
			Integer idLocalidade, Integer anoMesReferenciaArrecadacao,
			Integer idCategoria, Integer idLancamentoItemContabil)
			throws ErroRepositorioException;

	/**
	 * Sequencial do tipo lançamento igual a 4400
	 * 
	 * [UC0276] - Encerrar Arrecadação do Mês
	 * 
	 * Acumula o valor dos créditos realizados por localidade e categoria, para
	 * os pagamentos de contas efetuados em meses anteriores classificados no
	 * mês, para origem do crédito igual a descontos concedidos.
	 * 
	 * @author Pedro Alexandre
	 * @date 23/05/2006
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaArrecadacao
	 * @param idCategoria
	 * @return
	 * @throws ErroRepositorioException
	 */
/*	public BigDecimal acumularValorCreditoRealizadoPagamentosContasEfetuadosEmMesesAnterioresClassificadosNoMesOrigemCreditoDescontosConcedidos(
			Integer idLocalidade, Integer anoMesReferenciaArrecadacao,
			Integer idCategoria) throws ErroRepositorioException;
*/
	

	
	

	

	/**
	 * Sequencial do tipo lançamento igual a 5100
	 * 
	 * [UC0276] - Encerrar Arrecadação do Mês
	 * 
	 * Acumula o valor das entrads do parcelamento por localidade e categoria
	 * dos pagamento de guias de pagamento efetuados em meses anteriores
	 * classificados no mês com tipo de financiamento igual a entrada de
	 * parcelamento.
	 * 
	 * @author Pedro Alexandre
	 * @date 23/05/2006
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaArrecadacao
	 * @param idCategoria
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal acumularValorEntradaParcelamentoPagamentosGuiaPagamentoEfetuadosEmMesesAnterioresClassificadosNoMesFinanciamentoTipoEntradaParcelamento(
			Integer idLocalidade, Integer anoMesReferenciaArrecadacao,
			Integer idCategoria) throws ErroRepositorioException;

	/**
	 * Sequencial do tipo lançamento igual a 5200
	 * 
	 * [UC0276] - Encerrar Arrecadação do Mês
	 * 
	 * Acumula o valor das entrads do parcelamento por localidade, categoria e
	 * item contábil dos pagamento de guias de pagamento efetuados em meses
	 * anteriores classificados no mês com tipo de financiamento igual a
	 * serviço.
	 * 
	 * @author Pedro Alexandre
	 * @date 23/05/2006
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaArrecadacao
	 * @param idLancamentoItemContabil
	 * @param idCategoria
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal acumularValorEntradaParcelamentoPagamentosGuiaPagamentoEfetuadosEmMesesAnterioresClassificadosNoMesFinanciamentoTipoServico(
			Integer idLocalidade, Integer anoMesReferenciaArrecadacao,
			Integer idLancamentoItemContabil, Integer idCategoria)
			throws ErroRepositorioException;

	/**
	 * Sequencial do tipo lançamento igual a 5300
	 * 
	 * [UC0276] - Encerrar Arrecadação do Mês
	 * 
	 * Acumula o valor que falta ser cobrado dos débitos a cobrar por
	 * localidade, categoria e item contábil para os pagamentos de débitos a
	 * cobrar efetuados em meses anteriores classificados no mês.
	 * 
	 * @author Pedro Alexandre
	 * @date 23/05/2006
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaArrecadacao
	 * @param idLancamentoItemContabil
	 * @param idCategoria
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal acumularValorQueFaltaSerCobradoPagamentosDebitoACobrarEfetuadosEmMesesAnteriores(
			Integer idLocalidade, Integer anoMesReferenciaArrecadacao,
			Integer idLancamentoItemContabil, Integer idCategoria)
			throws ErroRepositorioException;

	/**
	 * Sequencial do tipo lançamento igual a 5500
	 * 
	 * [UC0276] - Encerrar Arrecadação do Mês
	 * 
	 * Pesquisa as devoluções efetuadas em meses anteriores classificadas no mês
	 * para acumular o valor da devolução por localidade e categoria com
	 * situação atual igual a devolução classificada.
	 * 
	 * @author Pedro Alexandre
	 * @date 30/05/2006
	 * 
	 * @param anoMesReferenciaArrecadacao
	 * @param idLocalidade
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarDevolucoesEfetuadasEmMesesAnterioresClassificadasNoMesSituacaoAtualDevolucaoClassificada(
			Integer anoMesReferenciaArrecadacao, Integer idLocalidade)
			throws ErroRepositorioException;

	/**
	 * Sequencial do tipo lançamento igual a 5600
	 * 
	 * [UC0276] - Encerrar Arrecadação do Mês
	 * 
	 * Pesquisa as devoluções efetuadas em meses anteriores classificadas no mês
	 * para acumular o valor da devolução por localidade, categoria e item
	 * contábil, com situação atual igual a devolução de outros valores.
	 * 
	 * @author Pedro Alexandre
	 * @date 31/05/2006
	 * 
	 * @param anoMesReferenciaArrecadacao
	 * @param idLocalidade
	 * @param idLancamentoItemContabil
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarDevolucoesEfetuadasEmMesesAnterioresClassificadasNoMesSituacaoAtualDevolucaoOutrosValores(
			Integer anoMesReferenciaArrecadacao, Integer idLocalidade,
			Integer idLancamentoItemContabil) throws ErroRepositorioException;

	
	

	/**
	 * Sequencial do tipo lançamento igual a 6200 (SOMA DOS SEQ.100,200,3500 e
	 * 3600)
	 * 
	 * [UC0276] - Encerrar Arrecadação do Mês
	 * 
	 * Este metódo acumular os valores dos sequencias : 100, 200, 3500 e
	 * 3600,para ser acumuldo á soma dos recebimentos de valores contabilizados
	 * como perdas.
	 * 
	 * @author Pedro Alexandre
	 * @date 02/06/2006
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaArrecadacao
	 * @param idCategoria
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal acumularValorAgua_EsgotoPagamentosClassificadosNoMes_EfetuadosEmMesesAnterioresContaContabilizadasComoPerdas(
			Integer idLocalidade, Integer anoMesReferenciaArrecadacao,
			Integer idCategoria) throws ErroRepositorioException;

	/**
	 * Sequencial do tipo lançamento igual a 6200 (SOMA DOS SEQ.
	 * 300,400,500,600,700,3700,3800,3900,4000 e 4100)
	 * 
	 * [UC0276] - Encerrar Arrecadação do Mês
	 * 
	 * Este metódo acumular os valores dos sequencias : 300, 400, 500, 600, 700,
	 * 3700, 3800, 3900, 4000 e 4100,para ser acumulado á soma dos recebimentos
	 * de valores contabilizados como perdas.
	 * 
	 * @author Pedro Alexandre
	 * @date 02/06/2006
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaArrecadacao
	 * @param idCategoria
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal acumularValorDebitoCobradoPagamentosClassificadosNoMes_EfetuadosEmMesesAnterioresContaContabilizadaComoPerdasFinanciamentoTipoServico_ParcelamentoAgua_ParcelamentoEsgoto_ParcelamentoServico(
			Integer idLocalidade, Integer anoMesReferenciaArrecadacao,
			Integer idCategoria) throws ErroRepositorioException;

	/**
	 * Sequencial do tipo lançamento igual a 6200 (SOMA DOS
	 * SEQ.800,900,1000,4200,4300 e 4400)
	 * 
	 * [UC0276] - Encerrar Arrecadação do Mês
	 * 
	 * Este metódo acumular os valores dos sequencias : 800, 900, 1000, 4200,
	 * 4300 e 4400, para ser acumulado negativamente á soma dos recebimentos de
	 * valores contabilizados como perdas.
	 * 
	 * @author Pedro Alexandre
	 * @date 02/06/2006
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaArrecadacao
	 * @param idCategoria
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal acumularValorCreditoRealizadoPagamentosClassificadosNoMes_EfetuadosMesesAnterioresContaContabilizadaComoPerdasOrigemCredito_ContasPagasEmDuplicidadeExcesso_ValoresCobradosIndevidamente_DescontosConcedidos(
			Integer idLocalidade, Integer anoMesReferenciaArrecadacao,
			Integer idCategoria) throws ErroRepositorioException;

	/**
	 * Sequencial do tipo lançamento igual a 6200 (SOMA DOS
	 * SEQ.1200,1300,1400,1500,4600,4700,4800 e 4900)
	 * 
	 * [UC0276] - Encerrar Arrecadação do Mês
	 * 
	 * Este metódo acumular os valores dos sequencias : 1200, 1300, 1400, 1500,
	 * 4600, 4700, 4800 e 4900, para ser acumulado negativamente á soma dos
	 * recebimentos de valores contabilizados como perdas.
	 * 
	 * @author Pedro Alexandre
	 * @date 02/06/2006
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaArrecadacao
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarContasImpostosDeduzidosPagamentosClassificadosNoMes_MesesAnterioresContaContabilizadasComoPerdasImpostoTipo_IR_CSLL_COFINS_PISPASEP(
			Integer idLocalidade, Integer anoMesReferenciaArrecadacao)
			throws ErroRepositorioException;

	/**
	 * [UC0276] - Encerrar Arrecadação do Mês
	 * 
	 * Pesquisa os débitos cobrados das contas dos pagamentos classificados de
	 * contas e dos pagamentos anteriores de conta classificados no mês.
	 * 
	 * @author Pedro Alexandre
	 * @date 02/06/2006
	 * 
	 * @param anoMesReferenciaArrecadacao
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<DebitoCobrado> pesquisarDebitosCobradosContasPagamentosClassificados_PagamentosAnterioresContaClassificadosNoMes(
			Integer anoMesReferenciaArrecadacao)
			throws ErroRepositorioException;

	/**
	 * [UC0276] - Encerrar Arrecadação do Mês
	 * 
	 * Pesquisa os créditos realizados das contas dos pagamentos classificados
	 * de contas e dos pagamentos anteriores de conta classificados no mês.
	 * 
	 * @author Pedro Alexandre
	 * @date 02/06/2006
	 * 
	 * @param anoMesReferenciaArrecadacao
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<CreditoRealizado> pesquisarCreditosRealizadosContasPagamentosClassificados_PagamentosAnterioresContaClassificadosNoMes(
			Integer anoMesReferenciaArrecadacao)
			throws ErroRepositorioException;

	/**
	 * [UC0300] Classificar Pagamentos e Devoluções
	 * 
	 * O sistema seleciona os pagamentos com ano/mês de referência da
	 * arrecadação igual ou menor que o ano/mês de referência da arrecadação
	 * corrente (seleciona a partir da tabela PAGAMENTO para
	 * PGMT_AMREFERENCIAARRECADACAO igual ou menor ao
	 * PARM_AMREFERENCIAARRECADACAO) e com o tipo do documento correspondente a
	 * conta
	 * 
	 * @author Raphael Rossiter
	 * @data 18/04/2006
	 * 
	 * @param anoMesReferenciaFaturamento
	 * @return Collection<Object[]>
	 */
	public Collection<Object[]> pesquisarPagamentosPorConta(
			Integer anoMesReferencia) throws ErroRepositorioException;

	/**
	 * [UC0300] Classificar Pagamentos e Devoluções
	 * 
	 * O sistema seleciona os pagamentos com ano/mês de referência da
	 * arrecadação igual ou menor que o ano/mês de referência da arrecadação
	 * corrente (seleciona a partir da tabela PAGAMENTO para
	 * PGMT_AMREFERENCIAARRECADACAO igual ou menor ao
	 * PARM_AMREFERENCIAARRECADACAO) e com o tipo do documento correspondente a
	 * conta
	 * 
	 * @author Raphael Rossiter, Pedro Alexandre
	 * @data 18/04/2006, 28/11/2006
	 * 
	 * @param anoMesReferencia
	 * @param idLocalidade
	 * @param idImovel
	 * @param anoMesReferenciaPagamento
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarPagamentosPorConta(
			Integer anoMesReferencia, Integer idLocalidade, Integer idImovel,
			Integer anoMesReferenciaPagamento) throws ErroRepositorioException;

	/**
	 * [UC0300] Classificar Pagamentos e Devoluções
	 * 
	 * O sistema seleciona os pagamentos com ano/mês de referência da
	 * arrecadação igual ou menor que o ano/mês de referência da arrecadação
	 * corrente (seleciona a partir da tabela PAGAMENTO para
	 * PGMT_AMREFERENCIAARRECADACAO igual ou menor ao
	 * PARM_AMREFERENCIAARRECADACAO), com o tipo do documento correspondente a
	 * guia de pagamento e o campo GPAG_ID informado
	 * 
	 * @author Raphael Rossiter
	 * @data 18/04/2006
	 * 
	 * @param anoMesReferenciaFaturamento
	 * @return Collection<Object[]>
	 */
	public Collection<Object[]> pesquisarPagamentosPorGuiaPagamentoComGuiaInformada(
			Integer anoMesReferencia) throws ErroRepositorioException;

	/**
	 * [UC0300] Classificar Pagamentos e Devoluções
	 * 
	 * O sistema seleciona os pagamentos com ano/mês de referência da
	 * arrecadação igual ou menor que o ano/mês de referência da arrecadação
	 * corrente (seleciona a partir da tabela PAGAMENTO para
	 * PGMT_AMREFERENCIAARRECADACAO igual ou menor ao
	 * PARM_AMREFERENCIAARRECADACAO), com o tipo do documento correspondente a
	 * guia de pagamento e o campo GPAG_ID informado
	 * 
	 * @author Raphael Rossiter, Pedro Alexandre
	 * @data 18/04/2006, 28/11/2006
	 * 
	 * @param anoMesReferenciaFaturamento
	 * @param idLocalidade
	 * @return Collection<Object[]>
	 */
	public Collection<Object[]> pesquisarPagamentosPorGuiaPagamentoComGuiaInformada(
			Integer anoMesReferencia, Integer idLocalidade)
			throws ErroRepositorioException;

	/**
	 * [UC0300] Classificar Pagamentos e Devoluções
	 * 
	 * O sistema seleciona os pagamentos com ano/mês de referência da
	 * arrecadação igual ou menor que o ano/mês de referência da arrecadação
	 * corrente (seleciona a partir da tabela PAGAMENTO para
	 * PGMT_AMREFERENCIAARRECADACAO igual ou menor ao
	 * PARM_AMREFERENCIAARRECADACAO), com o tipo do documento correspondente a
	 * guia de pagamento e o campo GPAG_ID não informado
	 * 
	 * @author Raphael Rossiter
	 * @data 18/04/2006
	 * 
	 * @param anoMesReferenciaFaturamento
	 * @return Collection<Object[]>
	 */
	public Collection<Object[]> pesquisarPagamentosPorGuiaPagamentoSemGuiaInformada(
			Integer anoMesReferencia) throws ErroRepositorioException;

	/**
	 * [UC0300] Classificar Pagamentos e Devoluções
	 * 
	 * O sistema seleciona os pagamentos com ano/mês de referência da
	 * arrecadação igual ou menor que o ano/mês de referência da arrecadação
	 * corrente (seleciona a partir da tabela PAGAMENTO para
	 * PGMT_AMREFERENCIAARRECADACAO igual ou menor ao
	 * PARM_AMREFERENCIAARRECADACAO), com o tipo do documento correspondente a
	 * guia de pagamento e o campo GPAG_ID não informado
	 * 
	 * @author Raphael Rossiter, Pedro Alexandre
	 * @data 18/04/2006, 12/12/2006
	 * 
	 * @param anoMesReferenciaFaturamento
	 * @param idLocalidade
	 * @return Collection<Object[]>
	 */
	public Collection<Object[]> pesquisarPagamentosPorGuiaPagamentoSemGuiaInformada(
			Integer anoMesReferencia, Integer idLocalidade)
			throws ErroRepositorioException;

	/**
	 * [UC0300] Classificar Pagamentos e Devoluções
	 * 
	 * O sistema seleciona os pagamentos com ano/mês de referência da
	 * arrecadação igual ou menor que o ano/mês de referência da arrecadação
	 * corrente (seleciona a partir da tabela PAGAMENTO para
	 * PGMT_AMREFERENCIAARRECADACAO igual ou menor ao
	 * PARM_AMREFERENCIAARRECADACAO), com o tipo do documento correspondente a
	 * debito a cobrar e o campo DBAC_ID não informado
	 * 
	 * @author Raphael Rossiter
	 * @data 18/04/2006
	 * 
	 * @param anoMesReferenciaFaturamento
	 * @return Collection<Object[]>
	 */
	public Collection<Object[]> pesquisarPagamentosPorDebitoACobrarSemDebitoInformada(
			Integer anoMesReferencia) throws ErroRepositorioException;

	/**
	 * [UC0300] Classificar Pagamentos e Devoluções
	 * 
	 * O sistema seleciona os pagamentos com ano/mês de referência da
	 * arrecadação igual ou menor que o ano/mês de referência da arrecadação
	 * corrente (seleciona a partir da tabela PAGAMENTO para
	 * PGMT_AMREFERENCIAARRECADACAO igual ou menor ao
	 * PARM_AMREFERENCIAARRECADACAO), com o tipo do documento correspondente a
	 * debito a cobrar e o campo DBAC_ID não informado
	 * 
	 * @author Raphael Rossiter, Pedro Alexandre
	 * @data 18/04/2006, 28/11/2006
	 * 
	 * @param anoMesReferenciaFaturamento
	 * @param idLocalidade
	 * @return Collection<Object[]>
	 */
	public Collection<Object[]> pesquisarPagamentosPorDebitoACobrarSemDebitoInformada(
			Integer anoMesReferencia, Integer idLocalidade)
			throws ErroRepositorioException;

	/**
	 * [UC0300] Classificar Pagamentos e Devoluções
	 * 
	 * O sistema seleciona os pagamentos com ano/mês de referência da
	 * arrecadação igual ou menor que o ano/mês de referência da arrecadação
	 * corrente (seleciona a partir da tabela PAGAMENTO para
	 * PGMT_AMREFERENCIAARRECADACAO igual ou menor ao
	 * PARM_AMREFERENCIAARRECADACAO), com o tipo do documento correspondente a
	 * debito a cobrar e o campo DBAC_ID informado
	 * 
	 * @author Raphael Rossiter, Pedro Alexandre
	 * @data 18/04/2006, 28/11/2006
	 * 
	 * @param anoMesReferenciaFaturamento
	 * @param idLocalidade
	 * @return Collection<Object[]>
	 */
	public Collection<Object[]> pesquisarPagamentosPorDebitoACobrarComDebitoInformado(
			Integer anoMesReferencia, Integer idLocalidade)
			throws ErroRepositorioException;

	/**
	 * [UC0300] Classificar Pagamentos e Devoluções
	 * 
	 * O sistema seleciona a conta correspondente ao pagamento através do imóvel
	 * e ano/mês de referência do pagamento (a partir da tabela CONTA com
	 * IMOV_ID = IMOV_ID da tabela PAGAMENTO, PGMT_AMREFERENCIAPAGAMENTO da
	 * tabela PAGAMENTO e DCST_IDATUAL com o valor correspondente a normal,
	 * retificada ou incluída, da tabela DEBTIO_CREDITO_SITUACAO)
	 * 
	 * [SF0001] Selecionar Conta pelo Imóvel e Ano/Mês de Referência
	 * 
	 * @author Raphael Rossiter, Pedro Alexandre
	 * @date 18/04/2006, 05/06/2007
	 * 
	 * @param imovel
	 * @param anoMesReferenciaPagamento
	 * @param anoMesFaturamento
	 * 
	 * @return Conta
	 */
	public Object[] selecionarContaPorImovelAnoMesReferencia(Imovel imovel,
			Integer anoMesReferenciaPagamento, Integer anoMesFaturamento) throws ErroRepositorioException;

	/**
	 * Este caso de uso cria um filtro que será usado na pesquisa de pagamentos
	 * 
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * Pesquisa os pagamentos do Imóvel pesquisarPagamento
	 * 
	 * @author Roberta Costa
	 * @date 12/06/06
	 * 
	 * @param FiltroPagamento
	 * @return Collection<Pagamento>
	 * @throws ControladorException
	 */
	public Collection<Pagamento> pesquisarPagamentoImovel(String idImovel,
			String idCliente, String idTipoRelacao, String localidadeInicial,
			String localidadeFinal, String idAvisoBancario,
			String idArrecadador, String periodoArrecadacaoInicial,
			String periodoArrecadacaoFinal, String periodoPagamentoInicio,
			String periodoPagamentoFim, Date dataPagamentoInicial,
			Date dataPagamentoFinal, String[] idsPagamentosSituacoes,
			String[] idsDebitosTipos, String[] idsArrecadacaoForma,
			String[] idsDocumentosTipos, String valorPagamentoInicial, 
            String valorPagamentoFinal) throws ErroRepositorioException;

	/**
	 * Este caso de uso cria um filtro que será usado na pesquisa de pagamentos
	 * 
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * Pesquisa os pagamentos do tipo Conta do Cliente
	 * pesquisarPagamentoClienteConta
	 * 
	 * @author Rafael Corrêa
	 * @date 12/12/06
	 * 
	 * @return Collection<Pagamento>
	 * @throws ControladorException
	 */
	public Collection pesquisarPagamentoClienteConta(
			String idImovel, String idCliente, String idTipoRelacao,
			String localidadeInicial, String localidadeFinal,
			String idAvisoBancario, String idArrecadador,
			String periodoArrecadacaoInicial, String periodoArrecadacaoFinal,
			String periodoPagamentoInicio, String periodoPagamentoFim,
			Date dataPagamentoInicial, Date dataPagamentoFinal,
			String[] idsPagamentosSituacoes, String[] idsDebitosTipos,
			String[] idsArrecadacaoForma, String[] idsDocumentosTipos, String valorPagamentoInicial,
            String valorPagamentoFinal)
			throws ErroRepositorioException;

	/**
	 * Este caso de uso cria um filtro que será usado na pesquisa de pagamentos
	 * 
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * Pesquisa os pagamentos do tipo Guia de Pagamento do Cliente
	 * pesquisarPagamentoClienteGuiaPagamento
	 * 
	 * @author Rafael Corrêa
	 * @date 12/06/06
	 * 
	 * @return Collection<Pagamento>
	 * @throws ControladorException
	 */
	public Collection pesquisarPagamentoClienteGuiaPagamento(
			String idImovel, String idCliente, String idTipoRelacao,
			String localidadeInicial, String localidadeFinal,
			String idAvisoBancario, String idArrecadador,
			String periodoArrecadacaoInicial, String periodoArrecadacaoFinal,
			String periodoPagamentoInicio, String periodoPagamentoFim,
			Date dataPagamentoInicial, Date dataPagamentoFinal,
			String[] idsPagamentosSituacoes, String[] idsDebitosTipos,
			String[] idsArrecadacaoForma, String[] idsDocumentosTipos, 
            String valorPagamentoInicial,
            String valorPagamentoFinal)
			throws ErroRepositorioException;

	/**
	 * Este caso de uso cria um filtro que será usado na pesquisa de pagamentos
	 * 
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * Pesquisa os pagamentos do tipo Debito a Cobrar do Cliente
	 * pesquisarPagamentoClienteDebitoACobrar
	 * 
	 * @author Rafael Corrêa
	 * @date 12/06/06
	 * 
	 * @return Collection
	 * @throws ControladorException
	 */
	public Collection pesquisarPagamentoClienteDebitoACobrar(
			String idImovel, String idCliente, String idTipoRelacao,
			String localidadeInicial, String localidadeFinal,
			String idAvisoBancario, String idArrecadador,
			String periodoArrecadacaoInicial, String periodoArrecadacaoFinal,
			String periodoPagamentoInicio, String periodoPagamentoFim,
			Date dataPagamentoInicial, Date dataPagamentoFinal,
			String[] idsPagamentosSituacoes, String[] idsDebitosTipos,
			String[] idsArrecadacaoForma, String[] idsDocumentosTipos,
            String valorPagamentoInicial,
            String valorPagamentoFinal )
			throws ErroRepositorioException;

	/**
	 * Este caso de uso cria um filtro que será usado na pesquisa de pagamentos
	 * 
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * Pesquisa os pagamentos do tipo Debito a Cobrar do Cliente
	 * pesquisarPagamentoClienteDebitoACobrar
	 * 
	 * @author Roberta Costa
	 * @date 12/06/06
	 * 
	 * @return Collection<Pagamento>
	 * @throws ControladorException
	 */
	public Collection<Pagamento> pesquisarPagamentoLocalidade(String idImovel,
			String idCliente, String idTipoRelacao, String localidadeInicial,
			String localidadeFinal, String idAvisoBancario,
			String idArrecadador, String periodoArrecadacaoInicial,
			String periodoArrecadacaoFinal, String periodoPagamentoInicio,
			String periodoPagamentoFim, Date dataPagamentoInicial,
			Date dataPagamentoFinal, String[] idsPagamentosSituacoes,
			String[] idsDebitosTipos, String[] idsArrecadacaoForma,
			String[] idsDocumentosTipos, Integer numeroPagina,
            String valorPagamentoInicial, 
            String valorPagamentoFinal)
			throws ErroRepositorioException;

	/**
	 * Este caso de uso cria um filtro que será usado na pesquisa de pagamentos
	 * 
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * Pesquisa os pagamentos do tipo Debito a Cobrar do Cliente
	 * pesquisarPagamentoClienteDebitoACobrar
	 * 
	 * @author Roberta Costa
	 * @date 12/06/06
	 * 
	 * @return Collection<Pagamento>
	 * @throws ControladorException
	 */
	public Collection<Pagamento> pesquisarPagamentoAvisoBancario(
			String idImovel, String idCliente, String idTipoRelacao,
			String localidadeInicial, String localidadeFinal,
			String idAvisoBancario, String idArrecadador,
			String periodoArrecadacaoInicial, String periodoArrecadacaoFinal,
			String periodoPagamentoInicio, String periodoPagamentoFim,
			Date dataPagamentoInicial, Date dataPagamentoFinal,
			String[] idsPagamentosSituacoes, String[] idsDebitosTipos,
			String[] idsArrecadacaoForma, String[] idsDocumentosTipos, 
            String valorPagamentoInicial, 
            String valorPagamentoFinal)
			throws ErroRepositorioException;

	/**
	 * Este caso de uso cria um filtro que será usado na pesquisa de pagamentos
	 * 
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * Pesquisa os pagamentos do tipo Debito a Cobrar do Cliente
	 * pesquisarPagamentoClienteDebitoACobrar
	 * 
	 * @author Roberta Costa
	 * @date 12/06/06
	 * 
	 * @return Collection<Pagamento>
	 * @throws ControladorException
	 */
	public Collection<Pagamento> pesquisarPagamentoMovimentoArrecadador(
			String idImovel, String idCliente, String idTipoRelacao,
			String localidadeInicial, String localidadeFinal,
			String idAvisoBancario, String idArrecadador,
			String periodoArrecadacaoInicial, String periodoArrecadacaoFinal,
			String periodoPagamentoInicio, String periodoPagamentoFim,
			Date dataPagamentoInicial, Date dataPagamentoFinal,
			String[] idsPagamentosSituacoes, String[] idsDebitosTipos,
			String[] idsArrecadacaoForma, String[] idsDocumentosTipos, 
            String valorPagamentoInicial, 
            String valorPagamentoFinal)
			throws ErroRepositorioException;

	/**
	 * Consultar dados diarios da arrecadacao
	 * 
	 * @author Fernanda Paiva
	 * @date 09/06/2006
	 * 
	 * @param anoMesReferencia,
	 *            id
	 * @return Coleção de DebitoAutomaticoMovimento
	 * @throws ErroRepositorioException
	 */

	public Collection consultarDadosDiarios(int anoMesReferencia, int id,
			String descricao, int idElo) throws ErroRepositorioException;

	/**
	 * [UC0300] Classificar Pagamentos e Devoluções
	 * 
	 * O sistema seleciona as devoluções com ano/mês de referência da
	 * arrecadação igual ou menor que o ano/mês de referência da arrecadação
	 * corrente (seleciona a partir da tabela DEVOLUCAO para
	 * DEVL_AMREFERENCIAARRECADACAO igual ou menor ao
	 * PARM_AMREFERENCIAARRECADACAO) e com o ano/mês de referência preenchido
	 * (DEVL_AMREFERENCIADEVOLUCAO com valor diferente de nulo)
	 * 
	 * @author Raphael Rossiter
	 * @data 14/06/2006
	 * 
	 * @param anoMesReferenciaFaturamento
	 * @return Collection<Object[]>
	 */
	public Collection<Object[]> pesquisarDevolucoesEmDuplicidadeOUExcesso(
			Integer anoMesReferencia) throws ErroRepositorioException;

	/**
	 * [UC0300] Classificar Pagamentos e Devoluções
	 * 
	 * O sistema seleciona as devoluções com ano/mês de referência da
	 * arrecadação igual ou menor que o ano/mês de referência da arrecadação
	 * corrente (seleciona a partir da tabela DEVOLUCAO para
	 * DEVL_AMREFERENCIAARRECADACAO igual ou menor ao
	 * PARM_AMREFERENCIAARRECADACAO) e com o ano/mês de referência preenchido
	 * (DEVL_AMREFERENCIADEVOLUCAO com valor diferente de nulo)
	 * 
	 * @author Raphael Rossiter, Pedro Alexandre
	 * @data 14/06/2006, 28/11/2006
	 * 
	 * @param anoMesReferenciaFaturamento
	 * @param idLocalidade
	 * @return Collection<Object[]>
	 */
	public Collection<Object[]> pesquisarDevolucoesEmDuplicidadeOUExcesso(
			Integer anoMesReferencia, Integer idLocalidade)
			throws ErroRepositorioException;

	/**
	 * [UC0300] Classificar Pagamentos e Devoluções
	 * 
	 * O sistema seleciona as devoluções com ano/mês de referência da
	 * arrecadação igual ou menor que o ano/mês de referência da arrecadação
	 * corrente (seleciona a partir da tabela DEVOLUCAO para
	 * DEVL_AMREFERENCIAARRECADACAO igual ou menor ao
	 * PARM_AMREFERENCIAARRECADACAO) e com o ano/mês de referência não
	 * preenchido (DEVL_AMREFERENCIADEVOLUCAO com valor diferente nulo)
	 * 
	 * @author Raphael Rossiter
	 * @data 14/06/2006
	 * 
	 * @param anoMesReferenciaFaturamento
	 * @return Collection<Object[]>
	 */
	public Collection<Object[]> pesquisarDevolucoesCobradasIndevidamente(
			Integer anoMesReferencia) throws ErroRepositorioException;

	/**
	 * [UC0300] Classificar Pagamentos e Devoluções
	 * 
	 * O sistema seleciona as devoluções com ano/mês de referência da
	 * arrecadação igual ou menor que o ano/mês de referência da arrecadação
	 * corrente (seleciona a partir da tabela DEVOLUCAO para
	 * DEVL_AMREFERENCIAARRECADACAO igual ou menor ao
	 * PARM_AMREFERENCIAARRECADACAO) e com o ano/mês de referência não
	 * preenchido (DEVL_AMREFERENCIADEVOLUCAO com valor diferente nulo)
	 * 
	 * @author Raphael Rossiter, Pedro Alexandre
	 * @data 14/06/2006, 28/11/2006
	 * 
	 * @param anoMesReferenciaFaturamento
	 * @param idLocalidade
	 * @return Collection<Object[]>
	 */
	public Collection<Object[]> pesquisarDevolucoesCobradasIndevidamente(
			Integer anoMesReferencia, Integer idLocalidade)
			throws ErroRepositorioException;

	/**
	 * [UC0300] Classificar Pagamentos e Devoluções
	 * 
	 * [SF0010] Selecionar Pagamentos não Classificados de Conta
	 * 
	 * @author Raphael Rossiter
	 * @date 26/04/2006
	 * 
	 * @param imovel,
	 *            anoMesReferenciaDevolucao
	 * @return Collection<Pagamento>
	 */
	public Collection<Pagamento> selecionarPagamentosNaoClassificadosConta(
			Imovel imovel, Integer anoMesReferenciaDevolucao)
			throws ErroRepositorioException;

	/**
	 * [UC0300] Classificar Pagamentos e Devoluções
	 * 
	 * Atualiza a situacao atual das devolucoes (DVST_IDATUAL) com valor
	 * correspondente a pagamento em duplicidade não encontrado (tabela
	 * DEVOLUCAO_SITUACAO)
	 * 
	 * [SF0011] Processar Devoluções de Pagamentos
	 * 
	 * @author Raphael Rossiter
	 * @date 15/06/2006
	 * 
	 * @param idsDevolucoes
	 * @return void
	 */
	public void atualizarSituacaoDevolucao(String[] idsDevolucao,
			Integer devolucaoSituacao) throws ErroRepositorioException;

	/**
	 * [UC0300] Classificar Pagamentos e Devoluções
	 * 
	 * Atualiza a situacao anterior dos pagamentos (PGST_IDANTERIOR) (tabela
	 * PAGAMENTO_SITUACAO)
	 * 
	 * [SF0008] Processar Pagamento a Maior ou a Menor
	 * 
	 * @author Raphael Rossiter
	 * @date 19/04/2006
	 * 
	 * @param idsPagamentos
	 * @return void
	 */
	public void atualizarSituacaoAnteriorPagamento(String[] idsPagamentos,
			Integer pagamentoSituacao) throws ErroRepositorioException;

	/**
	 * [UC0300] Classificar Pagamentos e Devoluções
	 * 
	 * [SF0012] Selecionar Pagamentos não classificados de guia de pagamento ou
	 * débito a cobrar
	 * 
	 * @author Raphael Rossiter
	 * @date 15/06/2006
	 * 
	 * @param imovel,
	 *            anoMesReferenciaDevolucao
	 * @return Collection<Pagamento>
	 */
	public Collection<Pagamento> selecionarPagamentosNaoClassificadosGuiaPagamentoDebitoACobrar(
			Imovel imovel, Cliente cliente, DebitoTipo debitoTipo)
			throws ErroRepositorioException;

	/**
	 * [UC0319] Filtrar Aviso Bancario
	 * 
	 * 
	 * @author Fernanda Paiva
	 * @date 16/08/2006
	 * 
	 * @param avisoBancarioHelper
	 * @return Coleção de DebitoAutomaticoMovimento
	 * @throws ErroRepositorioException
	 */
	public Integer filtrarAvisoBancarioAbertoFechadoCount(
			AvisoBancarioHelper avisoBancarioHelper,
			AvisoBancarioHelper avisoBancarioHelperNovo)
			throws ErroRepositorioException;

	/**
	 * [UC0319] Filtrar Aviso Bancario
	 * 
	 * 
	 * @author Fernanda Paiva
	 * @date 16/08/2006
	 * 
	 * @param avisoBancarioHelper
	 * @return Coleção de DebitoAutomaticoMovimento
	 * @throws ErroRepositorioException
	 */

	public Collection filtrarAvisoBancarioAbertoFechadoParaPaginacao(
			AvisoBancarioHelper avisoBancarioHelper, Integer numeroPagina)
			throws ErroRepositorioException;

	/**
	 * Este caso de uso cria um filtro que será usado na pesquisa de pagamentos
	 * 
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * Pesquisa os pagamentos do Cliente
	 * 
	 * @author Vivianne Sousa
	 * @date 21/08/06
	 * 
	 * @return Collection<Pagamento>
	 * @throws ErroRepositorioException
	 */
	public Collection<Pagamento> pesquisarPagamentoCliente(String idImovel,
			String idCliente, String idTipoRelacao, String localidadeInicial,
			String localidadeFinal, String idAvisoBancario,
			String idArrecadador, String periodoArrecadacaoInicial,
			String periodoArrecadacaoFinal, String periodoPagamentoInicio,
			String periodoPagamentoFim, Date dataPagamentoInicial,
			Date dataPagamentoFinal, String[] idsPagamentosSituacoes,
			String[] idsDebitosTipos, String[] idsArrecadacaoForma,
			String[] idsDocumentosTipos, Integer numeroPagina, String valorPagamentoInicial,
            String valorPagamentoFinal )
			throws ErroRepositorioException;

	/**
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * Pesquisa os pagamentos do Cliente
	 * 
	 * @author Rafael Corrêa
	 * @date 21/12/06
	 * 
	 * @return Collection<Pagamento>
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarPagamentoClienteCount(String idImovel,
			String idCliente, String idTipoRelacao, String localidadeInicial,
			String localidadeFinal, String idAvisoBancario,
			String idArrecadador, String periodoArrecadacaoInicial,
			String periodoArrecadacaoFinal, String periodoPagamentoInicio,
			String periodoPagamentoFim, Date dataPagamentoInicial,
			Date dataPagamentoFinal, String[] idsPagamentosSituacoes,
			String[] idsDebitosTipos, String[] idsArrecadacaoForma,
			String[] idsDocumentosTipos,
            String valorPagamentoInicial,
            String valorPagamentoFinal) throws ErroRepositorioException;

	/**
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * Pesquisa os pagamentos do tipo Aviso Bancario
	 * pesquisarPagamentoAvisoBancario
	 * 
	 * @author Vivianne Sousa
	 * @date 21/08/06
	 * 
	 * @return Collection<Pagamento>
	 * @throws ErroRepositorioException
	 */
	public Collection<Pagamento> pesquisarPagamentoAvisoBancarioParaPaginacao(
			String idImovel, String idCliente, String idTipoRelacao,
			String localidadeInicial, String localidadeFinal,
			String idAvisoBancario, String idArrecadador,
			String periodoArrecadacaoInicial, String periodoArrecadacaoFinal,
			String periodoPagamentoInicio, String periodoPagamentoFim,
			Date dataPagamentoInicial, Date dataPagamentoFinal,
			String[] idsPagamentosSituacoes, String[] idsDebitosTipos,
			String[] idsArrecadacaoForma, String[] idsDocumentosTipos,
			Integer numeroPagina, String valorPagamentoInicial, 
            String valorPagamentoFinal ) throws ErroRepositorioException;

	/**
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * Pesquisa os pagamentos do tipo Aviso Bancario
	 * pesquisarPagamentoAvisoBancario
	 * 
	 * @author Vivianne Sousa
	 * @date 21/08/06
	 * 
	 * @return Collection<Pagamento>
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarPagamentoAvisoBancarioCount(String idImovel,
			String idCliente, String idTipoRelacao, String localidadeInicial,
			String localidadeFinal, String idAvisoBancario,
			String idArrecadador, String periodoArrecadacaoInicial,
			String periodoArrecadacaoFinal, String periodoPagamentoInicio,
			String periodoPagamentoFim, Date dataPagamentoInicial,
			Date dataPagamentoFinal, String[] idsPagamentosSituacoes,
			String[] idsDebitosTipos, String[] idsArrecadacaoForma,
			String[] idsDocumentosTipos, String valorPagamentoInicial, 
            String valorPagamentoFinal) throws ErroRepositorioException;

	/**
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * Pesquisa os pagamentos do Imovel pesquisarPagamentoImovelParaPaginacao
	 * 
	 * @author Vivianne Sousa
	 * @date 22/08/06
	 * 
	 * @return Collection<Pagamento>
	 * @throws ErroRepositorioException
	 */

	public Integer pesquisarPagamentoImovelCount(String idImovel,
			String idCliente, String idTipoRelacao, String localidadeInicial,
			String localidadeFinal, String idAvisoBancario,
			String idArrecadador, String periodoArrecadacaoInicial,
			String periodoArrecadacaoFinal, String periodoPagamentoInicio,
			String periodoPagamentoFim, Date dataPagamentoInicial,
			Date dataPagamentoFinal, String[] idsPagamentosSituacoes,
			String[] idsDebitosTipos, String[] idsArrecadacaoForma,
			String[] idsDocumentosTipos, String valorPagamentoInicial, 
            String valorPagamentoFinal ) throws ErroRepositorioException;

	/**
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * Pesquisa os pagamentos do Imovel pesquisarPagamentoImovelParaPaginacao
	 * 
	 * @author Vivianne Sousa
	 * @date 22/08/06
	 * 
	 * @return Collection<Pagamento>
	 * @throws ErroRepositorioException
	 */

	public Collection<Pagamento> pesquisarPagamentoImovelParaPaginacao(
			String idImovel, String idCliente, String idTipoRelacao,
			String localidadeInicial, String localidadeFinal,
			String idAvisoBancario, String idArrecadador,
			String periodoArrecadacaoInicial, String periodoArrecadacaoFinal,
			String periodoPagamentoInicio, String periodoPagamentoFim,
			Date dataPagamentoInicial, Date dataPagamentoFinal,
			String[] idsPagamentosSituacoes, String[] idsDebitosTipos,
			String[] idsArrecadacaoForma, String[] idsDocumentosTipos,
			Integer numeroPagina, String valorPagamentoInicial, 
            String valorPagamentoFinal ) throws ErroRepositorioException;

	/**
	 * 
	 * Este caso de uso cria um filtro que será usado na pesquisa de pagamentos
	 * para o Relatório
	 * 
	 * @author Rafael Corrêa
	 * @date 29/08/2006
	 * 
	 * @param FiltroPagamento
	 * @return Collection<Pagamento>
	 * @throws ErroRepositorioException
	 */

	public Collection<Pagamento> pesquisarPagamentoImovelRelatorio(
			String idImovel, String idCliente, String idTipoRelacao,
			String localidadeInicial, String localidadeFinal,
			String idAvisoBancario, String idArrecadador,
			String periodoArrecadacaoInicial, String periodoArrecadacaoFinal,
			String periodoPagamentoInicio, String periodoPagamentoFim,
			Date dataPagamentoInicial, Date dataPagamentoFinal,
			String[] idsPagamentosSituacoes, String[] idsDebitosTipos,
			String[] idsArrecadacaoForma, String[] idsDocumentosTipos,
            String valorPagamentoInicial,
            String valorPagamentoFinal)
			throws ErroRepositorioException;

	/**
	 * Este caso de uso cria um sql que será usado na pesquisa de pagamentos
	 * para o Relatório
	 * 
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * Pesquisa os pagamentos do Cliente
	 * 
	 * @author Rafael Corrêa
	 * @date 12/12/06
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarPagamentoClienteRelatorio(
			String idImovel, String idCliente, String idTipoRelacao,
			String localidadeInicial, String localidadeFinal,
			String idAvisoBancario, String idArrecadador,
			String periodoArrecadacaoInicial, String periodoArrecadacaoFinal,
			String periodoPagamentoInicio, String periodoPagamentoFim,
			Date dataPagamentoInicial, Date dataPagamentoFinal,
			String[] idsPagamentosSituacoes, String[] idsDebitosTipos,
			String[] idsArrecadacaoForma, String[] idsDocumentosTipos,
            String valorPagamentoInicial,
            String valorPagamentoFinal)
			throws ErroRepositorioException;

	/**
	 * Este caso de uso cria um filtro que será usado na pesquisa de pagamentos
	 * para o Relatório
	 * 
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * Pesquisa os pagamentos do tipo Aviso Bancario
	 * pesquisarPagamentoAvisoBancario
	 * 
	 * @author Rafael Corrêa
	 * @date 29/08/06
	 * 
	 * @return Collection<Pagamento>
	 * @throws ErroRepositorioException
	 */
	public Collection<Pagamento> pesquisarPagamentoAvisoBancarioRelatorio(
			String idImovel, String idCliente, String idTipoRelacao,
			String localidadeInicial, String localidadeFinal,
			String idAvisoBancario, String idArrecadador,
			String periodoArrecadacaoInicial, String periodoArrecadacaoFinal,
			String periodoPagamentoInicio, String periodoPagamentoFim,
			Date dataPagamentoInicial, Date dataPagamentoFinal,
			String[] idsPagamentosSituacoes, String[] idsDebitosTipos,
			String[] idsArrecadacaoForma, String[] idsDocumentosTipos,
            String valorPagamentoInicial,
            String valorPagamentoFinal)
			throws ErroRepositorioException;

	/**
	 * Este caso de uso cria um filtro que será usado na pesquisa de pagamentos
	 * para o Relatório
	 * 
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * Pesquisa os pagamentos do tipo Debito a Cobrar do Cliente
	 * pesquisarPagamentoLocalidade
	 * 
	 * @author Rafael Corrêa
	 * @date 29/08/06
	 * 
	 * @return Collection<Pagamento>
	 * @throws ErroRepositorioException
	 */
	public Collection<Pagamento> pesquisarPagamentoMovimentoArrecadadorRelatorio(
			String idImovel, String idCliente, String idTipoRelacao,
			String localidadeInicial, String localidadeFinal,
			String idAvisoBancario, String idArrecadador,
			String periodoArrecadacaoInicial, String periodoArrecadacaoFinal,
			String periodoPagamentoInicio, String periodoPagamentoFim,
			Date dataPagamentoInicial, Date dataPagamentoFinal,
			String[] idsPagamentosSituacoes, String[] idsDebitosTipos,
			String[] idsArrecadacaoForma, String[] idsDocumentosTipos,
            String valorPagamentoInicial,
            String valorPagamentoFinal)
			throws ErroRepositorioException;

	/**
	 * Este caso de uso cria um filtro que será usado na pesquisa de pagamentos
	 * para o Relatório
	 * 
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * Pesquisa os pagamentos do tipo Debito a Cobrar do Cliente
	 * pesquisarPagamentoLocalidade
	 * 
	 * @author Roberta Costa
	 * @date 12/06/06
	 * 
	 * @return Collection<Pagamento>
	 * @throws ErroRepositorioException
	 */
	public Collection<Pagamento> pesquisarPagamentoLocalidadeRelatorio(
			String idImovel, String idCliente, String idTipoRelacao,
			String localidadeInicial, String localidadeFinal,
			String idAvisoBancario, String idArrecadador,
			String periodoArrecadacaoInicial, String periodoArrecadacaoFinal,
			String periodoPagamentoInicio, String periodoPagamentoFim,
			Date dataPagamentoInicial, Date dataPagamentoFinal,
			String[] idsPagamentosSituacoes, String[] idsDebitosTipos,
			String[] idsArrecadacaoForma, String[] idsDocumentosTipos,
            String valorPagamentoInicial,
            String valorPagamentoFinal)
			throws ErroRepositorioException;

	/**
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * Pesquisa os pagamentos do Imovel pesquisarPagamentoImovelParaPaginacao
	 * 
	 * @author Rafael Corrêa
	 * @date 29/08/06
	 * 
	 * @return Collection<Pagamento>
	 * @throws ErroRepositorioException
	 */

	public Integer pesquisarPagamentoMovimentoArrecadadorCount(String idImovel,
			String idCliente, String idTipoRelacao, String localidadeInicial,
			String localidadeFinal, String idAvisoBancario,
			String idArrecadador, String periodoArrecadacaoInicial,
			String periodoArrecadacaoFinal, String periodoPagamentoInicio,
			String periodoPagamentoFim, Date dataPagamentoInicial,
			Date dataPagamentoFinal, String[] idsPagamentosSituacoes,
			String[] idsDebitosTipos, String[] idsArrecadacaoForma,
			String[] idsDocumentosTipos, String valorPagamentoInicial, 
            String valorPagamentoFinal) throws ErroRepositorioException;

	/**
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * Pesquisa os pagamentos do Imovel pesquisarPagamentoImovelParaPaginacao
	 * 
	 * @author Vivianne Sousa
	 * @date 29/08/06
	 * 
	 * @return Collection<Pagamento>
	 * @throws ErroRepositorioException
	 */

	public Integer pesquisarPagamentoLocalidadeCount(String idImovel,
			String idCliente, String idTipoRelacao, String localidadeInicial,
			String localidadeFinal, String idAvisoBancario,
			String idArrecadador, String periodoArrecadacaoInicial,
			String periodoArrecadacaoFinal, String periodoPagamentoInicio,
			String periodoPagamentoFim, Date dataPagamentoInicial,
			Date dataPagamentoFinal, String[] idsPagamentosSituacoes,
			String[] idsDebitosTipos, String[] idsArrecadacaoForma,
			String[] idsDocumentosTipos, String valorPagamentoInicial, 
            String valorPagamentoFinal) throws ErroRepositorioException;

	public Integer verificarExistenciaGuiaDevolucao(Integer idGuiaDevolucao)
			throws ErroRepositorioException;

	/**
	 * Pesquisa os avisos bancários para o relatório através das opções
	 * selecionadas no Filtrar Aviso Bancário
	 * 
	 * @author Rafael Corrêa
	 * @date 04/09/06
	 * 
	 * @return Collection<AvisoBancarioRelatorioHelper>
	 * @throws ErroRepositorioException
	 */

	public Collection pesquisarAvisoBancarioRelatorio(
			AvisoBancarioHelper avisoBancarioHelper)
			throws ErroRepositorioException;

	/**
	 * Pesquisa os avisos deduções de um aviso bancário para o relatório através
	 * do id do aviso bancário
	 * 
	 * @author Rafael Corrêa
	 * @date 05/09/06
	 * 
	 * @return Collection<DeducoesRelatorioHelper>
	 * @throws ErroRepositorioException
	 */

	public Collection pesquisarAvisoDeducoesAvisoBancarioRelatorio(
			Integer idAvisoBancario) throws ErroRepositorioException;

	/**
	 * Pesquisa os avisos acertos de um aviso bancário para o relatório através
	 * do id do aviso bancário
	 * 
	 * @author Rafael Corrêa
	 * @date 05/09/06
	 * 
	 * @return Collection<AcertosRelatorioHelper>
	 * @throws ErroRepositorioException
	 */

	public Collection pesquisarAvisoAcertosAvisoBancarioRelatorio(
			Integer idAvisoBancario) throws ErroRepositorioException;

	/**
	 * 
	 * Pesquisa dos dados diários da arrecadação
	 * 
	 * [UC0333] Filtrar Dados Diários da Arrecadação
	 * 
	 * @author Rafael Santos
	 * @date 05/09/2006
	 * 
	 * @return
	 */
	public Collection filtrarDadosDiariosArrecadacao(
			String periodoArrecadacaoInicial, String periodoArrecadacaoFinal,
			String idLocalidade, String idGerenciaRegional,
			String idArrecadador, String idElo, String[] idsImovelPerfil,
			String[] idsLigacaoAgua, String[] idsLigacaoEsgoto,
			String[] idsDocumentosTipos, String[] idsCategoria,
			String[] idsEsferaPoder) throws ErroRepositorioException;

	/**
	 * Pesquisa dos dados diários de devolucao
	 * 
	 * [UC0333] Filtrar Dados Diários da Arrecadação
	 * 
	 * @author Francisco do Nascimento
	 * @date 21/07/2008
	 * 
	 * @return
	 */
	public Collection filtrarDevolucaoDadosDiarios(
			String periodoArrecadacaoInicial, String periodoArrecadacaoFinal,
			String idLocalidade, String idGerenciaRegional,
			String idArrecadador, String idElo, String[] idsImovelPerfil,
			String[] idsLigacaoAgua, String[] idsLigacaoEsgoto,
			String[] idsDocumentosTipos, String[] idsCategoria,
			String[] idsEsferaPoder) throws ErroRepositorioException;
	
	/**
	 * Pesquisa dos dados diários da arrecadação pela Gerencia
	 * 
	 * [UC0333] Filtrar Dados Diários da Arrecadação
	 * 
	 * @author Rafael Santos
	 * @date 05/09/2006
	 * 
	 * @return
	 */
	public Collection filtrarDadosDiariosArrecadacaoValoresDiarios(
			String idGerenciaRegional) throws ErroRepositorioException;

	/**
	 * Retornar Coleção do movimento do arrecadador
	 * 
	 * Seleciona Movimento Arrecadadores
	 * 
	 * @author Fernanda Paiva
	 * @date
	 * @throws ErroRepositorioException
	 */
	public Collection<ArrecadadorMovimento> retornarColecaoMovimentoArrecadadores(
			FiltroArrecadadorMovimento filtro, Integer numeroPagina)
			throws ErroRepositorioException;

	/**
	 * Retornar o valor do somatorio dos acertos daquele aviso bancario
	 * 
	 * @author Fernanda Paiva
	 * @date
	 * @throws ErroRepositorioException
	 */
	public BigDecimal pesquisarSomatorioAvisoAcerto(
			Integer indicadorCreditoDebito, Integer idAviso,
			Integer indicadorArrecadacaoDevolucao)
			throws ErroRepositorioException;

	/**
	 * Retornar os avisos bancario aberto e/ou fechado
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection filtrarAvisoBancarioAbertoFechadoFinal(
			AvisoBancarioHelper avisoBancarioHelper)
			throws ErroRepositorioException;

	/**
	 * Pesquisa os dados da Guia de Pagamento necessários para o relatório
	 * através do id da Guia de Pagamento
	 * 
	 * @author Vivianne Sousa
	 * @date 03/10/06
	 * 
	 * @return GuiaPagamentoRelatorioHelper
	 * @throws ErroRepositorioException
	 */

	public GuiaPagamentoRelatorioHelper pesquisarGuiaPagamentoRelatorio(
			Integer idGuiaPagamento) throws ErroRepositorioException;
	
	/**
	 * Pesquisa os dados da Guia de Pagamento Historico necessários para o relatório
	 * através do id da Guia de Pagamento Historico
	 * 
	 * @author Davi Menezes
	 * @date 01/11/11
	 * 
	 * @return GuiaPagamentoRelatorioHelper
	 * @throws ErroRepositorioException
	 */

	public GuiaPagamentoRelatorioHelper pesquisarGuiaPagamentoHistoricoRelatorio(
			Integer idGuiaPagamento) throws ErroRepositorioException;

	/**
	 * Pesquisa o nome do cliente da guia de pagamento através do id da Guia de
	 * Pagamento e com CRTP_ID com o valor correspondente a usuário(2)
	 * 
	 * @author Vivianne Sousa
	 * @date 04/10/06
	 * 
	 * @return String
	 * @throws ErroRepositorioException
	 */

	public String pesquisarNomeClienteGuiaPagamentoRelatorio(
			Integer idGuiaPagamento) throws ErroRepositorioException;

	/**
	 * Pesquisa os dados da Guia de Devolução necessários para o relatório
	 * através do id da Guia de Devolução
	 * 
	 * @author Ana Maria
	 * @date 05/10/06
	 * 
	 * @return GuiaDevolucaoRelatorioHelper
	 * @throws ErroRepositorioException
	 */

	public GuiaDevolucaoRelatorioHelper pesquisarGuiaDevolucaoRelatorio(
			Integer idGuiaDevolucao) throws ErroRepositorioException;

	/**
	 * Pesquisa os dados do Cliente pelo Imóvel
	 * 
	 * @author Ana Maria
	 * @date 06/10/06
	 * 
	 * @return Object[]
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarClienteImovel(Integer idImovel)
			throws ErroRepositorioException;

	/**
	 * Este caso de uso cria um filtro que será usado na pesquisa de pagamentos
	 * para pesquisar os pagamento historicos
	 * 
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * Pesquisa os pagamentos historicos do Imóvel
	 * 
	 * @author Rafael Santos
	 * @date 06/10/2006
	 * 
	 * @param FiltroPagamento
	 * @return Collection<Pagamento>
	 * @throws ControladorException
	 */
	public Collection<PagamentoHistorico> pesquisarPagamentoHistoricoImovel(
			String idImovel, String idCliente, String idTipoRelacao,
			String localidadeInicial, String localidadeFinal,
			String idAvisoBancario, String idArrecadador,
			String periodoArrecadacaoInicial, String periodoArrecadacaoFinal,
			String periodoPagamentoInicio, String periodoPagamentoFim,
			Date dataPagamentoInicial, Date dataPagamentoFinal,
			String[] idsPagamentosSituacoes, String[] idsDebitosTipos,
			String[] idsArrecadacaoForma, String[] idsDocumentosTipos)
			throws ErroRepositorioException;

	/**
	 * 
	 * Filtar a quantiade de pagamento historicos do imovel [UC0255] Filtrar
	 * Pagamentos
	 * 
	 * @author Rafael Santos
	 * @date 06/10/2006
	 * 
	 * @return Collection<Pagamento>
	 * @throws ErroRepositorioException
	 */

	public Integer pesquisarPagamentoHistoricoImovelCount(String idImovel,
			String idCliente, String idTipoRelacao, String localidadeInicial,
			String localidadeFinal, String idAvisoBancario,
			String idArrecadador, String periodoArrecadacaoInicial,
			String periodoArrecadacaoFinal, String periodoPagamentoInicio,
			String periodoPagamentoFim, Date dataPagamentoInicial,
			Date dataPagamentoFinal, String[] idsPagamentosSituacoes,
			String[] idsDebitosTipos, String[] idsArrecadacaoForma,
			String[] idsDocumentosTipos) throws ErroRepositorioException;

	/**
	 * Filtra os pagamento historicos do Imovel para paginação
	 * 
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * @author Rafael Santos
	 * @date 06/10/2006
	 * 
	 * @return Collection<Pagamento>
	 * @throws ErroRepositorioException
	 */

	public Collection<PagamentoHistorico> pesquisarPagamentoHistoricoImovelParaPaginacao(
			String idImovel, String idCliente, String idTipoRelacao,
			String localidadeInicial, String localidadeFinal,
			String idAvisoBancario, String idArrecadador,
			String periodoArrecadacaoInicial, String periodoArrecadacaoFinal,
			String periodoPagamentoInicio, String periodoPagamentoFim,
			Date dataPagamentoInicial, Date dataPagamentoFinal,
			String[] idsPagamentosSituacoes, String[] idsDebitosTipos,
			String[] idsArrecadacaoForma, String[] idsDocumentosTipos,
			Integer numeroPagina) throws ErroRepositorioException;

	/**
	 * Filtra os Pagamento Historicos do Cliente Conta
	 * 
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * @author Rafael Santos
	 * @date 06/10/2006
	 * 
	 * @param FiltroPagamento
	 * @return Collection<Pagamento>
	 * @throws ErroRepositorioException
	 */
	public Collection<PagamentoHistorico> pesquisarPagamentoHistoricoClienteConta(
			String idImovel, String idCliente, String idTipoRelacao,
			String localidadeInicial, String localidadeFinal,
			String idAvisoBancario, String idArrecadador,
			String periodoArrecadacaoInicial, String periodoArrecadacaoFinal,
			String periodoPagamentoInicio, String periodoPagamentoFim,
			Date dataPagamentoInicial, Date dataPagamentoFinal,
			String[] idsPagamentosSituacoes, String[] idsDebitosTipos,
			String[] idsArrecadacaoForma, String[] idsDocumentosTipos)
			throws ErroRepositorioException;

	/**
	 * Filtrar os pagamentos historicos do Cliente Guia Pagamento
	 * 
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * @author Rafael Santos
	 * @date 06/10/06
	 * 
	 * @param FiltroPagamento
	 * @return Collection<Pagamento>
	 * @throws ErroRepositorioException
	 */
	public Collection<PagamentoHistorico> pesquisarPagamentoHistoricoClienteGuiaPagamento(
			String idImovel, String idCliente, String idTipoRelacao,
			String localidadeInicial, String localidadeFinal,
			String idAvisoBancario, String idArrecadador,
			String periodoArrecadacaoInicial, String periodoArrecadacaoFinal,
			String periodoPagamentoInicio, String periodoPagamentoFim,
			Date dataPagamentoInicial, Date dataPagamentoFinal,
			String[] idsPagamentosSituacoes, String[] idsDebitosTipos,
			String[] idsArrecadacaoForma, String[] idsDocumentosTipos)
			throws ErroRepositorioException;

	/**
	 * Filtra os pagamentos historicos do debito a cobrar
	 * 
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * @author Rafael Santos
	 * @date 12/06/06,06/10/2006
	 * 
	 * @param FiltroPagamento
	 * @return Collection<Pagamento>
	 * @throws ErroRepositorioException
	 */
	public Collection<PagamentoHistorico> pesquisarPagamentoHistoricoClienteDebitoACobrar(
			String idImovel, String idCliente, String idTipoRelacao,
			String localidadeInicial, String localidadeFinal,
			String idAvisoBancario, String idArrecadador,
			String periodoArrecadacaoInicial, String periodoArrecadacaoFinal,
			String periodoPagamentoInicio, String periodoPagamentoFim,
			Date dataPagamentoInicial, Date dataPagamentoFinal,
			String[] idsPagamentosSituacoes, String[] idsDebitosTipos,
			String[] idsArrecadacaoForma, String[] idsDocumentosTipos)
			throws ErroRepositorioException;

	/**
	 * Filtrar a quantidade de pagamento historicos do cliente
	 * 
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * Pesquisa os pagamentos do Cliente
	 * 
	 * @author Rafael Santos
	 * @date 06/10/06
	 * 
	 * @return Collection<Pagamento>
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarPagamentoHistoricoClienteCount(String idImovel,
			String idCliente, String idTipoRelacao, String localidadeInicial,
			String localidadeFinal, String idAvisoBancario,
			String idArrecadador, String periodoArrecadacaoInicial,
			String periodoArrecadacaoFinal, String periodoPagamentoInicio,
			String periodoPagamentoFim, Date dataPagamentoInicial,
			Date dataPagamentoFinal, String[] idsPagamentosSituacoes,
			String[] idsDebitosTipos, String[] idsArrecadacaoForma,
			String[] idsDocumentosTipos) throws ErroRepositorioException;

	/**
	 * Filtra os pagamento historicos do cliente
	 * 
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * @author Rafael Santos
	 * @date 21/08/06,06/10/2006
	 * 
	 * @return Collection<Pagamento>
	 * @throws ErroRepositorioException
	 */
	public Collection<PagamentoHistorico> pesquisarPagamentoHistoricoCliente(
			String idImovel, String idCliente, String idTipoRelacao,
			String localidadeInicial, String localidadeFinal,
			String idAvisoBancario, String idArrecadador,
			String periodoArrecadacaoInicial, String periodoArrecadacaoFinal,
			String periodoPagamentoInicio, String periodoPagamentoFim,
			Date dataPagamentoInicial, Date dataPagamentoFinal,
			String[] idsPagamentosSituacoes, String[] idsDebitosTipos,
			String[] idsArrecadacaoForma, String[] idsDocumentosTipos,
			Integer numeroPagina) throws ErroRepositorioException;

	/**
	 * Filtra a quantiadade dos Pagamento Historicos da Localidade
	 * 
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * Pesquisa os pagamentos do Imovel pesquisarPagamentoImovelParaPaginacao
	 * 
	 * @author Rafael Santos
	 * @date 06/10/2006
	 * 
	 * @return Collection<Pagamento>
	 * @throws ErroRepositorioException
	 */

	public Integer pesquisarPagamentoHistoricoLocalidadeCount(String idImovel,
			String idCliente, String idTipoRelacao, String localidadeInicial,
			String localidadeFinal, String idAvisoBancario,
			String idArrecadador, String periodoArrecadacaoInicial,
			String periodoArrecadacaoFinal, String periodoPagamentoInicio,
			String periodoPagamentoFim, Date dataPagamentoInicial,
			Date dataPagamentoFinal, String[] idsPagamentosSituacoes,
			String[] idsDebitosTipos, String[] idsArrecadacaoForma,
			String[] idsDocumentosTipos) throws ErroRepositorioException;

	/**
	 * Filtra os Pagamento Historicos da Localidade
	 * 
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * @author Rafael Santos
	 * @date 06/10/2006
	 * 
	 * @param FiltroPagamento
	 * @return Collection<Pagamento>
	 * @throws ErroRepositorioException
	 */
	public Collection<PagamentoHistorico> pesquisarPagamentoHistoricoLocalidade(
			String idImovel, String idCliente, String idTipoRelacao,
			String localidadeInicial, String localidadeFinal,
			String idAvisoBancario, String idArrecadador,
			String periodoArrecadacaoInicial, String periodoArrecadacaoFinal,
			String periodoPagamentoInicio, String periodoPagamentoFim,
			Date dataPagamentoInicial, Date dataPagamentoFinal,
			String[] idsPagamentosSituacoes, String[] idsDebitosTipos,
			String[] idsArrecadacaoForma, String[] idsDocumentosTipos,
			Integer numeroPagina) throws ErroRepositorioException;

	/**
	 * Filtra oas pagamento historicos do Aviso Bancario
	 * 
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * @author Rafael Santos
	 * @date 06/10/2006
	 * 
	 * @param FiltroPagamento
	 * @return Collection<Pagamento>
	 * @throws ErroRepositorioException
	 */
	public Collection<PagamentoHistorico> pesquisarPagamentoHistoricoAvisoBancario(
			String idImovel, String idCliente, String idTipoRelacao,
			String localidadeInicial, String localidadeFinal,
			String idAvisoBancario, String idArrecadador,
			String periodoArrecadacaoInicial, String periodoArrecadacaoFinal,
			String periodoPagamentoInicio, String periodoPagamentoFim,
			Date dataPagamentoInicial, Date dataPagamentoFinal,
			String[] idsPagamentosSituacoes, String[] idsDebitosTipos,
			String[] idsArrecadacaoForma, String[] idsDocumentosTipos)
			throws ErroRepositorioException;

	/**
	 * Filtra a quantidade de pagamento historicos do avio bancario
	 * 
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * @author Rafael Santos
	 * @date 06/10/2006
	 * 
	 * @return Collection<Pagamento>
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarPagamentoHistoricoAvisoBancarioCount(
			String idImovel, String idCliente, String idTipoRelacao,
			String localidadeInicial, String localidadeFinal,
			String idAvisoBancario, String idArrecadador,
			String periodoArrecadacaoInicial, String periodoArrecadacaoFinal,
			String periodoPagamentoInicio, String periodoPagamentoFim,
			Date dataPagamentoInicial, Date dataPagamentoFinal,
			String[] idsPagamentosSituacoes, String[] idsDebitosTipos,
			String[] idsArrecadacaoForma, String[] idsDocumentosTipos)
			throws ErroRepositorioException;

	/**
	 * Filtra os pagamento historicos do aviso bancario para paginação
	 * 
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * @author Rafael Santos
	 * @date 06/10/2006
	 * 
	 * @return Collection<Pagamento>
	 * @throws ErroRepositorioException
	 */
	public Collection<PagamentoHistorico> pesquisarPagamentoHistoricoAvisoBancarioParaPaginacao(
			String idImovel, String idCliente, String idTipoRelacao,
			String localidadeInicial, String localidadeFinal,
			String idAvisoBancario, String idArrecadador,
			String periodoArrecadacaoInicial, String periodoArrecadacaoFinal,
			String periodoPagamentoInicio, String periodoPagamentoFim,
			Date dataPagamentoInicial, Date dataPagamentoFinal,
			String[] idsPagamentosSituacoes, String[] idsDebitosTipos,
			String[] idsArrecadacaoForma, String[] idsDocumentosTipos,
			Integer numeroPagina) throws ErroRepositorioException;

	/**
	 * Filtrar pagamentos historicos do movimento arrecador
	 * 
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * @author Rafael Santos
	 * @date 06/10/2006
	 * 
	 * @param FiltroPagamento
	 * @return Collection<Pagamento>
	 * @throws ErroRepositorioException
	 */
	public Collection<PagamentoHistorico> pesquisarPagamentoHistoricoMovimentoArrecadador(
			String idImovel, String idCliente, String idTipoRelacao,
			String localidadeInicial, String localidadeFinal,
			String idAvisoBancario, String idArrecadador,
			String periodoArrecadacaoInicial, String periodoArrecadacaoFinal,
			String periodoPagamentoInicio, String periodoPagamentoFim,
			Date dataPagamentoInicial, Date dataPagamentoFinal,
			String[] idsPagamentosSituacoes, String[] idsDebitosTipos,
			String[] idsArrecadacaoForma, String[] idsDocumentosTipos)
			throws ErroRepositorioException;

	/**
	 * Faz a pesquisa de devoluçãoHistorico fazendo os carregamentos de
	 * clienteContas, clienteImoveis, clientesGuiaPagamento
	 * 
	 * @author Vivianne Sousa
	 * @date 09/10/2006
	 * 
	 * @param FiltroDevolucaoHistorico
	 * @return Collection<DevolucaoHistorico>
	 * @throws ErroRepositorioException
	 */
	public Collection<DevolucaoHistorico> pesquisarDevolucaoHistorico(
			FiltroDevolucaoHistorico filtroDevolucaoHistorico)
			throws ErroRepositorioException;

	/**
	 * Este caso de uso cria um filtro que será usado na pesquisa de pagamentos
	 * histórico para o Relatório
	 * 
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * Pesquisa os pagamentos histórico do tipo Debito a Cobrar do Cliente
	 * 
	 * @author Vivianne Sousa
	 * @date 17/10/06
	 * 
	 * @return Collection<PagamentoHistorico>
	 * @throws ErroRepositorioException
	 */
	public Collection<PagamentoHistorico> pesquisarPagamentoHistoricoLocalidadeRelatorio(
			String idImovel, String idCliente, String idTipoRelacao,
			String localidadeInicial, String localidadeFinal,
			String idAvisoBancario, String idArrecadador,
			String periodoArrecadacaoInicial, String periodoArrecadacaoFinal,
			String periodoPagamentoInicio, String periodoPagamentoFim,
			Date dataPagamentoInicial, Date dataPagamentoFinal,
			String[] idsPagamentosSituacoes, String[] idsDebitosTipos,
			String[] idsArrecadacaoForma, String[] idsDocumentosTipos, String valorPagamentoInicial,
            String valorPagamentoFinal)
			throws ErroRepositorioException;

	/**
	 * Consulta dados da tabela dados diarios arrecadacao
	 * 
	 * @author Rafael Santos
	 * @created 21/10/2006
	 * 
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection consultarDadosDiarios(int idGerenciaRegional,
			int idLocalidade, int idElo) throws ErroRepositorioException;

	/**
	 * Pesquisa conta e agência do sistema de parâmetros
	 * 
	 * @author Ana Maria
	 * @date 23/10/06
	 * 
	 * @return Object[]
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarContaAgenciaSistemaParametro()
			throws ErroRepositorioException;

	/**
	 * Pesquisa id do lançamento contabil
	 * 
	 * @author Sávio Luiz
	 * @date 08/11/06
	 * 
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarIdLancamentoItemContabil(Integer idCreditoTipo)
			throws ErroRepositorioException;

	/**
	 * pesquisar descrição do Débito Automático
	 * 
	 * @author Sávio Luiz
	 * @date 22/11/06
	 * 
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public String pesquisarDescricaoDebitoAutomatico(Integer codigoRetorno)
			throws ErroRepositorioException;

	/**
	 * Pesquisa a lista de ano/mês de arrecadaçaõ menores e igual ao ano/mês de
	 * arrecadação atual.
	 * 
	 * [UC0300] Classificar Pagamentos e Devoluções
	 * 
	 * @author Raphael Rossiter, Pedro Alexandre
	 * @date 18/04/2006, 29/11/2006
	 * 
	 * @param anoMesArrecadacaoAtual
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarAnoMesArrecadacaoMenorIgualAtual(
			Integer anoMesArrecadacaoAtual) throws ErroRepositorioException;

	/**
	 * [UC0263] Filtrar Movimento dos Arrecadadores
	 * 
	 * @author Vivianne Sousa
	 * @date 30/11/06
	 * 
	 * @param codigoBanco
	 * @param codigoRemessa
	 * @param descricaoIdentificacaoServico
	 * @param numeroSequencialArquivo
	 * @param dataGeracaoInicio
	 * @param dataGeracaoFim
	 * @param ultimaAlteracaoInicio
	 * @param ultimaAlteracaoFim
	 * @param descricaoOcorrencia
	 * @param indicadorAceitacao
	 * @param numeroPagina
	 * @param indicadorAbertoFechado
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection filtrarMovimentoArrecadadorParaPaginacao(
			String codigoBanco, String codigoRemessa,
			String descricaoIdentificacaoServico,
			String numeroSequencialArquivo, Date dataGeracaoInicio,
			Date dataGeracaoFim, Date ultimaAlteracaoInicio,
			Date ultimaAlteracaoFim, String descricaoOcorrencia,
			String indicadorAceitacao, Integer numeroPagina,
			String indicadorAbertoFechado) throws ErroRepositorioException;

	/**
	 * [UC0263] Filtrar Movimento dos Arrecadadores
	 * 
	 * @author Vivianne Sousa
	 * @date 30/11/06
	 * 
	 * @param codigoBanco
	 * @param codigoRemessa
	 * @param descricaoIdentificacaoServico
	 * @param numeroSequencialArquivo
	 * @param dataGeracaoInicio
	 * @param dataGeracaoFim
	 * @param ultimaAlteracaoInicio
	 * @param ultimaAlteracaoFim
	 * @param descricaoOcorrencia
	 * @param indicadorAceitacao
	 * @param indicadorAbertoFechado
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer filtrarMovimentoArrecadadoresCount(String codigoBanco,
			String codigoRemessa, String descricaoIdentificacaoServico,
			String numeroSequencialArquivo, Date dataGeracaoInicio,
			Date dataGeracaoFim, Date ultimaAlteracaoInicio,
			Date ultimaAlteracaoFim, String descricaoOcorrencia,
			String indicadorAceitacao, String indicadorAbertoFechado)
			throws ErroRepositorioException;

	/**
	 * retorna o somatorio de PGMT_VLPAGAMENTO da tabela PAGAMENTO com AMIT_ID
	 * =AMIT_ID da tabela ARRECADADOR_MOVIMENTO_ITEM
	 * 
	 * [UC0254] Efetuar Análise do Movimento dos Arrecadadores
	 * 
	 * @author Vivianne Sousa
	 * @date 05/12/2006
	 * 
	 * @param idArrecadadorMovimentoItem
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal recuperaValorPagamentoArrecadadorMovimentoItem(
			Integer idArrecadadorMovimentoItem) throws ErroRepositorioException;

	/**
	 * retorna a decrição da Forma de Arrecadação (arfm_dsarrecadacaoforma) da
	 * tabela ARRECADACAO_FORMA a partir do codigoArrecadacaoForma
	 * (arfm_cdarrecadacaoforma) passado
	 * 
	 * [UC0262] Distribuir Dados do Registro do Movimento do Arrecadador
	 * 
	 * @author Vivianne Sousa
	 * @date 06/12/2006
	 * 
	 * @param codigoArrecadacaoForma
	 * @return
	 * @throws ErroRepositorioException
	 */
	public String recuperaDescricaoArrecadacaoForma(
			String codigoArrecadacaoForma) throws ErroRepositorioException;

	/**
	 * [UC0300] Classificar Pagamentos e Devoluções
	 * 
	 * Atualiza a situacao atual dos pagamentos (PGST_IDATUAL) (tabela
	 * PAGAMENTO_SITUACAO)
	 * 
	 * [SF0008] Processar Pagamento a Maior ou a Menor
	 * 
	 * @author Raphael Rossiter, Pedro Alexandre
	 * @date 19/04/2006, 30/11/2006
	 * 
	 * @param colecaoIdsPagamentos
	 * @param pagamentoSituacao
	 * @throws ErroRepositorioException
	 */
	public void atualizarSituacaoPagamento(Collection colecaoIdsPagamentos,
			Integer pagamentoSituacao) throws ErroRepositorioException;

	/**
	 * Atualiza o valor excedente e a situação dos pagamentos informados para o
	 * tipode situação informada.
	 * 
	 * @author Raphael Rossiter, Pedro Alexandre
	 * @date 18/04/2006, 12/12/2006
	 * 
	 * @param colecaoPagamento
	 * @param pagamentoSituacao
	 * @throws ErroRepositorioException
	 */
	public void atualizarSituacaoEValorExcedentePagamento(
			Collection<Pagamento> colecaoPagamento, Integer pagamentoSituacao)
			throws ErroRepositorioException;

	/**
	 * Pesquisa a lista de ano/mês de arrecadação menores e igual ao ano/mês de
	 * arrecadação atual e igual ao id do imóvel informado.
	 * 
	 * [UC0300] Classificar Pagamentos e Devoluções
	 * 
	 * @author Raphael Rossiter, Pedro Alexandre
	 * @date 18/04/2006, 29/11/2006
	 * 
	 * @param anoMesArrecadacaoAtual
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarAnoMesArrecadacaoMenorIgualAtualPorImovel(
			Integer anoMesArrecadacaoAtual, Integer idImovel)
			throws ErroRepositorioException;

	/**
	 * Pesquisar uma coleção de ids de localidades que possuem pagamentos
	 * 
	 * [UC0300] Classificar Pagamentos e Devoluções
	 * 
	 * @author Pedro Alexandre
	 * @date 29/11/2006
	 * 
	 * @param idLocalidade
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarIdsImovelPorLocalidade(Integer idLocalidade,
			Integer numeroPaginas, Integer quantidadeRegistros)
			throws ErroRepositorioException;

	/**
	 * Pesquisar os ids das localidades que possuem pagamentos
	 * 
	 * [UC0301] Gerar Dados Diários da Arrecadação
	 * 
	 * @author Pedro Alexandre
	 * @date 04/12/2006
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> pesquisarIdsLocalidadeComPagamentosOuDevolucoes()
			throws ErroRepositorioException;

	/**
	 * Pesquisar os ano/mês de referência do pagamentos para um imóvel e ano/mês
	 * de arrecadação informados para o tipo de documento informado.
	 * 
	 * [UC0300] Classificar Pagamentos e Devoluções
	 * 
	 * @author Pedro Alexandre
	 * @date 06/12/2006
	 * 
	 * @param anoMesArrecadacaoAtual
	 * @param idImovel
	 * @param idDocumentoTipo
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarAnoMesReferenciaPagamentoParaImovel(
			Integer anoMesArrecadacaoAtual, Integer idImovel,
			Integer idDocumentoTipo) throws ErroRepositorioException;

	/**
	 * Pesquisa a esfera do poder do cliente responsável pelo imóvel.
	 * 
	 * [UC0301] - Gerar Dados Diários da Arrecadacao
	 * 
	 * @author Pedro Alexandre
	 * @date 05/12/2006
	 * 
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarEsferaPoderClienteResponsavelPeloImovel(
			Integer idImovel) throws ErroRepositorioException;

	/**
	 * Atualiza a situção dos pagamentos informados.
	 * 
	 * [UC0300] Classificar Pagamentos e Devoluções
	 * 
	 * @author Raphael Rossiter, Pedro Alexandre
	 * @date 18/04/2006, 12/12/2006
	 * 
	 * @param pagamentoSituacao
	 * @param colecaoPagamentos
	 * @throws ErroRepositorioException
	 */
	public void atualizarSituacaoPagamento(Integer pagamentoSituacao,
			Collection<Pagamento> colecaoPagamentos)
			throws ErroRepositorioException;
	
	/**
	 * Este caso de uso cria um filtro que será usado na pesquisa de pagamentos
	 * para o Relatório
	 * 
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * Pesquisa os pagamentos do tipo Debito a Cobrar do Cliente
	 * pesquisarPagamentoLocalidade
	 * 
	 * @author Rafael Corrêa
	 * @date 12/12/06
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarPagamentoLocalidadeAmbosRelatorio(
			String idImovel, String idCliente, String idTipoRelacao,
			String localidadeInicial, String localidadeFinal,
			String idAvisoBancario, String idArrecadador,
			String periodoArrecadacaoInicial, String periodoArrecadacaoFinal,
			String periodoPagamentoInicio, String periodoPagamentoFim,
			Date dataPagamentoInicial, Date dataPagamentoFinal,
			String[] idsPagamentosSituacoes, String[] idsDebitosTipos,
			String[] idsArrecadacaoForma, String[] idsDocumentosTipos,
            String valorPagamentoInicial,
            String valorPagamentoFinal )
			throws ErroRepositorioException;
	
	/**
	 * Pesquisa o imóvel pelo id fazendo os carregamentos necessários
	 * 
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * @author Rafael Corrêa
	 * @date 16/12/06
	 * 
	 * @return Imovel
	 * @throws ErroRepositorioException
	 */
	public Imovel pesquisarImovelPagamento(Integer idImovel)
			throws ErroRepositorioException;
	
	/**
	 * Pesquisa o cliente pelo id fazendo os carregamentos necessários
	 * 
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * @author Rafael Corrêa
	 * @date 16/12/06
	 * 
	 * @return Cliente
	 * @throws ErroRepositorioException
	 */
	public Cliente pesquisarClientePagamento(Integer idCliente)
			throws ErroRepositorioException;
	
	/**
	 * Pesquisa o endereço de correspondência do cliente pelo seu id fazendo os carregamentos necessários
	 * 
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * @author Rafael Corrêa
	 * @date 16/12/06
	 * 
	 * @return ClienteEndereco
	 * @throws ErroRepositorioException
	 */
	public ClienteEndereco pesquisarClienteEnderecoPagamento(Integer idCliente)
			throws ErroRepositorioException;
	
	/**
	 * Pesquisa o telefone padrão do cliente pelo seu id fazendo os carregamentos necessários
	 * 
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * @author Rafael Corrêa
	 * @date 16/12/06
	 * 
	 * @return ClienteFone
	 * @throws ErroRepositorioException
	 */
	public ClienteFone pesquisarClienteFonePagamento(Integer idCliente)
			throws ErroRepositorioException;
	
	/**
	 * Pesquisa os clientes do imóvel pelo seu id do imóvel fazendo os carregamentos necessários
	 * 
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * @author Rafael Corrêa
	 * @date 16/12/06
	 * 
	 * @return Collection<ClienteImovel>
	 * @throws ErroRepositorioException
	 */
	public Collection<ClienteImovel> pesquisarClientesImoveisPagamento(Integer idImovel)
			throws ErroRepositorioException;
	
	/**
	 * Este caso de uso cria um filtro que será usado na pesquisa de pagamentos
	 * 
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * Pesquisa os pagamentos do tipo Debito a Cobrar do Cliente
	 * pesquisarPagamentoLocalidade
	 * 
	 * @author Rafael Corrêa
	 * @date 12/06/06
	 * 
	 * @return Collection<Pagamento>
	 * @throws ErroRepositorioException
	 */
	public Collection<Pagamento> pesquisarPagamentoMovimentoArrecadadorParaPaginacao(
			String idImovel, String idCliente, String idTipoRelacao,
			String localidadeInicial, String localidadeFinal,
			String idAvisoBancario, String idArrecadador,
			String periodoArrecadacaoInicial, String periodoArrecadacaoFinal,
			String periodoPagamentoInicio, String periodoPagamentoFim,
			Date dataPagamentoInicial, Date dataPagamentoFinal,
			String[] idsPagamentosSituacoes, String[] idsDebitosTipos,
			String[] idsArrecadacaoForma, String[] idsDocumentosTipos, Integer numeroPagina, 
            String valorPagamentoInicial, 
            String valorPagamentoFinal)
			throws ErroRepositorioException;

    

    /**
     * [UC0276] - Encerrar Arrecadação do Mês
     * 
     * Verifica se já existe resumo da arrecadação para o ano/mês de referência
     * da arrecadação
     * 
     * [FS0003] - Verificar a existência do resumo da arrecadação
     * 
     * @author Pedro Alexandre
     * @date 16/05/2006
     *
     * @param anoMesReferenciaArrecadacao
     * @param idLocalidade
     * @return
     * @throws ErroRepositorioException
     */
    public Collection pesquisarResumoArrecadacaoPorAnoMesArrecadacao(Integer anoMesReferenciaArrecadacao,Integer idLocalidade) throws ErroRepositorioException ;

    /**
     * Pesquisa uma coleção de ids das categorias cadastradas
     *
     * [UC0276] Encerrar Arrecadação do Mês
     *
     * @author Pedro Alexandre
     * @date 15/12/2006
     *
     * @return
     * @throws ErroRepositorioException
     */
    public Collection<Integer> pesquisarIdsCategorias() throws ErroRepositorioException ;
    
    /**
     * Pesquisa uma coleção de ids dos lançamentos de itens contábeis cadastrados
     *
     * [UC0276] Encerrar Arrecadação do Mês
     *
     * @author Pedro Alexandre
     * @date 15/12/2006
     *
     * @return
     * @throws ErroRepositorioException
     */
    public Collection pesquisarDadosLancamentosItemContabil() throws ErroRepositorioException ;

    /**
     * pesquisa a lista dos acertos da Arrecadação/Devolucao do Aviso Bancario
     * 
     *[UC0268] - Apresentar Análise do Aviso Bancário
     * 
     * @author Vivianne Sousa
     * @date 13/12/2006
     * 
     * @param idAvisoBancario
     * @return
     * @throws ErroRepositorioException
     */
     public Collection pesquisarAcertosAvisoBancario(Integer idAvisoBancario,
   		  Integer indicadorArrecadacaoDevolucao) 
     	throws ErroRepositorioException;
     
    /**
     * O sistema seleciona a lista de pagamentos associados ao aviso bancário 
     * a partir da tabela PAGAMENTO com AVBC_ID=AVBC_ID da tabela AVISO_BANCARIO 
     * classificados por LOCA_ID ,IMOV_ID e PGMT_AMREFERENCIAPAGAMENTO
     * 
     * [UC0268] - Apresentar Análise do Aviso Bancário
     * 
     * @author Vivianne Sousa
     * @date 15/12/2006
     * 
     * @return Collection
     * @throws ErroRepositorioException
     */
    public Collection pesquisarPagamentoAvisoBancario(Integer idAvisoBancario) 
    	throws ErroRepositorioException;
    
    /**
     * O sistema seleciona a lista de desvoluções associados ao aviso bancário 
     * a partir da tabela DEVOLUCAO com AVBC_ID=AVBC_ID da tabela AVISO_BANCARIO 
     * 
     * [UC0268] - Apresentar Análise do Aviso Bancário
     * 
     * @author Vivianne Sousa
     * @date 15/12/2006
     * 
     * @return Collection
     * @throws ErroRepositorioException
     */
    public Collection pesquisarDevolucaoAvisoBancario(Integer idAvisoBancario) 
    	throws ErroRepositorioException;
    
    /**
     * Pesquisa os avisos deduções de um aviso bancário para o relatório através
     * do id do aviso bancário
     * 
     *[UC0268] - Apresentar Análise do Aviso Bancário
     * 
     * @author Vivianne Sousa
     * @date 13/12/2006
     * 
     * @param idAvisoBancario
     * @return
     * @throws ErroRepositorioException
     */
     public Collection pesquisarDeducoesAvisoBancario(Integer idAvisoBancario) 
     	throws ErroRepositorioException;
     
     /**
      *[UC0268] - Apresentar Análise do Aviso Bancário
      * 
      * @author Vivianne Sousa
      * @date 13/12/2006
      * 
      * @param idAvisoBancario
      * @return
      * @throws ErroRepositorioException
      */
     public Object[] pesquisarValorAcertosAvisoBancario(Integer idAvisoBancario)
     	throws ErroRepositorioException ;
     
     /**
      * somatorio do valor das deduções existentes para o aviso bancario
      * 
      * [UC0268] - Apresentar Análise do Aviso Bancário
      * 
      * @author Vivianne Sousa
      * @date 13/12/2006
      * 
      * @param idAvisoBancario
      * @return
      * @throws ErroRepositorioException
      */
     public BigDecimal pesquisarSomatorioDeducoesAvisoBancario(Integer idAvisoBancario) 
     	throws ErroRepositorioException ;
 	
     /**
      * [UC0268] - Apresentar Análise do Aviso Bancário
      * 
      * @author Vivianne Sousa
      * @date 13/12/2006
      * 
      * @param idAvisoBancario
      * @return
      * @throws ErroRepositorioException
      */
     public Object[] pesquisarAvisoBancario(Integer idAvisoBancario)
     	throws ErroRepositorioException;
     
     /**
      * Pesquisa alguns valores necessarios para 
      * obter a situação do aviso bancario, se aberto ou fechado
      * 
      * [UC0254] - Efetuar Análise do Movimento dos Arrecadadores
      * 
      * @author Vivianne Sousa
      * @date 11/12/2006
      * 
      * @param idAvisoBancario
      * @return
      * @throws ErroRepositorioException
      */
     public Object[] pesquisarAvisoBancarioAvisoAcertos(Integer idAvisoBancario)
     	throws ErroRepositorioException;
     
     /**
      * Sequencial do tipo lançamento igual a 1200
       * 
       * [UC0276] - Encerrar Arrecadação do Mês
       * 
       * Pesquisa os daods de ContaImpostosDeduzidos dos pagamentos
       * classificados de contas para acumular o valor do imposto por
       * localidade e categoria e tipo de imposto.
       * 
       * @author Pedro ALexandre
       * @date 15/12/2006
       *
       * @param idLocalidade
       * @param anoMesReferenciaArrecadacao
       * @param idTipoImposto
       * @return
       * @throws ErroRepositorioException
       */
      public Collection pesquisarContasImpostosDeduzidosPagamentosClassificadosContaPorTipoImposto(
    		  Integer idLocalidade, 
    		  Integer anoMesReferenciaArrecadacao,
    		  Integer idTipoImposto) throws ErroRepositorioException ;

      /**
       * <Breve descrição sobre o caso de uso>
       *
       * <Identificador e nome do caso de uso>
       *
       * @author Pedro Alexandre
       * @date 18/12/2006
       *
       * @param anoMesReferenciaArrecadacao
       * @param idLocalidade
       * @param idSituacaoAnterior
       * @return
       * @throws ErroRepositorioException
       */
      public Collection pesquisarPagamentosNaoClassificadosMesPorSituacaoAnterior(Integer anoMesReferenciaArrecadacao, Integer idLocalidade, Integer idSituacaoAnterior) throws ErroRepositorioException ;

      /**
       * <Breve descrição sobre o caso de uso>
       *
       * <Identificador e nome do caso de uso>
       *
       * @author Pedro Alexandre
       * @date 18/12/2006
       *
       * @param anoMesReferenciaArrecadacao
       * @param idLocalidade
       * @param idDevolucaoSituacaoAtual
       * @return
       * @throws ErroRepositorioException
       */
      public Collection pesquisarDevolucoesNaoClassificadasMesPorSituacaoAtual(Integer anoMesReferenciaArrecadacao, Integer idLocalidade, Integer idDevolucaoSituacaoAtual) throws ErroRepositorioException ;

      /**
       * <Breve descrição sobre o caso de uso>
       *
       * <Identificador e nome do caso de uso>
       *
       * @author Pedro Alexandre
       * @date 18/12/2006
       *
       * @param idLocalidade
       * @param anoMesReferenciaArrecadacao
       * @param idImpostoTipo
       * @return
       * @throws ErroRepositorioException
       */
      public Collection pesquisarContasImpostosDeduzidosPagamentosContasEfetuadosEmMesesAnterioresClassificadosMesPorTipoImposto(Integer idLocalidade, Integer anoMesReferenciaArrecadacao, Integer idImpostoTipo) throws ErroRepositorioException ;

      /**
       * <Breve descrição sobre o caso de uso>
       *
       * <Identificador e nome do caso de uso>
       *
       * @author Pedro Alexandre
       * @date 18/12/2006
       *
       * @param idLocalidade
       * @param anoMesReferenciaArrecadacao
       * @param idPagamentoSituacaoAnterior
       * @return
       * @throws ErroRepositorioException
       */
      public Collection pesquisarPagamentosNaoClassificadosComBaixaComandadaPorSituacaoAnterior(Integer idLocalidade, Integer anoMesReferenciaArrecadacao, Integer idPagamentoSituacaoAnterior) throws ErroRepositorioException ;

      /**
       * <Breve descrição sobre o caso de uso>
       *
       * <Identificador e nome do caso de uso>
       *
       * @author Pedro Alexandre
       * @date 18/12/2006
       *
       * @param anoMesReferenciaArrecadacao
       * @param idLocalidade
       * @param idPagamentoSituacaoAtual
       * @return
       * @throws ErroRepositorioException
       */
      public Collection pesquisarPagamentosNaoClassificadosMesEMesesAnterioresPorSituacaoAtual(Integer anoMesReferenciaArrecadacao, Integer idLocalidade, Integer idPagamentoSituacaoAtual) throws ErroRepositorioException ;

      /**
       * <Breve descrição sobre o caso de uso>
       *
       * <Identificador e nome do caso de uso>
       *
       * @author Pedro ALexandre
       * @date 18/12/2006
       *
       * @param anoMesReferenciaArrecadacao
       * @param idLocalidade
       * @param idDevolucaoSituacaoAtual
       * @return
       * @throws ErroRepositorioException
       */
      public Collection pesquisarDevolucoesNaoClassificadasMesEAnterioresPorSituacaoAtual(Integer anoMesReferenciaArrecadacao, Integer idLocalidade, Integer idDevolucaoSituacaoAtual) throws ErroRepositorioException ;

      /**
       * <Breve descrição sobre o caso de uso>
       *
       * <Identificador e nome do caso de uso>
       *
       * @author Pedro Alexandre
       * @date 16/12/2006
       *
       * @param anoMesReferenciaArrecadacao
       * @param idLocalidade
       * @param idSituacaoAtual
       * @return
       * @throws ErroRepositorioException
       */
      public Collection pesquisarPagamentosNaoClassificadosMesPorSituacaoAtual(Integer anoMesReferenciaArrecadacao, Integer idLocalidade, Integer idSituacaoAtual) throws ErroRepositorioException ;

      /**
       * Exclui os dados diários da arrecadação do ano/mês da arrecadação corrente por localidade
       * 
       * [UC0301] Gerar Dados Diários da Arrecadação
       * 
       * @author Pedro Alexandre
       * @date 11/04/2006
       *
       * @param anoMesReferenciaArrecadacao
       * @param idLocalidade
       * @throws ErroRepositorioException
       */
      public void excluirDadosDiariosArrecadacaoPorAnoMesArrecadacaoPorLocalidade(int anoMesReferenciaArrecadacao, Integer idLocalidade) throws ErroRepositorioException ;

      /**
       * [UC0263] Filtrar Movimento dos Arrecadadores
       * 
       * @author Vivianne Sousa
       * @date 30/11/06
       * 
       * @param codigoBanco
       * @param codigoRemessa
       * @param descricaoIdentificacaoServico
       * @param numeroSequencialArquivo
       * @param dataGeracaoInicio
       * @param dataGeracaoFim
       * @param ultimaAlteracaoInicio
       * @param ultimaAlteracaoFim
       * @param descricaoOcorrencia
       * @param indicadorAceitacao
       * @param indicadorAbertoFechado
       * @return
       * @throws ErroRepositorioException
       */
      public Collection filtrarMovimentoArrecadadorParaRelatorio(String codigoBanco, String codigoRemessa, 
    		  String descricaoIdentificacaoServico, String numeroSequencialArquivo, Date dataGeracaoInicio, 
    		  Date dataGeracaoFim, Date ultimaAlteracaoInicio, Date ultimaAlteracaoFim, String descricaoOcorrencia,
    		  String indicadorAceitacao, String indicadorAbertoFechado) throws ErroRepositorioException;
      
      /**
       * [UC0263] Filtrar Movimento dos Arrecadadores
       * 
       * @author Vivianne Sousa
       * @date  04/01/07
       * 
       * @param codigoBanco
       * @param codigoRemessa
       * @param descricaoIdentificacaoServico
       * @param numeroSequencialArquivo
       * @param dataGeracaoInicio
       * @param dataGeracaoFim
       * @param ultimaAlteracaoInicio
       * @param ultimaAlteracaoFim
       * @param descricaoOcorrencia
       * @param indicadorAceitacao
       * @param indicadorAbertoFechado
       * 
       * @throws ControladorException
       */
      public Integer filtrarMovimentoArrecadadoresRelatorioCount(String codigoBanco, String codigoRemessa, 
      		String descricaoIdentificacaoServico, String numeroSequencialArquivo, Date dataGeracaoInicio, 
      		Date dataGeracaoFim, Date ultimaAlteracaoInicio, Date ultimaAlteracaoFim, String descricaoOcorrencia, 
      		String indicadorAceitacao, String indicadorAbertoFechado) throws ErroRepositorioException;
      
      /**
       * [UC0276] Encerrar Arrecadação do Mês
       *
       * Pesquisa a coleção de guias de pagamento categoria 
       * para o id da guia informada.
       *
       * @author Pedro Alexandre
       * @date 09/01/2007
       *
       * @param idGuiaPagamento
       * @return
       * @throws ErroRepositorioException
       */
      public Collection pesquisarGuiaPagamentoCategoria(Integer idGuiaPagamento) throws ErroRepositorioException ;

      /**
       * [UC0276] Encerrar Arrecadação do Mês
       *
       * Pesquisa a coleção de cliente de guias de pagamento  
       * para o id da guia informada.
       *
       * @author Pedro Alexandre
       * @date 09/01/2007
       *
       * @param idGuiaPagamento
       * @return
       * @throws ErroRepositorioException
       */
      public Collection pesquisarClienteGuiaPagamento(Integer idGuiaPagamento) throws ErroRepositorioException ;

      /**
       * [UC0276] Encerrar Arrecadação do Mês
       *
       * Para cada guia de pagamento transferida para o histórico 
       * atualiza o indicador de que a guia de pagamento está no histórico.
       *
       * @author Pedro Alexandre
       * @date 09/01/2007
       *
       * @param idsGuiasPagamento
       * @throws ErroRepositorioException
       */
      public void atualizarIndicadorGuiaPagamentoNoHistorico(Collection idsGuiasPagamento) throws ErroRepositorioException ;

      /**
       * [UC0276] Encerrar Arrecadação do Mês
       *
       * Atualiza o ano/mês de referência da arrecadação.
       *
       * @author Pedro Alexandre
       * @date 09/01/2007
       *
       * @param anoMesArrecadacaoAtual
       * @param anoMesArrecadacaoNovo
       * @throws ErroRepositorioException
       */
      public void atualizarAnoMesArrecadacao(int anoMesArrecadacaoAtual, int anoMesArrecadacaoNovo) throws ErroRepositorioException ;

      /**
       * [UC0276] Encerrar Arrecadação do Mês
       *
       * Pesquisa as contas correspondentes aos pagamentos classificados de conta 
       * e os pagamentos anteriores de conta classificados no mês.
       *
       * @author Pedro Alexandre
       * @date 09/01/2007
       *
       * @param anoMesReferenciaArrecadacao
       * @param idLocalidade
       * @param numeroIndice
       * @param quantidadeRegistros
       * @return
       * @throws ErroRepositorioException
       */

      public Collection pesquisarContasDePagamentosClassificadosContaEPagamentosAnterioresContaClassificadosNoMes(Integer anoMesReferenciaArrecadacao, Integer idLocalidade, Integer numeroIndice, Integer quantidadeRegistros, Integer idSetorComercial, boolean dividaAtiva) throws ErroRepositorioException ;


      /**
       * [UC0276] Encerrar Arrecadação do Mês
       *
       * Pesquisa as guias de pagamento correspondentes aos pagamentos classificados de guia de 
       * pagamento e aos pagamentos anteriores de guia de pagamento classificados no mês.
       *
       * @author Pedro Alexandre
       * @date 09/01/2007
       *
       * @param anoMesReferenciaArrecadacao
       * @param idLocalidade
       * @param numeroIndice
       * @param quantidadeRegistros
       * @return
       * @throws ErroRepositorioException
       */
      public Collection<GuiaPagamento> pesquisarGuiasPagamentoDePagamentosClassificadosGuiasPagamentoEPagamentosAnterioresGuiaPagamentoClassificadosNoMes(Integer anoMesReferenciaArrecadacao, Integer idLocalidade, Integer numeroIndice, Integer quantidadeRegistros) throws ErroRepositorioException ;

      /**
       * [UC0276] Encerrar Arrecadação do Mês
       *
       * Pesquisar os pagamentos classificados ou com valor excedente baixado e com 
       * valor excedente maior do que zero.
       *
       * @author Pedro Alexandre
       * @date 10/01/2007
       *
       * @param anoMesReferenciaArrecadacao
       * @param idLocalidade
       * @param numeroIndice
       * @param quantidadeRegistros
       * @return
       * @throws ErroRepositorioException
       */
      public Collection<Integer> pesquisarPagamentosClassificadosOuValorExcedenteBaixado(Integer anoMesReferenciaArrecadacao, Integer idLocalidade, int numeroIndice, int quantidadeRegistros) throws ErroRepositorioException ;

      /**
       * [UC0276] Encerrar Arrecadação do Mês
       *
       * Pesquisa as devoluções classificadas para transferir para o histórico.
       *
       * @author Pedro Alexandre
       * @date 10/01/2007
       *
       * @param anoMesReferenciaArrecadacao
       * @param idLocalidade
       * @param numeroIndice
       * @param quantidadeRegistros
       * @return
       * @throws ErroRepositorioException
       */
      public Collection<Devolucao> pesquisarDevolucoesClassificadasPorLocalidade(Integer anoMesReferenciaArrecadacao, Integer idLocalidade, Integer numeroIndice, Integer quantidadeRegistros) throws ErroRepositorioException ;

      /**
       * O sistema seleciona a lista de pagamentos associados ao aviso bancário 
       * a partir da tabela PAGAMENTO com AVBC_ID=AVBC_ID da tabela AVISO_BANCARIO 
       * 
       * @author Vivianne Sousa
       * @date 17/01/2007
       * 
       * @return Collection
       * @throws ErroRepositorioException
       */
      public Collection pesquisarPagamentoPorAvisoBancario(Integer idAvisoBancario) 
      	throws ErroRepositorioException;
      
  	/**
  	 * @author Ana Maria
  	 * @date 29/01/2007
  	 * 
  	 * @param idGuiaPagamento
  	 * 
  	 * @return Collection
  	 * @throws ErroRepositorioException
  	 */
  	public Collection pesquisarGuiaPagamento(Integer idGuiaPagamento) throws ErroRepositorioException;      
  	
  	
  	/**
	 * Atualiza logradouroBairro de um ou mais imóveis  
	 * 
	 * [UC0] Atualizar Logradouro
	 * 
	 * @author Raphael Rossiter
	 * @date 22/02/2007
	 * 
	 * @param 
	 * @return void
	 */
	public void atualizarLogradouroBairro(LogradouroBairro logradouroBairroAntigo, 
			LogradouroBairro logradouroBairroNovo) throws ErroRepositorioException ;
	
	/**
	 * Atualiza logradouroCep de um ou mais imóveis  
	 * 
	 * [UC0] Atualizar Logradouro
	 * 
	 * @author Raphael Rossiter
	 * @date 22/02/2007
	 * 
	 * @param 
	 * @return void
	 */
	public void atualizarLogradouroCep(LogradouroCep logradouroCepAntigo, 
			LogradouroCep logradouroCepNovo) throws ErroRepositorioException ;
	
	/**
     * Pesquisa o cliente da guia de pagamento 
     * através do id da Guia de Pagamento 
     * 
     * @author Vivianne Sousa
     * @date 28/02/2007
     * 
     * @return String
     * @throws ErroRepositorioException
     */

    public Object[] pesquisarClienteDeGuiaPagamento(Integer idGuiaPagamento)
    	throws ErroRepositorioException ;
    
	 /**
     * Pesquisa o cliente da guia de pagamento 
     * através do id da Guia de Pagamento 
     * 
     * @author Vivianne Sousa
     * @date 06/03/2007
     * 
     * @return String
     * @throws ErroRepositorioException
     */

    public Object[] pesquisarImovelDeClienteGuiaPagamento(Integer idGuiaPagamento) throws ErroRepositorioException;
    
    /**
     * Pesquisa o cliente da guia de pagamento 
     * através do id da Guia de Pagamento 
     * 
     * @author Vivianne Sousa
     * @date 28/02/2007
     * 
     * @return String
     * @throws ErroRepositorioException
     */

    public Object[] pesquisarClienteDeClienteImovel(Integer idGuiaPagamento) 
    	throws ErroRepositorioException;
    
    /**
     * Este caso de uso cria um filtro que será usado na pesquisa de pagamentos
     * para o Relatório
     * 
     * [UC0255] Filtrar Pagamentos
     * 
     * Pesquisa os pagamentos do tipo Debito a Cobrar do Cliente
     * pesquisarPagamentoLocalidade
     * 
     * @author Rafael Corrêa
     * @date 12/12/06
     * 
     * @return Collection
     * @throws ErroRepositorioException
     */
    public Collection pesquisarPagamentoImovelAmbosRelatorio(String idImovel)
            throws ErroRepositorioException;
    
    
    /**
     * [UC0213] Desfazer Parcelamento Debito -
     *  remover guia pagamento referente ao parcelamento
     * 
     * remove a guia de pagamento do Pagamento
     * 
     * @author Vivianne Sousa
     * @date 06/03/2007
     * 
     * @param 
     * @return void
     */
    public void removerGuiaPagamentoPagamento(Integer idPagamento) throws ErroRepositorioException ;

    /**
     * [UC0301] Gerar Dados Diários da Arrecadação
     *
     * Pesquisa os ano/mês de referência dos pagamentos para ano/mês 
     * de referência maior ou igual ao ano/mês de referência atual da arrecadação
     *
     * @author Pedro Alexandre 
     * @date 07/03/2007
     *
     * @param anoMesArrecadacaoAtual
     * @param idLocalidade
     * @return
     * @throws ErroRepositorioException
     */
    public Collection pesquisarAnoMesArrecadacaoPagamentoMaiorIgualAnoMesArrecadacaoAtual(Integer anoMesArrecadacaoAtual, Integer idLocalidade) throws ErroRepositorioException ;


    /**
     * [UC0113] - Faturar Grupo de Faturamento
     *
     * @author Raphael Rossiter 
     * @date 20/03/2007
     *
     * @param idDebitoACobrar
     * @return Integer 
     * @throws ErroRepositorioException
     */
    public Integer pesquisarQuantidadePagamentosPorDebitoACobrar(Integer idDebitoACobrar) throws ErroRepositorioException ;

    /**
     * [UC0300] Classificar Pagamentos e Devoluções
     * 
     * Atualiza a situacao atual dos pagamentos (PGST_IDATUAL) (tabela
     * PAGAMENTO_SITUACAO)
     *
     * @author Pedro Alexandre
     * @date 23/03/2007
     *
     * @param colecaoIdsPagamentos
     * @throws ErroRepositorioException
     */
    public void atualizarSituacaoPagamentoClassificado(Collection<Integer> colecaoIdsPagamentos) throws ErroRepositorioException ;

    /**
     * [UC0300] Classificar Pagamentos e Devoluções
     * 
     * Caso o valor total dos pagamentos seja menor que o valor do documento,
     * atualiza a situação atual dos pagamentos (PGST_IDATUAL) com valor
     * correspondente a valor não confere (tabela PAGAMENTO_SITUACAO) e
     * atualiza o id da conta nos pagamentos (seta CNTA_ID da tabela PAGAMENTO
     * para CNTA_ID da tabela CONTA)
     * 
     * [SB0008] Processar Pagamento a Maior ou a Menor
     * 
     * @author Pedro Alexandre
     * @date 28/03/2007
     *
     * @param mapPagamentosValorNaoConfere
     * @throws ErroRepositorioException
     */
    public void processarPagamentoValorNaoConfereConta(Map<Integer, Collection> mapPagamentosValorNaoConfere) throws ErroRepositorioException ;

    /**
     * [UC0300] Classificar Pagamentos e Devoluções
     * 
     * Caso o valor total dos pagamentos seja menor que o valor do documento,
     * atualiza a situação atual dos pagamentos (PGST_IDATUAL) com valor
     * correspondente a valor não confere (tabela PAGAMENTO_SITUACAO) e
     * atualiza o id da guia de pagamento nos pagamentos (seta GPAG_ID da tabela PAGAMENTO
     * para GPAG_ID da tabela GUIA PAGAMENTO)
     * 
     * [SB0008] Processar Pagamento a Maior ou a Menor
     * 
     * @author Pedro Alexandre
     * @date 28/03/2007
     *
     * @param mapPagamentosValorNaoConfere
     * @throws ErroRepositorioException
     */
    public void processarPagamentoValorNaoConfereGuiaPagamento(Map<Integer, Collection> mapPagamentosValorNaoConfere) throws ErroRepositorioException ;

    /**
     * [UC0300] Classificar Pagamentos e Devoluções
     * 
     * Caso o valor total dos pagamentos seja menor que o valor do documento,
     * atualiza a situação atual dos pagamentos (PGST_IDATUAL) com valor
     * correspondente a valor não confere (tabela PAGAMENTO_SITUACAO) e
     * atualiza o id do débito a cobrar nos pagamentos (seta DBAC_ID da tabela PAGAMENTO
     * para DBAC_ID da tabela DEBITO A COBRAR)
     * 
     * [SB0008] Processar Pagamento a Maior ou a Menor
     * 
     * @author Pedro Alexandre
     * @date 28/03/2007
     *
     * @param mapPagamentosValorNaoConfere
     * @throws ErroRepositorioException
     */
    public void processarPagamentoValorNaoConfereDebitoACobrar(Map<Integer, Collection> mapPagamentosValorNaoConfere) throws ErroRepositorioException ;

    /**
     * [UC0300] - Classificar Pagamentos e Devoluções
     *
     * <Breve descrição sobre o subfluxo>
     *
     * [SB0008] - Processar Pagamento a Maior ou a Menor	
     *
     * @author Pedro Alexandre
     * @date 28/03/2007
     *
     * @param colecaoPagamentos
     * @throws ErroRepositorioException
     */
    public void processarPagamentoValorNaoConfereIdentificadorDocumentoIgualANulo(Collection colecaoPagamentos) throws ErroRepositorioException ;

    /**
     * Remove o id da guia de pagamento dos pagamentos referentes a conta
     * para poder mandar a guia de pagamento para o histórico.
     *
     * [UC0000] Gerar Histórco para encerrar Faturamento
     *
     * @author Pedro Alexandre
     * @date 01/04/2007
     *
     * @param idConta
     * @return
     * @throws ErroRepositorioException
     */
    public void apagarIdGuiaPagamentoPagamentos(Integer idGuiaPagamento) throws ErroRepositorioException ;
    
    /**
	 * Pesquisa os movimentos dos arrecadores para a geração do relatório
	 * 
	 * [UCXXXX] Acompanhar Movimento dos Arrecadadores
	 * 
	 * @author Rafael Corrêa
	 * @date 02/04/2007
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarMovimentoArrecadadoresRelatorio(
			Integer mesAnoReferencia, Integer idArrecadador,
			Integer idFormaArrecadacao, Date dataPagamentoInicial,
			Date dataPagamentoFinal) throws ErroRepositorioException;


	/**
     * Sequencial do tipo lançamento igual a 750
     * 
     * [UC0276] - Encerrar Arrecadação do Mês
     * 
     * Acumula o valor do débitos cobrados por localidade, categoria dos
     * pagamentos classificados de conta para tipo de financiamento igual a
     * doações
     * 
     * @author Pedro Alexandre
     * @date 03/04/2007
     * 
     * @param idLocalidade
     * @param anoMesReferenciaArrecadacao
     * @param idCategoria
     * @param idLancamentoItemContabil
     * @return
     * @throws ErroRepositorioException
     */
    public BigDecimal acumularValorDebitoCobradoPagamentosClassificadosContaFinanciamentoTipoDoacoes(Integer idLocalidade, Integer anoMesReferenciaArrecadacao, Integer idCategoria, Integer idLancamentoItemContabil) throws ErroRepositorioException ;

    
    /**
     * Sequencial do tipo lançamento igual a 4150
     * 
     * [UC0276] - Encerrar Arrecadação do Mês
     * 
     * Acumula o valor dos débitos cobrados por localidade, categoria e item
     * contábil para os pagamentos de contas efetuados em meses anteriores
     * classificados no mês para tipo de financiamento igual doações.
     * 
     * @author Pedro Alexandre
     * @date 03/04/2007
     * 
     * @param idLocalidade
     * @param anoMesReferenciaArrecadacao
     * @param idCategoria
     * @param idLancamentoItemContabil
     * @return
     * @throws ErroRepositorioException
     */
    public BigDecimal acumularValorDebitoCobradoPagamentosContasEfetuadosEmMesesAnterioresClassificadosNoMesFinanciamentoTipoParcelamentoDoacoes(Integer idLocalidade, Integer anoMesReferenciaArrecadacao, Integer idCategoria, Integer idLancamentoItemContabil) throws ErroRepositorioException ;

    /**
     * [UC0276] Encerrar Arrecadação do Mês
     *
     * Pesquisa as guias de pagamento correspondentes aos pagamentos classificados de guia de 
     * pagamento e aos pagamentos anteriores de guia de pagamento classificados no mês.
     *
     * @author Pedro Alexandre
     * @date 09/01/2007
     *
     * @param anoMesReferenciaArrecadacao
     * @param idLocalidade
     * @return
     * @throws ErroRepositorioException
     */
    public Collection<GuiaPagamento> pesquisarGuiasPagamentoDePagamentosClassificadosGuiasPagamentoEPagamentosAnterioresGuiaPagamentoClassificadosNoMes(Integer anoMesReferenciaArrecadacao, Integer idLocalidade) throws ErroRepositorioException ;

    /**
     * [UC0276] Encerrar Arrecadação do Mês
     *
     * Pesquisar os pagamentos classificados ou com valor excedente baixado e com 
     * valor excedente maior do que zero para transferir para o histórico.
     *
     * @author Pedro Alexandre
     * @date 10/01/2007
     *
     * @param anoMesReferenciaArrecadacao
     * @param idLocalidade
     * @return
     * @throws ErroRepositorioException
     */
    public Collection<Pagamento> pesquisarPagamentosClassificadosOuValorExcedenteBaixado(Integer anoMesReferenciaArrecadacao, Integer idLocalidade) throws ErroRepositorioException ;

    /**
     * Sequencial do tipo lançamento igual a 4000
     * 
     * [UC0276] - Encerrar Arrecadação do Mês
     * 
     * Acumula o valor dos débitos cobrados por localidade, categoria e item
     * contábil para os pagamentos de contas efetuados em meses anteriores
     * classificados no mês para tipo de financiamento igual a parcelamento de
     * serviço e grupo de parcelamento diferente de juros cobrados.
     * 
     * @author Pedro Alexandre
     * @date 23/05/2006
     * 
     * @param idLocalidade
     * @param anoMesReferenciaArrecadacao
     * @param idCategoria
     * @param idLancamentoItemContabil
     * @return
     * @throws ErroRepositorioException
     */
    public BigDecimal acumularValorDebitoCobradoPagamentosContasEfetuadosEmMesesAnterioresClassificadosNoMesFinanciamentoTipoDoacoes(Integer idLocalidade, Integer anoMesReferenciaArrecadacao, Integer idCategoria, Integer idLancamentoItemContabil) throws ErroRepositorioException ;

    /**
     * [UC0276] Encerrar Arrecadação do Mês
     *
     * Pesquisa as devoluções classificadas para transferir para o histórico.
     *
     * @author Pedro Alexandre
     * @date 10/01/2007
     *
     * @param anoMesReferenciaArrecadacao
     * @param idLocalidade
     * @return
     * @throws ErroRepositorioException
     */
    public Collection<Devolucao> pesquisarDevolucoesClassificadasPorLocalidade(Integer anoMesReferenciaArrecadacao, Integer idLocalidade) throws ErroRepositorioException ;

    /**
     * [UC0276] Encerrar Arrecadação do Mês
     *
     * Pesquisa as contas correspondentes aos pagamentos classificados de conta 
     * e os pagamentos anteriores de conta classificados no mês.
     *
     * @author Pedro Alexandre
     * @date 09/01/2007
     *
     * @param anoMesReferenciaArrecadacao
     * @param idLocalidade
     * @return
     * @throws ErroRepositorioException
     */
    public Collection<Conta> pesquisarContasDePagamentosClassificadosContaEPagamentosAnterioresContaClassificadosNoMes(Integer anoMesReferenciaArrecadacao, Integer idLocalidade) throws ErroRepositorioException ;


    /**
     * [UC0276] Encerrar Arrecadação do Mês
     *
     * Pesquisa a conta  
     * @author Pedro Alexandre
     * @date 10/01/2007
     *
     * @param idConta
     * @return
     * @throws ErroRepositorioException
     */
    public Conta pesquisarConta(Integer idConta) throws ErroRepositorioException ;

    /**
     * [UC0276] Encerrar Arrecadação do Mês
     *
     * Pesquisar o pagamento
     *
     * @author Pedro Alexandre
     * @date 10/04/2007
     *
     * @param idPagamento
     * @return
     * @throws ErroRepositorioException
     */
    public Pagamento pesquisarPagamento(Integer idPagamento) throws ErroRepositorioException ;

	public Collection<Integer> pesquisarIdsSetoresComPagamentosOuDevolucoes() throws ErroRepositorioException;

    public Integer pesquisarIdLocalidadePorSetorComercial(Integer idSetorComercial) throws ErroRepositorioException ;
    
    public Collection<DebitoACobrar> pesquisarDebitosACobrarDePagamentosClassificadosGuiasPagamentoEPagamentosAnterioresGuiaPagamentoClassificadosNoMes(Integer anoMesReferenciaArrecadacao, Integer idLocalidade, boolean dividaAtiva) throws ErroRepositorioException ;

    /**
     * Sequencial do tipo lançamento igual a 4400, 4410, 4420, 4430
     * 
     * [UC0276] - Encerrar Arrecadação do Mês
     * 
     * Acumula o valor dos créditos realizados por localidade e categoria, para
     * os pagamentos de contas efetuados em meses anteriores classificados no
     * mês, para origem do crédito igual a descontos concedidos.
     * 
     * @author Pedro Alexandre
     * @date 23/05/2006
     * 
     * @param idLocalidade
     * @param anoMesReferenciaArrecadacao
     * @param idCategoria
     * @param idOrigemCredito
     * @return
     * @throws ErroRepositorioException
     */
    public BigDecimal acumularValorCreditoRealizadoPagamentosContasEfetuadosEmMesesAnterioresClassificadosNoMesPorOrigemCredito(Integer idLocalidade, Integer anoMesReferenciaArrecadacao, Integer idCategoria, Integer idOrigemCredito) throws ErroRepositorioException ;

    
    /**
     * Sequencial do tipo lançamento igual a 400 e 500
     * 
     * [UC0276] - Encerrar Arrecadação do Mês
     * 
     * Acumula o valor do débitos cobrados por localidade, categoria dos
     * pagamentos classificados de conta para tipo de financiamento igual a
     * parcelamento de água ou parcelamento de esgoto.
     * 
     * @author Pedro Alexandre
     * @date 18/04/2007
     * 
     * @param idLocalidade
     * @param anoMesReferenciaArrecadacao
     * @param idCategoria
     * @param idFinanciamentoTipo
     * @return
     * @throws ErroRepositorioException
     */
    public BigDecimal acumularValorDebitoCobradoPagamentosClassificadosContaPorFinanciamentoTipo(Integer idLocalidade, Integer anoMesReferenciaArrecadacao, Integer idCategoria, Integer idFinanciamentoTipo) throws ErroRepositorioException ;

    
    /**
     * Sequencial do tipo lançamento igual a 3800
     * 
     * [UC0276] - Encerrar Arrecadação do Mês
     * 
     * Acumula o valor dos débitos cobrados por localidade e categoria para os
     * pagamentos de contas efetuados em meses anteriores classificados no mês
     * para tipo de financiamento igual a parcelamento de água.
     * 
     * @author Pedro Alexandre
     * @date 18/04/2007
     * 
     * @param idLocalidade
     * @param anoMesReferenciaArrecadacao
     * @param idCategoria
     * @param idFinanciamentoTipo
     * @return
     * @throws ErroRepositorioException
     */
    public BigDecimal acumularValorDebitoCobradoPagamentosContasEfetuadosEmMesesAnterioresClassificadosNoMesPorFinanciamentoTipo(Integer idLocalidade, Integer anoMesReferenciaArrecadacao, Integer idCategoria, Integer idFinanciamentoTipo) throws ErroRepositorioException ;

    /**
     * Sequencial do tipo lançamento igual a 300
     * 
     * [UC0276] - Encerrar Arrecadação do Mês
     * 
     * Acumula o valor do débitos cobrados por localidade, categoria e item
     * contábil dos pagamentos classificados de conta para tipo de financiamento
     * igual a serviço
     * 
     * @author Pedro Alexandre
     * @date 22/05/2006
     * 
     * @param idLocalidade
     * @param anoMesReferenciaArrecadacao
     * @param idLancamentoItemContabil
     * @param idCategoria
     * @param colecaoIdsFinanciamentoTipo
     * @return
     * @throws ErroRepositorioException
     */
    public BigDecimal acumularValorDebitoCobradoPagamentosClassificadosContaPorFinanciamentoTipo(Integer idLocalidade, Integer anoMesReferenciaArrecadacao, Integer idLancamentoItemContabil, Integer idCategoria, Collection<Integer> colecaoIdsFinanciamentoTipo) throws ErroRepositorioException ;

    /**
     * Sequencial do tipo lançamento igual a 3700
     * 
     * [UC0276] - Encerrar Arrecadação do Mês
     * 
     * Acumula o valor dos débitos cobrados por localidade, categoria e item
     * contábil para os pagamentos de contas efetuados em meses anteriores
     * classificados no mês para tipo de financiamento igual a serviço.
     * 
     * @author Pedro Alexandre
     * @date 23/05/2006
     * 
     * @param idLocalidade
     * @param anoMesReferenciaArrecadacao
     * @param idLancamentoItemContabil
     * @param idCategoria
     * @param idsFinanciamentoTipos
     * @return
     * @throws ErroRepositorioException
     */
    public BigDecimal acumularValorDebitoCobradoPagamentosContasEfetuadosEmMesesAnterioresClassificadosNoMesPorFinanciamentoTipo(Integer idLocalidade, Integer anoMesReferenciaArrecadacao, Integer idLancamentoItemContabil, Integer idCategoria, Collection<Integer> idsFinanciamentoTipos) throws ErroRepositorioException ;
    
    /**
     * [UC0242] Registrar Movimento Arrecadadores
     * 
     * Atualiza o arrecadador contrato
     * 
     * 
     * @author Sávio Luiz,Vivianne Sousa
     * @date 19/04/2007,28/11/2007
     * 
     * @return Coleção de Bancos
     * @throws ErroRepositorioException
     */

    public void atualizarDadosArrecadadorContrato(
            ArrecadadorContrato arrecadadorContrato,
            boolean flagEnvioDebitoAutomatico, boolean flagRetornoCodigoBarras,
            boolean flagRetornoDebitoAutomatico, boolean flagRetornoFichaCompensacao)
            throws ErroRepositorioException;

    
    /**
     * Pesquisar os ano/mês de referência do pagamentos para um imóvel e ano/mês
     * de arrecadação informados para o tipo de documento informado.
     * 
     * [UC0300] Classificar Pagamentos e Devoluções
     * 
     * @author Pedro Alexandre
     * @date 06/12/2006
     * 
     * @param anoMesArrecadacaoAtual
     * @param idImovel
     * @return
     * @throws ErroRepositorioException
     */
    public Collection pesquisarAnoMesReferenciaPagamentoParaImovel(Integer anoMesArrecadacaoAtual, Integer idImovel) throws ErroRepositorioException ;
    
    /**
     * Seleciona os pagamentos histórios de um aviso
     * 
     * @author Rafael Corrêa
     * @date 23/04/2007
     * 
     * @return Collection
     * @throws ErroRepositorioException
     */
    public Collection pesquisarPagamentoHistoricoAvisoBancario(Integer idAvisoBancario) 
    	throws ErroRepositorioException;

    
    /**
	 * [UC0150] Retificar Conta
	 * 
	 * @author Vivianne Sousa
	 * @data 23/04/2006
	 * 
	 * @param idConta
	 * @return idParcelamento
	 */
	public Object[] pesquisarPagamentoDeConta(Integer idConta)
		throws ErroRepositorioException ;
	
	
	/**
	 * [UC0150] Retificar Conta
	 * 
	 * @author Vivianne Sousa
	 * @data 23/04/2006
	 * 
	 * @param idPagamento
	 * @param idConta
	 * @return
	 * @throws ErroRepositorioException
	 */
	public void atualizarContaEmPagamento(Integer idPagamento, Integer idConta)
		throws ErroRepositorioException;
	
	 /**
	 *  
	 * @author Sávio Luiz
	 * @data 28/04/2007
	 * 
	 * @param idConta
	 * @return idParcelamento
	 */
	public Integer pesquisarIdPagamentoDaGuia(
			Integer idGuiaPagamento) throws ErroRepositorioException;
	
	 /**
	 *  
	 * @author Sávio Luiz
	 * @data 28/04/2007
	 * 
	 * @param idConta
	 * @return idParcelamento
	 */
	public Integer pesquisarIdPagamentoDoDebitoACobrar(
			Integer idDebitoACobrar) throws ErroRepositorioException;
	
	/**
	 * Consulta ResumoArrecadacao para a geração do relatório '[UC0345] Gerar
	 * Relatório de Resumo Arrecadação' de acordo com a opção de totalização.
	 * 
	 * @author Vivianne Sousa
	 * @created 04/06/2007
	 * 
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection consultarResumoArrecadacaoRelatorioPorEstadoPorUnidadeNegocio(
			int anoMesReferencia) throws ErroRepositorioException;
	
	/**
	 * Consulta ResumoArrecadacao para a geração do relatório '[UC0345] Gerar
	 * Relatório de Resumo Arrecadação' de acordo com a opção de totalização.
	 * 
	 * @author Vivianne Sousa
	 * @created 04/06/2007
	 * 
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection consultarResumoArrecadacaoRelatorioPorUnidadeNegocio(
			int anoMesReferencia, Integer localidade)
			throws ErroRepositorioException;
	
	/**
	 * Pesquisar pagamentos pelo aviso bancário
	 * 
	 * @author Ana Maria
	 * @date 11/06/2007
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public PagamentosDevolucoesHelper filtrarPagamentos(FiltroPagamento filtroPagamento)
			throws ErroRepositorioException;
	
	/**
	 * Pesquisar devoluçãoes pelo aviso bancário
	 * 
	 * @author Ana Maria
	 * @date 11/06/2007
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public PagamentosDevolucoesHelper filtrarDevolucoes(FiltroDevolucao filtroDevolucao)
			throws ErroRepositorioException;	
	
	/**
	 * Pesquisar valores de arrecadação e devolução do aviso bancário
	 * 
	 * @author Ana Maria
	 * @date 14/06/2007
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public ValoresArrecadacaoDevolucaoAvisoBancarioHelper pesquisarValoresAvisoBancario(
			Integer idAvisoBancario) throws ErroRepositorioException;	
	
	/**
	 * Atualizar Pagamentos
	 * 
	 * @author Ana Maria
	 * @date 15/06/2007
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	
	public void atualizarAvisoBancarioPagamentos(Collection<Integer> idsPagamentos, Integer idAvisoBancarioD)
			throws ErroRepositorioException;
	
	/**
	 * Atualizar valor de arrecadação calculado 
	 * 
	 * @author Ana Maria
	 * @date 15/06/2007
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	
	public void atualizarValorArrecadacaoAvisoBancario(String valorArrecadacaoInformado, String valorArrecadacaoCalculado, 
			Integer idAvisoBancario)throws ErroRepositorioException;
	
	/**
	 * Atualizar Devoluções
	 * 
	 * @author Ana Maria
	 * @date 15/06/2007
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	
	public void atualizarAvisoBancarioDevolucoes(Collection<Integer> idsDevolucoes, Integer idAvisoBancarioD)
			throws ErroRepositorioException;
	
	 /**
	 * Atualizar valor de devolução calculado 
	 * 
	 * @author Ana Maria
	 * @date 15/06/2007
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	
	public void atualizarValorDevolucaoAvisoBancario(String valorDevolucaoInformado, String valorDevolucaoCalculado, 
			Integer idAvisoBancario)throws ErroRepositorioException;	
	
	/**
	 * [UC0263] Filtrar Movimento dos Arrecadadores - Relatório
	 * 
	 * @author Ana Maria
	 * @date 13/07/2007
	 * 
	 * @param codigoBanco
	 * @param codigoRemessa
	 * @param descricaoIdentificacaoServico
	 * @param numeroSequencialArquivo
	 * @param dataGeracaoInicio
	 * @param dataGeracaoFim
	 * @param ultimaAlteracaoInicio
	 * @param ultimaAlteracaoFim
	 * @param descricaoOcorrencia
	 * @param indicadorAceitacao
	 * @param indicadorAbertoFechado
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> filtrarIdsMovimentoArrecadador(
			String codigoBanco, String codigoRemessa,
			String descricaoIdentificacaoServico,
			String numeroSequencialArquivo, Date dataGeracaoInicio,
			Date dataGeracaoFim, Date ultimaAlteracaoInicio,
			Date ultimaAlteracaoFim, String descricaoOcorrencia,
			String indicadorAceitacao, String indicadorAbertoFechado) throws ErroRepositorioException;
	
	/**
	 * 
	 * [UC0619] Gerar Relação de Acompanhamento dos Movimentos Arrecadadores por NSA
	 * 
	 * @author Ana Maria
	 * @date 12/07/2007
	 * 
	 * @param idMovimentoArrecadador
	 * @return
	 */
	public Collection<MovimentoArrecadadoresPorNSAHelper> gerarMovimentoArrecadadoresNSA
			(Collection<Integer> idsArrecadadorMovimento, Integer codigoFormaArrecadacao)throws ErroRepositorioException;
	
	
	/**
	 * Processamento Rápido
	 * 
	 * @author Raphael Rossiter
	 * @date 17/08/2007
	 * 
	 * @return Collection<Conta>
	 * @throws ErroRepositorioException
	 */
	public Collection<Conta> pesquisarContaComPagamentoHistorico()
			throws ErroRepositorioException ;
	
	/**
	 * Pesquisa a agencia
	 * 
	 * @author Sávio Luiz
	 * @date 05/11/2007
	 * 
	 * @return Agencia
	 * @throws ErroRepositorioException
	 */
	public Agencia pesquisarAgencia(String codigoAgencia,
			Integer idBanco) throws ErroRepositorioException;
	
	/**
	 * [UC0626] Gerar Resumo de Metas Acumulado no Mês (CAERN)
	 * 
	 * @author Sávio Luiz
	 * @data 28/11/2007
	 * 
	 * @param idConta
	 * @return idParcelamento
	 */
	public Collection pesquisarPagamentoDeContas(Collection colecaoConta)
			throws ErroRepositorioException;
	
	/**
	 * [UC0739] Informar Situação de Expurgo do Pagamento
	 * 
	 * @author Sávio Luiz
	 * @data 02/01/2008
	 * 
	 * @param idConta
	 * @return idParcelamento
	 */
	public Collection pesquisarDadosPagamentoExpurgado(String dataPagamento,Integer idCliente,Integer anoMesArrecadacao)
			throws ErroRepositorioException;
	
	/**
	 * [UC0739] Informar Situação de Expurgo do Pagamento
	 * 
	 * @author Sávio Luiz
	 * @data 02/01/2008
	 * 
	 * @param idConta
	 * @return idParcelamento
	 */
	public Collection pesquisarDadosPagamentoHistoricoExpurgado(String dataPagamento,Integer idCliente,Integer anoMesArrecadacao)
			throws ErroRepositorioException;
	
	/**
	 * [UC0739] Informar Situação de Expurgadodo Pagamento
	 * 
	 * 
	 * @author Sávio Luiz
	 * @date 04/01/2008
	 * 
	 * @param idsPagamentos
	 * @return void
	 */
	public void atualizarSituacaoExpurgado(Collection colecaoPagamento)
			throws ErroRepositorioException;

	
	/**
	 * Pesquisa a ContaHistorico para o imóvel no ano/mês de referência informados
	 *
	 * [UC0300] Classificar Pagamentos e Devoluções
	 *
	 * @author Pedro Alexandre
	 * @date 15/01/2008
	 *
	 * @param imovel
	 * @param anoMesReferenciaPagamento
	 * @param anoMesFaturamento
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Object[] selecionarContaHistoricoPorImovelAnoMesReferencia(Imovel imovel,Integer anoMesReferenciaPagamento, Integer anoMesFaturamento) throws ErroRepositorioException ;


	
	/**
	 * [UC0744] Gerar Comparativo do Faturamento, Arrecadação e Expurgo
	 * 
	 * @author Sávio Luiz
	 * @data 10/01/2008
	 * 
	 * @param idConta
	 * @return idParcelamento
	 */
	public Collection pesquisarDadosComparativosFaturamentoArrecadacaoExpurgo(Integer anoMesReferencia,
			String idGerenciaRegional,String idUnidadeNegocio)
			throws ErroRepositorioException;
	
	/**
	 * [UCXXX] Pesquisa uma Guia de Arrecadação
	 * 
	 * @author Roberto Barbalho
	 * @data 25/01/2008
	 * 
	 * @param guiaDevolucaoId
	 * @return GuiaDevolucao
	 */
	public GuiaDevolucao pesquisarGuiaDevolucao(Integer guiaDevolucaoId) throws ErroRepositorioException;
    
    /**
     * [UC0242] Registrar Movimento dos Arrecadadores
     * 
     * Atualiza o valor total do movimento (armv_vltotalmovimento) (tabela
     * ARRECADADOR_MOVIMENTO)
     * 
     * @author Vivianne Sousa
     * @date 31/01/2008
     * 
     * @param idArrecadadorMovimento
     * @param valorTotalMovimento
     * @return void
     */
    public void atualizarValorMovimentoArrecadadorMovimento(
            Integer idArrecadadorMovimento, BigDecimal valorTotalMovimento) throws ErroRepositorioException;
    
    
    /**
     * [UC0737] Atualiza Quantidade de Parcela Paga Consecutiva e Parcela Bônus
     * 
     * @author Vivianne Sousa
     * @data 01/02/2008
     * 
     * @param idConta
     * @return dataPagamento
     */
    public Date pesquisarDataPagamentoDeConta(Integer idConta)
            throws ErroRepositorioException;
    
    /**
	 * [UC0744] Gerar Comparativo do Faturamento, Arrecadação e Expurgo
	 * 
	 * @author Sávio Luiz
	 * @data 17/02/2008
	 * 
	 * @param idConta
	 * @return idParcelamento
	 */
	public Collection pesquisarPagamentoExpurgado(Integer anoMesReferencia,
			String idGerenciaRegional,String idUnidadeNegocio)
			throws ErroRepositorioException;
	
	/**
	 * [UC0744] Gerar Comparativo do Faturamento, Arrecadação e Expurgo
	 * 
	 * @author Sávio Luiz
	 * @data 17/02/2008
	 * 
	 * @param idConta
	 * @return idParcelamento
	 */
	public Collection pesquisarPagamentoHistoricoExpurgado(Integer anoMesReferencia,
			String idGerenciaRegional,String idUnidadeNegocio)
			throws ErroRepositorioException;

	
	/**
	 * Sequencial do tipo lançamento igual a 100
	 * 
	 * [UC0276] - Encerrar Arrecadação do Mês
	 * 
	 * Acumula o valor de água e esgoto por categoria 
	 * e localidade paa os pagamentos classificados de conta
	 * 
	 * @author Pedro Alexandre
	 * @date 22/05/2008
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaArrecadacao
	 * @param idCategoria
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Object[] acumularValorAguaEsgotoPagamentosClassificadosConta(
			Integer idLocalidade, 
			Integer anoMesReferenciaArrecadacao,
			Integer idCategoria) throws ErroRepositorioException ;

	/**
	 * Sequencial do tipo lançamento igual a 3500
	 * 
	 * [UC0276] - Encerrar Arrecadação do Mês
	 * 
	 * Acumula o valor de água por localidade e categoria para os pagamentos de
	 * contas efetuados em meses anteriores classificados no mês.
	 * 
	 * @author Pedro Alexandre, Pedro Alexandre
	 * @date 23/05/2006, 23/05/2008
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaArrecadacao
	 * @param idCategoria
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Object[] acumularValorAguaEsgotoPagamentosContasEfetuadosEmMesesAnterioresClassificadosNoMes(
			Integer idLocalidade, 
			Integer anoMesReferenciaArrecadacao,
			Integer idCategoria) throws ErroRepositorioException ;

	/**
	 * [UC0259] - Processar Pagamento com Código de Barras
	 *
	 * [SB0014] - Processar Pagamento Legado CAEMA - GUIA DE PAGAMENTO
	 *
	 * @author Raphael Rossiter
	 * @date 16/06/2008
	 *
	 * @param imovel
	 * @param numeroGuia
	 * @param anoGuia
	 * @return Object[]
	 * @throws ErroRepositorioException
	 */
	public Integer[] pesquisarExistenciaGuiaPagamento(Imovel imovel,
			Integer numeroGuia, Integer anoGuia) throws ErroRepositorioException ;
	
	/**
	 * [UC0259] - Processar Pagamento com Código de Barras
	 *
	 * [SB0014] - Processar Pagamento Legado CAEMA - AVISO DE DÉBITOS
	 *
	 * @author Raphael Rossiter
	 * @date 16/06/2008
	 *
	 * @param imovel
	 * @param lotePagamento
	 * @param anoGuia
	 * @return Object[]
	 * @throws ErroRepositorioException
	 */
	public Integer[] pesquisarExistenciaGuiaPagamentoPorLotePagamento(Imovel imovel,
			Integer lotePagamento, Integer anoGuia) throws ErroRepositorioException ;

 	/**
 	 * [UC0301] Gerar Dados Diarios da Arrecadacao
 	 * Consulta das tarifas dos contratos do arrecadador
 	 * 
 	 * @author Francisco do Nascimento
 	 * @date 18/07/2008
 	 * 
 	 */
 	public ArrecadadorContratoTarifa pesquisarArrecadadorContratoTarifa(Integer idArrecadador,
 			Integer idFormaArrecadacao) throws ErroRepositorioException;
	/**
	 * Exclui resumo arrecadação do ano/mês da arrecadação corrente
	 * por localidade
	 * 
	 * [UC0276] Encerrar Arrecadação do Mês
	 * 
	 * @author Vivianne Sousa
	 * @date 11/08/2008
	 * 
	 * @param anoMesReferenciaArrecadacao
	 * @param idLocalidade
	 * @throws ErroRepositorioException
	 */
	public void excluirResumoArrecadacaoPorAnoMesArrecadacaoPorLocalidade(
			int anoMesReferenciaArrecadacao, Integer idLocalidade)
			throws ErroRepositorioException;
	
	/**
	 * [UC0818] Gerar Historico do Encerramento da Arrecadação
	 * 
	 * Pesquisa os creditos a realizar correspondentes as devoluções classificadas
	 * 
	 * @author Vivianne Sousa
	 * @date 26/08/2008
	 * 
	 * @param anoMesReferenciaArrecadacao
	 * @param idLocalidade
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<CreditoARealizar> pesquisarCreditoaRealizarDeDevolucoesClassificadas(
			Integer anoMesReferenciaArrecadacao, Integer idLocalidade)
			throws ErroRepositorioException;
	

	/**
	 * [UC0826] Gerar Relatório Análise da Arrecação
	 * 
	 * @see RepositorioArrecadacaoHBM#pesquisarAnaliseArrecadacao(PesquisarAnaliseArrecadacaoHelper)
	 * 
	 * @author Victor Cisneiros
	 * @date 24/07/2008
	 */
    public List<RelatorioAnaliseArrecadacaoBean> pesquisarAnaliseArrecadacao(
    		PesquisarAnaliseArrecadacaoHelper helper) throws ErroRepositorioException;
    
	/**
	 * [UC0827] Gerar Relatório Análise dos Avisos Bancarios
	 * 
	 * @see RepositorioArrecadacaoHBM#pesquisarAnaliseAvisosBancarios(PesquisarAnaliseAvisosBancariosHelper)
	 * 
	 * @author Victor Cisneiros
	 * @date 30/07/2008
	 */
    public List<RelatorioAnaliseAvisosBancariosBean> pesquisarAnaliseAvisosBancarios(
    		PesquisarAnaliseAvisosBancariosHelper helper) throws ErroRepositorioException;
    
    /**
     * [UC0829] Gerar Relatório Avisos Bancarios Por Conta Corrente
     * 
     * @see RepositorioArrecadacaoHBM#pesquisarAvisoBancarioPorContaCorrente(PesquisarAvisoBancarioPorContaCorrenteHelper)
     * 
     * @author Victor Cisneiros
     * @date 21/08/2008
     */
    public List<RelatorioAvisoBancarioPorContaCorrenteBean> pesquisarAvisoBancarioPorContaCorrente(
    		PesquisarAvisoBancarioPorContaCorrenteHelper helper) throws ErroRepositorioException;
    
    /**
     * [UC0829] Gerar Relatório Avisos Bancarios Por Conta Corrente
     * 
     * @see RepositorioArrecadacaoHBM#pesquisarPagamentosDosAvisos(PesquisarAvisoBancarioPorContaCorrenteHelper, Collection)
     * 
     * @author Victor Cisneiros
     * @date 21/08/2008
     */
    public List<Object[]> pesquisarPagamentosDosAvisos(
    		PesquisarAvisoBancarioPorContaCorrenteHelper helper, Collection<Integer> idsAvisos) throws ErroRepositorioException;
	
    /**
     * [UC0828] Atualizar Diferença Acumulada no Mês
     * 
     * @see RepositorioArrecadacaoHBM#pesquisarDiferencaAcumuladaNoMes(int)
     * 
     * @author Victor Cisneiros
     * @date 01/09/2008
     */
    public List<Object[]> pesquisarDiferencaAcumuladaNoMes(int anoMesArrecadacao, int anoMesArrecadacaoAnterior) throws ErroRepositorioException;
    
    /**
     * [UC0828] Atualizar Diferença Acumulada no Mês
     * 
     * @author Victor Cisneiros
     * @date 01/09/2008
     */
    public void removerDiferencasAcumuladasNoMes(int anoMesArrecadacao) throws ErroRepositorioException;

	/**
	 * Exclui os dados diários da devolucao do ano/mês da arrecadação corrente
	 * por localidade
	 * 
	 * [UC0301] Gerar Dados Diários da Arrecadação
	 * 
	 * @author Francisco do Nascimento
	 * @date 22/10/2008
	 * 
	 * @param anoMesReferenciaArrecadacao
	 * @param idLocalidade
	 * @throws ErroRepositorioException
	 */
	public void excluirDadosDiariosDevolucaoPorAnoMesArrecadacaoPorLocalidade(
			int anoMesReferenciaArrecadacao, Integer idLocalidade)
			throws ErroRepositorioException;    
    
    /**
	 * [UC0744] Gerar Comparativo do Faturamento, Arrecadação e Expurgo
	 * 
	 * @author Sávio Luiz
	 * @data 17/02/2008
	 * 
	 * @param idConta
	 * @return idParcelamento
	 */
	public Collection<Pagamento> pesquisarPagamentoPorLocalidade(Integer idLocalidade,Integer anoMesReferencia)
			throws ErroRepositorioException;
	

    
    /**
     * 
     * [UC0818] - Gerar Histórico do Encerramento da Arrecadação
     *
     * Fluxo Principal
     *
     * [FS0001] - Verifica existencia resumo arrecadação   
     *
     * @author bruno
     * @date 24/10/2008
     *
     * @param anoMesReferencia
     * @return
     */
    public Integer verificarExistenciaResumoArrecadacaoParaAnoMes( Integer anoMesReferencia ) throws ErroRepositorioException;
       
	/**
	 * [UC0333] Consultar Dados Diários da Arrecadação
	 * 
	 * Método para filtrar os dados diários para qualquer aba da funcionalidade
	 * 
	 * @author Francisco do Nascimento
	 * @date 12/11/2008
	 *
	 * @param filtro
	 * @return
	 */
	public Collection filtrarDadosDiariosArrecadacao(FiltroConsultarDadosDiariosArrecadacao filtro)
	 throws ErroRepositorioException;
	
	/**
	 * 
	 * [UC0333] Consultar Dados Diários da Arrecadação
	 *
	 * Verificar se existe dados diarios da arrecadacao de acordo com o filtro 
	 * passado
	 *
	 * @author Francisco do Nascimento
	 * @date 18/11/2008
	 *
	 * @param filtro
	 * @return boolean de existencia dos dados
	 * @throws ErroRepositorioException
	 */
	public boolean verificarExistenciaDadosDiariosArrecadacao(FiltroConsultarDadosDiariosArrecadacao filtro)
		throws ErroRepositorioException;
	
	/**
	 * Atualiza o valor excedente, a situação anterior para NULO e a situação atual dos pagamentos informados para o
	 * tipo de situação informada.
	 * 
	 * @author Raphael Rossiter
	 * @date 27/11/2008
	 * 
	 * @param colecaoPagamento
	 * @param pagamentoSituacao
	 * @throws ErroRepositorioException
	 */
	public void atualizarSituacaoAnteriorAtualEValorExcedentePagamento(
			Collection<Pagamento> colecaoPagamento, Integer pagamentoSituacao)
			throws ErroRepositorioException ;
	
	/**
	 * [UC0300] Classificar Pagamentos e Devoluções
	 * 
	 * O sistema seleciona os pagamentos com ano/mês de referência da
	 * arrecadação igual ou menor que o ano/mês de referência da arrecadação
	 * corrente (seleciona a partir da tabela PAGAMENTO para
	 * PGMT_AMREFERENCIAARRECADACAO igual ou menor ao
	 * PARM_AMREFERENCIAARRECADACAO), com o tipo do documento correspondente a
	 * guia de pagamento e o campo GPAG_ID informado e
	 * o ano/mês de referência contábil da guia de pagamento seja 
	 * menor ao ano/mês de referência do faturamento 
	 * 
	 * @author Vivianne Sousa
	 * @data 25/11/2008
	 * 
	 * @param anoMesReferenciaArrecadacao
	 * @param idLocalidade
	 * @param anoMesReferenciaFaturamento
	 * @return Collection<Object[]>
	 */
	public Collection<Object[]> pesquisarPagamentosPorGuiaPagamentoComGuiaInformadaRefContabilMenorRefFaturamento(
			Integer anoMesReferencia, Integer idLocalidade, Integer referenciafaturamento)
			throws ErroRepositorioException;



	/**
	 * [UC0300] Classificar Pagamentos e Devoluções
	 * 
	 * O sistema seleciona os pagamentos com ano/mês de referência da
	 * arrecadação igual ou menor que o ano/mês de referência da arrecadação
	 * corrente (seleciona a partir da tabela PAGAMENTO para
	 * PGMT_AMREFERENCIAARRECADACAO igual ou menor ao
	 * PARM_AMREFERENCIAARRECADACAO), com o tipo do documento correspondente a
	 * guia de pagamento e o campo GPAG_ID informado e
	 * o ano/mês de referência contábil da guia de pagamento seja 
	 * maior ou igual ao ano/mês de referência do faturamento 
	 * 
	 * @author Vivianne Sousa
	 * @data 25/11/2008
	 * 
	 * @param anoMesReferenciaArrecadacao
	 * @param idLocalidade
	 * @param anoMesReferenciaFaturamento
	 * @return Collection<Object[]>
	 */
	public Collection<Object[]> pesquisarPagamentosPorGuiaPagamentoComGuiaInformadaRefContabilMaiorIgualRefFaturamento(
			Integer anoMesReferencia, Integer idLocalidade, Integer referenciafaturamento)
			throws ErroRepositorioException;
	
	/**
	 * [UC0300] Classificar Pagamentos e Devoluções
	 * 
	 * O sistema seleciona os pagamentos com ano/mês de referência da
	 * arrecadação igual ou menor que o ano/mês de referência da arrecadação
	 * corrente (seleciona a partir da tabela PAGAMENTO para
	 * PGMT_AMREFERENCIAARRECADACAO igual ou menor ao
	 * PARM_AMREFERENCIAARRECADACAO), com o tipo do documento correspondente a
	 * debito a cobrar e o campo DBAC_ID informado e 
	 * o ano/mês de referência contábil do débito a cobrar seja 
	 * maior ou igual ao ano/mês de referência do faturamento 
	 * 
	 * @author Vivianne Sousa
	 * @data 25/11/2008
	 * 
	 * @param anoMesReferenciaFaturamento
	 * @param idLocalidade
	 * @param referenciafaturamento
	 * @return Collection<Object[]>
	 */
	public Collection<Object[]> pesquisarPagamentosPorDebitoACobrarComDebitoInformadoRefContabilMaiorIgualRefFaturamento(
			Integer anoMesReferencia, Integer idLocalidade, Integer referenciafaturamento)
			throws ErroRepositorioException ;
	
	/**
	 * [UC0300] Classificar Pagamentos e Devoluções
	 * 
	 * O sistema seleciona os pagamentos com ano/mês de referência da
	 * arrecadação igual ou menor que o ano/mês de referência da arrecadação
	 * corrente (seleciona a partir da tabela PAGAMENTO para
	 * PGMT_AMREFERENCIAARRECADACAO igual ou menor ao
	 * PARM_AMREFERENCIAARRECADACAO), com o tipo do documento correspondente a
	 * debito a cobrar e o campo DBAC_ID informado e 
	 * o ano/mês de referência contábil do débito a cobrar seja 
	 * menor q o ano/mês de referência do faturamento 
	 * 
	 * @author Vivianne Sousa
	 * @data 25/11/2008
	 * 
	 * @param anoMesReferenciaFaturamento
	 * @param idLocalidade
	 * @param referenciafaturamento
	 * @return Collection<Object[]>
	 */
	public Collection<Object[]> pesquisarPagamentosPorDebitoACobrarComDebitoInformadoRefContabilMenorRefFaturamento(
			Integer anoMesReferencia, Integer idLocalidade, Integer referenciafaturamento)
			throws ErroRepositorioException;
	
	/**
	 * Sequencial do tipo lançamento igual a 2440
	 * 
	 * [UC0276] - Encerrar Arrecadação do Mês
	 * 
	 * Pesquisa as devoluções do tipo descontos por pagamento a vista
	 * 
	 * @author Francisco do Nascimento
	 * @date 03/12/2008
	 * 
	 * @param anoMesReferenciaArrecadacao
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarDevolucoesDescontosPagamentoAVista(
			Integer anoMesReferenciaArrecadacao, 
			Integer idLocalidade)
			throws ErroRepositorioException;

	/**
	 * Sequencial do tipo lançamento igual a 2470
	 * 
	 * [UC0276] - Encerrar Arrecadação do Mês
	 * 
	 * Pesquisa as devoluções do tipo descontos por credito
	 * 
	 * @author Francisco do Nascimento
	 * @date 04/12/2008
	 * 
	 * @param anoMesReferenciaArrecadacao
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarDevolucoesDescontosCreditos(
			Integer anoMesReferenciaArrecadacao, 
			Integer idLocalidade)
			throws ErroRepositorioException;	
	
	/**
	 * Pesquisa os bancos q tem imoveis cadastrados em debito automatico
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarBancoDebitoAutomatico()
		throws ErroRepositorioException;
	
	/**
	  * [UC0146] Manter Conta
	  * 
	  * FS0028 - Verificar parâmetro consulta e débito automático
	  * @return
	  * @throws ErroRepositorioException
	  */
	public Collection pesquisarImoveisBancoDebitoAutomatico(String[] bancos)
			throws ErroRepositorioException;
	
	public Integer countImoveisBancoDebitoAutomatico(String[] bancos, 
			Integer anoMesInicial,Integer anoMesFinal, Date dataVencimentoInicial,
			Date dataVencimentoFinal, String indicadorContaPaga)
		throws ErroRepositorioException;
	
	public Collection selecionarImoveisBancoDebitoAutomatico(String[] bancos, 
			Integer anoMesInicial,Integer anoMesFinal, Date dataVencimentoInicial,
			Date dataVencimentoFinal, String indicadorContaPaga)
		throws ErroRepositorioException;
	
	 /**
	 * Consultar os dados do movimento arrecadador 
	 * 
	 * @author Arthur Carvalho
	 * @date 03/04/2009
	 */
    public Collection<Object[]>  consultarNomeArrecadadorNomeAgencia(
			String idArrecadadorMovimento ) throws ErroRepositorioException ;
    
	/**
	 * [UC0276] Encerrar Arrecadação do Mês
	 * 
	 * @author Vivianne Sousa
	 * @date 01/06/2009
	 * 
	 * @param anoMesReferenciaArrecadacao
	 * @param idLocalidade
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarPagamentosMesEMesesAnterioresCampanhaSolidariedadeCrianca(
			Integer anoMesReferenciaArrecadacao, 
			Integer idLocalidade, Integer idRD) throws ErroRepositorioException;
	
	/**
	 * Sequencial do tipo lançamento igual a 2450
	 * [UC0276] - Encerrar Arrecadação do Mês
	 * Pesquisa as devoluções do tipo descontos por pagamento a vista da campanha de solidariedade a criança
	 * 
	 * @author Vivianne Sousa
	 * @date 01/06/2009
	 * 
	 * @param anoMesReferenciaArrecadacao
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarDevolucoesDescontosPagamentoAVistaCampanhaCrianca(
			Integer anoMesReferenciaArrecadacao, Integer idLocalidade, Integer idRDComPercentualDoacao)
			throws ErroRepositorioException;
    
    /**
	 * [UC0259] - Processar Pagamento com Código de Barras
	 *
	 * [SB0014] - Processar Pagamento Legado COSANPA - CONTA
	 *
	 * @author Raphael Rossiter
	 * @date 01/06/2009
	 *
	 * @param numeroFatura
	 * @return Object[]
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarExistenciaContaPorNumeroFatura(String numeroFatura) throws ErroRepositorioException ;
	
	/**
	 * [UC0259] - Processar Pagamento com Código de Barras
	 *
	 * [SB0014] - Processar Pagamento Legado COSANPA - DOCUMENTO DE COBRANÇA
	 *
	 * @author Raphael Rossiter
	 * @date 01/06/2009
	 *
	 * @param numeroDocumentoFatura
	 * @return Object[]
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarParmsCobrancaDocumentoPorNumeroDocumentoFatura(String numeroDocumentoFatura) 
		throws ErroRepositorioException ;
	
	/**
	 * [UC0259] - Processar Pagamento com Código de Barras
	 *
	 * [SB0014] - Processar Pagamento Legado COSANPA - DOCUMENTO DE COBRANÇA
	 *
	 * @author Raphael Rossiter
	 * @date 02/06/2009
	 *
	 * @param idCobrancaDocumento
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarCobrancaDocumentoItem(Integer idCobrancaDocumento) throws ErroRepositorioException ;
    
    /**
     * [UC0300] Classificar Pagamentos e Devoluções
     * 
     * O sistema seleciona os pagamentos com ano/mês de referência da
     * arrecadação igual ou menor que o ano/mês de referência da arrecadação
     * corrente (seleciona a partir da tabela PAGAMENTO para
     * PGMT_AMREFERENCIAARRECADACAO igual ou menor ao
     * PARM_AMREFERENCIAARRECADACAO), com o tipo do documento correspondente a
     * debito a cobrar e o campo DBAC_ID informado e 
     * o ano/mês de referência contábil do débito a cobrar seja 
     * maior ou igual ao ano/mês de referência do faturamento 
     * 
     * @author Bruno Barros
     * @data 16/06/2009
     * 
     * @param anoMesReferenciaFaturamento
     * @param idLocalidade
     * @param referenciafaturamento
     * @return Collection<Object[]>
     */
    public Collection<Object[]> pesquisarPagamentosPorDebitoACobrarHistoricoComDebitoInformadoRefContabil(
            Integer anoMesReferencia, Integer idLocalidade )
            throws ErroRepositorioException;   
    
    /**
	 * [UC0259] - Processar Pagamento com Código de Barras
	 *
	 * [SB0014] - Processar Pagamento Legado COSANPA - GUIA DE PAGAMENTO
	 *
	 * @author Raphael Rossiter
	 * @date 29/06/2009
	 *
	 * @param numeroFatura
	 * @return Object[]
	 * @throws ErroRepositorioException
	 */
	public Object[] pesquisarExistenciaGuiaPagamentoPorNumeroGuiaFatura(String numeroGuiaFatura) throws ErroRepositorioException ;

	/**
	 * [UC0264] - Distribuir Dados do Código de Barras
	 * 
	 * [SB0008] - Distribuir Pagamento Legado COSANPA
	 * 
	 * @author Raphael Rossiter
	 * @created 27/07/2009
	 * 
	 * @param numeroFatura
	 * @return Object[]
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarFaturaPorNumeroFatura(String numeroFatura) throws ErroRepositorioException ;
	
	/**
	 * [UC0259] - Processar Pagamento com Código de Barras
	 * 
	 * [SB0016] - Processar Pagamento Antecipado de Conta
	 *
	 * @author Raphael Rossiter
	 * @date 13/10/2009
	 *
	 * @param idGuiaPagamento
	 * @return Conta
	 * @throws ErroRepositorioException
	 */
	public Conta pesquisarContaParaPagamentoParcial(Integer idGuiaPagamento) 
		throws ErroRepositorioException ;
	
	/**
	 * [UC0264] - Distribuir Dados do Código de Barras
	 * 
	 * [SB0008] - Distribuir Pagamento Legado COSANPA
	 * 
	 * @author Raphael Rossiter
	 * @created 27/07/2009
	 * 
	 * @param numeroFatura
	 * @return Object[]
	 * @throws ErroRepositorioException
	 */
	public Fatura pesquisarFaturaPorNumeroFaturaObjetpCompleto(String numeroFatura)
			throws ErroRepositorioException ;
	
	/**
	 * [UC0744] Gerar Comparativo do Faturamento, Arrecadação e Expurgo
	 * 
	 * @author Rômulo Aurélio
	 * @data 26/10/2008
	 * Pesquisa Quantidadede contas faturas para localidade/ referência
	*/
	public Object[] pesquisarQuantidadeContasFaturadas(Integer idLocalidade,
			Integer anoMesReferencia) throws ErroRepositorioException;
	
	/**
	 * [UC0744] Gerar Comparativo do Faturamento, Arrecadação e Expurgo
	 * 
	 * @author Rômulo Aurélio
	 * @data 26/10/2008
	 * Quantidade/valor de documentos pagoa para unidade de negócio / referência
	 */ 
	public Object[] pesquisarQuantidadeDocumentosPagos(Integer idLocalidade,
			Integer anoMesReferencia) throws ErroRepositorioException;
	
	/**
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * Pesquisa os pagamentos do Imovel pesquisarPagamentoImovelParaPaginacao
	 * 
	 * @author Arthur Carvalho
	 * @date 16/12/09
	 * 
	 * @return Collection<Pagamento>
	 * @throws ErroRepositorioException
	 */

	public Integer pesquisarPagamentoCount(String idImovel,
			String idCliente, String idTipoRelacao, String localidadeInicial,
			String localidadeFinal, String idAvisoBancario,
			String idArrecadador, String periodoArrecadacaoInicial,
			String periodoArrecadacaoFinal, String periodoPagamentoInicio,
			String periodoPagamentoFim, Date dataPagamentoInicial,
			Date dataPagamentoFinal, String[] idsPagamentosSituacoes,
			String[] idsDebitosTipos, String[] idsArrecadacaoForma,
			String[] idsDocumentosTipos, String valorPagamentoInicial, 
            String valorPagamentoFinal ) throws ErroRepositorioException;
	
	/**
	 * Pesquisa Guia pelo Id
	 * 
	 * @author Hugo Amorim
	 * @date 11/01/2010
	 * 
	 * @param idGuia
	 * @return idGuia
	 * @exception ErroRepositorioException
	 *                
	 */
	public Integer pesquisarExistenciaGuiaPagamento(Integer idGuia) throws ErroRepositorioException;
	
	/**
	 * [UC0978] Pesquisa Relatório de Pagamento para Entidades Beneficentes Analitico
	 * 
	 * @author Daniel Alves
	 * @data   26/01/2010
	 * @param  anoMesInicial = periodo inicial do relatorio
	 *         anoMesFinal   = periodo final do relatorio
	 *         idUnidadeBeneficente
	 *         idGerenciaRegional
	 *         idUnidadeNegocio
	 *         idLocalidade
	 */ 
	public Collection pesquisarPagamentoEntidadesBeneficentesAnalitico(String anoMesInicial, String anoMesFinal,
			String idEntidadeBeneficente, String idGerenciaRegional, 
			String idUnidadeNegocio, String idLocalidade, int opcaoTotalizacao) throws ErroRepositorioException;
	
	
	/**
	 * [UC0978] Pesquisa Relatório de Pagamento para Entidades Beneficentes Sintetico
	 * 
	 * @author Daniel Alves
	 * @data   26/01/2010
	 * @param  anoMesInicial = periodo inicial do relatorio
	 *         anoMesFinal   = periodo final do relatorio
	 *         idUnidadeBeneficente
	 *         idGerenciaRegional
	 *         idUnidadeNegocio
	 *         idLocalidade
	 */ 
	public Collection pesquisarPagamentoEntidadesBeneficentesSintetico(String anoMesInicial, String anoMesFinal,
			String idEntidadeBeneficente, String idGerenciaRegional, String idUnidadeNegocio, String idLocalidade,
			int opcaoTotalizacao
			) throws ErroRepositorioException;
	/**
	 * [UC0276] Encerrar Arrecadação do Mês
	 * 
	 * Pesquisar o pagamento
	 * 
	 * @author Ivan Sergio
	 * @date 26/03/2010
	 * 
	 * @param idPagamento
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Pagamento pesquisarPagamentoParaEncerrarArrecadacao(Integer idPagamento)
			throws ErroRepositorioException;
	
	
	/**
	 * [UC0978] Count para Relatório de Pagamento para Entidades Beneficentes Analitico
	 * 
	 * @author Daniel Alves
	 * @data   26/01/2010
	 * @param  anoMesInicial = periodo inicial do relatorio
	 *         anoMesFinal   = periodo final do relatorio
	 *         idUnidadeBeneficente
	 *         idGerenciaRegional
	 *         idUnidadeNegocio
	 *         idLocalidade
	 *         opcaoTotalizacao
	 * @exception ErroRepositorioException
	 */ 
	public int pesquisarPagamentoEntidadesBeneficentesAnaliticoCount(String anoMesInicial, String anoMesFinal,
			String idEntidadeBeneficente, String idGerenciaRegional, String idUnidadeNegocio, String idLocalidade,
			int opcaoTotalizacao
			) throws ErroRepositorioException;
	
	/**
	 * [UC0978] Count para Relatório de Pagamento para Entidades Beneficentes Sintetico
	 * 
	 * @author Daniel Alves
	 * @data   26/01/2010
	 * @param  anoMesInicial = periodo inicial do relatorio
	 *         anoMesFinal   = periodo final do relatorio
	 *         idUnidadeBeneficente
	 *         idGerenciaRegional
	 *         idUnidadeNegocio
	 *         idLocalidade
	 *         opcaoTotalizacao
	 * @exception ErroRepositorioException
	 */ 
	public int pesquisarPagamentoEntidadesBeneficentesSinteticoCount(String anoMesInicial, String anoMesFinal,
			String idEntidadeBeneficente, String idGerenciaRegional, String idUnidadeNegocio, String idLocalidade,
			int opcaoTotalizacao
			) throws ErroRepositorioException;	
	
	
	/**
	 * [UC0927]  Confirmar Cartão de Crédito/Débito
	 *
	 * @author Raphael Rossiter
	 * @date 03/02/2010
	 *
	 * @param idArrecadador
	 * @param numeroNsa
	 * @param codigoOpcaoExtrato
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarNumeroNsaPorArrecadador(Integer idArrecadador, Integer numeroNsa,
			String codigoOpcaoExtrato) throws ErroRepositorioException ;
	
	/**
	 * [UC0259] - Processar Pagamento com código de Barras
	 * 
	 * [SB0019]  Gerar Débitos/Créditos Parcelas Antecipadas 
	 *
	 * @author Raphael Rossiter
	 * @date 13/04/2010
	 *
	 * @param idDebitoACobrar
	 * @param numeroPrestacoesCobradas
	 * @param numeroPrestacoesAntecipadas
	 * @throws ErroRepositorioException
	 */
	public void atualizarNumeroPrestacoesAntecipadasECobradas(Integer idDebitoACobrar, Integer numeroPrestacoesCobradas,
			Integer numeroPrestacoesAntecipadas) throws ErroRepositorioException ;
	
	/**
	 * [UC0259] - Processar Pagamento com código de Barras
	 * 
	 * [SB0019]  Gerar Débitos/Créditos Parcelas Antecipadas 
	 *
	 * @author Raphael Rossiter
	 * @date 14/04/2010
	 *
	 * @param idCreditoARealizar
	 * @param numeroPrestacoesRealizadas
	 * @param numeroPrestacoesAntecipadas
	 * @throws ErroRepositorioException
	 */
	public void atualizarNumeroPrestacoesAntecipadasERealizadas(Integer idCreditoARealizar, Integer numeroPrestacoesRealizadas,
			Integer numeroPrestacoesAntecipadas) throws ErroRepositorioException ;
	
	
	/**
	 * 
	 * [UC0259]  Processar Pagamento com Código de Barras
	 * 
	 * [SB0019]  Gerar Débitos/Créditos Parcelas Antecipadas.
	 *
	 * @author Raphael Rossiter
	 * @date 19/04/2010
	 *
	 * @param idParcelamento
	 * @return
	 * @throws ErroRepositorioException
	 */
	public DebitoACobrar pesquisarDebitoACobrarJurosParcelamento(Integer idParcelamento)
	throws ErroRepositorioException ;
	
	/**
	 * [UC0259]  Processar Pagamento com Código de Barras
	 * 
	 * [SB0019]  Gerar Débitos/Créditos Parcelas Antecipadas. 
	 *
	 * @author Raphael Rossiter
	 * @date 19/04/2010
	 *
	 * @param debitoACobrar
	 * @throws ErroRepositorioException
	 */
	public void atualizarNumeroParcelasBonus(DebitoACobrar debitoACobrar) throws ErroRepositorioException ;

	/**
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * Pesquisa os pagamentos do tipo Aviso Bancario
	 * pesquisarPagamentoAvisoBancario historico
	 * 
	 * @author Arthur Carvalho
	 * @date 12/05/10
	 * 
	 * @return Collection<Pagamento>
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarPagamentoHistoricoAvisoBancarioCount(String idImovel,
			String idCliente, String idTipoRelacao, String localidadeInicial,
			String localidadeFinal, String idAvisoBancario,
			String idArrecadador, String periodoArrecadacaoInicial,
			String periodoArrecadacaoFinal, String periodoPagamentoInicio,
			String periodoPagamentoFim, Date dataPagamentoInicial,
			Date dataPagamentoFinal, String[] idsPagamentosSituacoes,
			String[] idsDebitosTipos, String[] idsArrecadacaoForma,
			String[] idsDocumentosTipos, String valorPagamentoInicial, 
            String valorPagamentoFinal) throws ErroRepositorioException;
	
		/**
		 * [UC0322] Inserir Guia Devolucao.
		 * 
		 * [FS0023] Verificar crédito a realizar. Verificarasds 
		 *
		 * @author Hugo Leonardo
		 * @date 26/05/2010
		 *
		 * @param idImovel, anoMesReferenciaConta
		 * @throws ErroRepositorioException
		 */
		public Integer verificarExistenciaCreditoARealizar(Integer idImovel, Integer anoMesReferenciaConta)
				throws ErroRepositorioException;
		
		/**
		 * [UC0322] Inserir Guia Devolucao.
		 * 
		 * [FS0023] Verificar crédito a realizar Histórico. Verificarasds 
		 *
		 * @author Hugo Leonardo
		 * @date 26/05/2010
		 *
		 * @param idImovel, anoMesReferenciaConta
		 * @throws ErroRepositorioException
		 */
		public Integer verificarExistenciaCreditoARealizarHistorico(Integer idImovel, Integer anoMesReferenciaConta)
				throws ErroRepositorioException;
		
		/**
		 * [UC0194] Inserir Crédito a realizar.
		 * 		[FS0013] Verificar Guia devolução. Verificarasds 
		 *
		 * @author Hugo Leonardo
		 * @date 27/05/2010
		 *
		 * @param idImovel, anoMesReferenciaGuiaDevolucao
		 * @throws ControladorException
		 */
		public Integer verificarExistenciaGuiaDevolucao(Integer idImovel, Integer anoMesReferenciaGuiaDevolucao)
				throws ErroRepositorioException;
		
		/**
		 * [UC0977] - Registrar Movimento Cartão de Crédito
		 * 
		 * [SB0006 Distribuir Dados do Registro de Movimento do Arrecadador] 
		 *
		 * @author Raphael Rossiter
		 * @date 08/06/2010
		 *
		 * @param parcelamentoPagamentoCartaoCredito
		 * @throws ErroRepositorioException
		 */
		public void confirmarPagamentoCartaoCreditoOperadora(ParcelamentoPagamentoCartaoCredito parcelamentoPagamentoCartaoCredito) 
			throws ErroRepositorioException ;
		
		/**
		 * [UC0977] - Registrar Movimento Cartão de Crédito
		 * 
		 * [SB0006 Distribuir Dados do Registro de Movimento do Arrecadador] 
		 *
		 * @author Raphael Rossiter
		 * @date 08/06/2010
		 *
		 * @param pagamentoCartaoDebito
		 * @throws ErroRepositorioException
		 */
		public void confirmarPagamentoCartaoDebitoOperadora(PagamentoCartaoDebito pagamentoCartaoDebito) 
			throws ErroRepositorioException ;

	/**
	 * 
	 * [UC0511] Filtrar Contrato Arrecadador
	 * @author Arthur Carvalho
	 * @date 26/05/10
	 * 
	 */
	public boolean verificarExistenciaContrato(String numeroContrato) throws ErroRepositorioException;
	
	/**
	 * [UC0339] Consultar Dados Diários da Arrecadação
	 *
	 * @author Hugo Amorim
	 * @date 29/06/2010
	 *
	 * @throws ControladorException
	 */
	public Date pesquisarDataProcessamentoMes(Integer anoMes) throws ErroRepositorioException;
	
	/**
	 * [UC0629] Consultar Arquivo Texto para Leitura
	 *
	 * @author Raphael Rossiter
	 * @date 29/06/2010
	 *
	 * @param helper
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer filtrarArquivoTextoRoteiroEmpresaCount(ConsultarArquivoTextoRoteiroEmpresaHelper helper)
			throws ErroRepositorioException ;
	
	/**
	 * [UC0629] Consultar Arquivo Texto para Leitura 
	 *
	 * @author Raphael Rossiter
	 * @date 29/06/2010
	 *
	 * @param helper
	 * @param numeroPagina
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection filtrarArquivoTextoRoteiroEmpresaParaPaginacao(ConsultarArquivoTextoRoteiroEmpresaHelper helper, Integer numeroPagina)
		throws ErroRepositorioException ;
	
	/**
	 * [UC1043] Gerar Relatório Análise Pagamento Cartão Débito
	 *
	 * @author Hugo Amorim
	 * @date 21/06/2010
	 *
	 * @throws ErroRepositorioException
	 */
	public Integer relatorioAnalisePagamentoCartaoDebitoCount(
			ConsultarRelatorioAnalisePagamentoCartaoDebitoHelper helper)
			throws ErroRepositorioException;
	
	/**
	 * [UC1043] Gerar Relatório Análise Pagamento Cartão Débito
	 *
	 * @author Hugo Amorim
	 * @date 21/06/2010
	 *
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarDadosRelatorioAnalisePagamentoCartaoDebito(
			ConsultarRelatorioAnalisePagamentoCartaoDebitoHelper helper)
			throws ErroRepositorioException;
	
	/**
	 * [UC1043] Gerar Relatório Análise Pagamento Cartão Débito
	 *
	 * @author Hugo Amorim
	 * @date 21/06/2010
	 *
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarDadosItenRelatorioAnalisePagamentoCartaoDebito(
			Integer integer)throws ErroRepositorioException;
	
	/**
	 * [UC1043] Gerar Relatório Análise Pagamento Cartão Débito
	 *
	 * @author Hugo Amorim
	 * @date 21/06/2010
	 *
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarDetalheItenRelatorioAnalisePagamentoCartaoDebito(
			Integer tipoItem, Integer idItem)throws ErroRepositorioException;
	
	/**
	 * [UC0724] - Processar Pagamento com Ficha de Compensação 
	 *
	 * @author Raphael Rossiter
	 * @date 24/11/2010
	 *
	 * @param idConta
	 * @return CobrancaDocumento
	 * @throws ErroRepositorioException
	 */
	public CobrancaDocumento pesquisarCobrancaDocumentoProcessarFichaCompensacao(Integer idCobrancaDocumento)
			throws ErroRepositorioException ;
	
	/**
	 * [UC0339] Consultar Dados Diários da Arrecadação
	 *
	 * @author Mariana Victor
	 * @date 01/02/2011
	 *
	 * @throws ErroRepositorioException
	 */
	public BigDecimal pesquisarFaturamentoCobradoEmConta(Integer anoMes)
			throws ErroRepositorioException;
	
	/**
	 * [UC0339] Consultar Dados Diários da Arrecadação
	 *
	 * @author Arthur Carvalho
	 * @date 22/03/2011
	 *
	 * @throws ErroRepositorioException
	 */
	public BigDecimal pesquisarFaturamentoCobradoEmContaComQuebra(Integer anoMes, Integer idGerenciaRegional, Integer idCategoria)
			throws ErroRepositorioException;
	
	/**
	 * [UC0188] Manter Guia de Pagamento
	 *
	 * [FS0019]  Verificar bloqueio de guia de pagamento
	 * 
	 * @author Mariana Victor
	 * @date 27/04/2011
	 *
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> verificarBloqueioGuiaPagamento(Collection<GuiaPagamento> guiasPagamentos)
			throws ErroRepositorioException;
	
	/**
	 * 
	 * [UC0300] Classificar Pagamentos e Devoluções
	 * 
	 * @author Raphael Rossiter
	 * @date 01/06/2011
	 * 
	 * @param idLocalidade
	 * @param anoMesReferencia
	 * 
	 * @throws ErroRepositorioException
	 */
	public void atualizarPagamentoBatimentoRelatorioPrimeiraSituacao(Integer idLocalidade, Integer anoMesReferencia) 
		throws ErroRepositorioException ;
	
	/**
	 * 
	 * [UC0300] Classificar Pagamentos e Devoluções
	 * 
	 * @author Raphael Rossiter
	 * @date 01/06/2011
	 * 
	 * @param idLocalidade
	 * @param anoMesReferencia
	 * 
	 * @throws ErroRepositorioException
	 */
	public void atualizarPagamentoBatimentoRelatorioSegundaSituacao(Integer idLocalidade, Integer anoMesReferencia) 
		throws ErroRepositorioException ;
	
	/**
	 * 
	 * [UC0300] Classificar Pagamentos e Devoluções
	 * 
	 * @author Raphael Rossiter
	 * @date 01/06/2011
	 * 
	 * @param idLocalidade
	 * @param anoMesReferencia
	 * 
	 * @throws ErroRepositorioException
	 */
	public void atualizarPagamentoBatimentoRelatorioTerceiraSituacao(Integer idLocalidade, Integer anoMesReferencia) 
		throws ErroRepositorioException ;
	
	/**
	 * 
	 * [UC0300] Classificar Pagamentos e Devoluções
	 * 
	 * @author Raphael Rossiter
	 * @date 01/06/2011
	 * 
	 * @param idLocalidade
	 * @param anoMesReferencia
	 * 
	 * @throws ErroRepositorioException
	 */
	public void atualizarPagamentoBatimentoRelatorioQuartaSituacao(Integer idLocalidade, Integer anoMesReferencia) 
		throws ErroRepositorioException ;

	/**
	 * [UC0724] - Processar Pagamento com Ficha de Compensação
	 * 
	 * @author Mariana Victor
	 * @data 04/08/2011
	 */
	public CobrancaDocumentoItem pesquisarCobrancaDocumentoItemProcessarFichaCompensacao(Integer idPrestacao) 
		throws ErroRepositorioException;
	
	
	/**
	 * [UC0724] - Processar Pagamento com Ficha de Compensação 
	 *
	 * @author Raphael Rossiter
	 * @date 26/07/2011
	 *
	 * @param idGuiaPagamento
	 * @return GuiaPagamento
	 * @throws ErroRepositorioException
	 */
	public GuiaPagamento pesquisarGuiaPagamentoProcessarFichaCompensacao(Integer idGuiaPagamento)
			throws ErroRepositorioException ;
	
	/**
	 * [UC 1215]  Gerar Relatório de Documentos não Aceitos
	 * 
	 * @author Raimundo Martins
	 *
	 * @date 19/08/2011
	 */
	public List<RelatorioDocumentoNaoAceitosBean> pesquisarDocumentosNaoAceitos(Arrecadador arrecadador, 
			String periodoInicial, String periodoFinal, Integer movimentoArrecadadorCodigo, 
			AvisoBancario avisoBancario, ArrecadacaoForma arrecadacaoForma) throws ErroRepositorioException;
	
	public boolean existeClienteIdDocNaoIdentificado();

	/**
	 * [UC 1217]  Gerar Relatório de Transferencia de Pagamento
	 * 
	 * @author Raimundo Martins
	 *
	 * @date 19/08/2011
	 */
	public List<RelatorioTranferenciaPagamentoBean> pesquisarTransfereciasPagamento(Arrecadador arrecadador, 
			String periodoInicial, String periodoFinal, AvisoBancario avisoBancario, ArrecadacaoForma arrecadacaoForma, 
			DebitoTipo debitoTipo, DocumentoTipo documentoTipo) throws ErroRepositorioException;


	/**
     * [UC0242] - Registrar Movimento dos Arrecadadores
	 * 
	 * @author Mariana Victor
	 * @date 18/08/2011
	 * 
	 * @param codigoConstante
	 * 
	 * @return DebitoTipo
	 * @throws ErroRepositorioException
	 */
	public DebitoTipo obterDebitoTipoCodigoConstante(Integer codigoConstante)
			throws ErroRepositorioException;
	
	/**
     * [UC1214] Informar Acerto Documentos Não Aceitos
	 * 
	 * 3. O sistema identifica os pagamentos com documentos não aceitos 
	 * 	 que foram gerados para um cliente fictício e
	 *   junto com o filtro selecionado pelo usuário.
	 * 
	 * @author Mariana Victor
	 * @date 19/08/2011
	 * 
	 * @param codigoConstante
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarPagamentosDocumentosNaoAceitos(
			InformarAcertoDocumentosNaoAceitosPagamentoHelper helper)
			throws ErroRepositorioException;
	
	/**
     * [UC1214] Informar Acerto Documentos Não Aceitos
	 * 
	 *  7.2.1. Total do Pagamento (PGMT _VLPAGAMENTO do pagamento doc. não aceito).
	 * 
	 * @author Mariana Victor
	 * @date 22/08/2011
	 * 
	 * @param idPagamento
	 * 
	 * @return BigDecimal
	 * @throws ErroRepositorioException
	 */
	public BigDecimal pesquisarValorPagamento(
			Integer idPagamento)
			throws ErroRepositorioException;
	
	/**
     * [UC1214] Informar Acerto Documentos Não Aceitos
	 * 
	 * @author Mariana Victor
	 * @date 24/08/2011
	 * 
	 * @param idGuia
	 * 
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarLocalidadeGuiaPagamento(
			Integer idGuia)
			throws ErroRepositorioException;
	
	/**
     * [UC1214] Informar Acerto Documentos Não Aceitos
	 * 
	 * @author Mariana Victor
	 * @date 24/08/2011
	 * 
	 * @param idConta
	 * 
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarLocalidadeConta(
			Integer idConta)
			throws ErroRepositorioException;
	
	/**
     * [UC1214] Informar Acerto Documentos Não Aceitos
	 * 
	 * @author Mariana Victor
	 * @date 24/08/2011
	 * 
	 * @param idDebitoACobrar
	 * 
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarLocalidadeDebitoACobrar(
			Integer idDebitoACobrar)
			throws ErroRepositorioException;

	/**
	 * Calcula o valor total do movimento arrecadador
	 * 
	 * @author Gustavo Amaral
	 * @date 14/09/2011
	 * 
	 * @param arrecadadorMovimento
	 * @return Collection
	 * @throws ErroRepositorioException
	 */	
	public Collection pesquisarValorAcertosArrecadadorMovimento(Integer idArrecadadorMovimento)
			throws ErroRepositorioException;
	
	/**
	 * Filtrar a quantidade de pagamento historicos do cliente
	 * 
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * Pesquisa os pagamentos do Cliente
	 * 
	 * @author Rodrigo Cabral
	 * @date 15/09/11
	 * 
	 * @return Collection<Pagamento>
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarPagamentoHistoricoClienteCount(String idImovel,
			String idCliente, String idTipoRelacao, String localidadeInicial,
			String localidadeFinal, String idAvisoBancario,
			String idArrecadador, String periodoArrecadacaoInicial,
			String periodoArrecadacaoFinal, String periodoPagamentoInicio,
			String periodoPagamentoFim, Date dataPagamentoInicial,
			Date dataPagamentoFinal, String[] idsPagamentosSituacoes,
			String[] idsDebitosTipos, String[] idsArrecadacaoForma,
			String[] idsDocumentosTipos, String valorPagamentoInicial, String valorPagamentoFinal) throws ErroRepositorioException;

	/**
	 * Este caso de uso cria um sql que será usado na pesquisa de pagamentos
	 * para o Relatório
	 * 
	 * [UC0255] Filtrar Pagamentos
	 * 
	 * Pesquisa os pagamentos Historico do Cliente
	 * 
	 * @author Rodrigo Cabral
	 * @date 16/09/11
	 * 
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarPagamentoHistoricoClienteRelatorio(
			String idImovel, String idCliente, String idTipoRelacao,
			String localidadeInicial, String localidadeFinal,
			String idAvisoBancario, String idArrecadador,
			String periodoArrecadacaoInicial, String periodoArrecadacaoFinal,
			String periodoPagamentoInicio, String periodoPagamentoFim,
			Date dataPagamentoInicial, Date dataPagamentoFinal,
			String[] idsPagamentosSituacoes, String[] idsDebitosTipos,
			String[] idsArrecadacaoForma, String[] idsDocumentosTipos,
            String valorPagamentoInicial,
            String valorPagamentoFinal)
			throws ErroRepositorioException;
	
	/**
	 * [UC1253] Gerar Debito a Cobrar Contrato de Demanda Imovel Residencial
	 * [SB0001] - Selecionar Contas
	 *
	 * @author Rafael Pinto
	 * @date 23/11/2011
	 *
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarContasNaoPagasContratosDemandasResidencial(
		Integer idLocalidade,Integer idDebitoTipo,Integer idCreditoTipo,Integer anoMesReferenciaArrecadacao)
		throws ErroRepositorioException ;

	/**
	 * Pesquisa o id do cliente da guia de pagamento através do id da Guia de
	 * Pagamento e com CRTP_ID com o valor correspondente a usuário(2)
	 * 
	 * @author Vivianne Sousa
	 * @date 07/12/2011
	 * 
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarIdClienteUsuarioGuiaPagamento(
			Integer idGuiaPagamento) throws ErroRepositorioException;
	
	
	/**
	 * @author Rodrigo Cabral
	 * @date 14/03/2012
	 * 
	 * @throws ErroRepositorioException
	 * @throws SQLException 
	 */	
	public void gerarResumoArrecadacaoAnaliticoDados()
			throws ErroRepositorioException, SQLException;
	
	/**
	 * @author Rodrigo Cabral
	 * @date 14/03/2012
	 * 
	 * @throws ErroRepositorioException
	 * @throws SQLException 
	 */	
	public void gerarResumoArrecadacaoAnaliticoTotais1()
			throws ErroRepositorioException, SQLException;
	
	/**
	 * @author Rodrigo Cabral
	 * @date 14/03/2012
	 * 
	 * @throws ErroRepositorioException
	 * @throws SQLException 
	 */	
	public void gerarResumoArrecadacaoAnaliticoTotais2()
			throws ErroRepositorioException, SQLException;
	
	/**
	 * [UC0629] Consultar Arquivo Texto Leitura
	 * [FS0018] Verificar não possibilidade de mudar a situação do arquivo
	 * 
	 * Verifica se existe rota subdividida com  situação  diferente de finalizada
	 * 
	 * @author Nathalia Santos  
	 * @date 20/03/2012
	 */
	public boolean verificarSituacaoRotaSudividida(Integer idArquivoTextoRoteiroEmpresaDivisao) 
		throws ErroRepositorioException;
	
	/**
	 * [UC0629] Consultar Arquivo Texto Leitura
	 * [FS0018] Verificar não possibilidade de mudar a situação do arquivo
	 * 
	 * Verifica se existe rota subdividida 
	 * 
	 * @author Nathalia Santos  
	 * @date 21/03/2012
	 */
	public boolean verificarExistenciaRotaSudividida(Integer idArquivoTextoRoteiroEmpresaDivisao) 
		throws ErroRepositorioException;
	
	/**
	 * [UC0629] Consultar Arquivo Texto Leitura
	 * [FS0018] Verificar não possibilidade de mudar a situação do arquivo
	 * 
	 * Verifica se existe rota subdividida utilizada no gerar zip
	 * 
	 * @author Nathalia Santos  
	 * @date 21/03/2012
	 */
	public boolean pesquisarArquivoTextoRoteiroEmpresaCount(Integer idArquivoTexto)
		throws ErroRepositorioException ;
	/**
	 * 
	 * [UC1349] Gerar Movimento de Débito Automático de Parcelamento por Cliente
	 * [SB0002] Carregar Lista de Bancos
	 * 
	 * @author Hugo Azevedo
	 * @date 27/06/2012
	 * 
	 */
	public Collection obterBancosContratoVigenteContasDebitoAutomatico() throws ErroRepositorioException;
	

	/**
	 * [UC0978] Pesquisa Relatório de Pagamento para Entidades Beneficentes Analitico
	 * 
	 * @author Erivan Sousa
	 * @data   27/07/2012
	 * @param  anoMesInicial = periodo inicial do relatorio
	 *         anoMesFinal   = periodo final do relatorio
	 *         idUnidadeBeneficente
	 *         idGerenciaRegional
	 *         idUnidadeNegocio
	 *         idLocalidade
	 *         opcaoTotalizacao
	 * @exception ErroRepositorioException
	 */ 
	public Collection pesquisarPagamentoEntidadesBeneficentesAnaliticoHistorico(String anoMesInicial, String anoMesFinal,
			String idEntidadeBeneficente, String idGerenciaRegional, String idUnidadeNegocio, String idLocalidade,
			int opcaoTotalizacao
			) throws ErroRepositorioException;

	/**
	 * 
	 * [UC1349] Gerar Movimento de Débito Automático de Parcelamento por Cliente
	 * [SB0002] Carregar Lista de Bancos
	 * 
	 * @author Hugo Azevedo
	 * @date 27/06/2012
	 * 
	 */
	public Collection<DebitoAutomaticoMovimentoParcelamentoCliente> obterColecaoDebitoAutomaticoParcCliente(Date dataParcelas, Integer idBanco) throws ErroRepositorioException;

	/**
	 * [UC0978] Pesquisa Relatório de Pagamento para Entidades Beneficentes Sintetico
	 * 
	 * @author Erivan Sousa
	 * @data   30/07/2012
	 * @param  anoMesInicial = periodo inicial do relatorio
	 *         anoMesFinal   = periodo final do relatorio
	 *         idUnidadeBeneficente
	 *         idGerenciaRegional
	 *         idUnidadeNegocio
	 *         idLocalidade
	 *         opcaoTotalizacao
	 * @exception ErroRepositorioException
	 */ 
	public Collection pesquisarPagamentoEntidadesBeneficentesSinteticoHistorico(String anoMesInicial, String anoMesFinal,
			String idEntidadeBeneficente, String idGerenciaRegional, String idUnidadeNegocio, String idLocalidade,
			int opcaoTotalizacao
			) throws ErroRepositorioException ;

	/**
	 * 
	 * [UC1349] Gerar Movimento de Débito Automático de Parcelamento por Cliente
	 * [SB0002] Carregar Lista de Bancos
	 * 
	 * @author Hugo Azevedo
	 * @date 27/06/2012
	 * 
	 */
	public Object[] pesquisaCamposArrecadadorContratoParCliente(Integer idBanco)
			throws ErroRepositorioException;

	/**
	 * [UC0242] - Registrar Movimento de Arrecadadores 
	 * 
	 * retorna o objeto debito automatico movimento
	 * 
	 * @author Mariana Victor
	 * @date 20/06/2012
	 */
	public DebitoAutomaticoMovimentoParcelamentoCliente obterDebitoAutomaticoMovimentoParcelamentoCliente(
			Integer idPrestacaoContratoParcelamento)
			throws ErroRepositorioException;
	
	/**
	 * [UC0276] Encerrar Arrecadação do Mês
	 * 
	 * @author Raphael Rossiter
	 * @date 15/08/2012
	 * 
	 * @param anoMesReferenciaArrecadacao
	 * @return idGuiaPagamentoGeral
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarGuiaPagamentoPorPagamento(Integer idPagamento) throws ErroRepositorioException;
	
	/**
	 * 
	 * [UC1349] Gerar Movimento de Débito Automático de Parcelamento por Cliente
	 * [SB0002] Carregar Lista de Bancos
	 * 
	 * @author Hugo Azevedo
	 * @date 27/06/2012
	 * 
	 */
	
	public DebitoAutomaticoMovimentoParcelamentoCliente obterDebitoAutomaticoMovParcCliente(Integer idMovimento) throws ErroRepositorioException;
	
	/**
	 * [UC 0629] Consultar Arquivo Texto Leitura
	 * 
	 * [SB 0008] Exibir Imagens Anormalidades do Imóvel
	 * 
	 * @author Davi Menezes
	 * @date 31/08/2012
	 */
	public Collection pesquisarFotoMovimentoRoteiroEmpresa(Integer idRota, Integer anoMesReferencia) 
			throws ErroRepositorioException;
	
	/**
	 * [ UC0932 ] - UC0932MonitorarLeiturasTransmitidas
	 * Buscar Imagens Leitura Anormalidades do Imóvel
	 * 
	 * @author Carlos Chaves
	 * @date 24/10/2012
	 */
	public Collection <MovimentoRoteiroEmpresaFoto> pesquisarFotoAnormalidadeImovel(Integer idImovel, Integer anoMesReferencia, Integer idTipoMedicao) throws ErroRepositorioException;
	
	/**
	 * 
	 * [UC0300] Classificar Pagamentos e Devoluções
	 * 
	 * @author Raphael Rossiter
	 * @date 17/01/2013
	 * 
	 * @param idLocalidade
	 * @param anoMesReferencia
	 * 
	 * @throws ErroRepositorioException
	 */
	public void atualizarPagamentoBatimentoRelatorioQuintaSituacao(Integer idLocalidade, Integer anoMesReferencia) 
		throws ErroRepositorioException ;

	/**
	 * [UC 1517] - Baixa Automática dos Pagamentos Não Classificados
	 * [SB 0001] - Selecionar Pagamento Valor Divergente
	 * 
	 * @author Davi Menezes
	 * @date 08/07/2013
	 * 
	 * @param idPagamentoSituacao
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarPagamentosValorNaoConfere(Integer idPagamentoSituacao,Integer idRota) throws ErroRepositorioException;
	
	/**
	 * [UC 1518 ] - Gerar Relatório dos Pagamentos Baixados Automaticamente
	 * [SB 0002 ] - Relatório Analítico
	 * 
	 * @author Davi Menezes
	 * @date 11/07/2013
	 */
	public Collection pesquisarRelatorioAnaliticoPagamentosBaixadosAutomaticamente(PagamentosBaixadosAutomaticamenteRelatorioHelper helper)
			throws ErroRepositorioException;
	
	/**
	 * [UC 1518 ] - Gerar Relatório dos Pagamentos Baixados Automaticamente
	 * [SB 0003 ] - Relatório Sintético
	 * 
	 * @author Davi Menezes
	 * @date 11/07/2013
	 */
	public Collection pesquisarRelatorioSinteticoPagamentosBaixadosAutomaticamente(PagamentosBaixadosAutomaticamenteRelatorioHelper helper)
			throws ErroRepositorioException;
	
	/**
	 * Verificar existencia de conta em Debito carteira movimento
	 * 
	 * @author Davi Menezes
	 * @date 13/11/2013
	 */
	public Integer verificarExistenciaContaDebitoCarteiraMovimento(Integer idConta) throws ErroRepositorioException;
	
	/**
	 * RN2013108067 – Mudança dos boletos bancarios: da carteira 18 para carteira 17
	 * [UC1574] - Solicitar Geracao de Arquivo Carteira 17 
	 * 
	 * @author Diogo Luiz
	 * @Date 07/11/2013
	 *
	 */
	public Collection pesquisaBancosDebitoAutomaticoCarteira17(Collection colecaoIdsFaturamentoGrupo,
		Integer anoMesReferenciaFaturamento, String bancoId) throws ErroRepositorioException;
	
	
	/**
	 * RN2013108067 – Mudança dos boletos bancarios: da carteira 18 para carteira 17
	 * [UC1575] - Gerar de Arquivo Carteira 17 
	 * 
	 * @author Diogo Luiz
	 * @Date 07/11/2013
	 *
	 */
	public Collection gerarArquivoCarteira17(Collection colecaoIdFaturamentoGrupo, Integer anoMesReferencia,
			Integer bancoId) throws ErroRepositorioException;

	/**
	 * RN2013108067 – Mudança dos boletos bancarios: da carteira 18 para carteira 17
	 * [UC1575] - Gerar de Arquivo Carteira 17 
	 * 
	 * @author Diogo Luiz
	 * @Date 07/11/2013
	 *
	 */
	public Collection obterConvenioArrecadacaoMovimento(Integer idBanco) throws ErroRepositorioException;

	
	/**
	 * RN2013108067 – Mudança dos boletos bancarios: da carteira 18 para carteira 17
	 * [UC1575] - Gerar de Arquivo Carteira 17 
	 * 
	 * @author Diogo Luiz
	 * @Date 07/11/2013
	 *
	 */
	public void atualizarNumeroSequencialCarteira17(ArrecadadorMovimento arrecadador, Integer idBanco)
			throws ErroRepositorioException;
	
	/**
	 * [UC XXXX] - Processar amortiza��o de d�vida ativa
	 * 
	 * Pesquisa a data do pagamento referente a guia de pagamento
	 * 
	 * @author Rafael Corr�a
	 * @date 16/02/2014
	 */
	public Date pesquisarDataPagamentoDeGuiaPagamento(Integer idGuiaPagamento) throws ErroRepositorioException;

	/**
	 * [UC XXXX] - Processar amortiza��o de d�vida ativa
	 * 
	 * Pesquisa o pagamento referente ao d�bito a cobrar
	 * 
	 * @author Rafael Corr�a
	 * @date 16/02/2014
	 */
	public Pagamento pesquisarPagamentoDoDebitoACobrar(Integer idDebitoACobrar) throws ErroRepositorioException;
	
	/**
	 * [UC XXXX] - Processar amortiza��o de d�vida ativa
	 * 
	 * Pesquisa os pagamentos referentes a conta
	 * 
	 * @author Rafael Corr�a
	 * @date 17/02/2014
	 */
	public Collection<Pagamento> pesquisarPagamentosClassificadosConta(Integer idConta) throws ErroRepositorioException;
	
	public Collection<PagamentoHistorico> pesquisarPagamentoHistoricoClienteGuiaPagamentoRelatorio(
		String idImovel, String idCliente, String idTipoRelacao,
		String localidadeInicial, String localidadeFinal,
		String idAvisoBancario, String idArrecadador,
		String periodoArrecadacaoInicial, String periodoArrecadacaoFinal,
		String periodoPagamentoInicio, String periodoPagamentoFim,
		Date dataPagamentoInicial, Date dataPagamentoFinal,
		String[] idsPagamentosSituacoes, String[] idsDebitosTipos,
		String[] idsArrecadacaoForma, String[] idsDocumentosTipos)
		throws ErroRepositorioException;
	
	/**
	 * [UC0113] - Faturar Grupo de Faturamento
	 * 
	 * @author Rafael Corr�a
	 * @date 27/06/2014
	 * 
	 * @param idCreditoARealizar
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarQuantidadeDevolucoesPorCreditoARealizar(Integer idCreditoARealizar) throws ErroRepositorioException;
	
	/**
	 * [UC0818] Gerar Hist�rico do Encerramento da Arrecada��o
	 * 
	 * Pesquisa a cole��o de itens de guias de pagamento para o id da guia informada.
	 * 
	 * @author Rafael Corr�a
	 * @date 29/10/2014
	 * 
	 * @param idGuiaPagamento
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarGuiaPagamentoItem(Integer idGuiaPagamento)
			throws ErroRepositorioException;
	
	/**
	 * [UC0818] Gerar Hist�rico do Encerramento da Arrecada��o
	 * 
	 * Pesquisa a cole��o de categoria para os item de guia de pagamento informado.
	 * 
	 * @author Rafael Corr�a
	 * @date 29/10/2014
	 * 
	 * @param idGuiaPagamentoItem
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarGuiaPagamentoItemCategoria(Integer idGuiaPagamentoItem)
			throws ErroRepositorioException;
	
	/**
	 * @author F�bio Aguiar
	 * @date 29/01/2014
	 * 
	 * @throws ErroRepositorioException
	 * @throws SQLException 
	 */	
	public void gerarResumoArrecadacaoAtualizaDados()
			throws ErroRepositorioException, SQLException;
	
	public Collection<Object[]> pesquisarGuiaPagamentoCarne(String idParcelamento) throws ErroRepositorioException;
	
}
