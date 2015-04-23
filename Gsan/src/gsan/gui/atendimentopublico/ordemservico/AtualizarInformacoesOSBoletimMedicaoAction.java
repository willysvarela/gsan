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
package gsan.gui.atendimentopublico.ordemservico;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import gsan.atendimentopublico.ordemservico.OrdemServico;
import gsan.atendimentopublico.ordemservico.OrdemServicoBoletim;
import gsan.atendimentopublico.ordemservico.ServicoTipo;
import gsan.atendimentopublico.ordemservico.ServicoTipoBoletim;
import gsan.cadastro.cliente.Cliente;
import gsan.fachada.Fachada;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.util.ConstantesSistema;
import gsan.util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1116] Atualizar Informa��es da OS para Boletim de Medi��o
 * 
 * @author Vivianne Sousa
 * @since 01/02/2011
 */
public class AtualizarInformacoesOSBoletimMedicaoAction extends GcomAction {


	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Obt�m a inst�ncia da fachada
		Fachada fachada = Fachada.getInstancia();

		AtualizarInformacoesOSBoletimMedicaoActionForm form = (AtualizarInformacoesOSBoletimMedicaoActionForm) actionForm;

		String idOrdemServico = form.getIdOrdemServico();

		if (idOrdemServico != null && !idOrdemServico.trim().equals("")
			&& (form.getNomeOrdemServico() == null || form.getNomeOrdemServico().equals("")) ) {
			OrdemServico ordemServico = pesquisarOrdemServico(idOrdemServico,form,httpServletRequest);

			// Verifica se a pesquisa retornou algum objeto para a cole��o
			if (ordemServico == null) {
				throw new ActionServletException("atencao.naocadastrado", null,"Ordem de Servi�o");
			}
		}

	    String valorConfirmacaoBoletimValorZero = ( String ) httpServletRequest.getParameter( "confirmado" ); 
		Map validacao = validarInformacoesBoletimMedicao(new Integer(form.getIdOrdemServico()),form);
		
		OrdemServicoBoletim ordemServicoBoletim = (OrdemServicoBoletim)validacao.get("ordemServicoBoletim");
		Boolean exibirMsgConfirmacao = (Boolean)validacao.get("exibirMsgConfirmacao");				
		
		if(exibirMsgConfirmacao && (valorConfirmacaoBoletimValorZero == null || 
				!valorConfirmacaoBoletimValorZero.equalsIgnoreCase("ok"))){
			
			httpServletRequest.setAttribute("caminhoActionConclusao",
					"/gsan/atualizarInformacoesOSBoletimMedicaoAction.do");
			httpServletRequest.setAttribute("nomeBotao1", "Sim");
			httpServletRequest.setAttribute("tipoRelatorio", "confirmaBoletimValorZero");
			
			return montarPaginaConfirmacao("atencao.encerrar_OS_boletim",httpServletRequest, actionMapping);
		}else{
			
			fachada.inserirOuAtualizar(ordemServicoBoletim);
			
			montarPaginaSucesso(httpServletRequest, 
				"Informa��es da OS para Boletim de Medi��o atualizada com sucesso.",
				"Realizar outra atualiza��o de Informa��es da OS para Boletim de Medi��o",
		        "exibirAtualizarInformacoesOSBoletimMedicaoActionAction.do?menu=sim");
			
		}
	
