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
package gsan.gui.atendimentopublico.registroatendimento;


import gsan.atendimentopublico.ordemservico.OrdemServico;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.struts.action.ActionForm;

public class ConsultarRegistroAtendimentoOSActionForm extends ActionForm{
	private static final long serialVersionUID = 1L;
	//Dados Gerais
    private String numeroRA;
    private String situacaoRA;
    private String tipoSolicitacao;
    private String tipoSolicitacaoId;
    private String especificacao;
    private String especificacaoId;
    
    private String idUnidadeAtual;
    private String unidadeAtual;

    // OS
    private String osId;
    private String tipoServicoId;
    private String tipoServicoDescricao;
    private String dataGeracao;
    private String situacao;
    
    //Cole��o de OS
    private Collection<OrdemServico> colecaoOS = new ArrayList();

	public Collection<OrdemServico> getColecaoOS() {
		return colecaoOS;
	}

	public void setColecaoOS(Collection<OrdemServico> colecaoOS) {
		this.colecaoOS = colecaoOS;
	}

	public String getDataGeracao() {
		return dataGeracao;
	}

	public void setDataGeracao(String dataGeracao) {
		this.dataGeracao = dataGeracao;
	}

	public String getEspecificacao() {
		return especificacao;
	}

	public void setEspecificacao(String especificacao) {
		this.especificacao = especificacao;
	}

	public String getIdUnidadeAtual() {
		return idUnidadeAtual;
	}

	public void setIdUnidadeAtual(String idUnidadeAtual) {
		this.idUnidadeAtual = idUnidadeAtual;
	}

	public String getNumeroRA() {
		return numeroRA;
	}

	public void setNumeroRA(String numeroRA) {
		this.numeroRA = numeroRA;
	}

	public String getOsId() {
		return osId;
	}

	public void setOsId(String osId) {
		this.osId = osId;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public String getSituacaoRA() {
		return situacaoRA;
	}

	public void setSituacaoRA(String situacaoRA) {
		this.situacaoRA = situacaoRA;
	}

	public String getTipoServicoDescricao() {
		return tipoServicoDescricao;
	}

	public void setTipoServicoDescricao(String tipoServicoDescricao) {
		this.tipoServicoDescricao = tipoServicoDescricao;
	}

	public String getTipoServicoId() {
		return tipoServicoId;
	}

	public void setTipoServicoId(String tipoServicoId) {
		this.tipoServicoId = tipoServicoId;
	}

	public String getTipoSolicitacao() {
		return tipoSolicitacao;
	}

	public void setTipoSolicitacao(String tipoSolicitacao) {
		this.tipoSolicitacao = tipoSolicitacao;
	}

	public String getUnidadeAtual() {
		return unidadeAtual;
	}

	public void setUnidadeAtual(String unidadeAtual) {
		this.unidadeAtual = unidadeAtual;
	}

	public String getEspecificacaoId() {
		return especificacaoId;
	}

	public void setEspecificacaoId(String especificacaoId) {
		this.especificacaoId = especificacaoId;
	}

	public String getTipoSolicitacaoId() {
		return tipoSolicitacaoId;
	}

	public void setTipoSolicitacaoId(String tipoSolicitacaoId) {
		this.tipoSolicitacaoId = tipoSolicitacaoId;
	}

}