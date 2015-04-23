package gsan.gui.portal.caema;

import gsan.atendimentopublico.ordemservico.ServicoTipo;
import gsan.atendimentopublico.registroatendimento.FiltroRegistroAtendimento;
import gsan.atendimentopublico.registroatendimento.FiltroRegistroAtendimentoSolicitante;
import gsan.atendimentopublico.registroatendimento.FiltroSolicitacaoTipoEspecificacao;
import gsan.atendimentopublico.registroatendimento.MeioSolicitacao;
import gsan.atendimentopublico.registroatendimento.RegistroAtendimento;
import gsan.atendimentopublico.registroatendimento.RegistroAtendimentoSolicitante;
import gsan.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;
import gsan.atendimentopublico.registroatendimento.bean.DefinirDataPrevistaUnidadeDestinoEspecificacaoHelper;
import gsan.cadastro.cliente.ClienteFone;
import gsan.cadastro.cliente.ClienteImovel;
import gsan.cadastro.cliente.FiltroClienteImovel;
import gsan.cadastro.cliente.FoneTipo;
import gsan.cadastro.imovel.FiltroImovel;
import gsan.cadastro.imovel.Imovel;
import gsan.cadastro.unidade.FiltroUnidadeOrganizacional;
import gsan.cadastro.unidade.UnidadeOrganizacional;
import gsan.fachada.Fachada;
import gsan.gui.GcomAction;
import gsan.seguranca.acesso.usuario.FiltroUsuario;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.util.ConstantesSistema;
import gsan.util.Util;
import gsan.util.filtro.ParametroNulo;
import gsan.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * <p>
 * <b>[RM2923]</b> Inserir Solicita��o de Servicos - Loja Virtual da Compesa
 * </p>
 * <p>
 * <b>[UC1189]</b> Inserir Registro de Atendimento Loja Virtual</b>
 * </p>
 * <p>
 * Gerar Registro de Atendimento
 * </p>
 * 
 * @author Magno Gouveia
 * @date 19/05/2011
 * 
 */
public class InserirSolicitacaoServicosPortalCaemaAction extends GcomAction {
public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("validarDados");

		Fachada fachada = Fachada.getInstancia();

		InserirSolicitacaoServicosPortalCaemaActionForm form = (InserirSolicitacaoServicosPortalCaemaActionForm) actionForm;
		
