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
package gsan.gui.atendimentopublico;

import gsan.atendimentopublico.FiltroLigacaoOrigem;
import gsan.atendimentopublico.LigacaoOrigem;
import gsan.atendimentopublico.ligacaoagua.FiltroDiametroLigacao;
import gsan.atendimentopublico.ligacaoagua.FiltroMaterialLigacao;
import gsan.atendimentopublico.ligacaoagua.FiltroPerfilLigacao;
import gsan.atendimentopublico.ligacaoagua.FiltroRamalLocalInstalacao;
import gsan.atendimentopublico.ligacaoagua.LigacaoAguaDiametro;
import gsan.atendimentopublico.ligacaoagua.LigacaoAguaMaterial;
import gsan.atendimentopublico.ligacaoagua.LigacaoAguaPerfil;
import gsan.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gsan.atendimentopublico.ligacaoagua.RamalLocalInstalacao;
import gsan.atendimentopublico.ordemservico.FiltroServicoNaoCobrancaMotivo;
import gsan.atendimentopublico.ordemservico.OrdemServico;
import gsan.atendimentopublico.ordemservico.ServicoNaoCobrancaMotivo;
import gsan.atendimentopublico.ordemservico.ServicoTipo;
import gsan.cadastro.cliente.Cliente;
import gsan.cadastro.cliente.ClienteImovel;
import gsan.cadastro.cliente.ClienteRelacaoTipo;
import gsan.cadastro.cliente.FiltroClienteImovel;
import gsan.cadastro.imovel.FiltroImovel;
import gsan.cadastro.imovel.Imovel;
import gsan.cadastro.localidade.Quadra;
import gsan.cadastro.localidade.bean.IntegracaoQuadraFaceHelper;
import gsan.cadastro.sistemaparametro.SistemaParametro;
import gsan.fachada.Fachada;
import gsan.faturamento.debito.FiltroDebitoTipo;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.micromedicao.consumo.LigacaoTipo;
import gsan.micromedicao.hidrometro.FiltroHidrometroLocalInstalacao;
import gsan.micromedicao.hidrometro.FiltroHidrometroProtecao;
import gsan.micromedicao.hidrometro.Hidrometro;
import gsan.micromedicao.hidrometro.HidrometroCapacidade;
import gsan.micromedicao.hidrometro.HidrometroLocalInstalacao;
import gsan.micromedicao.hidrometro.HidrometroProtecao;
import gsan.seguranca.acesso.PermissaoEspecial;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.filtro.ParametroNulo;
import gsan.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action respons�vel pela pre-exibi��o da pagina de inserir bairro
 * 
 * @author Leandro Cavalcanti
 * @created 20 de Junho de 2006
 */
