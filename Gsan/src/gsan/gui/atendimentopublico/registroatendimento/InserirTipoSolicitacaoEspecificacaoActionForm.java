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

import gsan.atendimentopublico.registroatendimento.ArquivoProcedimentoOperacionalPadrao;

import org.apache.struts.upload.FormFile;
import org.apache.struts.validator.ValidatorActionForm;

public class InserirTipoSolicitacaoEspecificacaoActionForm extends
		ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String descricao;

	private String idgrupoTipoSolicitacao;

	private String indicadorFaltaAgua;
	
	private String indicadorTarifaSocial;
	
	private String indicadorUsoSistema;
    
    private String idSolicitacaoTipoEspecificacao;
    
    private String indicadorUrgencia;
    
    private FormFile arquivo;
    
    private String indicadorDocumentoObrigatorio;
       
    public String getIdSolicitacaoTipoEspecificacao() {
        return idSolicitacaoTipoEspecificacao;
    }

    
    public void setIdSolicitacaoTipoEspecificacao(
            String idSolicitacaoTipoEspecificacao) {
        this.idSolicitacaoTipoEspecificacao = idSolicitacaoTipoEspecificacao;
    }

    public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getIdgrupoTipoSolicitacao() {
		return idgrupoTipoSolicitacao;
	}

	public void setIdgrupoTipoSolicitacao(String idgrupoTipoSolicitacao) {
		this.idgrupoTipoSolicitacao = idgrupoTipoSolicitacao;
	}

	public String getIndicadorFaltaAgua() {
		return indicadorFaltaAgua;
	}

	public void setIndicadorFaltaAgua(String indicadorFaltaAgua) {
		this.indicadorFaltaAgua = indicadorFaltaAgua;
	}

	public String getIndicadorTarifaSocial() {
		return indicadorTarifaSocial;
	}

	public void setIndicadorTarifaSocial(String indicadorTarifaSocial) {
		this.indicadorTarifaSocial = indicadorTarifaSocial;
	}

	/**
	 * @return Retorna o campo indicadorUsoSistema.
	 */
	public String getIndicadorUsoSistema() {
		return indicadorUsoSistema;
	}

	/**
	 * @param indicadorUsoSistema O indicadorUsoSistema a ser setado.
	 */
	public void setIndicadorUsoSistema(String indicadorUsoSistema) {
		this.indicadorUsoSistema = indicadorUsoSistema;
	}


	public String getIndicadorUrgencia() {
		return indicadorUrgencia;
	}


	public void setIndicadorUrgencia(String indicadorUrgencia) {
		this.indicadorUrgencia = indicadorUrgencia;
	}
	

	public FormFile getArquivo() {
		return arquivo;
	}


	public void setArquivo(FormFile arquivo) {
		this.arquivo = arquivo;
	}


	public String getIndicadorDocumentoObrigatorio() {
		return indicadorDocumentoObrigatorio;
	}


	public void setIndicadorDocumentoObrigatorio(String indicadorDocumentoObrigatorio) {
		this.indicadorDocumentoObrigatorio = indicadorDocumentoObrigatorio;
	}
	
}