		Imovel imovel = null;
		if(Util.verificarIdNaoVazio(form.getMatricula())){
			FiltroImovel filtroImovel = new FiltroImovel();
			filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, form.getMatricula()));
			
			Collection colecaoImoveis = fachada.pesquisar(filtroImovel, Imovel.class.getName());
			if(!Util.isVazioOrNulo(colecaoImoveis)){
				imovel = (Imovel) Util.retonarObjetoDeColecao(colecaoImoveis);
			}
		}
		
		// 2.2.1
		if (!Util.verificarNaoVazio(form.getNomeSolicitante())) {
			httpServletRequest.setAttribute("exception", "O nome do solicitante � obrigat�rio!");
			return retorno;
		}
		
		// 2.2.2
		if(!Util.verificarNaoVazio(form.getTelefoneContato())){
			httpServletRequest.setAttribute("exception", "O telefone de contato � obrigat�rio!");
			return retorno;
		}
		
		// 2.2.4
		if(!Util.verificarIdNaoVazio(form.getSolicitacaoTipo())){
			httpServletRequest.setAttribute("exception", "O tipo de solicita��o � obrigat�rio!");
			return retorno;
		}
		
		if(!Util.verificarNaoVazio(form.getPontoReferencia())){
			httpServletRequest.setAttribute("exception", "O ponto de refer�ncia � obrigat�rio!");
			return retorno;
		}
		
		// 2.2.5
		Date dataPrevista = null;
		if (Util.verificarIdNaoVazio(form.getEspecificacao())) {
			DefinirDataPrevistaUnidadeDestinoEspecificacaoHelper definirDataPrevistaUnidadeDestinoEspecificacaoHelper = fachada
					.definirDataPrevistaUnidadeDestinoEspecificacao(new Date(),
							Integer.parseInt(form.getEspecificacao()));

			dataPrevista = Util
					.formatarDataFinal(definirDataPrevistaUnidadeDestinoEspecificacaoHelper
							.getDataPrevista());
		} else {
			httpServletRequest.setAttribute("exception", "A especifica��o � obrigat�ria!");
			return retorno;
		}
		
		// 2.2.6
		
		if (!Util.verificarNaoVazio(form.getEmail())) {
			httpServletRequest.setAttribute("exception", "O e-mail do solicitante � obrigat�rio!");
			return retorno;
		}
		
		FiltroRegistroAtendimento filtroRegistroAtendimento = new FiltroRegistroAtendimento();
		filtroRegistroAtendimento.adicionarParametro(new ParametroSimples(FiltroRegistroAtendimento.IMOVEL_ID, form.getMatricula()));
		filtroRegistroAtendimento.adicionarParametro(new ParametroSimples(FiltroRegistroAtendimento.ID_SOLICITACAO_TIPO_ESPECIFICACAO, form.getEspecificacao()));
		filtroRegistroAtendimento.adicionarParametro(new ParametroSimples(FiltroRegistroAtendimento.CODIGO_SITUACAO, RegistroAtendimento.SITUACAO_PENDENTE));
		Collection colecaoRegistroAtendimento = fachada.pesquisar(filtroRegistroAtendimento, RegistroAtendimento.class.getName());

		if (Util.isVazioOrNulo(colecaoRegistroAtendimento)) {

			FiltroUsuario filtroUsuario = new FiltroUsuario();
			filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.INDICADOR_USUARIO_INTERNET, 
				ConstantesSistema.INDICADOR_USO_ATIVO));
			
			Usuario usuarioLogado = 
				(Usuario) Util.retonarObjetoDeColecao(fachada.pesquisar(filtroUsuario, Usuario.class.getName()));
			
			Integer idUsuarioLogado = (usuarioLogado != null)? usuarioLogado.getId() : null;
			
			FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
			filtroUnidadeOrganizacional.adicionarParametro(
				new ParametroSimples(FiltroUnidadeOrganizacional.CODIGO_CONSTANTE, 
					1));
			
			Collection colecaoUnidadeOrganizacional = 
				fachada.pesquisar(filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());
			
			Integer idUnidadeOrganizacional = null;
			if(!Util.isVazioOrNulo(colecaoUnidadeOrganizacional)){
				UnidadeOrganizacional unidadeOrganizacional = (UnidadeOrganizacional) Util.retonarObjetoDeColecao(colecaoUnidadeOrganizacional);
				idUnidadeOrganizacional = unidadeOrganizacional.getId();
			}
			
			Collection<ClienteFone> colecaoTelefone = null;
			if(form.getTelefoneContato() != null && !form.getTelefoneContato().trim().equals("")){
				colecaoTelefone = new ArrayList<ClienteFone>();
				
				FoneTipo foneTipo = new FoneTipo();
				foneTipo.setId(FoneTipo.RESIDENCIAL);
				
				String telefone = form.getTelefoneContato().trim();
				telefone = telefone.replace("(", "");
				telefone = telefone.replace(")", "");
				telefone = telefone.replace("-", "");
				telefone = telefone.replace(" ", "");
			
				String ddd = telefone.substring(0, 2);
				String numeroTelefone = telefone.substring(2);
				
				ClienteFone clienteFone = new ClienteFone();
				clienteFone.setFoneTipo(foneTipo);
				clienteFone.setDdd(ddd);
				clienteFone.setTelefone(numeroTelefone);
				
				colecaoTelefone.add(clienteFone);
			}
			
			Collection colecaoEndereco = new ArrayList();
			Imovel imovelEndereco = fachada.pesquisarImovelParaEndereco(imovel.getId());
			colecaoEndereco.add(imovelEndereco);
			
			Date date = new Date();
			
			String observacao = form.getObservacoes();
			if(observacao.length() > 400){
				observacao = observacao.substring(0, 400);
			}				
			
			String pontoReferencia = form.getPontoReferencia();
			if(pontoReferencia.length() > 200){
				pontoReferencia = pontoReferencia.substring(0, 200);
			}
			
			try {
				FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();
				filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.IMOVEL_ID, imovel.getId()));
				filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.CLIENTE_RELACAO_TIPO_ID, 2));
				filtroClienteImovel.adicionarParametro(new ParametroNulo(FiltroClienteImovel.DATA_FIM_RELACAO));
				
				ClienteImovel clienteImovel = (ClienteImovel) Util.retonarObjetoDeColecao(fachada.pesquisar(filtroClienteImovel, ClienteImovel.class.getName()));
				
				String protocolo = this.getFachada().obterProtocoloAtendimento();
				
				
				FiltroSolicitacaoTipoEspecificacao filtroSolicitacaoTipoEspecificacao = new FiltroSolicitacaoTipoEspecificacao(FiltroSolicitacaoTipoEspecificacao.DESCRICAO);
				filtroSolicitacaoTipoEspecificacao.adicionarParametro(new ParametroSimples(FiltroSolicitacaoTipoEspecificacao.ID,  new Integer(form.getEspecificacao())));
				filtroSolicitacaoTipoEspecificacao.adicionarCaminhoParaCarregamentoEntidade(FiltroSolicitacaoTipoEspecificacao.SERVICO_TIPO);
				Collection<SolicitacaoTipoEspecificacao> colecaoEspecificacao = fachada.pesquisar(filtroSolicitacaoTipoEspecificacao, SolicitacaoTipoEspecificacao.class.getName());
				
				SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao = (SolicitacaoTipoEspecificacao) Util.retonarObjetoDeColecao(colecaoEspecificacao);
				ServicoTipo servicoTipo = solicitacaoTipoEspecificacao.getServicoTipo();
				
				Integer idServicoTipo = null;
				if ( servicoTipo != null && servicoTipo.getId() != null ) {
					idServicoTipo = servicoTipo.getId();
				} 
				@SuppressWarnings("unused") 
				Integer[] idRASolicitada =
				fachada.inserirRegistroAtendimento(ConstantesSistema.SIM, //indicadorAtendimentoOnLine
												   Util.formatarData(date), //dataAtendimento
												   Util.formatarHoraSemData(date), //horaAtendimento
												   null, //tempoEsperaInicial
												   null, //tempoEsperaFinal
												   MeioSolicitacao.INTERNET, //idMeioSolicitacao
												   new Integer(form.getEspecificacao()), //idSolicitacaoTipoEspecificacao
												   Util.formatarData(dataPrevista), //dataPrevista 
												   observacao, //observacao
												   imovel.getId(), //idImovel
												   null, //descricaoLocalOcorrencia
												   new Integer(form.getSolicitacaoTipo()), //idSolicitacaoTipo
												   colecaoEndereco, //colecaoEndereco
												   null, //pontoReferenciaLocalOcorrencia
												   null, //idBairroArea
												   imovel.getLocalidade().getId(), //idLocalidade 
												   imovel.getSetorComercial().getId(), //idSetorComercial
												   imovel.getQuadra().getId(), //idQuadra 
												   null, //idDivisaoEsgoto
												   null, //idLocalOcorrencia
												   null, //idPavimentoRua 
												   null, //idPavimentoCalcada
												   idUnidadeOrganizacional, //idUnidadeAtendimento 
												   idUsuarioLogado, //idUsuarioLogado 
												   clienteImovel.getCliente().getId(), //idCliente
												   pontoReferencia, //pontoReferenciaSolicitante 
												   form.getNomeSolicitante(), //nomeSolicitante
												   false, //novoSolicitante
												   null, //idUnidadeSolicitante
												   null, //idFuncionario
												   colecaoTelefone, //colecaoFone
												   null, //colecaoEnderecoSolicitante 
												   null, //idUnidadeDestino
												   null, //parecerUnidadeDestino 
												   idServicoTipo, //idServicoTipo
												   null, //numeroRAManual 
												   null, //idRAJAGerado 
												   null, //nnCoordenadaNorte
												   null, //nnCoordenadaLeste 
												   ConstantesSistema.NAO, //indicCoordenadaSemLogradouro
												   null, //colecaoRegistroAtendimentoAnexo
												   protocolo, //protocoloAtendimento
												   null, //colecaoContas
												   observacao, //observacaoOS
												   null, //colecaoPagamentos
												   null, //habilitarCampoSatisfacaoEmail 
												   null, //enviarEmailSatisfacao 
												   form.getEmail(), //enderecoEmail
												   null);
				
				httpServletRequest.setAttribute("RASolicitadaComSucesso", true);
				httpServletRequest.setAttribute("mensagemRA", protocolo);
				httpServletRequest.setAttribute("dataPrevistaAtendimentoRA", Util.formatarData(dataPrevista));
				retorno = actionMapping.findForward("RASolicitadaComSucesso");
				
			} catch (Exception e) {
				httpServletRequest.setAttribute("exception", "N�o foi poss�vel inserir a solicita��o: \n" + e.getMessage());
				e.printStackTrace();
			}
		} else {
			RegistroAtendimento registroAtendimento = (RegistroAtendimento) Util.retonarObjetoDeColecao(colecaoRegistroAtendimento);
			
			FiltroRegistroAtendimentoSolicitante filtroRegistroAtendimentoSolicitante = new FiltroRegistroAtendimentoSolicitante();
			filtroRegistroAtendimentoSolicitante.adicionarParametro(new ParametroSimples(FiltroRegistroAtendimentoSolicitante.REGISTRO_ATENDIMENTO_ID, registroAtendimento.getId()));
			
			RegistroAtendimentoSolicitante registroAtendimentoSolicitante = (RegistroAtendimentoSolicitante) Util.retonarObjetoDeColecao(fachada.pesquisar(filtroRegistroAtendimentoSolicitante, RegistroAtendimentoSolicitante.class.getName()));
			
			httpServletRequest.setAttribute("voltarServicos", true);
			httpServletRequest.setAttribute("RAJaSolicitada", true);
			httpServletRequest.setAttribute("mensagemRA", registroAtendimentoSolicitante.getNumeroProtocoloAtendimento());
			httpServletRequest.setAttribute("dataPrevistaAtendimentoRA", Util.formatarData(registroAtendimento.getDataPrevistaAtual()));
		}
		
		return retorno;
	}
}