public class ExibirEfetuarLigacaoAguaComInstalacaoHidrometroAction extends
		GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Mudar isso quando tiver esquema de seguran�a
		HttpSession sessao = httpServletRequest.getSession(false);

		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");

		Fachada fachada = Fachada.getInstancia();

		// -----------------------------------------------------------
		// Verificar permiss�o especial
		boolean temPermissaoMotivoNaoCobranca = fachada
				.verificarPermissaoInformarMotivoNaoCobranca(usuarioLogado);
		// -----------------------------------------------------------

		// Set no mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("efetuarLigacaoAguaComInstalacaoHidrometro");

		EfetuarLigacaoAguaComInstalacaoHidrometroActionForm efetuarLigacaoAguaComInstalacaoHidrometroActionForm = (EfetuarLigacaoAguaComInstalacaoHidrometroActionForm) actionForm;

		if (httpServletRequest.getParameter("desfazer") != null) {
			efetuarLigacaoAguaComInstalacaoHidrometroActionForm
					.setAceitaLacre(null);
			efetuarLigacaoAguaComInstalacaoHidrometroActionForm
					.setSituacaoCavalete(null);
		}

		Boolean veioEncerrarOS = null;

		if (httpServletRequest.getAttribute("veioEncerrarOS") != null
				|| (efetuarLigacaoAguaComInstalacaoHidrometroActionForm
						.getVeioEncerrarOS() != null && efetuarLigacaoAguaComInstalacaoHidrometroActionForm
						.getVeioEncerrarOS().equals("true"))) {

			veioEncerrarOS = Boolean.TRUE;
		} else {
			if (efetuarLigacaoAguaComInstalacaoHidrometroActionForm
					.getVeioEncerrarOS() != null
					&& !efetuarLigacaoAguaComInstalacaoHidrometroActionForm
							.getVeioEncerrarOS().equals("")) {
				if (efetuarLigacaoAguaComInstalacaoHidrometroActionForm
						.getVeioEncerrarOS().toLowerCase().equals("true")) {
					veioEncerrarOS = veioEncerrarOS = Boolean.TRUE;
				} else {
					veioEncerrarOS = veioEncerrarOS = Boolean.FALSE;
				}
			} else {
				veioEncerrarOS = Boolean.FALSE;
			}
		}

		this.consultaSelectObrigatorio(sessao);

		// Variavel responsav�l pelo preenchimento do imovel no formul�rio
		String idOrdemServico = null;

		if (efetuarLigacaoAguaComInstalacaoHidrometroActionForm
				.getIdOrdemServico() != null) {
			idOrdemServico = efetuarLigacaoAguaComInstalacaoHidrometroActionForm
					.getIdOrdemServico();
		} else {
			idOrdemServico = (String) httpServletRequest
					.getAttribute("veioEncerrarOS");

			efetuarLigacaoAguaComInstalacaoHidrometroActionForm
					.setDataLigacao((String) httpServletRequest
							.getAttribute("dataEncerramento"));

			efetuarLigacaoAguaComInstalacaoHidrometroActionForm
					.setDataInstalacao((String) httpServletRequest
							.getAttribute("dataEncerramento"));

			sessao.setAttribute("caminhoRetornoIntegracaoComercial",
					httpServletRequest
							.getAttribute("caminhoRetornoIntegracaoComercial"));
		}

		if (httpServletRequest.getAttribute("semMenu") != null) {
			sessao.setAttribute("semMenu", "SIM");
		} else {
			sessao.removeAttribute("semMenu");
		}
		OrdemServico ordemServico = null;
		String matriculaImovel = null;
		Imovel imovelComLocalidade = null;

		// Permissao Especial Efetuar Ligacao de Agua com Instalacao de
		// hidrometro sem RA

		boolean efetuarLigacaoAguaComInstalacaodeHidrometroSemRA = Fachada
				.getInstancia()
				.verificarPermissaoEspecial(
						PermissaoEspecial.EFETUAR_LIGACAO_DE_AGUA_COM_INSTALACAO_DE_HIDROMETRO_SEM_RA,
						usuarioLogado);
		
		efetuarLigacaoAguaComInstalacaoHidrometroActionForm
				.setPermissaoEfetuarLigacaoAguaComInstalacaoHidrometrosemRA("false");

		if (!veioEncerrarOS) {
			httpServletRequest.setAttribute(
					"efetuarLigacaoAguaComInstalacaodeHidrometroSemRA",
					efetuarLigacaoAguaComInstalacaodeHidrometroSemRA);

			if (efetuarLigacaoAguaComInstalacaodeHidrometroSemRA) {
				efetuarLigacaoAguaComInstalacaoHidrometroActionForm
						.setPermissaoEfetuarLigacaoAguaComInstalacaoHidrometrosemRA("true");
			}
		}
		String idImovel = efetuarLigacaoAguaComInstalacaoHidrometroActionForm
				.getIdImovel();

		if (idImovel != null && !idImovel.trim().equals("")) {

			// Pesquisa o imovel na base
			String inscricaoImovelEncontrado = fachada
					.pesquisarInscricaoImovel(new Integer(idImovel));

			if (inscricaoImovelEncontrado != null
					&& !inscricaoImovelEncontrado.equalsIgnoreCase("")) {

				efetuarLigacaoAguaComInstalacaoHidrometroActionForm
						.setMatriculaImovel(idImovel);

				efetuarLigacaoAguaComInstalacaoHidrometroActionForm
						.setInscricaoImovel(inscricaoImovelEncontrado);

				Imovel imovel = (Imovel) fachada
						.pesquisarDadosImovel(new Integer(idImovel));

				// [FS0002] Validar Situa��o de �gua do Im�vel.
				if (imovel.getLigacaoAguaSituacao().getId().intValue() != LigacaoAguaSituacao.POTENCIAL
						.intValue()
						&& imovel.getLigacaoAguaSituacao().getId().intValue() != LigacaoAguaSituacao.FACTIVEL
								.intValue()
						&& imovel.getLigacaoAguaSituacao().getId().intValue() != LigacaoAguaSituacao.EM_FISCALIZACAO
								.intValue()) {

					throw new ActionServletException(
							"atencao.situacao_validar_ligacao_agua_invalida_exibir",
							null, imovel.getLigacaoAguaSituacao()
									.getDescricao());
				}

				/*
				 * [FS0007] Verificar Situa��o Rede de �gua na Quadra
				 * 
				 * Integra��o com o conceito de face da quadra
				 * Raphael Rossiter em 22/05/2009
				 */
				IntegracaoQuadraFaceHelper integracao = fachada.integracaoQuadraFace(imovel.getId());
				
				if ((integracao.getIndicadorRedeAgua()).equals(Quadra.SEM_REDE)) {
				
					throw new ActionServletException("atencao.seituacao_rede_agua_quadra", 
					null, imovel.getId() + "");
				}

				// [FS0006] Verificar Situa��o do Imovel
				if (imovel.getIndicadorExclusao() != ConstantesSistema.INDICADOR_IMOVEL_ATIVO) {
					throw new ActionServletException(
							"atencao.situacao_imovel_indicador_exclusao", null,
							imovel.getId() + "");
				}

				sessao.setAttribute("imovel", imovel);

				if (imovel != null) {

					/*
					 * FiltroImovel filtroImovel = new FiltroImovel();
					 * filtroImovel.adicionarCaminhoParaCarregamentoEntidade("localidade.hidrometroLocalArmazenagem");
					 * filtroImovel.adicionarParametro(new
					 * ParametroSimples(FiltroImovel.ID, imovel.getId()));
					 * 
					 * Collection colecaoImoveis =
					 * fachada.pesquisar(filtroImovel, Imovel.class.getName());
					 * 
					 * imovelComLocalidade = (Imovel)
					 * Util.retonarObjetoDeColecao(colecaoImoveis);
					 */

					if (imovel.getLocalidade().getHidrometroLocalArmazenagem() != null) {
						efetuarLigacaoAguaComInstalacaoHidrometroActionForm
								.setIdLocalArmazenagem(imovel.getLocalidade()
										.getHidrometroLocalArmazenagem()
										.getId().toString());
					}

					// Matricula Im�vel
					efetuarLigacaoAguaComInstalacaoHidrometroActionForm
							.setMatriculaImovel(imovel.getId().toString());

					// Situa��o da Liga��o de Agua
					String situacaoLigacaoAgua = imovel
							.getLigacaoAguaSituacao().getDescricao();
					efetuarLigacaoAguaComInstalacaoHidrometroActionForm
							.setSituacaoLigacaoAgua(situacaoLigacaoAgua);

					// Situa��o da Liga��o de Esgoto
					String situacaoLigacaoEsgoto = imovel
							.getLigacaoEsgotoSituacao().getDescricao();
					efetuarLigacaoAguaComInstalacaoHidrometroActionForm
							.setSituacaoLigacaoEsgoto(situacaoLigacaoEsgoto);

					this
							.pesquisarCliente(
									efetuarLigacaoAguaComInstalacaoHidrometroActionForm,
									new Integer(idImovel));
				}

				httpServletRequest.setAttribute("habilitaDataLigacao", true);

			} else {

				httpServletRequest.setAttribute("corImovel", "exception");

				efetuarLigacaoAguaComInstalacaoHidrometroActionForm
						.setInscricaoImovel(ConstantesSistema.CODIGO_IMOVEL_INEXISTENTE);
			}

		}

		// [FS0001] Validar Ordem de Servi�o.
		if (idOrdemServico != null && !idOrdemServico.trim().equals("")) {

			efetuarLigacaoAguaComInstalacaoHidrometroActionForm
					.setVeioEncerrarOS("" + veioEncerrarOS);

			ordemServico = fachada.recuperaOSPorId(new Integer(idOrdemServico));

			if (ordemServico != null) {

				fachada.validarLigacaoAguaComInstalacaoHidrometroExibir(
						ordemServico, veioEncerrarOS);

				sessao.setAttribute("ordemServico", ordemServico);

				efetuarLigacaoAguaComInstalacaoHidrometroActionForm
						.setIdOrdemServico(idOrdemServico);
				efetuarLigacaoAguaComInstalacaoHidrometroActionForm
						.setNomeOrdemServico(ordemServico.getServicoTipo()
								.getDescricao());

				Imovel imovel = ordemServico.getRegistroAtendimento()
						.getImovel();

				ServicoTipo servicoTipo = ordemServico.getServicoTipo();

				BigDecimal valorDebito = new BigDecimal(0.00);

				if (servicoTipo != null && servicoTipo.getDebitoTipo() != null) {

					efetuarLigacaoAguaComInstalacaoHidrometroActionForm
							.setIdTipoDebito(servicoTipo.getDebitoTipo()
									.getId().toString());

					efetuarLigacaoAguaComInstalacaoHidrometroActionForm
							.setDescricaoTipoDebito(servicoTipo.getDebitoTipo()
									.getDescricao());

					// [FS0013] - Altera��o de Valor
					this
							.permitirAlteracaoValor(ordemServico
									.getServicoTipo(),
									efetuarLigacaoAguaComInstalacaoHidrometroActionForm);

					// Colocado por Raphael Rossiter em 04/05/2007 (Analista:
					// Rosana Carvalho)
					valorDebito = this
							.calcularValores(httpServletRequest, ordemServico,
									efetuarLigacaoAguaComInstalacaoHidrometroActionForm);

					if (ordemServico.getServicoNaoCobrancaMotivo() != null) {
						efetuarLigacaoAguaComInstalacaoHidrometroActionForm
								.setMotivoNaoCobranca(ordemServico
										.getServicoNaoCobrancaMotivo().getId()
										.toString());
					}

					if (ordemServico.getPercentualCobranca() != null) {
						efetuarLigacaoAguaComInstalacaoHidrometroActionForm
								.setPercentualCobranca(ordemServico
										.getPercentualCobranca().toString());
					}
				}

				if (ordemServico.getDataEncerramento() != null) {
					efetuarLigacaoAguaComInstalacaoHidrometroActionForm
							.setDataLigacao(Util.formatarData(ordemServico
									.getDataEncerramento()));
					efetuarLigacaoAguaComInstalacaoHidrometroActionForm
							.setDataInstalacao(Util.formatarData(ordemServico
									.getDataEncerramento()));
				}

				sessao.setAttribute("imovel", imovel);

				matriculaImovel = imovel.getId().toString();
				efetuarLigacaoAguaComInstalacaoHidrometroActionForm
						.setMatriculaImovel("" + matriculaImovel);

				/*-------------- In�cio dados do Im�vel---------------*/

				sessao.setAttribute("imovel", ordemServico
						.getRegistroAtendimento().getImovel());

				if (imovel != null) {

					FiltroImovel filtroImovel = new FiltroImovel();
					filtroImovel
							.adicionarCaminhoParaCarregamentoEntidade("localidade.hidrometroLocalArmazenagem");
					filtroImovel.adicionarParametro(new ParametroSimples(
							FiltroImovel.ID, imovel.getId()));

					Collection colecaoImoveis = fachada.pesquisar(filtroImovel,
							Imovel.class.getName());

					imovelComLocalidade = (Imovel) Util
							.retonarObjetoDeColecao(colecaoImoveis);

					if (imovelComLocalidade.getLocalidade()
							.getHidrometroLocalArmazenagem() != null) {
						efetuarLigacaoAguaComInstalacaoHidrometroActionForm
								.setIdLocalArmazenagem(imovelComLocalidade
										.getLocalidade()
										.getHidrometroLocalArmazenagem()
										.getId().toString());
					}

					// Matricula Im�vel
					efetuarLigacaoAguaComInstalacaoHidrometroActionForm
							.setMatriculaImovel(imovel.getId().toString());

					// Inscri��o Im�vel
					String inscricaoImovel = fachada
							.pesquisarInscricaoImovel(imovel.getId());
					efetuarLigacaoAguaComInstalacaoHidrometroActionForm
							.setInscricaoImovel(inscricaoImovel);

					// Situa��o da Liga��o de Agua
					String situacaoLigacaoAgua = imovel
							.getLigacaoAguaSituacao().getDescricao();
					efetuarLigacaoAguaComInstalacaoHidrometroActionForm
							.setSituacaoLigacaoAgua(situacaoLigacaoAgua);

					// Situa��o da Liga��o de Esgoto
					String situacaoLigacaoEsgoto = imovel
							.getLigacaoEsgotoSituacao().getDescricao();
					efetuarLigacaoAguaComInstalacaoHidrometroActionForm
							.setSituacaoLigacaoEsgoto(situacaoLigacaoEsgoto);

					this
							.pesquisarCliente(
									efetuarLigacaoAguaComInstalacaoHidrometroActionForm,
									new Integer(matriculaImovel));
				}
				if (temPermissaoMotivoNaoCobranca) {
					httpServletRequest.setAttribute(
							"permissaoMotivoNaoCobranca",
							temPermissaoMotivoNaoCobranca);
				} else {
					efetuarLigacaoAguaComInstalacaoHidrometroActionForm
							.setPercentualCobranca("100");
					efetuarLigacaoAguaComInstalacaoHidrometroActionForm
							.setQuantidadeParcelas("1");
					efetuarLigacaoAguaComInstalacaoHidrometroActionForm
							.setValorParcelas(Util.formataBigDecimal(
									valorDebito, 2, true));
				}

			} else {
				efetuarLigacaoAguaComInstalacaoHidrometroActionForm
						.setNomeOrdemServico("Ordem de Servi�o inexistente");
				efetuarLigacaoAguaComInstalacaoHidrometroActionForm
						.setIdOrdemServico("");
				httpServletRequest.setAttribute("OrdemServioInexistente", true);
			}
		} /*
			 * else {
			 * 
			 * httpServletRequest.setAttribute("nomeCampo", "idOrdemServico");
			 * 
			 * efetuarLigacaoAguaComInstalacaoHidrometroActionForm
			 * .setIdOrdemServico("");
			 * efetuarLigacaoAguaComInstalacaoHidrometroActionForm
			 * .setMatriculaImovel("");
			 * efetuarLigacaoAguaComInstalacaoHidrometroActionForm
			 * .setInscricaoImovel("");
			 * efetuarLigacaoAguaComInstalacaoHidrometroActionForm
			 * .setClienteUsuario("");
			 * efetuarLigacaoAguaComInstalacaoHidrometroActionForm
			 * .setCpfCnpjCliente("");
			 * efetuarLigacaoAguaComInstalacaoHidrometroActionForm
			 * .setSituacaoLigacaoAgua("");
			 * efetuarLigacaoAguaComInstalacaoHidrometroActionForm
			 * .setSituacaoLigacaoEsgoto("");
			 * efetuarLigacaoAguaComInstalacaoHidrometroActionForm
			 * .setDataLigacao("");
			 * efetuarLigacaoAguaComInstalacaoHidrometroActionForm
			 * .setDiametroLigacao("");
			 * efetuarLigacaoAguaComInstalacaoHidrometroActionForm
			 * .setMaterialLigacao("");
			 * efetuarLigacaoAguaComInstalacaoHidrometroActionForm
			 * .setPerfilLigacao("");
			 * efetuarLigacaoAguaComInstalacaoHidrometroActionForm
			 * .setIdTipoDebito("");
			 * efetuarLigacaoAguaComInstalacaoHidrometroActionForm
			 * .setDescricaoTipoDebito("");
			 * efetuarLigacaoAguaComInstalacaoHidrometroActionForm
			 * .setQuantidadeParcelas("");
			 * efetuarLigacaoAguaComInstalacaoHidrometroActionForm
			 * .setValorParcelas("");
			 * efetuarLigacaoAguaComInstalacaoHidrometroActionForm
			 * .setIdLocalArmazenagem("");
			 * efetuarLigacaoAguaComInstalacaoHidrometroActionForm
			 * .setPercentualCobranca("-1");
			 * efetuarLigacaoAguaComInstalacaoHidrometroActionForm
			 * .setMotivoNaoCobranca("-1");
			 * efetuarLigacaoAguaComInstalacaoHidrometroActionForm
			 * .setAceitaLacre("2");
			 *  }
			 */

		String numeroHidrometro = efetuarLigacaoAguaComInstalacaoHidrometroActionForm
				.getNumeroHidrometro();

		// Verificar se o n�mero do hidr�metro n�o est� cadastrado

		if (numeroHidrometro != null && !numeroHidrometro.trim().equals("")) {

			// Pesquisa o hidr�metro
			Hidrometro hidrometro = fachada
					.pesquisarHidrometroPeloNumero(numeroHidrometro);

			if (hidrometro == null) {
				efetuarLigacaoAguaComInstalacaoHidrometroActionForm
						.setNumeroHidrometro("");
				throw new ActionServletException(
						"atencao.hidrometro_inexistente");
			} else {

				if ( imovelComLocalidade != null
					 && imovelComLocalidade.getLocalidade().getHidrometroLocalArmazenagem() != null
					 && hidrometro.getHidrometroLocalArmazenagem() != null
					 && !hidrometro.getHidrometroLocalArmazenagem()
								.getId()
								.equals(
										imovelComLocalidade
												.getLocalidade()
												.getHidrometroLocalArmazenagem()
												.getId())) {
					throw new ActionServletException(
							"atencao.hidrometro_local_armazenagem_imovel_diferente_hidrometro_local_armazenagem_hidrometro");
				}

				efetuarLigacaoAguaComInstalacaoHidrometroActionForm
						.setNumeroHidrometro(hidrometro.getNumero());

				if (ordemServico != null) {
					// Alterado por S�vio Data:26/12/2007
					// Quando era calculado o valor com o valor do debito
					// alterado, ent�o n�o pesquisa o valor
					// do debito novamente na base.
					if (httpServletRequest.getParameter("calculaValores") == null
							|| (httpServletRequest
									.getParameter("calculaValores") != null && !httpServletRequest
									.getParameter("calculaValores").equals("S"))) {

						// Valor D�bito
						BigDecimal valorDebito = fachada.obterValorDebito(
								ordemServico.getServicoTipo().getId(),
								new Integer(matriculaImovel), hidrometro
										.getHidrometroCapacidade());

						if (valorDebito != null) {
							efetuarLigacaoAguaComInstalacaoHidrometroActionForm
									.setValorDebito(Util.formataBigDecimal(
											valorDebito, 2, true));
						} else {
							efetuarLigacaoAguaComInstalacaoHidrometroActionForm
									.setValorDebito("0");
						}

						if (temPermissaoMotivoNaoCobranca) {
							httpServletRequest.setAttribute(
									"permissaoMotivoNaoCobranca",
									temPermissaoMotivoNaoCobranca);
						} else {
							efetuarLigacaoAguaComInstalacaoHidrometroActionForm
									.setPercentualCobranca("100");
							efetuarLigacaoAguaComInstalacaoHidrometroActionForm
									.setQuantidadeParcelas("1");
							efetuarLigacaoAguaComInstalacaoHidrometroActionForm
									.setValorParcelas(Util.formataBigDecimal(
											valorDebito, 2, true));
						}
					}
				}

			}
		}

		return retorno;
	}

	/*
	 * [FS0013 - Altera��o de Valor]
	 * 
	 * autor: Raphael Rossiter data: 19/04/2007
	 */
	private void permitirAlteracaoValor(ServicoTipo servicoTipo,
			EfetuarLigacaoAguaComInstalacaoHidrometroActionForm form) {

		if (servicoTipo.getIndicadorPermiteAlterarValor() == ConstantesSistema.INDICADOR_USO_ATIVO
				.shortValue()) {

			form.setAlteracaoValor("OK");
		} else {
			form.setAlteracaoValor("");
		}

	}

	/*
	 * Calcular valor da presta��o com juros
	 * 
	 * return: Retorna o valor total do d�bito
	 * 
	 * autor: Raphael Rossiter data: 04/05/2007
	 */
	private BigDecimal calcularValores(HttpServletRequest httpServletRequest,
			OrdemServico ordemServico,
			EfetuarLigacaoAguaComInstalacaoHidrometroActionForm form) {

		String calculaValores = httpServletRequest
				.getParameter("calculaValores");

		BigDecimal valorDebito = new BigDecimal(0);
		SistemaParametro sistemaParametro = this.getFachada()
				.pesquisarParametrosDoSistema();
		Integer qtdeParcelas = null;

		if (calculaValores != null && calculaValores.equals("S")) {

			// [UC0186] - Calcular Presta��o
			BigDecimal taxaJurosFinanciamento = null;
			qtdeParcelas = new Integer(form.getQuantidadeParcelas());

			if (ordemServico.getServicoTipo().getIndicadorCobrarJuros() == ConstantesSistema.SIM
					.shortValue()
					&& qtdeParcelas.intValue() > 1) {

				taxaJurosFinanciamento = sistemaParametro
						.getPercentualTaxaJurosFinanciamento();
			} else {
				taxaJurosFinanciamento = new BigDecimal(0);
			}

			BigDecimal valorPrestacao = null;
			if (taxaJurosFinanciamento != null) {

				valorDebito = new BigDecimal(form.getValorDebito().replace(",",
						"."));

				String percentualCobranca = form.getPercentualCobranca();

				if (percentualCobranca.equals("70")) {
					valorDebito = valorDebito.multiply(new BigDecimal(0.7));
				} else if (percentualCobranca.equals("50")) {
					valorDebito = valorDebito.multiply(new BigDecimal(0.5));
				}

				valorPrestacao = this.getFachada().calcularPrestacao(
						taxaJurosFinanciamento, qtdeParcelas, valorDebito,
						new BigDecimal("0.00"));

				valorPrestacao.setScale(2, BigDecimal.ROUND_HALF_UP);
			}

			if (valorPrestacao != null) {
				String valorPrestacaoComVirgula = Util.formataBigDecimal(
						valorPrestacao, 2, true);
				form.setValorParcelas(valorPrestacaoComVirgula);
			} else {
				form.setValorParcelas("0,00");
			}

		} else {

			if ( form.getNumeroHidrometro() != null && !form.getNumeroHidrometro().equals("") ) {
	
				HidrometroCapacidade hidrometroCapacidade = 
					Fachada.getInstancia().pesquisarCapacidadeHidrometro(form.getNumeroHidrometro());
			
				valorDebito = Fachada.getInstancia().obterValorDebito(
						ordemServico.getServicoTipo().getId(),
						ordemServico.getRegistroAtendimento().getImovel().getId(),
						hidrometroCapacidade);
			} else {
				valorDebito = Fachada.getInstancia().obterValorDebito(
						ordemServico.getServicoTipo().getId(),
						ordemServico.getRegistroAtendimento().getImovel().getId(),
						new Short(LigacaoTipo.LIGACAO_AGUA + ""));
			}

			form.setValorDebito(Util.formataBigDecimal(valorDebito, 2, true));
		}

		return valorDebito;
	}

	/**
	 * Pesquisa Cliente
	 * 
	 * @author Rafael Corr�a
	 * @date 27/11/2006
	 */
	private void pesquisarCliente(
			EfetuarLigacaoAguaComInstalacaoHidrometroActionForm efetuarLigacaoAguaComInstalacaoHidrometroActionForm,
			Integer matriculaImovel) {

		// Filtro para carregar o Cliente
		FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();

		filtroClienteImovel.adicionarParametro(new ParametroSimples(
				FiltroClienteImovel.IMOVEL_ID, matriculaImovel));

		filtroClienteImovel.adicionarParametro(new ParametroSimples(
				FiltroClienteImovel.CLIENTE_RELACAO_TIPO,
				ClienteRelacaoTipo.USUARIO));

		filtroClienteImovel.adicionarParametro(new ParametroNulo(
				FiltroClienteImovel.DATA_FIM_RELACAO));

		filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("cliente");

		Collection colecaoClienteImovel = Fachada.getInstancia().pesquisar(
				filtroClienteImovel, ClienteImovel.class.getName());

		if (colecaoClienteImovel != null && !colecaoClienteImovel.isEmpty()) {

			ClienteImovel clienteImovel = (ClienteImovel) colecaoClienteImovel
					.iterator().next();

			Cliente cliente = clienteImovel.getCliente();

			String documento = "";

			if (cliente.getCpf() != null && !cliente.getCpf().equals("")) {
				documento = cliente.getCpfFormatado();
			} else {
				documento = cliente.getCnpjFormatado();
			}
			// Cliente Nome/CPF-CNPJ
			efetuarLigacaoAguaComInstalacaoHidrometroActionForm
					.setClienteUsuario(cliente.getNome());
			efetuarLigacaoAguaComInstalacaoHidrometroActionForm
					.setCpfCnpjCliente(documento);

		} else {
			throw new ActionServletException("atencao.naocadastrado", null,
					"Cliente");
		}
	}

	/**
	 * Monta os select�s obrigatorios
	 * 
	 * @author Rafael Corr�a
	 * @date 27/11/2006
	 */
	private void consultaSelectObrigatorio(HttpSession sessao) {

		Fachada fachada = Fachada.getInstancia();

		// Filtro para o campo Diametro Liga��o �gua
		Collection colecaoDiametroLigacao = (Collection) sessao
				.getAttribute("colecaoDiametroLigacaoAgua");

		if (colecaoDiametroLigacao == null) {

			FiltroDiametroLigacao filtroDiametroLigacao = new FiltroDiametroLigacao();

			filtroDiametroLigacao.adicionarParametro(new ParametroSimples(
					FiltroDiametroLigacao.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			filtroDiametroLigacao
					.setCampoOrderBy(FiltroDiametroLigacao.DESCRICAO);

			colecaoDiametroLigacao = fachada.pesquisar(filtroDiametroLigacao,
					LigacaoAguaDiametro.class.getName());

			if (colecaoDiametroLigacao != null
					&& !colecaoDiametroLigacao.isEmpty()) {
				sessao.setAttribute("colecaoDiametroLigacao",
						colecaoDiametroLigacao);
			} else {
				throw new ActionServletException("atencao.naocadastrado", null,
						"Diametro da Liga��o");
			}
		}

		// Filtro para o campo Material da Liga��o
		Collection colecaoMaterialLigacao = (Collection) sessao
				.getAttribute("colecaoMaterialLigacao");

		if (colecaoMaterialLigacao == null) {

			FiltroMaterialLigacao filtroMaterialLigacao = new FiltroMaterialLigacao();
			filtroMaterialLigacao.adicionarParametro(new ParametroSimples(
					FiltroMaterialLigacao.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			filtroMaterialLigacao
					.setCampoOrderBy(FiltroMaterialLigacao.DESCRICAO);

			colecaoMaterialLigacao = fachada.pesquisar(filtroMaterialLigacao,
					LigacaoAguaMaterial.class.getName());

			if (colecaoMaterialLigacao != null
					&& !colecaoMaterialLigacao.isEmpty()) {
				sessao.setAttribute("colecaoMaterialLigacao",
						colecaoMaterialLigacao);
			} else {
				throw new ActionServletException("atencao.naocadastrado", null,
						"Material da Liga��o");
			}
		}

		// Filtro para o campo Perfil da Liga��o
		Collection colecaoPerfilLigacao = (Collection) sessao
				.getAttribute("colecaoPerfilLigacao");

		if (colecaoPerfilLigacao == null) {

			FiltroPerfilLigacao filtroPerfilLigacao = new FiltroPerfilLigacao();
			filtroPerfilLigacao.adicionarParametro(new ParametroSimples(
					FiltroPerfilLigacao.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			filtroPerfilLigacao.setCampoOrderBy(FiltroPerfilLigacao.DESCRICAO);

			colecaoPerfilLigacao = fachada.pesquisar(filtroPerfilLigacao,
					LigacaoAguaPerfil.class.getName());

			if (colecaoPerfilLigacao != null && !colecaoPerfilLigacao.isEmpty()) {
				sessao.setAttribute("colecaoPerfilLigacao",
						colecaoPerfilLigacao);
			} else {
				throw new ActionServletException("atencao.naocadastrado", null,
						"Material da Liga��o");
			}
		}

		// Filtro para o campo Tpo Debito
		Collection colecaoNaoCobranca = (Collection) sessao
				.getAttribute("colecaoNaoCobranca");
		if (colecaoNaoCobranca == null) {
			FiltroServicoNaoCobrancaMotivo filtroServicoNaoCobrancaMotivo = new FiltroServicoNaoCobrancaMotivo();

			filtroServicoNaoCobrancaMotivo
					.setCampoOrderBy(FiltroDebitoTipo.DESCRICAO);

			colecaoNaoCobranca = fachada.pesquisar(
					filtroServicoNaoCobrancaMotivo,
					ServicoNaoCobrancaMotivo.class.getName());

			if (colecaoNaoCobranca != null && !colecaoNaoCobranca.isEmpty()) {
				sessao.setAttribute("colecaoNaoCobranca", colecaoNaoCobranca);
			} else {
				throw new ActionServletException("atencao.naocadastrado", null,
						"Motivo da N�o Cobran�a");
			}
		}

		// Filtro para o campo Ramal local instalacao
		Collection colecaoRamalLocalInstalacao = (Collection) sessao
				.getAttribute("colecaoRamalLocalInstalacao");

		if (colecaoRamalLocalInstalacao == null) {

			FiltroRamalLocalInstalacao filtroRamalLocalInstalacao = new FiltroRamalLocalInstalacao();
			filtroRamalLocalInstalacao.adicionarParametro(new ParametroSimples(
					FiltroRamalLocalInstalacao.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			filtroRamalLocalInstalacao
					.setCampoOrderBy(FiltroPerfilLigacao.DESCRICAO);

			colecaoRamalLocalInstalacao = fachada.pesquisar(
					filtroRamalLocalInstalacao, RamalLocalInstalacao.class
							.getName());

			if (colecaoRamalLocalInstalacao != null
					&& !colecaoRamalLocalInstalacao.isEmpty()) {
				sessao.setAttribute("colecaoRamalLocalInstalacao",
						colecaoRamalLocalInstalacao);
			} else {
				throw new ActionServletException("atencao.naocadastrado", null,
						"Local de Instala��o do Ramal");
			}
		}

		// Pesquisando local de instala��o
		FiltroHidrometroLocalInstalacao filtroHidrometroLocalInstalacao = new FiltroHidrometroLocalInstalacao();
		filtroHidrometroLocalInstalacao
				.setCampoOrderBy(FiltroHidrometroLocalInstalacao.DESCRICAO);
		filtroHidrometroLocalInstalacao
				.adicionarParametro(new ParametroSimples(
						FiltroHidrometroLocalInstalacao.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection colecaoHidrometroLocalInstalacao = Fachada.getInstancia()
				.pesquisar(filtroHidrometroLocalInstalacao,
						HidrometroLocalInstalacao.class.getName());

		sessao.setAttribute("colecaoHidrometroLocalInstalacao",
				colecaoHidrometroLocalInstalacao);

		// Pesquisando prote��o
		FiltroHidrometroProtecao filtroHidrometroProtecao = new FiltroHidrometroProtecao();
		filtroHidrometroProtecao
				.setCampoOrderBy(FiltroHidrometroProtecao.DESCRICAO);
		filtroHidrometroProtecao.adicionarParametro(new ParametroSimples(
				FiltroHidrometroProtecao.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
		Collection colecaoHidrometroProtecao = Fachada.getInstancia()
				.pesquisar(filtroHidrometroProtecao,
						HidrometroProtecao.class.getName());

		sessao.setAttribute("colecaoHidrometroProtecao",
				colecaoHidrometroProtecao);

		// Filtro para o campo Ligacao origem
		Collection colecaoLigacaoOrigem = (Collection) sessao
				.getAttribute("colecaoLigacaoOrigem");

		if (colecaoLigacaoOrigem == null) {

			FiltroLigacaoOrigem filtroLigacaoOrigem = new FiltroLigacaoOrigem();

			filtroLigacaoOrigem.adicionarParametro(new ParametroSimples(
					FiltroLigacaoOrigem.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			filtroLigacaoOrigem.setCampoOrderBy(FiltroLigacaoOrigem.DESCRICAO);

			colecaoLigacaoOrigem = fachada.pesquisar(filtroLigacaoOrigem,
					LigacaoOrigem.class.getName());

			if (colecaoLigacaoOrigem != null && !colecaoLigacaoOrigem.isEmpty()) {
				sessao.setAttribute("colecaoLigacaoOrigem",
						colecaoLigacaoOrigem);
			} else {
				sessao.setAttribute("colecaoLigacaoOrigem", new ArrayList());
			}
		}

	}

}