		return retorno;

	}
	
	
	/**
     * [UC0457] Encerrar Ordem de Servi�o
     * [SB0007]- Gerar Informa��es para Boletim de Medi��o.
     * 
     * @author Vivianne Sousa
     * @created 28/01/2011
     */
    private Map validarInformacoesBoletimMedicao(
    		Integer idOrdemServico,AtualizarInformacoesOSBoletimMedicaoActionForm form) {
		
    	OrdemServicoBoletim ordemServicoBoletim = null;
    	Boolean exibirMsgConfirmacao = false;
    	
    	if(form.getExibeIndicadorExistePavimento().equals("1")
		   || form.getExibeQtdeReposicaoAsfalto().equals("1")
		   || form.getExibeQtdeReposicaoCalcada().equals("1")	
		   || form.getExibeQtdeReposicaoParalelo().equals("1")){
    		
    		ordemServicoBoletim = new OrdemServicoBoletim();
    		ordemServicoBoletim.setId(idOrdemServico);
    		OrdemServico os = new OrdemServico();
    		os.setId(idOrdemServico);
    		ordemServicoBoletim.setOrdemServico(os);
    		
    		if(form.getExibeIndicadorExistePavimento().equals("1")){
    			
    			if(form.getIndicadorExistePavimento() == null){
    				
    				throw new ActionServletException("atencao.campo_selecionado.obrigatorio",
    	 					null,"Existe Pavimento");
    			}else{
    				ordemServicoBoletim.setIndicadorPavimento(
    					new Short(form.getIndicadorExistePavimento()));
    			}
    			
    		}
    		
    		if(form.getExibeQtdeReposicaoAsfalto().equals("1")){
    			
    			if(form.getQtdeReposicaoAsfalto() == null || form.getQtdeReposicaoAsfalto().equals("")){
    				
    				form.setQtdeReposicaoAsfalto("0");
    				ordemServicoBoletim.setNumeroReposicaoAsfalto(new BigDecimal(0));
    				exibirMsgConfirmacao = true;
    				
    			}else if(form.getQtdeReposicaoAsfalto().equals("0") ||
    					form.getQtdeReposicaoAsfalto().equals("00") ||
    					form.getQtdeReposicaoAsfalto().equals("0,00")){
    				ordemServicoBoletim.setNumeroReposicaoAsfalto(new BigDecimal(0));
    				exibirMsgConfirmacao = true;
    				
    			}else{
    				
    				ordemServicoBoletim.setNumeroReposicaoAsfalto(Util.
    					formatarMoedaRealparaBigDecimal(form.getQtdeReposicaoAsfalto()));
    			}
    			
    			
    		}else{
    			ordemServicoBoletim.setNumeroReposicaoAsfalto(new BigDecimal(0));
    		}
    		
    		if(form.getExibeQtdeReposicaoParalelo().equals("1")){
    			
    			if(form.getQtdeReposicaoParalelo() == null || form.getQtdeReposicaoParalelo().equals("") ){
    				
    				form.setQtdeReposicaoParalelo("0");
    				ordemServicoBoletim.setNumeroReposicaoParalelo(new BigDecimal(0));
    				exibirMsgConfirmacao = true;
    				
    			}else if(form.getQtdeReposicaoParalelo().equals("0") ||
    					form.getQtdeReposicaoParalelo().equals("00")||
    					form.getQtdeReposicaoParalelo().equals("0,00")){
    				ordemServicoBoletim.setNumeroReposicaoParalelo(new BigDecimal(0));
    				exibirMsgConfirmacao = true;
    				
    			}else{
    				
    				ordemServicoBoletim.setNumeroReposicaoParalelo(Util.
    					formatarMoedaRealparaBigDecimal(form.getQtdeReposicaoParalelo()));
    			}
    			
    			
    		}else{
    			ordemServicoBoletim.setNumeroReposicaoParalelo(new BigDecimal(0));
    		}
    		
    		if(form.getExibeQtdeReposicaoCalcada().equals("1")){
    			
    			if(form.getQtdeReposicaoCalcada() == null || form.getQtdeReposicaoCalcada().equals("")){
    				
    				form.setQtdeReposicaoCalcada("0");
    				ordemServicoBoletim.setNumeroReposicaoCalcada(new BigDecimal(0));
    				exibirMsgConfirmacao = true;
    				
    			}else if(form.getQtdeReposicaoCalcada().equals("0")||
    					form.getQtdeReposicaoCalcada().equals("00")||
    					form.getQtdeReposicaoCalcada().equals("0,00")){
    				ordemServicoBoletim.setNumeroReposicaoCalcada(new BigDecimal(0));
    				exibirMsgConfirmacao = true;
    				
    			}else{
    				
    				ordemServicoBoletim.setNumeroReposicaoCalcada(Util.
    					formatarMoedaRealparaBigDecimal(form.getQtdeReposicaoCalcada()));
    			}
    			
    			
    		}else{
    			ordemServicoBoletim.setNumeroReposicaoCalcada(new BigDecimal(0));
    		}
    	
    		ordemServicoBoletim.setUltimaAlteracao(new Date());
    	}
    	
    	Map retorno = new HashMap();
    	retorno.put("ordemServicoBoletim",ordemServicoBoletim);
    	retorno.put("exibirMsgConfirmacao",exibirMsgConfirmacao);
    	
		return retorno;
	}

    /**
	 * Pesquisa Ordem Servi�o 
	 */
	private OrdemServico pesquisarOrdemServico(String idOrdemServico,
			AtualizarInformacoesOSBoletimMedicaoActionForm form,
			HttpServletRequest httpServletRequest) {
		
		OrdemServico ordemServico = Fachada.getInstancia().
			recuperaOSEDadosImovel(new Integer(idOrdemServico));

		//[FS0001] - Validar Ordem de Servi�o

		//Caso o servi�o associado � Ordem de Servi�o n�o corresponda a um servi�o com informa��es de boletim 
		//(SVTP_ICBOLETIM da tabela SERVICO_TIPO com SVTP_ID=SVTP_ID da tabela ORDEM_SERVICO 
		//com valor correspondente a (N�o)�2), exibir a mensagem
		//�O servi�o associado a esta ordem de servi�o solicitam informa��es para boletim�.
		if(ordemServico.getServicoTipo().getIndicadorBoletim().equals(ConstantesSistema.NAO)){
			throw new ActionServletException("atencao.os.solicitam.informacoes.boletim");
		}
		
		//Caso a Ordem de Servi�o j� tenha sido contabilizada em boletim
		//(ORSE_ID da tabela ORDEM_SERVICO existente na tabela BOLETIM_MEDICAO_OS 
		//exibir a mensagem �Esta ordem de servi�o j� foi contabilizada em boletim de medi��o�
		
		
		
		//Caso a Ordem de Servi�o n�o esteja na situa��o de encerrada 
		//(ORSE_CDSITUA��O na tabela ORDEM_SERVICO com o valor igual a �Pendente� (1)),
		//exibir a mensagem �Esta Ordem de Servi�o n�o est� encerrada�
		if(ordemServico.getSituacao() == 1){
			throw new ActionServletException("atencao.ordem_servico_situacao",
				null, OrdemServico.SITUACAO_DESCRICAO_ENCERRADO);
		}
		
		if (ordemServico != null) {

			form.setIdOrdemServico(ordemServico.getId().toString());
			form.setNomeOrdemServico(ordemServico.getServicoTipo().getDescricao());
			
			if(ordemServico.getImovel() != null){

				form.setIdImovel(ordemServico.getImovel().getId().toString());
				form.setInscricaoImovel(ordemServico.getImovel().getInscricaoFormatada());
				form.setSituacaoLigAgua(ordemServico.getImovel().getLigacaoAguaSituacao().getDescricao());
				form.setSituacaoLigEsgoto(ordemServico.getImovel().getLigacaoEsgotoSituacao().getDescricao());
				
				 //CLIENTE USUARIO DO IMOVEL
				 Cliente cliente = pesquisarClienteUsuarioImovel(ordemServico.getImovel().getId());
				 form.setNomeCliente(cliente.getNome());
				 
				 //CPF ou CNPJ do Cliente
				 if ( cliente.getCpf() != null ) {
					 form.setCpfCnpjCliente( cliente.getCpfFormatado() );
				 } else if ( cliente.getCnpj() != null ) {
					 form.setCpfCnpjCliente( cliente.getCnpjFormatado() );
				 }
				
			}
		
			ServicoTipo servicoTipo = ordemServico.getServicoTipo();
			//4.Dados das Informa��es para o boletim:
			verificarInformacoesBoletimMedicao(ordemServico.getId(),servicoTipo,form);
			
		} else {
			
			httpServletRequest.setAttribute("nomeCampo", "idOrdemServico");
			
			form.setIdOrdemServico("");
			form.setNomeOrdemServico("Ordem de Servi�o inexistente");
			
			form.setIdImovel("");
			form.setInscricaoImovel("");
			form.setSituacaoLigAgua("");
			form.setSituacaoLigEsgoto("");
			form.setNomeCliente("");
			form.setCpfCnpjCliente(""); 
		}
		
		return ordemServico;
	}
	

	/**
	 * Pesquisa Cliente Usu�rio
	 */
	private Cliente pesquisarClienteUsuarioImovel(Integer idImovel) {
		
		 Cliente cliente = Fachada.getInstancia().
		 	pesquisarClienteUsuarioImovel(idImovel);

		return cliente;
	}
	
	/**
     * [UC1116] Atualizar Informa��es da OS para Boletim de Medi��o
     * 
     * @author Vivianne Sousa
     * @created 02/02/2011
     */
    private void verificarInformacoesBoletimMedicao(
    		Integer idOrdemServico,ServicoTipo servicoTipo,
    		AtualizarInformacoesOSBoletimMedicaoActionForm form) {
		
    	String exibeIndicadorExistePavimento = "2";
    	String exibeQtdeReposicaoAsfalto = "2";
    	String exibeQtdeReposicaoCalcada = "2";
    	String exibeQtdeReposicaoParalelo = "2";
    	
//		ServicoTipo servicoTipo = getFachada().
//		 	recuperaServicoTipoDaOrdemServico(idOrdemServico);
		
		if(servicoTipo.getIndicadorBoletim().equals(ConstantesSistema.SIM)){
			//Caso o indicador do servi�o da ordem de servi�o 
			//que est� sendo encerrada tenha indicador para obter 
			//as informa��es para gera��o do boletim de medi��o 
			
			ServicoTipoBoletim servicoTipoBoletim = getFachada().
				recuperaServicoTipoBoletimDoServicoTipo(servicoTipo.getId());
			
			if(servicoTipoBoletim != null){
				
				//1.1.Caso o indicador de pavimento esteja solicitando
				//a informa��o da exist�ncia de pavimento 
				if(servicoTipoBoletim.getIndicadorPavimento().equals(ConstantesSistema.SIM)){
					//1.1.1.O sistema dever� solicitar a informa��o 
					//de exist�ncia do pavimento (Sim ou N�o, obrigatoriamente)
					exibeIndicadorExistePavimento = "1";
				}
				
				//1.2.Caso o indicador de quantidade de reposi��o em m� 
				//de asfalto esteja solicitando a informa��o do valor 
				if(servicoTipoBoletim.getIndicadorReposicaoAsfalto().equals(ConstantesSistema.SIM)){
					//1.2.1.O sistema dever� solicitar a informa��o da quantidade de reposi��o em m� de asfalto.
					//[FS0011 � Validar a quantidade m�]
					exibeQtdeReposicaoAsfalto = "1";
				}

				//1.3.Caso o indicador de quantidade de reposi��o em m� 
				//de paralelo esteja solicitando a informa��o do valor 
				if(servicoTipoBoletim.getIndicadorReposicaoParalelo().equals(ConstantesSistema.SIM)){
					//1.3.1.O sistema dever� solicitar a informa��o da quantidade de reposi��o em m� de asfalto.
					//[FS0011 � Validar a quantidade m�]
					exibeQtdeReposicaoParalelo = "1";
					
				}
				
				//1.4.Caso o indicador de quantidade de reposi��o em m� 
				//de cal�ada esteja solicitando a informa��o do valor 
				if(servicoTipoBoletim.getIndicadorReposicaoCalcada().equals(ConstantesSistema.SIM)){
					//1.4.1.O sistema dever� solicitar a informa��o da quantidade de reposi��o em m� de cal�ada.
					//[FS0011 � Validar a quantidade m�]
					exibeQtdeReposicaoCalcada = "1";
				}
				
				
				OrdemServicoBoletim ordemServicoBoletim = getFachada().
				recuperaOrdemServicoBoletimDaOS(idOrdemServico);
			
				if(ordemServicoBoletim != null){
					
					form.setIndicadorExistePavimento(ordemServicoBoletim.getIndicadorPavimento().toString());
					
					if(ordemServicoBoletim.getNumeroReposicaoAsfalto() != null){
						
						form.setQtdeReposicaoAsfalto(Util.formatarBigDecimalParaStringComVirgula(
								ordemServicoBoletim.getNumeroReposicaoAsfalto()));
					}else{
						form.setQtdeReposicaoAsfalto(null);
					}
					
					if(ordemServicoBoletim.getNumeroReposicaoParalelo() != null){
						
						form.setQtdeReposicaoParalelo(Util.formatarBigDecimalParaStringComVirgula(
								ordemServicoBoletim.getNumeroReposicaoParalelo()));
					}else{
						form.setQtdeReposicaoParalelo(null);
					}
					
					if(ordemServicoBoletim.getNumeroReposicaoCalcada() != null){
						
						form.setQtdeReposicaoCalcada(Util.formatarBigDecimalParaStringComVirgula(
								ordemServicoBoletim.getNumeroReposicaoCalcada()));
					}else{
						form.setQtdeReposicaoCalcada(null);
					}
					
				}else{
					form.setIndicadorExistePavimento(null);
					form.setQtdeReposicaoAsfalto(null);
					form.setQtdeReposicaoParalelo(null);
					form.setQtdeReposicaoCalcada(null);
				}
					
				
				
			}else{
				//Caso a Ordem de Servi�o n�o tenha informa��es cadastradas para o boletim
				//(ORSE_ID da tabela ORDEM_SERVICO n�o existente na tabela ORDEM_SERVICO_BOLETIM
				//exibir a mensagem �N�o existem informa��es desta ordem de servi�o para boletim�
				throw new ActionServletException("atencao.nao.existe.informacoes.boletim");
			}
		}
		
		form.setExibeIndicadorExistePavimento(exibeIndicadorExistePavimento);
		form.setExibeQtdeReposicaoAsfalto(exibeQtdeReposicaoAsfalto);
		form.setExibeQtdeReposicaoParalelo(exibeQtdeReposicaoParalelo);
		form.setExibeQtdeReposicaoCalcada(exibeQtdeReposicaoCalcada);
		
	}
}
